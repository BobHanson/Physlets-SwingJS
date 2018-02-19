(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.util.Hashtable','java.util.Vector','Thread',['javax.swing.KeyboardManager','.ComponentKeyStrokePair'],'javax.swing.KeyStroke','javax.swing.JMenuBar']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "KeyboardManager", function(){
Clazz.newInstance(this, arguments,0,C$);
});
C$.currentManager = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.currentManager = Clazz.new_(C$);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.containerMap = null;
this.componentKeyStrokeMap = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.containerMap = Clazz.new_((I$[1]||$incl$(1)));
this.componentKeyStrokeMap = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'getCurrentManager', function () {
return C$.currentManager;
}, 1);

Clazz.newMeth(C$, 'setCurrentManager$javax_swing_KeyboardManager', function (km) {
C$.currentManager = km;
}, 1);

Clazz.newMeth(C$, 'registerKeyStroke$javax_swing_KeyStroke$javax_swing_JComponent', function (k, c) {
var topContainer = C$.getTopAncestor$javax_swing_JComponent(c);
if (topContainer == null ) {
return;
}var keyMap = this.containerMap.get$O(topContainer);
if (keyMap == null ) {
keyMap = this.registerNewTopContainer$java_awt_Container(topContainer);
}var tmp = keyMap.get$O(k);
if (tmp == null ) {
keyMap.put$TK$TV(k, c);
} else if (Clazz.instanceOf(tmp, "java.util.Vector")) {
var v = tmp;
if (!v.contains$O(c)) {
v.addElement$TE(c);
}} else if (Clazz.instanceOf(tmp, "javax.swing.JComponent")) {
if (tmp !== c ) {
var v = Clazz.new_((I$[2]||$incl$(2)));
v.addElement$TE(tmp);
v.addElement$TE(c);
keyMap.put$TK$TV(k, v);
}} else {
System.out.println$S("Unexpected condition in registerKeyStroke");
(I$[3]||$incl$(3)).dumpStack();
}this.componentKeyStrokeMap.put$TK$TV(Clazz.new_((I$[4]||$incl$(4)).c$$O$O, [this, null, c, k]), topContainer);
});

Clazz.newMeth(C$, 'getTopAncestor$javax_swing_JComponent', function (c) {
for (var p = c.getParent(); p != null ; p = p.getParent()) {
if (Clazz.instanceOf(p, "java.awt.Window") && (p).isFocusableWindow()  || Clazz.instanceOf(p, "java.applet.Applet") ) {
return p;
}}
return null;
}, 1);

Clazz.newMeth(C$, 'unregisterKeyStroke$javax_swing_KeyStroke$javax_swing_JComponent', function (ks, c) {
var ckp = Clazz.new_((I$[4]||$incl$(4)).c$$O$O, [this, null, c, ks]);
var topContainer = this.componentKeyStrokeMap.get$O(ckp);
if (topContainer == null ) {
return;
}var keyMap = this.containerMap.get$O(topContainer);
if (keyMap == null ) {
(I$[3]||$incl$(3)).dumpStack();
return;
}var tmp = keyMap.get$O(ks);
if (tmp == null ) {
(I$[3]||$incl$(3)).dumpStack();
return;
}if (Clazz.instanceOf(tmp, "javax.swing.JComponent") && tmp === c  ) {
keyMap.remove$O(ks);
} else if (Clazz.instanceOf(tmp, "java.util.Vector")) {
var v = tmp;
v.removeElement$O(c);
if (v.isEmpty()) {
keyMap.remove$O(ks);
}}if (keyMap.isEmpty()) {
this.containerMap.remove$O(topContainer);
}this.componentKeyStrokeMap.remove$O(ckp);
});

Clazz.newMeth(C$, 'fireKeyboardAction$java_awt_event_KeyEvent$Z$java_awt_Container', function (e, pressed, topAncestor) {
if (e.isConsumed()) {
System.out.println$S("Aquired pre-used event!");
(I$[3]||$incl$(3)).dumpStack();
}var ks;
if (e.getID() == 400) {
ks = (I$[5]||$incl$(5)).getKeyStroke$C(e.getKeyChar());
} else {
ks = (I$[5]||$incl$(5)).getKeyStroke$I$I$Z(e.getKeyCode(), e.getModifiers(), !pressed);
}var keyMap = this.containerMap.get$O(topAncestor);
if (keyMap != null ) {
var tmp = keyMap.get$O(ks);
if (tmp == null ) {
} else if (Clazz.instanceOf(tmp, "javax.swing.JComponent")) {
var c = tmp;
if (c.isShowing() && c.isEnabled() ) {
this.fireBinding$javax_swing_JComponent$javax_swing_KeyStroke$java_awt_event_KeyEvent$Z(c, ks, e, pressed);
}} else if (Clazz.instanceOf(tmp, "java.util.Vector")) {
var v = tmp;
for (var counter = v.size() - 1; counter >= 0; counter--) {
var c = v.elementAt$I(counter);
if (c.isShowing() && c.isEnabled() ) {
this.fireBinding$javax_swing_JComponent$javax_swing_KeyStroke$java_awt_event_KeyEvent$Z(c, ks, e, pressed);
if (e.isConsumed()) return true;
}}
} else {
System.out.println$S("Unexpected condition in fireKeyboardAction " + tmp);
(I$[3]||$incl$(3)).dumpStack();
}}if (e.isConsumed()) {
return true;
}if (keyMap != null ) {
var v = keyMap.get$O(Clazz.getClass((I$[6]||$incl$(6))));
if (v != null ) {
var iter = v.elements();
while (iter.hasMoreElements()){
var mb = iter.nextElement();
if (mb.isShowing() && mb.isEnabled() ) {
this.fireBinding$javax_swing_JComponent$javax_swing_KeyStroke$java_awt_event_KeyEvent$Z(mb, ks, e, pressed);
if (e.isConsumed()) {
return true;
}}}
}}return e.isConsumed();
});

Clazz.newMeth(C$, 'fireBinding$javax_swing_JComponent$javax_swing_KeyStroke$java_awt_event_KeyEvent$Z', function (c, ks, e, pressed) {
if (c.processKeyBinding$javax_swing_KeyStroke$java_awt_event_KeyEvent$I$Z(ks, e, 2, pressed)) {
e.consume();
}});

Clazz.newMeth(C$, 'registerMenuBar$javax_swing_JMenuBar', function (mb) {
var top = C$.getTopAncestor$javax_swing_JComponent(mb);
if (top == null ) {
return;
}var keyMap = this.containerMap.get$O(top);
if (keyMap == null ) {
keyMap = this.registerNewTopContainer$java_awt_Container(top);
}var menuBars = keyMap.get$O(Clazz.getClass((I$[6]||$incl$(6))));
if (menuBars == null ) {
menuBars = Clazz.new_((I$[2]||$incl$(2)));
keyMap.put$TK$TV(Clazz.getClass((I$[6]||$incl$(6))), menuBars);
}if (!menuBars.contains$O(mb)) {
menuBars.addElement$TE(mb);
}});

Clazz.newMeth(C$, 'unregisterMenuBar$javax_swing_JMenuBar', function (mb) {
var topContainer = C$.getTopAncestor$javax_swing_JComponent(mb);
if (topContainer == null ) {
return;
}var keyMap = this.containerMap.get$O(topContainer);
if (keyMap != null ) {
var v = keyMap.get$O(Clazz.getClass((I$[6]||$incl$(6))));
if (v != null ) {
v.removeElement$O(mb);
if (v.isEmpty()) {
keyMap.remove$O(Clazz.getClass((I$[6]||$incl$(6))));
if (keyMap.isEmpty()) {
this.containerMap.remove$O(topContainer);
}}}}});

Clazz.newMeth(C$, 'registerNewTopContainer$java_awt_Container', function (topContainer) {
var keyMap = Clazz.new_((I$[1]||$incl$(1)));
this.containerMap.put$TK$TV(topContainer, keyMap);
return keyMap;
});
;
(function(){var C$=Clazz.newClass(P$.KeyboardManager, "ComponentKeyStrokePair", function(){
Clazz.newInstance(this, arguments[0],true,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.component = null;
this.keyStroke = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O$O', function (comp, key) {
C$.$init$.apply(this);
this.component = comp;
this.keyStroke = key;
}, 1);

Clazz.newMeth(C$, 'equals$O', function (o) {
if (!(Clazz.instanceOf(o, "javax.swing.KeyboardManager.ComponentKeyStrokePair"))) {
return false;
}var ckp = o;
return ((this.component.equals$O(ckp.component)) && (this.keyStroke.equals$O(ckp.keyStroke)) );
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.component.hashCode() * this.keyStroke.hashCode();
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:39
