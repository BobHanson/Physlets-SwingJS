

package animator4;

public class Connector extends Thing {

    Thing thing1,thing2;

    public Connector(AnimatorCanvas o,Thing t1, Thing t2) {
        super(o,"0","0");
        thing1=t1;
        thing2=t2;
    }
} 