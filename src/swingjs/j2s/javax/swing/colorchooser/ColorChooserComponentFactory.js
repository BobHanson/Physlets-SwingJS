(function(){var P$=Clazz.newPackage("javax.swing.colorchooser"),I$=[['javax.swing.colorchooser.AbstractColorChooserPanel','javax.swing.colorchooser.DefaultSwatchChooserPanel','javax.swing.colorchooser.DefaultRGBChooserPanel','javax.swing.colorchooser.DefaultPreviewPanel']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ColorChooserComponentFactory");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getDefaultChooserPanels', function () {
var choosers = Clazz.array((I$[1]||$incl$(1)), -1, [Clazz.new_((I$[2]||$incl$(2))), Clazz.new_((I$[3]||$incl$(3)))]);
return choosers;
}, 1);

Clazz.newMeth(C$, 'getPreviewPanel', function () {
return Clazz.new_((I$[4]||$incl$(4)));
}, 1);
})();
//Created 2018-05-15 01:02:43
