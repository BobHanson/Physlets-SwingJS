(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[];
var C$=Clazz.newClass(P$, "JSRootPaneUI", null, 'swingjs.plaf.JSLightweightUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.resizer = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'setResizer$swingjs_plaf_Resizer', function (resizer) {
this.resizer=resizer;
});

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.isRootPane=this.isContainer=true;
this.setDoc();
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.domNode=this.newDOMObject$S$S$SA("div", this.id, []);
}return this.domNode;
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
});

Clazz.newMeth(C$, 'getPreferredSize', function () {
return null;
});

Clazz.newMeth(C$, 'setInnerComponentBounds$I$I', function (width, height) {
var resizer = this.jc.getFrameViewer().getResizer();
if (resizer != null ) resizer.setPosition$I$I(0, 0);
});
})();
//Created 2018-05-24 08:47:57
