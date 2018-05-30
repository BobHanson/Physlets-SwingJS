(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[];
var C$=Clazz.newClass(P$, "JSAppletUI", null, 'swingjs.plaf.JSLightweightUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.containerNode=this.domNode=this.newDOMObject$S$S$SA("div", this.id, []);
}return this.domNode;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:51
