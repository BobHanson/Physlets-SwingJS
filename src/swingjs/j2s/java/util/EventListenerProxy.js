(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "EventListenerProxy", null, null, 'java.util.EventListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.listener = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function (listener) {
C$.$init$.apply(this);
this.listener = listener;
}, 1);

Clazz.newMeth(C$, 'getListener', function () {
return this.listener;
});
})();
//Created 2018-05-15 01:02:12
