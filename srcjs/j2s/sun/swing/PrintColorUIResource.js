(function(){var P$=Clazz.newPackage("sun.swing"),I$=[['javax.swing.plaf.ColorUIResource']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PrintColorUIResource", null, 'javax.swing.plaf.ColorUIResource');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.printColor = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$java_awt_Color', function (rgb, printColor) {
C$.superclazz.c$$I.apply(this, [rgb]);
C$.$init$.apply(this);
this.printColor = printColor;
}, 1);

Clazz.newMeth(C$, 'getPrintColor', function () {
return ((this.printColor != null ) ? this.printColor : this);
});

Clazz.newMeth(C$, 'writeReplace', function () {
return Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Color,[this]);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:23
