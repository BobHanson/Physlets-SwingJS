(function(){var P$=Clazz.newPackage("sun.font"),I$=[['Thread','java.util.HashMap']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AttributeMap", null, 'java.util.AbstractMap');
C$.first = false;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.first = false;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$$values = null;
this.delegateMap = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$sun_font_AttributeValues', function (values) {
Clazz.super_(C$, this,1);
this.$$values = values;
}, 1);

Clazz.newMeth(C$, 'entrySet', function () {
return p$.delegate.apply(this, []).entrySet();
});

Clazz.newMeth(C$, ['put$java_awt_font_TextAttribute$O','put$TK$TV'], function (key, value) {
return p$.delegate.apply(this, []).put$TK$TV(key, value);
});

Clazz.newMeth(C$, 'getValues', function () {
return this.$$values;
});

Clazz.newMeth(C$, 'delegate', function () {
if (this.delegateMap == null ) {
if (C$.first) {
C$.first = false;
(I$[1]||$incl$(1)).dumpStack();
}this.delegateMap = this.$$values.toMap$java_util_Map(Clazz.new_((I$[2]||$incl$(2)).c$$I,[27]));
this.$$values = null;
}return this.delegateMap;
});

Clazz.newMeth(C$, 'toString', function () {
if (this.$$values != null ) {
return "map of " + this.$$values.toString();
}return C$.superclazz.prototype.toString.apply(this, []);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:11
