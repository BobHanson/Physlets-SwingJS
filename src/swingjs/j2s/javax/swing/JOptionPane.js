(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['swingjs.JSUtil','javax.swing.JDialog','javax.swing.SwingUtilities','java.awt.BorderLayout','javax.swing.UIManager','java.awt.event.WindowAdapter','javax.swing.JOptionPane','java.awt.event.ComponentAdapter','javax.swing.JOptionPane$3']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JOptionPane", null, 'javax.swing.JComponent');
C$.UNINITIALIZED_VALUE = null;
C$.USE_HTML5_MODAL_FOR_WARNINGS_AND_ERRORS = false;
var p$=C$.prototype;
C$.sharedFrameKey = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.UNINITIALIZED_VALUE = "uninitializedValue";
C$.USE_HTML5_MODAL_FOR_WARNINGS_AND_ERRORS = true;
C$.sharedFrameKey = Clazz.getClass(C$);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.icon = null;
this.message = null;
this.options = null;
this.initialValue = null;
this.messageType = 0;
this.optionType = 0;
this.value = null;
this.selectionValues = null;
this.inputValue = null;
this.initialSelectionValue = null;
this.wantsInput = false;
this.disposeOnHide = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.disposeOnHide = true;
}, 1);

Clazz.newMeth(C$, 'showInputDialog$O', function (message) {
return C$.showInputDialog$java_awt_Component$O(null, message);
}, 1);

Clazz.newMeth(C$, 'showInputDialog$O$O', function (message, initialSelectionValue) {
return C$.showInputDialog$java_awt_Component$O$O(null, message, initialSelectionValue);
}, 1);

Clazz.newMeth(C$, 'showInputDialog$java_awt_Component$O', function (parentComponent, message) {
return C$.showInputDialog$java_awt_Component$O$S$I(parentComponent, message, "Input", 3);
}, 1);

Clazz.newMeth(C$, 'showInputDialog$java_awt_Component$O$O', function (parentComponent, message, initialSelectionValue) {
return C$.showInputDialog$java_awt_Component$O$S$I$javax_swing_Icon$OA$O(parentComponent, message, "Input", 3, null, null, initialSelectionValue);
}, 1);

Clazz.newMeth(C$, 'showInputDialog$java_awt_Component$O$S$I', function (parentComponent, message, title, messageType) {
return C$.showInputDialog$java_awt_Component$O$S$I$javax_swing_Icon$OA$O(parentComponent, message, title, messageType, null, null, null);
}, 1);

Clazz.newMeth(C$, 'showInputDialog$java_awt_Component$O$S$I$javax_swing_Icon$OA$O', function (parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue) {
var value;
if (!(Clazz.instanceOf(parentComponent, "java.beans.PropertyChangeListener"))) {
if (!(Clazz.instanceOf(message, "java.lang.String"))) {
C$.warnJSDeveloper();
message = "?";
}if (!(Clazz.instanceOf(initialSelectionValue, "java.lang.String"))) {
C$.warnJSDeveloper();
initialSelectionValue = (initialSelectionValue == null  ? "" : initialSelectionValue.toString());
}var jsReturn = (I$[1]||$incl$(1)).prompt$S$S(message, initialSelectionValue);
return (jsReturn == null  ? null : jsReturn);
}var pane = Clazz.new_(C$.c$$O$I$I$javax_swing_Icon$OA$O,[message, messageType, 2, icon, null, null]);
pane.setWantsInput$Z(true);
pane.setSelectionValues$OA(selectionValues);
pane.setInitialSelectionValue$O(initialSelectionValue);
pane.setComponentOrientation$java_awt_ComponentOrientation(((parentComponent == null ) ? C$.getRootFrame() : parentComponent).getComponentOrientation());
var style = C$.styleFromMessageType$I(messageType);
var dialog = pane.createDialog$java_awt_Component$S$I(parentComponent, title, style);
pane.selectInitialValue();
dialog.setVisible$Z(true);
return (I$[2]||$incl$(2)).ASYNCHRONOUS_OBJECT;
}, 1);

Clazz.newMeth(C$, 'showMessageDialog$java_awt_Component$O', function (parentComponent, message) {
C$.showMessageDialog$java_awt_Component$O$S$I(parentComponent, message, "Message", 1);
}, 1);

Clazz.newMeth(C$, 'showMessageDialog$java_awt_Component$O$S$I', function (parentComponent, message, title, messageType) {
C$.showMessageDialog$java_awt_Component$O$S$I$javax_swing_Icon(parentComponent, message, title, messageType, null);
}, 1);

Clazz.newMeth(C$, 'showMessageDialog$java_awt_Component$O$S$I$javax_swing_Icon', function (parentComponent, message, title, messageType, icon) {
var simplify = C$.USE_HTML5_MODAL_FOR_WARNINGS_AND_ERRORS && (messageType == 2 || messageType == 0 ) ;
var isPropertyListener = Clazz.instanceOf(parentComponent, "java.beans.PropertyChangeListener");
if (simplify || !isPropertyListener ) {
if (!simplify && !(Clazz.instanceOf(message, "java.lang.String")) ) C$.warnJSDeveloper();
var s = C$.getMessageTypeString$I$S(messageType, ": ") + (title == "Message" ? "" : title + "\n\n") + (Clazz.instanceOf(message, "java.lang.String") ? "" + message : "?") ;
(I$[1]||$incl$(1)).alert$O(s);
return;
}C$.showOptionDialog$java_awt_Component$O$S$I$I$javax_swing_Icon$OA$O(parentComponent, message, title, -1, messageType, icon, null, null);
}, 1);

Clazz.newMeth(C$, 'warnJSDeveloper', function () {
System.err.println$S("JOptionPane: Component does not implement PropertyChangeListener.");
}, 1);

Clazz.newMeth(C$, 'showConfirmDialog$java_awt_Component$O', function (parentComponent, message) {
return C$.showConfirmDialog$java_awt_Component$O$S$I(parentComponent, message, "Confirm", 1);
}, 1);

Clazz.newMeth(C$, 'showConfirmDialog$java_awt_Component$O$S$I', function (parentComponent, message, title, optionType) {
return C$.showConfirmDialog$java_awt_Component$O$S$I$I(parentComponent, message, title, optionType, 3);
}, 1);

Clazz.newMeth(C$, 'showConfirmDialog$java_awt_Component$O$S$I$I', function (parentComponent, message, title, optionType, messageType) {
return C$.showConfirmDialog$java_awt_Component$O$S$I$I$javax_swing_Icon(parentComponent, message, title, optionType, messageType, null);
}, 1);

Clazz.newMeth(C$, 'showConfirmDialog$java_awt_Component$O$S$I$I$javax_swing_Icon', function (parentComponent, message, title, optionType, messageType, icon) {
var options = Clazz.array(java.lang.String, -1, ["ok"]);
switch (optionType) {
case 2:
options = Clazz.array(java.lang.String, -1, ["ok", "cancel"]);
break;
case 1:
options = Clazz.array(java.lang.String, -1, ["yes", "no", "cancel"]);
break;
case 0:
options = Clazz.array(java.lang.String, -1, ["yes", "no"]);
break;
}
var jsReturn = true;
if (!(Clazz.instanceOf(parentComponent, "java.beans.PropertyChangeListener"))) {
if (!(Clazz.instanceOf(message, "java.lang.String"))) {
C$.warnJSDeveloper();
message = "?";
}jsReturn = (I$[1]||$incl$(1)).confirm$S(message);
switch (optionType) {
case 2:
default:
return (jsReturn ? 0 : 2);
case 1:
return (jsReturn ? 0 : 2);
case 0:
return (jsReturn ? 0 : 1);
}
}return C$.showOptionDialog$java_awt_Component$O$S$I$I$javax_swing_Icon$OA$O(parentComponent, message, title, optionType, messageType, icon, null, null);
}, 1);

Clazz.newMeth(C$, 'showOptionDialog$java_awt_Component$O$S$I$I$javax_swing_Icon$OA$O', function (parentComponent, message, title, optionType, messageType, icon, options, initialValue) {
if (!(Clazz.instanceOf(parentComponent, "java.beans.PropertyChangeListener"))) {
C$.warnJSDeveloper();
return 2;
}var pane = Clazz.new_(C$.c$$O$I$I$javax_swing_Icon$OA$O,[message, messageType, optionType, icon, options, initialValue]);
pane.setInitialValue$O(initialValue);
pane.setComponentOrientation$java_awt_ComponentOrientation(((parentComponent == null ) ? C$.getRootFrame() : parentComponent).getComponentOrientation());
var style = C$.styleFromMessageType$I(messageType);
var dialog = pane.createDialog$java_awt_Component$S$I(parentComponent, title, style);
pane.selectInitialValue();
dialog.setVisible$Z(true);
return (I$[2]||$incl$(2)).ASYNCHRONOUS_INTEGER;
}, 1);

Clazz.newMeth(C$, 'createDialog$java_awt_Component$S', function (parentComponent, title) {
var style = C$.styleFromMessageType$I(this.getMessageType());
this.disposeOnHide = false;
return p$.createDialog$java_awt_Component$S$I.apply(this, [parentComponent, title, style]);
});

Clazz.newMeth(C$, 'createDialog$S', function (title) {
var style = C$.styleFromMessageType$I(this.getMessageType());
var dialog = Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_Dialog$S$Z,[null, title, true]);
p$.initDialog$javax_swing_JDialog$I$java_awt_Component.apply(this, [dialog, style, null]);
return dialog;
});

Clazz.newMeth(C$, 'createDialog$java_awt_Component$S$I', function (parentComponent, title, style) {
var dialog;
var window = C$.getWindowForComponent$java_awt_Component(parentComponent);
if (Clazz.instanceOf(window, "java.awt.Frame")) {
dialog = Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_Frame$S$Z,[window, title, true]);
} else {
dialog = Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_Dialog$S$Z,[window, title, true]);
}if (Clazz.instanceOf(window, "javax.swing.SwingUtilities.SharedOwnerFrame")) {
var ownerShutdownListener = (I$[3]||$incl$(3)).getSharedOwnerFrameShutdownListener();
dialog.addWindowListener$java_awt_event_WindowListener(ownerShutdownListener);
}p$.initDialog$javax_swing_JDialog$I$java_awt_Component.apply(this, [dialog, style, parentComponent]);
return dialog;
});

Clazz.newMeth(C$, 'initDialog$javax_swing_JDialog$I$java_awt_Component', function (dialog, style, parentComponent) {
dialog.setComponentOrientation$java_awt_ComponentOrientation(this.getComponentOrientation());
var contentPane = dialog.getContentPane();
contentPane.setLayout$java_awt_LayoutManager(Clazz.new_((I$[4]||$incl$(4))));
contentPane.add$java_awt_Component$O(this, "Center");
dialog.setResizable$Z(false);
if ((I$[2]||$incl$(2)).isDefaultLookAndFeelDecorated()) {
var supportsWindowDecorations = (I$[5]||$incl$(5)).getLookAndFeel().getSupportsWindowDecorations();
if (supportsWindowDecorations) {
dialog.setUndecorated$Z(true);
this.getRootPane().setWindowDecorationStyle$I(style);
}}dialog.pack();
dialog.setLocationRelativeTo$java_awt_Component(parentComponent);
var adapter = ((
(function(){var C$=Clazz.newClass(P$, "JOptionPane$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.awt.event.WindowAdapter'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.gotFocus = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.gotFocus = false;
}, 1);

Clazz.newMeth(C$, 'windowClosing$java_awt_event_WindowEvent', function (we) {
if (this.b$['javax.swing.JOptionPane'].wantsInput) this.b$['javax.swing.JOptionPane'].setInputValue$O(null);
 else this.b$['javax.swing.JOptionPane'].setValue$O(Integer.$valueOf(-1));
if (this.b$['javax.swing.JOptionPane'].disposeOnHide) {
this.$finals.dialog.dispose();
}});

Clazz.newMeth(C$, 'windowGainedFocus$java_awt_event_WindowEvent', function (we) {
if (!this.gotFocus) {
this.b$['javax.swing.JOptionPane'].selectInitialValue();
this.gotFocus = true;
}});
})()
), Clazz.new_((I$[6]||$incl$(6)), [this, {dialog: dialog}],P$.JOptionPane$1));
dialog.addWindowListener$java_awt_event_WindowListener(adapter);
dialog.addWindowFocusListener$java_awt_event_WindowFocusListener(adapter);
dialog.addComponentListener$java_awt_event_ComponentListener(((
(function(){var C$=Clazz.newClass(P$, "JOptionPane$2", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.awt.event.ComponentAdapter'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'componentShown$java_awt_event_ComponentEvent', function (ce) {
this.b$['javax.swing.JOptionPane'].setValue$O((I$[7]||$incl$(7)).UNINITIALIZED_VALUE);
});
})()
), Clazz.new_((I$[8]||$incl$(8)), [this, null],P$.JOptionPane$2)));
java.awt.JSComponent.ensurePropertyChangeListener$java_awt_Component$java_awt_Component(this, parentComponent);
this.addPropertyChangeListener$java_beans_PropertyChangeListener(((
(function(){var C$=Clazz.newClass(P$, "JOptionPane$3", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.beans.PropertyChangeListener', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (event) {
if (this.$finals.dialog.isVisible() && event.getSource() === this.b$['javax.swing.JOptionPane']   && (event.getPropertyName().equals$O("value") || event.getPropertyName().equals$O("inputValue") ) ) {
var value = event.getNewValue();
if (value !== (I$[7]||$incl$(7)).UNINITIALIZED_VALUE ) {
this.$finals.dialog.setVisible$Z(false);
this.$finals.dialog.dispose();
}}});
})()
), Clazz.new_((I$[9]||$incl$(9)).$init$, [this, {dialog: dialog}])));
});

Clazz.newMeth(C$, 'getReturnIndex$O', function (value) {
var selectedValue = this.getValue();
var ret = -1;
if (selectedValue == null ) {
ret = -1;
} else if (this.options == null ) {
if (Clazz.instanceOf(selectedValue, "java.lang.Integer")) {
ret = (selectedValue).intValue();
} else {
ret = -1;
}} else {
for (var counter = 0, maxCounter = this.options.length; counter < maxCounter; counter++) {
if (this.options[counter].equals$O(selectedValue)) {
ret = counter;
break;
}}
}return ret;
});

Clazz.newMeth(C$, 'getFrameForComponent$java_awt_Component', function (parentComponent) {
if (parentComponent == null ) return C$.getRootFrame();
if (Clazz.instanceOf(parentComponent, "java.awt.Frame")) return parentComponent;
return C$.getFrameForComponent$java_awt_Component(parentComponent.getParent());
}, 1);

Clazz.newMeth(C$, 'getWindowForComponent$java_awt_Component', function (parentComponent) {
if (parentComponent == null ) return C$.getRootFrame();
if (Clazz.instanceOf(parentComponent, "java.awt.Frame") || Clazz.instanceOf(parentComponent, "java.awt.Dialog") ) return parentComponent;
return C$.getWindowForComponent$java_awt_Component(parentComponent.getParent());
}, 1);

Clazz.newMeth(C$, 'setRootFrame$java_awt_Frame', function (newRootFrame) {
if (newRootFrame != null ) {
(I$[3]||$incl$(3)).appContextPut$O$O(C$.sharedFrameKey, newRootFrame);
} else {
(I$[3]||$incl$(3)).appContextRemove$O(C$.sharedFrameKey);
}}, 1);

Clazz.newMeth(C$, 'getRootFrame', function () {
var sharedFrame = (I$[3]||$incl$(3)).appContextGet$O(C$.sharedFrameKey);
if (sharedFrame == null ) {
sharedFrame = (I$[3]||$incl$(3)).getSharedOwnerFrame();
(I$[3]||$incl$(3)).appContextPut$O$O(C$.sharedFrameKey, sharedFrame);
}return sharedFrame;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$O.apply(this, ["JOptionPane message"]);
}, 1);

Clazz.newMeth(C$, 'c$$O', function (message) {
C$.c$$O$I.apply(this, [message, -1]);
}, 1);

Clazz.newMeth(C$, 'c$$O$I', function (message, messageType) {
C$.c$$O$I$I.apply(this, [message, messageType, -1]);
}, 1);

Clazz.newMeth(C$, 'c$$O$I$I', function (message, messageType, optionType) {
C$.c$$O$I$I$javax_swing_Icon.apply(this, [message, messageType, optionType, null]);
}, 1);

Clazz.newMeth(C$, 'c$$O$I$I$javax_swing_Icon', function (message, messageType, optionType, icon) {
C$.c$$O$I$I$javax_swing_Icon$OA.apply(this, [message, messageType, optionType, icon, null]);
}, 1);

Clazz.newMeth(C$, 'c$$O$I$I$javax_swing_Icon$OA', function (message, messageType, optionType, icon, options) {
C$.c$$O$I$I$javax_swing_Icon$OA$O.apply(this, [message, messageType, optionType, icon, options, null]);
}, 1);

Clazz.newMeth(C$, 'c$$O$I$I$javax_swing_Icon$OA$O', function (message, messageType, optionType, icon, options, initialValue) {
Clazz.super_(C$, this,1);
this.message = message;
this.options = options;
this.initialValue = initialValue;
this.icon = icon;
this.setMessageType$I(messageType);
this.setOptionType$I(optionType);
this.value = C$.UNINITIALIZED_VALUE;
this.inputValue = C$.UNINITIALIZED_VALUE;
this.uiClassID = "OptionPaneUI";
this.updateUI();
}, 1);

Clazz.newMeth(C$, 'setUI$javax_swing_plaf_OptionPaneUI', function (ui) {
if (this.ui !== ui ) {
C$.superclazz.prototype.setUI$javax_swing_plaf_ComponentUI.apply(this, [ui]);
this.invalidate();
}});

Clazz.newMeth(C$, 'setMessage$O', function (newMessage) {
var oldMessage = this.message;
this.message = newMessage;
this.firePropertyChange$S$O$O("message", oldMessage, this.message);
});

Clazz.newMeth(C$, 'getMessage', function () {
return this.message;
});

Clazz.newMeth(C$, 'setIcon$javax_swing_Icon', function (newIcon) {
var oldIcon = this.icon;
this.icon = newIcon;
this.firePropertyChange$S$O$O("icon", oldIcon, this.icon);
});

Clazz.newMeth(C$, 'getIcon', function () {
return this.icon;
});

Clazz.newMeth(C$, 'setValue$O', function (newValue) {
var oldValue = this.value;
this.value = newValue;
this.firePropertyChange$S$O$O("value", oldValue, this.value);
});

Clazz.newMeth(C$, 'getValue', function () {
return this.value;
});

Clazz.newMeth(C$, 'setOptions$OA', function (newOptions) {
var oldOptions = this.options;
this.options = newOptions;
this.firePropertyChange$S$O$O("options", oldOptions, this.options);
});

Clazz.newMeth(C$, 'getOptions', function () {
if (this.options != null ) {
var optionCount = this.options.length;
var retOptions = Clazz.array(java.lang.Object, [optionCount]);
System.arraycopy(this.options, 0, retOptions, 0, optionCount);
return retOptions;
}return this.options;
});

Clazz.newMeth(C$, 'setInitialValue$O', function (newInitialValue) {
var oldIV = this.initialValue;
this.initialValue = newInitialValue;
this.firePropertyChange$S$O$O("initialValue", oldIV, this.initialValue);
});

Clazz.newMeth(C$, 'getInitialValue', function () {
return this.initialValue;
});

Clazz.newMeth(C$, 'setMessageType$I', function (newType) {
if (newType != 0 && newType != 1  && newType != 2  && newType != 3  && newType != -1 ) throw Clazz.new_(Clazz.load('java.lang.RuntimeException').c$$S,["JOptionPane: type must be one of JOptionPane.ERROR_MESSAGE, JOptionPane.INFORMATION_MESSAGE, JOptionPane.WARNING_MESSAGE, JOptionPane.QUESTION_MESSAGE or JOptionPane.PLAIN_MESSAGE"]);
var oldType = this.messageType;
this.messageType = newType;
this.firePropertyChange$S$I$I("messageType", oldType, this.messageType);
});

Clazz.newMeth(C$, 'getMessageType', function () {
return this.messageType;
});

Clazz.newMeth(C$, 'setOptionType$I', function (newType) {
if (newType != -1 && newType != 0  && newType != 1  && newType != 2 ) throw Clazz.new_(Clazz.load('java.lang.RuntimeException').c$$S,["JOptionPane: option type must be one of JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_CANCEL_OPTION or JOptionPane.OK_CANCEL_OPTION"]);
var oldType = this.optionType;
this.optionType = newType;
this.firePropertyChange$S$I$I("optionType", oldType, this.optionType);
});

Clazz.newMeth(C$, 'getOptionType', function () {
return this.optionType;
});

Clazz.newMeth(C$, 'setSelectionValues$OA', function (newValues) {
var oldValues = this.selectionValues;
this.selectionValues = newValues;
this.firePropertyChange$S$O$O("selectionValues", oldValues, newValues);
if (this.selectionValues != null ) this.setWantsInput$Z(true);
});

Clazz.newMeth(C$, 'getSelectionValues', function () {
return this.selectionValues;
});

Clazz.newMeth(C$, 'setInitialSelectionValue$O', function (newValue) {
var oldValue = this.initialSelectionValue;
this.initialSelectionValue = newValue;
this.firePropertyChange$S$O$O("initialSelectionValue", oldValue, newValue);
});

Clazz.newMeth(C$, 'getInitialSelectionValue', function () {
return this.initialSelectionValue;
});

Clazz.newMeth(C$, 'setInputValue$O', function (newValue) {
var oldValue = this.inputValue;
this.inputValue = newValue;
this.firePropertyChange$S$O$O("inputValue", oldValue, newValue);
});

Clazz.newMeth(C$, 'getInputValue', function () {
return this.inputValue;
});

Clazz.newMeth(C$, 'getMaxCharactersPerLineCount', function () {
return 2147483647;
});

Clazz.newMeth(C$, 'setWantsInput$Z', function (newValue) {
var oldValue = this.wantsInput;
this.wantsInput = newValue;
this.firePropertyChange$S$Z$Z("wantsInput", oldValue, newValue);
});

Clazz.newMeth(C$, 'getWantsInput', function () {
return this.wantsInput;
});

Clazz.newMeth(C$, 'selectInitialValue', function () {
var ui = this.getUI();
if (ui != null ) {
ui.selectInitialValue$javax_swing_JOptionPane(this);
}});

Clazz.newMeth(C$, 'styleFromMessageType$I', function (messageType) {
switch (messageType) {
case 0:
return 4;
case 3:
return 7;
case 2:
return 8;
case 1:
return 3;
case -1:
default:
return 2;
}
}, 1);

Clazz.newMeth(C$, 'paramString', function () {
var iconString = (this.icon != null  ? this.icon.toString() : "");
var initialValueString = (this.initialValue != null  ? this.initialValue.toString() : "");
var messageString = (this.message != null  ? this.message.toString() : "");
var messageTypeString = C$.getMessageTypeString$I$S(this.messageType, "_MESSAGE");
var optionTypeString;
if (this.optionType == -1) {
optionTypeString = "DEFAULT_OPTION";
} else if (this.optionType == 0) {
optionTypeString = "YES_NO_OPTION";
} else if (this.optionType == 1) {
optionTypeString = "YES_NO_CANCEL_OPTION";
} else if (this.optionType == 2) {
optionTypeString = "OK_CANCEL_OPTION";
} else optionTypeString = "";
var wantsInputString = (this.wantsInput ? "true" : "false");
return C$.superclazz.prototype.paramString.apply(this, []) + ",icon=" + iconString + ",initialValue=" + initialValueString + ",message=" + messageString + ",messageType=" + messageTypeString + ",optionType=" + optionTypeString + ",wantsInput=" + wantsInputString ;
});

Clazz.newMeth(C$, 'getMessageTypeString$I$S', function (messageType, trailer) {
var s;
switch (messageType) {
case 0:
s = "ERROR";
break;
case 1:
s = (trailer == "_MESSAGE" ? "INFORMATION" : "");
break;
case 2:
s = "WARNING";
break;
case 3:
s = (trailer == "_MESSAGE" ? "QUESTION" : "");
break;
case -1:
s = (trailer == "_MESSAGE" ? "PLAIN" : "");
break;
default:
s = "";
break;
}
return (s == "" ? "" : s + trailer);
}, 1);
})();
//Created 2018-05-15 01:02:31
