(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[['javax.swing.JPanel','java.io.File','java.io.FileOutputStream','java.awt.event.WindowAdapter','java.awt.BorderLayout','gnu.jpdf.TestMenuBar','gnu.jpdf.PDFJob','gnu.jpdf.TestPanel','javax.swing.JScrollPane','java.awt.Dimension','java.awt.Toolkit','javax.swing.JOptionPane','gnu.jpdf.HelpFrame','java.awt.JobAttributes','java.awt.Color','java.awt.Point','java.awt.Font','gnu.jpdf.BoundingBox','java.awt.MediaTracker','javax.swing.JMenu','javax.swing.JMenuItem','javax.swing.KeyStroke','javax.swing.JTextArea','java.lang.StringBuffer']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "HelpFrame", null, 'javax.swing.JFrame');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.setTitle$S("gnupdf Help");
var helpContent = this.getContentPane();
helpContent.setLayout$java_awt_LayoutManager(Clazz.new_((I$[5]||$incl$(5))));
var textArea = Clazz.new_((I$[23]||$incl$(23)).c$$I$I,[20, 40]);
textArea.setLineWrap$Z(true);
textArea.append$S(p$.getHelpText.apply(this, []));
var helpScroller = Clazz.new_((I$[9]||$incl$(9)).c$$java_awt_Component,[textArea]);
helpContent.add$java_awt_Component(helpScroller);
this.setSize$java_awt_Dimension(helpScroller.getSize());
this.setLocation$java_awt_Point(Clazz.new_((I$[16]||$incl$(16)).c$$I$I,[200, 200]));
this.pack();
this.toFront();
this.show();
textArea.setCaretPosition$I(0);
textArea.setEditable$Z(false);
}, 1);

Clazz.newMeth(C$, 'getHelpText', function () {
var out = Clazz.new_((I$[24]||$incl$(24)));
out.append$S("gnujpdf Help File and Tutorial\u000a");
out.append$S("\u000a");
out.append$S("This file contains some general help and a simple tutorial on the\u000a");
out.append$S("gnujpdf java package (gnu.jpdf.*).  More information can be\u000a");
out.append$S("obtained from the website, http://gnujpdf.sourceforge.net.\u000a");
out.append$S("\u000a");
out.append$S("gnujpdf is a set of Java classes that allows a programmer to use\u000a");
out.append$S("extended versions of java.awt.Graphics and java.awt.PrintJob to\u000a");
out.append$S("generate and print pdf files.  The idea is to use methods and\u000a");
out.append$S("classes that act on a Graphics object to produce the same output\u000a");
out.append$S("in a pdf file, on the screen, and on the printer.\u000a");
out.append$S("\u000a");
out.append$S("The best source of information for a programmer wishing to use\u000a");
out.append$S("this simple API is the source code in PDFTest.java.  It\u000a");
out.append$S("demonstrates a simple application that displays various\u000a");
out.append$S("formatting and simultaneously writes a pdf file that will be an\u000a");
out.append$S("identical copy of what is seen on the screen.\u000a");
out.append$S("\u000a");
out.append$S("The starting point for creating any PDF document with this\u000a");
out.append$S("library is the PDFJob class.\u000a");
out.append$S("\u000a");
out.append$S("PDFJob job = new PDFJob(fileOutputStream);\u000a");
out.append$S("\u000a");
out.append$S("The fileOutputStream is normally a stream initialized with the\u000a");
out.append$S("name of the pdf you wish to generate, such as \"test.pdf\".  A\u000a");
out.append$S("PDFGraphics object can be obtained from the job by calling:\u000a");
out.append$S("\u000a");
out.append$S("Graphics pdfGraphics = job.getGraphics();\u000a");
out.append$S("\u000a");
out.append$S("This Graphics object can be passed into the same methods used to\u000a");
out.append$S("draw to the screen.  Most of the common methods in\u000a");
out.append$S("java.awt.Graphics have been implemented (with a few important\u000a");
out.append$S("exceptions - this is a beta product, so there is still plenty of\u000a");
out.append$S("work to be done - see the source code for more specifics).  When\u000a");
out.append$S("calling methods such as drawString(..)  or drawImage(..), what is\u000a");
out.append$S("actually happening is that the PDFGraphics object is writing the\u000a");
out.append$S("necessary markup to the output stream.\u000a");
out.append$S("\u000a");
out.append$S("A new pdf page is initialized by disposing of the exisiting\u000a");
out.append$S("Graphics object and getting a new one from the job.\u000a");
out.append$S("\u000a");
out.append$S("pdfGraphics.dispose(); \u000a");
out.append$S("pdfGraphics = job.getGraphics();\u000a");
out.append$S("\u000a");
out.append$S("Any Graphics operations will now be made on a new page in the pdf\u000a");
out.append$S("document.  When the document is finished, the job must be closed\u000a");
out.append$S("out:\u000a");
out.append$S("\u000a");
out.append$S("pdfGraphics.dispose();\u000a");
out.append$S("job.end();\u000a");
out.append$S("\u000a");
out.append$S("And the fileOutputStream will need to be closed properly as well,\u000a");
out.append$S("as this is not guaranteed to be taken care of by the PDF classes.\u000a");
out.append$S("\u000a");
out.append$S("----------------\u000a");
out.append$S("End of Help File\u000a");
out.append$S("\u000a");
out.append$S("For more information, see http://gnujpdf.sourceforge.net\u000a");
out.append$S("\u000a");
return out.toString();
});
})();
//Created 2018-02-08 10:01:44
