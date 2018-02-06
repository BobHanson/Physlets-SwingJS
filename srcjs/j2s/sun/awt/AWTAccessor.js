(function(){var P$=Clazz.newPackage("sun.awt"),I$=[];
var C$=Clazz.newClass(P$, "AWTAccessor", function(){
Clazz.newInstance(this, arguments,0,C$);
});
var p$=C$.prototype;
C$.componentAccessor = null;
C$.windowAccessor = null;
C$.awtEventAccessor = null;
C$.eventQueueAccessor = null;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'setWindowAccessor$sun_awt_AWTAccessor_WindowAccessor', function (wa) {
C$.windowAccessor = wa;
}, 1);

Clazz.newMeth(C$, 'getWindowAccessor', function () {
return C$.windowAccessor;
}, 1);

Clazz.newMeth(C$, 'setComponentAccessor$sun_awt_AWTAccessor_ComponentAccessor', function (ca) {
C$.componentAccessor = ca;
}, 1);

Clazz.newMeth(C$, 'getComponentAccessor', function () {
return C$.componentAccessor;
}, 1);

Clazz.newMeth(C$, 'setAWTEventAccessor$sun_awt_AWTAccessor_AWTEventAccessor', function (aea) {
C$.awtEventAccessor = aea;
}, 1);

Clazz.newMeth(C$, 'getAWTEventAccessor', function () {
return C$.awtEventAccessor;
}, 1);

Clazz.newMeth(C$, 'setEventQueueAccessor$sun_awt_AWTAccessor_EventQueueAccessor', function (eqa) {
C$.eventQueueAccessor = eqa;
}, 1);

Clazz.newMeth(C$, 'getEventQueueAccessor', function () {
return C$.eventQueueAccessor;
}, 1);
;
(function(){var C$=Clazz.newInterface(P$.AWTAccessor, "WindowAccessor", function(){
});
})()
;
(function(){var C$=Clazz.newInterface(P$.AWTAccessor, "ComponentAccessor", function(){
});
})()
;
(function(){var C$=Clazz.newInterface(P$.AWTAccessor, "KeyboardFocusManagerAccessor", function(){
});
})()
;
(function(){var C$=Clazz.newInterface(P$.AWTAccessor, "AWTEventAccessor", function(){
});
})()
;
(function(){var C$=Clazz.newInterface(P$.AWTAccessor, "EventQueueAccessor", function(){
});
})()
;
(function(){var C$=Clazz.newInterface(P$.AWTAccessor, "CursorAccessor", function(){
});
})()
;
(function(){var C$=Clazz.newInterface(P$.AWTAccessor, "ClientPropertyKeyAccessor", function(){
});
})()
})();
//Created 2018-02-06 09:00:11
