(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.awt.font.FontRenderContext']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FontMetrics");
C$.DEFAULT_FRC = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.DEFAULT_FRC = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_geom_AffineTransform$Z$Z,[null, false, false]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.font = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Font', function (font) {
C$.$init$.apply(this);
this.font = font;
}, 1);

Clazz.newMeth(C$, 'getFont', function () {
return this.font;
});

Clazz.newMeth(C$, 'getFontRenderContext', function () {
return C$.DEFAULT_FRC;
});

Clazz.newMeth(C$, 'getHeight', function () {
return this.getLeading() + this.getAscent() + this.getDescent() ;
});

Clazz.newMeth(C$, 'getMaxAscent', function () {
return this.getAscent();
});

Clazz.newMeth(C$, 'getMaxDescent', function () {
return this.getDescent();
});

Clazz.newMeth(C$, 'getMaxDecent', function () {
return this.getMaxDescent();
});

Clazz.newMeth(C$, 'getMaxAdvance', function () {
return this.charWidth$C("M");
});

Clazz.newMeth(C$, 'charsWidth$CA$I$I', function (data, off, len) {
return this.stringWidth$S( String.instantialize(data, off, len));
});

Clazz.newMeth(C$, 'bytesWidth$BA$I$I', function (data, off, len) {
return this.stringWidth$S( String.instantialize(data, 0, off, len));
});

Clazz.newMeth(C$, 'getStringBounds$S$java_awt_Graphics', function (str, context) {
return this.font.getStringBounds$S$java_awt_font_FontRenderContext(str, p$.myFRC$java_awt_Graphics.apply(this, [context]));
});

Clazz.newMeth(C$, 'getStringBounds$S$I$I$java_awt_Graphics', function (str, beginIndex, limit, context) {
return this.font.getStringBounds$S$I$I$java_awt_font_FontRenderContext(str, beginIndex, limit, p$.myFRC$java_awt_Graphics.apply(this, [context]));
});

Clazz.newMeth(C$, 'getStringBounds$CA$I$I$java_awt_Graphics', function (chars, beginIndex, limit, context) {
return this.font.getStringBounds$CA$I$I$java_awt_font_FontRenderContext(chars, beginIndex, limit, p$.myFRC$java_awt_Graphics.apply(this, [context]));
});

Clazz.newMeth(C$, 'myFRC$java_awt_Graphics', function (context) {
return null;
});

Clazz.newMeth(C$, 'toString', function () {
return this.getClass().getName() + "[font=" + this.getFont() + "ascent=" + this.getAscent() + ", descent=" + this.getDescent() + ", height=" + this.getHeight() + "]" ;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:01:51
