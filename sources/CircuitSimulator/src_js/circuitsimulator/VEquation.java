package circuitsimulator;

/**
 * Used by circuitGrid. Forms the right rows of a matrix, corresponding to the (V,I)-equation for every element.
 * 
 * @author Toon Van Hoecke
 */
public class VEquation // equation:  indexV2 - indexV1 = f(z, indexI)
{
    int indexV1, indexV2, indexI1, indexI2;
    int sign;
    CircuitElement z;
    int fromr, fromc, tor, toc;
    int direction;
    
    public VEquation () {
        indexV1 = indexV2 = indexI1 = indexI2 = 0;
        fromr = fromc = tor = toc = 0;
        z = null;
        sign = 1;
        direction = 0;
    }
    
    VEquation (GridElement thisel, int dir, int hereVIndex, int nextVIndex, int iIndex) {
        z=thisel.connection[dir]; // connect this VEquation to the right CircuitElement
        direction = dir;
        sign = (dir != (int) thisel.connection[dir].direction) ? -1 : 1;
        indexV1 = hereVIndex;
        indexV2 = nextVIndex;
        indexI1 = iIndex;
        thisel.connection[dir].vequation = this; // connect the CircuitElement to this VEquation
    }
    
    public void leftValue(int nOV, Matrix matrix, int r) {
        matrix.set_elem(r, indexV1, z.indexVHere());
        matrix.set_elem(r, indexV2, z.indexVNext());
        matrix.set_elem(r, nOV+indexI1, z.impedance());
        if (z.numberOfNodes == 4) matrix.set_elem(r, nOV+z.indexIcoupled(), z.impedanceCoupled());
    }
     
    public void rightValue(int nOV, int nOPars, Matrix matrix, int r) {
           //next line activates the column of the corresponding input voltage and polarity
        matrix.set_elem(r, nOPars+z.inputIndex, z.input(sign));
        matrix.set_elem(r, nOPars, (-1)*z.rightFunction(sign));//activates non-linear functions and current sources
        matrix.set_elem(r, indexV1, z.integralVHere());//activates integral parts
        matrix.set_elem(r, indexV2, z.integralVNext());//activates integral parts
        matrix.set_elem(r, nOV+indexI1, z.differential());//activates differential parts
        if (z.numberOfNodes == 4) matrix.set_elem(r, nOV+z.indexIcoupled(), z.differentialCoupled());
    }
    
    public void print () {
		System.out.println(z.getMyName()+" in VEquation: indexV1/indexV2/indexI1: "
		       +indexV1+"/"+indexV2+"/"+indexI1);
    }
}
