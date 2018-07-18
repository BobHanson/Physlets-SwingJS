////////////////////////////////////////////////////////////////////////////////
//      EnsemblePanel.java
//      Wolfgang Christian
package diatomic;

import java.awt.Color;

import a2s.*;
/**
 * The class <code>EnsemblePanel</code> models a mixture or monatomic and diatomic hard disks.
 * This model was developed by Ernesto Martin.  The code was adapted from the Easy Java Simulations model
 * by Wolfgang Christian
 *
 * @author  Wolfgang Chrsitian and Ernesto Martin
 * @version 1.1, 15 Nov 2000
 */
public class EnsemblePanel extends Panel {

    // added by Wolfgang Christian
    int    iwidth =0, iheight=0;     // the size of the current image
    int    radiusD=10;               //  radius of diatomic molecules
    double separationD=1.2;
    double d      =radiusD * separationD;    //  separation of diatomic
    double mD     =2.0;              //  mass of Diatomics
    double mM     =1.0;              //  mass of Monoatomics
    double IM     =mD * d * d;       // moment of inertia of diatomic
    //int mnMaxNew=12; // max number of molecules
    //int anMaxNew=12; // max number of atoms
    int mnMax  =0;                   // number of molecules
    int anMax  =0;                   // number of atoms
    int mn     =1;                   // the current molecule
    int an     =1;                   // the current atom
    int mnC    =1;                   // ??
    int anC    =1;                   // ??
    int mnC_Ncb=1;                   // ??
    int mnC_cb =1;                   // ??
    int mn_cb  =1;                   // ??
    int mwc;                         //  ?? counter
    int tcount;                      //  number of times through the calculation loop
    int tEnerAv;                     //  counter for energy calculations
    // int worldXYmax=400;  // the size of the world
    int    wc =0;                    // flag to determine which wall had the collision
    int    wc0=0;                    // flag to determine which wall had the collision
    int    wc1;                      //  collision flag for diatomic ball 1
    int    wc2;                      //  collision flag for diatomic  ball 2
    int    wcn;                      //  collision flag
    int    cb;                       //  collision flag
    int    cb0;                      //  collision flag
    int    cbn;                      //  collision flag
    int    MDcol=0;                  // flag to check for molecule-atom collision
    double KinEnDiat;                //  > Other_Physics_quantities:5
    double KinEnDiatTrans;           //  > Other_Physics_quantities:6
    double KinEnDiatRot;             //  > Other_Physics_quantities:7
    double keRot_keTrans;            //  > Other_Physics_quantities:8
    //double mmPenet;             //  > Other_Physics_quantities:9
    //double mdPenet;             //  > Other_Physics_quantities:10
    //double dtPenetM;            //  > Other_Physics_quantities:11
    double KinEnMonoat;              //  > Other_Physics_quantities:12
    double KinEnTotalMD;             //  > Other_Physics_quantities:13
    double momChange;                //  > Other_Physics_quantities:14
    double momChangeM;               //  > Other_Physics_quantities:15
    double momChangeD;               //  > Other_Physics_quantities:16
    double tempM;                    //  > Other_Physics_quantities:17
    double tempD;                    //  > Other_Physics_quantities:18
    double tMav;                     //  > Other_Physics_quantities:19
    double tDav;                     //  > Other_Physics_quantities:20
    double theTime;                  //  > Time_related:2
    double dt;                       //  > Time_related:6
    //double dt2[];                    //  > Time_related:4
    // double dtPenetD;            //  > Time_related:7
    //double ddPenetD;            //  > Time_related:7
    // double ddPenet;             //  > Counter_and_Check:16
    Color  molColor[];  //  > Monatomic:11
    double x[][];                    //  > Diatomic:1
    double y[][];                    //  > Diatomic:2
    double vx[][];                   //  > Diatomic:3
    double vy[][];                   //  > Diatomic:4
    double xcm[];                    //  > Diatomic:5
    double ycm[];                    //  > Diatomic:6
    double vxcm[];                   //  > Diatomic:7
    double vycm[];                   //  > Diatomic:8
    double teta[];                   //  > Diatomic:9
    double w[];                      //  > Diatomic:10
    double xcm0[];                   //  > Diatomic:11
    double ycm0[];                   //  > Diatomic:12
    double vxcm0[];                  //  > Diatomic:13
    double vycm0[];                  //  > Diatomic:14
    double w0[];                     //  > Diatomic:15
    double teta0[];                  //  > Diatomic:16
    double vDinit;                   // initial velocity scale
    double wDinit;                   // initial angular velocity
    double tetaDinit;                //  > Diatomic:21

    boolean atomFixed[];  //  > Monatomic:11
    Color atomColor[];  //  > Monatomic:11
    double ax[];                     //  > Monoatomic:1
    double ay[];                     //  > Monoatomic:2
    double avx[];                    //  > Monoatomic:3
    double avy[];                    //  > Monoatomic:4
    double ax0[];                    //  > Monoatomic:5
    double ay0[];                    //  > Monoatomic:6
    double avx0[];                   //  > Monoatomic:7
    double avy0[];                   //  > Monoatomic:8
    int    radiusM;                  //  > Monoatomic:9
    double vMinit;                   //  initial velocity scale
    double dotProduct;               //  > Counter_and_Check:25
    int    energyCalls;              //  > Counter_and_Check:26

    /**
     * The constructor.
     *
     * The owner is the SApplet that created the ensemble.
     * @param     SApplet o
     */
    public EnsemblePanel() {}

    /**
     * Initialize the position and velocity of the diatomic molecules
     */
    void InitSemiRandDValues() {

        double rd;
        double interx;    //x separation between atoms
        double intery;    //y separation between atoms
        int    nxy, remx, remy;
        theTime      =0;
        tcount       =0;
        tEnerAv      =0;
        keRot_keTrans=0;
        nxy          =(int) (Math.sqrt(mnMax * 1.0) + 1);
        mn           =1;
        //inter = (int) ( worldXYmax/(nxy + 1) );
        interx=iwidth / (nxy + 1.0);
        intery=iheight / (nxy + 1.0);
        remy  =0;
        while (mn < mnMax + 1) {
            remx=(int) ((mn - 1) % nxy);
            if (remx == 0) {
                remy=remy + 1;
            }
            xcm[mn] =(remx + 1) * interx;
            ycm[mn] =(remy) * intery;
            rd      =(Math.random() - 0.5) * 2.0;
            vxcm[mn]=rd * vDinit;
            rd      =(Math.random() - 0.5) * 2.0;
            vycm[mn]=rd * vDinit;
            rd      =(Math.random() - 0.5) * 2.0;
            w[mn]   =rd * wDinit;
            rd      =(Math.random() - 0.5) * 2.0;
            teta[mn]=rd * tetaDinit;
            BallDValues(mn);    // calculate the x-y position and velocity of the diatomic from the CM values
            mn=mn + 1;
        }
        mn         =1;
        energyCalls=0;    // added by Wolfgang Christian
        //XcWorld1.update();
    }

    /**
     * Initial the atomic positions and velocities
     */
    void InitSemiRandMValues() {

        double rd;
        double interx;    //x separation between atoms
        double intery;    //y separation between atoms
        int    nxy, remx, remy;
        theTime      =0;
        tcount       =0;
        tEnerAv      =0;
        keRot_keTrans=0;
        nxy          =(int) (Math.sqrt(anMax * 1.0) + 1);
        an           =1;
        ///inter = worldXYmax/(nxy + 1);
        interx=iwidth / (nxy + 1.0);
        intery=iheight / (nxy + 1.0);
        remy  =0;
        while (an < anMax + 1) {
            remx=(int) ((an - 1) % nxy);
            if (remx == 0) {
                remy=remy + 1;
            }
            ax[an] =(remx + 1) * interx + 10.0;
            ay[an] =(remy) * intery + 10.0;
            rd     =(Math.random() - 0.5) * 2.0;
            avx[an]=rd * vMinit;
            rd     =(Math.random() - 0.5) * 2.0;
            avy[an]=rd * vMinit;
            if (atomFixed[an]) {
			avx[an] = 0.0;
			avy[an] = 0.0;
            }
            an=an + 1;
        }
        an         =1;
        energyCalls=0;    // added by Wolfgang Christian
    }

    /**
     * Set the number of atoms.
     *
     * @param num
     */
    public void setAtomNum(int num) {
        if(num>99){
          num=99;
          System.out.println("Number of atoms cannot be greater than 99");
        }
        mn   =1;
        an   =1;
        anMax=num;
        InitSemiRandMValues();
        InitSemiRandDValues();
        mn=1;
        an=1;
        KinEnTotal();
    }

    public void setAtomRad(int r) {
        radiusM=Math.max(1,r);
        int anAux;
        anAux        =an;
        an           =1;
        theTime      =0;
        tcount       =0;
        tEnerAv      =0;
        keRot_keTrans=0;
        energyCalls  =0;
        an           =anAux;
        KinEnTotal();
    }
/**
 * Set the fixed property.
 *
 * An atom will not move if it is fixed.
 *
 * @param i int the atom to be fixed.
 * @param fix boolean true if fixed, false otherwise
 */
    void setAtomFixed(int i, boolean fix) {
        if(i>=atomFixed.length || atomFixed[i]==fix) return ;
        this.atomFixed[i]=fix;
        if (atomFixed[i]){
			avx[i] = 0.0;
			avy[i] = 0.0;
        }
        KinEnTotal();
    }


    /**
     * Set the number of molecules.
     *
     * @param num
     */
    void setMolNum(int num) {
        if(num>99){
          num=99;
          System.out.println("Number of diatomics cannot be greater than 99");
        }
        mn   =1;
        an   =1;
        mnMax=num;
        InitSemiRandMValues();
        InitSemiRandDValues();
        mn=1;
        an=1;
        KinEnTotal();
    }

    public void setMolRad(int r) {
        radiusD=Math.max(1,r);
        int mnAux=mn;
        mn           =1;
        d            =radiusD * separationD;
        IM           =mD * d * d;
        theTime      =0;
        tcount       =0;
        tEnerAv      =0;
        keRot_keTrans=0;
        energyCalls  =0;
        while (mn < mnMax + 1) {
            BallDValues(mn);
            mn=mn + 1;
        }
        mn=mnAux;
        KinEnTotal();
    }

    public void setMolSeparation(double sep) {
        int mnAux=mn;
        separationD=Math.max(sep,0.001);
        mn           =1;
        d            =radiusD * separationD;
        IM           =mD * d * d;
        theTime      =0;
        tcount       =0;
        tEnerAv      =0;
        keRot_keTrans=0;
        energyCalls  =0;
        while (mn < mnMax + 1) {
            BallDValues(mn);
            mn=mn + 1;
        }
        mn=mnAux;
        KinEnTotal();
    }

    /**
     * Initialize the variables
     */
    void initialize() {

        //dt2    =new double[3];         //  > Time_related:4
        theTime=0;                     //  > Time_related:5
        dt     =0.1;                 //  > Time_related:6
        x      =new double[220][3];    //  > Diatomic:1
        y      =new double[220][3];    //  > Diatomic:2
        vx     =new double[220][3];    //  > Diatomic:3
        vy     =new double[220][3];    //  > Diatomic:4
        xcm    =new double[110];       //  > Diatomic:5
        ycm    =new double[110];       //  > Diatomic:6
        vxcm   =new double[110];       //  > Diatomic:7
        vycm   =new double[110];       //  > Diatomic:8
        teta   =new double[110];       //  > Diatomic:9
        w      =new double[110];       //  > Diatomic:10
        xcm0   =new double[110];       //  > Diatomic:11
        ycm0   =new double[110];       //  > Diatomic:12
        vxcm0  =new double[110];       //  > Diatomic:13
        vycm0  =new double[110];       //  > Diatomic:14
        w0     =new double[110];       //  > Diatomic:15
        teta0  =new double[110];       //  > Diatomic:16
        molColor = new Color[110];  //  > Monatomic:11
        radiusD=10;                    //  radius of balls in diatomic molecule

        atomFixed = new boolean[100];  //  > Monatomic:11
        atomColor = new Color[100];  //  > Monatomic:11
        ax     =new double[100];       //  > Monoatomic:1
        ay     =new double[100];       //  > Monoatomic:2
        avx    =new double[100];       //  > Monoatomic:3
        avy    =new double[100];       //  > Monoatomic:4
        ax0    =new double[100];       //  > Monoatomic:5
        ay0    =new double[100];       //  > Monoatomic:6
        avx0   =new double[100];       //  > Monoatomic:7
        avy0   =new double[100];       //  > Monoatomic:8
        radiusM=10;                    //  radius of balls in atom
        //mass = new double[220][3];              //  mass
        mD     =2.0;                   //mass of Diatomics
        mM     =1.0;                   //mass of Monoatomics
        d      =radiusD * separationD;
        IM     =mD * d * d;            //  set the moment of inertia
        tcount =0;                     //  > Counter_and_Check:4
        tEnerAv=0;                     //  > Counter_and_Check:5
        wc1    =0;                     //  > Counter_and_Check:6
        wc     =0;                     //  > Counter_and_Check:10
        wcn    =0;                     //  > Counter_and_Check:12
        MDcol  =1;                     //  > Counter_and_Check:22
        setDefault();
    }

    /**
     * Set default conditions.
     *
     * This method was called resetTime in EJS.
     */
    public void setDefault() {
    theTime = 0.0;  //  > resetTime:1
    tcount=0;  //  > resetTime:2
    energyCalls=0;  //  > resetTime:3
    //int i,j,k;  //  > resetTime:5
    mn=1;  //  > resetTime:8
    an=1;  //  > resetTime:9
    dt=0.1;  //  > resetTime:10
    mD=2.0;  //mass of Diatomics  //  > resetTime:11
    mM=1.0; // mass of Monoatomics  //  > resetTime:12
    radiusD=8;  //  > resetTime:13
    radiusM=8;  //  > resetTime:14

    double scale=5;   // scale added by Wolfgang Christian to produce realistic x and y values.
    vMinit=250.0/scale;  //  > resetTime:15
    vDinit=250.0/scale;  //  > resetTime:16
    wDinit=50.0/scale;  //  > resetTime:17

    tetaDinit=7.0;  //  > resetTime:18
    d=radiusD*separationD;  //  > resetTime:19
    IM= mD*d*d;  //  > resetTime:20
    for (int ii=0; ii<atomFixed.length; ii++) atomFixed[ii] = false;
    for (int ii=0; ii<atomColor.length; ii++) atomColor[ii] = Color.red;
    for (int ii=0; ii<molColor.length; ii++)  molColor[ii] = Color.green;
  }
    /**
     * Increment the time.  Loop over all atoms and molecules to update position and velocity.
     */
    public void monodiLoop() {      // new code

        //int countEnerPrint, timePrint, tAverage;    //  > monodiLoop:1
        tcount        =tcount + 1;         //  > monodiLoop:2
        theTime       =theTime + dt;       //  > monodiLoop:3
       // countEnerPrint=tcount % 30;        //  > monodiLoop:4
        //timePrint     =tcount % 10;        //  > monodiLoop:5
       // tAverage      =tcount % 10 + 1;    //  > monodiLoop:6

        /*
         * if (countEnerPrint == 29) {             //  > monodiLoop:7
         *   KinEnDPrint(tcount);      //  > monodiLoop:8
         * }       //  > monodiLoop:9
         *
         * if (timePrint == 0) {                   //  > monodiLoop:10
         * // tPrint();  //  > monodiLoop:11
         * }       //  > monodiLoop:12
         */
        for (mn=1; mn < mnMax + 1; mn++) {    //loop for diatomics; iterates over mn  //  > monodiLoop:13
            teta0[mn]=teta[mn];
            xcm0[mn] =xcm[mn];
            ycm0[mn] =ycm[mn];                     //  > monodiLoop:14
            vxcm0[mn]=vxcm[mn];
            vycm0[mn]=vycm[mn];
            w0[mn]   =w[mn];                       //  > monodiLoop:15
            teta[mn] =teta0[mn] + w[mn] * dt;      //  > monodiLoop:16
            xcm[mn]  =xcm0[mn] + vxcm[mn] * dt;    //  > monodiLoop:17
            ycm[mn]  =ycm0[mn] + vycm[mn] * dt;    //  > monodiLoop:18
            BallDValues(mn);                         //  > monodiLoop:19
            dwContact();                           //check for wall collision  //  > monodiLoop:20
            mnC=0;                                 // molecule contacted by mn  //  > monodiLoop:21
            ddContact();    //check for diatom-diatom collision   //  > monodiLoop:22
            // a call to the Collision algorithm BallBounceDD()is included //  //  > monodiLoop:23
            anC=0;                                 // atom contacted by mn  //  > monodiLoop:24
            // MDcol is a flag to enable or disable collision between atoms and molecules  //  > monodiLoop:25
            if (MDcol == 1) {
                dmContact();
            }                                      //check for diatom-monoatom collision  //  > monodiLoop:26
            if (anC > 0) {                         //  > monodiLoop:27
                BallBounceDM();    //Collision Algorithm:computes new velocities for Mono and DI  //  > monodiLoop:28
            }                                      //  > monodiLoop:29
            //DrawDiat();         //Draw Diatomic molecule mn  //  > monodiLoop:30
        }                                          // end of mn Loop  //  > monodiLoop:31
        //  > monodiLoop:32
        // **********  //  > monodiLoop:33
        for (an=1; an < anMax + 1; an++) {    //loop for monotomics; iterates over "an"  //  > monodiLoop:34
            ax0[an] =ax[an];
            ay0[an] =ay[an];                   //  > monodiLoop:35
            avx0[an]=avx[an];
            avy0[an]=avy[an];                  //  > monodiLoop:36
            ax[an]  =ax[an] + avx[an] * dt;    //  > monodiLoop:37
            ay[an]  =ay[an] + avy[an] * dt;    //  > monodiLoop:38
            mwContact();    //check for mono-wall collision; collision alg. included   //  > monodiLoop:39
            anC=0;                             //  > monodiLoop:40
            mmContact();    //check for mono-mono contact; returns anC (atom contacted)  //  > monodiLoop:41
            if (anC != 0) {                    //  > monodiLoop:42
                BallBounceMM();    // computes new atoms (an, anC) position and velocity taking care of penetration  //  > monodiLoop:4
            }                                  //  > monodiLoop:45
            mnC=0;                             //  > monodiLoop:46
        }                                      // end of "an" Loop  //  > monodiLoop:48
        if (tcount == 1) {  // make sure the energy is up to date
            KinEnTotal();
        }
    }

    public void BallBounceDD() {    // new code

        double vdMod, vdx, vdy;
        double dVp1, dVp2, dw1, dw2, bvp1, bvp2;
        double y1, y2;
        int    mn_Ncb, mnC_Ncb;
        if (mn_cb == 1) {
            mn_Ncb=2;
        } else {
            mn_Ncb=1;
        }
        if (mnC_cb == 1) {
            mnC_Ncb=2;
        } else {
            mnC_Ncb=1;
        }
        vdx  =x[mnC][mnC_cb] - x[mn][mn_cb];
        vdy  =y[mnC][mnC_cb] - y[mn][mn_cb];
        vdMod=Math.sqrt(vdx * vdx + vdy * vdy);
        bvp1 =(vx[mn][mn_cb] * vdx + vy[mn][mn_cb] * vdy) / vdMod;
        bvp2 =(vx[mnC][mnC_cb] * vdx + vy[mnC][mnC_cb] * vdy) / vdMod;
        //dtPenetD = ddPenet/Math.abs(bvp1 - bvp2);
        y1        =(vdx * (y[mn][mn_Ncb] - y[mn][mn_cb]) - vdy * (x[mn][mn_Ncb] - x[mn][mn_cb])) / (2.0 * vdMod);
        y2        =(vdx * (y[mnC][mnC_Ncb] - y[mnC][mnC_cb]) - vdy * (x[mnC][mnC_Ncb] - x[mnC][mnC_cb])) / (2.0 * vdMod);
        dVp1      =2.0 * (bvp2 - bvp1) / (1.0 + (y1 / d) * (y1 / d) + (mD / mD) * (1.0 + (y2 / d) * (y2 / d)));
        dVp2      =2.0 * (bvp1 - bvp2) / (1.0 + (y2 / d) * (y2 / d) + (mD / mD) * (1.0 + (y1 / d) * (y1 / d)));
        dw1       =(y1 / (d * d)) * dVp1;
        dw2       =(y2 / (d * d)) * dVp2;
        vxcm0[mn] =vxcm[mn];
        vycm0[mn] =vycm[mn];
        vxcm0[mnC]=vxcm[mnC];
        vycm0[mnC]=vycm[mnC];
        w0[mn]    =w[mn];
        w0[mnC]   =w[mnC];
        vxcm[mn]  =vxcm0[mn] + dVp1 * vdx / vdMod;
        vycm[mn]  =vycm0[mn] + dVp1 * vdy / vdMod;
        vxcm[mnC] =vxcm0[mnC] + dVp2 * vdx / vdMod;
        vycm[mnC] =vycm0[mnC] + dVp2 * vdy / vdMod;
        w[mn]     =w0[mn] + dw1;
        w[mnC]    =w0[mnC] + dw2;
        double KEDchangeTrans=0.0, KEDchangeRot=0.0;
        BallDVelValues(mn);
        BallDVelValues(mnC);
        KEDchangeTrans=0.5 * mD * (vxcm[mn] * vxcm[mn] - vxcm0[mn] * vxcm0[mn]);
        KEDchangeTrans+=0.5 * mD * (vycm[mn] * vycm[mn] - vycm0[mn] * vycm0[mn]);
        KEDchangeTrans+=0.5 * mD * (vxcm[mnC] * vxcm[mnC] - vxcm0[mnC] * vxcm0[mnC]);
        KEDchangeTrans+=0.5 * mD * (vycm[mnC] * vycm[mnC] - vycm0[mnC] * vycm0[mnC]);
        KEDchangeRot  =0.5 * IM * (w[mn] * w[mn] - w0[mn] * w0[mn]);
        KEDchangeRot  +=0.5 * IM * (w[mnC] * w[mnC] - w0[mnC] * w0[mnC]);
        KinEnDiatTrans+=KEDchangeTrans;
        KinEnDiatRot  +=KEDchangeRot;
        KinEnDiat     =KinEnDiatTrans + KinEnDiatRot;
        KinEnTotalMD  =KinEnMonoat + KinEnDiat;
    }

    public void BallBounceDM() {

        double vdMod, vdx, vdy;
        double dVp1, dVp2, dw1, bvp1, bvp2;
        double y1, y2;
        int    mn_Ncb;
        if (mn_cb == 1) {
            mn_Ncb=2;
        } else {
            mn_Ncb=1;
        }
        vdx  =x[mn][mn_cb] - ax[anC];
        vdy  =y[mn][mn_cb] - ay[anC];
        vdMod=Math.sqrt(vdx * vdx + vdy * vdy);
        bvp1 =(vx[mn][mn_cb] * vdx + vy[mn][mn_cb] * vdy) / vdMod;
        bvp2 =(avx[anC] * vdx + avy[anC] * vdy) / vdMod;
        //dtPenetD = mdPenet/Math.abs(bvp1 - bvp2);
        y1       =(vdx * (y[mn][mn_Ncb] - y[mn][mn_cb]) - vdy * (x[mn][mn_Ncb] - x[mn][mn_cb])) / (2.0 * vdMod);
        y2       =0.0;
	if (atomFixed[anC]) {	//$ mass of ball 2, mM ->infinite
	  bvp2=0.0;
	  dVp1= 2.0*(bvp2 - bvp1)/(1.0+(y1/d)*(y1/d));
	  dVp2= 0.0;
	  }
	else {
	  dVp1= 2.0*(bvp2 - bvp1)/(1.0+(y1/d)*(y1/d)+(mD/mM)*(1.0+(y2/d)*(y2/d)));
	  dVp2= 2.0*(bvp1 - bvp2)/(1.0+(y2/d)*(y2/d)+(mM/mD)*(1.0+(y1/d)*(y1/d)));
	}

        dw1      =(y1 / (d * d)) * dVp1;
        avx0[anC]=avx[anC];
        avy0[anC]=avy[anC];
        vxcm0[mn]=vxcm[mn];
        vycm0[mn]=vycm[mn];
        w0[mn]   =w[mn];
        avx[anC] =avx0[anC] + dVp2 * vdx / vdMod;
        avy[anC] =avy0[anC] + dVp2 * vdy / vdMod;
        vxcm[mn] =vxcm0[mn] + dVp1 * vdx / vdMod;
        vycm[mn] =vycm0[mn] + dVp1 * vdy / vdMod;
        w[mn]    =w0[mn] + dw1;
        double KEDchangeTrans=0.0, KEDchangeRot=0.0, KEMchange=0.0;
        BallDVelValues(mn);
        KEDchangeTrans=0.5 * mD * (vxcm[mn] * vxcm[mn] - vxcm0[mn] * vxcm0[mn]);
        KEDchangeTrans+=0.5 * mD * (vycm[mn] * vycm[mn] - vycm0[mn] * vycm0[mn]);
        KEDchangeRot  =0.5 * IM * (w[mn] * w[mn] - w0[mn] * w0[mn]);
        KinEnDiatTrans+=KEDchangeTrans;
        KinEnDiatRot  +=KEDchangeRot;
        KinEnDiat     =KinEnDiatTrans + KinEnDiatRot;
        KEMchange     =0.5 * mM * (avx[anC] * avx[anC] - avx0[anC] * avx0[anC]);
        KEMchange     +=0.5 * mM * (avy[anC] * avy[anC] - avy0[anC] * avy0[anC]);
        KinEnMonoat   +=KEMchange;
        KinEnTotalMD  =KinEnMonoat + KinEnDiat;
    }

    public void BallBounceMM() {

        double vdMod, vdx, vdy;
        double bvp1, bvp2, dbvp1, dbvp2;
        vdx  =ax[anC] - ax[an];
        vdy  =ay[anC] - ay[an];
        vdMod=Math.sqrt(vdx * vdx + vdy * vdy);
        bvp1 =(avx[an] * vdx + avy[an] * vdy) / vdMod;
        bvp2 =(avx[anC] * vdx + avy[anC] * vdy) / vdMod;
	if		(atomFixed[anC]) {		//$ mass of ball 2 ->infinite
	  bvp2=0.0;
	  dbvp1 = 2.0*(bvp2 - bvp1);
	  dbvp2  = 0.0;
	  }
	else if (atomFixed[an])	 {		//$ mass of ball 1 ->infinite
	  bvp1=0.0;
	  dbvp2 = 2.0*(bvp1 - bvp2);
	  dbvp1  = 0.0;
	  }
	else {
	  dbvp1 = 2.0*(bvp2 - bvp1)/(1+mM/mM);
	  dbvp2 = 2.0*(bvp1 - bvp2)/(1+mM/mM);
	}
        avx0[an] =avx[an];
        avy0[an] =avy[an];
        avx0[anC]=avx[anC];
        avy0[anC]=avy[anC];
        avx[an]  =avx0[an] + dbvp1 * vdx / vdMod;
        avy[an]  =avy0[an] + dbvp1 * vdy / vdMod;
        avx[anC] =avx0[anC] + dbvp2 * vdx / vdMod;
        avy[anC] =avy0[anC] + dbvp2 * vdy / vdMod;
        double KEMchange=0.0;
        KEMchange   =0.5 * mM * (avx[an] * avx[an] - avx0[an] * avx0[an]);
        KEMchange   +=0.5 * mM * (avy[an] * avy[an] - avy0[an] * avy0[an]);
        KEMchange   +=0.5 * mM * (avx[anC] * avx[anC] - avx0[anC] * avx0[anC]);
        KEMchange   +=0.5 * mM * (avy[anC] * avy[anC] - avy0[anC] * avy0[anC]);
        KinEnMonoat +=KEMchange;
        KinEnTotalMD=KinEnMonoat + KinEnDiat;
    }

    /**
     * Check for molecule-molecule collision
     */
    public void ddContact() {

        int    i, j, mnAuxiliar=mn + 1;    // to avoid duplications
        double dist2;
        mnC=0;
        for (mnAuxiliar=mn + 1; mnAuxiliar < mnMax + 1; mnAuxiliar++) {
            for (i=1; i < 3; i++) {
                for (j=1; j < 3; j++) {
                    // code added by Wolfgang Christian
                    // Check to see if the balls are moving together or away.  Leave the balls alone if they
                    // are moving away since the collision has already occured.
                    dotProduct=(x[mnAuxiliar][j] - x[mn][i]) * (vx[mnAuxiliar][j] - vx[mn][i]) + (y[mnAuxiliar][j] - y[mn][i]) * (vy[mnAuxiliar][j] - vy[mn][i]);
                    // end of code added by Wolfgang Christian
                    dist2=(x[mn][i] - x[mnAuxiliar][j]) * (x[mn][i] - x[mnAuxiliar][j]) + (y[mn][i] - y[mnAuxiliar][j]) * (y[mn][i] - y[mnAuxiliar][j]);
                    if ((dist2 < (4 * radiusD * radiusD)) && (dotProduct < 0.0)) {
                        mnC   =mnAuxiliar;
                        mn_cb =i;
                        mnC_cb=j;
                        BallBounceDD();
                    }            // end if
                }                // end i loop
            }                    // end j loop
        }                        // end of mnAuxiliar Loop
    }

    /**
     * Check for atom-atom collision
     * Set  anC  if there is a collision with the an-th atom.
     */
    public void mmContact() {

        int    anAux=an + 1;    //to avoid duplication
        double dist2;
        anC=0;
        for (anAux=an + 1; anAux < anMax + 1; anAux++) {
            // code added by Wolfgang Christian
            // Check to see if the balls are moving together or away.  Leave the balls alone if they
            // are moving away since the collision has already occured.
            dotProduct=(ax[anAux] - ax[an]) * (avx[anAux] - avx[an]) + (ay[anAux] - ay[an]) * (avy[anAux] - avy[an]);
            // end of code added by Wolfgang Christian
            dist2=(ax[an] - ax[anAux]) * (ax[an] - ax[anAux]) + (ay[an] - ay[anAux]) * (ay[an] - ay[anAux]);
            if ( (!atomFixed[anAux]||!atomFixed[an]) && (dist2 < (4 * radiusM * radiusM)) && ((dotProduct < 0.0))) {
                anC=anAux;
                return;
            }
        }                        // end of an loop
    }

    /**
     * Check for monatomic-wall collision
     */
    public void mwContact() {    // new code

        mwc=0;
        if ((ax[an] + radiusM > iwidth) && (avx[an] > 0.0)) {
            avx[an]=-avx[an];
        }
        if ((ay[an] + radiusM > iheight) && (avy[an] > 0.0)) {
            avy[an]=-avy[an];
        }
        if ((ax[an] - radiusM < 0) && (avx[an] < 0.0)) {
            avx[an]=-avx[an];
        }
        if ((ay[an] - radiusM < 0) && (avy[an] < 0.0)) {
            avy[an]=-avy[an];
        }
        double KEMchange=0.0;
        KEMchange   =0.5 * mM * (avx[an] * avx[an] - avx0[an] * avx0[an]);
        KEMchange   +=0.5 * mM * (avy[an] * avy[an] - avy0[an] * avy0[an]);
        KinEnMonoat +=KEMchange;
        KinEnTotalMD=KinEnMonoat + KinEnDiat;
    }

    /**
     * Do molecule-atom collision
     */
    public void dmContact() {

        int    j, anAuxiliar=1;
        double dist2, sumaRad;
        sumaRad=(radiusD + radiusM);
        anC    =0;
        for (anAuxiliar=1; anAuxiliar < anMax + 1; anAuxiliar++) {
            j=1;
            for (j=1; j < 3; j++) {
                // code added by Wolfgang Christian
                // Check to see if the balls are moving together or away.  Leave the balls alone if they
                // are moving away since the collision has already occured.
                dotProduct=(ax[anAuxiliar] - x[mn][j]) * (avx[anAuxiliar] - vx[mn][j]) + (ay[anAuxiliar] - y[mn][j]) * (avy[anAuxiliar] - vy[mn][j]);
                // end of code added by Wolfgang Christian
                dist2=(ax[anAuxiliar] - x[mn][j]) * (ax[anAuxiliar] - x[mn][j]) + (ay[anAuxiliar] - y[mn][j]) * (ay[anAuxiliar] - y[mn][j]);
                if ((dist2 < sumaRad * sumaRad) && (dotProduct < 0.0)) {
                    anC  =anAuxiliar;
                    mn_cb=j;
                    //mdPenet = sumaRad - Math.sqrt(dist2);
                    return;
                }
            }
        }                        // end of main loop
    }

    /**
     * Do molecule-wall collision
     */
    public void dwContact() {    // new code

        wc1=0;
        wc2=0;
        cb =0;
        cb0=0;
        wcn=0;
        if ((x[mn][1] + radiusD > iwidth) && (vx[mn][1] > 0.0)) {
            wc=1;
            cb=1;
            WallBounceD();
        }
        if ((y[mn][1] + radiusD > iheight) && (vy[mn][1] > 0.0)) {
            wc=2;
            cb=1;
            WallBounceD();
        }
        if ((x[mn][1] - radiusD < 0) && (vx[mn][1] < 0.0)) {
            wc=3;
            cb=1;
            WallBounceD();
        }
        if ((y[mn][1] - radiusD < 0) && (vy[mn][1] < 0.0)) {
            wc=4;
            cb=1;
            WallBounceD();
        }
        if ((x[mn][2] + radiusD > iwidth) && (vx[mn][2] > 0.0)) {
            wc=1;
            cb=2;
            WallBounceD();
        }
        if ((y[mn][2] + radiusD > iheight) && (vy[mn][2] > 0.0)) {
            wc=2;
            cb=2;
            WallBounceD();
        }
        if ((x[mn][2] - radiusD < 0) && (vx[mn][2] < 0.0)) {
            wc=3;
            cb=2;
            WallBounceD();
        }
        if ((y[mn][2] - radiusD < 0) && (vy[mn][2] < 0.0)) {
            wc=4;
            cb=2;
            WallBounceD();
        }
    }

    /**
     * Calculate the actual positions of the balls for the molecules given the CM position.
     *
     * @param int m the index of the ball
     */
    public void BallDValues(int m) {    // new code

        double dx, dy;
        dx       =d * Math.cos(teta[m]);
        dy       =d * Math.sin(teta[m]);
        x[m][1] =xcm[m] - dx;
        y[m][1] =ycm[m] - dy;
        x[m][2] =xcm[m] + dx;
        y[m][2] =ycm[m] + dy;
        vx[m][1]=vxcm[m] + w[m] * d * Math.sin(teta[m]);
        vx[m][2]=vxcm[m] - w[m] * d * Math.sin(teta[m]);
        vy[m][1]=vycm[m] - w[m] * d * Math.cos(teta[m]);
        vy[m][2]=vycm[m] + w[m] * d * Math.cos(teta[m]);
        //if (tcount == 0) {DrawDiat(); }
    }

    /**
     * Calculate the average velocities of the balls for the molecules given the CM position.
     */
    public void BallDVelValues(int m) {

        vx[m][1]=vxcm[m] + w[m] * d * Math.sin(teta[m]);
        vx[m][2]=vxcm[m] - w[m] * d * Math.sin(teta[m]);
        vy[m][1]=vycm[m] - w[m] * d * Math.cos(teta[m]);
        vy[m][2]=vycm[m] + w[m] * d * Math.cos(teta[m]);
    }

    /**
     * Check to see if diatomic is in contact with the wall.
     */
    public void WallBounceD() {

        double vdMod, vdx=0, vdy=0;// dAW;
        double Vp1, dVp1, w1, dw1;
        double y1;
        int    mn_Ncb;
        //int    n1, n2;
        mn_cb =cb;
        mn_Ncb=3 - cb;
        if (wc == 1) {
            vdx=1;
            vdy=0;
        }
        if (wc == 2) {
            vdx=0;
            vdy=1;
        }
        if (wc == 3) {
            vdx=-1;
            vdy=0;
        }
        if (wc == 4) {
            vdx=0;
            vdy=-1;
        }
        w1       =w[mn];
        vdMod    =Math.sqrt(vdx * vdx + vdy * vdy);
        Vp1      =(vxcm[mn] * vdx + vycm[mn] * vdy) / vdMod;
        y1       =(vdx * (y[mn][mn_Ncb] - y[mn][mn_cb]) - vdy * (x[mn][mn_Ncb] - x[mn][mn_cb])) / (2.0 * vdMod);
        dVp1     =-2.0 * (Vp1 + y1 * w1) / (1.0 + (y1 / d) * (y1 / d));
        dw1      =(y1 / (d * d)) * dVp1;
        vxcm0[mn]=vxcm[mn];
        vycm0[mn]=vycm[mn];
        w0[mn]   =w[mn];
        w0[mnC]  =w[mnC];
        vxcm[mn] =vxcm0[mn] + dVp1 * vdx / vdMod;
        vycm[mn] =vycm0[mn] + dVp1 * vdy / vdMod;
        w[mn]    =w0[mn] + dw1;
        wc=0;
        BallDVelValues(mn);
        double KEDchangeTrans=0.0, KEDchangeRot=0.0;
        KEDchangeTrans=0.5 * mD * (vxcm[mn] * vxcm[mn] - vxcm0[mn] * vxcm0[mn]);
        KEDchangeTrans+=0.5 * mD * (vycm[mn] * vycm[mn] - vycm0[mn] * vycm0[mn]);
        KEDchangeRot  =0.5 * IM * (w[mn] * w[mn] - w0[mn] * w0[mn]);
        KinEnDiatTrans+=KEDchangeTrans;
        KinEnDiatRot  +=KEDchangeRot;
        KinEnDiat     =KinEnDiatTrans + KinEnDiatRot;
        KinEnTotalMD  =KinEnMonoat + KinEnDiat;
    }

    /**
     * Bound the "an" atom off of the wall
     */
    public void WallBounceM() {
        if (mwc == 1) {
            avx[an]=-avx[an];
        }
        if (mwc == 3) {
            avx[an]=-avx[an];
        }
        if (mwc == 2) {
            avy[an]=-avy[an];
        }
        if (mwc == 4) {
            avy[an]=-avy[an];
        }
    }


    /**
     * Calculate the kinetic energies of the atoms and molecules.
     */
    public void KinEnTotal() {
       // double KinEnTotalMD0, ratio;
        energyCalls++;
        //KinEnTotalMD0 =KinEnTotalMD;
        KinEnDiatTrans=0.0;
        KinEnDiatRot  =0.0;
        KinEnDiat     =0.0;
        KinEnMonoat   =0;
        KinEnD();
        KinEnM();
        KinEnTotalMD=KinEnMonoat + KinEnDiat;
              /*
                if (energyCalls > 2) {
                    if (Double.isNaN(KinEnMonoat) || Double.isNaN(KinEnDiat) || Double.isNaN(KinEnTotalMD)) {
                       System.out.println("NaN KinEnMonoat= " + KinEnMonoat + " KinEnDiat= "
                                + KinEnDiat + " KinEnTotalMD= " + KinEnTotalMD);
                   }
                   if (Double.isInfinite(KinEnMonoat) || Double.isInfinite(KinEnDiat)
                           || Double.isInfinite(KinEnTotalMD)) {
                       System.out.println("Infinite KinEnMonoat= " + KinEnMonoat +
                               " KinEnDiat= " + KinEnDiat + " KinEnTotalMD= " + KinEnTotalMD);
                   }
                   ratio = KinEnTotalMD/KinEnTotalMD0;
                   if (Double.isNaN(ratio) || Double.isInfinite(ratio)) {
                       System.out.println(" ratio= " + ratio);
                   }
                   if ((ratio > 1.01 || ratio < 0.99)) {
                       System.out.println(" Old KE= " + KinEnTotalMD0 + " New KE= "
                               + KinEnTotalMD);
                   }
               }*/

              // System.out.println("E="+KinEnTotalMD);

    }

    /**
     * Calculate the kinetic energy of the atoms.
     */
    public void KinEnM() {    // new code

        KinEnMonoat=0.0;
        for (int anAux=1; anAux < anMax + 1; anAux++) {
            KinEnMonoat=KinEnMonoat + 0.5 * mM * (avx[anAux] * avx[anAux] + avy[anAux] * avy[anAux]);
        }
        // if (anMax!=0) { tempM=KinEnMonoat/anMax;} else { tempM=0.0;} // Temperature as average KE
    }

    /**
     * Calculate the kinetic enery of the molecules
     */
    public void KinEnD() {    // new code

        int mnAux=1;
        KinEnDiatTrans=0.0;
        KinEnDiatRot  =0.0;
        while (mnAux < mnMax + 1) {
            vx[mnAux][1]  =vxcm[mnAux] + w[mnAux] * d * Math.sin(teta[mnAux]);
            vx[mnAux][2]  =vxcm[mnAux] - w[mnAux] * d * Math.sin(teta[mnAux]);
            vy[mnAux][1]  =vycm[mnAux] - w[mnAux] * d * Math.cos(teta[mnAux]);
            vy[mnAux][2]  =vycm[mnAux] + w[mnAux] * d * Math.cos(teta[mnAux]);
            KinEnDiatTrans=KinEnDiatTrans + 0.5 * (mD) * (vxcm[mnAux] * vxcm[mnAux] + vycm[mnAux] * vycm[mnAux]);    //? mD or 2*mD
            KinEnDiatRot  =KinEnDiatRot + 0.5 * IM * w[mnAux] * w[mnAux];
            mnAux         =mnAux + 1;
        }
        KinEnDiat    =KinEnDiatTrans + KinEnDiatRot;
        keRot_keTrans=keRot_keTrans + KinEnDiatRot / KinEnDiatTrans;
        tEnerAv      =tEnerAv + 1;
        if (mnMax != 0) {
            tempD=KinEnDiatTrans / mnMax;
        } else {
            tempD=0.0;
        }
    }
}


/*--- Formatted in Physlet Style Style on Wed, Nov 15, '00 ---*/
