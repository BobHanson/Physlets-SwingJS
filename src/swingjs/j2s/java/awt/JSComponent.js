(function(){var P$=Clazz.newPackage("java.awt"),I$=[['Thread','swingjs.JSFrameViewer','java.lang.Boolean','javax.swing.UIManager']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSComponent", null, 'java.awt.Component');
C$.incr = 0;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.isAppletFrame = false;
this.isFramedApplet = false;
this.htmlName = null;
this.num = 0;
this.isRootPane = false;
this.isContentPane = false;
this.canvas = null;
this.appletViewer = null;
this.frameViewer = null;
this.topFrameViewer = null;
this.ui = null;
this.uiClassID = null;
this.peerVis = null;
this.isBackgroundPainted = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.appletViewer = ((I$[1]||$incl$(1)).currentThread()).appletViewer;
this.uiClassID = "ComponentUI";
}, 1);

Clazz.newMeth(C$, 'resizeOriginal$I$I', function (width, height) {
this.resize$I$I(width, height);
});

Clazz.newMeth(C$, 'ensurePropertyChangeListener$java_awt_Component$java_awt_Component', function (c, listener) {
if (Clazz.instanceOf(listener, "java.beans.PropertyChangeListener")) {
c.removePropertyChangeListener$java_beans_PropertyChangeListener(listener);
c.addPropertyChangeListener$java_beans_PropertyChangeListener(listener);
} else if (listener != null ) {
System.err.println$S("JSComponent: " + listener + " is not a PropertyChangeListener -- modal dialog will fail." );
}}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.num = ++C$.incr;
}, 1);

Clazz.newMeth(C$, 'getGraphics', function () {
if (this.width == 0 || this.height == 0  || !this.isVisible() ) return null;
if (this.frameViewer != null ) return this.frameViewer.getGraphics$I$I(0, 0).create();
if (this.parent == null ) {
return null;
}var g = this.parent.getGraphics();
if (g == null ) return null;
g.translate$I$I(this.x, (this.isContentPane ? 0 : this.y));
g.setClip$I$I$I$I(0, 0, this.width, this.height);
g.setFont$java_awt_Font(this.getFont());
return g;
});

Clazz.newMeth(C$, 'setFrameViewer$swingjs_JSFrameViewer', function (viewer) {
return this.frameViewer = (viewer == null  ? viewer = Clazz.new_((I$[2]||$incl$(2))).setForWindow$java_awt_Container(this) : viewer);
});

Clazz.newMeth(C$, 'getFrameViewer', function () {
var parent = null;
return (this.topFrameViewer != null  ? this.topFrameViewer : this.frameViewer != null  ? this.topFrameViewer = this.frameViewer : (parent = this.getParent()) == null  ? null : (this.topFrameViewer = parent.getFrameViewer()));
});

Clazz.newMeth(C$, 'getHTMLName$S', function (uid) {
return (this.htmlName == null  ? this.htmlName = this.appContext.getThreadGroup().getName() + "_" + uid + "_" + this.num  : this.htmlName);
});

Clazz.newMeth(C$, 'getUIClassID', function () {
return this.uiClassID;
});

Clazz.newMeth(C$, 'setUI$javax_swing_plaf_ComponentUI', function (ui) {
this.ui = ui;
if (ui != null ) {
ui.installJS();
}});

Clazz.newMeth(C$, 'getUI', function () {
return this.ui;
});

Clazz.newMeth(C$, 'updatePeerVisibility$Z', function (isVisible) {
if (this.getOrCreatePeer() == null ) this.peerVis = (isVisible ? (I$[3]||$incl$(3)).TRUE : (I$[3]||$incl$(3)).FALSE);
 else this.updatePeerVisibilityOrig$Z(isVisible);
});

Clazz.newMeth(C$, 'getOrCreatePeer', function () {
return (this.ui == null  ? null : this.ui == null  ? null : this.peer == null  ? (this.peer = this.getToolkit().createComponent$java_awt_Component(this)) : this.peer);
});

Clazz.newMeth(C$, 'updateUI', function () {
if (this.ui == null ) this.setUI$javax_swing_plaf_ComponentUI((I$[4]||$incl$(4)).getUI$java_awt_Component(this));
});

Clazz.newMeth(C$, 'getJSGraphic2D$java_awt_Graphics', function (g) {
{
return (g.mark ? g : null);
}
});

Clazz.newMeth(C$, 'checkBackgroundPainted$swingjs_JSGraphics2D', function (jsg) {
if (jsg == null ) {
this.isBackgroundPainted = false;
return;
}this.isBackgroundPainted = jsg.isBackgroundPainted();
if (this.isBackgroundPainted) {
this.ui.setBackgroundPainted();
(this).getRootPane().getUI().setBackgroundPainted();
}});

Clazz.newMeth(C$, 'selfOrParentBackgroundPainted', function () {
var c = this;
while (c != null ){
if (c.isBackgroundPainted) return true;
c = c.getParent();
}
return false;
});

Clazz.newMeth(C$, 'isBackgroundSet', function () {
return this.background != null ;
});
})();
//Created 2018-05-15 01:01:52
