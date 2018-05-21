(function(){var P$=Clazz.newPackage("javax.swing.colorchooser"),I$=[[['javax.swing.colorchooser.AbstractColorChooserPanel','.ModelListener'],'javax.swing.UIManager']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AbstractColorChooserPanel", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.JPanel');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.chooser = null;
this.colorListener = null;
this.dirty = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.dirty = true;
}, 1);

Clazz.newMeth(C$, 'getMnemonic', function () {
return 0;
});

Clazz.newMeth(C$, 'getDisplayedMnemonicIndex', function () {
return -1;
});

Clazz.newMeth(C$, 'installChooserPanel$javax_swing_JColorChooser', function (enclosingChooser) {
if (this.chooser != null ) {
throw Clazz.new_(Clazz.load('java.lang.RuntimeException').c$$S,["This chooser panel is already installed"]);
}this.chooser = enclosingChooser;
this.buildChooser();
this.updateChooser();
this.colorListener = Clazz.new_((I$[1]||$incl$(1)), [this, null]);
this.getColorSelectionModel().addChangeListener$javax_swing_event_ChangeListener(this.colorListener);
});

Clazz.newMeth(C$, 'uninstallChooserPanel$javax_swing_JColorChooser', function (enclosingChooser) {
this.getColorSelectionModel().removeChangeListener$javax_swing_event_ChangeListener(this.colorListener);
this.chooser = null;
});

Clazz.newMeth(C$, 'getColorSelectionModel', function () {
return this.chooser.getSelectionModel();
});

Clazz.newMeth(C$, 'getColorFromModel', function () {
return this.getColorSelectionModel().getSelectedColor();
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
if (this.dirty) {
this.updateChooser();
this.dirty = false;
}C$.superclazz.prototype.paint$java_awt_Graphics.apply(this, [g]);
});

Clazz.newMeth(C$, 'getInt$O$I', function (key, defaultValue) {
var value = (I$[2]||$incl$(2)).get$O(key);
if (Clazz.instanceOf(value, "java.lang.Integer")) {
return (value).intValue();
}if (Clazz.instanceOf(value, "java.lang.String")) {
try {
return Integer.parseInt(value);
} catch (nfe) {
if (Clazz.exceptionOf(nfe, "java.lang.NumberFormatException")){
} else {
throw nfe;
}
}
}return defaultValue;
}, 1);
;
(function(){var C$=Clazz.newClass(P$.AbstractColorChooserPanel, "ModelListener", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'javax.swing.event.ChangeListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
if (this.this$0.isShowing()) {
this.this$0.updateChooser();
this.this$0.dirty = false;
} else {
this.this$0.dirty = true;
}});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:43
