package circuitsimulator;
import java.util.Enumeration;
import java.util.Vector;

class Translator {
    String languageString="";
    Vector stringList = new Vector();
    java.applet.Applet applet;

    Translator (java.applet.Applet app){
        applet = app;
    }

    class LanguageItem {
        String english, foreign;

        LanguageItem(String e, String f){
            english = ""+e;
            foreign = ""+f;
        }

        public String toString(){return english+"="+foreign;}

    }

    void loadLanguage(String inputfile){
        System.out.println("PropertyHandler is loading Properties...");
        boolean trydoc=false;
        languageString="";
        try {
            java.net.URL url = new java.net.URL(applet.getCodeBase().toString()+inputfile);
            java.io.InputStream in = url.openStream();
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(in));
            String line;
            while ((line = br.readLine())!=null) {
                int sepInd = line.indexOf("=");
                stringList.addElement(new LanguageItem(line.substring(sepInd+1),line.substring(0,sepInd)));
            }
        } catch (Exception e) {trydoc = true;}
        if (trydoc){
            languageString="";
            try {
                java.net.URL url = new java.net.URL(applet.getDocumentBase().toString()+inputfile);
                java.io.InputStream in = url.openStream();
                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(in));
                String line;
                while ((line = br.readLine())!=null) {
                    int sepInd = line.indexOf("=");
                    stringList.addElement(new LanguageItem(line.substring(sepInd+1),line.substring(0,sepInd)));
                }
            } catch (Exception e) {
                System.out.println("load failed: " + e.getMessage());
            }
        }
        System.out.println("Language file loaded.");
        printList();
    }

    String translate(String from,String dir){
        if (languageString.equals("")) return from;
        LanguageItem langItem=null;
        if (dir.equals("->")) {
            for (Enumeration e=stringList.elements();e.hasMoreElements();) {
                langItem = (LanguageItem) e.nextElement();
                if (langItem.english.equals(from)) return langItem.foreign;
            }
        } else {
            for (Enumeration e=stringList.elements();e.hasMoreElements();) {
                langItem = (LanguageItem) e.nextElement();
                if (langItem.foreign.equals(from)) return langItem.english;
            }
        }
        System.out.println(from+": not in list");
        return from;
    }

    public void printList() {
        LanguageItem langItem=null;
        for (Enumeration e=stringList.elements();e.hasMoreElements();) {
            langItem = (LanguageItem) e.nextElement();
            System.out.println(langItem.toString());
        }
    }

}