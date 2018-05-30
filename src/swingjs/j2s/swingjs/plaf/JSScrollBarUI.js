(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.plaf.JSComponentUI','java.awt.Dimension','swingjs.api.js.DOMNode']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSScrollBarUI", null, 'swingjs.plaf.JSSliderUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.myScrollPaneUI = null;
this.isInvisible = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.isScrollBar=true;
}, 1);

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (e) {
C$.superclazz.prototype.propertyChange$java_beans_PropertyChangeEvent.apply(this, [e]);
if ((I$[1]||$incl$(1)).debugging) System.out.println$S(this.id + " propertyChange " + this.dumpEvent$java_util_EventObject(e) );
});

Clazz.newMeth(C$, 'stateChanged$javax_swing_event_ChangeEvent', function (e) {
C$.superclazz.prototype.stateChanged$javax_swing_event_ChangeEvent.apply(this, [e]);
if ((I$[1]||$incl$(1)).debugging) System.out.println$S(this.id + " stateChange " + this.dumpEvent$java_util_EventObject(e) );
});

Clazz.newMeth(C$, 'getPreferredSize', function () {
var wh = (this.myScrollPaneUI == null  ? 15 : this.myScrollPaneUI.scrollBarUIDisabled ? 0 : 15);
return Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[wh, wh]);
});

Clazz.newMeth(C$, 'setVisible$Z', function (b) {
this.isInvisible=(this.myScrollPaneUI != null  && this.myScrollPaneUI.scrollBarUIDisabled );
b&=!this.isInvisible;
(I$[3]||$incl$(3)).setVisible(this.getOuterNode(), b);
(I$[3]||$incl$(3)).setVisible(this.jqSlider, b);
});
})();
//Created 2018-05-24 08:47:57
