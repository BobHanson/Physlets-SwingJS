(function(){var P$=Clazz.newPackage("swingjs.api.js"),I$=[];
var C$=Clazz.newClass(P$, "HTML5CanvasContext2D");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getBuf8', function (imageData) {
{
return imageData.data
}
}, 1);

Clazz.newMeth(C$, 'putImageData', function (ctx, imageData, x, y) {
{
ctx.putImageData(imageData, x, y);
}
}, 1);

Clazz.newMeth(C$, 'push', function (ctx, map) {
{
(ctx._aSaved || (ctx._aSaved = [])).push(map);
return ctx._aSaved.length;
}
}, 1);

Clazz.newMeth(C$, 'pop', function (ctx) {
{
return (ctx._aSaved && ctx._aSaved.length > 0 ? ctx._aSaved.pop() : null);
}
return null;
}, 1);

Clazz.newMeth(C$, 'getSavedLevel', function (ctx) {
{
return (ctx._aSaved ? ctx._aSaved.length : 0);
}
}, 1);

Clazz.newMeth(C$, 'stretchImage', function (ctx, img, sx, sy, swidth, sheight, dx, dy, dwidth, dheight) {
{
ctx.drawImage(img, sx, sy, swidth, sheight, dx, dy, dwidth, dheight);
}
}, 1);

Clazz.newMeth(C$, 'getImageData', function (ctx, x, y, width, height) {
{
return ctx.getImageData(x, y, width, height);
}
}, 1);

Clazz.newMeth(C$, 'setLineWidth', function (ctx, d) {
{
ctx.lineWidth = d;
}
}, 1);

Clazz.newMeth(C$, 'setFont', function (ctx, canvasFont) {
{
ctx.font = canvasFont;
}
}, 1);

Clazz.newMeth(C$, 'setColor', function (ctx, s) {
{
ctx.fillStyle = ctx.strokeStyle = s;
}
}, 1);

Clazz.newMeth(C$, 'setFillStyle', function (ctx, s) {
{
ctx.fillStyle = s;
}
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:47
