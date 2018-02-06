(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.plaf.JSComponentUI','swingjs.api.js.DOMNode']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSViewportUI", null, 'swingjs.plaf.JSLightweightUI', ['java.beans.PropertyChangeListener', 'javax.swing.event.ChangeListener']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.viewport = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.domNode = this.newDOMObject$S$S$SA("div", this.id, []);
this.ignoreAllMouseEvents$swingjs_api_js_DOMNode(this.domNode);
}return this.domNode;
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.viewport = jc;
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
});

Clazz.newMeth(C$, 'getPreferredSize', function () {
if ((I$[1]||$incl$(1)).debugging) System.out.println$S(this.id + " getPreferredSize");
return null;
});

Clazz.newMeth(C$, 'setHTMLElement', function () {
return (I$[2]||$incl$(2)).setStyles(this.setHTMLElementCUI(), ["overflow", "hidden"]);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:59
