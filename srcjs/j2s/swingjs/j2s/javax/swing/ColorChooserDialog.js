(function(){var P$=Clazz.newPackage("javax.swing"),I$=[[['javax.swing.JColorChooser','.ASYNCHRONOUS_COLOR'],'javax.swing.colorchooser.AbstractColorChooserPanel','java.awt.Color','javax.swing.ColorTracker','javax.swing.JOptionPane','javax.swing.ColorChooserDialog','javax.swing.colorchooser.DefaultColorSelectionModel','java.lang.StringBuffer','java.awt.BorderLayout','javax.swing.JPanel','java.awt.FlowLayout','javax.swing.JButton','javax.swing.ColorChooserDialog$1','javax.swing.AbstractAction','javax.swing.KeyStroke','javax.swing.ColorChooserDialog$3','javax.swing.ColorChooserDialog$4','sun.swing.SwingUtilities2','javax.swing.JDialog','javax.swing.UIManager',['javax.swing.ColorChooserDialog','.Closer']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ColorChooserDialog", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.JDialog');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.initialColor = null;
this.chooserPane = null;
this.cancelButton = null;
this.disposeOnHide = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.disposeOnHide = true;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Dialog$S$Z$java_awt_Component$javax_swing_JColorChooser$java_awt_event_ActionListener$java_awt_event_ActionListener', function (owner, title, modal, c, chooserPane, okListener, cancelListener) {
C$.superclazz.c$$java_awt_Dialog$S$Z.apply(this, [owner, title, modal]);
C$.$init$.apply(this);
this.initColorChooserDialog$java_awt_Component$javax_swing_JColorChooser$java_awt_event_ActionListener$java_awt_event_ActionListener(c, chooserPane, okListener, cancelListener);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Dialog$S$Z$java_awt_Component$javax_swing_JColorChooser$java_awt_event_ActionListener$java_awt_event_ActionListener$Z', function (owner, title, modal, c, chooserPane, okListener, cancelListener, disposeOnHide) {
C$.superclazz.c$$java_awt_Dialog$S$Z.apply(this, [owner, title, modal]);
C$.$init$.apply(this);
this.disposeOnHide = disposeOnHide;
this.initColorChooserDialog$java_awt_Component$javax_swing_JColorChooser$java_awt_event_ActionListener$java_awt_event_ActionListener(c, chooserPane, okListener, cancelListener);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Frame$S$Z$java_awt_Component$javax_swing_JColorChooser$java_awt_event_ActionListener$java_awt_event_ActionListener', function (owner, title, modal, c, chooserPane, okListener, cancelListener) {
C$.superclazz.c$$java_awt_Frame$S$Z.apply(this, [owner, title, modal]);
C$.$init$.apply(this);
this.disposeOnHide = false;
this.initColorChooserDialog$java_awt_Component$javax_swing_JColorChooser$java_awt_event_ActionListener$java_awt_event_ActionListener(c, chooserPane, okListener, cancelListener);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Frame$S$Z$java_awt_Component$javax_swing_JColorChooser$java_awt_event_ActionListener$java_awt_event_ActionListener$Z', function (owner, title, modal, c, chooserPane, okListener, cancelListener, disposeOnHide) {
C$.superclazz.c$$java_awt_Frame$S$Z.apply(this, [owner, title, modal]);
C$.$init$.apply(this);
this.disposeOnHide = disposeOnHide;
this.initColorChooserDialog$java_awt_Component$javax_swing_JColorChooser$java_awt_event_ActionListener$java_awt_event_ActionListener(c, chooserPane, okListener, cancelListener);
}, 1);

Clazz.newMeth(C$, 'initColorChooserDialog$java_awt_Component$javax_swing_JColorChooser$java_awt_event_ActionListener$java_awt_event_ActionListener', function (c, chooserPane, okListener, cancelListener) {
if (!(Clazz.instanceOf(c, "java.beans.PropertyChangeListener"))) {
System.out.println$S("JColorChooser: no onDialogReturn(Object) found in component " + c);
}java.awt.JSComponent.ensurePropertyChangeListener$java_awt_Component$java_awt_Component(this, c);
this.chooserPane = chooserPane;
var contentPane = this.getContentPane();
contentPane.setLayout$java_awt_LayoutManager(Clazz.new_((I$[9]||$incl$(9))));
contentPane.add$java_awt_Component$O(chooserPane, "Center");
var okString = "OK";
var cancelString = "Cancel";
var resetString = "Reset";
var buttonPane = Clazz.new_((I$[10]||$incl$(10)));
buttonPane.setLayout$java_awt_LayoutManager(Clazz.new_((I$[11]||$incl$(11)).c$$I,[1]));
var okButton = Clazz.new_((I$[12]||$incl$(12)).c$$S,[okString]);
this.getRootPane().setDefaultButton$javax_swing_JButton(okButton);
okButton.setActionCommand$S("OK");
okButton.addActionListener$java_awt_event_ActionListener(((
(function(){var C$=Clazz.newClass(P$, "ColorChooserDialog$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.awt.event.ActionListener', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.b$['javax.swing.ColorChooserDialog'].doCallback$Z(true);
});
})()
), Clazz.new_((I$[13]||$incl$(13)).$init$, [this, null])));
if (okListener != null ) {
okButton.addActionListener$java_awt_event_ActionListener(okListener);
}buttonPane.add$java_awt_Component(okButton);
this.cancelButton = Clazz.new_((I$[12]||$incl$(12)).c$$S,[cancelString]);
var cancelKeyAction = ((
(function(){var C$=Clazz.newClass(P$, "ColorChooserDialog$2", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('javax.swing.AbstractAction'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
(e.getSource()).fireActionPerformed$java_awt_event_ActionEvent(e);
this.b$['javax.swing.ColorChooserDialog'].doCallback$Z(false);
});
})()
), Clazz.new_((I$[14]||$incl$(14)), [this, null],P$.ColorChooserDialog$2));
var cancelKeyStroke = (I$[15]||$incl$(15)).getKeyStroke$I$I(27, 0);
var inputMap = this.cancelButton.getInputMap$I(2);
var actionMap = this.cancelButton.getActionMap();
if (inputMap != null  && actionMap != null  ) {
inputMap.put$javax_swing_KeyStroke$O(cancelKeyStroke, "cancel");
actionMap.put$O$javax_swing_Action("cancel", cancelKeyAction);
}this.cancelButton.setActionCommand$S("cancel");
this.cancelButton.addActionListener$java_awt_event_ActionListener(((
(function(){var C$=Clazz.newClass(P$, "ColorChooserDialog$3", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.awt.event.ActionListener', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.b$['javax.swing.ColorChooserDialog'].doCallback$Z(false);
});
})()
), Clazz.new_((I$[16]||$incl$(16)).$init$, [this, null])));
if (cancelListener != null ) {
this.cancelButton.addActionListener$java_awt_event_ActionListener(cancelListener);
}buttonPane.add$java_awt_Component(this.cancelButton);
var resetButton = Clazz.new_((I$[12]||$incl$(12)).c$$S,[resetString]);
resetButton.addActionListener$java_awt_event_ActionListener(((
(function(){var C$=Clazz.newClass(P$, "ColorChooserDialog$4", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.awt.event.ActionListener', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.b$['javax.swing.ColorChooserDialog'].reset();
});
})()
), Clazz.new_((I$[17]||$incl$(17)).$init$, [this, null])));
var mnemonic = (I$[18]||$incl$(18)).getUIDefaultsInt$O$I("ColorChooser.resetMnemonic", -1);
if (mnemonic != -1) {
resetButton.setMnemonic$I(mnemonic);
}buttonPane.add$java_awt_Component(resetButton);
contentPane.add$java_awt_Component$O(buttonPane, "South");
if ((I$[19]||$incl$(19)).isDefaultLookAndFeelDecorated()) {
var supportsWindowDecorations = (I$[20]||$incl$(20)).getLookAndFeel().getSupportsWindowDecorations();
if (supportsWindowDecorations) {
this.getRootPane().setWindowDecorationStyle$I(5);
}}this.applyComponentOrientation$java_awt_ComponentOrientation(((c == null ) ? this.getRootPane() : c).getComponentOrientation());
this.pack();
this.setLocationRelativeTo$java_awt_Component(c);
this.addWindowListener$java_awt_event_WindowListener(Clazz.new_((I$[21]||$incl$(21)), [this, null]));
});

Clazz.newMeth(C$, 'doCallback$Z', function (isOK) {
this.firePropertyChange$S$O$O("SelectedColor", null, (isOK ? this.chooserPane.getSelectionModel().getSelectedColor() : null));
this.hide();
if (this.disposeOnHide) this.dispose();
});

Clazz.newMeth(C$, 'show', function () {
this.initialColor = this.chooserPane.getColor();
C$.superclazz.prototype.show.apply(this, []);
});

Clazz.newMeth(C$, 'reset', function () {
this.chooserPane.setColor$java_awt_Color(this.initialColor);
});
;
(function(){var C$=Clazz.newClass(P$.ColorChooserDialog, "Closer", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.awt.event.WindowAdapter');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'windowClosing$java_awt_event_WindowEvent', function (e) {
this.this$0.cancelButton.doClick$I(0);
var w = e.getWindow();
if (this.this$0.disposeOnHide) w.dispose();
 else w.hide();
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:28
