(function(){var P$=java.util,I$=[['java.util.Hashtable$1',['java.util.Hashtable','.Entry'],'java.util.Arrays',['java.util.Hashtable','.HashEnumerator'],['java.util.Collections','.SynchronizedSet'],['java.util.Hashtable','.HashIterator'],'java.util.Hashtable$2$1','java.util.AbstractSet','java.util.Hashtable$3$1','java.lang.StringBuilder',['java.util.Collections','.SynchronizedCollection'],'java.util.Hashtable$4$1','java.util.AbstractCollection']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Hashtable", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.util.Dictionary', ['java.util.Map', 'Cloneable', 'java.io.Serializable']);
C$.EMPTY_ENUMERATION = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.EMPTY_ENUMERATION = ((
(function(){var C$=Clazz.newClass(P$, "Hashtable$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Enumeration', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'hasMoreElements', function () {
return false;
});

Clazz.newMeth(C$, 'nextElement', function () {
throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});
})()
), Clazz.new_((I$[1]||$incl$(1)).$init$, [this, null]));
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.elementCount = 0;
this.elementData = null;
this.loadFactor = 0;
this.threshold = 0;
this.firstSlot = 0;
this.lastSlot = 0;
this.modCount = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.lastSlot = -1;
}, 1);

Clazz.newMeth(C$, ['newEntry$TK$TV$I'], function (key, value, hash) {
return Clazz.new_((I$[2]||$incl$(2)).c$$TK$TV,[key, value]);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$I.apply(this, [11]);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (capacity) {
Clazz.super_(C$, this,1);
if (capacity >= 0) {
this.elementCount = 0;
this.elementData = p$.newElementArray$I.apply(this, [capacity == 0 ? 1 : capacity]);
this.firstSlot = this.elementData.length;
this.loadFactor = 0.75;
p$.computeMaxSize.apply(this, []);
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}}, 1);

Clazz.newMeth(C$, 'c$$I$F', function (capacity, loadFactor) {
Clazz.super_(C$, this,1);
if (capacity >= 0 && loadFactor > 0  ) {
this.elementCount = 0;
this.firstSlot = capacity;
this.elementData = p$.newElementArray$I.apply(this, [capacity == 0 ? 1 : capacity]);
this.loadFactor = loadFactor;
p$.computeMaxSize.apply(this, []);
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}}, 1);

Clazz.newMeth(C$, 'c$$java_util_Map', function (map) {
C$.c$$I.apply(this, [map.size() < 6 ? 11 : ((map.size() * 4/3|0)) + 11]);
this.putAll$java_util_Map(map);
}, 1);

Clazz.newMeth(C$, ['newElementArray$I'], function (size) {
return Clazz.array((I$[2]||$incl$(2)), [size]);
});

Clazz.newMeth(C$, 'clear', function () {
this.elementCount = 0;
(I$[3]||$incl$(3)).fill$OA$O(this.elementData, null);
this.modCount++;
});

Clazz.newMeth(C$, 'clone', function () {
try {
var hashtable = Clazz.clone(this);
hashtable.elementData = this.elementData.clone();
var entry;
for (var i = this.elementData.length; --i >= 0; ) {
if ((entry = this.elementData[i]) != null ) {
hashtable.elementData[i] = entry.clone();
}}
return hashtable;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
return null;
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'computeMaxSize', function () {
this.threshold = ((this.elementData.length * this.loadFactor)|0);
});

Clazz.newMeth(C$, 'contains$O', function (value) {
if (value == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}for (var i = this.elementData.length; --i >= 0; ) {
var entry = this.elementData[i];
while (entry != null ){
if (value.equals$O(entry.value)) {
return true;
}entry = entry.next;
}
}
return false;
});

Clazz.newMeth(C$, 'containsKey$O', function (key) {
return this.getEntry$O(key) != null ;
});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
return this.contains$O(value);
});

Clazz.newMeth(C$, 'elements', function () {
if (this.elementCount == 0) {
return C$.EMPTY_ENUMERATION;
}return Clazz.new_((I$[4]||$incl$(4)).c$$Z, [this, null, false]);
});

Clazz.newMeth(C$, 'entrySet', function () {
return Clazz.new_((I$[5]||$incl$(5)).c$$java_util_Set$O,[((
(function(){var C$=Clazz.newClass(P$, "Hashtable$2", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.util.AbstractSet'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'size', function () {
return this.b$['java.util.Hashtable'].elementCount;
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.Hashtable'].clear();
});

Clazz.newMeth(C$, 'remove$O', function (object) {
if (this.contains$O(object)) {
this.b$['java.util.Hashtable'].remove$O((object).getKey());
return true;
}return false;
});

Clazz.newMeth(C$, 'contains$O', function (object) {
var entry = this.b$['java.util.Hashtable'].getEntry$O((object).getKey());
return object.equals$O(entry);
});

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[6]||$incl$(6)).c$$java_util_MapEntry_Type, [this, null, ((
(function(){var C$=Clazz.newClass(P$, "Hashtable$2$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, [['java.util.MapEntry','java.util.MapEntry.Type']], 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['get$java_util_MapEntry'], function (entry) {
return entry;
});
})()
), Clazz.new_((I$[7]||$incl$(7)).$init$, [this, null]))]);
});
})()
), Clazz.new_((I$[8]||$incl$(8)), [this, null],P$.Hashtable$2)), this]);
});

Clazz.newMeth(C$, 'equals$O', function (object) {
if (this === object ) {
return true;
}if (Clazz.instanceOf(object, "java.util.Map")) {
var map = object;
if (this.size() != map.size()) {
return false;
}var entries = this.entrySet();
for (var e, $e = map.entrySet().iterator(); $e.hasNext()&&((e=$e.next()),1);) {
if (!entries.contains$O(e)) {
return false;
}}
return true;
}return false;
});

Clazz.newMeth(C$, 'get$O', function (key) {
var hash = key.hashCode();
var index = (hash & 2147483647) % this.elementData.length;
var entry = this.elementData[index];
while (entry != null ){
if (entry.equalsKey$O$I(key, hash)) {
return entry.value;
}entry = entry.next;
}
return null;
});

Clazz.newMeth(C$, ['getEntry$O'], function (key) {
var hash = key.hashCode();
var index = (hash & 2147483647) % this.elementData.length;
var entry = this.elementData[index];
while (entry != null ){
if (entry.equalsKey$O$I(key, hash)) {
return entry;
}entry = entry.next;
}
return null;
});

Clazz.newMeth(C$, 'hashCode', function () {
var result = 0;
var it = this.entrySet().iterator();
while (it.hasNext()){
var entry = it.next();
var key = entry.getKey();
var value = entry.getValue();
var hash = (key !== this  ? key.hashCode() : 0) ^ (value !== this  ? (value != null  ? value.hashCode() : 0) : 0);
result = result+(hash);
}
return result;
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.elementCount == 0;
});

Clazz.newMeth(C$, 'keys', function () {
if (this.elementCount == 0) {
return C$.EMPTY_ENUMERATION;
}return Clazz.new_((I$[4]||$incl$(4)).c$$Z, [this, null, true]);
});

Clazz.newMeth(C$, 'keySet', function () {
return Clazz.new_((I$[5]||$incl$(5)).c$$java_util_Set$O,[((
(function(){var C$=Clazz.newClass(P$, "Hashtable$3", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.util.AbstractSet'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'contains$O', function (object) {
return this.b$['java.util.Hashtable'].containsKey$O(object);
});

Clazz.newMeth(C$, 'size', function () {
return this.b$['java.util.Hashtable'].elementCount;
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.Hashtable'].clear();
});

Clazz.newMeth(C$, 'remove$O', function (key) {
if (this.b$['java.util.Hashtable'].containsKey$O(key)) {
this.b$['java.util.Hashtable'].remove$O(key);
return true;
}return false;
});

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[6]||$incl$(6)).c$$java_util_MapEntry_Type, [this, null, ((
(function(){var C$=Clazz.newClass(P$, "Hashtable$3$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, [['java.util.MapEntry','java.util.MapEntry.Type']], 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['get$java_util_MapEntry'], function (entry) {
return entry.key;
});
})()
), Clazz.new_((I$[9]||$incl$(9)).$init$, [this, null]))]);
});
})()
), Clazz.new_((I$[8]||$incl$(8)), [this, null],P$.Hashtable$3)), this]);
});

Clazz.newMeth(C$, ['put$TK$TV'], function (key, value) {
if (key != null  && value != null  ) {
var hash = key.hashCode();
var index = (hash & 2147483647) % this.elementData.length;
var entry = this.elementData[index];
while (entry != null  && !entry.equalsKey$O$I(key, hash) ){
entry = entry.next;
}
if (entry == null ) {
this.modCount++;
if (++this.elementCount > this.threshold) {
this.rehash();
index = (hash & 2147483647) % this.elementData.length;
}if (index < this.firstSlot) {
this.firstSlot = index;
}if (index > this.lastSlot) {
this.lastSlot = index;
}entry = C$.newEntry$TK$TV$I(key, value, hash);
entry.next = this.elementData[index];
this.elementData[index] = entry;
return null;
}var result = entry.value;
entry.value = value;
return result;
}throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
});

Clazz.newMeth(C$, 'putAll$java_util_Map', function (map) {
for (var entry, $entry = map.entrySet().iterator(); $entry.hasNext()&&((entry=$entry.next()),1);) {
this.put$TK$TV(entry.getKey(), entry.getValue());
}
});

Clazz.newMeth(C$, 'rehash', function () {
var length = (this.elementData.length << 1) + 1;
if (length == 0) {
length = 1;
}var newFirst = length;
var newLast = -1;
var newData = p$.newElementArray$I.apply(this, [length]);
for (var i = this.lastSlot + 1; --i >= this.firstSlot; ) {
var entry = this.elementData[i];
while (entry != null ){
var index = (entry.getKeyHash() & 2147483647) % length;
if (index < newFirst) {
newFirst = index;
}if (index > newLast) {
newLast = index;
}var next = entry.next;
entry.next = newData[index];
newData[index] = entry;
entry = next;
}
}
this.firstSlot = newFirst;
this.lastSlot = newLast;
this.elementData = newData;
p$.computeMaxSize.apply(this, []);
});

Clazz.newMeth(C$, 'remove$O', function (key) {
var hash = key.hashCode();
var index = (hash & 2147483647) % this.elementData.length;
var last = null;
var entry = this.elementData[index];
while (entry != null  && !entry.equalsKey$O$I(key, hash) ){
last = entry;
entry = entry.next;
}
if (entry != null ) {
this.modCount++;
if (last == null ) {
this.elementData[index] = entry.next;
} else {
last.next = entry.next;
}this.elementCount--;
var result = entry.value;
entry.value = null;
return result;
}return null;
});

Clazz.newMeth(C$, 'size', function () {
return this.elementCount;
});

Clazz.newMeth(C$, 'toString', function () {
if (this.isEmpty()) {
return "{}";
}var buffer = Clazz.new_((I$[10]||$incl$(10)).c$$I,[this.size() * 28]);
buffer.append$C("{");
for (var i = this.lastSlot; i >= this.firstSlot; i--) {
var entry = this.elementData[i];
while (entry != null ){
if (entry.key !== this ) {
buffer.append$O(entry.key);
} else {
buffer.append$S("(this Map)");
}buffer.append$C("=");
if (entry.value !== this ) {
buffer.append$O(entry.value);
} else {
buffer.append$S("(this Map)");
}buffer.append$S(", ");
entry = entry.next;
}
}
if (this.elementCount > 0) {
buffer.setLength$I(buffer.length$() - 2);
}buffer.append$C("}");
return buffer.toString();
});

Clazz.newMeth(C$, 'values', function () {
return Clazz.new_((I$[11]||$incl$(11)).c$$java_util_Collection$O,[((
(function(){var C$=Clazz.newClass(P$, "Hashtable$4", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.util.AbstractCollection'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'contains$O', function (object) {
return this.b$['java.util.Hashtable'].contains$O(object);
});

Clazz.newMeth(C$, 'size', function () {
return this.b$['java.util.Hashtable'].elementCount;
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.Hashtable'].clear();
});

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[6]||$incl$(6)).c$$java_util_MapEntry_Type, [this, null, ((
(function(){var C$=Clazz.newClass(P$, "Hashtable$4$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, [['java.util.MapEntry','java.util.MapEntry.Type']], 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['get$java_util_MapEntry'], function (entry) {
return entry.value;
});
})()
), Clazz.new_((I$[12]||$incl$(12)).$init$, [this, null]))]);
});
})()
), Clazz.new_((I$[13]||$incl$(13)), [this, null],P$.Hashtable$4)), this]);
});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (stream) {
stream.defaultWriteObject();
stream.writeInt$I(this.elementData.length);
stream.writeInt$I(this.elementCount);
for (var i = this.elementData.length; --i >= 0; ) {
var entry = this.elementData[i];
while (entry != null ){
stream.writeObject$O(entry.key);
stream.writeObject$O(entry.value);
entry = entry.next;
}
}
});

Clazz.newMeth(C$, 'readObject$java_io_ObjectInputStream', function (stream) {
stream.defaultReadObject();
var length = stream.readInt();
this.elementData = p$.newElementArray$I.apply(this, [length]);
this.elementCount = stream.readInt();
for (var i = this.elementCount; --i >= 0; ) {
var key = stream.readObject();
var hash = key.hashCode();
var index = (hash & 2147483647) % length;
if (index < this.firstSlot) {
this.firstSlot = index;
}if (index > this.lastSlot) {
this.lastSlot = index;
}var entry = C$.newEntry$TK$TV$I(key, stream.readObject(), hash);
entry.next = this.elementData[index];
this.elementData[index] = entry;
}
});
;
(function(){var C$=Clazz.newClass(P$.Hashtable, "Entry", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.MapEntry');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.next = null;
this.hashcode = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$TK$TV'], function (theKey, theValue) {
C$.superclazz.c$$TK$TV.apply(this, [theKey, theValue]);
C$.$init$.apply(this);
this.hashcode = theKey.hashCode();
}, 1);

Clazz.newMeth(C$, 'clone', function () {
var entry = Clazz.clone(this);
if (this.next != null ) {
entry.next = this.next.clone();
}return entry;
});

Clazz.newMeth(C$, ['setValue$TV'], function (object) {
if (object == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}var result = this.value;
this.value = object;
return result;
});

Clazz.newMeth(C$, 'getKeyHash', function () {
return this.key.hashCode();
});

Clazz.newMeth(C$, 'equalsKey$O$I', function (aKey, hash) {
return this.hashcode == aKey.hashCode() && this.key.equals$O(aKey) ;
});

Clazz.newMeth(C$, 'toString', function () {
return this.key + "=" + this.value ;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Hashtable, "HashIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.util.Iterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.position = 0;
this.expectedModCount = 0;
this.type = null;
this.lastEntry = null;
this.lastPosition = 0;
this.canRemove = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.canRemove = false;
}, 1);

Clazz.newMeth(C$, ['c$$java_util_MapEntry_Type'], function (value) {
C$.$init$.apply(this);
this.type = value;
this.position = this.this$0.lastSlot;
this.expectedModCount = this.this$0.modCount;
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
if (this.lastEntry != null  && this.lastEntry.next != null  ) {
return true;
}while (this.position >= this.this$0.firstSlot){
if (this.this$0.elementData[this.position] == null ) {
this.position--;
} else {
return true;
}}
return false;
});

Clazz.newMeth(C$, 'next', function () {
if (this.expectedModCount == this.this$0.modCount) {
if (this.lastEntry != null ) {
this.lastEntry = this.lastEntry.next;
}if (this.lastEntry == null ) {
while (this.position >= this.this$0.firstSlot && (this.lastEntry = this.this$0.elementData[this.position]) == null  ){
this.position--;
}
if (this.lastEntry != null ) {
this.lastPosition = this.position;
this.position--;
}}if (this.lastEntry != null ) {
this.canRemove = true;
return this.type.get$java_util_MapEntry(this.lastEntry);
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$, 'remove', function () {
if (this.expectedModCount == this.this$0.modCount) {
if (this.canRemove) {
this.canRemove = false;
{
var removed = false;
var entry = this.this$0.elementData[this.lastPosition];
if (entry === this.lastEntry ) {
this.this$0.elementData[this.lastPosition] = entry.next;
removed = true;
} else {
while (entry != null  && entry.next !== this.lastEntry  ){
entry = entry.next;
}
if (entry != null ) {
entry.next = this.lastEntry.next;
removed = true;
}}if (removed) {
this.this$0.modCount++;
this.this$0.elementCount--;
this.expectedModCount++;
return;
}}} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
}}throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Hashtable, "HashEnumerator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.util.Enumeration');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.key = false;
this.start = 0;
this.entry = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$Z', function (isKey) {
C$.$init$.apply(this);
this.key = isKey;
this.start = this.this$0.lastSlot + 1;
}, 1);

Clazz.newMeth(C$, 'hasMoreElements', function () {
if (this.entry != null ) {
return true;
}while (--this.start >= this.this$0.firstSlot){
if (this.this$0.elementData[this.start] != null ) {
this.entry = this.this$0.elementData[this.start];
return true;
}}
return false;
});

Clazz.newMeth(C$, 'nextElement', function () {
if (this.hasMoreElements()) {
var result = this.key ? this.entry.key : this.entry.value;
this.entry = this.entry.next;
return result;
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-08 10:02:13
