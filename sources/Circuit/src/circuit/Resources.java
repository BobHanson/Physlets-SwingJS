package circuit;

import java.awt.Color;
import java.util.*;

public class Resources extends Properties {


    public Resources() {

        super();
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

            while ((st < len) && (val.charAt(st) != '"')) {

                st++;
            }
            st++;

            while ((st < len) && (val.charAt(len - 1) != '"')) {

                len--;
            }
            len--;


            if (st < len)
                return val.substring(st, len);
        }

        return def;
    }
}
