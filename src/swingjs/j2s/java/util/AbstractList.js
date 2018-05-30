(function(){var P$=java.util,I$=[[['java.util.AbstractList','.SubAbstractList','.SubAbstractListIterator'],['java.util.AbstractList','.SimpleListIterator'],['java.util.AbstractList','.FullListIterator'],['java.util.AbstractList','.SubAbstractListRandomAccess'],['java.util.AbstractList','.SubAbstractList']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AbstractList", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.util.AbstractCollection', 'java.util.List');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.modCount = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'add$I$TE', function (location, object) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, ['add$TE'], function (object) {
this.add$I$TE(this.size(), object);
return true;
});

Clazz.newMeth(C$, 'addAll$I$java_util_Collection', function (location, collection) {
var it = collection.iterator();
while (it.hasNext()){
this.add$I$TE(location++, it.next());
}
return !collection.isEmpty();
});

Clazz.newMeth(C$, 'clear', function () {
this.removeRange$I$I(0, this.size());
});

Clazz.newMeth(C$, 'equals$O', function (object) {
if (this === object ) {
return true;
}if (Clazz.instanceOf(object, "java.util.List")) {
var list = object;
if (list.size() != this.size()) {
return false;
}var it1 = this.iterator();
var it2 = list.iterator();
while (it1.hasNext()){
var e1 = it1.next();
var e2 = it2.next();
if (!(e1 == null  ? e2 == null  : e1.equals$O(e2))) {
return false;
}}
return true;
}return false;
});

Clazz.newMeth(C$, 'hashCode', function () {
var result = 1;
var it = this.iterator();
while (it.hasNext()){
var object = it.next();
result=(31 * result) + (object == null  ? 0 : object.hashCode());
}
return result;
});

Clazz.newMeth(C$, 'indexOf$O', function (object) {
var it = this.listIterator();
if (object != null ) {
while (it.hasNext()){
if (object.equals$O(it.next())) {
return it.previousIndex();
}}
} else {
while (it.hasNext()){
if (it.next() == null ) {
return it.previousIndex();
}}
}return -1;
});

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[2]||$incl$(2)), [this, null]);
});

Clazz.newMeth(C$, 'lastIndexOf$O', function (object) {
var it = this.listIterator$I(this.size());
if (object != null ) {
while (it.hasPrevious()){
if (object.equals$O(it.previous())) {
return it.nextIndex();
}}
} else {
while (it.hasPrevious()){
if (it.previous() == null ) {
return it.nextIndex();
}}
}return -1;
});

Clazz.newMeth(C$, 'listIterator', function () {
return this.listIterator$I(0);
});

Clazz.newMeth(C$, 'listIterator$I', function (location) {
return Clazz.new_((I$[3]||$incl$(3)).c$$I, [this, null, location]);
});

Clazz.newMeth(C$, 'remove$I', function (location) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'removeRange$I$I', function (start, end) {
var it = this.listIterator$I(start);
for (var i = start; i < end; i++) {
it.next();
it.remove();
}
});

Clazz.newMeth(C$, 'set$I$TE', function (location, object) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'subList$I$I', function (start, end) {
if (0 <= start && end <= this.size() ) {
if (start <= end) {
if (Clazz.instanceOf(this, "java.util.RandomAccess")) {
return Clazz.new_((I$[4]||$incl$(4)).c$$java_util_AbstractList$I$I,[this, start, end]);
}return Clazz.new_((I$[5]||$incl$(5)).c$$java_util_AbstractList$I$I,[this, start, end]);
}throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
});
;
(function(){var C$=Clazz.newClass(P$.AbstractList, "SimpleListIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.util.Iterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.pos = 0;
this.expectedModCount = 0;
this.lastPosition = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.pos = -1;
this.lastPosition = -1;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.expectedModCount=this.this$0.modCount;
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.pos + 1 < this.this$0.size();
});

Clazz.newMeth(C$, 'next', function () {
if (this.expectedModCount == this.this$0.modCount) {
try {
var result = this.this$0.get$I(this.pos + 1);
this.lastPosition=++this.pos;
return result;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.IndexOutOfBoundsException")){
throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
} else {
throw e;
}
}
}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$, 'remove', function () {
if (this.expectedModCount == this.this$0.modCount) {
try {
this.b$['java.util.AbstractList'].remove$I(this.lastPosition);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.IndexOutOfBoundsException")){
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
} else {
throw e;
}
}
if (this.this$0.modCount != this.expectedModCount) {
this.expectedModCount++;
}if (this.pos == this.lastPosition) {
this.pos--;
}this.lastPosition=-1;
} else {
throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
}});
})()
;
(function(){var C$=Clazz.newClass(P$.AbstractList, "FullListIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.AbstractList','java.util.AbstractList.SimpleListIterator'], 'java.util.ListIterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (start) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
if (0 <= start && start <= this.this$0.size() ) {
this.pos=start - 1;
} else {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}}, 1);

Clazz.newMeth(C$, ['add$TE'], function (object) {
if (this.expectedModCount == this.this$0.modCount) {
try {
this.b$['java.util.AbstractList'].add$I$TE(this.pos + 1, object);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.IndexOutOfBoundsException")){
throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
} else {
throw e;
}
}
this.pos++;
this.lastPosition=-1;
if (this.this$0.modCount != this.expectedModCount) {
this.expectedModCount++;
}} else {
throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
}});

Clazz.newMeth(C$, 'hasPrevious', function () {
return this.pos >= 0;
});

Clazz.newMeth(C$, 'nextIndex', function () {
return this.pos + 1;
});

Clazz.newMeth(C$, 'previous', function () {
if (this.expectedModCount == this.this$0.modCount) {
try {
var result = this.this$0.get$I(this.pos);
this.lastPosition=this.pos;
this.pos--;
return result;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.IndexOutOfBoundsException")){
throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
} else {
throw e;
}
}
}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$, 'previousIndex', function () {
return this.pos;
});

Clazz.newMeth(C$, ['set$TE'], function (object) {
if (this.expectedModCount == this.this$0.modCount) {
try {
this.b$['java.util.AbstractList'].set$I$TE(this.lastPosition, object);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.IndexOutOfBoundsException")){
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
} else {
throw e;
}
}
} else {
throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.AbstractList, "SubAbstractListRandomAccess", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.AbstractList','java.util.AbstractList.SubAbstractList'], 'java.util.RandomAccess');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_AbstractList$I$I', function (list, start, end) {
C$.superclazz.c$$java_util_AbstractList$I$I.apply(this, [list, start, end]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.AbstractList, "SubAbstractList", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.AbstractList');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.fullList = null;
this.offset = 0;
this.$size = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_AbstractList$I$I', function (list, start, end) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.fullList=list;
this.modCount=this.fullList.modCount;
this.offset=start;
this.$size=end - start;
}, 1);

Clazz.newMeth(C$, 'add$I$TE', function (location, object) {
if (this.modCount == this.fullList.modCount) {
if (0 <= location && location <= this.$size ) {
this.fullList.add$I$TE(location + this.offset, object);
this.$size++;
this.modCount=this.fullList.modCount;
} else {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}} else {
throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
}});

Clazz.newMeth(C$, 'addAll$I$java_util_Collection', function (location, collection) {
if (this.modCount == this.fullList.modCount) {
if (0 <= location && location <= this.$size ) {
var result = this.fullList.addAll$I$java_util_Collection(location + this.offset, collection);
if (result) {
this.$size+=collection.size();
this.modCount=this.fullList.modCount;
}return result;
}throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$, 'addAll$java_util_Collection', function (collection) {
if (this.modCount == this.fullList.modCount) {
var result = this.fullList.addAll$I$java_util_Collection(this.offset + this.$size, collection);
if (result) {
this.$size+=collection.size();
this.modCount=this.fullList.modCount;
}return result;
}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$, 'get$I', function (location) {
if (this.modCount == this.fullList.modCount) {
if (0 <= location && location < this.$size ) {
return this.fullList.get$I(location + this.offset);
}throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$, 'iterator', function () {
return this.listIterator$I(0);
});

Clazz.newMeth(C$, 'listIterator$I', function (location) {
if (this.modCount == this.fullList.modCount) {
if (0 <= location && location <= this.$size ) {
return Clazz.new_((I$[1]||$incl$(1)).c$$java_util_ListIterator$java_util_AbstractList_SubAbstractList$I$I,[this.fullList.listIterator$I(location + this.offset), this, this.offset, this.$size]);
}throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$, 'remove$I', function (location) {
if (this.modCount == this.fullList.modCount) {
if (0 <= location && location < this.$size ) {
var result = this.fullList.remove$I(location + this.offset);
this.$size--;
this.modCount=this.fullList.modCount;
return result;
}throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$, 'removeRange$I$I', function (start, end) {
if (start != end) {
if (this.modCount == this.fullList.modCount) {
this.fullList.removeRange$I$I(start + this.offset, end + this.offset);
this.$size-=end - start;
this.modCount=this.fullList.modCount;
} else {
throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
}}});

Clazz.newMeth(C$, 'set$I$TE', function (location, object) {
if (this.modCount == this.fullList.modCount) {
if (0 <= location && location < this.$size ) {
return this.fullList.set$I$TE(location + this.offset, object);
}throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$, 'size', function () {
return this.$size;
});

Clazz.newMeth(C$, 'sizeChanged$Z', function (increment) {
if (increment) {
this.$size++;
} else {
this.$size--;
}this.modCount=this.fullList.modCount;
});
;
(function(){var C$=Clazz.newClass(P$.AbstractList.SubAbstractList, "SubAbstractListIterator", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, 'java.util.ListIterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.subList = null;
this.iterator = null;
this.start = 0;
this.end = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_ListIterator$java_util_AbstractList_SubAbstractList$I$I', function (it, list, offset, length) {
C$.$init$.apply(this);
this.iterator=it;
this.subList=list;
this.start=offset;
this.end=this.start + length;
}, 1);

Clazz.newMeth(C$, ['add$TE'], function (object) {
this.iterator.add$TE(object);
this.subList.sizeChanged$Z(true);
this.end++;
});

Clazz.newMeth(C$, 'hasNext', function () {
return this.iterator.nextIndex() < this.end;
});

Clazz.newMeth(C$, 'hasPrevious', function () {
return this.iterator.previousIndex() >= this.start;
});

Clazz.newMeth(C$, 'next', function () {
if (this.iterator.nextIndex() < this.end) {
return this.iterator.next();
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$, 'nextIndex', function () {
return this.iterator.nextIndex() - this.start;
});

Clazz.newMeth(C$, 'previous', function () {
if (this.iterator.previousIndex() >= this.start) {
return this.iterator.previous();
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$, 'previousIndex', function () {
var previous = this.iterator.previousIndex();
if (previous >= this.start) {
return previous - this.start;
}return -1;
});

Clazz.newMeth(C$, 'remove', function () {
this.iterator.remove();
this.subList.sizeChanged$Z(false);
this.end--;
});

Clazz.newMeth(C$, ['set$TE'], function (object) {
this.iterator.set$TE(object);
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-24 08:45:44
