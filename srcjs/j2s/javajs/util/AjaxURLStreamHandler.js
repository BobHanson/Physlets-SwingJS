(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['javajs.util.AjaxURLConnection','javajs.util.SB']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AjaxURLStreamHandler", null, 'java.net.URLStreamHandler');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.protocol = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (protocol) {
Clazz.super_(C$, this,1);
this.protocol = protocol;
}, 1);

Clazz.newMeth(C$, 'openConnection$java_net_URL', function (url) {
return Clazz.new_((I$[1]||$incl$(1)).c$$java_net_URL,[url]);
});

Clazz.newMeth(C$, 'toExternalForm$java_net_URL', function (u) {
var result = Clazz.new_((I$[2]||$incl$(2)));
result.append$S(u.getProtocol());
result.append$S(":");
if (u.getAuthority() != null  && u.getAuthority().length$() > 0 ) {
result.append$S("//");
result.append$S(u.getAuthority());
}if (u.getPath() != null ) {
result.append$S(u.getPath());
}if (u.getQuery() != null ) {
result.append$S("?");
result.append$S(u.getQuery());
}if (u.getRef() != null ) {
result.append$S("#");
result.append$S(u.getRef());
}return result.toString();
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:01
