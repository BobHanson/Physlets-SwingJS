(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[[['swingjs.plaf.ButtonListener','.Actions'],'swingjs.plaf.LazyActionMap','javax.swing.SwingUtilities','javax.swing.plaf.ComponentInputMapUIResource','javax.swing.KeyStroke']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ButtonListener", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, ['java.awt.event.MouseListener', 'java.awt.event.MouseMotionListener', 'java.awt.event.FocusListener', 'javax.swing.event.ChangeListener', 'java.beans.PropertyChangeListener']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.ui = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'loadActionMap$swingjs_plaf_LazyActionMap', function (map) {
map.put$javax_swing_Action(Clazz.new_((I$[1]||$incl$(1)).c$$S,["pressed"]));
map.put$javax_swing_Action(Clazz.new_((I$[1]||$incl$(1)).c$$S,["released"]));
}, 1);

Clazz.newMeth(C$, 'c$$swingjs_plaf_JSButtonUI$Z', function (ui, isMenuItem) {
C$.$init$.apply(this);
this.ui=ui;
}, 1);

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (e) {
var prop = e.getPropertyName();
var b = e.getSource();
if (prop == "mnemonic") {
this.updateMnemonicBinding$javax_swing_AbstractButton(b);
} else if (prop == "contentAreaFilled") {
this.checkOpacity$javax_swing_AbstractButton(b);
} else {
this.ui.propertyChangedFromListener$S(prop);
}});

Clazz.newMeth(C$, 'checkOpacity$javax_swing_AbstractButton', function (b) {
b.setOpaque$Z(b.isContentAreaFilled());
});

Clazz.newMeth(C$, 'installKeyboardActions$javax_swing_JComponent', function (c) {
var button = c;
this.updateMnemonicBinding$javax_swing_AbstractButton(button);
(I$[2]||$incl$(2)).installLazyActionMap$javax_swing_JComponent$Class$S(c, Clazz.getClass(C$), "Button.actionMap");
var km = this.getInputMap$I$javax_swing_JComponent(0, c);
(I$[3]||$incl$(3)).replaceUIInputMap$javax_swing_JComponent$I$javax_swing_InputMap(c, 0, km);
});

Clazz.newMeth(C$, 'uninstallKeyboardActions$javax_swing_JComponent', function (c) {
(I$[3]||$incl$(3)).replaceUIInputMap$javax_swing_JComponent$I$javax_swing_InputMap(c, 2, null);
(I$[3]||$incl$(3)).replaceUIInputMap$javax_swing_JComponent$I$javax_swing_InputMap(c, 0, null);
(I$[3]||$incl$(3)).replaceUIActionMap$javax_swing_JComponent$javax_swing_ActionMap(c, null);
});

Clazz.newMeth(C$, 'getInputMap$I$javax_swing_JComponent', function (condition, c) {
return null;
});

Clazz.newMeth(C$, 'updateMnemonicBinding$javax_swing_AbstractButton', function (b) {
var m = b.getMnemonic();
if (m != 0) {
var map = (I$[3]||$incl$(3)).getUIInputMap$javax_swing_JComponent$I(b, 2);
if (map == null ) {
map=Clazz.new_((I$[4]||$incl$(4)).c$$javax_swing_JComponent,[b]);
(I$[3]||$incl$(3)).replaceUIInputMap$javax_swing_JComponent$I$javax_swing_InputMap(b, 2, map);
}map.clear();
map.put$javax_swing_KeyStroke$O((I$[5]||$incl$(5)).getKeyStroke$I$I$Z(m, 8, false), "pressed");
map.put$javax_swing_KeyStroke$O((I$[5]||$incl$(5)).getKeyStroke$I$I$Z(m, 8, true), "released");
map.put$javax_swing_KeyStroke$O((I$[5]||$incl$(5)).getKeyStroke$I$I$Z(m, 0, true), "released");
} else {
var map = (I$[3]||$incl$(3)).getUIInputMap$javax_swing_JComponent$I(b, 2);
if (map != null ) {
map.clear();
}}});

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
var b = e.getSource();
this.verifyButtonClick$javax_swing_AbstractButton(b);
});

Clazz.newMeth(C$, 'focusGained$java_awt_event_FocusEvent', function (e) {
});

Clazz.newMeth(C$, 'focusLost$java_awt_event_FocusEvent', function (e) {
var b = e.getSource();
var model = b.getModel();
model.setArmed$Z(false);
model.setPressed$Z(false);
});

Clazz.newMeth(C$, 'mouseMoved$java_awt_event_MouseEvent', function (e) {
});

Clazz.newMeth(C$, 'mouseDragged$java_awt_event_MouseEvent', function (e) {
});

Clazz.newMeth(C$, 'mouseClicked$java_awt_event_MouseEvent', function (e) {
});

Clazz.newMeth(C$, 'mousePressed$java_awt_event_MouseEvent', function (e) {
if ((I$[3]||$incl$(3)).isLeftMouseButton$java_awt_event_MouseEvent(e)) {
var b = e.getSource();
if (b.uiClassID == "MenuUI" && (b).isTopLevelMenu() ) (b).setPopupMenuVisible$Z(true);
}});

Clazz.newMeth(C$, 'mouseReleased$java_awt_event_MouseEvent', function (e) {
if ((I$[3]||$incl$(3)).isLeftMouseButton$java_awt_event_MouseEvent(e)) {
var b = e.getSource();
if (b.uiClassID == "MenuUI") {
return;
}b.doClick$I(0);
this.verifyButtonClick$javax_swing_AbstractButton(b);
}});

Clazz.newMeth(C$, 'verifyButtonClick$javax_swing_AbstractButton', function (b) {
var m = b.getModel();
var btn = this.ui.domBtn;
var state = m.isSelected();
{
setTimeout(function(){btn && (btn.checked = state)}, 0);
}
return true;
});

Clazz.newMeth(C$, 'mouseEntered$java_awt_event_MouseEvent', function (e) {
var b = e.getSource();
var model = b.getModel();
if (b.isRolloverEnabled() && !(I$[3]||$incl$(3)).isLeftMouseButton$java_awt_event_MouseEvent(e) ) {
model.setRollover$Z(true);
}if (model.isPressed()) model.setArmed$Z(true);
});

Clazz.newMeth(C$, 'mouseExited$java_awt_event_MouseEvent', function (e) {
var b = e.getSource();
var model = b.getModel();
if (b.isRolloverEnabled()) {
model.setRollover$Z(false);
}model.setArmed$Z(false);
});
;
(function(){var C$=Clazz.newClass(P$.ButtonListener, "Actions", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'sun.swing.UIAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.superclazz.c$$S.apply(this, [name]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var b = e.getSource();
var key = this.getName();
if (key == "pressed") {
var model = b.getModel();
model.setArmed$Z(true);
model.setPressed$Z(true);
if (!b.hasFocus()) {
b.requestFocus();
}} else if (key == "released") {
var model = b.getModel();
model.setPressed$Z(false);
model.setArmed$Z(false);
}});

Clazz.newMeth(C$, 'isEnabled$O', function (sender) {
if (sender != null  && (Clazz.instanceOf(sender, "javax.swing.AbstractButton"))  && !(sender).getModel().isEnabled() ) {
return false;
} else {
return true;
}});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:50
