(function(){var P$=Clazz.newPackage("java.awt.event"),I$=[];
var C$=Clazz.newClass(P$, "InputMethodEvent", null, 'java.awt.AWTEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.when = 0;
this.text = null;
this.committedCharacterCount = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Event', function (event) {
C$.superclazz.c$$java_awt_Event.apply(this, [event]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getText', function () {
return this.text;
});

Clazz.newMeth(C$, 'getCommittedCharacterCount', function () {
return this.committedCharacterCount;
});

Clazz.newMeth(C$, 'getWhen', function () {
return this.when;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:01:57
