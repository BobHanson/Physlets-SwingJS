(function(){var P$=Clazz.newPackage("javax.swing"),I$=[];
var C$=Clazz.newClass(P$, "ColorTracker", null, null, 'java.awt.event.ActionListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.chooser = null;
this.color = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JColorChooser', function (c) {
C$.$init$.apply(this);
this.chooser=c;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.color=this.chooser.getColor();
});

Clazz.newMeth(C$, 'getColor', function () {
return this.color;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:46:13
