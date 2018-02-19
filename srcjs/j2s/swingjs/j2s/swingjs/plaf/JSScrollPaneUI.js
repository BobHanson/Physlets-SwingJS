(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['java.awt.Point','swingjs.api.js.DOMNode',['swingjs.plaf.JSScrollPaneUI','.Actions'],'java.awt.Dimension','javax.swing.LookAndFeel','javax.swing.UIManager','java.lang.Boolean','javax.swing.SwingUtilities','swingjs.plaf.LazyActionMap','sun.swing.DefaultLookup',['swingjs.plaf.JSScrollPaneUI','.Handler'],['java.awt.Component','.BaselineResizeBehavior']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSScrollPaneUI", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'swingjs.plaf.JSLightweightUI', ['java.beans.PropertyChangeListener', 'javax.swing.event.ChangeListener']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.scrolledComponent = null;
this.viewport = null;
this.scrolledUI = null;
this.horizBarUI = null;
this.vertBarUI = null;
this.scrollBarUIDisabled = false;
this.scrollpane = null;
this.vsbChangeListener = null;
this.hsbChangeListener = null;
this.viewportChangeListener = null;
this.spPropertyChangeListener = null;
this.vsbPropertyChangeListener = null;
this.hsbPropertyChangeListener = null;
this.handler = null;
this.setValueCalled = false;
this.haveTextArea = false;
this.textAreaSize = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.scrollBarUIDisabled = false;
this.setValueCalled = false;
this.haveTextArea = false;
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
this.isContainer = true;
if (this.domNode == null ) {
this.domNode = this.newDOMObject$S$S$SA("div", this.id, []);
}return this.domNode;
});

Clazz.newMeth(C$, 'jsSetViewPort', function () {
var hscrollbar = this.scrollpane.getHorizontalScrollBar();
hscrollbar.addChangeListener$javax_swing_event_ChangeListener(this);
hscrollbar.addPropertyChangeListener$java_beans_PropertyChangeListener(this);
var vscrollbar = this.scrollpane.getVerticalScrollBar();
vscrollbar.addChangeListener$javax_swing_event_ChangeListener(this);
vscrollbar.addPropertyChangeListener$java_beans_PropertyChangeListener(this);
this.viewport = this.scrollpane.getViewport();
var sc = this.viewport.getView();
if (sc == null  || sc.ui == null  ) return false;
if (sc !== this.scrolledComponent ) {
this.scrolledComponent = sc;
this.scrolledUI = sc.ui;
this.scrolledUI.scrollPaneUI = this;
this.scrollNode = this.scrolledUI.domNode;
(I$[2]||$incl$(2)).setSize(this.scrollNode, this.c.getWidth(), this.c.getHeight());
}return true;
});

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
if (this.scrolledComponent == null  && !this.jsSetViewPort() ) return;
if (Clazz.instanceOf(e.getSource(), "javax.swing.BoundedRangeModel") && p$.jsModelStateChanged$javax_swing_event_ChangeEvent.apply(this, [e]) ) {
return;
}this.getHandler().stateChanged$javax_swing_event_ChangeEvent(e);
});

Clazz.newMeth(C$, 'getScrollBarPolicyCSS$I', function (policy) {
switch (policy % 10) {
default:
case 0:
return "auto";
case 1:
return "none";
case 2:
return "scroll";
}
});

Clazz.newMeth(C$, 'getPreferredSize', function () {
return null;
});

Clazz.newMeth(C$, 'loadActionMap$swingjs_plaf_LazyActionMap', function (map) {
map.put$javax_swing_Action(Clazz.new_((I$[3]||$incl$(3)).c$$S,["scrollUp"]));
map.put$javax_swing_Action(Clazz.new_((I$[3]||$incl$(3)).c$$S,["scrollDown"]));
map.put$javax_swing_Action(Clazz.new_((I$[3]||$incl$(3)).c$$S,["scrollHome"]));
map.put$javax_swing_Action(Clazz.new_((I$[3]||$incl$(3)).c$$S,["scrollEnd"]));
map.put$javax_swing_Action(Clazz.new_((I$[3]||$incl$(3)).c$$S,["unitScrollUp"]));
map.put$javax_swing_Action(Clazz.new_((I$[3]||$incl$(3)).c$$S,["unitScrollDown"]));
map.put$javax_swing_Action(Clazz.new_((I$[3]||$incl$(3)).c$$S,["scrollLeft"]));
map.put$javax_swing_Action(Clazz.new_((I$[3]||$incl$(3)).c$$S,["scrollRight"]));
map.put$javax_swing_Action(Clazz.new_((I$[3]||$incl$(3)).c$$S,["unitScrollRight"]));
map.put$javax_swing_Action(Clazz.new_((I$[3]||$incl$(3)).c$$S,["unitScrollLeft"]));
}, 1);

Clazz.newMeth(C$, 'paint$java_awt_Graphics$javax_swing_JComponent', function (g, c) {
p$.checkTextAreaHeight.apply(this, []);
var vpBorder = this.scrollpane.getViewportBorder();
if (vpBorder != null ) {
var r = this.scrollpane.getViewportBorderBounds();
vpBorder.paintBorder$java_awt_Component$java_awt_Graphics$I$I$I$I(this.scrollpane, g, r.x, r.y, r.width, r.height);
}});

Clazz.newMeth(C$, 'checkTextAreaHeight', function () {
this.haveTextArea = false;
var vp = this.scrollpane.getViewport();
if (vp == null ) return;
var vsb = this.scrollpane.getVerticalScrollBar();
var hsb = this.scrollpane.getHorizontalScrollBar();
if (vsb != null ) (vsb.getUI()).setScrollPaneCSS();
if (hsb != null ) (hsb.getUI()).setScrollPaneCSS();
var sc = vp.getView();
if (sc == null  || sc.getUI() == null   || sc.getUIClassID() != "TextAreaUI" ) return;
this.haveTextArea = true;
if (this.textAreaSize == null ) this.textAreaSize = Clazz.new_((I$[4]||$incl$(4)));
(sc.getUI()).getTextAreaTextSize$java_awt_Dimension(this.textAreaSize);
var overHeight = this.textAreaSize.height > sc.getBounds().height;
var overWidth = this.textAreaSize.width > sc.getBounds().width;
if (vsb == null  || !overHeight ) this.textAreaSize.height = sc.getHeight();
if (hsb == null  || !overWidth ) this.textAreaSize.width = sc.getWidth();
sc.setSize$java_awt_Dimension(this.textAreaSize);
});

Clazz.newMeth(C$, 'getMaximumSize$javax_swing_JComponent', function (c) {
return Clazz.new_((I$[4]||$incl$(4)).c$$I$I,[32767, 32767]);
});

Clazz.newMeth(C$, 'installDefaults$javax_swing_JScrollPane', function (scrollpane) {
(I$[5]||$incl$(5)).installBorder$javax_swing_JComponent$S(scrollpane, "ScrollPane.border");
(I$[5]||$incl$(5)).installColorsAndFont$javax_swing_JComponent$S$S$S(scrollpane, "ScrollPane.background", "ScrollPane.foreground", "ScrollPane.font");
var vpBorder = scrollpane.getViewportBorder();
if ((vpBorder == null ) || (Clazz.instanceOf(vpBorder, "javax.swing.plaf.UIResource")) ) {
vpBorder = (I$[6]||$incl$(6)).getBorder$O("ScrollPane.viewportBorder");
scrollpane.setViewportBorder$javax_swing_border_Border(vpBorder);
}(I$[5]||$incl$(5)).installProperty$javax_swing_JComponent$S$O(scrollpane, "opaque", (I$[7]||$incl$(7)).TRUE);
});

Clazz.newMeth(C$, 'installListeners$javax_swing_JScrollPane', function (c) {
this.vsbChangeListener = this.createVSBChangeListener();
this.vsbPropertyChangeListener = p$.createVSBPropertyChangeListener.apply(this, []);
this.hsbChangeListener = this.createHSBChangeListener();
this.hsbPropertyChangeListener = p$.createHSBPropertyChangeListener.apply(this, []);
this.viewportChangeListener = this.createViewportChangeListener();
this.spPropertyChangeListener = this.createPropertyChangeListener();
var viewport = this.scrollpane.getViewport();
var vsb = this.scrollpane.getVerticalScrollBar();
var hsb = this.scrollpane.getHorizontalScrollBar();
if (viewport != null ) {
viewport.addChangeListener$javax_swing_event_ChangeListener(this.viewportChangeListener);
viewport.addPropertyChangeListener$java_beans_PropertyChangeListener(this);
}this.horizBarUI = this.vertBarUI = null;
if (vsb != null ) {
vsb.getModel().addChangeListener$javax_swing_event_ChangeListener(this.vsbChangeListener);
vsb.addPropertyChangeListener$java_beans_PropertyChangeListener(this.vsbPropertyChangeListener);
}if (hsb != null ) {
hsb.getModel().addChangeListener$javax_swing_event_ChangeListener(this.hsbChangeListener);
hsb.addPropertyChangeListener$java_beans_PropertyChangeListener(this.hsbPropertyChangeListener);
}this.scrollpane.addPropertyChangeListener$java_beans_PropertyChangeListener(this.spPropertyChangeListener);
});

Clazz.newMeth(C$, 'installKeyboardActions$javax_swing_JScrollPane', function (c) {
var inputMap = this.getInputMap$I(1);
(I$[8]||$incl$(8)).replaceUIInputMap$javax_swing_JComponent$I$javax_swing_InputMap(c, 1, inputMap);
(I$[9]||$incl$(9)).installLazyActionMap$javax_swing_JComponent$Class$S(c, Clazz.getClass(C$), "ScrollPane.actionMap");
});

Clazz.newMeth(C$, 'getInputMap$I', function (condition) {
if (condition == 1) {
var keyMap = (I$[10]||$incl$(10)).get$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this.scrollpane, this, "ScrollPane.ancestorInputMap");
var rtlKeyMap;
if (this.scrollpane.getComponentOrientation().isLeftToRight() || ((rtlKeyMap = (I$[10]||$incl$(10)).get$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this.scrollpane, this, "ScrollPane.ancestorInputMap.RightToLeft")) == null ) ) {
return keyMap;
} else {
rtlKeyMap.setParent$javax_swing_InputMap(keyMap);
return rtlKeyMap;
}}return null;
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.scrollpane = jc;
this.installDefaults$javax_swing_JScrollPane(this.scrollpane);
this.installListeners$javax_swing_JScrollPane(this.scrollpane);
this.installKeyboardActions$javax_swing_JScrollPane(this.scrollpane);
});

Clazz.newMeth(C$, 'uninstallDefaults$javax_swing_JScrollPane', function (c) {
(I$[5]||$incl$(5)).uninstallBorder$javax_swing_JComponent(this.scrollpane);
if (Clazz.instanceOf(this.scrollpane.getViewportBorder(), "javax.swing.plaf.UIResource")) {
this.scrollpane.setViewportBorder$javax_swing_border_Border(null);
}});

Clazz.newMeth(C$, 'uninstallListeners$javax_swing_JComponent', function (c) {
var viewport = this.scrollpane.getViewport();
var vsb = this.scrollpane.getVerticalScrollBar();
var hsb = this.scrollpane.getHorizontalScrollBar();
if (viewport != null ) {
viewport.removeChangeListener$javax_swing_event_ChangeListener(this.viewportChangeListener);
}if (vsb != null ) {
vsb.getModel().removeChangeListener$javax_swing_event_ChangeListener(this.vsbChangeListener);
vsb.removePropertyChangeListener$java_beans_PropertyChangeListener(this.vsbPropertyChangeListener);
}if (hsb != null ) {
hsb.getModel().removeChangeListener$javax_swing_event_ChangeListener(this.hsbChangeListener);
hsb.removePropertyChangeListener$java_beans_PropertyChangeListener(this.hsbPropertyChangeListener);
}this.scrollpane.removePropertyChangeListener$java_beans_PropertyChangeListener(this.spPropertyChangeListener);
this.vsbChangeListener = null;
this.hsbChangeListener = null;
this.viewportChangeListener = null;
this.spPropertyChangeListener = null;
this.handler = null;
});

Clazz.newMeth(C$, 'uninstallKeyboardActions$javax_swing_JScrollPane', function (c) {
(I$[8]||$incl$(8)).replaceUIActionMap$javax_swing_JComponent$javax_swing_ActionMap(c, null);
(I$[8]||$incl$(8)).replaceUIInputMap$javax_swing_JComponent$I$javax_swing_InputMap(c, 1, null);
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
this.uninstallDefaults$javax_swing_JScrollPane(this.scrollpane);
this.uninstallListeners$javax_swing_JComponent(this.scrollpane);
this.uninstallKeyboardActions$javax_swing_JScrollPane(this.scrollpane);
this.scrollpane = null;
});

Clazz.newMeth(C$, 'getHandler', function () {
if (this.handler == null ) {
this.handler = Clazz.new_((I$[11]||$incl$(11)), [this, null]);
}return this.handler;
});

Clazz.newMeth(C$, 'syncScrollPaneWithViewport', function () {
var viewport = this.scrollpane.getViewport();
var vsb = this.scrollpane.getVerticalScrollBar();
var hsb = this.scrollpane.getHorizontalScrollBar();
var rowHead = this.scrollpane.getRowHeader();
var colHead = this.scrollpane.getColumnHeader();
var ltr = this.scrollpane.getComponentOrientation().isLeftToRight();
if (viewport != null ) {
var extentSize = viewport.getExtentSize();
var viewSize = viewport.getViewSize();
var viewPosition = viewport.getViewPosition();
if (vsb != null ) {
var extent = extentSize.height;
var max = viewSize.height;
var value = Math.max(0, Math.min(viewPosition.y, max - extent));
vsb.setValues$I$I$I$I(value, extent, 0, max);
}if (hsb != null ) {
var extent = extentSize.width;
var max = viewSize.width;
var value;
if (ltr) {
value = Math.max(0, Math.min(viewPosition.x, max - extent));
} else {
var currentValue = hsb.getValue();
if (this.setValueCalled && ((max - currentValue) == viewPosition.x) ) {
value = Math.max(0, Math.min(max - extent, currentValue));
if (extent != 0) {
this.setValueCalled = false;
}} else {
if (extent > max) {
viewPosition.x = max - extent;
viewport.setViewPosition$java_awt_Point(viewPosition);
value = 0;
} else {
value = Math.max(0, Math.min(max - extent, max - extent - viewPosition.x ));
}}}hsb.setValues$I$I$I$I(value, extent, 0, max);
}if (rowHead != null ) {
var p = rowHead.getViewPosition();
p.y = viewport.getViewPosition().y;
p.x = 0;
rowHead.setViewPosition$java_awt_Point(p);
}if (colHead != null ) {
var p = colHead.getViewPosition();
if (ltr) {
p.x = viewport.getViewPosition().x;
} else {
p.x = Math.max(0, viewport.getViewPosition().x);
}p.y = 0;
colHead.setViewPosition$java_awt_Point(p);
}}});

Clazz.newMeth(C$, 'getBaseline$javax_swing_JComponent$I$I', function (c, width, height) {
var viewport = this.scrollpane.getViewport();
var spInsets = this.scrollpane.getInsets();
var y = spInsets.top;
height = height - spInsets.top - spInsets.bottom ;
width = width - spInsets.left - spInsets.right ;
var columnHeader = this.scrollpane.getColumnHeader();
if (columnHeader != null  && columnHeader.isVisible() ) {
var header = columnHeader.getView();
if (header != null  && header.isVisible() ) {
var headerPref = header.getPreferredSize();
var baseline = header.getBaseline$I$I(headerPref.width, headerPref.height);
if (baseline >= 0) {
return y + baseline;
}}var columnPref = columnHeader.getPreferredSize();
height = height-(columnPref.height);
y = y+(columnPref.height);
}var view = (viewport == null ) ? null : viewport.getView();
if (view != null  && view.isVisible()  && view.getBaselineResizeBehavior() === (I$[12]||$incl$(12)).CONSTANT_ASCENT  ) {
var viewportBorder = this.scrollpane.getViewportBorder();
if (viewportBorder != null ) {
var vpbInsets = viewportBorder.getBorderInsets$java_awt_Component(this.scrollpane);
y = y+(vpbInsets.top);
height = height - vpbInsets.top - vpbInsets.bottom ;
width = width - vpbInsets.left - vpbInsets.right ;
}if (view.getWidth() > 0 && view.getHeight() > 0 ) {
var min = view.getMinimumSize();
width = Math.max(min.width, view.getWidth());
height = Math.max(min.height, view.getHeight());
}if (width > 0 && height > 0 ) {
var baseline = view.getBaseline$I$I(width, height);
if (baseline > 0) {
return y + baseline;
}}}return -1;
});

Clazz.newMeth(C$, 'getBaselineResizeBehavior$javax_swing_JComponent', function (c) {
C$.superclazz.prototype.getBaselineResizeBehavior$javax_swing_JComponent.apply(this, [c]);
return (I$[12]||$incl$(12)).CONSTANT_ASCENT;
});

Clazz.newMeth(C$, 'createViewportChangeListener', function () {
return this.getHandler();
});

Clazz.newMeth(C$, 'createHSBPropertyChangeListener', function () {
return this.getHandler();
});

Clazz.newMeth(C$, 'createHSBChangeListener', function () {
return this.getHandler();
});

Clazz.newMeth(C$, 'createVSBPropertyChangeListener', function () {
return this.getHandler();
});

Clazz.newMeth(C$, 'createVSBChangeListener', function () {
return this.getHandler();
});

Clazz.newMeth(C$, 'updateScrollBarDisplayPolicy$java_beans_PropertyChangeEvent', function (e) {
this.scrollpane.revalidate();
this.scrollpane.repaint();
});

Clazz.newMeth(C$, 'updateViewport$java_beans_PropertyChangeEvent', function (e) {
var oldViewport = (e.getOldValue());
var newViewport = (e.getNewValue());
if (oldViewport != null ) {
oldViewport.removeChangeListener$javax_swing_event_ChangeListener(this.viewportChangeListener);
}if (newViewport != null ) {
var p = newViewport.getViewPosition();
if (this.scrollpane.getComponentOrientation().isLeftToRight()) {
p.x = Math.max(p.x, 0);
} else {
var max = newViewport.getViewSize().width;
var extent = newViewport.getExtentSize().width;
if (extent > max) {
p.x = max - extent;
} else {
p.x = Math.max(0, Math.min(max - extent, p.x));
}}p.y = Math.max(p.y, 0);
newViewport.setViewPosition$java_awt_Point(p);
newViewport.addChangeListener$javax_swing_event_ChangeListener(this.viewportChangeListener);
}});

Clazz.newMeth(C$, 'updateRowHeader$java_beans_PropertyChangeEvent', function (e) {
var newRowHead = (e.getNewValue());
if (newRowHead != null ) {
var viewport = this.scrollpane.getViewport();
var p = newRowHead.getViewPosition();
p.y = (viewport != null ) ? viewport.getViewPosition().y : 0;
newRowHead.setViewPosition$java_awt_Point(p);
}});

Clazz.newMeth(C$, 'updateColumnHeader$java_beans_PropertyChangeEvent', function (e) {
var newColHead = (e.getNewValue());
if (newColHead != null ) {
var viewport = this.scrollpane.getViewport();
var p = newColHead.getViewPosition();
if (viewport == null ) {
p.x = 0;
} else {
if (this.scrollpane.getComponentOrientation().isLeftToRight()) {
p.x = viewport.getViewPosition().x;
} else {
p.x = Math.max(0, viewport.getViewPosition().x);
}}newColHead.setViewPosition$java_awt_Point(p);
this.scrollpane.add$java_awt_Component$O(newColHead, "COLUMN_HEADER");
}});

Clazz.newMeth(C$, 'updateHorizontalScrollBar$java_beans_PropertyChangeEvent', function (pce) {
p$.updateScrollBar$java_beans_PropertyChangeEvent$javax_swing_event_ChangeListener$java_beans_PropertyChangeListener$Z.apply(this, [pce, this.hsbChangeListener, this.hsbPropertyChangeListener, false]);
});

Clazz.newMeth(C$, 'updateVerticalScrollBar$java_beans_PropertyChangeEvent', function (pce) {
p$.updateScrollBar$java_beans_PropertyChangeEvent$javax_swing_event_ChangeListener$java_beans_PropertyChangeListener$Z.apply(this, [pce, this.vsbChangeListener, this.vsbPropertyChangeListener, true]);
});

Clazz.newMeth(C$, 'updateScrollBar$java_beans_PropertyChangeEvent$javax_swing_event_ChangeListener$java_beans_PropertyChangeListener$Z', function (pce, cl, pcl, isVertical) {
var sb = pce.getOldValue();
if (sb != null ) {
if (cl != null ) {
sb.getModel().removeChangeListener$javax_swing_event_ChangeListener(cl);
}if (pcl != null ) {
sb.removePropertyChangeListener$java_beans_PropertyChangeListener(pcl);
}}sb = pce.getNewValue();
if (sb != null ) {
if (isVertical) {
this.vertBarUI = sb.getUI();
this.vertBarUI.isScrollPaneScrollBar = true;
this.vertBarUI.myScrollPaneUI = this;
} else {
this.horizBarUI = sb.getUI();
this.horizBarUI.isScrollPaneScrollBar = true;
this.horizBarUI.myScrollPaneUI = this;
}if (cl != null ) {
sb.getModel().addChangeListener$javax_swing_event_ChangeListener(cl);
}if (pcl != null ) {
sb.addPropertyChangeListener$java_beans_PropertyChangeListener(pcl);
}}});

Clazz.newMeth(C$, 'createPropertyChangeListener', function () {
return this.getHandler();
});

Clazz.newMeth(C$, 'jsModelStateChanged$javax_swing_event_ChangeEvent', function (e) {
if (this.scrollBarUIDisabled) {
p$.jsScrollComponentUsingCSS$Z.apply(this, [e.getSource() === this.viewport ]);
return true;
}return false;
});

Clazz.newMeth(C$, 'jsScrollComponentUsingCSS$Z', function (isViewportChange) {
var r1 = this.viewport.getBounds();
var r2 = this.scrolledComponent.getBounds();
if (!r1.equals$O(r2) && !isViewportChange ) this.scrolledComponent.setBounds$java_awt_Rectangle(r1);
(I$[2]||$incl$(2)).setStyles(this.scrolledUI.domNode, ["overflow-x", p$.getScrollBarPolicyCSS$I.apply(this, [this.scrollpane.getHorizontalScrollBarPolicy()]), "overflow-y", p$.getScrollBarPolicyCSS$I.apply(this, [this.scrollpane.getVerticalScrollBarPolicy()])]);
if (this.horizBarUI != null ) (I$[2]||$incl$(2)).setVisible(this.horizBarUI.jqSlider, false);
if (this.vertBarUI != null ) (I$[2]||$incl$(2)).setVisible(this.vertBarUI.jqSlider, false);
});
;
(function(){var C$=Clazz.newClass(P$.JSScrollPaneUI, "ViewportChangeHandler", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'javax.swing.event.ChangeListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
this.this$0.getHandler().stateChanged$javax_swing_event_ChangeEvent(e);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JSScrollPaneUI, "HSBChangeListener", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'javax.swing.event.ChangeListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
this.this$0.getHandler().stateChanged$javax_swing_event_ChangeEvent(e);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JSScrollPaneUI, "VSBChangeListener", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'javax.swing.event.ChangeListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
this.this$0.getHandler().stateChanged$javax_swing_event_ChangeEvent(e);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JSScrollPaneUI, "PropertyChangeHandler", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.beans.PropertyChangeListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (e) {
this.this$0.getHandler().propertyChange$java_beans_PropertyChangeEvent(e);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JSScrollPaneUI, "Actions", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'sun.swing.UIAction');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (key) {
C$.superclazz.c$$S.apply(this, [key]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var scrollPane = e.getSource();
var ltr = scrollPane.getComponentOrientation().isLeftToRight();
var key = this.getName();
if (key == "scrollUp") {
p$.scroll$javax_swing_JScrollPane$I$I$Z.apply(this, [scrollPane, 1, -1, true]);
} else if (key == "scrollDown") {
p$.scroll$javax_swing_JScrollPane$I$I$Z.apply(this, [scrollPane, 1, 1, true]);
} else if (key == "scrollHome") {
p$.scrollHome$javax_swing_JScrollPane.apply(this, [scrollPane]);
} else if (key == "scrollEnd") {
p$.scrollEnd$javax_swing_JScrollPane.apply(this, [scrollPane]);
} else if (key == "unitScrollUp") {
p$.scroll$javax_swing_JScrollPane$I$I$Z.apply(this, [scrollPane, 1, -1, false]);
} else if (key == "unitScrollDown") {
p$.scroll$javax_swing_JScrollPane$I$I$Z.apply(this, [scrollPane, 1, 1, false]);
} else if (key == "scrollLeft") {
p$.scroll$javax_swing_JScrollPane$I$I$Z.apply(this, [scrollPane, 0, ltr ? -1 : 1, true]);
} else if (key == "scrollRight") {
p$.scroll$javax_swing_JScrollPane$I$I$Z.apply(this, [scrollPane, 0, ltr ? 1 : -1, true]);
} else if (key == "unitScrollLeft") {
p$.scroll$javax_swing_JScrollPane$I$I$Z.apply(this, [scrollPane, 0, ltr ? -1 : 1, false]);
} else if (key == "unitScrollRight") {
p$.scroll$javax_swing_JScrollPane$I$I$Z.apply(this, [scrollPane, 0, ltr ? 1 : -1, false]);
}});

Clazz.newMeth(C$, 'scrollEnd$javax_swing_JScrollPane', function (scrollpane) {
var vp = scrollpane.getViewport();
var view;
if (vp != null  && (view = vp.getView()) != null  ) {
var visRect = vp.getViewRect();
var bounds = view.getBounds();
if (scrollpane.getComponentOrientation().isLeftToRight()) {
vp.setViewPosition$java_awt_Point(Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[bounds.width - visRect.width, bounds.height - visRect.height]));
} else {
vp.setViewPosition$java_awt_Point(Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[0, bounds.height - visRect.height]));
}}});

Clazz.newMeth(C$, 'scrollHome$javax_swing_JScrollPane', function (scrollpane) {
var vp = scrollpane.getViewport();
var view;
if (vp != null  && (view = vp.getView()) != null  ) {
if (scrollpane.getComponentOrientation().isLeftToRight()) {
vp.setViewPosition$java_awt_Point(Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[0, 0]));
} else {
var visRect = vp.getViewRect();
var bounds = view.getBounds();
vp.setViewPosition$java_awt_Point(Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[bounds.width - visRect.width, 0]));
}}});

Clazz.newMeth(C$, 'scroll$javax_swing_JScrollPane$I$I$Z', function (scrollpane, orientation, direction, block) {
var vp = scrollpane.getViewport();
var view;
if (vp != null  && (view = vp.getView()) != null  ) {
var visRect = vp.getViewRect();
var vSize = view.getSize();
var amount;
if (Clazz.instanceOf(view, "javax.swing.Scrollable")) {
if (block) {
amount = (view).getScrollableBlockIncrement$java_awt_Rectangle$I$I(visRect, orientation, direction);
} else {
amount = (view).getScrollableUnitIncrement$java_awt_Rectangle$I$I(visRect, orientation, direction);
}} else {
if (block) {
if (orientation == 1) {
amount = visRect.height;
} else {
amount = visRect.width;
}} else {
amount = 10;
}}if (orientation == 1) {
visRect.y = visRect.y+((amount * direction));
if ((visRect.y + visRect.height) > vSize.height) {
visRect.y = Math.max(0, vSize.height - visRect.height);
} else if (visRect.y < 0) {
visRect.y = 0;
}} else {
if (scrollpane.getComponentOrientation().isLeftToRight()) {
visRect.x = visRect.x+((amount * direction));
if ((visRect.x + visRect.width) > vSize.width) {
visRect.x = Math.max(0, vSize.width - visRect.width);
} else if (visRect.x < 0) {
visRect.x = 0;
}} else {
visRect.x = visRect.x-((amount * direction));
if (visRect.width > vSize.width) {
visRect.x = vSize.width - visRect.width;
} else {
visRect.x = Math.max(0, Math.min(vSize.width - visRect.width, visRect.x));
}}}vp.setViewPosition$java_awt_Point(visRect.getLocation());
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JSScrollPaneUI, "Handler", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, ['javax.swing.event.ChangeListener', 'java.beans.PropertyChangeListener']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
var viewport = this.this$0.scrollpane.getViewport();
if (viewport != null ) {
if (e.getSource() === viewport ) {
p$.viewportStateChanged$javax_swing_event_ChangeEvent.apply(this, [e]);
} else {
var hsb = this.this$0.scrollpane.getHorizontalScrollBar();
if (hsb != null  && e.getSource() === hsb.getModel()  ) {
p$.hsbStateChanged$javax_swing_JViewport$javax_swing_event_ChangeEvent.apply(this, [viewport, e]);
} else {
var vsb = this.this$0.scrollpane.getVerticalScrollBar();
if (vsb != null  && e.getSource() === vsb.getModel()  ) {
p$.vsbStateChanged$javax_swing_JViewport$javax_swing_event_ChangeEvent.apply(this, [viewport, e]);
}}}}});

Clazz.newMeth(C$, 'vsbStateChanged$javax_swing_JViewport$javax_swing_event_ChangeEvent', function (viewport, e) {
var model = (e.getSource());
var p = viewport.getViewPosition();
p.y = model.getValue();
viewport.setViewPosition$java_awt_Point(p);
});

Clazz.newMeth(C$, 'hsbStateChanged$javax_swing_JViewport$javax_swing_event_ChangeEvent', function (viewport, e) {
var model = (e.getSource());
var p = viewport.getViewPosition();
var value = model.getValue();
if (this.this$0.scrollpane.getComponentOrientation().isLeftToRight()) {
p.x = value;
} else {
var max = viewport.getViewSize().width;
var extent = viewport.getExtentSize().width;
var oldX = p.x;
p.x = max - extent - value ;
if ((extent == 0) && (value != 0) && (oldX == max)  ) {
this.this$0.setValueCalled = true;
} else {
if ((extent != 0) && (oldX < 0) && (p.x == 0)  ) {
p.x = p.x+(value);
}}}viewport.setViewPosition$java_awt_Point(p);
});

Clazz.newMeth(C$, 'viewportStateChanged$javax_swing_event_ChangeEvent', function (e) {
this.this$0.syncScrollPaneWithViewport();
});

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (e) {
if (e.getSource() === this.this$0.scrollpane ) {
p$.scrollPanePropertyChange$java_beans_PropertyChangeEvent.apply(this, [e]);
} else {
p$.sbPropertyChange$java_beans_PropertyChangeEvent.apply(this, [e]);
}});

Clazz.newMeth(C$, 'scrollPanePropertyChange$java_beans_PropertyChangeEvent', function (e) {
var propertyName = e.getPropertyName();
if (propertyName == "verticalScrollBarDisplayPolicy") {
this.this$0.updateScrollBarDisplayPolicy$java_beans_PropertyChangeEvent(e);
} else if (propertyName == "horizontalScrollBarDisplayPolicy") {
this.this$0.updateScrollBarDisplayPolicy$java_beans_PropertyChangeEvent(e);
} else if (propertyName == "viewport") {
this.this$0.updateViewport$java_beans_PropertyChangeEvent(e);
} else if (propertyName == "rowHeader") {
this.this$0.updateRowHeader$java_beans_PropertyChangeEvent(e);
} else if (propertyName == "columnHeader") {
this.this$0.updateColumnHeader$java_beans_PropertyChangeEvent(e);
} else if (propertyName == "verticalScrollBar") {
this.this$0.updateVerticalScrollBar$java_beans_PropertyChangeEvent(e);
} else if (propertyName == "horizontalScrollBar") {
this.this$0.updateHorizontalScrollBar$java_beans_PropertyChangeEvent(e);
} else if (propertyName == "componentOrientation") {
this.this$0.scrollpane.revalidate();
this.this$0.scrollpane.repaint();
}});

Clazz.newMeth(C$, 'sbPropertyChange$java_beans_PropertyChangeEvent', function (e) {
var propertyName = e.getPropertyName();
var source = e.getSource();
if ("model" == propertyName) {
var sb = this.this$0.scrollpane.getVerticalScrollBar();
var oldModel = e.getOldValue();
var cl = null;
if (source === sb ) {
cl = this.this$0.vsbChangeListener;
} else if (source === this.this$0.scrollpane.getHorizontalScrollBar() ) {
sb = this.this$0.scrollpane.getHorizontalScrollBar();
cl = this.this$0.hsbChangeListener;
}if (cl != null ) {
if (oldModel != null ) {
oldModel.removeChangeListener$javax_swing_event_ChangeListener(cl);
}if (sb.getModel() != null ) {
sb.getModel().addChangeListener$javax_swing_event_ChangeListener(cl);
}}} else if ("componentOrientation" == propertyName) {
if (source === this.this$0.scrollpane.getHorizontalScrollBar() ) {
var hsb = this.this$0.scrollpane.getHorizontalScrollBar();
var viewport = this.this$0.scrollpane.getViewport();
var p = viewport.getViewPosition();
if (this.this$0.scrollpane.getComponentOrientation().isLeftToRight()) {
p.x = hsb.getValue();
} else {
p.x = viewport.getViewSize().width - viewport.getExtentSize().width - hsb.getValue() ;
}viewport.setViewPosition$java_awt_Point(p);
}}});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:27
