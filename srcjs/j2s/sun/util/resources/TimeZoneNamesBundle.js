(function(){var P$=Clazz.newPackage("sun.util.resources"),I$=[['java.util.LinkedHashMap']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "TimeZoneNamesBundle", null, 'sun.util.resources.OpenListResourceBundle');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'handleGetObject$S', function (key) {
var contents = C$.superclazz.prototype.handleGetObject$S.apply(this, [key]);
if (contents == null ) {
return null;
}var clen = contents.length;
var tmpobj = Clazz.array(java.lang.String, [clen + 1]);
tmpobj[0] = key;
for (var i = 0; i < clen; i++) {
tmpobj[i + 1] = contents[i];
}
return tmpobj;
});

Clazz.newMeth(C$, 'createMap$I', function (size) {
return Clazz.new_((I$[1]||$incl$(1)).c$$I,[size]);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:26
