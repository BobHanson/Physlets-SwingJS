(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode','javax.swing.BorderFactory','swingjs.plaf.JSComponentUI','swingjs.plaf.ButtonListener','javax.swing.UIManager','javax.swing.LookAndFeel','java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSButtonUI", null, 'swingjs.plaf.JSLightweightUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.label = null;
this.itemNode = null;
this.menuItem = null;
this.button = null;
this.isSimpleButton = false;
this.shiftOffset = 0;
this.defaultTextShiftOffset = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.shiftOffset = 0;
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
this.isSimpleButton = true;
this.allowPaintedBackground = false;
if (this.domNode == null ) {
this.domNode = this.$enableNode = this.newDOMObject$S$S$SA("button", this.id, ["type", "button"]);
(I$[1]||$incl$(1)).setStyles(this.domNode, ["lineHeight", "0.8"]);
this.iconNode = this.newDOMObject$S$S$SA("span", this.id + "_icon", []);
this.textNode = this.newDOMObject$S$S$SA("span", this.id + "_btn", []);
this.domNode.appendChild(this.iconNode);
this.domNode.appendChild(this.textNode);
this.setDataComponent$swingjs_api_js_DOMNode(this.domNode);
this.setDataComponent$swingjs_api_js_DOMNode(this.iconNode);
}this.setPadding$java_awt_Insets(this.button.getMargin());
this.setIconAndText$S$javax_swing_ImageIcon$I$S("button", this.button.getIcon(), this.button.getIconTextGap(), this.button.getText());
if (this.button.getBorder() == null  || this.button.getBorder() === (I$[2]||$incl$(2)).emptyBorder  ) (I$[1]||$incl$(1)).setStyles(this.domNode, ["border", "none"]);
 else if (this.button.getBorder() === (I$[2]||$incl$(2)).html5Border ) (I$[1]||$incl$(1)).setStyles(this.domNode, ["border", null]);
return this.domNode;
});

Clazz.newMeth(C$, 'createItem$S$swingjs_api_js_DOMNode', function (type, label) {
var text = this.button.getText();
var icon = this.button.getIcon();
var gap = this.button.getIconTextGap();
if (("|").equals$O(text) || ("-").equals$O(text) ) {
text = null;
}this.itemNode = this.newDOMObject$S$S$SA("li", this.id + type, []);
if (text == null  && icon == null  ) return this.itemNode;
var aNode = this.newDOMObject$S$S$SA("a", this.id + type + "_a" , []);
(I$[1]||$incl$(1)).setStyles(aNode, ["margin", "1px 4px 1px 4px"]);
this.itemNode.appendChild(aNode);
if (label == null ) {
if (this.iconNode == null ) this.iconNode = this.newDOMObject$S$S$SA("span", this.id + "_icon", []);
if (this.textNode == null ) this.textNode = this.newDOMObject$S$S$SA("span", this.id + "_text", []);
this.$$O(this.iconNode).attr("role", "menucloser");
this.$$O(this.textNode).attr("role", "menucloser");
this.setDataUI$swingjs_api_js_DOMNode(this.iconNode);
this.setDataUI$swingjs_api_js_DOMNode(this.textNode);
aNode.appendChild(this.iconNode);
aNode.appendChild(this.textNode);
this.setCssFont$swingjs_api_js_DOMNode$java_awt_Font(aNode, this.c.getFont());
this.$enableNode = aNode;
this.setIconAndText$S$javax_swing_ImageIcon$I$S("btn", icon, gap, text);
} else {
aNode.appendChild(label);
}this.setDataUI$swingjs_api_js_DOMNode(aNode);
this.setDataComponent$swingjs_api_js_DOMNode(aNode);
this.setDataComponent$swingjs_api_js_DOMNode(this.itemNode);
return this.itemNode;
});

Clazz.newMeth(C$, 'handleJSEvent$O$I$O', function (target, eventType, jQueryEvent) {
if ((I$[3]||$incl$(3)).debugging) System.out.println$S("JSButtonUI handleJSEvent for " + (target).id);
if (this.menuItem != null  && this.domBtn == null  ) {
switch (eventType) {
case 502:
this.menuItem.doClick$I(0);
return true;
}
}return false;
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.button = jc;
this.installDefaults$javax_swing_AbstractButton(this.button);
this.installListeners$javax_swing_AbstractButton(this.button);
this.installKeyboardActions$javax_swing_AbstractButton(this.button);
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
this.uninstallKeyboardActions$javax_swing_AbstractButton(this.button);
this.uninstallListeners$javax_swing_AbstractButton(this.button);
});

Clazz.newMeth(C$, 'installListeners$javax_swing_AbstractButton', function (b) {
var listener = Clazz.new_((I$[4]||$incl$(4)).c$$swingjs_plaf_JSButtonUI$Z,[this, this.isMenuItem]);
if (listener != null ) {
b.addMouseListener$java_awt_event_MouseListener(listener);
b.addMouseMotionListener$java_awt_event_MouseMotionListener(listener);
b.addFocusListener$java_awt_event_FocusListener(listener);
b.addPropertyChangeListener$java_beans_PropertyChangeListener(listener);
b.addChangeListener$javax_swing_event_ChangeListener(listener);
}});

Clazz.newMeth(C$, 'uninstallListeners$javax_swing_AbstractButton', function (b) {
var listener = this.getButtonListener$javax_swing_AbstractButton(b);
if (listener != null ) {
b.removeMouseListener$java_awt_event_MouseListener(listener);
b.removeMouseMotionListener$java_awt_event_MouseMotionListener(listener);
b.removeFocusListener$java_awt_event_FocusListener(listener);
b.removeChangeListener$javax_swing_event_ChangeListener(listener);
b.removePropertyChangeListener$java_beans_PropertyChangeListener(listener);
}});

Clazz.newMeth(C$, 'installKeyboardActions$javax_swing_AbstractButton', function (b) {
var listener = this.getButtonListener$javax_swing_AbstractButton(b);
if (listener != null ) {
listener.installKeyboardActions$javax_swing_JComponent(b);
}});

Clazz.newMeth(C$, 'uninstallKeyboardActions$javax_swing_AbstractButton', function (b) {
var listener = this.getButtonListener$javax_swing_AbstractButton(b);
if (listener != null ) {
listener.uninstallKeyboardActions$javax_swing_JComponent(b);
}});

Clazz.newMeth(C$, 'getButtonListener$javax_swing_AbstractButton', function (b) {
var listeners = b.getMouseMotionListeners();
if (listeners != null ) {
for (var counter = 0; counter < listeners.length; counter++) {
if (Clazz.instanceOf(listeners[counter], "swingjs.plaf.ButtonListener")) {
return listeners[counter];
}}
}return null;
});

Clazz.newMeth(C$, 'getPropertyPrefix', function () {
return "Button.";
});

Clazz.newMeth(C$, 'installDefaults$javax_swing_AbstractButton', function (b) {
var pp = this.getPropertyPrefix();
this.defaultTextShiftOffset = (I$[5]||$incl$(5)).getInt$O(pp + "textShiftOffset");
if (b.getMargin() == null  || (Clazz.instanceOf(b.getMargin(), "javax.swing.plaf.UIResource")) ) {
b.setMargin$java_awt_Insets((I$[5]||$incl$(5)).getInsets$O(pp + "margin"));
}(I$[6]||$incl$(6)).installColorsAndFont$javax_swing_JComponent$S$S$S(b, pp + "background", pp + "foreground", pp + "font");
(I$[6]||$incl$(6)).installBorder$javax_swing_JComponent$S(b, pp + "border");
(I$[6]||$incl$(6)).installProperty$javax_swing_JComponent$S$O(b, "iconTextGap",  new Integer(4));
});

Clazz.newMeth(C$, 'getCSSAdjustment$Z', function (addingCSS) {
return Clazz.new_((I$[7]||$incl$(7)).c$$I$I,[(this.itemNode == null  ? 0 : 10), 0]);
});

Clazz.newMeth(C$, 'setInnerComponentBounds$I$I', function (width, height) {
if (this.isSimpleButton && (this.imageNode == null  || this.button.getText() == null  ) ) (I$[1]||$incl$(1)).setSize(this.innerNode = this.domNode, width, height);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:37
