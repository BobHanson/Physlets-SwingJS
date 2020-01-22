package jacob;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;
import java.util.Vector;

public final class Data {
	public static void saveFile(Vector paramVector1, Vector paramVector2, OutputStream paramOutputStream)
			throws IOException {
		PrintStream localPrintStream = new PrintStream(paramOutputStream);
		localPrintStream.println("ppd " + Common.getPPDWidth() + " " + Common.getPPDHeight() + " " + PPD.electric_const
				+ " " + "0" + " " + PPD.suscept);
		Object localObject;
		int i;
		for (i = 0; i < paramVector1.size(); i++) {
			localObject = (Particle) paramVector1.elementAt(i);
			if (((Particle) localObject).parent == PPD.nullElement) {
				switch (((Particle) localObject).type) {
				case 3:
					localPrintStream.println("pp " + ((Particle) localObject).x + " " + ((Particle) localObject).y + " "
							+ ((Particle) localObject).k);
					break;
				case 4:
					localPrintStream.println("pn " + ((Particle) localObject).x + " " + ((Particle) localObject).y + " "
							+ ((Particle) localObject).k);
				}
			}
		}
		for (i = 0; i < paramVector2.size(); i++) {
			localObject = (Element) paramVector2.elementAt(i);
			int k = -1;
			if (((Element) localObject).connection != null) {
				k = PPD.elementBox.indexOf(((Element) localObject).connection);
			}
			switch (((Element) localObject).type) {
			case 0:
				ECircle localECircle = (ECircle) localObject;
				localPrintStream.println("ec " + localECircle.x + " " + localECircle.y + " " + localECircle.r + " "
						+ localECircle.Ex + " " + localECircle.Ey + " " + localECircle.movable + " "
						+ localECircle.force_circ + " " + localECircle.etransient + " " + localECircle.force_rotate
						+ " " + localECircle.force_translate + " " + k);
				break;
			case 1:
				ERectangle localERectangle = (ERectangle) localObject;
				localPrintStream.println("er " + localERectangle.x + " " + localERectangle.y + " "
						+ localERectangle.width + " " + localERectangle.height + " " + localERectangle.Ex + " "
						+ localERectangle.Ey + " " + localERectangle.movable + " " + localERectangle.force_circ + " "
						+ localERectangle.etransient + " " + localERectangle.force_rotate + " "
						+ localERectangle.force_translate + " " + k);
				break;
			case 2:
				ERing localERing = (ERing) localObject;
				localPrintStream.println("eo " + localERing.x + " " + localERing.y + " " + localERing.r1 + " "
						+ localERing.r2 + " " + localERing.Ex + " " + localERing.Ey + " " + localERing.movable + " "
						+ localERing.force_circ + " " + localERing.etransient + " " + localERing.force_rotate + " "
						+ localERing.force_translate + " " + k);
				break;
			}
			for (int j = 0; j < paramVector1.size(); j++) {
				Particle localParticle = (Particle) paramVector1.elementAt(j);
				if (localParticle.parent == localObject) {
					switch (localParticle.type) {
					case 3:
						localPrintStream
								.println("pp " + localParticle.x + " " + localParticle.y + " " + localParticle.k);
						break;
					case 4:
						localPrintStream
								.println("pn " + localParticle.x + " " + localParticle.y + " " + localParticle.k);
					}
				}
			}
		}
		localPrintStream.close();
		paramOutputStream.close();
	}

	public static void loadFile(Vector paramVector1, Vector paramVector2, InputStream is) {
		// System.out.println("Characters printed:");
		int i;
		char c;
		StringBuilder str = new StringBuilder();
		// reads till the end of the stream
		try {
			while ((i = is.read()) != -1) {

				// converts integer to character
				c = (char) i;
				str.append(c);
				// prints character
				// System.out.print(c);
			}
			loadTokens(paramVector1, paramVector2, str.toString());
		} catch (IOException | FileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadTokens(Vector paramVector1, Vector paramVector2, String inputStrings)
			throws IOException, FileFormatException {
		// StreamTokenizer localStreamTokenizer = new StreamTokenizer(paramInputStream);
		// localStreamTokenizer.eolIsSignificant(true);
		// localStreamTokenizer.commentChar(35);
		StringTokenizer localStreamTokenizer = new StringTokenizer(inputStrings, "\n");

		Object localObject = PPD.nullElement;
		Vector localVector = new Vector();
		//System.out.println("Tokens:");
		String[] strArray;
		while (localStreamTokenizer.hasMoreElements()) {
			String next = localStreamTokenizer.nextToken();
			if (next == null || next.trim().equals(""))
				continue; // skip empty lines
			if (next.trim().startsWith("//"))
				continue; // skip comment lines
			// System.out.println(next);
			strArray = next.split("\\s+");
			int i1 = -1;
			switch (strArray[0]) {
			default:
				System.err.println("Switch case not found.");
				break;
			case "ppd":
				Common.resizePPD(Integer.parseInt(strArray[1]), Integer.parseInt(strArray[2]));
				PPD.electric_const = Double.valueOf(strArray[3]);
				PPD.suscept = Double.valueOf(strArray[5]);
				break;
			case "pn":
				paramVector1.addElement(new ParticleNeg(Double.valueOf(strArray[1]), Double.valueOf(strArray[2]),
						(Element) localObject, 1, PPD.PARTICLE_NEG_Q)); // particle parameters: x,y,parent,k,Q
				break;
			case "pp":
				paramVector1.addElement(new ParticlePos(Double.valueOf(strArray[1]), Double.valueOf(strArray[2]),
						(Element) localObject, 1, PPD.PARTICLE_POS_Q)); // particle parameters: x,y,parent,k,Q
				break;
			case "er":
				localObject = new ERectangle(Integer.parseInt(strArray[1]), Integer.parseInt(strArray[2]),
						Integer.parseInt(strArray[3]), Integer.parseInt(strArray[4]), Double.valueOf(strArray[5]),
						Double.valueOf(strArray[6])); // x,y,width, height, Ex, Ey
				((Element) localObject).movable = Boolean.parseBoolean(strArray[7]);
				((Element) localObject).force_circ = Boolean.parseBoolean(strArray[8]);
				((Element) localObject).etransient = Boolean.parseBoolean(strArray[9]);
				((Element) localObject).force_rotate = Boolean.parseBoolean(strArray[10]);
				((Element) localObject).force_translate = Boolean.parseBoolean(strArray[11]);
				// if(strArray.length>11)i1=Integer.parseInt(strArray[11]);
				localVector.addElement(new Integer(i1));
				paramVector2.addElement(localObject);
				break;
			case "ec":
				localObject = new ECircle(Integer.parseInt(strArray[1]), Integer.parseInt(strArray[2]),
						Integer.parseInt(strArray[3]), Double.valueOf(strArray[4]), Double.valueOf(strArray[5])); // x,y,r,Ex,
																													// Ey,
				((Element) localObject).movable = Boolean.parseBoolean(strArray[6]);
				((Element) localObject).force_circ = Boolean.parseBoolean(strArray[7]);
				((Element) localObject).etransient = Boolean.parseBoolean(strArray[8]);
				((Element) localObject).force_rotate = Boolean.parseBoolean(strArray[9]);
				((Element) localObject).force_translate = Boolean.parseBoolean(strArray[10]);
				// if(strArray.length>11)i1=Integer.parseInt(strArray[11]);
				localVector.addElement(new Integer(i1));
				paramVector2.addElement(localObject);
				break;
			case "eo":
				localObject = new ERing(Integer.parseInt(strArray[1]), Integer.parseInt(strArray[2]),
						Integer.parseInt(strArray[3]), Integer.parseInt(strArray[4]), Double.valueOf(strArray[5]),
						Double.valueOf(strArray[6]));// x,y,r1,r2,Ex,Ey
				((Element) localObject).movable = Boolean.parseBoolean(strArray[7]);
				((Element) localObject).force_circ = Boolean.parseBoolean(strArray[8]);
				((Element) localObject).etransient = Boolean.parseBoolean(strArray[9]);
				((Element) localObject).force_rotate = Boolean.parseBoolean(strArray[10]);
				((Element) localObject).force_translate = Boolean.parseBoolean(strArray[11]);
				;
				localVector.addElement(new Integer(i1));
				paramVector2.addElement(localObject);
				break;
			}
		}
		for (int j = 0; j < localVector.size(); j++) {
			int k = ((Integer) localVector.elementAt(j)).intValue();
			if (k != -1) {
				Element localElement1 = (Element) PPD.elementBox.elementAt(j);
				Element localElement2 = (Element) PPD.elementBox.elementAt(k);
				localElement1.connection = localElement2;
				localElement2.connection = localElement1;
			}
		}

	}
}

/*
 * Location:
 * /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/
 * jacob/Data.class Java compiler version: 1 (45.3) JD-Core Version: 0.7.1
 */