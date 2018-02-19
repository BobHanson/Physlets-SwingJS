(function(){var P$=Clazz.newPackage("java.awt.datatransfer"),I$=[['java.awt.datatransfer.DataFlavor','java.io.StringReader']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "StringSelection", null, null, ['java.awt.datatransfer.Transferable', 'java.awt.datatransfer.ClipboardOwner']);
C$.flavors = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.flavors = Clazz.array((I$[1]||$incl$(1)), -1, [(I$[1]||$incl$(1)).stringFlavor, (I$[1]||$incl$(1)).plainTextFlavor]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.data = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (data) {
C$.$init$.apply(this);
this.data = data;
}, 1);

Clazz.newMeth(C$, 'getTransferDataFlavors', function () {
return C$.flavors.clone();
});

Clazz.newMeth(C$, 'isDataFlavorSupported$java_awt_datatransfer_DataFlavor', function (flavor) {
for (var i = 0; i < C$.flavors.length; i++) {
if (flavor.equals$java_awt_datatransfer_DataFlavor(C$.flavors[i])) {
return true;
}}
return false;
});

Clazz.newMeth(C$, 'getTransferData$java_awt_datatransfer_DataFlavor', function (flavor) {
if (flavor.equals$java_awt_datatransfer_DataFlavor(C$.flavors[0])) {
return this.data;
} else if (flavor.equals$java_awt_datatransfer_DataFlavor(C$.flavors[1])) {
return Clazz.new_((I$[2]||$incl$(2)).c$$S,[this.data == null  ? "" : this.data]);
} else {
throw Clazz.new_(Clazz.load('java.awt.datatransfer.UnsupportedFlavorException').c$$java_awt_datatransfer_DataFlavor,[flavor]);
}});

Clazz.newMeth(C$, 'lostOwnership$java_awt_datatransfer_Clipboard$java_awt_datatransfer_Transferable', function (clipboard, contents) {
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:01:53
