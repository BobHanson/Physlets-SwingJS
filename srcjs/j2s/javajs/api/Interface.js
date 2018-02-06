(function(){var P$=Clazz.newPackage("javajs.api"),I$=[];
var C$=Clazz.newClass(P$, "Interface");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getInterface$S', function (name) {
try {
var x = Clazz.forName(name);
return (x == null  ? null : x.newInstance());
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S("Interface.getInterface Error creating instance for " + name + ": \n" + e );
return null;
} else {
throw e;
}
}
}, 1);

Clazz.newMeth(C$, 'getInstanceWithParams$S$ClassA$OA', function (name, classes, params) {
try {
var cl = Clazz.forName(name);
return cl.getConstructor$ClassA(classes).newInstance$OA(params);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S("Interface.getInterfaceWithParams Error creating instance for " + name + ": \n" + e );
return null;
} else {
throw e;
}
}
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:59
