(function(){var P$=Clazz.newPackage("sun.awt.dnd"),I$=[];
var C$=Clazz.newClass(P$, "SunDropTargetEvent", null, 'java.awt.event.MouseEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Component$I$I$I$O', function (source, id, x, y, dispatcher) {
C$.superclazz.c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I.apply(this, [source, id, System.currentTimeMillis(), 0, x, y, 0, 0, 0, false, 0]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'dispatch', function () {
});

Clazz.newMeth(C$, 'consume', function () {
var was_consumed = this.isConsumed();
C$.superclazz.prototype.consume.apply(this, []);
});

Clazz.newMeth(C$, 'paramString', function () {
var typeStr = null;
switch (this.id) {
case 502:
typeStr = "MOUSE_DROPPED";
break;
default:
return C$.superclazz.prototype.paramString.apply(this, []);
}
return typeStr + ",(" + this.getX() + "," + this.getY() + ")" ;
});

Clazz.newMeth(C$);
})();
//Created 2018-01-05 07:08:09
