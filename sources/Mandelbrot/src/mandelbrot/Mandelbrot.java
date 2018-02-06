package mandelbrot;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import edu.davidson.display.SNumber;

public class Mandelbrot extends Applet {
    boolean isStandalone = false;
    boolean viewOnTop=true;
    BorderLayout borderLayout1 = new BorderLayout();
    Panel etchedBorder1 = new Panel();
    CardLayout cardLayout1 = new CardLayout();
    Panel viewPanel = new Panel();
    BorderLayout borderLayout2 = new BorderLayout();
    Button stopBtn = new Button();
    Panel controlPanel = new Panel();
    Button startBtn = new Button();
    Button zoomBtn = new Button();
    FlowLayout flowLayout1 = new FlowLayout();
    edu.davidson.graphics.ColumnLayout columnLayout=new edu.davidson.graphics.ColumnLayout(4);
    edu.davidson.graphics.ColumnLayout columnLayout2=new edu.davidson.graphics.ColumnLayout(4);
    Panel rangePanel = new Panel();
    Panel panel3 = new Panel();
    GridLayout gridLayout1 = new GridLayout();
    Label label5 = new Label();
    Label label7 = new Label();
    SNumber reMaxField = new SNumber();
    SNumber reMinField = new SNumber();
    SNumber imMinField = new SNumber();
    SNumber imMaxField = new SNumber();
    Label label6 = new Label();
    Label label8 = new Label();
    Label label9 = new Label();
    Panel panel1 = new Panel();
    Label label1 = new Label();
    FlowLayout flowLayout2 = new FlowLayout();
    GridLayout gridLayout2 = new GridLayout();
    boolean firstTime=true;
    boolean zoomOn=false;
    MandelbrotPanel mandelbrotPanel = new MandelbrotPanel();

  //Get a parameter value
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Construct the applet
  public Mandelbrot() {
     mandelbrotPanel.setOwner(this);
  }

  //Initialize the applet
  public void init() {
    try  {
      jbInit();
    }
    catch(Exception e)  {
      e.printStackTrace();
    }
    cardLayout1.show(etchedBorder1,"viewPanel");
    setMinMax( -2.0,1,-1.5,1.5);
  }


  //Component initialization
  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
        etchedBorder1.setLayout(cardLayout1);
        viewPanel.setLayout(borderLayout2);
        stopBtn.setLabel("Stop");
        stopBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                stopBtn_actionPerformed(e);
            }
        });
        startBtn.setLabel("Start");
        startBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                startBtn_actionPerformed(e);
            }
        });
        zoomBtn.setLabel("Zoom");
        zoomBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                zoomBtn_actionPerformed(e);
            }
        });
        rangePanel.setLayout(columnLayout);
        mandelbrotPanel.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }
        });
        rangePanel.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }
        });
        label1.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }
        });
        label6.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }
        });
        label7.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }
        });
        label8.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }
        });
        label9.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }
        });
        panel3.setLayout(gridLayout1);
        gridLayout1.setHgap(2);
        gridLayout1.setRows(3);
        gridLayout1.setVgap(2);
        label7.setAlignment(2);
        label7.setText("Im");
        label6.setAlignment(2);
        label6.setText("Re");
        label8.setAlignment(1);
        label8.setText("Max");
        label9.setAlignment(1);
        label9.setText("Min");
        label5.setAlignment(1);
        label1.setFont(new java.awt.Font("Dialog", 1, 14));
        label1.setAlignment(1);
        label1.setText("      Mandelbrot Limits");
        //panel1.setLayout(FlowLayout.CENTER);
        flowLayout2.setAlignment(FlowLayout.RIGHT);
        panel1.setLayout(gridLayout2);
        controlPanel.setBackground(Color.lightGray);
        this.add(etchedBorder1, BorderLayout.CENTER);
        etchedBorder1.add(viewPanel, "viewPanel");
        viewPanel.add(mandelbrotPanel, BorderLayout.CENTER);
        viewPanel.add(controlPanel, BorderLayout.SOUTH);
        controlPanel.add(startBtn, null);
        controlPanel.add(stopBtn, null);
        controlPanel.add(zoomBtn, null);
        etchedBorder1.add(rangePanel, "rangePanel");
        rangePanel.add(panel1, null);
        panel1.add(label1, null);
        rangePanel.add(panel3, null);
        panel3.add(label5, null);
        panel3.add(label9, null);
        panel3.add(label8, null);
        panel3.add(label6, null);
        panel3.add(reMinField, null);
        panel3.add(reMaxField, null);
        panel3.add(label7, null);
        panel3.add(imMinField, null);
        panel3.add(imMaxField, null);
  }

  //Get Applet information
  public String getAppletInfo() {
    return "Applet Information";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    return null;
  }


void this_mousePressed(MouseEvent e) {
      if(( e.getModifiers() & InputEvent.BUTTON3_MASK)!=0){
           //System.out.println("right click");
           if( viewOnTop){
               cardLayout1.show(etchedBorder1,"rangePanel");
            }else{
               cardLayout1.show(etchedBorder1,"viewPanel");
               checkMinMax();
            }
            viewOnTop = !viewOnTop;
      }
      /* else{
        if(zoomOn){
           //System.out.println("left click");
           Mandelbrot mandelbrot= new Mandelbrot();
           mandelbrot.init();
           MandelFrame mandelFrame=new MandelFrame(mandelbrot);
           mandelFrame.setSize(this.getSize().width,this.getSize().height);
           mandelFrame.show();
           mandelbrot.start();
           zoomOn=false;
        }
      }*/
    }

    void clone(double remin,double remax,double immin,double immax){
           if(remin==remax || immin==immax) return;
           Mandelbrot mandelbrot= new Mandelbrot();
           mandelbrot.init();
           mandelbrot.setMinMax(remin, remax, immin, immax);
           MandelFrame mandelFrame=new MandelFrame(mandelbrot);
           double ratio= (immax-immin)/(remax-remin);
           int width=mandelbrotPanel.getSize().width;
           int height=mandelbrotPanel.getSize().height;
           if(ratio>1){
             height= height+controlPanel.getSize().height;
             width=(int)(mandelbrotPanel.getSize().height/ratio);
           }else{
             width=mandelbrotPanel.getSize().width;
             height=(int)(mandelbrotPanel.getSize().width*ratio+controlPanel.getSize().height);
           }
           width=Math.max(20,width);// make sure there is a minimum width
           height=Math.max(20+controlPanel.getSize().height,height);
           mandelFrame.setSize(width,height);
           mandelFrame.show();
           mandelbrot.start();
           zoomOn=false;
    }

    void startBtn_actionPerformed(ActionEvent e) {
        mandelbrotPanel.start();
    }

    void stopBtn_actionPerformed(ActionEvent e) {
        mandelbrotPanel.stop();
    }

    public void destroy(){mandelbrotPanel.stop();}
    public void start(){
        if(firstTime){
            firstTime=false;
            mandelbrotPanel.start();
        }
    }

    public void setMinMax(double remin,double remax,double immin,double immax){
        double temp=0;
        if(remin>remax){
            temp=remin;
            remin=remax;
            remax=temp;
        }
        if(immin>immax){
            temp=immin;
            immin=immax;
            immax=temp;
        }
        reMinField.setValue(remin);
        reMaxField.setValue(remax);
        imMinField.setValue(immin);
        imMaxField.setValue(immax);
        mandelbrotPanel.reMin=remin;
        mandelbrotPanel.reMax=remax;
        mandelbrotPanel.imMin=immin;
        mandelbrotPanel.imMax=immax;

    }

    public void checkMinMax(){
        double remin=reMinField.getValue();
        double remax=reMaxField.getValue();
        double immin=imMinField.getValue();
        double immax=imMaxField.getValue();
        if(remin!= mandelbrotPanel.reMin || remax!= mandelbrotPanel.reMax ||
           immin!= mandelbrotPanel.imMin || immax!= mandelbrotPanel.imMax){
            mandelbrotPanel.stop();
            setMinMax( remin, remax, immin, immax);
            mandelbrotPanel.start();
        }
    }

    void zoomBtn_actionPerformed(ActionEvent e) {
      zoomOn=true;
    }
}
