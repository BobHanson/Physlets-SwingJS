(function(){var P$=java.util,I$=[[['java.util.TreeMap','.ValueIterator'],['java.util.TreeMap','.EntryIterator'],'java.util.TreeMap',['java.util.TreeMap','.KeySet'],['java.util.TreeMap','.NavigableSubMap','.SubMapEntryIterator'],['java.util.TreeMap','.DescendingSubMap'],['java.util.TreeMap','.NavigableSubMap','.SubMapKeyIterator'],['java.util.TreeMap','.NavigableSubMap','.DescendingSubMapKeyIterator'],['java.util.TreeMap','.AscendingSubMap','.AscendingEntrySetView'],['java.util.TreeMap','.NavigableSubMap','.DescendingSubMapEntryIterator'],'java.util.Collections',['java.util.TreeMap','.AscendingSubMap'],['java.util.TreeMap','.DescendingSubMap','.DescendingEntrySetView'],['java.util.TreeMap','.Entry'],'java.lang.InternalError',['java.util.TreeMap','.Values'],['java.util.TreeMap','.EntrySet'],['java.util.TreeMap','.KeyIterator'],['java.util.TreeMap','.DescendingKeyIterator'],['java.util.AbstractMap','.SimpleImmutableEntry']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "TreeMap", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.util.AbstractMap', ['java.util.NavigableMap', 'Cloneable', 'java.io.Serializable']);
C$.UNBOUNDED = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.UNBOUNDED =  Clazz.new_();
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$comparator = null;
this.root = null;
this.$size = 0;
this.modCount = 0;
this.$entrySet = null;
this.$navigableKeySet = null;
this.$descendingMap = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.root = null;
this.$size = 0;
this.modCount = 0;
this.$entrySet = null;
this.$navigableKeySet = null;
this.$descendingMap = null;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.$comparator = null;
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Comparator', function (comparator) {
Clazz.super_(C$, this,1);
this.$comparator = comparator;
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Map', function (m) {
Clazz.super_(C$, this,1);
this.$comparator = null;
this.putAll$java_util_Map(m);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_SortedMap', function (m) {
Clazz.super_(C$, this,1);
this.$comparator = m.comparator();
try {
p$.buildFromSorted$I$java_util_Iterator$java_io_ObjectInputStream$TV.apply(this, [m.size(), m.entrySet().iterator(), null, null]);
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var cannotHappen = e$$;
{
}
} else if (Clazz.exceptionOf(e$$, "java.lang.ClassNotFoundException")){
var cannotHappen = e$$;
{
}
} else {
throw e$$;
}
}
}, 1);

Clazz.newMeth(C$, 'size', function () {
return this.$size;
});

Clazz.newMeth(C$, 'containsKey$O', function (key) {
return this.getEntry$O(key) != null ;
});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
for (var e = this.getFirstEntry(); e != null ; e = C$.successor$java_util_TreeMap_Entry(e)) if (C$.valEquals$O$O(value, e.value)) return true;

return false;
});

Clazz.newMeth(C$, 'get$O', function (key) {
var p = this.getEntry$O(key);
return (p == null  ? null : p.value);
});

Clazz.newMeth(C$, 'comparator', function () {
return this.$comparator;
});

Clazz.newMeth(C$, 'firstKey', function () {
return C$.key$java_util_TreeMap_Entry(this.getFirstEntry());
});

Clazz.newMeth(C$, 'lastKey', function () {
return C$.key$java_util_TreeMap_Entry(this.getLastEntry());
});

Clazz.newMeth(C$, 'putAll$java_util_Map', function (map) {
var mapSize = map.size();
if (this.$size == 0 && mapSize != 0  && Clazz.instanceOf(map, "java.util.SortedMap") ) {
var c = (map).comparator();
if (c === this.$comparator  || (c != null  && c.equals$O(this.$comparator) ) ) {
++this.modCount;
try {
p$.buildFromSorted$I$java_util_Iterator$java_io_ObjectInputStream$TV.apply(this, [mapSize, map.entrySet().iterator(), null, null]);
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var cannotHappen = e$$;
{
}
} else if (Clazz.exceptionOf(e$$, "java.lang.ClassNotFoundException")){
var cannotHappen = e$$;
{
}
} else {
throw e$$;
}
}
return;
}}C$.superclazz.prototype.putAll$java_util_Map.apply(this, [map]);
});

Clazz.newMeth(C$, ['getEntry$O'], function (key) {
if (this.$comparator != null ) return this.getEntryUsingComparator$O(key);
if (key == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
var k = key;
var p = this.root;
while (p != null ){
var cmp = k.compareTo$TT(p.key);
if (cmp < 0) p = p.left;
 else if (cmp > 0) p = p.right;
 else return p;
}
return null;
});

Clazz.newMeth(C$, ['getEntryUsingComparator$O'], function (key) {
var k = key;
var cpr = this.$comparator;
if (cpr != null ) {
var p = this.root;
while (p != null ){
var cmp = cpr.compare$TT$TT(k, p.key);
if (cmp < 0) p = p.left;
 else if (cmp > 0) p = p.right;
 else return p;
}
}return null;
});

Clazz.newMeth(C$, ['getCeilingEntry$TK'], function (key) {
var p = this.root;
while (p != null ){
var cmp = this.compare$O$O(key, p.key);
if (cmp < 0) {
if (p.left != null ) p = p.left;
 else return p;
} else if (cmp > 0) {
if (p.right != null ) {
p = p.right;
} else {
var parent = p.parent;
var ch = p;
while (parent != null  && ch === parent.right  ){
ch = parent;
parent = parent.parent;
}
return parent;
}} else return p;
}
return null;
});

Clazz.newMeth(C$, ['getFloorEntry$TK'], function (key) {
var p = this.root;
while (p != null ){
var cmp = this.compare$O$O(key, p.key);
if (cmp > 0) {
if (p.right != null ) p = p.right;
 else return p;
} else if (cmp < 0) {
if (p.left != null ) {
p = p.left;
} else {
var parent = p.parent;
var ch = p;
while (parent != null  && ch === parent.left  ){
ch = parent;
parent = parent.parent;
}
return parent;
}} else return p;
}
return null;
});

Clazz.newMeth(C$, ['getHigherEntry$TK'], function (key) {
var p = this.root;
while (p != null ){
var cmp = this.compare$O$O(key, p.key);
if (cmp < 0) {
if (p.left != null ) p = p.left;
 else return p;
} else {
if (p.right != null ) {
p = p.right;
} else {
var parent = p.parent;
var ch = p;
while (parent != null  && ch === parent.right  ){
ch = parent;
parent = parent.parent;
}
return parent;
}}}
return null;
});

Clazz.newMeth(C$, ['getLowerEntry$TK'], function (key) {
var p = this.root;
while (p != null ){
var cmp = this.compare$O$O(key, p.key);
if (cmp > 0) {
if (p.right != null ) p = p.right;
 else return p;
} else {
if (p.left != null ) {
p = p.left;
} else {
var parent = p.parent;
var ch = p;
while (parent != null  && ch === parent.left  ){
ch = parent;
parent = parent.parent;
}
return parent;
}}}
return null;
});

Clazz.newMeth(C$, ['put$TK$TV'], function (key, value) {
var t = this.root;
if (t == null ) {
this.root = Clazz.new_((I$[14]||$incl$(14)).c$$TK$TV$java_util_TreeMap_Entry,[key, value, null]);
this.$size = 1;
this.modCount++;
return null;
}var cmp;
var parent;
var cpr = this.$comparator;
if (cpr != null ) {
do {
parent = t;
cmp = cpr.compare$TT$TT(key, t.key);
if (cmp < 0) t = t.left;
 else if (cmp > 0) t = t.right;
 else return t.setValue$TV(value);
} while (t != null );
} else {
if (key == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
var k = key;
do {
parent = t;
cmp = k.compareTo$TT(t.key);
if (cmp < 0) t = t.left;
 else if (cmp > 0) t = t.right;
 else return t.setValue$TV(value);
} while (t != null );
}var e = Clazz.new_((I$[14]||$incl$(14)).c$$TK$TV$java_util_TreeMap_Entry,[key, value, parent]);
if (cmp < 0) parent.left = e;
 else parent.right = e;
p$.fixAfterInsertion$java_util_TreeMap_Entry.apply(this, [e]);
this.$size++;
this.modCount++;
return null;
});

Clazz.newMeth(C$, 'remove$O', function (key) {
var p = this.getEntry$O(key);
if (p == null ) return null;
var oldValue = p.value;
p$.deleteEntry$java_util_TreeMap_Entry.apply(this, [p]);
return oldValue;
});

Clazz.newMeth(C$, 'clear', function () {
this.modCount++;
this.$size = 0;
this.root = null;
});

Clazz.newMeth(C$, 'clone', function () {
var clone = null;
try {
clone = Clazz.clone(this);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
throw Clazz.new_((I$[15]||$incl$(15)));
} else {
throw e;
}
}
clone.root = null;
clone.$size = 0;
clone.modCount = 0;
clone.$entrySet = null;
clone.$navigableKeySet = null;
clone.$descendingMap = null;
try {
clone.buildFromSorted$I$java_util_Iterator$java_io_ObjectInputStream$TV(this.$size, this.entrySet().iterator(), null, null);
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var cannotHappen = e$$;
{
}
} else if (Clazz.exceptionOf(e$$, "java.lang.ClassNotFoundException")){
var cannotHappen = e$$;
{
}
} else {
throw e$$;
}
}
return clone;
});

Clazz.newMeth(C$, 'firstEntry', function () {
return C$.exportEntry$java_util_TreeMap_Entry(this.getFirstEntry());
});

Clazz.newMeth(C$, 'lastEntry', function () {
return C$.exportEntry$java_util_TreeMap_Entry(this.getLastEntry());
});

Clazz.newMeth(C$, 'pollFirstEntry', function () {
var p = this.getFirstEntry();
var result = C$.exportEntry$java_util_TreeMap_Entry(p);
if (p != null ) p$.deleteEntry$java_util_TreeMap_Entry.apply(this, [p]);
return result;
});

Clazz.newMeth(C$, 'pollLastEntry', function () {
var p = this.getLastEntry();
var result = C$.exportEntry$java_util_TreeMap_Entry(p);
if (p != null ) p$.deleteEntry$java_util_TreeMap_Entry.apply(this, [p]);
return result;
});

Clazz.newMeth(C$, ['lowerEntry$TK'], function (key) {
return C$.exportEntry$java_util_TreeMap_Entry(this.getLowerEntry$TK(key));
});

Clazz.newMeth(C$, ['lowerKey$TK'], function (key) {
return C$.keyOrNull$java_util_TreeMap_Entry(this.getLowerEntry$TK(key));
});

Clazz.newMeth(C$, ['floorEntry$TK'], function (key) {
return C$.exportEntry$java_util_TreeMap_Entry(this.getFloorEntry$TK(key));
});

Clazz.newMeth(C$, ['floorKey$TK'], function (key) {
return C$.keyOrNull$java_util_TreeMap_Entry(this.getFloorEntry$TK(key));
});

Clazz.newMeth(C$, ['ceilingEntry$TK'], function (key) {
return C$.exportEntry$java_util_TreeMap_Entry(this.getCeilingEntry$TK(key));
});

Clazz.newMeth(C$, ['ceilingKey$TK'], function (key) {
return C$.keyOrNull$java_util_TreeMap_Entry(this.getCeilingEntry$TK(key));
});

Clazz.newMeth(C$, ['higherEntry$TK'], function (key) {
return C$.exportEntry$java_util_TreeMap_Entry(this.getHigherEntry$TK(key));
});

Clazz.newMeth(C$, ['higherKey$TK'], function (key) {
return C$.keyOrNull$java_util_TreeMap_Entry(this.getHigherEntry$TK(key));
});

Clazz.newMeth(C$, 'keySet', function () {
return this.navigableKeySet();
});

Clazz.newMeth(C$, 'navigableKeySet', function () {
var nks = this.$navigableKeySet;
return (nks != null ) ? nks : (this.$navigableKeySet = Clazz.new_((I$[4]||$incl$(4)).c$$java_util_NavigableMap,[this]));
});

Clazz.newMeth(C$, 'descendingKeySet', function () {
return this.descendingMap().navigableKeySet();
});

Clazz.newMeth(C$, 'values', function () {
var vs = this.$values;
return (vs != null ) ? vs : (this.$values = Clazz.new_((I$[16]||$incl$(16)), [this, null]));
});

Clazz.newMeth(C$, 'entrySet', function () {
var es = this.$entrySet;
return (es != null ) ? es : (this.$entrySet = Clazz.new_((I$[17]||$incl$(17)), [this, null]));
});

Clazz.newMeth(C$, 'descendingMap', function () {
var km = this.$descendingMap;
return (km != null ) ? km : (this.$descendingMap = Clazz.new_((I$[6]||$incl$(6)).c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this, true, null, true, true, null, true]));
});

Clazz.newMeth(C$, ['subMap$TK$Z$TK$Z'], function (fromKey, fromInclusive, toKey, toInclusive) {
return Clazz.new_((I$[12]||$incl$(12)).c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this, false, fromKey, fromInclusive, false, toKey, toInclusive]);
});

Clazz.newMeth(C$, ['headMap$TK$Z'], function (toKey, inclusive) {
return Clazz.new_((I$[12]||$incl$(12)).c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this, true, null, true, false, toKey, inclusive]);
});

Clazz.newMeth(C$, ['tailMap$TK$Z'], function (fromKey, inclusive) {
return Clazz.new_((I$[12]||$incl$(12)).c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this, false, fromKey, inclusive, true, null, true]);
});

Clazz.newMeth(C$, ['subMap$TK$TK'], function (fromKey, toKey) {
return this.subMap$TK$Z$TK$Z(fromKey, true, toKey, false);
});

Clazz.newMeth(C$, ['headMap$TK'], function (toKey) {
return this.headMap$TK$Z(toKey, false);
});

Clazz.newMeth(C$, ['tailMap$TK'], function (fromKey) {
return this.tailMap$TK$Z(fromKey, true);
});

Clazz.newMeth(C$, 'keyIterator', function () {
return Clazz.new_((I$[18]||$incl$(18)).c$$java_util_TreeMap_Entry, [this, null, this.getFirstEntry()]);
});

Clazz.newMeth(C$, 'descendingKeyIterator', function () {
return Clazz.new_((I$[19]||$incl$(19)).c$$java_util_TreeMap_Entry, [this, null, this.getLastEntry()]);
});

Clazz.newMeth(C$, 'compare$O$O', function (k1, k2) {
return this.$comparator == null  ? (k1).compareTo$TT(k2) : this.$comparator.compare$TT$TT(k1, k2);
});

Clazz.newMeth(C$, 'valEquals$O$O', function (o1, o2) {
return (o1 == null  ? o2 == null  : o1.equals$O(o2));
}, 1);

Clazz.newMeth(C$, ['exportEntry$java_util_TreeMap_Entry'], function (e) {
return e == null  ? null : Clazz.new_((I$[20]||$incl$(20)).c$$java_util_Map_Entry,[e]);
}, 1);

Clazz.newMeth(C$, ['keyOrNull$java_util_TreeMap_Entry'], function (e) {
return e == null  ? null : e.key;
}, 1);

Clazz.newMeth(C$, 'key$java_util_TreeMap_Entry', function (e) {
if (e == null ) throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
return e.key;
}, 1);

Clazz.newMeth(C$, 'getFirstEntry', function () {
var p = this.root;
if (p != null ) while (p.left != null )p = p.left;

return p;
});

Clazz.newMeth(C$, 'getLastEntry', function () {
var p = this.root;
if (p != null ) while (p.right != null )p = p.right;

return p;
});

Clazz.newMeth(C$, ['successor$java_util_TreeMap_Entry'], function (t) {
if (t == null ) return null;
 else if (t.right != null ) {
var p = t.right;
while (p.left != null )p = p.left;

return p;
} else {
var p = t.parent;
var ch = t;
while (p != null  && ch === p.right  ){
ch = p;
p = p.parent;
}
return p;
}}, 1);

Clazz.newMeth(C$, ['predecessor$java_util_TreeMap_Entry'], function (t) {
if (t == null ) return null;
 else if (t.left != null ) {
var p = t.left;
while (p.right != null )p = p.right;

return p;
} else {
var p = t.parent;
var ch = t;
while (p != null  && ch === p.left  ){
ch = p;
p = p.parent;
}
return p;
}}, 1);

Clazz.newMeth(C$, ['colorOf$java_util_TreeMap_Entry'], function (p) {
return (p == null  ? true : p.color);
}, 1);

Clazz.newMeth(C$, ['parentOf$java_util_TreeMap_Entry'], function (p) {
return (p == null  ? null : p.parent);
}, 1);

Clazz.newMeth(C$, ['setColor$java_util_TreeMap_Entry$Z'], function (p, c) {
if (p != null ) p.color = c;
}, 1);

Clazz.newMeth(C$, ['leftOf$java_util_TreeMap_Entry'], function (p) {
return (p == null ) ? null : p.left;
}, 1);

Clazz.newMeth(C$, ['rightOf$java_util_TreeMap_Entry'], function (p) {
return (p == null ) ? null : p.right;
}, 1);

Clazz.newMeth(C$, ['rotateLeft$java_util_TreeMap_Entry'], function (p) {
if (p != null ) {
var r = p.right;
p.right = r.left;
if (r.left != null ) r.left.parent = p;
r.parent = p.parent;
if (p.parent == null ) this.root = r;
 else if (p.parent.left === p ) p.parent.left = r;
 else p.parent.right = r;
r.left = p;
p.parent = r;
}});

Clazz.newMeth(C$, ['rotateRight$java_util_TreeMap_Entry'], function (p) {
if (p != null ) {
var l = p.left;
p.left = l.right;
if (l.right != null ) l.right.parent = p;
l.parent = p.parent;
if (p.parent == null ) this.root = l;
 else if (p.parent.right === p ) p.parent.right = l;
 else p.parent.left = l;
l.right = p;
p.parent = l;
}});

Clazz.newMeth(C$, ['fixAfterInsertion$java_util_TreeMap_Entry'], function (x) {
x.color = false;
while (x != null  && x !== this.root   && x.parent.color == false  ){
if (C$.parentOf$java_util_TreeMap_Entry(x) === C$.leftOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x))) ) {
var y = C$.rightOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x)));
if (C$.colorOf$java_util_TreeMap_Entry(y) == false ) {
C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(x), true);
C$.setColor$java_util_TreeMap_Entry$Z(y, true);
C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x)), false);
x = C$.parentOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x));
} else {
if (x === C$.rightOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x)) ) {
x = C$.parentOf$java_util_TreeMap_Entry(x);
p$.rotateLeft$java_util_TreeMap_Entry.apply(this, [x]);
}C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(x), true);
C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x)), false);
p$.rotateRight$java_util_TreeMap_Entry.apply(this, [C$.parentOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x))]);
}} else {
var y = C$.leftOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x)));
if (C$.colorOf$java_util_TreeMap_Entry(y) == false ) {
C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(x), true);
C$.setColor$java_util_TreeMap_Entry$Z(y, true);
C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x)), false);
x = C$.parentOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x));
} else {
if (x === C$.leftOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x)) ) {
x = C$.parentOf$java_util_TreeMap_Entry(x);
p$.rotateRight$java_util_TreeMap_Entry.apply(this, [x]);
}C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(x), true);
C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x)), false);
p$.rotateLeft$java_util_TreeMap_Entry.apply(this, [C$.parentOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x))]);
}}}
this.root.color = true;
});

Clazz.newMeth(C$, ['deleteEntry$java_util_TreeMap_Entry'], function (p) {
this.modCount++;
this.$size--;
if (p.left != null  && p.right != null  ) {
var s = C$.successor$java_util_TreeMap_Entry(p);
p.key = s.key;
p.value = s.value;
p = s;
}var replacement = (p.left != null  ? p.left : p.right);
if (replacement != null ) {
replacement.parent = p.parent;
if (p.parent == null ) this.root = replacement;
 else if (p === p.parent.left ) p.parent.left = replacement;
 else p.parent.right = replacement;
p.left = p.right = p.parent = null;
if (p.color == true ) p$.fixAfterDeletion$java_util_TreeMap_Entry.apply(this, [replacement]);
} else if (p.parent == null ) {
this.root = null;
} else {
if (p.color == true ) p$.fixAfterDeletion$java_util_TreeMap_Entry.apply(this, [p]);
if (p.parent != null ) {
if (p === p.parent.left ) p.parent.left = null;
 else if (p === p.parent.right ) p.parent.right = null;
p.parent = null;
}}});

Clazz.newMeth(C$, ['fixAfterDeletion$java_util_TreeMap_Entry'], function (x) {
while (x !== this.root  && C$.colorOf$java_util_TreeMap_Entry(x) == true  ){
if (x === C$.leftOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x)) ) {
var sib = C$.rightOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x));
if (C$.colorOf$java_util_TreeMap_Entry(sib) == false ) {
C$.setColor$java_util_TreeMap_Entry$Z(sib, true);
C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(x), false);
p$.rotateLeft$java_util_TreeMap_Entry.apply(this, [C$.parentOf$java_util_TreeMap_Entry(x)]);
sib = C$.rightOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x));
}if (C$.colorOf$java_util_TreeMap_Entry(C$.leftOf$java_util_TreeMap_Entry(sib)) == true  && C$.colorOf$java_util_TreeMap_Entry(C$.rightOf$java_util_TreeMap_Entry(sib)) == true  ) {
C$.setColor$java_util_TreeMap_Entry$Z(sib, false);
x = C$.parentOf$java_util_TreeMap_Entry(x);
} else {
if (C$.colorOf$java_util_TreeMap_Entry(C$.rightOf$java_util_TreeMap_Entry(sib)) == true ) {
C$.setColor$java_util_TreeMap_Entry$Z(C$.leftOf$java_util_TreeMap_Entry(sib), true);
C$.setColor$java_util_TreeMap_Entry$Z(sib, false);
p$.rotateRight$java_util_TreeMap_Entry.apply(this, [sib]);
sib = C$.rightOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x));
}C$.setColor$java_util_TreeMap_Entry$Z(sib, C$.colorOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x)));
C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(x), true);
C$.setColor$java_util_TreeMap_Entry$Z(C$.rightOf$java_util_TreeMap_Entry(sib), true);
p$.rotateLeft$java_util_TreeMap_Entry.apply(this, [C$.parentOf$java_util_TreeMap_Entry(x)]);
x = this.root;
}} else {
var sib = C$.leftOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x));
if (C$.colorOf$java_util_TreeMap_Entry(sib) == false ) {
C$.setColor$java_util_TreeMap_Entry$Z(sib, true);
C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(x), false);
p$.rotateRight$java_util_TreeMap_Entry.apply(this, [C$.parentOf$java_util_TreeMap_Entry(x)]);
sib = C$.leftOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x));
}if (C$.colorOf$java_util_TreeMap_Entry(C$.rightOf$java_util_TreeMap_Entry(sib)) == true  && C$.colorOf$java_util_TreeMap_Entry(C$.leftOf$java_util_TreeMap_Entry(sib)) == true  ) {
C$.setColor$java_util_TreeMap_Entry$Z(sib, false);
x = C$.parentOf$java_util_TreeMap_Entry(x);
} else {
if (C$.colorOf$java_util_TreeMap_Entry(C$.leftOf$java_util_TreeMap_Entry(sib)) == true ) {
C$.setColor$java_util_TreeMap_Entry$Z(C$.rightOf$java_util_TreeMap_Entry(sib), true);
C$.setColor$java_util_TreeMap_Entry$Z(sib, false);
p$.rotateLeft$java_util_TreeMap_Entry.apply(this, [sib]);
sib = C$.leftOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x));
}C$.setColor$java_util_TreeMap_Entry$Z(sib, C$.colorOf$java_util_TreeMap_Entry(C$.parentOf$java_util_TreeMap_Entry(x)));
C$.setColor$java_util_TreeMap_Entry$Z(C$.parentOf$java_util_TreeMap_Entry(x), true);
C$.setColor$java_util_TreeMap_Entry$Z(C$.leftOf$java_util_TreeMap_Entry(sib), true);
p$.rotateRight$java_util_TreeMap_Entry.apply(this, [C$.parentOf$java_util_TreeMap_Entry(x)]);
x = this.root;
}}}
C$.setColor$java_util_TreeMap_Entry$Z(x, true);
});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (s) {
s.defaultWriteObject();
s.writeInt$I(this.$size);
for (var i = this.entrySet().iterator(); i.hasNext(); ) {
var e = i.next();
s.writeObject$O(e.getKey());
s.writeObject$O(e.getValue());
}
});

Clazz.newMeth(C$, 'readObject$java_io_ObjectInputStream', function (s) {
s.defaultReadObject();
var size = s.readInt();
p$.buildFromSorted$I$java_util_Iterator$java_io_ObjectInputStream$TV.apply(this, [size, null, s, null]);
});

Clazz.newMeth(C$, ['readTreeSet$I$java_io_ObjectInputStream$TV'], function (size, s, defaultVal) {
p$.buildFromSorted$I$java_util_Iterator$java_io_ObjectInputStream$TV.apply(this, [size, null, s, defaultVal]);
});

Clazz.newMeth(C$, ['addAllForTreeSet$java_util_SortedSet$TV'], function (set, defaultVal) {
try {
p$.buildFromSorted$I$java_util_Iterator$java_io_ObjectInputStream$TV.apply(this, [set.size(), set.iterator(), null, defaultVal]);
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var cannotHappen = e$$;
{
}
} else if (Clazz.exceptionOf(e$$, "java.lang.ClassNotFoundException")){
var cannotHappen = e$$;
{
}
} else {
throw e$$;
}
}
});

Clazz.newMeth(C$, ['buildFromSorted$I$java_util_Iterator$java_io_ObjectInputStream$TV'], function (size, it, str, defaultVal) {
this.$size = size;
this.root = p$.buildFromSorted$I$I$I$I$java_util_Iterator$java_io_ObjectInputStream$TV.apply(this, [0, 0, size - 1, C$.computeRedLevel$I(size), it, str, defaultVal]);
});

Clazz.newMeth(C$, ['buildFromSorted$I$I$I$I$java_util_Iterator$java_io_ObjectInputStream$TV'], function (level, lo, hi, redLevel, it, str, defaultVal) {
if (hi < lo) return null;
var mid = (lo + hi) >>> 1;
var left = null;
if (lo < mid) left = p$.buildFromSorted$I$I$I$I$java_util_Iterator$java_io_ObjectInputStream$TV.apply(this, [level + 1, lo, mid - 1, redLevel, it, str, defaultVal]);
var key;
var value;
if (it != null ) {
if (defaultVal == null ) {
var entry = it.next();
key = entry.getKey();
value = entry.getValue();
} else {
key = it.next();
value = defaultVal;
}} else {
key = str.readObject();
value = (defaultVal != null  ? defaultVal : str.readObject());
}var middle = Clazz.new_((I$[14]||$incl$(14)).c$$TK$TV$java_util_TreeMap_Entry,[key, value, null]);
if (level == redLevel) middle.color = false;
if (left != null ) {
middle.left = left;
left.parent = middle;
}if (mid < hi) {
var right = p$.buildFromSorted$I$I$I$I$java_util_Iterator$java_io_ObjectInputStream$TV.apply(this, [level + 1, mid + 1, hi, redLevel, it, str, defaultVal]);
middle.right = right;
right.parent = middle;
}return middle;
});

Clazz.newMeth(C$, 'computeRedLevel$I', function (sz) {
var level = 0;
for (var m = sz - 1; m >= 0; m = (m/2|0) - 1) level++;

return level;
}, 1);
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "Values", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractCollection');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[1]||$incl$(1)).c$$java_util_TreeMap_Entry, [this, null, this.this$0.getFirstEntry()]);
});

Clazz.newMeth(C$, 'size', function () {
return this.b$['java.util.TreeMap'].size();
});

Clazz.newMeth(C$, 'contains$O', function (o) {
return this.b$['java.util.TreeMap'].containsValue$O(o);
});

Clazz.newMeth(C$, 'remove$O', function (o) {
for (var e = this.this$0.getFirstEntry(); e != null ; e = P$.TreeMap.successor$java_util_TreeMap_Entry(e)) {
if (P$.TreeMap.valEquals$O$O(e.getValue(), o)) {
this.this$0.deleteEntry$java_util_TreeMap_Entry.apply(this.this$0, [e]);
return true;
}}
return false;
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.TreeMap'].clear();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "EntrySet", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractSet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[2]||$incl$(2)).c$$java_util_TreeMap_Entry, [this, null, this.this$0.getFirstEntry()]);
});

Clazz.newMeth(C$, 'contains$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var entry = o;
var value = entry.getValue();
var p = this.this$0.getEntry$O(entry.getKey());
return p != null  && P$.TreeMap.valEquals$O$O(p.getValue(), value) ;
});

Clazz.newMeth(C$, 'remove$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var entry = o;
var value = entry.getValue();
var p = this.this$0.getEntry$O(entry.getKey());
if (p != null  && P$.TreeMap.valEquals$O$O(p.getValue(), value) ) {
this.this$0.deleteEntry$java_util_TreeMap_Entry.apply(this.this$0, [p]);
return true;
}return false;
});

Clazz.newMeth(C$, 'size', function () {
return this.b$['java.util.TreeMap'].size();
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.TreeMap'].clear();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "KeySet", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.AbstractSet', 'java.util.NavigableSet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.m = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_NavigableMap', function (map) {
Clazz.super_(C$, this,1);
this.m = map;
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
if (Clazz.instanceOf(this.m, "java.util.TreeMap")) return (this.m).keyIterator();
 else return ((this.m).keyIterator());
});

Clazz.newMeth(C$, 'descendingIterator', function () {
if (Clazz.instanceOf(this.m, "java.util.TreeMap")) return (this.m).descendingKeyIterator();
 else return ((this.m).descendingKeyIterator());
});

Clazz.newMeth(C$, 'size', function () {
return this.m.size();
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.m.isEmpty();
});

Clazz.newMeth(C$, 'contains$O', function (o) {
return this.m.containsKey$O(o);
});

Clazz.newMeth(C$, 'clear', function () {
this.m.clear();
});

Clazz.newMeth(C$, ['lower$TE'], function (e) {
return this.m.lowerKey$TK(e);
});

Clazz.newMeth(C$, ['floor$TE'], function (e) {
return this.m.floorKey$TK(e);
});

Clazz.newMeth(C$, ['ceiling$TE'], function (e) {
return this.m.ceilingKey$TK(e);
});

Clazz.newMeth(C$, ['higher$TE'], function (e) {
return this.m.higherKey$TK(e);
});

Clazz.newMeth(C$, 'first', function () {
return this.m.firstKey();
});

Clazz.newMeth(C$, 'last', function () {
return this.m.lastKey();
});

Clazz.newMeth(C$, 'comparator', function () {
return this.m.comparator();
});

Clazz.newMeth(C$, 'pollFirst', function () {
var e = this.m.pollFirstEntry();
return e == null  ? null : e.getKey();
});

Clazz.newMeth(C$, 'pollLast', function () {
var e = this.m.pollLastEntry();
return e == null  ? null : e.getKey();
});

Clazz.newMeth(C$, 'remove$O', function (o) {
var oldSize = this.size();
this.m.remove$O(o);
return this.size() != oldSize;
});

Clazz.newMeth(C$, ['subSet$TE$Z$TE$Z'], function (fromElement, fromInclusive, toElement, toInclusive) {
return Clazz.new_(C$.c$$java_util_NavigableMap,[this.m.subMap$TK$Z$TK$Z(fromElement, fromInclusive, toElement, toInclusive)]);
});

Clazz.newMeth(C$, ['headSet$TE$Z'], function (toElement, inclusive) {
return Clazz.new_(C$.c$$java_util_NavigableMap,[this.m.headMap$TK$Z(toElement, inclusive)]);
});

Clazz.newMeth(C$, ['tailSet$TE$Z'], function (fromElement, inclusive) {
return Clazz.new_(C$.c$$java_util_NavigableMap,[this.m.tailMap$TK$Z(fromElement, inclusive)]);
});

Clazz.newMeth(C$, ['subSet$TE$TE'], function (fromElement, toElement) {
return this.subSet$TE$Z$TE$Z(fromElement, true, toElement, false);
});

Clazz.newMeth(C$, ['headSet$TE'], function (toElement) {
return this.headSet$TE$Z(toElement, false);
});

Clazz.newMeth(C$, ['tailSet$TE'], function (fromElement) {
return this.tailSet$TE$Z(fromElement, true);
});

Clazz.newMeth(C$, 'descendingSet', function () {
return Clazz.new_(C$.c$$java_util_NavigableMap,[this.m.descendingMap()]);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "PrivateEntryIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.util.Iterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$next = null;
this.lastReturned = null;
this.expectedModCount = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_TreeMap_Entry'], function (first) {
C$.$init$.apply(this);
this.expectedModCount = this.this$0.modCount;
this.lastReturned = null;
this.$next = first;
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.$next != null ;
});

Clazz.newMeth(C$, 'nextEntry', function () {
var e = this.$next;
if (e == null ) throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
if (this.this$0.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
this.$next = P$.TreeMap.successor$java_util_TreeMap_Entry(e);
this.lastReturned = e;
return e;
});

Clazz.newMeth(C$, 'prevEntry', function () {
var e = this.$next;
if (e == null ) throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
if (this.this$0.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
this.$next = P$.TreeMap.predecessor$java_util_TreeMap_Entry(e);
this.lastReturned = e;
return e;
});

Clazz.newMeth(C$, 'remove', function () {
if (this.lastReturned == null ) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
if (this.this$0.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
if (this.lastReturned.left != null  && this.lastReturned.right != null  ) this.$next = this.lastReturned;
this.this$0.deleteEntry$java_util_TreeMap_Entry.apply(this.this$0, [this.lastReturned]);
this.expectedModCount = this.this$0.modCount;
this.lastReturned = null;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "EntryIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.TreeMap','java.util.TreeMap.PrivateEntryIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_TreeMap_Entry', function (first) {
C$.superclazz.c$$java_util_TreeMap_Entry.apply(this, [first]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.nextEntry();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "ValueIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.TreeMap','java.util.TreeMap.PrivateEntryIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_TreeMap_Entry', function (first) {
C$.superclazz.c$$java_util_TreeMap_Entry.apply(this, [first]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.nextEntry().value;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "KeyIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.TreeMap','java.util.TreeMap.PrivateEntryIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_TreeMap_Entry', function (first) {
C$.superclazz.c$$java_util_TreeMap_Entry.apply(this, [first]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.nextEntry().key;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "DescendingKeyIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.TreeMap','java.util.TreeMap.PrivateEntryIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_TreeMap_Entry', function (first) {
C$.superclazz.c$$java_util_TreeMap_Entry.apply(this, [first]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.prevEntry().key;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "NavigableSubMap", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.AbstractMap', ['java.util.NavigableMap', 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.m = null;
this.lo = null;
this.hi = null;
this.fromStart = false;
this.toEnd = false;
this.loInclusive = false;
this.hiInclusive = false;
this.descendingMapView = null;
this.entrySetView = null;
this.navigableKeySetView = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.descendingMapView = null;
this.entrySetView = null;
this.navigableKeySetView = null;
}, 1);

Clazz.newMeth(C$, ['c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z'], function (m, fromStart, lo, loInclusive, toEnd, hi, hiInclusive) {
Clazz.super_(C$, this,1);
if (!fromStart && !toEnd ) {
if (m.compare$O$O(lo, hi) > 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["fromKey > toKey"]);
} else {
if (!fromStart) m.compare$O$O(lo, lo);
if (!toEnd) m.compare$O$O(hi, hi);
}this.m = m;
this.fromStart = fromStart;
this.lo = lo;
this.loInclusive = loInclusive;
this.toEnd = toEnd;
this.hi = hi;
this.hiInclusive = hiInclusive;
}, 1);

Clazz.newMeth(C$, 'tooLow$O', function (key) {
if (!this.fromStart) {
var c = this.m.compare$O$O(key, this.lo);
if (c < 0 || (c == 0 && !this.loInclusive ) ) return true;
}return false;
});

Clazz.newMeth(C$, 'tooHigh$O', function (key) {
if (!this.toEnd) {
var c = this.m.compare$O$O(key, this.hi);
if (c > 0 || (c == 0 && !this.hiInclusive ) ) return true;
}return false;
});

Clazz.newMeth(C$, 'inRange$O', function (key) {
return !this.tooLow$O(key) && !this.tooHigh$O(key) ;
});

Clazz.newMeth(C$, 'inClosedRange$O', function (key) {
return (this.fromStart || this.m.compare$O$O(key, this.lo) >= 0 ) && (this.toEnd || this.m.compare$O$O(this.hi, key) >= 0 ) ;
});

Clazz.newMeth(C$, 'inRange$O$Z', function (key, inclusive) {
return inclusive ? this.inRange$O(key) : this.inClosedRange$O(key);
});

Clazz.newMeth(C$, 'absLowest', function () {
var e = (this.fromStart ? this.m.getFirstEntry() : (this.loInclusive ? this.m.getCeilingEntry$TK(this.lo) : this.m.getHigherEntry$TK(this.lo)));
return (e == null  || this.tooHigh$O(e.key) ) ? null : e;
});

Clazz.newMeth(C$, 'absHighest', function () {
var e = (this.toEnd ? this.m.getLastEntry() : (this.hiInclusive ? this.m.getFloorEntry$TK(this.hi) : this.m.getLowerEntry$TK(this.hi)));
return (e == null  || this.tooLow$O(e.key) ) ? null : e;
});

Clazz.newMeth(C$, ['absCeiling$TK'], function (key) {
if (this.tooLow$O(key)) return this.absLowest();
var e = this.m.getCeilingEntry$TK(key);
return (e == null  || this.tooHigh$O(e.key) ) ? null : e;
});

Clazz.newMeth(C$, ['absHigher$TK'], function (key) {
if (this.tooLow$O(key)) return this.absLowest();
var e = this.m.getHigherEntry$TK(key);
return (e == null  || this.tooHigh$O(e.key) ) ? null : e;
});

Clazz.newMeth(C$, ['absFloor$TK'], function (key) {
if (this.tooHigh$O(key)) return this.absHighest();
var e = this.m.getFloorEntry$TK(key);
return (e == null  || this.tooLow$O(e.key) ) ? null : e;
});

Clazz.newMeth(C$, ['absLower$TK'], function (key) {
if (this.tooHigh$O(key)) return this.absHighest();
var e = this.m.getLowerEntry$TK(key);
return (e == null  || this.tooLow$O(e.key) ) ? null : e;
});

Clazz.newMeth(C$, 'absHighFence', function () {
return (this.toEnd ? null : (this.hiInclusive ? this.m.getHigherEntry$TK(this.hi) : this.m.getCeilingEntry$TK(this.hi)));
});

Clazz.newMeth(C$, 'absLowFence', function () {
return (this.fromStart ? null : (this.loInclusive ? this.m.getLowerEntry$TK(this.lo) : this.m.getFloorEntry$TK(this.lo)));
});

Clazz.newMeth(C$, 'isEmpty', function () {
return (this.fromStart && this.toEnd ) ? this.m.isEmpty() : this.entrySet().isEmpty();
});

Clazz.newMeth(C$, 'size', function () {
return (this.fromStart && this.toEnd ) ? this.m.size() : this.entrySet().size();
});

Clazz.newMeth(C$, 'containsKey$O', function (key) {
return this.inRange$O(key) && this.m.containsKey$O(key) ;
});

Clazz.newMeth(C$, ['put$TK$TV'], function (key, value) {
if (!this.inRange$O(key)) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["key out of range"]);
return this.m.put$TK$TV(key, value);
});

Clazz.newMeth(C$, 'get$O', function (key) {
return !this.inRange$O(key) ? null : this.m.get$O(key);
});

Clazz.newMeth(C$, 'remove$O', function (key) {
return !this.inRange$O(key) ? null : this.m.remove$O(key);
});

Clazz.newMeth(C$, ['ceilingEntry$TK'], function (key) {
return P$.TreeMap.exportEntry$java_util_TreeMap_Entry(this.subCeiling$TK(key));
});

Clazz.newMeth(C$, ['ceilingKey$TK'], function (key) {
return P$.TreeMap.keyOrNull$java_util_TreeMap_Entry(this.subCeiling$TK(key));
});

Clazz.newMeth(C$, ['higherEntry$TK'], function (key) {
return P$.TreeMap.exportEntry$java_util_TreeMap_Entry(this.subHigher$TK(key));
});

Clazz.newMeth(C$, ['higherKey$TK'], function (key) {
return P$.TreeMap.keyOrNull$java_util_TreeMap_Entry(this.subHigher$TK(key));
});

Clazz.newMeth(C$, ['floorEntry$TK'], function (key) {
return P$.TreeMap.exportEntry$java_util_TreeMap_Entry(this.subFloor$TK(key));
});

Clazz.newMeth(C$, ['floorKey$TK'], function (key) {
return P$.TreeMap.keyOrNull$java_util_TreeMap_Entry(this.subFloor$TK(key));
});

Clazz.newMeth(C$, ['lowerEntry$TK'], function (key) {
return P$.TreeMap.exportEntry$java_util_TreeMap_Entry(this.subLower$TK(key));
});

Clazz.newMeth(C$, ['lowerKey$TK'], function (key) {
return P$.TreeMap.keyOrNull$java_util_TreeMap_Entry(this.subLower$TK(key));
});

Clazz.newMeth(C$, 'firstKey', function () {
return P$.TreeMap.key$java_util_TreeMap_Entry(this.subLowest());
});

Clazz.newMeth(C$, 'lastKey', function () {
return P$.TreeMap.key$java_util_TreeMap_Entry(this.subHighest());
});

Clazz.newMeth(C$, 'firstEntry', function () {
return P$.TreeMap.exportEntry$java_util_TreeMap_Entry(this.subLowest());
});

Clazz.newMeth(C$, 'lastEntry', function () {
return P$.TreeMap.exportEntry$java_util_TreeMap_Entry(this.subHighest());
});

Clazz.newMeth(C$, 'pollFirstEntry', function () {
var e = this.subLowest();
var result = P$.TreeMap.exportEntry$java_util_TreeMap_Entry(e);
if (e != null ) this.m.deleteEntry$java_util_TreeMap_Entry(e);
return result;
});

Clazz.newMeth(C$, 'pollLastEntry', function () {
var e = this.subHighest();
var result = P$.TreeMap.exportEntry$java_util_TreeMap_Entry(e);
if (e != null ) this.m.deleteEntry$java_util_TreeMap_Entry(e);
return result;
});

Clazz.newMeth(C$, 'navigableKeySet', function () {
var nksv = this.navigableKeySetView;
return (nksv != null ) ? nksv : (this.navigableKeySetView = Clazz.new_((I$[4]||$incl$(4)).c$$java_util_NavigableMap,[this]));
});

Clazz.newMeth(C$, 'keySet', function () {
return this.navigableKeySet();
});

Clazz.newMeth(C$, 'descendingKeySet', function () {
return this.descendingMap().navigableKeySet();
});

Clazz.newMeth(C$, ['subMap$TK$TK'], function (fromKey, toKey) {
return this.subMap$TK$Z$TK$Z(fromKey, true, toKey, false);
});

Clazz.newMeth(C$, ['headMap$TK'], function (toKey) {
return this.headMap$TK$Z(toKey, false);
});

Clazz.newMeth(C$, ['tailMap$TK'], function (fromKey) {
return this.tailMap$TK$Z(fromKey, true);
});
;
(function(){var C$=Clazz.newClass(P$.TreeMap.NavigableSubMap, "EntrySetView", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.util.AbstractSet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$size = 0;
this.sizeModCount = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$size = -1;
}, 1);

Clazz.newMeth(C$, 'size', function () {
if (this.this$0.fromStart && this.this$0.toEnd ) return this.this$0.m.size();
if (this.$size == -1 || this.sizeModCount != this.this$0.m.modCount ) {
this.sizeModCount = this.this$0.m.modCount;
this.$size = 0;
var i = this.iterator();
while (i.hasNext()){
this.$size++;
i.next();
}
}return this.$size;
});

Clazz.newMeth(C$, 'isEmpty', function () {
var n = this.this$0.absLowest();
return n == null  || this.this$0.tooHigh$O(n.key) ;
});

Clazz.newMeth(C$, 'contains$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var entry = o;
var key = entry.getKey();
if (!this.this$0.inRange$O(key)) return false;
var node = this.this$0.m.getEntry$O(key);
return node != null  && P$.TreeMap.valEquals$O$O(node.getValue(), entry.getValue()) ;
});

Clazz.newMeth(C$, 'remove$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var entry = o;
var key = entry.getKey();
if (!this.this$0.inRange$O(key)) return false;
var node = this.this$0.m.getEntry$O(key);
if (node != null  && P$.TreeMap.valEquals$O$O(node.getValue(), entry.getValue()) ) {
this.this$0.m.deleteEntry$java_util_TreeMap_Entry(node);
return true;
}return false;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap.NavigableSubMap, "SubMapIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.util.Iterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.lastReturned = null;
this.$next = null;
this.fenceKey = null;
this.expectedModCount = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry'], function (first, fence) {
C$.$init$.apply(this);
this.expectedModCount = this.this$0.m.modCount;
this.lastReturned = null;
this.$next = first;
this.fenceKey = fence == null  ? (I$[3]||$incl$(3)).UNBOUNDED : fence.key;
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.$next != null  && this.$next.key !== this.fenceKey  ;
});

Clazz.newMeth(C$, 'nextEntry', function () {
var e = this.$next;
if (e == null  || e.key === this.fenceKey  ) throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
if (this.this$0.m.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
this.$next = P$.TreeMap.successor$java_util_TreeMap_Entry(e);
this.lastReturned = e;
return e;
});

Clazz.newMeth(C$, 'prevEntry', function () {
var e = this.$next;
if (e == null  || e.key === this.fenceKey  ) throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
if (this.this$0.m.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
this.$next = P$.TreeMap.predecessor$java_util_TreeMap_Entry(e);
this.lastReturned = e;
return e;
});

Clazz.newMeth(C$, 'removeAscending', function () {
if (this.lastReturned == null ) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
if (this.this$0.m.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
if (this.lastReturned.left != null  && this.lastReturned.right != null  ) this.$next = this.lastReturned;
this.this$0.m.deleteEntry$java_util_TreeMap_Entry(this.lastReturned);
this.lastReturned = null;
this.expectedModCount = this.this$0.m.modCount;
});

Clazz.newMeth(C$, 'removeDescending', function () {
if (this.lastReturned == null ) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
if (this.this$0.m.modCount != this.expectedModCount) throw Clazz.new_(Clazz.load('java.util.ConcurrentModificationException'));
this.this$0.m.deleteEntry$java_util_TreeMap_Entry(this.lastReturned);
this.lastReturned = null;
this.expectedModCount = this.this$0.m.modCount;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap.NavigableSubMap, "SubMapEntryIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.TreeMap','java.util.TreeMap.NavigableSubMap','java.util.TreeMap.SubMapIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry', function (first, fence) {
C$.superclazz.c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry.apply(this, [first, fence]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.nextEntry();
});

Clazz.newMeth(C$, 'remove', function () {
this.removeAscending();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap.NavigableSubMap, "SubMapKeyIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.TreeMap','java.util.TreeMap.NavigableSubMap','java.util.TreeMap.SubMapIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry', function (first, fence) {
C$.superclazz.c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry.apply(this, [first, fence]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.nextEntry().key;
});

Clazz.newMeth(C$, 'remove', function () {
this.removeAscending();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap.NavigableSubMap, "DescendingSubMapEntryIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.TreeMap','java.util.TreeMap.NavigableSubMap','java.util.TreeMap.SubMapIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry', function (last, fence) {
C$.superclazz.c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry.apply(this, [last, fence]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.prevEntry();
});

Clazz.newMeth(C$, 'remove', function () {
this.removeDescending();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap.NavigableSubMap, "DescendingSubMapKeyIterator", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.TreeMap','java.util.TreeMap.NavigableSubMap','java.util.TreeMap.SubMapIterator']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry', function (last, fence) {
C$.superclazz.c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry.apply(this, [last, fence]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'next', function () {
return this.prevEntry().key;
});

Clazz.newMeth(C$, 'remove', function () {
this.removeDescending();
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "AscendingSubMap", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.TreeMap','java.util.TreeMap.NavigableSubMap']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z'], function (m, fromStart, lo, loInclusive, toEnd, hi, hiInclusive) {
C$.superclazz.c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z.apply(this, [m, fromStart, lo, loInclusive, toEnd, hi, hiInclusive]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'comparator', function () {
return this.m.comparator();
});

Clazz.newMeth(C$, ['subMap$TK$Z$TK$Z'], function (fromKey, fromInclusive, toKey, toInclusive) {
if (!this.inRange$O$Z(fromKey, fromInclusive)) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["fromKey out of range"]);
if (!this.inRange$O$Z(toKey, toInclusive)) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["toKey out of range"]);
return Clazz.new_(C$.c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this.m, false, fromKey, fromInclusive, false, toKey, toInclusive]);
});

Clazz.newMeth(C$, ['headMap$TK$Z'], function (toKey, inclusive) {
if (!this.inRange$O$Z(toKey, inclusive)) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["toKey out of range"]);
return Clazz.new_(C$.c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this.m, this.fromStart, this.lo, this.loInclusive, false, toKey, inclusive]);
});

Clazz.newMeth(C$, ['tailMap$TK$Z'], function (fromKey, inclusive) {
if (!this.inRange$O$Z(fromKey, inclusive)) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["fromKey out of range"]);
return Clazz.new_(C$.c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this.m, false, fromKey, inclusive, this.toEnd, this.hi, this.hiInclusive]);
});

Clazz.newMeth(C$, 'descendingMap', function () {
var mv = this.descendingMapView;
return (mv != null ) ? mv : (this.descendingMapView = Clazz.new_((I$[6]||$incl$(6)).c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this.m, this.fromStart, this.lo, this.loInclusive, this.toEnd, this.hi, this.hiInclusive]));
});

Clazz.newMeth(C$, 'keyIterator', function () {
return Clazz.new_((I$[7]||$incl$(7)).c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry, [this, null, this.absLowest(), this.absHighFence()]);
});

Clazz.newMeth(C$, 'descendingKeyIterator', function () {
return Clazz.new_((I$[8]||$incl$(8)).c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry, [this, null, this.absHighest(), this.absLowFence()]);
});

Clazz.newMeth(C$, 'entrySet', function () {
var es = this.entrySetView;
return (es != null ) ? es : Clazz.new_((I$[9]||$incl$(9)), [this, null]);
});

Clazz.newMeth(C$, 'subLowest', function () {
return this.absLowest();
});

Clazz.newMeth(C$, 'subHighest', function () {
return this.absHighest();
});

Clazz.newMeth(C$, ['subCeiling$TK'], function (key) {
return this.absCeiling$TK(key);
});

Clazz.newMeth(C$, ['subHigher$TK'], function (key) {
return this.absHigher$TK(key);
});

Clazz.newMeth(C$, ['subFloor$TK'], function (key) {
return this.absFloor$TK(key);
});

Clazz.newMeth(C$, ['subLower$TK'], function (key) {
return this.absLower$TK(key);
});
;
(function(){var C$=Clazz.newClass(P$.TreeMap.AscendingSubMap, "AscendingEntrySetView", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.TreeMap','java.util.TreeMap.NavigableSubMap','java.util.TreeMap.EntrySetView']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[5]||$incl$(5)).c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry, [this, null, this.this$0.absLowest(), this.this$0.absHighFence()]);
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "DescendingSubMap", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.TreeMap','java.util.TreeMap.NavigableSubMap']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.reverseComparator = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.reverseComparator = (I$[11]||$incl$(11)).reverseOrder$java_util_Comparator(this.m.$comparator);
}, 1);

Clazz.newMeth(C$, ['c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z'], function (m, fromStart, lo, loInclusive, toEnd, hi, hiInclusive) {
C$.superclazz.c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z.apply(this, [m, fromStart, lo, loInclusive, toEnd, hi, hiInclusive]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'comparator', function () {
return this.reverseComparator;
});

Clazz.newMeth(C$, ['subMap$TK$Z$TK$Z'], function (fromKey, fromInclusive, toKey, toInclusive) {
if (!this.inRange$O$Z(fromKey, fromInclusive)) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["fromKey out of range"]);
if (!this.inRange$O$Z(toKey, toInclusive)) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["toKey out of range"]);
return Clazz.new_(C$.c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this.m, false, toKey, toInclusive, false, fromKey, fromInclusive]);
});

Clazz.newMeth(C$, ['headMap$TK$Z'], function (toKey, inclusive) {
if (!this.inRange$O$Z(toKey, inclusive)) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["toKey out of range"]);
return Clazz.new_(C$.c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this.m, false, toKey, inclusive, this.toEnd, this.hi, this.hiInclusive]);
});

Clazz.newMeth(C$, ['tailMap$TK$Z'], function (fromKey, inclusive) {
if (!this.inRange$O$Z(fromKey, inclusive)) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["fromKey out of range"]);
return Clazz.new_(C$.c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this.m, this.fromStart, this.lo, this.loInclusive, false, fromKey, inclusive]);
});

Clazz.newMeth(C$, 'descendingMap', function () {
var mv = this.descendingMapView;
return (mv != null ) ? mv : (this.descendingMapView = Clazz.new_((I$[12]||$incl$(12)).c$$java_util_TreeMap$Z$TK$Z$Z$TK$Z,[this.m, this.fromStart, this.lo, this.loInclusive, this.toEnd, this.hi, this.hiInclusive]));
});

Clazz.newMeth(C$, 'keyIterator', function () {
return Clazz.new_((I$[8]||$incl$(8)).c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry, [this, null, this.absHighest(), this.absLowFence()]);
});

Clazz.newMeth(C$, 'descendingKeyIterator', function () {
return Clazz.new_((I$[7]||$incl$(7)).c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry, [this, null, this.absLowest(), this.absHighFence()]);
});

Clazz.newMeth(C$, 'entrySet', function () {
var es = this.entrySetView;
return (es != null ) ? es : Clazz.new_((I$[13]||$incl$(13)), [this, null]);
});

Clazz.newMeth(C$, 'subLowest', function () {
return this.absHighest();
});

Clazz.newMeth(C$, 'subHighest', function () {
return this.absLowest();
});

Clazz.newMeth(C$, ['subCeiling$TK'], function (key) {
return this.absFloor$TK(key);
});

Clazz.newMeth(C$, ['subHigher$TK'], function (key) {
return this.absLower$TK(key);
});

Clazz.newMeth(C$, ['subFloor$TK'], function (key) {
return this.absCeiling$TK(key);
});

Clazz.newMeth(C$, ['subLower$TK'], function (key) {
return this.absHigher$TK(key);
});
;
(function(){var C$=Clazz.newClass(P$.TreeMap.DescendingSubMap, "DescendingEntrySetView", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['java.util.TreeMap','java.util.TreeMap.NavigableSubMap','java.util.TreeMap.EntrySetView']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[10]||$incl$(10)).c$$java_util_TreeMap_Entry$java_util_TreeMap_Entry, [this, null, this.this$0.absHighest(), this.this$0.absLowFence()]);
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.TreeMap, "Entry", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, [['java.util.Map','java.util.Map.Entry']]);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.key = null;
this.value = null;
this.left = null;
this.right = null;
this.parent = null;
this.color = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.left = null;
this.right = null;
this.color = true;
}, 1);

Clazz.newMeth(C$, ['c$$TK$TV$java_util_TreeMap_Entry'], function (key, value, parent) {
C$.$init$.apply(this);
this.key = key;
this.value = value;
this.parent = parent;
}, 1);

Clazz.newMeth(C$, 'getKey', function () {
return this.key;
});

Clazz.newMeth(C$, 'getValue', function () {
return this.value;
});

Clazz.newMeth(C$, ['setValue$TV'], function (value) {
var oldValue = this.value;
this.value = value;
return oldValue;
});

Clazz.newMeth(C$, 'equals$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var e = o;
return P$.TreeMap.valEquals$O$O(this.key, e.getKey()) && P$.TreeMap.valEquals$O$O(this.value, e.getValue()) ;
});

Clazz.newMeth(C$, 'hashCode', function () {
var keyHash = (this.key == null  ? 0 : this.key.hashCode());
var valueHash = (this.value == null  ? 0 : this.value.hashCode());
return keyHash ^ valueHash;
});

Clazz.newMeth(C$, 'toString', function () {
return this.key + "=" + this.value ;
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-06 08:58:55
