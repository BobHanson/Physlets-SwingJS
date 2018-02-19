(function(){var P$=Clazz.newPackage("javax.swing"),I$=[[['javax.swing.DefaultCellEditor','.EditorDelegate'],'Boolean','java.lang.Boolean','java.awt.event.ActionEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DefaultCellEditor", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.AbstractCellEditor', ['javax.swing.table.TableCellEditor', 'javax.swing.tree.TreeCellEditor']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.editorComponent = null;
this.delegate = null;
this.clickCountToStart = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.clickCountToStart = 1;
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JTextField', function (textField) {
Clazz.super_(C$, this,1);
this.editorComponent = textField;
this.clickCountToStart = 2;
this.delegate = ((
(function(){var C$=Clazz.newClass(P$, "DefaultCellEditor$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load(['javax.swing.DefaultCellEditor','.EditorDelegate']), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'setValue$O', function (value) {
this.$finals.textField.setText$S((value != null ) ? value.toString() : "");
});

Clazz.newMeth(C$, 'getCellEditorValue', function () {
return this.$finals.textField.getText();
});
})()
), Clazz.new_((I$[1]||$incl$(1)), [this, {textField: textField}],P$.DefaultCellEditor$1));
textField.addActionListener$java_awt_event_ActionListener(this.delegate);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JCheckBox', function (checkBox) {
Clazz.super_(C$, this,1);
this.editorComponent = checkBox;
this.delegate = ((
(function(){var C$=Clazz.newClass(P$, "DefaultCellEditor$2", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load(['javax.swing.DefaultCellEditor','.EditorDelegate']), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'setValue$O', function (value) {
var selected = false;
if (Clazz.instanceOf(value, "java.lang.Boolean")) {
selected = (value).booleanValue();
} else if (Clazz.instanceOf(value, "java.lang.String")) {
selected = value.equals$O("true");
}this.$finals.checkBox.setSelected$Z(selected);
});

Clazz.newMeth(C$, 'getCellEditorValue', function () {
return (I$[2]||$incl$(2)).$valueOf(this.$finals.checkBox.isSelected());
});
})()
), Clazz.new_((I$[1]||$incl$(1)), [this, {checkBox: checkBox}],P$.DefaultCellEditor$2));
checkBox.addActionListener$java_awt_event_ActionListener(this.delegate);
checkBox.setRequestFocusEnabled$Z(false);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JComboBox', function (comboBox) {
Clazz.super_(C$, this,1);
this.editorComponent = comboBox;
comboBox.putClientProperty$O$O("JComboBox.isTableCellEditor", (I$[3]||$incl$(3)).TRUE);
this.delegate = ((
(function(){var C$=Clazz.newClass(P$, "DefaultCellEditor$3", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load(['javax.swing.DefaultCellEditor','.EditorDelegate']), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'setValue$O', function (value) {
this.$finals.comboBox.setSelectedItem$O(value);
});

Clazz.newMeth(C$, 'getCellEditorValue', function () {
return this.$finals.comboBox.getSelectedItem();
});

Clazz.newMeth(C$, 'shouldSelectCell$java_util_EventObject', function (anEvent) {
if (Clazz.instanceOf(anEvent, "java.awt.event.MouseEvent")) {
var e = anEvent;
return e.getID() != 506;
}return true;
});

Clazz.newMeth(C$, 'stopCellEditing', function () {
if (this.$finals.comboBox.isEditable()) {
this.$finals.comboBox.actionPerformed$java_awt_event_ActionEvent(Clazz.new_((I$[4]||$incl$(4)).c$$O$I$S,[this.b$['javax.swing.DefaultCellEditor'], 0, ""]));
}return C$.superclazz.prototype.stopCellEditing.apply(this, []);
});
})()
), Clazz.new_((I$[1]||$incl$(1)), [this, {comboBox: comboBox}],P$.DefaultCellEditor$3));
comboBox.addActionListener$java_awt_event_ActionListener(this.delegate);
}, 1);

Clazz.newMeth(C$, 'getComponent', function () {
return this.editorComponent;
});

Clazz.newMeth(C$, 'setClickCountToStart$I', function (count) {
this.clickCountToStart = count;
});

Clazz.newMeth(C$, 'getClickCountToStart', function () {
return this.clickCountToStart;
});

Clazz.newMeth(C$, 'getCellEditorValue', function () {
return this.delegate.getCellEditorValue();
});

Clazz.newMeth(C$, 'isCellEditable$java_util_EventObject', function (anEvent) {
return this.delegate.isCellEditable$java_util_EventObject(anEvent);
});

Clazz.newMeth(C$, 'shouldSelectCell$java_util_EventObject', function (anEvent) {
return this.delegate.shouldSelectCell$java_util_EventObject(anEvent);
});

Clazz.newMeth(C$, 'stopCellEditing', function () {
return this.delegate.stopCellEditing();
});

Clazz.newMeth(C$, 'cancelCellEditing', function () {
this.delegate.cancelCellEditing();
});

Clazz.newMeth(C$, 'getTreeCellEditorComponent$javax_swing_JTree$O$Z$Z$Z$I', function (tree, value, isSelected, expanded, leaf, row) {
var stringValue = tree.convertValueToText$O$Z$Z$Z$I$Z(value, isSelected, expanded, leaf, row, false);
this.delegate.setValue$O(stringValue);
return this.editorComponent;
});

Clazz.newMeth(C$, 'getTableCellEditorComponent$javax_swing_JTable$O$Z$I$I', function (table, value, isSelected, row, column) {
this.delegate.setValue$O(value);
if (Clazz.instanceOf(this.editorComponent, "javax.swing.JCheckBox")) {
var renderer = table.getCellRenderer$I$I(row, column);
var c = renderer.getTableCellRendererComponent$javax_swing_JTable$O$Z$Z$I$I(table, value, isSelected, true, row, column);
if (c != null ) {
this.editorComponent.setOpaque$Z(true);
this.editorComponent.setBackground$java_awt_Color(c.getBackground());
if (Clazz.instanceOf(c, "javax.swing.JComponent")) {
this.editorComponent.setBorder$javax_swing_border_Border((c).getBorder());
}} else {
this.editorComponent.setOpaque$Z(false);
}}return this.editorComponent;
});
;
(function(){var C$=Clazz.newClass(P$.DefaultCellEditor, "EditorDelegate", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, ['java.awt.event.ActionListener', 'java.awt.event.ItemListener']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.value = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getCellEditorValue', function () {
return this.value;
});

Clazz.newMeth(C$, 'setValue$O', function (value) {
this.value = value;
});

Clazz.newMeth(C$, 'isCellEditable$java_util_EventObject', function (anEvent) {
if (Clazz.instanceOf(anEvent, "java.awt.event.MouseEvent")) {
return (anEvent).getClickCount() >= this.this$0.clickCountToStart;
}return true;
});

Clazz.newMeth(C$, 'shouldSelectCell$java_util_EventObject', function (anEvent) {
return true;
});

Clazz.newMeth(C$, 'startCellEditing$java_util_EventObject', function (anEvent) {
return true;
});

Clazz.newMeth(C$, 'stopCellEditing', function () {
this.this$0.fireEditingStopped();
return true;
});

Clazz.newMeth(C$, 'cancelCellEditing', function () {
this.this$0.fireEditingCanceled();
});

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.this$0.stopCellEditing();
});

Clazz.newMeth(C$, 'itemStateChanged$java_awt_event_ItemEvent', function (e) {
this.this$0.stopCellEditing();
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:25
