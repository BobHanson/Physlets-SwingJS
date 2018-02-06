(function(){var P$=Clazz.newPackage("sun.java2d"),I$=[];
var C$=Clazz.newInterface(P$, "StateTrackable");
;
(function(){var C$=Clazz.newClass(P$.StateTrackable, "State", null, 'Enum');

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "IMMUTABLE", 0, []);
Clazz.newEnumConst($vals, C$.c$, "STABLE", 1, []);
Clazz.newEnumConst($vals, C$.c$, "DYNAMIC", 2, []);
Clazz.newEnumConst($vals, C$.c$, "UNTRACKABLE", 3, []);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})()
})();
//Created 2018-02-06 09:00:20
