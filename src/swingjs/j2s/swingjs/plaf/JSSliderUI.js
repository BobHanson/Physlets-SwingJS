(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.jquery.JQueryUI','swingjs.JSUtil','swingjs.api.js.DOMNode','java.awt.Dimension','sun.swing.DefaultLookup']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSSliderUI", null, 'swingjs.plaf.JSLightweightUI', ['java.beans.PropertyChangeListener', 'javax.swing.event.ChangeListener']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
{
var jqueryui = Clazz.getClass((I$[1]||$incl$(1)));
(I$[2]||$incl$(2)).loadStaticResource$S("swingjs/jquery/jquery-ui-j2sslider.css");
(I$[2]||$incl$(2)).loadStaticResource$S("swingjs/jquery/jquery-ui-j2sslider.js");
}
;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.jSlider = null;
this.min = 0;
this.max = 0;
this.val = 0;
this.majorSpacing = 0;
this.minorSpacing = 0;
this.paintTicks = false;
this.paintLabels = false;
this.snapToTicks = false;
this.labelTable = null;
this.orientation = null;
this.isScrollPaneScrollBar = false;
this.jqSlider = null;
this.z0 = 0;
this.model = null;
this.paintTrack = false;
this.isScrollBar = false;
this.jScrollBar = null;
this.sliderTrack = null;
this.sliderHandle = null;
this.disabled = 0;
this.myHeight = 0;
this.isHoriz = false;
this.isVerticalScrollBar = false;
this.isInverted = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.z0 = -2147483648;
this.paintTrack = true;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.needPreferred = true;
this.setDoc();
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
var js = this.jc;
this.min = js.getMinimum();
this.max = js.getMaximum();
this.val = js.getValue();
if (!this.isScrollBar) {
this.minorSpacing = js.getMinorTickSpacing();
this.majorSpacing = js.getMajorTickSpacing();
this.paintTicks = js.getPaintTicks();
this.paintLabels = js.getPaintLabels();
this.paintTrack = js.getPaintTrack();
this.snapToTicks = js.getSnapToTicks();
}this.orientation = (js.getOrientation() == 1 ? "vertical" : "horizontal");
this.model = js.getModel();
var isHoriz = (this.jSlider.getOrientation() == 0);
var isVerticalScrollBar = (this.isScrollBar && !isHoriz );
var isInverted = isVerticalScrollBar || !this.isScrollBar && this.jSlider.getInverted()  ;
var isChanged = false;
if (isHoriz != this.isHoriz  || isVerticalScrollBar != this.isVerticalScrollBar   || isInverted != this.isInverted  ) {
this.isHoriz = isHoriz;
this.isVerticalScrollBar = isVerticalScrollBar;
this.isInverted = isInverted;
isChanged = true;
}var isNew = (this.domNode == null );
if (isNew) {
this.domNode = this.wrap$S$S$swingjs_api_js_DOMNodeA("div", this.id + "_wrap", [this.jqSlider = (I$[3]||$incl$(3)).createElement("div", this.id)]);
this.$$O(this.domNode).addClass("swingjs");
p$.setJQuerySliderAndEvents.apply(this, []);
this.setTainted();
} else if (isChanged) {
(I$[3]||$incl$(3)).remove(this.jqSlider);
this.domNode.appendChild(this.jqSlider = (I$[3]||$incl$(3)).createElement("div", this.id));
p$.setJQuerySliderAndEvents.apply(this, []);
this.setTainted();
this.setInnerComponentBounds$I$I(this.jc.getWidth(), this.jc.getHeight());
}p$.setup$Z.apply(this, [isNew || isChanged ]);
this.setSlider();
if (this.jc.isOpaque()) this.setBackground$java_awt_Color(this.jc.getBackground());
return this.domNode;
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.jSlider = jc;
if (this.isScrollBar) this.jScrollBar = jc;
});

Clazz.newMeth(C$, 'setJQuerySliderAndEvents', function () {
var slider = this.$$O(this.jqSlider);
{
var me = this;
slider.j2sslider( { orientation: me.orientation, jslider: me.c, range: false, min: me.min, max: me.max, value: me.val, disabled: me.disabled, inverted: me.isInverted, change: function(jqevent, handle) { me.jqueryCallback$O$O(jqevent, handle); }, slide: function(jqevent, handle) { me.jqueryCallback$O$O(jqevent, handle); }, start: function(jqevent, handle) { me.jqueryStart$O$O(jqevent, handle); }, stop: function(jqevent, handle) { me.jqueryStop$O$O(jqevent, handle); } });
}
});

Clazz.newMeth(C$, 'jqueryStart$O$O', function (event, ui) {
this.jSlider.setValueIsAdjusting$Z(true);
});

Clazz.newMeth(C$, 'jqueryStop$O$O', function (event, ui) {
this.jSlider.setValueIsAdjusting$Z(false);
});

Clazz.newMeth(C$, 'jqueryCallback$O$O', function (event, ui) {
var value = 0;
{
value = ui.value;
}
this.jSlider.setValue$I(this.val = (value));
});

Clazz.newMeth(C$, 'setEnabled$Z', function (b) {
C$.superclazz.prototype.setEnabled$Z.apply(this, [b]);
p$.setSliderAttr$S$F.apply(this, ["disabled", (this.disabled = (b ? 0 : 1))]);
});

Clazz.newMeth(C$, 'setup$Z', function (isNew) {
this.sliderTrack = (I$[3]||$incl$(3)).firstChild(this.domNode);
this.sliderHandle = (I$[3]||$incl$(3)).firstChild(this.sliderTrack);
if (isNew) {
this.ignoreAllMouseEvents$swingjs_api_js_DOMNode(this.sliderHandle);
this.ignoreAllMouseEvents$swingjs_api_js_DOMNode(this.sliderTrack);
this.setDataComponent$swingjs_api_js_DOMNode(this.sliderHandle);
}});

Clazz.newMeth(C$, 'setSliderAttr$S$F', function (key, val) {
var slider = this.$$O(this.jqSlider);
{
var a = {};
a[key]= val;
slider.j2sslider(a);
}
});

Clazz.newMeth(C$, 'setSlider', function () {
p$.setSliderAttr$S$F.apply(this, ["value", this.val]);
p$.setSliderAttr$S$F.apply(this, ["min", this.min]);
p$.setSliderAttr$S$F.apply(this, ["max", this.max]);
this.myHeight = 10;
var barPlace = 40;
if (this.isHoriz && this.jSlider.getBorder() != null  ) barPlace = barPlace+(10);
var tickClass = "ui-j2sslider-tick-mark" + (this.isHoriz ? "-vert" : "-horiz");
this.$$O(this.domNode).find("." + tickClass).remove();
this.$$O(this.domNode).find(".jslider-labels").remove();
this.setHTMLSize$swingjs_api_js_DOMNode$Z(this.jqSlider, false);
if (this.majorSpacing == 0 && this.minorSpacing == 0  || !this.paintTicks && !this.paintLabels  ) return;
var margin = 10;
var length = (this.isHoriz ? this.jSlider.getWidth() : this.jSlider.getHeight());
if (length <= 0) length = (this.isHoriz ? this.getPreferredHorizontalSize().width : this.getPreferredVerticalSize().height);
if (this.isHoriz) this.actualWidth = length;
 else this.actualHeight = length;
length = length-(2 * margin);
if (this.paintTicks) {
if (this.minorSpacing == 0) this.minorSpacing = this.majorSpacing;
var check = (this.majorSpacing/this.minorSpacing|0);
var fracSpacing = this.minorSpacing * 1.0 / (this.max - this.min);
var numTicks = (((this.max - this.min)/this.minorSpacing|0)) + 1;
this.myHeight = this.myHeight+(10);
for (var i = 0; i < numTicks; i++) {
var node = (I$[3]||$incl$(3)).createElement("div", this.id + "_t" + i );
this.$$O(node).addClass("swingjs");
this.$$O(node).addClass(tickClass);
var isMajor = (i % check == 0);
var frac = (this.isHoriz == this.isInverted  ? 1 - fracSpacing * i : fracSpacing * i);
var spt = new Float((frac * length + margin)).toString() + "px";
if (isMajor) this.$$O(node).css(this.isHoriz ? "height" : "width", "10px");
this.$$O(node).css(this.isHoriz ? "left" : "top", spt).appendTo(this.domNode);
}
this.setHTMLSize$swingjs_api_js_DOMNode$Z(this.domNode, false);
}if (this.paintLabels) {
this.myHeight = this.myHeight+(20);
this.labelTable = this.jSlider.getLabelTable();
var keys = this.labelTable.keys();
while (keys.hasMoreElements()){
var key = keys.nextElement();
var n = Integer.parseInt(key.toString());
var label = this.labelTable.get$O(key);
var labelNode = (label.getUI()).getOuterNode();
var frac = (n - this.min) * 1.0 / (this.max - this.min);
if (this.isHoriz == this.isInverted ) frac = 1 - frac;
var px = (frac * length + margin);
var left;
var top;
if (this.isHoriz) {
top = 20;
left = ((px - (label.getWidth()/2|0))|0);
} else {
top = ((px - (label.getHeight()/2|0))|0);
left = 28;
}(I$[3]||$incl$(3)).setPositionAbsolute(labelNode, top, left);
this.domNode.appendChild(labelNode);
}
}if (this.paintTicks) {
if (this.isHoriz) {
(I$[3]||$incl$(3)).setStyles(this.sliderHandle, ["transform", "scaleX(0.5) rotateZ(45deg)", "top", "-8px"]);
(I$[3]||$incl$(3)).setStyles(this.sliderTrack, ["height", "1px", "background", "black", "top", "10px"]);
p$.setSliderAttr$S$F.apply(this, ["scaleX", 0.5]);
} else {
(I$[3]||$incl$(3)).setStyles(this.sliderHandle, ["transform", "scaleY(0.5) rotateZ(45deg)", "left", "-10px", "margin-bottom", "-7px"]);
(I$[3]||$incl$(3)).setStyles(this.sliderTrack, ["width", "1px", "left", "12px", "background", "black"]);
p$.setSliderAttr$S$F.apply(this, ["scaleY", 0.5]);
}} else {
(I$[3]||$incl$(3)).setStyles(this.sliderTrack, [this.isHoriz ? "top" : "left", barPlace + "%"]);
}if (!this.isHoriz && !this.isScrollPaneScrollBar ) (I$[3]||$incl$(3)).setStyles(this.sliderTrack, ["height", length + "px"]);
if (this.isScrollPaneScrollBar) this.setScrollPaneCSS();
this.setHTMLSize$swingjs_api_js_DOMNode$Z(this.domNode, false);
});

Clazz.newMeth(C$, 'setHTMLSize$swingjs_api_js_DOMNode$Z', function (obj, addCSS) {
var d = 20;
if (this.paintLabels || this.paintTicks ) d = d+(10);
if (this.jSlider.getBorder() != null ) d = d+(10);
return Clazz.new_((I$[4]||$incl$(4)).c$$I$I,[(this.isHoriz ? this.actualWidth : d), (this.isHoriz ? d : this.actualHeight)]);
});

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (e) {
var prop = e.getPropertyName();
if (prop == "ancestor") {
p$.setup$Z.apply(this, [false]);
}});

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
var v;
if ((v = this.jSlider.getMinimum()) != this.min) p$.setSliderAttr$S$F.apply(this, ["min", this.min = v]);
if ((v = this.jSlider.getMaximum()) != this.max) p$.setSliderAttr$S$F.apply(this, ["max", this.max = v]);
if ((v = this.jSlider.getValue()) != this.val) p$.setSliderAttr$S$F.apply(this, ["value", this.val = v]);
p$.setup$Z.apply(this, [false]);
});

Clazz.newMeth(C$, 'setInnerComponentBounds$I$I', function (width, height) {
if (!this.paintTicks && !this.paintLabels ) {
if (this.orientation == "vertical") {
(I$[3]||$incl$(3)).setStyles(this.sliderTrack, ["height", (height - 20) + "px"]);
}if (this.isScrollPaneScrollBar) this.setScrollPaneCSS();
}});

Clazz.newMeth(C$, 'setScrollPaneCSS', function () {
this.isScrollPaneScrollBar = true;
if (this.orientation == "vertical") {
(I$[3]||$incl$(3)).setStyles(this.sliderTrack, ["left", "0px", "width", "12px"]);
(I$[3]||$incl$(3)).setStyles(this.sliderHandle, ["left", "-1px"]);
} else {
(I$[3]||$incl$(3)).setStyles(this.sliderTrack, ["top", "0px", "height", "12px"]);
(I$[3]||$incl$(3)).setStyles(this.sliderHandle, ["top", "-1px"]);
}});

Clazz.newMeth(C$, 'getPreferredHorizontalSize', function () {
var horizDim = (I$[5]||$incl$(5)).get$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this.jSlider, this, "Slider.horizontalSize");
if (horizDim == null ) {
horizDim = Clazz.new_((I$[4]||$incl$(4)).c$$I$I,[200, 21]);
}return horizDim;
});

Clazz.newMeth(C$, 'getPreferredVerticalSize', function () {
var vertDim = (I$[5]||$incl$(5)).get$javax_swing_JComponent$javax_swing_plaf_ComponentUI$S(this.jSlider, this, "Slider.verticalSize");
if (vertDim == null ) {
vertDim = Clazz.new_((I$[4]||$incl$(4)).c$$I$I,[21, 200]);
}return vertDim;
});
})();
//Created 2018-05-15 01:03:25
