package circuitsimulator;
import java.util.Vector;
import java.util.Enumeration;
/**
 * This class is the engine of the circuit applet.
 * The circuit grid is constructed here with all its components.
 * It contains all circuit evaluation and calculation routines.
 *
 * @author Toon Van Hoecke
 */
public class CircuitGrid
{
    int rows, cols;
    private int bpStartVEquation=0, nextBranchPoint=0, startIIndex=0;
    private int currow=0, curcol=0, startVIndex=0, curVIndex=0, curIIndex=0;
    private int numberOfI, numberOfVE, lastVIndex, lastIIndex, numberOfCirc;

    /**
     * Number of voltage parameters
     */
    int numberOfV, numberOfPars;
    private int direction, dirold; //0: left, 1: under, 2: right, 3: above
    private Circuit circuit;

    GridElement[][] element;
    Vector vEquations = new Vector(); //vector of V(I) characteristics for each CircuitElement
    private Vector branchPoints = new Vector(); //vector of GridElements with 3 or 4 connections
    //private Vector equalVs = new Vector(); // vector of V indices which are equal to each other
    Vector cirElemList = new Vector(); //vector of CircuitElements
	Vector sourceContainer = new Vector(); //vector of Sources
	private Vector constraints = new Vector(); //vector of constraints between separate circuits

    double[] y;
    private Matrix a,aInv,b,multip;
    boolean leftlinear=true;
    boolean rightlinear=true;

    private VEquation vEquationPtr;
    private BranchPoint branchPointPtr;

    /**
     * Standard constructor to build the grid with rows and cols
     *
     * @param trows number of rows
     * @param tcols number of columns
     * @param circ The circuit applet that is using this class
     */
    CircuitGrid (int trows, int tcols, Circuit circ) {
        circuit = circ;
        rows = trows; cols = tcols;
        element = new GridElement[rows][cols];
        for (int i=0;i<rows;i++) for (int j=0;j<cols;j++)
            element[i][j] = new GridElement();
		if ((circuit.debugLevel&circuit.DEBUG_IO)>0) System.out.println(rows+"x"+cols+" elements made");
    }

    //**************** circuit parsing methods *********************

    public boolean constructEquationBase() {// construction of VEquations and BranchPoints
        if (initDefinition()== false) return false;
        int firstx=currow, firsty=curcol; //first upper left corner coordinates
        boolean finished = true;
        do {
            do {
                if (element[currow][curcol].defined == true) {
                    if (element[currow][curcol].nOfUntreatedCons>2)
                        stopBranchCorrections(); // arrival in a BranchPoint
                    else firstLoopCorrections(); // procedure for the first loop
                    finished = startNextBranchPoint(); // step to next unfinished branch
                } else { // find start direction of the next point in the branch
                    findNextDirection(); // find start direction of the next point in the branch
                        // next line makes a new BranchPoint if necessary
                    if (element[currow][curcol].nOfUntreatedCons>2) addNewBranchPoint();
                    addNewVEquation(); // add a new V Equation
                    changeCoords(); // step to the next point
                    finished = false;
                }
            } while (!finished);
        } while (findNextCircuit());
		if ((circuit.debugLevel&circuit.DEBUG_NUM)>0)
		    System.out.println("end of construction: lastVIndex="+lastVIndex+", lastIIndex="+lastIIndex);
        numberOfV = lastVIndex+1;//number of V equations is lastVIndex+1
        numberOfI = lastIIndex+1;//number of I equations is lastIIndex+1
        numberOfPars = numberOfV + numberOfI;
        reset();
        return true;
    }

    public void reset(){
        y = new double[numberOfPars+sourceContainer.size()+1];
        for (int i=0;i<=numberOfPars+sourceContainer.size();i++) y[i]=0.0;
    }

    private boolean initDefinition() {
        numberOfCirc = 0; startVIndex = 0; startIIndex=0;
		if (this.cirElemList.isEmpty()) {
            System.out.println("No elements found !");
		    return false;
		}
		if ((circuit.debugLevel&circuit.DEBUG_IO)>0) System.out.println("Parse initialization started");
        // initialize the GridElements
        for (int i=0;i<rows;i++) for (int j=0;j<cols;j++){
            element[i][j].nOfUntreatedCons = element[i][j].numberOfCons();
            if (element[i][j].nOfUntreatedCons == 1) {
                if ((circuit.debugLevel&circuit.DEBUG_IO)>0) System.out.println("Open loop found !");
                return false;
            }
            element[i][j].defined = false;
        }
		if ((circuit.debugLevel&circuit.DEBUG_IO)>0) System.out.println("Loop is closed, all grid points initialized");
        // find left upper corner with smallest column index
        currow=0;curcol=0;
        while (element[currow][curcol].numberOfCons() == 0) {
            currow=0;
            while (element[currow][curcol].numberOfCons() == 0 && currow < rows-1) currow++;
            if (element[currow][curcol].numberOfCons() == 0) curcol++;
        }
		if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) System.out.println("first element found: "+currow+","+curcol);
        direction = 3;// put direction up to find first connection clockwise in a later stage
        // initialize the vectors equalVs, vEquations, branchPoints and also the indices
        //equalVs.removeAllElements();
        constraints.removeAllElements();
        vEquations.removeAllElements();
        branchPoints.removeAllElements();
        lastVIndex = 0; lastIIndex = 0;
        curIIndex = 0; curVIndex = 0;
        numberOfCirc++;
        return true;
    }

    private boolean findNextCircuit () {
        currow=0;curcol=0;
        while ((element[currow][curcol].numberOfCons() == 0) || element[currow][curcol].defined) {
            currow=0;
            while (((element[currow][curcol].numberOfCons() == 0) || element[currow][curcol].defined)
                    && (currow < rows-1)) currow++;
            if ((element[currow][curcol].numberOfCons() == 0) || element[currow][curcol].defined) curcol++;
            if (curcol == cols-1) {
		        if ((circuit.debugLevel&circuit.DEBUG_NUM)>0)
		            System.out.println("no additional circuits found: "+currow+","+curcol);
		        return false;
		    }
        }
		if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) System.out.println("next circuit found: "+currow+","+curcol);
        direction = 3;// put direction up to find first connection clockwise in a later stage
        lastVIndex++; lastIIndex++;
        startVIndex = lastVIndex; startIIndex = lastIIndex;
        numberOfCirc++;
        constraints.addElement(new Constraints(0,lastVIndex,0));
		return true;
    }

    private boolean startNextBranchPoint () { //find next BranchPoint and start new loop
        BranchPoint thisBranchPoint;
        boolean finished = true;
        nextBranchPoint=0;
        bpStartVEquation = vEquations.size();
        if (branchPoints.size()>0) {
            while ( nextBranchPoint < branchPoints.size()
                && ((BranchPoint)branchPoints.elementAt(nextBranchPoint)).toDefine == 0 )
                nextBranchPoint++;
            if (nextBranchPoint < branchPoints.size()) {
                thisBranchPoint = (BranchPoint) branchPoints.elementAt(nextBranchPoint);
                currow = thisBranchPoint.xcoord; curcol = thisBranchPoint.ycoord;
                curVIndex = element[currow][curcol].getVIndex();
                direction = thisBranchPoint.start(element[currow][curcol], lastIIndex);
		        if ((circuit.debugLevel&circuit.DEBUG_NUM)>0)
		            System.out.println("next branch started at "+currow+","+curcol+
		             ", lastVIndex/lastIIndex: "+lastVIndex+"/"+lastIIndex);
                if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) thisBranchPoint.print();
                lastIIndex++;
                addNewVEquation(); // make a new VEquation
                changeCoords();
                finished = false;
            }
        }
        return finished;
    }

    private void stopBranchCorrections() {
                //if a branchpoint is reached, correct the necessary indices
        int prevVIndex=curVIndex;
        branchPointPtr=(BranchPoint)branchPoints.elementAt(element[currow][curcol].bpIndex);
        branchPointPtr.stop(direction, lastIIndex);
        curVIndex= element[currow][curcol].getVIndex();
        if (lastVIndex>0) {
            int n = vEquations.size()-1;
            VEquation curVEquation=(VEquation)vEquations.elementAt(n);
            curVEquation.indexV2 = curVIndex;
            while (curVEquation.indexV1 == lastVIndex) {
                curVEquation.indexV1=curVIndex;
                if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) curVEquation.print();
                curVEquation=(VEquation)vEquations.elementAt(--n);
                curVEquation.indexV2=curVIndex;
            }
            if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) curVEquation.print();
        }
        if (((VEquation)vEquations.elementAt(bpStartVEquation)).indexV1 != prevVIndex) {
            lastVIndex--;
		} else {//when the loop is all wires, add element to the list of equal voltages.
            //equalVs.addElement(new EqualityIndices(prevVIndex,curVIndex,lastIIndex));
            constraints.addElement(new Constraints(prevVIndex,curVIndex,lastIIndex));
            if ((circuit.debugLevel&circuit.DEBUG_NUM)>0)
                System.out.println("Equality for V-indices: "+prevVIndex+"="+curVIndex+", with I-index="+lastIIndex);}
        if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) branchPointPtr.print();
    }

    private void firstLoopCorrections() {
                // if the starting point is reached, correct the necessary indices
        if (lastVIndex>0) {
            VEquation curVEquation;
            if (!branchPoints.isEmpty()) {
                branchPointPtr.iIndex[branchPointPtr.lastDirection]=startIIndex;
                lastIIndex--;
                if ((circuit.debugLevel&circuit.DEBUG_NUM)>0)
                    System.out.println(bpStartVEquation+"/"+vEquations.size());
                for (int n=bpStartVEquation;n<vEquations.size();n++) {
                    curVEquation=(VEquation)vEquations.elementAt(n);
                    curVEquation.indexI1=startIIndex;
                    if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) curVEquation.print();
                }
            }
            int n = vEquations.size()-1;
            curVEquation=(VEquation)vEquations.elementAt(n);
            curVEquation.indexV2 = startVIndex;
            if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) curVEquation.print();
            while (curVEquation.indexV1 == lastVIndex) {
                curVEquation.indexV1=startVIndex;
                if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) curVEquation.print();
                curVEquation=(VEquation)vEquations.elementAt(--n);
                curVEquation.indexV2=startVIndex;
            }
            lastVIndex--;
		} else {//when the loop is all wires, add element to the list of equal voltages.
            //equalVs.addElement(new EqualityIndices(0,0,0));
            constraints.addElement(new Constraints(0,0,0));
            if ((circuit.debugLevel&circuit.DEBUG_NUM)>0)
                System.out.println("Equality for V-indices: 0=0, with I-index=0");}
    }

    private void addNewVEquation() { //prepare a V-equation for the actual element
        if (element[currow][curcol].connection[direction].name().equals("wire") == false) {
            lastVIndex++;
            vEquations.addElement(new VEquation(element[currow][curcol],direction,
                                                curVIndex,lastVIndex,lastIIndex));
            curVIndex=lastVIndex;
            // check linearity of element
            if (element[currow][curcol].connection[direction].leftlinear == false) leftlinear = false;
            if (element[currow][curcol].connection[direction].rightlinear == false) rightlinear = false;
        } else {
            vEquations.addElement(new VEquation(element[currow][curcol],direction,
                                                curVIndex,curVIndex,lastIIndex));
        }
        vEquationPtr=(VEquation) vEquations.lastElement();

        if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) {
            System.out.print("In direction " +direction+": ");
            vEquationPtr.print();
        }
		element[currow][curcol].connected(vEquationPtr);
    }

    private void addNewBranchPoint() {
        branchPoints.addElement(new BranchPoint(element[currow][curcol],
            currow,curcol,dirold,direction,lastIIndex));
        branchPointPtr=(BranchPoint)branchPoints.lastElement();
        element[currow][curcol].bpIndex = branchPoints.size()-1;
        if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) branchPointPtr.print();
        bpStartVEquation = vEquations.size();
        lastIIndex++;
    }

    private void findNextDirection() {
            // look for the next untreated connection in a grid point, turning counter-clockwise
        dirold=direction;
        direction=(direction+3)%4;
        while (element[currow][curcol].connection[direction]==null)
             direction=(direction+1)%4;
    }

    private void changeCoords() { // step to the next point in the grid
        switch (direction) {
            case 0: curcol--;break; //to left
            case 1: currow++;break; //to under
            case 2: curcol++;break; //to right
            case 3: currow--; //to above
        }
    }

    //**************** matrix building and calculation methods *********************

    public void buildEquations() {
        // build equation matrix:
        // A.X = B.Y thus X = A(inv).B.Y
        // with A=LeftEquationMatrix and B=RightEquationMatrix
        // X[n] = {V[..][n],I[..][n]} en Y[n] = {X[n-1],Vi[n]}
        a = new Matrix(numberOfPars,numberOfPars);
        leftEquationMatrix(a);
        if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) {
            System.out.println("Matrix a:");a.print();}
        aInv = new Matrix(numberOfPars,numberOfPars);
        aInv.matinv(a);
        if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) {
            System.out.println("Matrix a Inverted:");aInv.print();}
        //last column of matrix b is preserved for the indices of input voltages
        b = new Matrix(numberOfPars,numberOfPars+sourceContainer.size()+1);
        rightEquationMatrix(b);
        if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) {
            System.out.println("Matrix b:");b.print();}
        multip = new Matrix(numberOfPars,numberOfPars+sourceContainer.size()+1);
        multip.matmul(aInv,b);
        if ((circuit.debugLevel&circuit.DEBUG_NUM)>0) {
            System.out.println("Matrix a Inverted . b:");multip.print();}
    }

    private void leftEquationMatrix (Matrix matrix) {
        matrix.set_elem(0, 0, 1.0);// V[0]=0!!!
        numberOfVE=0;// becomes the number of valuable V Equations
        for (int i=0;i<vEquations.size();i++) { // build v equations
            vEquationPtr=(VEquation)vEquations.elementAt(i);
            if (!vEquationPtr.z.name().equals("wire"))
                vEquationPtr.leftValue(numberOfV,matrix,++numberOfVE);
        }
        // insert the list of constraints such as equal Vs
        Constraints constraintPtr;
        int numberOfConstraints=constraints.size();
        for (int i=0;i<constraints.size();i++) {
            constraintPtr = (Constraints) constraints.elementAt(i);
            constraintPtr.leftValue(numberOfVE, matrix, i);
        }
        // build i equations
        int startint = numberOfVE+1+numberOfConstraints;
        if (!branchPoints.isEmpty())
            for (int i=startint;i<numberOfPars;i++) {
                branchPointPtr = (BranchPoint) branchPoints.elementAt(i-startint);
                for (int j=0;j<4;j++)
                    if (branchPointPtr.iSign[j] != 0)
                        matrix.set_elem(i,numberOfV+branchPointPtr.iIndex[j],branchPointPtr.iSign[j]);
            }
    }

    private void rightEquationMatrix (Matrix matrix) {
        //V[0]=0!!! first row is zero
        int j=0;
        CircuitElement cirelem;
        for (int i=0;i<vEquations.size();i++) { // build v equations
            vEquationPtr=(VEquation)vEquations.elementAt(i);
            if (!vEquationPtr.z.name().equals("wire"))
                vEquationPtr.rightValue(numberOfV,numberOfPars,matrix,++j);
        }
        //no right part for equal V's and branchpoints
    }

    public void calculateStep(double t) { // used for next step calculations
        y[numberOfPars]=1;
		for (int i=1;i<=sourceContainer.size();i++)
            y[numberOfPars+i] = ((Source)sourceContainer.elementAt(i-1)).getV(t);
        if (leftlinear == false) {leftEquationMatrix(a);  aInv.matinv(a);}
        if (rightlinear == false) rightEquationMatrix(b);
        if (leftlinear == false || rightlinear == false) multip.matmul(aInv,b);
        multip.resolve(y); //X=(A(inv).B).Y
    }


    //**************** element manipulation and searching methods *********************

    public int addCircuitElement(CircuitElement cirelem){
        int fromcon, fromr, fromc, tocon, tor, toc;

        if (cirelem.name().equals("probe")){
            cirElemList.addElement(cirelem);
		    return cirelem.hashCode();
        }
        fromr=tor=cirelem.row;  fromc=toc=cirelem.col;
        if (cirelem.to.equals("h")) {fromcon=2; tocon=0; toc++;}
        else {fromcon=1; tocon=3; tor++;}
        if ((tor<rows)&(fromr<rows)&(toc<cols)&(fromc<cols)&(tor>=0)&(toc>=0)&(fromr>=0)&(fromr>=0)) {
            if (element[tor][toc].connection[tocon] != null)
                cirElemList.removeElement(element[tor][toc].connection[tocon]);
            if (cirelem.name().equals("nothing") == false){
                element[tor][toc].connection[tocon]
                = element[fromr][fromc].connection[fromcon] = cirelem;
                cirElemList.addElement(cirelem);
            } else element[tor][toc].connection[tocon]
                = element[fromr][fromc].connection[fromcon] = null;
            if (cirelem.numberOfNodes == 4) {

            }


		    return cirelem.hashCode();
		} else return -1;
    }

    public boolean removeCircuitElement(int id){
        int fromcon, tocon, tor, toc, fromr, fromc;
        CircuitElement cirelem = getCircuitElement(id);
        if (cirelem==null) return false;
        fromr=tor=cirelem.row;  fromc=toc=cirelem.col;
        if (cirelem.to.equals("h")) {fromcon=2; tocon=0; toc++;}
        else {fromcon=1; tocon=3; tor++;}
        element[tor][toc].connection[tocon]
         = element[fromr][fromc].connection[fromcon] = null;
        cirElemList.removeElement(cirelem);
        if ((circuit.debugLevel&circuit.DEBUG_IO)>0) System.out.println("Element removed: "+cirelem.name());
        return true;
    }

    public boolean moveCircuitElement(int id, int newfromr, int newfromc, String to){
        int fromcon, tocon, tor, toc, fromr, fromc, newtor, newtoc, newfromcon, newtocon;
        CircuitElement cirelem = getCircuitElement(id); //find coords and remove connections
        if (cirelem==null) return false;
        fromr=tor=cirelem.row;  fromc=toc=cirelem.col;
        if (cirelem.to.equals("h")) {fromcon=2; tocon=0; toc++;}
        else {fromcon=1; tocon=3; tor++;}
        newtor=newfromr; newtoc=newfromc; // make new connections
        if (to.equals("h")) {newfromcon=2; newtocon=0; newtoc++;}
        else {newfromcon=1; newtocon=3; newtor++;}
        if ((newtor<rows)&(newfromr<rows)&(newtoc<cols)&(newfromc<cols)
             &(newtor>=0)&(newtoc>=0)&(newfromr>=0)&(newfromr>=0)) {
            element[tor][toc].connection[tocon]
            = element[fromr][fromc].connection[fromcon] = null;
            if (element[newfromr][newfromc].connection[newfromcon] != null)
                cirElemList.removeElement(element[newfromr][newfromc].connection[newfromcon]);
            cirelem.move(newfromr, newfromc, to);
            if ((circuit.debugLevel&circuit.DEBUG_IO)>0)
                System.out.println(cirelem.name()+" moved to: "+newfromr+" "+newfromc+" "+to);
            element[newtor][newtoc].connection[newtocon]
                = element[newfromr][newfromc].connection[newfromcon] = cirelem;
            return true;
        } else return false;
    }

    public CircuitElement getCircuitElement(int id) {
        CircuitElement cirelem=null;
        for (Enumeration e=cirElemList.elements();e.hasMoreElements();) {
            cirelem = (CircuitElement) e.nextElement();
            if (cirelem.hashCode()==id) return cirelem;
        }
        if(Circuit.DEBUG)System.out.println("getCircuitElement method: No element found");
        return null;
    }

    public CircuitElement getCircuitElement(int r, int c, String tostr) {
        CircuitElement cirelem=null;
        for (Enumeration e=cirElemList.elements();e.hasMoreElements();) {
            cirelem = (CircuitElement) e.nextElement();
            if (cirelem.row==r & cirelem.col==c & cirelem.to.equals(tostr)) return cirelem;
        }
        if(Circuit.DEBUG) System.out.println("getCircuitElement method: No element found");
        return null;
    }

    public String getcomponentList() {
        CircuitElement cirelem=null;
        String s = "setGrid(\"rows="+rows+",cols="+cols+"\");\n";
        s += "setNumberOfDT("+circuit.numberofdt+");\n";
        s += "setDT("+circuit.dt+");\n";
        s += "setNOC("+circuit.noc+");\n";
        s += "setFPS("+circuit.fps+");\n";
        for (Enumeration e=cirElemList.elements();e.hasMoreElements();) {
            cirelem = (CircuitElement) e.nextElement();
            s += cirelem.getAddObjectString();
        }
        return s;
    }
}
