(function(){var P$=Clazz.newPackage("javax.swing"),I$=[];
var C$=Clazz.newClass(P$, "JRadioButton", null, 'javax.swing.JToggleButton');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$S$javax_swing_Icon$Z.apply(this, [null, null, false]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_Icon', function (icon) {
C$.c$$S$javax_swing_Icon$Z.apply(this, [null, icon, false]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_Action', function (a) {
C$.c$.apply(this, []);
this.setAction$javax_swing_Action(a);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_Icon$Z', function (icon, selected) {
C$.c$$S$javax_swing_Icon$Z.apply(this, [null, icon, selected]);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (text) {
C$.c$$S$javax_swing_Icon$Z.apply(this, [text, null, false]);
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (text, selected) {
C$.c$$S$javax_swing_Icon$Z.apply(this, [text, null, selected]);
}, 1);

Clazz.newMeth(C$, 'c$$S$javax_swing_Icon', function (text, icon) {
C$.c$$S$javax_swing_Icon$Z.apply(this, [text, icon, false]);
}, 1);

Clazz.newMeth(C$, 'c$$S$javax_swing_Icon$Z', function (text, icon, selected) {
C$.superclazz.c$$S$javax_swing_Icon$Z$S.apply(this, [text, icon, selected, "RadioButtonUI"]);
C$.$init$.apply(this);
this.setBorderPainted$Z(false);
this.setHorizontalAlignment$I(10);
}, 1);

Clazz.newMeth(C$, 'setIconFromAction$javax_swing_Action', function (a) {
});

Clazz.newMeth(C$, 'paramString', function () {
return C$.superclazz.prototype.paramString.apply(this, []);
});
})();
//Created 2018-02-06 08:59:32
