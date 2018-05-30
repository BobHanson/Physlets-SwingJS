(function(){var P$=Clazz.newPackage("javax.swing.table"),I$=[['java.awt.Rectangle','java.awt.event.MouseEvent','javax.swing.SwingUtilities','javax.swing.table.DefaultTableColumnModel','sun.swing.table.DefaultTableCellHeaderRenderer']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JTableHeader", null, 'javax.swing.JComponent', 'javax.swing.event.TableColumnModelListener');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.table = null;
this.columnModel = null;
this.reorderingAllowed = false;
this.resizingAllowed = false;
this.updateTableInRealTime = false;
this.resizingColumn = null;
this.draggedColumn = null;
this.draggedDistance = 0;
this.defaultRenderer = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$javax_swing_table_TableColumnModel.apply(this, [null]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_table_TableColumnModel', function (cm) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
if (cm == null ) cm=this.createDefaultColumnModel();
this.setColumnModel$javax_swing_table_TableColumnModel(cm);
this.initializeLocalVars();
this.uiClassID="TableHeaderUI";
this.updateUI();
}, 1);

Clazz.newMeth(C$, 'setTable$javax_swing_JTable', function (table) {
var old = this.table;
this.table=table;
this.firePropertyChange$S$O$O("table", old, table);
});

Clazz.newMeth(C$, 'getTable', function () {
return this.table;
});

Clazz.newMeth(C$, 'setReorderingAllowed$Z', function (reorderingAllowed) {
var old = this.reorderingAllowed;
this.reorderingAllowed=reorderingAllowed;
this.firePropertyChange$S$Z$Z("reorderingAllowed", old, reorderingAllowed);
});

Clazz.newMeth(C$, 'getReorderingAllowed', function () {
return this.reorderingAllowed;
});

Clazz.newMeth(C$, 'setResizingAllowed$Z', function (resizingAllowed) {
var old = this.resizingAllowed;
this.resizingAllowed=resizingAllowed;
this.firePropertyChange$S$Z$Z("resizingAllowed", old, resizingAllowed);
});

Clazz.newMeth(C$, 'getResizingAllowed', function () {
return this.resizingAllowed;
});

Clazz.newMeth(C$, 'getDraggedColumn', function () {
return this.draggedColumn;
});

Clazz.newMeth(C$, 'getDraggedDistance', function () {
return this.draggedDistance;
});

Clazz.newMeth(C$, 'getResizingColumn', function () {
return this.resizingColumn;
});

Clazz.newMeth(C$, 'setUpdateTableInRealTime$Z', function (flag) {
this.updateTableInRealTime=flag;
});

Clazz.newMeth(C$, 'getUpdateTableInRealTime', function () {
return this.updateTableInRealTime;
});

Clazz.newMeth(C$, 'setDefaultRenderer$javax_swing_table_TableCellRenderer', function (defaultRenderer) {
this.defaultRenderer=defaultRenderer;
});

Clazz.newMeth(C$, 'getDefaultRenderer', function () {
return this.defaultRenderer;
});

Clazz.newMeth(C$, 'columnAtPoint$java_awt_Point', function (point) {
var x = point.x;
if (!this.getComponentOrientation().isLeftToRight()) {
x=p$.getWidthInRightToLeft.apply(this, []) - x - 1 ;
}return this.getColumnModel().getColumnIndexAtX$I(x);
});

Clazz.newMeth(C$, 'getHeaderRect$I', function (column) {
var r = Clazz.new_((I$[1]||$incl$(1)));
var cm = this.getColumnModel();
r.height=this.getHeight();
if (column < 0) {
if (!this.getComponentOrientation().isLeftToRight()) {
r.x=p$.getWidthInRightToLeft.apply(this, []);
}} else if (column >= cm.getColumnCount()) {
if (this.getComponentOrientation().isLeftToRight()) {
r.x=this.getWidth();
}} else {
for (var i = 0; i < column; i++) {
r.x+=cm.getColumn$I(i).getWidth();
}
if (!this.getComponentOrientation().isLeftToRight()) {
r.x=p$.getWidthInRightToLeft.apply(this, []) - r.x - cm.getColumn$I(column).getWidth() ;
}r.width=cm.getColumn$I(column).getWidth();
}return r;
});

Clazz.newMeth(C$, 'getToolTipText$java_awt_event_MouseEvent', function (event) {
var tip = null;
var p = event.getPoint();
var column;
if ((column=this.columnAtPoint$java_awt_Point(p)) != -1) {
var aColumn = this.columnModel.getColumn$I(column);
var renderer = aColumn.getHeaderRenderer();
if (renderer == null ) {
renderer=this.defaultRenderer;
}var component = renderer.getTableCellRendererComponent$javax_swing_JTable$O$Z$Z$I$I(this.getTable(), aColumn.getHeaderValue(), false, false, -1, column);
if (Clazz.instanceOf(component, "javax.swing.JComponent")) {
var newEvent;
var cellRect = this.getHeaderRect$I(column);
p.translate$I$I(-cellRect.x, -cellRect.y);
newEvent=Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I,[component, event.getID(), event.getWhen(), event.getModifiers(), p.x, p.y, event.getXOnScreen(), event.getYOnScreen(), event.getClickCount(), event.isPopupTrigger(), 0]);
tip=(component).getToolTipText$java_awt_event_MouseEvent(newEvent);
}}if (tip == null ) tip=this.getToolTipText();
return tip;
});

Clazz.newMeth(C$, 'setUI$javax_swing_plaf_TableHeaderUI', function (ui) {
if (this.ui !== ui ) {
C$.superclazz.prototype.setUI$javax_swing_plaf_ComponentUI.apply(this, [ui]);
this.repaint();
}});

Clazz.newMeth(C$, 'updateUI', function () {
C$.superclazz.prototype.updateUI.apply(this, []);
var renderer = this.getDefaultRenderer();
if (Clazz.instanceOf(renderer, "java.awt.Component")) {
(I$[3]||$incl$(3)).updateComponentTreeUI$java_awt_Component(renderer);
}});

Clazz.newMeth(C$, 'setColumnModel$javax_swing_table_TableColumnModel', function (columnModel) {
if (columnModel == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Cannot set a null ColumnModel"]);
}var old = this.columnModel;
if (columnModel !== old ) {
if (old != null ) {
old.removeColumnModelListener$javax_swing_event_TableColumnModelListener(this);
}this.columnModel=columnModel;
columnModel.addColumnModelListener$javax_swing_event_TableColumnModelListener(this);
this.firePropertyChange$S$O$O("columnModel", old, columnModel);
this.resizeAndRepaint();
}});

Clazz.newMeth(C$, 'getColumnModel', function () {
return this.columnModel;
});

Clazz.newMeth(C$, 'columnAdded$javax_swing_event_TableColumnModelEvent', function (e) {
this.resizeAndRepaint();
});

Clazz.newMeth(C$, 'columnRemoved$javax_swing_event_TableColumnModelEvent', function (e) {
this.resizeAndRepaint();
});

Clazz.newMeth(C$, 'columnMoved$javax_swing_event_TableColumnModelEvent', function (e) {
this.repaint();
});

Clazz.newMeth(C$, 'columnMarginChanged$javax_swing_event_ChangeEvent', function (e) {
this.resizeAndRepaint();
});

Clazz.newMeth(C$, 'columnSelectionChanged$javax_swing_event_ListSelectionEvent', function (e) {
});

Clazz.newMeth(C$, 'createDefaultColumnModel', function () {
return Clazz.new_((I$[4]||$incl$(4)));
});

Clazz.newMeth(C$, 'createDefaultRenderer', function () {
return Clazz.new_((I$[5]||$incl$(5)));
});

Clazz.newMeth(C$, 'initializeLocalVars', function () {
this.setOpaque$Z(true);
this.table=null;
this.reorderingAllowed=true;
this.resizingAllowed=true;
this.draggedColumn=null;
this.draggedDistance=0;
this.resizingColumn=null;
this.updateTableInRealTime=true;
this.setDefaultRenderer$javax_swing_table_TableCellRenderer(this.createDefaultRenderer());
});

Clazz.newMeth(C$, 'resizeAndRepaint', function () {
this.revalidate();
this.repaint();
});

Clazz.newMeth(C$, 'setDraggedColumn$javax_swing_table_TableColumn', function (aColumn) {
this.draggedColumn=aColumn;
});

Clazz.newMeth(C$, 'setDraggedDistance$I', function (distance) {
this.draggedDistance=distance;
});

Clazz.newMeth(C$, 'setResizingColumn$javax_swing_table_TableColumn', function (aColumn) {
this.resizingColumn=aColumn;
});

Clazz.newMeth(C$, 'getWidthInRightToLeft', function () {
if ((this.table != null ) && (this.table.getAutoResizeMode() != 0) ) {
return this.table.getWidth();
}return C$.superclazz.prototype.getWidth.apply(this, []);
});

Clazz.newMeth(C$, 'paramString', function () {
var reorderingAllowedString = (this.reorderingAllowed ? "true" : "false");
var resizingAllowedString = (this.resizingAllowed ? "true" : "false");
var updateTableInRealTimeString = (this.updateTableInRealTime ? "true" : "false");
return C$.superclazz.prototype.paramString.apply(this, []) + ",draggedDistance=" + this.draggedDistance + ",reorderingAllowed=" + reorderingAllowedString + ",resizingAllowed=" + resizingAllowedString + ",updateTableInRealTime=" + updateTableInRealTimeString ;
});
})();
//Created 2018-05-24 08:46:59
