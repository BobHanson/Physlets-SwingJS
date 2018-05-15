(function(){var P$=Clazz.newPackage("java.awt.datatransfer"),I$=[['java.awt.EventQueue','java.awt.datatransfer.Clipboard$1','java.awt.datatransfer.DataFlavor','sun.awt.EventListenerAggregate','java.awt.datatransfer.FlavorListener','java.awt.datatransfer.FlavorEvent','java.awt.datatransfer.Clipboard$2','java.util.HashSet','java.util.Arrays']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Clipboard");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.name = null;
this.owner = null;
this.contents = null;
this.flavorListeners = null;
this.currentDataFlavors = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.$init$.apply(this);
this.name = name;
}, 1);

Clazz.newMeth(C$, 'getName', function () {
return this.name;
});

Clazz.newMeth(C$, 'setContents$java_awt_datatransfer_Transferable$java_awt_datatransfer_ClipboardOwner', function (contents, owner) {
var oldOwner = this.owner;
var oldContents = this.contents;
this.owner = owner;
this.contents = contents;
if (oldOwner != null  && oldOwner !== owner  ) {
(I$[1]||$incl$(1)).invokeLater$Runnable(((
(function(){var C$=Clazz.newClass(P$, "Clipboard$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'Runnable', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'run', function () {
this.$finals.oldOwner.lostOwnership$java_awt_datatransfer_Clipboard$java_awt_datatransfer_Transferable(this.b$['java.awt.datatransfer.Clipboard'], this.$finals.oldContents);
});
})()
), Clazz.new_((I$[2]||$incl$(2)).$init$, [this, {oldOwner: oldOwner, oldContents: oldContents}])));
}p$.fireFlavorsChanged.apply(this, []);
});

Clazz.newMeth(C$, 'getContents$O', function (requestor) {
return this.contents;
});

Clazz.newMeth(C$, 'getAvailableDataFlavors', function () {
var cntnts = this.getContents$O(null);
if (cntnts == null ) {
return Clazz.array((I$[3]||$incl$(3)), [0]);
}return cntnts.getTransferDataFlavors();
});

Clazz.newMeth(C$, 'isDataFlavorAvailable$java_awt_datatransfer_DataFlavor', function (flavor) {
if (flavor == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["flavor"]);
}var cntnts = this.getContents$O(null);
if (cntnts == null ) {
return false;
}return cntnts.isDataFlavorSupported$java_awt_datatransfer_DataFlavor(flavor);
});

Clazz.newMeth(C$, 'getData$java_awt_datatransfer_DataFlavor', function (flavor) {
if (flavor == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["flavor"]);
}var cntnts = this.getContents$O(null);
if (cntnts == null ) {
throw Clazz.new_(Clazz.load('java.awt.datatransfer.UnsupportedFlavorException').c$$java_awt_datatransfer_DataFlavor,[flavor]);
}return cntnts.getTransferData$java_awt_datatransfer_DataFlavor(flavor);
});

Clazz.newMeth(C$, 'addFlavorListener$java_awt_datatransfer_FlavorListener', function (listener) {
if (listener == null ) {
return;
}if (this.flavorListeners == null ) {
this.currentDataFlavors = p$.getAvailableDataFlavorSet.apply(this, []);
this.flavorListeners = Clazz.new_((I$[4]||$incl$(4)).c$$Class,[Clazz.getClass((I$[5]||$incl$(5)),['flavorsChanged$java_awt_datatransfer_FlavorEvent'])]);
}this.flavorListeners.add$java_util_EventListener(listener);
});

Clazz.newMeth(C$, 'removeFlavorListener$java_awt_datatransfer_FlavorListener', function (listener) {
if (listener == null  || this.flavorListeners == null  ) {
return;
}this.flavorListeners.remove$java_util_EventListener(listener);
});

Clazz.newMeth(C$, 'getFlavorListeners', function () {
return this.flavorListeners == null  ? Clazz.array((I$[5]||$incl$(5)), [0]) : this.flavorListeners.getListenersCopy();
});

Clazz.newMeth(C$, 'fireFlavorsChanged', function () {
if (this.flavorListeners == null ) {
return;
}var prevDataFlavors = this.currentDataFlavors;
this.currentDataFlavors = p$.getAvailableDataFlavorSet.apply(this, []);
if (prevDataFlavors.equals$O(this.currentDataFlavors)) {
return;
}var flavorListenerArray = this.flavorListeners.getListenersInternal();
for (var i = 0; i < flavorListenerArray.length; i++) {
var listener = flavorListenerArray[i];
(I$[1]||$incl$(1)).invokeLater$Runnable(((
(function(){var C$=Clazz.newClass(P$, "Clipboard$2", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'Runnable', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'run', function () {
this.$finals.listener.flavorsChanged$java_awt_datatransfer_FlavorEvent(Clazz.new_((I$[6]||$incl$(6)).c$$java_awt_datatransfer_Clipboard,[this.b$['java.awt.datatransfer.Clipboard']]));
});
})()
), Clazz.new_((I$[7]||$incl$(7)).$init$, [this, {listener: listener}])));
}
});

Clazz.newMeth(C$, 'getAvailableDataFlavorSet', function () {
var set = Clazz.new_((I$[8]||$incl$(8)));
var contents = this.getContents$O(null);
if (contents != null ) {
var flavors = contents.getTransferDataFlavors();
if (flavors != null ) {
set.addAll$java_util_Collection((I$[9]||$incl$(9)).asList$TTA(flavors));
}}return set;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:01:54
