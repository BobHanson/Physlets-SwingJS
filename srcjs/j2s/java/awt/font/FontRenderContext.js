(function(){var P$=Clazz.newPackage("java.awt.font"),I$=[['java.awt.geom.AffineTransform','java.awt.RenderingHints']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FontRenderContext");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.tx = null;
this.aaHintValue = null;
this.fmHintValue = null;
this.defaulting = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.defaulting = true;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_geom_AffineTransform$Z$Z', function (tx, isAntiAliased, usesFractionalMetrics) {
C$.$init$.apply(this);
if (tx != null  && !tx.isIdentity() ) {
this.tx = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_geom_AffineTransform,[tx]);
}}, 1);

Clazz.newMeth(C$, 'c$$java_awt_geom_AffineTransform$O$O', function (tx, aaHint, fmHint) {
C$.$init$.apply(this);
if (tx != null  && !tx.isIdentity() ) {
this.tx = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_geom_AffineTransform,[tx]);
}this.aaHintValue = aaHint;
this.fmHintValue = fmHint;
}, 1);

Clazz.newMeth(C$, 'isTransformed', function () {
if (!this.defaulting) {
return this.tx != null ;
} else {
return !this.getTransform().isIdentity();
}});

Clazz.newMeth(C$, 'getTransformType', function () {
if (!this.defaulting) {
if (this.tx == null ) {
return 0;
} else {
return this.tx.getType();
}} else {
return this.getTransform().getType();
}});

Clazz.newMeth(C$, 'getTransform', function () {
return (this.tx == null ) ? Clazz.new_((I$[1]||$incl$(1))) : Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_geom_AffineTransform,[this.tx]);
});

Clazz.newMeth(C$, 'isAntiAliased', function () {
return !(this.aaHintValue === (I$[2]||$incl$(2)).VALUE_TEXT_ANTIALIAS_OFF  || this.aaHintValue === (I$[2]||$incl$(2)).VALUE_TEXT_ANTIALIAS_DEFAULT  );
});

Clazz.newMeth(C$, 'usesFractionalMetrics', function () {
return !(this.fmHintValue === (I$[2]||$incl$(2)).VALUE_FRACTIONALMETRICS_OFF  || this.fmHintValue === (I$[2]||$incl$(2)).VALUE_FRACTIONALMETRICS_DEFAULT  );
});

Clazz.newMeth(C$, 'getAntiAliasingHint', function () {
if (this.defaulting) {
if (this.isAntiAliased()) {
return (I$[2]||$incl$(2)).VALUE_TEXT_ANTIALIAS_ON;
} else {
return (I$[2]||$incl$(2)).VALUE_TEXT_ANTIALIAS_OFF;
}}return this.aaHintValue;
});

Clazz.newMeth(C$, 'getFractionalMetricsHint', function () {
if (this.defaulting) {
if (this.usesFractionalMetrics()) {
return (I$[2]||$incl$(2)).VALUE_FRACTIONALMETRICS_ON;
} else {
return (I$[2]||$incl$(2)).VALUE_FRACTIONALMETRICS_OFF;
}}return this.fmHintValue;
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
try {
return this.equals$java_awt_font_FontRenderContext(obj);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.ClassCastException")){
return false;
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'equals$java_awt_font_FontRenderContext', function (rhs) {
if (this === rhs ) {
return true;
}if (rhs == null ) {
return false;
}if (!rhs.defaulting && !this.defaulting ) {
if (rhs.aaHintValue === this.aaHintValue  && rhs.fmHintValue === this.fmHintValue  ) {
return this.tx == null  ? rhs.tx == null  : this.tx.equals$O(rhs.tx);
}return false;
} else {
return rhs.getAntiAliasingHint() === this.getAntiAliasingHint()  && rhs.getFractionalMetricsHint() === this.getFractionalMetricsHint()   && rhs.getTransform().equals$O(this.getTransform()) ;
}});

Clazz.newMeth(C$, 'hashCode', function () {
var hash = this.tx == null  ? 0 : this.tx.hashCode();
if (this.defaulting) {
hash = hash+(this.getAntiAliasingHint().hashCode());
hash = hash+(this.getFractionalMetricsHint().hashCode());
} else {
hash = hash+(this.aaHintValue.hashCode());
hash = hash+(this.fmHintValue.hashCode());
}return hash;
});
})();
//Created 2018-02-06 08:58:19
