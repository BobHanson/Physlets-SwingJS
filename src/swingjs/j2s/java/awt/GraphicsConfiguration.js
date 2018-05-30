(function(){var P$=Clazz.newPackage("java.awt"),I$=[['swingjs.api.Interface','java.awt.image.ColorModel','java.awt.image.WritableRaster','java.lang.Boolean','java.util.Hashtable']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "GraphicsConfiguration");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'createCompatibleImage$I$I$I', function (width, height, transparency) {
if (this.getColorModel().getTransparency() == transparency) {
return this.createCompatibleImage$I$I(width, height);
}var cm = this.getColorModel$I(transparency);
if (cm == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Unknown transparency: " + transparency]);
}var wr = cm.createCompatibleWritableRaster$I$I(width, height);
return this.newBufferedImage$java_awt_image_ColorModel$java_awt_image_WritableRaster$Z$java_util_Hashtable(cm, wr, cm.isAlphaPremultiplied(), null);
});

Clazz.newMeth(C$, 'newBufferedImage$java_awt_image_ColorModel$java_awt_image_WritableRaster$Z$java_util_Hashtable', function (cm, wr, alphaPremultiplied, properties) {
return (I$[1]||$incl$(1)).getInstanceWithParams$S$ClassA$OA("java.awt.image.BufferedImage", Clazz.array(java.lang.Class, -1, [Clazz.getClass((I$[2]||$incl$(2))), Clazz.getClass((I$[3]||$incl$(3))), Clazz.getClass((I$[4]||$incl$(4))), Clazz.getClass((I$[5]||$incl$(5)))]), Clazz.array(java.lang.Object, -1, [cm, wr, alphaPremultiplied ? (I$[4]||$incl$(4)).TRUE : (I$[4]||$incl$(4)).FALSE, properties]));
});

Clazz.newMeth(C$, 'createCompatibleVolatileImage$I$I', function (width, height) {
var vi = null;
try {
vi=this.createCompatibleVolatileImage$I$I$java_awt_ImageCapabilities$I(width, height, null, 1);
} catch (e) {
if (Clazz.exceptionOf(e, "java.awt.AWTException")){
Clazz.assert(C$, this, function(){return false});
} else {
throw e;
}
}
return vi;
});

Clazz.newMeth(C$, 'createCompatibleVolatileImage$I$I$I', function (width, height, transparency) {
var vi = null;
try {
vi=this.createCompatibleVolatileImage$I$I$java_awt_ImageCapabilities$I(width, height, null, transparency);
} catch (e) {
if (Clazz.exceptionOf(e, "java.awt.AWTException")){
Clazz.assert(C$, this, function(){return false});
} else {
throw e;
}
}
return vi;
});

Clazz.newMeth(C$, 'createCompatibleVolatileImage$I$I$java_awt_ImageCapabilities$I', function (width, height, caps, transparency) {
var vi = (I$[1]||$incl$(1)).getInstanceWithParams$S$ClassA$OA("sun.awt.image.SunVolatileImage", Clazz.array(java.lang.Class, -1, [Clazz.getClass(C$), Clazz.getClass(java.lang.Integer), Clazz.getClass(java.lang.Integer), Clazz.getClass((I$[4]||$incl$(4))), Clazz.getClass(java.lang.Object), Clazz.getClass(java.lang.Integer)]), Clazz.array(java.lang.Object, -1, [this, Integer.$valueOf(width), Integer.$valueOf(height), caps, Integer.$valueOf(transparency)]));
if (caps != null  && caps.isAccelerated()  && !vi.getCapabilities().isAccelerated() ) {
throw Clazz.new_(Clazz.load('java.awt.AWTException').c$$S,["Supplied image capabilities could not be met by this graphics configuration."]);
}return vi;
});

Clazz.newMeth(C$, 'isTranslucencyCapable', function () {
return false;
});
C$.$_ASSERT_ENABLED_ = ClassLoader.$getClassAssertionStatus(C$);
})();
//Created 2018-05-24 08:45:09
