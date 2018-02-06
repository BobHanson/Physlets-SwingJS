package minkowski;
import java.lang.Object;

/*
 *
 * Transformation
 *
 */
public class Transformation extends Object
{
   public char flag='L';	//R=Rotation,  G=Galilean, L=Lorentz

   public final void setType(char flag_)
   {
	   flag=flag_;
   }

   public final char getType()
   {
	   return flag;
   }


   public Transformation(char flag_)
   {
	   flag=flag_;
   }
   public final double transformH(double h_, double v_, double p_)
   {
		switch(flag)
		{
		case 'R':
			double p=p_*Math.PI;
			return h_*Math.cos(p)+v_*Math.sin(p);
		case 'G':
			return h_-p_*v_;
		case 'L':
			double gamma=1.0/Math.sqrt(1-p_*p_);
			//return (h_+p_*v_)*gamma;
      return (h_-p_*v_)*gamma;
		default: return 0;
		}
   }
   public final  double transformV(double h_,double v_, double p_)
   {
		switch(flag)
		{
		case 'R':
			double p=p_*Math.PI;
			return -h_*Math.sin(p)+v_*Math.cos(p);
		case 'G':
			return v_;
		case 'L':
			double gamma=1.0/Math.sqrt(1-p_*p_);
			//return (v_+h_*p_)*gamma;
      return (v_-h_*p_)*gamma;
		default: return 0;
		}
   }

}

