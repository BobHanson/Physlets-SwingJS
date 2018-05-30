(function(){var P$=Clazz.newPackage("java.awt.event"),I$=[];
var C$=Clazz.newClass(P$, "MouseWheelEvent", null, 'java.awt.event.MouseEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.scrollType = 0;
this.scrollAmount = 0;
this.wheelRotation = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Component$I$J$I$I$I$I$Z$I$I$I', function (source, id, when, modifiers, x, y, clickCount, popupTrigger, scrollType, scrollAmount, wheelRotation) {
C$.c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I$I$I.apply(this, [source, id, when, modifiers, x, y, 0, 0, clickCount, popupTrigger, scrollType, scrollAmount, wheelRotation]);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I$I$I', function (source, id, when, modifiers, x, y, xAbs, yAbs, clickCount, popupTrigger, scrollType, scrollAmount, wheelRotation) {
C$.superclazz.c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I.apply(this, [source, id, when, modifiers, x, y, xAbs, yAbs, clickCount, popupTrigger, 0]);
C$.$init$.apply(this);
this.scrollType=scrollType;
this.scrollAmount=scrollAmount;
this.wheelRotation=wheelRotation;
}, 1);

Clazz.newMeth(C$, 'getScrollType', function () {
return this.scrollType;
});

Clazz.newMeth(C$, 'getScrollAmount', function () {
return this.scrollAmount;
});

Clazz.newMeth(C$, 'getWheelRotation', function () {
return this.wheelRotation;
});

Clazz.newMeth(C$, 'getUnitsToScroll', function () {
return this.scrollAmount * this.wheelRotation;
});

Clazz.newMeth(C$, 'paramString', function () {
var scrollTypeStr = null;
if (this.getScrollType() == 0) {
scrollTypeStr="WHEEL_UNIT_SCROLL";
} else if (this.getScrollType() == 1) {
scrollTypeStr="WHEEL_BLOCK_SCROLL";
} else {
scrollTypeStr="unknown scroll type";
}return C$.superclazz.prototype.paramString.apply(this, []) + ",scrollType=" + scrollTypeStr + ",scrollAmount=" + this.getScrollAmount() + ",wheelRotation=" + this.getWheelRotation() ;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:18
