(function(){var P$=Clazz.newPackage("javax.swing.tree"),I$=[['sun.swing.DefaultLookup','javax.swing.border.EmptyBorder','java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DefaultTreeCellRenderer", null, 'javax.swing.JLabel', 'javax.swing.tree.TreeCellRenderer');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.tree = null;
this.selected = false;
this.$hasFocus = false;
this.drawsFocusBorderAroundIcon = false;
this.drawDashedFocusIndicator = false;
this.treeBGColor = null;
this.focusBGColor = null;
this.closedIcon = null;
this.leafIcon = null;
this.openIcon = null;
this.textSelectionColor = null;
this.textNonSelectionColor = null;
this.backgroundSelectionColor = null;
this.backgroundNonSelectionColor = null;
this.borderSelectionColor = null;
this.isDropCell = false;
this.fillBackground = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.fillBackground = true;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.setLeafIcon$javax_swing_Icon((I$[1]||$incl$(1)).getIcon$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.leafIcon"));
this.setClosedIcon$javax_swing_Icon((I$[1]||$incl$(1)).getIcon$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.closedIcon"));
this.setOpenIcon$javax_swing_Icon((I$[1]||$incl$(1)).getIcon$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.openIcon"));
this.setTextSelectionColor$java_awt_Color((I$[1]||$incl$(1)).getColor$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.selectionForeground"));
this.setTextNonSelectionColor$java_awt_Color((I$[1]||$incl$(1)).getColor$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.textForeground"));
this.setBackgroundSelectionColor$java_awt_Color((I$[1]||$incl$(1)).getColor$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.selectionBackground"));
this.setBackgroundNonSelectionColor$java_awt_Color((I$[1]||$incl$(1)).getColor$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.textBackground"));
this.setBorderSelectionColor$java_awt_Color((I$[1]||$incl$(1)).getColor$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.selectionBorderColor"));
this.drawsFocusBorderAroundIcon = (I$[1]||$incl$(1)).getBoolean$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S$Z(this, this.ui, "Tree.drawsFocusBorderAroundIcon", false);
this.drawDashedFocusIndicator = (I$[1]||$incl$(1)).getBoolean$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S$Z(this, this.ui, "Tree.drawDashedFocusIndicator", false);
this.fillBackground = (I$[1]||$incl$(1)).getBoolean$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S$Z(this, this.ui, "Tree.rendererFillBackground", true);
var margins = (I$[1]||$incl$(1)).getInsets$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.rendererMargins");
if (margins != null ) {
this.setBorder$javax_swing_border_Border(Clazz.new_((I$[2]||$incl$(2)).c$$I$I$I$I,[margins.top, margins.left, margins.bottom, margins.right]));
}this.setName$S("Tree.cellRenderer");
}, 1);

Clazz.newMeth(C$, 'getDefaultOpenIcon', function () {
return (I$[1]||$incl$(1)).getIcon$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.openIcon");
});

Clazz.newMeth(C$, 'getDefaultClosedIcon', function () {
return (I$[1]||$incl$(1)).getIcon$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.closedIcon");
});

Clazz.newMeth(C$, 'getDefaultLeafIcon', function () {
return (I$[1]||$incl$(1)).getIcon$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.leafIcon");
});

Clazz.newMeth(C$, 'setOpenIcon$javax_swing_Icon', function (newIcon) {
this.openIcon = newIcon;
});

Clazz.newMeth(C$, 'getOpenIcon', function () {
return this.openIcon;
});

Clazz.newMeth(C$, 'setClosedIcon$javax_swing_Icon', function (newIcon) {
this.closedIcon = newIcon;
});

Clazz.newMeth(C$, 'getClosedIcon', function () {
return this.closedIcon;
});

Clazz.newMeth(C$, 'setLeafIcon$javax_swing_Icon', function (newIcon) {
this.leafIcon = newIcon;
});

Clazz.newMeth(C$, 'getLeafIcon', function () {
return this.leafIcon;
});

Clazz.newMeth(C$, 'setTextSelectionColor$java_awt_Color', function (newColor) {
this.textSelectionColor = newColor;
});

Clazz.newMeth(C$, 'getTextSelectionColor', function () {
return this.textSelectionColor;
});

Clazz.newMeth(C$, 'setTextNonSelectionColor$java_awt_Color', function (newColor) {
this.textNonSelectionColor = newColor;
});

Clazz.newMeth(C$, 'getTextNonSelectionColor', function () {
return this.textNonSelectionColor;
});

Clazz.newMeth(C$, 'setBackgroundSelectionColor$java_awt_Color', function (newColor) {
this.backgroundSelectionColor = newColor;
});

Clazz.newMeth(C$, 'getBackgroundSelectionColor', function () {
return this.backgroundSelectionColor;
});

Clazz.newMeth(C$, 'setBackgroundNonSelectionColor$java_awt_Color', function (newColor) {
this.backgroundNonSelectionColor = newColor;
});

Clazz.newMeth(C$, 'getBackgroundNonSelectionColor', function () {
return this.backgroundNonSelectionColor;
});

Clazz.newMeth(C$, 'setBorderSelectionColor$java_awt_Color', function (newColor) {
this.borderSelectionColor = newColor;
});

Clazz.newMeth(C$, 'getBorderSelectionColor', function () {
return this.borderSelectionColor;
});

Clazz.newMeth(C$, 'setFont$java_awt_Font', function (font) {
if (Clazz.instanceOf(font, "javax.swing.plaf.FontUIResource")) font = null;
C$.superclazz.prototype.setFont$java_awt_Font.apply(this, [font]);
});

Clazz.newMeth(C$, 'getFont', function () {
var font = C$.superclazz.prototype.getFont.apply(this, []);
if (font == null  && this.tree != null  ) {
font = this.tree.getFont();
}return font;
});

Clazz.newMeth(C$, 'setBackground$java_awt_Color', function (color) {
if (Clazz.instanceOf(color, "javax.swing.plaf.ColorUIResource")) color = null;
C$.superclazz.prototype.setBackground$java_awt_Color.apply(this, [color]);
});

Clazz.newMeth(C$, 'getTreeCellRendererComponent$javax_swing_JTree$O$Z$Z$Z$I$Z', function (tree, value, sel, expanded, leaf, row, hasFocus) {
var stringValue = tree.convertValueToText$O$Z$Z$Z$I$Z(value, sel, expanded, leaf, row, hasFocus);
this.tree = tree;
this.$hasFocus = hasFocus;
this.setText$S(stringValue);
var fg = null;
this.isDropCell = false;
if (sel) {
fg = this.getTextSelectionColor();
} else {
fg = this.getTextNonSelectionColor();
}this.setForeground$java_awt_Color(fg);
var icon = null;
if (leaf) {
icon = this.getLeafIcon();
} else if (expanded) {
icon = this.getOpenIcon();
} else {
icon = this.getClosedIcon();
}if (!tree.isEnabled()) {
this.setEnabled$Z(false);
this.setDisabledIcon$javax_swing_Icon(icon);
} else {
this.setEnabled$Z(true);
this.setIcon$javax_swing_Icon(icon);
}this.setComponentOrientation$java_awt_ComponentOrientation(tree.getComponentOrientation());
this.selected = sel;
return this;
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
var bColor;
if (this.isDropCell) {
bColor = (I$[1]||$incl$(1)).getColor$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this, this.ui, "Tree.dropCellBackground");
if (bColor == null ) {
bColor = this.getBackgroundSelectionColor();
}} else if (this.selected) {
bColor = this.getBackgroundSelectionColor();
} else {
bColor = this.getBackgroundNonSelectionColor();
if (bColor == null ) {
bColor = this.getBackground();
}}var imageOffset = -1;
if (bColor != null  && this.fillBackground ) {
imageOffset = p$.getLabelStart.apply(this, []);
g.setColor$java_awt_Color(bColor);
if (this.getComponentOrientation().isLeftToRight()) {
g.fillRect$I$I$I$I(imageOffset, 0, this.getWidth() - imageOffset, this.getHeight());
} else {
g.fillRect$I$I$I$I(0, 0, this.getWidth() - imageOffset, this.getHeight());
}}if (this.$hasFocus) {
if (this.drawsFocusBorderAroundIcon) {
imageOffset = 0;
} else if (imageOffset == -1) {
imageOffset = p$.getLabelStart.apply(this, []);
}if (this.getComponentOrientation().isLeftToRight()) {
p$.paintFocus$java_awt_Graphics$I$I$I$I$java_awt_Color.apply(this, [g, imageOffset, 0, this.getWidth() - imageOffset, this.getHeight(), bColor]);
} else {
p$.paintFocus$java_awt_Graphics$I$I$I$I$java_awt_Color.apply(this, [g, 0, 0, this.getWidth() - imageOffset, this.getHeight(), bColor]);
}}C$.superclazz.prototype.paint$java_awt_Graphics.apply(this, [g]);
});

Clazz.newMeth(C$, 'paintFocus$java_awt_Graphics$I$I$I$I$java_awt_Color', function (g, x, y, w, h, notColor) {
var bsColor = this.getBorderSelectionColor();
if (bsColor != null  && (this.selected || !this.drawDashedFocusIndicator ) ) {
g.setColor$java_awt_Color(bsColor);
g.drawRect$I$I$I$I(x, y, w - 1, h - 1);
}});

Clazz.newMeth(C$, 'getLabelStart', function () {
var currentI = this.getIcon();
if (currentI != null  && this.getText() != null  ) {
return currentI.getIconWidth() + Math.max(0, this.getIconTextGap() - 1);
}return 0;
});

Clazz.newMeth(C$, 'getPreferredSize', function () {
var retDimension = this.getPrefSizeJComp();
return (retDimension == null  ? null : Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[retDimension.width + 3, retDimension.height]));
});

Clazz.newMeth(C$, 'validate', function () {
});

Clazz.newMeth(C$, 'invalidate', function () {
});

Clazz.newMeth(C$, 'revalidate', function () {
});

Clazz.newMeth(C$, 'repaint$J$I$I$I$I', function (tm, x, y, width, height) {
});

Clazz.newMeth(C$, 'repaint$java_awt_Rectangle', function (r) {
});

Clazz.newMeth(C$, 'repaint', function () {
});

Clazz.newMeth(C$, 'firePropertyChange$S$O$O', function (propertyName, oldValue, newValue) {
});

Clazz.newMeth(C$, 'firePropertyChange$S$B$B', function (propertyName, oldValue, newValue) {
});

Clazz.newMeth(C$, 'firePropertyChange$S$C$C', function (propertyName, oldValue, newValue) {
});

Clazz.newMeth(C$, 'firePropertyChange$S$H$H', function (propertyName, oldValue, newValue) {
});

Clazz.newMeth(C$, 'firePropertyChange$S$I$I', function (propertyName, oldValue, newValue) {
});

Clazz.newMeth(C$, 'firePropertyChange$S$J$J', function (propertyName, oldValue, newValue) {
});

Clazz.newMeth(C$, 'firePropertyChange$S$F$F', function (propertyName, oldValue, newValue) {
});

Clazz.newMeth(C$, 'firePropertyChange$S$D$D', function (propertyName, oldValue, newValue) {
});

Clazz.newMeth(C$, 'firePropertyChange$S$Z$Z', function (propertyName, oldValue, newValue) {
});
})();
//Created 2018-02-08 10:03:00
