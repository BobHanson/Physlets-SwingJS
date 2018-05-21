(function(){var P$=Clazz.newPackage("a2s"),I$=[['java.awt.Insets','a2s.A2SEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Button", null, 'javax.swing.JButton');
C$.awtInsets = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.awtInsets = Clazz.new_((I$[1]||$incl$(1)).c$$I$I$I$I,[0, 6, 0, 6]);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (text) {
C$.superclazz.c$$S.apply(this, [text]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getMargin', function () {
return C$.awtInsets;
});

Clazz.newMeth(C$, 'fireActionPerformed$java_awt_event_ActionEvent', function (event) {
(I$[2]||$incl$(2)).addListener$javax_swing_JComponent$java_awt_Component(null, this);
C$.superclazz.prototype.fireActionPerformed$java_awt_event_ActionEvent.apply(this, [event]);
});
})();
//Created 2018-05-15 01:01:42
