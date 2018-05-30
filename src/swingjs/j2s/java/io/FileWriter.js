(function(){var P$=java.io,I$=[['java.io.FileOutputStream']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FileWriter", null, 'java.io.OutputStreamWriter');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (fileName) {
C$.superclazz.c$$java_io_OutputStream.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$S,[fileName])]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (fileName, append) {
C$.superclazz.c$$java_io_OutputStream.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$S$Z,[fileName, append])]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_File', function (file) {
C$.superclazz.c$$java_io_OutputStream.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$java_io_File,[file])]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_File$Z', function (file, append) {
C$.superclazz.c$$java_io_OutputStream.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$java_io_File$Z,[file, append])]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:34
