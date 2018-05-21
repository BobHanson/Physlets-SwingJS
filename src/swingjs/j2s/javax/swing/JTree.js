(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.lang.Boolean','java.util.Vector','javax.swing.tree.DefaultMutableTreeNode','javax.swing.tree.DefaultTreeModel',['javax.swing.JTree','.DynamicUtilTreeNode'],'java.util.Stack','java.util.Hashtable','javax.swing.tree.DefaultTreeSelectionModel','javax.swing.SwingUtilities','javax.swing.tree.TreePath','java.awt.event.MouseEvent','java.util.Collections',['javax.swing.JTree','.EmptySelectionModel'],'javax.swing.event.TreeExpansionListener','javax.swing.event.TreeWillExpandListener','javax.swing.event.TreeExpansionEvent','javax.swing.event.TreeSelectionListener',['javax.swing.JTree','.TreeSelectionRedirector'],['javax.swing.text.Position','.Bias'],'java.awt.Dimension',['javax.swing.JTree','.TreeModelHandler']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JTree", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.JComponent', 'javax.swing.Scrollable');
C$.TEMP_STACK_SIZE = 0;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.TEMP_STACK_SIZE = 11;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.treeModel = null;
this.selectionModel = null;
this.rootVisible = false;
this.cellRenderer = null;
this.rowHeight = 0;
this.rowHeightSet = false;
this.expandedState = null;
this.showsRootHandles = false;
this.showsRootHandlesSet = false;
this.selectionRedirector = null;
this.cellEditor = null;
this.editable = false;
this.largeModel = false;
this.visibleRowCount = 0;
this.invokesStopCellEditing = false;
this.scrollsOnExpand = false;
this.scrollsOnExpandSet = false;
this.toggleClickCount = 0;
this.treeModelListener = null;
this.expandedStack = null;
this.leadPath = null;
this.anchorPath = null;
this.expandsSelectedPaths = false;
this.settingUI = false;
this.dragEnabled = false;
this.uiTreeExpansionListener = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.rowHeightSet = false;
this.showsRootHandlesSet = false;
this.scrollsOnExpandSet = false;
}, 1);

Clazz.newMeth(C$, 'getDefaultTreeModel', function () {
var root = Clazz.new_((I$[3]||$incl$(3)).c$$O,["JTree"]);
var parent;
parent = Clazz.new_((I$[3]||$incl$(3)).c$$O,["colors"]);
root.add$javax_swing_tree_MutableTreeNode(parent);
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["blue"]));
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["violet"]));
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["red"]));
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["yellow"]));
parent = Clazz.new_((I$[3]||$incl$(3)).c$$O,["sports"]);
root.add$javax_swing_tree_MutableTreeNode(parent);
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["basketball"]));
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["soccer"]));
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["football"]));
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["hockey"]));
parent = Clazz.new_((I$[3]||$incl$(3)).c$$O,["food"]);
root.add$javax_swing_tree_MutableTreeNode(parent);
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["hot dogs"]));
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["pizza"]));
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["ravioli"]));
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_((I$[3]||$incl$(3)).c$$O,["bananas"]));
return Clazz.new_((I$[4]||$incl$(4)).c$$javax_swing_tree_TreeNode,[root]);
}, 1);

Clazz.newMeth(C$, 'createTreeModel$O', function (value) {
var root;
if ((Clazz.instanceOf(value, Clazz.array(java.lang.Object, -1))) || (Clazz.instanceOf(value, "java.util.Hashtable")) || (Clazz.instanceOf(value, "java.util.Vector"))  ) {
root = Clazz.new_((I$[3]||$incl$(3)).c$$O,["root"]);
(I$[5]||$incl$(5)).createChildren$javax_swing_tree_DefaultMutableTreeNode$O(root, value);
} else {
root = Clazz.new_((I$[5]||$incl$(5)).c$$O$O,["root", value]);
}return Clazz.new_((I$[4]||$incl$(4)).c$$javax_swing_tree_TreeNode$Z,[root, false]);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$javax_swing_tree_TreeModel.apply(this, [C$.getDefaultTreeModel()]);
}, 1);

Clazz.newMeth(C$, 'c$$OA', function (value) {
C$.c$$javax_swing_tree_TreeModel.apply(this, [C$.createTreeModel$O(value)]);
this.setRootVisible$Z(false);
this.setShowsRootHandles$Z(true);
p$.expandRoot.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Vector', function (value) {
C$.c$$javax_swing_tree_TreeModel.apply(this, [C$.createTreeModel$O(value)]);
this.setRootVisible$Z(false);
this.setShowsRootHandles$Z(true);
p$.expandRoot.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Hashtable', function (value) {
C$.c$$javax_swing_tree_TreeModel.apply(this, [C$.createTreeModel$O(value)]);
this.setRootVisible$Z(false);
this.setShowsRootHandles$Z(true);
p$.expandRoot.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_tree_TreeNode', function (root) {
C$.c$$javax_swing_tree_TreeNode$Z.apply(this, [root, false]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_tree_TreeNode$Z', function (root, asksAllowsChildren) {
C$.c$$javax_swing_tree_TreeModel.apply(this, [Clazz.new_((I$[4]||$incl$(4)).c$$javax_swing_tree_TreeNode$Z,[root, asksAllowsChildren])]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_tree_TreeModel', function (newModel) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.expandedStack = Clazz.new_((I$[6]||$incl$(6)));
this.toggleClickCount = 2;
this.expandedState = Clazz.new_((I$[7]||$incl$(7)));
this.setLayout$java_awt_LayoutManager(null);
this.rowHeight = 16;
this.visibleRowCount = 20;
this.rootVisible = true;
this.selectionModel = Clazz.new_((I$[8]||$incl$(8)));
this.cellRenderer = null;
this.scrollsOnExpand = true;
this.setOpaque$Z(true);
this.expandsSelectedPaths = true;
this.uiClassID = "TreeUI";
this.updateUI();
this.setModel$javax_swing_tree_TreeModel(newModel);
}, 1);

Clazz.newMeth(C$, 'setUI$javax_swing_plaf_TreeUI', function (ui) {
if (this.ui !== ui ) {
this.settingUI = true;
this.uiTreeExpansionListener = null;
try {
C$.superclazz.prototype.setUI$javax_swing_plaf_ComponentUI.apply(this, [ui]);
} finally {
this.settingUI = false;
}
}});

Clazz.newMeth(C$, 'updateUI', function () {
C$.superclazz.prototype.updateUI.apply(this, []);
(I$[9]||$incl$(9)).updateRendererOrEditorUI$O(this.getCellRenderer());
(I$[9]||$incl$(9)).updateRendererOrEditorUI$O(this.getCellEditor());
});

Clazz.newMeth(C$, 'getCellRenderer', function () {
return this.cellRenderer;
});

Clazz.newMeth(C$, 'setCellRenderer$javax_swing_tree_TreeCellRenderer', function (x) {
var oldValue = this.cellRenderer;
this.cellRenderer = x;
this.firePropertyChange$S$O$O("cellRenderer", oldValue, this.cellRenderer);
this.invalidate();
});

Clazz.newMeth(C$, 'setEditable$Z', function (flag) {
var oldValue = this.editable;
this.editable = flag;
this.firePropertyChange$S$Z$Z("editable", oldValue, flag);
});

Clazz.newMeth(C$, 'isEditable', function () {
return this.editable;
});

Clazz.newMeth(C$, 'setCellEditor$javax_swing_tree_TreeCellEditor', function (cellEditor) {
var oldEditor = this.cellEditor;
this.cellEditor = cellEditor;
this.firePropertyChange$S$O$O("cellEditor", oldEditor, cellEditor);
this.invalidate();
});

Clazz.newMeth(C$, 'getCellEditor', function () {
return this.cellEditor;
});

Clazz.newMeth(C$, 'getModel', function () {
return this.treeModel;
});

Clazz.newMeth(C$, 'setModel$javax_swing_tree_TreeModel', function (newModel) {
this.clearSelection();
var oldModel = this.treeModel;
if (this.treeModel != null  && this.treeModelListener != null  ) this.treeModel.removeTreeModelListener$javax_swing_event_TreeModelListener(this.treeModelListener);
this.treeModel = newModel;
this.clearToggledPaths();
if (this.treeModel != null ) {
if (this.treeModelListener == null ) this.treeModelListener = this.createTreeModelListener();
if (this.treeModelListener != null ) this.treeModel.addTreeModelListener$javax_swing_event_TreeModelListener(this.treeModelListener);
if (this.treeModel.getRoot() != null  && !this.treeModel.isLeaf$O(this.treeModel.getRoot()) ) {
this.expandedState.put$TK$TV(Clazz.new_((I$[10]||$incl$(10)).c$$O,[this.treeModel.getRoot()]), (I$[1]||$incl$(1)).TRUE);
}}this.firePropertyChange$S$O$O("model", oldModel, this.treeModel);
this.invalidate();
});

Clazz.newMeth(C$, 'isRootVisible', function () {
return this.rootVisible;
});

Clazz.newMeth(C$, 'setRootVisible$Z', function (rootVisible) {
var oldValue = this.rootVisible;
this.rootVisible = rootVisible;
this.firePropertyChange$S$Z$Z("rootVisible", oldValue, this.rootVisible);
});

Clazz.newMeth(C$, 'setShowsRootHandles$Z', function (newValue) {
var oldValue = this.showsRootHandles;
this.showsRootHandles = newValue;
this.showsRootHandlesSet = true;
this.firePropertyChange$S$Z$Z("showsRootHandles", oldValue, this.showsRootHandles);
this.invalidate();
});

Clazz.newMeth(C$, 'getShowsRootHandles', function () {
return this.showsRootHandles;
});

Clazz.newMeth(C$, 'setRowHeight$I', function (rowHeight) {
var oldValue = this.rowHeight;
this.rowHeight = rowHeight;
this.rowHeightSet = true;
this.firePropertyChange$S$I$I("rowHeight", oldValue, this.rowHeight);
this.invalidate();
});

Clazz.newMeth(C$, 'getRowHeight', function () {
return this.rowHeight;
});

Clazz.newMeth(C$, 'isFixedRowHeight', function () {
return (this.rowHeight > 0);
});

Clazz.newMeth(C$, 'setLargeModel$Z', function (newValue) {
var oldValue = this.largeModel;
this.largeModel = newValue;
this.firePropertyChange$S$Z$Z("largeModel", oldValue, newValue);
});

Clazz.newMeth(C$, 'isLargeModel', function () {
return this.largeModel;
});

Clazz.newMeth(C$, 'setInvokesStopCellEditing$Z', function (newValue) {
var oldValue = this.invokesStopCellEditing;
this.invokesStopCellEditing = newValue;
this.firePropertyChange$S$Z$Z("invokesStopCellEditing", oldValue, newValue);
});

Clazz.newMeth(C$, 'getInvokesStopCellEditing', function () {
return this.invokesStopCellEditing;
});

Clazz.newMeth(C$, 'setScrollsOnExpand$Z', function (newValue) {
var oldValue = this.scrollsOnExpand;
this.scrollsOnExpand = newValue;
this.scrollsOnExpandSet = true;
this.firePropertyChange$S$Z$Z("scrollsOnExpand", oldValue, newValue);
});

Clazz.newMeth(C$, 'getScrollsOnExpand', function () {
return this.scrollsOnExpand;
});

Clazz.newMeth(C$, 'setToggleClickCount$I', function (clickCount) {
var oldCount = this.toggleClickCount;
this.toggleClickCount = clickCount;
this.firePropertyChange$S$I$I("toggleClickCount", oldCount, clickCount);
});

Clazz.newMeth(C$, 'getToggleClickCount', function () {
return this.toggleClickCount;
});

Clazz.newMeth(C$, 'setExpandsSelectedPaths$Z', function (newValue) {
var oldValue = this.expandsSelectedPaths;
this.expandsSelectedPaths = newValue;
this.firePropertyChange$S$Z$Z("expandsSelectedPaths", oldValue, newValue);
});

Clazz.newMeth(C$, 'getExpandsSelectedPaths', function () {
return this.expandsSelectedPaths;
});

Clazz.newMeth(C$, 'setDragEnabled$Z', function (b) {
this.dragEnabled = b;
});

Clazz.newMeth(C$, 'getDragEnabled', function () {
return this.dragEnabled;
});

Clazz.newMeth(C$, 'isPathEditable$javax_swing_tree_TreePath', function (path) {
return this.isEditable();
});

Clazz.newMeth(C$, 'getToolTipText$java_awt_event_MouseEvent', function (event) {
var tip = null;
if (event != null ) {
var p = event.getPoint();
var selRow = this.getRowForLocation$I$I(p.x, p.y);
var r = this.getCellRenderer();
if (selRow != -1 && r != null  ) {
var path = this.getPathForRow$I(selRow);
var lastPath = path.getLastPathComponent();
var rComponent = r.getTreeCellRendererComponent$javax_swing_JTree$O$Z$Z$Z$I$Z(this, lastPath, this.isRowSelected$I(selRow), this.isExpanded$I(selRow), this.getModel().isLeaf$O(lastPath), selRow, true);
if (Clazz.instanceOf(rComponent, "javax.swing.JComponent")) {
var newEvent;
var pathBounds = this.getPathBounds$javax_swing_tree_TreePath(path);
p.translate$I$I(-pathBounds.x, -pathBounds.y);
newEvent = Clazz.new_((I$[11]||$incl$(11)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I,[rComponent, event.getID(), event.getWhen(), event.getModifiers(), p.x, p.y, event.getXOnScreen(), event.getYOnScreen(), event.getClickCount(), event.isPopupTrigger(), 0]);
tip = (rComponent).getToolTipText$java_awt_event_MouseEvent(newEvent);
}}}if (tip == null ) {
tip = this.getToolTipText();
}return tip;
});

Clazz.newMeth(C$, 'convertValueToText$O$Z$Z$Z$I$Z', function (value, selected, expanded, leaf, row, hasFocus) {
if (value != null ) {
var sValue = value.toString();
if (sValue != null ) {
return sValue;
}}return "";
});

Clazz.newMeth(C$, 'getRowCount', function () {
var tree = this.getUI();
if (tree != null ) return tree.getRowCount$javax_swing_JTree(this);
return 0;
});

Clazz.newMeth(C$, 'setSelectionPath$javax_swing_tree_TreePath', function (path) {
this.getSelectionModel().setSelectionPath$javax_swing_tree_TreePath(path);
});

Clazz.newMeth(C$, 'setSelectionPaths$javax_swing_tree_TreePathA', function (paths) {
this.getSelectionModel().setSelectionPaths$javax_swing_tree_TreePathA(paths);
});

Clazz.newMeth(C$, 'setLeadSelectionPath$javax_swing_tree_TreePath', function (newPath) {
var oldValue = this.leadPath;
this.leadPath = newPath;
this.firePropertyChange$S$O$O("leadSelectionPath", oldValue, newPath);
});

Clazz.newMeth(C$, 'setAnchorSelectionPath$javax_swing_tree_TreePath', function (newPath) {
var oldValue = this.anchorPath;
this.anchorPath = newPath;
this.firePropertyChange$S$O$O("anchorSelectionPath", oldValue, newPath);
});

Clazz.newMeth(C$, 'setSelectionRow$I', function (row) {
var rows = Clazz.array(Integer.TYPE, -1, [row]);
this.setSelectionRows$IA(rows);
});

Clazz.newMeth(C$, 'setSelectionRows$IA', function (rows) {
var ui = this.getUI();
if (ui != null  && rows != null  ) {
var numRows = rows.length;
var paths = Clazz.array((I$[10]||$incl$(10)), [numRows]);
for (var counter = 0; counter < numRows; counter++) {
paths[counter] = ui.getPathForRow$javax_swing_JTree$I(this, rows[counter]);
}
this.setSelectionPaths$javax_swing_tree_TreePathA(paths);
}});

Clazz.newMeth(C$, 'addSelectionPath$javax_swing_tree_TreePath', function (path) {
this.getSelectionModel().addSelectionPath$javax_swing_tree_TreePath(path);
});

Clazz.newMeth(C$, 'addSelectionPaths$javax_swing_tree_TreePathA', function (paths) {
this.getSelectionModel().addSelectionPaths$javax_swing_tree_TreePathA(paths);
});

Clazz.newMeth(C$, 'addSelectionRow$I', function (row) {
var rows = Clazz.array(Integer.TYPE, -1, [row]);
this.addSelectionRows$IA(rows);
});

Clazz.newMeth(C$, 'addSelectionRows$IA', function (rows) {
var ui = this.getUI();
if (ui != null  && rows != null  ) {
var numRows = rows.length;
var paths = Clazz.array((I$[10]||$incl$(10)), [numRows]);
for (var counter = 0; counter < numRows; counter++) paths[counter] = ui.getPathForRow$javax_swing_JTree$I(this, rows[counter]);

this.addSelectionPaths$javax_swing_tree_TreePathA(paths);
}});

Clazz.newMeth(C$, 'getLastSelectedPathComponent', function () {
var selPath = this.getSelectionModel().getSelectionPath();
if (selPath != null ) return selPath.getLastPathComponent();
return null;
});

Clazz.newMeth(C$, 'getLeadSelectionPath', function () {
return this.leadPath;
});

Clazz.newMeth(C$, 'getAnchorSelectionPath', function () {
return this.anchorPath;
});

Clazz.newMeth(C$, 'getSelectionPath', function () {
return this.getSelectionModel().getSelectionPath();
});

Clazz.newMeth(C$, 'getSelectionPaths', function () {
return this.getSelectionModel().getSelectionPaths();
});

Clazz.newMeth(C$, 'getSelectionRows', function () {
return this.getSelectionModel().getSelectionRows();
});

Clazz.newMeth(C$, 'getSelectionCount', function () {
return this.selectionModel.getSelectionCount();
});

Clazz.newMeth(C$, 'getMinSelectionRow', function () {
return this.getSelectionModel().getMinSelectionRow();
});

Clazz.newMeth(C$, 'getMaxSelectionRow', function () {
return this.getSelectionModel().getMaxSelectionRow();
});

Clazz.newMeth(C$, 'getLeadSelectionRow', function () {
var leadPath = this.getLeadSelectionPath();
if (leadPath != null ) {
return this.getRowForPath$javax_swing_tree_TreePath(leadPath);
}return -1;
});

Clazz.newMeth(C$, 'isPathSelected$javax_swing_tree_TreePath', function (path) {
return this.getSelectionModel().isPathSelected$javax_swing_tree_TreePath(path);
});

Clazz.newMeth(C$, 'isRowSelected$I', function (row) {
return this.getSelectionModel().isRowSelected$I(row);
});

Clazz.newMeth(C$, 'getExpandedDescendants$javax_swing_tree_TreePath', function (parent) {
if (!this.isExpanded$javax_swing_tree_TreePath(parent)) return null;
var toggledPaths = this.expandedState.keys();
var elements = null;
var path;
var value;
if (toggledPaths != null ) {
while (toggledPaths.hasMoreElements()){
path = toggledPaths.nextElement();
value = this.expandedState.get$O(path);
if (path !== parent  && value != null   && (value).booleanValue()  && parent.isDescendant$javax_swing_tree_TreePath(path)  && this.isVisible$javax_swing_tree_TreePath(path) ) {
if (elements == null ) {
elements = Clazz.new_((I$[2]||$incl$(2)));
}elements.addElement$TE(path);
}}
}if (elements == null ) {
var empty = (I$[12]||$incl$(12)).emptySet();
return (I$[12]||$incl$(12)).enumeration$java_util_Collection(empty);
}return elements.elements();
});

Clazz.newMeth(C$, 'hasBeenExpanded$javax_swing_tree_TreePath', function (path) {
return (path != null  && this.expandedState.get$O(path) != null  );
});

Clazz.newMeth(C$, 'isExpanded$javax_swing_tree_TreePath', function (path) {
if (path == null ) return false;
var value = this.expandedState.get$O(path);
if (value == null  || !(value).booleanValue() ) return false;
var parentPath = path.getParentPath();
if (parentPath != null ) return this.isExpanded$javax_swing_tree_TreePath(parentPath);
return true;
});

Clazz.newMeth(C$, 'isExpanded$I', function (row) {
var tree = this.getUI();
if (tree != null ) {
var path = tree.getPathForRow$javax_swing_JTree$I(this, row);
if (path != null ) {
var value = this.expandedState.get$O(path);
return (value != null  && value.booleanValue() );
}}return false;
});

Clazz.newMeth(C$, 'isCollapsed$javax_swing_tree_TreePath', function (path) {
return !this.isExpanded$javax_swing_tree_TreePath(path);
});

Clazz.newMeth(C$, 'isCollapsed$I', function (row) {
return !this.isExpanded$I(row);
});

Clazz.newMeth(C$, 'makeVisible$javax_swing_tree_TreePath', function (path) {
if (path != null ) {
var parentPath = path.getParentPath();
if (parentPath != null ) {
this.expandPath$javax_swing_tree_TreePath(parentPath);
}}});

Clazz.newMeth(C$, 'isVisible$javax_swing_tree_TreePath', function (path) {
if (path != null ) {
var parentPath = path.getParentPath();
if (parentPath != null ) return this.isExpanded$javax_swing_tree_TreePath(parentPath);
return true;
}return false;
});

Clazz.newMeth(C$, 'getPathBounds$javax_swing_tree_TreePath', function (path) {
var tree = this.getUI();
if (tree != null ) return tree.getPathBounds$javax_swing_JTree$javax_swing_tree_TreePath(this, path);
return null;
});

Clazz.newMeth(C$, 'getRowBounds$I', function (row) {
return this.getPathBounds$javax_swing_tree_TreePath(this.getPathForRow$I(row));
});

Clazz.newMeth(C$, 'scrollPathToVisible$javax_swing_tree_TreePath', function (path) {
if (path != null ) {
this.makeVisible$javax_swing_tree_TreePath(path);
var bounds = this.getPathBounds$javax_swing_tree_TreePath(path);
if (bounds != null ) {
this.scrollRectToVisible$java_awt_Rectangle(bounds);
}}});

Clazz.newMeth(C$, 'scrollRowToVisible$I', function (row) {
this.scrollPathToVisible$javax_swing_tree_TreePath(this.getPathForRow$I(row));
});

Clazz.newMeth(C$, 'getPathForRow$I', function (row) {
var tree = this.getUI();
if (tree != null ) return tree.getPathForRow$javax_swing_JTree$I(this, row);
return null;
});

Clazz.newMeth(C$, 'getRowForPath$javax_swing_tree_TreePath', function (path) {
var tree = this.getUI();
if (tree != null ) return tree.getRowForPath$javax_swing_JTree$javax_swing_tree_TreePath(this, path);
return -1;
});

Clazz.newMeth(C$, 'expandPath$javax_swing_tree_TreePath', function (path) {
var model = this.getModel();
if (path != null  && model != null   && !model.isLeaf$O(path.getLastPathComponent()) ) {
this.setExpandedState$javax_swing_tree_TreePath$Z(path, true);
}});

Clazz.newMeth(C$, 'expandRow$I', function (row) {
this.expandPath$javax_swing_tree_TreePath(this.getPathForRow$I(row));
});

Clazz.newMeth(C$, 'collapsePath$javax_swing_tree_TreePath', function (path) {
this.setExpandedState$javax_swing_tree_TreePath$Z(path, false);
});

Clazz.newMeth(C$, 'collapseRow$I', function (row) {
this.collapsePath$javax_swing_tree_TreePath(this.getPathForRow$I(row));
});

Clazz.newMeth(C$, 'getPathForLocation$I$I', function (x, y) {
var closestPath = this.getClosestPathForLocation$I$I(x, y);
if (closestPath != null ) {
var pathBounds = this.getPathBounds$javax_swing_tree_TreePath(closestPath);
if (pathBounds != null  && x >= pathBounds.x  && x < (pathBounds.x + pathBounds.width)  && y >= pathBounds.y  && y < (pathBounds.y + pathBounds.height) ) return closestPath;
}return null;
});

Clazz.newMeth(C$, 'getRowForLocation$I$I', function (x, y) {
return this.getRowForPath$javax_swing_tree_TreePath(this.getPathForLocation$I$I(x, y));
});

Clazz.newMeth(C$, 'getClosestPathForLocation$I$I', function (x, y) {
var tree = this.getUI();
if (tree != null ) return tree.getClosestPathForLocation$javax_swing_JTree$I$I(this, x, y);
return null;
});

Clazz.newMeth(C$, 'getClosestRowForLocation$I$I', function (x, y) {
return this.getRowForPath$javax_swing_tree_TreePath(this.getClosestPathForLocation$I$I(x, y));
});

Clazz.newMeth(C$, 'isEditing', function () {
var tree = this.getUI();
if (tree != null ) return tree.isEditing$javax_swing_JTree(this);
return false;
});

Clazz.newMeth(C$, 'stopEditing', function () {
var tree = this.getUI();
if (tree != null ) return tree.stopEditing$javax_swing_JTree(this);
return false;
});

Clazz.newMeth(C$, 'cancelEditing', function () {
var tree = this.getUI();
if (tree != null ) tree.cancelEditing$javax_swing_JTree(this);
});

Clazz.newMeth(C$, 'startEditingAtPath$javax_swing_tree_TreePath', function (path) {
var tree = this.getUI();
if (tree != null ) tree.startEditingAtPath$javax_swing_JTree$javax_swing_tree_TreePath(this, path);
});

Clazz.newMeth(C$, 'getEditingPath', function () {
var tree = this.getUI();
if (tree != null ) return tree.getEditingPath$javax_swing_JTree(this);
return null;
});

Clazz.newMeth(C$, 'setSelectionModel$javax_swing_tree_TreeSelectionModel', function (selectionModel) {
if (selectionModel == null ) selectionModel = (I$[13]||$incl$(13)).sharedInstance();
var oldValue = this.selectionModel;
if (this.selectionModel != null  && this.selectionRedirector != null  ) {
this.selectionModel.removeTreeSelectionListener$javax_swing_event_TreeSelectionListener(this.selectionRedirector);
}this.selectionModel = selectionModel;
if (this.selectionRedirector != null ) {
this.selectionModel.addTreeSelectionListener$javax_swing_event_TreeSelectionListener(this.selectionRedirector);
}this.firePropertyChange$S$O$O("selectionModel", oldValue, this.selectionModel);
});

Clazz.newMeth(C$, 'getSelectionModel', function () {
return this.selectionModel;
});

Clazz.newMeth(C$, 'getPathBetweenRows$I$I', function (index0, index1) {
var newMinIndex;
var newMaxIndex;
var tree = this.getUI();
newMinIndex = Math.min(index0, index1);
newMaxIndex = Math.max(index0, index1);
if (tree != null ) {
var selection = Clazz.array((I$[10]||$incl$(10)), [newMaxIndex - newMinIndex + 1]);
for (var counter = newMinIndex; counter <= newMaxIndex; counter++) {
selection[counter - newMinIndex] = tree.getPathForRow$javax_swing_JTree$I(this, counter);
}
return selection;
}return null;
});

Clazz.newMeth(C$, 'setSelectionInterval$I$I', function (index0, index1) {
var paths = this.getPathBetweenRows$I$I(index0, index1);
this.getSelectionModel().setSelectionPaths$javax_swing_tree_TreePathA(paths);
});

Clazz.newMeth(C$, 'addSelectionInterval$I$I', function (index0, index1) {
var paths = this.getPathBetweenRows$I$I(index0, index1);
this.getSelectionModel().addSelectionPaths$javax_swing_tree_TreePathA(paths);
});

Clazz.newMeth(C$, 'removeSelectionInterval$I$I', function (index0, index1) {
var paths = this.getPathBetweenRows$I$I(index0, index1);
this.getSelectionModel().removeSelectionPaths$javax_swing_tree_TreePathA(paths);
});

Clazz.newMeth(C$, 'removeSelectionPath$javax_swing_tree_TreePath', function (path) {
this.getSelectionModel().removeSelectionPath$javax_swing_tree_TreePath(path);
});

Clazz.newMeth(C$, 'removeSelectionPaths$javax_swing_tree_TreePathA', function (paths) {
this.getSelectionModel().removeSelectionPaths$javax_swing_tree_TreePathA(paths);
});

Clazz.newMeth(C$, 'removeSelectionRow$I', function (row) {
var rows = Clazz.array(Integer.TYPE, -1, [row]);
this.removeSelectionRows$IA(rows);
});

Clazz.newMeth(C$, 'removeSelectionRows$IA', function (rows) {
var ui = this.getUI();
if (ui != null  && rows != null  ) {
var numRows = rows.length;
var paths = Clazz.array((I$[10]||$incl$(10)), [numRows]);
for (var counter = 0; counter < numRows; counter++) paths[counter] = ui.getPathForRow$javax_swing_JTree$I(this, rows[counter]);

this.removeSelectionPaths$javax_swing_tree_TreePathA(paths);
}});

Clazz.newMeth(C$, 'clearSelection', function () {
this.getSelectionModel().clearSelection();
});

Clazz.newMeth(C$, 'isSelectionEmpty', function () {
return this.getSelectionModel().isSelectionEmpty();
});

Clazz.newMeth(C$, 'addTreeExpansionListener$javax_swing_event_TreeExpansionListener', function (tel) {
if (this.settingUI) {
this.uiTreeExpansionListener = tel;
}this.listenerList.add$Class$TT(Clazz.getClass((I$[14]||$incl$(14)),['treeCollapsed$javax_swing_event_TreeExpansionEvent','treeExpanded$javax_swing_event_TreeExpansionEvent']), tel);
});

Clazz.newMeth(C$, 'removeTreeExpansionListener$javax_swing_event_TreeExpansionListener', function (tel) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[14]||$incl$(14)),['treeCollapsed$javax_swing_event_TreeExpansionEvent','treeExpanded$javax_swing_event_TreeExpansionEvent']), tel);
if (this.uiTreeExpansionListener === tel ) {
this.uiTreeExpansionListener = null;
}});

Clazz.newMeth(C$, 'getTreeExpansionListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[14]||$incl$(14)),['treeCollapsed$javax_swing_event_TreeExpansionEvent','treeExpanded$javax_swing_event_TreeExpansionEvent']));
});

Clazz.newMeth(C$, 'addTreeWillExpandListener$javax_swing_event_TreeWillExpandListener', function (tel) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[15]||$incl$(15)),['treeWillCollapse$javax_swing_event_TreeExpansionEvent','treeWillExpand$javax_swing_event_TreeExpansionEvent']), tel);
});

Clazz.newMeth(C$, 'removeTreeWillExpandListener$javax_swing_event_TreeWillExpandListener', function (tel) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[15]||$incl$(15)),['treeWillCollapse$javax_swing_event_TreeExpansionEvent','treeWillExpand$javax_swing_event_TreeExpansionEvent']), tel);
});

Clazz.newMeth(C$, 'getTreeWillExpandListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[15]||$incl$(15)),['treeWillCollapse$javax_swing_event_TreeExpansionEvent','treeWillExpand$javax_swing_event_TreeExpansionEvent']));
});

Clazz.newMeth(C$, 'fireTreeExpanded$javax_swing_tree_TreePath', function (path) {
var listeners = this.listenerList.getListenerList();
var e = null;
if (this.uiTreeExpansionListener != null ) {
e = Clazz.new_((I$[16]||$incl$(16)).c$$O$javax_swing_tree_TreePath,[this, path]);
this.uiTreeExpansionListener.treeExpanded$javax_swing_event_TreeExpansionEvent(e);
}for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[14]||$incl$(14)),['treeCollapsed$javax_swing_event_TreeExpansionEvent','treeExpanded$javax_swing_event_TreeExpansionEvent'])  && listeners[i + 1] !== this.uiTreeExpansionListener  ) {
if (e == null ) e = Clazz.new_((I$[16]||$incl$(16)).c$$O$javax_swing_tree_TreePath,[this, path]);
(listeners[i + 1]).treeExpanded$javax_swing_event_TreeExpansionEvent(e);
}}
});

Clazz.newMeth(C$, 'fireTreeCollapsed$javax_swing_tree_TreePath', function (path) {
var listeners = this.listenerList.getListenerList();
var e = null;
if (this.uiTreeExpansionListener != null ) {
e = Clazz.new_((I$[16]||$incl$(16)).c$$O$javax_swing_tree_TreePath,[this, path]);
this.uiTreeExpansionListener.treeCollapsed$javax_swing_event_TreeExpansionEvent(e);
}for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[14]||$incl$(14)),['treeCollapsed$javax_swing_event_TreeExpansionEvent','treeExpanded$javax_swing_event_TreeExpansionEvent'])  && listeners[i + 1] !== this.uiTreeExpansionListener  ) {
if (e == null ) e = Clazz.new_((I$[16]||$incl$(16)).c$$O$javax_swing_tree_TreePath,[this, path]);
(listeners[i + 1]).treeCollapsed$javax_swing_event_TreeExpansionEvent(e);
}}
});

Clazz.newMeth(C$, 'fireTreeWillExpand$javax_swing_tree_TreePath', function (path) {
var listeners = this.listenerList.getListenerList();
var e = null;
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[15]||$incl$(15)),['treeWillCollapse$javax_swing_event_TreeExpansionEvent','treeWillExpand$javax_swing_event_TreeExpansionEvent']) ) {
if (e == null ) e = Clazz.new_((I$[16]||$incl$(16)).c$$O$javax_swing_tree_TreePath,[this, path]);
(listeners[i + 1]).treeWillExpand$javax_swing_event_TreeExpansionEvent(e);
}}
});

Clazz.newMeth(C$, 'fireTreeWillCollapse$javax_swing_tree_TreePath', function (path) {
var listeners = this.listenerList.getListenerList();
var e = null;
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[15]||$incl$(15)),['treeWillCollapse$javax_swing_event_TreeExpansionEvent','treeWillExpand$javax_swing_event_TreeExpansionEvent']) ) {
if (e == null ) e = Clazz.new_((I$[16]||$incl$(16)).c$$O$javax_swing_tree_TreePath,[this, path]);
(listeners[i + 1]).treeWillCollapse$javax_swing_event_TreeExpansionEvent(e);
}}
});

Clazz.newMeth(C$, 'addTreeSelectionListener$javax_swing_event_TreeSelectionListener', function (tsl) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[17]||$incl$(17)),['valueChanged$javax_swing_event_TreeSelectionEvent']), tsl);
if (this.listenerList.getListenerCount$Class(Clazz.getClass((I$[17]||$incl$(17)),['valueChanged$javax_swing_event_TreeSelectionEvent'])) != 0 && this.selectionRedirector == null  ) {
this.selectionRedirector = Clazz.new_((I$[18]||$incl$(18)), [this, null]);
this.selectionModel.addTreeSelectionListener$javax_swing_event_TreeSelectionListener(this.selectionRedirector);
}});

Clazz.newMeth(C$, 'removeTreeSelectionListener$javax_swing_event_TreeSelectionListener', function (tsl) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[17]||$incl$(17)),['valueChanged$javax_swing_event_TreeSelectionEvent']), tsl);
if (this.listenerList.getListenerCount$Class(Clazz.getClass((I$[17]||$incl$(17)),['valueChanged$javax_swing_event_TreeSelectionEvent'])) == 0 && this.selectionRedirector != null  ) {
this.selectionModel.removeTreeSelectionListener$javax_swing_event_TreeSelectionListener(this.selectionRedirector);
this.selectionRedirector = null;
}});

Clazz.newMeth(C$, 'getTreeSelectionListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[17]||$incl$(17)),['valueChanged$javax_swing_event_TreeSelectionEvent']));
});

Clazz.newMeth(C$, 'fireValueChanged$javax_swing_event_TreeSelectionEvent', function (e) {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[17]||$incl$(17)),['valueChanged$javax_swing_event_TreeSelectionEvent']) ) {
(listeners[i + 1]).valueChanged$javax_swing_event_TreeSelectionEvent(e);
}}
});

Clazz.newMeth(C$, 'treeDidChange', function () {
this.revalidate();
this.repaint();
});

Clazz.newMeth(C$, 'setVisibleRowCount$I', function (newCount) {
var oldCount = this.visibleRowCount;
this.visibleRowCount = newCount;
this.firePropertyChange$S$I$I("visibleRowCount", oldCount, this.visibleRowCount);
this.invalidate();
});

Clazz.newMeth(C$, 'getVisibleRowCount', function () {
return this.visibleRowCount;
});

Clazz.newMeth(C$, 'expandRoot', function () {
var model = this.getModel();
if (model != null  && model.getRoot() != null  ) {
this.expandPath$javax_swing_tree_TreePath(Clazz.new_((I$[10]||$incl$(10)).c$$O,[model.getRoot()]));
}});

Clazz.newMeth(C$, 'getNextMatch$S$I$javax_swing_text_Position_Bias', function (prefix, startingRow, bias) {
var max = this.getRowCount();
if (prefix == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}if (startingRow < 0 || startingRow >= max ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}prefix = prefix.toUpperCase();
var increment = (bias === (I$[19]||$incl$(19)).Forward ) ? 1 : -1;
var row = startingRow;
do {
var path = this.getPathForRow$I(row);
var text = this.convertValueToText$O$Z$Z$Z$I$Z(path.getLastPathComponent(), this.isRowSelected$I(row), this.isExpanded$I(row), true, row, false);
if (text.toUpperCase().startsWith$S(prefix)) {
return path;
}row = (row + increment + max ) % max;
} while (row != startingRow);
return null;
});

Clazz.newMeth(C$, 'getPreferredScrollableViewportSize', function () {
var width = this.getPreferredSize().width;
var visRows = this.getVisibleRowCount();
var height = -1;
if (this.isFixedRowHeight()) height = visRows * this.getRowHeight();
 else {
var ui = this.getUI();
if (ui != null  && visRows > 0 ) {
var rc = ui.getRowCount$javax_swing_JTree(this);
if (rc >= visRows) {
var bounds = this.getRowBounds$I(visRows - 1);
if (bounds != null ) {
height = bounds.y + bounds.height;
}} else if (rc > 0) {
var bounds = this.getRowBounds$I(0);
if (bounds != null ) {
height = bounds.height * visRows;
}}}if (height == -1) {
height = 16 * visRows;
}}return Clazz.new_((I$[20]||$incl$(20)).c$$I$I,[width, height]);
});

Clazz.newMeth(C$, 'getScrollableUnitIncrement$java_awt_Rectangle$I$I', function (visibleRect, orientation, direction) {
if (orientation == 1) {
var rowBounds;
var firstIndex = this.getClosestRowForLocation$I$I(0, visibleRect.y);
if (firstIndex != -1) {
rowBounds = this.getRowBounds$I(firstIndex);
if (rowBounds.y != visibleRect.y) {
if (direction < 0) {
return Math.max(0, (visibleRect.y - rowBounds.y));
}return (rowBounds.y + rowBounds.height - visibleRect.y);
}if (direction < 0) {
if (firstIndex != 0) {
rowBounds = this.getRowBounds$I(firstIndex - 1);
return rowBounds.height;
}} else {
return rowBounds.height;
}}return 0;
}return 4;
});

Clazz.newMeth(C$, 'getScrollableBlockIncrement$java_awt_Rectangle$I$I', function (visibleRect, orientation, direction) {
return (orientation == 1) ? visibleRect.height : visibleRect.width;
});

Clazz.newMeth(C$, 'getScrollableTracksViewportWidth', function () {
if (Clazz.instanceOf(this.getParent(), "javax.swing.JViewport")) {
return ((this.getParent()).getWidth() > this.getPreferredSize().width);
}return false;
});

Clazz.newMeth(C$, 'getScrollableTracksViewportHeight', function () {
if (Clazz.instanceOf(this.getParent(), "javax.swing.JViewport")) {
return ((this.getParent()).getHeight() > this.getPreferredSize().height);
}return false;
});

Clazz.newMeth(C$, 'setExpandedState$javax_swing_tree_TreePath$Z', function (path, state) {
if (path != null ) {
var stack;
var parentPath = path.getParentPath();
if (this.expandedStack.size() == 0) {
stack = Clazz.new_((I$[6]||$incl$(6)));
} else {
stack = this.expandedStack.pop();
}try {
while (parentPath != null ){
if (this.isExpanded$javax_swing_tree_TreePath(parentPath)) {
parentPath = null;
} else {
stack.push$TE(parentPath);
parentPath = parentPath.getParentPath();
}}
for (var counter = stack.size() - 1; counter >= 0; counter--) {
parentPath = stack.pop();
if (!this.isExpanded$javax_swing_tree_TreePath(parentPath)) {
try {
this.fireTreeWillExpand$javax_swing_tree_TreePath(parentPath);
} catch (eve) {
if (Clazz.exceptionOf(eve, "javax.swing.tree.ExpandVetoException")){
return;
} else {
throw eve;
}
}
this.expandedState.put$TK$TV(parentPath, (I$[1]||$incl$(1)).TRUE);
this.fireTreeExpanded$javax_swing_tree_TreePath(parentPath);
}}
} finally {
if (this.expandedStack.size() < C$.TEMP_STACK_SIZE) {
stack.removeAllElements();
this.expandedStack.push$TE(stack);
}}
if (!state) {
var cValue = this.expandedState.get$O(path);
if (cValue != null  && (cValue).booleanValue() ) {
try {
this.fireTreeWillCollapse$javax_swing_tree_TreePath(path);
} catch (eve) {
if (Clazz.exceptionOf(eve, "javax.swing.tree.ExpandVetoException")){
return;
} else {
throw eve;
}
}
this.expandedState.put$TK$TV(path, (I$[1]||$incl$(1)).FALSE);
this.fireTreeCollapsed$javax_swing_tree_TreePath(path);
if (this.removeDescendantSelectedPaths$javax_swing_tree_TreePath$Z(path, false) && !this.isPathSelected$javax_swing_tree_TreePath(path) ) {
this.addSelectionPath$javax_swing_tree_TreePath(path);
}}} else {
var cValue = this.expandedState.get$O(path);
if (cValue == null  || !(cValue).booleanValue() ) {
try {
this.fireTreeWillExpand$javax_swing_tree_TreePath(path);
} catch (eve) {
if (Clazz.exceptionOf(eve, "javax.swing.tree.ExpandVetoException")){
return;
} else {
throw eve;
}
}
this.expandedState.put$TK$TV(path, (I$[1]||$incl$(1)).TRUE);
this.fireTreeExpanded$javax_swing_tree_TreePath(path);
}}}});

Clazz.newMeth(C$, 'getDescendantToggledPaths$javax_swing_tree_TreePath', function (parent) {
if (parent == null ) return null;
var descendants = Clazz.new_((I$[2]||$incl$(2)));
var nodes = this.expandedState.keys();
var path;
while (nodes.hasMoreElements()){
path = nodes.nextElement();
if (parent.isDescendant$javax_swing_tree_TreePath(path)) descendants.addElement$TE(path);
}
return descendants.elements();
});

Clazz.newMeth(C$, 'removeDescendantToggledPaths$java_util_Enumeration', function (toRemove) {
if (toRemove != null ) {
while (toRemove.hasMoreElements()){
var descendants = this.getDescendantToggledPaths$javax_swing_tree_TreePath(toRemove.nextElement());
if (descendants != null ) {
while (descendants.hasMoreElements()){
this.expandedState.remove$O(descendants.nextElement());
}
}}
}});

Clazz.newMeth(C$, 'clearToggledPaths', function () {
this.expandedState.clear();
});

Clazz.newMeth(C$, 'createTreeModelListener', function () {
return Clazz.new_((I$[21]||$incl$(21)), [this, null]);
});

Clazz.newMeth(C$, 'removeDescendantSelectedPaths$javax_swing_tree_TreePath$Z', function (path, includePath) {
var toRemove = p$.getDescendantSelectedPaths$javax_swing_tree_TreePath$Z.apply(this, [path, includePath]);
if (toRemove != null ) {
this.getSelectionModel().removeSelectionPaths$javax_swing_tree_TreePathA(toRemove);
return true;
}return false;
});

Clazz.newMeth(C$, 'getDescendantSelectedPaths$javax_swing_tree_TreePath$Z', function (path, includePath) {
var sm = this.getSelectionModel();
var selPaths = (sm != null ) ? sm.getSelectionPaths() : null;
if (selPaths != null ) {
var shouldRemove = false;
for (var counter = selPaths.length - 1; counter >= 0; counter--) {
if (selPaths[counter] != null  && path.isDescendant$javax_swing_tree_TreePath(selPaths[counter])  && (!path.equals$O(selPaths[counter]) || includePath ) ) shouldRemove = true;
 else selPaths[counter] = null;
}
if (!shouldRemove) {
selPaths = null;
}return selPaths;
}return null;
});

Clazz.newMeth(C$, 'removeDescendantSelectedPaths$javax_swing_event_TreeModelEvent', function (e) {
var pPath = e.getTreePath();
var oldChildren = e.getChildren();
var sm = this.getSelectionModel();
if (sm != null  && pPath != null   && oldChildren != null   && oldChildren.length > 0 ) {
for (var counter = oldChildren.length - 1; counter >= 0; counter--) {
this.removeDescendantSelectedPaths$javax_swing_tree_TreePath$Z(pPath.pathByAddingChild$O(oldChildren[counter]), true);
}
}});

Clazz.newMeth(C$, 'setUIProperty$S$O', function (propertyName, value) {
if (propertyName == "rowHeight") {
if (!this.rowHeightSet) {
this.setRowHeight$I((value).intValue());
this.rowHeightSet = false;
}} else if (propertyName == "scrollsOnExpand") {
if (!this.scrollsOnExpandSet) {
this.setScrollsOnExpand$Z((value).booleanValue());
this.scrollsOnExpandSet = false;
}} else if (propertyName == "showsRootHandles") {
if (!this.showsRootHandlesSet) {
this.setShowsRootHandles$Z((value).booleanValue());
this.showsRootHandlesSet = false;
}} else {
C$.superclazz.prototype.setUIProperty$S$O.apply(this, [propertyName, value]);
}});

Clazz.newMeth(C$, 'paramString', function () {
var rootVisibleString = (this.rootVisible ? "true" : "false");
var showsRootHandlesString = (this.showsRootHandles ? "true" : "false");
var editableString = (this.editable ? "true" : "false");
var largeModelString = (this.largeModel ? "true" : "false");
var invokesStopCellEditingString = (this.invokesStopCellEditing ? "true" : "false");
var scrollsOnExpandString = (this.scrollsOnExpand ? "true" : "false");
return C$.superclazz.prototype.paramString.apply(this, []) + ",editable=" + editableString + ",invokesStopCellEditing=" + invokesStopCellEditingString + ",largeModel=" + largeModelString + ",rootVisible=" + rootVisibleString + ",rowHeight=" + this.rowHeight + ",scrollsOnExpand=" + scrollsOnExpandString + ",showsRootHandles=" + showsRootHandlesString + ",toggleClickCount=" + this.toggleClickCount + ",visibleRowCount=" + this.visibleRowCount ;
});
;
(function(){var C$=Clazz.newClass(P$.JTree, "EmptySelectionModel", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.tree.DefaultTreeSelectionModel');
C$.$sharedInstance = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.$sharedInstance = Clazz.new_(C$);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'sharedInstance', function () {
return C$.$sharedInstance;
}, 1);

Clazz.newMeth(C$, 'setSelectionPaths$javax_swing_tree_TreePathA', function (pPaths) {
});

Clazz.newMeth(C$, 'addSelectionPaths$javax_swing_tree_TreePathA', function (paths) {
});

Clazz.newMeth(C$, 'removeSelectionPaths$javax_swing_tree_TreePathA', function (paths) {
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JTree, "TreeSelectionRedirector", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'javax.swing.event.TreeSelectionListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'valueChanged$javax_swing_event_TreeSelectionEvent', function (e) {
var newE;
newE = e.cloneWithSource$O(this.this$0);
this.this$0.fireValueChanged$javax_swing_event_TreeSelectionEvent(newE);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JTree, "TreeModelHandler", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'javax.swing.event.TreeModelListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'treeNodesChanged$javax_swing_event_TreeModelEvent', function (e) {
});

Clazz.newMeth(C$, 'treeNodesInserted$javax_swing_event_TreeModelEvent', function (e) {
});

Clazz.newMeth(C$, 'treeStructureChanged$javax_swing_event_TreeModelEvent', function (e) {
if (e == null ) return;
var parent = e.getTreePath();
if (parent == null ) return;
if (parent.getPathCount() == 1) {
this.this$0.clearToggledPaths();
if (this.this$0.treeModel.getRoot() != null  && !this.this$0.treeModel.isLeaf$O(this.this$0.treeModel.getRoot()) ) {
this.this$0.expandedState.put$TK$TV(parent, (I$[1]||$incl$(1)).TRUE);
}} else if (this.this$0.expandedState.get$O(parent) != null ) {
var toRemove = Clazz.new_((I$[2]||$incl$(2)).c$$I,[1]);
var isExpanded = this.this$0.isExpanded$javax_swing_tree_TreePath(parent);
toRemove.addElement$TE(parent);
this.this$0.removeDescendantToggledPaths$java_util_Enumeration(toRemove.elements());
if (isExpanded) {
var model = this.this$0.getModel();
if (model == null  || model.isLeaf$O(parent.getLastPathComponent()) ) this.this$0.collapsePath$javax_swing_tree_TreePath(parent);
 else this.this$0.expandedState.put$TK$TV(parent, (I$[1]||$incl$(1)).TRUE);
}}this.this$0.removeDescendantSelectedPaths$javax_swing_tree_TreePath$Z(parent, false);
});

Clazz.newMeth(C$, 'treeNodesRemoved$javax_swing_event_TreeModelEvent', function (e) {
if (e == null ) return;
var parent = e.getTreePath();
var children = e.getChildren();
if (children == null ) return;
var rPath;
var toRemove = Clazz.new_((I$[2]||$incl$(2)).c$$I,[Math.max(1, children.length)]);
for (var counter = children.length - 1; counter >= 0; counter--) {
rPath = parent.pathByAddingChild$O(children[counter]);
if (this.this$0.expandedState.get$O(rPath) != null ) toRemove.addElement$TE(rPath);
}
if (toRemove.size() > 0) this.this$0.removeDescendantToggledPaths$java_util_Enumeration(toRemove.elements());
var model = this.this$0.getModel();
if (model == null  || model.isLeaf$O(parent.getLastPathComponent()) ) this.this$0.expandedState.remove$O(parent);
this.this$0.removeDescendantSelectedPaths$javax_swing_event_TreeModelEvent(e);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JTree, "DynamicUtilTreeNode", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.tree.DefaultMutableTreeNode');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.hasChildren = false;
this.childValue = null;
this.loadedChildren = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'createChildren$javax_swing_tree_DefaultMutableTreeNode$O', function (parent, children) {
if (Clazz.instanceOf(children, "java.util.Vector")) {
var childVector = children;
for (var counter = 0, maxCounter = childVector.size(); counter < maxCounter; counter++) parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_(C$.c$$O$O,[childVector.elementAt$I(counter), childVector.elementAt$I(counter)]));

} else if (Clazz.instanceOf(children, "java.util.Hashtable")) {
var childHT = children;
var keys = childHT.keys();
var aKey;
while (keys.hasMoreElements()){
aKey = keys.nextElement();
parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_(C$.c$$O$O,[aKey, childHT.get$O(aKey)]));
}
} else if (Clazz.instanceOf(children, Clazz.array(java.lang.Object, -1))) {
var childArray = children;
for (var counter = 0, maxCounter = childArray.length; counter < maxCounter; counter++) parent.add$javax_swing_tree_MutableTreeNode(Clazz.new_(C$.c$$O$O,[childArray[counter], childArray[counter]]));

}}, 1);

Clazz.newMeth(C$, 'c$$O$O', function (value, children) {
C$.superclazz.c$$O.apply(this, [value]);
C$.$init$.apply(this);
this.loadedChildren = false;
this.childValue = children;
if (children != null ) {
if (Clazz.instanceOf(children, "java.util.Vector")) this.setAllowsChildren$Z(true);
 else if (Clazz.instanceOf(children, "java.util.Hashtable")) this.setAllowsChildren$Z(true);
 else if (Clazz.instanceOf(children, Clazz.array(java.lang.Object, -1))) this.setAllowsChildren$Z(true);
 else this.setAllowsChildren$Z(false);
} else this.setAllowsChildren$Z(false);
}, 1);

Clazz.newMeth(C$, 'isLeaf', function () {
return !this.getAllowsChildren();
});

Clazz.newMeth(C$, 'getChildCount', function () {
if (!this.loadedChildren) this.loadChildren();
return C$.superclazz.prototype.getChildCount.apply(this, []);
});

Clazz.newMeth(C$, 'loadChildren', function () {
this.loadedChildren = true;
C$.createChildren$javax_swing_tree_DefaultMutableTreeNode$O(this, this.childValue);
});

Clazz.newMeth(C$, 'getChildAt$I', function (index) {
if (!this.loadedChildren) this.loadChildren();
return C$.superclazz.prototype.getChildAt$I.apply(this, [index]);
});

Clazz.newMeth(C$, 'children', function () {
if (!this.loadedChildren) this.loadChildren();
return C$.superclazz.prototype.children.apply(this, []);
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-15 01:02:36
