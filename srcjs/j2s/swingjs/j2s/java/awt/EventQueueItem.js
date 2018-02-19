(function(){var P$=Clazz.newPackage("java.awt"),I$=[];
var C$=Clazz.newClass(P$, "EventQueueItem");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.event = null;
this.id = 0;
this.next = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_AWTEvent', function (evt) {
C$.$init$.apply(this);
this.event = evt;
this.id = evt.getID();
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:01:48
