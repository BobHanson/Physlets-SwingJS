(function(){var P$=Clazz.newPackage("javax.swing.tree"),I$=[];
var C$=Clazz.newClass(P$, "ExpandVetoException", null, 'Exception');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.event = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_event_TreeExpansionEvent', function (event) {
C$.c$$javax_swing_event_TreeExpansionEvent$S.apply(this, [event, null]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_event_TreeExpansionEvent$S', function (event, message) {
C$.superclazz.c$$S.apply(this, [message]);
C$.$init$.apply(this);
this.event=event;
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:11
