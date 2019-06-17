(function(){var P$=Clazz.newPackage("swingjs"),I$=[[0,'swingjs.JSUtil']],$I$=function(i){return I$[i]||(I$[i]=Clazz.load(I$[0][i]))};
var C$=Clazz.newClass(P$, "JSDummyApplet", null, 'javax.swing.JApplet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['runMain$swingjs_JSAppletViewer$SA','runMain'], function (v, args) {
var className=v.main;
var theClass=null;
try {
theClass=Clazz.forName(className);
} catch (e) {
if (Clazz.exceptionOf(e,"ClassNotFoundException")){
System.out.println$S("Running main but cannot find class " + className);
$I$(1).alert$O("Cannot find class " + className + " for running main(args)" );
e.printStackTrace$();
return null;
} else {
throw e;
}
}
System.out.println$S("Running main(args) in class " + className);

setTimeout(function(){ theClass.$clazz$.main$SA.call(null, args || []);
$I$(1).readyCallback$S$S$java_awt_JSComponent$swingjs_JSAppletViewer(v.appletName, v.fullName, v.applet, v);

},1);
return theClass;
});

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 22:44:06 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
