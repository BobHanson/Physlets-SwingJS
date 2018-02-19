(function(){var P$=Clazz.newPackage("a2s"),I$=[];
var C$=Clazz.newClass(P$, "CheckboxGroup", null, 'javax.swing.ButtonGroup');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'getSelectedCheckbox', function () {
for (var e = this.getElements(); e.hasMoreElements(); ) {
var ab = e.nextElement();
if (ab.isSelected()) return ab;
}
return null;
});
})();
//Created 2018-02-08 10:01:39
