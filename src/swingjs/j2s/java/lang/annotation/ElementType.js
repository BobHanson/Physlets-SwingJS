(function(){var P$=java.lang.annotation,I$=[];
var C$=Clazz.newClass(P$, "ElementType", null, 'Enum');

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "TYPE", 0, []);
Clazz.newEnumConst($vals, C$.c$, "FIELD", 1, []);
Clazz.newEnumConst($vals, C$.c$, "METHOD", 2, []);
Clazz.newEnumConst($vals, C$.c$, "PARAMETER", 3, []);
Clazz.newEnumConst($vals, C$.c$, "CONSTRUCTOR", 4, []);
Clazz.newEnumConst($vals, C$.c$, "LOCAL_VARIABLE", 5, []);
Clazz.newEnumConst($vals, C$.c$, "ANNOTATION_TYPE", 6, []);
Clazz.newEnumConst($vals, C$.c$, "PACKAGE", 7, []);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})();
//Created 2018-05-24 08:45:39
