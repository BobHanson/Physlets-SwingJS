(function(){var P$=java.util,I$=[['java.util.Vector']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Observable");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.observers = null;
this.changed = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.observers = Clazz.new_((I$[1]||$incl$(1)));
this.changed = false;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'addObserver$java_util_Observer', function (observer) {
if (observer == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (!this.observers.contains$O(observer)) this.observers.addElement$TE(observer);
});

Clazz.newMeth(C$, 'clearChanged', function () {
this.changed=false;
});

Clazz.newMeth(C$, 'countObservers', function () {
return this.observers.size();
});

Clazz.newMeth(C$, 'deleteObserver$java_util_Observer', function (observer) {
this.observers.removeElement$O(observer);
});

Clazz.newMeth(C$, 'deleteObservers', function () {
this.observers.setSize$I(0);
});

Clazz.newMeth(C$, 'hasChanged', function () {
return this.changed;
});

Clazz.newMeth(C$, 'notifyObservers', function () {
this.notifyObservers$O(null);
});

Clazz.newMeth(C$, 'notifyObservers$O', function (data) {
if (this.changed) {
var clone = this.observers.clone();
var size = clone.size();
for (var i = 0; i < size; i++) {
clone.elementAt$I(i).update$java_util_Observable$O(this, data);
}
this.clearChanged();
}});

Clazz.newMeth(C$, 'setChanged', function () {
this.changed=true;
});
})();
//Created 2018-05-24 08:45:48
