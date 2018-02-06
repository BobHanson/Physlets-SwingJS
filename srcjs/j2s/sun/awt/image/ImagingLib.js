(function(){var P$=Clazz.newPackage("sun.awt.image"),I$=[['swingjs.JSToolkit']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ImagingLib");
C$.verbose = false;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.verbose = false;
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'filter$java_awt_image_RasterOp$java_awt_image_Raster$java_awt_image_WritableRaster', function (op, src, dst) {
if (dst == null ) {
dst = op.createCompatibleDestRaster$java_awt_image_Raster(src);
}return (I$[1]||$incl$(1)).filterRaster$java_awt_image_Raster$java_awt_image_WritableRaster$java_awt_image_RasterOp(src, dst, op);
}, 1);

Clazz.newMeth(C$, 'filter$java_awt_image_BufferedImageOp$java_awt_image_BufferedImage$java_awt_image_BufferedImage', function (op, src, dst) {
if (C$.verbose) {
System.out.println$S("in filter and op is " + op + "bufimage is " + src + " and " + dst );
}if (dst == null ) {
dst = op.createCompatibleDestImage$java_awt_image_BufferedImage$java_awt_image_ColorModel(src, null);
}return (I$[1]||$incl$(1)).filterImage$java_awt_image_BufferedImage$java_awt_image_BufferedImage$java_awt_image_BufferedImageOp(src, dst, op);
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:18
