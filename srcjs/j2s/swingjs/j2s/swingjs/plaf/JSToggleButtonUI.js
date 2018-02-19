(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.plaf.JSComponentUI','javax.swing.UIManager']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSToggleButtonUI", null, 'swingjs.plaf.JSButtonUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getPropertyPrefix', function () {
return "ToggleButton.";
});

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
if ((I$[1]||$incl$(1)).debugging) System.out.println$S(this.id + " stateChange " + this.dumpEvent$java_util_EventObject(e) );
var model = this.button.getModel();
this.setBackground$java_awt_Color(model.isArmed() && model.isPressed()  || model.isSelected()  ? (I$[2]||$incl$(2)).getColor$O(this.getPropertyPrefix() + "highlight") : this.button.getBackground());
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:29
