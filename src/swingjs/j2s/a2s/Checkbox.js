(function(){var P$=Clazz.newPackage("a2s"),I$=[['javax.swing.JRadioButton','a2s.A2SEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Checkbox", null, 'javax.swing.JCheckBox');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'newRadioButton$S$javax_swing_ButtonGroup$Z', function (string, bg, b) {
var rb = Clazz.new_((I$[1]||$incl$(1)).c$$S$Z,[string, b]);
bg.add$javax_swing_AbstractButton(rb);
return rb;
}, 1);

Clazz.newMeth(C$, 'c$$S', function (string) {
C$.superclazz.c$$S$Z.apply(this, [string, false]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (string, b) {
C$.superclazz.c$$S$Z.apply(this, [string, b]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$Z$a2s_CheckboxGroup', function (label, state, group) {
Clazz.super_(C$, this,1);
this.setLabel$S(label);
this.setState$Z(state);
if (group != null ) group.add$javax_swing_AbstractButton(this);
if (state && (group != null ) ) {
this.setSelected$Z(state);
}}, 1);

Clazz.newMeth(C$, 'c$$S$a2s_CheckboxGroup$Z', function (label, group, state) {
C$.c$$S$Z$a2s_CheckboxGroup.apply(this, [label, state, group]);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getState', function () {
return this.isSelected();
});

Clazz.newMeth(C$, 'setState$Z', function (b) {
this.setSelected$Z(b);
});

Clazz.newMeth(C$, 'setCheckboxGroup$a2s_CheckboxGroup', function (group) {
if (group != null ) group.add$javax_swing_AbstractButton(this);
});

Clazz.newMeth(C$, 'fireActionPerformed$java_awt_event_ActionEvent', function (event) {
(I$[2]||$incl$(2)).addListener$javax_swing_JComponent$java_awt_Component(null, this);
C$.superclazz.prototype.fireActionPerformed$java_awt_event_ActionEvent.apply(this, [event]);
});
})();
//Created 2018-05-24 08:44:59
