(function(){var P$=java.util,I$=[['java.util.Collections','java.util.Collections$EmptySet$1','java.util.Collections$SingletonSet$1','java.util.Collections$SingletonMap$1$1$1','java.util.Collections$SingletonMap$1$1','java.util.AbstractSet',['java.util.Collections','.SynchronizedList'],['java.util.Collections','.SynchronizedRandomAccessList'],['java.util.Collections','.SynchronizedSet'],['java.util.Collections','.SynchronizedCollection'],'java.util.Collections$UnmodifiableCollection$1','java.util.Collections$UnmodifiableList$1',['java.util.Collections','.UnmodifiableRandomAccessList'],['java.util.Collections','.UnmodifiableList'],['java.util.Collections','.UnmodifiableMap','.UnmodifiableEntrySet','.UnmodifiableMapEntry'],'java.util.Collections$UnmodifiableMap$UnmodifiableEntrySet$1','java.lang.reflect.Array',['java.util.Collections','.UnmodifiableMap','.UnmodifiableEntrySet'],['java.util.Collections','.UnmodifiableSet'],['java.util.Collections','.UnmodifiableCollection'],['java.util.Collections','.CheckedListIterator'],'java.util.Arrays',['java.util.Collections','.CheckedMap','.CheckedEntry'],['java.util.Collections','.CheckedMap','.CheckedEntrySet','.CheckedEntryIterator'],['java.util.Map','.Entry'],['java.util.Collections','.CheckedMap','.CheckedEntrySet'],['java.util.Collections','.EmptyList'],['java.util.Collections','.EmptySet'],['java.util.Collections','.EmptyMap'],'java.util.Collections$1',['java.util.Collections','.CopiesList'],['java.util.Collections','.ReverseComparator'],['java.util.Collections','.ReverseComparatorWithComparator'],'java.util.Random',['java.util.Collections','.SingletonSet'],['java.util.Collections','.SingletonList'],['java.util.Collections','.SingletonMap'],'java.util.ArrayList',['java.util.Collections','.SynchronizedMap'],['java.util.Collections','.SynchronizedSortedMap'],['java.util.Collections','.SynchronizedSortedSet'],['java.util.Collections','.UnmodifiableMap'],['java.util.Collections','.UnmodifiableSortedMap'],['java.util.Collections','.UnmodifiableSortedSet'],['java.util.Collections','.CheckedCollection'],['java.util.Collections','.CheckedMap'],['java.util.Collections','.CheckedRandomAccessList'],['java.util.Collections','.CheckedList'],['java.util.Collections','.CheckedSet'],['java.util.Collections','.CheckedSortedMap'],['java.util.Collections','.CheckedSortedSet']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Collections", function(){
Clazz.newInstance(this, arguments,0,C$);
});
C$.EMPTY_LIST = null;
C$.EMPTY_SET = null;
C$.EMPTY_MAP = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.EMPTY_LIST = Clazz.new_((I$[27]||$incl$(27)));
C$.EMPTY_SET = Clazz.new_((I$[28]||$incl$(28)));
C$.EMPTY_MAP = Clazz.new_((I$[29]||$incl$(29)));
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'binarySearch$java_util_List$TT', function (list, object) {
if (list == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (list.isEmpty()) {
return -1;
}var key = object;
if (!(Clazz.instanceOf(list, "java.util.RandomAccess"))) {
var it = list.listIterator();
while (it.hasNext()){
var result;
if ((result=key.compareTo$TT(it.next())) <= 0) {
if (result == 0) {
return it.previousIndex();
}return -it.previousIndex() - 1;
}}
return -list.size() - 1;
}var low = 0;
var mid = list.size();
var high = mid - 1;
var result = -1;
while (low <= high){
mid=(low + high) >> 1;
if ((result=key.compareTo$TT(list.get$I(mid))) > 0) {
low=mid + 1;
} else if (result == 0) {
return mid;
} else {
high=mid - 1;
}}
return -mid - (result < 0 ? 1 : 2);
}, 1);

Clazz.newMeth(C$, 'binarySearch$java_util_List$TT$java_util_Comparator', function (list, object, comparator) {
if (comparator == null ) {
return C$.binarySearch$java_util_List$TT(list, object);
}if (!(Clazz.instanceOf(list, "java.util.RandomAccess"))) {
var it = list.listIterator();
while (it.hasNext()){
var result;
if ((result=comparator.compare$TT$TT(object, it.next())) <= 0) {
if (result == 0) {
return it.previousIndex();
}return -it.previousIndex() - 1;
}}
return -list.size() - 1;
}var low = 0;
var mid = list.size();
var high = mid - 1;
var result = -1;
while (low <= high){
mid=(low + high) >> 1;
if ((result=comparator.compare$TT$TT(object, list.get$I(mid))) > 0) {
low=mid + 1;
} else if (result == 0) {
return mid;
} else {
high=mid - 1;
}}
return -mid - (result < 0 ? 1 : 2);
}, 1);

Clazz.newMeth(C$, 'copy$java_util_List$java_util_List', function (destination, source) {
if (destination.size() < source.size()) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException'));
}var srcIt = source.iterator();
var destIt = destination.listIterator();
while (srcIt.hasNext()){
try {
destIt.next();
} catch (e) {
if (Clazz.exceptionOf(e, "java.util.NoSuchElementException")){
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException'));
} else {
throw e;
}
}
destIt.set$TE(srcIt.next());
}
}, 1);

Clazz.newMeth(C$, 'enumeration$java_util_Collection', function (collection) {
var c = collection;
return ((
(function(){var C$=Clazz.newClass(P$, "Collections$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Enumeration', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.it = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.it = this.$finals.c.iterator();
}, 1);

Clazz.newMeth(C$, 'hasMoreElements', function () {
return this.it.hasNext();
});

Clazz.newMeth(C$, 'nextElement', function () {
return this.it.next();
});
})()
), Clazz.new_((I$[30]||$incl$(30)).$init$, [this, {c: c}]));
}, 1);

Clazz.newMeth(C$, 'fill$java_util_List$TT', function (list, object) {
var it = list.listIterator();
while (it.hasNext()){
it.next();
it.set$TE(object);
}
}, 1);

Clazz.newMeth(C$, 'max$java_util_Collection', function (collection) {
var it = collection.iterator();
var max = it.next();
while (it.hasNext()){
var next = it.next();
if (max.compareTo$TT(next) < 0) {
max=next;
}}
return max;
}, 1);

Clazz.newMeth(C$, 'max$java_util_Collection$java_util_Comparator', function (collection, comparator) {
var it = collection.iterator();
var max = it.next();
while (it.hasNext()){
var next = it.next();
if (comparator.compare$TT$TT(max, next) < 0) {
max=next;
}}
return max;
}, 1);

Clazz.newMeth(C$, 'min$java_util_Collection', function (collection) {
var it = collection.iterator();
var min = it.next();
while (it.hasNext()){
var next = it.next();
if (min.compareTo$TT(next) > 0) {
min=next;
}}
return min;
}, 1);

Clazz.newMeth(C$, 'min$java_util_Collection$java_util_Comparator', function (collection, comparator) {
var it = collection.iterator();
var min = it.next();
while (it.hasNext()){
var next = it.next();
if (comparator.compare$TT$TT(min, next) > 0) {
min=next;
}}
return min;
}, 1);

Clazz.newMeth(C$, 'nCopies$I$TT', function (length, object) {
return Clazz.new_((I$[31]||$incl$(31)).c$$I$TE,[length, object]);
}, 1);

Clazz.newMeth(C$, 'reverse$java_util_List', function (list) {
var size = list.size();
var front = list.listIterator();
var back = list.listIterator$I(size);
for (var i = 0; i < (size/2|0); i++) {
var frontNext = front.next();
var backPrev = back.previous();
front.set$TE(backPrev);
back.set$TE(frontNext);
}
}, 1);

Clazz.newMeth(C$, 'reverseOrder', function () {
return Clazz.new_((I$[32]||$incl$(32)));
}, 1);

Clazz.newMeth(C$, 'reverseOrder$java_util_Comparator', function (c) {
if (c == null ) {
return C$.reverseOrder();
}return Clazz.new_((I$[33]||$incl$(33)).c$$java_util_Comparator,[c]);
}, 1);

Clazz.newMeth(C$, 'shuffle$java_util_List', function (list) {
C$.shuffle$java_util_List$java_util_Random(list, Clazz.new_((I$[34]||$incl$(34))));
}, 1);

Clazz.newMeth(C$, 'shuffle$java_util_List$java_util_Random', function (list, random) {
if (!(Clazz.instanceOf(list, "java.util.RandomAccess"))) {
var array = list.toArray();
for (var i = array.length - 1; i > 0; i--) {
var index = random.nextInt() % (i + 1);
if (index < 0) {
index=-index;
}var temp = array[i];
array[i]=array[index];
array[index]=temp;
}
var i = 0;
var it = list.listIterator();
while (it.hasNext()){
it.next();
it.set$TE(array[i++]);
}
} else {
var rawList = list;
for (var i = rawList.size() - 1; i > 0; i--) {
var index = random.nextInt() % (i + 1);
if (index < 0) {
index=-index;
}rawList.set$I$TE(index, rawList.set$I$TE(i, rawList.get$I(index)));
}
}}, 1);

Clazz.newMeth(C$, 'singleton$TE', function (object) {
return Clazz.new_((I$[35]||$incl$(35)).c$$TE,[object]);
}, 1);

Clazz.newMeth(C$, 'singletonList$TE', function (object) {
return Clazz.new_((I$[36]||$incl$(36)).c$$TE,[object]);
}, 1);

Clazz.newMeth(C$, 'singletonMap$TK$TV', function (key, value) {
return Clazz.new_((I$[37]||$incl$(37)).c$$TK$TV,[key, value]);
}, 1);

Clazz.newMeth(C$, 'sort$java_util_List', function (list) {
var array = list.toArray();
(I$[22]||$incl$(22)).sort$OA(array);
var i = 0;
var it = list.listIterator();
while (it.hasNext()){
it.next();
it.set$TE(array[i++]);
}
}, 1);

Clazz.newMeth(C$, 'sort$java_util_List$java_util_Comparator', function (list, comparator) {
var array = list.toArray$TTA(Clazz.array(java.lang.Object, [list.size()]));
(I$[22]||$incl$(22)).sort$TTA$java_util_Comparator(array, comparator);
var i = 0;
var it = list.listIterator();
while (it.hasNext()){
it.next();
it.set$TE(array[i++]);
}
}, 1);

Clazz.newMeth(C$, 'swap$java_util_List$I$I', function (list, index1, index2) {
if (list == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (index1 == index2) {
return;
}var rawList = list;
rawList.set$I$TE(index2, rawList.set$I$TE(index1, rawList.get$I(index2)));
}, 1);

Clazz.newMeth(C$, 'replaceAll$java_util_List$TT$TT', function (list, obj, obj2) {
var index;
var found = false;
while ((index=list.indexOf$O(obj)) > -1){
found=true;
list.set$I$TE(index, obj2);
}
return found;
}, 1);

Clazz.newMeth(C$, 'rotate$java_util_List$I', function (lst, dist) {
var list = lst;
var size = list.size();
if (size == 0) {
return;
}var normdist;
if (dist > 0) {
normdist=dist % size;
} else {
normdist=size - ((dist % size) * -1);
}if (normdist == 0 || normdist == size ) {
return;
}if (Clazz.instanceOf(list, "java.util.RandomAccess")) {
var temp = list.get$I(0);
var index = 0;
var beginIndex = 0;
for (var i = 0; i < size; i++) {
index=(index + normdist) % size;
temp=list.set$I$TE(index, temp);
if (index == beginIndex) {
index=++beginIndex;
temp=list.get$I(beginIndex);
}}
} else {
var divideIndex = (size - normdist) % size;
var sublist1 = list.subList$I$I(0, divideIndex);
var sublist2 = list.subList$I$I(divideIndex, size);
C$.reverse$java_util_List(sublist1);
C$.reverse$java_util_List(sublist2);
C$.reverse$java_util_List(list);
}}, 1);

Clazz.newMeth(C$, 'indexOfSubList$java_util_List$java_util_List', function (list, sublist) {
var size = list.size();
var sublistSize = sublist.size();
if (sublistSize > size) {
return -1;
}if (sublistSize == 0) {
return 0;
}var firstObj = sublist.get$I(0);
var index = list.indexOf$O(firstObj);
if (index == -1) {
return -1;
}while (index < size && (size - index >= sublistSize) ){
var listIt = list.listIterator$I(index);
if ((firstObj == null ) ? listIt.next() == null  : firstObj.equals$O(listIt.next())) {
var sublistIt = sublist.listIterator$I(1);
var difFound = false;
while (sublistIt.hasNext()){
var element = sublistIt.next();
if (!listIt.hasNext()) {
return -1;
}if ((element == null ) ? listIt.next() != null  : !element.equals$O(listIt.next())) {
difFound=true;
break;
}}
if (!difFound) {
return index;
}}index++;
}
return -1;
}, 1);

Clazz.newMeth(C$, 'lastIndexOfSubList$java_util_List$java_util_List', function (list, sublist) {
var sublistSize = sublist.size();
var size = list.size();
if (sublistSize > size) {
return -1;
}if (sublistSize == 0) {
return size;
}var lastObj = sublist.get$I(sublistSize - 1);
var index = list.lastIndexOf$O(lastObj);
while ((index > -1) && (index + 1 >= sublistSize) ){
var listIt = list.listIterator$I(index + 1);
if ((lastObj == null ) ? listIt.previous() == null  : lastObj.equals$O(listIt.previous())) {
var sublistIt = sublist.listIterator$I(sublistSize - 1);
var difFound = false;
while (sublistIt.hasPrevious()){
var element = sublistIt.previous();
if (!listIt.hasPrevious()) {
return -1;
}if ((element == null ) ? listIt.previous() != null  : !element.equals$O(listIt.previous())) {
difFound=true;
break;
}}
if (!difFound) {
return listIt.nextIndex();
}}index--;
}
return -1;
}, 1);

Clazz.newMeth(C$, 'list$java_util_Enumeration', function (enumeration) {
var list = Clazz.new_((I$[38]||$incl$(38)));
while (enumeration.hasMoreElements()){
list.add$TE(enumeration.nextElement());
}
return list;
}, 1);

Clazz.newMeth(C$, 'synchronizedCollection$java_util_Collection', function (collection) {
if (collection == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}return Clazz.new_((I$[10]||$incl$(10)).c$$java_util_Collection,[collection]);
}, 1);

Clazz.newMeth(C$, 'synchronizedList$java_util_List', function (list) {
if (list == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (Clazz.instanceOf(list, "java.util.RandomAccess")) {
return Clazz.new_((I$[8]||$incl$(8)).c$$java_util_List,[list]);
}return Clazz.new_((I$[7]||$incl$(7)).c$$java_util_List,[list]);
}, 1);

Clazz.newMeth(C$, 'synchronizedMap$java_util_Map', function (map) {
if (map == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}return Clazz.new_((I$[39]||$incl$(39)).c$$java_util_Map,[map]);
}, 1);

Clazz.newMeth(C$, 'synchronizedSet$java_util_Set', function (set) {
if (set == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}return Clazz.new_((I$[9]||$incl$(9)).c$$java_util_Set,[set]);
}, 1);

Clazz.newMeth(C$, 'synchronizedSortedMap$java_util_SortedMap', function (map) {
if (map == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}return Clazz.new_((I$[40]||$incl$(40)).c$$java_util_SortedMap,[map]);
}, 1);

Clazz.newMeth(C$, 'synchronizedSortedSet$java_util_SortedSet', function (set) {
if (set == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}return Clazz.new_((I$[41]||$incl$(41)).c$$java_util_SortedSet,[set]);
}, 1);

Clazz.newMeth(C$, 'unmodifiableCollection$java_util_Collection', function (collection) {
if (collection == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}return Clazz.new_((I$[20]||$incl$(20)).c$$java_util_Collection,[collection]);
}, 1);

Clazz.newMeth(C$, 'unmodifiableList$java_util_List', function (list) {
if (list == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (Clazz.instanceOf(list, "java.util.RandomAccess")) {
return Clazz.new_((I$[13]||$incl$(13)).c$$java_util_List,[list]);
}return Clazz.new_((I$[14]||$incl$(14)).c$$java_util_List,[list]);
}, 1);

Clazz.newMeth(C$, 'unmodifiableMap$java_util_Map', function (map) {
if (map == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}return Clazz.new_((I$[42]||$incl$(42)).c$$java_util_Map,[map]);
}, 1);

Clazz.newMeth(C$, 'unmodifiableSet$java_util_Set', function (set) {
if (set == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}return Clazz.new_((I$[19]||$incl$(19)).c$$java_util_Set,[set]);
}, 1);

Clazz.newMeth(C$, 'unmodifiableSortedMap$java_util_SortedMap', function (map) {
if (map == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}return Clazz.new_((I$[43]||$incl$(43)).c$$java_util_SortedMap,[map]);
}, 1);

Clazz.newMeth(C$, 'unmodifiableSortedSet$java_util_SortedSet', function (set) {
if (set == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}return Clazz.new_((I$[44]||$incl$(44)).c$$java_util_SortedSet,[set]);
}, 1);

Clazz.newMeth(C$, 'frequency$java_util_Collection$O', function (c, o) {
if (c == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (c.isEmpty()) {
return 0;
}var result = 0;
var itr = c.iterator();
while (itr.hasNext()){
var e = itr.next();
if (o == null  ? e == null  : o.equals$O(e)) {
result++;
}}
return result;
}, 1);

Clazz.newMeth(C$, 'emptyList', function () {
return C$.EMPTY_LIST;
}, 1);

Clazz.newMeth(C$, 'emptySet', function () {
return C$.EMPTY_SET;
}, 1);

Clazz.newMeth(C$, 'emptyMap', function () {
return C$.EMPTY_MAP;
}, 1);

Clazz.newMeth(C$, 'checkedCollection$java_util_Collection$Class', function (c, type) {
return Clazz.new_((I$[45]||$incl$(45)).c$$java_util_Collection$Class,[c, type]);
}, 1);

Clazz.newMeth(C$, 'checkedMap$java_util_Map$Class$Class', function (m, keyType, valueType) {
return Clazz.new_((I$[46]||$incl$(46)).c$$java_util_Map$Class$Class,[m, keyType, valueType]);
}, 1);

Clazz.newMeth(C$, 'checkedList$java_util_List$Class', function (list, type) {
if (Clazz.instanceOf(list, "java.util.RandomAccess")) {
return Clazz.new_((I$[47]||$incl$(47)).c$$java_util_List$Class,[list, type]);
}return Clazz.new_((I$[48]||$incl$(48)).c$$java_util_List$Class,[list, type]);
}, 1);

Clazz.newMeth(C$, 'checkedSet$java_util_Set$Class', function (s, type) {
return Clazz.new_((I$[49]||$incl$(49)).c$$java_util_Set$Class,[s, type]);
}, 1);

Clazz.newMeth(C$, 'checkedSortedMap$java_util_SortedMap$Class$Class', function (m, keyType, valueType) {
return Clazz.new_((I$[50]||$incl$(50)).c$$java_util_SortedMap$Class$Class,[m, keyType, valueType]);
}, 1);

Clazz.newMeth(C$, 'checkedSortedSet$java_util_SortedSet$Class', function (s, type) {
return Clazz.new_((I$[51]||$incl$(51)).c$$java_util_SortedSet$Class,[s, type]);
}, 1);

Clazz.newMeth(C$, 'addAll$java_util_Collection$TTA', function (c, a) {
var modified = false;
for (var i = 0; i < a.length; i++) {
modified|=c.add$TE(a[i]);
}
return modified;
}, 1);

Clazz.newMeth(C$, 'disjoint$java_util_Collection$java_util_Collection', function (c1, c2) {
if ((Clazz.instanceOf(c1, "java.util.Set")) && !(Clazz.instanceOf(c2, "java.util.Set"))  || (c2.size()) > c1.size() ) {
var tmp = c1;
c1=c2;
c2=tmp;
}var it = c1.iterator();
while (it.hasNext()){
if (c2.contains$O(it.next())) {
return false;
}}
return true;
}, 1);

Clazz.newMeth(C$, 'checkType$TE$Class', function (obj, type) {
if (!type.isInstance$O(obj)) {
throw Clazz.new_(Clazz.load('java.lang.ClassCastException').c$$S,["Attempt to insert " + obj.getClass() + " element into collection with element type " + type ]);
}return obj;
}, 1);
;
(function(){var C$=Clazz.newClass(P$.Collections, "CopiesList", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.AbstractList', 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.n = 0;
this.element = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$TE', function (length, object) {
Clazz.super_(C$, this,1);
if (length < 0) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}this.n=length;
this.element=object;
}, 1);

Clazz.newMeth(C$, 'contains$O', function (object) {
return this.element == null  ? object == null  : this.element.equals$O(object);
});

Clazz.newMeth(C$, 'size', function () {
return this.n;
});

Clazz.newMeth(C$, 'get$I', function (location) {
if (0 <= location && location < this.n ) {
return this.element;
}throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "EmptyList", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.AbstractList', 'java.io.Serializable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'contains$O', function (object) {
return false;
});

Clazz.newMeth(C$, 'size', function () {
return 0;
});

Clazz.newMeth(C$, 'get$I', function (location) {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
});

Clazz.newMeth(C$, 'readResolve', function () {
return (I$[1]||$incl$(1)).EMPTY_LIST;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "EmptySet", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.AbstractSet', 'java.io.Serializable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'contains$O', function (object) {
return false;
});

Clazz.newMeth(C$, 'size', function () {
return 0;
});

Clazz.newMeth(C$, 'iterator', function () {
return ((
(function(){var C$=Clazz.newClass(P$, "Collections$EmptySet$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Iterator', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return false;
});

Clazz.newMeth(C$, 'next', function () {
throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$, 'remove', function () {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});
})()
), Clazz.new_((I$[2]||$incl$(2)).$init$, [this, null]));
});

Clazz.newMeth(C$, 'readResolve', function () {
return (I$[1]||$incl$(1)).EMPTY_SET;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "EmptyMap", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.AbstractMap', 'java.io.Serializable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'containsKey$O', function (key) {
return false;
});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
return false;
});

Clazz.newMeth(C$, 'entrySet', function () {
return (I$[1]||$incl$(1)).EMPTY_SET;
});

Clazz.newMeth(C$, 'get$O', function (key) {
return null;
});

Clazz.newMeth(C$, 'keySet', function () {
return (I$[1]||$incl$(1)).EMPTY_SET;
});

Clazz.newMeth(C$, 'values', function () {
return (I$[1]||$incl$(1)).EMPTY_LIST;
});

Clazz.newMeth(C$, 'readResolve', function () {
return (I$[1]||$incl$(1)).EMPTY_MAP;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "ReverseComparator", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, ['java.util.Comparator', 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['compare$TT$TT'], function (o1, o2) {
var c2 = o2;
return c2.compareTo$TT(o1);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "ReverseComparatorWithComparator", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, ['java.util.Comparator', 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.comparator = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Comparator', function (comparator) {
C$.$init$.apply(this);
this.comparator=comparator;
}, 1);

Clazz.newMeth(C$, ['compare$TT$TT'], function (o1, o2) {
return this.comparator.compare$TT$TT(o2, o1);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "SingletonSet", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.AbstractSet', 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.element = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$TE'], function (object) {
Clazz.super_(C$, this,1);
this.element=object;
}, 1);

Clazz.newMeth(C$, 'contains$O', function (object) {
return this.element == null  ? object == null  : this.element.equals$O(object);
});

Clazz.newMeth(C$, 'size', function () {
return 1;
});

Clazz.newMeth(C$, 'iterator', function () {
return ((
(function(){var C$=Clazz.newClass(P$, "Collections$SingletonSet$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Iterator', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$hasNext = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$hasNext = true;
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.$hasNext;
});

Clazz.newMeth(C$, 'next', function () {
if (this.$hasNext) {
this.$hasNext=false;
return this.b$['java.util.Collections.SingletonSet'].element;
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$, 'remove', function () {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});
})()
), Clazz.new_((I$[3]||$incl$(3)).$init$, [this, null]));
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "SingletonList", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.AbstractList', 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.element = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$TE'], function (object) {
Clazz.super_(C$, this,1);
this.element=object;
}, 1);

Clazz.newMeth(C$, 'contains$O', function (object) {
return this.element == null  ? object == null  : this.element.equals$O(object);
});

Clazz.newMeth(C$, 'get$I', function (location) {
if (location == 0) {
return this.element;
}throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
});

Clazz.newMeth(C$, 'size', function () {
return 1;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "SingletonMap", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.AbstractMap', 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.k = null;
this.v = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$TK$TV'], function (key, value) {
Clazz.super_(C$, this,1);
this.k=key;
this.v=value;
}, 1);

Clazz.newMeth(C$, 'containsKey$O', function (key) {
return this.k == null  ? key == null  : this.k.equals$O(key);
});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
return this.v == null  ? value == null  : this.v.equals$O(value);
});

Clazz.newMeth(C$, 'get$O', function (key) {
if (this.containsKey$O(key)) {
return this.v;
}return null;
});

Clazz.newMeth(C$, 'size', function () {
return 1;
});

Clazz.newMeth(C$, 'entrySet', function () {
return ((
(function(){var C$=Clazz.newClass(P$, "Collections$SingletonMap$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.util.AbstractSet'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'contains$O', function (object) {
if (Clazz.instanceOf(object, "java.util.Map.Entry")) {
var entry = object;
return this.b$['java.util.Collections.SingletonMap'].containsKey$O(entry.getKey()) && this.b$['java.util.Collections.SingletonMap'].containsValue$O(entry.getValue()) ;
}return false;
});

Clazz.newMeth(C$, 'size', function () {
return 1;
});

Clazz.newMeth(C$, 'iterator', function () {
return ((
(function(){var C$=Clazz.newClass(P$, "Collections$SingletonMap$1$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Iterator', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$hasNext = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$hasNext = true;
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.$hasNext;
});

Clazz.newMeth(C$, 'next', function () {
if (this.$hasNext) {
this.$hasNext=false;
return ((
(function(){var C$=Clazz.newClass(P$, "Collections$SingletonMap$1$1$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, [['java.util.Map','java.util.Map.Entry']], 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'equals$O', function (object) {
return this.b$['java.util.Collections$SingletonMap$1'].contains$O(object);
});

Clazz.newMeth(C$, 'getKey', function () {
return this.b$['java.util.Collections.SingletonMap'].k;
});

Clazz.newMeth(C$, 'getValue', function () {
return this.b$['java.util.Collections.SingletonMap'].v;
});

Clazz.newMeth(C$, 'hashCode', function () {
return (this.b$['java.util.Collections.SingletonMap'].k == null  ? 0 : this.b$['java.util.Collections.SingletonMap'].k.hashCode()) ^ (this.b$['java.util.Collections.SingletonMap'].v == null  ? 0 : this.b$['java.util.Collections.SingletonMap'].v.hashCode());
});

Clazz.newMeth(C$, ['setValue$TV'], function (value) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});
})()
), Clazz.new_((I$[4]||$incl$(4)).$init$, [this, null]));
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$, 'remove', function () {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});
})()
), Clazz.new_((I$[5]||$incl$(5)).$init$, [this, null]));
});
})()
), Clazz.new_((I$[6]||$incl$(6)), [this, null],P$.Collections$SingletonMap$1));
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "SynchronizedCollection", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, ['java.util.Collection', 'java.io.Serializable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.c = null;
this.mutex = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Collection', function (collection) {
C$.$init$.apply(this);
this.c=collection;
this.mutex=this;
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Collection$O', function (collection, mutex) {
C$.$init$.apply(this);
this.c=collection;
this.mutex=mutex;
}, 1);

Clazz.newMeth(C$, ['add$TE'], function (object) {
{
return this.c.add$TE(object);
}});

Clazz.newMeth(C$, 'addAll$java_util_Collection', function (collection) {
{
return this.c.addAll$java_util_Collection(collection);
}});

Clazz.newMeth(C$, 'clear', function () {
{
this.c.clear();
}});

Clazz.newMeth(C$, 'contains$O', function (object) {
{
return this.c.contains$O(object);
}});

Clazz.newMeth(C$, 'containsAll$java_util_Collection', function (collection) {
{
return this.c.containsAll$java_util_Collection(collection);
}});

Clazz.newMeth(C$, 'isEmpty', function () {
{
return this.c.isEmpty();
}});

Clazz.newMeth(C$, 'iterator', function () {
{
return this.c.iterator();
}});

Clazz.newMeth(C$, 'remove$O', function (object) {
{
return this.c.remove$O(object);
}});

Clazz.newMeth(C$, 'removeAll$java_util_Collection', function (collection) {
{
return this.c.removeAll$java_util_Collection(collection);
}});

Clazz.newMeth(C$, 'retainAll$java_util_Collection', function (collection) {
{
return this.c.retainAll$java_util_Collection(collection);
}});

Clazz.newMeth(C$, 'size', function () {
{
return this.c.size();
}});

Clazz.newMeth(C$, 'toArray', function () {
{
return this.c.toArray();
}});

Clazz.newMeth(C$, 'toString', function () {
{
return this.c.toString();
}});

Clazz.newMeth(C$, 'toArray$TTA', function (array) {
{
return this.c.toArray$TTA(array);
}});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (stream) {
{
stream.defaultWriteObject();
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "SynchronizedRandomAccessList", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.SynchronizedList'], 'java.util.RandomAccess');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_List', function (l) {
C$.superclazz.c$$java_util_List.apply(this, [l]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_List$O', function (l, mutex) {
C$.superclazz.c$$java_util_List$O.apply(this, [l, mutex]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'subList$I$I', function (start, end) {
{
return Clazz.new_(C$.c$$java_util_List$O,[this.list.subList$I$I(start, end), this.mutex]);
}});

Clazz.newMeth(C$, 'writeReplace', function () {
return Clazz.new_((I$[7]||$incl$(7)).c$$java_util_List,[this.list]);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "SynchronizedList", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.SynchronizedCollection'], 'java.util.List');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.list = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_List', function (l) {
C$.superclazz.c$$java_util_Collection.apply(this, [l]);
C$.$init$.apply(this);
this.list=l;
}, 1);

Clazz.newMeth(C$, 'c$$java_util_List$O', function (l, mutex) {
C$.superclazz.c$$java_util_Collection$O.apply(this, [l, mutex]);
C$.$init$.apply(this);
this.list=l;
}, 1);

Clazz.newMeth(C$, 'add$I$TE', function (location, object) {
{
this.list.add$I$TE(location, object);
}});

Clazz.newMeth(C$, 'addAll$I$java_util_Collection', function (location, collection) {
{
return this.list.addAll$I$java_util_Collection(location, collection);
}});

Clazz.newMeth(C$, 'equals$O', function (object) {
{
return this.list.equals$O(object);
}});

Clazz.newMeth(C$, 'get$I', function (location) {
{
return this.list.get$I(location);
}});

Clazz.newMeth(C$, 'hashCode', function () {
{
return this.list.hashCode();
}});

Clazz.newMeth(C$, 'indexOf$O', function (object) {
{
return this.list.indexOf$O(object);
}});

Clazz.newMeth(C$, 'lastIndexOf$O', function (object) {
{
return this.list.lastIndexOf$O(object);
}});

Clazz.newMeth(C$, 'listIterator', function () {
{
return this.list.listIterator();
}});

Clazz.newMeth(C$, 'listIterator$I', function (location) {
{
return this.list.listIterator$I(location);
}});

Clazz.newMeth(C$, 'remove$I', function (location) {
{
return this.list.remove$I(location);
}});

Clazz.newMeth(C$, 'set$I$TE', function (location, object) {
{
return this.list.set$I$TE(location, object);
}});

Clazz.newMeth(C$, 'subList$I$I', function (start, end) {
{
return Clazz.new_(C$.c$$java_util_List$O,[this.list.subList$I$I(start, end), this.mutex]);
}});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (stream) {
{
stream.defaultWriteObject();
}});

Clazz.newMeth(C$, 'readResolve', function () {
if (Clazz.instanceOf(this.list, "java.util.RandomAccess")) {
return Clazz.new_((I$[8]||$incl$(8)).c$$java_util_List$O,[this.list, this.mutex]);
}return this;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "SynchronizedMap", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, ['java.util.Map', 'java.io.Serializable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.m = null;
this.mutex = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_Map'], function (map) {
C$.$init$.apply(this);
this.m=map;
this.mutex=this;
}, 1);

Clazz.newMeth(C$, ['c$$java_util_Map$O'], function (map, mutex) {
C$.$init$.apply(this);
this.m=map;
this.mutex=mutex;
}, 1);

Clazz.newMeth(C$, 'clear', function () {
{
this.m.clear();
}});

Clazz.newMeth(C$, 'containsKey$O', function (key) {
{
return this.m.containsKey$O(key);
}});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
{
return this.m.containsValue$O(value);
}});

Clazz.newMeth(C$, 'entrySet', function () {
{
return Clazz.new_((I$[9]||$incl$(9)).c$$java_util_Set$O,[this.m.entrySet(), this.mutex]);
}});

Clazz.newMeth(C$, 'equals$O', function (object) {
{
return this.m.equals$O(object);
}});

Clazz.newMeth(C$, 'get$O', function (key) {
{
return this.m.get$O(key);
}});

Clazz.newMeth(C$, 'hashCode', function () {
{
return this.m.hashCode();
}});

Clazz.newMeth(C$, 'isEmpty', function () {
{
return this.m.isEmpty();
}});

Clazz.newMeth(C$, 'keySet', function () {
{
return Clazz.new_((I$[9]||$incl$(9)).c$$java_util_Set$O,[this.m.keySet(), this.mutex]);
}});

Clazz.newMeth(C$, ['put$TK$TV'], function (key, value) {
{
return this.m.put$TK$TV(key, value);
}});

Clazz.newMeth(C$, 'putAll$java_util_Map', function (map) {
{
this.m.putAll$java_util_Map(map);
}});

Clazz.newMeth(C$, 'remove$O', function (key) {
{
return this.m.remove$O(key);
}});

Clazz.newMeth(C$, 'size', function () {
{
return this.m.size();
}});

Clazz.newMeth(C$, 'values', function () {
{
return Clazz.new_((I$[10]||$incl$(10)).c$$java_util_Collection$O,[this.m.values(), this.mutex]);
}});

Clazz.newMeth(C$, 'toString', function () {
{
return this.m.toString();
}});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (stream) {
{
stream.defaultWriteObject();
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "SynchronizedSet", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.SynchronizedCollection'], 'java.util.Set');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Set', function (set) {
C$.superclazz.c$$java_util_Collection.apply(this, [set]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Set$O', function (set, mutex) {
C$.superclazz.c$$java_util_Collection$O.apply(this, [set, mutex]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'equals$O', function (object) {
{
return this.c.equals$O(object);
}});

Clazz.newMeth(C$, 'hashCode', function () {
{
return this.c.hashCode();
}});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (stream) {
{
stream.defaultWriteObject();
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "SynchronizedSortedMap", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.SynchronizedMap'], 'java.util.SortedMap');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.sm = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_SortedMap'], function (map) {
C$.superclazz.c$$java_util_Map.apply(this, [map]);
C$.$init$.apply(this);
this.sm=map;
}, 1);

Clazz.newMeth(C$, ['c$$java_util_SortedMap$O'], function (map, mutex) {
C$.superclazz.c$$java_util_Map$O.apply(this, [map, mutex]);
C$.$init$.apply(this);
this.sm=map;
}, 1);

Clazz.newMeth(C$, 'comparator', function () {
{
return this.sm.comparator();
}});

Clazz.newMeth(C$, 'firstKey', function () {
{
return this.sm.firstKey();
}});

Clazz.newMeth(C$, ['headMap$TK'], function (endKey) {
{
return Clazz.new_(C$.c$$java_util_SortedMap$O,[this.sm.headMap$TK(endKey), this.mutex]);
}});

Clazz.newMeth(C$, 'lastKey', function () {
{
return this.sm.lastKey();
}});

Clazz.newMeth(C$, ['subMap$TK$TK'], function (startKey, endKey) {
{
return Clazz.new_(C$.c$$java_util_SortedMap$O,[this.sm.subMap$TK$TK(startKey, endKey), this.mutex]);
}});

Clazz.newMeth(C$, ['tailMap$TK'], function (startKey) {
{
return Clazz.new_(C$.c$$java_util_SortedMap$O,[this.sm.tailMap$TK(startKey), this.mutex]);
}});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (stream) {
{
stream.defaultWriteObject();
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "SynchronizedSortedSet", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.SynchronizedSet'], 'java.util.SortedSet');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.ss = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_SortedSet', function (set) {
C$.superclazz.c$$java_util_Set.apply(this, [set]);
C$.$init$.apply(this);
this.ss=set;
}, 1);

Clazz.newMeth(C$, 'c$$java_util_SortedSet$O', function (set, mutex) {
C$.superclazz.c$$java_util_Set$O.apply(this, [set, mutex]);
C$.$init$.apply(this);
this.ss=set;
}, 1);

Clazz.newMeth(C$, 'comparator', function () {
{
return this.ss.comparator();
}});

Clazz.newMeth(C$, 'first', function () {
{
return this.ss.first();
}});

Clazz.newMeth(C$, ['headSet$TE'], function (end) {
{
return Clazz.new_(C$.c$$java_util_SortedSet$O,[this.ss.headSet$TE(end), this.mutex]);
}});

Clazz.newMeth(C$, 'last', function () {
{
return this.ss.last();
}});

Clazz.newMeth(C$, ['subSet$TE$TE'], function (start, end) {
{
return Clazz.new_(C$.c$$java_util_SortedSet$O,[this.ss.subSet$TE$TE(start, end), this.mutex]);
}});

Clazz.newMeth(C$, ['tailSet$TE'], function (start) {
{
return Clazz.new_(C$.c$$java_util_SortedSet$O,[this.ss.tailSet$TE(start), this.mutex]);
}});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (stream) {
{
stream.defaultWriteObject();
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "UnmodifiableCollection", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, ['java.util.Collection', 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.c = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Collection', function (collection) {
C$.$init$.apply(this);
this.c=collection;
}, 1);

Clazz.newMeth(C$, ['add$TE'], function (object) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'addAll$java_util_Collection', function (collection) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'clear', function () {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'contains$O', function (object) {
return this.c.contains$O(object);
});

Clazz.newMeth(C$, 'containsAll$java_util_Collection', function (collection) {
return this.c.containsAll$java_util_Collection(collection);
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.c.isEmpty();
});

Clazz.newMeth(C$, 'iterator', function () {
return ((
(function(){var C$=Clazz.newClass(P$, "Collections$UnmodifiableCollection$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Iterator', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.iterator = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.iterator = this.b$['java.util.Collections.UnmodifiableCollection'].c.iterator();
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.iterator.hasNext();
});

Clazz.newMeth(C$, 'next', function () {
return this.iterator.next();
});

Clazz.newMeth(C$, 'remove', function () {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});
})()
), Clazz.new_((I$[11]||$incl$(11)).$init$, [this, null]));
});

Clazz.newMeth(C$, 'remove$O', function (object) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'removeAll$java_util_Collection', function (collection) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'retainAll$java_util_Collection', function (collection) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'size', function () {
return this.c.size();
});

Clazz.newMeth(C$, 'toArray', function () {
return this.c.toArray();
});

Clazz.newMeth(C$, 'toArray$TTA', function (array) {
return this.c.toArray$TTA(array);
});

Clazz.newMeth(C$, 'toString', function () {
return this.c.toString();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "UnmodifiableList", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.UnmodifiableCollection'], 'java.util.List');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.list = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_List', function (l) {
C$.superclazz.c$$java_util_Collection.apply(this, [l]);
C$.$init$.apply(this);
this.list=l;
}, 1);

Clazz.newMeth(C$, 'add$I$TE', function (location, object) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'addAll$I$java_util_Collection', function (location, collection) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'equals$O', function (object) {
return this.list.equals$O(object);
});

Clazz.newMeth(C$, 'get$I', function (location) {
return this.list.get$I(location);
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.list.hashCode();
});

Clazz.newMeth(C$, 'indexOf$O', function (object) {
return this.list.indexOf$O(object);
});

Clazz.newMeth(C$, 'lastIndexOf$O', function (object) {
return this.list.lastIndexOf$O(object);
});

Clazz.newMeth(C$, 'listIterator', function () {
return this.listIterator$I(0);
});

Clazz.newMeth(C$, 'listIterator$I', function (location) {
return ((
(function(){var C$=Clazz.newClass(P$, "Collections$UnmodifiableList$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.ListIterator', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.iterator = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.iterator = this.b$['java.util.Collections.UnmodifiableList'].list.listIterator$I(this.$finals.location);
}, 1);

Clazz.newMeth(C$, ['add$TE'], function (object) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'hasNext', function () {
return this.iterator.hasNext();
});

Clazz.newMeth(C$, 'hasPrevious', function () {
return this.iterator.hasPrevious();
});

Clazz.newMeth(C$, 'next', function () {
return this.iterator.next();
});

Clazz.newMeth(C$, 'nextIndex', function () {
return this.iterator.nextIndex();
});

Clazz.newMeth(C$, 'previous', function () {
return this.iterator.previous();
});

Clazz.newMeth(C$, 'previousIndex', function () {
return this.iterator.previousIndex();
});

Clazz.newMeth(C$, 'remove', function () {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, ['set$TE'], function (object) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});
})()
), Clazz.new_((I$[12]||$incl$(12)).$init$, [this, {location: location}]));
});

Clazz.newMeth(C$, 'remove$I', function (location) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'set$I$TE', function (location, object) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'subList$I$I', function (start, end) {
return Clazz.new_(C$.c$$java_util_List,[this.list.subList$I$I(start, end)]);
});

Clazz.newMeth(C$, 'readResolve', function () {
if (Clazz.instanceOf(this.list, "java.util.RandomAccess")) {
return Clazz.new_((I$[13]||$incl$(13)).c$$java_util_List,[this.list]);
}return this;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "UnmodifiableRandomAccessList", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.UnmodifiableList'], 'java.util.RandomAccess');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_List', function (l) {
C$.superclazz.c$$java_util_List.apply(this, [l]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'subList$I$I', function (start, end) {
return Clazz.new_(C$.c$$java_util_List,[this.list.subList$I$I(start, end)]);
});

Clazz.newMeth(C$, 'writeReplace', function () {
return Clazz.new_((I$[14]||$incl$(14)).c$$java_util_List,[this.list]);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "UnmodifiableSet", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.UnmodifiableCollection'], 'java.util.Set');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Set', function (set) {
C$.superclazz.c$$java_util_Collection.apply(this, [set]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'equals$O', function (object) {
return this.c.equals$O(object);
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.c.hashCode();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "UnmodifiableMap", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, ['java.util.Map', 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.m = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_Map'], function (map) {
C$.$init$.apply(this);
this.m=map;
}, 1);

Clazz.newMeth(C$, 'clear', function () {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'containsKey$O', function (key) {
return this.m.containsKey$O(key);
});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
return this.m.containsValue$O(value);
});

Clazz.newMeth(C$, 'entrySet', function () {
return Clazz.new_((I$[18]||$incl$(18)).c$$java_util_Set,[this.m.entrySet()]);
});

Clazz.newMeth(C$, 'equals$O', function (object) {
return this.m.equals$O(object);
});

Clazz.newMeth(C$, 'get$O', function (key) {
return this.m.get$O(key);
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.m.hashCode();
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.m.isEmpty();
});

Clazz.newMeth(C$, 'keySet', function () {
return Clazz.new_((I$[19]||$incl$(19)).c$$java_util_Set,[this.m.keySet()]);
});

Clazz.newMeth(C$, ['put$TK$TV'], function (key, value) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'putAll$java_util_Map', function (map) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'remove$O', function (key) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'size', function () {
return this.m.size();
});

Clazz.newMeth(C$, 'values', function () {
return Clazz.new_((I$[20]||$incl$(20)).c$$java_util_Collection,[this.m.values()]);
});

Clazz.newMeth(C$, 'toString', function () {
return this.m.toString();
});
;
(function(){var C$=Clazz.newClass(P$.Collections.UnmodifiableMap, "UnmodifiableEntrySet", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.UnmodifiableSet']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_Set'], function (set) {
C$.superclazz.c$$java_util_Set.apply(this, [set]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return ((
(function(){var C$=Clazz.newClass(P$, "Collections$UnmodifiableMap$UnmodifiableEntrySet$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Iterator', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.iterator = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.iterator = this.b$['java.util.Collections.UnmodifiableMap.UnmodifiableEntrySet'].c.iterator();
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.iterator.hasNext();
});

Clazz.newMeth(C$, 'next', function () {
return Clazz.new_((I$[15]||$incl$(15)).c$$java_util_Map_Entry,[this.iterator.next()]);
});

Clazz.newMeth(C$, 'remove', function () {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});
})()
), Clazz.new_((I$[16]||$incl$(16)).$init$, [this, null]));
});

Clazz.newMeth(C$, 'toArray', function () {
var length = this.c.size();
var result = Clazz.array(java.lang.Object, [length]);
var it = this.iterator();
for (var i = length; --i >= 0; ) {
result[i]=it.next();
}
return result;
});

Clazz.newMeth(C$, 'toArray$TTA', function (contents) {
var size = this.c.size();
var index = 0;
var it = this.iterator();
if (size > contents.length) {
var ct = contents.getClass().getComponentType();
contents=Clazz.array(ct, size);
}while (index < size){
contents[index++]=it.next();
}
if (index < contents.length) {
contents[index]=null;
}return contents;
});
;
(function(){var C$=Clazz.newClass(P$.Collections.UnmodifiableMap.UnmodifiableEntrySet, "UnmodifiableMapEntry", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, [['java.util.Map','java.util.Map.Entry']]);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.mapEntry = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_Map_Entry'], function (entry) {
C$.$init$.apply(this);
this.mapEntry=entry;
}, 1);

Clazz.newMeth(C$, 'equals$O', function (object) {
return this.mapEntry.equals$O(object);
});

Clazz.newMeth(C$, 'getKey', function () {
return this.mapEntry.getKey();
});

Clazz.newMeth(C$, 'getValue', function () {
return this.mapEntry.getValue();
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.mapEntry.hashCode();
});

Clazz.newMeth(C$, ['setValue$TV'], function (object) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'toString', function () {
return this.mapEntry.toString();
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "UnmodifiableSortedMap", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.UnmodifiableMap'], 'java.util.SortedMap');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.sm = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_SortedMap'], function (map) {
C$.superclazz.c$$java_util_Map.apply(this, [map]);
C$.$init$.apply(this);
this.sm=map;
}, 1);

Clazz.newMeth(C$, 'comparator', function () {
return this.sm.comparator();
});

Clazz.newMeth(C$, 'firstKey', function () {
return this.sm.firstKey();
});

Clazz.newMeth(C$, ['headMap$TK'], function (before) {
return Clazz.new_(C$.c$$java_util_SortedMap,[this.sm.headMap$TK(before)]);
});

Clazz.newMeth(C$, 'lastKey', function () {
return this.sm.lastKey();
});

Clazz.newMeth(C$, ['subMap$TK$TK'], function (start, end) {
return Clazz.new_(C$.c$$java_util_SortedMap,[this.sm.subMap$TK$TK(start, end)]);
});

Clazz.newMeth(C$, ['tailMap$TK'], function (after) {
return Clazz.new_(C$.c$$java_util_SortedMap,[this.sm.tailMap$TK(after)]);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "UnmodifiableSortedSet", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.UnmodifiableSet'], 'java.util.SortedSet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.ss = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_SortedSet', function (set) {
C$.superclazz.c$$java_util_Set.apply(this, [set]);
C$.$init$.apply(this);
this.ss=set;
}, 1);

Clazz.newMeth(C$, 'comparator', function () {
return this.ss.comparator();
});

Clazz.newMeth(C$, 'first', function () {
return this.ss.first();
});

Clazz.newMeth(C$, ['headSet$TE'], function (before) {
return Clazz.new_(C$.c$$java_util_SortedSet,[this.ss.headSet$TE(before)]);
});

Clazz.newMeth(C$, 'last', function () {
return this.ss.last();
});

Clazz.newMeth(C$, ['subSet$TE$TE'], function (start, end) {
return Clazz.new_(C$.c$$java_util_SortedSet,[this.ss.subSet$TE$TE(start, end)]);
});

Clazz.newMeth(C$, ['tailSet$TE'], function (after) {
return Clazz.new_(C$.c$$java_util_SortedSet,[this.ss.tailSet$TE(after)]);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "CheckedCollection", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, ['java.util.Collection', 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.c = null;
this.type = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Collection$Class', function (c, type) {
C$.$init$.apply(this);
if (c == null  || type == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.c=c;
this.type=type;
}, 1);

Clazz.newMeth(C$, 'size', function () {
return this.c.size();
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.c.isEmpty();
});

Clazz.newMeth(C$, 'contains$O', function (obj) {
return this.c.contains$O(obj);
});

Clazz.newMeth(C$, 'iterator', function () {
var i = this.c.iterator();
if (Clazz.instanceOf(i, "java.util.ListIterator")) {
i=Clazz.new_((I$[21]||$incl$(21)).c$$java_util_ListIterator$Class,[i, this.type]);
}return i;
});

Clazz.newMeth(C$, 'toArray', function () {
return this.c.toArray();
});

Clazz.newMeth(C$, 'toArray$TTA', function (arr) {
return this.c.toArray$TTA(arr);
});

Clazz.newMeth(C$, ['add$TE'], function (obj) {
return this.c.add$TE(P$.Collections.checkType$TE$Class(obj, this.type));
});

Clazz.newMeth(C$, 'remove$O', function (obj) {
return this.c.remove$O(obj);
});

Clazz.newMeth(C$, 'containsAll$java_util_Collection', function (c1) {
return this.c.containsAll$java_util_Collection(c1);
});

Clazz.newMeth(C$, 'addAll$java_util_Collection', function (c1) {
var size = c1.size();
if (size == 0) {
return false;
}var arr = Clazz.array(java.lang.Object, [size]);
var it = c1.iterator();
for (var i = 0; i < size; i++) {
arr[i]=P$.Collections.checkType$TE$Class(it.next(), this.type);
}
var added = false;
for (var i = 0; i < size; i++) {
added|=this.c.add$TE(arr[i]);
}
return added;
});

Clazz.newMeth(C$, 'removeAll$java_util_Collection', function (c1) {
return this.c.removeAll$java_util_Collection(c1);
});

Clazz.newMeth(C$, 'retainAll$java_util_Collection', function (c1) {
return this.c.retainAll$java_util_Collection(c1);
});

Clazz.newMeth(C$, 'clear', function () {
this.c.clear();
});

Clazz.newMeth(C$, 'toString', function () {
return this.c.toString();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "CheckedListIterator", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, 'java.util.ListIterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.i = null;
this.type = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_ListIterator$Class', function (i, type) {
C$.$init$.apply(this);
this.i=i;
this.type=type;
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.i.hasNext();
});

Clazz.newMeth(C$, 'next', function () {
return this.i.next();
});

Clazz.newMeth(C$, 'remove', function () {
this.i.remove();
});

Clazz.newMeth(C$, 'hasPrevious', function () {
return this.i.hasPrevious();
});

Clazz.newMeth(C$, 'previous', function () {
return this.i.previous();
});

Clazz.newMeth(C$, 'nextIndex', function () {
return this.i.nextIndex();
});

Clazz.newMeth(C$, 'previousIndex', function () {
return this.i.previousIndex();
});

Clazz.newMeth(C$, ['set$TE'], function (obj) {
this.i.set$TE(P$.Collections.checkType$TE$Class(obj, this.type));
});

Clazz.newMeth(C$, ['add$TE'], function (obj) {
this.i.add$TE(P$.Collections.checkType$TE$Class(obj, this.type));
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "CheckedList", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.CheckedCollection'], 'java.util.List');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.l = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_List$Class', function (l, type) {
C$.superclazz.c$$java_util_Collection$Class.apply(this, [l, type]);
C$.$init$.apply(this);
this.l=l;
}, 1);

Clazz.newMeth(C$, 'addAll$I$java_util_Collection', function (index, c1) {
var size = c1.size();
if (size == 0) {
return false;
}var arr = Clazz.array(java.lang.Object, [size]);
var it = c1.iterator();
for (var i = 0; i < size; i++) {
arr[i]=P$.Collections.checkType$TE$Class(it.next(), this.type);
}
return this.l.addAll$I$java_util_Collection(index, (I$[22]||$incl$(22)).asList$TTA(arr));
});

Clazz.newMeth(C$, 'get$I', function (index) {
return this.l.get$I(index);
});

Clazz.newMeth(C$, 'set$I$TE', function (index, obj) {
return this.l.set$I$TE(index, P$.Collections.checkType$TE$Class(obj, this.type));
});

Clazz.newMeth(C$, 'add$I$TE', function (index, obj) {
this.l.add$I$TE(index, P$.Collections.checkType$TE$Class(obj, this.type));
});

Clazz.newMeth(C$, 'remove$I', function (index) {
return this.l.remove$I(index);
});

Clazz.newMeth(C$, 'indexOf$O', function (obj) {
return this.l.indexOf$O(obj);
});

Clazz.newMeth(C$, 'lastIndexOf$O', function (obj) {
return this.l.lastIndexOf$O(obj);
});

Clazz.newMeth(C$, 'listIterator', function () {
return Clazz.new_((I$[21]||$incl$(21)).c$$java_util_ListIterator$Class,[this.l.listIterator(), this.type]);
});

Clazz.newMeth(C$, 'listIterator$I', function (index) {
return Clazz.new_((I$[21]||$incl$(21)).c$$java_util_ListIterator$Class,[this.l.listIterator$I(index), this.type]);
});

Clazz.newMeth(C$, 'subList$I$I', function (fromIndex, toIndex) {
return P$.Collections.checkedList$java_util_List$Class(this.l.subList$I$I(fromIndex, toIndex), this.type);
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
return this.l.equals$O(obj);
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.l.hashCode();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "CheckedRandomAccessList", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.CheckedList'], 'java.util.RandomAccess');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_List$Class', function (l, type) {
C$.superclazz.c$$java_util_List$Class.apply(this, [l, type]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "CheckedSet", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.CheckedCollection'], 'java.util.Set');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Set$Class', function (s, type) {
C$.superclazz.c$$java_util_Collection$Class.apply(this, [s, type]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'equals$O', function (obj) {
return this.c.equals$O(obj);
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.c.hashCode();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "CheckedMap", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, ['java.util.Map', 'java.io.Serializable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.m = null;
this.keyType = null;
this.valueType = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_Map$Class$Class'], function (m, keyType, valueType) {
C$.$init$.apply(this);
if (m == null  || keyType == null   || valueType == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.m=m;
this.keyType=keyType;
this.valueType=valueType;
}, 1);

Clazz.newMeth(C$, 'size', function () {
return this.m.size();
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.m.isEmpty();
});

Clazz.newMeth(C$, 'containsKey$O', function (key) {
return this.m.containsKey$O(key);
});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
return this.m.containsValue$O(value);
});

Clazz.newMeth(C$, 'get$O', function (key) {
return this.m.get$O(key);
});

Clazz.newMeth(C$, ['put$TK$TV'], function (key, value) {
return this.m.put$TK$TV(P$.Collections.checkType$TE$Class(key, this.keyType), P$.Collections.checkType$TE$Class(value, this.valueType));
});

Clazz.newMeth(C$, 'remove$O', function (key) {
return this.m.remove$O(key);
});

Clazz.newMeth(C$, 'putAll$java_util_Map', function (map) {
var size = map.size();
if (size == 0) {
return;
}var entries = Clazz.array((I$[25]||$incl$(25)), [size]);
var it = map.entrySet().iterator();
for (var i = 0; i < size; i++) {
var e = it.next();
P$.Collections.checkType$TE$Class(e.getKey(), this.keyType);
P$.Collections.checkType$TE$Class(e.getValue(), this.valueType);
entries[i]=e;
}
for (var i = 0; i < size; i++) {
this.m.put$TK$TV(entries[i].getKey(), entries[i].getValue());
}
});

Clazz.newMeth(C$, 'clear', function () {
this.m.clear();
});

Clazz.newMeth(C$, 'keySet', function () {
return this.m.keySet();
});

Clazz.newMeth(C$, 'values', function () {
return this.m.values();
});

Clazz.newMeth(C$, 'entrySet', function () {
return Clazz.new_((I$[26]||$incl$(26)).c$$java_util_Set$Class,[this.m.entrySet(), this.valueType]);
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
return this.m.equals$O(obj);
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.m.hashCode();
});

Clazz.newMeth(C$, 'toString', function () {
return this.m.toString();
});
;
(function(){var C$=Clazz.newClass(P$.Collections.CheckedMap, "CheckedEntry", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, [['java.util.Map','java.util.Map.Entry']]);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.e = null;
this.valueType = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_Map_Entry$Class'], function (e, valueType) {
C$.$init$.apply(this);
if (e == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.e=e;
this.valueType=valueType;
}, 1);

Clazz.newMeth(C$, 'getKey', function () {
return this.e.getKey();
});

Clazz.newMeth(C$, 'getValue', function () {
return this.e.getValue();
});

Clazz.newMeth(C$, ['setValue$TV'], function (obj) {
return this.e.setValue$TV(P$.Collections.checkType$TE$Class(obj, this.valueType));
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
return this.e.equals$O(obj);
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.e.hashCode();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections.CheckedMap, "CheckedEntrySet", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, 'java.util.Set');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.s = null;
this.valueType = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_Set$Class'], function (s, valueType) {
C$.$init$.apply(this);
this.s=s;
this.valueType=valueType;
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return Clazz.new_((I$[24]||$incl$(24)).c$$java_util_Iterator$Class,[this.s.iterator(), this.valueType]);
});

Clazz.newMeth(C$, 'toArray', function () {
var thisSize = this.size();
var array = Clazz.array(java.lang.Object, [thisSize]);
var it = this.iterator();
for (var i = 0; i < thisSize; i++) {
array[i]=it.next();
}
return array;
});

Clazz.newMeth(C$, 'toArray$TTA', function (array) {
var thisSize = this.size();
if (array.length < thisSize) {
var ct = array.getClass().getComponentType();
array=Clazz.array(ct, thisSize);
}var it = this.iterator();
for (var i = 0; i < thisSize; i++) {
array[i]=it.next();
}
if (thisSize < array.length) {
array[thisSize]=null;
}return array;
});

Clazz.newMeth(C$, 'retainAll$java_util_Collection', function (c) {
return this.s.retainAll$java_util_Collection(c);
});

Clazz.newMeth(C$, 'removeAll$java_util_Collection', function (c) {
return this.s.removeAll$java_util_Collection(c);
});

Clazz.newMeth(C$, 'containsAll$java_util_Collection', function (c) {
return this.s.containsAll$java_util_Collection(c);
});

Clazz.newMeth(C$, ['addAll$java_util_Collection'], function (c) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'remove$O', function (o) {
return this.s.remove$O(o);
});

Clazz.newMeth(C$, 'contains$O', function (o) {
return this.s.contains$O(o);
});

Clazz.newMeth(C$, ['add$java_util_Map_Entry','add$TE'], function (o) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.s.isEmpty();
});

Clazz.newMeth(C$, 'clear', function () {
this.s.clear();
});

Clazz.newMeth(C$, 'size', function () {
return this.s.size();
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.s.hashCode();
});

Clazz.newMeth(C$, 'equals$O', function (object) {
return this.s.equals$O(object);
});
;
(function(){var C$=Clazz.newClass(P$.Collections.CheckedMap.CheckedEntrySet, "CheckedEntryIterator", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, 'java.util.Iterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.i = null;
this.valueType = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_Iterator$Class'], function (i, valueType) {
C$.$init$.apply(this);
this.i=i;
this.valueType=valueType;
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.i.hasNext();
});

Clazz.newMeth(C$, 'remove', function () {
this.i.remove();
});

Clazz.newMeth(C$, 'next', function () {
return Clazz.new_((I$[23]||$incl$(23)).c$$java_util_Map_Entry$Class,[this.i.next(), this.valueType]);
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "CheckedSortedSet", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.CheckedSet'], 'java.util.SortedSet');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.ss = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_SortedSet$Class', function (s, type) {
C$.superclazz.c$$java_util_Set$Class.apply(this, [s, type]);
C$.$init$.apply(this);
this.ss=s;
}, 1);

Clazz.newMeth(C$, 'comparator', function () {
return this.ss.comparator();
});

Clazz.newMeth(C$, ['subSet$TE$TE'], function (fromElement, toElement) {
return Clazz.new_(C$.c$$java_util_SortedSet$Class,[this.ss.subSet$TE$TE(fromElement, toElement), this.type]);
});

Clazz.newMeth(C$, ['headSet$TE'], function (toElement) {
return Clazz.new_(C$.c$$java_util_SortedSet$Class,[this.ss.headSet$TE(toElement), this.type]);
});

Clazz.newMeth(C$, ['tailSet$TE'], function (fromElement) {
return Clazz.new_(C$.c$$java_util_SortedSet$Class,[this.ss.tailSet$TE(fromElement), this.type]);
});

Clazz.newMeth(C$, 'first', function () {
return this.ss.first();
});

Clazz.newMeth(C$, 'last', function () {
return this.ss.last();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Collections, "CheckedSortedMap", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['java.util.Collections','java.util.Collections.CheckedMap'], 'java.util.SortedMap');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.sm = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$java_util_SortedMap$Class$Class'], function (m, keyType, valueType) {
C$.superclazz.c$$java_util_Map$Class$Class.apply(this, [m, keyType, valueType]);
C$.$init$.apply(this);
this.sm=m;
}, 1);

Clazz.newMeth(C$, 'comparator', function () {
return this.sm.comparator();
});

Clazz.newMeth(C$, ['subMap$TK$TK'], function (fromKey, toKey) {
return Clazz.new_(C$.c$$java_util_SortedMap$Class$Class,[this.sm.subMap$TK$TK(fromKey, toKey), this.keyType, this.valueType]);
});

Clazz.newMeth(C$, ['headMap$TK'], function (toKey) {
return Clazz.new_(C$.c$$java_util_SortedMap$Class$Class,[this.sm.headMap$TK(toKey), this.keyType, this.valueType]);
});

Clazz.newMeth(C$, ['tailMap$TK'], function (fromKey) {
return Clazz.new_(C$.c$$java_util_SortedMap$Class$Class,[this.sm.tailMap$TK(fromKey), this.keyType, this.valueType]);
});

Clazz.newMeth(C$, 'firstKey', function () {
return this.sm.firstKey();
});

Clazz.newMeth(C$, 'lastKey', function () {
return this.sm.lastKey();
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-24 08:45:45
