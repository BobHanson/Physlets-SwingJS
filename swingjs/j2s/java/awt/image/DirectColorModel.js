(function(){var P$=Clazz.newPackage("java.awt.image"),p$1={},I$=[[0,'java.awt.color.ColorSpace','java.awt.image.ColorModel','java.awt.image.Raster']],$I$=function(i){return I$[i]||(I$[i]=Clazz.load(I$[0][i]))};
var C$=Clazz.newClass(P$, "DirectColorModel", null, 'java.awt.image.PackedColorModel');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.red_mask=0;
this.green_mask=0;
this.blue_mask=0;
this.alpha_mask=0;
this.is_LinearRGB=false;
this.lRGBprecision=0;
this.tosRGB8LUT=null;
this.fromsRGB8LUT8=null;
this.fromsRGB8LUT16=null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$I$I$I', function (bits, rmask, gmask, bmask) {
C$.c$$I$I$I$I$I.apply(this, [bits, rmask, gmask, bmask, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$I$I$I$I$I', function (bits, rmask, gmask, bmask, amask) {
C$.superclazz.c$$java_awt_color_ColorSpace$I$I$I$I$I$Z$I$I.apply(this, [$I$(1).getInstance$I(1000), bits, rmask, gmask, bmask, amask, false, amask == 0 ? 1 : 3, $I$(2).getDefaultTransferType$I(bits)]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_color_ColorSpace$I$I$I$I$I$Z$I', function (space, bits, rmask, gmask, bmask, amask, isAlphaPremultiplied, transferType) {
C$.superclazz.c$$java_awt_color_ColorSpace$I$I$I$I$I$Z$I$I.apply(this, [space, bits, rmask, gmask, bmask, amask, isAlphaPremultiplied, amask == 0 ? 1 : 3, transferType]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getRedMask$', function () {
return this.maskArray[0];
});

Clazz.newMeth(C$, 'getGreenMask$', function () {
return this.maskArray[1];
});

Clazz.newMeth(C$, 'getBlueMask$', function () {
return this.maskArray[2];
});

Clazz.newMeth(C$, 'getAlphaMask$', function () {
if (this.supportsAlpha) {
return this.maskArray[3];
} else {
return 0;
}});

Clazz.newMeth(C$, 'getDefaultRGBComponents$I', function (pixel) {
var components=this.getComponents$I$IA$I(pixel, null, 0);
var norm=this.getNormalizedComponents$IA$I$FA$I(components, 0, null, 0);
return this.colorSpace.toRGB$FA(norm);
}, p$1);

Clazz.newMeth(C$, 'getsRGBComponentFromsRGB$I$I', function (pixel, idx) {
var c=((pixel & this.maskArray[idx]) >>> this.maskOffsets[idx]);
if (this.isAlphaPremultiplied) {
var a=((pixel & this.maskArray[3]) >>> this.maskOffsets[3]);
c=(a == 0) ? 0 : ((((c * this.scaleFactors[idx]) * 255.0 / (a * this.scaleFactors[3])) + 0.5)|0);
} else if (this.scaleFactors[idx] != 1.0 ) {
c=(((c * this.scaleFactors[idx]) + 0.5)|0);
}return c;
}, p$1);

Clazz.newMeth(C$, 'getsRGBComponentFromLinearRGB$I$I', function (pixel, idx) {
var c=((pixel & this.maskArray[idx]) >>> this.maskOffsets[idx]);
if (this.isAlphaPremultiplied) {
var factor=((1 << this.lRGBprecision) - 1);
var a=((pixel & this.maskArray[3]) >>> this.maskOffsets[3]);
c=(a == 0) ? 0 : ((((c * this.scaleFactors[idx]) * factor / (a * this.scaleFactors[3])) + 0.5)|0);
} else if (this.nBits[idx] != this.lRGBprecision) {
if (this.lRGBprecision == 16) {
c=(((c * this.scaleFactors[idx] * 257.0 ) + 0.5)|0);
} else {
c=(((c * this.scaleFactors[idx]) + 0.5)|0);
}}return this.tosRGB8LUT[c] & 255;
}, p$1);

Clazz.newMeth(C$, 'getRed$I', function (pixel) {
if (this.is_sRGB) {
return p$1.getsRGBComponentFromsRGB$I$I.apply(this, [pixel, 0]);
} else if (this.is_LinearRGB) {
return p$1.getsRGBComponentFromLinearRGB$I$I.apply(this, [pixel, 0]);
}var rgb=p$1.getDefaultRGBComponents$I.apply(this, [pixel]);
return ((rgb[0] * 255.0 + 0.5)|0);
});

Clazz.newMeth(C$, 'getGreen$I', function (pixel) {
if (this.is_sRGB) {
return p$1.getsRGBComponentFromsRGB$I$I.apply(this, [pixel, 1]);
} else if (this.is_LinearRGB) {
return p$1.getsRGBComponentFromLinearRGB$I$I.apply(this, [pixel, 1]);
}var rgb=p$1.getDefaultRGBComponents$I.apply(this, [pixel]);
return ((rgb[1] * 255.0 + 0.5)|0);
});

Clazz.newMeth(C$, 'getBlue$I', function (pixel) {
if (this.is_sRGB) {
return p$1.getsRGBComponentFromsRGB$I$I.apply(this, [pixel, 2]);
} else if (this.is_LinearRGB) {
return p$1.getsRGBComponentFromLinearRGB$I$I.apply(this, [pixel, 2]);
}var rgb=p$1.getDefaultRGBComponents$I.apply(this, [pixel]);
return ((rgb[2] * 255.0 + 0.5)|0);
});

Clazz.newMeth(C$, 'getAlpha$I', function (pixel) {
if (!this.supportsAlpha) return 255;
var a=((pixel & this.maskArray[3]) >>> this.maskOffsets[3]);
if (this.scaleFactors[3] != 1.0 ) {
a=((a * this.scaleFactors[3] + 0.5)|0);
}return a;
});

Clazz.newMeth(C$, 'getRGB$I', function (pixel) {
if (this.is_sRGB || this.is_LinearRGB ) {
return (this.getAlpha$I(pixel) << 24) | (this.getRed$I(pixel) << 16) | (this.getGreen$I(pixel) << 8) | (this.getBlue$I(pixel) << 0) ;
}var rgb=p$1.getDefaultRGBComponents$I.apply(this, [pixel]);
return (this.getAlpha$I(pixel) << 24) | ((((rgb[0] * 255.0 + 0.5)|0)) << 16) | ((((rgb[1] * 255.0 + 0.5)|0)) << 8) | ((((rgb[2] * 255.0 + 0.5)|0)) << 0) ;
});

Clazz.newMeth(C$, 'getRed$O', function (inData) {
var pixel=0;
switch (this.transferType) {
case 0:
var bdata=inData;
pixel=bdata[0] & 255;
break;
case 3:
var idata=inData;
pixel=idata[0];
break;
default:
throw Clazz.new_(Clazz.load('UnsupportedOperationException').c$$S,["This method has not been " + "implemented for transferType " + this.transferType ]);
}
return this.getRed$I(pixel);
});

Clazz.newMeth(C$, 'getGreen$O', function (inData) {
var pixel=0;
switch (this.transferType) {
case 0:
var bdata=inData;
pixel=bdata[0] & 255;
break;
case 3:
var idata=inData;
pixel=idata[0];
break;
default:
throw Clazz.new_(Clazz.load('UnsupportedOperationException').c$$S,["This method has not been " + "implemented for transferType " + this.transferType ]);
}
return this.getGreen$I(pixel);
});

Clazz.newMeth(C$, 'getBlue$O', function (inData) {
var pixel=0;
switch (this.transferType) {
case 0:
var bdata=inData;
pixel=bdata[0] & 255;
break;
case 3:
var idata=inData;
pixel=idata[0];
break;
default:
throw Clazz.new_(Clazz.load('UnsupportedOperationException').c$$S,["This method has not been " + "implemented for transferType " + this.transferType ]);
}
return this.getBlue$I(pixel);
});

Clazz.newMeth(C$, 'getAlpha$O', function (inData) {
var pixel=0;
switch (this.transferType) {
case 0:
var bdata=inData;
pixel=bdata[0] & 255;
break;
case 3:
var idata=inData;
pixel=idata[0];
break;
default:
throw Clazz.new_(Clazz.load('UnsupportedOperationException').c$$S,["This method has not been " + "implemented for transferType " + this.transferType ]);
}
return this.getAlpha$I(pixel);
});

Clazz.newMeth(C$, 'getRGB$O', function (inData) {
var pixel=0;
switch (this.transferType) {
case 0:
var bdata=inData;
pixel=bdata[0] & 255;
break;
case 3:
var idata=inData;
pixel=idata[0];
break;
default:
throw Clazz.new_(Clazz.load('UnsupportedOperationException').c$$S,["This method has not been " + "implemented for transferType " + this.transferType ]);
}
return this.getRGB$I(pixel);
});

Clazz.newMeth(C$, 'getDataElements$I$O', function (rgb, pixel) {
var intpixel=null;
if (this.transferType == 3 && pixel != null  ) {
intpixel=pixel;
intpixel[0]=0;
} else {
intpixel=Clazz.array(Integer.TYPE, [1]);
}var defaultCM=$I$(2).getRGBdefault$();
if (this === defaultCM  || this.equals$O(defaultCM) ) {
intpixel[0]=rgb;
return intpixel;
}var red;
var grn;
var blu;
var alp;
red=(rgb >> 16) & 255;
grn=(rgb >> 8) & 255;
blu=rgb & 255;
if (this.is_sRGB || this.is_LinearRGB ) {
var precision;
var factor;
if (this.is_LinearRGB) {
if (this.lRGBprecision == 8) {
red=this.fromsRGB8LUT8[red] & 255;
grn=this.fromsRGB8LUT8[grn] & 255;
blu=this.fromsRGB8LUT8[blu] & 255;
precision=8;
factor=0.003921569;
} else {
red=this.fromsRGB8LUT16[red] & 65535;
grn=this.fromsRGB8LUT16[grn] & 65535;
blu=this.fromsRGB8LUT16[blu] & 65535;
precision=16;
factor=1.5259022E-5;
}} else {
precision=8;
factor=0.003921569;
}if (this.supportsAlpha) {
alp=(rgb >> 24) & 255;
if (this.isAlphaPremultiplied) {
factor *= (alp * (0.003921569));
precision=-1;
}if (this.nBits[3] != 8) {
alp=(((alp * (0.003921569) * ((1 << this.nBits[3]) - 1) ) + 0.5)|0);
if (alp > ((1 << this.nBits[3]) - 1)) {
alp=(1 << this.nBits[3]) - 1;
}}intpixel[0]=alp << this.maskOffsets[3];
}if (this.nBits[0] != precision) {
red=(((red * factor * ((1 << this.nBits[0]) - 1) ) + 0.5)|0);
}if (this.nBits[1] != precision) {
grn=(((grn * factor * ((1 << this.nBits[1]) - 1) ) + 0.5)|0);
}if (this.nBits[2] != precision) {
blu=(((blu * factor * ((1 << this.nBits[2]) - 1) ) + 0.5)|0);
}} else {
var norm=Clazz.array(Float.TYPE, [3]);
var factor=0.003921569;
norm[0]=red * factor;
norm[1]=grn * factor;
norm[2]=blu * factor;
norm=this.colorSpace.fromRGB$FA(norm);
if (this.supportsAlpha) {
alp=(rgb >> 24) & 255;
if (this.isAlphaPremultiplied) {
factor *= alp;
for (var i=0; i < 3; i++) {
norm[i] *= factor;
}
}if (this.nBits[3] != 8) {
alp=(((alp * (0.003921569) * ((1 << this.nBits[3]) - 1) ) + 0.5)|0);
if (alp > ((1 << this.nBits[3]) - 1)) {
alp=(1 << this.nBits[3]) - 1;
}}intpixel[0]=alp << this.maskOffsets[3];
}red=(((norm[0] * ((1 << this.nBits[0]) - 1)) + 0.5)|0);
grn=(((norm[1] * ((1 << this.nBits[1]) - 1)) + 0.5)|0);
blu=(((norm[2] * ((1 << this.nBits[2]) - 1)) + 0.5)|0);
}if (this.maxBits > 23) {
if (red > ((1 << this.nBits[0]) - 1)) {
red=(1 << this.nBits[0]) - 1;
}if (grn > ((1 << this.nBits[1]) - 1)) {
grn=(1 << this.nBits[1]) - 1;
}if (blu > ((1 << this.nBits[2]) - 1)) {
blu=(1 << this.nBits[2]) - 1;
}}intpixel[0]|=(red << this.maskOffsets[0]) | (grn << this.maskOffsets[1]) | (blu << this.maskOffsets[2]) ;
switch (this.transferType) {
case 0:
{
var bdata;
if (pixel == null ) {
bdata=Clazz.array(Byte.TYPE, [1]);
} else {
bdata=pixel;
}bdata[0]=(((255 & intpixel[0])|0)|0);
return bdata;
}case 3:
return intpixel;
}
throw Clazz.new_(Clazz.load('UnsupportedOperationException').c$$S,["This method has not been " + "implemented for transferType " + this.transferType ]);
});

Clazz.newMeth(C$, 'getComponents$I$IA$I', function (pixel, components, offset) {
if (components == null ) {
components=Clazz.array(Integer.TYPE, [offset + this.numComponents]);
}for (var i=0; i < this.numComponents; i++) {
components[offset + i]=(pixel & this.maskArray[i]) >>> this.maskOffsets[i];
}
return components;
});

Clazz.newMeth(C$, 'getComponents$O$IA$I', function (pixel, components, offset) {
var intpixel=0;
switch (this.transferType) {
case 0:
var bdata=pixel;
intpixel=bdata[0] & 255;
break;
case 3:
var idata=pixel;
intpixel=idata[0];
break;
default:
throw Clazz.new_(Clazz.load('UnsupportedOperationException').c$$S,["This method has not been " + "implemented for transferType " + this.transferType ]);
}
return this.getComponents$I$IA$I(intpixel, components, offset);
});

Clazz.newMeth(C$, 'createCompatibleWritableRaster$I$I', function (w, h) {
if ((w <= 0) || (h <= 0) ) {
throw Clazz.new_(Clazz.load('IllegalArgumentException').c$$S,["Width (" + w + ") and height (" + h + ") cannot be <= 0" ]);
}var bandmasks;
if (this.supportsAlpha) {
bandmasks=Clazz.array(Integer.TYPE, [4]);
bandmasks[3]=this.alpha_mask;
} else {
bandmasks=Clazz.array(Integer.TYPE, [3]);
}bandmasks[0]=this.red_mask;
bandmasks[1]=this.green_mask;
bandmasks[2]=this.blue_mask;
if (this.pixel_bits > 8) {
return $I$(3).createPackedRaster$I$I$I$IA$java_awt_Point(3, w, h, bandmasks, null);
} else {
return $I$(3).createPackedRaster$I$I$I$IA$java_awt_Point(0, w, h, bandmasks, null);
}});

Clazz.newMeth(C$, 'getDataElement$IA$I', function (components, offset) {
var pixel=0;
for (var i=0; i < this.numComponents; i++) {
pixel|=((components[offset + i] << this.maskOffsets[i]) & this.maskArray[i]);
}
return pixel;
});

Clazz.newMeth(C$, 'getDataElements$IA$I$O', function (components, offset, obj) {
var pixel=0;
for (var i=0; i < this.numComponents; i++) {
pixel|=((components[offset + i] << this.maskOffsets[i]) & this.maskArray[i]);
}
switch (this.transferType) {
case 0:
if (Clazz.instanceOf(obj, Clazz.array(Byte.TYPE, -1))) {
var bdata=obj;
bdata[0]=(((pixel & 255)|0)|0);
return bdata;
} else {
var bdata=Clazz.array(Byte.TYPE, -1, [($b$[0] = (pixel & 255), $b$[0])]);
return bdata;
}case 3:
if (Clazz.instanceOf(obj, Clazz.array(Integer.TYPE, -1))) {
var idata=obj;
idata[0]=pixel;
return idata;
} else {
var idata=Clazz.array(Integer.TYPE, -1, [pixel]);
return idata;
}default:
throw Clazz.new_(Clazz.load('ClassCastException').c$$S,["This method has not been " + "implemented for transferType " + this.transferType ]);
}
});

Clazz.newMeth(C$, 'toString', function () {
return  String.instantialize("DirectColorModel: rmask=" + Integer.toHexString$I(this.red_mask) + " gmask=" + Integer.toHexString$I(this.green_mask) + " bmask=" + Integer.toHexString$I(this.blue_mask) + " amask=" + Integer.toHexString$I(this.alpha_mask) );
});
var $b$ = new Int8Array(1);

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 21:46:51 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
