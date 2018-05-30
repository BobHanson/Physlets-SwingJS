(function(){var P$=Clazz.newPackage("java.awt"),I$=[];
var C$=Clazz.newInterface(P$, "EventFilter");
;
(function(){var C$=Clazz.newClass(P$.EventFilter, "FilterAction", null, 'Enum');

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "ACCEPT", 0, []);
Clazz.newEnumConst($vals, C$.c$, "REJECT", 1, []);
Clazz.newEnumConst($vals, C$.c$, "ACCEPT_IMMEDIATELY", 2, []);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})()
})();
//Created 2018-05-24 08:45:08
