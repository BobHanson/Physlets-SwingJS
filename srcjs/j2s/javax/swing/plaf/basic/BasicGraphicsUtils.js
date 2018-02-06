(function(){var P$=Clazz.newPackage("javax.swing.plaf.basic"),I$=[['java.awt.Insets']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "BasicGraphicsUtils");
C$.GROOVE_INSETS = null;
C$.ETCHED_INSETS = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.GROOVE_INSETS = Clazz.new_((I$[1]||$incl$(1)).c$$I$I$I$I,[2, 2, 2, 2]);
C$.ETCHED_INSETS = Clazz.new_((I$[1]||$incl$(1)).c$$I$I$I$I,[2, 2, 2, 2]);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'drawEtchedRect$java_awt_Graphics$I$I$I$I$java_awt_Color$java_awt_Color$java_awt_Color$java_awt_Color', function (g, x, y, w, h, shadow, darkShadow, highlight, lightHighlight) {
var oldColor = g.getColor();
g.translate$I$I(x, y);
g.setColor$java_awt_Color(shadow);
g.drawLine$I$I$I$I(0, 0, w - 1, 0);
g.drawLine$I$I$I$I(0, 1, 0, h - 2);
g.setColor$java_awt_Color(darkShadow);
g.drawLine$I$I$I$I(1, 1, w - 3, 1);
g.drawLine$I$I$I$I(1, 2, 1, h - 3);
g.setColor$java_awt_Color(lightHighlight);
g.drawLine$I$I$I$I(w - 1, 0, w - 1, h - 1);
g.drawLine$I$I$I$I(0, h - 1, w - 1, h - 1);
g.setColor$java_awt_Color(highlight);
g.drawLine$I$I$I$I(w - 2, 1, w - 2, h - 3);
g.drawLine$I$I$I$I(1, h - 2, w - 2, h - 2);
g.translate$I$I(-x, -y);
g.setColor$java_awt_Color(oldColor);
}, 1);

Clazz.newMeth(C$, 'getEtchedInsets', function () {
return C$.ETCHED_INSETS;
}, 1);

Clazz.newMeth(C$, 'drawGroove$java_awt_Graphics$I$I$I$I$java_awt_Color$java_awt_Color', function (g, x, y, w, h, shadow, highlight) {
var oldColor = g.getColor();
g.translate$I$I(x, y);
g.setColor$java_awt_Color(shadow);
g.drawRect$I$I$I$I(0, 0, w - 2, h - 2);
g.setColor$java_awt_Color(highlight);
g.drawLine$I$I$I$I(1, h - 3, 1, 1);
g.drawLine$I$I$I$I(1, 1, w - 3, 1);
g.drawLine$I$I$I$I(0, h - 1, w - 1, h - 1);
g.drawLine$I$I$I$I(w - 1, h - 1, w - 1, 0);
g.translate$I$I(-x, -y);
g.setColor$java_awt_Color(oldColor);
}, 1);

Clazz.newMeth(C$, 'getGrooveInsets', function () {
return C$.GROOVE_INSETS;
}, 1);

Clazz.newMeth(C$, 'drawBezel$java_awt_Graphics$I$I$I$I$Z$Z$java_awt_Color$java_awt_Color$java_awt_Color$java_awt_Color', function (g, x, y, w, h, isPressed, isDefault, shadow, darkShadow, highlight, lightHighlight) {
var oldColor = g.getColor();
g.translate$I$I(x, y);
if (isPressed && isDefault ) {
g.setColor$java_awt_Color(darkShadow);
g.drawRect$I$I$I$I(0, 0, w - 1, h - 1);
g.setColor$java_awt_Color(shadow);
g.drawRect$I$I$I$I(1, 1, w - 3, h - 3);
} else if (isPressed) {
C$.drawLoweredBezel$java_awt_Graphics$I$I$I$I$java_awt_Color$java_awt_Color$java_awt_Color$java_awt_Color(g, x, y, w, h, shadow, darkShadow, highlight, lightHighlight);
} else if (isDefault) {
g.setColor$java_awt_Color(darkShadow);
g.drawRect$I$I$I$I(0, 0, w - 1, h - 1);
g.setColor$java_awt_Color(lightHighlight);
g.drawLine$I$I$I$I(1, 1, 1, h - 3);
g.drawLine$I$I$I$I(2, 1, w - 3, 1);
g.setColor$java_awt_Color(highlight);
g.drawLine$I$I$I$I(2, 2, 2, h - 4);
g.drawLine$I$I$I$I(3, 2, w - 4, 2);
g.setColor$java_awt_Color(shadow);
g.drawLine$I$I$I$I(2, h - 3, w - 3, h - 3);
g.drawLine$I$I$I$I(w - 3, 2, w - 3, h - 4);
g.setColor$java_awt_Color(darkShadow);
g.drawLine$I$I$I$I(1, h - 2, w - 2, h - 2);
g.drawLine$I$I$I$I(w - 2, h - 2, w - 2, 1);
} else {
g.setColor$java_awt_Color(lightHighlight);
g.drawLine$I$I$I$I(0, 0, 0, h - 1);
g.drawLine$I$I$I$I(1, 0, w - 2, 0);
g.setColor$java_awt_Color(highlight);
g.drawLine$I$I$I$I(1, 1, 1, h - 3);
g.drawLine$I$I$I$I(2, 1, w - 3, 1);
g.setColor$java_awt_Color(shadow);
g.drawLine$I$I$I$I(1, h - 2, w - 2, h - 2);
g.drawLine$I$I$I$I(w - 2, 1, w - 2, h - 3);
g.setColor$java_awt_Color(darkShadow);
g.drawLine$I$I$I$I(0, h - 1, w - 1, h - 1);
g.drawLine$I$I$I$I(w - 1, h - 1, w - 1, 0);
}g.translate$I$I(-x, -y);
g.setColor$java_awt_Color(oldColor);
}, 1);

Clazz.newMeth(C$, 'drawLoweredBezel$java_awt_Graphics$I$I$I$I$java_awt_Color$java_awt_Color$java_awt_Color$java_awt_Color', function (g, x, y, w, h, shadow, darkShadow, highlight, lightHighlight) {
g.setColor$java_awt_Color(darkShadow);
g.drawLine$I$I$I$I(0, 0, 0, h - 1);
g.drawLine$I$I$I$I(1, 0, w - 2, 0);
g.setColor$java_awt_Color(shadow);
g.drawLine$I$I$I$I(1, 1, 1, h - 2);
g.drawLine$I$I$I$I(1, 1, w - 3, 1);
g.setColor$java_awt_Color(lightHighlight);
g.drawLine$I$I$I$I(0, h - 1, w - 1, h - 1);
g.drawLine$I$I$I$I(w - 1, h - 1, w - 1, 0);
g.setColor$java_awt_Color(highlight);
g.drawLine$I$I$I$I(1, h - 2, w - 2, h - 2);
g.drawLine$I$I$I$I(w - 2, h - 2, w - 2, 1);
}, 1);

Clazz.newMeth(C$, 'drawDashedRect$java_awt_Graphics$I$I$I$I', function (g, x, y, width, height) {
var vx;
var vy;
for (vx = x; vx < (x + width); vx = vx+(2)) {
g.fillRect$I$I$I$I(vx, y, 1, 1);
g.fillRect$I$I$I$I(vx, y + height - 1, 1, 1);
}
for (vy = y; vy < (y + height); vy = vy+(2)) {
g.fillRect$I$I$I$I(x, vy, 1, 1);
g.fillRect$I$I$I$I(x + width - 1, vy, 1, 1);
}
}, 1);

Clazz.newMeth(C$, 'isLeftToRight$java_awt_Component', function (c) {
return c.getComponentOrientation().isLeftToRight();
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:53
