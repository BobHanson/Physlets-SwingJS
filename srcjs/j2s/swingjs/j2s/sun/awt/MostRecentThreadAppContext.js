(function(){var P$=Clazz.newPackage("sun.awt"),I$=[];
var C$=Clazz.newClass(P$, "MostRecentThreadAppContext");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.thread = null;
this.appContext = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$Thread$sun_awt_AppContext', function (key, value) {
C$.$init$.apply(this);
this.thread = key;
this.appContext = value;
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:08
