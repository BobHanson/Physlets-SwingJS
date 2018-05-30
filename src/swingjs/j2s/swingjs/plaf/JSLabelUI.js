(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode','javax.swing.LookAndFeel']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSLabelUI", null, 'swingjs.plaf.JSLightweightUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.label = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.setDoc();
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.domNode=this.newDOMObject$S$S$SA("label", this.id, []);
this.textNode=this.newDOMObject$S$S$SA("span", this.id + "_text", []);
this.iconNode=this.newDOMObject$S$S$SA("span", this.id + "_icon", []);
this.centeringNode=this.newDOMObject$S$S$SA("span", this.id + "_cntr", []);
this.centeringNode.appendChild(this.iconNode);
this.centeringNode.appendChild(this.textNode);
this.domNode.appendChild(this.centeringNode);
}this.setIconAndText$S$javax_swing_ImageIcon$I$S("label", this.label.getIcon(), this.label.getIconTextGap(), this.label.getText());
(I$[1]||$incl$(1)).setStyles(this.domNode, ["position", "absolute", "width", this.c.getWidth() + "px", "height", this.c.getHeight() + "px"]);
if (this.actualHeight > 0) (I$[1]||$incl$(1)).setStyles(this.centeringNode, ["position", "absolute", "height", this.actualHeight + "px"]);
if (this.actualWidth > 0) (I$[1]||$incl$(1)).setStyles(this.centeringNode, ["position", "absolute", "width", this.actualWidth + "px"]);
this.setCssFont$swingjs_api_js_DOMNode$java_awt_Font(this.centeringNode, this.c.getFont());
if (this.jc.isOpaque() && this.jc.isEnabled() ) this.setBackground$java_awt_Color(this.jc.getBackground());
return this.domNode;
});

Clazz.newMeth(C$, 'setHTMLElement', function () {
this.setHTMLElementCUI();
return this.outerNode;
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.label=jc;
(I$[2]||$incl$(2)).installColorsAndFont$javax_swing_JComponent$S$S$S(jc, "Label.background", "Label.foreground", "Label.font");
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
});
})();
//Created 2018-05-24 08:47:54
