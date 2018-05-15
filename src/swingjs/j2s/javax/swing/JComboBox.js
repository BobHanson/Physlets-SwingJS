(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['javax.swing.AbstractAction','javax.swing.JPopupMenu','javax.swing.DefaultComboBoxModel','javax.swing.JComboBox$1','javax.swing.SwingUtilities','java.awt.event.ItemListener','java.awt.event.ActionListener','javax.swing.event.PopupMenuListener','javax.swing.event.PopupMenuEvent',['javax.swing.JComboBox','.ComboBoxActionPropertyChangeListener'],'java.awt.EventQueue','java.awt.event.ActionEvent','java.awt.event.ItemEvent',['javax.swing.JComboBox','.DefaultKeySelectionManager']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JComboBox", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.JComponent', ['java.awt.ItemSelectable', 'javax.swing.event.ListDataListener', 'java.awt.event.ActionListener']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
{

this.c$$TEA = C$.c$$OA;
this.prototype.setPrototypeDisplayValue$TE = p$.setPrototypeDisplayValue$O;
this.prototype.addItem$TE = p$.addItem$O;
this.prototype.insertItemAt$TE$I =  p$.insertItemAt$O$I;
}
;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.dataModel = null;
this.renderer = null;
this.editor = null;
this.maximumRowCount = 0;
this.$isEditable = false;
this.keySelectionManager = null;
this.actionCommand = null;
this.lightWeightPopupEnabled = false;
this.selectedItemReminder = null;
this.prototypeDisplayValue = null;
this.firingActionEvent = false;
this.selectingItem = false;
this.$action = null;
this.actionPropertyChangeListener = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.maximumRowCount = 8;
this.$isEditable = false;
this.keySelectionManager = null;
this.actionCommand = "comboBoxChanged";
this.lightWeightPopupEnabled = (I$[2]||$incl$(2)).getDefaultLightWeightPopupEnabled();
this.selectedItemReminder = null;
this.firingActionEvent = false;
this.selectingItem = false;
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_ComboBoxModel', function (aModel) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.setModel$javax_swing_ComboBoxModel(aModel);
p$.initComboBox.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'c$$OA', function (items) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.setModel$javax_swing_ComboBoxModel(Clazz.new_((I$[3]||$incl$(3)).c$$OA,[items]));
p$.initComboBox.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Vector', function (items) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.setModel$javax_swing_ComboBoxModel(Clazz.new_((I$[3]||$incl$(3)).c$$java_util_Vector,[items]));
p$.initComboBox.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.setModel$javax_swing_ComboBoxModel(Clazz.new_((I$[3]||$incl$(3))));
p$.initComboBox.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'initComboBox', function () {
this.installAncestorListener();
this.uiClassID = "ComboBoxUI";
this.setUIProperty$S$O("opaque", new Boolean(true));
this.updateUI();
});

Clazz.newMeth(C$, 'installAncestorListener', function () {
this.addAncestorListener$javax_swing_event_AncestorListener(((
(function(){var C$=Clazz.newClass(P$, "JComboBox$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'javax.swing.event.AncestorListener', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'ancestorAdded$javax_swing_event_AncestorEvent', function (event) {
this.b$['javax.swing.JComboBox'].hidePopup();
});

Clazz.newMeth(C$, 'ancestorRemoved$javax_swing_event_AncestorEvent', function (event) {
this.b$['javax.swing.JComboBox'].hidePopup();
});

Clazz.newMeth(C$, 'ancestorMoved$javax_swing_event_AncestorEvent', function (event) {
if (event.getSource() !== this.b$['javax.swing.JComboBox'] ) this.b$['javax.swing.JComboBox'].hidePopup();
});
})()
), Clazz.new_((I$[4]||$incl$(4)).$init$, [this, null])));
});

Clazz.newMeth(C$, 'updateUI', function () {
C$.superclazz.prototype.updateUI.apply(this, []);
var renderer = this.getRenderer();
if (Clazz.instanceOf(renderer, "java.awt.Component")) {
(I$[5]||$incl$(5)).updateComponentTreeUI$java_awt_Component(renderer);
}});

Clazz.newMeth(C$, 'setModel$javax_swing_ComboBoxModel', function (aModel) {
var oldModel = this.dataModel;
if (oldModel != null ) {
oldModel.removeListDataListener$javax_swing_event_ListDataListener(this);
}this.dataModel = aModel;
this.dataModel.addListDataListener$javax_swing_event_ListDataListener(this);
this.selectedItemReminder = this.dataModel.getSelectedItem();
this.firePropertyChange$S$O$O("model", oldModel, this.dataModel);
});

Clazz.newMeth(C$, 'getModel', function () {
return this.dataModel;
});

Clazz.newMeth(C$, 'setLightWeightPopupEnabled$Z', function (aFlag) {
var oldFlag = this.lightWeightPopupEnabled;
this.lightWeightPopupEnabled = aFlag;
this.firePropertyChange$S$Z$Z("lightWeightPopupEnabled", oldFlag, this.lightWeightPopupEnabled);
});

Clazz.newMeth(C$, 'isLightWeightPopupEnabled', function () {
return this.lightWeightPopupEnabled;
});

Clazz.newMeth(C$, 'setEditable$Z', function (aFlag) {
var oldFlag = this.$isEditable;
this.$isEditable = aFlag;
this.firePropertyChange$S$Z$Z("editable", oldFlag, this.$isEditable);
});

Clazz.newMeth(C$, 'isEditable', function () {
return this.$isEditable;
});

Clazz.newMeth(C$, 'setMaximumRowCount$I', function (count) {
var oldCount = this.maximumRowCount;
this.maximumRowCount = count;
this.firePropertyChange$S$I$I("maximumRowCount", oldCount, this.maximumRowCount);
});

Clazz.newMeth(C$, 'getMaximumRowCount', function () {
return this.maximumRowCount;
});

Clazz.newMeth(C$, 'setRenderer$javax_swing_ListCellRenderer', function (aRenderer) {
var oldRenderer = this.renderer;
this.renderer = aRenderer;
this.firePropertyChange$S$O$O("renderer", oldRenderer, this.renderer);
this.invalidate();
});

Clazz.newMeth(C$, 'getRenderer', function () {
return this.renderer;
});

Clazz.newMeth(C$, 'setEditor$javax_swing_ComboBoxEditor', function (anEditor) {
var oldEditor = this.editor;
if (this.editor != null ) {
this.editor.removeActionListener$java_awt_event_ActionListener(this);
}this.editor = anEditor;
if (this.editor != null ) {
this.editor.addActionListener$java_awt_event_ActionListener(this);
}this.firePropertyChange$S$O$O("editor", oldEditor, this.editor);
});

Clazz.newMeth(C$, 'getEditor', function () {
return this.editor;
});

Clazz.newMeth(C$, 'setSelectedItem$O', function (anObject) {
var oldSelection = this.selectedItemReminder;
var objectToSelect = anObject;
if (oldSelection == null  || !oldSelection.equals$O(anObject) ) {
if (anObject != null  && !this.isEditable() ) {
var found = false;
for (var i = 0; i < this.dataModel.getSize(); i++) {
var element = this.dataModel.getElementAt$I(i);
if (anObject.equals$O(element)) {
found = true;
objectToSelect = element;
break;
}}
if (!found) {
return;
}}this.selectingItem = true;
this.dataModel.setSelectedItem$O(objectToSelect);
this.selectingItem = false;
if (this.selectedItemReminder !== this.dataModel.getSelectedItem() ) {
this.selectedItemChanged();
}}this.fireActionEvent();
});

Clazz.newMeth(C$, 'getSelectedItem', function () {
return this.dataModel.getSelectedItem();
});

Clazz.newMeth(C$, 'setSelectedIndex$I', function (anIndex) {
var size = this.dataModel.getSize();
if (anIndex == -1) {
this.setSelectedItem$O(null);
} else if (anIndex < -1 || anIndex >= size ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["setSelectedIndex: " + anIndex + " out of bounds" ]);
} else {
this.setSelectedItem$O(this.dataModel.getElementAt$I(anIndex));
}});

Clazz.newMeth(C$, 'getSelectedIndex', function () {
var sObject = this.dataModel.getSelectedItem();
var i;
var c;
var obj;
for (i = 0, c = this.dataModel.getSize(); i < c; i++) {
obj = this.dataModel.getElementAt$I(i);
if (obj != null  && obj.equals$O(sObject) ) return i;
}
return -1;
});

Clazz.newMeth(C$, 'getPrototypeDisplayValue', function () {
return this.prototypeDisplayValue;
});

Clazz.newMeth(C$, 'setPrototypeDisplayValue$O', function (prototypeDisplayValue) {
var oldValue = this.prototypeDisplayValue;
this.prototypeDisplayValue = prototypeDisplayValue;
this.firePropertyChange$S$O$O("prototypeDisplayValue", oldValue, prototypeDisplayValue);
});

Clazz.newMeth(C$, 'addItem$O', function (anObject) {
this.checkMutableComboBoxModel();
(this.dataModel).addElement$O(anObject);
});

Clazz.newMeth(C$, 'insertItemAt$O$I', function (anObject, index) {
this.checkMutableComboBoxModel();
(this.dataModel).insertElementAt$O$I(anObject, index);
});

Clazz.newMeth(C$, 'removeItem$O', function (anObject) {
this.checkMutableComboBoxModel();
(this.dataModel).removeElement$O(anObject);
});

Clazz.newMeth(C$, 'removeItemAt$I', function (anIndex) {
this.checkMutableComboBoxModel();
(this.dataModel).removeElementAt$I(anIndex);
});

Clazz.newMeth(C$, 'removeAllItems', function () {
this.checkMutableComboBoxModel();
var model = this.dataModel;
var size = model.getSize();
if (Clazz.instanceOf(model, "javax.swing.DefaultComboBoxModel")) {
(model).removeAllElements();
} else {
for (var i = 0; i < size; ++i) {
var element = model.getElementAt$I(0);
model.removeElement$O(element);
}
}this.selectedItemReminder = null;
if (this.isEditable()) {
this.editor.setItem$O(null);
}});

Clazz.newMeth(C$, 'checkMutableComboBoxModel', function () {
if (!(Clazz.instanceOf(this.dataModel, "javax.swing.MutableComboBoxModel"))) throw Clazz.new_(Clazz.load('java.lang.RuntimeException').c$$S,["Cannot use this method with a non-Mutable data model."]);
});

Clazz.newMeth(C$, 'showPopup', function () {
this.setPopupVisible$Z(true);
});

Clazz.newMeth(C$, 'hidePopup', function () {
this.setPopupVisible$Z(false);
});

Clazz.newMeth(C$, 'setPopupVisible$Z', function (v) {
(this.getUI()).setPopupVisible$javax_swing_JComboBox$Z(this, v);
});

Clazz.newMeth(C$, 'isPopupVisible', function () {
return (this.getUI()).isPopupVisible$javax_swing_JComboBox(this);
});

Clazz.newMeth(C$, 'addItemListener$java_awt_event_ItemListener', function (aListener) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[6]||$incl$(6)),['itemStateChanged$java_awt_event_ItemEvent']), aListener);
});

Clazz.newMeth(C$, 'removeItemListener$java_awt_event_ItemListener', function (aListener) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[6]||$incl$(6)),['itemStateChanged$java_awt_event_ItemEvent']), aListener);
});

Clazz.newMeth(C$, 'getItemListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[6]||$incl$(6)),['itemStateChanged$java_awt_event_ItemEvent']));
});

Clazz.newMeth(C$, 'addActionListener$java_awt_event_ActionListener', function (l) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[7]||$incl$(7)),['actionPerformed$java_awt_event_ActionEvent']), l);
});

Clazz.newMeth(C$, 'removeActionListener$java_awt_event_ActionListener', function (l) {
if ((l != null ) && (this.getAction() === l ) ) {
this.setAction$javax_swing_Action(null);
} else {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[7]||$incl$(7)),['actionPerformed$java_awt_event_ActionEvent']), l);
}});

Clazz.newMeth(C$, 'getActionListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[7]||$incl$(7)),['actionPerformed$java_awt_event_ActionEvent']));
});

Clazz.newMeth(C$, 'addPopupMenuListener$javax_swing_event_PopupMenuListener', function (l) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[8]||$incl$(8)),['popupMenuCanceled$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeInvisible$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeVisible$javax_swing_event_PopupMenuEvent']), l);
});

Clazz.newMeth(C$, 'removePopupMenuListener$javax_swing_event_PopupMenuListener', function (l) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[8]||$incl$(8)),['popupMenuCanceled$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeInvisible$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeVisible$javax_swing_event_PopupMenuEvent']), l);
});

Clazz.newMeth(C$, 'getPopupMenuListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[8]||$incl$(8)),['popupMenuCanceled$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeInvisible$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeVisible$javax_swing_event_PopupMenuEvent']));
});

Clazz.newMeth(C$, 'firePopupMenuWillBecomeVisible', function () {
var listeners = this.listenerList.getListenerList();
var e = null;
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[8]||$incl$(8)),['popupMenuCanceled$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeInvisible$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeVisible$javax_swing_event_PopupMenuEvent']) ) {
if (e == null ) e = Clazz.new_((I$[9]||$incl$(9)).c$$O,[this]);
(listeners[i + 1]).popupMenuWillBecomeVisible$javax_swing_event_PopupMenuEvent(e);
}}
});

Clazz.newMeth(C$, 'firePopupMenuWillBecomeInvisible', function () {
var listeners = this.listenerList.getListenerList();
var e = null;
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[8]||$incl$(8)),['popupMenuCanceled$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeInvisible$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeVisible$javax_swing_event_PopupMenuEvent']) ) {
if (e == null ) e = Clazz.new_((I$[9]||$incl$(9)).c$$O,[this]);
(listeners[i + 1]).popupMenuWillBecomeInvisible$javax_swing_event_PopupMenuEvent(e);
}}
});

Clazz.newMeth(C$, 'firePopupMenuCanceled', function () {
var listeners = this.listenerList.getListenerList();
var e = null;
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[8]||$incl$(8)),['popupMenuCanceled$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeInvisible$javax_swing_event_PopupMenuEvent','popupMenuWillBecomeVisible$javax_swing_event_PopupMenuEvent']) ) {
if (e == null ) e = Clazz.new_((I$[9]||$incl$(9)).c$$O,[this]);
(listeners[i + 1]).popupMenuCanceled$javax_swing_event_PopupMenuEvent(e);
}}
});

Clazz.newMeth(C$, 'setActionCommand$S', function (aCommand) {
this.actionCommand = aCommand;
});

Clazz.newMeth(C$, 'getActionCommand', function () {
return this.actionCommand;
});

Clazz.newMeth(C$, 'setAction$javax_swing_Action', function (a) {
var oldValue = this.getAction();
if (this.$action == null  || !this.$action.equals$O(a) ) {
this.$action = a;
if (oldValue != null ) {
this.removeActionListener$java_awt_event_ActionListener(oldValue);
oldValue.removePropertyChangeListener$java_beans_PropertyChangeListener(this.actionPropertyChangeListener);
this.actionPropertyChangeListener = null;
}this.configurePropertiesFromAction$javax_swing_Action(this.$action);
if (this.$action != null ) {
if (!p$.isListener$Class$java_awt_event_ActionListener.apply(this, [Clazz.getClass((I$[7]||$incl$(7)),['actionPerformed$java_awt_event_ActionEvent']), this.$action])) {
this.addActionListener$java_awt_event_ActionListener(this.$action);
}this.actionPropertyChangeListener = this.createActionPropertyChangeListener$javax_swing_Action(this.$action);
this.$action.addPropertyChangeListener$java_beans_PropertyChangeListener(this.actionPropertyChangeListener);
}this.firePropertyChange$S$O$O("action", oldValue, this.$action);
}});

Clazz.newMeth(C$, 'isListener$Class$java_awt_event_ActionListener', function (c, a) {
var isListener = false;
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === c  && listeners[i + 1] === a  ) {
isListener = true;
}}
return isListener;
});

Clazz.newMeth(C$, 'getAction', function () {
return this.$action;
});

Clazz.newMeth(C$, 'configurePropertiesFromAction$javax_swing_Action', function (a) {
(I$[1]||$incl$(1)).setEnabledFromAction$javax_swing_JComponent$javax_swing_Action(this, a);
(I$[1]||$incl$(1)).setToolTipTextFromAction$javax_swing_JComponent$javax_swing_Action(this, a);
p$.setActionCommandFromAction$javax_swing_Action.apply(this, [a]);
});

Clazz.newMeth(C$, 'createActionPropertyChangeListener$javax_swing_Action', function (a) {
return Clazz.new_((I$[10]||$incl$(10)).c$$javax_swing_JComboBox$javax_swing_Action,[this, a]);
});

Clazz.newMeth(C$, 'actionPropertyChanged$javax_swing_Action$S', function (action, propertyName) {
if (propertyName == "ActionCommandKey") {
p$.setActionCommandFromAction$javax_swing_Action.apply(this, [action]);
} else if (propertyName == "enabled") {
(I$[1]||$incl$(1)).setEnabledFromAction$javax_swing_JComponent$javax_swing_Action(this, action);
} else if ("ShortDescription" == propertyName) {
(I$[1]||$incl$(1)).setToolTipTextFromAction$javax_swing_JComponent$javax_swing_Action(this, action);
}});

Clazz.newMeth(C$, 'setActionCommandFromAction$javax_swing_Action', function (a) {
this.setActionCommand$S((a != null ) ? a.getValue$S("ActionCommandKey") : null);
});

Clazz.newMeth(C$, 'fireItemStateChanged$java_awt_event_ItemEvent', function (e) {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[6]||$incl$(6)),['itemStateChanged$java_awt_event_ItemEvent']) ) {
(listeners[i + 1]).itemStateChanged$java_awt_event_ItemEvent(e);
}}
});

Clazz.newMeth(C$, 'fireActionEvent', function () {
if (!this.firingActionEvent) {
this.firingActionEvent = true;
var e = null;
var listeners = this.listenerList.getListenerList();
var mostRecentEventTime = (I$[11]||$incl$(11)).getMostRecentEventTime();
var modifiers = 0;
var currentEvent = (I$[11]||$incl$(11)).getCurrentEvent();
if (Clazz.instanceOf(currentEvent, "java.awt.event.InputEvent")) {
modifiers = (currentEvent).getModifiers();
} else if (Clazz.instanceOf(currentEvent, "java.awt.event.ActionEvent")) {
modifiers = (currentEvent).getModifiers();
}for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[7]||$incl$(7)),['actionPerformed$java_awt_event_ActionEvent']) ) {
if (e == null ) e = Clazz.new_((I$[12]||$incl$(12)).c$$O$I$S$J$I,[this, 1001, this.getActionCommand(), mostRecentEventTime, modifiers]);
(listeners[i + 1]).actionPerformed$java_awt_event_ActionEvent(e);
}}
this.firingActionEvent = false;
}});

Clazz.newMeth(C$, 'selectedItemChanged', function () {
if (this.selectedItemReminder != null ) {
this.fireItemStateChanged$java_awt_event_ItemEvent(Clazz.new_((I$[13]||$incl$(13)).c$$java_awt_ItemSelectable$I$O$I,[this, 701, this.selectedItemReminder, 2]));
}this.selectedItemReminder = this.dataModel.getSelectedItem();
if (this.selectedItemReminder != null ) {
this.fireItemStateChanged$java_awt_event_ItemEvent(Clazz.new_((I$[13]||$incl$(13)).c$$java_awt_ItemSelectable$I$O$I,[this, 701, this.selectedItemReminder, 1]));
}});

Clazz.newMeth(C$, 'getSelectedObjects', function () {
var selectedObject = this.getSelectedItem();
if (selectedObject == null ) return Clazz.array(java.lang.Object, [0]);
 else {
var result = Clazz.array(java.lang.Object, [1]);
result[0] = selectedObject;
return result;
}});

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var newItem = this.getEditor().getItem();
this.setPopupVisible$Z(false);
this.getModel().setSelectedItem$O(newItem);
var oldCommand = this.getActionCommand();
this.setActionCommand$S("comboBoxEdited");
this.fireActionEvent();
this.setActionCommand$S(oldCommand);
});

Clazz.newMeth(C$, 'contentsChanged$javax_swing_event_ListDataEvent', function (e) {
var oldSelection = this.selectedItemReminder;
var newSelection = this.dataModel.getSelectedItem();
if (oldSelection == null  || !oldSelection.equals$O(newSelection) ) {
this.selectedItemChanged();
if (!this.selectingItem) {
this.fireActionEvent();
}}});

Clazz.newMeth(C$, 'intervalAdded$javax_swing_event_ListDataEvent', function (e) {
if (this.selectedItemReminder !== this.dataModel.getSelectedItem() ) {
this.selectedItemChanged();
}});

Clazz.newMeth(C$, 'intervalRemoved$javax_swing_event_ListDataEvent', function (e) {
this.contentsChanged$javax_swing_event_ListDataEvent(e);
});

Clazz.newMeth(C$, 'selectWithKeyChar$C', function (keyChar) {
var index;
if (this.keySelectionManager == null ) this.keySelectionManager = this.createDefaultKeySelectionManager();
index = this.keySelectionManager.selectionForKey$C$javax_swing_ComboBoxModel(keyChar, this.getModel());
if (index != -1) {
this.setSelectedIndex$I(index);
return true;
} else return false;
});

Clazz.newMeth(C$, 'setEnabled$Z', function (b) {
C$.superclazz.prototype.setEnabled$Z.apply(this, [b]);
this.firePropertyChange$S$Z$Z("enabled", !this.isEnabled(), this.isEnabled());
});

Clazz.newMeth(C$, 'configureEditor$javax_swing_ComboBoxEditor$O', function (anEditor, anItem) {
anEditor.setItem$O(anItem);
});

Clazz.newMeth(C$, 'processKeyEvent$java_awt_event_KeyEvent', function (e) {
if (e.getKeyCode() == 9) {
this.hidePopup();
}C$.superclazz.prototype.processKeyEvent$java_awt_event_KeyEvent.apply(this, [e]);
});

Clazz.newMeth(C$, 'setKeySelectionManager$javax_swing_JComboBox_KeySelectionManager', function (aManager) {
this.keySelectionManager = aManager;
});

Clazz.newMeth(C$, 'getKeySelectionManager', function () {
return this.keySelectionManager;
});

Clazz.newMeth(C$, 'getItemCount', function () {
return this.dataModel.getSize();
});

Clazz.newMeth(C$, 'getItemAt$I', function (index) {
return this.dataModel.getElementAt$I(index);
});

Clazz.newMeth(C$, 'createDefaultKeySelectionManager', function () {
return Clazz.new_((I$[14]||$incl$(14)), [this, null]);
});

Clazz.newMeth(C$, 'paramString', function () {
var selectedItemReminderString = (this.selectedItemReminder != null  ? this.selectedItemReminder.toString() : "");
var isEditableString = (this.$isEditable ? "true" : "false");
var lightWeightPopupEnabledString = (this.lightWeightPopupEnabled ? "true" : "false");
return C$.superclazz.prototype.paramString.apply(this, []) + ",isEditable=" + isEditableString + ",lightWeightPopupEnabled=" + lightWeightPopupEnabledString + ",maximumRowCount=" + this.maximumRowCount + ",selectedItemReminder=" + selectedItemReminderString ;
});
;
(function(){var C$=Clazz.newClass(P$.JComboBox, "ComboBoxActionPropertyChangeListener", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.ActionPropertyChangeListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JComboBox$javax_swing_Action', function (b, a) {
C$.superclazz.c$$TT$javax_swing_Action.apply(this, [b, a]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, ['actionPropertyChanged$javax_swing_JComboBox$javax_swing_Action$java_beans_PropertyChangeEvent','actionPropertyChanged$TT$javax_swing_Action$java_beans_PropertyChangeEvent'], function (cb, action, e) {
if ((I$[1]||$incl$(1)).shouldReconfigure$java_beans_PropertyChangeEvent(e)) {
cb.configurePropertiesFromAction$javax_swing_Action(action);
} else {
cb.actionPropertyChanged$javax_swing_Action$S(action, e.getPropertyName());
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newInterface(P$.JComboBox, "KeySelectionManager", function(){
});
})()
;
(function(){var C$=Clazz.newClass(P$.JComboBox, "DefaultKeySelectionManager", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, [['javax.swing.JComboBox','javax.swing.JComboBox.KeySelectionManager']]);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'selectionForKey$C$javax_swing_ComboBoxModel', function (aKey, aModel) {
var i;
var c;
var currentSelection = -1;
var selectedItem = aModel.getSelectedItem();
var v;
var pattern;
if (selectedItem != null ) {
for (i = 0, c = aModel.getSize(); i < c; i++) {
if (selectedItem === aModel.getElementAt$I(i) ) {
currentSelection = i;
break;
}}
}pattern = ("" + aKey).toLowerCase();
aKey = pattern.charAt(0);
for (i = ++currentSelection, c = aModel.getSize(); i < c; i++) {
var elem = aModel.getElementAt$I(i);
if (elem != null  && elem.toString() != null  ) {
v = elem.toString().toLowerCase();
if (v.length$() > 0 && v.charAt(0) == aKey ) return i;
}}
for (i = 0; i < currentSelection; i++) {
var elem = aModel.getElementAt$I(i);
if (elem != null  && elem.toString() != null  ) {
v = elem.toString().toLowerCase();
if (v.length$() > 0 && v.charAt(0) == aKey ) return i;
}}
return -1;
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-15 01:02:26
