(function(){var P$=Clazz.newPackage("java.awt.dnd"),I$=[];
var C$=Clazz.newClass(P$, "DropTargetEvent", null, 'java.util.EventObject');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.context = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_dnd_DropTargetContext', function (dtc) {
C$.superclazz.c$.apply(this, [dtc.getDropTarget()]);
C$.$init$.apply(this);
this.context = dtc;
}, 1);

Clazz.newMeth(C$, 'getDropTargetContext', function () {
return this.context;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:17
