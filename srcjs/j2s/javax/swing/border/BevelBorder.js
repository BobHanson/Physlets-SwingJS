(function(){var P$=Clazz.newPackage("javax.swing.border"),I$=[['java.awt.Insets']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "BevelBorder", null, 'javax.swing.border.AbstractBorder');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.bevelType = 0;
this.highlightOuter = null;
this.highlightInner = null;
this.shadowInner = null;
this.shadowOuter = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (bevelType) {
Clazz.super_(C$, this,1);
this.bevelType = bevelType;
}, 1);

Clazz.newMeth(C$, 'c$$I$java_awt_Color$java_awt_Color', function (bevelType, highlight, shadow) {
C$.c$$I$java_awt_Color$java_awt_Color$java_awt_Color$java_awt_Color.apply(this, [bevelType, highlight.brighter(), highlight, shadow, shadow.brighter()]);
}, 1);

Clazz.newMeth(C$, 'c$$I$java_awt_Color$java_awt_Color$java_awt_Color$java_awt_Color', function (bevelType, highlightOuterColor, highlightInnerColor, shadowOuterColor, shadowInnerColor) {
C$.c$$I.apply(this, [bevelType]);
this.highlightOuter = highlightOuterColor;
this.highlightInner = highlightInnerColor;
this.shadowOuter = shadowOuterColor;
this.shadowInner = shadowInnerColor;
}, 1);

Clazz.newMeth(C$, 'paintBorder$java_awt_Component$java_awt_Graphics$I$I$I$I', function (c, g, x, y, width, height) {
if (this.bevelType == 0) {
this.paintRaisedBevel$java_awt_Component$java_awt_Graphics$I$I$I$I(c, g, x, y, width, height);
} else if (this.bevelType == 1) {
this.paintLoweredBevel$java_awt_Component$java_awt_Graphics$I$I$I$I(c, g, x, y, width, height);
}});

Clazz.newMeth(C$, 'getBorderInsets$java_awt_Component', function (c) {
return Clazz.new_((I$[1]||$incl$(1)).c$$I$I$I$I,[2, 2, 2, 2]);
});

Clazz.newMeth(C$, 'getBorderInsets$java_awt_Component$java_awt_Insets', function (c, insets) {
insets.left = insets.top = insets.right = insets.bottom = 2;
return insets;
});

Clazz.newMeth(C$, 'getHighlightOuterColor$java_awt_Component', function (c) {
var highlight = this.getHighlightOuterColor();
return highlight != null  ? highlight : c.getBackground().brighter().brighter();
});

Clazz.newMeth(C$, 'getHighlightInnerColor$java_awt_Component', function (c) {
var highlight = this.getHighlightInnerColor();
return highlight != null  ? highlight : c.getBackground().brighter();
});

Clazz.newMeth(C$, 'getShadowInnerColor$java_awt_Component', function (c) {
var shadow = this.getShadowInnerColor();
return shadow != null  ? shadow : c.getBackground().darker();
});

Clazz.newMeth(C$, 'getShadowOuterColor$java_awt_Component', function (c) {
var shadow = this.getShadowOuterColor();
return shadow != null  ? shadow : c.getBackground().darker().darker();
});

Clazz.newMeth(C$, 'getHighlightOuterColor', function () {
return this.highlightOuter;
});

Clazz.newMeth(C$, 'getHighlightInnerColor', function () {
return this.highlightInner;
});

Clazz.newMeth(C$, 'getShadowInnerColor', function () {
return this.shadowInner;
});

Clazz.newMeth(C$, 'getShadowOuterColor', function () {
return this.shadowOuter;
});

Clazz.newMeth(C$, 'getBevelType', function () {
return this.bevelType;
});

Clazz.newMeth(C$, 'isBorderOpaque', function () {
return true;
});

Clazz.newMeth(C$, 'paintRaisedBevel$java_awt_Component$java_awt_Graphics$I$I$I$I', function (c, g, x, y, width, height) {
var oldColor = g.getColor();
var h = height;
var w = width;
g.translate$I$I(x, y);
g.setColor$java_awt_Color(this.getHighlightOuterColor$java_awt_Component(c));
g.drawLine$I$I$I$I(0, 0, 0, h - 2);
g.drawLine$I$I$I$I(1, 0, w - 2, 0);
g.setColor$java_awt_Color(this.getHighlightInnerColor$java_awt_Component(c));
g.drawLine$I$I$I$I(1, 1, 1, h - 3);
g.drawLine$I$I$I$I(2, 1, w - 3, 1);
g.setColor$java_awt_Color(this.getShadowOuterColor$java_awt_Component(c));
g.drawLine$I$I$I$I(0, h - 1, w - 1, h - 1);
g.drawLine$I$I$I$I(w - 1, 0, w - 1, h - 2);
g.setColor$java_awt_Color(this.getShadowInnerColor$java_awt_Component(c));
g.drawLine$I$I$I$I(1, h - 2, w - 2, h - 2);
g.drawLine$I$I$I$I(w - 2, 1, w - 2, h - 3);
g.translate$I$I(-x, -y);
g.setColor$java_awt_Color(oldColor);
});

Clazz.newMeth(C$, 'paintLoweredBevel$java_awt_Component$java_awt_Graphics$I$I$I$I', function (c, g, x, y, width, height) {
var oldColor = g.getColor();
var h = height;
var w = width;
g.translate$I$I(x, y);
g.setColor$java_awt_Color(this.getShadowInnerColor$java_awt_Component(c));
g.drawLine$I$I$I$I(0, 0, 0, h - 1);
g.drawLine$I$I$I$I(1, 0, w - 1, 0);
g.setColor$java_awt_Color(this.getShadowOuterColor$java_awt_Component(c));
g.drawLine$I$I$I$I(1, 1, 1, h - 2);
g.drawLine$I$I$I$I(2, 1, w - 2, 1);
g.setColor$java_awt_Color(this.getHighlightOuterColor$java_awt_Component(c));
g.drawLine$I$I$I$I(1, h - 1, w - 1, h - 1);
g.drawLine$I$I$I$I(w - 1, 1, w - 1, h - 2);
g.setColor$java_awt_Color(this.getHighlightInnerColor$java_awt_Component(c));
g.drawLine$I$I$I$I(2, h - 2, w - 2, h - 2);
g.drawLine$I$I$I$I(w - 2, 2, w - 2, h - 3);
g.translate$I$I(-x, -y);
g.setColor$java_awt_Color(oldColor);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:45
