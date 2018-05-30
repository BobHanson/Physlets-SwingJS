(function(){var P$=Clazz.newPackage("javax.swing.colorchooser"),I$=[['javax.swing.event.EventListenerList','java.awt.Color','javax.swing.event.ChangeListener','javax.swing.event.ChangeEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DefaultColorSelectionModel", null, null, 'javax.swing.colorchooser.ColorSelectionModel');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.changeEvent = null;
this.listenerList = null;
this.selectedColor = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.changeEvent = null;
this.listenerList = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.selectedColor=(I$[2]||$incl$(2)).white;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Color', function (color) {
C$.$init$.apply(this);
this.selectedColor=color;
}, 1);

Clazz.newMeth(C$, 'getSelectedColor', function () {
return this.selectedColor;
});

Clazz.newMeth(C$, 'setSelectedColor$java_awt_Color', function (color) {
if (color != null  && !this.selectedColor.equals$O(color) ) {
this.selectedColor=color;
this.fireStateChanged();
}});

Clazz.newMeth(C$, 'addChangeListener$javax_swing_event_ChangeListener', function (l) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[3]||$incl$(3)),['stateChanged$javax_swing_event_ChangeEvent']), l);
});

Clazz.newMeth(C$, 'removeChangeListener$javax_swing_event_ChangeListener', function (l) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[3]||$incl$(3)),['stateChanged$javax_swing_event_ChangeEvent']), l);
});

Clazz.newMeth(C$, 'getChangeListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[3]||$incl$(3)),['stateChanged$javax_swing_event_ChangeEvent']));
});

Clazz.newMeth(C$, 'fireStateChanged', function () {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i-=2) {
if (listeners[i] === Clazz.getClass((I$[3]||$incl$(3)),['stateChanged$javax_swing_event_ChangeEvent']) ) {
if (this.changeEvent == null ) {
this.changeEvent=Clazz.new_((I$[4]||$incl$(4)).c$$O,[this]);
}(listeners[i + 1]).stateChanged$javax_swing_event_ChangeEvent(this.changeEvent);
}}
});
})();
//Created 2018-05-24 08:46:46
