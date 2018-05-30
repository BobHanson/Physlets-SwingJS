(function(){var P$=Clazz.newPackage("a2s"),I$=[['a2s.A2SEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "A2SListener", null, null, ['java.awt.event.AdjustmentListener', 'java.awt.event.ActionListener', 'java.awt.event.KeyListener', 'java.awt.event.MouseListener', 'java.awt.event.MouseMotionListener', 'java.awt.event.TextListener', 'javax.swing.event.ChangeListener']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
});

Clazz.newMeth(C$, 'mouseDragged$java_awt_event_MouseEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
});

Clazz.newMeth(C$, 'mouseMoved$java_awt_event_MouseEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
});

Clazz.newMeth(C$, 'mouseClicked$java_awt_event_MouseEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
});

Clazz.newMeth(C$, 'mousePressed$java_awt_event_MouseEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
});

Clazz.newMeth(C$, 'mouseReleased$java_awt_event_MouseEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
});

Clazz.newMeth(C$, 'mouseEntered$java_awt_event_MouseEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
});

Clazz.newMeth(C$, 'mouseExited$java_awt_event_MouseEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
});

Clazz.newMeth(C$, 'keyTyped$java_awt_event_KeyEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
});

Clazz.newMeth(C$, 'keyPressed$java_awt_event_KeyEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
});

Clazz.newMeth(C$, 'keyReleased$java_awt_event_KeyEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
});

Clazz.newMeth(C$, 'adjustmentValueChanged$java_awt_event_AdjustmentEvent', function (e) {
Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AWTEvent,[e]).run();
if (Clazz.instanceOf(e.getSource(), "a2s.Scrollbar")) (e.getSource()).processAdjustmentEvent$java_awt_event_AdjustmentEvent(e);
});

Clazz.newMeth(C$, 'textValueChanged$java_awt_event_TextEvent', function (e) {
System.out.println$S("AHAH! a2sListener textvalue changed " + e);
});

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
System.out.println$S("Ahah a2slistener state changed " + e.getSource());
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:44:58
