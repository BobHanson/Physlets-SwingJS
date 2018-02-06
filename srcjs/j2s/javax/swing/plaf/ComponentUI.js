(function(){var P$=Clazz.newPackage("javax.swing.plaf"),I$=[['java.lang.Error',['java.awt.Component','.BaselineResizeBehavior']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ComponentUI", null, null, 'java.awt.peer.ComponentPeer');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (component) {
});

Clazz.newMeth(C$, 'installJS', function () {
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (c) {
});

Clazz.newMeth(C$, 'uninstallJS', function () {
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics$javax_swing_JComponent', function (g, c) {
});

Clazz.newMeth(C$, 'update$java_awt_Graphics$javax_swing_JComponent', function (g, c) {
});

Clazz.newMeth(C$, 'getPreferredSize', function () {
return null;
});

Clazz.newMeth(C$, 'getMinimumSize', function () {
return this.getPreferredSize();
});

Clazz.newMeth(C$, 'getMaximumSize', function () {
return null;
});

Clazz.newMeth(C$, 'contains$javax_swing_JComponent$I$I', function (c, x, y) {
return c.inside$I$I(x, y);
});

Clazz.newMeth(C$, 'createUI$javax_swing_JComponent', function (c) {
throw Clazz.new_((I$[1]||$incl$(1)).c$$S,["ComponentUI.createUI not implemented."]);
}, 1);

Clazz.newMeth(C$, 'getBaseline$javax_swing_JComponent$I$I', function (c, width, height) {
return -1;
});

Clazz.newMeth(C$, 'getBaselineResizeBehavior$javax_swing_JComponent', function (c) {
return (I$[2]||$incl$(2)).OTHER;
});
})();
//Created 2018-02-06 08:59:50
