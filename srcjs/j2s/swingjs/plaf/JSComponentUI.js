(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['java.awt.Color','swingjs.JSUtil','swingjs.api.js.DOMNode','swingjs.JSToolkit','javajs.util.PT','java.awt.Dimension',['java.awt.Component','.BaselineResizeBehavior'],'javax.swing.UIManager','java.awt.Point','java.awt.Toolkit','java.awt.event.FocusEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSComponentUI", null, 'javax.swing.plaf.ComponentUI', ['java.awt.peer.ContainerPeer', 'swingjs.plaf.JSEventHandler', 'java.beans.PropertyChangeListener', 'javax.swing.event.ChangeListener']);
C$.rootPaneColor = null;
C$.incr = 0;
C$.borderTest = false;
C$.debugging = false;
C$.frameZ = 0;
C$.focusedUI = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.rootPaneColor = Clazz.new_((I$[1]||$incl$(1)).c$$I$I$I,[238, 238, 238]);
C$.frameZ = 19000;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.id = null;
this.c = null;
this.jc = null;
this.outerNode = null;
this.innerNode = null;
this.domNode = null;
this.centeringNode = null;
this.imageNode = null;
this.draggable = false;
this.domBtn = null;
this.$enableNode = null;
this.enableNodes = null;
this.iconNode = null;
this.textNode = null;
this.textAlign = null;
this.valueNode = null;
this.scrollNode = null;
this.iconHeight = 0;
this.focusNode = null;
this.children = null;
this.num = 0;
this.isToolbarFixed = false;
this.isTainted = false;
this.boundsSet = false;
this.isMenuItem = false;
this.isMenu = false;
this.x = 0;
this.y = 0;
this.preferredSize = null;
this.isContainer = false;
this.isWindow = false;
this.isRootPane = false;
this.isContentPane = false;
this.parent = null;
this.currentText = null;
this.currentIcon = null;
this.currentGap = 0;
this.scrollPaneUI = null;
this.classID = null;
this.body = null;
this.document = null;
this.applet = null;
this.needPreferred = false;
this.width = 0;
this.height = 0;
this.containerNode = null;
this.isNull = false;
this.waitImage = null;
this.canAlignText = false;
this.canAlignIcon = false;
this.createMsgs = null;
this.layingOut = false;
this.isDisposed = false;
this.actualHeight = 0;
this.actualWidth = 0;
this.allowPaintedBackground = false;
this.colorUNKNOWN = null;
this.inactiveForeground = null;
this.inactiveBackground = null;
this.enabled = false;
this.jsActualWidth = 0;
this.jsActualHeight = 0;
this.dropTarget = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.isToolbarFixed = true;
this.isTainted = true;
this.boundsSet = false;
this.isMenuItem = false;
this.isMenu = false;
this.currentGap = 2147483647;
this.createMsgs = "";
this.allowPaintedBackground = true;
this.colorUNKNOWN = Clazz.new_((I$[1]||$incl$(1)));
this.inactiveForeground = this.colorUNKNOWN;
this.inactiveBackground = this.colorUNKNOWN;
this.enabled = true;
this.dropTarget = this;
}, 1);

Clazz.newMeth(C$, 'setDraggable$javajs_api_JSFunction', function (f) {
this.draggable = true;
(I$[2]||$incl$(2)).J2S._setDraggable(this.updateDOMNode(), f);
});

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.setDoc();
}, 1);

Clazz.newMeth(C$, 'setDoc', function () {
{
this.document = document; this.body = document.body;
}
C$.debugging = (I$[2]||$incl$(2)).debugging;
});

Clazz.newMeth(C$, 'installJS', function () {
{
this.c.addChangeListener$javax_swing_event_ChangeListener && this.c.addChangeListener$javax_swing_event_ChangeListener(this);
}
this.c.addPropertyChangeListener$java_beans_PropertyChangeListener(this);
});

Clazz.newMeth(C$, 'uninstallJS', function () {
{
this.c && this.c.removeChangeListener$javax_swing_event_ChangeListener && this.c.removeChangeListener$javax_swing_event_ChangeListener(this);
this.c && this.c.removePropertyChangeListener$java_beans_PropertyChangeListener(this);
}
if (this.outerNode != null ) {
(I$[3]||$incl$(3)).remove(this.outerNode);
this.outerNode = null;
}});

Clazz.newMeth(C$, '$$O', function (node) {
return (I$[2]||$incl$(2)).getJQuery().$(node);
});

Clazz.newMeth(C$, 'set$javax_swing_JComponent', function (target) {
this.c = target;
this.jc = this.c;
this.applet = (I$[4]||$incl$(4)).getHTML5Applet$java_awt_Component(this.c);
this.newID$Z(false);
this.installUI$javax_swing_JComponent(target);
this.installJS();
if (this.needPreferred) p$.getHTMLSize.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'newID$Z', function (forceNew) {
this.classID = this.c.getUIClassID();
if (this.id == null  || forceNew ) {
this.num = ++C$.incr;
this.id = this.c.getHTMLName$S(this.classID) + "_" + this.num ;
}});

Clazz.newMeth(C$, 'reInit', function () {
this.setTainted();
this.domNode = null;
this.newID$Z(true);
});

Clazz.newMeth(C$, 'setDataComponent$swingjs_api_js_DOMNode', function (button) {
(I$[3]||$incl$(3)).setAttr(button, "data-component", this.c);
});

Clazz.newMeth(C$, 'ignoreAllMouseEvents$swingjs_api_js_DOMNode', function (node) {
this.$$O(node).addClass("swingjs-ui");
});

Clazz.newMeth(C$, 'setDataUI$swingjs_api_js_DOMNode', function (node) {
(I$[3]||$incl$(3)).setAttr(node, "data-ui", this);
});

Clazz.newMeth(C$, 'handleJSEvent$O$I$O', function (target, eventType, jQueryEvent) {
return true;
});

Clazz.newMeth(C$, 'setJ2sMouseHandler$swingjs_api_js_DOMNode', function (frameNode) {
(I$[3]||$incl$(3)).setAttrs(frameNode, ["applet", this.applet, "_frameViewer", this.jc.getFrameViewer()]);
(I$[2]||$incl$(2)).J2S._jsSetMouse(frameNode, true);
});

Clazz.newMeth(C$, 'getFocusedUI', function () {
return C$.focusedUI;
});

Clazz.newMeth(C$, 'addJQueryFocusCallbacks', function () {
var node = this.$$O(this.focusNode);
var me = this;
{
node.focus(function() {me.notifyFocus$Z(true)});
node.blur(function() {me.notifyFocus$Z(false)});
}
});

Clazz.newMeth(C$, 'bindJSEvents$swingjs_api_js_DOMNode$S$I$Z', function (node, eventList, eventID, andSetCSS) {
var f = null;
var me = this;
if (andSetCSS) {
this.setDataUI$swingjs_api_js_DOMNode(node);
this.ignoreAllMouseEvents$swingjs_api_js_DOMNode(node);
}{
f = function(event) { me.handleJSEvent$O$I$O(node, eventID, event) }
}
this.$$O(node).bind(eventList, f);
});

Clazz.newMeth(C$, 'bindJSKeyEvents$swingjs_api_js_DOMNode$Z', function (node, andFocusOut) {
this.setDataUI$swingjs_api_js_DOMNode(node);
this.bindJSEvents$swingjs_api_js_DOMNode$S$I$Z(node, "keydown keypress keyup" + (andFocusOut ? " focusout" : ""), 401, false);
if (andFocusOut) this.addJQueryFocusCallbacks();
});

Clazz.newMeth(C$, 'revalidate', function () {
this.jc.revalidate();
});

Clazz.newMeth(C$, 'setTainted', function () {
this.isTainted = true;
});

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
if (C$.debugging) System.out.println$S(this.id + " stateChange " + this.dumpEvent$java_util_EventObject(e) );
});

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (e) {
var prop = e.getPropertyName();
if (this.isDisposed && this.c.visible && prop == "ancestor"   && e.getNewValue() != null  ) this.setVisible$Z(true);
this.propertyChangedCUI$S(prop);
});

Clazz.newMeth(C$, 'propertyChangedFromListener$S', function (prop) {
this.propertyChangedCUI$S(prop);
});

Clazz.newMeth(C$, 'propertyChangedCUI$S', function (prop) {
if (!this.isMenu) this.updateDOMNode();
if (prop == "preferredSize") {
this.preferredSize = this.c.getPreferredSize();
this.getPreferredSize();
return;
}if (prop == "background") {
this.setBackground$java_awt_Color(this.c.getBackground());
return;
}if (prop == "foreground") {
this.setForeground$java_awt_Color(this.c.getForeground());
return;
}if (prop == "opaque") {
this.setBackground$java_awt_Color(this.c.getBackground());
return;
}if (prop == "inverted") {
this.updateDOMNode();
return;
}if (prop == "text") {
var val = (this.c).getText();
if (val == null  ? this.currentText != null  : !val.equals$O(this.currentText)) this.setIconAndText$S$javax_swing_ImageIcon$I$S(prop, this.currentIcon, this.currentGap, val);
return;
}if (prop == "iconTextGap") {
if (this.iconNode != null ) {
var gap = (this.c).getIconTextGap();
if (this.currentGap != gap) this.setIconAndText$S$javax_swing_ImageIcon$I$S(prop, this.currentIcon, gap, this.currentText);
}return;
}if (prop == "icon") {
if (this.iconNode != null ) {
var icon = (this.c).getIcon();
if (icon == null  ? this.currentIcon != null  : !icon.equals$O(this.currentIcon)) this.setIconAndText$S$javax_swing_ImageIcon$I$S(prop, icon, this.currentGap, this.currentText);
}return;
}if (prop == "horizontalAlignment" || prop == "verticalAlignment" ) {
p$.setAlignment.apply(this, []);
return;
}if (C$.debugging) System.out.println$S("JSComponentUI: unrecognized prop: " + this.id + " " + prop );
});

Clazz.newMeth(C$, 'setIconAndText$S$javax_swing_ImageIcon$I$S', function (prop, icon, gap, text) {
this.actualWidth = this.actualHeight = 0;
this.currentIcon = icon;
this.currentText = text;
this.currentGap = gap;
this.canAlignText = false;
this.canAlignIcon = false;
this.imageNode = null;
if (this.iconNode != null ) {
(I$[3]||$incl$(3)).setAttr(this.iconNode, "innerHTML", "");
if (icon != null ) {
this.imageNode = (I$[3]||$incl$(3)).getImageNode(icon.getImage());
(I$[3]||$incl$(3)).setStyles(this.imageNode, ["vertical-align", "middle"]);
this.iconNode.appendChild(this.imageNode);
this.iconHeight = icon.getIconHeight();
}}if (text == null  || text.length$() == 0 ) {
text = "";
if (icon != null ) this.canAlignIcon = true;
} else {
if (icon == null ) {
this.canAlignText = true;
} else {
if (gap == 2147483647) gap = this.getDefaultIconTextGap();
if (gap != 0 && text != null  ) (I$[3]||$incl$(3)).addHorizontalGap(this.iconNode, gap);
}if (text.indexOf("<html>") == 0) {
text = (I$[5]||$incl$(5)).rep$S$S$S(text.substring(6, text.length$() - 7), "</br>", "");
}}var obj = null;
if (this.textNode != null ) {
prop = "innerHTML";
obj = this.textNode;
} else if (this.valueNode != null ) {
prop = "value";
obj = this.valueNode;
if (this.iconNode != null ) (I$[3]||$incl$(3)).setVisible(obj, text != null );
}if (obj != null ) this.setProp$swingjs_api_js_DOMNode$S$S(obj, prop, text);
if (this.centeringNode == null ) {
p$.setBackgroundFor$swingjs_api_js_DOMNode$java_awt_Color.apply(this, [this.valueNode, this.c.getBackground()]);
} else {
this.setCssFont$swingjs_api_js_DOMNode$java_awt_Font(this.centeringNode, this.c.getFont());
}if (!this.boundsSet) this.setHTMLSize$swingjs_api_js_DOMNode$Z(this.domNode, true);
if (this.centeringNode != null ) p$.setAlignment.apply(this, []);
if (C$.debugging) System.out.println$S("JSComponentUI: setting " + this.id + " " + prop );
});

Clazz.newMeth(C$, 'getDefaultIconTextGap', function () {
return 0;
});

Clazz.newMeth(C$, 'setAllowPaintedBackground$Z', function (TF) {
this.allowPaintedBackground = TF;
});

Clazz.newMeth(C$, 'getDOMNode', function () {
return this.updateDOMNode();
});

Clazz.newMeth(C$, 'updateDOMNode', function () {
var msg = "Swingjs WARNING: default JSComponentUI is being used for " + this.getClass().getName();
if (C$.debugging && this.createMsgs.indexOf(msg) < 0 ) {
this.createMsgs += msg;
(I$[2]||$incl$(2)).alert$O(msg);
}System.out.println$S(msg);
return (this.domNode == null  ? this.domNode = (I$[3]||$incl$(3)).createElement("div", this.id) : this.domNode);
});

Clazz.newMeth(C$, 'setCssFont$swingjs_api_js_DOMNode$java_awt_Font', function (obj, font) {
if (font != null ) {
var istyle = font.getStyle();
var name = font.getFamily();
if (name == "Dialog" || name == "SansSerif" ) name = "Arial";
(I$[3]||$incl$(3)).setStyles(obj, ["font-family", name, "font-size", font.getSize() + "px", "font-style", ((istyle & 2) == 0 ? "normal" : "italic"), "font-weight", ((istyle & 1) == 0 ? "normal" : "bold")]);
}this.enabled = !this.c.isEnabled();
this.setEnabled$Z(this.c.isEnabled());
return obj;
});

Clazz.newMeth(C$, 'newDOMObject$S$S$SA', function (key, id, attr) {
var obj = (I$[3]||$incl$(3)).createElement(key, id);
for (var i = 0; i < attr.length; ) (I$[3]||$incl$(3)).setAttr(obj, attr[i++], attr[i++]);

if (!this.c.isEnabled()) this.setEnabled$Z(false);
return obj;
});

Clazz.newMeth(C$, 'wrap$S$S$swingjs_api_js_DOMNodeA', function (type, id, elements) {
var obj = this.newDOMObject$S$S$SA(type, id + type, []);
for (var i = 0; i < elements.length; i++) {
obj.appendChild(elements[i]);
}
return obj;
});

Clazz.newMeth(C$, 'debugDump$swingjs_api_js_DOMNode', function (d) {
System.out.println$O((I$[3]||$incl$(3)).getAttr(d, "outerHTML"));
});

Clazz.newMeth(C$, 'vCenter$swingjs_api_js_DOMNode$I', function (obj, offset) {
(I$[3]||$incl$(3)).setStyles(obj, ["top", "50%", "transform", "translateY(" + offset + "%)" ]);
}, 1);

Clazz.newMeth(C$, 'setHTMLSize$swingjs_api_js_DOMNode$Z', function (obj, addCSS) {
return this.setHTMLSize1$swingjs_api_js_DOMNode$Z$Z(obj, addCSS, true);
});

Clazz.newMeth(C$, 'setHTMLSize1$swingjs_api_js_DOMNode$Z$Z', function (node, addCSS, usePreferred) {
if (node == null ) return null;
addCSS = (addCSS&!this.isMenuItem);
var h;
var w;
var w0 = null;
var h0 = null;
var w0i = null;
var h0i = null;
var position = null;
var parentNode = null;
if (this.centeringNode != null  && node === this.domNode  ) node = this.centeringNode;
if (this.scrollPaneUI != null ) {
w = this.scrollPaneUI.c.getWidth();
h = this.scrollPaneUI.c.getHeight();
} else if (usePreferred && this.preferredSize != null  ) {
w = this.preferredSize.width;
h = this.preferredSize.height;
} else {
parentNode = (I$[3]||$incl$(3)).remove(node);
if (!this.isMenuItem) {
w0 = node.style.width; h0 = node.style.height; position = node.style.position;
if (node == this.centeringNode && this.innerNode) { w0i = this.innerNode.style.width; h0i = this.innerNode.style.height; }
}
(I$[3]||$incl$(3)).setStyles(node, ["position", null, "width", null, "height", null]);
if (this.innerNode != null ) (I$[3]||$incl$(3)).setStyles(this.innerNode, ["width", null, "height", null]);
var div;
if ((I$[3]||$incl$(3)).getAttr(node, "tagName") === "DIV" ) div = node;
 else div = this.wrap$S$S$swingjs_api_js_DOMNodeA("div", this.id + "_temp", [node]);
(I$[3]||$incl$(3)).setPositionAbsolute(div, -2147483648, 0);
this.$$O(this.body).after(div);
var r = div.getBoundingClientRect();
w = (Math.max(0, Math.ceil(r.width))|0);
h = (Math.max(0, Math.ceil(r.height))|0);
if (!usePreferred) {
this.actualWidth = w;
this.actualHeight = h;
}this.$$O(div).detach();
}var size = this.getCSSAdjustment$Z(addCSS);
size.width = size.width+(w);
size.height = size.height+(h);
if (addCSS) {
(I$[3]||$incl$(3)).setPositionAbsolute(node, -2147483648, 0);
(I$[3]||$incl$(3)).setSize(node, size.width, size.height);
if (node === this.centeringNode ) {
(I$[3]||$incl$(3)).setPositionAbsolute(parentNode, -2147483648, 0);
(I$[3]||$incl$(3)).setSize(parentNode, size.width, size.height);
}} else {
(I$[3]||$incl$(3)).setStyles(node, ["position", null]);
if (w0 != null ) {
(I$[3]||$incl$(3)).setStyles(node, ["width", w0, "height", h0, "position", position]);
}}if (w0i != null ) {
(I$[3]||$incl$(3)).setStyles(this.innerNode, ["width", w0i, "height", h0i]);
}if (parentNode != null ) parentNode.appendChild(node);
return size;
});

Clazz.newMeth(C$, 'getCSSAdjustment$Z', function (addingCSS) {
return Clazz.new_((I$[6]||$incl$(6)).c$$I$I,[0, 0]);
});

Clazz.newMeth(C$, 'setHTMLElement', function () {
return this.setHTMLElementCUI();
});

Clazz.newMeth(C$, 'setHTMLElementCUI', function () {
if (!this.isTainted) return this.outerNode;
this.domNode = this.updateDOMNode();
p$.checkTransparent$swingjs_api_js_DOMNode.apply(this, [this.domNode]);
var children = this.getChildren();
var n = children.length;
if (this.isMenuItem) {
this.outerNode = this.domNode;
if (n == 0) return this.outerNode;
}if (this.outerNode == null ) this.outerNode = this.wrap$S$S$swingjs_api_js_DOMNodeA("div", this.id, [this.domNode]);
{
this.outerNode.setAttribute("name", this.jc.__CLASS_NAME__);
}
p$.setOuterLocationFromComponent.apply(this, []);
if (n > 0 && this.containerNode == null  ) this.containerNode = this.outerNode;
if (this.isContainer || n > 0 ) {
if (this.isContainer && !this.isMenuItem ) {
var w = this.getContainerWidth();
var h = this.getContainerHeight();
(I$[3]||$incl$(3)).setSize(this.outerNode, w, h);
if (this.isContentPane) (I$[3]||$incl$(3)).setStyles(this.outerNode, ["overflow", "hidden"]);
}if (this.isRootPane) {
if (this.jc.getFrameViewer().isApplet) {
(I$[4]||$incl$(4)).getHTML5Applet$java_awt_Component(this.jc)._getContentLayer().appendChild(this.outerNode);
}(I$[3]||$incl$(3)).setStyles(this.outerNode, ["overflow", "hidden"]);
}this.addChildrenToDOM$java_awt_ComponentA(children);
if (this.isWindow) {
(I$[3]||$incl$(3)).remove(this.outerNode);
this.$$O(this.body).append(this.outerNode);
}}this.isTainted = false;
if (this.jc.getDropTarget() != null ) p$.setDropTarget.apply(this, []);
return this.outerNode;
});

Clazz.newMeth(C$, 'setOuterLocationFromComponent', function () {
if (this.outerNode != null  && !this.isMenuItem ) {
if (this.parent == null  && this.jc.getParent() != null   && (this.parent = this.jc.getParent().getUI()) != null   && this.parent.outerNode != null  ) this.parent.outerNode.appendChild(this.outerNode);
(I$[3]||$incl$(3)).setPositionAbsolute(this.outerNode, -2147483648, 0);
(I$[3]||$incl$(3)).setStyles(this.outerNode, ["left", (this.x = this.c.getX()) + "px", "top", (this.y = this.c.getY()) + "px"]);
}});

Clazz.newMeth(C$, 'getChildren', function () {
return (this.children == null  ? this.jc.getComponents() : this.children);
});

Clazz.newMeth(C$, 'addChildrenToDOM$java_awt_ComponentA', function (children) {
var n = children.length;
for (var i = 0; i < n; i++) {
var ui = (I$[4]||$incl$(4)).getUI$java_awt_Component$Z(children[i], false);
if (ui == null  || ui.isNull ) {
continue;
}ui.parent = this;
if (ui.getOuterNode() == null ) {
System.out.println$S("JSCUI could not add " + ui.c.getName() + " to " + this.c.getName() );
} else {
if (ui.domNode !== ui.outerNode  && (I$[3]||$incl$(3)).getParent(ui.domNode) == null  ) ui.outerNode.appendChild(ui.domNode);
this.containerNode.appendChild(ui.outerNode);
}}
});

Clazz.newMeth(C$, 'getContainerWidth', function () {
return this.width = this.c.getWidth();
});

Clazz.newMeth(C$, 'getContainerHeight', function () {
return this.height = this.c.getHeight();
});

Clazz.newMeth(C$, 'getPreferredSize', function () {
var d = p$.getHTMLSize.apply(this, []);
if (C$.debugging) System.out.println$S("CUI >> getPrefSize >> " + d + " for " + this.id );
return d;
});

Clazz.newMeth(C$, 'getHTMLSize', function () {
return this.setHTMLSize$swingjs_api_js_DOMNode$Z(this.updateDOMNode(), false);
});

Clazz.newMeth(C$, 'update$java_awt_Graphics$javax_swing_JComponent', function (g, c) {
if (C$.borderTest) {
g.setColor$java_awt_Color((I$[1]||$incl$(1)).red);
g.drawRect$I$I$I$I(0, 0, c.getWidth(), c.getHeight());
System.out.println$S("drawing " + c.getWidth() + " " + c.getHeight() );
}this.setHTMLElement();
if (c.isOpaque() && this.allowPaintedBackground ) {
g.setColor$java_awt_Color(c.getBackground());
g.fillRect$I$I$I$I(0, 0, c.getWidth(), c.getHeight());
this.setBackgroundPainted();
}this.paint$java_awt_Graphics$javax_swing_JComponent(g, c);
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
this.update$java_awt_Graphics$javax_swing_JComponent(g, this.jc);
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics$javax_swing_JComponent', function (g, c) {
});

Clazz.newMeth(C$, 'repaint$J$I$I$I$I', function (tm, x, y, width, height) {
});

Clazz.newMeth(C$, 'print$java_awt_Graphics', function (g) {
(I$[2]||$incl$(2)).notImplemented$S("");
});

Clazz.newMeth(C$, 'getMinimumSize', function () {
return this.getPreferredSize();
});

Clazz.newMeth(C$, 'getMaximumSize', function () {
if (this.isToolbarFixed) {
var parent = this.jc.getParent();
var parentClass = (parent == null  ? null : parent.getUIClassID());
if ("ToolBarUI" == parentClass) return this.getPreferredSize();
}return null;
});

Clazz.newMeth(C$, 'getMinimumSize$javax_swing_JComponent', function (jc) {
return this.getMinimumSize();
});

Clazz.newMeth(C$, 'getPreferredSize$javax_swing_JComponent', function (jc) {
return this.getPreferredSize();
});

Clazz.newMeth(C$, 'getMaximumSize$javax_swing_JComponent', function (jc) {
return this.getMaximumSize();
});

Clazz.newMeth(C$, 'contains$javax_swing_JComponent$I$I', function (c, x, y) {
return c.inside$I$I(x, y);
});

Clazz.newMeth(C$, 'createUI$javax_swing_JComponent', function (c) {
return null;
}, 1);

Clazz.newMeth(C$, 'getBaseline$javax_swing_JComponent$I$I', function (c, width, height) {
if (c == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["Component must be non-null"]);
}if (width < 0 || height < 0 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Width and height must be >= 0"]);
}return -1;
});

Clazz.newMeth(C$, 'getBaselineResizeBehavior$javax_swing_JComponent', function (c) {
if (c == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["Component must be non-null"]);
}return (I$[7]||$incl$(7)).OTHER;
});

Clazz.newMeth(C$, 'getJSTextValue', function () {
return (I$[3]||$incl$(3)).getAttr(this.domNode, this.valueNode == null  ? "innerHTML" : "value");
});

Clazz.newMeth(C$, 'getOuterNode', function () {
return (this.outerNode == null  ? this.setHTMLElement() : this.outerNode);
});

Clazz.newMeth(C$, 'setProp$swingjs_api_js_DOMNode$S$S', function (obj, prop, val) {
return (I$[3]||$incl$(3)).setAttr(obj, prop, val);
});

Clazz.newMeth(C$, 'isObscured', function () {
(I$[2]||$incl$(2)).notImplemented$S("");
return false;
});

Clazz.newMeth(C$, 'canDetermineObscurity', function () {
(I$[2]||$incl$(2)).notImplemented$S("");
return false;
});

Clazz.newMeth(C$, 'setVisible$Z', function (b) {
var node = this.getOuterNode();
if (node == null ) node = this.domNode;
(I$[3]||$incl$(3)).setVisible(node, b);
if (b) {
if (this.isDisposed) p$.undisposeUI$swingjs_api_js_DOMNode.apply(this, [node]);
this.toFront();
}});

Clazz.newMeth(C$, 'toFront', function () {
});

Clazz.newMeth(C$, 'setEnabled$Z', function (b) {
if (b == this.enabled ) return;
this.enabled = b;
if (this.$enableNode != null ) p$.enableNode$swingjs_api_js_DOMNode$Z.apply(this, [this.$enableNode, b]);
 else if (this.enableNodes != null ) for (var i = 0; i < this.enableNodes.length; i++) p$.enableNode$swingjs_api_js_DOMNode$Z.apply(this, [this.enableNodes[i], b]);

});

Clazz.newMeth(C$, 'enableNode$swingjs_api_js_DOMNode$Z', function (node, b) {
if (node == null ) return;
(I$[3]||$incl$(3)).setAttr(node, "disabled", (b ? null : "TRUE"));
var pp = this.getPropertyPrefix();
if (!b && this.inactiveForeground === this.colorUNKNOWN  ) this.getDisabledColors$S(pp);
if (this.jc.isOpaque()) {
var bg = this.c.getBackground();
this.setBackground$java_awt_Color(b || !(Clazz.instanceOf(bg, "javax.swing.plaf.UIResource")) || this.inactiveBackground == null    ? bg : this.inactiveBackground);
}var fg = this.c.getForeground();
this.setForeground$java_awt_Color(b ? fg : this.getInactiveTextColor$java_awt_Color(fg));
});

Clazz.newMeth(C$, 'getInactiveTextColor$java_awt_Color', function (fg) {
return (this.inactiveForeground == null  ? fg : this.inactiveForeground);
});

Clazz.newMeth(C$, 'getDisabledColors$S', function (pp) {
this.inactiveBackground = (I$[8]||$incl$(8)).getColor$O(pp + "inactiveBackground");
this.inactiveForeground = (I$[8]||$incl$(8)).getColor$O(pp + "inactiveForeground");
});

Clazz.newMeth(C$, 'setBounds$I$I$I$I$I', function (x, y, width, height, op) {
var isBounded = (width > 0 && height > 0 );
if (isBounded && !this.boundsSet ) {
if (this.c.visible) this.setVisible$Z(true);
this.boundsSet = true;
}if (C$.debugging) System.out.println$S("CUI << SetBounds >> [" + x + " " + y + " " + width + " " + height + "] op=" + op + " for " + this.id );
switch (op) {
case 3:
case 1:
x = this.c.getX();
y = this.c.getY();
if (this.x != x || this.y != y ) {
this.x = x;
this.y = y;
}p$.setOuterLocationFromComponent.apply(this, []);
if (op == 1) break;
case 4:
case 2:
if (this.scrollPaneUI != null ) {
width = Math.min(width, this.scrollPaneUI.c.getWidth());
height = Math.min(height, this.scrollPaneUI.c.getHeight());
}if (width > 0 && height > 0 ) p$.setSizeFromComponent$I$I$I.apply(this, [width, height, op]);
break;
}
});

Clazz.newMeth(C$, 'setSizeFromComponent$I$I$I', function (width, height, op) {
var size = this.getCSSAdjustment$Z(true);
this.width = width;
this.height = height;
if (C$.debugging) System.out.println$S(this.id + " setBounds " + this.x + " " + this.y + " " + this.width + " " + this.height + " op=" + op + " createDOM?" + (this.domNode == null ) );
if (this.domNode == null ) this.updateDOMNode();
this.setJSDimensions$I$I(width + size.width, height + size.height);
this.setInnerComponentBounds$I$I(width, height);
});

Clazz.newMeth(C$, 'setJSDimensions$I$I', function (width, height) {
if (this.jsActualWidth > 0) width = this.jsActualWidth;
if (this.jsActualHeight > 0) height = this.jsActualHeight;
(I$[3]||$incl$(3)).setSize(this.domNode, width, height);
if (this.outerNode != null ) (I$[3]||$incl$(3)).setSize(this.outerNode, width, height);
});

Clazz.newMeth(C$, 'setInnerComponentBounds$I$I', function (width, height) {
p$.setAlignment.apply(this, []);
if (C$.debugging) System.out.println$S("CUI reshapeMe: need to reshape " + this.id + " w:" + this.width + "->" + width + " h:" + this.height + "->" + height );
});

Clazz.newMeth(C$, 'setAlignment', function () {
if (this.canAlignText) {
this.setVerticalAlignment$Z(true);
p$.setTextAlignment.apply(this, []);
} else if (this.canAlignIcon) {
this.setVerticalAlignment$Z(false);
p$.setTextAlignment.apply(this, []);
}});

Clazz.newMeth(C$, 'setTextAlignment', function () {
if (this.c.getWidth() == 0) return;
var type = (this.c).getHorizontalAlignment();
var prop = null;
switch (type) {
case 4:
case 11:
prop = "right";
break;
case 2:
case 10:
prop = "left";
break;
case 0:
prop = "center";
break;
default:
return;
}
(I$[3]||$incl$(3)).setStyles(this.domNode, ["width", this.c.getWidth() + "px", "text-align", this.textAlign = prop]);
if (this.jc.uiClassID == "LabelUI" && this.centeringNode != null  ) {
var left = 0;
var w = this.actualWidth;
if (w == 0) w = this.setHTMLSize1$swingjs_api_js_DOMNode$Z$Z(this.centeringNode, false, false).width;
switch (type) {
case 2:
case 10:
break;
case 4:
case 11:
prop = "right";
left = this.c.getWidth() - w;
break;
case 0:
left = ((this.c.getWidth() - w)/2|0);
break;
default:
return;
}
(I$[3]||$incl$(3)).setStyles(this.centeringNode, ["position", "absolute", "left", left + "px"]);
}});

Clazz.newMeth(C$, 'setVerticalAlignment$Z', function (isText) {
var type = (this.c).getVerticalAlignment();
if (this.centeringNode == null  || this.c.getHeight() == 0 ) return;
var top = 0;
var h = this.actualHeight;
if (h == 0) {
if (isText) {
if (this.c.getFont() == null ) return;
h = this.setHTMLSize1$swingjs_api_js_DOMNode$Z$Z(this.domNode, false, false).height;
h = h-((this.c.getFont().getFontMetrics().getDescent()));
} else {
h = this.iconHeight;
}}switch (type) {
case 1:
break;
case 3:
top = this.c.getHeight() - h;
break;
case 0:
top = ((this.c.getHeight() - h)/2|0);
break;
default:
return;
}
(I$[3]||$incl$(3)).setStyles(this.centeringNode, ["position", "absolute", "top", top + "px"]);
});

Clazz.newMeth(C$, 'handleEvent$java_awt_AWTEvent', function (e) {
});

Clazz.newMeth(C$, 'coalescePaintEvent$java_awt_event_PaintEvent', function (e) {
(I$[2]||$incl$(2)).notImplemented$S("");
});

Clazz.newMeth(C$, 'getLocationOnScreen', function () {
var offset = this.$$O(this.outerNode).offset();
return Clazz.new_((I$[9]||$incl$(9)).c$$I$I,[offset.left, offset.top]);
});

Clazz.newMeth(C$, 'getColorModel', function () {
return (I$[10]||$incl$(10)).getDefaultToolkit().getColorModel();
});

Clazz.newMeth(C$, 'getToolkit', function () {
return (I$[10]||$incl$(10)).getDefaultToolkit();
});

Clazz.newMeth(C$, 'getGraphics', function () {
return null;
});

Clazz.newMeth(C$, 'getFontMetrics$java_awt_Font', function (font) {
return this.c.getFontMetrics$java_awt_Font(font);
});

Clazz.newMeth(C$, 'dispose', function () {
this.isDisposed = true;
(I$[3]||$incl$(3)).remove(this.domNode);
if (this.domNode !== this.outerNode ) (I$[3]||$incl$(3)).remove(this.outerNode);
});

Clazz.newMeth(C$, 'undisposeUI$swingjs_api_js_DOMNode', function (node) {
if (this.c.getParent() != null ) {
var ui = this.c.getParent().getUI();
if (ui.containerNode != null ) ui.containerNode.appendChild(node);
}if (this.outerNode != null  && this.domNode !== this.outerNode  ) this.outerNode.appendChild(this.domNode);
this.isDisposed = false;
});

Clazz.newMeth(C$, 'setForeground$java_awt_Color', function (color) {
if (this.domNode != null ) (I$[3]||$incl$(3)).setStyles(this.domNode, ["color", (color == null  ? "rgba(0,0,0,0)" : (I$[4]||$incl$(4)).getCSSColor$java_awt_Color(color == null  ? (I$[1]||$incl$(1)).black : color))]);
});

Clazz.newMeth(C$, 'setBackground$java_awt_Color', function (color) {
p$.setBackgroundFor$swingjs_api_js_DOMNode$java_awt_Color.apply(this, [this.domNode, color]);
});

Clazz.newMeth(C$, 'setBackgroundFor$swingjs_api_js_DOMNode$java_awt_Color', function (node, color) {
if (node == null  || this.isMenuItem ) return;
(I$[3]||$incl$(3)).setStyles(node, ["background-color", (I$[4]||$incl$(4)).getCSSColor$java_awt_Color(color == null  ? C$.rootPaneColor : color)]);
if (this.allowPaintedBackground && this.jc.selfOrParentBackgroundPainted() ) p$.setTransparent$swingjs_api_js_DOMNode.apply(this, [node]);
 else p$.checkTransparent$swingjs_api_js_DOMNode.apply(this, [node]);
});

Clazz.newMeth(C$, 'checkTransparent$swingjs_api_js_DOMNode', function (node) {
if (!this.c.isOpaque() && node != null  ) p$.setTransparent$swingjs_api_js_DOMNode.apply(this, [node]);
});

Clazz.newMeth(C$, 'setBackgroundPainted', function () {
p$.setTransparent$swingjs_api_js_DOMNode.apply(this, [this.domNode]);
});

Clazz.newMeth(C$, 'setTransparent$swingjs_api_js_DOMNode', function (node) {
(I$[3]||$incl$(3)).setStyles(node, ["background", "transparent"]);
});

Clazz.newMeth(C$, 'setFont$java_awt_Font', function (f) {
if (this.domNode != null ) this.setCssFont$swingjs_api_js_DOMNode$java_awt_Font(this.domNode, f);
});

Clazz.newMeth(C$, 'updateCursorImmediately', function () {
var curs;
switch (this.c.getCursor().getType()) {
case 1:
curs = "crosshair";
break;
case 3:
curs = "wait";
break;
case 8:
curs = "ns-resize";
break;
case 12:
curs = "grab";
break;
case 13:
curs = "move";
break;
default:
curs = "default";
break;
}
(I$[3]||$incl$(3)).setStyles(this.getOuterNode(), ["cursor", curs]);
this.setWaitImage$Z(curs == "wait");
});

Clazz.newMeth(C$, 'setWaitImage$Z', function (doShow) {
if (this.waitImage != null ) {
if (!doShow) return;
var path = "";
{
path = this.applet._j2sPath;
}
path += "/img/cursor_wait.gif";
if (C$.debugging) System.out.println$S("loading wait cursor " + path);
this.waitImage = this.newDOMObject$S$S$SA("image", this.id + "_waitImage", ["src", path]);
}if (doShow) this.$$O(this.waitImage).show();
 else this.$$O(this.waitImage).hide();
});

Clazz.newMeth(C$, 'requestFocus$java_awt_Component$Z$Z$J$sun_awt_CausedFocusEvent_Cause', function (lightweightChild, temporary, focusedWindowChangeAllowed, time, cause) {
if (this.focusNode == null ) return false;
this.$$O(this.focusNode).focus();
if (this.textNode != null ) this.$$O(this.textNode).select();
 else if (this.valueNode != null ) this.$$O(this.valueNode).select();
return true;
});

Clazz.newMeth(C$, 'isFocusable', function () {
return (this.focusNode != null );
});

Clazz.newMeth(C$, 'createImage$java_awt_image_ImageProducer', function (producer) {
(I$[2]||$incl$(2)).notImplemented$S("");
return null;
});

Clazz.newMeth(C$, 'createImage$I$I', function (width, height) {
(I$[2]||$incl$(2)).notImplemented$S("");
return null;
});

Clazz.newMeth(C$, 'createVolatileImage$I$I', function (width, height) {
(I$[2]||$incl$(2)).notImplemented$S("");
return null;
});

Clazz.newMeth(C$, 'prepareImage$java_awt_Image$I$I$java_awt_image_ImageObserver', function (img, w, h, o) {
(I$[2]||$incl$(2)).notImplemented$S("");
return false;
});

Clazz.newMeth(C$, 'checkImage$java_awt_Image$I$I$java_awt_image_ImageObserver', function (img, w, h, o) {
(I$[2]||$incl$(2)).notImplemented$S("");
return 0;
});

Clazz.newMeth(C$, 'getGraphicsConfiguration', function () {
(I$[2]||$incl$(2)).notImplemented$S("");
return null;
});

Clazz.newMeth(C$, 'handlesWheelScrolling', function () {
(I$[2]||$incl$(2)).notImplemented$S("");
return false;
});

Clazz.newMeth(C$, 'getBackBuffer', function () {
(I$[2]||$incl$(2)).notImplemented$S("");
return null;
});

Clazz.newMeth(C$, 'destroyBuffers', function () {
(I$[2]||$incl$(2)).notImplemented$S("");
});

Clazz.newMeth(C$, 'reparent$java_awt_peer_ContainerPeer', function (newContainer) {
(I$[2]||$incl$(2)).notImplemented$S("");
});

Clazz.newMeth(C$, 'isReparentSupported', function () {
(I$[2]||$incl$(2)).notImplemented$S("");
return false;
});

Clazz.newMeth(C$, 'layout', function () {
(I$[2]||$incl$(2)).notImplemented$S("");
});

Clazz.newMeth(C$, 'getBounds', function () {
(I$[2]||$incl$(2)).notImplemented$S("");
return null;
});

Clazz.newMeth(C$, 'hasFocus', function () {
return this.focusNode != null  && this.focusNode === (I$[3]||$incl$(3)).getAttr(this.document, "activeElement")  ;
});

Clazz.newMeth(C$, 'notifyFocus$Z', function (focusGained) {
var e = Clazz.new_((I$[11]||$incl$(11)).c$$java_awt_Component$I,[this.c, focusGained ? 1004 : 1005]);
if (focusGained) {
C$.focusedUI = this;
(I$[10]||$incl$(10)).getEventQueue().postEvent$java_awt_AWTEvent(e);
} else {
C$.focusedUI = null;
{
(I$[10]||$incl$(10)).getEventQueue().dispatchEventAndWait$java_awt_AWTEvent$O(e, this.c);
}}});

Clazz.newMeth(C$, 'getZIndex$S', function (what) {
var node = this.domNode;
var z = 0;
{
if (what) return this.applet._z[what];
while (node && !node.style["z-index"]) node = node.parentElement;
z = parseInt(node.style["z-index"]);
return(!z || isNaN(z) ? 100000 : z);
}
});

Clazz.newMeth(C$, 'getInsets', function () {
return null;
});

Clazz.newMeth(C$, 'beginValidate', function () {
});

Clazz.newMeth(C$, 'endValidate', function () {
});

Clazz.newMeth(C$, 'beginLayout', function () {
if (!this.boundsSet && !this.isContainer ) this.setVisible$Z(false);
this.layingOut = true;
});

Clazz.newMeth(C$, 'endLayout', function () {
this.layingOut = false;
});

Clazz.newMeth(C$, 'getId', function () {
return this.id;
});

Clazz.newMeth(C$, 'dumpEvent$java_util_EventObject', function (e) {
return e.toString();
});

Clazz.newMeth(C$, 'toCSSString$java_awt_Color', function (c) {
var opacity = c.getAlpha();
if (opacity == 255) return "#" + C$.toRGBHexString$java_awt_Color(c);
var rgb = c.getRGB();
return "rgba(" + ((rgb >> 16) & 255) + "," + ((rgb >> 8) & 255) + "," + (rgb & 255) + "," + new Float(opacity / 255.0).toString()  + ")";
}, 1);

Clazz.newMeth(C$, 'toRGBHexString$java_awt_Color', function (c) {
var rgb = c.getRGB();
if (rgb == 0) return "000000";
var r = "00" + Integer.toHexString((rgb >> 16) & 255);
r = r.substring(r.length$() - 2);
var g = "00" + Integer.toHexString((rgb >> 8) & 255);
g = g.substring(g.length$() - 2);
var b = "00" + Integer.toHexString(rgb & 255);
b = b.substring(b.length$() - 2);
return r + g + b ;
}, 1);

Clazz.newMeth(C$, 'updateSceneGraph$javax_swing_JComponent$javax_swing_JComponent$swingjs_JSGraphics2D', function (comp, owner, g) {
var node = (comp.ui).outerNode;
var x = 0;
var y = 0;
{
x = g.$transform.m02; y = g.$transform.m12;
if (x == node.lastSceneX && y == node.lastSceneY) return;
node.lastSceneX = x; node.lastSceneY = y;
}
(I$[3]||$incl$(3)).setStyles(node, ["left", x + "px", "top", y + "px"]);
{
if (node.parentElement == null) owner.ui.outerNode.appendChild(node);
}
}, 1);

Clazz.newMeth(C$, 'getPropertyPrefix', function () {
return null;
});

Clazz.newMeth(C$, 'setPadding$java_awt_Insets', function (padding) {
(I$[3]||$incl$(3)).setStyles(this.domNode, ["padding", padding == null  ? "0px" : padding.top + "px " + padding.left + "px " + padding.bottom + "px " + padding.right + "px" ]);
});

Clazz.newMeth(C$, 'addDropTarget$java_awt_dnd_DropTarget', function (t) {
this.dropTarget = t;
p$.setDropTarget.apply(this, []);
});

Clazz.newMeth(C$, 'removeTarget', function () {
if (this.dropTarget == null ) return;
this.dropTarget = null;
p$.setDropTarget.apply(this, []);
});

Clazz.newMeth(C$, 'setDropTarget', function () {
var node;
if (this.dropTarget !== this  && ((node = this.outerNode) != null  || (node = this.domNode) != null  ) ) (I$[3]||$incl$(3)).setAttr(node, "data-dropComponent", this.jc);
});
})();
//Created 2018-02-06 09:00:38
