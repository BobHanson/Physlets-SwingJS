(function(){var P$=Clazz.newPackage("javax.swing.event"),I$=[];
var C$=Clazz.newClass(P$, "UndoableEditEvent", null, 'java.util.EventObject');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.myEdit = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O$javax_swing_undo_UndoableEdit', function (source, edit) {
C$.superclazz.c$.apply(this, [source]);
C$.$init$.apply(this);
this.myEdit = edit;
}, 1);

Clazz.newMeth(C$, 'getEdit', function () {
return this.myEdit;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:48
