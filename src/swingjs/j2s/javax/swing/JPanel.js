(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.lang.Boolean','java.awt.FlowLayout']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JPanel", null, 'javax.swing.JComponent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_LayoutManager$Z', function (layout, isDoubleBuffered) {
Clazz.super_(C$, this,1);
this.setLayout$java_awt_LayoutManager(layout);
this.setUIProperty$S$O("opaque", (I$[1]||$incl$(1)).TRUE);
this.uiClassID="PanelUI";
this.updateUI();
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_LayoutManager', function (layout) {
C$.c$$java_awt_LayoutManager$Z.apply(this, [layout, true]);
}, 1);

Clazz.newMeth(C$, 'c$$Z', function (isDoubleBuffered) {
C$.c$$java_awt_LayoutManager$Z.apply(this, [Clazz.new_((I$[2]||$incl$(2))), isDoubleBuffered]);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$Z.apply(this, [true]);
}, 1);
})();
//Created 2018-05-24 08:46:19
