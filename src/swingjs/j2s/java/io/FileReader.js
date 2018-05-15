(function(){var P$=java.io,I$=[['java.io.FileInputStream']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FileReader", null, 'java.io.InputStreamReader');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (fileName) {
C$.superclazz.c$$java_io_InputStream.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$S,[fileName])]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_File', function (file) {
C$.superclazz.c$$java_io_InputStream.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$java_io_File,[file])]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_FileDescriptor', function (fd) {
C$.superclazz.c$$java_io_InputStream.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$java_io_FileDescriptor,[fd])]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:05
