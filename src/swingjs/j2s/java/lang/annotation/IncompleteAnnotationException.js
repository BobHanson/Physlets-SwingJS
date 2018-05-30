(function(){var P$=java.lang.annotation,I$=[['org.apache.harmony.luni.util.Msg']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "IncompleteAnnotationException", null, 'RuntimeException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$annotationType = null;
this.$elementName = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$Class$S', function (annotationType, elementName) {
C$.superclazz.c$$S.apply(this, [(I$[1]||$incl$(1)).getString$S$O$O("annotation.0", elementName, annotationType)]);
C$.$init$.apply(this);
this.$annotationType=annotationType;
this.$elementName=elementName;
}, 1);

Clazz.newMeth(C$, 'annotationType', function () {
return this.$annotationType;
});

Clazz.newMeth(C$, 'elementName', function () {
return this.$elementName;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:39
