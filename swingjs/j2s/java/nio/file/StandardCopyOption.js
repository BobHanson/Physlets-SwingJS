(function(){var P$=Clazz.newPackage("java.nio.file"),I$=[];
var C$=Clazz.newClass(P$, "StandardCopyOption", null, 'Enum', 'java.nio.file.CopyOption');

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "REPLACE_EXISTING", 0, []);
Clazz.newEnumConst($vals, C$.c$, "COPY_ATTRIBUTES", 1, []);
Clazz.newEnumConst($vals, C$.c$, "ATOMIC_MOVE", 2, []);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values$', function() { return $vals }, 1);
Clazz.newMeth(C$, 'valueOf$S', function(name) { for (var val in $vals){ if ($vals[val].name == name) return $vals[val]} return null }, 1);
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 21:47:00 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
