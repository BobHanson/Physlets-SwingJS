
/**
 * Title:        Numerics<p>
 * Description:  Numeric Routines for Physlets<p>
 * Copyright:    Copyright (c) 2000<p>
 * @author Wolfgang Christian
 */
package edu.davidson.numerics;

/**
 * <p>A handy collection of methods for taking derivatives, etc.
 * </p>
 *
 * @version 1.0, Sept 22 2000
 * @author  Wolfgang Christian
 */
public class Util {

    public Util() {
    }

/**
 * Take the numeric derivative of an input array.
 *
 * @param dx the change in the independent variable between input values
 * @param input the input array
 * @param output the derivative of the input array
 */
    public static void numericDerivative(double dx, double[] input, double[] output ){
    int len=input.length;
    if( len!=output.length){
        System.out.println("Vector lengths do not match.");
        return;
    }
    if(len<2){
        System.out.println("Vector length must be > 1.");
        return;
    }
    if(len<3){
        output[0]=(input[1]-input[0])/dx;
        output[1]=output[0];
        return;
    }
    output[0]=(-0.5*input[2]+2.0*input[1]-1.5*input[0])/dx;  // see F. Vesely p 14
    output[len-1]=(1.5*input[len-1]-2.0*input[len-2]+0.5*input[len-3])/dx;  // see F. Vesely p 15;
    for(int i=1; i<len-1; i++){
        output[i]=(input[i+1]-input[i-1])/(2.0*dx);
    }
  }
}