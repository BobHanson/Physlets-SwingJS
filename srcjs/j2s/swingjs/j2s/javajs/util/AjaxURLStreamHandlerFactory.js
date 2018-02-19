(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['java.util.Hashtable','javajs.util.AjaxURLStreamHandler']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AjaxURLStreamHandlerFactory", null, null, 'java.net.URLStreamHandlerFactory');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.htFactories = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.htFactories = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'createURLStreamHandler$S', function (protocol) {
var fac = this.htFactories.get$O(protocol);
if (fac == null ) this.htFactories.put$TK$TV(protocol, fac = Clazz.new_((I$[2]||$incl$(2)).c$$S,[protocol]));
return (fac.protocol == null  ? null : fac);
});
})();
//Created 2018-02-08 10:02:18
