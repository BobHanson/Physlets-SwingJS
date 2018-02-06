(function(){var P$=Clazz.newPackage("swingjs"),I$=[['swingjs.JSToolkit']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSFontMetrics", null, 'java.awt.FontMetrics');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.fwidths = null;
this.iwidths = null;
this.FIRST_PRINTABLE = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.FIRST_PRINTABLE = 32;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$java_awt_Font.apply(this, [null]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'setFont$java_awt_Font', function (f) {
this.font = f;
});

Clazz.newMeth(C$, 'getLeading', function () {
return (this.font.getSize()/20|0) + 1;
});

Clazz.newMeth(C$, 'getAscent', function () {
return this.font.getSize();
});

Clazz.newMeth(C$, 'getDescent', function () {
return (this.font.getSize()/4|0) + 1;
});

Clazz.newMeth(C$, 'charWidth$C', function (pt) {
return (pt.$c() < 256  ? (this.getWidthsFloat()[pt.$c()]|0) : this.stringWidth$S("" + pt));
});

Clazz.newMeth(C$, 'charWidth$I', function (pt) {
{
var spt;
return ((pt + 0 == pt ? pt : (pt = (spt = pt).charCodeAt(0))) < 256 ? (this.getWidthsFloat()[pt] | 0) : this.stringWidth$S(isChar ? spt : String.fromCharCode (pt)));
}
});

Clazz.newMeth(C$, 'stringWidth$S', function (s) {
return ((I$[1]||$incl$(1)).getStringWidth$swingjs_api_js_HTML5CanvasContext2D$java_awt_Font$S(null, this.font, s)|0);
});

Clazz.newMeth(C$, 'getWidths', function () {
if (this.iwidths != null ) return this.iwidths;
this.iwidths = Clazz.array(Integer.TYPE, [256]);
this.getWidthsFloat();
for (var ch = this.FIRST_PRINTABLE; ch < 256; ch++) {
this.iwidths[ch] = (this.fwidths[ch]|0);
}
return this.iwidths;
});

Clazz.newMeth(C$, 'getWidthsFloat', function () {
if (this.fwidths != null ) return this.fwidths;
this.fwidths = Clazz.array(Float.TYPE, [256]);
for (var ch = this.FIRST_PRINTABLE; ch < 256; ch++) {
this.fwidths[ch] = (I$[1]||$incl$(1)).getStringWidth$swingjs_api_js_HTML5CanvasContext2D$java_awt_Font$S(null, this.font, "" + String.fromCharCode(ch));
}
return this.fwidths;
});
})();
//Created 2018-02-06 09:00:29
