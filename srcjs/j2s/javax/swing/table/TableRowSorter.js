(function(){var P$=Clazz.newPackage("javax.swing.table"),I$=[[['javax.swing.table.TableRowSorter','.TableRowSorterModelWrapper']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "TableRowSorter", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.DefaultRowSorter');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.tableModel = null;
this.stringConverter = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$TM.apply(this, [null]);
}, 1);

Clazz.newMeth(C$, ['c$$TM'], function (model) {
Clazz.super_(C$, this,1);
this.setModel$TM(model);
}, 1);

Clazz.newMeth(C$, ['setModel$TM'], function (model) {
this.tableModel = model;
this.setModelWrapper$javax_swing_DefaultRowSorter_ModelWrapper(Clazz.new_((I$[1]||$incl$(1)), [this, null]));
});

Clazz.newMeth(C$, 'setStringConverter$javax_swing_table_TableStringConverter', function (stringConverter) {
this.stringConverter = stringConverter;
});

Clazz.newMeth(C$, 'getStringConverter', function () {
return this.stringConverter;
});

Clazz.newMeth(C$, 'getComparator$I', function (column) {
var comparator = C$.superclazz.prototype.getComparator$I.apply(this, [column]);
if (comparator != null ) {
return comparator;
}return null;
});

Clazz.newMeth(C$, 'useToString$I', function (column) {
var comparator = C$.superclazz.prototype.getComparator$I.apply(this, [column]);
if (comparator != null ) {
return false;
}var columnClass = this.getModel().getColumnClass$I(column);
if (columnClass === Clazz.getClass(java.lang.String) ) {
return false;
}if (Clazz.getClass(java.lang.Comparable,['compareTo$TT']).isAssignableFrom$Class(columnClass)) {
return false;
}return true;
});
;
(function(){var C$=Clazz.newClass(P$.TableRowSorter, "TableRowSorterModelWrapper", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['javax.swing.DefaultRowSorter','javax.swing.DefaultRowSorter.ModelWrapper']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getModel', function () {
return this.this$0.tableModel;
});

Clazz.newMeth(C$, 'getColumnCount', function () {
return (this.this$0.tableModel == null ) ? 0 : this.this$0.tableModel.getColumnCount();
});

Clazz.newMeth(C$, 'getRowCount', function () {
return (this.this$0.tableModel == null ) ? 0 : this.this$0.tableModel.getRowCount();
});

Clazz.newMeth(C$, 'getValueAt$I$I', function (row, column) {
return this.this$0.tableModel.getValueAt$I$I(row, column);
});

Clazz.newMeth(C$, 'getStringValueAt$I$I', function (row, column) {
var converter = this.this$0.getStringConverter();
if (converter != null ) {
var value = converter.toString$javax_swing_table_TableModel$I$I(this.this$0.tableModel, row, column);
if (value != null ) {
return value;
}return "";
}var o = this.getValueAt$I$I(row, column);
if (o == null ) {
return "";
}var string = o.toString();
if (string == null ) {
return "";
}return string;
});

Clazz.newMeth(C$, 'getIdentifier$I', function (index) {
return new Integer(index);
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-06 08:59:54
