(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['javax.swing.event.EventListenerList','javax.swing.event.ChangeListener','javax.swing.event.ChangeEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DefaultSingleSelectionModel", null, null, 'javax.swing.SingleSelectionModel');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.changeEvent = null;
this.listenerList = null;
this.index = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.changeEvent = null;
this.listenerList = Clazz.new_((I$[1]||$incl$(1)));
this.index = -1;
}, 1);

Clazz.newMeth(C$, 'getSelectedIndex', function () {
return this.index;
});

Clazz.newMeth(C$, 'setSelectedIndex$I', function (index) {
if (this.index != index) {
this.index = index;
this.fireStateChanged();
}});

Clazz.newMeth(C$, 'clearSelection', function () {
this.setSelectedIndex$I(-1);
});

Clazz.newMeth(C$, 'isSelected', function () {
var ret = false;
if (this.getSelectedIndex() != -1) {
ret = true;
}return ret;
});

Clazz.newMeth(C$, 'addChangeListener$javax_swing_event_ChangeListener', function (l) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[2]||$incl$(2)),['stateChanged$javax_swing_event_ChangeEvent']), l);
});

Clazz.newMeth(C$, 'removeChangeListener$javax_swing_event_ChangeListener', function (l) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[2]||$incl$(2)),['stateChanged$javax_swing_event_ChangeEvent']), l);
});

Clazz.newMeth(C$, 'getChangeListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[2]||$incl$(2)),['stateChanged$javax_swing_event_ChangeEvent']));
});

Clazz.newMeth(C$, 'fireStateChanged', function () {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[2]||$incl$(2)),['stateChanged$javax_swing_event_ChangeEvent']) ) {
if (this.changeEvent == null ) this.changeEvent = Clazz.new_((I$[3]||$incl$(3)).c$$O,[this]);
(listeners[i + 1]).stateChanged$javax_swing_event_ChangeEvent(this.changeEvent);
}}
});

Clazz.newMeth(C$, 'getListeners$Class', function (listenerType) {
return this.listenerList.getListeners$Class(listenerType);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:26
