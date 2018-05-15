(function(){var P$=Clazz.newPackage("swingjs"),I$=[['java.awt.GraphicsEnvironment','swingjs.api.Interface','java.awt.image.ColorModel','java.awt.image.WritableRaster','java.lang.Boolean','java.util.Hashtable','swingjs.JSUtil','java.awt.Rectangle']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSGraphicsConfiguration", null, 'java.awt.GraphicsConfiguration');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
System.out.println$S("JSGraphicsConfiguration initialized");
}, 1);

Clazz.newMeth(C$, 'getDevice', function () {
return (I$[1]||$incl$(1)).getLocalGraphicsEnvironment().getDefaultScreenDevice();
});

Clazz.newMeth(C$, 'createCompatibleImage$I$I', function (width, height) {
var cm = this.getColorModel();
var wr = cm.createCompatibleWritableRaster$I$I(width, height);
return this.newBufferedImage$java_awt_image_ColorModel$java_awt_image_WritableRaster$Z$java_util_Hashtable(cm, wr, false, null);
});

Clazz.newMeth(C$, 'newBufferedImage$java_awt_image_ColorModel$java_awt_image_WritableRaster$Z$java_util_Hashtable', function (cm, wr, alphaPremultiplied, properties) {
return (I$[2]||$incl$(2)).getInstanceWithParams$S$ClassA$OA("java.awt.image.BufferedImage", Clazz.array(java.lang.Class, -1, [Clazz.getClass((I$[3]||$incl$(3))), Clazz.getClass((I$[4]||$incl$(4))), Clazz.getClass((I$[5]||$incl$(5))), Clazz.getClass((I$[6]||$incl$(6)))]), Clazz.array(java.lang.Object, -1, [cm, wr, alphaPremultiplied ? (I$[5]||$incl$(5)).TRUE : (I$[5]||$incl$(5)).FALSE, properties]));
});

Clazz.newMeth(C$, 'getColorModel', function () {
return (I$[3]||$incl$(3)).getRGBdefault();
});

Clazz.newMeth(C$, 'getColorModel$I', function (transparency) {
return this.getColorModel();
});

Clazz.newMeth(C$, 'getDefaultTransform', function () {
return (I$[2]||$incl$(2)).getInstance$S$Z("java.awt.geom.AffineTransform", true);
});

Clazz.newMeth(C$, 'getNormalizingTransform', function () {
return this.getDefaultTransform();
});

Clazz.newMeth(C$, 'getBounds', function () {
var doc = null;
{
doc = document;
}
var d = (I$[7]||$incl$(7)).getJQuery().$(doc);
return Clazz.new_((I$[8]||$incl$(8)).c$$I$I,[d.width(), d.height()]);
});
})();
//Created 2018-05-15 01:03:15
