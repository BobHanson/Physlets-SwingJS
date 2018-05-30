(function(){var P$=Clazz.newPackage("java.beans"),I$=[];
var C$=Clazz.newClass(P$, "PropertyChangeListenerProxy", null, 'java.util.EventListenerProxy', 'java.beans.PropertyChangeListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.propertyName = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$java_beans_PropertyChangeListener', function (propertyName, listener) {
C$.superclazz.c$.apply(this, [listener]);
C$.$init$.apply(this);
this.propertyName=propertyName;
}, 1);

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (evt) {
(this.getListener()).propertyChange$java_beans_PropertyChangeEvent(evt);
});

Clazz.newMeth(C$, 'getPropertyName', function () {
return this.propertyName;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:31
