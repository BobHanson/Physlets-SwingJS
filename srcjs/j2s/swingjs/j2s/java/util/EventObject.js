(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "EventObject", null, null, 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.source = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function (source) {
C$.$init$.apply(this);
if (source != null ) this.source = source;
}, 1);

Clazz.newMeth(C$, 'getSource', function () {
return this.source;
});

Clazz.newMeth(C$, 'toString', function () {
return this.getClass().getName() + "[source=" + String.valueOf(this.source) + ']' ;
});
})();
//Created 2018-02-08 10:02:12
