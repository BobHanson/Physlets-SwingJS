(function(){var P$=Clazz.newPackage("sun.swing"),I$=[];
var C$=Clazz.newClass(P$, "StringUIClientPropertyKey", null, null, 'sun.swing.UIClientPropertyKey');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.key=null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (key) {
C$.$init$.apply(this);
this.key=key;
}, 1);

Clazz.newMeth(C$, 'toString', function () {
return this.key;
});

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 21:47:48 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
