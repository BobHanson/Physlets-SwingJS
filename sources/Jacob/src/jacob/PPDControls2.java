package jacob;

import a2s.*;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


class PPDControls2
  extends Panel
{
  Slider e_slider;
  Slider m_slider;
  Slider b_slider;
  Slider s_slider;
  Slider ae_slider;
  Slider am_slider;
  double e_max = 150.0D;
  double m_max = 0.004D;
  double b_max = 1.0D;
  double s_max = 20.0D;
  double ae_max = 1000.0D;
  double am_max = 1000.0D;
  
  public PPDControls2()
  {
    setForeground(PPD.CONTROLS_FOREGROUND);
    GridBagLayout localGridBagLayout = new GridBagLayout();
    GridBagConstraints localGridBagConstraints = new GridBagConstraints();
    setLayout(localGridBagLayout);
    localGridBagConstraints.insets = new Insets(10, 10, 0, 10);
    localGridBagConstraints.fill = 0;
    localGridBagConstraints.weightx = 1.0D;
    localGridBagConstraints.weighty = 1.0D;
    localGridBagConstraints.gridheight = 1;
    localGridBagConstraints.anchor = 11;
    localGridBagConstraints.gridwidth = 0;
    this.e_slider = new Slider();
    localGridBagLayout.setConstraints(this.e_slider, localGridBagConstraints);
    add(this.e_slider);
    localGridBagConstraints.insets = new Insets(0, 10, 15, 10);
    Label localLabel = new Label(PPD.CONTROLS_ESTEP);
    localGridBagLayout.setConstraints(localLabel, localGridBagConstraints);
    add(localLabel);
    localGridBagConstraints.insets = new Insets(10, 10, 0, 10);
    this.m_slider = new Slider();
    localGridBagLayout.setConstraints(this.m_slider, localGridBagConstraints);
    add(this.m_slider);
    localGridBagConstraints.insets = new Insets(0, 10, 15, 10);
    localLabel = new Label(PPD.CONTROLS_MSTEP);
    localGridBagLayout.setConstraints(localLabel, localGridBagConstraints);
    add(localLabel);
    localGridBagConstraints.insets = new Insets(10, 10, 0, 10);
    this.b_slider = new Slider();
    localGridBagLayout.setConstraints(this.b_slider, localGridBagConstraints);
    add(this.b_slider);
    localGridBagConstraints.insets = new Insets(0, 10, 15, 10);
    localLabel = new Label(PPD.CONTROLS_BSTEP);
    localGridBagLayout.setConstraints(localLabel, localGridBagConstraints);
    add(localLabel);
    localGridBagConstraints.insets = new Insets(10, 10, 0, 10);
    this.ae_slider = new Slider();
    localGridBagLayout.setConstraints(this.ae_slider, localGridBagConstraints);
    add(this.ae_slider);
    localGridBagConstraints.insets = new Insets(0, 10, 15, 10);
    localLabel = new Label(PPD.CONTROLS_ASTEP);
    localGridBagLayout.setConstraints(localLabel, localGridBagConstraints);
    add(localLabel);
    localGridBagConstraints.insets = new Insets(10, 10, 0, 10);
    this.am_slider = new Slider();
    localGridBagLayout.setConstraints(this.am_slider, localGridBagConstraints);
    add(this.am_slider);
    localGridBagConstraints.insets = new Insets(0, 10, 15, 10);
    localLabel = new Label(PPD.CONTROLS_ASTEP_MAG);
    localGridBagLayout.setConstraints(localLabel, localGridBagConstraints);
    add(localLabel);
    localGridBagConstraints.insets = new Insets(10, 10, 0, 10);
    this.s_slider = new Slider();
    localGridBagLayout.setConstraints(this.s_slider, localGridBagConstraints);
    add(this.s_slider);
    localGridBagConstraints.insets = new Insets(0, 10, 15, 10);
    localLabel = new Label(PPD.CONTROLS_SUSC);
    localGridBagLayout.setConstraints(localLabel, localGridBagConstraints);
    add(localLabel);
    setBackground(PPD.CONTROLS_BACKGROUND);
    updateControls();
  }
  
  public boolean handleEvent(Event paramEvent)
  {
    Object localObject = paramEvent.arg;
    if ((paramEvent.target instanceof Slider))
    {
      updateValues();
      return true;
    }
    return false;
  }
  
  public void updateControls()
  {
    this.e_slider.setValue(PPD.electric_const / this.e_max);
    this.m_slider.setValue(PPD.magnetic_const / this.m_max);
    this.b_slider.setValue(PPD.B_const / this.b_max);
    this.s_slider.setValue(PPD.suscept / this.s_max);
  }
  
  public synchronized void updateValues()
  {
    PPD.electric_const = this.e_slider.getValue() * this.e_max;
    PPD.magnetic_const = this.m_slider.getValue() * this.m_max;
    PPD.B_const = this.b_slider.getValue() * this.b_max;
    PPD.suscept = this.s_slider.getValue() * this.s_max;
    if (this.m_slider.getValue() == 0.0D) {
      PPD.magnetism = false;
    } else {
      PPD.magnetism = true;
    }
    PPD.arrow_scale = this.ae_slider.getValue() * this.ae_max;
    PPD.arrow_mag_scale = this.am_slider.getValue() * this.am_max;
    if (PPD.arrow_scale == 0.0D) {
      PPD.arrows = false;
    } else {
      PPD.arrows = true;
    }
    if (PPD.arrow_mag_scale == 0.0D) {
      PPD.arrows_mag = false;
    } else {
      PPD.arrows_mag = true;
    }
  }
  
  public void paint(Graphics paramGraphics)
  {
    Color localColor1 = getBackground();
    Color localColor2 = Draw.brighter(localColor1, 0.8D);
    Color localColor3 = Draw.darker(localColor1, 0.65D);
    int i = size().width - 1;
    int j = size().height - 1;
    paramGraphics.setColor(localColor2);
    paramGraphics.drawLine(0, 0, i, 0);
    paramGraphics.drawLine(0, 0, 0, j);
    paramGraphics.setColor(localColor3);
    paramGraphics.drawLine(i, 0, i, j);
    paramGraphics.drawLine(0, j, i, j);
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/PPDControls2.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */