(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[['javax.swing.JPanel','java.io.File','java.io.FileOutputStream','java.awt.event.WindowAdapter','java.awt.BorderLayout','gnu.jpdf.TestMenuBar','gnu.jpdf.PDFJob','gnu.jpdf.TestPanel','javax.swing.JScrollPane','java.awt.Dimension','java.awt.Toolkit','javax.swing.JOptionPane','gnu.jpdf.HelpFrame','java.awt.JobAttributes','java.awt.Color','java.awt.Point','java.awt.Font','gnu.jpdf.BoundingBox','java.awt.MediaTracker','javax.swing.JMenu','javax.swing.JMenuItem','javax.swing.KeyStroke','javax.swing.JTextArea','java.lang.StringBuffer']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "TestMenuBar", null, 'javax.swing.JMenuBar');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.file = null;
this.personnel = null;
this.help = null;
this.about = null;
this.view = null;
this.printer = null;
this.close = null;
this.helpTopics = null;
this.aboutApp = null;
this.viewFirstPage = null;
this.viewSecondPage = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_event_ActionListener', function (parent) {
Clazz.super_(C$, this,1);
this.file=Clazz.new_((I$[20]||$incl$(20)).c$$S,["File"]);
this.file.setMnemonic$I(70);
this.printer=Clazz.new_((I$[21]||$incl$(21)).c$$S,["Print"]);
this.printer.setMnemonic$I(82);
this.printer.setAccelerator$javax_swing_KeyStroke((I$[22]||$incl$(22)).getKeyStroke$I$I(82, 2));
this.printer.addActionListener$java_awt_event_ActionListener(parent);
this.file.add$javax_swing_JMenuItem(this.printer);
this.close=Clazz.new_((I$[21]||$incl$(21)).c$$S,["Close"]);
this.close.setMnemonic$I(81);
this.close.setAccelerator$javax_swing_KeyStroke((I$[22]||$incl$(22)).getKeyStroke$I$I(81, 2));
this.close.addActionListener$java_awt_event_ActionListener(parent);
this.file.add$javax_swing_JMenuItem(this.close);
this.view=Clazz.new_((I$[20]||$incl$(20)).c$$S,["View"]);
this.view.setMnemonic$I(86);
this.viewFirstPage=Clazz.new_((I$[21]||$incl$(21)).c$$S,["First Page"]);
this.viewFirstPage.addActionListener$java_awt_event_ActionListener(parent);
this.view.add$javax_swing_JMenuItem(this.viewFirstPage);
this.viewSecondPage=Clazz.new_((I$[21]||$incl$(21)).c$$S,["Second Page"]);
this.viewSecondPage.addActionListener$java_awt_event_ActionListener(parent);
this.view.add$javax_swing_JMenuItem(this.viewSecondPage);
this.help=Clazz.new_((I$[20]||$incl$(20)).c$$S,["Help"]);
this.help.setMnemonic$I(72);
this.helpTopics=Clazz.new_((I$[21]||$incl$(21)).c$$S,["Help Topics"]);
this.helpTopics.addActionListener$java_awt_event_ActionListener(parent);
this.help.add$javax_swing_JMenuItem(this.helpTopics);
this.about=Clazz.new_((I$[20]||$incl$(20)).c$$S,["About"]);
this.about.setMnemonic$I(65);
this.aboutApp=Clazz.new_((I$[21]||$incl$(21)).c$$S,["About"]);
this.aboutApp.addActionListener$java_awt_event_ActionListener(parent);
this.about.add$javax_swing_JMenuItem(this.aboutApp);
this.add$javax_swing_JMenu(this.file);
this.add$javax_swing_JMenu(this.view);
this.add$javax_swing_JMenu(this.help);
this.add$javax_swing_JMenu(this.about);
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:04
