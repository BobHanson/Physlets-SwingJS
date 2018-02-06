(function(){var P$=Clazz.newPackage("sun.security.action"),I$=[];
var C$=Clazz.newClass(P$, "GetPropertyAction", null, null, 'java.security.PrivilegedAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.theProp = null;
this.defaultVal = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (theProp) {
C$.$init$.apply(this);
this.theProp = theProp;
}, 1);

Clazz.newMeth(C$, 'c$$S$S', function (theProp, defaultVal) {
C$.$init$.apply(this);
this.theProp = theProp;
this.defaultVal = defaultVal;
}, 1);

Clazz.newMeth(C$, 'run', function () {
var value = System.getProperty(this.theProp);
return (value == null  ? this.defaultVal : value);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:22
