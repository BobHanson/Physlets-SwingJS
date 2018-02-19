(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSCellHolderUI", null, 'swingjs.plaf.JSLightweightUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getRowColumnID$swingjs_plaf_JSComponentUI$I$I', function (holder, row, col) {
return holder.id + "_tab" + (row >= 0 ? "row" + row : "") + "_col" + col ;
}, 1);

Clazz.newMeth(C$, 'getCellNode$swingjs_plaf_JSComponentUI$I$I', function (holder, row, col) {
var rcID = C$.getRowColumnID$swingjs_plaf_JSComponentUI$I$I(holder, row, col);
var td = (I$[1]||$incl$(1)).createElement("div", rcID);
holder.$$swingjs_api_js_DOMNode(td).addClass("swing-td");
(I$[1]||$incl$(1)).setAttrs(td, ["data-table-ui", holder, "data-row", "" + row, "data-col", "" + col]);
return td;
}, 1);

Clazz.newMeth(C$, 'updateCellNode$swingjs_api_js_DOMNode$java_awt_JSComponent$Z', function (td, c, isHeader) {
var ui;
if (c != null  && !(ui = c.getUI()).isNull ) {
ui.domNode = ui.outerNode = null;
ui.updateDOMNode();
ui.newID();
td.appendChild(ui.domNode);
(I$[1]||$incl$(1)).setStyles(ui.domNode, ["width", "unset"]);
(I$[1]||$incl$(1)).setStyles(ui.domNode, ["height", "unset"]);
ui.domNode = ui.outerNode = null;
}}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-01-04 06:50:25
