(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['javax.swing.text.DocumentFilter','javax.swing.Action',['javax.swing.JFormattedTextField','.CommitAction'],['javax.swing.JFormattedTextField','.CancelAction'],'javax.swing.text.DefaultFormatterFactory','Boolean',['javax.swing.JFormattedTextField','.FocusLostHandler'],'javax.swing.text.TextAction',['javax.swing.JFormattedTextField','.DocumentHandler'],'javax.swing.ActionMap','swingjs.JSUtil','javax.swing.text.NumberFormatter','javax.swing.text.InternationalFormatter','javax.swing.text.DateFormatter','java.text.DecimalFormat','javax.swing.text.DefaultFormatter']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JFormattedTextField", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.JTextField');
C$.$defaultActions = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.$defaultActions = Clazz.array((I$[2]||$incl$(2)), -1, [Clazz.new_((I$[3]||$incl$(3))), Clazz.new_((I$[4]||$incl$(4)))]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.factory = null;
this.format = null;
this.value = null;
this.editValid = false;
this.focusLostBehavior = 0;
this.edited = false;
this.documentListener = null;
this.textFormatterActionMap = null;
this.focusLostHandler = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$javax_swing_text_Document$S$I.apply(this, [null, null, 0]);
C$.$init$.apply(this);
this.uiClassID="FormattedTextFieldUI";
this.updateUI();
this.enableEvents$J(4);
this.setFocusLostBehavior$I(1);
}, 1);

Clazz.newMeth(C$, 'c$$O', function (value) {
C$.c$.apply(this, []);
this.setValue$O(value);
}, 1);

Clazz.newMeth(C$, 'c$$java_text_Format', function (format) {
C$.c$.apply(this, []);
this.setFormatterFactory$javax_swing_JFormattedTextField_AbstractFormatterFactory(p$.getDefaultFormatterFactory$O.apply(this, [format]));
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JFormattedTextField_AbstractFormatter', function (formatter) {
C$.c$$javax_swing_JFormattedTextField_AbstractFormatterFactory.apply(this, [Clazz.new_((I$[5]||$incl$(5)).c$$javax_swing_JFormattedTextField_AbstractFormatter,[formatter])]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JFormattedTextField_AbstractFormatterFactory', function (factory) {
C$.c$.apply(this, []);
this.setFormatterFactory$javax_swing_JFormattedTextField_AbstractFormatterFactory(factory);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JFormattedTextField_AbstractFormatterFactory$O', function (factory, currentValue) {
C$.c$$O.apply(this, [currentValue]);
this.setFormatterFactory$javax_swing_JFormattedTextField_AbstractFormatterFactory(factory);
}, 1);

Clazz.newMeth(C$, 'setFocusLostBehavior$I', function (behavior) {
if (behavior != 0 && behavior != 1  && behavior != 3  && behavior != 2 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["setFocusLostBehavior must be one of: JFormattedTextField.COMMIT, JFormattedTextField.COMMIT_OR_REVERT, JFormattedTextField.PERSIST or JFormattedTextField.REVERT"]);
}this.focusLostBehavior=behavior;
});

Clazz.newMeth(C$, 'getFocusLostBehavior', function () {
return this.focusLostBehavior;
});

Clazz.newMeth(C$, 'setFormatterFactory$javax_swing_JFormattedTextField_AbstractFormatterFactory', function (tf) {
var oldFactory = this.factory=tf;
this.firePropertyChange$S$O$O("formatterFactory", oldFactory, tf);
p$.setValue$O$Z$Z.apply(this, [this.getValue(), true, false]);
});

Clazz.newMeth(C$, 'getFormatterFactory', function () {
return this.factory;
});

Clazz.newMeth(C$, 'setFormatter$javax_swing_JFormattedTextField_AbstractFormatter', function (format) {
var oldFormat = this.format;
if (oldFormat != null ) {
oldFormat.uninstall();
}p$.setEditValid$Z.apply(this, [true]);
this.format=format;
if (format != null ) {
format.install$javax_swing_JFormattedTextField(this);
}p$.setEdited$Z.apply(this, [false]);
this.firePropertyChange$S$O$O("textFormatter", oldFormat, format);
});

Clazz.newMeth(C$, 'getFormatter', function () {
return this.format;
});

Clazz.newMeth(C$, 'setValue$O', function (value) {
if (value != null  && this.getFormatterFactory() == null  ) {
this.setFormatterFactory$javax_swing_JFormattedTextField_AbstractFormatterFactory(p$.getDefaultFormatterFactory$O.apply(this, [value]));
}p$.setValue$O$Z$Z.apply(this, [value, true, true]);
});

Clazz.newMeth(C$, 'getValue', function () {
return this.value;
});

Clazz.newMeth(C$, 'commitEdit', function () {
var format = this.getFormatter();
if (format != null ) {
p$.setValue$O$Z$Z.apply(this, [format.stringToValue$S(this.getText()), false, true]);
}});

Clazz.newMeth(C$, 'setEditValid$Z', function (isValid) {
if (isValid != this.editValid ) {
this.editValid=isValid;
this.firePropertyChange$S$O$O("editValid", (I$[6]||$incl$(6)).$valueOf(!isValid), (I$[6]||$incl$(6)).$valueOf(isValid));
}});

Clazz.newMeth(C$, 'isEditValid', function () {
return this.editValid;
});

Clazz.newMeth(C$, 'invalidEdit', function () {
});

Clazz.newMeth(C$, 'processInputMethodEvent$java_awt_event_InputMethodEvent', function (e) {
C$.superclazz.prototype.processInputMethodEvent$java_awt_event_InputMethodEvent.apply(this, [e]);
});

Clazz.newMeth(C$, 'processFocusEvent$java_awt_event_FocusEvent', function (e) {
C$.superclazz.prototype.processFocusEvent$java_awt_event_FocusEvent.apply(this, [e]);
if (e.isTemporary()) {
return;
}if (p$.isEdited.apply(this, []) && e.getID() == 1005 ) {
if (this.focusLostHandler == null ) {
this.focusLostHandler=Clazz.new_((I$[7]||$incl$(7)), [this, null]);
}this.focusLostHandler.run();
} else if (!p$.isEdited.apply(this, [])) {
p$.setValue$O$Z$Z.apply(this, [this.getValue(), true, true]);
}});

Clazz.newMeth(C$, 'getActions', function () {
return (I$[8]||$incl$(8)).augmentList$javax_swing_ActionA$javax_swing_ActionA(C$.superclazz.prototype.getActions.apply(this, []), C$.$defaultActions);
});

Clazz.newMeth(C$, 'setDocument$javax_swing_text_Document', function (doc) {
if (this.documentListener != null  && this.getDocument() != null  ) {
this.getDocument().removeDocumentListener$javax_swing_event_DocumentListener(this.documentListener);
}C$.superclazz.prototype.setDocument$javax_swing_text_Document.apply(this, [doc]);
if (this.documentListener == null ) {
this.documentListener=Clazz.new_((I$[9]||$incl$(9)), [this, null]);
}doc.addDocumentListener$javax_swing_event_DocumentListener(this.documentListener);
});

Clazz.newMeth(C$, 'setFormatterActions$javax_swing_ActionA', function (actions) {
if (actions == null ) {
if (this.textFormatterActionMap != null ) {
this.textFormatterActionMap.clear();
}} else {
if (this.textFormatterActionMap == null ) {
var map = this.getActionMap();
this.textFormatterActionMap=Clazz.new_((I$[10]||$incl$(10)));
while (map != null ){
var parent = map.getParent();
if (Clazz.instanceOf(parent, "javax.swing.plaf.UIResource") || parent == null  ) {
map.setParent$javax_swing_ActionMap(this.textFormatterActionMap);
this.textFormatterActionMap.setParent$javax_swing_ActionMap(parent);
break;
}map=parent;
}
}for (var counter = actions.length - 1; counter >= 0; counter--) {
var key = actions[counter].getValue$S("Name");
if (key != null ) {
this.textFormatterActionMap.put$O$javax_swing_Action(key, actions[counter]);
}}
}});

Clazz.newMeth(C$, 'setValue$O$Z$Z', function (value, createFormat, firePC) {
var oldValue = this.value;
this.value=value;
if (createFormat) {
var factory = this.getFormatterFactory();
var atf;
if (factory != null ) {
atf=factory.getFormatter$javax_swing_JFormattedTextField(this);
} else {
atf=null;
}this.setFormatter$javax_swing_JFormattedTextField_AbstractFormatter(atf);
} else {
p$.setEditValid$Z.apply(this, [true]);
}p$.setEdited$Z.apply(this, [false]);
if (firePC) {
this.firePropertyChange$S$O$O("value", oldValue, value);
}});

Clazz.newMeth(C$, 'setEdited$Z', function (edited) {
this.edited=edited;
});

Clazz.newMeth(C$, 'isEdited', function () {
return this.edited;
});

Clazz.newMeth(C$, 'getDefaultFormatterFactory$O', function (type) {
if (Clazz.instanceOf(type, "java.text.DateFormat")) {
(I$[11]||$incl$(11)).notImplemented$S(null);
return null;
}if (Clazz.instanceOf(type, "java.text.NumberFormat")) {
return Clazz.new_((I$[5]||$incl$(5)).c$$javax_swing_JFormattedTextField_AbstractFormatter,[Clazz.new_((I$[12]||$incl$(12)).c$$java_text_NumberFormat,[type])]);
}if (Clazz.instanceOf(type, "java.text.Format")) {
return Clazz.new_((I$[5]||$incl$(5)).c$$javax_swing_JFormattedTextField_AbstractFormatter,[Clazz.new_((I$[13]||$incl$(13)).c$$java_text_Format,[type])]);
}if (Clazz.instanceOf(type, "java.util.Date")) {
return Clazz.new_((I$[5]||$incl$(5)).c$$javax_swing_JFormattedTextField_AbstractFormatter,[Clazz.new_((I$[14]||$incl$(14)))]);
}if (Clazz.instanceOf(type, "java.lang.Number")) {
var displayFormatter = Clazz.new_((I$[12]||$incl$(12)));
(displayFormatter).setValueClass$Class(type.getClass());
var editFormatter = Clazz.new_((I$[12]||$incl$(12)).c$$java_text_NumberFormat,[Clazz.new_((I$[15]||$incl$(15)).c$$S,["#.#"])]);
(editFormatter).setValueClass$Class(type.getClass());
return Clazz.new_((I$[5]||$incl$(5)).c$$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter,[displayFormatter, displayFormatter, editFormatter]);
}return Clazz.new_((I$[5]||$incl$(5)).c$$javax_swing_JFormattedTextField_AbstractFormatter,[Clazz.new_((I$[16]||$incl$(16)))]);
});
;
(function(){var C$=Clazz.newClass(P$.JFormattedTextField, "FocusLostHandler", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'Runnable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'run', function () {
var fb = this.this$0.getFocusLostBehavior();
if (fb == 0 || fb == 1 ) {
try {
this.this$0.commitEdit();
this.this$0.setValue$O$Z$Z(this.this$0.getValue(), true, true);
} catch (pe) {
if (Clazz.exceptionOf(pe, "java.text.ParseException")){
if (fb == 1) {
this.this$0.setValue$O$Z$Z(this.this$0.getValue(), true, true);
}} else {
throw pe;
}
}
} else if (fb == 2) {
this.this$0.setValue$O$Z$Z(this.this$0.getValue(), true, true);
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JFormattedTextField, "AbstractFormatterFactory", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JFormattedTextField, "AbstractFormatter", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.ftf = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'install$javax_swing_JFormattedTextField', function (ftf) {
if (this.ftf != null ) {
this.uninstall();
}this.ftf=ftf;
if (ftf != null ) {
try {
ftf.setText$S(this.valueToString$O(ftf.getValue()));
} catch (pe) {
if (Clazz.exceptionOf(pe, "java.text.ParseException")){
ftf.setText$S("");
this.setEditValid$Z(false);
} else {
throw pe;
}
}
p$.installDocumentFilter$javax_swing_text_DocumentFilter.apply(this, [this.getDocumentFilter()]);
ftf.setFormatterActions$javax_swing_ActionA(this.getActions());
}});

Clazz.newMeth(C$, 'uninstall', function () {
if (this.ftf != null ) {
p$.installDocumentFilter$javax_swing_text_DocumentFilter.apply(this, [null]);
this.ftf.setFormatterActions$javax_swing_ActionA(null);
}});

Clazz.newMeth(C$, 'getFormattedTextField', function () {
return this.ftf;
});

Clazz.newMeth(C$, 'invalidEdit', function () {
var ftf = this.getFormattedTextField();
if (ftf != null ) {
ftf.invalidEdit();
}});

Clazz.newMeth(C$, 'setEditValid$Z', function (valid) {
var ftf = this.getFormattedTextField();
if (ftf != null ) {
ftf.setEditValid$Z(valid);
}});

Clazz.newMeth(C$, 'getActions', function () {
return null;
});

Clazz.newMeth(C$, 'getDocumentFilter', function () {
return null;
});

Clazz.newMeth(C$, 'getNavigationFilter', function () {
return null;
});

Clazz.newMeth(C$, 'clone', function () {
var formatter = Clazz.clone(this);
formatter.ftf=null;
return formatter;
});

Clazz.newMeth(C$, 'installDocumentFilter$javax_swing_text_DocumentFilter', function (filter) {
var ftf = this.getFormattedTextField();
if (ftf != null ) {
var doc = ftf.getDocument();
if (Clazz.instanceOf(doc, "swingjs.api.JSMinimalAbstractDocument")) {
(doc).setDocumentFilter$javax_swing_text_DocumentFilter(filter);
}doc.putProperty$O$O(Clazz.getClass((I$[1]||$incl$(1))), null);
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JFormattedTextField, "CommitAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['javax.swing.JTextField','javax.swing.JTextField.NotifyAction']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getFocusedComponent();
if (Clazz.instanceOf(target, "javax.swing.JFormattedTextField")) {
try {
(target).commitEdit();
} catch (pe) {
if (Clazz.exceptionOf(pe, "java.text.ParseException")){
(target).invalidEdit();
return;
} else {
throw pe;
}
}
}C$.superclazz.prototype.actionPerformed$java_awt_event_ActionEvent.apply(this, [e]);
});

Clazz.newMeth(C$, 'isEnabled', function () {
var target = this.getFocusedComponent();
if (Clazz.instanceOf(target, "javax.swing.JFormattedTextField")) {
var ftf = target;
if (!ftf.isEdited()) {
return false;
}return true;
}return C$.superclazz.prototype.isEnabled.apply(this, []);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JFormattedTextField, "CancelAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["reset-field-edit"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getFocusedComponent();
if (Clazz.instanceOf(target, "javax.swing.JFormattedTextField")) {
var ftf = target;
ftf.setValue$O(ftf.getValue());
}});

Clazz.newMeth(C$, 'isEnabled', function () {
var target = this.getFocusedComponent();
if (Clazz.instanceOf(target, "javax.swing.JFormattedTextField")) {
var ftf = target;
if (!ftf.isEdited()) {
return false;
}return true;
}return C$.superclazz.prototype.isEnabled.apply(this, []);
});
})()
;
(function(){var C$=Clazz.newClass(P$.JFormattedTextField, "DocumentHandler", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'javax.swing.event.DocumentListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'insertUpdate$javax_swing_event_DocumentEvent', function (e) {
this.this$0.setEdited$Z.apply(this.this$0, [true]);
});

Clazz.newMeth(C$, 'removeUpdate$javax_swing_event_DocumentEvent', function (e) {
this.this$0.setEdited$Z.apply(this.this$0, [true]);
});

Clazz.newMeth(C$, 'changedUpdate$javax_swing_event_DocumentEvent', function (e) {
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-24 08:46:17
