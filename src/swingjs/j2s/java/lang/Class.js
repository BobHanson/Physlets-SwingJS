(function(){var P$=java.lang,I$=[['java.lang.reflect.Method','java.util.Arrays','java.lang.reflect.TypeVariable','java.lang.Boolean','java.lang.Void','java.lang.Enum','java.util.HashMap']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(java.lang, "Class", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, ['java.io.Serializable', 'java.lang.reflect.GenericDeclaration']);
var p$=C$.prototype;
C$.initted = false;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.initted = false;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$clazz$ = null;
this.$methodList$ = null;
this.name = null;
this.classRedefinedCount = 0;
this.lastRedefinedCount = 0;
this.enumConstants = null;
this.$enumConstantDirectory = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.classRedefinedCount = 0;
this.lastRedefinedCount = 0;
this.enumConstants = null;
this.$enumConstantDirectory = null;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'toString', function () {
return (this.isInterface() ? "interface " : (this.isPrimitive() ? "" : "class ")) + this.getName();
});

Clazz.newMeth(C$, 'forName$S', function (className) {
return Class.forName0$S$Z$ClassLoader(className, true, null);
}, 1);

Clazz.newMeth(C$, 'forName$S$Z$ClassLoader', function (name, initialize, loader) {
return Class.forName0$S$Z$ClassLoader(name, initialize, loader);
}, 1);

Clazz.newMeth(C$, 'forName0$S$Z$ClassLoader', function (name, initialize, loader) {
{
return Clazz.forName(name, initialize, loader);
}
}, 1);

Clazz.newMeth(C$, 'newInstance', function () {
{
return new this.$clazz$;
}
});

Clazz.newMeth(C$, 'isInstance$O', function (obj) {
{
return Clazz.instanceOf(obj, this.$clazz$);
}
});

Clazz.newMeth(C$, 'isAssignableFrom$Class', function (cls) {
{
return(Clazz.instanceOf(cls.$clazz$, this.$clazz$));
}
});

Clazz.newMeth(C$, 'isInterface', function () {
{
return !this.$clazz$.$init$;
}
});

Clazz.newMeth(C$, 'isArray', function () {
{
return !!this.$clazz$.__ARRAYTYPE;
}
});

Clazz.newMeth(C$, 'isPrimitive', function () {
{
return !!this.__PRIMITIVE;
}
});

Clazz.newMeth(C$, 'isAnnotation', function () {
return (this.getModifiers() & 8192) != 0;
});

Clazz.newMeth(C$, 'isSynthetic', function () {
return (this.getModifiers() & 4096) != 0;
});

Clazz.newMeth(C$, 'getName', function () {
if (this.name == null ) this.name = p$.getName0.apply(this, []);
return this.name;
});

Clazz.newMeth(C$, 'getName0', function () {
{
return this.$clazz$.__CLASS_NAME$__ || this.$clazz$.__CLASS_NAME__;
}
});

Clazz.newMeth(C$, 'getClassLoader', function () {
return this.getClassLoader0();
});

Clazz.newMeth(C$, 'getClassLoader0', function () {
var loader = null;
{
var baseFolder = Clazz._Loader.getJ2SLibBase();
loader = Clazz._Loader.requireLoaderByBase(baseFolder);
var me = this;
loader.getResourceAsStream$S = function(s) { return me.getResourceAsStream$S(s.indexOf("/") == 0 ? s : "/" + s) };
loader.getResource$S = function(s) { return me.getResource$S(s.indexOf("/") == 0 ? s : "/" + s) };
}
return loader;
});

Clazz.newMeth(C$, 'getTypeParameters', function () {
return Clazz.array((I$[3]||$incl$(3)), [0]);
});

Clazz.newMeth(C$, 'getSuperclass', function () {
{
if (this.$clazz$ == java.lang.Object) return null;
return Clazz.getClass(this.$clazz$.superclazz || java.lang.Object);
}
});

Clazz.newMeth(C$, 'getInterfaces', function () {
alert('native method must be replaced! Ljava/lang/Class;.getInterfaces()[Ljava/lang/Class<*>;');
}
);

Clazz.newMeth(C$, 'getComponentType', function () {
return null;
});

Clazz.newMeth(C$, 'getModifiers', function () {
return 1;
});

Clazz.newMeth(C$, 'getSigners', function () {
alert('native method must be replaced! Ljava/lang/Class;.getSigners()[Ljava/lang/Object;');
}
);

Clazz.newMeth(C$, 'setSigners$OA', function (signers) {
alert('native method must be replaced! Ljava/lang/Class;.setSigners([Ljava/lang/Object;)V');
}
);

Clazz.newMeth(C$, 'getDeclaringClass', function () {
alert('native method must be replaced! Ljava/lang/Class;.getDeclaringClass()Ljava/lang/Class<*>;');
}
);

Clazz.newMeth(C$, 'getSimpleName', function () {
if (this.isArray()) return this.getComponentType().getSimpleName() + "[]";
var name = "";
{
name = (this.$clazz$.__ANON ? "" : this.$clazz$.__CLASS_NAME__);
}
return name.substring(name.lastIndexOf(".") + 1);
});

Clazz.newMeth(C$, 'getCanonicalName', function () {
if (this.isArray()) {
var canonicalName = this.getComponentType().getCanonicalName();
if (canonicalName != null ) return canonicalName + "[]";
 else return null;
}if (p$.isLocalOrAnonymousClass.apply(this, [])) return null;
var name = null;

name = this.$clazz$.__CLASS_NAME__ || null;
return name;
});

Clazz.newMeth(C$, 'isAnonymousClass', function () {
return "".equals$O(this.getSimpleName());
});

Clazz.newMeth(C$, 'isLocalClass', function () {
{
return !!this.$clazz$.__LOCAL;
}
});

Clazz.newMeth(C$, 'isLocalOrAnonymousClass', function () {
{
return !!this.$clazz$.__ISANON || !!this.$clazz$.__LOCAL;
}
});

Clazz.newMeth(C$, 'getClasses', function () {
return null;
});

Clazz.newMeth(C$, 'getFields', function () {
return null;
});

Clazz.newMeth(C$, 'getMethods', function () {
return (p$.privateGetPublicMethods.apply(this, []));
});

Clazz.newMeth(C$, 'getConstructors', function () {
return null;
});

Clazz.newMeth(C$, 'getField$S', function (name) {
{
return null;
}});

Clazz.newMeth(C$, 'getMethod$S$ClassA', function (name, paramTypes) {
{
return Clazz.new_(java.lang.reflect.Method.c$$Class$S$ClassA$Class$ClassA$I, [this, name, paramTypes, java.lang.Void, [], 0]);
}
});

Clazz.newMeth(C$, 'getConstructor$ClassA', function (parameterTypes) {
{
return Clazz.new_(java.lang.reflect.Constructor.c$$Class$ClassA$ClassA$I, [this, parameterTypes || [], [], java.lang.reflect.Modifier.PUBLIC]);
}
});

Clazz.newMeth(C$, 'getDeclaredClasses', function () {
return this.getClasses();
});

Clazz.newMeth(C$, 'getDeclaredFields', function () {
return this.getFields();
});

Clazz.newMeth(C$, 'getDeclaredMethods', function () {
return this.getMethods();
});

Clazz.newMeth(C$, 'getDeclaredConstructors', function () {
return this.getConstructors();
});

Clazz.newMeth(C$, 'getDeclaredField$S', function (name) {
return this.getField$S(name);
});

Clazz.newMeth(C$, 'getDeclaredMethod$S$ClassA', function (name, parameterTypes) {
return this.getMethod$S$ClassA(name, parameterTypes);
});

Clazz.newMeth(C$, 'getDeclaredConstructor$ClassA', function (parameterTypes) {
return this.getConstructor$ClassA(parameterTypes);
});

Clazz.newMeth(C$, 'getResourceAsStream$S', function (name) {
var clazzName = this.$clazz$.__CLASS_NAME__;
{
if (!name) return null;
name = name.replace (/\\/g, '/');
var baseFolder = null;
var fname = name;
if (arguments.length == 2 && name.indexOf ('/') != 0) { // additional argument
name = "/" + name;
} if (name.indexOf ('/') == 0) { if (arguments.length == 2)  // additional argument
baseFolder = arguments[1];
if (!baseFolder) baseFolder = Clazz._Loader.getJ2SLibBase();
if (baseFolder.charAt(baseFolder.length - 1) != '/') baseFolder += "/";
fname = baseFolder + name.substring (1);
} else { baseFolder = Clazz._Loader.getJ2SLibBase(); // getClass().getClassLoader() uses full path
fname = baseFolder;
if (this.$_$base == null) { // getClass().getResource() will be here
var pkgs = clazzName.split(".");
var fname = baseFolder;
if (fname.charAt(fname.length - 1) != '/') fname += "/";
var map = Clazz._allPackage;
for (var i = 0; i < pkgs.length - 1; i++) { if (!(map = map[pkgs[i]])) break;
fname += pkgs[i] + "/";
} } fname += name;
} var url = null;
var javapath = fname;
try { if (fname.indexOf(":/") < 0) { var d = document.location.href.split("?")[0].split("/");
d[d.length - 1] = fname;
fname = d.join("/");
} Clazz.load("java.net.URL");
url = Clazz.new_(java.net.URL.c$$S,[fname]);
} catch (e) { return null;
} var fileCache = J2S._getSetJavaFileCache(null);
var data = fileCache && fileCache.get$O(javapath);
if (!data) data = J2S._getFileData(fname.toString(),null,1,1);
if (data == null || data == "error" || data.indexOf && data.indexOf("[Exception") == 0) return null;
var bytes = (data.__BYTESIZE == 1 ? data : J2S._strToBytes(data));
Clazz.load("java.io.BufferedInputStream");
Clazz.load("java.io.ByteArrayInputStream");
var is = Clazz.new_(java.io.BufferedInputStream.c$$java_io_InputStream, [Clazz.new_(java.io.ByteArrayInputStream.c$$BA, [bytes])]);
is.url = url;
url._streamData = is;
return is;
}
});

Clazz.newMeth(C$, 'getResource$S', function (name) {
{
var stream = this.getResourceAsStream$S(name);
return(stream ? stream.url : null);
}
});

Clazz.newMeth(C$, 'getPrimitiveClass$S', function (name) {
switch (name) {
case "boolean":
return (I$[4]||$incl$(4)).TYPE;
case "byte":
return Byte.TYPE;
case "char":
return Character.TYPE;
case "short":
return Short.TYPE;
case "int":
return Integer.TYPE;
case "long":
return Long.TYPE;
case "float":
return Float.TYPE;
case "double":
return Double.TYPE;
default:
return null;
}
}, 1);

Clazz.newMeth(C$, 'privateGetPublicMethods', function () {
var ms;
if (this.$methodList$ != null ) {
ms = Clazz.array((I$[1]||$incl$(1)), [this.$methodList$.length]);
for (var i = ms.length; --i >= 0; ) {
ms[i] = Clazz.new_((I$[1]||$incl$(1)).c$$Class$S$ClassA$Class$ClassA$I,[this, this.$methodList$[i], null, Clazz.getClass((I$[5]||$incl$(5))), null, 1]);
}
return ms;
}ms = Clazz.array((I$[1]||$incl$(1)), [0]);
{
var p = this.$clazz$.prototype;
for (var attr in p) { if (typeof p[attr] == "function" && !p[attr].__CLASS_NAME__ && p[attr] != this.$clazz$[attr] && p[attr].exClazz == this.$clazz$) { // there are polynormical methods.
ms.push(Clazz.new_(Clazz.load('java.lang.reflect.Method').c$$Class$S$ClassA$Class$ClassA$I,  [this, attr, [], java.lang.Void, [], 1]));
} } p = this.$clazz$;
for (var attr in p) { if (typeof p[attr] == "function" && !p[attr].__CLASS_NAME__  && p[attr].exClazz == this.$clazz$) { ms.push(Clazz.new_(Clazz.load('java.lang.reflect.Method').c$$Class$S$ClassA$Class$ClassA$I,  [this, attr, [], java.lang.Void, [], 1 | 8]));
} }
}
return ms;
});

Clazz.newMeth(C$, 'arrayContentsEq$OA$OA', function (a1, a2) {
if (a1 == null ) {
return a2 == null  || a2.length == 0 ;
}if (a2 == null ) {
return a1.length == 0;
}if (a1.length != a2.length) {
return false;
}for (var i = 0; i < a1.length; i++) {
if (a1[i] !== a2[i] ) {
return false;
}}
return true;
}, 1);

Clazz.newMeth(C$, 'isEnum', function () {
return (this.getModifiers() & 16384) != 0 && this.getSuperclass() === Clazz.getClass((I$[6]||$incl$(6)))  ;
});

Clazz.newMeth(C$, 'getEnumConstants', function () {
var values = this.getEnumConstantsShared();
return (values != null ) ? values.clone() : null;
});

Clazz.newMeth(C$, 'getEnumConstantsShared', function () {
if (this.enumConstants == null ) {
if (!this.isEnum()) return null;
try {
var values = this.getMethod$S$ClassA("values", []);
this.enumConstants = values.invoke$O$OA(null, null);
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.lang.reflect.InvocationTargetException")){
var ex = e$$;
{
return null;
}
} else if (Clazz.exceptionOf(e$$, "java.lang.NoSuchMethodException")){
var ex = e$$;
{
return null;
}
} else if (Clazz.exceptionOf(e$$, "java.lang.IllegalAccessException")){
var ex = e$$;
{
return null;
}
} else {
throw e$$;
}
}
}return this.enumConstants;
});

Clazz.newMeth(C$, 'enumConstantDirectory', function () {
if (this.$enumConstantDirectory == null ) {
var universe = this.getEnumConstantsShared();
if (universe == null ) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[this.getName() + " is not an enum type"]);
var m = Clazz.new_((I$[7]||$incl$(7)).c$$I,[2 * universe.length]);
for (var constant, $constant = 0, $$constant = universe; $constant<$$constant.length&&((constant=$$constant[$constant]),1);$constant++) m.put$TK$TV((constant).name(), constant);

this.$enumConstantDirectory = m;
}return this.$enumConstantDirectory;
});

Clazz.newMeth(C$, 'cast$O', function (obj) {
if (obj != null  && !this.isInstance$O(obj) ) throw Clazz.new_(Clazz.load('java.lang.ClassCastException').c$$S,[p$.cannotCastMsg$O.apply(this, [obj])]);
return obj;
});

Clazz.newMeth(C$, 'cannotCastMsg$O', function (obj) {
return "Cannot cast " + obj.getClass().getName() + " to " + this.getName() ;
});

Clazz.newMeth(C$, 'hashCode', function () {
var name = null;

name = this.$clazz$.__CLASS_NAME__ || this.$clazz$.toString();
return name.hashCode();
});

Clazz.newMeth(C$, 'equals$O', function (o) {

return o.__CLASS_NAME__ == "java.lang.Class" && o.$clazz$ == this.$clazz$;
return false;
});
;
(function(){var C$=Clazz.newClass(Class, "JSClass", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.__CLASS_NAME__ = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(Class, "MethodArray", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.methods = null;
this.$length = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.methods = Clazz.array((I$[1]||$incl$(1)), [20]);
this.$length = 0;
}, 1);

Clazz.newMeth(C$, 'add$reflect_Method', function (m) {
if (this.$length == this.methods.length) {
this.methods = (I$[2]||$incl$(2)).copyOf$TTA$I(this.methods, 2 * this.methods.length);
}this.methods[this.$length++] = m;
});

Clazz.newMeth(C$, 'addAll$reflect_MethodA', function (ma) {
for (var i = 0; i < ma.length; i++) {
this.add$reflect_Method(ma[i]);
}
});

Clazz.newMeth(C$, 'addAll$Class_MethodArray', function (ma) {
for (var i = 0; i < ma.length$(); i++) {
this.add$reflect_Method(ma.get$I(i));
}
});

Clazz.newMeth(C$, 'addIfNotPresent$reflect_Method', function (newMethod) {
for (var i = 0; i < this.$length; i++) {
var m = this.methods[i];
if (m === newMethod  || (m != null  && m.equals$O(newMethod) ) ) {
return;
}}
this.add$reflect_Method(newMethod);
});

Clazz.newMeth(C$, 'addAllIfNotPresent$Class_MethodArray', function (newMethods) {
for (var i = 0; i < newMethods.length$(); i++) {
var m = newMethods.get$I(i);
if (m != null ) {
this.addIfNotPresent$reflect_Method(m);
}}
});

Clazz.newMeth(C$, 'length$', function () {
return this.$length;
});

Clazz.newMeth(C$, 'get$I', function (i) {
return this.methods[i];
});

Clazz.newMeth(C$, 'removeByNameAndSignature$reflect_Method', function (toRemove) {
for (var i = 0; i < this.$length; i++) {
var m = this.methods[i];
if (m != null  && m.getReturnType() === toRemove.getReturnType()   && m.getName() == toRemove.getName()  && Class.arrayContentsEq$OA$OA(m.getParameterTypes(), toRemove.getParameterTypes()) ) {
this.methods[i] = null;
}}
});

Clazz.newMeth(C$, 'compactAndTrim', function () {
var newPos = 0;
for (var pos = 0; pos < this.$length; pos++) {
var m = this.methods[pos];
if (m != null ) {
if (pos != newPos) {
this.methods[newPos] = m;
}newPos++;
}}
if (newPos != this.methods.length) {
this.methods = (I$[2]||$incl$(2)).copyOf$TTA$I(this.methods, newPos);
}});

Clazz.newMeth(C$, 'getArray', function () {
return this.methods;
});
})()
})();
//Created 2018-05-15 01:02:06
