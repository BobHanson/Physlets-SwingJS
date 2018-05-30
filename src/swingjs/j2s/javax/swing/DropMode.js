(function(){var P$=Clazz.newPackage("javax.swing"),I$=[];
var C$=Clazz.newClass(P$, "DropMode", null, 'Enum');

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "USE_SELECTION", 0, []);
Clazz.newEnumConst($vals, C$.c$, "ON", 1, []);
Clazz.newEnumConst($vals, C$.c$, "INSERT", 2, []);
Clazz.newEnumConst($vals, C$.c$, "INSERT_ROWS", 3, []);
Clazz.newEnumConst($vals, C$.c$, "INSERT_COLS", 4, []);
Clazz.newEnumConst($vals, C$.c$, "ON_OR_INSERT", 5, []);
Clazz.newEnumConst($vals, C$.c$, "ON_OR_INSERT_ROWS", 6, []);
Clazz.newEnumConst($vals, C$.c$, "ON_OR_INSERT_COLS", 7, []);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})();
//Created 2018-05-24 08:46:10
