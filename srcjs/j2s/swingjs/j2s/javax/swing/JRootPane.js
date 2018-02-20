(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['javax.swing.SwingUtilities','java.awt.Dimension','sun.awt.AppContext','javax.swing.JLayeredPane','javax.swing.JPanel','java.awt.BorderLayout',['javax.swing.JRootPane','.RootLayout']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JRootPane", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.JComponent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.windowDecorationStyle = 0;
this.menuBar = null;
this.contentPane = null;
this.layeredPane = null;
this.glassPane = null;
this.defaultButton = null;
this.defaultPressAction = null;
this.defaultReleaseAction = null;
this.useTrueDoubleBuffering = false;
this.paneCount = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.useTrueDoubleBuffering = true;
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (prefix, isApplet) {
Clazz.super_(C$, this,1);
this.uiClassID = "RootPaneUI";
this.setName$S((I$[3]||$incl$(3)).getAppContext().getThreadGroup().getName() + prefix + (++this.paneCount) + ".JRootPane" );
this.setGlassPane$java_awt_Component(this.createGlassPane());
this.setLayeredPane$javax_swing_JLayeredPane(this.createLayeredPane());
this.setContentPane$java_awt_Container(this.createContentPane());
this.setLayout$java_awt_LayoutManager(this.createRootLayout());
this.updateUI();
}, 1);

Clazz.newMeth(C$, 'setDoubleBuffered$Z', function (aFlag) {
});

Clazz.newMeth(C$, 'getWindowDecorationStyle', function () {
return this.windowDecorationStyle;
});

Clazz.newMeth(C$, 'setWindowDecorationStyle$I', function (windowDecorationStyle) {
if (windowDecorationStyle < 0 || windowDecorationStyle > 8 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Invalid decoration style"]);
}var oldWindowDecorationStyle = this.getWindowDecorationStyle();
this.windowDecorationStyle = windowDecorationStyle;
this.firePropertyChange$S$I$I("windowDecorationStyle", oldWindowDecorationStyle, windowDecorationStyle);
});

Clazz.newMeth(C$, 'createLayeredPane', function () {
var p = Clazz.new_((I$[4]||$incl$(4)));
p.setName$S(this.getName() + ".layeredPane");
return p;
});

Clazz.newMeth(C$, 'createContentPane', function () {
var c = Clazz.new_((I$[5]||$incl$(5)));
c.setName$S(this.getName() + ".contentPane");
c.setLayout$java_awt_LayoutManager(((
(function(){var C$=Clazz.newClass(P$, "JRootPane$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.awt.BorderLayout'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'addLayoutComponent$java_awt_Component$O', function (comp, constraints) {
if (constraints == null ) {
constraints = "Center";
}C$.superclazz.prototype.addLayoutComponent$java_awt_Component$O.apply(this, [comp, constraints]);
});
})()
), Clazz.new_((I$[6]||$incl$(6)), [this, null],P$.JRootPane$1)));
return c;
});

Clazz.newMeth(C$, 'createGlassPane', function () {
var c = Clazz.new_((I$[5]||$incl$(5)));
c.setName$S(this.getName() + ".glassPane");
c.setVisible$Z(false);
c.setOpaque$Z(false);
return c;
});

Clazz.newMeth(C$, 'createRootLayout', function () {
return Clazz.new_((I$[7]||$incl$(7)), [this, null]);
});

Clazz.newMeth(C$, 'setJMenuBar$javax_swing_JMenuBar', function (menu) {
if (this.menuBar != null  && this.menuBar.getParent() === this.layeredPane  ) this.layeredPane.remove$java_awt_Component(this.menuBar);
this.menuBar = menu;
if (this.menuBar != null ) this.layeredPane.add$java_awt_Component$O(this.menuBar, (I$[4]||$incl$(4)).FRAME_CONTENT_LAYER);
});

Clazz.newMeth(C$, 'setMenuBar$javax_swing_JMenuBar', function (menu) {
if (this.menuBar != null  && this.menuBar.getParent() === this.layeredPane  ) this.layeredPane.remove$java_awt_Component(this.menuBar);
this.menuBar = menu;
if (this.menuBar != null ) this.layeredPane.add$java_awt_Component$O(this.menuBar, (I$[4]||$incl$(4)).FRAME_CONTENT_LAYER);
});

Clazz.newMeth(C$, 'getJMenuBar', function () {
return this.menuBar;
});

Clazz.newMeth(C$, 'getMenuBar', function () {
return this.menuBar;
});

Clazz.newMeth(C$, 'setContentPane$java_awt_Container', function (content) {
if (content == null ) throw Clazz.new_(Clazz.load('java.awt.IllegalComponentStateException').c$$S,["contentPane cannot be set to null."]);
if (this.contentPane != null  && this.contentPane.getParent() === this.layeredPane  ) this.layeredPane.remove$java_awt_Component(this.contentPane);
this.contentPane = content;
content.isContentPane = true;
this.layeredPane.add$java_awt_Component$O(this.contentPane, (I$[4]||$incl$(4)).FRAME_CONTENT_LAYER);
});

Clazz.newMeth(C$, 'getContentPane', function () {
return this.contentPane;
});

Clazz.newMeth(C$, 'setLayeredPane$javax_swing_JLayeredPane', function (layered) {
if (layered == null ) throw Clazz.new_(Clazz.load('java.awt.IllegalComponentStateException').c$$S,["layeredPane cannot be set to null."]);
if (this.layeredPane != null  && this.layeredPane.getParent() === this  ) this.remove$java_awt_Component(this.layeredPane);
this.layeredPane = layered;
this.add$java_awt_Component$I(this.layeredPane, -1);
});

Clazz.newMeth(C$, 'getLayeredPane', function () {
return this.layeredPane;
});

Clazz.newMeth(C$, 'setGlassPane$java_awt_Component', function (glass) {
if (glass == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["glassPane cannot be set to null."]);
}var visible = false;
if (this.glassPane != null  && this.glassPane.getParent() === this  ) {
this.remove$java_awt_Component(this.glassPane);
visible = this.glassPane.isVisible();
}glass.setVisible$Z(visible);
this.glassPane = glass;
this.add$java_awt_Component$I(this.glassPane, 0);
if (visible) {
this.repaint();
}});

Clazz.newMeth(C$, 'getGlassPane', function () {
return this.glassPane;
});

Clazz.newMeth(C$, 'isValidateRoot', function () {
return true;
});

Clazz.newMeth(C$, 'isOptimizedDrawingEnabled', function () {
return !this.glassPane.isVisible();
});

Clazz.newMeth(C$, 'addNotify', function () {
C$.superclazz.prototype.addNotify.apply(this, []);
this.enableEvents$J(8);
});

Clazz.newMeth(C$, 'removeNotify', function () {
C$.superclazz.prototype.removeNotify.apply(this, []);
});

Clazz.newMeth(C$, 'setDefaultButton$javax_swing_JButton', function (defaultButton) {
var oldDefault = this.defaultButton;
if (oldDefault !== defaultButton ) {
this.defaultButton = defaultButton;
if (oldDefault != null ) {
oldDefault.repaint();
}if (defaultButton != null ) {
defaultButton.repaint();
}}this.firePropertyChange$S$O$O("defaultButton", oldDefault, defaultButton);
});

Clazz.newMeth(C$, 'getDefaultButton', function () {
return this.defaultButton;
});

Clazz.newMeth(C$, 'setUseTrueDoubleBuffering$Z', function (useTrueDoubleBuffering) {
this.useTrueDoubleBuffering = useTrueDoubleBuffering;
});

Clazz.newMeth(C$, 'getUseTrueDoubleBuffering', function () {
return this.useTrueDoubleBuffering;
});

Clazz.newMeth(C$, 'disableTrueDoubleBuffering', function () {
});

Clazz.newMeth(C$, 'addImpl$java_awt_Component$O$I', function (comp, constraints, index) {
this.addImplCont$java_awt_Component$O$I(comp, constraints, index);
if (this.glassPane != null  && this.glassPane.getParent() === this   && this.getComponent$I(0) !== this.glassPane  ) {
this.add$java_awt_Component$I(this.glassPane, 0);
}return comp;
});

Clazz.newMeth(C$, 'paramString', function () {
return C$.superclazz.prototype.paramString.apply(this, []);
});

Clazz.newMeth(C$, 'setFrameViewer$swingjs_JSFrameViewer', function (v) {
this.layeredPane.setFrameViewer$swingjs_JSFrameViewer(v);
this.contentPane.setFrameViewer$swingjs_JSFrameViewer(v);
if (this.glassPane != null ) this.glassPane.setFrameViewer$swingjs_JSFrameViewer(v);
return C$.superclazz.prototype.setFrameViewer$swingjs_JSFrameViewer.apply(this, [v]);
});
;
(function(){var C$=Clazz.newClass(P$.JRootPane, "DefaultAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.AbstractAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.owner = null;
this.root = null;
this.press = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JRootPane$Z', function (root, press) {
Clazz.super_(C$, this,1);
this.root = root;
this.press = press;
}, 1);

Clazz.newMeth(C$, 'setOwner$javax_swing_JButton', function (owner) {
this.owner = owner;
});

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
if (this.owner != null  && (I$[1]||$incl$(1)).getRootPane$java_awt_Component(this.owner) === this.root  ) {
var model = this.owner.getModel();
if (this.press) {
model.setArmed$Z(true);
model.setPressed$Z(true);
} else {
model.setPressed$Z(false);
}}});

Clazz.newMeth(C$, 'isEnabled', function () {
return this.owner.getModel().isEnabled();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JRootPane, "RootLayout", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.awt.LayoutManager2');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'preferredLayoutSize$java_awt_Container', function (parent) {
var rd;
var mbd;
var i = this.this$0.getInsets();
if (this.this$0.contentPane != null ) {
rd = this.this$0.contentPane.getPreferredSize();
} else {
rd = parent.getSize();
}if (this.this$0.menuBar != null  && this.this$0.menuBar.isVisible() ) {
mbd = this.this$0.menuBar.getPreferredSize();
} else {
mbd = Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[0, 0]);
}return Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[Math.max(rd.width, mbd.width) + i.left + i.right , rd.height + mbd.height + i.top + i.bottom ]);
});

Clazz.newMeth(C$, 'minimumLayoutSize$java_awt_Container', function (parent) {
var rd;
var mbd;
var i = this.this$0.getInsets();
if (this.this$0.contentPane != null ) {
rd = this.this$0.contentPane.getMinimumSize();
} else {
rd = parent.getSize();
}if (this.this$0.menuBar != null  && this.this$0.menuBar.isVisible() ) {
mbd = this.this$0.menuBar.getMinimumSize();
} else {
mbd = Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[0, 0]);
}return Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[Math.max(rd.width, mbd.width) + i.left + i.right , rd.height + mbd.height + i.top + i.bottom ]);
});

Clazz.newMeth(C$, 'maximumLayoutSize$java_awt_Container', function (target) {
var rd;
var mbd;
var i = this.this$0.getInsets();
if (this.this$0.menuBar != null  && this.this$0.menuBar.isVisible() ) {
mbd = this.this$0.menuBar.getMaximumSize();
} else {
mbd = Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[0, 0]);
}if (this.this$0.contentPane != null ) {
rd = this.this$0.contentPane.getMaximumSize();
} else {
rd = Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[2147483647, 2147483647 - i.top - i.bottom - mbd.height - 1 ]);
}return Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[Math.min(rd.width, mbd.width) + i.left + i.right , rd.height + mbd.height + i.top + i.bottom ]);
});

Clazz.newMeth(C$, 'layoutContainer$java_awt_Container', function (parent) {
var b = parent.getBounds();
var i = this.this$0.getInsets();
var contentY = 0;
var w = b.width - i.right - i.left ;
var h = b.height - i.top - i.bottom ;
if (this.this$0.layeredPane != null ) {
this.this$0.layeredPane.setBounds$I$I$I$I(i.left, i.top, w, h);
}if (this.this$0.glassPane != null ) {
this.this$0.glassPane.setBounds$I$I$I$I(i.left, i.top, w, h);
}if (this.this$0.menuBar != null  && this.this$0.menuBar.isVisible() ) {
var mbd = this.this$0.menuBar.getPreferredSize();
this.this$0.menuBar.setBounds$I$I$I$I(0, 0, w, mbd.height);
contentY = contentY+(mbd.height);
}if (this.this$0.contentPane != null ) {
this.this$0.contentPane.setBounds$I$I$I$I(0, contentY, w, h - contentY);
}});

Clazz.newMeth(C$, 'addLayoutComponent$S$java_awt_Component', function (name, comp) {
});

Clazz.newMeth(C$, 'removeLayoutComponent$java_awt_Component', function (comp) {
});

Clazz.newMeth(C$, 'addLayoutComponent$java_awt_Component$O', function (comp, constraints) {
});

Clazz.newMeth(C$, 'getLayoutAlignmentX$java_awt_Container', function (target) {
return 0.0;
});

Clazz.newMeth(C$, 'getLayoutAlignmentY$java_awt_Container', function (target) {
return 0.0;
});

Clazz.newMeth(C$, 'invalidateLayout$java_awt_Container', function (target) {
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-02-08 11:13:19