(function(){var P$=java.util,I$=[[['java.util.LinkedHashMap','.Entry'],['java.util.LinkedHashMap','.KeyIterator'],['java.util.LinkedHashMap','.ValueIterator'],['java.util.LinkedHashMap','.EntryIterator']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "LinkedHashMap", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.util.HashMap', 'java.util.Map');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.header = null;
this.accessOrder = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$F', function (initialCapacity, loadFactor) {
C$.superclazz.c$$I$F.apply(this, [initialCapacity, loadFactor]);
C$.$init$.apply(this);
this.accessOrder = false;
this.init();
}, 1);

Clazz.newMeth(C$, 'c$$I', function (initialCapacity) {
C$.superclazz.c$$I.apply(this, [initialCapacity]);
C$.$init$.apply(this);
this.accessOrder = false;
this.init();
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.accessOrder = false;
this.init();
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Map', function (m) {
C$.superclazz.c$$java_util_Map.apply(this, [m]);
C$.$init$.apply(this);
this.accessOrder = false;
this.init();
}, 1);

Clazz.newMeth(C$, 'c$$I$F$Z', function (initialCapacity, loadFactor, accessOrder) {
C$.superclazz.c$$I$F.apply(this, [initialCapacity, loadFactor]);
C$.$init$.apply(this);
this.accessOrder = accessOrder;
this.init();
}, 1);

Clazz.newMeth(C$, 'init', function () {
this.header = Clazz.new_((I$[1]||$incl$(1)).c$$I$TK$TV$java_util_HashMap_Entry,[-1, null, null, null]);
this.header.before = this.header.after = this.header;
});

Clazz.newMeth(C$, 'transfer$java_util_HashMap_EntryA', function (newTable) {
var newCapacity = newTable.length;
for (var e = this.header.after; e !== this.header ; e = e.after) {
var index = P$.HashMap.indexFor$I$I(e.hash, newCapacity);
e.next = newTable[index];
newTable[index] = e;
}
});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
if (value == null ) {
for (var e = this.header.after; e !== this.header ; e = e.after) if (e.value == null ) return true;

} else {
for (var e = this.header.after; e !== this.header ; e = e.after) if (value.equals$O(e.value)) return true;

}return false;
});

Clazz.newMeth(C$, 'get$O', function (key) {
var e = this.getEntry$O(key);
if (e == null ) return null;
e.recordAccess$java_util_HashMap(this);
return e.value;
});

Clazz.newMeth(C$, 'clear', function () {
C$.superclazz.prototype.clear.apply(this, []);
this.header.before = this.header.after = this.header;
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

Clazz.newMeth(C$, ['addEntry$I$TK$TV$I'], function (hash, key, value, bucketIndex) {
this.createEntry$I$TK$TV$I(hash, key, value, bucketIndex);
var eldest = this.header.after;
if (this.removeEldestEntry$java_util_Map_Entry(eldest)) {
this.removeEntryForKey$O(eldest.key);
} else {
if (this.$size >= this.threshold) this.resize$I(2 * this.table.length);
}});

Clazz.newMeth(C$, ['createEntry$I$TK$TV$I'], function (hash, key, value, bucketIndex) {
var old = this.table[bucketIndex];
var e = Clazz.new_((I$[1]||$incl$(1)).c$$I$TK$TV$java_util_HashMap_Entry,[hash, key, value, old]);
this.table[bucketIndex] = e;
e.addBefore$java_util_LinkedHashMap_Entry(this.header);
this.$size++;
});

Clazz.newMeth(C$, ['removeEldestEntry$java_util_Map_Entry'], function (eldest) {
return false;
});
;
(function(){var C$=Clazz.newClass(P$.LinkedHashMap, "Entry", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.HashMap','java.util.HashMap.Entry']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.before = null;
this.after = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$I$TK$TV$java_util_HashMap_Entry'], function (hash, key, value, next) {
C$.superclazz.c$$I$TK$TV$java_util_HashMap_Entry.apply(this, [hash, key, value, next]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'remove', function () {
this.before.after = this.after;
this.after.before = this.before;
});

Clazz.newMeth(C$, ['addBefore$java_util_LinkedHashMap_Entry'], function (existingEntry) {
this.after = existingEntry;
this.before = existingEntry.before;
this.before.after = this;
this.after.before = this;
});

Clazz.newMeth(C$, ['recordAccess$java_util_HashMap'], function (m) {
var lm = m;
if (lm.accessOrder) {
lm.modCount++;
p$.remove.apply(this, []);
p$.addBefore$java_util_LinkedHashMap_Entry.apply(this, [lm.header]);
}});

Clazz.newMeth(C$, ['recordRemoval$java_util_HashMap'], function (m) {
p$.remove.apply(this, []);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.LinkedHashMap, "LinkedHashIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.util.Iterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$nextEntry = null;
this.lastReturned = null;
this.expectedModCount = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$nextEntry = this.this$0.header.after;
this.lastReturned = null;
this.expectedModCount = this.this$0.modCount;
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.$nextEntry !== this.this$0.header ;
});

Clazz.newMeth(C$, 'remove', function () {
if (this.lastReturned == null ) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
if (this.this$0.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
this.b$['java.util.LinkedHashMap'].remove$O(this.lastReturned.key);
this.lastReturned = null;
this.expectedModCount = this.this$0.modCount;
});

Clazz.newMeth(C$, 'nextEntry', function () {
if (this.this$0.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
if (this.$nextEntry === this.this$0.header ) throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
var e = this.lastReturned = this.$nextEntry;
this.$nextEntry = e.after;
return e;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.LinkedHashMap, "KeyIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.LinkedHashMap','java.util.LinkedHashMap.LinkedHashIterator']);

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
(function(){var C$=Clazz.newClass(P$.LinkedHashMap, "ValueIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.LinkedHashMap','java.util.LinkedHashMap.LinkedHashIterator']);

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
(function(){var C$=Clazz.newClass(P$.LinkedHashMap, "EntryIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.LinkedHashMap','java.util.LinkedHashMap.LinkedHashIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.nextEntry();
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-06 08:58:51
