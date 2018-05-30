(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[];
var C$=Clazz.newClass(P$, "JSCheckBoxMenuItemUI", null, 'swingjs.plaf.JSCheckBoxUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.isMenuItem=true;
}, 1);

Clazz.newMeth(C$, 'getPropertyPrefix', function () {
return "CheckBoxMenuItem.";
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.menuItem=jc;
C$.superclazz.prototype.installUI$javax_swing_JComponent.apply(this, [jc]);
});
})();
//Created 2018-05-24 08:47:52
