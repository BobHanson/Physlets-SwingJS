(function(){var P$=Clazz.newPackage("javax.swing"),I$=[];
var C$=Clazz.newClass(P$, "ActionPropertyChangeListener", null, null, 'java.beans.PropertyChangeListener');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.action = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$TT$javax_swing_Action', function (c, a) {
C$.$init$.apply(this);
p$.setTarget$TT.apply(this, [c]);
this.action=a;
}, 1);

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (e) {
var target = this.getTarget();
if (target == null ) {
this.getAction().removePropertyChangeListener$java_beans_PropertyChangeListener(this);
} else {
this.actionPropertyChanged$TT$javax_swing_Action$java_beans_PropertyChangeEvent(target, this.getAction(), e);
}});

Clazz.newMeth(C$, 'setTarget$TT', function (c) {
});

Clazz.newMeth(C$, 'getTarget', function () {
return null;
});

Clazz.newMeth(C$, 'getAction', function () {
return this.action;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:46:06
