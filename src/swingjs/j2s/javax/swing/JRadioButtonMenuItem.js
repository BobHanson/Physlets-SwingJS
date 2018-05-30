(function(){var P$=Clazz.newPackage("javax.swing"),I$=[[['javax.swing.JToggleButton','.ToggleButtonModel']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JRadioButtonMenuItem", null, 'javax.swing.JMenuItem');

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

Clazz.newMeth(C$, 'c$$S', function (text) {
C$.c$$S$javax_swing_Icon$Z.apply(this, [text, null, false]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_Action', function (a) {
C$.c$.apply(this, []);
this.setAction$javax_swing_Action(a);
}, 1);

Clazz.newMeth(C$, 'c$$S$javax_swing_Icon', function (text, icon) {
C$.c$$S$javax_swing_Icon$Z.apply(this, [text, icon, false]);
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (text, selected) {
C$.c$$S.apply(this, [text]);
this.setSelected$Z(selected);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_Icon$Z', function (icon, selected) {
C$.c$$S$javax_swing_Icon$Z.apply(this, [null, icon, selected]);
}, 1);

Clazz.newMeth(C$, 'c$$S$javax_swing_Icon$Z', function (text, icon, selected) {
C$.superclazz.c$$S$javax_swing_Icon$S.apply(this, [text, icon, "RadioButtonMenuItemUI"]);
C$.$init$.apply(this);
this.setModel$javax_swing_ButtonModel(Clazz.new_((I$[1]||$incl$(1))));
this.setSelected$Z(selected);
this.setFocusable$Z(false);
}, 1);

Clazz.newMeth(C$, 'shouldUpdateSelectedStateFromAction', function () {
return true;
});
})();
//Created 2018-05-24 08:46:21
