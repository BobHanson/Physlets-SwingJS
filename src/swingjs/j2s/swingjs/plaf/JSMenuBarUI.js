(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode','swingjs.plaf.DefaultMenuLayout','javax.swing.LookAndFeel','java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSMenuBarUI", null, 'swingjs.plaf.JSPanelUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.menuBar = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.isContainer = true;
this.setDoc();
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.containerNode = this.domNode = this.newDOMObject$S$S$SA("div", this.id, []);
(I$[1]||$incl$(1)).setPositionAbsolute(this.domNode, 0, 0);
}return this.domNode;
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.menuBar = jc;
if (this.menuBar.getLayout() == null  || Clazz.instanceOf(this.menuBar.getLayout(), "javax.swing.plaf.UIResource") ) {
this.menuBar.setLayout$java_awt_LayoutManager(Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_Container$I,[this.menuBar, 2]));
}(I$[3]||$incl$(3)).installColorsAndFont$javax_swing_JComponent$S$S$S(jc, "MenuBar.background", "MenuBar.foreground", "MenuBar.font");
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
});

Clazz.newMeth(C$, 'getContainerHeight', function () {
return this.height = 25;
});

Clazz.newMeth(C$, 'getPreferredSize', function () {
var d = Clazz.new_((I$[4]||$incl$(4)).c$$I$I,[0, 25]);
return d;
});
})();
//Created 2018-05-15 01:03:23
