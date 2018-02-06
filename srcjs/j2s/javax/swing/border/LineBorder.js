(function(){var P$=Clazz.newPackage("javax.swing.border"),I$=[['java.awt.Color',['java.awt.geom.RoundRectangle2D','.Float'],['java.awt.geom.Rectangle2D','.Float'],['java.awt.geom.Path2D','.Float'],'java.awt.Insets']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "LineBorder", null, 'javax.swing.border.AbstractBorder');
C$.blackLine = null;
C$.grayLine = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.thickness = 0;
this.lineColor = null;
this.roundedCorners = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'createBlackLineBorder', function () {
if (C$.blackLine == null ) {
C$.blackLine = Clazz.new_(C$.c$$java_awt_Color$I,[(I$[1]||$incl$(1)).black, 1]);
}return C$.blackLine;
}, 1);

Clazz.newMeth(C$, 'createGrayLineBorder', function () {
if (C$.grayLine == null ) {
C$.grayLine = Clazz.new_(C$.c$$java_awt_Color$I,[(I$[1]||$incl$(1)).gray, 1]);
}return C$.grayLine;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Color', function (color) {
C$.c$$java_awt_Color$I$Z.apply(this, [color, 1, false]);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Color$I', function (color, thickness) {
C$.c$$java_awt_Color$I$Z.apply(this, [color, thickness, false]);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Color$I$Z', function (color, thickness, roundedCorners) {
Clazz.super_(C$, this,1);
this.lineColor = color;
this.thickness = thickness;
this.roundedCorners = roundedCorners;
}, 1);

Clazz.newMeth(C$, 'paintBorder$java_awt_Component$java_awt_Graphics$I$I$I$I', function (c, g, x, y, width, height) {
if ((this.thickness > 0) && (Clazz.instanceOf(g, "java.awt.Graphics2D")) ) {
var g2d = g;
var oldColor = g2d.getColor();
g2d.setColor$java_awt_Color(this.lineColor);
var outer;
var inner;
var offs = this.thickness;
var size = offs + offs;
if (this.roundedCorners) {
var arc = offs + size;
outer = Clazz.new_((I$[2]||$incl$(2)).c$$F$F$F$F$F$F,[x, y, width, height, arc, arc]);
inner = Clazz.new_((I$[2]||$incl$(2)).c$$F$F$F$F$F$F,[x + offs, y + offs, width - size, height - size, arc, arc]);
} else {
outer = Clazz.new_((I$[3]||$incl$(3)).c$$F$F$F$F,[x, y, width, height]);
inner = Clazz.new_((I$[3]||$incl$(3)).c$$F$F$F$F,[x + offs, y + offs, width - size, height - size]);
}var path = Clazz.new_((I$[4]||$incl$(4)).c$$I,[0]);
path.append$java_awt_Shape$Z(outer, false);
path.append$java_awt_Shape$Z(inner, false);
g2d.fill$java_awt_Shape(path);
g2d.setColor$java_awt_Color(oldColor);
}});

Clazz.newMeth(C$, 'getBorderInsets$java_awt_Component', function (c) {
return Clazz.new_((I$[5]||$incl$(5)).c$$I$I$I$I,[this.thickness, this.thickness, this.thickness, this.thickness]);
});

Clazz.newMeth(C$, 'getBorderInsets$java_awt_Component$java_awt_Insets', function (c, insets) {
insets.left = insets.top = insets.right = insets.bottom = this.thickness;
return insets;
});

Clazz.newMeth(C$, 'getLineColor', function () {
return this.lineColor;
});

Clazz.newMeth(C$, 'getThickness', function () {
return this.thickness;
});

Clazz.newMeth(C$, 'getRoundedCorners', function () {
return this.roundedCorners;
});

Clazz.newMeth(C$, 'isBorderOpaque', function () {
return !this.roundedCorners;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:46
