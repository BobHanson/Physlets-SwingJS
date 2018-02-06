package impedance;

abstract class BasisNetwerk implements Netwerk {
        protected BasisNetwerk(Netwerk n1, Netwerk n2) {
                netw1=n1;
                netw2=n2;
        }
        protected Netwerk netw1;
        protected Netwerk netw2;
}
