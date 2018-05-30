(function(){var P$=Clazz.newPackage("javax.xml.sax.helpers"),I$=[['java.lang.Thread','Thread','java.lang.UnknownError']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "NewInstance");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'newInstance$ClassLoader$S', function (classLoader, className) {
var driverClass;
if (classLoader == null ) {
driverClass=Clazz.forName(className);
} else {
driverClass=classLoader.loadClass$S(className);
}return driverClass.newInstance();
}, 1);

Clazz.newMeth(C$, 'getClassLoader', function () {
var m = null;
try {
m=Clazz.getClass((I$[1]||$incl$(1))).getMethod$S$ClassA("getContextClassLoader", []);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.NoSuchMethodException")){
return Clazz.getClass(C$).getClassLoader();
} else {
throw e;
}
}
try {
return m.invoke$O$OA((I$[2]||$incl$(2)).currentThread(), null);
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.lang.IllegalAccessException")){
var e = e$$;
{
throw Clazz.new_((I$[3]||$incl$(3)).c$$S,[e.getMessage()]);
}
} else if (Clazz.exceptionOf(e$$, "java.lang.reflect.InvocationTargetException")){
var e = e$$;
{
throw Clazz.new_((I$[3]||$incl$(3)).c$$S,[e.getMessage()]);
}
} else {
throw e$$;
}
}
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:14
