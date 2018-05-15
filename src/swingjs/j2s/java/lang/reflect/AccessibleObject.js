(function(){var P$=java.lang.reflect,I$=[['java.lang.annotation.Annotation']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AccessibleObject", null, null, 'java.lang.reflect.AnnotatedElement');
C$.emptyArgs = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.emptyArgs = Clazz.array(java.lang.Object, [0]);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'isAccessible', function () {
return false;
});

Clazz.newMeth(C$, 'setAccessible$reflect_AccessibleObjectA$Z', function (objects, flag) {
return;
}, 1);

Clazz.newMeth(C$, 'setAccessible$Z', function (flag) {
return;
});

Clazz.newMeth(C$, 'isAnnotationPresent$Class', function (annotationType) {
return false;
});

Clazz.newMeth(C$, 'getDeclaredAnnotations', function () {
return Clazz.array((I$[1]||$incl$(1)), [0]);
});

Clazz.newMeth(C$, 'getAnnotations', function () {
return Clazz.array((I$[1]||$incl$(1)), [0]);
});

Clazz.newMeth(C$, 'getAnnotation$Class', function (annotationType) {
return null;
});

Clazz.newMeth(C$, 'marshallArguments$ClassA$OA', function (parameterTypes, args) {
return null;
}, 1);

Clazz.newMeth(C$, 'invokeV$O$OA', function (receiver, args) {
return;
});

Clazz.newMeth(C$, 'invokeL$O$OA', function (receiver, args) {
return null;
});

Clazz.newMeth(C$, 'invokeI$O$OA', function (receiver, args) {
return 0;
});

Clazz.newMeth(C$, 'invokeJ$O$OA', function (receiver, args) {
return 0;
});

Clazz.newMeth(C$, 'invokeF$O$OA', function (receiver, args) {
return 0.0;
});

Clazz.newMeth(C$, 'invokeD$O$OA', function (receiver, args) {
return 0.0;
});

Clazz.newMeth(C$, 'getParameterTypesImpl', function () {
alert('native method must be replaced! Ljava/lang/reflect/AccessibleObject;.getParameterTypesImpl()[Ljava/lang/Class<*>;');
}
);

Clazz.newMeth(C$, 'getModifiers', function () {
alert('native method must be replaced! Ljava/lang/reflect/AccessibleObject;.getModifiers()I');
}
);

Clazz.newMeth(C$, 'getExceptionTypesImpl', function () {
alert('native method must be replaced! Ljava/lang/reflect/AccessibleObject;.getExceptionTypesImpl()[Ljava/lang/Class<*>;');
}
);

Clazz.newMeth(C$, 'getSignature', function () {
alert('native method must be replaced! Ljava/lang/reflect/AccessibleObject;.getSignature()Ljava/lang/String;');
}
);

Clazz.newMeth(C$, 'checkAccessibility$Class$O', function (senderClass, receiver) {
alert('native method must be replaced! Ljava/lang/reflect/AccessibleObject;.checkAccessibility(Ljava/lang/Class<*>;Ljava/lang/Object;)Z');
}
);

Clazz.newMeth(C$, 'initializeClass$Class', function (clazz) {
alert('native method must be replaced! Ljava/lang/reflect/AccessibleObject;.initializeClass(Ljava/lang/Class<*>;)V');
}
, 2);

Clazz.newMeth(C$, 'getStackClass$I', function (depth) {
alert('native method must be replaced! Ljava/lang/reflect/AccessibleObject;.getStackClass(I)Ljava/lang/Class<*>;');
}
, 2);
})();
//Created 2018-05-15 01:02:09
