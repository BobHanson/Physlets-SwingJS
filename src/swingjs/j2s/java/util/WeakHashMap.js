(function(){var P$=java.util,I$=[['java.util.WeakHashMap',['java.util.WeakHashMap','.KeyIterator'],['java.util.WeakHashMap','.ValueIterator'],['java.util.WeakHashMap','.EntryIterator'],'java.util.ArrayList',['java.util.AbstractMap','.SimpleEntry'],'java.lang.ref.ReferenceQueue',['java.util.WeakHashMap','.Entry'],'java.util.HashMap','java.util.Arrays',['java.util.WeakHashMap','.KeySet'],['java.util.WeakHashMap','.Values'],['java.util.WeakHashMap','.EntrySet']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "WeakHashMap", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.util.AbstractMap', 'java.util.Map');
var p$=C$.prototype;
C$.NULL_KEY = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.NULL_KEY =  Clazz.new_();
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.table = null;
this.$size = 0;
this.threshold = 0;
this.loadFactor = 0;
this.queue = null;
this.modCount = 0;
this.$entrySet = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.queue = Clazz.new_((I$[7]||$incl$(7)));
this.$entrySet = null;
}, 1);

Clazz.newMeth(C$, ['newTable$I'], function (n) {
return Clazz.array((I$[8]||$incl$(8)), [n]);
});

Clazz.newMeth(C$, 'c$$I$F', function (initialCapacity, loadFactor) {
Clazz.super_(C$, this,1);
if (initialCapacity < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Illegal Initial Capacity: " + initialCapacity]);
if (initialCapacity > 1073741824) initialCapacity=1073741824;
if (loadFactor <= 0  || Float.isNaN(loadFactor) ) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Illegal Load factor: " + new Float(loadFactor).toString()]);
var capacity = 1;
while (capacity < initialCapacity)capacity<<=1;

this.table=p$.newTable$I.apply(this, [capacity]);
this.loadFactor=loadFactor;
this.threshold=((capacity * loadFactor)|0);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (initialCapacity) {
C$.c$$I$F.apply(this, [initialCapacity, 0.75]);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.loadFactor=0.75;
this.threshold=16;
this.table=p$.newTable$I.apply(this, [16]);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Map', function (m) {
C$.c$$I$F.apply(this, [Math.max(((m.size() / 0.75)|0) + 1, 16), 0.75]);
this.putAll$java_util_Map(m);
}, 1);

Clazz.newMeth(C$, 'maskNull$O', function (key) {
return (key == null ) ? C$.NULL_KEY : key;
}, 1);

Clazz.newMeth(C$, 'unmaskNull$O', function (key) {
return (key === C$.NULL_KEY ) ? null : key;
}, 1);

Clazz.newMeth(C$, 'eq$O$O', function (x, y) {
return x === y  || x.equals$O(y) ;
}, 1);

Clazz.newMeth(C$, 'indexFor$I$I', function (h, length) {
return h & (length - 1);
}, 1);

Clazz.newMeth(C$, 'expungeStaleEntries', function () {
for (var x; (x=this.queue.poll()) != null ; ) {
{
var e = x;
var i = C$.indexFor$I$I(e.hash, this.table.length);
var prev = this.table[i];
var p = prev;
while (p != null ){
var next = p.$next;
if (p === e ) {
if (prev === e ) this.table[i]=next;
 else prev.$next=next;
e.value=null;
this.$size--;
break;
}prev=p;
p=next;
}
}}
});

Clazz.newMeth(C$, 'getTable', function () {
p$.expungeStaleEntries.apply(this, []);
return this.table;
});

Clazz.newMeth(C$, 'size', function () {
if (this.$size == 0) return 0;
p$.expungeStaleEntries.apply(this, []);
return this.$size;
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.size() == 0;
});

Clazz.newMeth(C$, 'get$O', function (key) {
var k = C$.maskNull$O(key);
var h = (I$[9]||$incl$(9)).hash$I(k.hashCode());
var tab = p$.getTable.apply(this, []);
var index = C$.indexFor$I$I(h, tab.length);
var e = tab[index];
while (e != null ){
if (e.hash == h && C$.eq$O$O(k, e.get()) ) return e.value;
e=e.$next;
}
return null;
});

Clazz.newMeth(C$, 'containsKey$O', function (key) {
return this.getEntry$O(key) != null ;
});

Clazz.newMeth(C$, ['getEntry$O'], function (key) {
var k = C$.maskNull$O(key);
var h = (I$[9]||$incl$(9)).hash$I(k.hashCode());
var tab = p$.getTable.apply(this, []);
var index = C$.indexFor$I$I(h, tab.length);
var e = tab[index];
while (e != null  && !(e.hash == h && C$.eq$O$O(k, e.get()) ) )e=e.$next;

return e;
});

Clazz.newMeth(C$, ['put$TK$TV'], function (key, value) {
var k = C$.maskNull$O(key);
var h = (I$[9]||$incl$(9)).hash$I(k.hashCode());
var tab = p$.getTable.apply(this, []);
var i = C$.indexFor$I$I(h, tab.length);
for (var e = tab[i]; e != null ; e=e.$next) {
if (h == e.hash && C$.eq$O$O(k, e.get()) ) {
var oldValue = e.value;
if (value !== oldValue ) e.value=value;
return oldValue;
}}
this.modCount++;
var e = tab[i];
tab[i]=Clazz.new_((I$[8]||$incl$(8)).c$$O$TV$ref_ReferenceQueue$I$java_util_WeakHashMap_Entry,[k, value, this.queue, h, e]);
if (++this.$size >= this.threshold) this.resize$I(tab.length * 2);
return null;
});

Clazz.newMeth(C$, 'resize$I', function (newCapacity) {
var oldTable = p$.getTable.apply(this, []);
var oldCapacity = oldTable.length;
if (oldCapacity == 1073741824) {
this.threshold=2147483647;
return;
}var newTable = p$.newTable$I.apply(this, [newCapacity]);
p$.transfer$java_util_WeakHashMap_EntryA$java_util_WeakHashMap_EntryA.apply(this, [oldTable, newTable]);
this.table=newTable;
if (this.$size >= (this.threshold/2|0)) {
this.threshold=((newCapacity * this.loadFactor)|0);
} else {
p$.expungeStaleEntries.apply(this, []);
p$.transfer$java_util_WeakHashMap_EntryA$java_util_WeakHashMap_EntryA.apply(this, [newTable, oldTable]);
this.table=oldTable;
}});

Clazz.newMeth(C$, ['transfer$java_util_WeakHashMap_EntryA$java_util_WeakHashMap_EntryA'], function (src, dest) {
for (var j = 0; j < src.length; ++j) {
var e = src[j];
src[j]=null;
while (e != null ){
var next = e.$next;
var key = e.get();
if (key == null ) {
e.$next=null;
e.value=null;
this.$size--;
} else {
var i = C$.indexFor$I$I(e.hash, dest.length);
e.$next=dest[i];
dest[i]=e;
}e=next;
}
}
});

Clazz.newMeth(C$, 'putAll$java_util_Map', function (m) {
var numKeysToBeAdded = m.size();
if (numKeysToBeAdded == 0) return;
if (numKeysToBeAdded > this.threshold) {
var targetCapacity = ((numKeysToBeAdded / this.loadFactor + 1)|0);
if (targetCapacity > 1073741824) targetCapacity=1073741824;
var newCapacity = this.table.length;
while (newCapacity < targetCapacity)newCapacity<<=1;

if (newCapacity > this.table.length) this.resize$I(newCapacity);
}for (var e, $e = m.entrySet().iterator(); $e.hasNext()&&((e=$e.next()),1);) this.put$TK$TV(e.getKey(), e.getValue());

});

Clazz.newMeth(C$, 'remove$O', function (key) {
var k = C$.maskNull$O(key);
var h = (I$[9]||$incl$(9)).hash$I(k.hashCode());
var tab = p$.getTable.apply(this, []);
var i = C$.indexFor$I$I(h, tab.length);
var prev = tab[i];
var e = prev;
while (e != null ){
var next = e.$next;
if (h == e.hash && C$.eq$O$O(k, e.get()) ) {
this.modCount++;
this.$size--;
if (prev === e ) tab[i]=next;
 else prev.$next=next;
return e.value;
}prev=e;
e=next;
}
return null;
});

Clazz.newMeth(C$, 'removeMapping$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var tab = p$.getTable.apply(this, []);
var entry = o;
var k = C$.maskNull$O(entry.getKey());
var h = (I$[9]||$incl$(9)).hash$I(k.hashCode());
var i = C$.indexFor$I$I(h, tab.length);
var prev = tab[i];
var e = prev;
while (e != null ){
var next = e.$next;
if (h == e.hash && e.equals$O(entry) ) {
this.modCount++;
this.$size--;
if (prev === e ) tab[i]=next;
 else prev.$next=next;
return true;
}prev=e;
e=next;
}
return false;
});

Clazz.newMeth(C$, 'clear', function () {
while (this.queue.poll() != null );
this.modCount++;
(I$[10]||$incl$(10)).fill$OA$O(this.table, null);
this.$size=0;
while (this.queue.poll() != null );
});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
if (value == null ) return p$.containsNullValue.apply(this, []);
var tab = p$.getTable.apply(this, []);
for (var i = tab.length; i-- > 0; ) for (var e = tab[i]; e != null ; e=e.$next) if (value.equals$O(e.value)) return true;


return false;
});

Clazz.newMeth(C$, 'containsNullValue', function () {
var tab = p$.getTable.apply(this, []);
for (var i = tab.length; i-- > 0; ) for (var e = tab[i]; e != null ; e=e.$next) if (e.value == null ) return true;


return false;
});

Clazz.newMeth(C$, 'keySet', function () {
var ks = this.$keySet;
return (ks != null  ? ks : (this.$keySet=Clazz.new_((I$[11]||$incl$(11)), [this, null])));
});

Clazz.newMeth(C$, 'values', function () {
var vs = this.$values;
return (vs != null ) ? vs : (this.$values=Clazz.new_((I$[12]||$incl$(12)), [this, null]));
});

Clazz.newMeth(C$, 'entrySet', function () {
var es = this.$entrySet;
return es != null  ? es : (this.$entrySet=Clazz.new_((I$[13]||$incl$(13)), [this, null]));
});
;
(function(){var C$=Clazz.newClass(P$.WeakHashMap, "Entry", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.lang.ref.WeakReference', [['java.util.Map','java.util.Map.Entry']]);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.value = null;
this.hash = 0;
this.$next = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$O$TV$ref_ReferenceQueue$I$java_util_WeakHashMap_Entry'], function (key, value, queue, hash, next) {
C$.superclazz.c$$TT$ref_ReferenceQueue.apply(this, [key, queue]);
C$.$init$.apply(this);
this.value=value;
this.hash=hash;
this.$next=next;
}, 1);

Clazz.newMeth(C$, 'getKey', function () {
return (I$[1]||$incl$(1)).unmaskNull$O(this.get());
});

Clazz.newMeth(C$, 'getValue', function () {
return this.value;
});

Clazz.newMeth(C$, ['setValue$TV'], function (newValue) {
var oldValue = this.value;
this.value=newValue;
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
var k = this.getKey();
var v = this.getValue();
return ((k == null  ? 0 : k.hashCode()) ^ (v == null  ? 0 : v.hashCode()));
});

Clazz.newMeth(C$, 'toString', function () {
return this.getKey() + "=" + this.getValue() ;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.WeakHashMap, "HashIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.util.Iterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.index = 0;
this.entry = null;
this.lastReturned = null;
this.expectedModCount = 0;
this.nextKey = null;
this.currentKey = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.entry = null;
this.lastReturned = null;
this.expectedModCount = this.this$0.modCount;
this.nextKey = null;
this.currentKey = null;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.index=this.this$0.isEmpty() ? 0 : this.this$0.table.length;
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
var t = this.this$0.table;
while (this.nextKey == null ){
var e = this.entry;
var i = this.index;
while (e == null  && i > 0 )e=t[--i];

this.entry=e;
this.index=i;
if (e == null ) {
this.currentKey=null;
return false;
}this.nextKey=e.get();
if (this.nextKey == null ) this.entry=this.entry.$next;
}
return true;
});

Clazz.newMeth(C$, 'nextEntry', function () {
if (this.this$0.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
if (this.nextKey == null  && !this.hasNext() ) throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
this.lastReturned=this.entry;
this.entry=this.entry.$next;
this.currentKey=this.nextKey;
this.nextKey=null;
return this.lastReturned;
});

Clazz.newMeth(C$, 'remove', function () {
if (this.lastReturned == null ) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
if (this.this$0.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
this.b$['java.util.WeakHashMap'].remove$O(this.currentKey);
this.expectedModCount=this.this$0.modCount;
this.lastReturned=null;
this.currentKey=null;
});
})()
;
(function(){var C$=Clazz.newClass(P$.WeakHashMap, "ValueIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.WeakHashMap','java.util.WeakHashMap.HashIterator']);

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
(function(){var C$=Clazz.newClass(P$.WeakHashMap, "KeyIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.WeakHashMap','java.util.WeakHashMap.HashIterator']);

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
(function(){var C$=Clazz.newClass(P$.WeakHashMap, "EntryIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.WeakHashMap','java.util.WeakHashMap.HashIterator']);

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
(function(){var C$=Clazz.newClass(P$.WeakHashMap, "KeySet", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractSet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[2]||$incl$(2)), [this, null]);
});

Clazz.newMeth(C$, 'size', function () {
return this.b$['java.util.WeakHashMap'].size();
});

Clazz.newMeth(C$, 'contains$O', function (o) {
return this.this$0.containsKey$O(o);
});

Clazz.newMeth(C$, 'remove$O', function (o) {
if (this.this$0.containsKey$O(o)) {
this.b$['java.util.WeakHashMap'].remove$O(o);
return true;
} else return false;
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.WeakHashMap'].clear();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.WeakHashMap, "Values", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractCollection');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[3]||$incl$(3)), [this, null]);
});

Clazz.newMeth(C$, 'size', function () {
return this.b$['java.util.WeakHashMap'].size();
});

Clazz.newMeth(C$, 'contains$O', function (o) {
return this.this$0.containsValue$O(o);
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.WeakHashMap'].clear();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.WeakHashMap, "EntrySet", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractSet');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[4]||$incl$(4)), [this, null]);
});

Clazz.newMeth(C$, 'contains$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var e = o;
var candidate = this.this$0.getEntry$O(e.getKey());
return candidate != null  && candidate.equals$O(e) ;
});

Clazz.newMeth(C$, 'remove$O', function (o) {
return this.this$0.removeMapping$O(o);
});

Clazz.newMeth(C$, 'size', function () {
return this.b$['java.util.WeakHashMap'].size();
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.WeakHashMap'].clear();
});

Clazz.newMeth(C$, 'deepCopy', function () {
var list = Clazz.new_((I$[5]||$incl$(5)).c$$I,[this.size()]);
for (var e, $e = this.iterator(); $e.hasNext()&&((e=$e.next()),1);) list.add$TE(Clazz.new_((I$[6]||$incl$(6)).c$$java_util_Map_Entry,[e]));

return list;
});

Clazz.newMeth(C$, 'toArray', function () {
return p$.deepCopy.apply(this, []).toArray();
});

Clazz.newMeth(C$, 'toArray$TTA', function (a) {
return p$.deepCopy.apply(this, []).toArray$TTA(a);
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-24 08:45:50
