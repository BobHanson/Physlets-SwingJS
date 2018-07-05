package circuitsimulator;

import java.awt.Graphics;

import edu.davidson.tools.*;

/**
 * Circuit element, representing a probe between two points
 * 
 * @author Toon Van Hoecke
 */
public class Probe extends CircuitElement
{
    String type = "voltage";
    int row2, col2;
    
    Probe(Circuit circuit, String ntype, int rb, int cb, int re, int ce) {
        super(circuit,rb,cb,"h");
        type = ""+ntype;
        row2 = re;
        col2 = ce;
    }
    
    public boolean set(String list) {
        list=list.toLowerCase().trim();
        list=SUtil.removeWhitespace(list);
        if (SUtil.parameterExist(list,"type=")) type=""+SUtil.getParamStr(list,"type=");
        if (SUtil.parameterExist(list,"row=")) row=(int) SUtil.getParam(list,"row=");
        if (SUtil.parameterExist(list,"col=")) col=(int) SUtil.getParam(list,"col=");
        if (SUtil.parameterExist(list,"row2=")) row2=(int) SUtil.getParam(list,"row2=");
        if (SUtil.parameterExist(list,"col2=")) col2=(int) SUtil.getParam(list,"col2=");
        return true;
    }
    
    public double getI(){  
        return 0.0;
    }
    
    public double getV(){ 
		int b=circuit.cirgrid.element[row][col].getVIndex();
		int e=circuit.cirgrid.element[row2][col2].getVIndex();
		return circuit.cirgrid.y[e]-circuit.cirgrid.y[b];
    }
    
    public String getAddObjectString() {
        String s = "addObject(\""+getMyName()+"\",\"type="+type+",row="+row+",col="+col 
                     +",row2="+row2+",col2="+col2+"\");\n";
        return s;
    }
    
    public void loadImage(Graphics g) {}//overriding
    
    public void paint(Graphics g){
		int d=circuit.interGrid;
		int b=d/2;
        g.setFont(font);
        g.setColor(java.awt.Color.blue);
        g.fillOval(b+col*d-3,b+row*d-3,6,6);
        g.drawString(label+"-", b+col*d+3,b+row*d-3);
        g.fillOval(b+col2*d-3,b+row2*d-3,6,6);
        g.drawString(label+"+", b+col2*d+3,b+row2*d-3);
    }
    
}
