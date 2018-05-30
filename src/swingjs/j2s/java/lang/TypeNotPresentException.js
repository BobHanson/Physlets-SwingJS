(function(){var P$=java.lang,I$=[];
var C$=Clazz.newClass(java.lang, "TypeNotPresentException", null, 'RuntimeException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$typeName = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Throwable', function (typeName, cause) {
C$.superclazz.c$$S$Throwable.apply(this, ["Type " + typeName + " not present" , cause]);
C$.$init$.apply(this);
this.$typeName=typeName;
}, 1);

Clazz.newMeth(C$, 'typeName', function () {
return this.$typeName;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:39
