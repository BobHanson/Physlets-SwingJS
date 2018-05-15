(function(){var P$=Clazz.newPackage("java.awt.image"),I$=[['java.awt.color.ColorSpace','java.awt.image.ColorModel','java.awt.image.Raster','java.awt.image.MultiPixelPackedSampleModel','java.awt.image.ComponentSampleModel','java.awt.image.DirectColorModel','java.awt.image.DataBuffer','java.awt.image.BufferedImage']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "IndexColorModel", null, 'java.awt.image.ColorModel');
C$.opaqueBits = null;
C$.alphaBits = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.opaqueBits = Clazz.array(Integer.TYPE, -1, [8, 8, 8]);
C$.alphaBits = Clazz.array(Integer.TYPE, -1, [8, 8, 8, 8]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.rgb = null;
this.map_size = 0;
this.pixel_mask = 0;
this.transparent_index = 0;
this.allgrayopaque = false;
this.lookupcache = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.transparent_index = -1;
this.lookupcache = Clazz.array(Integer.TYPE, [40]);
}, 1);

Clazz.newMeth(C$, 'c$$I$I$BA$BA$BA', function (bits, size, r, g, b) {
C$.superclazz.c$$I$IA$java_awt_color_ColorSpace$Z$Z$I$I.apply(this, [bits, C$.opaqueBits, (I$[1]||$incl$(1)).getInstance$I(1000), false, false, 1, (I$[2]||$incl$(2)).getDefaultTransferType$I(bits)]);
C$.$init$.apply(this);
if (bits < 1 || bits > 16 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Number of bits must be between 1 and 16."]);
}p$.setRGBs$I$BA$BA$BA$BA.apply(this, [size, r, g, b, null]);
p$.calculatePixelMask.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'c$$I$I$BA$BA$BA$I', function (bits, size, r, g, b, trans) {
C$.superclazz.c$$I$IA$java_awt_color_ColorSpace$Z$Z$I$I.apply(this, [bits, C$.opaqueBits, (I$[1]||$incl$(1)).getInstance$I(1000), false, false, 1, (I$[2]||$incl$(2)).getDefaultTransferType$I(bits)]);
C$.$init$.apply(this);
if (bits < 1 || bits > 16 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Number of bits must be between 1 and 16."]);
}p$.setRGBs$I$BA$BA$BA$BA.apply(this, [size, r, g, b, null]);
p$.setTransparentPixel$I.apply(this, [trans]);
p$.calculatePixelMask.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'c$$I$I$BA$BA$BA$BA', function (bits, size, r, g, b, a) {
C$.superclazz.c$$I$IA$java_awt_color_ColorSpace$Z$Z$I$I.apply(this, [bits, C$.alphaBits, (I$[1]||$incl$(1)).getInstance$I(1000), true, false, 3, (I$[2]||$incl$(2)).getDefaultTransferType$I(bits)]);
C$.$init$.apply(this);
if (bits < 1 || bits > 16 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Number of bits must be between 1 and 16."]);
}p$.setRGBs$I$BA$BA$BA$BA.apply(this, [size, r, g, b, a]);
p$.calculatePixelMask.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'c$$I$I$BA$I$Z', function (bits, size, cmap, start, hasalpha) {
C$.c$$I$I$BA$I$Z$I.apply(this, [bits, size, cmap, start, hasalpha, -1]);
if (bits < 1 || bits > 16 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Number of bits must be between 1 and 16."]);
}}, 1);

Clazz.newMeth(C$, 'c$$I$I$BA$I$Z$I', function (bits, size, cmap, start, hasalpha, trans) {
C$.superclazz.c$$I$IA$java_awt_color_ColorSpace$Z$Z$I$I.apply(this, [bits, C$.opaqueBits, (I$[1]||$incl$(1)).getInstance$I(1000), false, false, 1, (I$[2]||$incl$(2)).getDefaultTransferType$I(bits)]);
C$.$init$.apply(this);
if (bits < 1 || bits > 16 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Number of bits must be between 1 and 16."]);
}if (size < 1) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Map size (" + size + ") must be >= 1" ]);
}this.map_size = size;
this.rgb = Clazz.array(Integer.TYPE, [p$.calcRealMapSize$I$I.apply(this, [bits, size])]);
var j = start;
var alpha = 255;
var allgray = true;
var transparency = 1;
for (var i = 0; i < size; i++) {
var r = cmap[j++] & 255;
var g = cmap[j++] & 255;
var b = cmap[j++] & 255;
allgray = allgray && (r == g) && (g == b)  ;
if (hasalpha) {
alpha = cmap[j++] & 255;
if (alpha != 255) {
if (alpha == 0) {
if (transparency == 1) {
transparency = 2;
}if (this.transparent_index < 0) {
this.transparent_index = i;
}} else {
transparency = 3;
}allgray = false;
}}this.rgb[i] = (alpha << 24) | (r << 16) | (g << 8) | b ;
}
this.allgrayopaque = allgray;
p$.setTransparency$I.apply(this, [transparency]);
p$.setTransparentPixel$I.apply(this, [trans]);
p$.calculatePixelMask.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'c$$I$I$IA$I$Z$I$I', function (bits, size, cmap, start, hasalpha, trans, transferType) {
C$.superclazz.c$$I$IA$java_awt_color_ColorSpace$Z$Z$I$I.apply(this, [bits, C$.opaqueBits, (I$[1]||$incl$(1)).getInstance$I(1000), false, false, 1, transferType]);
C$.$init$.apply(this);
if (bits < 1 || bits > 16 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Number of bits must be between 1 and 16."]);
}if (size < 1) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Map size (" + size + ") must be >= 1" ]);
}if ((transferType != 0) && (transferType != 1) ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["transferType must be eitherDataBuffer.TYPE_BYTE or DataBuffer.TYPE_USHORT"]);
}p$.setRGBs$I$IA$I$Z.apply(this, [size, cmap, start, hasalpha]);
p$.setTransparentPixel$I.apply(this, [trans]);
p$.calculatePixelMask.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'setRGBs$I$BA$BA$BA$BA', function (size, r, g, b, a) {
if (size < 1) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Map size (" + size + ") must be >= 1" ]);
}this.map_size = size;
this.rgb = Clazz.array(Integer.TYPE, [p$.calcRealMapSize$I$I.apply(this, [this.pixel_bits, size])]);
var alpha = 255;
var transparency = 1;
var allgray = true;
for (var i = 0; i < size; i++) {
var rc = r[i] & 255;
var gc = g[i] & 255;
var bc = b[i] & 255;
allgray = allgray && (rc == gc) && (gc == bc)  ;
if (a != null ) {
alpha = a[i] & 255;
if (alpha != 255) {
if (alpha == 0) {
if (transparency == 1) {
transparency = 2;
}if (this.transparent_index < 0) {
this.transparent_index = i;
}} else {
transparency = 3;
}allgray = false;
}}this.rgb[i] = (alpha << 24) | (rc << 16) | (gc << 8) | bc ;
}
this.allgrayopaque = allgray;
p$.setTransparency$I.apply(this, [transparency]);
});

Clazz.newMeth(C$, 'setRGBs$I$IA$I$Z', function (size, cmap, start, hasalpha) {
this.map_size = size;
this.rgb = Clazz.array(Integer.TYPE, [p$.calcRealMapSize$I$I.apply(this, [this.pixel_bits, size])]);
var j = start;
var transparency = 1;
var allgray = true;
for (var i = 0; i < size; i++, j++) {
var cmaprgb = cmap[j];
var r = (cmaprgb >> 16) & 255;
var g = (cmaprgb >> 8) & 255;
var b = (cmaprgb) & 255;
allgray = allgray && (r == g) && (g == b)  ;
if (hasalpha) {
var alpha = cmaprgb >>> 24;
if (alpha != 255) {
if (alpha == 0) {
if (transparency == 1) {
transparency = 2;
}if (this.transparent_index < 0) {
this.transparent_index = i;
}} else {
transparency = 3;
}allgray = false;
}} else {
cmaprgb = cmaprgb|(-16777216);
}this.rgb[i] = cmaprgb;
}
this.allgrayopaque = allgray;
p$.setTransparency$I.apply(this, [transparency]);
});

Clazz.newMeth(C$, 'calcRealMapSize$I$I', function (bits, size) {
var newSize = Math.max(1 << bits, size);
return Math.max(newSize, 256);
});

Clazz.newMeth(C$, 'getTransparency', function () {
return this.transparency;
});

Clazz.newMeth(C$, 'getComponentSize', function () {
if (this.nBits == null ) {
if (this.supportsAlpha) {
this.nBits = Clazz.array(Integer.TYPE, [4]);
this.nBits[3] = 8;
} else {
this.nBits = Clazz.array(Integer.TYPE, [3]);
}this.nBits[0] = this.nBits[1] = this.nBits[2] = 8;
}return this.nBits;
});

Clazz.newMeth(C$, 'getMapSize', function () {
return this.map_size;
});

Clazz.newMeth(C$, 'getTransparentPixel', function () {
return this.transparent_index;
});

Clazz.newMeth(C$, 'getReds$BA', function (r) {
for (var i = 0; i < this.map_size; i++) {
r[i] = (((this.rgb[i] >> 16)|0)|0);
}
});

Clazz.newMeth(C$, 'getGreens$BA', function (g) {
for (var i = 0; i < this.map_size; i++) {
g[i] = (((this.rgb[i] >> 8)|0)|0);
}
});

Clazz.newMeth(C$, 'getBlues$BA', function (b) {
for (var i = 0; i < this.map_size; i++) {
b[i] = ((this.rgb[i]|0)|0);
}
});

Clazz.newMeth(C$, 'getAlphas$BA', function (a) {
for (var i = 0; i < this.map_size; i++) {
a[i] = (((this.rgb[i] >> 24)|0)|0);
}
});

Clazz.newMeth(C$, 'getRGBs$IA', function (rgb) {
System.arraycopy(this.rgb, 0, rgb, 0, this.map_size);
});

Clazz.newMeth(C$, 'setTransparentPixel$I', function (trans) {
if (trans >= 0 && trans < this.map_size ) {
this.rgb[$j$=trans] = this.rgb[$j$]&(16777215);
this.transparent_index = trans;
this.allgrayopaque = false;
if (this.transparency == 1) {
p$.setTransparency$I.apply(this, [2]);
}}});

Clazz.newMeth(C$, 'setTransparency$I', function (transparency) {
if (this.transparency != transparency) {
this.transparency = transparency;
if (transparency == 1) {
this.supportsAlpha = false;
this.numComponents = 3;
this.nBits = C$.opaqueBits;
} else {
this.supportsAlpha = true;
this.numComponents = 4;
this.nBits = C$.alphaBits;
}}});

Clazz.newMeth(C$, 'calculatePixelMask', function () {
var maskbits = this.pixel_bits;
if (maskbits == 3) {
maskbits = 4;
} else if (maskbits > 4 && maskbits < 8 ) {
maskbits = 8;
}this.pixel_mask = (1 << maskbits) - 1;
});

Clazz.newMeth(C$, 'getRed$I', function (pixel) {
return (this.rgb[pixel & this.pixel_mask] >> 16) & 255;
});

Clazz.newMeth(C$, 'getGreen$I', function (pixel) {
return (this.rgb[pixel & this.pixel_mask] >> 8) & 255;
});

Clazz.newMeth(C$, 'getBlue$I', function (pixel) {
return this.rgb[pixel & this.pixel_mask] & 255;
});

Clazz.newMeth(C$, 'getAlpha$I', function (pixel) {
return (this.rgb[pixel & this.pixel_mask] >> 24) & 255;
});

Clazz.newMeth(C$, 'getRGB$I', function (pixel) {
return this.rgb[pixel & this.pixel_mask];
});

Clazz.newMeth(C$, 'getDataElements$I$O', function (rgb, pixel) {
var red = (rgb >> 16) & 255;
var green = (rgb >> 8) & 255;
var blue = rgb & 255;
var alpha = (rgb >>> 24);
var pix = 0;
for (var i = 38; i >= 0; i = i-(2)) {
if ((pix = this.lookupcache[i]) == 0) {
break;
}if (rgb == this.lookupcache[i + 1]) {
return p$.installpixel$O$I.apply(this, [pixel, ~pix]);
}}
if (this.allgrayopaque) {
var minDist = 256;
var d;
var gray = ((red * 77 + green * 150 + blue * 29 + 128)/256|0);
for (var i = 0; i < this.map_size; i++) {
if (this.rgb[i] == 0) {
continue;
}d = (this.rgb[i] & 255) - gray;
if (d < 0) d = -d;
if (d < minDist) {
pix = i;
if (d == 0) {
break;
}minDist = d;
}}
} else if (this.transparency == 1) {
var smallestError = 2147483647;
var lut = this.rgb;
var lutrgb;
for (var i = 0; i < this.map_size; i++) {
lutrgb = lut[i];
if (lutrgb == rgb && lutrgb != 0 ) {
pix = i;
smallestError = 0;
break;
}}
if (smallestError != 0) {
for (var i = 0; i < this.map_size; i++) {
lutrgb = lut[i];
if (lutrgb == 0) {
continue;
}var tmp = ((lutrgb >> 16) & 255) - red;
var currentError = tmp * tmp;
if (currentError < smallestError) {
tmp = ((lutrgb >> 8) & 255) - green;
currentError = currentError+(tmp * tmp);
if (currentError < smallestError) {
tmp = (lutrgb & 255) - blue;
currentError = currentError+(tmp * tmp);
if (currentError < smallestError) {
pix = i;
smallestError = currentError;
}}}}
}} else if (alpha == 0 && this.transparent_index >= 0 ) {
pix = this.transparent_index;
} else {
var smallestError = 2147483647;
var lut = this.rgb;
for (var i = 0; i < this.map_size; i++) {
var lutrgb = lut[i];
if (lutrgb == rgb) {
pix = i;
break;
}var tmp = ((lutrgb >> 16) & 255) - red;
var currentError = tmp * tmp;
if (currentError < smallestError) {
tmp = ((lutrgb >> 8) & 255) - green;
currentError = currentError+(tmp * tmp);
if (currentError < smallestError) {
tmp = (lutrgb & 255) - blue;
currentError = currentError+(tmp * tmp);
if (currentError < smallestError) {
tmp = (lutrgb >>> 24) - alpha;
currentError = currentError+(tmp * tmp);
if (currentError < smallestError) {
pix = i;
smallestError = currentError;
}}}}}
}System.arraycopy(this.lookupcache, 2, this.lookupcache, 0, 38);
this.lookupcache[39] = rgb;
this.lookupcache[38] = ~pix;
return p$.installpixel$O$I.apply(this, [pixel, pix]);
});

Clazz.newMeth(C$, 'installpixel$O$I', function (pixel, pix) {
switch (this.transferType) {
case 3:
var intObj;
if (pixel == null ) {
pixel = intObj = Clazz.array(Integer.TYPE, [1]);
} else {
intObj = pixel;
}intObj[0] = pix;
break;
case 0:
var byteObj;
if (pixel == null ) {
pixel = byteObj = Clazz.array(Byte.TYPE, [1]);
} else {
byteObj = pixel;
}byteObj[0] = ((pix|0)|0);
break;
case 1:
var shortObj;
if (pixel == null ) {
pixel = shortObj = Clazz.array(Short.TYPE, [1]);
} else {
shortObj = pixel;
}shortObj[0] = (pix|0);
break;
default:
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["This method has not been " + "implemented for transferType " + this.transferType ]);
}
return pixel;
});

Clazz.newMeth(C$, 'getComponents$I$IA$I', function (pixel, components, offset) {
if (components == null ) {
components = Clazz.array(Integer.TYPE, [offset + this.numComponents]);
}components[offset + 0] = this.getRed$I(pixel);
components[offset + 1] = this.getGreen$I(pixel);
components[offset + 2] = this.getBlue$I(pixel);
if (this.supportsAlpha && (components.length - offset) > 3 ) {
components[offset + 3] = this.getAlpha$I(pixel);
}return components;
});

Clazz.newMeth(C$, 'getComponents$O$IA$I', function (pixel, components, offset) {
var intpixel;
switch (this.transferType) {
case 0:
var bdata = pixel;
intpixel = bdata[0] & 255;
break;
case 1:
var sdata = pixel;
intpixel = sdata[0] & 65535;
break;
case 3:
var idata = pixel;
intpixel = idata[0];
break;
default:
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["This method has not been " + "implemented for transferType " + this.transferType ]);
}
return this.getComponents$I$IA$I(intpixel, components, offset);
});

Clazz.newMeth(C$, 'getDataElement$IA$I', function (components, offset) {
var rgb = (components[offset + 0] << 16) | (components[offset + 1] << 8) | (components[offset + 2]) ;
if (this.supportsAlpha) {
rgb = rgb|((components[offset + 3] << 24));
} else {
rgb = rgb|(-16777216);
}var inData = this.getDataElements$I$O(rgb, null);
var pixel;
switch (this.transferType) {
case 0:
var bdata = inData;
pixel = bdata[0] & 255;
break;
case 1:
var sdata = inData;
pixel = sdata[0];
break;
case 3:
var idata = inData;
pixel = idata[0];
break;
default:
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["This method has not been " + "implemented for transferType " + this.transferType ]);
}
return pixel;
});

Clazz.newMeth(C$, 'getDataElements$IA$I$O', function (components, offset, pixel) {
var rgb = (components[offset + 0] << 16) | (components[offset + 1] << 8) | (components[offset + 2]) ;
if (this.supportsAlpha) {
rgb = rgb|((components[offset + 3] << 24));
} else {
rgb = rgb&(-16777216);
}return this.getDataElements$I$O(rgb, pixel);
});

Clazz.newMeth(C$, 'createCompatibleWritableRaster$I$I', function (w, h) {
var raster;
if (this.pixel_bits == 1 || this.pixel_bits == 2  || this.pixel_bits == 4 ) {
raster = (I$[3]||$incl$(3)).createPackedRaster$I$I$I$I$I$java_awt_Point(0, w, h, 1, this.pixel_bits, null);
} else {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["This method is not supported  for pixel bits > 16."]);
}return raster;
});

Clazz.newMeth(C$, 'isCompatibleRaster$java_awt_image_Raster', function (raster) {
var size = raster.getSampleModel().getSampleSize$I(0);
return ((raster.getTransferType() == this.transferType) && (raster.getNumBands() == 1) && ((1 << size) >= this.map_size)  );
});

Clazz.newMeth(C$, 'createCompatibleSampleModel$I$I', function (w, h) {
var off = Clazz.array(Integer.TYPE, [1]);
off[0] = 0;
if (this.pixel_bits == 1 || this.pixel_bits == 2  || this.pixel_bits == 4 ) {
return Clazz.new_((I$[4]||$incl$(4)).c$$I$I$I$I,[this.transferType, w, h, this.pixel_bits]);
} else {
return Clazz.new_((I$[5]||$incl$(5)).c$$I$I$I$I$I$IA,[this.transferType, w, h, 1, w, off]);
}});

Clazz.newMeth(C$, 'isCompatibleSampleModel$java_awt_image_SampleModel', function (sm) {
if (!(Clazz.instanceOf(sm, "java.awt.image.ComponentSampleModel")) && !(Clazz.instanceOf(sm, "java.awt.image.MultiPixelPackedSampleModel")) ) {
return false;
}if (sm.getTransferType() != this.transferType) {
return false;
}if (sm.getNumBands() != 1) {
return false;
}return true;
});

Clazz.newMeth(C$, 'convertToIntDiscrete$java_awt_image_Raster$Z', function (raster, forceARGB) {
var cm;
if (!this.isCompatibleRaster$java_awt_image_Raster(raster)) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["This raster is not compatiblewith this IndexColorModel."]);
}if (forceARGB || this.transparency == 3 ) {
cm = (I$[2]||$incl$(2)).getRGBdefault();
} else if (this.transparency == 2) {
cm = Clazz.new_((I$[6]||$incl$(6)).c$$I$I$I$I$I,[25, 16711680, 65280, 255, 16777216]);
} else {
cm = Clazz.new_((I$[6]||$incl$(6)).c$$I$I$I$I,[24, 16711680, 65280, 255]);
}var w = raster.getWidth();
var h = raster.getHeight();
var discreteRaster = cm.createCompatibleWritableRaster$I$I(w, h);
var obj = null;
var data = null;
var rX = raster.getMinX();
var rY = raster.getMinY();
for (var y = 0; y < h; y++, rY++) {
obj = raster.getDataElements$I$I$I$I$O(rX, rY, w, 1, obj);
if (Clazz.instanceOf(obj, Clazz.array(Integer.TYPE, -1))) {
data = obj;
} else {
data = (I$[7]||$incl$(7)).toIntArray$O(obj);
}for (var x = 0; x < w; x++) {
data[x] = this.rgb[data[x] & this.pixel_mask];
}
discreteRaster.setDataElements$I$I$I$I$O(0, y, w, 1, data);
}
return Clazz.new_((I$[8]||$incl$(8)).c$$java_awt_image_ColorModel$java_awt_image_WritableRaster$Z$java_util_Hashtable,[cm, discreteRaster, false, null]);
});

Clazz.newMeth(C$, 'isValid$I', function (pixel) {
return true;
});

Clazz.newMeth(C$, 'isValid', function () {
return true;
});

Clazz.newMeth(C$, 'finalize', function () {
});

Clazz.newMeth(C$, 'toString', function () {
return  String.instantialize("IndexColorModel: #pixelBits = " + this.pixel_bits + " numComponents = " + this.numComponents + " color space = " + this.colorSpace + " transparency = " + this.transparency + " transIndex   = " + this.transparent_index + " has alpha = " + this.supportsAlpha + " isAlphaPre = " + this.$isAlphaPremultiplied );
});
var $j$;

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:01
