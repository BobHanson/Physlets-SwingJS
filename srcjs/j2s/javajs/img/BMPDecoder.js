(function(){var P$=Clazz.newPackage("javajs.img"),I$=[['javajs.util.Rdr']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "BMPDecoder");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.bis = null;
this.temp = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'decodeWindowsBMP$BA', function (bytes) {
try {
this.bis = (I$[1]||$incl$(1)).getBIS$BA(bytes);
this.temp = Clazz.array(Byte.TYPE, [4]);
if (p$.readByte.apply(this, []) != 66  || p$.readByte.apply(this, []) != 77  ) return null;
p$.readInt.apply(this, []);
p$.readShort.apply(this, []);
p$.readShort.apply(this, []);
p$.readInt.apply(this, []);
var imageWidth;
var imageHeight;
var bitsPerPixel;
var nColors = 0;
var imageSize = 0;
var headerSize = p$.readInt.apply(this, []);
switch (headerSize) {
case 12:
imageWidth = p$.readShort.apply(this, []);
imageHeight = p$.readShort.apply(this, []);
p$.readShort.apply(this, []);
bitsPerPixel = p$.readShort.apply(this, []);
break;
case 40:
imageWidth = p$.readInt.apply(this, []);
imageHeight = p$.readInt.apply(this, []);
p$.readShort.apply(this, []);
bitsPerPixel = p$.readShort.apply(this, []);
var ncompression = p$.readInt.apply(this, []);
if (ncompression != 0) {
System.out.println$S("BMP Compression is :" + ncompression + " -- aborting" );
return null;
}imageSize = p$.readInt.apply(this, []);
p$.readInt.apply(this, []);
p$.readInt.apply(this, []);
nColors = p$.readInt.apply(this, []);
p$.readInt.apply(this, []);
break;
default:
System.out.println$S("BMP Header unrecognized, length=" + headerSize + " -- aborting" );
return null;
}
var isYReversed = (imageHeight < 0);
if (isYReversed) imageHeight = -imageHeight;
var nPixels = imageHeight * imageWidth;
var bytesPerPixel = (bitsPerPixel/8|0);
nColors = (nColors > 0 ? nColors : 1 << bitsPerPixel);
var npad = (bytesPerPixel == 4 ? 0 : imageSize == 0 ? 4 - (imageWidth % 4) : ((imageSize/imageHeight|0)) - imageWidth * bytesPerPixel) % 4;
var palette;
var buf = Clazz.array(Integer.TYPE, [nPixels]);
var dpt = (isYReversed ? imageWidth : -imageWidth);
var pt0 = (isYReversed ? 0 : nPixels + dpt);
var pt1 = (isYReversed ? nPixels : dpt);
switch (bitsPerPixel) {
case 32:
case 24:
for (var pt = pt0; pt != pt1; pt = pt+(dpt), p$.pad$I.apply(this, [npad])) for (var i = 0; i < imageWidth; i++) buf[pt + i] = p$.readColor$I.apply(this, [bytesPerPixel]);


break;
case 8:
palette = Clazz.array(Integer.TYPE, [nColors]);
for (var i = 0; i < nColors; i++) palette[i] = p$.readColor$I.apply(this, [4]);

for (var pt = pt0; pt != pt1; pt = pt+(dpt), p$.pad$I.apply(this, [npad])) for (var i = 0; i < imageWidth; i++) buf[pt + i] = palette[p$.readByte.apply(this, [])];


break;
case 4:
npad = (4 - ((((imageWidth + 1)/2|0)) % 4)) % 4;
palette = Clazz.array(Integer.TYPE, [nColors]);
for (var i = 0; i < nColors; i++) palette[i] = p$.readColor$I.apply(this, [4]);

var b4 = 0;
for (var pt = pt0; pt != pt1; pt = pt+(dpt), p$.pad$I.apply(this, [npad])) for (var i = 0, shift = 4; i < imageWidth; i++, shift = 4 - shift) buf[pt + i] = palette[((shift == 4 ? (b4 = p$.readByte.apply(this, [])) : b4) >> shift) & 15];


break;
case 1:
var color1 = p$.readColor$I.apply(this, [3]);
var color2 = p$.readColor$I.apply(this, [3]);
npad = (4 - ((((imageWidth + 7)/8|0)) % 4)) % 4;
var b = 0;
for (var pt = pt0; pt != pt1; pt = pt+(dpt), p$.pad$I.apply(this, [npad])) for (var i = 0, bpt = -1; i < imageWidth; i++, bpt--) {
if (bpt < 0) {
b = p$.readByte.apply(this, []);
bpt = 7;
}buf[pt + i] = ((b & (1 << bpt)) == 0 ? color1 : color2);
}

break;
case 64:
case 2:
default:
System.out.println$S("Not a 32-, 24-, 8-, 4-, or 1-bit Windows Bitmap, aborting...");
return null;
}
return Clazz.array(java.lang.Object, -1, [buf, Integer.$valueOf(imageWidth), Integer.$valueOf(imageHeight)]);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S("Caught exception in loadbitmap!");
} else {
throw e;
}
}
return null;
});

Clazz.newMeth(C$, 'pad$I', function (npad) {
for (var i = 0; i < npad; i++) p$.readByte.apply(this, []);

return true;
});

Clazz.newMeth(C$, 'readColor$I', function (n) {
this.bis.read$BA$I$I(this.temp, 0, n);
return -16777216 | ((this.temp[2] & 255) << 16) | ((this.temp[1] & 255) << 8) | this.temp[0] & 255;
});

Clazz.newMeth(C$, 'readInt', function () {
this.bis.read$BA$I$I(this.temp, 0, 4);
return ((this.temp[3] & 255) << 24) | ((this.temp[2] & 255) << 16) | ((this.temp[1] & 255) << 8) | this.temp[0] & 255 ;
});

Clazz.newMeth(C$, 'readShort', function () {
this.bis.read$BA$I$I(this.temp, 0, 2);
return ((this.temp[1] & 255) << 8) | this.temp[0] & 255;
});

Clazz.newMeth(C$, 'readByte', function () {
this.bis.read$BA$I$I(this.temp, 0, 1);
return this.temp[0] & 255;
});
})();
//Created 2018-02-06 08:59:00
