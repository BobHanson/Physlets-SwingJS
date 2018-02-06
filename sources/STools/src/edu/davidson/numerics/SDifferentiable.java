package edu.davidson.numerics;

/**
 * The SDifferentiable interface is designed to pass the rate equations to ordinay differential equation solvers.
 *
 * The Science Tools package contains a number of differential equations equations solvers including a Runge-Kutta
 * fourth order algorithm with adaptive step size, SRK45.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 1.0, Date: 2000/07/17
 */
public interface SDifferentiable{

/**
 * The rate method calculates the rate of change for a set of variables.
 *
 * The current values of the variables are used to calculate the rate of change.  The rates
 * are in the same order as the variables, x.  If the first variable, x[0], is time, its rate
 * will be 1 since dt/dt=1.  Other rates would, of course, depend on the variables.
 *
 * @param x the current variable values
 */
    public double[] rate(double[] x);

/**
 * The number of rate equations.
 *
 * The number of variables may be greater than the number or rate eqations. This method returns the number of
 * rate equations.  Typically, the ode solver will ignore extra variables.
 *
 * @return int the number of differential equations
 */
    public int getNumEqu();
}

