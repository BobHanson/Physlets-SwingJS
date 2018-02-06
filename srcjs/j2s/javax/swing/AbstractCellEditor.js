(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['javax.swing.event.EventListenerList','javax.swing.event.CellEditorListener','javax.swing.event.ChangeEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AbstractCellEditor", null, null, 'javax.swing.CellEditor');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.listenerList = null;
this.changeEvent = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.listenerList = Clazz.new_((I$[1]||$incl$(1)));
this.changeEvent = null;
}, 1);

Clazz.newMeth(C$, 'isCellEditable$java_util_EventObject', function (e) {
return true;
});

Clazz.newMeth(C$, 'shouldSelectCell$java_util_EventObject', function (anEvent) {
return true;
});

Clazz.newMeth(C$, 'stopCellEditing', function () {
this.fireEditingStopped();
return true;
});

Clazz.newMeth(C$, 'cancelCellEditing', function () {
this.fireEditingCanceled();
});

Clazz.newMeth(C$, 'addCellEditorListener$javax_swing_event_CellEditorListener', function (l) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[2]||$incl$(2)),['editingCanceled$javax_swing_event_ChangeEvent','editingStopped$javax_swing_event_ChangeEvent']), l);
});

Clazz.newMeth(C$, 'removeCellEditorListener$javax_swing_event_CellEditorListener', function (l) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[2]||$incl$(2)),['editingCanceled$javax_swing_event_ChangeEvent','editingStopped$javax_swing_event_ChangeEvent']), l);
});

Clazz.newMeth(C$, 'getCellEditorListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[2]||$incl$(2)),['editingCanceled$javax_swing_event_ChangeEvent','editingStopped$javax_swing_event_ChangeEvent']));
});

Clazz.newMeth(C$, 'fireEditingStopped', function () {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[2]||$incl$(2)),['editingCanceled$javax_swing_event_ChangeEvent','editingStopped$javax_swing_event_ChangeEvent']) ) {
if (this.changeEvent == null ) this.changeEvent = Clazz.new_((I$[3]||$incl$(3)).c$$O,[this]);
(listeners[i + 1]).editingStopped$javax_swing_event_ChangeEvent(this.changeEvent);
}}
});

Clazz.newMeth(C$, 'fireEditingCanceled', function () {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[2]||$incl$(2)),['editingCanceled$javax_swing_event_ChangeEvent','editingStopped$javax_swing_event_ChangeEvent']) ) {
if (this.changeEvent == null ) this.changeEvent = Clazz.new_((I$[3]||$incl$(3)).c$$O,[this]);
(listeners[i + 1]).editingCanceled$javax_swing_event_ChangeEvent(this.changeEvent);
}}
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:16
