(function(){var P$=Clazz.newPackage("org.apache.tools.bzip2"),I$=[[0,'org.apache.tools.bzip2.CBZip2InputStream']],$I$=function(i){return I$[i]||(I$[i]=Clazz.load(I$[0][i]))};
var C$=Clazz.newClass(P$, "CBZip2InputStreamFactory");

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getStream$java_io_InputStream', function (is) {
is.read$BA$I$I(Clazz.array(Byte.TYPE, [2]), 0, 2);
return Clazz.new_($I$(1).c$$java_io_InputStream,[is]);
});

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 21:47:40 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
