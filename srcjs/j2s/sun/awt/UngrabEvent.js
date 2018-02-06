(function(){var P$=Clazz.newPackage("sun.awt"),I$=[];
var C$=Clazz.newClass(P$, "UngrabEvent", null, 'java.awt.AWTEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Component', function (source) {
C$.superclazz.c$$O$I.apply(this, [source, 1998]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'toString', function () {
return "sun.awt.UngrabEvent[" + this.getSource() + "]" ;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:14
