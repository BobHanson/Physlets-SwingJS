(function(){var P$=Clazz.newPackage("java.awt.font"),I$=[[['java.awt.geom.Rectangle2D','.Float']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "GraphicAttribute");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.fAlignment = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (alignment) {
C$.$init$.apply(this);
if (alignment < -2 || alignment > 2 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["bad alignment"]);
}this.fAlignment = alignment;
}, 1);

Clazz.newMeth(C$, 'getBounds', function () {
var ascent = this.getAscent();
return Clazz.new_((I$[1]||$incl$(1)).c$$F$F$F$F,[0, -ascent, this.getAdvance(), ascent + this.getDescent()]);
});

Clazz.newMeth(C$, 'getAlignment', function () {
return this.fAlignment;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:01:58
