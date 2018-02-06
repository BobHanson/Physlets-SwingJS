(function(){var P$=Clazz.newPackage("swingjs"),I$=[['java.net.URL','swingjs.api.Interface','java.awt.Dimension','javajs.util.Lst','java.awt.Insets','swingjs.JSThreadGroup','swingjs.JSAppletThread','java.lang.Thread','sun.awt.SunToolkit','java.awt.Toolkit','swingjs.JSGraphicsConfiguration','sun.applet.AppletEventMulticaster','sun.applet.AppletEvent','javax.swing.JFrame','swingjs.JSUtil','java.awt.Font','javajs.util.PT','swingjs.JSFrameViewer','java.util.ArrayList','swingjs.JSToolkit']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSAppletViewer", null, 'swingjs.JSFrameViewer', ['java.applet.AppletStub', 'java.applet.AppletContext']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
{
try {
(I$[1]||$incl$(1)).setURLStreamHandlerFactory$java_net_URLStreamHandlerFactory((I$[2]||$incl$(2)).getInstance$S$Z("javajs.util.AjaxURLStreamHandlerFactory", false));
} catch (e) {
}
}
;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.maximumSize = 0;
this.graphicsConfig = null;
this.threadGroup = null;
this.myThread = null;
this.haveFrames = false;
this.defaultAppletSize = null;
this.currentAppletSize = null;
this.nextStatus = 0;
this.status = 0;
this.listeners = null;
this.allWindows = null;
this.sharedOwnerFrame = null;
this.appContext = null;
this.timerQueue = null;
this.$isResizable = false;
this.haveResizable = false;
this.addFrame = false;
this.jAppletFrame = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.maximumSize = 2147483647;
this.haveFrames = false;
this.defaultAppletSize = Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[10, 10]);
this.currentAppletSize = Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[10, 10]);
this.status = 0;
this.allWindows = Clazz.new_((I$[4]||$incl$(4)));
}, 1);

Clazz.newMeth(C$, 'addWindow$java_awt_Window', function (window) {
this.allWindows.removeObj$O(window);
this.allWindows.addLast$TV(window);
});

Clazz.newMeth(C$, 'c$$java_util_Hashtable', function (params) {
C$.superclazz.c$$java_util_Hashtable.apply(this, [params]);
C$.$init$.apply(this);
System.out.println$S("JSAppletViewer initializing2");
this.isApplet = true;
this.appletViewer = this;
p$.setDisplayParams$java_util_Hashtable.apply(this, [params]);
}, 1);

Clazz.newMeth(C$, 'setDisplayParams$java_util_Hashtable', function (params) {
this.display = params.get$O("display");
var s = "" + params.get$O("isResizable");
this.$isResizable = "true".equalsIgnoreCase$S(s);
this.haveResizable = (this.$isResizable || "false".equalsIgnoreCase$S(s) );
this.addFrame = "true".equalsIgnoreCase$S("" + params.get$O("addFrame"));
this.insets = Clazz.new_((I$[5]||$incl$(5)).c$$I$I$I$I,[0, 0, 0, 0]);
this.threadGroup = Clazz.new_((I$[6]||$incl$(6)).c$$S,[this.appletName]);
this.myThread = Clazz.new_((I$[7]||$incl$(7)).c$$swingjs_JSAppletViewer$ThreadGroup$S,[this, this.threadGroup, this.appletName]);
(I$[8]||$incl$(8)).thisThread = (this.myThread);
this.appContext = (I$[9]||$incl$(9)).createNewAppContext();
(I$[10]||$incl$(10)).getDefaultToolkit();
Clazz.new_((I$[11]||$incl$(11))).getDevice();
});

Clazz.newMeth(C$, 'start', function () {
if (this.status == 0) this.myThread.start();
 else this.showStatus$S("already started");
});

Clazz.newMeth(C$, 'addAppletListener$sun_applet_AppletListener', function (l) {
this.listeners = (I$[12]||$incl$(12)).add$sun_applet_AppletListener$sun_applet_AppletListener(this.listeners, l);
});

Clazz.newMeth(C$, 'removeAppletListener$sun_applet_AppletListener', function (l) {
this.listeners = (I$[12]||$incl$(12)).remove$sun_applet_AppletListener$sun_applet_AppletListener(this.listeners, l);
});

Clazz.newMeth(C$, 'dispatchAppletEvent$I$O', function (id, argument) {
if (this.listeners != null ) {
var evt = Clazz.new_((I$[13]||$incl$(13)).c$$O$I$O,[this, id, argument]);
this.listeners.appletStateChanged$sun_applet_AppletEvent(evt);
}});

Clazz.newMeth(C$, 'isActive', function () {
return true;
});

Clazz.newMeth(C$, 'appletResize$I$I', function (width, height) {
var currentSize = Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[this.currentAppletSize.width, this.currentAppletSize.height]);
this.currentAppletSize.width = width;
this.currentAppletSize.height = height;
this.japplet.setBounds$I$I$I$I(0, 0, this.getWidth(), this.getHeight());
this.japplet.getRootPane().setBounds$I$I$I$I(0, 0, this.getWidth(), this.getHeight());
this.japplet.getContentPane().setBounds$I$I$I$I(0, 0, this.getWidth(), this.getHeight());
(this.japplet.getContentPane()).revalidate();
if (this.addFrame) {
this.jAppletFrame = Clazz.new_((I$[14]||$incl$(14)).c$$S,["SwingJS Applet Viewer"]);
var pane = this.japplet.getContentPane();
this.jAppletFrame.setContentPane$java_awt_Container(pane);
this.japplet.setVisible$Z(false);
this.jAppletFrame.pack();
this.jAppletFrame.setDefaultCloseOperation$I(2);
}this.japplet.repaint$I$I$I$I(0, 0, this.getWidth(), this.getHeight());
this.dispatchAppletEvent$I$O(51234, currentSize);
});

Clazz.newMeth(C$, 'getDocumentBase', function () {
try {
return Clazz.new_((I$[1]||$incl$(1)).c$$S,[this.params.get$O("documentBase")]);
} catch (e) {
if (Clazz.exceptionOf(e, "java.net.MalformedURLException")){
return null;
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'getCodeBase', function () {
try {
return Clazz.new_((I$[1]||$incl$(1)).c$$S,[this.params.get$O("codePath")]);
} catch (e) {
if (Clazz.exceptionOf(e, "java.net.MalformedURLException")){
return null;
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'getParameter$S', function (name) {
var s = this.params.get$O(name);
System.out.println$S("get parameter: " + name + " = " + s );
return (s == null  ? null : "" + s);
});

Clazz.newMeth(C$, 'getAppletContext', function () {
return this;
});

Clazz.newMeth(C$, 'getHeight', function () {
return this.html5Applet._getHeight();
});

Clazz.newMeth(C$, 'getWidth', function () {
return this.html5Applet._getWidth();
});

Clazz.newMeth(C$, 'setBounds$I$I$I$I', function (x, y, width, height) {
this.japplet.reshape$I$I$I$I(x, y, width, height);
this.currentAppletSize.width = width;
this.currentAppletSize.height = height;
});

Clazz.newMeth(C$, 'getImage$java_net_URL', function (url) {
return null;
});

Clazz.newMeth(C$, 'getApplet$S', function (name) {
var applet = null;
{
applet = SwingJS._applets[name]; applet && (applet = applet._applet);
}
return applet;
});

Clazz.newMeth(C$, 'getApplets', function () {
return null;
});

Clazz.newMeth(C$, 'showDocument$java_net_URL', function (url) {
(I$[15]||$incl$(15)).showWebPage$java_net_URL$O(url, null);
});

Clazz.newMeth(C$, 'showDocument$java_net_URL$S', function (url, target) {
(I$[15]||$incl$(15)).showWebPage$java_net_URL$O(url, target);
});

Clazz.newMeth(C$, 'showStatus$S', function (status) {
(I$[15]||$incl$(15)).log$S(status);
{
Clazz._LoaderProgressMonitor.showStatus(status, true);
}
});

Clazz.newMeth(C$, 'showAppletStatus$S', function (status) {
this.getAppletContext().showStatus$S(this.htmlName + " " + status );
});

Clazz.newMeth(C$, 'showAppletException$Throwable', function (t) {
{
this.showAppletStatus("error " + (t.getMessage ? t.getMessage() : t));
if (t.printStackTrace) t.printStackTrace();
else System.out.println(t.stack);
}
});

Clazz.newMeth(C$, 'run1$I', function (mode) {
System.out.println$S("JSAppletViewer thread run1 mode=" + mode + " status=" + this.nextStatus );
var ok = false;
switch (mode) {
case 0:
this.currentAppletSize.width = this.defaultAppletSize.width = this.getWidth();
this.currentAppletSize.height = this.defaultAppletSize.height = this.getHeight();
this.nextStatus = 1;
ok = true;
break;
case 1:
switch (this.nextStatus) {
case 1:
if (this.status != 0) {
p$.showAppletStatus$S.apply(this, ["notdisposed"]);
this.status = 7;
break;
}System.out.println$S("JSAppletViewer runloader");
p$.runLoader.apply(this, []);
this.nextStatus = (this.main == null  ? 2 : 76);
ok = true;
break;
case 2:
if (this.status != 1 && this.status != 5 ) {
p$.showAppletStatus$S.apply(this, ["notloaded"]);
break;
}System.out.println$S("JSAppletViewer init");
this.japplet.setFont$java_awt_Font(Clazz.new_((I$[16]||$incl$(16)).c$$S$I$I,["Dialog", 0, 12]));
this.japplet.resize$java_awt_Dimension(this.defaultAppletSize);
this.japplet.init();
this.japplet.validate();
this.status = 2;
p$.showAppletStatus$S.apply(this, ["initialized"]);
this.nextStatus = 3;
ok = true;
break;
case 3:
if (this.status != 2 && this.status != 4 ) {
p$.showAppletStatus$S.apply(this, ["notstarted"]);
this.status = 7;
break;
}this.japplet.getRootPane().addNotify();
System.out.println$S("JSAppletViewer start" + this.currentAppletSize);
this.japplet.resize$java_awt_Dimension(this.currentAppletSize);
this.japplet.start();
this.status = 3;
p$.showAppletStatus$S.apply(this, ["started"]);
this.nextStatus = 35;
ok = true;
break;
case 35:
this.japplet.getContentPane().setBounds$java_awt_Rectangle(this.japplet.getBounds());
this.japplet.setVisible$Z(true);
this.japplet.validate();
p$.showAppletStatus$S.apply(this, ["ready"]);
(I$[15]||$incl$(15)).readyCallback$S$S$java_awt_Container$swingjs_JSAppletViewer(this.appletName, this.fullName, this.applet, this);
if (this.$isResizable && !this.addFrame ) {
this.resizer = ((I$[15]||$incl$(15)).getInstance$S("swingjs.plaf.Resizer")).set$swingjs_JSFrameViewer(this);
if (this.resizer != null ) this.resizer.show();
}this.japplet.repaint();
break;
case 4:
if (this.status == 3) {
this.status = 4;
this.japplet.setVisible$Z(false);
this.japplet.stop();
p$.showAppletStatus$S.apply(this, ["stopped"]);
} else {
p$.showAppletStatus$S.apply(this, ["notstopped"]);
this.status = 7;
}break;
case 5:
if (this.status == 4 || this.status == 2 ) {
this.status = 5;
this.japplet.destroy();
p$.showAppletStatus$S.apply(this, ["destroyed"]);
} else {
p$.showAppletStatus$S.apply(this, ["notdestroyed"]);
this.status = 7;
}break;
case 75:
if (this.status == 5 || this.status == 1 ) {
p$.showAppletStatus$S.apply(this, ["notdisposed"]);
this.status = 7;
} else {
this.status = 0;
this.applet = null;
p$.showAppletStatus$S.apply(this, ["disposed"]);
}break;
case 76:
p$.showAppletStatus$S.apply(this, ["running " + this.main]);
var args = this.params.get$O("args");
if (Clazz.instanceOf(args, "java.lang.String")) args = (I$[17]||$incl$(17)).split$S$S(args, " ");
(this.applet).runMain$S$SA(this.main, args);
(I$[15]||$incl$(15)).readyCallback$S$S$java_awt_Container$swingjs_JSAppletViewer(this.appletName, this.fullName, this.applet, this);
break;
case 6:
break;
default:
System.out.println$S("unrecognized JSAppletViewer status: " + this.nextStatus);
break;
}
break;
default:
System.out.println$S("unrecognized JSAppletThread mode: " + mode);
break;
}
return (ok ? 1 : 2);
});

Clazz.newMeth(C$, 'runLoader', function () {
this.dispatchAppletEvent$I$O(51235, null);
this.status = 1;
this.main = this.getParameter$S("main");
var code = (this.main == null  ? this.getParameter$S("code") : null);
try {
if (code == null  && this.main == null  ) {
System.err.println$S("runloader.err-- \"code\" or \"main\" must be specified.");
throw Clazz.new_(Clazz.load('java.lang.InstantiationException').c$$S,["\"code\" or \"main\" must be specified."]);
}if (code == null ) code = "swingjs.JSApplet";
this.top = this.applet = this.japplet = (I$[15]||$incl$(15)).getInstance$S(code);
if (this.applet == null ) {
System.out.println$S(code + " could not be launched");
this.status = 7;
} else if (!(Clazz.instanceOf(this.applet, "javax.swing.JApplet"))) {
(I$[15]||$incl$(15)).alert$O(code + " is not a JApplet!?");
this.status = 7;
}} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.lang.InstantiationException")){
var e = e$$;
{
this.status = 7;
p$.showAppletException$Throwable.apply(this, [e]);
return;
}
} else if (Clazz.exceptionOf(e$$, "java.lang.Exception")){
var e = e$$;
{
this.status = 7;
p$.showAppletException$Throwable.apply(this, [e]);
return;
}
} else if (Clazz.exceptionOf(e$$, "java.lang.ThreadDeath")){
var e = e$$;
{
this.status = 7;
p$.showAppletStatus$S.apply(this, ["death"]);
return;
}
} else if (Clazz.exceptionOf(e$$, "java.lang.Error")){
var e = e$$;
{
this.status = 7;
p$.showAppletException$Throwable.apply(this, [e]);
return;
}
} else {
throw e$$;
}
} finally {
this.dispatchAppletEvent$I$O(51236, null);
}
if (this.applet != null ) {
this.japplet.setStub$java_applet_AppletStub(this);
this.japplet.setVisible$Z(false);
this.japplet.setDispatcher();
p$.showAppletStatus$S.apply(this, ["loaded"]);
}});

Clazz.newMeth(C$, 'newFrameViewer$Z', function (forceNew) {
return (this.haveFrames || forceNew  ? Clazz.new_((I$[18]||$incl$(18))) : null);
});

Clazz.newMeth(C$, 'getTimerQueue', function () {
return (this.timerQueue == null  ? (this.timerQueue = Clazz.new_((I$[19]||$incl$(19)))) : this.timerQueue);
});

Clazz.newMeth(C$, 'exit', function () {
for (var i = this.allWindows.size(); --i >= 0; ) try {
this.allWindows.get$I(i).dispose();
} catch (e) {
}

});

Clazz.newMeth(C$, 'getAudioClip$java_net_URL', function (url) {
return (I$[20]||$incl$(20)).getAudioClip$java_net_URL(url);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:27
