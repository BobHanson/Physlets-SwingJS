(function(){var P$=Clazz.newPackage("java.beans"),I$=[];
var C$=Clazz.newClass(P$, "IndexedPropertyChangeEvent", null, 'java.beans.PropertyChangeEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.index = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O$S$O$O$I', function (source, propertyName, oldValue, newValue, index) {
C$.superclazz.c$$O$S$O$O.apply(this, [source, propertyName, oldValue, newValue]);
C$.$init$.apply(this);
this.index = index;
}, 1);

Clazz.newMeth(C$, 'getIndex', function () {
return this.index;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:27
