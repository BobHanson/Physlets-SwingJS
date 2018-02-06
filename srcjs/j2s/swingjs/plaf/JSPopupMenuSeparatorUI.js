(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[];
var C$=Clazz.newClass(P$, "JSPopupMenuSeparatorUI", null, 'swingjs.plaf.JSSeparatorUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.isMenuItem = true;
this.allowPaintedBackground = false;
}, 1);
})();
//Created 2018-02-06 09:00:50
