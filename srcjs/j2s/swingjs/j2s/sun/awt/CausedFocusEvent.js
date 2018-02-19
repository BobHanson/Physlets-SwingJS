(function(){var P$=Clazz.newPackage("sun.awt"),I$=[[['sun.awt.CausedFocusEvent','.Cause']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "CausedFocusEvent", null, 'java.awt.event.FocusEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.cause = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getCause', function () {
return this.cause;
});

Clazz.newMeth(C$, 'toString', function () {
return "java.awt.FocusEvent[" + C$.superclazz.prototype.paramString.apply(this, []) + ",cause=" + this.cause + "] on " + this.getSource() ;
});

Clazz.newMeth(C$, 'c$$java_awt_Component$I$Z$java_awt_Component$sun_awt_CausedFocusEvent_Cause', function (source, id, temporary, opposite, cause) {
C$.superclazz.c$$java_awt_Component$I$Z$java_awt_Component.apply(this, [source, id, temporary, opposite]);
C$.$init$.apply(this);
if (cause == null ) {
cause = (I$[1]||$incl$(1)).UNKNOWN;
}this.cause = cause;
}, 1);

Clazz.newMeth(C$, 'retarget$java_awt_event_FocusEvent$java_awt_Component', function (e, newSource) {
if (e == null ) return null;
return Clazz.new_(C$.c$$java_awt_Component$I$Z$java_awt_Component$sun_awt_CausedFocusEvent_Cause,[newSource, e.getID(), e.isTemporary(), e.getOppositeComponent(), (Clazz.instanceOf(e, "sun.awt.CausedFocusEvent")) ? (e).getCause() : (I$[1]||$incl$(1)).RETARGETED]);
}, 1);
;
(function(){var C$=Clazz.newClass(P$.CausedFocusEvent, "Cause", null, 'Enum');

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "UNKNOWN", 0, []);
Clazz.newEnumConst($vals, C$.c$, "MOUSE_EVENT", 1, []);
Clazz.newEnumConst($vals, C$.c$, "TRAVERSAL", 2, []);
Clazz.newEnumConst($vals, C$.c$, "TRAVERSAL_UP", 3, []);
Clazz.newEnumConst($vals, C$.c$, "TRAVERSAL_DOWN", 4, []);
Clazz.newEnumConst($vals, C$.c$, "TRAVERSAL_FORWARD", 5, []);
Clazz.newEnumConst($vals, C$.c$, "TRAVERSAL_BACKWARD", 6, []);
Clazz.newEnumConst($vals, C$.c$, "MANUAL_REQUEST", 7, []);
Clazz.newEnumConst($vals, C$.c$, "AUTOMATIC_TRAVERSE", 8, []);
Clazz.newEnumConst($vals, C$.c$, "ROLLBACK", 9, []);
Clazz.newEnumConst($vals, C$.c$, "NATIVE_SYSTEM", 10, []);
Clazz.newEnumConst($vals, C$.c$, "ACTIVATION", 11, []);
Clazz.newEnumConst($vals, C$.c$, "CLEAR_GLOBAL_FOCUS_OWNER", 12, []);
Clazz.newEnumConst($vals, C$.c$, "RETARGETED", 13, []);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})()

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:08
