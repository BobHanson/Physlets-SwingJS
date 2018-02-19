
/**
 * A text field that accepts only integers/
 */

package edu.davidson.display;

import java.awt.Color;
//import java.awt.*;
import a2s.*;
import java.awt.event.*;

public class SInteger extends TextField implements TextListener
{ /**
   * Creates the text field
   * @param defval an integer default,
   * @param size the size of the text field
   */
   public SInteger(int defval, int size)
   {  super("" + defval, size);
      addTextListener(this);
      addKeyListener(new KeyAdapter() 
         {  public void keyTyped(KeyEvent evt)
            {  char ch = evt.getKeyChar();
               if (!('0' <= ch && ch <= '9' 
                     || ch == '-'
                     || Character.isISOControl(ch)))
                  evt.consume();
               else{
                  lastCaretPosition = getCaretPosition();
                  if(isEditable() && !noColor )setBackground(Color.yellow);
               }
            }
         });
      lastValue = "" + defval;
   }

   public SInteger() {
      this(0, 3);
   }

   public void textValueChanged(TextEvent evt){
       //if(isEditable() && !noColor )setBackground(Color.yellow);
       checkValue();
   }

   private void checkValue()
   {  try
      {  Integer.parseInt(getText().trim() + "0");
         lastValue = getText();
      }
      catch(NumberFormatException e){
         setText(lastValue);
         setCaretPosition(lastCaretPosition);
      }
   }

   /**
   * Get the integer data value
   * @return the integer data value
   * @remark if invalid, returns the valid prefix (or 0 if none)
   * This only happens when the field is blank or contains just a
   * single -
   */

   public int getValue()
   {  checkValue();
      try{
          val=Integer.parseInt(getText().trim());
          if(isEditable() && !noColor)setBackground(Color.white);
          return val;
      }
      catch(NumberFormatException e){
         if(!noColor)setBackground(Color.red);
         return 0;
      }
   }
   /**
   * Set the integer data value
   * @param v is the new integer value.
   */
   public void setValue(int v){
      val=v;
      setText(""+v);
      if(!noColor)setBackground(Color.white);
   }

   public boolean isNoColor(){return noColor;}
   public void setNoColor(boolean nc){noColor=nc;}

   private String lastValue;
   private int lastCaretPosition;
   private boolean noColor=false;
   private int val=0;
}
