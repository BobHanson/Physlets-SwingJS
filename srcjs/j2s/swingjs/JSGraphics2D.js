(function(){var P$=Clazz.newPackage("swingjs"),I$=[['java.awt.RenderingHints','java.util.Hashtable','java.awt.geom.AffineTransform','java.awt.BasicStroke','swingjs.api.js.HTML5CanvasContext2D','swingjs.JSToolkit','swingjs.api.js.DOMNode','swingjs.JSUtil','java.awt.Font','java.awt.Toolkit','java.awt.Rectangle','java.awt.Color','java.lang.Boolean']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSGraphics2D", null, null, 'Cloneable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.backgroundPainted = false;
this.constrainX = 0;
this.constrainY = 0;
this.width = 0;
this.height = 0;
this.canvas = null;
this.ctx = null;
this.gc = null;
this.currentStroke = null;
this.currentClip = null;
this.currentComposite = null;
this.initialState = 0;
this.isShifted = false;
this.font = null;
this.hints = null;
this.$transform = null;
this.backgroundColor = null;
this.inPath = false;
this.foregroundColor = null;
this.imageData = null;
this.buf8 = null;
this.lastx = 0;
this.lasty = 0;
this.nx = 0;
this.ny = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O', function (canvas) {
C$.$init$.apply(this);
this.hints = Clazz.new_((I$[1]||$incl$(1)).c$$java_util_Map,[Clazz.new_((I$[2]||$incl$(2)))]);
this.canvas = canvas;
this.ctx = this.canvas.getContext("2d");
this.$transform = Clazz.new_((I$[3]||$incl$(3)));
this.setStroke$java_awt_Stroke(Clazz.new_((I$[4]||$incl$(4))));
{
this.gc = SwingJS;
this.width = canvas.width;
this.height = canvas.height;
}
}, 1);

Clazz.newMeth(C$, 'getDeviceConfiguration', function () {
return this.gc;
});

Clazz.newMeth(C$, 'drawLine$I$I$I$I', function (x0, y0, x1, y1) {
var inPath = this.inPath;
if (!inPath) this.ctx.beginPath();
this.ctx.moveTo(x0, y0);
this.ctx.lineTo(x1, y1);
if (!inPath) this.ctx.stroke();
});

Clazz.newMeth(C$, 'drawOval$I$I$I$I', function (left, top, width, height) {
if (width == height) p$.doCirc$I$I$I$Z.apply(this, [left, top, width, false]);
 else p$.doArc$D$D$D$D$D$D$Z.apply(this, [left, top, width, height, 0, 360, false]);
});

Clazz.newMeth(C$, 'fillOval$I$I$I$I', function (left, top, width, height) {
if (width == height) p$.doCirc$I$I$I$Z.apply(this, [left, top, width, true]);
 else p$.doArc$D$D$D$D$D$D$Z.apply(this, [left, top, width, height, 0, 360, true]);
});

Clazz.newMeth(C$, 'drawArc$I$I$I$I$I$I', function (x, y, width, height, startAngle, arcAngle) {
p$.doArc$D$D$D$D$D$D$Z.apply(this, [x, y, width, height, startAngle, arcAngle, false]);
});

Clazz.newMeth(C$, 'fillArc$I$I$I$I$I$I', function (centerX, centerY, width, height, startAngle, arcAngle) {
p$.doArc$D$D$D$D$D$D$Z.apply(this, [centerX, centerY, width, height, startAngle, arcAngle, true]);
});

Clazz.newMeth(C$, 'doCirc$I$I$I$Z', function (left, top, diameter, fill) {
var r = diameter / 2.0;
this.ctx.beginPath();
this.ctx.arc(left + r, top + r, r, 0, 6.283185307179586, false);
this.ctx.closePath();
if (fill) this.ctx.fill();
 else this.ctx.stroke();
});

Clazz.newMeth(C$, 'doArc$D$D$D$D$D$D$Z', function (x, y, width, height, startAngle, arcAngle, fill) {
this.ctx.save();
{
if (arcAngle < 0 ) {
startAngle += arcAngle;
arcAngle = -arcAngle;
}this.ctx.translate(x, y);
this.ctx.scale(width / 2, height / 2);
this.ctx.beginPath();
this.ctx.arc(1, 1, 1, p$.toRad$D.apply(this, [360 - startAngle]), p$.toRad$D.apply(this, [360 - arcAngle - startAngle ]), true);
if (fill) this.ctx.lineTo(1, 1);
}this.ctx.restore();
if (fill) this.ctx.fill();
 else this.ctx.stroke();
});

Clazz.newMeth(C$, 'toRad$D', function (a) {
return a * 0.017453292519943295;
});

Clazz.newMeth(C$, 'background$java_awt_Color', function (bgcolor) {
this.backgroundColor = bgcolor;
if (bgcolor == null ) {
if (!this.isShifted) this.ctx.translate(-0.5, -0.5);
this.isShifted = true;
return;
}this.clearRect$I$I$I$I(0, 0, this.width, this.height);
});

Clazz.newMeth(C$, 'drawPolygon$java_awt_Polygon', function (p) {
p$.doPoly$IA$IA$I$Z.apply(this, [p.xpoints, p.ypoints, p.npoints, false]);
});

Clazz.newMeth(C$, 'drawPolygon$IA$IA$I', function (axPoints, ayPoints, nPoints) {
p$.doPoly$IA$IA$I$Z.apply(this, [axPoints, ayPoints, nPoints, false]);
});

Clazz.newMeth(C$, 'doPoly$IA$IA$I$Z', function (axPoints, ayPoints, nPoints, doFill) {
this.ctx.beginPath();
this.ctx.moveTo(axPoints[0], ayPoints[0]);
for (var i = 1; i < nPoints; i++) this.ctx.lineTo(axPoints[i], ayPoints[i]);

if (doFill) {
this.ctx.fill();
} else {
this.ctx.lineTo(axPoints[0], ayPoints[0]);
this.ctx.stroke();
}});

Clazz.newMeth(C$, 'drawRect$I$I$I$I', function (x, y, width, height) {
this.ctx.beginPath();
this.ctx.rect(x, y, width, height);
this.ctx.stroke();
});

Clazz.newMeth(C$, 'fillPolygon$java_awt_Polygon', function (p) {
p$.doPoly$IA$IA$I$Z.apply(this, [p.xpoints, p.ypoints, p.npoints, true]);
});

Clazz.newMeth(C$, 'fillPolygon$IA$IA$I', function (axPoints, ayPoints, nPoints) {
p$.doPoly$IA$IA$I$Z.apply(this, [axPoints, ayPoints, nPoints, true]);
});

Clazz.newMeth(C$, 'fillRect$I$I$I$I', function (x, y, width, height) {
this.backgroundPainted = true;
this.ctx.fillRect(x, y, width, height);
});

Clazz.newMeth(C$, 'fill3DRect$I$I$I$I$Z', function (x, y, width, height, raised) {
var p = this.getPaint();
var c = this.getColor();
var brighter = c.brighter();
var darker = c.darker();
if (!raised) {
this.setColor$java_awt_Color(darker);
} else if (p !== c ) {
this.setColor$java_awt_Color(c);
}this.fillRect$I$I$I$I(x + 1, y + 1, width - 2, height - 2);
this.setColor$java_awt_Color(raised ? brighter : darker);
this.fillRect$I$I$I$I(x, y, 1, height);
this.fillRect$I$I$I$I(x + 1, y, width - 2, 1);
this.setColor$java_awt_Color(raised ? darker : brighter);
this.fillRect$I$I$I$I(x + 1, y + height - 1, width - 1, 1);
this.fillRect$I$I$I$I(x + width - 1, y, 1, height - 1);
this.setPaint$java_awt_Paint(p);
});

Clazz.newMeth(C$, 'setFont$java_awt_Font', function (font) {
if (font === this.font ) return;
this.font = font;
if (font != null ) (I$[5]||$incl$(5)).setFont(this.ctx, (I$[6]||$incl$(6)).getCanvasFont$java_awt_Font(font));
});

Clazz.newMeth(C$, 'setStrokeBold$Z', function (tf) {
p$.setLineWidth$D.apply(this, [tf ? 2.0 : 1.0]);
});

Clazz.newMeth(C$, 'setLineWidth$D', function (d) {
(I$[5]||$incl$(5)).setLineWidth(this.ctx, d);
});

Clazz.newMeth(C$, 'canDoLineTo', function () {
return true;
});

Clazz.newMeth(C$, 'doStroke$Z', function (isBegin) {
this.inPath = isBegin;
if (isBegin) {
this.ctx.beginPath();
} else {
this.ctx.stroke();
}});

Clazz.newMeth(C$, 'lineTo$I$I', function (x2, y2) {
this.ctx.lineTo(x2, y2);
});

Clazz.newMeth(C$, 'clip$java_awt_Shape', function (s) {
p$.doShape$java_awt_Shape.apply(this, [s]);
this.currentClip = s;
this.ctx.clip();
});

Clazz.newMeth(C$, 'draw$java_awt_Shape', function (s) {
p$.doShape$java_awt_Shape.apply(this, [s]);
this.ctx.stroke();
});

Clazz.newMeth(C$, 'doShape$java_awt_Shape', function (s) {
this.ctx.beginPath();
var pts = Clazz.array(Double.TYPE, [6]);
var pi = s.getPathIterator$java_awt_geom_AffineTransform(null);
while (!pi.isDone()){
switch (pi.currentSegment$DA(pts)) {
case 0:
this.ctx.moveTo(pts[0], pts[1]);
break;
case 1:
this.ctx.lineTo(pts[0], pts[1]);
break;
case 2:
this.ctx.quadraticCurveTo(pts[0], pts[1], pts[2], pts[3]);
break;
case 3:
this.ctx.bezierCurveTo(pts[0], pts[1], pts[2], pts[3], pts[4], pts[5]);
break;
case 4:
this.ctx.closePath();
break;
}
pi.next();
}
return pi.getWindingRule();
});

Clazz.newMeth(C$, 'fill$java_awt_Shape', function (s) {
if (p$.doShape$java_awt_Shape.apply(this, [s]) == 0) {
this.ctx.fill("evenodd");
}
 else this.ctx.fill();
});

Clazz.newMeth(C$, 'drawImage$java_awt_Image$I$I$java_awt_image_ImageObserver', function (img, x, y, observer) {
return this.drawImagePriv$java_awt_Image$I$I$java_awt_image_ImageObserver(img, x, y, observer);
});

Clazz.newMeth(C$, 'drawImage$java_awt_Image$I$I$I$I$java_awt_image_ImageObserver', function (img, x, y, width, height, observer) {
this.backgroundPainted = true;
if (img != null ) {
var imgNode = p$.getImageNode$java_awt_Image.apply(this, [img]);
if (imgNode != null ) this.ctx.drawImage(imgNode, x, y, width, height);
if (observer != null ) p$.observe$java_awt_Image$java_awt_image_ImageObserver$Z.apply(this, [img, observer, imgNode != null ]);
}return true;
});

Clazz.newMeth(C$, 'getImageNode$java_awt_Image', function (img) {
this.backgroundPainted = true;
var imgNode = (I$[7]||$incl$(7)).getImageNode(img);
return (imgNode == null  ? (I$[6]||$incl$(6)).getCompositor().createImageNode$java_awt_Image(img) : imgNode);
});

Clazz.newMeth(C$, 'observe$java_awt_Image$java_awt_image_ImageObserver$Z', function (img, observer, isOK) {
observer.imageUpdate$java_awt_Image$I$I$I$I$I(img, (isOK ? 0 : 192), -1, -1, -1, -1);
});

Clazz.newMeth(C$, 'drawImage$java_awt_Image$I$I$java_awt_Color$java_awt_image_ImageObserver', function (img, x, y, bgcolor, observer) {
this.backgroundPainted = true;
(I$[8]||$incl$(8)).notImplemented$S(null);
return this.drawImage$java_awt_Image$I$I$java_awt_image_ImageObserver(img, x, y, observer);
});

Clazz.newMeth(C$, 'drawImage$java_awt_Image$I$I$I$I$java_awt_Color$java_awt_image_ImageObserver', function (img, x, y, width, height, bgcolor, observer) {
this.backgroundPainted = true;
(I$[8]||$incl$(8)).notImplemented$S(null);
return this.drawImage$java_awt_Image$I$I$I$I$java_awt_image_ImageObserver(img, x, y, width, height, observer);
});

Clazz.newMeth(C$, 'drawImage$java_awt_Image$I$I$I$I$I$I$I$I$java_awt_image_ImageObserver', function (img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer) {
this.backgroundPainted = true;
if (img != null ) {
var bytes = null;
var imgNode = p$.getImageNode$java_awt_Image.apply(this, [img]);
if (imgNode != null ) (I$[5]||$incl$(5)).stretchImage(this.ctx, imgNode, sx1, sy1, sx2 - sx1, sy2 - sy1, dx1, dy1, dx2 - dx1, dy2 - dy1);
if (observer != null ) p$.observe$java_awt_Image$java_awt_image_ImageObserver$Z.apply(this, [img, observer, imgNode != null ]);
}return true;
});

Clazz.newMeth(C$, 'drawImage$java_awt_Image$I$I$I$I$I$I$I$I$java_awt_Color$java_awt_image_ImageObserver', function (img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer) {
(I$[8]||$incl$(8)).notImplemented$S(null);
return this.drawImage$java_awt_Image$I$I$I$I$I$I$I$I$java_awt_image_ImageObserver(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
});

Clazz.newMeth(C$, 'drawImage$java_awt_Image$java_awt_geom_AffineTransform$java_awt_image_ImageObserver', function (img, xform, obs) {
return p$.drawImageXT$java_awt_Image$java_awt_geom_AffineTransform$java_awt_image_ImageObserver.apply(this, [img, xform, obs]);
});

Clazz.newMeth(C$, 'drawRenderedImage$java_awt_image_RenderedImage$java_awt_geom_AffineTransform', function (img, xform) {
p$.drawImageXT$java_awt_Image$java_awt_geom_AffineTransform$java_awt_image_ImageObserver.apply(this, [img, xform, null]);
});

Clazz.newMeth(C$, 'drawRenderableImage$java_awt_image_renderable_RenderableImage$java_awt_geom_AffineTransform', function (img, xform) {
p$.drawImageXT$java_awt_Image$java_awt_geom_AffineTransform$java_awt_image_ImageObserver.apply(this, [img, xform, null]);
});

Clazz.newMeth(C$, 'drawImageXT$java_awt_Image$java_awt_geom_AffineTransform$java_awt_image_ImageObserver', function (img, xform, obs) {
this.ctx.save();
p$.transformCTX$java_awt_geom_AffineTransform.apply(this, [xform]);
var ret = this.drawImagePriv$java_awt_Image$I$I$java_awt_image_ImageObserver(img, 0, 0, obs);
this.ctx.restore();
return ret;
});

Clazz.newMeth(C$, 'drawImagePriv$java_awt_Image$I$I$java_awt_image_ImageObserver', function (img, x, y, observer) {
this.backgroundPainted = true;
if (img != null ) {
var pixels = null;
var isRGB = false;
{
pixels = img._pix;
isRGB = (img.imageType == 1);
}
var imgNode = null;
var width = (img).getRaster().getWidth();
var height = (img).getRaster().getHeight();
if (pixels == null ) {
if ((imgNode = p$.getImageNode$java_awt_Image.apply(this, [img])) != null ) this.ctx.drawImage(imgNode, x, y, width, height);
} else {
if (this.buf8 == null  || x != this.lastx  || y != this.lasty  || this.nx != width  || this.ny != height ) {
this.imageData = (I$[5]||$incl$(5)).getImageData(this.ctx, x, y, width, height);
this.buf8 = (I$[5]||$incl$(5)).getBuf8(this.imageData);
this.lastx = x;
this.lasty = y;
this.nx = width;
this.ny = height;
}for (var pt = 0, i = 0, n = Math.min((this.buf8.length/4|0), pixels.length); i < n; i++) {
var argb = pixels[i];
this.buf8[pt++] = (argb >> 16) & 255;
this.buf8[pt++] = (argb >> 8) & 255;
this.buf8[pt++] = argb & 255;
this.buf8[pt++] = (isRGB ? 255 : (argb >> 24) & 255);
}
(I$[5]||$incl$(5)).putImageData(this.ctx, this.imageData, x, y);
}if (observer != null ) p$.observe$java_awt_Image$java_awt_image_ImageObserver$Z.apply(this, [img, observer, imgNode != null ]);
}return true;
});

Clazz.newMeth(C$, 'hit$java_awt_Rectangle$java_awt_Shape$Z', function (rect, s, onStroke) {
(I$[8]||$incl$(8)).notImplemented$S(null);
return false;
});

Clazz.newMeth(C$, 'getStroke', function () {
return this.currentStroke;
});

Clazz.newMeth(C$, 'setStroke$java_awt_Stroke', function (s) {
if (!(Clazz.instanceOf(s, "java.awt.BasicStroke"))) return;
var b = this.currentStroke = s;
var dash = b.getDashArray();
var idash = Clazz.array(Integer.TYPE, [dash == null  ? 0 : dash.length]);
for (var i = idash.length; --i >= 0; ) idash[i] = (dash[i]|0);

this.ctx.setLineDash(idash);
p$.setLineWidth$D.apply(this, [b.getLineWidth()]);
var lineCap;
var lineJoin;
var miterLimit = -1;
switch (b.getEndCap()) {
case 0:
lineCap = "butt";
break;
case 2:
lineCap = "square";
break;
case 1:
default:
lineCap = "round";
}
switch (b.getLineJoin()) {
case 2:
lineJoin = "bevel";
break;
case 0:
lineJoin = "miter";
miterLimit = b.getMiterLimit();
break;
case 1:
lineJoin = "round";
}
{
this.ctx.lineCap = lineCap; this.ctx.lineJoin = lineJoin; if (miterLimit >= 0) this.ctx.miterLimit = miterLimit;
}
});

Clazz.newMeth(C$, 'setRenderingHint$java_awt_RenderingHints_Key$O', function (hintKey, hintValue) {
this.hints.put$O$O(hintKey, hintValue);
});

Clazz.newMeth(C$, 'getRenderingHint$java_awt_RenderingHints_Key', function (hintKey) {
return this.hints.get$O(hintKey);
});

Clazz.newMeth(C$, 'setRenderingHints$java_util_Map', function (hints) {
this.hints = Clazz.new_((I$[1]||$incl$(1)).c$$java_util_Map,[hints]);
});

Clazz.newMeth(C$, 'addRenderingHints$java_util_Map', function (hints) {
for (var e, $e = hints.entrySet().iterator(); $e.hasNext()&&((e=$e.next()),1);) this.hints.put$O$O(e.getKey(), e.getValue());

});

Clazz.newMeth(C$, 'getRenderingHints', function () {
return this.hints;
});

Clazz.newMeth(C$, 'setBackground$java_awt_Color', function (color) {
this.background$java_awt_Color(color);
});

Clazz.newMeth(C$, 'getBackground', function () {
return this.backgroundColor;
});

Clazz.newMeth(C$, 'getColor', function () {
return this.foregroundColor;
});

Clazz.newMeth(C$, 'setColor$java_awt_Color', function (c) {
this.foregroundColor = c;
p$.setGraphicsColor$java_awt_Color.apply(this, [c]);
});

Clazz.newMeth(C$, 'setPaint$java_awt_Paint', function (paint) {
this.setColor$java_awt_Color(paint);
});

Clazz.newMeth(C$, 'getFont', function () {
if (this.font == null ) this.font = Clazz.new_((I$[9]||$incl$(9)).c$$S$I$I,["Arial", 0, 12]);
return this.font;
});

Clazz.newMeth(C$, 'getFontMetrics', function () {
return this.getFontMetrics$java_awt_Font(this.getFont());
});

Clazz.newMeth(C$, 'getFontMetrics$java_awt_Font', function (f) {
return (I$[10]||$incl$(10)).getDefaultToolkit().getFontMetrics$java_awt_Font(f);
});

Clazz.newMeth(C$, 'clipRect$I$I$I$I', function (x, y, width, height) {
this.ctx.beginPath();
this.ctx.rect(x, y, width, height);
p$.setCurrentClip$I$I$I$I.apply(this, [x, y, width, height]);
this.ctx.clip();
});

Clazz.newMeth(C$, 'setClip$I$I$I$I', function (x, y, width, height) {
p$.setCurrentClip$I$I$I$I.apply(this, [x, y, width, height]);
this.ctx.beginPath();
this.ctx.rect(x, y, width, height);
this.ctx.clip();
});

Clazz.newMeth(C$, 'setCurrentClip$I$I$I$I', function (x, y, width, height) {
var r = (Clazz.instanceOf(this.currentClip, "java.awt.Rectangle") ? this.currentClip : null);
if (r == null  || r.x != x  || r.y != y  || r.width != width  || r.height != height ) this.currentClip = Clazz.new_((I$[11]||$incl$(11)).c$$I$I$I$I,[x, y, width, height]);
});

Clazz.newMeth(C$, 'setClip$java_awt_Shape', function (clip) {
this.currentClip = clip;
this.ctx.beginPath();
p$.doShape$java_awt_Shape.apply(this, [clip]);
this.ctx.clip();
});

Clazz.newMeth(C$, 'clearRect$I$I$I$I', function (x, y, width, height) {
this.ctx.clearRect(x, y, width, height);
p$.setGraphicsColor$java_awt_Color.apply(this, [this.backgroundColor == null  ? (I$[12]||$incl$(12)).WHITE : this.backgroundColor]);
this.fillRect$I$I$I$I(x, y, width, height);
p$.setGraphicsColor$java_awt_Color.apply(this, [this.foregroundColor]);
});

Clazz.newMeth(C$, 'setGraphicsColor$java_awt_Color', function (c) {
if (c == null ) return;
(I$[5]||$incl$(5)).setColor(this.ctx, (I$[6]||$incl$(6)).getCSSColor$java_awt_Color(c));
});

Clazz.newMeth(C$, 'drawPolyline$IA$IA$I', function (xPoints, yPoints, nPoints) {
if (nPoints < 2) return;
this.ctx.moveTo(xPoints[0], yPoints[0]);
for (var i = 1; i < nPoints; i++) {
this.ctx.lineTo(xPoints[i], yPoints[i]);
}
this.ctx.stroke();
});

Clazz.newMeth(C$, 'copyArea$I$I$I$I$I$I', function (x, y, width, height, dx, dy) {
(I$[8]||$incl$(8)).notImplemented$S(null);
});

Clazz.newMeth(C$, 'drawRoundRect$I$I$I$I$I$I', function (x, y, width, height, arcWidth, arcHeight) {
(I$[8]||$incl$(8)).notImplemented$S(null);
this.drawRect$I$I$I$I(x, y, width, height);
});

Clazz.newMeth(C$, 'fillRoundRect$I$I$I$I$I$I', function (x, y, width, height, arcWidth, arcHeight) {
(I$[8]||$incl$(8)).notImplemented$S(null);
this.fillRect$I$I$I$I(x, y, width, height);
});

Clazz.newMeth(C$, 'getClip', function () {
return this.currentClip == null  ? p$.getClipBoundsImpl.apply(this, []) : this.currentClip;
});

Clazz.newMeth(C$, 'drawString$S$I$I', function (s, x, y) {
this.ctx.fillText(s, x, y);
});

Clazz.newMeth(C$, 'drawString$S$F$F', function (str, x, y) {
this.ctx.fillText(str, x, y);
});

Clazz.newMeth(C$, 'drawString$java_text_AttributedCharacterIterator$I$I', function (iterator, x, y) {
(I$[8]||$incl$(8)).notImplemented$S(null);
});

Clazz.newMeth(C$, 'drawString$java_text_AttributedCharacterIterator$F$F', function (iterator, x, y) {
(I$[8]||$incl$(8)).notImplemented$S(null);
});

Clazz.newMeth(C$, 'translate$D$D', function (tx, ty) {
(I$[8]||$incl$(8)).notImplemented$S(null);
});

Clazz.newMeth(C$, 'shear$D$D', function (shx, shy) {
(I$[8]||$incl$(8)).notImplemented$S(null);
});

Clazz.newMeth(C$, 'translate$I$I', function (x, y) {
this.ctx.translate(x, y);
this.$transform.translate$D$D(x, y);
});

Clazz.newMeth(C$, 'rotate$D', function (radians) {
this.ctx.rotate(radians);
this.$transform.rotate$D(radians);
});

Clazz.newMeth(C$, 'rotate$D$D$D', function (theta, x, y) {
this.ctx.translate(x, y);
this.ctx.rotate(theta);
this.ctx.translate(-x, -y);
this.$transform.rotate$D$D$D(theta, x, y);
});

Clazz.newMeth(C$, 'scale$D$D', function (sx, sy) {
this.ctx.scale(sx, sy);
this.$transform.scale$D$D(sx, sy);
});

Clazz.newMeth(C$, 'transform$java_awt_geom_AffineTransform', function (t) {
p$.transformCTX$java_awt_geom_AffineTransform.apply(this, [t]);
this.$transform.concatenate$java_awt_geom_AffineTransform(t);
});

Clazz.newMeth(C$, 'transformCTX$java_awt_geom_AffineTransform', function (t) {
{
this.ctx.transform (t.m00, t.m10, t.m01, t.m11, t.m02, t.m12);
}
});

Clazz.newMeth(C$, 'setTransform$java_awt_geom_AffineTransform', function (t) {
{
this.ctx.setTransform (t.m00, t.m10, t.m01, t.m11, t.m02, t.m12);
}
this.$transform.setTransform$java_awt_geom_AffineTransform(t);
});

Clazz.newMeth(C$, 'getTransform', function () {
return this.$transform.clone();
});

Clazz.newMeth(C$, 'getPaint', function () {
return this.getColor();
});

Clazz.newMeth(C$, 'getFontRenderContext', function () {
return this.getFontMetrics$java_awt_Font(this.getFont()).getFontRenderContext();
});

Clazz.newMeth(C$, 'setPaintMode', function () {
(I$[8]||$incl$(8)).notImplemented$S(null);
});

Clazz.newMeth(C$, 'setXORMode$java_awt_Color', function (c1) {
(I$[8]||$incl$(8)).notImplemented$S(null);
});

Clazz.newMeth(C$, 'getClipBounds', function () {
return this.getClipBounds$java_awt_Rectangle(null);
});

Clazz.newMeth(C$, 'getClipBounds$java_awt_Rectangle', function (r) {
var clipRect = p$.getClipBoundsImpl.apply(this, []);
if (r == null ) {
r = clipRect;
} else {
r.x = clipRect.x;
r.y = clipRect.y;
r.width = clipRect.width;
r.height = clipRect.height;
}return r;
});

Clazz.newMeth(C$, 'getClipBoundsImpl', function () {
if (this.currentClip == null ) {
this.currentClip = Clazz.new_((I$[11]||$incl$(11)).c$$I$I$I$I,[0, 0, this.width, this.height]);
}return this.currentClip.getBounds();
});

Clazz.newMeth(C$, 'setComposite$java_awt_Composite', function (comp) {
if (comp === this.currentComposite ) return;
var newRule = 0;
var isValid = (comp == null  || this.currentComposite == null   || (Clazz.instanceOf(comp, "java.awt.AlphaComposite")) && (newRule = (comp).getRule()) != this.currentComposite.getRule()  );
if (isValid && (I$[6]||$incl$(6)).setGraphicsCompositeAlpha$swingjs_JSGraphics2D$I(this, newRule) ) {
this.currentComposite = comp;
}this.setAlpha$F(comp == null  ? 1 : (comp).getAlpha());
});

Clazz.newMeth(C$, 'getComposite', function () {
return this.currentComposite;
});

Clazz.newMeth(C$, 'drawImage$java_awt_image_BufferedImage$java_awt_image_BufferedImageOp$I$I', function (img, op, x, y) {
(I$[6]||$incl$(6)).drawImageOp$swingjs_JSGraphics2D$java_awt_image_BufferedImage$java_awt_image_BufferedImageOp$I$I(this, img, op, x, y);
});

Clazz.newMeth(C$, 'setAlpha$F', function (f) {
{
this.ctx.globalAlpha = f;
}
});

Clazz.newMeth(C$, 'getCanvas', function () {
return this.canvas;
});

Clazz.newMeth(C$, 'isBackgroundPainted', function () {
return this.backgroundPainted;
});

Clazz.newMeth(C$, 'mark', function () {
this.ctx.save();
var map = Clazz.array(java.lang.Object, [7]);
var alpha = 0;
{
alpha = this.ctx.globalAlpha;
}
map[0] = Float.$valueOf(alpha);
map[1] = this.currentComposite;
map[2] = this.currentStroke;
map[3] = this.$transform;
map[4] = this.font;
map[5] = this.currentClip;
map[6] = (this.backgroundPainted ? (I$[13]||$incl$(13)).TRUE : (I$[13]||$incl$(13)).FALSE);
this.backgroundPainted = false;
return (I$[5]||$incl$(5)).push(this.ctx, map);
});

Clazz.newMeth(C$, 'reset$I', function (n0) {
if (n0 < 1) n0 = 1;
while ((I$[5]||$incl$(5)).getSavedLevel(this.ctx) >= n0){
var map = (I$[5]||$incl$(5)).pop(this.ctx);
this.setComposite$java_awt_Composite(map[1]);
var alpha = map[0];
if (alpha != null ) this.setAlpha$F(alpha.floatValue());
this.setStroke$java_awt_Stroke(map[2]);
this.setTransform$java_awt_geom_AffineTransform(map[3]);
this.setFont$java_awt_Font(map[4]);
this.currentClip = map[5];
this.backgroundPainted = (map[6]).booleanValue();
this.ctx.restore();
}
});

Clazz.newMeth(C$, 'create$I$I$I$I', function (x, y, width, height) {
var g = this.create();
if (g == null ) return null;
g.translate$I$I(x, y);
g.clipRect$I$I$I$I(0, 0, width, height);
return g;
});

Clazz.newMeth(C$, 'create', function () {
return this.clone();
});

Clazz.newMeth(C$, 'clone', function () {
var n = this.mark();
var g = this;
{
g = Clazz.clone(this);
}
g.$transform = Clazz.new_((I$[3]||$incl$(3)).c$$java_awt_geom_AffineTransform,[this.$transform]);
if (this.hints != null ) {
g.hints = this.hints.clone();
}g.setStroke$java_awt_Stroke(this.currentStroke.clone());
g.initialState = n;
return g;
});

Clazz.newMeth(C$, 'dispose', function () {
this.reset$I(this.initialState);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:29
