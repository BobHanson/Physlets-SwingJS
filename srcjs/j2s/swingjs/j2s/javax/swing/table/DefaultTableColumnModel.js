(function(){var P$=Clazz.newPackage("javax.swing.table"),I$=[['javax.swing.event.EventListenerList','java.util.Vector','javax.swing.event.TableColumnModelEvent','javax.swing.event.TableColumnModelListener','javax.swing.event.ChangeEvent','javax.swing.DefaultListSelectionModel']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DefaultTableColumnModel", null, null, ['javax.swing.table.TableColumnModel', 'java.beans.PropertyChangeListener', 'javax.swing.event.ListSelectionListener']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.tableColumns = null;
this.selectionModel = null;
this.columnMargin = 0;
this.listenerList = null;
this.changeEvent = null;
this.columnSelectionAllowed = false;
this.totalColumnWidth = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.listenerList = Clazz.new_((I$[1]||$incl$(1)));
this.changeEvent = null;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.tableColumns = Clazz.new_((I$[2]||$incl$(2)));
this.setSelectionModel$javax_swing_ListSelectionModel(this.createSelectionModel());
this.setColumnMargin$I(1);
p$.invalidateWidthCache.apply(this, []);
this.setColumnSelectionAllowed$Z(false);
}, 1);

Clazz.newMeth(C$, 'addColumn$javax_swing_table_TableColumn', function (aColumn) {
if (aColumn == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Object is null"]);
}this.tableColumns.addElement$TE(aColumn);
aColumn.addPropertyChangeListener$java_beans_PropertyChangeListener(this);
p$.invalidateWidthCache.apply(this, []);
this.fireColumnAdded$javax_swing_event_TableColumnModelEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_table_TableColumnModel$I$I,[this, 0, this.getColumnCount() - 1]));
});

Clazz.newMeth(C$, 'removeColumn$javax_swing_table_TableColumn', function (column) {
var columnIndex = this.tableColumns.indexOf$O(column);
if (columnIndex != -1) {
if (this.selectionModel != null ) {
this.selectionModel.removeIndexInterval$I$I(columnIndex, columnIndex);
}column.removePropertyChangeListener$java_beans_PropertyChangeListener(this);
this.tableColumns.removeElementAt$I(columnIndex);
p$.invalidateWidthCache.apply(this, []);
this.fireColumnRemoved$javax_swing_event_TableColumnModelEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_table_TableColumnModel$I$I,[this, columnIndex, 0]));
}});

Clazz.newMeth(C$, 'moveColumn$I$I', function (columnIndex, newIndex) {
if ((columnIndex < 0) || (columnIndex >= this.getColumnCount()) || (newIndex < 0) || (newIndex >= this.getColumnCount())  ) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["moveColumn() - Index out of range"]);
var aColumn;
if (columnIndex == newIndex) {
this.fireColumnMoved$javax_swing_event_TableColumnModelEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_table_TableColumnModel$I$I,[this, columnIndex, newIndex]));
return;
}aColumn = this.tableColumns.elementAt$I(columnIndex);
this.tableColumns.removeElementAt$I(columnIndex);
var selected = this.selectionModel.isSelectedIndex$I(columnIndex);
this.selectionModel.removeIndexInterval$I$I(columnIndex, columnIndex);
this.tableColumns.insertElementAt$TE$I(aColumn, newIndex);
this.selectionModel.insertIndexInterval$I$I$Z(newIndex, 1, true);
if (selected) {
this.selectionModel.addSelectionInterval$I$I(newIndex, newIndex);
} else {
this.selectionModel.removeSelectionInterval$I$I(newIndex, newIndex);
}this.fireColumnMoved$javax_swing_event_TableColumnModelEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_table_TableColumnModel$I$I,[this, columnIndex, newIndex]));
});

Clazz.newMeth(C$, 'setColumnMargin$I', function (newMargin) {
if (newMargin != this.columnMargin) {
this.columnMargin = newMargin;
this.fireColumnMarginChanged();
}});

Clazz.newMeth(C$, 'getColumnCount', function () {
return this.tableColumns.size();
});

Clazz.newMeth(C$, 'getColumns', function () {
return this.tableColumns.elements();
});

Clazz.newMeth(C$, 'getColumnIndex$O', function (identifier) {
if (identifier == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Identifier is null"]);
}var enumeration = this.getColumns();
var aColumn;
var index = 0;
while (enumeration.hasMoreElements()){
aColumn = enumeration.nextElement();
if (identifier.equals$O(aColumn.getIdentifier())) return index;
index++;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Identifier not found"]);
});

Clazz.newMeth(C$, 'getColumn$I', function (columnIndex) {
return this.tableColumns.elementAt$I(columnIndex);
});

Clazz.newMeth(C$, 'getColumnMargin', function () {
return this.columnMargin;
});

Clazz.newMeth(C$, 'getColumnIndexAtX$I', function (x) {
if (x < 0) {
return -1;
}var cc = this.getColumnCount();
for (var column = 0; column < cc; column++) {
x = x - this.getColumn$I(column).getWidth();
if (x < 0) {
return column;
}}
return -1;
});

Clazz.newMeth(C$, 'getTotalColumnWidth', function () {
if (this.totalColumnWidth == -1) {
this.recalcWidthCache();
}return this.totalColumnWidth;
});

Clazz.newMeth(C$, 'setSelectionModel$javax_swing_ListSelectionModel', function (newModel) {
if (newModel == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Cannot set a null SelectionModel"]);
}var oldModel = this.selectionModel;
if (newModel !== oldModel ) {
if (oldModel != null ) {
oldModel.removeListSelectionListener$javax_swing_event_ListSelectionListener(this);
}this.selectionModel = newModel;
newModel.addListSelectionListener$javax_swing_event_ListSelectionListener(this);
}});

Clazz.newMeth(C$, 'getSelectionModel', function () {
return this.selectionModel;
});

Clazz.newMeth(C$, 'setColumnSelectionAllowed$Z', function (flag) {
this.columnSelectionAllowed = flag;
});

Clazz.newMeth(C$, 'getColumnSelectionAllowed', function () {
return this.columnSelectionAllowed;
});

Clazz.newMeth(C$, 'getSelectedColumns', function () {
if (this.selectionModel != null ) {
var iMin = this.selectionModel.getMinSelectionIndex();
var iMax = this.selectionModel.getMaxSelectionIndex();
if ((iMin == -1) || (iMax == -1) ) {
return Clazz.array(Integer.TYPE, [0]);
}var rvTmp = Clazz.array(Integer.TYPE, [1 + (iMax - iMin)]);
var n = 0;
for (var i = iMin; i <= iMax; i++) {
if (this.selectionModel.isSelectedIndex$I(i)) {
rvTmp[n++] = i;
}}
var rv = Clazz.array(Integer.TYPE, [n]);
System.arraycopy(rvTmp, 0, rv, 0, n);
return rv;
}return Clazz.array(Integer.TYPE, [0]);
});

Clazz.newMeth(C$, 'getSelectedColumnCount', function () {
if (this.selectionModel != null ) {
var iMin = this.selectionModel.getMinSelectionIndex();
var iMax = this.selectionModel.getMaxSelectionIndex();
var count = 0;
for (var i = iMin; i <= iMax; i++) {
if (this.selectionModel.isSelectedIndex$I(i)) {
count++;
}}
return count;
}return 0;
});

Clazz.newMeth(C$, 'addColumnModelListener$javax_swing_event_TableColumnModelListener', function (x) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[4]||$incl$(4)),['columnAdded$javax_swing_event_TableColumnModelEvent','columnMarginChanged$javax_swing_event_ChangeEvent','columnMoved$javax_swing_event_TableColumnModelEvent','columnRemoved$javax_swing_event_TableColumnModelEvent','columnSelectionChanged$javax_swing_event_ListSelectionEvent']), x);
});

Clazz.newMeth(C$, 'removeColumnModelListener$javax_swing_event_TableColumnModelListener', function (x) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[4]||$incl$(4)),['columnAdded$javax_swing_event_TableColumnModelEvent','columnMarginChanged$javax_swing_event_ChangeEvent','columnMoved$javax_swing_event_TableColumnModelEvent','columnRemoved$javax_swing_event_TableColumnModelEvent','columnSelectionChanged$javax_swing_event_ListSelectionEvent']), x);
});

Clazz.newMeth(C$, 'getColumnModelListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[4]||$incl$(4)),['columnAdded$javax_swing_event_TableColumnModelEvent','columnMarginChanged$javax_swing_event_ChangeEvent','columnMoved$javax_swing_event_TableColumnModelEvent','columnRemoved$javax_swing_event_TableColumnModelEvent','columnSelectionChanged$javax_swing_event_ListSelectionEvent']));
});

Clazz.newMeth(C$, 'fireColumnAdded$javax_swing_event_TableColumnModelEvent', function (e) {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[4]||$incl$(4)),['columnAdded$javax_swing_event_TableColumnModelEvent','columnMarginChanged$javax_swing_event_ChangeEvent','columnMoved$javax_swing_event_TableColumnModelEvent','columnRemoved$javax_swing_event_TableColumnModelEvent','columnSelectionChanged$javax_swing_event_ListSelectionEvent']) ) {
(listeners[i + 1]).columnAdded$javax_swing_event_TableColumnModelEvent(e);
}}
});

Clazz.newMeth(C$, 'fireColumnRemoved$javax_swing_event_TableColumnModelEvent', function (e) {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[4]||$incl$(4)),['columnAdded$javax_swing_event_TableColumnModelEvent','columnMarginChanged$javax_swing_event_ChangeEvent','columnMoved$javax_swing_event_TableColumnModelEvent','columnRemoved$javax_swing_event_TableColumnModelEvent','columnSelectionChanged$javax_swing_event_ListSelectionEvent']) ) {
(listeners[i + 1]).columnRemoved$javax_swing_event_TableColumnModelEvent(e);
}}
});

Clazz.newMeth(C$, 'fireColumnMoved$javax_swing_event_TableColumnModelEvent', function (e) {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[4]||$incl$(4)),['columnAdded$javax_swing_event_TableColumnModelEvent','columnMarginChanged$javax_swing_event_ChangeEvent','columnMoved$javax_swing_event_TableColumnModelEvent','columnRemoved$javax_swing_event_TableColumnModelEvent','columnSelectionChanged$javax_swing_event_ListSelectionEvent']) ) {
(listeners[i + 1]).columnMoved$javax_swing_event_TableColumnModelEvent(e);
}}
});

Clazz.newMeth(C$, 'fireColumnSelectionChanged$javax_swing_event_ListSelectionEvent', function (e) {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[4]||$incl$(4)),['columnAdded$javax_swing_event_TableColumnModelEvent','columnMarginChanged$javax_swing_event_ChangeEvent','columnMoved$javax_swing_event_TableColumnModelEvent','columnRemoved$javax_swing_event_TableColumnModelEvent','columnSelectionChanged$javax_swing_event_ListSelectionEvent']) ) {
(listeners[i + 1]).columnSelectionChanged$javax_swing_event_ListSelectionEvent(e);
}}
});

Clazz.newMeth(C$, 'fireColumnMarginChanged', function () {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[4]||$incl$(4)),['columnAdded$javax_swing_event_TableColumnModelEvent','columnMarginChanged$javax_swing_event_ChangeEvent','columnMoved$javax_swing_event_TableColumnModelEvent','columnRemoved$javax_swing_event_TableColumnModelEvent','columnSelectionChanged$javax_swing_event_ListSelectionEvent']) ) {
if (this.changeEvent == null ) this.changeEvent = Clazz.new_((I$[5]||$incl$(5)).c$$O,[this]);
(listeners[i + 1]).columnMarginChanged$javax_swing_event_ChangeEvent(this.changeEvent);
}}
});

Clazz.newMeth(C$, 'getListeners$Class', function (listenerType) {
return this.listenerList.getListeners$Class(listenerType);
});

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (evt) {
var name = evt.getPropertyName();
if (name == "width" || name == "preferredWidth" ) {
p$.invalidateWidthCache.apply(this, []);
this.fireColumnMarginChanged();
}});

Clazz.newMeth(C$, 'valueChanged$javax_swing_event_ListSelectionEvent', function (e) {
this.fireColumnSelectionChanged$javax_swing_event_ListSelectionEvent(e);
});

Clazz.newMeth(C$, 'createSelectionModel', function () {
return Clazz.new_((I$[6]||$incl$(6)));
});

Clazz.newMeth(C$, 'recalcWidthCache', function () {
var enumeration = this.getColumns();
this.totalColumnWidth = 0;
while (enumeration.hasMoreElements()){
this.totalColumnWidth = this.totalColumnWidth+((enumeration.nextElement()).getWidth());
}
});

Clazz.newMeth(C$, 'invalidateWidthCache', function () {
this.totalColumnWidth = -1;
});
})();
//Created 2018-02-08 10:02:53
