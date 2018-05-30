(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[['java.lang.StringBuffer','java.util.Vector','java.io.ByteArrayOutputStream','java.util.zip.DeflaterOutputStream','gnu.jpdf.PDFStream','java.awt.image.PixelGrabber']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PDFImage", null, 'gnu.jpdf.PDFStream', ['java.awt.image.ImageObserver', 'java.io.Serializable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.objwidth = 0;
this.objheight = 0;
this.width = 0;
this.height = 0;
this.img = null;
this.name = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["/XObject"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Image', function (img) {
C$.c$.apply(this, []);
this.setImage$java_awt_Image$I$I$I$I$java_awt_image_ImageObserver(img, 0, 0, img.getWidth$java_awt_image_ImageObserver(this), img.getHeight$java_awt_image_ImageObserver(this), this);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Image$I$I$I$I$java_awt_image_ImageObserver', function (img, x, y, w, h, obs) {
C$.c$.apply(this, []);
this.objwidth=w;
this.objheight=h;
this.setImage$java_awt_Image$I$I$I$I$java_awt_image_ImageObserver(img, x, y, img.getWidth$java_awt_image_ImageObserver(this), img.getHeight$java_awt_image_ImageObserver(this), obs);
}, 1);

Clazz.newMeth(C$, 'getWidth', function () {
return this.width;
});

Clazz.newMeth(C$, 'setWidth$I', function (v) {
this.width=v;
});

Clazz.newMeth(C$, 'getHeight', function () {
return this.height;
});

Clazz.newMeth(C$, 'setHeight$I', function (v) {
this.height=v;
});

Clazz.newMeth(C$, 'setName$S', function (n) {
this.name=n;
});

Clazz.newMeth(C$, 'getName', function () {
return this.name;
});

Clazz.newMeth(C$, 'setImage$java_awt_Image$I$I$I$I$java_awt_image_ImageObserver', function (img, x, y, w, h, obs) {
this.img=img;
this.width=w;
this.height=h;
});

Clazz.newMeth(C$, 'base85Encoding$S', function (stringToEncode) {
if ((stringToEncode == null ) || (stringToEncode.length$() == 0) ) {
return "";
}if ((stringToEncode.length$() > 8) || ((stringToEncode.length$() % 2) != 0) ) {
System.out.println$S("PDFImage.base85Encoding, Incorrect tuple length: " + stringToEncode.length$());
return "";
}var sb = Clazz.new_((I$[1]||$incl$(1)));
var numHexDigits = (stringToEncode.length$()/2|0);
var numAppendBytes = 4 - numHexDigits;
for (var i = 0; i < numAppendBytes; i++) {
stringToEncode += "00";
}
var digitVector = Clazz.new_((I$[2]||$incl$(2)));
var number = Long.parseLong(stringToEncode, 16);
var remainder = 0;
while (number >= 85){
remainder=((number % 85)|0);
number=(number/85|0);
digitVector.add$I$TE(0,  new Integer(remainder));
}
digitVector.add$I$TE(0,  new Integer((number|0)));
for (var i = 0; i < digitVector.size(); i++) {
var c = String.fromCharCode(((digitVector.elementAt$I(i)).intValue() + 33));
sb.append$C(c);
}
var tuple = sb.toString();
var len = tuple.length$();
switch (len) {
case 1:
tuple="!!!!" + tuple;
break;
case 2:
tuple="!!!" + tuple;
break;
case 3:
tuple="!!" + tuple;
break;
case 4:
tuple="!" + tuple;
break;
default:
break;
}
return (tuple);
});

Clazz.newMeth(C$, 'writeStream$java_io_OutputStream', function (os) {
var b = Clazz.new_((I$[3]||$incl$(3)));
var dos = Clazz.new_((I$[4]||$incl$(4)).c$$java_io_OutputStream,[b]);
this.buf.writeTo$java_io_OutputStream(dos);
dos.finish();
dos.close();
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, "/Filter [/FlateDecode /ASCII85Decode]\u000a");
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, "/Length ");
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, Integer.toString(b.size()));
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, "\u000a>>\u000astream\u000a");
b.writeTo$java_io_OutputStream(os);
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, "\u000aendstream\u000aendobj\u000a");
});

Clazz.newMeth(C$, 'write$java_io_OutputStream', function (os) {
this.writeStart$java_io_OutputStream(os);
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, "/Subtype /Image\u000a/Name ");
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, this.name);
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, "\u000a/Width ");
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, Integer.toString(this.width));
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, "\u000a/Height ");
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, Integer.toString(this.height));
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(os, "\u000a/BitsPerComponent 8\u000a/ColorSpace /DeviceRGB\u000a");
var bos = this.getStream();
var w = this.width;
var h = this.height;
var x = 0;
var y = 0;
var pixels = Clazz.array(Integer.TYPE, [w * h]);
var pg = Clazz.new_((I$[6]||$incl$(6)).c$$java_awt_Image$I$I$I$I$IA$I$I,[this.img, x, y, w, h, pixels, 0, w]);
try {
pg.grabPixels();
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.InterruptedException")){
System.err.println$S("interrupted waiting for pixels!");
return;
} else {
throw e;
}
}
if ((pg.getStatus() & 128) != 0) {
System.err.println$S("image fetch aborted or errored");
return;
}var out = Clazz.new_((I$[1]||$incl$(1)));
for (var j = 0; j < h; j++) {
for (var i = 0; i < w; i++) {
out.append$S(C$.handlePixel$I$I$I(x + i, y + j, pixels[j * w + i]));
if (out.toString().length$() >= 8) {
var tuple = out.substring$I$I(0, 8);
out.$delete$I$I(0, 8);
var encTuple = p$.base85Encoding$S.apply(this, [tuple]);
if (encTuple.equals$O("!!!!!")) {
encTuple="z";
}(I$[5]||$incl$(5)).write$java_io_OutputStream$S(bos, encTuple);
}}
}
var lastTuple = p$.base85Encoding$S.apply(this, [out.toString()]);
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(bos, lastTuple);
(I$[5]||$incl$(5)).write$java_io_OutputStream$S(bos, "~");
this.setDeflate$Z(false);
this.writeStream$java_io_OutputStream(os);
});

Clazz.newMeth(C$, 'handlePixel$I$I$I', function (x, y, p) {
var alpha = (p >> 24) & 255;
var red = (p >> 16) & 255;
var green = (p >> 8) & 255;
var blue = (p) & 255;
var redHex = Integer.toHexString(red);
var greenHex = Integer.toHexString(green);
var blueHex = Integer.toHexString(blue);
if (redHex.length$() == 1) {
redHex="0" + redHex;
}if (greenHex.length$() == 1) {
greenHex="0" + greenHex;
}if (blueHex.length$() == 1) {
blueHex="0" + blueHex;
}return redHex + greenHex + blueHex ;
}, 1);

Clazz.newMeth(C$, 'imageUpdate$java_awt_Image$I$I$I$I$I', function (img, infoflags, x, y, w, h) {
System.err.println$S("img=" + img + "\ninfoflags=" + infoflags + "\nx=" + x + " y=" + y + " w=" + w + " h=" + h );
if (infoflags == 1) this.width=w;
if (infoflags == 2) this.height=h;
return false;
});
})();
//Created 2018-05-24 08:45:03
