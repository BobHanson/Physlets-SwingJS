(function(){var P$=Clazz.newPackage("javax.swing"),I$=[];
var C$=Clazz.newClass(P$, "JToolTip", null, 'javax.swing.JComponent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.tipText = null;
this.component = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.setOpaque$Z(true);
this.uiClassID = "ToolTipUI";
this.updateUI();
}, 1);

Clazz.newMeth(C$, 'setTipText$S', function (tipText) {
var oldValue = this.tipText;
this.tipText = tipText;
this.firePropertyChange$S$O$O("tiptext", oldValue, tipText);
});

Clazz.newMeth(C$, 'getTipText', function () {
return this.tipText;
});

Clazz.newMeth(C$, 'setComponent$javax_swing_JComponent', function (c) {
var oldValue = this.component;
this.component = c;
this.firePropertyChange$S$O$O("component", oldValue, c);
});

Clazz.newMeth(C$, 'getComponent', function () {
return this.component;
});

Clazz.newMeth(C$, 'alwaysOnTop', function () {
return true;
});

Clazz.newMeth(C$, 'paramString', function () {
var tipTextString = (this.tipText != null  ? this.tipText : "");
return C$.superclazz.prototype.paramString.apply(this, []) + ",tipText=" + tipTextString ;
});
})();
//Created 2018-02-06 08:59:37
