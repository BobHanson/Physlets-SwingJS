(function(){var P$=java.util,I$=[['java.lang.reflect.Array','java.lang.StringBuilder']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AbstractCollection", null, null, 'java.util.Collection');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, ['add$TE'], function (object) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'addAll$java_util_Collection', function (collection) {
var result = false;
var it = collection.iterator();
while (it.hasNext()){
if (this.add$TE(it.next())) {
result = true;
}}
return result;
});

Clazz.newMeth(C$, 'clear', function () {
var it = this.iterator();
while (it.hasNext()){
it.next();
it.remove();
}
});

Clazz.newMeth(C$, 'contains$O', function (object) {
var it = this.iterator();
if (object != null ) {
while (it.hasNext()){
if (object.equals$O(it.next())) {
return true;
}}
} else {
while (it.hasNext()){
if (it.next() == null ) {
return true;
}}
}return false;
});

Clazz.newMeth(C$, 'containsAll$java_util_Collection', function (collection) {
var it = collection.iterator();
while (it.hasNext()){
if (!this.contains$O(it.next())) {
return false;
}}
return true;
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.size() == 0;
});

Clazz.newMeth(C$, 'remove$O', function (object) {
var it = this.iterator();
if (object != null ) {
while (it.hasNext()){
if (object.equals$O(it.next())) {
it.remove();
return true;
}}
} else {
while (it.hasNext()){
if (it.next() == null ) {
it.remove();
return true;
}}
}return false;
});

Clazz.newMeth(C$, 'removeAll$java_util_Collection', function (collection) {
var result = false;
var it = this.iterator();
while (it.hasNext()){
if (collection.contains$O(it.next())) {
it.remove();
result = true;
}}
return result;
});

Clazz.newMeth(C$, 'retainAll$java_util_Collection', function (collection) {
var result = false;
var it = this.iterator();
while (it.hasNext()){
if (!collection.contains$O(it.next())) {
it.remove();
result = true;
}}
return result;
});

Clazz.newMeth(C$, 'toArray', function () {
var size = this.size();
var index = 0;
var it = this.iterator();
var array = Clazz.array(java.lang.Object, [size]);
while (index < size){
array[index++] = it.next();
}
return array;
});

Clazz.newMeth(C$, 'toArray$TTA', function (contents) {
var size = this.size();
var index = 0;
if (size > contents.length) {
var ct = contents.getClass().getComponentType();
contents = Clazz.array(ct, size);
}for (var entry, $entry = this.iterator(); $entry.hasNext()&&((entry=$entry.next()),1);) {
contents[index++] = entry;
}
if (index < contents.length) {
contents[index] = null;
}return contents;
});

Clazz.newMeth(C$, 'toString', function () {
if (this.isEmpty()) {
return "[]";
}var buffer = Clazz.new_((I$[2]||$incl$(2)).c$$I,[this.size() * 16]);
buffer.append$C("[");
var it = this.iterator();
while (it.hasNext()){
var next = it.next();
if (next !== this ) {
buffer.append$O(next);
} else {
buffer.append$S("(this Collection)");
}if (it.hasNext()) {
buffer.append$S(", ");
}}
buffer.append$C("]");
return buffer.toString();
});
})();
//Created 2018-02-08 10:02:11
