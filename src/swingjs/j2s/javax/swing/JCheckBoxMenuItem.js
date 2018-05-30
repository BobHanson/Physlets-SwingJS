(function(){var P$=Clazz.newPackage("javax.swing"),I$=[[['javax.swing.JToggleButton','.ToggleButtonModel']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JCheckBoxMenuItem", null, 'javax.swing.JMenuItem', 'javax.swing.SwingConstants');

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

Clazz.newMeth(C$, 'c$$S$Z', function (text, b) {
C$.c$$S$javax_swing_Icon$Z.apply(this, [text, null, b]);
}, 1);

Clazz.newMeth(C$, 'c$$S$javax_swing_Icon$Z', function (text, icon, b) {
C$.superclazz.c$$S$javax_swing_Icon$S.apply(this, [text, icon, "CheckBoxMenuItemUI"]);
C$.$init$.apply(this);
this.setModel$javax_swing_ButtonModel(Clazz.new_((I$[1]||$incl$(1))));
this.setSelected$Z(b);
this.setFocusable$Z(false);
}, 1);

Clazz.newMeth(C$, 'getState', function () {
return this.isSelected();
});

Clazz.newMeth(C$, 'setState$Z', function (b) {
this.setSelected$Z(b);
});

Clazz.newMeth(C$, 'getSelectedObjects', function () {
if (this.isSelected() == false ) return null;
var selectedObjects = Clazz.array(java.lang.Object, [1]);
selectedObjects[0]=this.getText();
return selectedObjects;
});

Clazz.newMeth(C$, 'paramString', function () {
return C$.superclazz.prototype.paramString.apply(this, []);
});

Clazz.newMeth(C$, 'shouldUpdateSelectedStateFromAction', function () {
return true;
});
})();
//Created 2018-05-24 08:46:12
