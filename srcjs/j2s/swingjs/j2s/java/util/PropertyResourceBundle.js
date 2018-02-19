(function(){var P$=java.util,I$=[['java.util.Properties','java.util.HashMap','sun.util.ResourceBundleEnumeration']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PropertyResourceBundle", null, 'java.util.ResourceBundle');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.lookup = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_io_InputStream', function (stream) {
Clazz.super_(C$, this,1);
this.setStream$java_io_InputStream(stream);
}, 1);

Clazz.newMeth(C$, 'setStream$java_io_InputStream', function (stream) {
var properties = Clazz.new_((I$[1]||$incl$(1)));
properties.load$java_io_InputStream(stream);
this.lookup = Clazz.new_((I$[2]||$incl$(2)).c$$java_util_Map,[properties]);
return this;
});

Clazz.newMeth(C$, 'c$$java_io_Reader', function (reader) {
Clazz.super_(C$, this,1);
var properties = Clazz.new_((I$[1]||$incl$(1)));
properties.load$java_io_Reader(reader);
this.lookup = Clazz.new_((I$[2]||$incl$(2)).c$$java_util_Map,[properties]);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'handleGetObject$S', function (key) {
if (key == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}return this.lookup.get$O(key);
});

Clazz.newMeth(C$, 'getKeys', function () {
var parent = this.parent;
return Clazz.new_((I$[3]||$incl$(3)).c$$java_util_Set$java_util_Enumeration,[this.lookup.keySet(), (parent != null ) ? parent.getKeys() : null]);
});

Clazz.newMeth(C$, 'handleKeySet', function () {
return this.lookup.keySet();
});
})();
//Created 2018-02-08 10:02:14
