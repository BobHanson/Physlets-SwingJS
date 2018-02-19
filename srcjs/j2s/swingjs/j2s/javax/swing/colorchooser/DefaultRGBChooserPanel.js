(function(){var P$=Clazz.newPackage("javax.swing.colorchooser"),I$=[['java.awt.BorderLayout','javax.swing.JPanel','javax.swing.colorchooser.SmartGridLayout','javax.swing.JLabel','javax.swing.colorchooser.AbstractColorChooserPanel','javax.swing.JSlider','javax.swing.JSpinner','javax.swing.SpinnerNumberModel','javax.swing.colorchooser.CenterLayout','java.lang.Boolean','java.awt.Color']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DefaultRGBChooserPanel", null, 'javax.swing.colorchooser.AbstractColorChooserPanel', 'javax.swing.event.ChangeListener');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.redSlider = null;
this.greenSlider = null;
this.blueSlider = null;
this.redField = null;
this.blueField = null;
this.greenField = null;
this.minValue = 0;
this.maxValue = 0;
this.isAdjusting = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.minValue = 0;
this.maxValue = 255;
this.isAdjusting = false;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.setInheritsPopupMenu$Z(true);
}, 1);

Clazz.newMeth(C$, 'setColor$java_awt_Color', function (newColor) {
var red = newColor.getRed();
var blue = newColor.getBlue();
var green = newColor.getGreen();
if (this.redSlider.getValue() != red) {
this.redSlider.setValue$I(red);
}if (this.greenSlider.getValue() != green) {
this.greenSlider.setValue$I(green);
}if (this.blueSlider.getValue() != blue) {
this.blueSlider.setValue$I(blue);
}if ((this.redField.getValue()).intValue() != red) this.redField.setValue$O( new Integer(red));
if ((this.greenField.getValue()).intValue() != green) this.greenField.setValue$O( new Integer(green));
if ((this.blueField.getValue()).intValue() != blue) this.blueField.setValue$O( new Integer(blue));
});

Clazz.newMeth(C$, 'getDisplayName', function () {
return "RGB";
});

Clazz.newMeth(C$, 'getMnemonic', function () {
return P$.AbstractColorChooserPanel.getInt$O$I("ColorChooser.rgbMnemonic", -1);
});

Clazz.newMeth(C$, 'getDisplayedMnemonicIndex', function () {
return P$.AbstractColorChooserPanel.getInt$O$I("ColorChooser.rgbDisplayedMnemonicIndex", -1);
});

Clazz.newMeth(C$, 'getSmallDisplayIcon', function () {
return null;
});

Clazz.newMeth(C$, 'getLargeDisplayIcon', function () {
return null;
});

Clazz.newMeth(C$, 'installChooserPanel$javax_swing_JColorChooser', function (enclosingChooser) {
C$.superclazz.prototype.installChooserPanel$javax_swing_JColorChooser.apply(this, [enclosingChooser]);
});

Clazz.newMeth(C$, 'buildChooser', function () {
var redString = "Red";
var greenString = "Green";
var blueString = "Blue";
this.setLayout$java_awt_LayoutManager(Clazz.new_((I$[1]||$incl$(1))));
var color = this.getColorFromModel();
var enclosure = Clazz.new_((I$[2]||$incl$(2)));
enclosure.setLayout$java_awt_LayoutManager(Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[3, 3]));
enclosure.setInheritsPopupMenu$Z(true);
this.add$java_awt_Component$O(enclosure, "Center");
var l = Clazz.new_((I$[4]||$incl$(4)).c$$S,[redString]);
l.setDisplayedMnemonic$I((I$[5]||$incl$(5)).getInt$O$I("ColorChooser.rgbRedMnemonic", -1));
enclosure.add$java_awt_Component(l);
this.redSlider = Clazz.new_((I$[6]||$incl$(6)).c$$I$I$I$I,[0, 0, 255, color.getRed()]);
this.redSlider.setMajorTickSpacing$I(85);
this.redSlider.setMinorTickSpacing$I(17);
this.redSlider.setPaintTicks$Z(true);
this.redSlider.setPaintLabels$Z(true);
this.redSlider.setInheritsPopupMenu$Z(true);
enclosure.add$java_awt_Component(this.redSlider);
this.redField = Clazz.new_((I$[7]||$incl$(7)).c$$javax_swing_SpinnerModel,[Clazz.new_((I$[8]||$incl$(8)).c$$I$I$I$I,[color.getRed(), 0, 255, 1])]);
l.setLabelFor$java_awt_Component(this.redSlider);
this.redField.setInheritsPopupMenu$Z(true);
var redFieldHolder = Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_LayoutManager,[Clazz.new_((I$[9]||$incl$(9)))]);
redFieldHolder.setInheritsPopupMenu$Z(true);
this.redField.addChangeListener$javax_swing_event_ChangeListener(this);
redFieldHolder.add$java_awt_Component(this.redField);
enclosure.add$java_awt_Component(redFieldHolder);
l = Clazz.new_((I$[4]||$incl$(4)).c$$S,[greenString]);
l.setDisplayedMnemonic$I((I$[5]||$incl$(5)).getInt$O$I("ColorChooser.rgbGreenMnemonic", -1));
enclosure.add$java_awt_Component(l);
this.greenSlider = Clazz.new_((I$[6]||$incl$(6)).c$$I$I$I$I,[0, 0, 255, color.getGreen()]);
this.greenSlider.setMajorTickSpacing$I(85);
this.greenSlider.setMinorTickSpacing$I(17);
this.greenSlider.setPaintTicks$Z(true);
this.greenSlider.setPaintLabels$Z(true);
this.greenSlider.setInheritsPopupMenu$Z(true);
enclosure.add$java_awt_Component(this.greenSlider);
this.greenField = Clazz.new_((I$[7]||$incl$(7)).c$$javax_swing_SpinnerModel,[Clazz.new_((I$[8]||$incl$(8)).c$$I$I$I$I,[color.getGreen(), 0, 255, 1])]);
l.setLabelFor$java_awt_Component(this.greenSlider);
this.greenField.setInheritsPopupMenu$Z(true);
var greenFieldHolder = Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_LayoutManager,[Clazz.new_((I$[9]||$incl$(9)))]);
greenFieldHolder.add$java_awt_Component(this.greenField);
greenFieldHolder.setInheritsPopupMenu$Z(true);
this.greenField.addChangeListener$javax_swing_event_ChangeListener(this);
enclosure.add$java_awt_Component(greenFieldHolder);
l = Clazz.new_((I$[4]||$incl$(4)).c$$S,[blueString]);
l.setDisplayedMnemonic$I((I$[5]||$incl$(5)).getInt$O$I("ColorChooser.rgbBlueMnemonic", -1));
enclosure.add$java_awt_Component(l);
this.blueSlider = Clazz.new_((I$[6]||$incl$(6)).c$$I$I$I$I,[0, 0, 255, color.getBlue()]);
this.blueSlider.setMajorTickSpacing$I(85);
this.blueSlider.setMinorTickSpacing$I(17);
this.blueSlider.setPaintTicks$Z(true);
this.blueSlider.setPaintLabels$Z(true);
this.blueSlider.setInheritsPopupMenu$Z(true);
enclosure.add$java_awt_Component(this.blueSlider);
this.blueField = Clazz.new_((I$[7]||$incl$(7)).c$$javax_swing_SpinnerModel,[Clazz.new_((I$[8]||$incl$(8)).c$$I$I$I$I,[color.getBlue(), 0, 255, 1])]);
l.setLabelFor$java_awt_Component(this.blueSlider);
this.blueField.setInheritsPopupMenu$Z(true);
var blueFieldHolder = Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_LayoutManager,[Clazz.new_((I$[9]||$incl$(9)))]);
blueFieldHolder.add$java_awt_Component(this.blueField);
this.blueField.addChangeListener$javax_swing_event_ChangeListener(this);
blueFieldHolder.setInheritsPopupMenu$Z(true);
enclosure.add$java_awt_Component(blueFieldHolder);
this.redSlider.addChangeListener$javax_swing_event_ChangeListener(this);
this.greenSlider.addChangeListener$javax_swing_event_ChangeListener(this);
this.blueSlider.addChangeListener$javax_swing_event_ChangeListener(this);
this.redSlider.putClientProperty$O$O("JSlider.isFilled", (I$[10]||$incl$(10)).TRUE);
this.greenSlider.putClientProperty$O$O("JSlider.isFilled", (I$[10]||$incl$(10)).TRUE);
this.blueSlider.putClientProperty$O$O("JSlider.isFilled", (I$[10]||$incl$(10)).TRUE);
});

Clazz.newMeth(C$, 'uninstallChooserPanel$javax_swing_JColorChooser', function (enclosingChooser) {
C$.superclazz.prototype.uninstallChooserPanel$javax_swing_JColorChooser.apply(this, [enclosingChooser]);
this.removeAll();
});

Clazz.newMeth(C$, 'updateChooser', function () {
if (!this.isAdjusting) {
this.isAdjusting = true;
p$.setColor$java_awt_Color.apply(this, [this.getColorFromModel()]);
this.isAdjusting = false;
}});

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
if (Clazz.instanceOf(e.getSource(), "javax.swing.JSlider") && !this.isAdjusting ) {
var red = this.redSlider.getValue();
var green = this.greenSlider.getValue();
var blue = this.blueSlider.getValue();
var color = Clazz.new_((I$[11]||$incl$(11)).c$$I$I$I,[red, green, blue]);
this.getColorSelectionModel().setSelectedColor$java_awt_Color(color);
} else if (Clazz.instanceOf(e.getSource(), "javax.swing.JSpinner") && !this.isAdjusting ) {
var red = (this.redField.getValue()).intValue();
var green = (this.greenField.getValue()).intValue();
var blue = (this.blueField.getValue()).intValue();
var color = Clazz.new_((I$[11]||$incl$(11)).c$$I$I$I,[red, green, blue]);
this.getColorSelectionModel().setSelectedColor$java_awt_Color(color);
}});
})();
//Created 2018-02-08 10:02:46
