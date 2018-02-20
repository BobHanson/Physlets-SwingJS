(function(){var P$=Clazz.newPackage("java.awt"),I$=[[['java.awt.Component','.BaselineResizeBehavior']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "GridBagLayoutInfo");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.width = 0;
this.height = 0;
this.startx = 0;
this.starty = 0;
this.minWidth = null;
this.minHeight = null;
this.weightX = null;
this.weightY = null;
this.$hasBaseline = false;
this.baselineType = null;
this.maxAscent = null;
this.maxDescent = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$I', function (width, height) {
C$.$init$.apply(this);
this.width = width;
this.height = height;
}, 1);

Clazz.newMeth(C$, 'hasConstantDescent$I', function (row) {
return ((this.baselineType[row] & (1 << (I$[1]||$incl$(1)).CONSTANT_DESCENT.ordinal())) != 0);
});

Clazz.newMeth(C$, 'hasBaseline$I', function (row) {
return (this.$hasBaseline && this.baselineType[row] != 0 );
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:01:49