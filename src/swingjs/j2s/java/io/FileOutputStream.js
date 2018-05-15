(function(){var P$=java.io,I$=[['java.io.File','javajs.util.OC']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FileOutputStream", null, 'java.io.OutputStream');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.out = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.c$$java_io_File$Z.apply(this, [name != null  ? Clazz.new_((I$[1]||$incl$(1)).c$$S,[name]) : null, false]);
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (name, append) {
C$.c$$java_io_File$Z.apply(this, [name != null  ? Clazz.new_((I$[1]||$incl$(1)).c$$S,[name]) : null, append]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_File', function (file) {
C$.c$$java_io_File$Z.apply(this, [file, false]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_File$Z', function (file, append) {
Clazz.super_(C$, this,1);
var name = (file != null  ? file.getPath() : null);
if (name == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (append) {
p$.openAppend$S.apply(this, [name]);
} else {
p$.open$S.apply(this, [name]);
}}, 1);

Clazz.newMeth(C$, 'c$$java_io_FileDescriptor', function (fdObj) {
C$.c$$java_io_File$Z.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$S,["output"]), false]);
}, 1);

Clazz.newMeth(C$, 'open$S', function (name) {
this.out = Clazz.new_((I$[2]||$incl$(2)));
this.out.setParams$javajs_api_BytePoster$S$Z$java_io_OutputStream(null, name, false, null);
});

Clazz.newMeth(C$, 'openAppend$S', function (name) {
System.out.println$S("FileOutputStream disabled -- no JSToolkit");
});

Clazz.newMeth(C$, 'writeBytes$BA$I$I', function (b, off, len) {
this.out.write$BA$I$I(b, off, len);
});

Clazz.newMeth(C$, 'write$I', function (b) {
this.out.writeByteAsInt$I(b);
});

Clazz.newMeth(C$, 'write$BA', function (b) {
p$.writeBytes$BA$I$I.apply(this, [b, 0, b.length]);
});

Clazz.newMeth(C$, 'write$BA$I$I', function (b, off, len) {
p$.writeBytes$BA$I$I.apply(this, [b, off, len]);
});

Clazz.newMeth(C$, 'close', function () {
this.out.closeChannel();
});

Clazz.newMeth(C$, 'finalize', function () {
this.close();
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:05
