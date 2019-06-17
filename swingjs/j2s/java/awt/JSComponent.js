(function(){var P$=Clazz.newPackage("java.awt"),I$=[[0,'Thread','java.awt.Container','java.awt.Insets','javax.swing.JComponent','swingjs.JSFrameViewer','Boolean','javax.swing.UIManager','java.util.Arrays','swingjs.JSUtil','javax.swing.border.AbstractBorder']],$I$=function(i){return I$[i]||(I$[i]=Clazz.load(I$[0][i]))};
var C$=Clazz.newClass(P$, "JSComponent", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.awt.Component');
C$.秘incr=0;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.秘isAppletFrame=false;
this.秘isFramedApplet=false;
this.秘htmlName=null;
this.秘num=0;
this.秘tempInsets=null;
this.秘gtemp=null;
this.秘isRootPane=false;
this.秘isContentPane=false;
this.秘appletViewer=null;
this.秘frameViewer=null;
this.秘topFrameViewer=null;
this.秘canvas=null;
this.ui=null;
this.秘uiClassID=null;
this.秘peerVis=null;
this.秘border=null;
this.秘iPaintMyself=0;
this.秘repaintAsUpdate=false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.秘appletViewer=($I$(1).currentThread$()).秘appletViewer;
this.秘iPaintMyself=0;
this.秘repaintAsUpdate=true;
}, 1);

Clazz.newMeth(C$, '秘resizeOriginal$I$I', function (width, height) {
this.resize$I$I(width, height);
});

Clazz.newMeth(C$, '秘ensurePropertyChangeListener$java_awt_Component$java_awt_Component', function (c, listener) {
if (Clazz.instanceOf(listener, "java.beans.PropertyChangeListener")) {
c.removePropertyChangeListener$java_beans_PropertyChangeListener(listener);
c.addPropertyChangeListener$java_beans_PropertyChangeListener(listener);
} else if (listener != null ) {
System.err.println$S("JSComponent: " + listener + " is not a PropertyChangeListener -- modal dialog will fail." );
}}, 1);

Clazz.newMeth(C$, '秘getChildArray$java_awt_Container', function (c) {
return (c == null  ? $I$(2).EMPTY_ARRAY : c.getChildArray$());
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.秘num=++C$.秘incr;
}, 1);

Clazz.newMeth(C$, 'getGraphics$', function () {
if (this.width == 0 || this.height == 0  || !this.isVisible$() ) return null;
var g;
if (this.秘frameViewer != null ) {
g=this.秘frameViewer.getGraphics$().create$();
if (this.秘isContentPane) {
if (this.秘tempInsets == null ) this.秘tempInsets=Clazz.new_($I$(3).c$$I$I$I$I,[0, 0, 0, 0]);
(this).getRootPane$().getInsets$java_awt_Insets(this.秘tempInsets);
if (this.秘tempInsets.left != 0 || this.秘tempInsets.top != 0 ) g.translate$I$I(this.秘tempInsets.left, this.秘tempInsets.top);
}return g;
}if (this.parent == null ) {
return null;
}g=this.parent.getGraphics$();
if (g == null ) return null;
if (!$I$(4).isComponentObtainingGraphicsFrom$java_awt_Component(null) && !this.秘paintsSelf$() ) {
this.秘setPaintsSelf$I(1);
(this.ui).clearPaintPath$();
}g.translate$I$I(this.x, (this.秘isContentPane ? 0 : this.y));
g.clipRect$I$I$I$I(0, 0, this.width, this.height);
g.setFont$java_awt_Font(this.getFont$());
return g;
});

Clazz.newMeth(C$, 'addNotify$', function () {
if (this.秘paintsSelf$()) (this.ui).clearPaintPath$();
C$.superclazz.prototype.addNotify$.apply(this, []);
});

Clazz.newMeth(C$, 'setFrameViewer$swingjs_JSFrameViewer', function (viewer) {
return this.秘frameViewer=(viewer == null  ? viewer=Clazz.new_($I$(5)).setForWindow$javax_swing_RootPaneContainer(this) : viewer);
});

Clazz.newMeth(C$, 'getFrameViewer$', function () {
var parent=null;
return (this.秘topFrameViewer != null  ? this.秘topFrameViewer : this.秘frameViewer != null  ? this.秘topFrameViewer=this.秘frameViewer : (parent=this.getParent$()) == null  ? null : (this.秘topFrameViewer=parent.getFrameViewer$()));
});

Clazz.newMeth(C$, 'getHTMLName$S', function (uid) {
return (this.秘htmlName == null  ? this.秘htmlName=this.appContext.getThreadGroup$().getName$() + "_" + uid + "_" + this.秘num  : this.秘htmlName);
});

Clazz.newMeth(C$, 'getUIClassID$', function () {
return (this.秘uiClassID == null  ? this.秘uiClassID="ComponentUI" : this.秘uiClassID);
});

Clazz.newMeth(C$, 'setUIClassID$S', function (id) {
this.秘uiClassID=id;
});

Clazz.newMeth(C$, 'setUI$javax_swing_plaf_ComponentUI', function (ui) {
this.ui=ui;
});

Clazz.newMeth(C$, 'getUI$', function () {
return this.ui;
});

Clazz.newMeth(C$, 'isDisplayable$', function () {
return C$.秘getTopInvokableAncestor$java_awt_Component$Z(this, false) != null ;
});

Clazz.newMeth(C$, 'updatePeerVisibility$Z', function (isVisible) {
if (this.getOrCreatePeer$() == null ) this.秘peerVis=(isVisible ? $I$(6).TRUE : $I$(6).FALSE);
 else this.updatePeerVisibilityOrig$Z(isVisible);
});

Clazz.newMeth(C$, 'getOrCreatePeer$', function () {
return (this.ui == null  ? null : this.peer == null  ? (this.peer=this.getToolkit$().createComponent$java_awt_Component(this)) : this.peer);
});

Clazz.newMeth(C$, 'updateUI$', function () {
if (this.秘uiClassID == null ) this.秘uiClassID=this.getUIClassID$();
if (this.ui == null ) this.setUI$javax_swing_plaf_ComponentUI($I$(7).getUI$java_awt_Component(this));
});

Clazz.newMeth(C$, '秘getJSGraphic2D$java_awt_Graphics', function (g) {
return (g.mark$ ? g :null);
});

Clazz.newMeth(C$, '秘isAWT$', function () {
return (!!this.isAWT$ ||false);
});

Clazz.newMeth(C$, '秘setIsAWT$', function () {

this.isAWT$ = true;
});

Clazz.newMeth(C$, 'canPaint$', function () {
return (this.秘repaintAsUpdate && this.秘isAWT$()  || !(Clazz.instanceOf(this.peer, "java.awt.peer.LightweightPeer")) );
});

Clazz.newMeth(C$, 'isBackgroundSet$', function () {
return (this.background == null  ? false : this.isAWT$ ||false ? !(Clazz.instanceOf(this.background, "javax.swing.plaf.UIResource")) : true);
});

Clazz.newMeth(C$, 'isForegroundSet$', function () {
return (this.foreground == null  ? false : this.isAWT$ ||false ? !(Clazz.instanceOf(this.foreground, "javax.swing.plaf.UIResource")) : true);
});

Clazz.newMeth(C$, 'isFontSet$', function () {
return ((this.font == null  ? null : new Boolean(this.isAWT$ ||false ? !(Clazz.instanceOf(this.font, "javax.swing.plaf.FontUIResource")) : true))).booleanValue$();
});

Clazz.newMeth(C$, '秘updateUIZOrder$', function () {
var n=(this).getComponentCount$();
if (n < 2) return;
var components=C$.秘getChildArray$java_awt_Container(this);
var zorders=Clazz.array(Integer.TYPE, [n]);
for (var i=0; i < n; i++) zorders[i]=(components[i].getUI$()).getZIndex$S(null);

$I$(8).sort$IA(zorders);
for (var i=0; i < n; i++) (components[i].getUI$()).setZOrder$I(zorders[n - 1 - i ]);

});

Clazz.newMeth(C$, 'invalidateComp$', function () {
C$.superclazz.prototype.invalidateComp$.apply(this, []);
if (this.ui != null ) (this.ui).invalidate$();
});

Clazz.newMeth(C$, 'validate$', function () {
var wasValid=this.isValid$();
C$.superclazz.prototype.validate$.apply(this, []);
if (this.ui != null  && !wasValid ) (this.ui).endValidate$();
});

Clazz.newMeth(C$, '秘paintWithBackgroundCheck$java_awt_Graphics', function (g) {
var jcg=this.秘getJSGraphic2D$java_awt_Graphics(g);
this.秘checkBackgroundPainted$swingjs_JSGraphics2D$Z(jcg, true);
this.paint$java_awt_Graphics(g);
this.秘checkBackgroundPainted$swingjs_JSGraphics2D$Z(jcg, false);
});

Clazz.newMeth(C$, '秘paintContainerBackgroundCheck$java_awt_Graphics', function (g) {
var jcg=this.秘getJSGraphic2D$java_awt_Graphics(g);
this.秘checkBackgroundPainted$swingjs_JSGraphics2D$Z(jcg, true);
(this).paintContainer$java_awt_Graphics(g);
this.秘checkBackgroundPainted$swingjs_JSGraphics2D$Z(jcg, false);
});

Clazz.newMeth(C$, 'addKeyListener$java_awt_event_KeyListener', function (l) {
C$.superclazz.prototype.addKeyListener$java_awt_event_KeyListener.apply(this, [l]);
if (l != null  && this.ui != null  ) (this.ui).enableJSKeys$Z(true);
});

Clazz.newMeth(C$, 'removeKeyListener$java_awt_event_KeyListener', function (l) {
C$.superclazz.prototype.removeKeyListener$java_awt_event_KeyListener.apply(this, [l]);
if (this.keyListener == null  && this.ui != null  ) (this.ui).enableJSKeys$Z(false);
});

Clazz.newMeth(C$, '秘jsInputMapSet$', function () {
if (this.ui != null ) (this.ui).enableJSKeys$Z(true);
});

Clazz.newMeth(C$, '秘getTopInvokableAncestor$java_awt_Component$Z', function (c, andFocusable) {
for (var p=c; p != null ; p=C$.秘nextHigher$java_awt_Component(p)) {
if (p.isWindowOrJSApplet$() && (!andFocusable || (p).isFocusableWindow$() ) ) {
return p;
}}
return null;
}, 1);

Clazz.newMeth(C$, '秘nextHigher$java_awt_Component', function (c) {
var p=c.getParent$();
if (p == null  && Clazz.instanceOf(c, "javax.swing.JPopupMenu") ) p=(c).getInvoker$();
return p;
}, 1);

Clazz.newMeth(C$, '秘isFocusSetAndEnabled$', function () {
return this.秘isFocusableSet && this.isFocusable$() ;
});

Clazz.newMeth(C$, '秘checkBackgroundPainted$swingjs_JSGraphics2D$Z', function (jsg, init) {
if (jsg == null  || init ) {
this.秘gtemp=jsg;
(this.ui).paintBackground$swingjs_JSGraphics2D(jsg);
return;
}this.秘gtemp=null;
});

Clazz.newMeth(C$, '秘setPaintsSelf$I', function (flag) {
return (this.秘iPaintMyself == 2 ? 2 : (this.秘iPaintMyself=flag));
});

Clazz.newMeth(C$, '秘paintsSelf$', function () {
if (this.秘iPaintMyself == 0) {
this.秘iPaintMyself=this.秘setPaintsSelf$I($I$(9).isOverridden$O$S$Class(this, "paint$java_awt_Graphics", Clazz.getClass($I$(4))) || $I$(9).isOverridden$O$S$Class(this, "paintComponent$java_awt_Graphics", Clazz.getClass($I$(4))) || $I$(9).isOverridden$O$S$Class(this, "paintContainer$java_awt_Graphics", Clazz.getClass($I$(2))) || $I$(9).isOverridden$O$S$Class(this, "update$java_awt_Graphics", Clazz.getClass($I$(4))) || $I$(9).isOverridden$O$S$Class(this.秘border, "paintBorder$java_awt_Component$java_awt_Graphics$I$I$I$I", Clazz.getClass($I$(10)))   ? 1 : -1);
}return (this.秘iPaintMyself != -1);
});

Clazz.newMeth(C$, '秘selfOrChildIsPainted$', function () {
return this.秘paintsSelf$();
});

Clazz.newMeth(C$, 'removeAll$', function () {
this.秘setPaintsSelf$I(0);
(this).paintImmediately$I$I$I$I(0, 0, this.width, this.height);
});

Clazz.newMeth(C$, '秘update$', function () {
var g=this.getGraphics$();
try {
this.update$java_awt_Graphics(g);
} finally {
g.dispose$();
}
});

Clazz.newMeth(C$, '秘repaint$', function () {
if (this.秘isAWT$()) {
this.秘repaintAsUpdate=false;
try {
(this).repaint$();
} finally {
this.秘repaintAsUpdate=true;
}
} else {
this.repaint$();
}});
;
(function(){var C$=Clazz.newInterface(P$.JSComponent, "A2SComponentWrapper", function(){
});
})()
;
(function(){var C$=Clazz.newInterface(P$.JSComponent, "A2SWrappedComponent", function(){
});
})()
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-17 01:19:22 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
