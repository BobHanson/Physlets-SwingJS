(function(){var P$=Clazz.newPackage("swingjs"),I$=[['swingjs.JSImage','swingjs.JSUtil','swingjs.api.Interface']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSImagekit", null, null, 'java.awt.image.ImageConsumer');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.width = 0;
this.height = 0;
this.props = null;
this.colorModel = null;
this.hints = 0;
this.x = 0;
this.y = 0;
this.off = 0;
this.scansize = 0;
this.pixels = null;
this.jsimage = null;
this.pixelBytes = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'createImageFromBytes$BA$I$I$S', function (data, imageoffset, imagelength, name) {
return C$.createImageFromBytesStatic$BA$I$I$S(data, imageoffset, imagelength, name);
});

Clazz.newMeth(C$, 'imageComplete$I', function (status) {
this.jsimage = Clazz.new_((I$[1]||$incl$(1)).c$$IA$I$I$S,[this.pixels, this.width, this.height, null]);
});

Clazz.newMeth(C$, 'getCreatedImage', function () {
return this.jsimage;
});

Clazz.newMeth(C$, 'setDimensions$I$I', function (width, height) {
this.width = width;
this.height = height;
});

Clazz.newMeth(C$, 'setProperties$java_util_Hashtable', function (props) {
this.props = props;
});

Clazz.newMeth(C$, 'setColorModel$java_awt_image_ColorModel', function (model) {
this.colorModel = model;
});

Clazz.newMeth(C$, 'setHints$I', function (hintflags) {
this.hints = hintflags;
});

Clazz.newMeth(C$, 'setPixels$I$I$I$I$java_awt_image_ColorModel$IA$I$I', function (x, y, w, h, model, pixels, off, scansize) {
if (this.pixels == null ) {
this.colorModel = model;
this.pixels = Clazz.array(Integer.TYPE, [this.width * this.height]);
}for (var n = 0, j = y; n < h; n++, j++) {
for (var m = 0, i = x; m < w; m++, i++) {
var k = i + j * this.width;
this.pixels[k] = pixels[(j - y) * scansize + (i - x) + off];
}
}
});

Clazz.newMeth(C$, 'setPixels$I$I$I$I$java_awt_image_ColorModel$BA$I$I', function (x, y, w, h, model, pixels, off, scansize) {
this.colorModel = model;
this.width = w;
this.height = h;
this.x = x;
this.y = y;
this.off = off;
this.scansize = scansize;
this.pixelBytes = pixels;
(I$[2]||$incl$(2)).notImplemented$S("byte-based image pixels");
});

Clazz.newMeth(C$, 'createImageFromBytesStatic$BA$I$I$S', function (data, imageoffset, imagelength, name) {
var w = 0;
var h = 0;
var argb = null;
var b = null;
var type = null;
if (data == null ) {
w = imageoffset;
h = imagelength;
} else {
if (imagelength < 0) imagelength = data.length;
var n = imagelength - imageoffset;
System.arraycopy(data, imageoffset, b = Clazz.array(Byte.TYPE, [n]), 0, n);
if (b.length < 54) return null;
switch (C$.getSourceType$BA(b)) {
case 3:
var ie = (I$[3]||$incl$(3)).getInstance$S$Z("javajs.img.BMPDecoder", true);
var o = ie.decodeWindowsBMP$BA(b);
if (o == null  || o[0] == null  ) return null;
w = (o[1]).intValue();
h = (o[2]).intValue();
argb = o[0];
break;
case 1:
var pt = 2;
while (true){
switch (C$.getInt$BA$I(b, pt)) {
case 49407:
case 49919:
h = C$.getIntRev$BA$I(b, pt + 5);
w = C$.getIntRev$BA$I(b, pt + 7);
pt = 0;
break;
}
if (pt == 0) break;
pt = pt+(2 + C$.getIntRev$BA$I(b, pt + 2));
}
type = "jpeg";
break;
case 0:
w = C$.getLong$BA$I(b, 16);
h = C$.getLong$BA$I(b, 20);
type = "png";
break;
case 2:
w = C$.getInt$BA$I(b, 6);
h = C$.getInt$BA$I(b, 8);
type = "gif";
break;
case -1:
System.out.println$S("JSImagekit: Unknown image type: " + b[0] + " " + b[1] + " " + b[2] + " " + b[3] );
data = null;
break;
}
}if (w == 0 || h == 0 ) return null;
var jsimage = Clazz.new_((I$[1]||$incl$(1)).c$$IA$I$I$S,[argb, w, h, name]);
if (data != null  && argb == null  ) jsimage.getDOMImage$BA$S(b, type);
return jsimage;
}, 1);

Clazz.newMeth(C$, 'getLong$BA$I', function (b, pt) {
return ((b[pt] & 255) << 24) + ((b[pt + 1] & 255) << 16) + ((b[pt + 2] & 255) << 8) + (b[pt + 3] & 255) ;
}, 1);

Clazz.newMeth(C$, 'getInt$BA$I', function (b, pt) {
return (b[pt] & 255) + ((b[pt + 1] & 255) << 8);
}, 1);

Clazz.newMeth(C$, 'getIntRev$BA$I', function (b, pt) {
return ((b[pt] & 255) << 8) + (b[pt + 1] & 255);
}, 1);

Clazz.newMeth(C$, 'getSourceType$BA', function (b) {
return (b == null  ? -1 : (b[0] & 255) == 137 && b[1] == 80   && b[2] == 78   && b[3] == 71   ? 0 : (b[0] & 255) == 255 && (b[1] & 255) == 216  ? 1 : b[0] == 71  && b[1] == 73   && b[2] == 70   ? 2 : b[0] == 66  && b[1] == 77   ? 3 : -1);
}, 1);
})();
//Created 2018-05-15 01:03:15
