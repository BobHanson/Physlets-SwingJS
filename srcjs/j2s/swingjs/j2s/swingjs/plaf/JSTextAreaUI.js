(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['java.awt.Insets','swingjs.api.js.DOMNode','java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSTextAreaUI", null, 'swingjs.plaf.JSTextUI');

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
this.$domBtn = this.focusNode = this.$enableNode = this.textNode = this.valueNode = this.domNode = this.newDOMObject$S$S$SA("textarea", this.id, []);
(I$[2]||$incl$(2)).setStyles(this.domNode, ["resize", "none"]);
this.bindJSKeyEvents$swingjs_api_js_DOMNode$Z(this.domNode, true);
}this.textListener.checkDocument();
this.setCssFont$swingjs_api_js_DOMNode$java_awt_Font((I$[2]||$incl$(2)).setAttr(this.domNode, "innerHTML", this.getComponentText()), this.c.getFont());
if (!this.editable) (I$[2]||$incl$(2)).setAttr(this.domNode, "readOnly", "true");
return this.domNode;
});

Clazz.newMeth(C$, 'getTextAreaTextSize$java_awt_Dimension', function (d) {
var sh = 0;
var sw = 0;
{
var h = this.domNode.style.height;
this.domNode.style.height = null;
sh = this.domNode.scrollHeight;
this.domNode.style.height = h;
var w = this.domNode.style.width;
this.domNode.style.width = null;
sw = this.domNode.scrollWidth;
this.domNode.style.width = w;
}
d.width = sw;
d.height = sh;
});

Clazz.newMeth(C$, 'getInsets', function () {
return this.myInsets;
});

Clazz.newMeth(C$, 'getCSSAdjustment$Z', function (addingCSS) {
return (addingCSS ? Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[-5, -12]) : Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[0, 0]));
});

Clazz.newMeth(C$, 'getPropertyPrefix', function () {
return "TextArea.";
});

Clazz.newMeth(C$, 'setHTMLElement', function () {
return (I$[2]||$incl$(2)).setStyles(this.setHTMLElementCUI(), ["overflow", "hidden", "position", "absolute"]);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:28
