(function(){var P$=Clazz.newPackage("sun.awt.image"),I$=[['java.util.Hashtable']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "OffScreenImageSource", null, null, 'java.awt.image.ImageProducer');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.image = null;
this.width = 0;
this.height = 0;
this.properties = null;
this.theConsumer = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_image_BufferedImage$java_util_Hashtable', function (image, properties) {
C$.$init$.apply(this);
this.image = image;
if (properties != null ) {
this.properties = properties;
} else {
this.properties = Clazz.new_((I$[1]||$incl$(1)));
}this.width = image.getWidth();
this.height = image.getHeight();
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_image_BufferedImage', function (image) {
C$.c$$java_awt_image_BufferedImage$java_util_Hashtable.apply(this, [image, null]);
}, 1);

Clazz.newMeth(C$, 'addConsumer$java_awt_image_ImageConsumer', function (ic) {
this.theConsumer = ic;
p$.produce.apply(this, []);
});

Clazz.newMeth(C$, 'isConsumer$java_awt_image_ImageConsumer', function (ic) {
return (ic === this.theConsumer );
});

Clazz.newMeth(C$, 'removeConsumer$java_awt_image_ImageConsumer', function (ic) {
if (this.theConsumer === ic ) {
this.theConsumer = null;
}});

Clazz.newMeth(C$, 'startProduction$java_awt_image_ImageConsumer', function (ic) {
this.addConsumer$java_awt_image_ImageConsumer(ic);
});

Clazz.newMeth(C$, 'requestTopDownLeftRightResend$java_awt_image_ImageConsumer', function (ic) {
});

Clazz.newMeth(C$, 'sendPixels', function () {
var cm = this.image.getColorModel();
});

Clazz.newMeth(C$, 'produce', function () {
try {
this.theConsumer.setDimensions$I$I(this.image.getWidth(), this.image.getHeight());
this.theConsumer.setProperties$java_util_Hashtable(this.properties);
p$.sendPixels.apply(this, []);
this.theConsumer.imageComplete$I(2);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.NullPointerException")){
if (this.theConsumer != null ) {
this.theConsumer.imageComplete$I(1);
}} else {
throw e;
}
}
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:19
