(function(){var P$=Clazz.newPackage("sun.font"),I$=[['sun.font.FontDesignMetrics','java.util.Hashtable',['sun.font.FontDesignMetrics','.KeyReference'],'swingjs.JSToolkit']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FontDesignMetrics", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.awt.FontMetrics');
C$.roundingUpValue = 0;
C$.metricsCache = null;
C$.recentMetrics = null;
C$.recentIndex = 0;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.roundingUpValue = 0.95;
C$.metricsCache = Clazz.new_((I$[2]||$incl$(2)));
C$.recentMetrics = Clazz.array(C$, [5]);
C$.recentIndex = 0;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.ascent = 0;
this.descent = 0;
this.leading = 0;
this.height = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.height = -1;
}, 1);

Clazz.newMeth(C$, 'getMetrics$java_awt_Font', function (font) {
var m = null;
var r;
r=C$.metricsCache.get$O(font);
if (r != null ) {
m=r.get();
}if (m == null ) {
m=Clazz.new_(C$.c$$java_awt_Font,[font]);
C$.metricsCache.put$TK$TV(font, Clazz.new_((I$[3]||$incl$(3)).c$$O$O,[font, m]));
}for (var i = 0; i < C$.recentMetrics.length; i++) {
if (C$.recentMetrics[i] === m ) {
return m;
}}
{
C$.recentMetrics[C$.recentIndex++]=m;
if (C$.recentIndex == 5) {
C$.recentIndex=0;
}}return m;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Font', function (font) {
C$.superclazz.c$$java_awt_Font.apply(this, [font]);
C$.$init$.apply(this);
this.font=font;
p$.initMatrixAndMetrics.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'initMatrixAndMetrics', function () {
{
//need to calculate ascent, descent, leading, and maxAdvance
}
});

Clazz.newMeth(C$, 'charWidth$C', function (ch) {
var s = "";
{
s = "" + ch;
}
return this.stringWidth$S(s);
});

Clazz.newMeth(C$, 'stringWidth$S', function (str) {
return ((0.5 + p$.getWidth$S.apply(this, [str]))|0);
});

Clazz.newMeth(C$, 'getWidth$S', function (str) {
return (I$[4]||$incl$(4)).getStringWidth$swingjs_api_js_HTML5CanvasContext2D$java_awt_Font$S(null, this.font, str);
});

Clazz.newMeth(C$, 'charsWidth$CA$I$I', function (data, off, len) {
var width = 0;
if (len < 0) {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException').c$$S,["len=" + len]);
}var limit = off + len;
for (var i = off; i < limit; i++) {
var ch = data[i];
width += this.stringWidth$S("" + ch);
}
return ((0.5 + width)|0);
});

Clazz.newMeth(C$, 'getWidths', function () {
var widths = Clazz.array(Integer.TYPE, [256]);
return widths;
});

Clazz.newMeth(C$, 'getAscent', function () {
if (this.ascent == 0 ) this.ascent=this.font.getFontMetrics().getAscent();
return ((C$.roundingUpValue + this.ascent)|0);
});

Clazz.newMeth(C$, 'getDescent', function () {
if (this.descent == 0 ) this.descent=this.font.getFontMetrics().getDescent();
return ((C$.roundingUpValue + this.descent)|0);
});

Clazz.newMeth(C$, 'getLeading', function () {
return ((C$.roundingUpValue + this.descent + this.leading )|0) - ((C$.roundingUpValue + this.descent)|0);
});

Clazz.newMeth(C$, 'getHeight', function () {
if (this.height < 0) {
this.height=this.getAscent() + ((C$.roundingUpValue + this.descent + this.leading )|0);
}return this.height;
});

Clazz.newMeth(C$, 'charWidth$I', function (codePoint) {
if (!Character.isValidCodePoint(codePoint)) {
codePoint=65535;
}if (codePoint < 256) {
return this.getWidths()[codePoint];
} else {
var buffer = Clazz.array(Character.TYPE, [2]);
var len = Character.toChars(codePoint, buffer, 0);
return this.charsWidth$CA$I$I(buffer, 0, len);
}});
;
(function(){var C$=Clazz.newClass(P$.FontDesignMetrics, "KeyReference", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.key = null;
this.val = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O$O', function (key, value) {
C$.$init$.apply(this);
this.key=key;
this.val=value;
}, 1);

Clazz.newMeth(C$, 'get', function () {
return this.val;
});

Clazz.newMeth(C$, 'dispose', function () {
if ((I$[1]||$incl$(1)).metricsCache.get$O(this.key) === this ) {
(I$[1]||$incl$(1)).metricsCache.remove$O(this.key);
}});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:30
