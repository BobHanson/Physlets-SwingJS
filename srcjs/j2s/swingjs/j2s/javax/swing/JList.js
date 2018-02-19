(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.awt.Point','javax.swing.DropMode','javax.swing.AbstractListModel','javax.swing.SwingUtilities',['javax.swing.text.Position','.Bias'],'java.awt.event.MouseEvent','javax.swing.DefaultListSelectionModel','javax.swing.event.ListSelectionListener','javax.swing.event.ListSelectionEvent',['javax.swing.JList','.ListSelectionHandler'],'java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JList", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.JComponent', 'javax.swing.Scrollable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.fixedCellWidth = 0;
this.fixedCellHeight = 0;
this.horizontalScrollIncrement = 0;
this.prototypeCellValue = null;
this.visibleRowCount = 0;
this.selectionForeground = null;
this.selectionBackground = null;
this.dragEnabled = false;
this.selectionModel = null;
this.dataModel = null;
this.cellRenderer = null;
this.selectionListener = null;
this.layoutOrientation = 0;
this.dropMode = null;
this.dropLocation = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.fixedCellWidth = -1;
this.fixedCellHeight = -1;
this.horizontalScrollIncrement = -1;
this.visibleRowCount = 8;
this.dropMode = (I$[2]||$incl$(2)).USE_SELECTION;
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_ListModel', function (dataModel) {
Clazz.super_(C$, this,1);
if (dataModel == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["dataModel must be non null"]);
}this.layoutOrientation = 0;
this.dataModel = dataModel;
this.selectionModel = this.createSelectionModel();
this.setAutoscrolls$Z(true);
this.setOpaque$Z(true);
this.uiClassID = "ListUI";
this.updateUI();
}, 1);

Clazz.newMeth(C$, 'c$$OA', function (listData) {
C$.c$$javax_swing_ListModel.apply(this, [((
(function(){var C$=Clazz.newClass(P$, "JList$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('javax.swing.AbstractListModel'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getSize', function () {
return this.$finals.listData.length;
});

Clazz.newMeth(C$, 'getElementAt$I', function (i) {
return this.$finals.listData[i];
});
})()
), Clazz.new_((I$[3]||$incl$(3)), [this, {listData: listData}],P$.JList$1))]);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Vector', function (listData) {
C$.c$$javax_swing_ListModel.apply(this, [((
(function(){var C$=Clazz.newClass(P$, "JList$2", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('javax.swing.AbstractListModel'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getSize', function () {
return this.$finals.listData.size();
});

Clazz.newMeth(C$, 'getElementAt$I', function (i) {
return this.$finals.listData.elementAt$I(i);
});
})()
), Clazz.new_((I$[3]||$incl$(3)), [this, {listData: listData}],P$.JList$2))]);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$javax_swing_ListModel.apply(this, [((
(function(){var C$=Clazz.newClass(P$, "JList$3", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('javax.swing.AbstractListModel'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getSize', function () {
return 0;
});

Clazz.newMeth(C$, 'getElementAt$I', function (i) {
return "No Data Model";
});
})()
), Clazz.new_((I$[3]||$incl$(3)), [this, null],P$.JList$3))]);
}, 1);

Clazz.newMeth(C$, 'updateUI', function () {
C$.superclazz.prototype.updateUI.apply(this, []);
var renderer = this.getCellRenderer();
if (Clazz.instanceOf(renderer, "java.awt.Component")) {
(I$[4]||$incl$(4)).updateComponentTreeUI$java_awt_Component(renderer);
}});

Clazz.newMeth(C$, 'updateFixedCellSize', function () {
var cr = this.getCellRenderer();
var value = this.getPrototypeCellValue();
if ((cr != null ) && (value != null ) ) {
var c = cr.getListCellRendererComponent$javax_swing_JList$O$I$Z$Z(this, value, 0, false, false);
var f = c.getFont();
c.setFont$java_awt_Font(this.getFont());
var d = c.getPreferredSize();
this.fixedCellWidth = d.width;
this.fixedCellHeight = d.height;
c.setFont$java_awt_Font(f);
}});

Clazz.newMeth(C$, 'getPrototypeCellValue', function () {
return this.prototypeCellValue;
});

Clazz.newMeth(C$, 'setPrototypeCellValue$O', function (prototypeCellValue) {
var oldValue = this.prototypeCellValue;
this.prototypeCellValue = prototypeCellValue;
if ((prototypeCellValue != null ) && !prototypeCellValue.equals$O(oldValue) ) {
p$.updateFixedCellSize.apply(this, []);
}this.firePropertyChange$S$O$O("prototypeCellValue", oldValue, prototypeCellValue);
});

Clazz.newMeth(C$, 'getFixedCellWidth', function () {
return this.fixedCellWidth;
});

Clazz.newMeth(C$, 'setFixedCellWidth$I', function (width) {
var oldValue = this.fixedCellWidth;
this.fixedCellWidth = width;
this.firePropertyChange$S$I$I("fixedCellWidth", oldValue, this.fixedCellWidth);
});

Clazz.newMeth(C$, 'getFixedCellHeight', function () {
return this.fixedCellHeight;
});

Clazz.newMeth(C$, 'setFixedCellHeight$I', function (height) {
var oldValue = this.fixedCellHeight;
this.fixedCellHeight = height;
this.firePropertyChange$S$I$I("fixedCellHeight", oldValue, this.fixedCellHeight);
});

Clazz.newMeth(C$, 'getCellRenderer', function () {
return this.cellRenderer;
});

Clazz.newMeth(C$, 'setCellRenderer$javax_swing_ListCellRenderer', function (cellRenderer) {
var oldValue = this.cellRenderer;
this.cellRenderer = cellRenderer;
if ((cellRenderer != null ) && !cellRenderer.equals$O(oldValue) ) {
p$.updateFixedCellSize.apply(this, []);
}this.firePropertyChange$S$O$O("cellRenderer", oldValue, cellRenderer);
});

Clazz.newMeth(C$, 'getSelectionForeground', function () {
return this.selectionForeground;
});

Clazz.newMeth(C$, 'setSelectionForeground$java_awt_Color', function (selectionForeground) {
var oldValue = this.selectionForeground;
this.selectionForeground = selectionForeground;
this.firePropertyChange$S$O$O("selectionForeground", oldValue, selectionForeground);
});

Clazz.newMeth(C$, 'getSelectionBackground', function () {
return this.selectionBackground;
});

Clazz.newMeth(C$, 'setSelectionBackground$java_awt_Color', function (selectionBackground) {
var oldValue = this.selectionBackground;
this.selectionBackground = selectionBackground;
this.firePropertyChange$S$O$O("selectionBackground", oldValue, selectionBackground);
});

Clazz.newMeth(C$, 'getVisibleRowCount', function () {
return this.visibleRowCount;
});

Clazz.newMeth(C$, 'setVisibleRowCount$I', function (visibleRowCount) {
var oldValue = this.visibleRowCount;
this.visibleRowCount = Math.max(0, visibleRowCount);
this.firePropertyChange$S$I$I("visibleRowCount", oldValue, visibleRowCount);
});

Clazz.newMeth(C$, 'getLayoutOrientation', function () {
return this.layoutOrientation;
});

Clazz.newMeth(C$, 'setLayoutOrientation$I', function (layoutOrientation) {
var oldValue = this.layoutOrientation;
switch (layoutOrientation) {
case 0:
case 1:
case 2:
this.layoutOrientation = layoutOrientation;
this.firePropertyChange$S$I$I("layoutOrientation", oldValue, layoutOrientation);
break;
default:
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["layoutOrientation must be one of: VERTICAL, HORIZONTAL_WRAP or VERTICAL_WRAP"]);
}
});

Clazz.newMeth(C$, 'getFirstVisibleIndex', function () {
var r = this.getVisibleRect();
var first;
if (this.getComponentOrientation().isLeftToRight()) {
first = this.locationToIndex$java_awt_Point(r.getLocation());
} else {
first = this.locationToIndex$java_awt_Point(Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[(r.x + r.width) - 1, r.y]));
}if (first != -1) {
var bounds = this.getCellBounds$I$I(first, first);
if (bounds != null ) {
(I$[4]||$incl$(4)).computeIntersection$I$I$I$I$java_awt_Rectangle(r.x, r.y, r.width, r.height, bounds);
if (bounds.width == 0 || bounds.height == 0 ) {
first = -1;
}}}return first;
});

Clazz.newMeth(C$, 'getLastVisibleIndex', function () {
var leftToRight = this.getComponentOrientation().isLeftToRight();
var r = this.getVisibleRect();
var lastPoint;
if (leftToRight) {
lastPoint = Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[(r.x + r.width) - 1, (r.y + r.height) - 1]);
} else {
lastPoint = Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[r.x, (r.y + r.height) - 1]);
}var location = this.locationToIndex$java_awt_Point(lastPoint);
if (location != -1) {
var bounds = this.getCellBounds$I$I(location, location);
if (bounds != null ) {
(I$[4]||$incl$(4)).computeIntersection$I$I$I$I$java_awt_Rectangle(r.x, r.y, r.width, r.height, bounds);
if (bounds.width == 0 || bounds.height == 0 ) {
var isHorizontalWrap = (this.getLayoutOrientation() == 2);
var visibleLocation = isHorizontalWrap ? Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[lastPoint.x, r.y]) : Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[r.x, lastPoint.y]);
var last;
var visIndex = -1;
var lIndex = location;
location = -1;
do {
last = visIndex;
visIndex = this.locationToIndex$java_awt_Point(visibleLocation);
if (visIndex != -1) {
bounds = this.getCellBounds$I$I(visIndex, visIndex);
if (visIndex != lIndex && bounds != null   && bounds.contains$java_awt_Point(visibleLocation) ) {
location = visIndex;
if (isHorizontalWrap) {
visibleLocation.y = bounds.y + bounds.height;
if (visibleLocation.y >= lastPoint.y) {
last = visIndex;
}} else {
visibleLocation.x = bounds.x + bounds.width;
if (visibleLocation.x >= lastPoint.x) {
last = visIndex;
}}} else {
last = visIndex;
}}} while (visIndex != -1 && last != visIndex );
}}}return location;
});

Clazz.newMeth(C$, 'ensureIndexIsVisible$I', function (index) {
var cellBounds = this.getCellBounds$I$I(index, index);
if (cellBounds != null ) {
this.scrollRectToVisible$java_awt_Rectangle(cellBounds);
}});

Clazz.newMeth(C$, 'setDragEnabled$Z', function (b) {
this.dragEnabled = b;
});

Clazz.newMeth(C$, 'getDragEnabled', function () {
return this.dragEnabled;
});

Clazz.newMeth(C$, 'setDropMode$javax_swing_DropMode', function (dropMode) {
if (dropMode != null ) {
switch (dropMode) {
case (I$[2]||$incl$(2)).USE_SELECTION:
case (I$[2]||$incl$(2)).ON:
case (I$[2]||$incl$(2)).INSERT:
case (I$[2]||$incl$(2)).ON_OR_INSERT:
this.dropMode = dropMode;
return;
}
}throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[dropMode + ": Unsupported drop mode for list"]);
});

Clazz.newMeth(C$, 'getNextMatch$S$I$javax_swing_text_Position_Bias', function (prefix, startIndex, bias) {
var model = this.getModel();
var max = model.getSize();
if (prefix == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}if (startIndex < 0 || startIndex >= max ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}prefix = prefix.toUpperCase();
var increment = (bias === (I$[5]||$incl$(5)).Forward ) ? 1 : -1;
var index = startIndex;
do {
var o = model.getElementAt$I(index);
if (o != null ) {
var string;
if (Clazz.instanceOf(o, "java.lang.String")) {
string = (o).toUpperCase();
} else {
string = o.toString();
if (string != null ) {
string = string.toUpperCase();
}}if (string != null  && string.startsWith$S(prefix) ) {
return index;
}}index = (index + increment + max ) % max;
} while (index != startIndex);
return -1;
});

Clazz.newMeth(C$, 'getToolTipText$java_awt_event_MouseEvent', function (event) {
if (event != null ) {
var p = event.getPoint();
var index = this.locationToIndex$java_awt_Point(p);
var r = this.getCellRenderer();
var cellBounds;
if (index != -1 && r != null   && (cellBounds = this.getCellBounds$I$I(index, index)) != null   && cellBounds.contains$I$I(p.x, p.y) ) {
var lsm = this.getSelectionModel();
var rComponent = r.getListCellRendererComponent$javax_swing_JList$O$I$Z$Z(this, this.getModel().getElementAt$I(index), index, lsm.isSelectedIndex$I(index), (this.hasFocus() && (lsm.getLeadSelectionIndex() == index) ));
var newEvent;
p.translate$I$I(-cellBounds.x, -cellBounds.y);
newEvent = Clazz.new_((I$[6]||$incl$(6)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I,[rComponent, event.getID(), event.getWhen(), event.getModifiers(), p.x, p.y, event.getXOnScreen(), event.getYOnScreen(), event.getClickCount(), event.isPopupTrigger(), 0]);
var tip = (rComponent).getToolTipText$java_awt_event_MouseEvent(newEvent);
if (tip != null ) {
return tip;
}}}return C$.superclazz.prototype.getToolTipText.apply(this, []);
});

Clazz.newMeth(C$, 'locationToIndex$java_awt_Point', function (location) {
var ui = this.getUI();
return (ui != null ) ? ui.locationToIndex$javax_swing_JList$java_awt_Point(this, location) : -1;
});

Clazz.newMeth(C$, 'indexToLocation$I', function (index) {
var ui = this.getUI();
return (ui != null ) ? ui.indexToLocation$javax_swing_JList$I(this, index) : null;
});

Clazz.newMeth(C$, 'getCellBounds$I$I', function (index0, index1) {
var ui = this.getUI();
return (ui != null ) ? ui.getCellBounds$javax_swing_JList$I$I(this, index0, index1) : null;
});

Clazz.newMeth(C$, 'getModel', function () {
return this.dataModel;
});

Clazz.newMeth(C$, 'setModel$javax_swing_ListModel', function (model) {
if (model == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["model must be non null"]);
}var oldValue = this.dataModel;
this.dataModel = model;
this.firePropertyChange$S$O$O("model", oldValue, this.dataModel);
this.clearSelection();
});

Clazz.newMeth(C$, 'setListData$OA', function (listData) {
this.setModel$javax_swing_ListModel(((
(function(){var C$=Clazz.newClass(P$, "JList$4", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('javax.swing.AbstractListModel'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getSize', function () {
return this.$finals.listData.length;
});

Clazz.newMeth(C$, 'getElementAt$I', function (i) {
return this.$finals.listData[i];
});
})()
), Clazz.new_((I$[3]||$incl$(3)), [this, {listData: listData}],P$.JList$4)));
});

Clazz.newMeth(C$, 'setListData$java_util_Vector', function (listData) {
this.setModel$javax_swing_ListModel(((
(function(){var C$=Clazz.newClass(P$, "JList$5", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('javax.swing.AbstractListModel'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getSize', function () {
return this.$finals.listData.size();
});

Clazz.newMeth(C$, 'getElementAt$I', function (i) {
return this.$finals.listData.elementAt$I(i);
});
})()
), Clazz.new_((I$[3]||$incl$(3)), [this, {listData: listData}],P$.JList$5)));
});

Clazz.newMeth(C$, 'createSelectionModel', function () {
return Clazz.new_((I$[7]||$incl$(7)));
});

Clazz.newMeth(C$, 'getSelectionModel', function () {
return this.selectionModel;
});

Clazz.newMeth(C$, 'fireSelectionValueChanged$I$I$Z', function (firstIndex, lastIndex, isAdjusting) {
var listeners = this.listenerList.getListenerList();
var e = null;
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[8]||$incl$(8)),['valueChanged$javax_swing_event_ListSelectionEvent']) ) {
if (e == null ) {
e = Clazz.new_((I$[9]||$incl$(9)).c$$O$I$I$Z,[this, firstIndex, lastIndex, isAdjusting]);
}(listeners[i + 1]).valueChanged$javax_swing_event_ListSelectionEvent(e);
}}
});

Clazz.newMeth(C$, 'addListSelectionListener$javax_swing_event_ListSelectionListener', function (listener) {
if (this.selectionListener == null ) {
this.selectionListener = Clazz.new_((I$[10]||$incl$(10)), [this, null]);
this.getSelectionModel().addListSelectionListener$javax_swing_event_ListSelectionListener(this.selectionListener);
}this.listenerList.add$Class$TT(Clazz.getClass((I$[8]||$incl$(8)),['valueChanged$javax_swing_event_ListSelectionEvent']), listener);
});

Clazz.newMeth(C$, 'removeListSelectionListener$javax_swing_event_ListSelectionListener', function (listener) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[8]||$incl$(8)),['valueChanged$javax_swing_event_ListSelectionEvent']), listener);
});

Clazz.newMeth(C$, 'getListSelectionListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[8]||$incl$(8)),['valueChanged$javax_swing_event_ListSelectionEvent']));
});

Clazz.newMeth(C$, 'setSelectionModel$javax_swing_ListSelectionModel', function (selectionModel) {
if (selectionModel == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["selectionModel must be non null"]);
}if (this.selectionListener != null ) {
this.selectionModel.removeListSelectionListener$javax_swing_event_ListSelectionListener(this.selectionListener);
selectionModel.addListSelectionListener$javax_swing_event_ListSelectionListener(this.selectionListener);
}var oldValue = this.selectionModel;
this.selectionModel = selectionModel;
this.firePropertyChange$S$O$O("selectionModel", oldValue, selectionModel);
});

Clazz.newMeth(C$, 'setSelectionMode$I', function (selectionMode) {
this.getSelectionModel().setSelectionMode$I(selectionMode);
});

Clazz.newMeth(C$, 'getSelectionMode', function () {
return this.getSelectionModel().getSelectionMode();
});

Clazz.newMeth(C$, 'getAnchorSelectionIndex', function () {
return this.getSelectionModel().getAnchorSelectionIndex();
});

Clazz.newMeth(C$, 'getLeadSelectionIndex', function () {
return this.getSelectionModel().getLeadSelectionIndex();
});

Clazz.newMeth(C$, 'getMinSelectionIndex', function () {
return this.getSelectionModel().getMinSelectionIndex();
});

Clazz.newMeth(C$, 'getMaxSelectionIndex', function () {
return this.getSelectionModel().getMaxSelectionIndex();
});

Clazz.newMeth(C$, 'isSelectedIndex$I', function (index) {
return this.getSelectionModel().isSelectedIndex$I(index);
});

Clazz.newMeth(C$, 'isSelectionEmpty', function () {
return this.getSelectionModel().isSelectionEmpty();
});

Clazz.newMeth(C$, 'clearSelection', function () {
this.getSelectionModel().clearSelection();
});

Clazz.newMeth(C$, 'setSelectionInterval$I$I', function (anchor, lead) {
this.getSelectionModel().setSelectionInterval$I$I(anchor, lead);
});

Clazz.newMeth(C$, 'addSelectionInterval$I$I', function (anchor, lead) {
this.getSelectionModel().addSelectionInterval$I$I(anchor, lead);
});

Clazz.newMeth(C$, 'removeSelectionInterval$I$I', function (index0, index1) {
this.getSelectionModel().removeSelectionInterval$I$I(index0, index1);
});

Clazz.newMeth(C$, 'setValueIsAdjusting$Z', function (b) {
this.getSelectionModel().setValueIsAdjusting$Z(b);
});

Clazz.newMeth(C$, 'getValueIsAdjusting', function () {
return this.getSelectionModel().getValueIsAdjusting();
});

Clazz.newMeth(C$, 'getSelectedIndices', function () {
var sm = this.getSelectionModel();
var iMin = sm.getMinSelectionIndex();
var iMax = sm.getMaxSelectionIndex();
if ((iMin < 0) || (iMax < 0) ) {
return Clazz.array(Integer.TYPE, [0]);
}var rvTmp = Clazz.array(Integer.TYPE, [1 + (iMax - iMin)]);
var n = 0;
for (var i = iMin; i <= iMax; i++) {
if (sm.isSelectedIndex$I(i)) {
rvTmp[n++] = i;
}}
var rv = Clazz.array(Integer.TYPE, [n]);
System.arraycopy(rvTmp, 0, rv, 0, n);
return rv;
});

Clazz.newMeth(C$, 'setSelectedIndex$I', function (index) {
if (index >= this.getModel().getSize()) {
return;
}this.getSelectionModel().setSelectionInterval$I$I(index, index);
});

Clazz.newMeth(C$, 'setSelectedIndices$IA', function (indices) {
var sm = this.getSelectionModel();
sm.clearSelection();
var size = this.getModel().getSize();
for (var i = 0; i < indices.length; i++) {
if (indices[i] < size) {
sm.addSelectionInterval$I$I(indices[i], indices[i]);
}}
});

Clazz.newMeth(C$, 'getSelectedValues', function () {
var sm = this.getSelectionModel();
var dm = this.getModel();
var iMin = sm.getMinSelectionIndex();
var iMax = sm.getMaxSelectionIndex();
if ((iMin < 0) || (iMax < 0) ) {
return Clazz.array(java.lang.Object, [0]);
}var rvTmp = Clazz.array(java.lang.Object, [1 + (iMax - iMin)]);
var n = 0;
for (var i = iMin; i <= iMax; i++) {
if (sm.isSelectedIndex$I(i)) {
rvTmp[n++] = dm.getElementAt$I(i);
}}
var rv = Clazz.array(java.lang.Object, [n]);
System.arraycopy(rvTmp, 0, rv, 0, n);
return rv;
});

Clazz.newMeth(C$, 'getSelectedIndex', function () {
return this.getMinSelectionIndex();
});

Clazz.newMeth(C$, 'getSelectedValue', function () {
var i = this.getMinSelectionIndex();
return (i == -1) ? null : this.getModel().getElementAt$I(i);
});

Clazz.newMeth(C$, 'setSelectedValue$O$Z', function (anObject, shouldScroll) {
if (anObject == null ) this.setSelectedIndex$I(-1);
 else if (!anObject.equals$O(this.getSelectedValue())) {
var i;
var c;
var dm = this.getModel();
for (i = 0, c = dm.getSize(); i < c; i++) if (anObject.equals$O(dm.getElementAt$I(i))) {
this.setSelectedIndex$I(i);
if (shouldScroll) this.ensureIndexIsVisible$I(i);
this.repaint();
return;
}
this.setSelectedIndex$I(-1);
}this.repaint();
});

Clazz.newMeth(C$, 'checkScrollableParameters$java_awt_Rectangle$I', function (visibleRect, orientation) {
if (visibleRect == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["visibleRect must be non-null"]);
}switch (orientation) {
case 1:
case 0:
break;
default:
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["orientation must be one of: VERTICAL, HORIZONTAL"]);
}
});

Clazz.newMeth(C$, 'getPreferredScrollableViewportSize', function () {
if (this.getLayoutOrientation() != 0) {
return this.getPreferredSize();
}var insets = this.getInsets();
var dx = insets.left + insets.right;
var dy = insets.top + insets.bottom;
var visibleRowCount = this.getVisibleRowCount();
var fixedCellWidth = this.getFixedCellWidth();
var fixedCellHeight = this.getFixedCellHeight();
if ((fixedCellWidth > 0) && (fixedCellHeight > 0) ) {
var width = fixedCellWidth + dx;
var height = (visibleRowCount * fixedCellHeight) + dy;
return Clazz.new_((I$[11]||$incl$(11)).c$$I$I,[width, height]);
} else if (this.getModel().getSize() > 0) {
var width = this.getPreferredSize().width;
var height;
var r = this.getCellBounds$I$I(0, 0);
if (r != null ) {
height = (visibleRowCount * r.height) + dy;
} else {
height = 1;
}return Clazz.new_((I$[11]||$incl$(11)).c$$I$I,[width, height]);
} else {
fixedCellWidth = (fixedCellWidth > 0) ? fixedCellWidth : 256;
fixedCellHeight = (fixedCellHeight > 0) ? fixedCellHeight : 16;
return Clazz.new_((I$[11]||$incl$(11)).c$$I$I,[fixedCellWidth, fixedCellHeight * visibleRowCount]);
}});

Clazz.newMeth(C$, 'getScrollableUnitIncrement$java_awt_Rectangle$I$I', function (visibleRect, orientation, direction) {
p$.checkScrollableParameters$java_awt_Rectangle$I.apply(this, [visibleRect, orientation]);
if (orientation == 1) {
var row = this.locationToIndex$java_awt_Point(visibleRect.getLocation());
if (row == -1) {
return 0;
} else {
if (direction > 0) {
var r = this.getCellBounds$I$I(row, row);
return (r == null ) ? 0 : r.height - (visibleRect.y - r.y);
} else {
var r = this.getCellBounds$I$I(row, row);
if ((r.y == visibleRect.y) && (row == 0) ) {
return 0;
} else if (r.y == visibleRect.y) {
var loc = r.getLocation();
loc.y--;
var prevIndex = this.locationToIndex$java_awt_Point(loc);
var prevR = this.getCellBounds$I$I(prevIndex, prevIndex);
if (prevR == null  || prevR.y >= r.y ) {
return 0;
}return prevR.height;
} else {
return visibleRect.y - r.y;
}}}} else if (orientation == 0 && this.getLayoutOrientation() != 0 ) {
var leftToRight = this.getComponentOrientation().isLeftToRight();
var index;
var leadingPoint;
if (leftToRight) {
leadingPoint = visibleRect.getLocation();
} else {
leadingPoint = Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[visibleRect.x + visibleRect.width - 1, visibleRect.y]);
}index = this.locationToIndex$java_awt_Point(leadingPoint);
if (index != -1) {
var cellBounds = this.getCellBounds$I$I(index, index);
if (cellBounds != null  && cellBounds.contains$java_awt_Point(leadingPoint) ) {
var leadingVisibleEdge;
var leadingCellEdge;
if (leftToRight) {
leadingVisibleEdge = visibleRect.x;
leadingCellEdge = cellBounds.x;
} else {
leadingVisibleEdge = visibleRect.x + visibleRect.width;
leadingCellEdge = cellBounds.x + cellBounds.width;
}if (leadingCellEdge != leadingVisibleEdge) {
if (direction < 0) {
return Math.abs(leadingVisibleEdge - leadingCellEdge);
} else if (leftToRight) {
return leadingCellEdge + cellBounds.width - leadingVisibleEdge;
} else {
return leadingVisibleEdge - cellBounds.x;
}}return cellBounds.width;
}}}var f = this.getFont();
return (f != null ) ? f.getSize() : 1;
});

Clazz.newMeth(C$, 'getScrollableBlockIncrement$java_awt_Rectangle$I$I', function (visibleRect, orientation, direction) {
p$.checkScrollableParameters$java_awt_Rectangle$I.apply(this, [visibleRect, orientation]);
if (orientation == 1) {
var inc = visibleRect.height;
if (direction > 0) {
var last = this.locationToIndex$java_awt_Point(Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[visibleRect.x, visibleRect.y + visibleRect.height - 1]));
if (last != -1) {
var lastRect = this.getCellBounds$I$I(last, last);
if (lastRect != null ) {
inc = lastRect.y - visibleRect.y;
if ((inc == 0) && (last < this.getModel().getSize() - 1) ) {
inc = lastRect.height;
}}}} else {
var newFirst = this.locationToIndex$java_awt_Point(Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[visibleRect.x, visibleRect.y - visibleRect.height]));
var first = this.getFirstVisibleIndex();
if (newFirst != -1) {
if (first == -1) {
first = this.locationToIndex$java_awt_Point(visibleRect.getLocation());
}var newFirstRect = this.getCellBounds$I$I(newFirst, newFirst);
var firstRect = this.getCellBounds$I$I(first, first);
if ((newFirstRect != null ) && (firstRect != null ) ) {
while ((newFirstRect.y + visibleRect.height < firstRect.y + firstRect.height) && (newFirstRect.y < firstRect.y) ){
newFirst++;
newFirstRect = this.getCellBounds$I$I(newFirst, newFirst);
}
inc = visibleRect.y - newFirstRect.y;
if ((inc <= 0) && (newFirstRect.y > 0) ) {
newFirst--;
newFirstRect = this.getCellBounds$I$I(newFirst, newFirst);
if (newFirstRect != null ) {
inc = visibleRect.y - newFirstRect.y;
}}}}}return inc;
} else if (orientation == 0 && this.getLayoutOrientation() != 0 ) {
var leftToRight = this.getComponentOrientation().isLeftToRight();
var inc = visibleRect.width;
if (direction > 0) {
var x = visibleRect.x + (leftToRight ? (visibleRect.width - 1) : 0);
var last = this.locationToIndex$java_awt_Point(Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[x, visibleRect.y]));
if (last != -1) {
var lastRect = this.getCellBounds$I$I(last, last);
if (lastRect != null ) {
if (leftToRight) {
inc = lastRect.x - visibleRect.x;
} else {
inc = visibleRect.x + visibleRect.width - (lastRect.x + lastRect.width);
}if (inc < 0) {
inc = inc+(lastRect.width);
} else if ((inc == 0) && (last < this.getModel().getSize() - 1) ) {
inc = lastRect.width;
}}}} else {
var x = visibleRect.x + (leftToRight ? -visibleRect.width : visibleRect.width - 1 + visibleRect.width);
var first = this.locationToIndex$java_awt_Point(Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[x, visibleRect.y]));
if (first != -1) {
var firstRect = this.getCellBounds$I$I(first, first);
if (firstRect != null ) {
var firstRight = firstRect.x + firstRect.width;
if (leftToRight) {
if ((firstRect.x < visibleRect.x - visibleRect.width) && (firstRight < visibleRect.x) ) {
inc = visibleRect.x - firstRight;
} else {
inc = visibleRect.x - firstRect.x;
}} else {
var visibleRight = visibleRect.x + visibleRect.width;
if ((firstRight > visibleRight + visibleRect.width) && (firstRect.x > visibleRight) ) {
inc = firstRect.x - visibleRight;
} else {
inc = firstRight - visibleRight;
}}}}}return inc;
}return visibleRect.width;
});

Clazz.newMeth(C$, 'getScrollableTracksViewportWidth', function () {
if (this.getLayoutOrientation() == 2 && this.getVisibleRowCount() <= 0 ) {
return true;
}if (Clazz.instanceOf(this.getParent(), "javax.swing.JViewport")) {
return ((this.getParent()).getWidth() > this.getPreferredSize().width);
}return false;
});

Clazz.newMeth(C$, 'getScrollableTracksViewportHeight', function () {
if (this.getLayoutOrientation() == 1 && this.getVisibleRowCount() <= 0 ) {
return true;
}if (Clazz.instanceOf(this.getParent(), "javax.swing.JViewport")) {
return ((this.getParent()).getHeight() > this.getPreferredSize().height);
}return false;
});

Clazz.newMeth(C$, 'paramString', function () {
var selectionForegroundString = (this.selectionForeground != null  ? this.selectionForeground.toString() : "");
var selectionBackgroundString = (this.selectionBackground != null  ? this.selectionBackground.toString() : "");
return C$.superclazz.prototype.paramString.apply(this, []) + ",fixedCellHeight=" + this.fixedCellHeight + ",fixedCellWidth=" + this.fixedCellWidth + ",horizontalScrollIncrement=" + this.horizontalScrollIncrement + ",selectionBackground=" + selectionBackgroundString + ",selectionForeground=" + selectionForegroundString + ",visibleRowCount=" + this.visibleRowCount + ",layoutOrientation=" + this.layoutOrientation ;
});
;
(function(){var C$=Clazz.newClass(P$.JList, "DropLocation", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.index = 0;
this.$isInsert = false;
this.dropPoint = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getDropPoint', function () {
return this.dropPoint;
});

Clazz.newMeth(C$, 'c$$java_awt_Point$I$Z', function (p, index, isInsert) {
C$.$init$.apply(this);
this.dropPoint = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Point,[p]);
this.index = index;
this.$isInsert = isInsert;
}, 1);

Clazz.newMeth(C$, 'getIndex', function () {
return this.index;
});

Clazz.newMeth(C$, 'isInsert', function () {
return this.$isInsert;
});

Clazz.newMeth(C$, 'toString', function () {
return this.getClass().getName() + "[" + "index=" + this.index + "," + "insert=" + this.$isInsert + "]" ;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JList, "ListSelectionHandler", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'javax.swing.event.ListSelectionListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'valueChanged$javax_swing_event_ListSelectionEvent', function (e) {
this.this$0.fireSelectionValueChanged$I$I$Z(e.getFirstIndex(), e.getLastIndex(), e.getValueIsAdjusting());
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-08 10:02:31
