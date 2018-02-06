
package ising;

import java.awt.*;

public final class IsingModel {

   private final int J = 1;
   private int spins[][];
   private int backup[][];         // stores spins painted the last time, used to minimize paint time
   private int L;
   private double E;
   private int M;
   private int flipsPerStep=1;
   private double T, H;
   public int time_counter=0;
   public double ave_M, ave_E;

   private double newH;
   boolean hasChanged=false;

   public IsingModel(){
      reinitialize(32, 2, 0);
   }
   public IsingModel(int L, double T, double H) {  reinitialize(L, T, H);  }

   public double getE(){return E/(double)(L*L);}

   public double getM(){return M/(double)(L*L); }

   public double getT(){return T; }

   public double getB(){return H; }

   public int getFlipsPerStep(){return flipsPerStep; }
   public void setFlipsPerStep(int f){flipsPerStep=f; }

   public int getArraySize(){return L; }
   public void setArraySize(int arraySize){
      if(arraySize==L) return;
      reinitialize(arraySize, T,H);
   }

   public void reinitialize(){
       reinitialize( L,  T,  H);
   }

   synchronized public boolean reinitialize(int L, double T, double H) {
       this.L = L;
       flipsPerStep=L*L;
       if(T<0) {
           System.out.println("\nTemperature is negative, automatically negated!");
           T *= -1;
       }
       this.T = T;
       this.H = H;
       time_counter = 0;
       ave_M = ave_E = 0;
       spins = new int[L][L];
       backup = new int[L][L];

       for(int i=0; i<L; i++)
          for(int j=0; j<L; j++)
             {
                 if(Math.random() > 0.5)
                    spins[i][j] = 1;
                 else
                    spins[i][j] = -1;
              }
       getME();
       return true;
    }

   public void resetT(double T) {
       if(T<0) {
           System.out.println("\nTemperature is negative, automatically negated!");
           T *= -1;
       }
       this.T = T;
   }

   /**
    * Signal a change in the external field.
    */
   public synchronized void resetH(double H) {
       hasChanged=true;
       this.newH = H;
   }

   /**
    * Adjust the enerergy to account for changes in the external field.
    */
   synchronized void adjustE() {
       E-=(newH-H)*M;
       H=newH;
       hasChanged=false;
   }

/*
   private int pbc(int x) {
       if(x >= L)
           return (x-L);
       else if(x<0)
           return (x+L);
       else
           return x;
   }
   */
   /**
    * Calculate the total energy and magnetization by summing over all spin states.
    */
   synchronized void getME() {
       int nn_sum = 0;
       M = 0;

       for(int i=0; i<L; i++)
          for(int j=0; j<L; j++)
             {
                 M += spins[i][j];
                 if(spins[i][(j+1)%L] == spins[i][j])
                     nn_sum ++;
                 else
                     nn_sum --;

                 if(spins[(i+1)%L][j] == spins[i][j])
                     nn_sum ++;
                 else
                     nn_sum --;
             }
       E = -J*nn_sum - H*M;
       newH=H;
       hasChanged=false;
   }

   private boolean flip() {
       int x = (int) (Math.random()*L);
       int y = (int) (Math.random()*L);
       int delta_M = -2*spins[x][y];

       int delta_nn_sum = spins[(x-1+L)%L][y] + spins[(x+1)%L][y]
                        + spins[x][(y-1+L)%L] + spins[x][(y+1)%L];
       delta_nn_sum = -2 * spins[x][y] * delta_nn_sum;

       double delta_E = -J * delta_nn_sum - H * delta_M;

       if(delta_E <=0 || Math.random()<Math.exp(-delta_E/T))
         {
             spins[x][y] *= -1;
             M += delta_M;
             E += delta_E;
             return true;
         }
       else
             return false;
    }

   public void onestep() {
       if(hasChanged)this.adjustE();
       for(int i=0; i<flipsPerStep; i++)
          flip();
       ave_M += M;
       ave_E += E;
       time_counter ++;
       if(hasChanged)this.adjustE();
   }

   public void setSpinBloc(int xStart, int yStart, int xEnd, int yEnd, int setOption) {
       for(int i=xStart; i<=xEnd; i++)
	   for(int j= yStart; j<=yEnd; j++)
              if(setOption == 0) spins[i][j] *= -1;
              else spins[i][j] = setOption;

       getME();
   }

   // only paint spins that have changed values
   public void drawSpins(Graphics g, int x0, int y0, int width, int height) {
       double dw = ((double) width) / L;
       double dh = ((double) height) / L;

       for(int i=0; i<L; i++)
           for(int j=0; j<L; j++)
               if(spins[i][j] != backup[i][j]) {  // only paint spins that have changed values
                   backup[i][j] = spins[i][j];
                   if(spins[i][j] == 1)
                       g.setColor(Color.red);
                   else
                       g.setColor(Color.green);

                   int x1 = (int)(x0 + i*dw);
                   int x2 = (int)(x0 + (i+1)*dw);
                   int y1 = (int)(y0 + j*dh);
                   int y2 = (int)(y0 + (j+1)*dh);
                   g.clearRect(x1, y1, x2 - x1, y2 - y1);
                   g.fillRect(x1, y1, x2 - x1, y2 - y1);
               }
   }

   public void reDrawSpins(Graphics g, int x0, int y0, int width, int height) {
       double dw = ((double) width) / L;
       double dh = ((double) height) / L;

       for(int i=0; i<L; i++)
           for(int j=0; j<L; j++) {
               backup[i][j] = spins[i][j];
               if(spins[i][j] == 1)
                   g.setColor(Color.red);
               else
                   g.setColor(Color.green);
               int x1 = (int)(x0 + i*dw);
               int x2 = (int)(x0 + (i+1)*dw);
               int y1 = (int)(y0 + j*dh);
               int y2 = (int)(y0 + (j+1)*dh);

               g.fillRect(x1, y1, x2 - x1, y2 - y1);
           }
   }
}
