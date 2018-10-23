package edu.davidson.tools;
import java.awt.Color;
import java.awt.Graphics;

import java.util.StringTokenizer;
import edu.davidson.display.Format;
import edu.davidson.numerics.Parser;

final public class SUtil {

  public SUtil() {
  }

  static public void drawArrow(Graphics g, int x0,int y0,int x1,int y1){
      drawArrow( g,  x0, y0, x1, y1, 5);
  }

  static public void drawArrow(Graphics g, int x0,int y0,int x1,int y1, int s){
      x1=Math.min(x1,2000);
      x1=Math.max(x1,-2000);
      y1=Math.min(y1,2000);
      y1=Math.max(y1,-2000);
      g.drawLine(x0,y0,x1,y1);
      double x=x1-x0;
      double y=-(y1-y0);
      double h=Math.sqrt(x*x+y*y);
      double w;
      if (h>3*s)w=s; else w=h/3;
      if(h>1){
             double u=(w*x/h);
             double v=-(w*y/h);
             double base_x= x1-3*u;
             double base_y= y1-3*v;
             g.drawLine((int)(base_x-v),(int)(base_y+u),x1,y1);
             g.drawLine((int)(base_x+v),(int)(base_y-u),x1,y1);
      }
  }

  public static void drawThickArrow(Graphics g, int x1, int y1, int x2, int y2, int size, int thickness) {
    if(thickness<2){
          drawSolidArrow(g,x1,y1,x2,y2,size);
          return;
    }
    x1=Math.min(x1,2000);
    x1=Math.max(x1,-2000);
    y1=Math.min(y1,2000);
    y1=Math.max(y1,-2000);
    double deltaX=x2-x1;
    double deltaY=-(y2-y1);
    double h=Math.sqrt(deltaX*deltaX+deltaY*deltaY);
    size=Math.max(1,(int)(size+thickness/2));  // the size of the head
    if (h<4*size)size=(int)(h/4);
    if (2*thickness>size)thickness=size/2;
    double halfWidth = Math.max(thickness/2.0,2);
    double u=(size*deltaX/h);
    double v=-(size*deltaY/h);
    double base_x= x2-3*u;  // base of arrow
    double base_y= y2-3*v;
   // int xOffset = -(int)(halfWidth*deltaY/h);
   // int yOffset = -(int)(halfWidth*deltaX/h);
    int xOffset = -(int)Math.round(halfWidth*deltaY/h);
    int yOffset = -(int)Math.round(halfWidth*deltaX/h);

    int[] xCorners = { x1-xOffset, (int)base_x-xOffset,(int)(base_x-v), x2,(int)(base_x+v),
                       (int)base_x+xOffset, x1+xOffset };
    int[] yCorners = { y1-yOffset, (int)base_y-yOffset,(int)(base_y+u), y2,(int)(base_y-u),
                       (int)base_y+yOffset, y1+yOffset };
    g.fillPolygon(xCorners, yCorners, 7);
  }

    public static void drawThickLine(Graphics g,
                              int x1, int y1,
                              int x2, int y2,
                              int lineWidth) {
    double angle;
    double halfWidth = ((double)lineWidth)/2.0;
    double deltaX = (double)(x2 - x1);
    double deltaY = (double)(y2 - y1);
    if (x1 == x2)
      angle=Math.PI;
    else
      angle=Math.atan(deltaY/deltaX)+Math.PI/2;
    int xOffset = (int)(halfWidth*Math.cos(angle));
    int yOffset = (int)(halfWidth*Math.sin(angle));
    int[] xCorners = { x1-xOffset, x2-xOffset,
                       x2+xOffset, x1+xOffset };
    int[] yCorners = { y1-yOffset, y2-yOffset,
                       y2+yOffset, y1+yOffset };
    g.fillPolygon(xCorners, yCorners, 4);
  }

  static public void drawSolidArrow(Graphics g, int x0, int y0, int x1, int y1, int s){
    x1=Math.min(x1,2000);
    x1=Math.max(x1,-2000);
    y1=Math.min(y1,2000);
    y1=Math.max(y1,-2000);
      g.drawLine(x0,y0,x1,y1);
      double x=x1-x0;
      double y=y1-y0;
      double h=Math.sqrt(x*x+y*y);

      double u=s*x/h;
      double v=s*y/h;
      double base_x=x1-3*u;
      double base_y=y1-3*v;
      int x2=(int)(base_x-v);
      int y2=(int)(base_y+u);
      int x3=(int)(base_x+v);
      int y3=(int)(base_y-u);
      g.fillPolygon(new int[] {x1,x2,x3},new int[] {y1,y2,y3},3);
  }

  static public void drawArrowHead(Graphics g, int x0,int y0,int x1,int y1, int s){
    x1=Math.min(x1,2000);
    x1=Math.max(x1,-2000);
    y1=Math.min(y1,2000);
    y1=Math.max(y1,-2000);
      double x=x1-x0;
      double y=-(y1-y0);
      double h=Math.sqrt(x*x+y*y);
      double u=(s*x/h);
      double v=-(s*y/h);
      double base_x= x0-3*u;
      double base_y= y0-3*v;
      g.drawLine((int)(base_x-v),(int)(base_y+u),x0,y0);
      g.drawLine((int)(base_x+v),(int)(base_y-u),x0,y0);
  }

  static public void drawSolidArrowHead(Graphics g, int x0,int y0,int x1,int y1, int s){
    x1=Math.min(x1,2000);
    x1=Math.max(x1,-2000);
    y1=Math.min(y1,2000);
    y1=Math.max(y1,-2000);
      double x=x1-x0;
      double y=-(y1-y0);
      double h=Math.sqrt(x*x+y*y);
      double u=(s*x/h);
      double v=-(s*y/h);
      double base_x= x0-3*u;
      double base_y= y0-3*v;
 //     g.drawLine((int)(base_x-v),(int)(base_y+u),x0,y0);
 //     g.drawLine((int)(base_x+v),(int)(base_y-u),x0,y0);
      g.fillPolygon(new int[] {x0,(int)(base_x-v),(int)(base_x+v)},new int[]{y0,(int)(base_y+u),(int)(base_y-u)},3);
  }

  static public Color paleColor(Color c){
      int r=255-(255-c.getRed())/2;
      int g=255-(255-c.getGreen())/2;
      int b=255-(255-c.getBlue())/2;
      return new Color(r,g,b);
  }

  static public Color veryPaleColor(Color c){
      int r=255-(255-c.getRed())/4;
      int g=255-(255-c.getGreen())/4;
      int b=255-(255-c.getBlue())/4;
      return new Color(r,g,b);
  }

  static public Color complementColor(Color c){
      int r=255-c.getRed();
      int g=255-c.getGreen();
      int b=255-c.getBlue();
      return new Color(r,g,b);
  }

  static public double chop(double val,double precision){
      if(Math.abs(val)<Math.abs(precision)){
         return 0;
      }
      else return val;
  }

  static public String removeWhitespace(String str){
      StringBuffer sb=new StringBuffer(32);
      for(int i=0; i<str.length(); i++){
         if( !Character.isWhitespace(str.charAt(i)) )sb.append(str.charAt(i));
      }
      return sb.toString();
  }

  static public boolean parameterExist(String paramList, String param){
      param=param.toLowerCase();
      StringTokenizer ptokens=new StringTokenizer(paramList,",;");
      int n=ptokens.countTokens();
      for(int i=0; i<n; i++){
          String valStr=ptokens.nextToken().trim();
          valStr=valStr.toLowerCase();
          if (valStr!=null && !valStr.equals("") && valStr.startsWith(param)) return true;
      }
      return false;
  }

  /**
   * Convert any escape sequences such as \,  or \= and replace with the character.
   *
   * @param function the function
   *
   * @return the new function
   */
  static private String convertEscapeCharacter(String str){
      if(str==null || str.length()<1) return str;
      StringBuffer sb=new StringBuffer(str.length());
      for(int i=0; i<str.length(); i++){
         if(str.charAt(i) != '\\' )sb.append(str.charAt(i));
         else{ // check for escape sequence
           sb.append(str.charAt(i)); // add the backslash
           if( (i<str.length()-1) &&  (str.charAt(i+1) == ',')  )sb.append("#"); // replace the next character
           if( (i<str.length()-1) &&  (str.charAt(i+1) == '=')  )sb.append("@");
           i++;
         }
      }
      return sb.toString();
   }

  /**
   * Remove any escape sequences such as \,  or \= and replace with the character.
   *
   * @param function the function
   *
   * @return the new function
   */
  static private String restoreEscapeCharacter(String str){
      if(str==null || str.length()<1) return str;
      StringBuffer sb=new StringBuffer(str.length());
      for(int i=0; i<str.length(); i++){
         if(str.charAt(i) != '\\' )sb.append(str.charAt(i));
         else{ // check for escape sequence but skip the backslash.
           if( (i<str.length()-1) &&  (str.charAt(i+1) == '#')  )sb.append(","); // replace the next character
           if( (i<str.length()-1) &&  (str.charAt(i+1) == '@')  )sb.append("=");
           i++;
         }
      }
      return sb.toString();
   }

  static public double getParam(String paramList, String param){
      param=param.toLowerCase();
      StringTokenizer ptokens=new StringTokenizer(paramList,",;");
      int n=ptokens.countTokens();
      double value=0;
      for(int i=0; i<n; i++){
          String valStr=ptokens.nextToken().trim();
          valStr=valStr.toLowerCase();
          if (valStr!=null && !valStr.equals("") && valStr.startsWith(param)){
              valStr=valStr.substring( param.length() );
              try { value=Double.valueOf(valStr).doubleValue(); } catch (Exception e) {System.out.println("Parameter in add method is not a number. param "+ param + " "+valStr);}
              value=Format.atof(valStr);
              return value;
          }
      }
      System.out.println("Error: parameter not found. paramList:" + paramList + "parameter:" + param);
      return value;
  }

  static private String removeWhitespaceToEqual(String str){
      boolean equalFound=false;
      StringBuffer sb=new StringBuffer(str.length());
      for(int i=0; i<str.length(); i++){
         if(str.charAt(i)=='=')equalFound=true;
         if(equalFound || !Character.isWhitespace(str.charAt(i)) )sb.append(str.charAt(i));
      }
      return sb.toString();
  }
  static public String getParamStr(String paramList, String param){
      paramList=convertEscapeCharacter(paramList);
      StringTokenizer ptokens=new StringTokenizer(paramList,",;");
      int n=ptokens.countTokens();
      for(int i=0; i<n; i++){
          String valStr=ptokens.nextToken();
          valStr=removeWhitespaceToEqual(valStr);
          if (valStr!=null && !valStr.equals("") && valStr.startsWith(param)){
              valStr=valStr.substring( param.length() );
              valStr=restoreEscapeCharacter(valStr);
              return valStr;
          }
      }
      System.out.println("Error: parameter not found. paramList:" + paramList + "parameter:" + param);
      return null;
  }

  static public boolean isValidFunction(String funcStr, String varList){
    if(funcStr==null) return false;
    StringTokenizer ptokens=new StringTokenizer(varList,",;");
    int n=ptokens.countTokens();
    String[] vars= new String[n];
    for(int i=0; i<n; i++){
        String valStr=ptokens.nextToken();
        vars[i]=removeWhitespaceToEqual(valStr);
    }
    Parser parser = new Parser(vars.length);
    for(int i=0; i<vars.length; i++){
             parser.defineVariable(i+1,vars[i]); // define the variables
    }
    parser.define(funcStr);
    parser.parse();
    if(parser.getErrorCode() != Parser.NO_ERROR){
             System.out.println("Failed to parse function): "+funcStr);
             System.out.println("Parse error: " + parser.getErrorString() +
                   " at function 1, position " + parser.getErrorPosition());
             for(int i=0; i<vars.length; i++){
               System.out.println("vars "+vars[i]);
             }
             return false;
      }else{return true;}
  }

}