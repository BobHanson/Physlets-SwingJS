(function(){var P$=Clazz.newPackage("java.beans"),I$=[];
var C$=Clazz.newClass(P$, "PropertyVetoException", null, 'Exception');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.evt = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$java_beans_PropertyChangeEvent', function (mess, evt) {
C$.superclazz.c$$S.apply(this, [mess]);
C$.$init$.apply(this);
this.evt=evt;
}, 1);

Clazz.newMeth(C$, 'getPropertyChangeEvent', function () {
return this.evt;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:31