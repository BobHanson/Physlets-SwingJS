(function(){var P$=java.util,I$=[[['java.util.HashMap','.Entry'],['java.util.HashMap','.KeyIterator'],['java.util.HashMap','.ValueIterator'],['java.util.HashMap','.EntryIterator'],['java.util.HashMap','.KeySet'],['java.util.HashMap','.Values'],['java.util.HashMap','.EntrySet']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "HashMap", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.util.AbstractMap', ['java.util.Map', 'Cloneable', 'java.io.Serializable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.table = null;
this.$size = 0;
this.threshold = 0;
this.$loadFactor = 0;
this.modCount = 0;
this.$entrySet = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$entrySet = null;
}, 1);

Clazz.newMeth(C$, 'c$$I$F', function (initialCapacity, loadFactor) {
Clazz.super_(C$, this,1);
if (initialCapacity < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Illegal initial capacity: " + initialCapacity]);
if (initialCapacity > 1073741824) initialCapacity = 1073741824;
if (loadFactor <= 0  || Float.isNaN(loadFactor) ) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Illegal load factor: " + new Float(loadFactor).toString()]);
var capacity = 1;
while (capacity < initialCapacity)capacity = capacity<<(1);

this.$loadFactor = loadFactor;
this.threshold = ((capacity * loadFactor)|0);
this.table = Clazz.array((I$[1]||$incl$(1)), [capacity]);
this.init();
}, 1);

Clazz.newMeth(C$, 'c$$I', function (initialCapacity) {
C$.c$$I$F.apply(this, [initialCapacity, 0.75]);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.$loadFactor = 0.75;
this.threshold = 12;
this.table = Clazz.array((I$[1]||$incl$(1)), [16]);
this.init();
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Map', function (m) {
C$.c$$I$F.apply(this, [Math.max(((m.size() / 0.75)|0) + 1, 16), 0.75]);
p$.putAllForCreate$java_util_Map.apply(this, [m]);
}, 1);

Clazz.newMeth(C$, 'init', function () {
});

Clazz.newMeth(C$, 'hash$I', function (h) {
h = h^((h >>> 20) ^ (h >>> 12));
return h ^ (h >>> 7) ^ (h >>> 4) ;
}, 1);

Clazz.newMeth(C$, 'indexFor$I$I', function (h, length) {
return h & (length - 1);
}, 1);

Clazz.newMeth(C$, 'size', function () {
return this.$size;
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.$size == 0;
});

Clazz.newMeth(C$, 'get$O', function (key) {
if (key == null ) return p$.getForNullKey.apply(this, []);
var hash = C$.hash$I(key.hashCode());
for (var e = this.table[C$.indexFor$I$I(hash, this.table.length)]; e != null ; e = e.next) {
var k;
if (e.hash == hash && ((k = e.key) === key  || key.equals$O(k) ) ) return e.value;
}
return null;
});

Clazz.newMeth(C$, 'getForNullKey', function () {
for (var e = this.table[0]; e != null ; e = e.next) {
if (e.key == null ) return e.value;
}
return null;
});

Clazz.newMeth(C$, 'containsKey$O', function (key) {
return this.getEntry$O(key) != null ;
});

Clazz.newMeth(C$, ['getEntry$O'], function (key) {
var hash = (key == null ) ? 0 : C$.hash$I(key.hashCode());
for (var e = this.table[C$.indexFor$I$I(hash, this.table.length)]; e != null ; e = e.next) {
var k;
if (e.hash == hash && ((k = e.key) === key  || (key != null  && key.equals$O(k) ) ) ) return e;
}
return null;
});

Clazz.newMeth(C$, ['put$TK$TV'], function (key, value) {
if (key == null ) return p$.putForNullKey$TV.apply(this, [value]);
var hash = C$.hash$I(key.hashCode());
var i = C$.indexFor$I$I(hash, this.table.length);
for (var e = this.table[i]; e != null ; e = e.next) {
var k;
if (e.hash == hash && ((k = e.key) === key  || key.equals$O(k) ) ) {
var oldValue = e.value;
e.value = value;
e.recordAccess$java_util_HashMap(this);
return oldValue;
}}
this.modCount++;
this.addEntry$I$TK$TV$I(hash, key, value, i);
return null;
});

Clazz.newMeth(C$, ['putForNullKey$TV'], function (value) {
for (var e = this.table[0]; e != null ; e = e.next) {
if (e.key == null ) {
var oldValue = e.value;
e.value = value;
e.recordAccess$java_util_HashMap(this);
return oldValue;
}}
this.modCount++;
this.addEntry$I$TK$TV$I(0, null, value, 0);
return null;
});

Clazz.newMeth(C$, ['putForCreate$TK$TV'], function (key, value) {
var hash = (key == null ) ? 0 : C$.hash$I(key.hashCode());
var i = C$.indexFor$I$I(hash, this.table.length);
for (var e = this.table[i]; e != null ; e = e.next) {
var k;
if (e.hash == hash && ((k = e.key) === key  || (key != null  && key.equals$O(k) ) ) ) {
e.value = value;
return;
}}
this.createEntry$I$TK$TV$I(hash, key, value, i);
});

Clazz.newMeth(C$, 'putAllForCreate$java_util_Map', function (m) {
for (var i = m.entrySet().iterator(); i.hasNext(); ) {
var e = i.next();
p$.putForCreate$TK$TV.apply(this, [e.getKey(), e.getValue()]);
}
});

Clazz.newMeth(C$, 'resize$I', function (newCapacity) {
var oldTable = this.table;
var oldCapacity = oldTable.length;
if (oldCapacity == 1073741824) {
this.threshold = 2147483647;
return;
}var newTable = Clazz.array((I$[1]||$incl$(1)), [newCapacity]);
this.transfer$java_util_HashMap_EntryA(newTable);
this.table = newTable;
this.threshold = ((newCapacity * this.$loadFactor)|0);
});

Clazz.newMeth(C$, 'transfer$java_util_HashMap_EntryA', function (newTable) {
var src = this.table;
var newCapacity = newTable.length;
for (var j = 0; j < src.length; j++) {
var e = src[j];
if (e != null ) {
src[j] = null;
do {
var next = e.next;
var i = C$.indexFor$I$I(e.hash, newCapacity);
e.next = newTable[i];
newTable[i] = e;
e = next;
} while (e != null );
}}
});

Clazz.newMeth(C$, 'putAll$java_util_Map', function (m) {
var numKeysToBeAdded = m.size();
if (numKeysToBeAdded == 0) return;
if (numKeysToBeAdded > this.threshold) {
var targetCapacity = ((numKeysToBeAdded / this.$loadFactor + 1)|0);
if (targetCapacity > 1073741824) targetCapacity = 1073741824;
var newCapacity = this.table.length;
while (newCapacity < targetCapacity)newCapacity = newCapacity<<(1);

if (newCapacity > this.table.length) this.resize$I(newCapacity);
}for (var i = m.entrySet().iterator(); i.hasNext(); ) {
var e = i.next();
this.put$TK$TV(e.getKey(), e.getValue());
}
});

Clazz.newMeth(C$, 'remove$O', function (key) {
var e = this.removeEntryForKey$O(key);
return (e == null  ? null : e.value);
});

Clazz.newMeth(C$, ['removeEntryForKey$O'], function (key) {
var hash = (key == null ) ? 0 : C$.hash$I(key.hashCode());
var i = C$.indexFor$I$I(hash, this.table.length);
var prev = this.table[i];
var e = prev;
while (e != null ){
var next = e.next;
var k;
if (e.hash == hash && ((k = e.key) === key  || (key != null  && key.equals$O(k) ) ) ) {
this.modCount++;
this.$size--;
if (prev === e ) this.table[i] = next;
 else prev.next = next;
e.recordRemoval$java_util_HashMap(this);
return e;
}prev = e;
e = next;
}
return e;
});

Clazz.newMeth(C$, ['removeMapping$O'], function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return null;
var entry = o;
var key = entry.getKey();
var hash = (key == null ) ? 0 : C$.hash$I(key.hashCode());
var i = C$.indexFor$I$I(hash, this.table.length);
var prev = this.table[i];
var e = prev;
while (e != null ){
var next = e.next;
if (e.hash == hash && e.equals$O(entry) ) {
this.modCount++;
this.$size--;
if (prev === e ) this.table[i] = next;
 else prev.next = next;
e.recordRemoval$java_util_HashMap(this);
return e;
}prev = e;
e = next;
}
return e;
});

Clazz.newMeth(C$, 'clear', function () {
this.modCount++;
var tab = this.table;
for (var i = 0; i < tab.length; i++) tab[i] = null;

this.$size = 0;
});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
if (value == null ) return p$.containsNullValue.apply(this, []);
var tab = this.table;
for (var i = 0; i < tab.length; i++) for (var e = tab[i]; e != null ; e = e.next) if (value.equals$O(e.value)) return true;


return false;
});

Clazz.newMeth(C$, 'containsNullValue', function () {
var tab = this.table;
for (var i = 0; i < tab.length; i++) for (var e = tab[i]; e != null ; e = e.next) if (e.value == null ) return true;


return false;
});

Clazz.newMeth(C$, 'clone', function () {
var result = null;
try {
result = Clazz.clone(this);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
} else {
throw e;
}
}
result.table = Clazz.array((I$[1]||$incl$(1)), [this.table.length]);
result.$entrySet = null;
result.modCount = 0;
result.$size = 0;
result.init();
result.putAllForCreate$java_util_Map(this);
return result;
});

Clazz.newMeth(C$, ['addEntry$I$TK$TV$I'], function (hash, key, value, bucketIndex) {
var e = this.table[bucketIndex];
this.table[bucketIndex] = Clazz.new_((I$[1]||$incl$(1)).c$$I$TK$TV$java_util_HashMap_Entry,[hash, key, value, e]);
if (this.$size++ >= this.threshold) this.resize$I(2 * this.table.length);
});

Clazz.newMeth(C$, ['createEntry$I$TK$TV$I'], function (hash, key, value, bucketIndex) {
var e = this.table[bucketIndex];
this.table[bucketIndex] = Clazz.new_((I$[1]||$incl$(1)).c$$I$TK$TV$java_util_HashMap_Entry,[hash, key, value, e]);
this.$size++;
});

Clazz.newMeth(C$, 'newKeyIterator', function () {
return Clazz.new_((I$[2]||$incl$(2)), [this, null]);
});

Clazz.newMeth(C$, 'newValueIterator', function () {
return Clazz.new_((I$[3]||$incl$(3)), [this, null]);
});

Clazz.newMeth(C$, 'newEntryIterator', function () {
return Clazz.new_((I$[4]||$incl$(4)), [this, null]);
});

Clazz.newMeth(C$, 'keySet', function () {
var ks = this.$keySet;
return (ks != null  ? ks : (this.$keySet = Clazz.new_((I$[5]||$incl$(5)), [this, null])));
});

Clazz.newMeth(C$, 'values', function () {
var vs = this.$values;
return (vs != null  ? vs : (this.$values = Clazz.new_((I$[6]||$incl$(6)), [this, null])));
});

Clazz.newMeth(C$, 'entrySet', function () {
return p$.entrySet0.apply(this, []);
});

Clazz.newMeth(C$, 'entrySet0', function () {
var es = this.$entrySet;
return es != null  ? es : (this.$entrySet = Clazz.new_((I$[7]||$incl$(7)), [this, null]));
});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (s) {
var i = (this.$size > 0) ? p$.entrySet0.apply(this, []).iterator() : null;
s.defaultWriteObject();
s.writeInt$I(this.table.length);
s.writeInt$I(this.$size);
if (i != null ) {
while (i.hasNext()){
var e = i.next();
s.writeObject$O(e.getKey());
s.writeObject$O(e.getValue());
}
}});

Clazz.newMeth(C$, 'readObject$java_io_ObjectInputStream', function (s) {
s.defaultReadObject();
var numBuckets = s.readInt();
this.table = Clazz.array((I$[1]||$incl$(1)), [numBuckets]);
this.init();
var size = s.readInt();
for (var i = 0; i < size; i++) {
var key = s.readObject();
var value = s.readObject();
p$.putForCreate$TK$TV.apply(this, [key, value]);
}
});

Clazz.newMeth(C$, 'capacity', function () {
return this.table.length;
});

Clazz.newMeth(C$, 'loadFactor', function () {
return this.$loadFactor;
});
;
(function(){var C$=Clazz.newClass(P$.HashMap, "Entry", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, [['java.util.Map','java.util.Map.Entry']]);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.key = null;
this.value = null;
this.next = null;
this.hash = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$I$TK$TV$java_util_HashMap_Entry'], function (h, k, v, n) {
C$.$init$.apply(this);
this.value = v;
this.next = n;
this.key = k;
this.hash = h;
}, 1);

Clazz.newMeth(C$, 'getKey', function () {
return this.key;
});

Clazz.newMeth(C$, 'getValue', function () {
return this.value;
});

Clazz.newMeth(C$, ['setValue$TV'], function (newValue) {
var oldValue = this.value;
this.value = newValue;
return oldValue;
});

Clazz.newMeth(C$, 'equals$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var e = o;
var k1 = this.getKey();
var k2 = e.getKey();
if (k1 === k2  || (k1 != null  && k1.equals$O(k2) ) ) {
var v1 = this.getValue();
var v2 = e.getValue();
if (v1 === v2  || (v1 != null  && v1.equals$O(v2) ) ) return true;
}return false;
});

Clazz.newMeth(C$, 'hashCode', function () {
return (this.key == null  ? 0 : this.key.hashCode()) ^ (this.value == null  ? 0 : this.value.hashCode());
});

Clazz.newMeth(C$, 'toString', function () {
return this.getKey() + "=" + this.getValue() ;
});

Clazz.newMeth(C$, ['recordAccess$java_util_HashMap'], function (m) {
});

Clazz.newMeth(C$, ['recordRemoval$java_util_HashMap'], function (m) {
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.HashMap, "HashIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.util.Iterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$next = null;
this.expectedModCount = 0;
this.index = 0;
this.current = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.expectedModCount = this.this$0.modCount;
if (this.this$0.$size > 0) {
var t = this.this$0.table;
while (this.index < t.length && (this.$next = t[this.index++]) == null  );
}}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.$next != null ;
});

Clazz.newMeth(C$, 'nextEntry', function () {
if (this.this$0.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
var e = this.$next;
if (e == null ) throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
if ((this.$next = e.next) == null ) {
var t = this.this$0.table;
while (this.index < t.length && (this.$next = t[this.index++]) == null  );
}this.current = e;
return e;
});

Clazz.newMeth(C$, 'remove', function () {
if (this.current == null ) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
if (this.this$0.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
var k = this.current.key;
this.current = null;
this.b$['java.util.HashMap'].removeEntryForKey$O(k);
this.expectedModCount = this.this$0.modCount;
});
})()
;
(function(){var C$=Clazz.newClass(P$.HashMap, "ValueIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.HashMap','java.util.HashMap.HashIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.nextEntry().value;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.HashMap, "KeyIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.HashMap','java.util.HashMap.HashIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.nextEntry().getKey();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.HashMap, "EntryIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.HashMap','java.util.HashMap.HashIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.nextEntry();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.HashMap, "KeySet", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractSet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return this.this$0.newKeyIterator();
});

Clazz.newMeth(C$, 'size', function () {
return this.this$0.$size;
});

Clazz.newMeth(C$, 'contains$O', function (o) {
return this.this$0.containsKey$O(o);
});

Clazz.newMeth(C$, 'remove$O', function (o) {
return this.b$['java.util.HashMap'].removeEntryForKey$O(o) != null ;
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.HashMap'].clear();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.HashMap, "Values", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractCollection');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return this.this$0.newValueIterator();
});

Clazz.newMeth(C$, 'size', function () {
return this.this$0.$size;
});

Clazz.newMeth(C$, 'contains$O', function (o) {
return this.this$0.containsValue$O(o);
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.HashMap'].clear();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.HashMap, "EntrySet", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractSet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return this.this$0.newEntryIterator();
});

Clazz.newMeth(C$, 'contains$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var e = o;
var candidate = this.this$0.getEntry$O(e.getKey());
return candidate != null  && candidate.equals$O(e) ;
});

Clazz.newMeth(C$, 'remove$O', function (o) {
return this.this$0.removeMapping$O(o) != null ;
});

Clazz.newMeth(C$, 'size', function () {
return this.this$0.$size;
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.HashMap'].clear();
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-15 01:02:12
