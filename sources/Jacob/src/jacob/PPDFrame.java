package jacob;

import a2s.*;
import java.awt.Event;
import java.awt.FileDialog;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class PPDFrame
  extends Frame
{
  PPDCanvas ppdCanvas;
  PPDControls ppdControls;
  PPDControls2 ppdControls2;
  MenuBar menuBar;
  Menu fileMenu;
  Menu numMenu;
  Menu createMenu;
  Menu visual3dMenu;
  Menu editMenu;
  Menu forceMenu;
  Menu miscMenu;
  Menu plotMenu;
  ActionLabel actionLabel;
  FileDialog fileLoadDialog;
  FileDialog fileSaveDialog;
  
  public PPDFrame()
  {
    super(PPD.PPD_NAME);
    addNotify();
    this.fileMenu = new Menu(PPD.FILEMENU);
    this.fileMenu.add(new MenuItem(PPD.FILEMENU_OPEN));
    this.fileMenu.add(new MenuItem(PPD.FILEMENU_SAVE));
    this.fileMenu.addSeparator();
    this.fileMenu.add(new MenuItem(PPD.FILEMENU_QUIT));
    this.numMenu = new Menu(PPD.NUMMENU);
    this.numMenu.add(new MenuItem(PPD.PARTICLE1));
    this.numMenu.add(new MenuItem(PPD.PARTICLE5));
    this.numMenu.add(new MenuItem(PPD.PARTICLE10));
    this.createMenu = new Menu(PPD.CREATEMENU);
    this.createMenu.add(new MenuItem(PPD.CREATEMENU_CIRCLE));
    this.createMenu.add(new MenuItem(PPD.CREATEMENU_RECTANGLE));
    this.createMenu.add(new MenuItem(PPD.CREATEMENU_RING));
    this.createMenu.addSeparator();
    this.createMenu.add(new MenuItem(PPD.CREATEMENU_PARTICLE_POS));
    this.createMenu.add(new MenuItem(PPD.CREATEMENU_PARTICLE_NEG));
    this.createMenu.add(new MenuItem(PPD.CREATEMENU_DIPOL));
    this.createMenu.addSeparator();
    this.createMenu.add(this.numMenu);
    this.visual3dMenu = new Menu(PPD.VISUAL3DMENU);
    this.visual3dMenu.add(new MenuItem(PPD.VISUAL3DMENU_INC));
    this.visual3dMenu.add(new MenuItem(PPD.VISUAL3DMENU_DEC));
    this.visual3dMenu.add(new MenuItem(PPD.VISUAL3DMENU_INV));
    this.editMenu = new Menu(PPD.ACTIONMENU_EDITMENU);
    this.editMenu.add(new MenuItem(PPD.ACTIONMENU_DELETE));
    this.editMenu.add(new MenuItem(PPD.ACTIONMENU_MOVE));
    this.editMenu.add(new MenuItem(PPD.ACTIONMENU_SCALE));
    this.forceMenu = new Menu(PPD.ACTIONMENU_FORCEMENU);
    this.forceMenu.add(new MenuItem(PPD.ACTIONMENU_FORCE_SET));
    this.forceMenu.add(new MenuItem(PPD.ACTIONMENU_FORCE_DEL));
    this.forceMenu.add(new MenuItem(PPD.ACTIONMENU_FORCE_CIRC));
    this.forceMenu.add(new MenuItem(PPD.ACTIONMENU_FORCE_ROTATE));
    this.forceMenu.add(new MenuItem(PPD.ACTIONMENU_FORCE_TRANSLATE));
    this.miscMenu = new Menu(PPD.ACTIONMENU_MISCMENU);
    this.miscMenu.add(new MenuItem(PPD.ACTIONMENU_TAG_MOVE));
    this.miscMenu.add(new MenuItem(PPD.ACTIONMENU_TAG_IO));
    this.miscMenu.add(new MenuItem(PPD.ACTIONMENU_CONNECT));
    this.miscMenu.add(new MenuItem(PPD.ACTIONMENU_SET_CONST_ZERO));
    this.miscMenu.addSeparator();
    this.miscMenu.add(new MenuItem(PPD.ACTIONMENU_SET_CONST));
    this.miscMenu.add(new MenuItem(PPD.ACTIONMENU_SET_ZERO));
    this.miscMenu.addSeparator();
    this.miscMenu.add(this.visual3dMenu);
    this.plotMenu = new Menu(PPD.ACTIONMENU_PLOTMENU);
    this.plotMenu.add(new MenuItem(PPD.ACTIONMENU_PLOT_LINT));
    this.plotMenu.add(new MenuItem(PPD.ACTIONMENU_PLOT_POTENTIAL));
    this.plotMenu.add(new MenuItem(PPD.ACTIONMENU_PLOT_ENERGY));
    this.plotMenu.add(new MenuItem(PPD.ACTIONMENU_PLOT_CURR));
    this.plotMenu.addSeparator();
    this.plotMenu.add(new MenuItem(PPD.ACTIONMENU_PLOT_FIELD));
    this.menuBar = new MenuBar();
    if (Common.isApp())
    {
      this.menuBar.add(this.fileMenu);
      this.fileLoadDialog = new FileDialog(this, "Load", 0);
      this.fileSaveDialog = new FileDialog(this, "Save", 1);
    }
    this.menuBar.add(this.createMenu);
    this.menuBar.add(this.editMenu);
    this.menuBar.add(this.forceMenu);
    this.menuBar.add(this.miscMenu);
    this.menuBar.add(this.plotMenu);
    setMenuBar(this.menuBar);
    this.ppdControls = new PPDControls();
    PPD.ppdControls = this.ppdControls2 = new PPDControls2();
    this.ppdCanvas = new PPDCanvas();
    this.actionLabel = new ActionLabel(PPD.ACTIONLABEL_MOVE, true);
    add("North", this.ppdControls);
    add("East", this.ppdControls2);
    add("Center", this.ppdCanvas);
    add("South", this.actionLabel);
    resize(PPD.PPD_WIDTH, PPD.PPD_HEIGHT);
    start();
    show();
  }
  
  public void start()
  {
    PPD.animate = false;
    PPD.draw_field = false;
    PPD.arrows = false;
    PPD.magnetism = false;
    this.ppdCanvas.start();
  }
  
  public void stop()
  {
    this.ppdCanvas.stop();
    PPD.particleArray = null;
    PPD.elementBox.removeAllElements();
    PPD.particleBox.removeAllElements();
  }
  
  public boolean handleEvent(Event paramEvent)
  {
    Object localObject = paramEvent.arg;
    if (paramEvent.id == 201)
    {
      Common.stopPPD();
    }
    else if (paramEvent.id == 401)
    {
      switch (paramEvent.key)
      {
      case 43: 
        PPD.SHADOW_OFFSET += 1;
        this.ppdCanvas.redraw();
        break;
      case 45: 
        PPD.SHADOW_OFFSET -= 1;
        this.ppdCanvas.redraw();
        break;
      case 60: 
        if ((this.ppdCanvas.currentElement == null) || (this.ppdCanvas.currentElement.period <= 1.0D)) {
          break;
        }
        this.ppdCanvas.currentElement.period -= 1.0D;
        break;
      case 62: 
        if (this.ppdCanvas.currentElement == null) {
          break;
        }
        this.ppdCanvas.currentElement.period += 1.0D;
        break;
      }
    }
    else if ((paramEvent.target instanceof MenuItem))
    {
      Menu localMenu = (Menu)((MenuItem)paramEvent.target).getParent();
      String str = (String)localObject;
      if (localMenu == this.fileMenu)
      {
        if (str.equals(PPD.FILEMENU_OPEN))
        {
          this.fileLoadDialog.show();
          PPD.particleBox.removeAllElements();
          PPD.elementBox.removeAllElements();
          try
          {
            Data.loadFile(PPD.particleBox, PPD.elementBox, new FileInputStream(this.fileLoadDialog.getDirectory() + this.fileLoadDialog.getFile()));
          }
          catch (Exception localException1)
          {
            System.out.println("load failed: " + localException1.getMessage());
          }
          Calculus.registerNeightbours();
          Calculus.correctParticles();
          PPD.particleArray = new Particle[PPD.particleBox.size()];
          PPD.particleBox.copyInto(PPD.particleArray);
          this.ppdCanvas.repaint();
        }
        else if (str.equals(PPD.FILEMENU_SAVE))
        {
          this.fileSaveDialog.show();
          try
          {
            Data.saveFile(PPD.particleBox, PPD.elementBox, new FileOutputStream(this.fileSaveDialog.getDirectory() + this.fileSaveDialog.getFile()));
          }
          catch (Exception localException2)
          {
            System.out.println("save failed: " + localException2.getMessage());
          }
        }
        else if (str.equals(PPD.FILEMENU_QUIT))
        {
          Common.stopPPD();
        }
      }
      else if (localMenu == this.createMenu)
      {
        if (str.equals(PPD.CREATEMENU_CIRCLE))
        {
          this.ppdCanvas.currentAction = 4096;
          this.actionLabel.setText(PPD.ACTIONLABEL_CREATE_CIRCLE);
        }
        else if (str.equals(PPD.CREATEMENU_RECTANGLE))
        {
          this.ppdCanvas.currentAction = 4097;
          this.actionLabel.setText(PPD.ACTIONLABEL_CREATE_RECTANGLE);
        }
        else if (str.equals(PPD.CREATEMENU_RING))
        {
          this.ppdCanvas.currentAction = 4098;
          this.actionLabel.setText(PPD.ACTIONLABEL_CREATE_RING);
        }
        else if (str.equals(PPD.CREATEMENU_PARTICLE_POS))
        {
          this.ppdCanvas.currentAction = 4352;
          this.actionLabel.setText(PPD.ACTIONLABEL_CREATE_PARTICLE_POS);
        }
        else if (str.equals(PPD.CREATEMENU_PARTICLE_NEG))
        {
          this.ppdCanvas.currentAction = 4353;
          this.actionLabel.setText(PPD.ACTIONLABEL_CREATE_PARTICLE_NEG);
        }
        else if (str.equals(PPD.CREATEMENU_DIPOL))
        {
          this.ppdCanvas.currentAction = 4354;
          this.actionLabel.setText(PPD.ACTIONLABEL_CREATE_DIPOL);
        }
      }
      else if ((localMenu == this.editMenu) || (localMenu == this.forceMenu) || (localMenu == this.miscMenu) || (localMenu == this.plotMenu))
      {
        if (str.equals(PPD.ACTIONMENU_DELETE))
        {
          this.ppdCanvas.currentAction = 4608;
          this.actionLabel.setText(PPD.ACTIONLABEL_DELETE);
        }
        else if (str.equals(PPD.ACTIONMENU_MOVE))
        {
          this.ppdCanvas.currentAction = 4609;
          this.actionLabel.setText(PPD.ACTIONLABEL_MOVE);
        }
        else if (str.equals(PPD.ACTIONMENU_SCALE))
        {
          this.ppdCanvas.currentAction = 4610;
          this.actionLabel.setText(PPD.ACTIONLABEL_SCALE);
        }
        else if (str.equals(PPD.ACTIONMENU_FORCE_SET))
        {
          this.ppdCanvas.currentAction = 4864;
          this.actionLabel.setText(PPD.ACTIONLABEL_FORCE_SET);
        }
        else if (str.equals(PPD.ACTIONMENU_FORCE_DEL))
        {
          this.ppdCanvas.currentAction = 4865;
          this.actionLabel.setText(PPD.ACTIONLABEL_FORCE_DEL);
        }
        else if (str.equals(PPD.ACTIONMENU_FORCE_CIRC))
        {
          this.ppdCanvas.currentAction = 4866;
          this.actionLabel.setText(PPD.ACTIONLABEL_FORCE_CIRC);
        }
        else if (str.equals(PPD.ACTIONMENU_FORCE_ROTATE))
        {
          this.ppdCanvas.currentAction = 4867;
          this.actionLabel.setText(PPD.ACTIONLABEL_FORCE_ROTATE);
        }
        else if (str.equals(PPD.ACTIONMENU_FORCE_TRANSLATE))
        {
          this.ppdCanvas.currentAction = 4868;
          this.actionLabel.setText(PPD.ACTIONLABEL_FORCE_TRANSLATE);
        }
        else if (str.equals(PPD.ACTIONMENU_TAG_MOVE))
        {
          this.ppdCanvas.currentAction = 5376;
          this.actionLabel.setText(PPD.ACTIONLABEL_TAG_MOVE);
        }
        else if (str.equals(PPD.ACTIONMENU_TAG_IO))
        {
          this.ppdCanvas.currentAction = 5632;
          this.actionLabel.setText(PPD.ACTIONLABEL_TAG_IO);
        }
        else if (str.equals(PPD.ACTIONMENU_SET_CONST_ZERO))
        {
          this.ppdCanvas.currentAction = 6656;
          this.actionLabel.setText(PPD.ACTIONLABEL_SET_CONST_ZERO);
        }
        else if (str.equals(PPD.ACTIONMENU_CONNECT))
        {
          this.ppdCanvas.currentAction = 6912;
          this.actionLabel.setText(PPD.ACTIONLABEL_CONNECT_1);
        }
        else if (str.equals(PPD.ACTIONMENU_SET_CONST))
        {
          for (int i = 0; i < PPD.particleBox.size(); i++) {
            ((Particle)PPD.particleBox.elementAt(i)).k += PPD.PARTICLE_K_INC;
          }
          this.ppdCanvas.redraw();
        }
        else if (str.equals(PPD.ACTIONMENU_PLOT_LINT))
        {
          this.ppdCanvas.currentAction = 6144;
          this.actionLabel.setText(PPD.ACTIONLABEL_PLOT_LINT);
        }
        else if (str.equals(PPD.ACTIONMENU_PLOT_POTENTIAL))
        {
          this.ppdCanvas.currentAction = 6400;
          this.actionLabel.setText(PPD.ACTIONLABEL_PLOT_POTENTIAL);
        }
        else if (str.equals(PPD.ACTIONMENU_PLOT_ENERGY))
        {
          this.ppdCanvas.currentAction = 7168;
          if (PPD.energyPlotter == null) {
            PPD.energyPlotter = new Plotter(PPD.ACTIONMENU_PLOT_ENERGY, 200, 100);
          }
          PPD.energyPlotter.show();
        }
        else if (str.equals(PPD.ACTIONMENU_PLOT_FIELD))
        {
          PPD.draw_field = !PPD.draw_field;
        }
        else if (str.equals(PPD.ACTIONMENU_PLOT_CURR))
        {
          this.ppdCanvas.currentAction = 7424;
          this.actionLabel.setText(PPD.ACTIONLABEL_PLOT_CURR);
        }
        else if (str.equals(PPD.ACTIONMENU_SET_ZERO))
        {
          this.ppdCanvas.currentAction = 5888;
          this.actionLabel.setText(PPD.ACTIONLABEL_SET_ZERO);
        }
      }
      else if (localMenu == this.numMenu)
      {
        if (str.equals(PPD.PARTICLE1)) {
          PPD.num_particles = 1;
        } else if (str.equals(PPD.PARTICLE5)) {
          PPD.num_particles = 5;
        } else if (str.equals(PPD.PARTICLE10)) {
          PPD.num_particles = 10;
        }
      }
      else if (localMenu == this.visual3dMenu)
      {
        if (str.equals(PPD.VISUAL3DMENU_INC))
        {
          if (PPD.SHADOW_OFFSET > 0) {
            PPD.SHADOW_OFFSET += 3;
          } else {
            PPD.SHADOW_OFFSET -= 3;
          }
        }
        else if (str.equals(PPD.VISUAL3DMENU_DEC))
        {
          if (PPD.SHADOW_OFFSET > 0) {
            PPD.SHADOW_OFFSET -= 3;
          } else {
            PPD.SHADOW_OFFSET += 3;
          }
        }
        else if (str.equals(PPD.VISUAL3DMENU_INV)) {
          PPD.SHADOW_OFFSET = -PPD.SHADOW_OFFSET;
        }
        this.ppdCanvas.redraw();
      }
      return true;
    }
    return false;
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/PPDFrame.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */