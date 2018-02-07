(function(){var P$=Clazz.newPackage("javax.swing.table"),I$=[['javax.swing.event.EventListenerList','javax.swing.event.TableModelListener','javax.swing.event.TableModelEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AbstractTableModel", null, null, 'javax.swing.table.TableModel');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.listenerList = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.listenerList = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'getColumnName$I', function (column) {
var result = "";
for (; column >= 0; column = (column/26|0) - 1) {
result = String.fromCharCode(((String.fromCharCode((column % 26))).$c() + 65)) + result;
}
return result;
});

Clazz.newMeth(C$, 'findColumn$S', function (columnName) {
for (var i = 0; i < this.getColumnCount(); i++) {
if (columnName.equals$O(this.getColumnName$I(i))) {
return i;
}}
return -1;
});

Clazz.newMeth(C$, 'getColumnClass$I', function (columnIndex) {
return Clazz.getClass(java.lang.Object);
});

Clazz.newMeth(C$, 'isCellEditable$I$I', function (rowIndex, columnIndex) {
return false;
});

Clazz.newMeth(C$, 'setValueAt$O$I$I', function (aValue, rowIndex, columnIndex) {
});

Clazz.newMeth(C$, 'addTableModelListener$javax_swing_event_TableModelListener', function (l) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[2]||$incl$(2)),['tableChanged$javax_swing_event_TableModelEvent']), l);
});

Clazz.newMeth(C$, 'removeTableModelListener$javax_swing_event_TableModelListener', function (l) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[2]||$incl$(2)),['tableChanged$javax_swing_event_TableModelEvent']), l);
});

Clazz.newMeth(C$, 'getTableModelListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[2]||$incl$(2)),['tableChanged$javax_swing_event_TableModelEvent']));
});

Clazz.newMeth(C$, 'fireTableDataChanged', function () {
this.fireTableChanged$javax_swing_event_TableModelEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_table_TableModel,[this]));
});

Clazz.newMeth(C$, 'fireTableStructureChanged', function () {
this.fireTableChanged$javax_swing_event_TableModelEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_table_TableModel$I,[this, -1]));
});

Clazz.newMeth(C$, 'fireTableRowsInserted$I$I', function (firstRow, lastRow) {
this.fireTableChanged$javax_swing_event_TableModelEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_table_TableModel$I$I$I$I,[this, firstRow, lastRow, -1, 1]));
});

Clazz.newMeth(C$, 'fireTableRowsUpdated$I$I', function (firstRow, lastRow) {
this.fireTableChanged$javax_swing_event_TableModelEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_table_TableModel$I$I$I$I,[this, firstRow, lastRow, -1, 0]));
});

Clazz.newMeth(C$, 'fireTableRowsDeleted$I$I', function (firstRow, lastRow) {
this.fireTableChanged$javax_swing_event_TableModelEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_table_TableModel$I$I$I$I,[this, firstRow, lastRow, -1, -1]));
});

Clazz.newMeth(C$, 'fireTableCellUpdated$I$I', function (row, column) {
this.fireTableChanged$javax_swing_event_TableModelEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_table_TableModel$I$I$I,[this, row, row, column]));
});

Clazz.newMeth(C$, 'fireTableChanged$javax_swing_event_TableModelEvent', function (e) {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[2]||$incl$(2)),['tableChanged$javax_swing_event_TableModelEvent']) ) {
(listeners[i + 1]).tableChanged$javax_swing_event_TableModelEvent(e);
}}
});

Clazz.newMeth(C$, 'getListeners$Class', function (listenerType) {
return this.listenerList.getListeners$Class(listenerType);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:53