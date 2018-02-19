(function(){var P$=Clazz.newPackage("javax.swing"),I$=[];
var C$=Clazz.newClass(P$, "ComponentInputMap", null, 'javax.swing.InputMap');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.component = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JComponent', function (component) {
Clazz.super_(C$, this,1);
this.component = component;
if (component == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["ComponentInputMaps must be associated with a non-null JComponent"]);
}}, 1);

Clazz.newMeth(C$, 'setParent$javax_swing_InputMap', function (map) {
if (this.getParent() === map ) {
return;
}if (map != null  && (!(Clazz.instanceOf(map, "javax.swing.ComponentInputMap")) || (map).getComponent() !== this.getComponent()  ) ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["ComponentInputMaps must have a parent ComponentInputMap associated with the same component"]);
}C$.superclazz.prototype.setParent$javax_swing_InputMap.apply(this, [map]);
this.getComponent().componentInputMapChanged$javax_swing_ComponentInputMap(this);
});

Clazz.newMeth(C$, 'getComponent', function () {
return this.component;
});

Clazz.newMeth(C$, 'put$javax_swing_KeyStroke$O', function (keyStroke, actionMapKey) {
C$.superclazz.prototype.put$javax_swing_KeyStroke$O.apply(this, [keyStroke, actionMapKey]);
if (this.getComponent() != null ) {
this.getComponent().componentInputMapChanged$javax_swing_ComponentInputMap(this);
}});

Clazz.newMeth(C$, 'remove$javax_swing_KeyStroke', function (key) {
C$.superclazz.prototype.remove$javax_swing_KeyStroke.apply(this, [key]);
if (this.getComponent() != null ) {
this.getComponent().componentInputMapChanged$javax_swing_ComponentInputMap(this);
}});

Clazz.newMeth(C$, 'clear', function () {
var oldSize = this.size();
C$.superclazz.prototype.clear.apply(this, []);
if (oldSize > 0 && this.getComponent() != null  ) {
this.getComponent().componentInputMapChanged$javax_swing_ComponentInputMap(this);
}});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:25
