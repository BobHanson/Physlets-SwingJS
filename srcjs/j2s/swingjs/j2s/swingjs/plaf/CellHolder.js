(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "CellHolder", null, 'swingjs.plaf.JSLightweightUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getRowColumnID$swingjs_plaf_JSComponentUI$I$I', function (holder, row, col) {
return holder.id + "_tab" + (row >= 0 ? "row" + row : "") + "_col" + col ;
}, 1);

Clazz.newMeth(C$, 'createCellNode$swingjs_plaf_JSComponentUI$I$I', function (holder, row, col) {
var rcID = C$.getRowColumnID$swingjs_plaf_JSComponentUI$I$I(holder, row, col);
var td = (I$[1]||$incl$(1)).createElement("div", rcID);
holder.$$O(td).addClass("swing-td");
(I$[1]||$incl$(1)).setAttrs(td, ["data-table-ui", holder, "data-row", "" + row, "data-col", "" + col]);
return td;
}, 1);

Clazz.newMeth(C$, 'findCellNode$swingjs_plaf_JSComponentUI$I$I', function (holder, row, col) {
var rcID = C$.getRowColumnID$swingjs_plaf_JSComponentUI$I$I(holder, row, col);
return holder.$$O("#" + rcID).get(0);
}, 1);

Clazz.newMeth(C$, 'updateCellNode$swingjs_api_js_DOMNode$java_awt_JSComponent$I$I', function (td, renderer, width, height) {
var ui;
if (renderer != null  && !(ui = renderer.getUI()).isNull ) {
if (width > 0) renderer.setSize$I$I(width, height);
ui.outerNode = null;
ui.reInit();
ui.updateDOMNode();
(I$[1]||$incl$(1)).setAttr(ui.domNode, "data-source", renderer);
(I$[1]||$incl$(1)).setAttr(ui.domNode, "data-ui", null);
(I$[1]||$incl$(1)).setAttr(ui.domNode, "data-component", null);
(I$[1]||$incl$(1)).removeAllChildren(td);
td.appendChild(ui.domNode);
(I$[1]||$incl$(1)).setStyles(ui.domNode, ["width", "unset"]);
(I$[1]||$incl$(1)).setStyles(ui.domNode, ["height", "unset"]);
ui.domNode = ui.outerNode = null;
}}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:21
