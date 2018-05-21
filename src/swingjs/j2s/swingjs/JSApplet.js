(function(){var P$=Clazz.newPackage("swingjs"),I$=[['swingjs.JSUtil']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSApplet", null, 'javax.swing.JApplet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['runMain$S$SA','runMain'], function (className, args) {
var theClass = null;
try {
theClass = Clazz.forName(className);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.ClassNotFoundException")){
System.out.println$S("Running main but cannot find class " + className);
(I$[1]||$incl$(1)).alert$O("Cannot find class " + className + " for running main(args)" );
e.printStackTrace();
return null;
} else {
throw e;
}
}
System.out.println$S("Running main(args) in class " + className);
{
setTimeout(function(){theClass.$clazz$.main.call(null, args || []);},1);
}
return theClass;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:13
