(function(){var P$=java.util,I$=[[['java.util.IdentityHashMap','.KeyIterator'],['java.util.IdentityHashMap','.ValueIterator'],['java.util.IdentityHashMap','.EntryIterator'],['java.util.AbstractMap','.SimpleEntry'],'java.lang.reflect.Array','java.lang.InternalError',['java.util.IdentityHashMap','.KeySet'],['java.util.IdentityHashMap','.Values'],['java.util.IdentityHashMap','.EntrySet']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "IdentityHashMap", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.util.AbstractMap', ['java.util.Map', 'java.io.Serializable', 'Cloneable']);
C$.NULL_KEY = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.NULL_KEY =  Clazz.new_();
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.table = null;
this.$size = 0;
this.modCount = 0;
this.threshold = 0;
this.$entrySet = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$entrySet = null;
}, 1);

Clazz.newMeth(C$, 'maskNull$O', function (key) {
return (key == null  ? C$.NULL_KEY : key);
}, 1);

Clazz.newMeth(C$, 'unmaskNull$O', function (key) {
return (key === C$.NULL_KEY  ? null : key);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
p$.init$I.apply(this, [32]);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (expectedMaxSize) {
Clazz.super_(C$, this,1);
if (expectedMaxSize < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["expectedMaxSize is negative: " + expectedMaxSize]);
p$.init$I.apply(this, [p$.capacity$I.apply(this, [expectedMaxSize])]);
}, 1);

Clazz.newMeth(C$, 'capacity$I', function (expectedMaxSize) {
var minCapacity = ((3 * expectedMaxSize)/2|0);
var result;
if (minCapacity > 536870912 || minCapacity < 0 ) {
result = 536870912;
} else {
result = 4;
while (result < minCapacity)result = result<<(1);

}return result;
});

Clazz.newMeth(C$, 'init$I', function (initCapacity) {
this.threshold = ((initCapacity * 2)/3|0);
this.table = Clazz.array(java.lang.Object, [2 * initCapacity]);
});

Clazz.newMeth(C$, 'c$$java_util_Map', function (m) {
C$.c$$I.apply(this, [(((1 + m.size()) * 1.1)|0)]);
this.putAll$java_util_Map(m);
}, 1);

Clazz.newMeth(C$, 'size', function () {
return this.$size;
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.$size == 0;
});

Clazz.newMeth(C$, 'hash$O$I', function (x, length) {
var h = System.identityHashCode(x);
return ((h << 1) - (h << 8)) & (length - 1);
}, 1);

Clazz.newMeth(C$, 'nextKeyIndex$I$I', function (i, len) {
return (i + 2 < len ? i + 2 : 0);
}, 1);

Clazz.newMeth(C$, 'get$O', function (key) {
var k = C$.maskNull$O(key);
var tab = this.table;
var len = tab.length;
var i = C$.hash$O$I(k, len);
while (true){
var item = tab[i];
if (item === k ) return tab[i + 1];
if (item == null ) return null;
i = C$.nextKeyIndex$I$I(i, len);
}
});

Clazz.newMeth(C$, 'containsKey$O', function (key) {
var k = C$.maskNull$O(key);
var tab = this.table;
var len = tab.length;
var i = C$.hash$O$I(k, len);
while (true){
var item = tab[i];
if (item === k ) return true;
if (item == null ) return false;
i = C$.nextKeyIndex$I$I(i, len);
}
});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
var tab = this.table;
for (var i = 1; i < tab.length; i = i+(2)) if (tab[i] === value  && tab[i - 1] != null  ) return true;

return false;
});

Clazz.newMeth(C$, 'containsMapping$O$O', function (key, value) {
var k = C$.maskNull$O(key);
var tab = this.table;
var len = tab.length;
var i = C$.hash$O$I(k, len);
while (true){
var item = tab[i];
if (item === k ) return tab[i + 1] === value ;
if (item == null ) return false;
i = C$.nextKeyIndex$I$I(i, len);
}
});

Clazz.newMeth(C$, ['put$TK$TV'], function (key, value) {
var k = C$.maskNull$O(key);
var tab = this.table;
var len = tab.length;
var i = C$.hash$O$I(k, len);
var item;
while ((item = tab[i]) != null ){
if (item === k ) {
var oldValue = tab[i + 1];
tab[i + 1] = value;
return oldValue;
}i = C$.nextKeyIndex$I$I(i, len);
}
this.modCount++;
tab[i] = k;
tab[i + 1] = value;
if (++this.$size >= this.threshold) p$.resize$I.apply(this, [len]);
return null;
});

Clazz.newMeth(C$, 'resize$I', function (newCapacity) {
var newLength = newCapacity * 2;
var oldTable = this.table;
var oldLength = oldTable.length;
if (oldLength == 1073741824) {
if (this.threshold == 536870911) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Capacity exhausted."]);
this.threshold = 536870911;
return;
}if (oldLength >= newLength) return;
var newTable = Clazz.array(java.lang.Object, [newLength]);
this.threshold = (newLength/3|0);
for (var j = 0; j < oldLength; j = j+(2)) {
var key = oldTable[j];
if (key != null ) {
var value = oldTable[j + 1];
oldTable[j] = null;
oldTable[j + 1] = null;
var i = C$.hash$O$I(key, newLength);
while (newTable[i] != null )i = C$.nextKeyIndex$I$I(i, newLength);

newTable[i] = key;
newTable[i + 1] = value;
}}
this.table = newTable;
});

Clazz.newMeth(C$, 'putAll$java_util_Map', function (m) {
var n = m.size();
if (n == 0) return;
if (n > this.threshold) p$.resize$I.apply(this, [p$.capacity$I.apply(this, [n])]);
for (var e, $e = m.entrySet().iterator(); $e.hasNext()&&((e=$e.next()),1);) this.put$TK$TV(e.getKey(), e.getValue());

});

Clazz.newMeth(C$, 'remove$O', function (key) {
var k = C$.maskNull$O(key);
var tab = this.table;
var len = tab.length;
var i = C$.hash$O$I(k, len);
while (true){
var item = tab[i];
if (item === k ) {
this.modCount++;
this.$size--;
var oldValue = tab[i + 1];
tab[i + 1] = null;
tab[i] = null;
p$.closeDeletion$I.apply(this, [i]);
return oldValue;
}if (item == null ) return null;
i = C$.nextKeyIndex$I$I(i, len);
}
});

Clazz.newMeth(C$, 'removeMapping$O$O', function (key, value) {
var k = C$.maskNull$O(key);
var tab = this.table;
var len = tab.length;
var i = C$.hash$O$I(k, len);
while (true){
var item = tab[i];
if (item === k ) {
if (tab[i + 1] !== value ) return false;
this.modCount++;
this.$size--;
tab[i] = null;
tab[i + 1] = null;
p$.closeDeletion$I.apply(this, [i]);
return true;
}if (item == null ) return false;
i = C$.nextKeyIndex$I$I(i, len);
}
});

Clazz.newMeth(C$, 'closeDeletion$I', function (d) {
var tab = this.table;
var len = tab.length;
var item;
for (var i = C$.nextKeyIndex$I$I(d, len); (item = tab[i]) != null ; i = C$.nextKeyIndex$I$I(i, len)) {
var r = C$.hash$O$I(item, len);
if ((i < r && (r <= d || d <= i ) ) || (r <= d && d <= i ) ) {
tab[d] = item;
tab[d + 1] = tab[i + 1];
tab[i] = null;
tab[i + 1] = null;
d = i;
}}
});

Clazz.newMeth(C$, 'clear', function () {
this.modCount++;
var tab = this.table;
for (var i = 0; i < tab.length; i++) tab[i] = null;

this.$size = 0;
});

Clazz.newMeth(C$, 'equals$O', function (o) {
if (o === this ) {
return true;
} else if (Clazz.instanceOf(o, "java.util.IdentityHashMap")) {
var m = o;
if (m.size() != this.$size) return false;
var tab = m.table;
for (var i = 0; i < tab.length; i = i+(2)) {
var k = tab[i];
if (k != null  && !p$.containsMapping$O$O.apply(this, [k, tab[i + 1]]) ) return false;
}
return true;
} else if (Clazz.instanceOf(o, "java.util.Map")) {
var m = o;
return this.entrySet().equals$O(m.entrySet());
} else {
return false;
}});

Clazz.newMeth(C$, 'hashCode', function () {
var result = 0;
var tab = this.table;
for (var i = 0; i < tab.length; i = i+(2)) {
var key = tab[i];
if (key != null ) {
var k = C$.unmaskNull$O(key);
result = result+(System.identityHashCode(k) ^ System.identityHashCode(tab[i + 1]));
}}
return result;
});

Clazz.newMeth(C$, 'clone', function () {
try {
var m = Clazz.clone(this);
m.$entrySet = null;
m.table = this.table.clone();
return m;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
throw Clazz.new_((I$[6]||$incl$(6)));
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'keySet', function () {
var ks = this.$keySet;
if (ks != null ) return ks;
 else return this.$keySet = Clazz.new_((I$[7]||$incl$(7)), [this, null]);
});

Clazz.newMeth(C$, 'values', function () {
var vs = this.$values;
if (vs != null ) return vs;
 else return this.$values = Clazz.new_((I$[8]||$incl$(8)), [this, null]);
});

Clazz.newMeth(C$, 'entrySet', function () {
var es = this.$entrySet;
if (es != null ) return es;
 else return this.$entrySet = Clazz.new_((I$[9]||$incl$(9)), [this, null]);
});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (s) {
s.defaultWriteObject();
s.writeInt$I(this.$size);
var tab = this.table;
for (var i = 0; i < tab.length; i = i+(2)) {
var key = tab[i];
if (key != null ) {
s.writeObject$O(C$.unmaskNull$O(key));
s.writeObject$O(tab[i + 1]);
}}
});

Clazz.newMeth(C$, 'readObject$java_io_ObjectInputStream', function (s) {
s.defaultReadObject();
var size = s.readInt();
p$.init$I.apply(this, [p$.capacity$I.apply(this, [((size * 4)/3|0)])]);
for (var i = 0; i < size; i++) {
var key = s.readObject();
var value = s.readObject();
p$.putForCreate$TK$TV.apply(this, [key, value]);
}
});

Clazz.newMeth(C$, ['putForCreate$TK$TV'], function (key, value) {
var k = C$.maskNull$O(key);
var tab = this.table;
var len = tab.length;
var i = C$.hash$O$I(k, len);
var item;
while ((item = tab[i]) != null ){
if (item === k ) throw Clazz.new_(Clazz.load('java.io.StreamCorruptedException'));
i = C$.nextKeyIndex$I$I(i, len);
}
tab[i] = k;
tab[i + 1] = value;
});
;
(function(){var C$=Clazz.newClass(P$.IdentityHashMap, "IdentityHashMapIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.util.Iterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.index = 0;
this.expectedModCount = 0;
this.lastReturnedIndex = 0;
this.indexValid = false;
this.traversalTable = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.index = (this.this$0.$size != 0 ? 0 : this.this$0.table.length);
this.expectedModCount = this.this$0.modCount;
this.lastReturnedIndex = -1;
this.traversalTable = this.this$0.table;
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
var tab = this.traversalTable;
for (var i = this.index; i < tab.length; i = i+(2)) {
var key = tab[i];
if (key != null ) {
this.index = i;
return this.indexValid = true;
}}
this.index = tab.length;
return false;
});

Clazz.newMeth(C$, 'nextIndex', function () {
if (this.this$0.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
if (!this.indexValid && !this.hasNext() ) throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
this.indexValid = false;
this.lastReturnedIndex = this.index;
this.index = this.index+(2);
return this.lastReturnedIndex;
});

Clazz.newMeth(C$, 'remove', function () {
if (this.lastReturnedIndex == -1) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
if (this.this$0.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
this.expectedModCount = ++this.this$0.modCount;
var deletedSlot = this.lastReturnedIndex;
this.lastReturnedIndex = -1;
this.this$0.$size--;
this.index = deletedSlot;
this.indexValid = false;
var tab = this.traversalTable;
var len = tab.length;
var d = deletedSlot;
var key = tab[d];
tab[d] = null;
tab[d + 1] = null;
if (tab !== this.b$['java.util.IdentityHashMap'].table ) {
this.b$['java.util.IdentityHashMap'].remove$O(key);
this.expectedModCount = this.this$0.modCount;
return;
}var item;
for (var i = P$.IdentityHashMap.nextKeyIndex$I$I(d, len); (item = tab[i]) != null ; i = P$.IdentityHashMap.nextKeyIndex$I$I(i, len)) {
var r = P$.IdentityHashMap.hash$O$I(item, len);
if ((i < r && (r <= d || d <= i ) ) || (r <= d && d <= i ) ) {
if (i < deletedSlot && d >= deletedSlot  && this.traversalTable === this.b$['java.util.IdentityHashMap'].table  ) {
var remaining = len - deletedSlot;
var newTable = Clazz.array(java.lang.Object, [remaining]);
System.arraycopy(tab, deletedSlot, newTable, 0, remaining);
this.traversalTable = newTable;
this.index = 0;
}tab[d] = item;
tab[d + 1] = tab[i + 1];
tab[i] = null;
tab[i + 1] = null;
d = i;
}}
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.IdentityHashMap, "KeyIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.IdentityHashMap','java.util.IdentityHashMap.IdentityHashMapIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'next', function () {
return P$.IdentityHashMap.unmaskNull$O(this.traversalTable[this.nextIndex()]);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.IdentityHashMap, "ValueIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.IdentityHashMap','java.util.IdentityHashMap.IdentityHashMapIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.traversalTable[this.nextIndex() + 1];
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.IdentityHashMap, "EntryIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.IdentityHashMap','java.util.IdentityHashMap.IdentityHashMapIterator'], [['java.util.Map','java.util.Map.Entry']]);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'next', function () {
this.nextIndex();
return this;
});

Clazz.newMeth(C$, 'getKey', function () {
if (this.lastReturnedIndex < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Entry was removed"]);
return P$.IdentityHashMap.unmaskNull$O(this.traversalTable[this.lastReturnedIndex]);
});

Clazz.newMeth(C$, 'getValue', function () {
if (this.lastReturnedIndex < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Entry was removed"]);
return this.traversalTable[this.lastReturnedIndex + 1];
});

Clazz.newMeth(C$, ['setValue$TV'], function (value) {
if (this.lastReturnedIndex < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Entry was removed"]);
var oldValue = this.traversalTable[this.lastReturnedIndex + 1];
this.traversalTable[this.lastReturnedIndex + 1] = value;
if (this.traversalTable !== this.b$['java.util.IdentityHashMap'].table ) this.this$0.put$TK$TV(this.traversalTable[this.lastReturnedIndex], value);
return oldValue;
});

Clazz.newMeth(C$, 'equals$O', function (o) {
if (this.lastReturnedIndex < 0) return C$.superclazz.prototype.equals$O.apply(this, [o]);
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var e = o;
return e.getKey() === this.getKey()  && e.getValue() === this.getValue()  ;
});

Clazz.newMeth(C$, 'hashCode', function () {
if (this.lastReturnedIndex < 0) return C$.superclazz.prototype.hashCode.apply(this, []);
return System.identityHashCode(this.getKey()) ^ System.identityHashCode(this.getValue());
});

Clazz.newMeth(C$, 'toString', function () {
if (this.lastReturnedIndex < 0) return C$.superclazz.prototype.toString.apply(this, []);
return this.getKey() + "=" + this.getValue() ;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.IdentityHashMap, "KeySet", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractSet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[1]||$incl$(1)), [this, null]);
});

Clazz.newMeth(C$, 'size', function () {
return this.this$0.$size;
});

Clazz.newMeth(C$, 'contains$O', function (o) {
return this.this$0.containsKey$O(o);
});

Clazz.newMeth(C$, 'remove$O', function (o) {
var oldSize = this.this$0.$size;
this.b$['java.util.IdentityHashMap'].remove$O(o);
return this.this$0.$size != oldSize;
});

Clazz.newMeth(C$, 'removeAll$java_util_Collection', function (c) {
var modified = false;
for (var i = this.iterator(); i.hasNext(); ) {
if (c.contains$O(i.next())) {
i.remove();
modified = true;
}}
return modified;
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.IdentityHashMap'].clear();
});

Clazz.newMeth(C$, 'hashCode', function () {
var result = 0;
for (var key, $key = this.iterator(); $key.hasNext()&&((key=$key.next()),1);) result = result+(System.identityHashCode(key));

return result;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.IdentityHashMap, "Values", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractCollection');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[2]||$incl$(2)), [this, null]);
});

Clazz.newMeth(C$, 'size', function () {
return this.this$0.$size;
});

Clazz.newMeth(C$, 'contains$O', function (o) {
return this.this$0.containsValue$O(o);
});

Clazz.newMeth(C$, 'remove$O', function (o) {
for (var i = this.iterator(); i.hasNext(); ) {
if (i.next() === o ) {
i.remove();
return true;
}}
return false;
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.IdentityHashMap'].clear();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.IdentityHashMap, "EntrySet", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractSet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[3]||$incl$(3)), [this, null]);
});

Clazz.newMeth(C$, 'contains$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var entry = o;
return this.this$0.containsMapping$O$O.apply(this.this$0, [entry.getKey(), entry.getValue()]);
});

Clazz.newMeth(C$, 'remove$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var entry = o;
return this.this$0.removeMapping$O$O.apply(this.this$0, [entry.getKey(), entry.getValue()]);
});

Clazz.newMeth(C$, 'size', function () {
return this.this$0.$size;
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.IdentityHashMap'].clear();
});

Clazz.newMeth(C$, 'removeAll$java_util_Collection', function (c) {
var modified = false;
for (var i = this.iterator(); i.hasNext(); ) {
if (c.contains$O(i.next())) {
i.remove();
modified = true;
}}
return modified;
});

Clazz.newMeth(C$, 'toArray', function () {
var size = this.size();
var result = Clazz.array(java.lang.Object, [size]);
var it = this.iterator();
for (var i = 0; i < size; i++) result[i] = Clazz.new_((I$[4]||$incl$(4)).c$$java_util_Map_Entry,[it.next()]);

return result;
});

Clazz.newMeth(C$, 'toArray$TTA', function (a) {
var size = this.size();
if (a.length < size) a = Clazz.array(a.getClass().getComponentType(), size);
var it = this.iterator();
for (var i = 0; i < size; i++) a[i] = Clazz.new_((I$[4]||$incl$(4)).c$$java_util_Map_Entry,[it.next()]);

if (a.length > size) a[size] = null;
return a;
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-06 08:58:50
