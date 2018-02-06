(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.awt.Toolkit','javax.swing.SwingUtilities',['javax.swing.Popup','.DefaultFrame'],['javax.swing.Popup','.HeavyWeightWindow']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Popup", function(){
Clazz.newInstance(this, arguments,0,C$);
});
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.component = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Component$java_awt_Component$I$I', function (owner, contents, x, y) {
C$.c$.apply(this, []);
if (contents == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Contents must be non-null"]);
}this.reset$java_awt_Component$java_awt_Component$I$I(owner, contents, x, y);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'show', function () {
var component = this.getComponent();
if (component != null ) {
component.visible = false;
component.show();
}});

Clazz.newMeth(C$, 'hide', function () {
var component = this.getComponent();
if (Clazz.instanceOf(component, "javax.swing.JWindow")) {
component.hide();
(component).getContentPane().removeAll();
}this.dispose();
});

Clazz.newMeth(C$, 'dispose', function () {
var component = this.getComponent();
var window = (I$[2]||$incl$(2)).getWindowAncestor$java_awt_Component(component);
if (Clazz.instanceOf(component, "javax.swing.JWindow")) {
(component).dispose();
component = null;
}if (Clazz.instanceOf(window, "javax.swing.Popup.DefaultFrame")) {
window.dispose();
}});

Clazz.newMeth(C$, 'reset$java_awt_Component$java_awt_Component$I$I', function (owner, contents, ownerX, ownerY) {
if (this.getComponent() == null ) {
this.component = this.createComponent$java_awt_Component(owner);
}var c = this.getComponent();
if (Clazz.instanceOf(c, "javax.swing.JWindow")) {
var component = this.getComponent();
component.setLocation$I$I(ownerX, ownerY);
component.getContentPane().add$java_awt_Component$O(contents, "Center");
contents.invalidate();
if (component.isVisible()) {
this.pack();
}}});

Clazz.newMeth(C$, 'pack', function () {
var component = this.getComponent();
if (Clazz.instanceOf(component, "java.awt.Window")) {
(component).pack();
}});

Clazz.newMeth(C$, 'getParentWindow$java_awt_Component', function (owner) {
var window = null;
if (Clazz.instanceOf(owner, "java.awt.Window")) {
window = owner;
} else if (owner != null ) {
window = (I$[2]||$incl$(2)).getWindowAncestor$java_awt_Component(owner);
}if (window == null ) {
window = Clazz.new_((I$[3]||$incl$(3)));
}return window;
});

Clazz.newMeth(C$, 'createComponent$java_awt_Component', function (owner) {
return Clazz.new_((I$[4]||$incl$(4)).c$$java_awt_Window,[p$.getParentWindow$java_awt_Component.apply(this, [owner])]);
});

Clazz.newMeth(C$, 'getComponent', function () {
return this.component;
});

Clazz.newMeth(C$, 'isDefaultFrame$O', function (window) {
return Clazz.instanceOf(window, "javax.swing.Popup.DefaultFrame");
}, 1);

Clazz.newMeth(C$, 'isHeavyWeight$java_awt_Container', function (window) {
return Clazz.instanceOf(window, "javax.swing.Popup.HeavyWeightWindow");
}, 1);
;
(function(){var C$=Clazz.newClass(P$.Popup, "HeavyWeightWindow", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.JWindow');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Window', function (parent) {
C$.superclazz.c$$java_awt_Window.apply(this, [parent]);
C$.$init$.apply(this);
this.setFocusableWindowState$Z(false);
var tk = (I$[1]||$incl$(1)).getDefaultToolkit();
if (Clazz.instanceOf(tk, "sun.awt.SunToolkit")) {
(tk).setOverrideRedirect$java_awt_Window(this);
}this.getRootPane().setUseTrueDoubleBuffering$Z(false);
try {
this.setAlwaysOnTop$Z(true);
} catch (se) {
if (Clazz.exceptionOf(se, "java.lang.SecurityException")){
} else {
throw se;
}
}
}, 1);

Clazz.newMeth(C$, 'update$java_awt_Graphics', function (g) {
this.paint$java_awt_Graphics(g);
});

Clazz.newMeth(C$, 'show', function () {
this.pack();
if (this.getWidth() > 0 && this.getHeight() > 0 ) {
C$.superclazz.prototype.show.apply(this, []);
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Popup, "DefaultFrame", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.awt.Frame');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-06 08:59:40
