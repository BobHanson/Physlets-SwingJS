(function(){var P$=Clazz.newPackage("sun.font"),I$=[['java.awt.font.TextAttribute']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "EAttribute", null, 'Enum');
var p$=C$.prototype;
C$.atts = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.atts = Clazz.getClass(C$).getEnumConstants();
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EFAMILY", 0, [(I$[1]||$incl$(1)).FAMILY]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EWEIGHT", 1, [(I$[1]||$incl$(1)).WEIGHT]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EWIDTH", 2, [(I$[1]||$incl$(1)).WIDTH]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EPOSTURE", 3, [(I$[1]||$incl$(1)).POSTURE]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "ESIZE", 4, [(I$[1]||$incl$(1)).SIZE]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "ETRANSFORM", 5, [(I$[1]||$incl$(1)).TRANSFORM]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "ESUPERSCRIPT", 6, [(I$[1]||$incl$(1)).SUPERSCRIPT]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EFONT", 7, [(I$[1]||$incl$(1)).FONT]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "ECHAR_REPLACEMENT", 8, [(I$[1]||$incl$(1)).CHAR_REPLACEMENT]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EFOREGROUND", 9, [(I$[1]||$incl$(1)).FOREGROUND]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EBACKGROUND", 10, [(I$[1]||$incl$(1)).BACKGROUND]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EUNDERLINE", 11, [(I$[1]||$incl$(1)).UNDERLINE]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "ESTRIKETHROUGH", 12, [(I$[1]||$incl$(1)).STRIKETHROUGH]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "ERUN_DIRECTION", 13, [(I$[1]||$incl$(1)).RUN_DIRECTION]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EBIDI_EMBEDDING", 14, [(I$[1]||$incl$(1)).BIDI_EMBEDDING]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EJUSTIFICATION", 15, [(I$[1]||$incl$(1)).JUSTIFICATION]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EINPUT_METHOD_HIGHLIGHT", 16, [(I$[1]||$incl$(1)).INPUT_METHOD_HIGHLIGHT]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EINPUT_METHOD_UNDERLINE", 17, [(I$[1]||$incl$(1)).INPUT_METHOD_UNDERLINE]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "ESWAP_COLORS", 18, [(I$[1]||$incl$(1)).SWAP_COLORS]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "ENUMERIC_SHAPING", 19, [(I$[1]||$incl$(1)).NUMERIC_SHAPING]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EKERNING", 20, [(I$[1]||$incl$(1)).KERNING]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "ELIGATURES", 21, [(I$[1]||$incl$(1)).LIGATURES]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "ETRACKING", 22, [(I$[1]||$incl$(1)).TRACKING]);
Clazz.newEnumConst($vals, C$.c$$java_awt_font_TextAttribute, "EBASELINE_TRANSFORM", 23, [null]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.mask = 0;
this.att = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_font_TextAttribute', function (ta) {
C$.$init$.apply(this);
this.mask = 1 << this.ordinal();
this.att = ta;
}, 1);

Clazz.newMeth(C$, 'forAttribute$java_text_AttributedCharacterIterator_Attribute', function (ta) {
for (var ea, $ea = 0, $$ea = C$.atts; $ea<$$ea.length&&((ea=$$ea[$ea]),1);$ea++) {
if (ea.att === ta ) {
return ea;
}}
return null;
}, 1);

Clazz.newMeth(C$, 'toString', function () {
return this.name().substring(1).toLowerCase();
});

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})();
//Created 2018-02-08 10:03:12