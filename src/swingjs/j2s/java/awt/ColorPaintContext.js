(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.awt.image.ColorModel']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ColorPaintContext", null, null, 'java.awt.PaintContext');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.color = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$java_awt_image_ColorModel', function (color, cm) {
C$.$init$.apply(this);
this.color=color;
}, 1);

Clazz.newMeth(C$, 'dispose', function () {
});

Clazz.newMeth(C$, 'getRGB', function () {
return this.color;
});

Clazz.newMeth(C$, 'getColorModel', function () {
return (I$[1]||$incl$(1)).getRGBdefault();
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:06
