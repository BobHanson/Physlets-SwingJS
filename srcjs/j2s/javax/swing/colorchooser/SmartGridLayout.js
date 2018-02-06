(function(){var P$=Clazz.newPackage("javax.swing.colorchooser"),I$=[['java.awt.Component','java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SmartGridLayout", null, null, 'java.awt.LayoutManager');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.rows = 0;
this.columns = 0;
this.xGap = 0;
this.yGap = 0;
this.componentCount = 0;
this.layoutGrid = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.rows = 2;
this.columns = 2;
this.xGap = 2;
this.yGap = 2;
this.componentCount = 0;
}, 1);

Clazz.newMeth(C$, 'c$$I$I', function (numColumns, numRows) {
C$.$init$.apply(this);
this.rows = numRows;
this.columns = numColumns;
this.layoutGrid = Clazz.array((I$[1]||$incl$(1)), [numColumns, numRows]);
}, 1);

Clazz.newMeth(C$, 'layoutContainer$java_awt_Container', function (c) {
p$.buildLayoutGrid$java_awt_Container.apply(this, [c]);
var rowHeights = Clazz.array(Integer.TYPE, [this.rows]);
var columnWidths = Clazz.array(Integer.TYPE, [this.columns]);
for (var row = 0; row < this.rows; row++) {
rowHeights[row] = p$.computeRowHeight$I.apply(this, [row]);
}
for (var column = 0; column < this.columns; column++) {
columnWidths[column] = p$.computeColumnWidth$I.apply(this, [column]);
}
var insets = c.getInsets();
if (c.getComponentOrientation().isLeftToRight()) {
var horizLoc = insets.left;
for (var column = 0; column < this.columns; column++) {
var vertLoc = insets.top;
for (var row = 0; row < this.rows; row++) {
var current = this.layoutGrid[column][row];
current.setBounds$I$I$I$I(horizLoc, vertLoc, columnWidths[column], rowHeights[row]);
vertLoc = vertLoc+((rowHeights[row] + this.yGap));
}
horizLoc = horizLoc+((columnWidths[column] + this.xGap));
}
} else {
var horizLoc = c.getWidth() - insets.right;
for (var column = 0; column < this.columns; column++) {
var vertLoc = insets.top;
horizLoc = horizLoc-(columnWidths[column]);
for (var row = 0; row < this.rows; row++) {
var current = this.layoutGrid[column][row];
current.setBounds$I$I$I$I(horizLoc, vertLoc, columnWidths[column], rowHeights[row]);
vertLoc = vertLoc+((rowHeights[row] + this.yGap));
}
horizLoc = horizLoc-(this.xGap);
}
}});

Clazz.newMeth(C$, 'minimumLayoutSize$java_awt_Container', function (c) {
p$.buildLayoutGrid$java_awt_Container.apply(this, [c]);
var insets = c.getInsets();
var height = 0;
var width = 0;
for (var row = 0; row < this.rows; row++) {
height = height+(p$.computeRowHeight$I.apply(this, [row]));
}
for (var column = 0; column < this.columns; column++) {
width = width+(p$.computeColumnWidth$I.apply(this, [column]));
}
height = height+((this.yGap * (this.rows - 1)) + insets.top + insets.bottom );
width = width+((this.xGap * (this.columns - 1)) + insets.right + insets.left );
return Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[width, height]);
});

Clazz.newMeth(C$, 'preferredLayoutSize$java_awt_Container', function (c) {
return this.minimumLayoutSize$java_awt_Container(c);
});

Clazz.newMeth(C$, 'addLayoutComponent$S$java_awt_Component', function (s, c) {
});

Clazz.newMeth(C$, 'removeLayoutComponent$java_awt_Component', function (c) {
});

Clazz.newMeth(C$, 'buildLayoutGrid$java_awt_Container', function (c) {
var children = c.getComponents();
for (var componentCount = 0; componentCount < children.length; componentCount++) {
var row = 0;
var column = 0;
if (componentCount != 0) {
column = componentCount % this.columns;
row = ((componentCount - column)/this.columns|0);
}this.layoutGrid[column][row] = children[componentCount];
}
});

Clazz.newMeth(C$, 'computeColumnWidth$I', function (columnNum) {
var maxWidth = 1;
for (var row = 0; row < this.rows; row++) {
var width = this.layoutGrid[columnNum][row].getPreferredSize().width;
if (width > maxWidth) {
maxWidth = width;
}}
return maxWidth;
});

Clazz.newMeth(C$, 'computeRowHeight$I', function (rowNum) {
var maxHeight = 1;
for (var column = 0; column < this.columns; column++) {
var height = this.layoutGrid[column][rowNum].getPreferredSize().height;
if (height > maxHeight) {
maxHeight = height;
}}
return maxHeight;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:47
