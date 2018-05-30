(function(){var P$=Clazz.newPackage("org.apache.tools.bzip2"),I$=[['org.apache.tools.bzip2.CBZip2InputStream']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "CBZip2InputStreamFactory");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getStream$java_io_InputStream', function (is) {
is.read$BA$I$I(Clazz.array(Byte.TYPE, [2]), 0, 2);
return Clazz.new_((I$[1]||$incl$(1)).c$$java_io_InputStream,[is]);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:16
