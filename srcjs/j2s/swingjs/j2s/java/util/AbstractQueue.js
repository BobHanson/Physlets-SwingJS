(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "AbstractQueue", null, 'java.util.AbstractCollection', 'java.util.Queue');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, ['add$TE'], function (o) {
if (null == o ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (this.offer$TE(o)) {
return true;
}throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
});

Clazz.newMeth(C$, 'addAll$java_util_Collection', function (c) {
if (null == c ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (this === c ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}return C$.superclazz.prototype.addAll$java_util_Collection.apply(this, [c]);
});

Clazz.newMeth(C$, 'remove', function () {
var o = this.poll();
if (null == o ) {
throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
}return o;
});

Clazz.newMeth(C$, 'element', function () {
var o = this.peek();
if (null == o ) {
throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
}return o;
});

Clazz.newMeth(C$, 'clear', function () {
var o;
do {
o = this.poll();
} while (null != o );
});
})();
//Created 2018-02-08 10:02:11
