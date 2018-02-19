(function(){var P$=Clazz.newPackage("java.awt"),I$=[['swingjs.JSFontMetrics','java.awt.geom.AffineTransform','swingjs.JSToolkit',['java.text.AttributedCharacterIterator','.Attribute'],'java.awt.font.TextAttribute','javajs.util.SB',['java.awt.geom.Rectangle2D','.Float']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Font");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.fm = null;
this.family = null;
this.name = null;
this.style = 0;
this.size = 0;
this.pointSize = 0;
this.$hasLayoutAttributes = false;
this.nonIdentityTx = false;
this.hash = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getFontMetrics', function () {
if (this.fm == null ) ((this.fm = Clazz.new_((I$[1]||$incl$(1))))).setFont$java_awt_Font(this);
return this.fm;
});

Clazz.newMeth(C$, 'setFontMetrics$java_awt_FontMetrics', function (fm) {
this.fm = fm;
});

Clazz.newMeth(C$, 'c$$S$I$I', function (name, style, size) {
C$.$init$.apply(this);
this.name = (name != null ) ? name : "Default";
this.style = (style & -4) == 0 ? style : 0;
this.size = size;
this.pointSize = size;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Font', function (font) {
C$.$init$.apply(this);
this.name = font.name;
this.style = font.style;
this.size = font.size;
this.pointSize = font.pointSize;
}, 1);

Clazz.newMeth(C$, 'getTransform', function () {
return Clazz.new_((I$[2]||$incl$(2)));
});

Clazz.newMeth(C$, 'getFamily', function () {
return (this.family == null  ? this.family = (I$[3]||$incl$(3)).getFontFamily$java_awt_Font(this) : this.family);
});

Clazz.newMeth(C$, 'getName', function () {
return this.name;
});

Clazz.newMeth(C$, 'getFontName', function () {
return this.name;
});

Clazz.newMeth(C$, 'getStyle', function () {
return this.style;
});

Clazz.newMeth(C$, 'getSize', function () {
return this.size;
});

Clazz.newMeth(C$, 'getSize2D', function () {
return this.pointSize;
});

Clazz.newMeth(C$, 'isPlain', function () {
return this.style == 0;
});

Clazz.newMeth(C$, 'isBold', function () {
return (this.style & 1) != 0;
});

Clazz.newMeth(C$, 'isItalic', function () {
return (this.style & 2) != 0;
});

Clazz.newMeth(C$, 'isTransformed', function () {
return this.nonIdentityTx;
});

Clazz.newMeth(C$, 'hasLayoutAttributes', function () {
return this.$hasLayoutAttributes;
});

Clazz.newMeth(C$, 'getFont$S', function (nm) {
return C$.getFont$S$java_awt_Font(nm, null);
}, 1);

Clazz.newMeth(C$, 'decode$S', function (str) {
var fontName = str;
var styleName = "";
var fontSize = 12;
var fontStyle = 0;
if (str == null ) {
return Clazz.new_(C$.c$$S$I$I,["Dialog", fontStyle, fontSize]);
}var lastHyphen = str.lastIndexOf("-");
var lastSpace = str.lastIndexOf(" ");
var sepChar = (lastHyphen > lastSpace) ? "-" : " ";
var sizeIndex = str.lastIndexOf(sepChar);
var styleIndex = str.lastIndexOf(sepChar, sizeIndex - 1);
var strlen = str.length$();
if (sizeIndex > 0 && sizeIndex + 1 < strlen ) {
try {
fontSize = Integer.$valueOf(str.substring(sizeIndex + 1)).intValue();
if (fontSize <= 0) {
fontSize = 12;
}} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.NumberFormatException")){
styleIndex = sizeIndex;
sizeIndex = strlen;
if (str.charAt(sizeIndex - 1) == sepChar) {
sizeIndex--;
}} else {
throw e;
}
}
}if (styleIndex >= 0 && styleIndex + 1 < strlen ) {
styleName = str.substring(styleIndex + 1, sizeIndex);
styleName = styleName.toLowerCase();
if (styleName.equals$O("bolditalic")) {
fontStyle = 3;
} else if (styleName.equals$O("italic")) {
fontStyle = 2;
} else if (styleName.equals$O("bold")) {
fontStyle = 1;
} else if (styleName.equals$O("plain")) {
fontStyle = 0;
} else {
styleIndex = sizeIndex;
if (str.charAt(styleIndex - 1) == sepChar) {
styleIndex--;
}}fontName = str.substring(0, styleIndex);
} else {
var fontEnd = strlen;
if (styleIndex > 0) {
fontEnd = styleIndex;
} else if (sizeIndex > 0) {
fontEnd = sizeIndex;
}if (fontEnd > 0 && str.charAt(fontEnd - 1) == sepChar ) {
fontEnd--;
}fontName = str.substring(0, fontEnd);
}return Clazz.new_(C$.c$$S$I$I,[fontName, fontStyle, fontSize]);
}, 1);

Clazz.newMeth(C$, 'getFont$S$java_awt_Font', function (nm, font) {
var str = null;
try {
str = System.getProperty(nm);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.SecurityException")){
} else {
throw e;
}
}
if (str == null ) {
return font;
}return C$.decode$S(str);
}, 1);

Clazz.newMeth(C$, 'hashCode', function () {
if (this.hash == 0) {
this.hash = this.name.hashCode() ^ this.style ^ this.size ;
}return this.hash;
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
if (obj === this ) {
return true;
}if (obj != null ) {
try {
var font = obj;
if (this.size == font.size && this.style == font.style  && this.nonIdentityTx == font.nonIdentityTx   && this.$hasLayoutAttributes == font.$hasLayoutAttributes   && this.pointSize == font.pointSize   && this.name.equals$O(font.name) ) {
return true;
}} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.ClassCastException")){
} else {
throw e;
}
}
}return false;
});

Clazz.newMeth(C$, 'toString', function () {
var strStyle;
if (this.isBold()) {
strStyle = this.isItalic() ? "bolditalic" : "bold";
} else {
strStyle = this.isItalic() ? "italic" : "plain";
}return this.getClass().getName() + "[family=" + this.getFamily() + ",name=" + this.name + ",style=" + strStyle + ",size=" + this.size + "]" ;
});

Clazz.newMeth(C$, 'getAvailableAttributes', function () {
var attributes = Clazz.array((I$[4]||$incl$(4)), -1, [(I$[5]||$incl$(5)).FAMILY, (I$[5]||$incl$(5)).WEIGHT, (I$[5]||$incl$(5)).WIDTH, (I$[5]||$incl$(5)).SIZE, (I$[5]||$incl$(5)).UNDERLINE, (I$[5]||$incl$(5)).STRIKETHROUGH]);
return attributes;
});

Clazz.newMeth(C$, 'deriveFont$I$F', function (style, sizePts) {
var f = Clazz.new_(C$.c$$S$I$I,[this.name, style, ((sizePts + 0.5)|0)]);
f.pointSize = sizePts;
return f;
});

Clazz.newMeth(C$, 'deriveFont$F', function (sizePts) {
var f = Clazz.new_(C$.c$$S$I$I,[this.name, this.style, ((sizePts + 0.5)|0)]);
f.pointSize = sizePts;
return f;
});

Clazz.newMeth(C$, 'deriveFont$I', function (style) {
return Clazz.new_(C$.c$$S$I$I,[this.name, style, this.size]);
});

Clazz.newMeth(C$, 'hasUniformLineMetrics', function () {
return false;
});

Clazz.newMeth(C$, 'getStringBounds$S$java_awt_font_FontRenderContext', function (str, frc) {
return this.getStringBoundsStr$S$I$I(str, 0, -1);
});

Clazz.newMeth(C$, 'getStringBounds$S$I$I$java_awt_font_FontRenderContext', function (str, beginIndex, limit, frc) {
return this.getStringBoundsStr$S$I$I(str, beginIndex, limit);
});

Clazz.newMeth(C$, 'getStringBounds$CA$I$I$java_awt_font_FontRenderContext', function (chars, beginIndex, limit, frc) {
var sb = Clazz.new_((I$[6]||$incl$(6)));
sb.appendCB$CA$I$I(chars, beginIndex, limit);
return this.getStringBoundsStr$S$I$I(sb.toString(), 0, -1);
});

Clazz.newMeth(C$, 'getStringBoundsStr$S$I$I', function (s, i, j) {
if (j >= i) s = s.substring(i, j);
var fm = this.getFontMetrics();
var dec = fm.getDescent();
var asc = fm.getAscent();
var width = fm.stringWidth$S(s);
return Clazz.new_((I$[7]||$incl$(7)).c$$F$F$F$F,[0, -dec, width, asc + dec]);
});

Clazz.newMeth(C$, 'finalize', function () {
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:01:48
