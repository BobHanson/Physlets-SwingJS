(function(){var P$=Clazz.newPackage("sun.misc"),I$=[];
var C$=Clazz.newClass(P$, "FIFOQueueEnumerator", null, null, 'java.util.Enumeration');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.queue = null;
this.cursor = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$sun_misc_Queue', function (q) {
C$.$init$.apply(this);
this.queue = q;
this.cursor = q.tail;
}, 1);

Clazz.newMeth(C$, 'hasMoreElements', function () {
return (this.cursor != null );
});

Clazz.newMeth(C$, 'nextElement', function () {
{
if (this.cursor != null ) {
var result = this.cursor;
this.cursor = this.cursor.prev;
return result.obj;
}}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException').c$$S,["FIFOQueueEnumerator"]);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:10
