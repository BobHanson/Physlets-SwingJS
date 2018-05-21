(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "AbstractSequentialList", null, 'java.util.AbstractList');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'add$I$TE', function (location, object) {
this.listIterator$I(location).add$TE(object);
});

Clazz.newMeth(C$, 'addAll$I$java_util_Collection', function (location, collection) {
var it = this.listIterator$I(location);
var colIt = collection.iterator();
var next = it.nextIndex();
while (colIt.hasNext()){
it.add$TE(colIt.next());
it.previous();
}
return next != it.nextIndex();
});

Clazz.newMeth(C$, 'get$I', function (location) {
try {
return this.listIterator$I(location).next();
} catch (e) {
if (Clazz.exceptionOf(e, "java.util.NoSuchElementException")){
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'iterator', function () {
return this.listIterator$I(0);
});

Clazz.newMeth(C$, 'remove$I', function (location) {
try {
var it = this.listIterator$I(location);
var result = it.next();
it.remove();
return result;
} catch (e) {
if (Clazz.exceptionOf(e, "java.util.NoSuchElementException")){
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'set$I$TE', function (location, object) {
var it = this.listIterator$I(location);
var result = it.next();
it.set$TE(object);
return result;
});
})();
//Created 2018-05-15 01:02:11
