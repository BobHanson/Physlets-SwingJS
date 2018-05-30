(function(){var P$=Clazz.newPackage("java.beans"),I$=[];
var C$=Clazz.newClass(P$, "PropertyChangeEvent", null, 'java.util.EventObject');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.propertyName = null;
this.newValue = null;
this.oldValue = null;
this.propagationId = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O$S$O$O', function (source, propertyName, oldValue, newValue) {
C$.superclazz.c$.apply(this, [source]);
C$.$init$.apply(this);
this.propertyName=propertyName;
this.newValue=newValue;
this.oldValue=oldValue;
}, 1);

Clazz.newMeth(C$, 'getPropertyName', function () {
return this.propertyName;
});

Clazz.newMeth(C$, 'getNewValue', function () {
return this.newValue;
});

Clazz.newMeth(C$, 'getOldValue', function () {
return this.oldValue;
});

Clazz.newMeth(C$, 'setPropagationId$O', function (propagationId) {
this.propagationId=propagationId;
});

Clazz.newMeth(C$, 'getPropagationId', function () {
return this.propagationId;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:31
