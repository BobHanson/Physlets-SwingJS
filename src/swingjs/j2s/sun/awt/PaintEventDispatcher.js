(function(){var P$=Clazz.newPackage("sun.awt"),I$=[['java.awt.event.PaintEvent','java.awt.Rectangle']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PaintEventDispatcher");
C$.dispatcher = null;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'setPaintEventDispatcher$sun_awt_PaintEventDispatcher', function (dispatcher) {
{
C$.dispatcher=dispatcher;
}}, 1);

Clazz.newMeth(C$, 'getPaintEventDispatcher', function () {
{
if (C$.dispatcher == null ) {
C$.dispatcher=Clazz.new_(C$);
}return C$.dispatcher;
}}, 1);

Clazz.newMeth(C$, 'createPaintEvent$java_awt_Component$I$I$I$I', function (target, x, y, w, h) {
return Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Component$I$java_awt_Rectangle,[target, 800, Clazz.new_((I$[2]||$incl$(2)).c$$I$I$I$I,[x, y, w, h])]);
});

Clazz.newMeth(C$, 'shouldDoNativeBackgroundErase$java_awt_Component', function (c) {
return true;
});

Clazz.newMeth(C$, 'queueSurfaceDataReplacing$java_awt_Component$Runnable', function (c, r) {
return false;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:22
