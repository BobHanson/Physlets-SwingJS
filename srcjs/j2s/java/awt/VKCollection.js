(function(){var P$=Clazz.newPackage("java.awt"),I$=[['sun.awt.AppContext','java.util.HashMap','java.util.StringTokenizer','java.util.Collections','java.awt.VKCollection','java.lang.StringBuilder']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "VKCollection");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.code2name = null;
this.name2code = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.code2name = Clazz.new_((I$[2]||$incl$(2)));
this.name2code = Clazz.new_((I$[2]||$incl$(2)));
}, 1);

Clazz.newMeth(C$, 'put$S$Integer', function (name, code) {
this.code2name.put$TK$TV(code, name);
this.name2code.put$TK$TV(name, code);
});

Clazz.newMeth(C$, 'findCode$S', function (name) {
return this.name2code.get$O(name);
});

Clazz.newMeth(C$, 'findName$Integer', function (code) {
return this.code2name.get$O(code);
});
})();
//Created 2018-02-06 08:58:07
