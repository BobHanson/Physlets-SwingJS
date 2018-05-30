(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode','java.awt.Dimension','javax.swing.LookAndFeel']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSMenuItemUI", null, 'swingjs.plaf.JSButtonUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.isMenuItem=true;
this.allowPaintedBackground=false;
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.domNode=this.createItem$S$swingjs_api_js_DOMNode("_item", null);
}(I$[1]||$incl$(1)).setVisible(this.domNode, this.jc.isVisible());
return this.domNode;
});

Clazz.newMeth(C$, 'getContainerHeight', function () {
return this.height=25;
});

Clazz.newMeth(C$, 'getCSSAdjustment$Z', function (addingCSS) {
return Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[5, 0]);
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.menuItem=jc;
C$.superclazz.prototype.installUI$javax_swing_JComponent.apply(this, [jc]);
(I$[3]||$incl$(3)).installColorsAndFont$javax_swing_JComponent$S$S$S(jc, "MenuItem.background", "MenuItem.foreground", "MenuItem.font");
});
})();
//Created 2018-05-24 08:47:55
