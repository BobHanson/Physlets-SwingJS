(function(){var P$=Clazz.newPackage("swingjs.api.js"),I$=[['swingjs.JSUtil']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DOMNode");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'createElement', function (key, id) {
var node = null;
{
node = document.createElement(key);
node.__CLASS_NAME__ = "swingjs.api.DOMNode";
if(id)node.id = id;
}
return node;
}, 1);

Clazz.newMeth(C$, 'createTextNode', function (text) {
var node = null;
{
node = document.createTextNode(text);
}
return node;
}, 1);

Clazz.newMeth(C$, 'getParent', function (node) {
{
return node.parentNode;
}
}, 1);

Clazz.newMeth(C$, 'remove', function (node) {
if (node == null ) return null;
{
try { var p = node.parentElement;
p.removeChild(node);
$(body).remove(node);
} catch(e) { };
return p;
}
}, 1);

Clazz.newMeth(C$, 'getAttr', function (node, attr) {
{
if (node)return node[attr];
}
}, 1);

Clazz.newMeth(C$, 'getStyle', function (node, style) {
{
if (node)return node.style[style];
}
}, 1);

Clazz.newMeth(C$, 'getRectangle', function (node, r) {
{
r.x = parseInt(node.style.left.split("p")[0]);
r.y = parseInt(node.style.top.split("p")[0]);
r.width = parseInt(node.style.width.split("p")[0]);
r.height = parseInt(node.style.height.split("p")[0]);
}
}, 1);

Clazz.newMeth(C$, 'setAttr', function (node, attr, val) {

node[attr] = (val == "TRUE" ? true : val);
return node;
}, 1);

Clazz.newMeth(C$, 'setAttrs', function (node, attr) {
{
for (var i = 0; i < attr.length;) { var key = attr[i++];
var val = attr[i++];
key && (node[key] = val);
}
}
return node;
}, 1);

Clazz.newMeth(C$, 'setStyles', function (node, attr) {
if (node != null ) {
for (var i = 0; i < attr.length;) { node.style[attr[i++]] = attr[i++]; }
}
return node;
}, 1);

Clazz.newMeth(C$, 'setSize', function (node, width, height) {
return C$.setStyles(node, ["width", width + "px", "height", height + "px"]);
}, 1);

Clazz.newMeth(C$, 'setPositionAbsolute', function (node, top, left) {
if (top != -2147483648) {
C$.setStyles(node, ["top", top + "px"]);
C$.setStyles(node, ["left", left + "px"]);
}return C$.setStyles(node, ["position", "absolute"]);
}, 1);

Clazz.newMeth(C$, 'firstChild', function (node) {
{
return node.firstChild;
}
}, 1);

Clazz.newMeth(C$, 'lastChild', function (node) {
{
return node.lastChild;
}
}, 1);

Clazz.newMeth(C$, 'addJqueryHandledEvent', function (me, node, event) {
var f = null;
{
f = function(ev) {me.handleJSEvent$O$I$O(node, -1, ev)};
}
(I$[1]||$incl$(1)).getJQuery().$(node).on(event, f);
}, 1);

Clazz.newMeth(C$, 'setZ', function (node, z) {
return C$.setStyles(node, ["z-index", "" + z]);
}, 1);

Clazz.newMeth(C$, 'getAudioElement', function (filePath, isLoop) {
return C$.setAttrs(C$.createElement("audio", null), ["controls", "true", (isLoop ? "loop" : null), (isLoop ? "true" : null), "src", filePath]);
}, 1);

Clazz.newMeth(C$, 'setCursor', function (c) {
{
document.body.style.cursor = c;
}
}, 1);

Clazz.newMeth(C$, 'getImageNode', function (img) {
{
return (img._canvas || img._imgNode);
}
}, 1);

Clazz.newMeth(C$, 'addHorizontalGap', function (domNode, gap) {
var label = C$.setStyles(C$.createElement("label", null), ["letter-spacing", gap + "px", "font-size", "0pt"]);
label.appendChild(C$.createTextNode("."));
domNode.appendChild(label);
}, 1);

Clazz.newMeth(C$, 'removeAllChildren', function (node) {
{
while (node.hasChildNodes()) { node.removeChild(node.lastChild); }
}
}, 1);

Clazz.newMeth(C$, 'seTabIndex', function (node, i) {
{
node.tabIndex = i;
}
}, 1);

Clazz.newMeth(C$, 'setVisible', function (node, visible) {
C$.setStyles(node, ["display", visible ? "block" : "none"]);
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:33
