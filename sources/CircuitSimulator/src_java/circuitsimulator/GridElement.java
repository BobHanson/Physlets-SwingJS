package circuitsimulator;

/**
 * Used by circuitGrid. Representation of each grid point with its specific connections.
 * 
 * @author Toon Van Hoecke
 */
public class GridElement
{
    int vIndex, nOfUntreatedCons, bpIndex;
    VEquation vEquation;
    BranchPoint branchPoint;
    CircuitElement[] connection = new CircuitElement[4]; //0 : left, 1 : under, 2: right, 3 : above
    boolean defined; 
    
    public GridElement() {
        vIndex=0;
        bpIndex=0;
        nOfUntreatedCons=0;
        defined=false;
        vEquation = null;
        branchPoint = null;
    
		//{{INIT_CONTROLS
		//}}
	}
    
    public void connected(VEquation veq) {
        vEquation = veq;
        defined=true;
    }
    
    public void connected(BranchPoint bp) {
        branchPoint = bp;
    }
    
    public int getVIndex(){
        int v=-1;
        if (vEquation!=null) v=vEquation.indexV1;
        return v;
    }
    
    public int numberOfCons(){
        int n=0;
        for (int i=0;i<4;i++) if (connection[i]!=null) n++;
        return n;
    }
	//{{DECLARE_CONTROLS
	//}}
}
