(function(){var P$=java.util,I$=[['java.util.Vector$1','java.util.Arrays',['java.util.Collections','.SynchronizedRandomAccessList'],'java.lang.reflect.Array','java.lang.StringBuffer']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Vector", null, 'java.util.AbstractList', ['java.util.List', 'java.util.RandomAccess', 'Cloneable', 'java.io.Serializable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.elementCount = 0;
this.elementData = null;
this.capacityIncrement = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$I$I.apply(this, [10, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (capacity) {
C$.c$$I$I.apply(this, [capacity, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$I$I', function (capacity, capacityIncrement) {
Clazz.super_(C$, this,1);
this.elementCount = 0;
try {
this.elementData = p$.newElementArray$I.apply(this, [capacity]);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.NegativeArraySizeException")){
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
} else {
throw e;
}
}
this.capacityIncrement = capacityIncrement;
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Collection', function (collection) {
C$.c$$I$I.apply(this, [collection.size(), 0]);
var it = collection.iterator();
while (it.hasNext()){
this.elementData[this.elementCount++] = it.next();
}
}, 1);

Clazz.newMeth(C$, 'newElementArray$I', function (size) {
return Clazz.array(java.lang.Object, [size]);
});

Clazz.newMeth(C$, 'add$I$TE', function (location, object) {
this.insertElementAt$TE$I(object, location);
});

Clazz.newMeth(C$, ['add$TE'], function (object) {
this.addElement$TE(object);
return true;
});

Clazz.newMeth(C$, 'addAll$I$java_util_Collection', function (location, collection) {
if (0 <= location && location <= this.elementCount ) {
var size = collection.size();
if (size == 0) {
return false;
}var required = size - (this.elementData.length - this.elementCount);
if (required > 0) {
p$.growBy$I.apply(this, [required]);
}var count = this.elementCount - location;
if (count > 0) {
System.arraycopy(this.elementData, location, this.elementData, location + size, count);
}var it = collection.iterator();
while (it.hasNext()){
this.elementData[location++] = it.next();
}
this.elementCount = this.elementCount+(size);
this.modCount++;
return true;
}throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$I,[location]);
});

Clazz.newMeth(C$, 'addAll$java_util_Collection', function (collection) {
return this.addAll$I$java_util_Collection(this.elementCount, collection);
});

Clazz.newMeth(C$, ['addElement$TE'], function (object) {
if (this.elementCount == this.elementData.length) {
p$.growByOne.apply(this, []);
}this.elementData[this.elementCount++] = object;
this.modCount++;
});

Clazz.newMeth(C$, 'capacity', function () {
return this.elementData.length;
});

Clazz.newMeth(C$, 'clear', function () {
this.removeAllElements();
});

Clazz.newMeth(C$, 'clone', function () {
try {
var vector = Clazz.clone(this);
vector.elementData = this.elementData.clone();
return vector;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
return null;
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'contains$O', function (object) {
return this.indexOf$O$I(object, 0) != -1;
});

Clazz.newMeth(C$, 'containsAll$java_util_Collection', function (collection) {
return C$.superclazz.prototype.containsAll$java_util_Collection.apply(this, [collection]);
});

Clazz.newMeth(C$, 'copyInto$OA', function (elements) {
System.arraycopy(this.elementData, 0, elements, 0, this.elementCount);
});

Clazz.newMeth(C$, 'elementAt$I', function (location) {
if (location < this.elementCount) {
return this.elementData[location];
}throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$I,[location]);
});

Clazz.newMeth(C$, 'elements', function () {
return ((
(function(){var C$=Clazz.newClass(P$, "Vector$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Enumeration', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.pos = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.pos = 0;
}, 1);

Clazz.newMeth(C$, 'hasMoreElements', function () {
return this.pos < this.b$['java.util.Vector'].elementCount;
});

Clazz.newMeth(C$, 'nextElement', function () {
{
if (this.pos < this.b$['java.util.Vector'].elementCount) {
return this.b$['java.util.Vector'].elementData[this.pos++];
}}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});
})()
), Clazz.new_((I$[1]||$incl$(1)).$init$, [this, null]));
});

Clazz.newMeth(C$, 'ensureCapacity$I', function (minimumCapacity) {
if (this.elementData.length < minimumCapacity) {
var next = (this.capacityIncrement <= 0 ? this.elementData.length : this.capacityIncrement) + this.elementData.length;
p$.grow$I.apply(this, [minimumCapacity > next ? minimumCapacity : next]);
}});

Clazz.newMeth(C$, 'equals$O', function (object) {
if (this === object ) {
return true;
}if (Clazz.instanceOf(object, "java.util.List")) {
var list = object;
if (list.size() != this.size()) {
return false;
}var index = 0;
var it = list.iterator();
while (it.hasNext()){
var e1 = this.elementData[index++];
var e2 = it.next();
if (!(e1 == null  ? e2 == null  : e1.equals$O(e2))) {
return false;
}}
return true;
}return false;
});

Clazz.newMeth(C$, 'firstElement', function () {
if (this.elementCount > 0) {
return this.elementData[0];
}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$, 'get$I', function (location) {
return this.elementAt$I(location);
});

Clazz.newMeth(C$, 'grow$I', function (newCapacity) {
var newData = p$.newElementArray$I.apply(this, [newCapacity]);
Clazz.assert(C$, this, function(){return this.elementCount <= newCapacity});
System.arraycopy(this.elementData, 0, newData, 0, this.elementCount);
this.elementData = newData;
});

Clazz.newMeth(C$, 'growByOne', function () {
var adding = 0;
if (this.capacityIncrement <= 0) {
if ((adding = this.elementData.length) == 0) {
adding = 1;
}} else {
adding = this.capacityIncrement;
}var newData = p$.newElementArray$I.apply(this, [this.elementData.length + adding]);
System.arraycopy(this.elementData, 0, newData, 0, this.elementCount);
this.elementData = newData;
});

Clazz.newMeth(C$, 'growBy$I', function (required) {
var adding = 0;
if (this.capacityIncrement <= 0) {
if ((adding = this.elementData.length) == 0) {
adding = required;
}while (adding < required){
adding = adding+(adding);
}
} else {
adding = ((required/this.capacityIncrement|0)) * this.capacityIncrement;
if (adding < required) {
adding = adding+(this.capacityIncrement);
}}var newData = p$.newElementArray$I.apply(this, [this.elementData.length + adding]);
System.arraycopy(this.elementData, 0, newData, 0, this.elementCount);
this.elementData = newData;
});

Clazz.newMeth(C$, 'hashCode', function () {
var result = 1;
for (var i = 0; i < this.elementCount; i++) {
result = (31 * result) + (this.elementData[i] == null  ? 0 : this.elementData[i].hashCode());
}
return result;
});

Clazz.newMeth(C$, 'indexOf$O', function (object) {
return this.indexOf$O$I(object, 0);
});

Clazz.newMeth(C$, 'indexOf$O$I', function (object, location) {
if (object != null ) {
for (var i = location; i < this.elementCount; i++) {
if (object.equals$O(this.elementData[i])) {
return i;
}}
} else {
for (var i = location; i < this.elementCount; i++) {
if (this.elementData[i] == null ) {
return i;
}}
}return -1;
});

Clazz.newMeth(C$, ['insertElementAt$TE$I'], function (object, location) {
if (0 <= location && location <= this.elementCount ) {
if (this.elementCount == this.elementData.length) {
p$.growByOne.apply(this, []);
}var count = this.elementCount - location;
if (count > 0) {
System.arraycopy(this.elementData, location, this.elementData, location + 1, count);
}this.elementData[location] = object;
this.elementCount++;
this.modCount++;
} else {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$I,[location]);
}});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.elementCount == 0;
});

Clazz.newMeth(C$, 'lastElement', function () {
try {
return this.elementData[this.elementCount - 1];
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.IndexOutOfBoundsException")){
throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'lastIndexOf$O', function (object) {
return this.lastIndexOf$O$I(object, this.elementCount - 1);
});

Clazz.newMeth(C$, 'lastIndexOf$O$I', function (object, location) {
if (location < this.elementCount) {
if (object != null ) {
for (var i = location; i >= 0; i--) {
if (object.equals$O(this.elementData[i])) {
return i;
}}
} else {
for (var i = location; i >= 0; i--) {
if (this.elementData[i] == null ) {
return i;
}}
}return -1;
}throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$I,[location]);
});

Clazz.newMeth(C$, 'remove$I', function (location) {
if (location < this.elementCount) {
var result = this.elementData[location];
this.elementCount--;
var size = this.elementCount - location;
if (size > 0) {
System.arraycopy(this.elementData, location + 1, this.elementData, location, size);
}this.elementData[this.elementCount] = null;
this.modCount++;
return result;
}throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$I,[location]);
});

Clazz.newMeth(C$, 'remove$O', function (object) {
return this.removeElement$O(object);
});

Clazz.newMeth(C$, 'removeAll$java_util_Collection', function (collection) {
return C$.superclazz.prototype.removeAll$java_util_Collection.apply(this, [collection]);
});

Clazz.newMeth(C$, 'removeAllElements', function () {
(I$[2]||$incl$(2)).fill$OA$I$I$O(this.elementData, 0, this.elementCount, null);
this.modCount++;
this.elementCount = 0;
});

Clazz.newMeth(C$, 'removeElement$O', function (object) {
var index;
if ((index = this.indexOf$O$I(object, 0)) == -1) {
return false;
}this.removeElementAt$I(index);
return true;
});

Clazz.newMeth(C$, 'removeElementAt$I', function (location) {
if (0 <= location && location < this.elementCount ) {
this.elementCount--;
var size = this.elementCount - location;
if (size > 0) {
System.arraycopy(this.elementData, location + 1, this.elementData, location, size);
}this.elementData[this.elementCount] = null;
this.modCount++;
} else {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$I,[location]);
}});

Clazz.newMeth(C$, 'removeRange$I$I', function (start, end) {
if (start >= 0 && start <= end  && end <= this.size() ) {
if (start == end) {
return;
}if (end != this.elementCount) {
System.arraycopy(this.elementData, end, this.elementData, start, this.elementCount - end);
var newCount = this.elementCount - (end - start);
(I$[2]||$incl$(2)).fill$OA$I$I$O(this.elementData, newCount, this.elementCount, null);
this.elementCount = newCount;
} else {
(I$[2]||$incl$(2)).fill$OA$I$I$O(this.elementData, start, this.elementCount, null);
this.elementCount = start;
}this.modCount++;
} else {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}});

Clazz.newMeth(C$, 'retainAll$java_util_Collection', function (collection) {
return C$.superclazz.prototype.retainAll$java_util_Collection.apply(this, [collection]);
});

Clazz.newMeth(C$, 'set$I$TE', function (location, object) {
if (location < this.elementCount) {
var result = this.elementData[location];
this.elementData[location] = object;
return result;
}throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$I,[location]);
});

Clazz.newMeth(C$, ['setElementAt$TE$I'], function (object, location) {
if (location < this.elementCount) {
this.elementData[location] = object;
} else {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$I,[location]);
}});

Clazz.newMeth(C$, 'setSize$I', function (length) {
if (length == this.elementCount) {
return;
}this.ensureCapacity$I(length);
if (this.elementCount > length) {
(I$[2]||$incl$(2)).fill$OA$I$I$O(this.elementData, length, this.elementCount, null);
}this.elementCount = length;
this.modCount++;
});

Clazz.newMeth(C$, 'size', function () {
return this.elementCount;
});

Clazz.newMeth(C$, 'subList$I$I', function (start, end) {
return Clazz.new_((I$[3]||$incl$(3)).c$$java_util_List$O,[C$.superclazz.prototype.subList$I$I.apply(this, [start, end]), this]);
});

Clazz.newMeth(C$, 'toArray', function () {
var result = Clazz.array(java.lang.Object, [this.elementCount]);
System.arraycopy(this.elementData, 0, result, 0, this.elementCount);
return result;
});

Clazz.newMeth(C$, 'toArray$TTA', function (contents) {
if (this.elementCount > contents.length) {
var ct = contents.getClass().getComponentType();
contents = Clazz.array(ct, this.elementCount);
}System.arraycopy(this.elementData, 0, contents, 0, this.elementCount);
if (this.elementCount < contents.length) {
contents[this.elementCount] = null;
}return contents;
});

Clazz.newMeth(C$, 'toString', function () {
if (this.elementCount == 0) {
return "[]";
}var length = this.elementCount - 1;
var buffer = Clazz.new_((I$[5]||$incl$(5)).c$$I,[this.size() * 16]);
buffer.append$C("[");
for (var i = 0; i < length; i++) {
if (this.elementData[i] === this ) {
buffer.append$S("(this Collection)");
} else {
buffer.append$O(this.elementData[i]);
}buffer.append$S(", ");
}
if (this.elementData[length] === this ) {
buffer.append$S("(this Collection)");
} else {
buffer.append$O(this.elementData[length]);
}buffer.append$C("]");
return buffer.toString();
});

Clazz.newMeth(C$, 'trimToSize', function () {
if (this.elementData.length != this.elementCount) {
p$.grow$I.apply(this, [this.elementCount]);
}});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (stream) {
stream.defaultWriteObject();
});
C$.$_ASSERT_ENABLED_ = ClassLoader.$getClassAssertionStatus(C$);
})();
//Created 2018-02-06 08:58:55
