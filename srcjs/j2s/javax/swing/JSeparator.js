(function(){var P$=Clazz.newPackage("javax.swing"),I$=[];
var C$=Clazz.newClass(P$, "JSeparator", null, 'javax.swing.JComponent', 'javax.swing.SwingConstants');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.orientation = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.orientation = 0;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$I.apply(this, [0]);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (orientation) {
C$.c$$I$S.apply(this, [orientation, "SeparatorUI"]);
}, 1);

Clazz.newMeth(C$, 'c$$I$S', function (orientation, sid) {
Clazz.super_(C$, this,1);
p$.checkOrientation$I.apply(this, [orientation]);
this.orientation = orientation;
this.setFocusable$Z(false);
this.uiClassID = sid;
this.updateUI();
}, 1);

Clazz.newMeth(C$, 'getOrientation', function () {
return this.orientation;
});

Clazz.newMeth(C$, 'setOrientation$I', function (orientation) {
if (this.orientation == orientation) {
return;
}var oldValue = this.orientation;
p$.checkOrientation$I.apply(this, [orientation]);
this.orientation = orientation;
this.firePropertyChange$S$I$I("orientation", oldValue, orientation);
this.revalidate();
this.repaint();
});

Clazz.newMeth(C$, 'checkOrientation$I', function (orientation) {
switch (orientation) {
case 1:
case 0:
break;
default:
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["orientation must be one of: VERTICAL, HORIZONTAL"]);
}
});

Clazz.newMeth(C$, 'paramString', function () {
var orientationString = (this.orientation == 0 ? "HORIZONTAL" : "VERTICAL");
return C$.superclazz.prototype.paramString.apply(this, []) + ",orientation=" + orientationString ;
});
})();
//Created 2018-02-06 08:59:33
