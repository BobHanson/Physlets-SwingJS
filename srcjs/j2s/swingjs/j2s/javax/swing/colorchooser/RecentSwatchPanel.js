(function(){var P$=Clazz.newPackage("javax.swing.colorchooser"),I$=[['java.awt.GridBagLayout','java.awt.GridBagConstraints','javax.swing.JPanel','javax.swing.colorchooser.MainSwatchPanel','javax.swing.colorchooser.RecentSwatchPanel',['javax.swing.colorchooser.DefaultSwatchChooserPanel','.MainSwatchListener'],['javax.swing.colorchooser.DefaultSwatchChooserPanel','.RecentSwatchListener'],'java.awt.BorderLayout','javax.swing.border.CompoundBorder','javax.swing.border.LineBorder','java.awt.Color','java.awt.Insets','javax.swing.JLabel','java.awt.Dimension','javax.swing.UIManager']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "RecentSwatchPanel", null, 'javax.swing.colorchooser.SwatchPanel');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'initValues', function () {
this.swatchSize = (I$[15]||$incl$(15)).getDimension$O("ColorChooser.swatchesRecentSwatchSize");
this.numSwatches = Clazz.new_((I$[14]||$incl$(14)).c$$I$I,[5, 7]);
this.gap = Clazz.new_((I$[14]||$incl$(14)).c$$I$I,[1, 1]);
});

Clazz.newMeth(C$, 'initColors', function () {
var defaultRecentColor = (I$[15]||$incl$(15)).getColor$O("ColorChooser.swatchesDefaultRecentColor");
var numColors = this.numSwatches.width * this.numSwatches.height;
this.colors = Clazz.array((I$[11]||$incl$(11)), [numColors]);
for (var i = 0; i < numColors; i++) {
this.colors[i] = defaultRecentColor;
}
});

Clazz.newMeth(C$, 'setMostRecentColor$java_awt_Color', function (c) {
System.arraycopy(this.colors, 0, this.colors, 1, this.colors.length - 1);
this.colors[0] = c;
this.repaint();
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:46
