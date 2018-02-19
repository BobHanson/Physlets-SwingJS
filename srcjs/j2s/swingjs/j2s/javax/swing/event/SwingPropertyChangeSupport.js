(function(){var P$=Clazz.newPackage("javax.swing.event"),I$=[['javax.swing.SwingUtilities','javax.swing.event.SwingPropertyChangeSupport$1']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SwingPropertyChangeSupport", null, 'java.beans.PropertyChangeSupport');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.notifyOnEDT = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O', function (sourceBean) {
C$.c$$O$Z.apply(this, [sourceBean, false]);
}, 1);

Clazz.newMeth(C$, 'c$$O$Z', function (sourceBean, notifyOnEDT) {
C$.superclazz.c$$O.apply(this, [sourceBean]);
C$.$init$.apply(this);
this.notifyOnEDT = notifyOnEDT;
}, 1);

Clazz.newMeth(C$, 'firePropertyChange$java_beans_PropertyChangeEvent', function (evt) {
if (evt == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (!this.isNotifyOnEDT() || (I$[1]||$incl$(1)).isEventDispatchThread() ) {
C$.superclazz.prototype.firePropertyChange$java_beans_PropertyChangeEvent.apply(this, [evt]);
} else {
(I$[1]||$incl$(1)).invokeLater$Runnable(((
(function(){var C$=Clazz.newClass(P$, "SwingPropertyChangeSupport$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'Runnable', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'run', function () {
this.b$['javax.swing.event.SwingPropertyChangeSupport'].firePropertyChange$java_beans_PropertyChangeEvent(this.$finals.evt);
});
})()
), Clazz.new_((I$[2]||$incl$(2)).$init$, [this, {evt: evt}])));
}});

Clazz.newMeth(C$, 'isNotifyOnEDT', function () {
return this.notifyOnEDT;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:47
