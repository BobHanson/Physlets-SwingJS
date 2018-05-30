(function(){var P$=Clazz.newPackage("sun.awt.datatransfer"),I$=[['sun.awt.datatransfer.DataTransferer','java.util.HashMap','java.awt.datatransfer.DataFlavor','sun.awt.datatransfer.SunClipboard',['sun.awt.datatransfer.ClipboardTransferable','.DataFactory']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ClipboardTransferable", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, 'java.awt.datatransfer.Transferable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.flavorsToData = null;
this.flavors = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.flavorsToData = Clazz.new_((I$[2]||$incl$(2)));
this.flavors = Clazz.array((I$[3]||$incl$(3)), [0]);
}, 1);

Clazz.newMeth(C$, 'c$$sun_awt_datatransfer_SunClipboard', function (clipboard) {
C$.$init$.apply(this);
clipboard.openClipboard$sun_awt_datatransfer_SunClipboard(null);
try {
var formats = clipboard.getClipboardFormats();
if (formats != null  && formats.length > 0 ) {
var cached_data = Clazz.new_((I$[2]||$incl$(2)).c$$I$F,[formats.length, 1.0]);
var flavorsForFormats = (I$[1]||$incl$(1)).getInstance().getFlavorsForFormats$JA$java_awt_datatransfer_FlavorTable(formats, (I$[4]||$incl$(4)).flavorMap);
for (var iter = flavorsForFormats.keySet().iterator(); iter.hasNext(); ) {
var flavor = iter.next();
var lFormat = flavorsForFormats.get$O(flavor);
p$.fetchOneFlavor$sun_awt_datatransfer_SunClipboard$java_awt_datatransfer_DataFlavor$Long$java_util_HashMap.apply(this, [clipboard, flavor, lFormat, cached_data]);
}
this.flavors=(I$[1]||$incl$(1)).getInstance().setToSortedDataFlavorArray$java_util_Set$java_util_Map(this.flavorsToData.keySet(), flavorsForFormats);
}} finally {
clipboard.closeClipboard();
}
}, 1);

Clazz.newMeth(C$, 'fetchOneFlavor$sun_awt_datatransfer_SunClipboard$java_awt_datatransfer_DataFlavor$Long$java_util_HashMap', function (clipboard, flavor, lFormat, cached_data) {
if (!this.flavorsToData.containsKey$O(flavor)) {
var format = lFormat.longValue();
var data = null;
if (!cached_data.containsKey$O(lFormat)) {
try {
data=clipboard.getClipboardData$J(format);
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var e = e$$;
{
data=e;
}
} else {
var e = e$$;
{
e.printStackTrace();
}
}
}
cached_data.put$TK$TV(lFormat, data);
} else {
data=cached_data.get$O(lFormat);
}if (Clazz.instanceOf(data, "java.io.IOException")) {
this.flavorsToData.put$TK$TV(flavor, data);
return false;
} else if (data != null ) {
this.flavorsToData.put$TK$TV(flavor, Clazz.new_((I$[5]||$incl$(5)).c$$J$BA, [this, null, format, data]));
return true;
}}return false;
});

Clazz.newMeth(C$, 'getTransferDataFlavors', function () {
return this.flavors.clone();
});

Clazz.newMeth(C$, 'isDataFlavorSupported$java_awt_datatransfer_DataFlavor', function (flavor) {
return this.flavorsToData.containsKey$O(flavor);
});

Clazz.newMeth(C$, 'getTransferData$java_awt_datatransfer_DataFlavor', function (flavor) {
if (!this.isDataFlavorSupported$java_awt_datatransfer_DataFlavor(flavor)) {
throw Clazz.new_(Clazz.load('java.awt.datatransfer.UnsupportedFlavorException').c$$java_awt_datatransfer_DataFlavor,[flavor]);
}var ret = this.flavorsToData.get$O(flavor);
if (Clazz.instanceOf(ret, "java.io.IOException")) {
throw ret;
} else if (Clazz.instanceOf(ret, "sun.awt.datatransfer.ClipboardTransferable.DataFactory")) {
var factory = ret;
ret=factory.getTransferData$java_awt_datatransfer_DataFlavor(flavor);
}return ret;
});
;
(function(){var C$=Clazz.newClass(P$.ClipboardTransferable, "DataFactory", function(){
Clazz.newInstance(this, arguments[0],true,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.format = 0;
this.data = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$J$BA', function (format, data) {
C$.$init$.apply(this);
this.format=format;
this.data=data;
}, 1);

Clazz.newMeth(C$, 'getTransferData$java_awt_datatransfer_DataFlavor', function (flavor) {
return (I$[1]||$incl$(1)).getInstance().translateBytes$BA$java_awt_datatransfer_DataFlavor$J$java_awt_datatransfer_Transferable(this.data, flavor, this.format, this.this$0);
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:24
