(function(){var P$=Clazz.newPackage("sun.awt.image"),I$=[['java.awt.GraphicsEnvironment','java.awt.Font']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "OffScreenImage", null, 'java.awt.image.BufferedImage');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.c = null;
this.defaultFont = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$I$I', function (width, height, imageType) {
C$.superclazz.c$$I$I$I.apply(this, [width, height, imageType]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getGraphics', function () {
return this.createGraphics();
});

Clazz.newMeth(C$, 'createGraphics', function () {
if (this.c == null ) {
var env = (I$[1]||$incl$(1)).getLocalGraphicsEnvironment();
return env.createGraphics$java_awt_image_BufferedImage(this);
}var font = this.c.getFont();
if (font == null ) {
if (this.defaultFont == null ) {
this.defaultFont = Clazz.new_((I$[2]||$incl$(2)).c$$S$I$I,["Dialog", 0, 12]);
}font = this.defaultFont;
}return null;
});

Clazz.newMeth(C$, 'getSource', function () {
return null;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:09
