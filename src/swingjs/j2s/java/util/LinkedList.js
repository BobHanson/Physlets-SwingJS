(function(){var P$=java.util,I$=[[['java.util.LinkedList','.Link'],['java.util.LinkedList','.LinkIterator'],'java.lang.reflect.Array']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "LinkedList", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.util.AbstractSequentialList', ['java.util.List', 'java.util.Queue', 'Cloneable', 'java.io.Serializable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$size = 0;
this.voidLink = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$size = 0;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.voidLink = Clazz.new_((I$[1]||$incl$(1)).c$$TET$java_util_LinkedList_Link$java_util_LinkedList_Link,[null, null, null]);
this.voidLink.previous = this.voidLink;
this.voidLink.next = this.voidLink;
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Collection', function (collection) {
C$.c$.apply(this, []);
this.addAll$java_util_Collection(collection);
}, 1);

Clazz.newMeth(C$, 'add$I$TE', function (location, object) {
if (0 <= location && location <= this.$size ) {
var link = this.voidLink;
if (location < ((this.$size/2|0))) {
for (var i = 0; i <= location; i++) {
link = link.next;
}
} else {
for (var i = this.$size; i > location; i--) {
link = link.previous;
}
}var previous = link.previous;
var newLink = Clazz.new_((I$[1]||$incl$(1)).c$$TET$java_util_LinkedList_Link$java_util_LinkedList_Link,[object, previous, link]);
previous.next = newLink;
link.previous = newLink;
this.$size++;
this.modCount++;
} else {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}});

Clazz.newMeth(C$, ['add$TE'], function (object) {
var oldLast = this.voidLink.previous;
var newLink = Clazz.new_((I$[1]||$incl$(1)).c$$TET$java_util_LinkedList_Link$java_util_LinkedList_Link,[object, oldLast, this.voidLink]);
this.voidLink.previous = newLink;
oldLast.next = newLink;
this.$size++;
this.modCount++;
return true;
});

Clazz.newMeth(C$, 'addAll$I$java_util_Collection', function (location, collection) {
if (location < 0 || location > this.$size ) {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}var adding = collection.size();
if (adding == 0) {
return false;
}var previous = this.voidLink;
if (location < ((this.$size/2|0))) {
for (var i = 0; i < location; i++) {
previous = previous.next;
}
} else {
for (var i = this.$size; i >= location; i--) {
previous = previous.previous;
}
}var next = previous.next;
for (var e, $e = collection.iterator(); $e.hasNext()&&((e=$e.next()),1);) {
var newLink = Clazz.new_((I$[1]||$incl$(1)).c$$TET$java_util_LinkedList_Link$java_util_LinkedList_Link,[e, previous, null]);
previous.next = newLink;
previous = newLink;
}
previous.next = next;
next.previous = previous;
this.$size = this.$size+(adding);
this.modCount++;
return true;
});

Clazz.newMeth(C$, 'addAll$java_util_Collection', function (collection) {
var adding = collection.size();
if (adding == 0) {
return false;
}var previous = this.voidLink.previous;
for (var e, $e = collection.iterator(); $e.hasNext()&&((e=$e.next()),1);) {
var newLink = Clazz.new_((I$[1]||$incl$(1)).c$$TET$java_util_LinkedList_Link$java_util_LinkedList_Link,[e, previous, null]);
previous.next = newLink;
previous = newLink;
}
previous.next = this.voidLink;
this.voidLink.previous = previous;
this.$size = this.$size+(adding);
this.modCount++;
return true;
});

Clazz.newMeth(C$, ['addFirst$TE'], function (object) {
var oldFirst = this.voidLink.next;
var newLink = Clazz.new_((I$[1]||$incl$(1)).c$$TET$java_util_LinkedList_Link$java_util_LinkedList_Link,[object, this.voidLink, oldFirst]);
this.voidLink.next = newLink;
oldFirst.previous = newLink;
this.$size++;
this.modCount++;
});

Clazz.newMeth(C$, ['addLast$TE'], function (object) {
var oldLast = this.voidLink.previous;
var newLink = Clazz.new_((I$[1]||$incl$(1)).c$$TET$java_util_LinkedList_Link$java_util_LinkedList_Link,[object, oldLast, this.voidLink]);
this.voidLink.previous = newLink;
oldLast.next = newLink;
this.$size++;
this.modCount++;
});

Clazz.newMeth(C$, 'clear', function () {
if (this.$size > 0) {
this.$size = 0;
this.voidLink.next = this.voidLink;
this.voidLink.previous = this.voidLink;
this.modCount++;
}});

Clazz.newMeth(C$, 'clone', function () {
return Clazz.new_(C$.c$$java_util_Collection,[this]);
});

Clazz.newMeth(C$, 'contains$O', function (object) {
var link = this.voidLink.next;
if (object != null ) {
while (link !== this.voidLink ){
if (object.equals$O(link.data)) {
return true;
}link = link.next;
}
} else {
while (link !== this.voidLink ){
if (link.data == null ) {
return true;
}link = link.next;
}
}return false;
});

Clazz.newMeth(C$, 'get$I', function (location) {
if (0 <= location && location < this.$size ) {
var link = this.voidLink;
if (location < ((this.$size/2|0))) {
for (var i = 0; i <= location; i++) {
link = link.next;
}
} else {
for (var i = this.$size; i > location; i--) {
link = link.previous;
}
}return link.data;
}throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
});

Clazz.newMeth(C$, 'getFirst', function () {
var first = this.voidLink.next;
if (first !== this.voidLink ) {
return first.data;
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$, 'getLast', function () {
var last = this.voidLink.previous;
if (last !== this.voidLink ) {
return last.data;
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$, 'indexOf$O', function (object) {
var pos = 0;
var link = this.voidLink.next;
if (object != null ) {
while (link !== this.voidLink ){
if (object.equals$O(link.data)) {
return pos;
}link = link.next;
pos++;
}
} else {
while (link !== this.voidLink ){
if (link.data == null ) {
return pos;
}link = link.next;
pos++;
}
}return -1;
});

Clazz.newMeth(C$, 'lastIndexOf$O', function (object) {
var pos = this.$size;
var link = this.voidLink.previous;
if (object != null ) {
while (link !== this.voidLink ){
pos--;
if (object.equals$O(link.data)) {
return pos;
}link = link.previous;
}
} else {
while (link !== this.voidLink ){
pos--;
if (link.data == null ) {
return pos;
}link = link.previous;
}
}return -1;
});

Clazz.newMeth(C$, 'listIterator$I', function (location) {
return Clazz.new_((I$[2]||$incl$(2)).c$$java_util_LinkedList$I,[this, location]);
});

Clazz.newMeth(C$, 'remove$I', function (location) {
if (0 <= location && location < this.$size ) {
var link = this.voidLink;
if (location < ((this.$size/2|0))) {
for (var i = 0; i <= location; i++) {
link = link.next;
}
} else {
for (var i = this.$size; i > location; i--) {
link = link.previous;
}
}var previous = link.previous;
var next = link.next;
previous.next = next;
next.previous = previous;
this.$size--;
this.modCount++;
return link.data;
}throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
});

Clazz.newMeth(C$, 'remove$O', function (object) {
var link = this.voidLink.next;
if (object != null ) {
while (link !== this.voidLink  && !object.equals$O(link.data) ){
link = link.next;
}
} else {
while (link !== this.voidLink  && link.data != null  ){
link = link.next;
}
}if (link === this.voidLink ) {
return false;
}var next = link.next;
var previous = link.previous;
previous.next = next;
next.previous = previous;
this.$size--;
this.modCount++;
return true;
});

Clazz.newMeth(C$, 'removeFirst', function () {
var first = this.voidLink.next;
if (first !== this.voidLink ) {
var next = first.next;
this.voidLink.next = next;
next.previous = this.voidLink;
this.$size--;
this.modCount++;
return first.data;
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$, 'removeLast', function () {
var last = this.voidLink.previous;
if (last !== this.voidLink ) {
var previous = last.previous;
this.voidLink.previous = previous;
previous.next = this.voidLink;
this.$size--;
this.modCount++;
return last.data;
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$, 'set$I$TE', function (location, object) {
if (0 <= location && location < this.$size ) {
var link = this.voidLink;
if (location < ((this.$size/2|0))) {
for (var i = 0; i <= location; i++) {
link = link.next;
}
} else {
for (var i = this.$size; i > location; i--) {
link = link.previous;
}
}var result = link.data;
link.data = object;
return result;
}throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
});

Clazz.newMeth(C$, 'size', function () {
return this.$size;
});

Clazz.newMeth(C$, ['offer$TE'], function (o) {
this.add$TE(o);
return true;
});

Clazz.newMeth(C$, 'poll', function () {
return this.$size == 0 ? null : this.removeFirst();
});

Clazz.newMeth(C$, 'remove', function () {
return this.removeFirst();
});

Clazz.newMeth(C$, 'peek', function () {
var first = this.voidLink.next;
return first === this.voidLink  ? null : first.data;
});

Clazz.newMeth(C$, 'element', function () {
return this.getFirst();
});

Clazz.newMeth(C$, 'toArray', function () {
var index = 0;
var contents = Clazz.array(java.lang.Object, [this.$size]);
var link = this.voidLink.next;
while (link !== this.voidLink ){
contents[index++] = link.data;
link = link.next;
}
return contents;
});

Clazz.newMeth(C$, 'toArray$TTA', function (contents) {
var index = 0;
if (this.$size > contents.length) {
var ct = contents.getClass().getComponentType();
contents = Clazz.array(ct, this.$size);
}var link = this.voidLink.next;
while (link !== this.voidLink ){
contents[index++] = link.data;
link = link.next;
}
if (index < contents.length) {
contents[index] = null;
}return contents;
});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (stream) {
stream.defaultWriteObject();
stream.writeInt$I(this.$size);
var it = this.iterator();
while (it.hasNext()){
stream.writeObject$O(it.next());
}
});

Clazz.newMeth(C$, 'readObject$java_io_ObjectInputStream', function (stream) {
stream.defaultReadObject();
this.$size = stream.readInt();
this.voidLink = Clazz.new_((I$[1]||$incl$(1)).c$$TET$java_util_LinkedList_Link$java_util_LinkedList_Link,[null, null, null]);
var link = this.voidLink;
for (var i = this.$size; --i >= 0; ) {
var nextLink = Clazz.new_((I$[1]||$incl$(1)).c$$TET$java_util_LinkedList_Link$java_util_LinkedList_Link,[stream.readObject(), link, null]);
link.next = nextLink;
link = nextLink;
}
link.next = this.voidLink;
this.voidLink.previous = link;
});
;
(function(){var C$=Clazz.newClass(P$.LinkedList, "Link", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.data = null;
this.previous = null;
this.next = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$TET$java_util_LinkedList_Link$java_util_LinkedList_Link', function (o, p, n) {
C$.$init$.apply(this);
this.data = o;
this.previous = p;
this.next = n;
}, 1);

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.LinkedList, "LinkIterator", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, 'java.util.ListIterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.pos = 0;
this.expectedModCount = 0;
this.list = null;
this.link = null;
this.lastLink = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_LinkedList$I', function (object, location) {
C$.$init$.apply(this);
this.list = object;
this.expectedModCount = this.list.modCount;
if (0 <= location && location <= this.list.$size ) {
this.link = this.list.voidLink;
if (location < (this.list.$size/2|0)) {
for (this.pos = -1; this.pos + 1 < location; this.pos++) {
this.link = this.link.next;
}
} else {
for (this.pos = this.list.$size; this.pos >= location; this.pos--) {
this.link = this.link.previous;
}
}} else {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}}, 1);

Clazz.newMeth(C$, ['add$TET','add$TE'], function (object) {
if (this.expectedModCount == this.list.modCount) {
var next = this.link.next;
var newLink = Clazz.new_((I$[1]||$incl$(1)).c$$TET$java_util_LinkedList_Link$java_util_LinkedList_Link,[object, this.link, next]);
this.link.next = newLink;
next.previous = newLink;
this.link = newLink;
this.lastLink = null;
this.pos++;
this.expectedModCount++;
this.list.$size++;
this.list.modCount++;
} else {
throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
}});

Clazz.newMeth(C$, 'hasNext', function () {
return this.link.next !== this.list.voidLink ;
});

Clazz.newMeth(C$, 'hasPrevious', function () {
return this.link !== this.list.voidLink ;
});

Clazz.newMeth(C$, 'next', function () {
if (this.expectedModCount == this.list.modCount) {
var next = this.link.next;
if (next !== this.list.voidLink ) {
this.lastLink = this.link = next;
this.pos++;
return this.link.data;
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$, 'nextIndex', function () {
return this.pos + 1;
});

Clazz.newMeth(C$, 'previous', function () {
if (this.expectedModCount == this.list.modCount) {
if (this.link !== this.list.voidLink ) {
this.lastLink = this.link;
this.link = this.link.previous;
this.pos--;
return this.lastLink.data;
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$, 'previousIndex', function () {
return this.pos;
});

Clazz.newMeth(C$, 'remove', function () {
if (this.expectedModCount == this.list.modCount) {
if (this.lastLink != null ) {
var next = this.lastLink.next;
var previous = this.lastLink.previous;
next.previous = previous;
previous.next = next;
if (this.lastLink === this.link ) {
this.pos--;
}this.link = previous;
this.lastLink = null;
this.expectedModCount++;
this.list.$size--;
this.list.modCount++;
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
}} else {
throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
}});

Clazz.newMeth(C$, ['set$TET','set$TE'], function (object) {
if (this.expectedModCount == this.list.modCount) {
if (this.lastLink != null ) {
this.lastLink.data = object;
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
}} else {
throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
}});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-15 01:02:13
