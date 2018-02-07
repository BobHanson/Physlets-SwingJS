(function(){var P$=Clazz.newPackage("java.awt.dnd"),I$=[['java.awt.Point']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DropTargetDropEvent", null, 'java.awt.dnd.DropTargetEvent');
C$.zero = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.zero = Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[0, 0]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.location = null;
this.actions = 0;
this.dropAction = 0;
this.isLocalTx = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.location = C$.zero;
this.actions = 0;
this.dropAction = 0;
this.isLocalTx = false;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_dnd_DropTargetContext$java_awt_Point$I$I', function (dtc, cursorLocn, dropAction, srcActions) {
C$.superclazz.c$$java_awt_dnd_DropTargetContext.apply(this, [dtc]);
C$.$init$.apply(this);
if (cursorLocn == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["cursorLocn"]);
if (dropAction != 0 && dropAction != 1  && dropAction != 2  && dropAction != 1073741824 ) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["dropAction = " + dropAction]);
if ((srcActions & -1073741828) != 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["srcActions"]);
this.location = cursorLocn;
this.actions = srcActions;
this.dropAction = dropAction;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_dnd_DropTargetContext$java_awt_Point$I$I$Z', function (dtc, cursorLocn, dropAction, srcActions, isLocal) {
C$.c$$java_awt_dnd_DropTargetContext$java_awt_Point$I$I.apply(this, [dtc, cursorLocn, dropAction, srcActions]);
this.isLocalTx = isLocal;
}, 1);

Clazz.newMeth(C$, 'getLocation', function () {
return this.location;
});

Clazz.newMeth(C$, 'getCurrentDataFlavors', function () {
return this.getDropTargetContext().getCurrentDataFlavors();
});

Clazz.newMeth(C$, 'getCurrentDataFlavorsAsList', function () {
return this.getDropTargetContext().getCurrentDataFlavorsAsList();
});

Clazz.newMeth(C$, 'isDataFlavorSupported$java_awt_datatransfer_DataFlavor', function (df) {
return this.getDropTargetContext().isDataFlavorSupported$java_awt_datatransfer_DataFlavor(df);
});

Clazz.newMeth(C$, 'getSourceActions', function () {
return this.actions;
});

Clazz.newMeth(C$, 'getDropAction', function () {
return this.dropAction;
});

Clazz.newMeth(C$, 'getTransferable', function () {
return this.getDropTargetContext().getTransferable();
});

Clazz.newMeth(C$, 'acceptDrop$I', function (dropAction) {
this.getDropTargetContext().acceptDrop$I(dropAction);
});

Clazz.newMeth(C$, 'rejectDrop', function () {
this.getDropTargetContext().rejectDrop();
});

Clazz.newMeth(C$, 'dropComplete$Z', function (success) {
this.getDropTargetContext().dropComplete$Z(success);
});

Clazz.newMeth(C$, 'isLocalTransfer', function () {
return this.isLocalTx;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:17