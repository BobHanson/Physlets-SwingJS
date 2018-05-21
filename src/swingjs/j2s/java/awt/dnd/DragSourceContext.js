(function(){var P$=Clazz.newPackage("java.awt.dnd"),I$=[['java.awt.dnd.DragSource']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DragSourceContext", null, null, ['java.awt.dnd.DragSourceListener', 'java.awt.dnd.DragSourceMotionListener', 'java.io.Serializable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.peer = null;
this.trigger = null;
this.cursor = null;
this.transferable = null;
this.listener = null;
this.useCustomCursor = false;
this.sourceActions = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_dnd_peer_DragSourceContextPeer$java_awt_dnd_DragGestureEvent$java_awt_Cursor$java_awt_Image$java_awt_Point$java_awt_datatransfer_Transferable$java_awt_dnd_DragSourceListener', function (dscp, trigger, dragCursor, dragImage, offset, t, dsl) {
C$.$init$.apply(this);
if (dscp == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["DragSourceContextPeer"]);
}if (trigger == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["Trigger"]);
}if (trigger.getDragSource() == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["DragSource"]);
}if (trigger.getComponent() == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Component"]);
}if (trigger.getSourceAsDragGestureRecognizer().getSourceActions() == 0) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["source actions"]);
}if (trigger.getDragAction() == 0) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["no drag action"]);
}if (t == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["Transferable"]);
}if (dragImage != null  && offset == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["offset"]);
}this.peer = dscp;
this.trigger = trigger;
this.cursor = dragCursor;
this.transferable = t;
this.listener = dsl;
this.sourceActions = trigger.getSourceAsDragGestureRecognizer().getSourceActions();
this.useCustomCursor = (dragCursor != null );
this.updateCurrentCursor$I$I$I(trigger.getDragAction(), this.getSourceActions(), 0);
}, 1);

Clazz.newMeth(C$, 'getDragSource', function () {
return this.trigger.getDragSource();
});

Clazz.newMeth(C$, 'getComponent', function () {
return this.trigger.getComponent();
});

Clazz.newMeth(C$, 'getTrigger', function () {
return this.trigger;
});

Clazz.newMeth(C$, 'getSourceActions', function () {
return this.sourceActions;
});

Clazz.newMeth(C$, 'setCursor$java_awt_Cursor', function (c) {
this.useCustomCursor = (c != null );
p$.setCursorImpl$java_awt_Cursor.apply(this, [c]);
});

Clazz.newMeth(C$, 'getCursor', function () {
return this.cursor;
});

Clazz.newMeth(C$, 'addDragSourceListener$java_awt_dnd_DragSourceListener', function (dsl) {
if (dsl == null ) return;
if (this.equals$O(dsl)) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["DragSourceContext may not be its own listener"]);
if (this.listener != null ) throw Clazz.new_(Clazz.load('java.util.TooManyListenersException'));
 else this.listener = dsl;
});

Clazz.newMeth(C$, 'removeDragSourceListener$java_awt_dnd_DragSourceListener', function (dsl) {
if (this.listener != null  && this.listener.equals$O(dsl) ) {
this.listener = null;
} else throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
});

Clazz.newMeth(C$, 'transferablesFlavorsChanged', function () {
if (this.peer != null ) this.peer.transferablesFlavorsChanged();
});

Clazz.newMeth(C$, 'dragEnter$java_awt_dnd_DragSourceDragEvent', function (dsde) {
var dsl = this.listener;
if (dsl != null ) {
dsl.dragEnter$java_awt_dnd_DragSourceDragEvent(dsde);
}this.getDragSource().processDragEnter$java_awt_dnd_DragSourceDragEvent(dsde);
this.updateCurrentCursor$I$I$I(this.getSourceActions(), dsde.getTargetActions(), 1);
});

Clazz.newMeth(C$, 'dragOver$java_awt_dnd_DragSourceDragEvent', function (dsde) {
var dsl = this.listener;
if (dsl != null ) {
dsl.dragOver$java_awt_dnd_DragSourceDragEvent(dsde);
}this.getDragSource().processDragOver$java_awt_dnd_DragSourceDragEvent(dsde);
this.updateCurrentCursor$I$I$I(this.getSourceActions(), dsde.getTargetActions(), 2);
});

Clazz.newMeth(C$, 'dragExit$java_awt_dnd_DragSourceEvent', function (dse) {
var dsl = this.listener;
if (dsl != null ) {
dsl.dragExit$java_awt_dnd_DragSourceEvent(dse);
}this.getDragSource().processDragExit$java_awt_dnd_DragSourceEvent(dse);
this.updateCurrentCursor$I$I$I(0, 0, 0);
});

Clazz.newMeth(C$, 'dropActionChanged$java_awt_dnd_DragSourceDragEvent', function (dsde) {
var dsl = this.listener;
if (dsl != null ) {
dsl.dropActionChanged$java_awt_dnd_DragSourceDragEvent(dsde);
}this.getDragSource().processDropActionChanged$java_awt_dnd_DragSourceDragEvent(dsde);
this.updateCurrentCursor$I$I$I(this.getSourceActions(), dsde.getTargetActions(), 3);
});

Clazz.newMeth(C$, 'dragDropEnd$java_awt_dnd_DragSourceDropEvent', function (dsde) {
var dsl = this.listener;
if (dsl != null ) {
dsl.dragDropEnd$java_awt_dnd_DragSourceDropEvent(dsde);
}this.getDragSource().processDragDropEnd$java_awt_dnd_DragSourceDropEvent(dsde);
});

Clazz.newMeth(C$, 'dragMouseMoved$java_awt_dnd_DragSourceDragEvent', function (dsde) {
this.getDragSource().processDragMouseMoved$java_awt_dnd_DragSourceDragEvent(dsde);
});

Clazz.newMeth(C$, 'getTransferable', function () {
return this.transferable;
});

Clazz.newMeth(C$, 'updateCurrentCursor$I$I$I', function (sourceAct, targetAct, status) {
if (this.useCustomCursor) {
return;
}var c = null;
switch (status) {
default:
targetAct = 0;
case 1:
case 2:
case 3:
var ra = sourceAct & targetAct;
if (ra == 0) {
if ((sourceAct & 1073741824) == 1073741824) c = (I$[1]||$incl$(1)).DefaultLinkNoDrop;
 else if ((sourceAct & 2) == 2) c = (I$[1]||$incl$(1)).DefaultMoveNoDrop;
 else c = (I$[1]||$incl$(1)).DefaultCopyNoDrop;
} else {
if ((ra & 1073741824) == 1073741824) c = (I$[1]||$incl$(1)).DefaultLinkDrop;
 else if ((ra & 2) == 2) c = (I$[1]||$incl$(1)).DefaultMoveDrop;
 else c = (I$[1]||$incl$(1)).DefaultCopyDrop;
}}
p$.setCursorImpl$java_awt_Cursor.apply(this, [c]);
});

Clazz.newMeth(C$, 'setCursorImpl$java_awt_Cursor', function (c) {
if (this.cursor == null  || !this.cursor.equals$O(c) ) {
this.cursor = c;
if (this.peer != null ) this.peer.setCursor$java_awt_Cursor(this.cursor);
}});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:01:55
