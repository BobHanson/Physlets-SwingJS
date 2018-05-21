(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode','java.awt.Component']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSMenuUI", null, 'swingjs.plaf.JSMenuItemUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.jm = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.isMenu = true;
this.setDoc();
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.isMenuItem = !(this.jc).isTopLevelMenu();
if (this.isMenuItem) {
this.containerNode = this.domNode = this.createItem$S$swingjs_api_js_DOMNode("_menu", null);
} else {
this.domNode = this.createItem$S$swingjs_api_js_DOMNode("_item", null);
}}this.setCssFont$swingjs_api_js_DOMNode$java_awt_Font(this.domNode, this.c.getFont());
(I$[1]||$incl$(1)).setVisible(this.domNode, this.jc.isVisible());
return this.domNode;
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.jm = jc;
C$.superclazz.prototype.installUI$javax_swing_JComponent.apply(this, [jc]);
});

Clazz.newMeth(C$, 'getChildren', function () {
return (this.isMenuItem ? Clazz.array((I$[2]||$incl$(2)), -1, [this.jm.getPopupMenu()]) : this.jm.getComponents());
});

Clazz.newMeth(C$, 'getMaximumSize', function () {
return this.getPreferredSize();
});
})();
//Created 2018-05-15 01:03:23
