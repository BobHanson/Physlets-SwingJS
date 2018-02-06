(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode','javajs.util.PT','swingjs.plaf.JSComponentUI','javax.swing.LookAndFeel','javax.swing.UIManager']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSComboBoxUI", null, 'swingjs.plaf.JSLightweightUI', ['java.beans.PropertyChangeListener', 'java.awt.event.ItemListener', 'javax.swing.event.ListDataListener']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.comboBox = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.isContainer = true;
this.allowPaintedBackground = false;
this.setDoc();
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.domNode = this.focusNode = (I$[1]||$incl$(1)).setStyles(this.newDOMObject$S$S$SA("select", this.id, []), ["padding", "0px 0px", "lineHeight", "0.8", "box-sizing", "border-box"]);
this.ignoreAllMouseEvents$swingjs_api_js_DOMNode(this.domNode);
(I$[1]||$incl$(1)).addJqueryHandledEvent(this, this.domNode, "change");
this.addJQueryFocusCallbacks();
}p$.populateList.apply(this, []);
return this.domNode;
});

Clazz.newMeth(C$, 'handleJSEvent$O$I$O', function (target, eventType, jQueryEvent) {
switch (eventType) {
case -1:
var index = (I$[2]||$incl$(2)).parseInt$S("" + (I$[1]||$incl$(1)).getAttr(this.domNode, "selectedIndex"));
this.comboBox.setSelectedIndex$I(index);
break;
}
return true;
});

Clazz.newMeth(C$, 'populateList', function () {
this.$$O(this.domNode).empty();
var n = this.comboBox.getItemCount();
var iselect = this.comboBox.getSelectedIndex();
for (var i = 0; i < n; i++) {
var item = this.comboBox.getItemAt$I(i).toString();
var option = (I$[1]||$incl$(1)).createElement("option", this.id + "_" + (++(I$[3]||$incl$(3)).incr) );
(I$[1]||$incl$(1)).setAttr(option, "innerHTML", item);
if (i == iselect) (I$[1]||$incl$(1)).setAttr(option, "selected", "true");
this.domNode.appendChild(option);
}
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.comboBox = jc;
this.installListeners();
(I$[4]||$incl$(4)).installColorsAndFont$javax_swing_JComponent$S$S$S(jc, "ComboBox.background", "ComboBox.foreground", "ComboBox.font");
});

Clazz.newMeth(C$, 'getPropertyPrefix', function () {
return "ComboBox.";
});

Clazz.newMeth(C$, 'getDisabledColors$S', function (pp) {
this.inactiveBackground = (I$[5]||$incl$(5)).getColor$O(pp + "disabledBackground");
this.inactiveForeground = (I$[5]||$incl$(5)).getColor$O(pp + "disabledForeground");
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
this.uninstallListeners();
});

Clazz.newMeth(C$, 'installListeners', function () {
this.comboBox.addItemListener$java_awt_event_ItemListener(this);
if (this.comboBox.getModel() != null ) {
this.comboBox.getModel().addListDataListener$javax_swing_event_ListDataListener(this);
}});

Clazz.newMeth(C$, 'uninstallListeners', function () {
this.comboBox.removeItemListener$java_awt_event_ItemListener(this);
if (this.comboBox.getModel() != null ) this.comboBox.getModel().removeListDataListener$javax_swing_event_ListDataListener(this);
});

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (evt) {
if ((I$[3]||$incl$(3)).debugging) System.out.println$S("JSComboBoxUI propertychange " + evt);
});

Clazz.newMeth(C$, 'contains$javax_swing_JComponent$I$I', function (c, x, y) {
return true;
});

Clazz.newMeth(C$, 'setPopupVisible$javax_swing_JComboBox$Z', function (c, v) {
});

Clazz.newMeth(C$, 'isPopupVisible$javax_swing_JComboBox', function (c) {
return false;
});

Clazz.newMeth(C$, 'isFocusTraversable$javax_swing_JComboBox', function (c) {
return true;
});

Clazz.newMeth(C$, 'intervalAdded$javax_swing_event_ListDataEvent', function (e) {
this.revalidate();
});

Clazz.newMeth(C$, 'intervalRemoved$javax_swing_event_ListDataEvent', function (e) {
this.revalidate();
});

Clazz.newMeth(C$, 'contentsChanged$javax_swing_event_ListDataEvent', function (e) {
this.revalidate();
});

Clazz.newMeth(C$, 'itemStateChanged$java_awt_event_ItemEvent', function (e) {
});
})();
//Created 2018-02-06 09:00:38
