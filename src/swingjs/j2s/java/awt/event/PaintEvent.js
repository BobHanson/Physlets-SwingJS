(function(){var P$=Clazz.newPackage("java.awt.event"),I$=[];
var C$=Clazz.newClass(P$, "PaintEvent", null, 'java.awt.event.ComponentEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.updateRect = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Component$I$java_awt_Rectangle', function (source, id, updateRect) {
C$.superclazz.c$$java_awt_Component$I.apply(this, [source, id]);
C$.$init$.apply(this);
this.updateRect=updateRect;
}, 1);

Clazz.newMeth(C$, 'getUpdateRect', function () {
return this.updateRect;
});

Clazz.newMeth(C$, 'setUpdateRect$java_awt_Rectangle', function (updateRect) {
this.updateRect=updateRect;
});

Clazz.newMeth(C$, 'paramString', function () {
var typeStr;
switch (this.id) {
case 800:
typeStr="PAINT";
break;
case 801:
typeStr="UPDATE";
break;
default:
typeStr="unknown type";
}
return typeStr + ",updateRect=" + (this.updateRect != null  ? this.updateRect.toString() : "null") ;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:18