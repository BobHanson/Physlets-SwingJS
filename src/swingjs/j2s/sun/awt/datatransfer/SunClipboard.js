(function(){var P$=Clazz.newPackage("sun.awt.datatransfer"),I$=[['java.awt.datatransfer.SystemFlavorMap','java.lang.StringBuffer','sun.awt.datatransfer.TransferableProxy','java.awt.EventQueue','sun.awt.datatransfer.SunClipboard$1','sun.awt.AppContext','sun.awt.datatransfer.ClipboardTransferable','sun.awt.datatransfer.DataTransferer','java.lang.Boolean','sun.awt.datatransfer.SunClipboard$2','sun.awt.SunToolkit','sun.awt.PeerEvent','sun.awt.EventListenerAggregate','java.awt.datatransfer.FlavorListener','java.awt.datatransfer.FlavorEvent','sun.awt.datatransfer.SunClipboard$1SunFlavorChangeNotifier']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SunClipboard", null, 'java.awt.datatransfer.Clipboard', 'java.beans.PropertyChangeListener');
C$.flavorMap = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.flavorMap = (I$[1]||$incl$(1)).getDefaultFlavorMap();
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.contentsContext = null;
this.CLIPBOARD_FLAVOR_LISTENER_KEY = null;
this.numberOfFlavorListeners = 0;
this.$currentDataFlavors = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.contentsContext = null;
this.numberOfFlavorListeners = 0;
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.superclazz.c$$S.apply(this, [name]);
C$.$init$.apply(this);
this.CLIPBOARD_FLAVOR_LISTENER_KEY=Clazz.new_((I$[2]||$incl$(2)).c$$S,[name + "_CLIPBOARD_FLAVOR_LISTENER_KEY"]);
}, 1);

Clazz.newMeth(C$, 'setContents$java_awt_datatransfer_Transferable$java_awt_datatransfer_ClipboardOwner', function (contents, owner) {
if (contents == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["contents"]);
}p$.initContext.apply(this, []);
var oldOwner = this.owner;
var oldContents = this.contents;
try {
this.owner=owner;
this.contents=Clazz.new_((I$[3]||$incl$(3)).c$$java_awt_datatransfer_Transferable$Z,[contents, true]);
this.setContentsNative$java_awt_datatransfer_Transferable(contents);
} finally {
if (oldOwner != null  && oldOwner !== owner  ) {
(I$[4]||$incl$(4)).invokeLater$Runnable(((
(function(){var C$=Clazz.newClass(P$, "SunClipboard$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'Runnable', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'run', function () {
this.$finals.oldOwner.lostOwnership$java_awt_datatransfer_Clipboard$java_awt_datatransfer_Transferable(this.b$['sun.awt.datatransfer.SunClipboard'], this.$finals.oldContents);
});
})()
), Clazz.new_((I$[5]||$incl$(5)).$init$, [this, {oldOwner: oldOwner, oldContents: oldContents}])));
}}
});

Clazz.newMeth(C$, 'initContext', function () {
var context = (I$[6]||$incl$(6)).getAppContext();
if (this.contentsContext !== context ) {
{
if (context.isDisposed()) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Can\'t set contents from disposed AppContext"]);
}context.addPropertyChangeListener$S$java_beans_PropertyChangeListener("disposed", this);
}if (this.contentsContext != null ) {
this.contentsContext.removePropertyChangeListener$S$java_beans_PropertyChangeListener("disposed", this);
}this.contentsContext=context;
}});

Clazz.newMeth(C$, 'getContents$O', function (requestor) {
if (this.contents != null ) {
return this.contents;
}return Clazz.new_((I$[7]||$incl$(7)).c$$sun_awt_datatransfer_SunClipboard,[this]);
});

Clazz.newMeth(C$, 'getContextContents', function () {
var context = (I$[6]||$incl$(6)).getAppContext();
return (context === this.contentsContext ) ? this.contents : null;
});

Clazz.newMeth(C$, 'getAvailableDataFlavors', function () {
var cntnts = p$.getContextContents.apply(this, []);
if (cntnts != null ) {
return cntnts.getTransferDataFlavors();
}var formats = this.getClipboardFormatsOpenClose();
return (I$[8]||$incl$(8)).getInstance().getFlavorsForFormatsAsArray$JA$java_awt_datatransfer_FlavorTable(formats, C$.flavorMap);
});

Clazz.newMeth(C$, 'isDataFlavorAvailable$java_awt_datatransfer_DataFlavor', function (flavor) {
if (flavor == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["flavor"]);
}var cntnts = p$.getContextContents.apply(this, []);
if (cntnts != null ) {
return cntnts.isDataFlavorSupported$java_awt_datatransfer_DataFlavor(flavor);
}var formats = this.getClipboardFormatsOpenClose();
return C$.formatArrayAsDataFlavorSet$JA(formats).contains$O(flavor);
});

Clazz.newMeth(C$, 'getData$java_awt_datatransfer_DataFlavor', function (flavor) {
if (flavor == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["flavor"]);
}var cntnts = p$.getContextContents.apply(this, []);
if (cntnts != null ) {
return cntnts.getTransferData$java_awt_datatransfer_DataFlavor(flavor);
}var format = 0;
var data = null;
var localeTransferable = null;
try {
this.openClipboard$sun_awt_datatransfer_SunClipboard(null);
var formats = this.getClipboardFormats();
var lFormat = (I$[8]||$incl$(8)).getInstance().getFlavorsForFormats$JA$java_awt_datatransfer_FlavorTable(formats, C$.flavorMap).get$O(flavor);
if (lFormat == null ) {
throw Clazz.new_(Clazz.load('java.awt.datatransfer.UnsupportedFlavorException').c$$java_awt_datatransfer_DataFlavor,[flavor]);
}format=lFormat.longValue();
data=this.getClipboardData$J(format);
if ((I$[8]||$incl$(8)).getInstance().isLocaleDependentTextFormat$J(format)) {
localeTransferable=this.createLocaleTransferable$JA(formats);
}} finally {
this.closeClipboard();
}
return (I$[8]||$incl$(8)).getInstance().translateBytes$BA$java_awt_datatransfer_DataFlavor$J$java_awt_datatransfer_Transferable(data, flavor, format, localeTransferable);
});

Clazz.newMeth(C$, 'createLocaleTransferable$JA', function (formats) {
return null;
});

Clazz.newMeth(C$, 'openClipboard$sun_awt_datatransfer_SunClipboard', function (newOwner) {
});

Clazz.newMeth(C$, 'closeClipboard', function () {
});

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (evt) {
if ("disposed".equals$O(evt.getPropertyName()) && (I$[9]||$incl$(9)).TRUE.equals(evt.getNewValue()) ) {
var disposedContext = evt.getSource();
this.lostOwnershipLater$sun_awt_AppContext(disposedContext);
}});

Clazz.newMeth(C$, 'lostOwnershipImpl', function () {
this.lostOwnershipLater$sun_awt_AppContext(null);
});

Clazz.newMeth(C$, 'lostOwnershipLater$sun_awt_AppContext', function (disposedContext) {
var context = this.contentsContext;
if (context == null ) {
return;
}var runnable = ((
(function(){var C$=Clazz.newClass(P$, "SunClipboard$2", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'Runnable', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'run', function () {
var sunClipboard = this.b$['sun.awt.datatransfer.SunClipboard'];
var owner = null;
var contents = null;
{
var context = sunClipboard.contentsContext;
if (context == null ) {
return;
}if (this.$finals.disposedContext == null  || context === this.$finals.disposedContext  ) {
owner=sunClipboard.owner;
contents=sunClipboard.contents;
sunClipboard.contentsContext=null;
sunClipboard.owner=null;
sunClipboard.contents=null;
sunClipboard.clearNativeContext();
context.removePropertyChangeListener$S$java_beans_PropertyChangeListener("disposed", sunClipboard);
} else {
return;
}}if (owner != null ) {
owner.lostOwnership$java_awt_datatransfer_Clipboard$java_awt_datatransfer_Transferable(sunClipboard, contents);
}});
})()
), Clazz.new_((I$[10]||$incl$(10)).$init$, [this, {disposedContext: disposedContext}]));
(I$[11]||$incl$(11)).postEvent$sun_awt_AppContext$java_awt_AWTEvent(context, Clazz.new_((I$[12]||$incl$(12)).c$$O$Runnable$J,[this, runnable, 1]));
});

Clazz.newMeth(C$, 'getClipboardFormatsOpenClose', function () {
try {
this.openClipboard$sun_awt_datatransfer_SunClipboard(null);
return this.getClipboardFormats();
} finally {
this.closeClipboard();
}
});

Clazz.newMeth(C$, 'formatArrayAsDataFlavorSet$JA', function (formats) {
return (formats == null ) ? null : (I$[8]||$incl$(8)).getInstance().getFlavorsForFormatsAsSet$JA$java_awt_datatransfer_FlavorTable(formats, C$.flavorMap);
}, 1);

Clazz.newMeth(C$, 'addFlavorListener$java_awt_datatransfer_FlavorListener', function (listener) {
if (listener == null ) {
return;
}var appContext = (I$[6]||$incl$(6)).getAppContext();
var contextFlavorListeners = appContext.get$O(this.CLIPBOARD_FLAVOR_LISTENER_KEY);
if (contextFlavorListeners == null ) {
contextFlavorListeners=Clazz.new_((I$[13]||$incl$(13)).c$$Class,[Clazz.getClass((I$[14]||$incl$(14)),['flavorsChanged$java_awt_datatransfer_FlavorEvent'])]);
appContext.put$O$O(this.CLIPBOARD_FLAVOR_LISTENER_KEY, contextFlavorListeners);
}contextFlavorListeners.add$java_util_EventListener(listener);
if (this.numberOfFlavorListeners++ == 0) {
var currentFormats = null;
try {
this.openClipboard$sun_awt_datatransfer_SunClipboard(null);
currentFormats=this.getClipboardFormats();
} catch (exc) {
if (Clazz.exceptionOf(exc, "java.lang.IllegalStateException")){
} else {
throw exc;
}
} finally {
this.closeClipboard();
}
this.$currentDataFlavors=C$.formatArrayAsDataFlavorSet$JA(currentFormats);
this.registerClipboardViewerChecked();
}});

Clazz.newMeth(C$, 'removeFlavorListener$java_awt_datatransfer_FlavorListener', function (listener) {
if (listener == null ) {
return;
}var appContext = (I$[6]||$incl$(6)).getAppContext();
var contextFlavorListeners = appContext.get$O(this.CLIPBOARD_FLAVOR_LISTENER_KEY);
if (contextFlavorListeners == null ) {
return;
}if (contextFlavorListeners.remove$java_util_EventListener(listener) && --this.numberOfFlavorListeners == 0 ) {
this.unregisterClipboardViewerChecked();
this.$currentDataFlavors=null;
}});

Clazz.newMeth(C$, 'getFlavorListeners', function () {
var contextFlavorListeners = (I$[6]||$incl$(6)).getAppContext().get$O(this.CLIPBOARD_FLAVOR_LISTENER_KEY);
return contextFlavorListeners == null  ? Clazz.array((I$[14]||$incl$(14)), [0]) : contextFlavorListeners.getListenersCopy();
});

Clazz.newMeth(C$, 'areFlavorListenersRegistered', function () {
return (this.numberOfFlavorListeners > 0);
});

Clazz.newMeth(C$, 'checkChange$JA', function (formats) {
var prevDataFlavors = this.$currentDataFlavors;
this.$currentDataFlavors=C$.formatArrayAsDataFlavorSet$JA(formats);
if ((prevDataFlavors != null ) && (this.$currentDataFlavors != null ) && prevDataFlavors.equals$O(this.$currentDataFlavors)  ) {
return;
};for (var it = (I$[6]||$incl$(6)).getAppContexts().iterator(); it.hasNext(); ) {
var appContext = it.next();
if (appContext == null  || appContext.isDisposed() ) {
continue;
}var flavorListeners = appContext.get$O(this.CLIPBOARD_FLAVOR_LISTENER_KEY);
if (flavorListeners != null ) {
var flavorListenerArray = flavorListeners.getListenersInternal();
for (var i = 0; i < flavorListenerArray.length; i++) {
(I$[11]||$incl$(11)).postEvent$sun_awt_AppContext$java_awt_AWTEvent(appContext, Clazz.new_((I$[12]||$incl$(12)).c$$O$Runnable$J,[this, Clazz.new_((I$[16]||$incl$(16)).c$$java_awt_datatransfer_FlavorListener, [this, null, flavorListenerArray[i]]), 1]));
}
}}
});
;
(function(){var C$=Clazz.newClass(P$, "SunClipboard$1SunFlavorChangeNotifier", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'Runnable', 2);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.flavorListener = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_datatransfer_FlavorListener', function (flavorListener) {
C$.$init$.apply(this);
this.flavorListener=flavorListener;
}, 1);

Clazz.newMeth(C$, 'run', function () {
if (this.flavorListener != null ) {
this.flavorListener.flavorsChanged$java_awt_datatransfer_FlavorEvent(Clazz.new_((I$[15]||$incl$(15)).c$$java_awt_datatransfer_Clipboard,[this.this$0]));
}});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:24
