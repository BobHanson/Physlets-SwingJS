(function(){var P$=Clazz.newPackage("sun.util.resources"),I$=[['sun.util.ResourceBundleEnumeration','java.util.HashMap']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "OpenListResourceBundle", null, 'java.util.ResourceBundle');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.lookup = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.lookup = null;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'handleGetObject$S', function (key) {
if (key == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.loadLookupTablesIfNecessary();
return this.lookup.get$O(key);
});

Clazz.newMeth(C$, 'getKeys', function () {
var parent = this.parent;
return Clazz.new_((I$[1]||$incl$(1)).c$$java_util_Set$java_util_Enumeration,[this.handleGetKeys(), (parent != null ) ? parent.getKeys() : null]);
});

Clazz.newMeth(C$, 'handleGetKeys', function () {
this.loadLookupTablesIfNecessary();
return this.lookup.keySet();
});

Clazz.newMeth(C$, 'getParent', function () {
return this.parent;
});

Clazz.newMeth(C$, 'loadLookupTablesIfNecessary', function () {
if (this.lookup == null ) {
p$.loadLookup.apply(this, []);
}});

Clazz.newMeth(C$, 'loadLookup', function () {
if (this.lookup != null ) return;
var contents = this.getContents();
var temp = this.createMap$I(contents.length);
for (var i = 0; i < contents.length; ++i) {
var key = contents[i][0];
var value = contents[i][1];
if (key == null  || value == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}temp.put$TK$TV(key, value);
}
this.lookup = temp;
});

Clazz.newMeth(C$, 'createMap$I', function (size) {
return Clazz.new_((I$[2]||$incl$(2)).c$$I,[size]);
});
})();
//Created 2018-05-15 01:03:12
