(function(){var P$=Clazz.newPackage("sun.awt"),I$=[['swingjs.JSUtil','sun.awt.EventQueueItem','sun.awt.SunToolkit']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PostEventQueue");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.queueHead = null;
this.queueTail = null;
this.eventQueue = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.queueHead = null;
this.queueTail = null;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_EventQueue', function (eq) {
C$.$init$.apply(this);
this.eventQueue = eq;
}, 1);

Clazz.newMeth(C$, 'noEvents', function () {
return this.queueHead == null ;
});

Clazz.newMeth(C$, 'flush', function () {
if (this.queueHead != null ) {
var tempQueue;
{
tempQueue = this.queueHead;
this.queueHead = this.queueTail = null;
while (tempQueue != null ){
(I$[1]||$incl$(1)).alert$O("postevent IS NOT IMPLEMENTED " + tempQueue.event);
this.eventQueue.postEvent$java_awt_AWTEvent(tempQueue.event);
tempQueue = tempQueue.next;
}
}}});

Clazz.newMeth(C$, 'postEvent$java_awt_AWTEvent', function (event) {
var item = Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_AWTEvent,[event]);
{
if (this.queueHead == null ) {
this.queueHead = this.queueTail = item;
} else {
this.queueTail.next = item;
this.queueTail = item;
}}(I$[3]||$incl$(3)).wakeupEventQueue$java_awt_EventQueue$Z(this.eventQueue, false);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:05
