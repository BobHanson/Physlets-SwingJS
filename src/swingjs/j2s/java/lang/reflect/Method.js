(function(){var P$=java.lang.reflect,I$=[];
var C$=Clazz.newClass(P$, "Method", null, 'java.lang.reflect.AccessibleObject', ['java.lang.reflect.GenericDeclaration', 'java.lang.reflect.Member']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.clazz = null;
this.name = null;
this.returnType = null;
this.parameterTypes = null;
this.exceptionTypes = null;
this.modifiers = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$Class$S$ClassA$Class$ClassA$I', function (declaringClass, name, parameterTypes, returnType, checkedExceptions, modifiers) {
Clazz.super_(C$, this,1);
this.clazz = declaringClass;
this.name = name;
this.parameterTypes = parameterTypes;
this.returnType = returnType;
this.exceptionTypes = checkedExceptions;
this.modifiers = modifiers;
}, 1);

Clazz.newMeth(C$, 'getTypeParameters', function () {
return null;
});

Clazz.newMeth(C$, 'toGenericString', function () {
return null;
});

Clazz.newMeth(C$, 'getGenericParameterTypes', function () {
return null;
});

Clazz.newMeth(C$, 'getGenericExceptionTypes', function () {
return null;
});

Clazz.newMeth(C$, 'getGenericReturnType', function () {
return null;
});

Clazz.newMeth(C$, 'getParameterAnnotations', function () {
return null;
});

Clazz.newMeth(C$, 'isVarArgs', function () {
return false;
});

Clazz.newMeth(C$, 'isBridge', function () {
return false;
});

Clazz.newMeth(C$, 'isSynthetic', function () {
return false;
});

Clazz.newMeth(C$, 'getDefaultValue', function () {
return null;
});

Clazz.newMeth(C$, 'equals$O', function (object) {
if (object != null  && Clazz.instanceOf(object, "java.lang.reflect.Method") ) {
var other = object;
if ((this.getDeclaringClass() === other.getDeclaringClass() ) && (this.getName() == other.getName()) ) {
var params1 = this.parameterTypes;
var params2 = other.parameterTypes;
if (params1.length == params2.length) {
for (var i = 0; i < params1.length; i++) {
if (params1[i] !== params2[i] ) return false;
}
return true;
}}}return false;
});

Clazz.newMeth(C$, 'getDeclaringClass', function () {
return this.clazz;
});

Clazz.newMeth(C$, 'getExceptionTypes', function () {
return this.exceptionTypes;
});

Clazz.newMeth(C$, 'getModifiers', function () {
return this.modifiers;
});

Clazz.newMeth(C$, 'getName', function () {
return this.name;
});

Clazz.newMeth(C$, 'getParameterTypes', function () {
return this.parameterTypes;
});

Clazz.newMeth(C$, 'getReturnType', function () {
return this.returnType;
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.getDeclaringClass().getName().hashCode() ^ this.getName().hashCode();
});

Clazz.newMeth(C$, 'invoke$O$OA', function (receiver, args) {

var m = this.clazz.prototype[this.getName ()];
if (m == null) { m = this.clazz[this.getName ()];
} if (m != null) { return m.apply(receiver,args);
} else { // should never reach here!
}
});

Clazz.newMeth(C$, 'toString', function () {
return null;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:09
