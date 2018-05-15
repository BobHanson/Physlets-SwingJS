(function(){var P$=Clazz.newPackage("sun.misc"),I$=[['sun.misc.QueueElement','swingjs.JSUtil','sun.misc.LIFOQueueEnumerator','sun.misc.FIFOQueueEnumerator']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Queue");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.length = 0;
this.head = null;
this.tail = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.length = 0;
this.head = null;
this.tail = null;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'enqueue$O', function (obj) {
var newElt = Clazz.new_((I$[1]||$incl$(1)).c$$O,[obj]);
if (this.head == null ) {
this.head = newElt;
this.tail = newElt;
this.length = 1;
} else {
newElt.next = this.head;
this.head.prev = newElt;
this.head = newElt;
this.length++;
}this.notify();
});

Clazz.newMeth(C$, 'dequeue', function () {
return this.dequeue$J(0);
});

Clazz.newMeth(C$, 'dequeue$J', function (timeOut) {
while (this.tail == null ){
(I$[2]||$incl$(2)).warn$S("Cannot wait in Queue.java");
this.wait$J(timeOut);
}
var elt = this.tail;
this.tail = elt.prev;
if (this.tail == null ) {
this.head = null;
} else {
this.tail.next = null;
}this.length--;
return elt.obj;
});

Clazz.newMeth(C$, 'isEmpty', function () {
return (this.tail == null );
});

Clazz.newMeth(C$, 'elements', function () {
return Clazz.new_((I$[3]||$incl$(3)).c$$sun_misc_Queue,[this]);
});

Clazz.newMeth(C$, 'reverseElements', function () {
return Clazz.new_((I$[4]||$incl$(4)).c$$sun_misc_Queue,[this]);
});

Clazz.newMeth(C$, 'dump$S', function (msg) {
System.err.println$S(">> " + msg);
System.err.println$S("[" + this.length + " elt(s); head = " + (this.head == null  ? "null" : (this.head.obj) + "") + " tail = " + (this.tail == null  ? "null" : (this.tail.obj) + "") );
var cursor = this.head;
var last = null;
while (cursor != null ){
System.err.println$S("  " + cursor);
last = cursor;
cursor = cursor.next;
}
if (last !== this.tail ) {
System.err.println$S("  tail != last: " + this.tail + ", " + last );
}System.err.println$S("]");
});
})();
//Created 2018-05-15 01:03:10
