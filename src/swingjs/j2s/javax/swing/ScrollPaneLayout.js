(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.awt.Dimension','javax.swing.SwingUtilities','java.awt.Rectangle','java.awt.Insets','javax.swing.UIManager']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ScrollPaneLayout", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, ['java.awt.LayoutManager', 'javax.swing.ScrollPaneConstants']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.viewport = null;
this.vsb = null;
this.hsb = null;
this.rowHead = null;
this.colHead = null;
this.lowerLeft = null;
this.lowerRight = null;
this.upperLeft = null;
this.upperRight = null;
this.vsbPolicy = 0;
this.hsbPolicy = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.vsbPolicy = 20;
this.hsbPolicy = 30;
}, 1);

Clazz.newMeth(C$, 'syncWithScrollPane$javax_swing_JScrollPane', function (sp) {
this.viewport = sp.getViewport();
this.vsb = sp.getVerticalScrollBar();
this.hsb = sp.getHorizontalScrollBar();
this.rowHead = sp.getRowHeader();
this.colHead = sp.getColumnHeader();
this.lowerLeft = sp.getCorner$S("LOWER_LEFT_CORNER");
this.lowerRight = sp.getCorner$S("LOWER_RIGHT_CORNER");
this.upperLeft = sp.getCorner$S("UPPER_LEFT_CORNER");
this.upperRight = sp.getCorner$S("UPPER_RIGHT_CORNER");
this.vsbPolicy = sp.getVerticalScrollBarPolicy();
this.hsbPolicy = sp.getHorizontalScrollBarPolicy();
});

Clazz.newMeth(C$, 'addSingletonComponent$java_awt_Component$java_awt_Component', function (oldC, newC) {
if ((oldC != null ) && (oldC !== newC ) ) {
oldC.getParent().remove$java_awt_Component(oldC);
}return newC;
});

Clazz.newMeth(C$, 'addLayoutComponent$S$java_awt_Component', function (s, c) {
if (s.equals$O("VIEWPORT")) {
this.viewport = this.addSingletonComponent$java_awt_Component$java_awt_Component(this.viewport, c);
} else if (s.equals$O("VERTICAL_SCROLLBAR")) {
this.vsb = this.addSingletonComponent$java_awt_Component$java_awt_Component(this.vsb, c);
} else if (s.equals$O("HORIZONTAL_SCROLLBAR")) {
this.hsb = this.addSingletonComponent$java_awt_Component$java_awt_Component(this.hsb, c);
} else if (s.equals$O("ROW_HEADER")) {
this.rowHead = this.addSingletonComponent$java_awt_Component$java_awt_Component(this.rowHead, c);
} else if (s.equals$O("COLUMN_HEADER")) {
this.colHead = this.addSingletonComponent$java_awt_Component$java_awt_Component(this.colHead, c);
} else if (s.equals$O("LOWER_LEFT_CORNER")) {
this.lowerLeft = this.addSingletonComponent$java_awt_Component$java_awt_Component(this.lowerLeft, c);
} else if (s.equals$O("LOWER_RIGHT_CORNER")) {
this.lowerRight = this.addSingletonComponent$java_awt_Component$java_awt_Component(this.lowerRight, c);
} else if (s.equals$O("UPPER_LEFT_CORNER")) {
this.upperLeft = this.addSingletonComponent$java_awt_Component$java_awt_Component(this.upperLeft, c);
} else if (s.equals$O("UPPER_RIGHT_CORNER")) {
this.upperRight = this.addSingletonComponent$java_awt_Component$java_awt_Component(this.upperRight, c);
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["invalid layout key " + s]);
}});

Clazz.newMeth(C$, 'removeLayoutComponent$java_awt_Component', function (c) {
if (c === this.viewport ) {
this.viewport = null;
} else if (c === this.vsb ) {
this.vsb = null;
} else if (c === this.hsb ) {
this.hsb = null;
} else if (c === this.rowHead ) {
this.rowHead = null;
} else if (c === this.colHead ) {
this.colHead = null;
} else if (c === this.lowerLeft ) {
this.lowerLeft = null;
} else if (c === this.lowerRight ) {
this.lowerRight = null;
} else if (c === this.upperLeft ) {
this.upperLeft = null;
} else if (c === this.upperRight ) {
this.upperRight = null;
}});

Clazz.newMeth(C$, 'getVerticalScrollBarPolicy', function () {
return this.vsbPolicy;
});

Clazz.newMeth(C$, 'setVerticalScrollBarPolicy$I', function (x) {
switch (x) {
case 20:
case 21:
case 22:
this.vsbPolicy = x;
break;
default:
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["invalid verticalScrollBarPolicy"]);
}
});

Clazz.newMeth(C$, 'getHorizontalScrollBarPolicy', function () {
return this.hsbPolicy;
});

Clazz.newMeth(C$, 'setHorizontalScrollBarPolicy$I', function (x) {
switch (x) {
case 30:
case 31:
case 32:
this.hsbPolicy = x;
break;
default:
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["invalid horizontalScrollBarPolicy"]);
}
});

Clazz.newMeth(C$, 'getViewport', function () {
return this.viewport;
});

Clazz.newMeth(C$, 'getHorizontalScrollBar', function () {
return this.hsb;
});

Clazz.newMeth(C$, 'getVerticalScrollBar', function () {
return this.vsb;
});

Clazz.newMeth(C$, 'getRowHeader', function () {
return this.rowHead;
});

Clazz.newMeth(C$, 'getColumnHeader', function () {
return this.colHead;
});

Clazz.newMeth(C$, 'getCorner$S', function (key) {
if (key.equals$O("LOWER_LEFT_CORNER")) {
return this.lowerLeft;
} else if (key.equals$O("LOWER_RIGHT_CORNER")) {
return this.lowerRight;
} else if (key.equals$O("UPPER_LEFT_CORNER")) {
return this.upperLeft;
} else if (key.equals$O("UPPER_RIGHT_CORNER")) {
return this.upperRight;
} else {
return null;
}});

Clazz.newMeth(C$, 'preferredLayoutSize$java_awt_Container', function (parent) {
var scrollPane = parent;
this.vsbPolicy = scrollPane.getVerticalScrollBarPolicy();
this.hsbPolicy = scrollPane.getHorizontalScrollBarPolicy();
var insets = parent.getInsets();
var prefWidth = insets.left + insets.right;
var prefHeight = insets.top + insets.bottom;
var extentSize = null;
var viewSize = null;
var view = null;
if (this.viewport != null ) {
extentSize = this.viewport.getPreferredSize();
view = this.viewport.getView();
if (view != null ) {
viewSize = view.getPreferredSize();
}}if (extentSize != null ) {
prefWidth = prefWidth+(extentSize.width);
prefHeight = prefHeight+(extentSize.height);
}var viewportBorder = scrollPane.getViewportBorder();
if (viewportBorder != null ) {
var vpbInsets = viewportBorder.getBorderInsets$java_awt_Component(parent);
prefWidth = prefWidth+(vpbInsets.left + vpbInsets.right);
prefHeight = prefHeight+(vpbInsets.top + vpbInsets.bottom);
}if ((this.rowHead != null ) && this.rowHead.isVisible() ) {
prefWidth = prefWidth+(this.rowHead.getPreferredSize().width);
}if ((this.colHead != null ) && this.colHead.isVisible() ) {
prefHeight = prefHeight+(this.colHead.getPreferredSize().height);
}if ((this.vsb != null ) && (this.vsbPolicy != 21) ) {
if (this.vsbPolicy == 22) {
prefWidth = prefWidth+(this.vsb.getPreferredSize().width);
} else if ((viewSize != null ) && (extentSize != null ) ) {
var canScroll = true;
if (Clazz.instanceOf(view, "javax.swing.Scrollable")) {
canScroll = !(view).getScrollableTracksViewportHeight();
}if (canScroll && (viewSize.height > extentSize.height) ) {
prefWidth = prefWidth+(this.vsb.getPreferredSize().width);
}}}if ((this.hsb != null ) && (this.hsbPolicy != 31) ) {
if (this.hsbPolicy == 32) {
prefHeight = prefHeight+(this.hsb.getPreferredSize().height);
} else if ((viewSize != null ) && (extentSize != null ) ) {
var canScroll = true;
if (Clazz.instanceOf(view, "javax.swing.Scrollable")) {
canScroll = !(view).getScrollableTracksViewportWidth();
}if (canScroll && (viewSize.width > extentSize.width) ) {
prefHeight = prefHeight+(this.hsb.getPreferredSize().height);
}}}return Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[prefWidth, prefHeight]);
});

Clazz.newMeth(C$, 'minimumLayoutSize$java_awt_Container', function (parent) {
var scrollPane = parent;
this.vsbPolicy = scrollPane.getVerticalScrollBarPolicy();
this.hsbPolicy = scrollPane.getHorizontalScrollBarPolicy();
var insets = parent.getInsets();
var minWidth = insets.left + insets.right;
var minHeight = insets.top + insets.bottom;
if (this.viewport != null ) {
var size = this.viewport.getMinimumSize();
minWidth = minWidth+(size.width);
minHeight = minHeight+(size.height);
}var viewportBorder = scrollPane.getViewportBorder();
if (viewportBorder != null ) {
var vpbInsets = viewportBorder.getBorderInsets$java_awt_Component(parent);
minWidth = minWidth+(vpbInsets.left + vpbInsets.right);
minHeight = minHeight+(vpbInsets.top + vpbInsets.bottom);
}if ((this.rowHead != null ) && this.rowHead.isVisible() ) {
var size = this.rowHead.getMinimumSize();
minWidth = minWidth+(size.width);
minHeight = Math.max(minHeight, size.height);
}if ((this.colHead != null ) && this.colHead.isVisible() ) {
var size = this.colHead.getMinimumSize();
minWidth = Math.max(minWidth, size.width);
minHeight = minHeight+(size.height);
}if ((this.vsb != null ) && (this.vsbPolicy != 21) ) {
var size = this.vsb.getMinimumSize();
minWidth = minWidth+(size.width);
minHeight = Math.max(minHeight, size.height);
}if ((this.hsb != null ) && (this.hsbPolicy != 31) ) {
var size = this.hsb.getMinimumSize();
minWidth = Math.max(minWidth, size.width);
minHeight = minHeight+(size.height);
}return Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[minWidth, minHeight]);
});

Clazz.newMeth(C$, 'layoutContainer$java_awt_Container', function (parent) {
var scrollPane = parent;
this.vsbPolicy = scrollPane.getVerticalScrollBarPolicy();
this.hsbPolicy = scrollPane.getHorizontalScrollBarPolicy();
var availR = scrollPane.getBounds();
availR.x = availR.y = 0;
var insets = parent.getInsets();
availR.x = insets.left;
availR.y = insets.top;
availR.width = availR.width-(insets.left + insets.right);
availR.height = availR.height-(insets.top + insets.bottom);
var leftToRight = (I$[2]||$incl$(2)).isLeftToRight$java_awt_Component(scrollPane);
var colHeadR = Clazz.new_((I$[3]||$incl$(3)).c$$I$I$I$I,[0, availR.y, 0, 0]);
if ((this.colHead != null ) && (this.colHead.isVisible()) ) {
var colHeadHeight = Math.min(availR.height, this.colHead.getPreferredSize().height);
colHeadR.height = colHeadHeight;
availR.y = availR.y+(colHeadHeight);
availR.height = availR.height-(colHeadHeight);
}var rowHeadR = Clazz.new_((I$[3]||$incl$(3)).c$$I$I$I$I,[0, 0, 0, 0]);
if ((this.rowHead != null ) && (this.rowHead.isVisible()) ) {
var rowHeadWidth = Math.min(availR.width, this.rowHead.getPreferredSize().width);
rowHeadR.width = rowHeadWidth;
availR.width = availR.width-(rowHeadWidth);
if (leftToRight) {
rowHeadR.x = availR.x;
availR.x = availR.x+(rowHeadWidth);
} else {
rowHeadR.x = availR.x + availR.width;
}}var viewportBorder = scrollPane.getViewportBorder();
var vpbInsets;
if (viewportBorder != null ) {
vpbInsets = viewportBorder.getBorderInsets$java_awt_Component(parent);
availR.x = availR.x+(vpbInsets.left);
availR.y = availR.y+(vpbInsets.top);
availR.width = availR.width-(vpbInsets.left + vpbInsets.right);
availR.height = availR.height-(vpbInsets.top + vpbInsets.bottom);
} else {
vpbInsets = Clazz.new_((I$[4]||$incl$(4)).c$$I$I$I$I,[0, 0, 0, 0]);
}var view = (this.viewport != null ) ? this.viewport.getView() : null;
var viewPrefSize = (view != null ) ? view.getPreferredSize() : Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[0, 0]);
var extentSize = (this.viewport != null ) ? this.viewport.toViewCoordinates$java_awt_Dimension(availR.getSize()) : Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[0, 0]);
var viewTracksViewportWidth = false;
var viewTracksViewportHeight = false;
var isEmpty = (availR.width < 0 || availR.height < 0 );
var sv;
if (!isEmpty && Clazz.instanceOf(view, "javax.swing.Scrollable") ) {
sv = view;
viewTracksViewportWidth = sv.getScrollableTracksViewportWidth();
viewTracksViewportHeight = sv.getScrollableTracksViewportHeight();
} else {
sv = null;
}var vsbR = Clazz.new_((I$[3]||$incl$(3)).c$$I$I$I$I,[0, availR.y - vpbInsets.top, 0, 0]);
var vsbNeeded;
if (isEmpty) {
vsbNeeded = false;
} else if (this.vsbPolicy == 22) {
vsbNeeded = true;
} else if (this.vsbPolicy == 21) {
vsbNeeded = false;
} else {
vsbNeeded = !viewTracksViewportHeight && (viewPrefSize.height > extentSize.height) ;
}if ((this.vsb != null ) && vsbNeeded ) {
p$.adjustForVSB$Z$java_awt_Rectangle$java_awt_Rectangle$java_awt_Insets$Z.apply(this, [true, availR, vsbR, vpbInsets, leftToRight]);
extentSize = this.viewport.toViewCoordinates$java_awt_Dimension(availR.getSize());
}var hsbR = Clazz.new_((I$[3]||$incl$(3)).c$$I$I$I$I,[availR.x - vpbInsets.left, 0, 0, 0]);
var hsbNeeded;
if (isEmpty) {
hsbNeeded = false;
} else if (this.hsbPolicy == 32) {
hsbNeeded = true;
} else if (this.hsbPolicy == 31) {
hsbNeeded = false;
} else {
hsbNeeded = !viewTracksViewportWidth && (viewPrefSize.width > extentSize.width) ;
}if ((this.hsb != null ) && hsbNeeded ) {
p$.adjustForHSB$Z$java_awt_Rectangle$java_awt_Rectangle$java_awt_Insets.apply(this, [true, availR, hsbR, vpbInsets]);
if ((this.vsb != null ) && !vsbNeeded && (this.vsbPolicy != 21)  ) {
extentSize = this.viewport.toViewCoordinates$java_awt_Dimension(availR.getSize());
vsbNeeded = viewPrefSize.height > extentSize.height;
if (vsbNeeded) {
p$.adjustForVSB$Z$java_awt_Rectangle$java_awt_Rectangle$java_awt_Insets$Z.apply(this, [true, availR, vsbR, vpbInsets, leftToRight]);
}}}if (this.viewport != null ) {
this.viewport.setBounds$java_awt_Rectangle(availR);
if (sv != null ) {
extentSize = this.viewport.toViewCoordinates$java_awt_Dimension(availR.getSize());
var oldHSBNeeded = hsbNeeded;
var oldVSBNeeded = vsbNeeded;
viewTracksViewportWidth = sv.getScrollableTracksViewportWidth();
viewTracksViewportHeight = sv.getScrollableTracksViewportHeight();
if (this.vsb != null  && this.vsbPolicy == 20 ) {
var newVSBNeeded = !viewTracksViewportHeight && (viewPrefSize.height > extentSize.height) ;
if (newVSBNeeded != vsbNeeded ) {
vsbNeeded = newVSBNeeded;
p$.adjustForVSB$Z$java_awt_Rectangle$java_awt_Rectangle$java_awt_Insets$Z.apply(this, [vsbNeeded, availR, vsbR, vpbInsets, leftToRight]);
extentSize = this.viewport.toViewCoordinates$java_awt_Dimension(availR.getSize());
}}if (this.hsb != null  && this.hsbPolicy == 30 ) {
var newHSBbNeeded = !viewTracksViewportWidth && (viewPrefSize.width > extentSize.width) ;
if (newHSBbNeeded != hsbNeeded ) {
hsbNeeded = newHSBbNeeded;
p$.adjustForHSB$Z$java_awt_Rectangle$java_awt_Rectangle$java_awt_Insets.apply(this, [hsbNeeded, availR, hsbR, vpbInsets]);
if ((this.vsb != null ) && !vsbNeeded && (this.vsbPolicy != 21)  ) {
extentSize = this.viewport.toViewCoordinates$java_awt_Dimension(availR.getSize());
vsbNeeded = viewPrefSize.height > extentSize.height;
if (vsbNeeded) {
p$.adjustForVSB$Z$java_awt_Rectangle$java_awt_Rectangle$java_awt_Insets$Z.apply(this, [true, availR, vsbR, vpbInsets, leftToRight]);
}}}}if (oldHSBNeeded != hsbNeeded  || oldVSBNeeded != vsbNeeded  ) {
this.viewport.setBounds$java_awt_Rectangle(availR);
}}}vsbR.height = availR.height + vpbInsets.top + vpbInsets.bottom ;
hsbR.width = availR.width + vpbInsets.left + vpbInsets.right ;
rowHeadR.height = availR.height + vpbInsets.top + vpbInsets.bottom ;
rowHeadR.y = availR.y - vpbInsets.top;
colHeadR.width = availR.width + vpbInsets.left + vpbInsets.right ;
colHeadR.x = availR.x - vpbInsets.left;
if (this.rowHead != null ) {
this.rowHead.setBounds$java_awt_Rectangle(rowHeadR);
}if (this.colHead != null ) {
this.colHead.setBounds$java_awt_Rectangle(colHeadR);
}if (this.vsb != null ) {
if (vsbNeeded) {
if (this.colHead != null  && (I$[5]||$incl$(5)).getBoolean$O("ScrollPane.fillUpperCorner") ) {
if ((leftToRight && this.upperRight == null  ) || (!leftToRight && this.upperLeft == null  ) ) {
vsbR.y = colHeadR.y;
vsbR.height = vsbR.height+(colHeadR.height);
}}this.vsb.setVisible$Z(true);
this.vsb.setBounds$java_awt_Rectangle(vsbR);
} else {
this.vsb.setVisible$Z(false);
}}if (this.hsb != null ) {
if (hsbNeeded) {
if (this.rowHead != null  && (I$[5]||$incl$(5)).getBoolean$O("ScrollPane.fillLowerCorner") ) {
if ((leftToRight && this.lowerLeft == null  ) || (!leftToRight && this.lowerRight == null  ) ) {
if (leftToRight) {
hsbR.x = rowHeadR.x;
}hsbR.width = hsbR.width+(rowHeadR.width);
}}this.hsb.setVisible$Z(true);
this.hsb.setBounds$java_awt_Rectangle(hsbR);
} else {
this.hsb.setVisible$Z(false);
}}if (this.lowerLeft != null ) {
this.lowerLeft.setBounds$I$I$I$I(leftToRight ? rowHeadR.x : vsbR.x, hsbR.y, leftToRight ? rowHeadR.width : vsbR.width, hsbR.height);
}if (this.lowerRight != null ) {
this.lowerRight.setBounds$I$I$I$I(leftToRight ? vsbR.x : rowHeadR.x, hsbR.y, leftToRight ? vsbR.width : rowHeadR.width, hsbR.height);
}if (this.upperLeft != null ) {
this.upperLeft.setBounds$I$I$I$I(leftToRight ? rowHeadR.x : vsbR.x, colHeadR.y, leftToRight ? rowHeadR.width : vsbR.width, colHeadR.height);
}if (this.upperRight != null ) {
this.upperRight.setBounds$I$I$I$I(leftToRight ? vsbR.x : rowHeadR.x, colHeadR.y, leftToRight ? vsbR.width : rowHeadR.width, colHeadR.height);
}});

Clazz.newMeth(C$, 'adjustForVSB$Z$java_awt_Rectangle$java_awt_Rectangle$java_awt_Insets$Z', function (wantsVSB, available, vsbR, vpbInsets, leftToRight) {
var oldWidth = vsbR.width;
if (wantsVSB) {
var vsbWidth = Math.max(0, Math.min(this.vsb.getPreferredSize().width, available.width));
available.width = available.width-(vsbWidth);
vsbR.width = vsbWidth;
if (leftToRight) {
vsbR.x = available.x + available.width + vpbInsets.right ;
} else {
vsbR.x = available.x - vpbInsets.left;
available.x = available.x+(vsbWidth);
}} else {
available.width = available.width+(oldWidth);
}});

Clazz.newMeth(C$, 'adjustForHSB$Z$java_awt_Rectangle$java_awt_Rectangle$java_awt_Insets', function (wantsHSB, available, hsbR, vpbInsets) {
var oldHeight = hsbR.height;
if (wantsHSB) {
var hsbHeight = Math.max(0, Math.min(available.height, this.hsb.getPreferredSize().height));
available.height = available.height-(hsbHeight);
hsbR.y = available.y + available.height + vpbInsets.bottom ;
hsbR.height = hsbHeight;
} else {
available.height = available.height+(oldHeight);
}});

Clazz.newMeth(C$, 'getViewportBorderBounds$javax_swing_JScrollPane', function (scrollpane) {
return scrollpane.getViewportBorderBounds();
});
;
(function(){var C$=Clazz.newClass(P$.ScrollPaneLayout, "UIResource", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.ScrollPaneLayout', 'javax.swing.plaf.UIResource');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:39
