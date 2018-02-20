(function(){var P$=Clazz.newPackage("java.awt"),I$=[];
var C$=Clazz.newClass(P$, "Graphics2D", null, 'java.awt.Graphics');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'draw3DRect$I$I$I$I$Z', function (x, y, width, height, raised) {
var p = this.getPaint();
var c = this.getColor();
var brighter = c.brighter();
var darker = c.darker();
this.setColor$java_awt_Color(raised ? brighter : darker);
this.fillRect$I$I$I$I(x, y, 1, height + 1);
this.fillRect$I$I$I$I(x + 1, y, width - 1, 1);
this.setColor$java_awt_Color(raised ? darker : brighter);
this.fillRect$I$I$I$I(x + 1, y + height, width, 1);
this.fillRect$I$I$I$I(x + width, y, 1, height);
this.setPaint$java_awt_Paint(p);
});

Clazz.newMeth(C$, 'fill3DRect$I$I$I$I$Z', function (x, y, width, height, raised) {
var p = this.getPaint();
var c = this.getColor();
var brighter = c.brighter();
var darker = c.darker();
if (!raised) {
this.setColor$java_awt_Color(darker);
} else if (p !== c ) {
this.setColor$java_awt_Color(c);
}this.fillRect$I$I$I$I(x + 1, y + 1, width - 2, height - 2);
this.setColor$java_awt_Color(raised ? brighter : darker);
this.fillRect$I$I$I$I(x, y, 1, height);
this.fillRect$I$I$I$I(x + 1, y, width - 2, 1);
this.setColor$java_awt_Color(raised ? darker : brighter);
this.fillRect$I$I$I$I(x + 1, y + height - 1, width - 1, 1);
this.fillRect$I$I$I$I(x + width - 1, y, 1, height - 1);
this.setPaint$java_awt_Paint(p);
});
})();
//Created 2018-02-08 10:01:49