package circuitsimulator;

import java.awt.*;
import java.util.*;

public class CircuitProperties extends Properties {
    public CircuitProperties() {
        super();
        defaultProperties();
    }

    public CircuitProperties(CircuitProperties defaults) {
        super(defaults);
    }

    public boolean getBoolean(String key, boolean def) {
        String val = getProperty(key);
        if (val != null) {
            if ("true".equalsIgnoreCase(val.trim()))
                return true;
            else if ("false".equalsIgnoreCase(val.trim()))
                return false;
        }
        return def;
    }

    public Color getColor(String key, Color def) {
        String val = getProperty(key);
        if (val != null) {
            StringTokenizer tkn = new StringTokenizer(val);
            try {
                int r = Integer.parseInt(tkn.nextToken());
                int g = Integer.parseInt(tkn.nextToken());
                int b = Integer.parseInt(tkn.nextToken());
                return new Color(r, g, b);
            } catch (NumberFormatException x) {
                return def;
            }
        }
        return def;
    }

    public double getDouble(String key, double def) {
        String val = getProperty(key);
        double i = def;
        if (val != null) {
            try {
                i = Double.valueOf(val.trim()).doubleValue();
            } catch (NumberFormatException x) {}
        }
        return i;
    }

    public int getInt(String key, int def) {
        String val = getProperty(key);
        int i = def;
        if (val != null) {
            try {
                i = Integer.parseInt(val.trim());
            } catch (NumberFormatException x) {}
        }
        return i;
    }

    public String getString(String key, String def) {
        String val = getProperty(key);
        if (val != null) {
            int len = val.length();
            int st = 0;
            while ((st < len) && (val.charAt(st) != '"')) {st++;}
            st++;
            while ((st < len) && (val.charAt(len - 1) != '"')) {len--;}
            len--;
            if (st < len) return val.substring(st, len);
        }
        return def;
    }
    
    public String getkey(String value)
    {
        String key="";
        for (Enumeration e=propertyNames();e.hasMoreElements();) {
            key = (String) e.nextElement();
            if (getProperty(key).equals(value)) return key;
        }
        return value;
    }
    
    public void defaultProperties() {
        put("nothing","nothing");
        put("wire","wire");
        put("resistor","resistor");
        put("capacitor","capacitor");
        put("inductor","inductor");
        put("source","source");
        put("diode","diode");
        put("battery","battery");
        put("switch","switch");
        put("scope","scope");
        put("ameter","ameter");
        put("vmeter","vmeter");
        put("vgeneral","vgeneral");
        put("igeneral","igeneral");
        put("bulb","bulb");
        put("sinwave","sinwave");
        put("squarewave","squarewave");
        put("currentsource","currentsource");
        put("probe","probe");
        put("transformer","transformer");
        put("transformercoil","transformercoil");
        put("OK","OK");
        put("Cancel","Cancel");
        put("rows","rows");
        put("cols","cols");
        put("Load","Load");
        put("setgrid","Set grid");
        put("List","List");
        put("arrows","Show ->");
        put("Direction","Direction");
        put("down","+ down/right");
        put("up","+ up/left");
        put("Frequency","Frequency");
        put("Calculate","Calculate");
        put("Start","Start");
        put("Pause","Pause");
        put("Reset","Reset");
        put("scope_title","Voltage signal of");
        put("scope_vscale","Voltage Scale");
        put("scope_tbase","Time Base");
        put("circuitlist","List of the build circuit");
        put("menu_delete","Delete Component");
        put("menu_changevalue","Change Properties");
        put("menu_knobvalue","Display Value Knob");
        put("menu_knobfrequency","Display Frequency Knob");
        put("menu_showvalue","Show/hide Value/Function");
        put("menu_changeswitch","Change Switch");
        put("menu_changepolarity","Change Polarity");
        put("menu_elemlabel","Set Label");
        put("menu_getvoltage","Display Oscilloscope");
        put("menu_voltmeter","Display Voltmeter");
        put("menu_ammeter","Display Ampèremeter");
        put("menu_vgraph","Display Voltage Graph");
        put("menu_igraph","Display Current Graph");
        put("changevalue_title","Change properties");
        put("elemlabel_title","Change label");
        put("vgraph_title","Voltage graph of");
        put("igraph_title","Current graph of");
        put("gmode_strip","To Strip Chart");
        put("gmode_full","To Full Chart");
        put("vaxis","Voltage (V)");
        put("iaxis","Current (A)");
        put("taxis","Time (s)");
        put("twindow","Strip Window (s)");
    }
}
