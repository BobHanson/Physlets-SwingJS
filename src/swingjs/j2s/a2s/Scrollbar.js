(function(){var P$=Clazz.newPackage("a2s"),I$=[['a2s.A2SEvent','a2s.A2SListener']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Scrollbar", null, 'javax.swing.JScrollBar', 'a2s.A2SContainer');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.listener = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.listener = null;
}, 1);

Clazz.newMeth(C$, 'c$$I', function (direction) {
C$.superclazz.c$$I.apply(this, [direction]);
C$.$init$.apply(this);
(I$[1]||$incl$(1)).addListener$javax_swing_JComponent$java_awt_Component(null, this);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
(I$[1]||$incl$(1)).addListener$javax_swing_JComponent$java_awt_Component(null, this);
}, 1);

Clazz.newMeth(C$, 'c$$I$I$I$I$I', function (orientation, value, extent, min, max) {
C$.superclazz.c$$I$I$I$I$I.apply(this, [orientation, value, extent, min, max]);
C$.$init$.apply(this);
(I$[1]||$incl$(1)).addListener$javax_swing_JComponent$java_awt_Component(null, this);
}, 1);

Clazz.newMeth(C$, 'processAdjustmentEvent$java_awt_event_AdjustmentEvent', function (e) {
});

Clazz.newMeth(C$, 'setValue$I', function (n) {
C$.superclazz.prototype.setValue$I.apply(this, [n]);
});

Clazz.newMeth(C$, 'getMinimum', function () {
return C$.superclazz.prototype.getMinimum.apply(this, []);
});

Clazz.newMeth(C$, 'getMaximum', function () {
return C$.superclazz.prototype.getMaximum.apply(this, []);
});

Clazz.newMeth(C$, 'getValue', function () {
return C$.superclazz.prototype.getValue.apply(this, []);
});

Clazz.newMeth(C$, 'getA2SListener', function () {
if (this.listener == null ) this.listener = Clazz.new_((I$[2]||$incl$(2)));
return this.listener;
});
})();
//Created 2018-05-15 01:01:45
