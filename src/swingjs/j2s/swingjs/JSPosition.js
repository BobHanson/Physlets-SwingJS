(function(){var P$=Clazz.newPackage("swingjs"),I$=[];
var C$=Clazz.newClass(P$, "JSPosition", null, null, 'javax.swing.text.Position');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.pos = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (offset) {
C$.$init$.apply(this);
this.pos=offset;
}, 1);

Clazz.newMeth(C$, 'getOffset', function () {
return this.pos;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:45
