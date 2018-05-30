(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode','javax.swing.Timer','swingjs.plaf.JSSpinnerUI$1','javax.swing.LookAndFeel']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSSpinnerUI", null, 'swingjs.plaf.JSLightweightUI');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.spinner = null;
this.dn = null;
this.up = null;
this.dnNode = null;
this.upNode = null;
this.timer = null;
this.incrementing = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.domNode=this.newDOMObject$S$S$SA("div", this.id, []);
this.focusNode=this.valueNode=(I$[1]||$incl$(1)).setStyles(this.newDOMObject$S$S$SA("input", this.id, ["type", "text"]), ["padding", "0px 1px", "width", "30px", "text-align", "right"]);
P$.JSComponentUI.vCenter$swingjs_api_js_DOMNode$I(this.valueNode, -10);
this.bindJSKeyEvents$swingjs_api_js_DOMNode$Z(this.valueNode, false);
this.up=(I$[1]||$incl$(1)).setStyles(this.newDOMObject$S$S$SA("div", this.id + "_updiv", []), ["left", "33px", "top", "-5px", "position", "absolute"]);
this.upNode=(I$[1]||$incl$(1)).setStyles(this.newDOMObject$S$S$SA("input", this.id + "_up", ["type", "button", "value", ""]), ["transform", "scaleY(.5)", "width", "20px", "height", "20px"]);
this.up.appendChild(this.upNode);
this.bindJSEvents$swingjs_api_js_DOMNode$S$I$Z(this.upNode, "mousedown touchstart", 501, true);
this.bindJSEvents$swingjs_api_js_DOMNode$S$I$Z(this.upNode, "mouseup touchend", 502, true);
this.dn=(I$[1]||$incl$(1)).setStyles(this.newDOMObject$S$S$SA("div", this.id + "_dndiv", []), ["left", "33px", "top", "5px", "position", "absolute"]);
this.dnNode=(I$[1]||$incl$(1)).setStyles(this.newDOMObject$S$S$SA("input", this.id + "_dn", ["type", "button", "value", ""]), ["transform", "scaleY(.5)", "width", "20px", "height", "20px"]);
this.dn.appendChild(this.dnNode);
this.bindJSEvents$swingjs_api_js_DOMNode$S$I$Z(this.dnNode, "mousedown touchstart", 501, true);
this.bindJSEvents$swingjs_api_js_DOMNode$S$I$Z(this.dnNode, "mouseup touchend", 502, true);
this.domNode.appendChild(this.valueNode);
this.domNode.appendChild(this.up);
this.domNode.appendChild(this.dn);
this.enableNodes=Clazz.array((I$[1]||$incl$(1)), -1, [this.valueNode, this.up, this.dn]);
this.addJQueryFocusCallbacks();
}this.setCssFont$swingjs_api_js_DOMNode$java_awt_Font(p$.setValue.apply(this, []), this.c.getFont());
var w = (this.spinner.isPreferredSizeSet() ? this.spinner.getPreferredSize().width : 70);
(I$[1]||$incl$(1)).setStyles(this.valueNode, ["width", (w - 38) + "px"]);
(I$[1]||$incl$(1)).setStyles(this.up, ["left", (w - 34) + "px"]);
(I$[1]||$incl$(1)).setStyles(this.dn, ["left", (w - 34) + "px"]);
return this.domNode;
});

Clazz.newMeth(C$, 'setValue', function () {
this.setProp$swingjs_api_js_DOMNode$S$S(this.valueNode, "value", "" + this.spinner.getValue());
return this.valueNode;
});

Clazz.newMeth(C$, 'handleJSEvent$O$I$O', function (target, eventID, jQueryEvent) {
var keyCode = 0;
var id = (I$[1]||$incl$(1)).getAttr(target, "id");
{
keyCode = jQueryEvent.keyCode; if (keyCode == 13) keyCode = 10;
}
switch (eventID) {
case 501:
if (this.timer != null ) this.timer.stop();
this.incrementing=(id == this.id + "_up");
if (!this.incrementing && id != this.id + "_dn" ) return true;
this.timer=Clazz.new_((I$[2]||$incl$(2)).c$$I$java_awt_event_ActionListener,[20, ((
(function(){var C$=Clazz.newClass(P$, "JSSpinnerUI$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.awt.event.ActionListener', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.b$['swingjs.plaf.JSSpinnerUI'].doAction();
});
})()
), Clazz.new_((I$[3]||$incl$(3)).$init$, [this, null]))]);
this.timer.start();
this.doAction();
break;
case 502:
if (this.timer != null ) this.timer.stop();
this.timer=null;
break;
case 401:
if (keyCode == 10) {
try {
var n = Integer.parseInt("" + (I$[1]||$incl$(1)).getAttr(this.valueNode, "value"));
this.spinner.setValue$O( new Integer(n));
} catch (e) {
}
}break;
}
return true;
});

Clazz.newMeth(C$, 'doAction', function () {
var val = (this.incrementing ? this.spinner.getNextValue() : this.spinner.getPreviousValue());
if (val != null ) this.spinner.setValue$O(val);
});

Clazz.newMeth(C$, 'propertyChangedFromListener$S', function (prop) {
this.propertyChangedCUI$S(prop);
});

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
if (this.valueNode == null ) {
return;
}p$.setValue.apply(this, []);
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.spinner=jc;
(I$[4]||$incl$(4)).installColorsAndFont$javax_swing_JComponent$S$S$S(jc, "Spinner.background", "Spinner.foreground", "Spinner.font");
C$.superclazz.prototype.installUI$javax_swing_JComponent.apply(this, [jc]);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:58
