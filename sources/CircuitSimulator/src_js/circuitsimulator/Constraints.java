package circuitsimulator;

/**
 * Used by circuitGrid. Contains the linear constraints of the circuit.
 * 
 * @author Toon Van Hoecke
 */
class Constraints 
{ 
    int curVindex=0,prevVindex=0,curIindex=0,prevIindex=0;
    double curVvalue=0.0,prevVvalue=0.0,curIvalue=0.0,prevIvalue=0.0;

    Constraints (int i, int j, int k) {
        curVindex=i;prevVindex=j;curIindex=k;
        curVvalue=1.0;prevVvalue=-1.0;curIvalue=1e-20;
    }
    
    Constraints (int i, int j, int k, int l, double iv, double jv, double kv, double lv) {
        curVindex=i;prevVindex=j;curIindex=k;prevIindex=l;
        curVvalue=iv;prevVvalue=jv;curIvalue=kv;prevIvalue=lv;
    }

    public void leftValue(int nOV, Matrix matrix, int r) {
        matrix.set_elem(nOV+1+r, curVindex, curVvalue);
        matrix.set_elem(nOV+1+r, prevVindex, prevVvalue);
        matrix.set_elem(nOV+1+r, nOV+curIindex, curIvalue);
        matrix.set_elem(nOV+1+r, nOV+prevIindex, prevIvalue);
    }
}
    
