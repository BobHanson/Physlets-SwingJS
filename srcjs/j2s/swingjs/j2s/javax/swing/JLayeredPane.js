(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.awt.Component','java.awt.Color','java.util.Hashtable','java.util.ArrayList']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JLayeredPane", null, 'javax.swing.JComponent');
C$.DEFAULT_LAYER = null;
C$.PALETTE_LAYER = null;
C$.MODAL_LAYER = null;
C$.POPUP_LAYER = null;
C$.DRAG_LAYER = null;
C$.FRAME_CONTENT_LAYER = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.DEFAULT_LAYER =  new Integer(0);
C$.PALETTE_LAYER =  new Integer(100);
C$.MODAL_LAYER =  new Integer(200);
C$.POPUP_LAYER =  new Integer(300);
C$.DRAG_LAYER =  new Integer(400);
C$.FRAME_CONTENT_LAYER =  new Integer(-30000);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.componentToLayer = null;
this.optimizedDrawingPossible = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.optimizedDrawingPossible = true;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.setLayout$java_awt_LayoutManager(null);
this.uiClassID = "LayeredPaneUI";
this.updateUI();
}, 1);

Clazz.newMeth(C$, 'validateOptimizedDrawing', function () {
var layeredComponentFound = false;
{
var layer = null;
for (var c, $c = 0, $$c = this.getComponents(); $c<$$c.length&&((c=$$c[$c]),1);$c++) {
layer = null;
if ((Clazz.instanceOf(c, "javax.swing.JComponent") && (layer = (c).getClientProperty$O("layeredContainerLayer")) != null  )) {
if (layer != null  && layer.equals(C$.FRAME_CONTENT_LAYER) ) continue;
layeredComponentFound = true;
break;
}}
}if (layeredComponentFound) this.optimizedDrawingPossible = false;
 else this.optimizedDrawingPossible = true;
});

Clazz.newMeth(C$, 'addImpl$java_awt_Component$O$I', function (comp, constraints, index) {
var layer = C$.DEFAULT_LAYER.intValue();
var pos;
if (Clazz.instanceOf(constraints, "java.lang.Integer")) {
layer = (constraints).intValue();
this.setLayer$java_awt_Component$I(comp, layer);
} else layer = this.getLayer$java_awt_Component(comp);
pos = this.insertIndexForLayer$I$I(layer, index);
this.addImplCont$java_awt_Component$O$I(comp, constraints, pos);
comp.validate();
comp.repaint();
p$.validateOptimizedDrawing.apply(this, []);
return comp;
});

Clazz.newMeth(C$, 'remove$I', function (index) {
var c = this.getComponent$I(index);
C$.superclazz.prototype.remove$I.apply(this, [index]);
if (c != null  && !(Clazz.instanceOf(c, "javax.swing.JComponent")) ) {
this.getComponentToLayer().remove$O(c);
}p$.validateOptimizedDrawing.apply(this, []);
});

Clazz.newMeth(C$, 'removeAll', function () {
var children = this.getComponents();
var cToL = this.getComponentToLayer();
for (var counter = children.length - 1; counter >= 0; counter--) {
var c = children[counter];
if (c != null  && !(Clazz.instanceOf(c, "javax.swing.JComponent")) ) {
cToL.remove$O(c);
}}
C$.superclazz.prototype.removeAll.apply(this, []);
});

Clazz.newMeth(C$, 'isOptimizedDrawingEnabled', function () {
return this.optimizedDrawingPossible;
});

Clazz.newMeth(C$, 'putLayer$javax_swing_JComponent$I', function (c, layer) {
var layerObj;
layerObj =  new Integer(layer);
c.putClientProperty$O$O("layeredContainerLayer", layerObj);
}, 1);

Clazz.newMeth(C$, 'getLayer$javax_swing_JComponent', function (c) {
var i;
if ((i = c.getClientProperty$O("layeredContainerLayer")) != null ) return i.intValue();
return C$.DEFAULT_LAYER.intValue();
}, 1);

Clazz.newMeth(C$, 'getLayeredPaneAbove$java_awt_Component', function (c) {
if (c == null ) return null;
var parent = c.getParent();
while (parent != null  && !(Clazz.instanceOf(parent, "javax.swing.JLayeredPane")) )parent = parent.getParent();

return parent;
}, 1);

Clazz.newMeth(C$, 'setLayer$java_awt_Component$I', function (c, layer) {
this.setLayer$java_awt_Component$I$I(c, layer, -1);
});

Clazz.newMeth(C$, 'setLayer$java_awt_Component$I$I', function (c, layer, position) {
var layerObj;
layerObj = this.getObjectForLayer$I(layer);
if (layer == this.getLayer$java_awt_Component(c) && position == this.getPosition$java_awt_Component(c) ) {
this.repaint$java_awt_Rectangle(c.getBounds());
return;
}if (Clazz.instanceOf(c, "javax.swing.JComponent")) (c).putClientProperty$O$O("layeredContainerLayer", layerObj);
 else this.getComponentToLayer().put$TK$TV(c, layerObj);
if (c.getParent() == null  || c.getParent() !== this  ) {
this.repaint$java_awt_Rectangle(c.getBounds());
return;
}var index = p$.insertIndexForLayer$java_awt_Component$I$I.apply(this, [c, layer, position]);
this.setComponentZOrder$java_awt_Component$I(c, index);
this.repaint$java_awt_Rectangle(c.getBounds());
});

Clazz.newMeth(C$, 'getLayer$java_awt_Component', function (c) {
var i;
if (Clazz.instanceOf(c, "javax.swing.JComponent")) i = (c).getClientProperty$O("layeredContainerLayer");
 else i = this.getComponentToLayer().get$O(c);
if (i == null ) return C$.DEFAULT_LAYER.intValue();
return i.intValue();
});

Clazz.newMeth(C$, 'getIndexOf$java_awt_Component', function (c) {
var i;
var count;
count = this.getComponentCount();
for (i = 0; i < count; i++) {
if (c === this.getComponent$I(i) ) return i;
}
return -1;
});

Clazz.newMeth(C$, 'moveToFront$java_awt_Component', function (c) {
this.setPosition$java_awt_Component$I(c, 0);
});

Clazz.newMeth(C$, 'moveToBack$java_awt_Component', function (c) {
this.setPosition$java_awt_Component$I(c, -1);
});

Clazz.newMeth(C$, 'setPosition$java_awt_Component$I', function (c, position) {
this.setLayer$java_awt_Component$I$I(c, this.getLayer$java_awt_Component(c), position);
});

Clazz.newMeth(C$, 'getPosition$java_awt_Component', function (c) {
var i;
var count;
var startLayer;
var curLayer;
var startLocation;
var pos = 0;
count = this.getComponentCount();
startLocation = this.getIndexOf$java_awt_Component(c);
if (startLocation == -1) return -1;
startLayer = this.getLayer$java_awt_Component(c);
for (i = startLocation - 1; i >= 0; i--) {
curLayer = this.getLayer$java_awt_Component(this.getComponent$I(i));
if (curLayer == startLayer) pos++;
 else return pos;
}
return pos;
});

Clazz.newMeth(C$, 'highestLayer', function () {
if (this.getComponentCount() > 0) return this.getLayer$java_awt_Component(this.getComponent$I(0));
return 0;
});

Clazz.newMeth(C$, 'lowestLayer', function () {
var count = this.getComponentCount();
if (count > 0) return this.getLayer$java_awt_Component(this.getComponent$I(count - 1));
return 0;
});

Clazz.newMeth(C$, 'getComponentCountInLayer$I', function (layer) {
var i;
var count;
var curLayer;
var layerCount = 0;
count = this.getComponentCount();
for (i = 0; i < count; i++) {
curLayer = this.getLayer$java_awt_Component(this.getComponent$I(i));
if (curLayer == layer) {
layerCount++;
} else if (layerCount > 0 || curLayer < layer ) {
break;
}}
return layerCount;
});

Clazz.newMeth(C$, 'getComponentsInLayer$I', function (layer) {
var i;
var count;
var curLayer;
var layerCount = 0;
var results;
results = Clazz.array((I$[1]||$incl$(1)), [this.getComponentCountInLayer$I(layer)]);
count = this.getComponentCount();
for (i = 0; i < count; i++) {
curLayer = this.getLayer$java_awt_Component(this.getComponent$I(i));
if (curLayer == layer) {
results[layerCount++] = this.getComponent$I(i);
} else if (layerCount > 0 || curLayer < layer ) {
break;
}}
return results;
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
if (this.isOpaque()) {
var r = g.getClipBounds();
var c = this.getBackground();
if (c == null ) c = (I$[2]||$incl$(2)).lightGray;
g.setColor$java_awt_Color(c);
if (r != null ) {
g.fillRect$I$I$I$I(r.x, r.y, r.width, r.height);
} else {
g.fillRect$I$I$I$I(0, 0, this.getWidth(), this.getHeight());
}}C$.superclazz.prototype.paint$java_awt_Graphics.apply(this, [g]);
});

Clazz.newMeth(C$, 'getComponentToLayer', function () {
if (this.componentToLayer == null ) this.componentToLayer = Clazz.new_((I$[3]||$incl$(3)).c$$I,[4]);
return this.componentToLayer;
});

Clazz.newMeth(C$, 'getObjectForLayer$I', function (layer) {
var layerObj;
switch (layer) {
case 0:
layerObj = C$.DEFAULT_LAYER;
break;
case 100:
layerObj = C$.PALETTE_LAYER;
break;
case 200:
layerObj = C$.MODAL_LAYER;
break;
case 300:
layerObj = C$.POPUP_LAYER;
break;
case 400:
layerObj = C$.DRAG_LAYER;
break;
default:
layerObj =  new Integer(layer);
}
return layerObj;
});

Clazz.newMeth(C$, 'insertIndexForLayer$I$I', function (layer, position) {
return p$.insertIndexForLayer$java_awt_Component$I$I.apply(this, [null, layer, position]);
});

Clazz.newMeth(C$, 'insertIndexForLayer$java_awt_Component$I$I', function (comp, layer, position) {
var i;
var count;
var curLayer;
var layerStart = -1;
var layerEnd = -1;
var componentCount = this.getComponentCount();
var compList = Clazz.new_((I$[4]||$incl$(4)).c$$I,[componentCount]);
for (var index = 0; index < componentCount; index++) {
if (this.getComponent$I(index) !== comp ) {
compList.add$TE(this.getComponent$I(index));
}}
count = compList.size();
for (i = 0; i < count; i++) {
curLayer = this.getLayer$java_awt_Component(compList.get$I(i));
if (layerStart == -1 && curLayer == layer ) {
layerStart = i;
}if (curLayer < layer) {
if (i == 0) {
layerStart = 0;
layerEnd = 0;
} else {
layerEnd = i;
}break;
}}
if (layerStart == -1 && layerEnd == -1 ) return count;
if (layerStart != -1 && layerEnd == -1 ) layerEnd = count;
if (layerEnd != -1 && layerStart == -1 ) layerStart = layerEnd;
if (position == -1) return layerEnd;
if (position > -1 && layerStart + position <= layerEnd ) return layerStart + position;
return layerEnd;
});

Clazz.newMeth(C$, 'paramString', function () {
var optimizedDrawingPossibleString = (this.optimizedDrawingPossible ? "true" : "false");
return C$.superclazz.prototype.paramString.apply(this, []) + ",optimizedDrawingPossible=" + optimizedDrawingPossibleString ;
});
})();
//Created 2018-02-08 10:02:31
