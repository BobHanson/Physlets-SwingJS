(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode','java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSRadioButtonUI", null, 'swingjs.plaf.JSButtonUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
return this.updateButton$S("radio");
});

Clazz.newMeth(C$, 'getPropertyPrefix', function () {
return "RadioButton.";
});

Clazz.newMeth(C$, 'updateButton$S', function (myType) {
var b = this.jc;
var isNew = false;
var doAll = false;
if (this.domNode == null ) {
doAll=true;
var name = this.id;
this.domBtn=this.$enableNode=this.newDOMObject$S$S$SA("input", this.id, ["type", myType, "name", name]);
this.iconNode=this.newDOMObject$S$S$SA("span", this.id + "_icon", []);
this.textNode=this.newDOMObject$S$S$SA("label", this.id + "l1", []);
this.label=this.newDOMObject$S$S$SA("label", this.id + "l2", ["htmlFor", this.id]);
this.setDataComponent$swingjs_api_js_DOMNode(this.label);
this.setDataComponent$swingjs_api_js_DOMNode(this.iconNode);
this.setDataComponent$swingjs_api_js_DOMNode(this.domBtn);
this.setDataComponent$swingjs_api_js_DOMNode(this.textNode);
this.setEnabled$Z(this.c.isEnabled());
if (this.isMenuItem) {
this.domNode=this.createItem$S$swingjs_api_js_DOMNode("_item", this.label);
} else {
this.domNode=this.newDOMObject$S$S$SA("div", this.id + "_0", []);
this.centeringNode=this.newDOMObject$S$S$SA("div", this.id + "_ctr", []);
this.centeringNode.appendChild(this.label);
this.domNode.appendChild(this.centeringNode);
}}if (b.isSelected() || isNew ) (I$[1]||$incl$(1)).setAttr(this.domBtn, "checked", "true");
this.setCssFont$swingjs_api_js_DOMNode$java_awt_Font(this.textNode, this.c.getFont());
this.setIconAndText$S$javax_swing_ImageIcon$I$S("radio", this.button.getIcon(), this.button.getIconTextGap(), this.button.getText());
(I$[1]||$incl$(1)).setStyles(this.centeringNode, ["position", null]);
(I$[1]||$incl$(1)).setStyles(this.iconNode, ["position", null]);
(I$[1]||$incl$(1)).setStyles(this.domBtn, ["position", null]);
(I$[1]||$incl$(1)).setStyles(this.textNode, ["position", null]);
var dobj = null;
if (!this.isMenuItem) {
var wBtn = this.setHTMLSize1$swingjs_api_js_DOMNode$Z$Z(this.domBtn, false, false).width - 1;
var wIcon = Math.max(0, this.setHTMLSize1$swingjs_api_js_DOMNode$Z$Z(this.iconNode, false, false).width - 1);
dobj=this.setHTMLSize1$swingjs_api_js_DOMNode$Z$Z(this.wrap$S$S$swingjs_api_js_DOMNodeA("div", "", [this.iconNode, this.domBtn, this.textNode]), false, false);
(I$[1]||$incl$(1)).setStyles(this.textNode, ["left", wBtn + wIcon + "px" ]);
P$.JSComponentUI.vCenter$swingjs_api_js_DOMNode$I(this.domBtn, -75);
P$.JSComponentUI.vCenter$swingjs_api_js_DOMNode$I(this.iconNode, -15);
P$.JSComponentUI.vCenter$swingjs_api_js_DOMNode$I(this.textNode, -50);
(I$[1]||$incl$(1)).setPositionAbsolute(this.domBtn, -2147483648, 0);
(I$[1]||$incl$(1)).setPositionAbsolute(this.iconNode, -2147483648, 0);
(I$[1]||$incl$(1)).setPositionAbsolute(this.textNode, -2147483648, 0);
(I$[1]||$incl$(1)).setPositionAbsolute(this.label, -2147483648, 0);
}this.label.appendChild(this.iconNode);
this.label.appendChild(this.domBtn);
this.label.appendChild(this.textNode);
if (doAll && !this.isMenuItem ) (I$[1]||$incl$(1)).setPositionAbsolute(this.domNode, -2147483648, 0);
if (!this.isMenuItem) {
(I$[1]||$incl$(1)).setSize(this.label, dobj.width, dobj.height);
(I$[1]||$incl$(1)).setSize(this.centeringNode, dobj.width, dobj.height);
}return this.domNode;
});

Clazz.newMeth(C$, 'setHTMLSize$swingjs_api_js_DOMNode$Z', function (obj, addCSS) {
(I$[1]||$incl$(1)).setStyles(this.label, ["position", null, "width", null, "height", null]);
(I$[1]||$incl$(1)).setStyles(this.domBtn, ["position", null, "width", null, "height", null]);
(I$[1]||$incl$(1)).setStyles(this.textNode, ["position", null, "width", null, "height", null]);
var d;
if (this.isMenuItem) {
d=Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[20, 20]);
} else {
d=this.setHTMLSize1$swingjs_api_js_DOMNode$Z$Z(obj, addCSS, false);
(I$[1]||$incl$(1)).setPositionAbsolute(this.domBtn, -2147483648, 0);
(I$[1]||$incl$(1)).setPositionAbsolute(this.textNode, -2147483648, 0);
(I$[1]||$incl$(1)).setPositionAbsolute(this.label, -2147483648, 0);
if (this.centeringNode != null ) (I$[1]||$incl$(1)).setPositionAbsolute(this.centeringNode, -2147483648, 0);
(I$[1]||$incl$(1)).setStyles(this.label, ["width", d.width + "px", "height", d.height + "px"]);
}return d;
});

Clazz.newMeth(C$, 'handleDOMEvent$O', function (e) {
(this.c).doClick$I(0);
});

Clazz.newMeth(C$, 'getDefaultIconTextGap', function () {
return 4;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:57
