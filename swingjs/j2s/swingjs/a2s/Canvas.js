(function(){var P$=Clazz.newPackage("swingjs.a2s");
var C$=Clazz.newClass(P$, "Canvas", null, 'swingjs.a2s.Panel');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_GraphicsConfiguration', function (config) {
C$.c$.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.秘setPaintsSelf$I(2);
}, 1);

Clazz.newMeth(C$, 'getPreferredSize$', function () {
return this.preferredSize$();
});

Clazz.newMeth(C$, 'preferredSize$', function () {
return this.prefSizeComp$();
});

Clazz.newMeth(C$, 'setBackground$java_awt_Color', function (c) {
C$.superclazz.prototype.setBackground$java_awt_Color.apply(this, [c]);
this.setOpaque$Z(c != null );
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
g.clearRect$I$I$I$I(0, 0, this.width, this.height);
});

Clazz.newMeth(C$, 'update$java_awt_Graphics', function (g) {
g.clearRect$I$I$I$I(0, 0, this.width, this.height);
this.paint$java_awt_Graphics(g);
});
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 22:44:19 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
