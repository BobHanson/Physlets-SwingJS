(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode','java.awt.Dimension','javax.swing.LookAndFeel']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSPanelUI", null, 'swingjs.plaf.JSLightweightUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$frameZ = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$frameZ = 10000;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.isContainer = true;
this.setDoc();
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
var root = this.jc.getRootPane();
this.isContentPane = (root != null  && this.jc === root.getContentPane()  );
this.domNode = this.newDOMObject$S$S$SA("div", this.id, []);
if (root != null  && root.getGlassPane() === this.c  ) (I$[1]||$incl$(1)).setVisible(this.domNode, false);
}return this.domNode;
});

Clazz.newMeth(C$, 'setHTMLSize$swingjs_api_js_DOMNode$Z', function (obj, addCSS) {
return Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[this.c.getWidth(), this.c.getHeight()]);
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
(I$[3]||$incl$(3)).installColorsAndFont$javax_swing_JComponent$S$S$S(jc, "Panel.background", "Panel.foreground", "Panel.font");
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
});

Clazz.newMeth(C$, 'getPreferredSize', function () {
return null;
});
})();
//Created 2018-02-08 10:03:25
