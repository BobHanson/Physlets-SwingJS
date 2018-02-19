(function(){var P$=Clazz.newPackage("java.awt"),I$=[];
var C$=Clazz.newClass(P$, "MediaEntry");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.tracker = null;
this.ID = 0;
this.next = null;
this.status = 0;
this.cancelled = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_MediaTracker$I', function (mt, id) {
C$.$init$.apply(this);
this.tracker = mt;
this.ID = id;
}, 1);

Clazz.newMeth(C$, 'insert$java_awt_MediaEntry$java_awt_MediaEntry', function (head, me) {
var cur = head;
var prev = null;
while (cur != null ){
if (cur.ID > me.ID) {
break;
}prev = cur;
cur = cur.next;
}
me.next = cur;
if (prev == null ) {
head = me;
} else {
prev.next = me;
}return head;
}, 1);

Clazz.newMeth(C$, 'getID', function () {
return this.ID;
});

Clazz.newMeth(C$, 'cancel', function () {
this.cancelled = true;
});

Clazz.newMeth(C$, 'getStatus$Z$Z', function (doLoad, doVerify) {
return 8;
});

Clazz.newMeth(C$, 'setStatus$I', function (flag) {
{
this.status = flag;
}this.tracker.setDone();
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 21:23:33
