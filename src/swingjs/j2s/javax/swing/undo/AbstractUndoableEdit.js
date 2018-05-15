(function(){var P$=Clazz.newPackage("javax.swing.undo"),I$=[['javax.swing.UIManager']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AbstractUndoableEdit", null, null, 'javax.swing.undo.UndoableEdit');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.hasBeenDone = false;
this.alive = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.hasBeenDone = true;
this.alive = true;
}, 1);

Clazz.newMeth(C$, 'die', function () {
this.alive = false;
});

Clazz.newMeth(C$, 'undo', function () {
if (!this.canUndo()) {
throw Clazz.new_(Clazz.load('javax.swing.undo.CannotUndoException'));
}this.hasBeenDone = false;
});

Clazz.newMeth(C$, 'canUndo', function () {
return this.alive && this.hasBeenDone ;
});

Clazz.newMeth(C$, 'redo', function () {
if (!this.canRedo()) {
throw Clazz.new_(Clazz.load('javax.swing.undo.CannotRedoException'));
}this.hasBeenDone = true;
});

Clazz.newMeth(C$, 'canRedo', function () {
return this.alive && !this.hasBeenDone ;
});

Clazz.newMeth(C$, 'addEdit$javax_swing_undo_UndoableEdit', function (anEdit) {
return false;
});

Clazz.newMeth(C$, 'replaceEdit$javax_swing_undo_UndoableEdit', function (anEdit) {
return false;
});

Clazz.newMeth(C$, 'isSignificant', function () {
return true;
});

Clazz.newMeth(C$, 'getPresentationName', function () {
return "";
});

Clazz.newMeth(C$, 'getUndoPresentationName', function () {
var name = this.getPresentationName();
if (!"".equals$O(name)) {
name = (I$[1]||$incl$(1)).getString$O("AbstractUndoableEdit.undoText") + " " + name ;
} else {
name = (I$[1]||$incl$(1)).getString$O("AbstractUndoableEdit.undoText");
}return name;
});

Clazz.newMeth(C$, 'getRedoPresentationName', function () {
var name = this.getPresentationName();
if (!"".equals$O(name)) {
name = (I$[1]||$incl$(1)).getString$O("AbstractUndoableEdit.redoText") + " " + name ;
} else {
name = (I$[1]||$incl$(1)).getString$O("AbstractUndoableEdit.redoText");
}return name;
});

Clazz.newMeth(C$, 'toString', function () {
return C$.superclazz.prototype.toString.apply(this, []) + " hasBeenDone: " + this.hasBeenDone + " alive: " + this.alive ;
});
})();
//Created 2018-05-15 01:03:00
