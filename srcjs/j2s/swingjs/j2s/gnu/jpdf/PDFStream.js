(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[['java.io.ByteArrayOutputStream','java.io.PrintWriter','java.util.zip.DeflaterOutputStream']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PDFStream", null, 'gnu.jpdf.PDFObject', 'java.io.Serializable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.buf = null;
this.deflate = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$S.apply(this, [null]);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (type) {
C$.superclazz.c$$S.apply(this, [type]);
C$.$init$.apply(this);
this.buf = Clazz.new_((I$[1]||$incl$(1)));
this.deflate = false;
}, 1);

Clazz.newMeth(C$, 'setDeflate$Z', function (mode) {
this.deflate = mode;
});

Clazz.newMeth(C$, 'getDeflate', function () {
return this.deflate;
});

Clazz.newMeth(C$, 'getOutputStream', function () {
return this.buf;
});

Clazz.newMeth(C$, 'getWriter', function () {
return Clazz.new_((I$[2]||$incl$(2)).c$$java_io_OutputStream$Z,[this.buf, true]);
});

Clazz.newMeth(C$, 'getStream', function () {
return this.buf;
});

Clazz.newMeth(C$, 'write$java_io_OutputStream', function (os) {
this.writeStart$java_io_OutputStream(os);
this.writeStream$java_io_OutputStream(os);
});

Clazz.newMeth(C$, 'writeStream$java_io_OutputStream', function (os) {
if (this.deflate) {
var b = Clazz.new_((I$[1]||$incl$(1)));
var dos = Clazz.new_((I$[3]||$incl$(3)).c$$java_io_OutputStream,[b]);
this.buf.writeTo$java_io_OutputStream(dos);
dos.finish();
dos.close();
C$.write$java_io_OutputStream$S(os, "/Filter /FlateDecode\u000a");
C$.write$java_io_OutputStream$S(os, "/Length ");
C$.write$java_io_OutputStream$S(os, Integer.toString(b.size() + 1));
C$.write$java_io_OutputStream$S(os, "\u000a>>\u000astream\u000a");
b.writeTo$java_io_OutputStream(os);
C$.write$java_io_OutputStream$S(os, "\u000a");
} else {
C$.write$java_io_OutputStream$S(os, "/Length ");
C$.write$java_io_OutputStream$S(os, Integer.toString(this.buf.size()));
C$.write$java_io_OutputStream$S(os, "\u000a>>\u000astream\u000a");
this.buf.writeTo$java_io_OutputStream(os);
}C$.write$java_io_OutputStream$S(os, "endstream\u000aendobj\u000a");
});

Clazz.newMeth(C$, 'write$java_io_OutputStream$S', function (os, s) {
var b = s.getBytes();
os.write$BA$I$I(b, 0, b.length);
}, 1);

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (out) {
out.writeInt$I(this.buf.size());
out.write$BA$I$I(this.buf.toByteArray(), 0, this.buf.size());
});

Clazz.newMeth(C$, 'readObject$java_io_ObjectInputStream', function ($in) {
var l = $in.readInt();
var b = Clazz.array(Byte.TYPE, [l]);
$in.read$BA$I$I(b, 0, l);
this.buf = Clazz.new_((I$[1]||$incl$(1)).c$$I,[l]);
this.buf.write$BA(b);
});
})();
//Created 2018-02-08 10:01:44
