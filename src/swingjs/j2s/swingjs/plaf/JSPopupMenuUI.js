(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.jquery.JQueryUI','swingjs.JSUtil','javax.swing.LookAndFeel','swingjs.api.js.DOMNode']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSPopupMenuUI", null, 'swingjs.plaf.JSPanelUI');
C$.j2sSwingMenu = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
{
var jqueryui = Clazz.getClass((I$[1]||$incl$(1)));
}
;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.menu = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
if (C$.j2sSwingMenu == null ) {
(I$[2]||$incl$(2)).loadStaticResource$S("swingjs/jquery/j2sMenu.js");
C$.j2sSwingMenu=(I$[2]||$incl$(2)).J2S._getSwing();
}this.isContainer=true;
this.isMenuItem=true;
this.setDoc();
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.domNode=this.containerNode=this.newDOMObject$S$S$SA("ul", this.id, []);
}return this.domNode;
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
(I$[3]||$incl$(3)).installColorsAndFont$javax_swing_JComponent$S$S$S(jc, "PopupMenu.background", "PopupMenu.foreground", "PopupMenu.font");
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
});

Clazz.newMeth(C$, 'getPopup', function () {
return null;
});

Clazz.newMeth(C$, 'setVisible$Z', function (b) {
if (this.menu == null ) {
this.menu=this.jc;
C$.j2sSwingMenu.setMenu(this.menu);
}if (b) {
this.jc.addNotify();
this.getOuterNode();
var x = 0;
var y = 0;
{
x = this.menu.desiredLocationX;
y = this.menu.desiredLocationY;
}
C$.j2sSwingMenu.showMenu(this.menu, x, y);
} else {
C$.j2sSwingMenu.hideMenu(this.menu);
}});

Clazz.newMeth(C$, 'dispose', function () {
(I$[4]||$incl$(4)).remove(this.domNode);
(I$[4]||$incl$(4)).remove(this.outerNode);
C$.j2sSwingMenu.disposeMenu(this.menu);
});

Clazz.newMeth(C$, 'getPreferredSize', function () {
return null;
});
})();
//Created 2018-05-24 08:47:57
