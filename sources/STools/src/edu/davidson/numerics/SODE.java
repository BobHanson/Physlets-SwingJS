////////////////////////////////////////////////////////////////////////////////
//	SODE.java
//	Wolfgang Christian
package edu.davidson.numerics;

/**
 * SODE is an abstract class that specifies the public methods for an ODE solver.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 1.0, Date: 2000/10/28
 */
public abstract class SODE {

    public SODE() {
    }

/**
 * Set the SDifferentiable object that will provide the rate equations to the solver.
 *
 * @param d the SDifferentiable object
 *
 * @see SDifferentiable
 */
    abstract public void setDifferentials(SDifferentiable d);

/**
 * Get the tolerance.
 *
 * The step size will be reduced in a variable step size method until the estimated error is less than the tolerance.
 *
 * @see #setTol
 * @see #setH
 */
    abstract public double getTol();

/**
 * Get the tolerance. The tolerance is used by variable a step size method to set the set size.
 *
 * This method has no effect on fixed step size methods.
 *
 * @param t the tolerance
 *
 * @see #getTol
 */
    abstract public void   setTol(double t);

/**
 * Get the current step size.
 *
 * The step size can change in variable step size methods.
 *
 * @see #setTol
 */
    abstract public double getH();

/**
 * Set the initial step size.
 *
 * The step size can change if the ODE solver uses a variable step size.
 *
 * @param h the step size.
 *
 * @see #setTol
 */
    abstract public void   setH(double h);

/**
 * Calculate a new state, x, by incrementing the indepenent variable by dx.
 *
 * Many ODE steps may be performed in order to increment the indepenent variable by dx.  For example, if dx is one and
 * the step size is set to 0.1 by the setH method, then the ODE solver should perform 10 ODE steps.
 *
 * @param dx the increment in the indepenent variable.
 * @param x the current state variables
 *
 * @return int the number of steps that the ode solver performed
 */
    abstract public int step(double dx, double x[]);

/**
 * Calculate a new state , x, by incrementing the indepenent variable by a single ODE step.
 *
 * This methos is can used for testing the ODE method.  The increment, dx, may change if
 * the numerical method uses a variable step size.  The actual step size is returned.
 *
 * @param dx the step size
 * @param x the current state variables
 *
 * @return the step size that was perfomed
 *
 * @see #step
 */
    abstract public double stepODE(double dx, double x[]);

/**
 * Set the number of euations that should be solved.
 *
 * This method can be used to allocate memory for temporary arrays and to inform the ode solver
 * that not all state variables have valid rate equations.
 *
 */
    abstract public void setNumberOfEquations(int n);

}