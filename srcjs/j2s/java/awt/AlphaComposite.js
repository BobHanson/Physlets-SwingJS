(function(){var P$=Clazz.newPackage("java.awt"),I$=[['sun.java2d.SunCompositeContext']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AlphaComposite", null, null, 'java.awt.Composite');
C$.Clear = null;
C$.Src = null;
C$.Dst = null;
C$.SrcOver = null;
C$.DstOver = null;
C$.SrcIn = null;
C$.DstIn = null;
C$.SrcOut = null;
C$.DstOut = null;
C$.SrcAtop = null;
C$.DstAtop = null;
C$.Xor = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.Clear = Clazz.new_(C$.c$$I,[1]);
C$.Src = Clazz.new_(C$.c$$I,[2]);
C$.Dst = Clazz.new_(C$.c$$I,[9]);
C$.SrcOver = Clazz.new_(C$.c$$I,[3]);
C$.DstOver = Clazz.new_(C$.c$$I,[4]);
C$.SrcIn = Clazz.new_(C$.c$$I,[5]);
C$.DstIn = Clazz.new_(C$.c$$I,[6]);
C$.SrcOut = Clazz.new_(C$.c$$I,[7]);
C$.DstOut = Clazz.new_(C$.c$$I,[8]);
C$.SrcAtop = Clazz.new_(C$.c$$I,[10]);
C$.DstAtop = Clazz.new_(C$.c$$I,[11]);
C$.Xor = Clazz.new_(C$.c$$I,[12]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.extraAlpha = 0;
this.rule = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (rule) {
C$.c$$I$F.apply(this, [rule, 1.0]);
}, 1);

Clazz.newMeth(C$, 'c$$I$F', function (rule, alpha) {
C$.$init$.apply(this);
if (alpha < 0.0  || alpha > 1.0  ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["alpha value out of range"]);
}if (rule < 1 || rule > 12 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["unknown composite rule"]);
}this.rule = rule;
this.extraAlpha = alpha;
}, 1);

Clazz.newMeth(C$, 'getInstance$I', function (rule) {
switch (rule) {
case 1:
return C$.Clear;
case 2:
return C$.Src;
case 9:
return C$.Dst;
case 3:
return C$.SrcOver;
case 4:
return C$.DstOver;
case 5:
return C$.SrcIn;
case 6:
return C$.DstIn;
case 7:
return C$.SrcOut;
case 8:
return C$.DstOut;
case 10:
return C$.SrcAtop;
case 11:
return C$.DstAtop;
case 12:
return C$.Xor;
default:
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["unknown composite rule"]);
}
}, 1);

Clazz.newMeth(C$, 'getInstance$I$F', function (rule, alpha) {
if (alpha == 1.0 ) {
return C$.getInstance$I(rule);
}return Clazz.new_(C$.c$$I$F,[rule, alpha]);
}, 1);

Clazz.newMeth(C$, 'createContext$java_awt_image_ColorModel$java_awt_image_ColorModel$java_awt_RenderingHints', function (srcColorModel, dstColorModel, hints) {
return Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_AlphaComposite$java_awt_image_ColorModel$java_awt_image_ColorModel,[this, srcColorModel, dstColorModel]);
});

Clazz.newMeth(C$, 'getAlpha', function () {
return this.extraAlpha;
});

Clazz.newMeth(C$, 'getRule', function () {
return this.rule;
});

Clazz.newMeth(C$, 'derive$I', function (rule) {
return (this.rule == rule) ? this : C$.getInstance$I$F(rule, this.extraAlpha);
});

Clazz.newMeth(C$, 'derive$F', function (alpha) {
return (this.extraAlpha == alpha ) ? this : C$.getInstance$I$F(this.rule, alpha);
});

Clazz.newMeth(C$, 'hashCode', function () {
return (Float.floatToIntBits(this.extraAlpha) * 31 + this.rule);
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
if (!(Clazz.instanceOf(obj, "java.awt.AlphaComposite"))) {
return false;
}var ac = obj;
if (this.rule != ac.rule) {
return false;
}if (this.extraAlpha != ac.extraAlpha ) {
return false;
}return true;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:07
