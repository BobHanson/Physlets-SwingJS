(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.awt.Color','javax.swing.JComponent','java.awt.BorderLayout','javax.swing.JRootPane','javax.swing.SwingUtilities','javax.swing.RepaintManager']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JApplet", null, 'java.applet.Applet', 'javax.swing.RootPaneContainer');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.rootPane = null;
this.rootPaneCheckingEnabled = false;
this.transferHandler = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.rootPaneCheckingEnabled = false;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.setFrameViewer$swingjs_JSFrameViewer(this.appletViewer);
this.uiClassID = "AppletUI";
p$.setJApplet.apply(this, []);
this.updateUI();
}, 1);

Clazz.newMeth(C$, 'setJApplet', function () {
this.setForeground$java_awt_Color((I$[1]||$incl$(1)).black);
this.setBackground$java_awt_Color((I$[1]||$incl$(1)).white);
this.setLocale$java_util_Locale((I$[2]||$incl$(2)).getDefaultLocale());
this.setLayout$java_awt_LayoutManager(Clazz.new_((I$[3]||$incl$(3))));
this.setRootPane$javax_swing_JRootPane(this.createRootPane());
this.rootPane.setFrameViewer$swingjs_JSFrameViewer(this.appletViewer);
this.setRootPaneCheckingEnabled$Z(true);
this.setFocusTraversalPolicyProvider$Z(true);
this.enableEvents$J(8);
});

Clazz.newMeth(C$, 'createRootPane', function () {
var rp = Clazz.new_((I$[4]||$incl$(4)).c$$S$Z,["", true]);
rp.setOpaque$Z(true);
return rp;
});

Clazz.newMeth(C$, 'setTransferHandler$javax_swing_TransferHandler', function (newHandler) {
var oldHandler = this.transferHandler;
this.transferHandler = newHandler;
(I$[5]||$incl$(5)).installSwingDropTargetAsNecessary$java_awt_Component$javax_swing_TransferHandler(this, this.transferHandler);
this.firePropertyChange$S$O$O("transferHandler", oldHandler, newHandler);
});

Clazz.newMeth(C$, 'getTransferHandler', function () {
return this.transferHandler;
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
(g).setBackground$java_awt_Color(this.getBackground());
(g).setColor$java_awt_Color(this.getForeground());
this.rootPane.paint$java_awt_Graphics(g);
});

Clazz.newMeth(C$, 'update$java_awt_Graphics', function (g) {
this.paint$java_awt_Graphics(g);
});

Clazz.newMeth(C$, 'setJMenuBar$javax_swing_JMenuBar', function (menuBar) {
this.getRootPane().setMenuBar$javax_swing_JMenuBar(menuBar);
});

Clazz.newMeth(C$, 'getJMenuBar', function () {
return this.getRootPane().getMenuBar();
});

Clazz.newMeth(C$, 'isRootPaneCheckingEnabled', function () {
return this.rootPaneCheckingEnabled;
});

Clazz.newMeth(C$, 'setRootPaneCheckingEnabled$Z', function (enabled) {
this.rootPaneCheckingEnabled = enabled;
});

Clazz.newMeth(C$, 'addImpl$java_awt_Component$O$I', function (comp, constraints, index) {
if (this.isRootPaneCheckingEnabled()) {
return this.getContentPane().add$java_awt_Component$O$I(comp, constraints, index);
}return this.addImplCont$java_awt_Component$O$I(comp, constraints, index);
});

Clazz.newMeth(C$, 'remove$java_awt_Component', function (comp) {
if (comp === this.rootPane ) {
C$.superclazz.prototype.remove$java_awt_Component.apply(this, [comp]);
} else {
this.getContentPane().remove$java_awt_Component(comp);
}});

Clazz.newMeth(C$, 'setLayout$java_awt_LayoutManager', function (manager) {
if (this.isRootPaneCheckingEnabled()) {
this.getContentPane().setLayout$java_awt_LayoutManager(manager);
} else {
C$.superclazz.prototype.setLayout$java_awt_LayoutManager.apply(this, [manager]);
}});

Clazz.newMeth(C$, 'getRootPane', function () {
return this.rootPane;
});

Clazz.newMeth(C$, 'setRootPane$javax_swing_JRootPane', function (root) {
if (this.rootPane != null ) {
this.remove$java_awt_Component(this.rootPane);
}this.rootPane = root;
if (this.rootPane != null ) {
var checkingEnabled = this.isRootPaneCheckingEnabled();
try {
this.setRootPaneCheckingEnabled$Z(false);
this.add$java_awt_Component$O(this.rootPane, "Center");
} finally {
this.setRootPaneCheckingEnabled$Z(checkingEnabled);
}
}});

Clazz.newMeth(C$, 'getContentPane', function () {
return this.getRootPane().getContentPane();
});

Clazz.newMeth(C$, 'setContentPane$java_awt_Container', function (contentPane) {
this.getRootPane().setContentPane$java_awt_Container(contentPane);
});

Clazz.newMeth(C$, 'getLayeredPane', function () {
return this.getRootPane().getLayeredPane();
});

Clazz.newMeth(C$, 'setLayeredPane$javax_swing_JLayeredPane', function (layeredPane) {
this.getRootPane().setLayeredPane$javax_swing_JLayeredPane(layeredPane);
});

Clazz.newMeth(C$, 'getGlassPane', function () {
return this.getRootPane().getGlassPane();
});

Clazz.newMeth(C$, 'setGlassPane$java_awt_Component', function (glassPane) {
this.getRootPane().setGlassPane$java_awt_Component(glassPane);
});

Clazz.newMeth(C$, 'getGraphics', function () {
(I$[2]||$incl$(2)).getGraphicsInvoked$java_awt_Component(this);
return C$.superclazz.prototype.getGraphics.apply(this, []);
});

Clazz.newMeth(C$, 'repaint$J$I$I$I$I', function (time, x, y, width, height) {
if ((I$[6]||$incl$(6)).HANDLE_TOP_LEVEL_PAINT) {
(I$[6]||$incl$(6)).currentManager$java_awt_Component(this).addDirtyRegion$java_applet_Applet$I$I$I$I(this, x, y, width, height);
} else {
C$.superclazz.prototype.repaint$J$I$I$I$I.apply(this, [time, x, y, width, height]);
}});

Clazz.newMeth(C$, 'repaintNow', function () {
this.repaint$J$I$I$I$I(100, 0, 0, this.getWidth(), this.getHeight());
});

Clazz.newMeth(C$, 'paramString', function () {
var rootPaneString = (this.rootPane != null  ? this.rootPane.toString() : "");
var rootPaneCheckingEnabledString = (this.rootPaneCheckingEnabled ? "true" : "false");
return C$.superclazz.prototype.paramString.apply(this, []) + ",rootPane=" + rootPaneString + ",rootPaneCheckingEnabled=" + rootPaneCheckingEnabledString ;
});

Clazz.newMeth(C$, 'addNotify', function () {
C$.superclazz.prototype.addNotify.apply(this, []);
this.getLayeredPane().isFramedApplet = true;
});
})();
//Created 2018-02-08 10:03:50
