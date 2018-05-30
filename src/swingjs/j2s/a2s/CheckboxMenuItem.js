(function(){var P$=Clazz.newPackage("a2s"),I$=[];
var C$=Clazz.newClass(P$, "CheckboxMenuItem", null, 'javax.swing.JCheckBoxMenuItem');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (string) {
C$.superclazz.c$$S.apply(this, [string]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (string, b) {
C$.superclazz.c$$S$Z.apply(this, [string, b]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getState', function () {
return this.isSelected();
});

Clazz.newMeth(C$, 'setState$Z', function (tf) {
this.setSelected$Z(tf);
});
})();
//Created 2018-05-24 08:44:59
