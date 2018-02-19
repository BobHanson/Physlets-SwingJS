(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['javax.swing.SwingUtilities','swingjs.plaf.JSComponentUI']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "TextListener", null, null, ['java.awt.event.MouseListener', 'java.awt.event.MouseMotionListener', 'java.awt.event.FocusListener', 'javax.swing.event.ChangeListener', 'java.beans.PropertyChangeListener', 'javax.swing.event.DocumentListener']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.txtComp = null;
this.haveDocument = false;
this.ui = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$swingjs_plaf_JSTextUI$javax_swing_text_JTextComponent', function (ui, txtComp) {
C$.$init$.apply(this);
this.txtComp = txtComp;
this.ui = ui;
}, 1);

Clazz.newMeth(C$, 'checkDocument', function () {
if (!this.haveDocument && this.txtComp.getDocument() != null  ) {
this.haveDocument = true;
this.txtComp.getDocument().addDocumentListener$javax_swing_event_DocumentListener(this);
}});

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (e) {
var prop = e.getPropertyName();
if ("font" == prop || "foreground" == prop  || "preferredSize" == prop ) {
var txtComp = e.getSource();
(txtComp.getUI()).propertyChangedFromListener$S(prop);
}if ("editable" == prop) this.ui.setEditable$Z((e.getNewValue()).booleanValue());
});

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
var txtComp = e.getSource();
txtComp.repaint();
});

Clazz.newMeth(C$, 'focusGained$java_awt_event_FocusEvent', function (e) {
});

Clazz.newMeth(C$, 'focusLost$java_awt_event_FocusEvent', function (e) {
});

Clazz.newMeth(C$, 'mouseMoved$java_awt_event_MouseEvent', function (e) {
});

Clazz.newMeth(C$, 'mouseDragged$java_awt_event_MouseEvent', function (e) {
});

Clazz.newMeth(C$, 'mouseClicked$java_awt_event_MouseEvent', function (e) {
});

Clazz.newMeth(C$, 'mousePressed$java_awt_event_MouseEvent', function (e) {
if ((I$[1]||$incl$(1)).isLeftMouseButton$java_awt_event_MouseEvent(e)) {
var txtComp = e.getSource();
if (!txtComp.contains$I$I(e.getX(), e.getY())) return;
if (!txtComp.hasFocus() && txtComp.isRequestFocusEnabled() ) {
txtComp.requestFocus();
}}});

Clazz.newMeth(C$, 'mouseReleased$java_awt_event_MouseEvent', function (e) {
});

Clazz.newMeth(C$, 'mouseEntered$java_awt_event_MouseEvent', function (e) {
});

Clazz.newMeth(C$, 'mouseExited$java_awt_event_MouseEvent', function (e) {
});

Clazz.newMeth(C$, 'handleJSTextEvent$swingjs_plaf_JSTextUI$I$O', function (ui, eventType, jQueryEvent) {
var dot = 0;
var mark = 0;
var evType = null;
var keyCode = 0;
{
mark = jQueryEvent.target.selectionStart;
dot = jQueryEvent.target.selectionEnd;
evType = jQueryEvent.type;
keyCode = jQueryEvent.keyCode;
if (keyCode == 13) keyCode = 10;
}
var oldDot = ui.editor.getCaret().getDot();
var oldMark = ui.editor.getCaret().getMark();
if (dot != mark && oldMark == dot ) {
dot = mark;
mark = oldMark;
}switch (eventType) {
case 507:
return false;
case 501:
case 502:
case 500:
break;
case 401:
case 402:
case 400:
if (keyCode == 10 && ui.handleEnter$I(eventType) ) break;
var val = ui.getJSTextValue();
if (!val.equals$O(ui.currentText)) {
var oldval = ui.currentText;
ui.editor.setText$S(val);
ui.editor.firePropertyChange$S$O$O("text", oldval, val);
ui.domNode.setSelectionRange(dot, dot);
}break;
}
if (dot != oldDot || mark != oldMark ) {
ui.editor.getCaret().setDot$I(dot);
if (dot != mark) ui.editor.getCaret().moveDot$I(mark);
ui.editor.caretEvent.fire();
}if ((I$[2]||$incl$(2)).debugging) System.out.println$S(ui.id + " TextListener handling event " + evType + " " + eventType + " " + ui.editor.getCaret() + " " + ui.getComponentText().length$() );
return true;
});

Clazz.newMeth(C$, 'insertUpdate$javax_swing_event_DocumentEvent', function (e) {
p$.setText.apply(this, []);
});

Clazz.newMeth(C$, 'removeUpdate$javax_swing_event_DocumentEvent', function (e) {
p$.setText.apply(this, []);
});

Clazz.newMeth(C$, 'changedUpdate$javax_swing_event_DocumentEvent', function (e) {
});

Clazz.newMeth(C$, 'setText', function () {
this.ui.setText$S(this.txtComp.getText());
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:30
