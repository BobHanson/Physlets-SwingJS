(function(){var P$=Clazz.newPackage("java.awt.image"),I$=[['java.awt.image.ColorModel','swingjs.JSUtil']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PixelGrabber", null, null, 'java.awt.image.ImageConsumer');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.producer = null;
this.dstX = 0;
this.dstY = 0;
this.dstW = 0;
this.dstH = 0;
this.imageModel = null;
this.bytePixels = null;
this.intPixels = null;
this.dstOff = 0;
this.dstScan = 0;
this.grabbing = false;
this.flags = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Image$I$I$I$I$IA$I$I', function (img, x, y, w, h, pix, off, scansize) {
C$.c$$java_awt_image_ImageProducer$I$I$I$I$IA$I$I.apply(this, [img.getSource(), x, y, w, h, pix, off, scansize]);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_image_ImageProducer$I$I$I$I$IA$I$I', function (ip, x, y, w, h, pix, off, scansize) {
C$.$init$.apply(this);
this.producer=ip;
this.dstX=x;
this.dstY=y;
this.dstW=w;
this.dstH=h;
this.dstOff=off;
this.dstScan=scansize;
this.intPixels=pix;
this.imageModel=(I$[1]||$incl$(1)).getRGBdefault();
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Image$I$I$I$I$Z', function (img, x, y, w, h, forceRGB) {
C$.$init$.apply(this);
this.producer=img.getSource();
this.dstX=x;
this.dstY=y;
this.dstW=w;
this.dstH=h;
if (forceRGB) {
this.imageModel=(I$[1]||$incl$(1)).getRGBdefault();
}}, 1);

Clazz.newMeth(C$, 'startGrabbing', function () {
if ((this.flags & 112) != 0) {
return;
}if (!this.grabbing) {
this.grabbing=true;
this.flags&=-129;
this.producer.startProduction$java_awt_image_ImageConsumer(this);
}});

Clazz.newMeth(C$, 'abortGrabbing', function () {
this.imageComplete$I(4);
});

Clazz.newMeth(C$, 'grabPixels', function () {
return this.grabPixels$J(0);
});

Clazz.newMeth(C$, 'grabPixels$J', function (ms) {
if ((this.flags & 112) != 0) {
return (this.flags & 48) != 0;
}var end = ms + System.currentTimeMillis();
if (!this.grabbing) {
this.grabbing=true;
this.flags&=-129;
this.producer.startProduction$java_awt_image_ImageConsumer(this);
}while (this.grabbing){
var timeout;
if (ms == 0) {
timeout=0;
} else {
timeout=end - System.currentTimeMillis();
if (timeout <= 0) {
break;
}}(I$[2]||$incl$(2)).warn$S("wait in PixelGrabber.grabPixels");
this.wait$J(timeout);
}
return (this.flags & 48) != 0;
});

Clazz.newMeth(C$, 'getStatus', function () {
return this.flags;
});

Clazz.newMeth(C$, 'getWidth', function () {
return (this.dstW < 0) ? -1 : this.dstW;
});

Clazz.newMeth(C$, 'getHeight', function () {
return (this.dstH < 0) ? -1 : this.dstH;
});

Clazz.newMeth(C$, 'getPixels', function () {
return (this.bytePixels == null ) ? (this.intPixels) : (this.bytePixels);
});

Clazz.newMeth(C$, 'getColorModel', function () {
return this.imageModel;
});

Clazz.newMeth(C$, 'setDimensions$I$I', function (width, height) {
if (this.dstW < 0) {
this.dstW=width - this.dstX;
}if (this.dstH < 0) {
this.dstH=height - this.dstY;
}if (this.dstW <= 0 || this.dstH <= 0 ) {
this.imageComplete$I(3);
} else if (this.intPixels == null  && this.imageModel === (I$[1]||$incl$(1)).getRGBdefault()  ) {
this.intPixels=Clazz.array(Integer.TYPE, [this.dstW * this.dstH]);
this.dstScan=this.dstW;
this.dstOff=0;
}this.flags|=3;
});

Clazz.newMeth(C$, 'setHints$I', function (hints) {
return;
});

Clazz.newMeth(C$, 'setProperties$java_util_Hashtable', function (props) {
return;
});

Clazz.newMeth(C$, 'setColorModel$java_awt_image_ColorModel', function (model) {
return;
});

Clazz.newMeth(C$, 'convertToRGB', function () {
var size = this.dstW * this.dstH;
var newpixels = Clazz.array(Integer.TYPE, [size]);
if (this.bytePixels != null ) {
for (var i = 0; i < size; i++) {
newpixels[i]=this.imageModel.getRGB$I(this.bytePixels[i] & 255);
}
} else if (this.intPixels != null ) {
for (var i = 0; i < size; i++) {
newpixels[i]=this.imageModel.getRGB$I(this.intPixels[i]);
}
}this.bytePixels=null;
this.intPixels=newpixels;
this.dstScan=this.dstW;
this.dstOff=0;
this.imageModel=(I$[1]||$incl$(1)).getRGBdefault();
});

Clazz.newMeth(C$, 'setPixels$I$I$I$I$java_awt_image_ColorModel$BA$I$I', function (srcX, srcY, srcW, srcH, model, pixels, srcOff, srcScan) {
if (srcY < this.dstY) {
var diff = this.dstY - srcY;
if (diff >= srcH) {
return;
}srcOff+=srcScan * diff;
srcY+=diff;
srcH-=diff;
}if (srcY + srcH > this.dstY + this.dstH) {
srcH=(this.dstY + this.dstH) - srcY;
if (srcH <= 0) {
return;
}}if (srcX < this.dstX) {
var diff = this.dstX - srcX;
if (diff >= srcW) {
return;
}srcOff+=diff;
srcX+=diff;
srcW-=diff;
}if (srcX + srcW > this.dstX + this.dstW) {
srcW=(this.dstX + this.dstW) - srcX;
if (srcW <= 0) {
return;
}}var dstPtr = this.dstOff + (srcY - this.dstY) * this.dstScan + (srcX - this.dstX);
if (this.intPixels == null ) {
if (this.bytePixels == null ) {
this.bytePixels=Clazz.array(Byte.TYPE, [this.dstW * this.dstH]);
this.dstScan=this.dstW;
this.dstOff=0;
this.imageModel=model;
} else if (this.imageModel !== model ) {
p$.convertToRGB.apply(this, []);
}if (this.bytePixels != null ) {
for (var h = srcH; h > 0; h--) {
System.arraycopy(pixels, srcOff, this.bytePixels, dstPtr, srcW);
srcOff+=srcScan;
dstPtr+=this.dstScan;
}
}}if (this.intPixels != null ) {
var dstRem = this.dstScan - srcW;
var srcRem = srcScan - srcW;
for (var h = srcH; h > 0; h--) {
for (var w = srcW; w > 0; w--) {
this.intPixels[dstPtr++]=model.getRGB$I(pixels[srcOff++] & 255);
}
srcOff+=srcRem;
dstPtr+=dstRem;
}
}this.flags|=8;
});

Clazz.newMeth(C$, 'setPixels$I$I$I$I$java_awt_image_ColorModel$IA$I$I', function (srcX, srcY, srcW, srcH, model, pixels, srcOff, srcScan) {
if (srcY < this.dstY) {
var diff = this.dstY - srcY;
if (diff >= srcH) {
return;
}srcOff+=srcScan * diff;
srcY+=diff;
srcH-=diff;
}if (srcY + srcH > this.dstY + this.dstH) {
srcH=(this.dstY + this.dstH) - srcY;
if (srcH <= 0) {
return;
}}if (srcX < this.dstX) {
var diff = this.dstX - srcX;
if (diff >= srcW) {
return;
}srcOff+=diff;
srcX+=diff;
srcW-=diff;
}if (srcX + srcW > this.dstX + this.dstW) {
srcW=(this.dstX + this.dstW) - srcX;
if (srcW <= 0) {
return;
}}if (this.intPixels == null ) {
if (this.bytePixels == null ) {
this.intPixels=Clazz.array(Integer.TYPE, [this.dstW * this.dstH]);
this.dstScan=this.dstW;
this.dstOff=0;
this.imageModel=model;
} else {
p$.convertToRGB.apply(this, []);
}}var dstPtr = this.dstOff + (srcY - this.dstY) * this.dstScan + (srcX - this.dstX);
if (this.imageModel === model ) {
for (var h = srcH; h > 0; h--) {
System.arraycopy(pixels, srcOff, this.intPixels, dstPtr, srcW);
srcOff+=srcScan;
dstPtr+=this.dstScan;
}
} else {
if (this.imageModel !== (I$[1]||$incl$(1)).getRGBdefault() ) {
p$.convertToRGB.apply(this, []);
}var dstRem = this.dstScan - srcW;
var srcRem = srcScan - srcW;
for (var h = srcH; h > 0; h--) {
for (var w = srcW; w > 0; w--) {
this.intPixels[dstPtr++]=model.getRGB$I(pixels[srcOff++]);
}
srcOff+=srcRem;
dstPtr+=dstRem;
}
}this.flags|=8;
});

Clazz.newMeth(C$, 'imageComplete$I', function (status) {
this.grabbing=false;
switch (status) {
default:
case 1:
this.flags|=192;
break;
case 4:
this.flags|=128;
break;
case 3:
this.flags|=32;
break;
case 2:
this.flags|=16;
break;
}
this.producer.removeConsumer$java_awt_image_ImageConsumer(this);
});

Clazz.newMeth(C$, 'status', function () {
return this.flags;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:27
