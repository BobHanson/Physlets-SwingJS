(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode','java.awt.Dimension','java.awt.event.ActionEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSTextFieldUI", null, 'swingjs.plaf.JSTextUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.inputType = null;
this.textField = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.inputType = "text";
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.allowPaintedBackground = false;
this.focusNode = this.$enableNode = this.valueNode = this.domNode = (I$[1]||$incl$(1)).setStyles(this.newDOMObject$S$S$SA("input", this.id, ["type", this.inputType]), ["lineHeight", "0.8", "box-sizing", "border-box"]);
(I$[1]||$incl$(1)).setAttrs(this.focusNode, ["ui", this]);
this.setDataComponent$swingjs_api_js_DOMNode(this.domNode);
this.bindJSKeyEvents$swingjs_api_js_DOMNode$Z(this.domNode, false);
this.addJQueryFocusCallbacks();
}this.setPadding$java_awt_Insets(this.editor.getMargin());
this.textListener.checkDocument();
this.setCssFont$swingjs_api_js_DOMNode$java_awt_Font(this.setProp$swingjs_api_js_DOMNode$S$S(this.domNode, "value", this.getComponentText()), this.c.getFont());
this.setEditable$Z(this.editable);
if (this.jc.isOpaque() && this.jc.isEnabled() ) this.setBackground$java_awt_Color(this.jc.getBackground());
return this.domNode;
});

Clazz.newMeth(C$, 'getCSSAdjustment$Z', function (addingCSS) {
return Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[0, addingCSS ? 0 : -2]);
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.textField = jc;
C$.superclazz.prototype.installUI$javax_swing_JComponent.apply(this, [jc]);
});

Clazz.newMeth(C$, 'handleEnter$I', function (eventType) {
if (eventType == 401) {
var a = this.getActionMap().get$O("notify-field-accept");
if (a != null ) a.actionPerformed$java_awt_event_ActionEvent(Clazz.new_((I$[3]||$incl$(3)).c$$O$I$S$J$I,[this.c, 1001, "notify-field-accept", System.currentTimeMillis(), 0]));
}return true;
});

Clazz.newMeth(C$, 'getPropertyPrefix', function () {
return "TextField.";
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:28
