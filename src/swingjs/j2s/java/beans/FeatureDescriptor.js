(function(){var P$=Clazz.newPackage("java.beans"),I$=[['java.util.Hashtable']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FeatureDescriptor");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.classRef = null;
this.expert = false;
this.hidden = false;
this.preferred = false;
this.shortDescription = null;
this.name = null;
this.displayName = null;
this.table = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getName', function () {
return this.name;
});

Clazz.newMeth(C$, 'setName$S', function (name) {
this.name = name;
});

Clazz.newMeth(C$, 'getDisplayName', function () {
if (this.displayName == null ) {
return this.getName();
}return this.displayName;
});

Clazz.newMeth(C$, 'setDisplayName$S', function (displayName) {
this.displayName = displayName;
});

Clazz.newMeth(C$, 'isExpert', function () {
return this.expert;
});

Clazz.newMeth(C$, 'setExpert$Z', function (expert) {
this.expert = expert;
});

Clazz.newMeth(C$, 'isHidden', function () {
return this.hidden;
});

Clazz.newMeth(C$, 'setHidden$Z', function (hidden) {
this.hidden = hidden;
});

Clazz.newMeth(C$, 'isPreferred', function () {
return this.preferred;
});

Clazz.newMeth(C$, 'setPreferred$Z', function (preferred) {
this.preferred = preferred;
});

Clazz.newMeth(C$, 'getShortDescription', function () {
if (this.shortDescription == null ) {
return this.getDisplayName();
}return this.shortDescription;
});

Clazz.newMeth(C$, 'setShortDescription$S', function (text) {
this.shortDescription = text;
});

Clazz.newMeth(C$, 'setValue$S$O', function (attributeName, value) {
if (this.table == null ) {
this.table = Clazz.new_((I$[1]||$incl$(1)));
}this.table.put$TK$TV(attributeName, value);
});

Clazz.newMeth(C$, 'getValue$S', function (attributeName) {
if (this.table == null ) {
return null;
}return this.table.get$O(attributeName);
});

Clazz.newMeth(C$, 'attributeNames', function () {
if (this.table == null ) {
this.table = Clazz.new_((I$[1]||$incl$(1)));
}return this.table.keys();
});

Clazz.newMeth(C$, 'c$$java_beans_FeatureDescriptor$java_beans_FeatureDescriptor', function (x, y) {
C$.$init$.apply(this);
this.expert = !!(x.expert | y.expert);
this.hidden = !!(x.hidden | y.hidden);
this.preferred = !!(x.preferred | y.preferred);
this.name = y.name;
this.shortDescription = x.shortDescription;
if (y.shortDescription != null ) {
this.shortDescription = y.shortDescription;
}this.displayName = x.displayName;
if (y.displayName != null ) {
this.displayName = y.displayName;
}this.classRef = x.classRef;
if (y.classRef != null ) {
this.classRef = y.classRef;
}p$.addTable$java_util_Hashtable.apply(this, [x.table]);
p$.addTable$java_util_Hashtable.apply(this, [y.table]);
}, 1);

Clazz.newMeth(C$, 'c$$java_beans_FeatureDescriptor', function (old) {
C$.$init$.apply(this);
this.expert = old.expert;
this.hidden = old.hidden;
this.preferred = old.preferred;
this.name = old.name;
this.shortDescription = old.shortDescription;
this.displayName = old.displayName;
this.classRef = old.classRef;
p$.addTable$java_util_Hashtable.apply(this, [old.table]);
}, 1);

Clazz.newMeth(C$, 'addTable$java_util_Hashtable', function (t) {
if (t == null ) {
return;
}var keys = t.keys();
while (keys.hasMoreElements()){
var key = keys.nextElement();
var value = t.get$O(key);
this.setValue$S$O(key, value);
}
});

Clazz.newMeth(C$, 'setClass0$Class', function (cls) {
this.classRef = cls;
});

Clazz.newMeth(C$, 'getClass0', function () {
return (this.classRef != null ) ? this.classRef : null;
});

Clazz.newMeth(C$, 'getReturnType$Class$reflect_Method', function (base, method) {
return null;
}, 1);

Clazz.newMeth(C$, 'getParameterTypes$Class$reflect_Method', function (base, method) {
return Clazz.array(java.lang.Class, [0]);
}, 1);
})();
//Created 2018-05-15 01:02:03
