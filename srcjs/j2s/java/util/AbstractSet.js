(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "AbstractSet", null, 'java.util.AbstractCollection', 'java.util.Set');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'equals$O', function (object) {
if (this === object ) {
return true;
}if (Clazz.instanceOf(object, "java.util.Set")) {
var s = object;
return this.size() == s.size() && this.containsAll$java_util_Collection(s) ;
}return false;
});

Clazz.newMeth(C$, 'hashCode', function () {
var result = 0;
var it = this.iterator();
while (it.hasNext()){
var next = it.next();
result = result+(next == null  ? 0 : next.hashCode());
}
return result;
});

Clazz.newMeth(C$, 'removeAll$java_util_Collection', function (collection) {
var result = false;
if (this.size() <= collection.size()) {
var it = this.iterator();
while (it.hasNext()){
if (collection.contains$O(it.next())) {
it.remove();
result = true;
}}
} else {
var it = collection.iterator();
while (it.hasNext()){
result = this.remove$O(it.next()) || result ;
}
}return result;
});
})();
//Created 2018-02-06 08:58:46
