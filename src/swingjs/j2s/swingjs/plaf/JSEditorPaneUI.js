(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['java.awt.Insets','swingjs.api.js.DOMNode','java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSEditorPaneUI", null, 'swingjs.plaf.JSTextUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$domBtn = null;
this.myInsets = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.myInsets = Clazz.new_((I$[1]||$incl$(1)).c$$I$I$I$I,[0, 0, 5, 5]);
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.allowPaintedBackground = false;
this.$domBtn = this.focusNode = this.$enableNode = this.textNode = this.valueNode = this.domNode = this.newDOMObject$S$S$SA("div", this.id, []);
(I$[2]||$incl$(2)).setStyles(this.domNode, ["resize", "none"]);
this.setDataUI$swingjs_api_js_DOMNode(this.domNode);
if ((this.c).isEditable()) this.bindJSKeyEvents$swingjs_api_js_DOMNode$Z(this.domNode, true);
}this.textListener.checkDocument();
this.setCssFont$swingjs_api_js_DOMNode$java_awt_Font((I$[2]||$incl$(2)).setAttr(this.domNode, "innerHTML", this.getComponentText()), this.c.getFont());
(I$[2]||$incl$(2)).setAttr(this.domNode, "contentEditable", this.editable ? "true" : "false");
return this.domNode;
});

Clazz.newMeth(C$, 'getInsets', function () {
return this.myInsets;
});

Clazz.newMeth(C$, 'getCSSAdjustment$Z', function (addingCSS) {
return Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[0, 0]);
});

Clazz.newMeth(C$, 'getPropertyPrefix', function () {
return "EditablePane.";
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:22
