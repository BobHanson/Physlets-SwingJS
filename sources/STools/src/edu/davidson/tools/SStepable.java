package edu.davidson.tools;

/**
 * The SStepable interface is designed for animations that use SClock.,
 *
 *
 * @author             Wolfgang Christian
 * @version            Revision: 1.0, Date: 2000/07/17
 */
public interface SStepable{

/**
 * Step the state of the object by an amount dt.
 *
 * The current value of the clock is passed as the time parameter.  At the end of the
 * step the object should be in a state corresponding given by time+dt.
 *
 * @param dt the time step
 * @param time the current time
 */
    public void     step(double dt, double time);
}

