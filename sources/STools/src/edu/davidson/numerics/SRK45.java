package edu.davidson.numerics;

/**
 * Class SRK45
 *
 *
 * @author
 * @version %I%, %G%
 */
public final class SRK45 extends SODE {
   private static int maxMessages=4;
  //static double[]   a={1,2.0/9.0, 1.0/3.0, 3.0/4.0, 1.0 , 5.0/6.0};
  static double[][] b        = {
    {2.0 / 9.0}, {1.0 / 12.0, 1.0 / 4.0}, {69.0 / 128.0, -243.0 / 128.0, 135.0 / 64.0},
    {-17.0 / 12.0, 27.0 / 4.0, -27.0 / 5.0, 16.0 / 15.0},
    {65.0 / 432.0, -5.0 / 16.0, 13.0 / 16.0, 4.0 / 27.0, 5.0 / 144.0}
  };
  static double[]   ch       = {47.0 / 450.0, 0.0, 12.0 / 25.0, 32.0 / 225.0, 1.0 / 30.0, 6.0 / 25.0};
  static double[]   ct       = {-1.0 / 150.0, 0.0, 3.0 / 100.0, -16.0 / 75.0, -1.0 / 20.0, 6.0 / 25.0};
  double            stepSize = 0.01;
  double            fixedStepSize=stepSize;
  int               numEqu   = 0;
  double[]          tempState;
  double[][]        f;
  SDifferentiable   equations;
  double            err;
  double            tol  = 1.0e-6;
  int               count = 0;

  /**
   * Constructor SRK45
   *
   */
  public SRK45() {}

  /**
   * Method getH
   *
   *
   * @return
   */
  public double getH() {
    return stepSize;
  }

  /**
   * Method setH
   *
   * @param h
   */
  public void setH(double h) {
    stepSize = h;
  }

  /**
   * Method getTol
   *
   *
   * @return
   */
  public double getTol() {
    return tol;
  }

  /**
   * Method setTol
   *
   * @param t
   */
  public void setTol(double t) {
    this.tol = t;
  }

  /**
   * Method step
   *
   * @param dx
   * @param x
   *
   * @return
   */
  public int step(double dx, double x[]) {
     if(dx==0)return 0;
    if(x.length < numEqu) {  // check to make sure that the arrarys are large enough
      numEqu    = x.length;
      tempState = new double[numEqu];
      f         = new double[6][numEqu];
      System.out.println("Warning:  Temporary arrays reset.");
    }
    fixedStepSize=dx;
    if(dx>0)stepSize=Math.abs(stepSize);
    else stepSize=-Math.abs(stepSize);
    double timeError=0;
    count = 0;
    if(fixedStepSize>0)timeError = fixedStepSize - plus(x);
    else timeError = fixedStepSize - minus(x);
    return count;
  }

  /**
   * Method stepODE
   *
   * @param dx
   * @param x
   *
   * @return
   */
  public double stepODE(double dx, double x[]) {
    if(x.length < numEqu) {  // check to make sure that the arrarys are large enough
      System.out.println("Error:  The temporary arrays are not large enough.");
      return 0;
    }
    if(dx>=0)stepSize=Math.abs(dx);
    else stepSize=-Math.abs(dx);
    return stepRK45(x);
  }

  // this ought to be private BUT it is public for backward compatibility with old EField and BField applets.

  /**
   * Method stepRK45
   *
   * @param _x
   *
   * @return
   */
  public double stepRK45(double[] _x) {
    double truncErr;
    double state[] = _x;
    if(state.length < numEqu) {           // check to make sure that the arrarys are large enough
      numEqu    = state.length;
      tempState = new double[numEqu];
      f         = new double[6][numEqu];  // the six intermediate rates
      System.out.println("Warning:  Temporary arrays reset.");
    }
    int    i, j, k;  // counters
    double currentStep = stepSize;
    System.arraycopy(state, 0, tempState, 0, numEqu);  // save the initial state
    //ode.getRates(state,f[0]);         // get the initial rates
    double[] dydx = equations.rate(state);
    for(i = 0; i < numEqu; i++) {
      f[0][i] = dydx[i];
    }
    do {
      currentStep = stepSize;
      for(k = 1; k < 6; k++) {
        for(i = 0; i < numEqu; i++) {
          state[i] = tempState[i];                 // reset to the initial state
          for(j = 0; j < k; j++) {
            state[i] = state[i] + stepSize * b[k - 1][j] * f[j][i];
          }
        }
        //ode.getRates(state,f[k]); // get the intermediate rates
        dydx = equations.rate(state);
        for(i = 0; i < numEqu; i++) {
          f[k][i] = dydx[i];
        }
      }
      err = 0;
      for(i = 0; i < numEqu; i++) {
        state[i] = tempState[i];                   // reset the initial state
        truncErr = 0;
        for(k = 0; k < 6; k++) {
          state[i] += stepSize * ch[k] * f[k][i];  // step the inital state
          truncErr += stepSize * ct[k] * f[k][i];  // estimate the error
        }
        truncErr = Math.abs(truncErr);
        if(err < truncErr) {
          err = truncErr;                          // find the maximum error
        }
      }
      if(err <= Double.MIN_VALUE) {
        err = tol / 1.0e5;                         // force the stepsize to grow by 10
      }
      // find h step for the next try.
      if(err > tol) {                              // shrink if error is too large
        stepSize = 0.9 * stepSize * Math.pow(tol / err, 0.25);
      } else if(err < 2.0 * tol) {                 //grow if the error is too small
        stepSize = 0.9 * stepSize * Math.pow(tol / err, 0.2);
      }
    } while(err > tol);
    return currentStep;  // the value of the step that was actually taken.
  }

  /**
   * Method setDifferentials
   *
   * @param d
   */
  public final void setDifferentials(SDifferentiable d) {
    equations = d;
    numEqu    = equations.getNumEqu();
    tempState = new double[numEqu];
    f         = new double[6][numEqu];
  }

  /**
   * Method getDiffernetials
   *
   *
   * @return
   */
  public final SDifferentiable getDiffernetials() {
    return equations;
  }

  /**
   * Method setNumberOfEquations
   *
   * @param n
   */
  public final void setNumberOfEquations(int n) {
    numEqu    = n;
    tempState = new double[numEqu];
    f         = new double[6][numEqu];
  }

  private double plus(double[] _x) {  // positive step size
    double remainder = fixedStepSize;  // dt will keep track of the remaining time
    if((stepSize <= 0) || (                            // is the stepsize postive?
        stepSize > fixedStepSize) || (                 //is the stepsize larger than what is requested?
            fixedStepSize - stepSize == fixedStepSize  // is the stepsize smaller than the precision?
                )) {
      stepSize= fixedStepSize;                           // reset the step size and let it adapt to an optimum size
    }
    while(remainder > tol * fixedStepSize) {           // check to see if we are close enough
      count++;
      double oldRemainder = remainder;
      if(remainder <= stepSize) {           // temporarily reduce the step size so that we hit the exact dt value
        double tempStep = stepSize;         // save the current optimum step size
        stepSize=remainder;                  // set the RK4/5 step size to the remainder
        double delta = stepRK45(_x);
        remainder -= delta;
        stepSize=Math.min(tempStep, delta);  // restore the optimum step size
      } else {
        remainder -= stepRK45(_x);                   // do a rk45 step and set the remainder
      }
      // check to see if roundoff error prevents further calculation.
      if((Math.abs(oldRemainder - remainder) <= 100 * Double.MIN_VALUE)
          || (tol * fixedStepSize / 100.0 > stepSize)) {
        if(maxMessages <= 0) {
          break;
        }
        maxMessages--;
        System.out.println("Warning: RK45MultiStep did not converge. Remainder=" + remainder);
        if(maxMessages == 0) {
          System.out.println("Further warnings surppressed.");
        }
        break;
      }
    }
    return remainder;
  }

  private double minus(double[] _x) {  // negative step size
    double remainder = fixedStepSize;  // dt will keep track of the remaining time
    if((stepSize >= 0) || (                            // is the step negative?
        stepSize < fixedStepSize) || (                 // is the stepsize larger than what is requested?
            fixedStepSize - stepSize == fixedStepSize  // is the stepsize smaller than the precision?
                )) {
      stepSize=fixedStepSize;                           // reset the step size and let it adapt to an optimum size
    }
    while(remainder < tol * fixedStepSize) {           // check to see if we are close enough
      count++;
      double oldRemainder = remainder;
      if(remainder >= stepSize) {
        double tempStep = stepSize;         // save the current optimum step size
        stepSize=remainder;                  // set the step RK4/5 size to the remainder
        double delta = stepRK45(_x);
        remainder -= delta;
        stepSize=Math.max(tempStep, delta);  // restore the optimum step size
      } else {
        remainder -= stepRK45(_x);                    // do a rk45 step and set the remainder
      }
      // check to see if roundoff error prevents further calculation.
      if((Math.abs(oldRemainder - remainder) <= 100 * Double.MIN_VALUE)
          || (tol * fixedStepSize / 100.0 < stepSize)) {
        if(maxMessages <= 0) {
          break;
        }
        maxMessages--;
        System.out.println("Warning: RK45MultiStep did not converge. Remainder=" + remainder);
        if(maxMessages == 0) {
          System.out.println("Further warnings surppressed.");
        }
        break;
      }
    }
    return remainder;
  }
}
