(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[];
var C$=Clazz.newClass(P$, "BadLocationException", null, 'Exception');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.offs = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$I', function (s, offs) {
C$.superclazz.c$$S.apply(this, [s]);
C$.$init$.apply(this);
this.offs=offs;
}, 1);

Clazz.newMeth(C$, 'offsetRequested', function () {
return this.offs;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:01
