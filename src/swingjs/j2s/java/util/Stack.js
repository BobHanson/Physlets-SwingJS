(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "Stack", null, 'java.util.Vector');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'empty', function () {
return this.elementCount == 0;
});

Clazz.newMeth(C$, 'peek', function () {
try {
return this.elementData[this.elementCount - 1];
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.IndexOutOfBoundsException")){
throw Clazz.new_(Clazz.load('java.util.EmptyStackException'));
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'pop', function () {
try {
var index = this.elementCount - 1;
var obj = this.elementData[index];
this.removeElementAt$I(index);
return obj;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.IndexOutOfBoundsException")){
throw Clazz.new_(Clazz.load('java.util.EmptyStackException'));
} else {
throw e;
}
}
});

Clazz.newMeth(C$, ['push$TE'], function (object) {
this.addElement$TE(object);
return object;
});

Clazz.newMeth(C$, 'search$O', function (o) {
var index = this.lastIndexOf$O(o);
if (index >= 0) return (this.elementCount - index);
return -1;
});
})();
//Created 2018-05-24 08:45:49
