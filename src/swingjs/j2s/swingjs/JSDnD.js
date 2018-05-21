(function(){var P$=Clazz.newPackage("swingjs"),I$=[['swingjs.JSDnD',['swingjs.JSDnD','.FileTransferable'],'swingjs.JSFileBytes','java.awt.datatransfer.DataFlavor','java.util.ArrayList',['swingjs.JSDnD','.JSDropFileMouseEvent'],'java.awt.dnd.DropTargetContext',['swingjs.JSDnD','.FileDropTargetContextPeer'],'java.awt.dnd.DropTargetDropEvent','java.awt.Point']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSDnD", function(){
Clazz.newInstance(this, arguments,0,C$);
});

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'drop$javax_swing_JComponent$S$BA$I$I', function (jc, name, data, x, y) {
var target = jc.getDropTarget();
var offset;
if (target != null ) {
offset = jc.getLocationOnScreen();
target.drop$java_awt_dnd_DropTargetDropEvent(C$.createFileDropEvent$java_awt_dnd_DropTarget$S$BA$I$I(target, name, data, x, y));
return;
}var top = jc.getTopLevelAncestor();
offset = top.getLocationOnScreen();
top.dispatchEvent$java_awt_AWTEvent(Clazz.new_((I$[6]||$incl$(6)).c$$java_awt_Component$I$I$I$S$BA,[jc, 502, x - offset.x, y - offset.y, name, data]));
}, 1);

Clazz.newMeth(C$, 'createFileDropEvent$java_awt_dnd_DropTarget$S$BA$I$I', function (target, name, data, x, y) {
var context = Clazz.new_((I$[7]||$incl$(7)).c$$java_awt_dnd_DropTarget,[target]);
context.addNotify$java_awt_dnd_peer_DropTargetContextPeer(Clazz.new_((I$[8]||$incl$(8)).c$$java_awt_dnd_DropTarget$S$BA,[target, name, data]));
return Clazz.new_((I$[9]||$incl$(9)).c$$java_awt_dnd_DropTargetContext$java_awt_Point$I$I,[context, Clazz.new_((I$[10]||$incl$(10)).c$$I$I,[x, y]), 2, 1073741827]);
}, 1);
;
(function(){var C$=Clazz.newClass(P$.JSDnD, "JSDropTargetEvent", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.awt.event.MouseEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Component$I$J$I$I$I$I', function (source, id, when, modifiers, x, y, clickCount) {
C$.superclazz.c$$java_awt_Component$I$J$I$I$I$I$Z$I.apply(this, [source, id, when, modifiers, x, y, clickCount, false, 0]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JSDnD, "JSDropFileMouseEvent", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['swingjs.JSDnD','swingjs.JSDnD.JSDropTargetEvent']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.name = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Component$I$I$I$S$BA', function (source, id, x, y, name, data) {
C$.superclazz.c$$java_awt_Component$I$J$I$I$I$I.apply(this, [source, id, System.currentTimeMillis(), 0, x, y, 0]);
C$.$init$.apply(this);
this.name = name;
this.setBData$BA(data);
}, 1);

Clazz.newMeth(C$, 'dispatch', function () {
try {
var target = (this.getSource()).getDropTarget();
target.drop$java_awt_dnd_DropTargetDropEvent((I$[1]||$incl$(1)).createFileDropEvent$java_awt_dnd_DropTarget$S$BA$I$I(target, this.name, this.getBData(), this.getX(), this.getY()));
} catch (e) {
System.out.println$S("Error creating Drop event " + e);
}
});

Clazz.newMeth(C$, 'consume', function () {
C$.superclazz.prototype.consume.apply(this, []);
});

Clazz.newMeth(C$, 'paramString', function () {
var typeStr = null;
switch (this.id) {
case 502:
typeStr = "MOUSE_DROPPED";
break;
default:
return C$.superclazz.prototype.paramString.apply(this, []);
}
return typeStr + ",(" + this.getX() + "," + this.getY() + ")" ;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JSDnD, "FileDropTargetContextPeer", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, 'java.awt.dnd.peer.DropTargetContextPeer');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.fileTransferable = null;
this.target = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_dnd_DropTarget$S$BA', function (target, name, data) {
C$.$init$.apply(this);
this.target = target;
this.fileTransferable = Clazz.new_((I$[2]||$incl$(2)).c$$S$BA,[name, data]);
}, 1);

Clazz.newMeth(C$, 'setTargetActions$I', function (actions) {
});

Clazz.newMeth(C$, 'getTargetActions', function () {
return 3;
});

Clazz.newMeth(C$, 'getDropTarget', function () {
return this.target;
});

Clazz.newMeth(C$, 'getTransferDataFlavors', function () {
return this.fileTransferable.getTransferDataFlavors();
});

Clazz.newMeth(C$, 'getTransferable', function () {
return this.fileTransferable;
});

Clazz.newMeth(C$, 'isTransferableJVMLocal', function () {
return true;
});

Clazz.newMeth(C$, 'acceptDrag$I', function (dragAction) {
});

Clazz.newMeth(C$, 'rejectDrag', function () {
});

Clazz.newMeth(C$, 'acceptDrop$I', function (dropAction) {
});

Clazz.newMeth(C$, 'rejectDrop', function () {
});

Clazz.newMeth(C$, 'dropComplete$Z', function (success) {
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JSDnD, "FileTransferable", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, 'java.awt.datatransfer.Transferable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.file = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$BA', function (name, data) {
C$.$init$.apply(this);
this.file = Clazz.new_((I$[3]||$incl$(3)).c$$S$BA,[name, data]);
}, 1);

Clazz.newMeth(C$, 'getTransferDataFlavors', function () {
var flavors = Clazz.array((I$[4]||$incl$(4)), [1]);
flavors[0] = (I$[4]||$incl$(4)).javaFileListFlavor;
return flavors;
});

Clazz.newMeth(C$, 'isDataFlavorSupported$java_awt_datatransfer_DataFlavor', function (flavor) {
return flavor.isFlavorJavaFileListType();
});

Clazz.newMeth(C$, 'getTransferData$java_awt_datatransfer_DataFlavor', function (flavor) {
var list = Clazz.new_((I$[5]||$incl$(5)));
list.add$TE(this.file);
return list;
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:14
