(function(){var P$=Clazz.newPackage("sun.swing"),I$=[['java.awt.font.FontRenderContext','sun.swing.StringUIClientPropertyKey','java.awt.RenderingHints','javax.swing.SwingUtilities','java.lang.StringBuffer','javax.swing.UIManager',['sun.swing.SwingUtilities2','.Section']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SwingUtilities2", function(){
Clazz.newInstance(this, arguments,0,C$);
});
C$.LAF_STATE_KEY = null;
C$.DEFAULT_FRC = null;
C$.AA_TEXT_PROPERTY_KEY = null;
C$.COMPONENT_UI_PROPERTY_KEY = null;
C$.BASICMENUITEMUI_MAX_TEXT_OFFSET = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.LAF_STATE_KEY =  Clazz.new_();
C$.DEFAULT_FRC = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_geom_AffineTransform$Z$Z,[null, false, false]);
C$.AA_TEXT_PROPERTY_KEY =  Clazz.new_();
C$.COMPONENT_UI_PROPERTY_KEY =  Clazz.new_();
C$.BASICMENUITEMUI_MAX_TEXT_OFFSET = Clazz.new_((I$[2]||$incl$(2)).c$$S,["maxTextOffset"]);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'drawTextAntialiased$javax_swing_JComponent', function (c) {
if (c != null ) {
return c.getClientProperty$O(C$.AA_TEXT_PROPERTY_KEY);
}return null;
}, 1);

Clazz.newMeth(C$, 'getLeftSideBearing$javax_swing_JComponent$java_awt_FontMetrics$S', function (c, fm, string) {
if ((string == null ) || (string.length$() == 0) ) {
return 0;
}return C$.getLeftSideBearing$javax_swing_JComponent$java_awt_FontMetrics$C(c, fm, string.charAt(0));
}, 1);

Clazz.newMeth(C$, 'getLeftSideBearing$javax_swing_JComponent$java_awt_FontMetrics$C', function (c, fm, firstChar) {
return 0;
}, 1);

Clazz.newMeth(C$, 'getRightSideBearing$javax_swing_JComponent$java_awt_FontMetrics$S', function (c, fm, string) {
if ((string == null ) || (string.length$() == 0) ) {
return 0;
}return C$.getRightSideBearing$javax_swing_JComponent$java_awt_FontMetrics$C(c, fm, string.charAt(string.length$() - 1));
}, 1);

Clazz.newMeth(C$, 'getRightSideBearing$javax_swing_JComponent$java_awt_FontMetrics$C', function (c, fm, lastChar) {
return 0;
}, 1);

Clazz.newMeth(C$, 'getFontMetrics$javax_swing_JComponent$java_awt_Graphics', function (c, g) {
if (c != null ) {
return c.getFontMetrics$java_awt_Font(g.getFont());
}return g.getFont().getFontMetrics();
}, 1);

Clazz.newMeth(C$, 'getFontMetrics$javax_swing_JComponent$java_awt_Graphics$java_awt_Font', function (c, g, font) {
if (c != null ) {
return c.getFontMetrics$java_awt_Font(font);
}return font.getFontMetrics();
}, 1);

Clazz.newMeth(C$, 'stringWidth$javax_swing_JComponent$java_awt_FontMetrics$S', function (c, fm, string) {
if (string == null  || string.equals$O("") ) {
return 0;
}return fm.stringWidth$S(string);
}, 1);

Clazz.newMeth(C$, 'drawString$javax_swing_JComponent$java_awt_Graphics$S$I$I', function (c, g, text, x, y) {
if (text == null  || text.length$() <= 0 ) {
return;
}var info = C$.drawTextAntialiased$javax_swing_JComponent(c);
if (info != null  && (Clazz.instanceOf(g, "java.awt.Graphics2D")) ) {
var g2 = g;
var oldContrast = null;
var oldAAValue = g2.getRenderingHint$java_awt_RenderingHints_Key((I$[3]||$incl$(3)).KEY_TEXT_ANTIALIASING);
if (info.aaHint !== oldAAValue ) {
g2.setRenderingHint$java_awt_RenderingHints_Key$O((I$[3]||$incl$(3)).KEY_TEXT_ANTIALIASING, info.aaHint);
} else {
oldAAValue = null;
}if (info.lcdContrastHint != null ) {
oldContrast = g2.getRenderingHint$java_awt_RenderingHints_Key((I$[3]||$incl$(3)).KEY_TEXT_LCD_CONTRAST);
if (info.lcdContrastHint.equals(oldContrast)) {
oldContrast = null;
} else {
g2.setRenderingHint$java_awt_RenderingHints_Key$O((I$[3]||$incl$(3)).KEY_TEXT_LCD_CONTRAST, info.lcdContrastHint);
}}g.drawString$S$I$I(text, x, y);
if (oldAAValue != null ) {
g2.setRenderingHint$java_awt_RenderingHints_Key$O((I$[3]||$incl$(3)).KEY_TEXT_ANTIALIASING, oldAAValue);
}if (oldContrast != null ) {
g2.setRenderingHint$java_awt_RenderingHints_Key$O((I$[3]||$incl$(3)).KEY_TEXT_LCD_CONTRAST, oldContrast);
}} else {
g.drawString$S$I$I(text, x, y);
}}, 1);

Clazz.newMeth(C$, 'loc2IndexFileList$javax_swing_JList$java_awt_Point', function (list, point) {
var index = list.locationToIndex$java_awt_Point(point);
if (index != -1) {
var bySize = list.getClientProperty$O("List.isFileList");
if (Clazz.instanceOf(bySize, "java.lang.Boolean") && (bySize).booleanValue() && !C$.pointIsInActualBounds$javax_swing_JList$I$java_awt_Point(list, index, point)  ) {
index = -1;
}}return index;
}, 1);

Clazz.newMeth(C$, 'pointIsInActualBounds$javax_swing_JList$I$java_awt_Point', function (list, index, point) {
var renderer = list.getCellRenderer();
var dataModel = list.getModel();
var value = dataModel.getElementAt$I(index);
var item = renderer.getListCellRendererComponent$javax_swing_JList$O$I$Z$Z(list, value, index, false, false);
var itemSize = item.getPreferredSize();
var cellBounds = list.getCellBounds$I$I(index, index);
if (!item.getComponentOrientation().isLeftToRight()) {
cellBounds.x = cellBounds.x+((cellBounds.width - itemSize.width));
}cellBounds.width = itemSize.width;
return cellBounds.contains$java_awt_Point(point);
}, 1);

Clazz.newMeth(C$, 'pointOutsidePrefSize$javax_swing_JTable$I$I$java_awt_Point', function (table, row, column, p) {
if (table.convertColumnIndexToModel$I(column) != 0 || row == -1 ) {
return true;
}var tcr = table.getCellRenderer$I$I(row, column);
var value = table.getValueAt$I$I(row, column);
var cell = tcr.getTableCellRendererComponent$javax_swing_JTable$O$Z$Z$I$I(table, value, false, false, row, column);
var itemSize = cell.getPreferredSize();
var cellBounds = table.getCellRect$I$I$Z(row, column, false);
cellBounds.width = itemSize.width;
cellBounds.height = itemSize.height;
if (p.x > cellBounds.x + cellBounds.width || p.y > cellBounds.y + cellBounds.height ) {
return true;
}return false;
}, 1);

Clazz.newMeth(C$, 'setLeadAnchorWithoutSelection$javax_swing_ListSelectionModel$I$I', function (model, lead, anchor) {
if (anchor == -1) {
anchor = lead;
}if (lead == -1) {
model.setAnchorSelectionIndex$I(-1);
model.setLeadSelectionIndex$I(-1);
} else {
if (model.isSelectedIndex$I(lead)) {
model.addSelectionInterval$I$I(lead, lead);
} else {
model.removeSelectionInterval$I$I(lead, lead);
}model.setAnchorSelectionIndex$I(anchor);
}}, 1);

Clazz.newMeth(C$, 'shouldIgnore$java_awt_event_MouseEvent$javax_swing_JComponent', function (me, c) {
return c == null  || !c.isEnabled()  || !(I$[4]||$incl$(4)).isLeftMouseButton$java_awt_event_MouseEvent(me)  || me.isConsumed() ;
}, 1);

Clazz.newMeth(C$, 'adjustFocus$javax_swing_JComponent', function (c) {
if (!c.hasFocus() && c.isRequestFocusEnabled() ) {
c.requestFocus();
}}, 1);

Clazz.newMeth(C$, 'getGraphics2D$java_awt_Graphics', function (g) {
if (Clazz.instanceOf(g, "java.awt.Graphics2D")) {
return g;
} else {
return null;
}}, 1);

Clazz.newMeth(C$, 'isPrinting$java_awt_Graphics', function (g) {
return false;
}, 1);

Clazz.newMeth(C$, 'useSelectedTextColor$javax_swing_text_Highlighter_Highlight$javax_swing_text_JTextComponent', function (h, c) {
var painter = h.getPainter();
var painterClass = painter.getClass().getName();
if (painterClass.indexOf("javax.swing.text.DefaultHighlighter") != 0 && painterClass.indexOf("com.sun.java.swing.plaf.windows.WindowsTextUI") != 0 ) {
return false;
}try {
var defPainter = painter;
if (defPainter.getColor() != null  && !defPainter.getColor().equals$O(c.getSelectionColor()) ) {
return false;
}} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.ClassCastException")){
return false;
} else {
throw e;
}
}
return true;
}, 1);

Clazz.newMeth(C$, 'canAccessSystemClipboard', function () {
var canAccess = false;
return canAccess;
}, 1);

Clazz.newMeth(C$, 'canCurrentEventAccessSystemClipboard', function () {
return false;
}, 1);

Clazz.newMeth(C$, 'displayPropertiesToCSS$java_awt_Font$java_awt_Color', function (font, fg) {
var rule = Clazz.new_((I$[5]||$incl$(5)).c$$S,["body {"]);
if (font != null ) {
rule.append$S(" font-family: ");
rule.append$S(font.getFamily());
rule.append$S(" ; ");
rule.append$S(" font-size: ");
rule.append$S("" + font.getSize());
rule.append$S("pt ;");
if (font.isBold()) {
rule.append$S(" font-weight: 700 ; ");
}if (font.isItalic()) {
rule.append$S(" font-style: italic ; ");
}}if (fg != null ) {
rule.append$S(" color: #");
if (fg.getRed() < 16) {
rule.append$C("0");
}rule.append$S(Integer.toHexString(fg.getRed()));
if (fg.getGreen() < 16) {
rule.append$C("0");
}rule.append$S(Integer.toHexString(fg.getGreen()));
if (fg.getBlue() < 16) {
rule.append$C("0");
}rule.append$S(Integer.toHexString(fg.getBlue()));
rule.append$S(" ; ");
}rule.append$S(" }");
return rule.toString();
}, 1);

Clazz.newMeth(C$, 'makeIcon$Class$Class$S', function (baseClass, rootClass, imageFile) {
return null;
}, 1);

Clazz.newMeth(C$, 'isLocalDisplay', function () {
return true;
}, 1);

Clazz.newMeth(C$, 'getUIDefaultsInt$O', function (key) {
return C$.getUIDefaultsInt$O$I(key, 0);
}, 1);

Clazz.newMeth(C$, 'getUIDefaultsInt$O$java_util_Locale', function (key, l) {
return C$.getUIDefaultsInt$O$java_util_Locale$I(key, l, 0);
}, 1);

Clazz.newMeth(C$, 'getUIDefaultsInt$O$I', function (key, defaultValue) {
return C$.getUIDefaultsInt$O$java_util_Locale$I(key, null, defaultValue);
}, 1);

Clazz.newMeth(C$, 'getUIDefaultsInt$O$java_util_Locale$I', function (key, l, defaultValue) {
var value = (I$[6]||$incl$(6)).get$O$java_util_Locale(key, l);
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

Clazz.newMeth(C$, 'liesIn$java_awt_Rectangle$java_awt_Point$Z$Z$Z', function (rect, p, horizontal, ltr, three) {
var p0;
var pComp;
var length;
var forward;
if (horizontal) {
p0 = rect.x;
pComp = p.x;
length = rect.width;
forward = ltr;
} else {
p0 = rect.y;
pComp = p.y;
length = rect.height;
forward = true;
}if (three) {
var boundary = (length >= 30) ? 10 : (length/3|0);
if (pComp < p0 + boundary) {
return forward ? (I$[7]||$incl$(7)).LEADING : (I$[7]||$incl$(7)).TRAILING;
} else if (pComp >= p0 + length - boundary) {
return forward ? (I$[7]||$incl$(7)).TRAILING : (I$[7]||$incl$(7)).LEADING;
}return (I$[7]||$incl$(7)).MIDDLE;
} else {
var middle = p0 + (length/2|0);
if (forward) {
return pComp >= middle ? (I$[7]||$incl$(7)).TRAILING : (I$[7]||$incl$(7)).LEADING;
} else {
return pComp < middle ? (I$[7]||$incl$(7)).TRAILING : (I$[7]||$incl$(7)).LEADING;
}}}, 1);

Clazz.newMeth(C$, 'liesInHorizontal$java_awt_Rectangle$java_awt_Point$Z$Z', function (rect, p, ltr, three) {
return C$.liesIn$java_awt_Rectangle$java_awt_Point$Z$Z$Z(rect, p, true, ltr, three);
}, 1);

Clazz.newMeth(C$, 'liesInVertical$java_awt_Rectangle$java_awt_Point$Z', function (rect, p, three) {
return C$.liesIn$java_awt_Rectangle$java_awt_Point$Z$Z$Z(rect, p, false, false, three);
}, 1);

Clazz.newMeth(C$, 'compositeRequestFocus$java_awt_Component', function (editorComponent) {
}, 1);
;
(function(){var C$=Clazz.newClass(P$.SwingUtilities2, "Section", null, 'Enum');

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "LEADING", 0, []);
Clazz.newEnumConst($vals, C$.c$, "MIDDLE", 1, []);
Clazz.newEnumConst($vals, C$.c$, "TRAILING", 2, []);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})()
;
(function(){var C$=Clazz.newClass(P$.SwingUtilities2, "AATextInfo", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.aaHint = null;
this.lcdContrastHint = null;
this.frc = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getAATextInfo$Z', function (lafCondition) {
return null;
}, 1);

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:23
