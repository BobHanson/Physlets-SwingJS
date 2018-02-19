(function(){var P$=Clazz.newPackage("a2s"),I$=[];
var C$=Clazz.newClass(P$, "Canvas", null, 'a2s.Panel');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.notified = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
this.update$java_awt_Graphics(g);
});

Clazz.newMeth(C$, 'update$java_awt_Graphics', function (g) {
if (!this.notified) System.out.println$S("neither paint(g) nor update(g) is implemented for " + this);
this.notified = true;
{
this.paintComponent$java_awt_Graphics && this.paintComponent$java_awt_Graphics(g);
}
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:01:38
