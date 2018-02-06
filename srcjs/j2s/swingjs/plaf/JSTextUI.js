(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['javax.swing.text.DefaultEditorKit','javax.swing.UIManager','swingjs.JSToolkit','swingjs.plaf.JSCaret','javax.swing.SwingUtilities','javax.swing.plaf.InputMapUIResource','javax.swing.plaf.ActionMapUIResource','swingjs.plaf.TextListener','swingjs.api.js.DOMNode']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSTextUI", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'swingjs.plaf.JSLightweightUI');
var p$=C$.prototype;
C$.defaultKit = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.defaultKit = Clazz.new_((I$[1]||$incl$(1)));
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.inactiveBackgroundColor = null;
this.textListener = null;
this.editor = null;
this.editable = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.editable = true;
}, 1);

Clazz.newMeth(C$, 'getComponentText', function () {
var jtc = this.jc;
return (jtc.getDocument() == null  ? null : (this.currentText = (this.c).getText()));
});

Clazz.newMeth(C$, 'installDefaults', function () {
var prefix = this.getPropertyPrefix();
var f = this.editor.getFont();
if ((f == null ) || (Clazz.instanceOf(f, "javax.swing.plaf.UIResource")) ) {
this.editor.setFont$java_awt_Font((I$[2]||$incl$(2)).getFont$O(prefix + "font"));
}var bg = this.editor.getBackground();
if ((bg == null ) || (Clazz.instanceOf(bg, "javax.swing.plaf.UIResource")) ) {
this.editor.setBackground$java_awt_Color((I$[2]||$incl$(2)).getColor$O(prefix + "background"));
}var fg = this.editor.getForeground();
if ((fg == null ) || (Clazz.instanceOf(fg, "javax.swing.plaf.UIResource")) ) {
this.editor.setForeground$java_awt_Color((I$[2]||$incl$(2)).getColor$O(prefix + "foreground"));
}var dfg = this.editor.getDisabledTextColor();
if ((dfg == null ) || (Clazz.instanceOf(dfg, "javax.swing.plaf.UIResource")) ) {
this.editor.setDisabledTextColor$java_awt_Color((I$[2]||$incl$(2)).getColor$O(prefix + "inactiveForeground"));
}dfg = (I$[2]||$incl$(2)).getColor$O(prefix + "inactiveBackground");
this.inactiveBackgroundColor = (dfg == null  ? null : (I$[3]||$incl$(3)).getCSSColor$java_awt_Color(dfg));
var margin = this.editor.getMargin();
if (margin == null  || Clazz.instanceOf(margin, "javax.swing.plaf.UIResource") ) {
this.editor.setMargin$java_awt_Insets((I$[2]||$incl$(2)).getInsets$O(prefix + ".margin"));
}});

Clazz.newMeth(C$, 'installDefaults2', function () {
var caret = this.editor.getCaret();
if (caret == null  || Clazz.instanceOf(caret, "javax.swing.plaf.UIResource") ) {
this.editor.setCaret$javax_swing_text_Caret(Clazz.new_((I$[4]||$incl$(4))));
}});

Clazz.newMeth(C$, 'handleJSEvent$O$I$O', function (target, eventType, jQueryEvent) {
var t = this.jc;
return (t.isEditable() ? this.textListener.handleJSTextEvent$swingjs_plaf_JSTextUI$I$O(this, eventType, jQueryEvent) : false);
});

Clazz.newMeth(C$, 'uninstallDefaults', function () {
if (Clazz.instanceOf(this.editor.getCaretColor(), "javax.swing.plaf.UIResource")) {
this.editor.setCaretColor$java_awt_Color(null);
}if (Clazz.instanceOf(this.editor.getSelectionColor(), "javax.swing.plaf.UIResource")) {
this.editor.setSelectionColor$java_awt_Color(null);
}if (Clazz.instanceOf(this.editor.getDisabledTextColor(), "javax.swing.plaf.UIResource")) {
this.editor.setDisabledTextColor$java_awt_Color(null);
}if (Clazz.instanceOf(this.editor.getSelectedTextColor(), "javax.swing.plaf.UIResource")) {
this.editor.setSelectedTextColor$java_awt_Color(null);
}if (Clazz.instanceOf(this.editor.getBorder(), "javax.swing.plaf.UIResource")) {
this.editor.setBorder$javax_swing_border_Border(null);
}if (Clazz.instanceOf(this.editor.getMargin(), "javax.swing.plaf.UIResource")) {
this.editor.setMargin$java_awt_Insets(null);
}});

Clazz.newMeth(C$, 'installKeyboardActions', function () {
var km = this.getInputMap();
if (km != null ) {
(I$[5]||$incl$(5)).replaceUIInputMap$javax_swing_JComponent$I$javax_swing_InputMap(this.editor, 0, km);
}var map = this.getActionMap();
if (map != null ) {
(I$[5]||$incl$(5)).replaceUIActionMap$javax_swing_JComponent$javax_swing_ActionMap(this.editor, map);
}});

Clazz.newMeth(C$, 'getInputMap', function () {
var map = Clazz.new_((I$[6]||$incl$(6)));
return map;
});

Clazz.newMeth(C$, 'getActionMap', function () {
var mapName = this.classID + ".actionMap";
var map = (I$[2]||$incl$(2)).get$O(mapName);
if (map == null ) {
map = this.createActionMap();
if (map != null ) {
(I$[2]||$incl$(2)).getLookAndFeelDefaults().put$O$O(mapName, map);
}}return map;
});

Clazz.newMeth(C$, 'createActionMap', function () {
var map = Clazz.new_((I$[7]||$incl$(7)));
var actions = this.editor.getActions();
var n = (actions == null  ? 0 : actions.length);
for (var i = 0; i < n; i++) {
var a = actions[i];
map.put$O$javax_swing_Action(a.getValue$S("Name"), a);
}
return map;
});

Clazz.newMeth(C$, 'uninstallKeyboardActions', function () {
this.editor.setKeymap$javax_swing_text_Keymap(null);
(I$[5]||$incl$(5)).replaceUIInputMap$javax_swing_JComponent$I$javax_swing_InputMap(this.editor, 2, null);
(I$[5]||$incl$(5)).replaceUIActionMap$javax_swing_JComponent$javax_swing_ActionMap(this.editor, null);
});

Clazz.newMeth(C$, 'getComponent', function () {
return this.editor;
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.isToolbarFixed = false;
this.editor = jc;
this.textListener = Clazz.new_((I$[8]||$incl$(8)).c$$swingjs_plaf_JSTextUI$javax_swing_text_JTextComponent,[this, this.editor]);
this.installDefaults();
p$.installDefaults2.apply(this, []);
this.installListeners$javax_swing_text_JTextComponent(this.editor);
this.installKeyboardActions();
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
this.uninstallDefaults();
jc.removeAll();
var lm = jc.getLayout();
if (Clazz.instanceOf(lm, "javax.swing.plaf.UIResource")) {
jc.setLayout$java_awt_LayoutManager(null);
}this.uninstallKeyboardActions();
this.uninstallListeners$javax_swing_text_JTextComponent(this.editor);
this.editor = null;
this.textListener = null;
});

Clazz.newMeth(C$, 'installListeners$javax_swing_text_JTextComponent', function (b) {
var listener = this.textListener;
b.addMouseListener$java_awt_event_MouseListener(listener);
b.addMouseMotionListener$java_awt_event_MouseMotionListener(listener);
b.addFocusListener$java_awt_event_FocusListener(listener);
b.addPropertyChangeListener$java_beans_PropertyChangeListener(listener);
});

Clazz.newMeth(C$, 'uninstallListeners$javax_swing_text_JTextComponent', function (b) {
var listener = this.textListener;
b.removeMouseListener$java_awt_event_MouseListener(listener);
b.removeMouseMotionListener$java_awt_event_MouseMotionListener(listener);
b.removeFocusListener$java_awt_event_FocusListener(listener);
b.removePropertyChangeListener$java_beans_PropertyChangeListener(listener);
b.getDocument().removeDocumentListener$javax_swing_event_DocumentListener(listener);
});

Clazz.newMeth(C$, 'getMinimumSize', function () {
var d = this.getPreferredSize();
var i = this.jc.getInsets();
d.width = d.width+(i.left + i.right);
d.height = d.height+(i.top + i.bottom);
return d;
});

Clazz.newMeth(C$, 'getMaximumSize', function () {
return C$.superclazz.prototype.getMaximumSize.apply(this, []);
});

Clazz.newMeth(C$, 'getEditorKit$javax_swing_text_JTextComponent', function (tc) {
return C$.defaultKit;
});

Clazz.newMeth(C$, 'handleEnter$I', function (eventType) {
return false;
});

Clazz.newMeth(C$, 'setEditable$Z', function (editable) {
this.editable = editable;
if (this.domNode == null ) return;
(I$[9]||$incl$(9)).setAttr(this.domNode, "readOnly", editable ? null : "true");
});

Clazz.newMeth(C$, 'setText$S', function (val) {
var prop = null;
var obj = null;
if (val == null  ? this.currentText != null  : !val.equals$O(this.currentText)) {
this.currentText = val;
if (this.textNode != null ) {
prop = "innerHTML";
obj = this.textNode;
} else if (this.valueNode != null ) {
prop = "value";
obj = this.valueNode;
}if (obj != null ) this.setProp$swingjs_api_js_DOMNode$S$S(obj, prop, val);
}});

Clazz.newMeth(C$, 'getInactiveTextColor$java_awt_Color', function (fg) {
return (!this.editor.isEnabled() ? this.editor.getDisabledTextColor() : !this.editor.isEditable() ? this.inactiveForeground : fg);
});
;
(function(){var C$=Clazz.newClass(P$.JSTextUI, "TextActionWrapper", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.action = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.action = null;
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_text_TextAction', function (action) {
C$.superclazz.c$$S.apply(this, [action.getValue$S("Name")]);
C$.$init$.apply(this);
this.action = action;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.action.actionPerformed$java_awt_event_ActionEvent(e);
});

Clazz.newMeth(C$, 'isEnabled', function () {
return (this.this$0.editor == null  || this.this$0.editor.isEditable() ) ? this.action.isEnabled() : false;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JSTextUI, "FocusAction", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'javax.swing.AbstractAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.this$0.editor.requestFocus();
});

Clazz.newMeth(C$, 'isEnabled', function () {
return this.this$0.editor.isEditable();
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:57
