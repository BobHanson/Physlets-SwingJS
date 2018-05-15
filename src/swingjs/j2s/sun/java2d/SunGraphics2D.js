(function(){var P$=Clazz.newPackage("sun.java2d"),I$=[];
var C$=Clazz.newClass(P$, "SunGraphics2D", null, 'java.awt.Graphics2D');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.transformState = 0;
this.foregroundColor = null;
this.backgroundColor = null;
this.$transform = null;
this.transX = 0;
this.transY = 0;
this.hints = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'clone', function () {
return this.clone0();
});

Clazz.newMeth(C$, 'clone0', function () {
return null;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:10
