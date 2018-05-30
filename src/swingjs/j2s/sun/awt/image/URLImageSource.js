(function(){var P$=Clazz.newPackage("sun.awt.image"),I$=[['java.net.URL']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "URLImageSource", null, 'sun.awt.image.InputStreamImageSource');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.url = null;
this.conn = null;
this.actualHost = null;
this.actualPort = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_net_URL', function (u) {
Clazz.super_(C$, this,1);
this.url=u;
}, 1);

Clazz.newMeth(C$, 'c$$S', function (href) {
C$.c$$java_net_URL.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$java_net_URL$S,[null, href])]);
}, 1);

Clazz.newMeth(C$, 'c$$java_net_URL$java_net_URLConnection', function (u, uc) {
C$.c$$java_net_URL.apply(this, [u]);
this.conn=uc;
}, 1);

Clazz.newMeth(C$, 'c$$java_net_URLConnection', function (uc) {
C$.c$$java_net_URL$java_net_URLConnection.apply(this, [uc.getURL(), uc]);
}, 1);

Clazz.newMeth(C$, 'checkSecurity$O$Z', function (context, quiet) {
if (this.actualHost != null ) {
try {
var security = System.getSecurityManager();
if (security != null ) {
security.checkConnect$S$I$O(this.actualHost, this.actualPort, context);
}} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.SecurityException")){
if (!quiet) {
throw e;
}return false;
} else {
throw e;
}
}
}return true;
});

Clazz.newMeth(C$, 'getDecoder', function () {
return null;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:29
