(function(){var P$=Clazz.newPackage("javax.swing"),I$=[];
var C$=Clazz.newClass(P$, "ClientPropertyKey", null, 'Enum');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$$Z, "JComponent_INPUT_VERIFIER", 0, [true]);
Clazz.newEnumConst($vals, C$.c$$Z, "JComponent_TRANSFER_HANDLER", 1, [true]);
Clazz.newEnumConst($vals, C$.c$$Z, "JComponent_ANCESTOR_NOTIFIER", 2, [true]);
Clazz.newEnumConst($vals, C$.c$$Z, "PopupFactory_FORCE_HEAVYWEIGHT_POPUP", 3, [true]);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$Z.apply(this, [false]);
}, 1);

Clazz.newMeth(C$, 'c$$Z', function (reportValueNotSerializable) {
C$.$init$.apply(this);
}, 1);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})();
//Created 2018-05-24 08:46:07
