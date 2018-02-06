package circuit;

import java.io.*;

public class Common {
  public static String CIRCUIT="Circuit";
  public static String VOLTAGE="Voltage";
  public static String VOLTAGE_V="Voltage (V)";
  public static String CURRENT="Current";
  public static String CURRENT_A="Current (A)";
  public static String CURRENT_RMS="RMS Current";
  public static String CURRENT_RMS_A="RMS Current (A)";
  public static String VOLTAGE_CURRENT="Voltage and Current";
  public static String TIME="Time";
  public static String TIME_SEC="Time (s)";
  public static String FREQUENCY="Frequency";
  public static String FREQUENCY_HZ="Frequency (Hz)";
  public static String PHASE="Phase";
  public static String DEG="deg";
  public static String DEGREE="degree";
  public static String EMF="emf";
  public static String OHM="Ohm";
  public static String BATTERY="Battery";
  public static String BATTERY_EMF="Battery emf=";
  public static String SWITCH_ON="Switch on.";
  public static String SWITCH_OFF="Switch off.";
  public static String LOAD_RESISTOR="Load Resistor";
  public static String LOAD_POWER="Load Power";
  public static String LEGEND_V="V";
  public static String LEGEND_VEQU="V=";
  public static String LEGEND_VTIME="V(t)";
  public static String LEGEND_DELTAV ="delta V=";
  public static String LEGEND_I="I";
  public static String LEGEND_A="A";
  public static String LEGEND_R="R";
  public static String LEGEND_C="C";
  public static String LEGEND_VAC="Vac";
  public static String LEGEND_VC="Vc";
  public static String LEGEND_VR="Vr";
  public static String LEGEND_VL="Vl";
  public static String LEGEND_SELECT="Select a component.";
  public static String LEGEND_NOVALUE="Values are not available.";
  public static String LEGEND_NOVOLTAGE="Voltage is not available.";
  public static String LEGEND_VOLTMETER="Voltmeter: ";
  public static String LEGEND_AMMETER="Ammeter: ";
  public static String LEGEND_CURRENTPHASE="Current Phase=";
  public static String LEGEND_FREQ="F=";

  public static Resources rc = null;
  public static boolean rc_set = false;

  public Common() {
    //Locale.getDefault().toString();
  }

  public static void initResources(InputStream is) {

        rc = new Resources();

        try {
            rc.load(is);
        } catch (Exception x) {}
    }

    public static void setResources() {
        CIRCUIT = rc.getString( "circuit", "Circuit" );
        VOLTAGE = rc.getString( "voltage", "Voltage" );
        VOLTAGE_V = rc.getString( "voltage.v", "Voltage (V)" );
        CURRENT = rc.getString( "current", "Current" );
        CURRENT_A = rc.getString( "current.a", "Current (A)" );
        CURRENT_RMS= rc.getString( "current.rms", "RMS Current" );
        CURRENT_RMS_A= rc.getString( "current.rms.a", "RMS Current (A)" );
        VOLTAGE_CURRENT = rc.getString( "voltage.current", "Voltage and Current" );
        TIME = rc.getString( "time", "Time" );
        TIME_SEC = rc.getString( "time.sec", "Time (s)" );
        FREQUENCY = rc.getString( "frequency", "Frequency" );
        FREQUENCY_HZ = rc.getString( "frequency.hz", "Frequency (Hz)" );
        PHASE = rc.getString( "phase", "Phase" );
        DEG = rc.getString( "deg", "deg" );
        DEGREE = rc.getString( "degree", "degree" );
        EMF = rc.getString( "emf", "emf" );
        OHM = rc.getString( "ohm", "Ohm" );
        BATTERY = rc.getString( "battery", "Battery" );
        BATTERY_EMF = rc.getString( "battery.emf", "Battery emf=" );
        SWITCH_ON=rc.getString( "switch.on", "Switch on.");
        SWITCH_OFF=rc.getString( "switch.off", "Switch off.");
        LOAD_RESISTOR = rc.getString( "load.resistor", "Load Resistor" );
        LOAD_POWER = rc.getString( "load.power", "Load Power" );
        LEGEND_V = rc.getString( "legend.v", "V" );
        LEGEND_VEQU = rc.getString( "legend.vequ", "V=" );
        LEGEND_VTIME = rc.getString( "legend.vtime", "V(t)" );
        LEGEND_DELTAV = rc.getString( "legend.deltav", "delta V=" );
        LEGEND_I = rc.getString( "legend.i", "I" );
        LEGEND_A = rc.getString( "legend.a", "A" );
        LEGEND_R = rc.getString( "legend.r", "R" );
        LEGEND_C = rc.getString( "legend.c", "C" );
        LEGEND_VAC = rc.getString( "legend.vac", "Vac" );
        LEGEND_VR = rc.getString( "legend.vr", "Vr" );
        LEGEND_VL = rc.getString( "legend.vl", "Vl" );
        LEGEND_VC = rc.getString( "legend.vc", "Vc" );
        LEGEND_SELECT=rc.getString( "legend.select", "Select a component." );
        LEGEND_NOVALUE=rc.getString( "legend.novalue", "Values are not available." );
        LEGEND_NOVOLTAGE=rc.getString("legend.novoltage", "Voltage is not available.");
        LEGEND_VOLTMETER=rc.getString("legend.voltmeter", "Voltmeter: ");
        LEGEND_AMMETER=rc.getString("legend.ammeter", "Ammeter: ");
        LEGEND_CURRENTPHASE=rc.getString("legend.currentphase", "Current Phase=");
        LEGEND_FREQ=rc.getString("legend.freq", "F=");
        rc_set = true;

    }

}