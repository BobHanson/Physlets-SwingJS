(function(){var P$=Clazz.newPackage("java.awt.event"),I$=[];
var C$=Clazz.newClass(P$, "TextEvent", null, 'java.awt.AWTEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O$I', function (source, id) {
C$.superclazz.c$$O$I.apply(this, [source, id]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'paramString', function () {
var typeStr;
switch (this.id) {
case 900:
typeStr = "TEXT_VALUE_CHANGED";
break;
default:
typeStr = "unknown type";
}
return typeStr;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:01:58
