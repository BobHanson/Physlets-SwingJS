package jacob;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;
import java.util.Vector;

public final class Data
{
  public static void saveFile(Vector paramVector1, Vector paramVector2, OutputStream paramOutputStream)
    throws IOException
  {
    PrintStream localPrintStream = new PrintStream(paramOutputStream);
    localPrintStream.println("ppd " + Common.getPPDWidth() + " " + Common.getPPDHeight() + " " + PPD.electric_const + " " + "0" + " " + PPD.suscept);
    Object localObject;
    int i;
    for (i = 0; i < paramVector1.size(); i++)
    {
      localObject = (Particle)paramVector1.elementAt(i);
      if (((Particle)localObject).parent == PPD.nullElement) {
        switch (((Particle)localObject).type)
        {
        case 3: 
          localPrintStream.println("pp " + ((Particle)localObject).x + " " + ((Particle)localObject).y + " " + ((Particle)localObject).k);
          break;
        case 4: 
          localPrintStream.println("pn " + ((Particle)localObject).x + " " + ((Particle)localObject).y + " " + ((Particle)localObject).k);
        }
      }
    }
    for (i = 0; i < paramVector2.size(); i++)
    {
      localObject = (Element)paramVector2.elementAt(i);
      int k = -1;
      if (((Element)localObject).connection != null) {
        k = PPD.elementBox.indexOf(((Element)localObject).connection);
      }
      switch (((Element)localObject).type)
      {
      case 0: 
        ECircle localECircle = (ECircle)localObject;
        localPrintStream.println("ec " + localECircle.x + " " + localECircle.y + " " + localECircle.r + " " + localECircle.Ex + " " + localECircle.Ey + " " + localECircle.movable + " " + localECircle.force_circ + " " + localECircle.etransient + " " + localECircle.force_rotate + " " + localECircle.force_translate + " " + k);
        break;
      case 1: 
        ERectangle localERectangle = (ERectangle)localObject;
        localPrintStream.println("er " + localERectangle.x + " " + localERectangle.y + " " + localERectangle.width + " " + localERectangle.height + " " + localERectangle.Ex + " " + localERectangle.Ey + " " + localERectangle.movable + " " + localERectangle.force_circ + " " + localERectangle.etransient + " " + localERectangle.force_rotate + " " + localERectangle.force_translate + " " + k);
        break;
      case 2: 
        ERing localERing = (ERing)localObject;
        localPrintStream.println("eo " + localERing.x + " " + localERing.y + " " + localERing.r1 + " " + localERing.r2 + " " + localERing.Ex + " " + localERing.Ey + " " + localERing.movable + " " + localERing.force_circ + " " + localERing.etransient + " " + localERing.force_rotate + " " + localERing.force_translate + " " + k);
        break;
      }
      for (int j = 0; j < paramVector1.size(); j++)
      {
        Particle localParticle = (Particle)paramVector1.elementAt(j);
        if (localParticle.parent == localObject) {
          switch (localParticle.type)
          {
          case 3: 
            localPrintStream.println("pp " + localParticle.x + " " + localParticle.y + " " + localParticle.k);
            break;
          case 4: 
            localPrintStream.println("pn " + localParticle.x + " " + localParticle.y + " " + localParticle.k);
          }
        }
      }
    }
    localPrintStream.close();
    paramOutputStream.close();
  }
  
  public static void loadFile(Vector paramVector1, Vector paramVector2, InputStream paramInputStream)
    throws IOException, FileFormatException{
    StreamTokenizer localStreamTokenizer = new StreamTokenizer(paramInputStream);
    Object localObject = PPD.nullElement;
    localStreamTokenizer.eolIsSignificant(true);
    localStreamTokenizer.commentChar(35);
    Vector localVector = new Vector();
    int k;
    boolean done;
    label36:
    done=false;
    while(!done){
      switch (localStreamTokenizer.nextToken())
      {
      default: 
    	 done = localStreamTokenizer.ttype==StreamTokenizer.TT_EOF;
        break;
      case 10: 
        break;
      case -3: 
        int i;
        if ("ppd".equals(localStreamTokenizer.sval))
        {
          i = Common.getPPDWidth();
          k = Common.getPPDHeight();
          if (localStreamTokenizer.nextToken() == -2)
          {
            i = (int)localStreamTokenizer.nval;
            if (localStreamTokenizer.nextToken() == -2)
            {
              k = (int)localStreamTokenizer.nval;
              if ((localStreamTokenizer.nextToken() != -2) || (localStreamTokenizer.nextToken() != -2) || (localStreamTokenizer.nextToken() != -2)) {}
            }
          }
          if ((i != Common.getPPDWidth()) || (k != Common.getPPDHeight())) {
            Common.resizePPD(i, k);
          }
        }
        else
        {
          int m;
          boolean bool2;
          boolean bool3;
          boolean bool4;
          boolean bool5;
          int i1;
          if ("ec".equals(localStreamTokenizer.sval))
          {
            i = 0;
            k = 0;
            m = 0;
            double d3 = 0.0D;
            double d5 = 0.0D;
            boolean bool1 = false;
            bool2 = true;
            bool3 = false;
            bool4 = false;
            bool5 = false;
            i1 = -1;
            if (localStreamTokenizer.nextToken() == -2)
            {
              i = (int)localStreamTokenizer.nval;
              if (localStreamTokenizer.nextToken() == -2)
              {
                k = (int)localStreamTokenizer.nval;
                if (localStreamTokenizer.nextToken() == -2)
                {
                  m = (int)localStreamTokenizer.nval;
                  if (localStreamTokenizer.nextToken() == -2)
                  {
                    d3 = localStreamTokenizer.nval;
                    if (localStreamTokenizer.nextToken() == -2)
                    {
                      d5 = localStreamTokenizer.nval;
                      if (localStreamTokenizer.nextToken() == -3)
                      {
                        if (localStreamTokenizer.sval.equals("true")) {
                          bool1 = true;
                        }
                        if (localStreamTokenizer.nextToken() == -3)
                        {
                          if (localStreamTokenizer.sval.equals("true")) {
                            bool3 = true;
                          }
                          if (localStreamTokenizer.nextToken() == -3)
                          {
                            if (localStreamTokenizer.sval.equals("false")) {
                              bool2 = false;
                            }
                            if (localStreamTokenizer.nextToken() == -3)
                            {
                              if (localStreamTokenizer.sval.equals("true")) {
                                bool5 = true;
                              }
                              if (localStreamTokenizer.nextToken() == -3)
                              {
                                if (localStreamTokenizer.sval.equals("true")) {
                                  bool4 = true;
                                }
                                if (localStreamTokenizer.nextToken() == -2) {
                                  i1 = (int)localStreamTokenizer.nval;
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            localVector.addElement(new Integer(i1));
            localObject = new ECircle(i, k, m, d3, d5);
            ((Element)localObject).movable = bool1;
            ((Element)localObject).etransient = bool2;
            ((Element)localObject).force_circ = bool3;
            ((Element)localObject).force_rotate = bool5;
            ((Element)localObject).force_translate = bool4;
            paramVector2.addElement(localObject);
            do
            {
              localStreamTokenizer.nextToken();
              if (localStreamTokenizer.ttype == 10) {
                break;
              }
            } while (localStreamTokenizer.ttype != -1);
          }
          else
          {
            int n;
            double d4;
            double d6;
            int i2;
            if ("er".equals(localStreamTokenizer.sval))
            {
              i = 0;
              k = 0;
              m = 0;
              n = 0;
              d4 = 0.0D;
              d6 = 0.0D;
              bool2 = false;
              bool3 = true;
              bool4 = false;
              bool5 = false;
              i1 = 0;
              i2 = -1;
              if (localStreamTokenizer.nextToken() == -2)
              {
                i = (int)localStreamTokenizer.nval;
                if (localStreamTokenizer.nextToken() == -2)
                {
                  k = (int)localStreamTokenizer.nval;
                  if (localStreamTokenizer.nextToken() == -2)
                  {
                    m = (int)localStreamTokenizer.nval;
                    if (localStreamTokenizer.nextToken() == -2)
                    {
                      n = (int)localStreamTokenizer.nval;
                      if (localStreamTokenizer.nextToken() == -2)
                      {
                        d4 = localStreamTokenizer.nval;
                        if (localStreamTokenizer.nextToken() == -2)
                        {
                          d6 = localStreamTokenizer.nval;
                          if (localStreamTokenizer.nextToken() == -3)
                          {
                            if (localStreamTokenizer.sval.equals("true")) {
                              bool2 = true;
                            }
                            if (localStreamTokenizer.nextToken() == -3)
                            {
                              if (localStreamTokenizer.sval.equals("true")) {
                                bool4 = true;
                              }
                              if (localStreamTokenizer.nextToken() == -3)
                              {
                                if (localStreamTokenizer.sval.equals("false")) {
                                  bool3 = false;
                                }
                                if (localStreamTokenizer.nextToken() == -3)
                                {
                                  if (localStreamTokenizer.sval.equals("true")) {
                                    bool5 = true;
                                  }
                                  if (localStreamTokenizer.nextToken() == -3)
                                  {
                                    if (localStreamTokenizer.sval.equals("true")) {
                                      i1 = 1;
                                    }
                                    if (localStreamTokenizer.nextToken() == -2) {
                                      i2 = (int)localStreamTokenizer.nval;
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
              localVector.addElement(new Integer(i2));
              localObject = new ERectangle(i, k, m, n, d4, d6);
              ((Element)localObject).movable = bool2;
              ((Element)localObject).etransient = bool3;
              ((Element)localObject).force_circ = bool4;
              ((Element)localObject).force_rotate = bool5;
              ((Element)localObject).force_translate = (i1!=0);
              paramVector2.addElement(localObject);
              do
              {
                localStreamTokenizer.nextToken();
                if (localStreamTokenizer.ttype == 10) {
                  break;
                }
              } while (localStreamTokenizer.ttype != -1);
            }
            else if ("eo".equals(localStreamTokenizer.sval))
            {
              i = 0;
              k = 0;
              m = 0;
              n = 0;
              d4 = 0.0D;
              d6 = 0.0D;
              bool2 = false;
              bool3 = true;
              bool4 = false;
              bool5 = false;
              i1 = 0;
              i2 = -1;
              if (localStreamTokenizer.nextToken() == -2)
              {
                i = (int)localStreamTokenizer.nval;
                if (localStreamTokenizer.nextToken() == -2)
                {
                  k = (int)localStreamTokenizer.nval;
                  if (localStreamTokenizer.nextToken() == -2)
                  {
                    n = (int)localStreamTokenizer.nval;
                    if (localStreamTokenizer.nextToken() == -2)
                    {
                      m = (int)localStreamTokenizer.nval;
                      if (localStreamTokenizer.nextToken() == -2)
                      {
                        d4 = localStreamTokenizer.nval;
                        if (localStreamTokenizer.nextToken() == -2)
                        {
                          d6 = localStreamTokenizer.nval;
                          if (localStreamTokenizer.nextToken() == -3)
                          {
                            if (localStreamTokenizer.sval.equals("true")) {
                              bool2 = true;
                            }
                            if (localStreamTokenizer.nextToken() == -3)
                            {
                              if (localStreamTokenizer.sval.equals("true")) {
                                bool4 = true;
                              }
                              if (localStreamTokenizer.nextToken() == -3)
                              {
                                if (localStreamTokenizer.sval.equals("false")) {
                                  bool3 = false;
                                }
                                if (localStreamTokenizer.nextToken() == -3)
                                {
                                  if (localStreamTokenizer.sval.equals("true")) {
                                    bool5 = true;
                                  }
                                  if (localStreamTokenizer.nextToken() == -3)
                                  {
                                    if (localStreamTokenizer.sval.equals("true")) {
                                      i1 = 1;
                                    }
                                    if (localStreamTokenizer.nextToken() == -2) {
                                      i2 = (int)localStreamTokenizer.nval;
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
              localVector.addElement(new Integer(i2));
              localObject = new ERing(i, k, n, m, d4, d6);
              ((Element)localObject).movable = bool2;
              ((Element)localObject).etransient = bool3;
              ((Element)localObject).force_circ = bool4;
              ((Element)localObject).force_rotate = bool5;
              ((Element)localObject).force_translate = (i1!=0);
              paramVector2.addElement(localObject);
              do
              {
                localStreamTokenizer.nextToken();
                if (localStreamTokenizer.ttype == 10) {
                  break;
                }
              } while (localStreamTokenizer.ttype != -1);
            }
            else
            {
              double d1;
              double d2;
              if ("pp".equals(localStreamTokenizer.sval))
              {
                d1 = 0.0D;
                d2 = 0.0D;
                d4 = 1.0D;
                d6 = PPD.PARTICLE_POS_Q;
                if (localStreamTokenizer.nextToken() == -2)
                {
                  d1 = localStreamTokenizer.nval;
                  if (localStreamTokenizer.nextToken() == -2)
                  {
                    d2 = localStreamTokenizer.nval;
                    if (localStreamTokenizer.nextToken() == -2)
                    {
                      d4 = localStreamTokenizer.nval;
                      if (localStreamTokenizer.nextToken() == -2) {
                        d6 = localStreamTokenizer.nval;
                      }
                    }
                  }
                }
                paramVector1.addElement(new ParticlePos(d1, d2, (Element)localObject, d4, d6));
                do
                {
                  localStreamTokenizer.nextToken();
                  if (localStreamTokenizer.ttype == 10) {
                    break;
                  }
                } while (localStreamTokenizer.ttype != -1);
              }
              else if ("pn".equals(localStreamTokenizer.sval))
              {
                d1 = 0.0D;
                d2 = 0.0D;
                d4 = 1.0D;
                d6 = PPD.PARTICLE_NEG_Q;
                if (localStreamTokenizer.nextToken() == -2)
                {
                  d1 = localStreamTokenizer.nval;
                  if (localStreamTokenizer.nextToken() == -2)
                  {
                    d2 = localStreamTokenizer.nval;
                    if (localStreamTokenizer.nextToken() == -2)
                    {
                      d4 = localStreamTokenizer.nval;
                      if (localStreamTokenizer.nextToken() == -2) {
                        d6 = localStreamTokenizer.nval;
                      }
                    }
                  }
                }
                paramVector1.addElement(new ParticleNeg(d1, d2, (Element)localObject, d4, d6));
                do
                {
                  localStreamTokenizer.nextToken();
                  if (localStreamTokenizer.ttype == 10) {
                    break;
                  }
                } while (localStreamTokenizer.ttype != -1);
              }
              else if (localStreamTokenizer.nextToken() != 10)
              {
                if (localStreamTokenizer.ttype != -1) {
                	done=true;
                  //break label36;
                }
              }
            }
          }
        }
        break;
      }
    }
    paramInputStream.close();
    if (localStreamTokenizer.ttype != -1) {
      throw new FileFormatException(localStreamTokenizer.toString());
    }
    for (int j = 0; j < localVector.size(); j++)
    {
      k = ((Integer)localVector.elementAt(j)).intValue();
      if (k != -1)
      {
        Element localElement1 = (Element)PPD.elementBox.elementAt(j);
        Element localElement2 = (Element)PPD.elementBox.elementAt(k);
        localElement1.connection = localElement2;
        localElement2.connection = localElement1;
      }
    }
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/Data.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */