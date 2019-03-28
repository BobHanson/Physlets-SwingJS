package circuitsimulator;

//import a2s.*;
import java.awt.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import edu.davidson.display.Format;
import edu.davidson.tools.*;

/**
 * <p>Implements an element at the circuit.
 * </p>
 * <p>Different types of elements (subclasses) are:<br>
 * <ul><li>Nothing: removes another element
 * <li>Wire: connects two points with a conductor
 * <li>Resistor
 * <li>Capacitor
 * <li>Inductor
 * <li>Switch: opens/closes a connection
 * <li>Bulb: light bulb
 * <li>Source: Time dependent voltage source
 * <li>Battery: Constant voltage source
 * <li>SinWave, SquareWave: Special cases of voltage source
 * <li>Current Source: Constant current source
 * <li>Vmeter: Voltmeter (used in circuitbuilder)
 * <li>Ameter: Amperemeter (used in circuitbuilder)
 * <li>Scope: Oscilloscope (used in circuitbuilder)
 * <li>Probe: special element (see later)
 * <li>VGeneral, IGeneral: general V or I dependent elements
 * </ul></p>
 * <p>The construction should happen by an 'addObject' call in the basic circuit applet.
 * </p>
 * <p>Elements (not wire or nothing) all cause a new V-Equation when parsing:<br>
 * V_i-V_j=f(I_k,V_l) with i,j,k,m column indices.<br>
 * Two big matrices are build to translate the Kirchoff-rules.<br>
 * <ul><li>Resistor: V_i-V_j=R*I_k
 * <li>Capacitor: V_i-V_j=(1/C)*integral(I_k*dt)
 * <li>Inductor: V_i-V_j=L*d(I_k)/dt
 * <li>Source (all voltage sources), Battery: V_i-V_j=V_m
 * <li>CurrentSource: I_k=I
 * <li>VGeneral: V_i-V_j=f(I_k,V_i-V_j)
 * <li>IGeneral: I_k=f(I_k,V_i-V_j)
 * <li>Scope, Vmeter, Ameter: special resistors with R the internal resistance
 * <li>Switch: special resistor with R the internal resistance 0 or 1e20
 * <li>Bulb: special resistor with the resistance determined by V (voltage) and W (power)
 * </ul></p>
 * <p>A first order approximation is used for integration and differentiation.<br>
 * For the n_th time step this means:
 * <ul><li>Resistor:  -V_(i,n)+V_(j,n)+R*I_(k,n)=0
 * <li>Capacitor: -V_(i,n)+V_(j,n)+(dt/C)*I_(k,n)=-V_(i,n-1)+V_(j,n-1)
 * <li>Inductor:  -V_(i,n)+V_(j,n)+(L/dt)*I_(k,n)=(L/dt)*I_(k,n-1)
 * <li>Source: -V_(i,n)+V_(j,n)=V_(m,n)*(+/-1)
 * <li>CurrentSource: I(i,n)=I*(+/-1)
 * <li>VGeneral: -V_(i,n)+V(j,n)=-f(I_(k,n-1),V_(i,n-1)-V_(j,n-1))
 * <li>IGeneral: I_(k,n)=-f(I_(k,n-1),V_(i,n-1)-V_(j,n-1))
 * <li>Scope, Vmeter, Ameter: polarized (+/-) for the display</ul>
 * with i = index of potential before the element according to the chosen current direction<br>
 * j = index of potential after the element according to the chosen current direction<br>
 * k = index of current<br>
 * m = index of source input<br>
 * n = index of time step<br>
 * (+/-1) depends on the orientation of the source according to the chosen current direction<br>
 * </p>
 * <p>New elements can be made based on the basic elements by overriding the functions:
 * impedance(), differential(), integralVHere(), integralVNext(), input() or rightFunction()<br>
 * For non-linear behaviour (as in VGeneral or IGeneral):<br>
 * I_(k,n-1) is implemented as getI()<br>
 * V_(i,n-1)-V_(j,n-1) is implemented as getV()<br>
 * </p>
 * <p>The probe element is not a real circuit element: it is not included in the dynamic element list.
 * It can only be used to get the voltage difference between two arbitrary points off the grid.
 * </p>
 * <p>
 * The following general parameters can be scripted in the addObject() function:
 * <ul>
 * <li>row=
 * <li>col=
 * <li>to=   (direction: h or v)
 * <li>d=   (polarity)
 * <li>
 * <li>
 * <li>
 * </ul>
 * </p>
 *
 * @author T. Van Hoecke
 */
public class CircuitElement extends Canvas implements SDataSource
{
    double value; //only necessary for passive components
    double frequency; //eventually used by frequency dependent components (e.g. some sources)
    String function=""; //used by sources and general elements
    int direction; //only necessary for direction sensitive elements like sources and meters
    int inputIndex; //index necessary for matrix building, only necessary for sources
    //Image cirim; //image on the canvas
    Font font; // the value and label font on the circuit grid
    Format format; // the value format on the circuit grid
    Circuit circuit; // the calling applet
    CircuitCanvas circuitCanvas; //canvas for the circuit grid
    int x, y; //position coordinates
    String label=""; // default label on the circuit grid
    String unity=""; // default unity on the circuit grid
    String imagename="";
    CircuitElement otherElem=null;
    boolean canvasElement=true;

    VEquation vequation; //connection to the corresponding VEquation after parsing
    int numberOfNodes=2;
    int row, col; //anchor point in the grid
    String to = ""; //anchor direction
    String polarity; //if polarized, polarity
    boolean polarized = false; // if the polarity of an element is essential
    boolean leftlinear = true; // if the element is linear in the left side of the equation
    boolean rightlinear = true; // if the element is linear in the right side of the equation
    boolean valueVisible = true; // if the element's value is visible on the circuit grid
    boolean imageVisible = true; // if the element's default image is visible on the circuit grid
    boolean variableImage = false; // if the element's image is variable, like a bulb
    boolean reverseEquation = false; // if the element's equation is I(V)
    boolean overloaded = false; // if fire icon is currently displayed
    double maxCurrentValue = 10.0; // if the current is higher, a fire-icon will be displayed
    Color parsedbgColor = new Color(255,255,185);

    String[] varStrings= new String[]{"t","v","i"}; // for data connections
    double[][] vars= new double[1][3]; // for data connections

    CircuitElement()
    {
        value = 0.0;
        direction = 0;
        inputIndex = 0;
        row = col = 0;
        to = "h";
        vequation = null;
        font = new Font("TimesRoman", Font.PLAIN,10);
        format = new Format("%.3g");
        imagename = getMyName();
        canvasElement =false;
        this.setValueVisible(false);
		setBounds(0,2,52,22);
		setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
	}

    CircuitElement(Circuit circ)
    {
        circuit = circ;
        circuitCanvas = circ.circanvas;
        value = 0.0;
        direction = 0;
        inputIndex = 0;
        row = col = 0;
        to = "h";
        vequation = null;
        font = new Font("TimesRoman", Font.PLAIN,10);
        format = new Format("%.3g");
        imagename = getMyName();
        canvasElement =false;
        this.setValueVisible(false);
		setBounds(0,2,52,22);
		setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
	}

    /**
     * Subclasses have to include the following super constructor:<br>
     * super(circuit,r,c,t);
     *
     * @param circ The circuit that adds thisa new element
     * @param r row index of the element
     * @param c column index of the element
     * @param t direction: "h" horizontal or "v" vertical
     */
    CircuitElement(Circuit circ, int r, int c, String t)
    {
        circuit = circ;
        circuitCanvas = circ.circanvas;
        value = 1.0;
        direction = 0;
        inputIndex = 0;
        row = r; col = c;
        to = ""+t;
        vequation = null;
        imagename = getMyName();
        font = new Font("TimesRoman", Font.PLAIN,10);
        format = new Format("%.3g");
        try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    CircuitElement(Circuit circ, int pol, int r, int c, String t)
    {
        this(circ,r,c,t);
        polarized = true;
        if (pol==1) {//1 for right or under
            polarity = "p";
            direction = to.equals("h") ? 2 : 1;
        }
        else {//-1 for left or above
            polarity = "m";
            direction = to.equals("h") ? 0 : 3;
        }
    }

//    public void setCircuit(Circuit circ) {
//        circuit = circ;
//        loadImage(null);
//        repaint();
//    }
//
    public boolean set(String list) {
        list=list.toLowerCase().trim();
        list=SUtil.removeWhitespace(list);
        if (SUtil.parameterExist(list,"row=")) row=(int) SUtil.getParam(list,"row=");
        if (SUtil.parameterExist(list,"col=")) col=(int) SUtil.getParam(list,"col=");
        if (SUtil.parameterExist(list,"to=")) to = "" + SUtil.getParamStr(list,"to=");
        if (SUtil.parameterExist(list,"d=")) {
            int pol=(int) SUtil.getParam(list,"d=");
            if (pol==1) {//1 for right or under
                polarity = "p"; direction = to.equals("h") ? 2 : 1;
            } else {//-1 for left or above
                polarity = "m"; direction = to.equals("h") ? 0 : 3;
            }
        }
        if (SUtil.parameterExist(list,"func=")) function=""+SUtil.getParamStr(list,"func=");
        if (SUtil.parameterExist(list,"freq=")) frequency=SUtil.getParam(list,"freq=");
        return true;
    }

    /**
     * Implement this function if output to the circuit panel is not the element's value.<br>
     *
     * @return String represented on the circuit panel
     */
    public String valueStr(){return format.form(value);}

    /**
     * Use this function to make the element's value visible on the circuit panel.<br>
     *
     * @param i A value > 0 makes it visible
     */
    public void setValueVisible(boolean b){valueVisible = b;}

    /**
     * Use this function to make the element's default image visible on the circuit panel.<br>
     *
     * @param i A value > 0 makes the default image visible
     */
    public void setImageVisible(boolean b){imageVisible = b;}

    /**
     * Use this function to change the element's default image. If gifname is empty then the default image is not visible.
     *
     * @param id the object identifier
     * @param gifname the name of the image new gif file
     */
    public boolean setImage(String gifname){
        if (gifname.equals("")) setImageVisible(false);
        else imagename += gifname;
        return true;
    }

    /**
     * This function changes the polarity of the element<br>
     */
    public void changePolarity() {
        if (polarized) {
            if (polarity.equals("m")) {
                polarity = "p";
                direction = to.equals("h") ? 2 : 1;
            } else {
            polarity = "m";
            direction = to.equals("h") ? 0 : 3;
            }
        }
    }

    public void setFormat(String str){format = new Format(str);}

    public void setFont(Font nfont){
        font = new Font(nfont.getFamily(), nfont.getStyle(), nfont.getSize());
    }

    public void setvalue(String s){value = (Double.valueOf(s)).doubleValue();}

    public void setFrequency(double freq){frequency = freq;}

    public void setlabel(String l){label = ""+l;}

    public void setdirection(int d){direction = d;}

    public void setMaxCurrentValue(String s){maxCurrentValue = (Double.valueOf(s)).doubleValue();}

    public void move(int r, int c, String t){
        row = r; col = c; to = t;
    };

    /**
     * sets the coefficient of the I_k column for all elements
     *
     * @return e.g. R (resistor), dt/C (capacitor), L/dt (inductor)
     */
    public double impedance(){return (reverseEquation)?1.0:0.0;}

    /**
     * activated by coupled elements such as transformers
     *
     * @return e.g. L_of_coupled_coil/dt (inductor)
     */
    public double impedanceCoupled() {return 0.0;}

    /**
     * sets the coefficient of the V_j column for all elements
     *
     * @return
     */
    public double indexVHere(){return (reverseEquation)?0.0:-1.0;}

    /**
     * sets the coefficient of the V_i column for all elements
     *
     * @return
     */
    public double indexVNext(){return (reverseEquation)?0.0:1.0;}

    /**
     * activated by capacitors for integration
     *
     * @return coefficient for V_(i,n-1)
     */
    public double integralVHere(){return 0.0;}

    /**
     * activated by capacitors for integration
     *
     * @return coefficient for V_(j,n-1)
     */
    public double integralVNext(){return 0.0;}

    /**
     * activated by voltage sources
     *
     * @param sign +1 or -1
     * @return sign for sources, 0 for all other elements
     */
    public double input(double sign) {return 0.0;}

    /**
     * activated by inductors for differentiation
     *
     * @return coefficient of I_(k,n-1)
     */
    public double differential() {return 0.0;}

    /**
     * activated by coupled elements such as transformers
     *
     * @return coefficient of I_(index_of_coupled_elem,n-1)
     */
    public double differentialCoupled() {return 0.0;}

    /**
     * activated by general elements.
     *
     * @param sign +1 or -1
     * @return f(I,V)
     */
    public double rightFunction(double sign) {return 0.0;}

    /**
     * necessary for coupled elements.
     *
     */
    public void coupledTo(CircuitElement other) {
        this.otherElem=other;
    }

    /**
     * necessary for coupled elements.
     *
     */
    public int indexIcoupled() {
        return this.otherElem.vequation.indexI1;
    }


    public String getvalue(){return Double.toString(value);}

    public String getlabel(){return label;}

    public String getunity(){return unity;}

    /**
     * @return I_(k,n-1)
     */
    public double getI(){
        double i=0;
        try{
          i= circuit.cirgrid.y[circuit.cirgrid.numberOfV+vequation.indexI1];
        }catch(Exception ex){
        }
        return i;
    }

    /**
     * @return V_(i,n-1)-V_(j,n-1)
     */
    public double getV(){
      double v=0;
      try{
        v= circuit.cirgrid.y[vequation.indexV2]-circuit.cirgrid.y[vequation.indexV1];
      }catch(Exception ex){
      }
      return v;
    }

    /**
     * @return time
     */
    public double gett(){ //return time corresponding to "dt*noc"
        return circuit.realt;
    }

    public String getMyName() {
        String name = getClass().getName().toLowerCase();
        return name.substring(name.indexOf(".")+1);
    }

    public String getAddObjectString() {
        String s = "addObject(\""+circuit.cirProp.getProperty(getMyName())
                    +"\",\"row="+ row +",col="+ col +",to="+ to+",label="+ label;
        if (polarized) s += (polarity.equals("p")) ? ",d=1" : ",d=-1";
        s += getStringAdditions() + "\");\n";
        return s;
    }

    public String getStringAdditions() {return "";} //needs to be overrided when necessary
        // return comma delimited specific properties

    /**
     * Returns a string containing the comma delimited specific element properties.
     *
     */
    public String get() {
        if (getStringAdditions().length() == 0) return "";
        return getStringAdditions().substring(1);
    }

// ************************* Painting methods ***************************

	/**
	 * <p>
	 * Creates an image of the specific element by loading a gif file with the same
	 * name.<br>
	 * Create two gif files : ****h.gif for horizontal representation (48x13 pixels)
	 * and ****v.gif for vertical representation (13x48 pixels). Put them in the
	 * imagedir directory.
	 * </p>
	 * <p>
	 * If the image can not be found, "?" is displayed
	 * </p>
	 * <p>
	 * Create an empty paint()-function if you want to avoid this default image
	 * loading.
	 * </p>
	 *
	 * @param g Graphics
	 */
	public void paintImage(Graphics g) {
		Image cirim = circuit.getCachedImage(imagename + to);
		if (cirim == null) {
			g.setColor(java.awt.Color.red);
			g.setFont(new Font("TimesRoman", Font.BOLD, 22));
			if (to.equals("h")) {
				g.drawLine(x + 3, y, x + circuit.interGrid / 2 - 6, y);
				g.drawLine(x + circuit.interGrid / 2 + 6, y, x + circuit.interGrid - 4, y);
				g.setColor(java.awt.Color.black);
				g.drawString("?", x - 6 + circuit.interGrid / 2, y + 6);
			} else {
				g.drawLine(x, y + 3, x, y + circuit.interGrid / 2 - 10);
				g.drawLine(x, y + circuit.interGrid / 2 + 10, x, y + circuit.interGrid - 4);
				g.setColor(java.awt.Color.black);
				g.drawString("?", x - 4, y + 8 + circuit.interGrid / 2);
			}
			g.setColor(java.awt.Color.red);
			g.setFont(font);
		} else {
			if (to.equals("h")) {
				g.drawLine(x + 3, y, x + circuit.interGrid / 2 - 24, y);
				g.drawLine(x + circuit.interGrid / 2 + 24, y, x + circuit.interGrid - 4, y);
			} else {
				g.drawLine(x, y + 3, x, y + circuit.interGrid / 2 - 24);
				g.drawLine(x, y + circuit.interGrid / 2 + 24, x, y + circuit.interGrid - 4);
			}
			int ox = to.equals("h") ? (circuit.interGrid / 2) - 24 : -6;
			int oy = to.equals("h") ? -6 : (circuit.interGrid / 2) - 24;
			g.drawImage(cirim, x + ox, y + oy, circuitCanvas);
		}
	}

	/**
	 * <p>
	 * Displays the fire-icon if the element's current is bigger the maxCurrentValue
	 * </p>
	 *
	 * @param g  Graphics
	 * @param ix x-position
	 * @param iy y-position
	 */
	public void overload(Graphics g) {
		int ox = to.equals("h") ? (circuit.interGrid / 2) - 24 : -24;
		int oy = to.equals("h") ? -24 : (circuit.interGrid / 2) - 24;
		// waits while the whole image is drawn
		Image cirim = circuit.getCachedImage("fire");
		if (cirim != null && cirim.getWidth(this) > 0)
			g.drawImage(cirim, x + ox, y + oy, circuitCanvas);
		overloaded = true;
	}

    /**
     * <p>If the imageVisible is set to false, a rectangle with a "Z" is displayed</p>
     *
     * @param g Graphics
     */
    public void unknownImage (Graphics g) {
        g.setColor(java.awt.Color.red);
        g.setFont(new Font("TimesRoman", Font.BOLD,12));
        if (to.equals("h")) {
            g.drawLine(x+3, y, x+circuit.interGrid/2-13, y);
            g.drawLine(x+circuit.interGrid/2+13, y, x+circuit.interGrid-4, y);
            g.drawRect(x+circuit.interGrid/2-13, y-6, 25, 13);
            g.setColor(java.awt.Color.black);
            g.drawString("Z", x-4+circuit.interGrid/2, y+6);
        } else {
            g.drawLine(x, y+3, x, y+circuit.interGrid/2-13);
            g.drawLine(x, y+circuit.interGrid/2+13, x, y+circuit.interGrid-4);
            g.drawRect(x-6, y+circuit.interGrid/2-13, 13, 25);
            g.setColor(java.awt.Color.black);
            g.drawString("Z", x-2, y+5+circuit.interGrid/2);
        }
        g.setColor(java.awt.Color.red);
        g.setFont(font);
    }

    private void showArrows(Graphics g) {
        g.setColor(java.awt.Color.white);
        if (getI() != 0) {
            if (to.equals("h")){
                g.fillRect(x+22, y+6, 11, 5);
                g.setColor(java.awt.Color.blue);
                g.drawLine(x+22, y+8, x+32, y+8);
                if ((vequation.direction==2 && getI()> 0)||(vequation.direction==0 && getI()< 0)) {
                    g.drawLine(x+29, y+6, x+32, y+8);
                    g.drawLine(x+29, y+10, x+32, y+8);
                } else {
                    g.drawLine(x+22, y+8, x+25, y+6);
                    g.drawLine(x+22, y+8, x+25, y+10);
                }
            } else {
                g.fillRect(x-10, y+22, 5, 11);
                g.setColor(java.awt.Color.blue);
                g.drawLine(x-8, y+22, x-8, y+32);
                if ((vequation.direction==1 && getI()> 0)||(vequation.direction==3 && getI()< 0)) {
                    g.drawLine(x-6, y+29, x-8, y+32);
                    g.drawLine(x-10, y+29, x-8, y+32);
                } else {
                    g.drawLine(x-8, y+22, x-6, y+25);
                    g.drawLine(x-8, y+22, x-10, y+25);
                }
            }
        }
    }

    void showValue(Graphics g) {
        g.setColor(java.awt.Color.blue);
        if (to.equals("h")) g.drawString(valueStr(), x+circuit.interGrid/2-17, y-6);
        else g.drawString(valueStr(), x+7, y+circuit.interGrid/2-1);
    }

    void showSigns(Graphics g) {
        int p = polarity.equals("p")? circuit.interGrid-11:6;
        g.setColor(java.awt.Color.red);
        if (to.equals("h")){ //draws + and -
            g.fillRect(x+p, y-4, 6, 2);
            g.fillRect(x+p+2, y-6, 2, 6);
            g.setColor(java.awt.Color.black);
            g.fillRect(x+circuit.interGrid-5-p, y-4, 6, 2);
        } else {
            g.fillRect(x+2, y+p, 6, 2);
            g.fillRect(x+4, y+p-2, 2, 6);
            g.setColor(java.awt.Color.black);
            g.fillRect(x+2, y+circuit.interGrid-5-p+2, 6, 2);
        }
    }

    /**
     * <p>If the variableImage is set to true, implement this function to repaint the image</p>
     *
     * @param g Graphics
     */
    public void repaintImage(Graphics g){
        if (circuit.parsed && circuit.showCurrent) showArrows(g); // draw arrows for chosen current direction
    }

    /**
     * <p>Implement if element drawing is realized by drawing simple things</p>
     * <p>Don't forget to override 'loadImage()' if necessary!<br>
     * Always start with 'super.paint(g);'<br>
     * You can use an area of 48x13 pixels horizontally and 13x48 pixels vertically.<br>
     * Always implement horizontal and vertical drawings.<br>
     * <ul><li>x = horizontal zero position
     * <li>y = vertical zero position
     * <li>circuit.interGrid = number of pixels between two grid points (=54)</ul></p>
     * <p>The following is an example of the line drawing for a wire:</p>
     *
     * <p>  public void paint(Graphics g){<br>
     *         super.paint(g);<br>
     *         g.setColor(java.awt.Color.red);<br>
     *         if (to.equals("h")) g.drawLine(x+3, y, x+circuit.interGrid-4, y); //horizontal<br>
     *         else g.drawLine(x, y+3, x, y+circuit.interGrid-4); //vertical<br>
     *     }</p>
     *
     * @param g Graphics
     */
    public void paint(Graphics g){
        g.setColor(java.awt.Color.red);
        if (circuit!=null && canvasElement) {
            x = (int)(circuit.interGrid*(col+0.5)); y = (int)(circuit.interGrid*(row+0.5));
            int cig = circuit.interGrid/2;
            g.setFont(font);
            if (imageVisible) paintImage(g); else unknownImage(g); // display image
            if (valueVisible) showValue(g); // display value
            g.setColor(java.awt.Color.black);
            if (to.equals("h")) g.drawString(label, x+cig-4, y-14); // draw label
            else g.drawString(label, x+7, y+cig+7);
            if (circuit.parsed && circuit.showCurrent) showArrows(g); // draw arrows for chosen current direction
            g.setColor(java.awt.Color.red);
            if (circuit.parsed && this.maxCurrentValue <= Math.abs(this.getI())) {
                overload(g); circuit.pause();
            }
            if (polarized) showSigns(g); // if polarized, draw + and -
        } else {
            x=0; y=10;
            paintImage(g);
        }
    }

    public int getCoupledID(){return otherElem.getID();}

// ************************** SDataSource methods ***************************

    public double[][] getVariables(){
        vars[0][0]=circuit.clock.getTime(); // time;
	    vars[0][1] = getV(); // voltage
        vars[0][2] = getI(); //current
        return vars;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return this.hashCode();}
    public void setOwner(SApplet o){circuit=(Circuit)o;}
    public SApplet getOwner(){return circuit;}
// ************************** end SDataSource methods ***********************

}
