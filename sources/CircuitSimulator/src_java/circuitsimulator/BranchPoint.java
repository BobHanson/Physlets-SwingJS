package circuitsimulator;

/**
 * Used by circuitGrid. Representation of grid-elements with more than 2 connections.
 * 
 * @author Toon Van Hoecke
 */
public class BranchPoint
{
    int xcoord, ycoord;
    int[] iIndex = new int[4];
    int[] iSign = new int[4];
    int lastDirection;
    int toDefine;
    
    public BranchPoint () {
        xcoord=0;
        ycoord=0;
        for (int i=0;i<4;i++) {
            iIndex[i]=0;
            iSign[i]=0;
        }
        lastDirection=0;
        toDefine=4;
    }
    
    BranchPoint (GridElement curel, int x, int y, 
                        int comeFromDir, int goToDir, int iInd)
    {
        xcoord=x; ycoord=y;
        iIndex[(comeFromDir+2)%4]=iInd;
        iSign[(comeFromDir+2)%4]=-1;
        iIndex[goToDir]=iInd+1;
        iSign[goToDir]=1;
        lastDirection=goToDir;
        toDefine=curel.numberOfCons()-2;
        
    }
    
    public int start (GridElement curel, int iInd) {
        //find last connection clockwise
        int direction;
        direction=(lastDirection+1)%4;
        while (curel.connection[direction]==null | iSign[direction]!=0)
                    direction=(direction+1)%4;
        //define element
        iIndex[direction]=iInd+1;
        iSign[direction]=1;
        lastDirection=direction;
        toDefine--;
        return direction;
    }
    
    public void stop (int direction, int iInd) {
        iIndex[(direction+2)%4]=iInd;
        iSign[(direction+2)%4]=-1;
        lastDirection=direction;
        toDefine--;
    }
    
    public void print () {
		System.out.println("BranchPoint: x/y/lastDir/todefine: "
		       +xcoord+"/"+ycoord+"/"+lastDirection+"/"+toDefine);
        for (int i=0;i<4;i++) {
            System.out.print(iIndex[i]+" "+iSign[i]+" ");
        }
        System.out.println(); 
    }
	//{{DECLARE_CONTROLS
	//}}
}
