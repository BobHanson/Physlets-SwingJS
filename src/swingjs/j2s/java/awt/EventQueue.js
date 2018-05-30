(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.awt.Queue','Thread','sun.awt.SunToolkit','sun.awt.AWTAutoShutdown','java.awt.EventQueueItem','swingjs.JSToolkit','java.awt.Toolkit','sun.awt.AppContext','java.awt.EventDispatchThread','java.awt.event.InvocationEvent','java.lang.Error']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "EventQueue");
C$.threadInitNumber = 0;
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.queues = null;
this.nextQueue = null;
this.previousQueue = null;
this.dispatchThread = null;
this.threadGroup = null;
this.mostRecentEventTime = 0;
this.currentEvent = null;
this.waitForID = 0;
this.name = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.queues = Clazz.array((I$[1]||$incl$(1)), [4]);
this.threadGroup = (I$[2]||$incl$(2)).currentThread().getThreadGroup();
this.mostRecentEventTime = System.currentTimeMillis();
this.name = "AWT-EventQueue-" + C$.nextThreadNum();
}, 1);

Clazz.newMeth(C$, 'nextThreadNum', function () {
return C$.threadInitNumber++;
}, 1);

Clazz.newMeth(C$, 'noEvents$java_awt_EventQueue', function (eventQueue) {
return eventQueue.noEvents();
}, 1);

Clazz.newMeth(C$, 'getNextQueue$java_awt_EventQueue', function (eventQueue) {
return eventQueue.nextQueue;
}, 1);

Clazz.newMeth(C$, 'removeSourceEvents$java_awt_EventQueue$O$Z', function (eventQueue, source, removeAllEvents) {
eventQueue.removeSourceEvents$O$Z(source, removeAllEvents);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
for (var i = 0; i < 4; i++) {
this.queues[i]=Clazz.new_((I$[1]||$incl$(1)));
}
}, 1);

Clazz.newMeth(C$, 'postEvent$java_awt_AWTEvent', function (event) {
(I$[3]||$incl$(3)).flushPendingEvents();
this.postEventPrivate$java_awt_AWTEvent(event);
});

Clazz.newMeth(C$, 'postEventPrivate$java_awt_AWTEvent', function (theEvent) {
theEvent.isPosted=true;
{
if (this.dispatchThread == null  && this.nextQueue == null  ) {
if (theEvent.getSource() === (I$[4]||$incl$(4)).getInstance() ) {
return;
} else {
this.initDispatchThread();
}}if (this.nextQueue != null ) {
this.nextQueue.postEventPrivate$java_awt_AWTEvent(theEvent);
return;
}p$.postEventNow$java_awt_AWTEvent$I.apply(this, [theEvent, C$.getPriority$java_awt_AWTEvent(theEvent)]);
}});

Clazz.newMeth(C$, 'getPriority$java_awt_AWTEvent', function (theEvent) {
if (Clazz.instanceOf(theEvent, "sun.awt.PeerEvent")) {
var flags = (theEvent).getFlags();
if ((flags & 2) != 0) return 3;
if ((flags & 1) != 0) return 2;
if ((flags & 4) != 0) return 0;
}switch (theEvent.getID()) {
case 1201:
case 800:
case 801:
return 0;
default:
return 1;
}
}, 1);

Clazz.newMeth(C$, 'postEventNow$java_awt_AWTEvent$I', function (theEvent, priority) {
if (p$.coalesceEvent$java_awt_AWTEvent$I.apply(this, [theEvent, priority])) {
return;
}var newItem = Clazz.new_((I$[5]||$incl$(5)).c$$java_awt_AWTEvent,[theEvent]);
p$.cacheEQItem$java_awt_EventQueueItem.apply(this, [newItem]);
if (this.queues[priority].head == null ) {
var shouldNotify = p$.noEvents.apply(this, []);
this.queues[priority].head=this.queues[priority].tail=newItem;
if (shouldNotify) {
if (theEvent.getSource() !== (I$[4]||$incl$(4)).getInstance() ) {
(I$[4]||$incl$(4)).getInstance().notifyThreadBusy$Thread(this.dispatchThread);
}}} else {
this.queues[priority].tail.next=newItem;
this.queues[priority].tail=newItem;
}});

Clazz.newMeth(C$, 'coalescePaintEvent$java_awt_event_PaintEvent', function (e) {
var sourcePeer = (e.getSource()).peer;
if (sourcePeer != null ) {
sourcePeer.coalescePaintEvent$java_awt_event_PaintEvent(e);
}var cache = (e.getSource()).eventCache;
if (cache == null ) {
return false;
}var index = C$.eventToCacheIndex$java_awt_AWTEvent(e);
if (index != -1 && cache[index] != null  ) {
var merged = p$.mergePaintEvents$java_awt_event_PaintEvent$java_awt_event_PaintEvent.apply(this, [e, cache[index].event]);
if (merged != null ) {
cache[index].event=merged;
return true;
}}return false;
});

Clazz.newMeth(C$, 'mergePaintEvents$java_awt_event_PaintEvent$java_awt_event_PaintEvent', function (a, b) {
var aRect = a.getUpdateRect();
var bRect = b.getUpdateRect();
if (bRect.contains$java_awt_Rectangle(aRect)) {
return b;
}if (aRect.contains$java_awt_Rectangle(bRect)) {
return a;
}return null;
});

Clazz.newMeth(C$, 'coalesceMouseEvent$java_awt_event_MouseEvent', function (e) {
var cache = (e.getSource()).eventCache;
if (cache == null ) {
return false;
}var index = C$.eventToCacheIndex$java_awt_AWTEvent(e);
if (index != -1 && cache[index] != null  ) {
cache[index].event=e;
return true;
}return false;
});

Clazz.newMeth(C$, 'coalescePeerEvent$sun_awt_PeerEvent', function (e) {
var cache = (e.getSource()).eventCache;
if (cache == null ) {
return false;
}var index = C$.eventToCacheIndex$java_awt_AWTEvent(e);
if (index != -1 && cache[index] != null  ) {
e=e.coalesceEvents$sun_awt_PeerEvent(cache[index].event);
if (e != null ) {
cache[index].event=e;
return true;
} else {
cache[index]=null;
}}return false;
});

Clazz.newMeth(C$, 'coalesceOtherEvent$java_awt_AWTEvent$I', function (e, priority) {
var id = e.getID();
var source = e.getSource();
for (var entry = this.queues[priority].head; entry != null ; entry=entry.next) {
if (entry.event.getSource() === source  && entry.id == id ) {
var coalescedEvent = source.coalesceEvents$java_awt_AWTEvent$java_awt_AWTEvent(entry.event, e);
if (coalescedEvent != null ) {
entry.event=coalescedEvent;
return true;
}}}
return false;
});

Clazz.newMeth(C$, 'coalesceEvent$java_awt_AWTEvent$I', function (e, priority) {
if (!(Clazz.instanceOf(e.getSource(), "java.awt.Component"))) {
return false;
}if (Clazz.instanceOf(e, "sun.awt.PeerEvent")) {
return p$.coalescePeerEvent$sun_awt_PeerEvent.apply(this, [e]);
}if ((e.getSource()).isCoalescingEnabled() && p$.coalesceOtherEvent$java_awt_AWTEvent$I.apply(this, [e, priority]) ) {
return true;
}if (Clazz.instanceOf(e, "java.awt.event.PaintEvent")) {
return p$.coalescePaintEvent$java_awt_event_PaintEvent.apply(this, [e]);
}if (Clazz.instanceOf(e, "java.awt.event.MouseEvent")) {
return p$.coalesceMouseEvent$java_awt_event_MouseEvent.apply(this, [e]);
}return false;
});

Clazz.newMeth(C$, 'cacheEQItem$java_awt_EventQueueItem', function (entry) {
var index = C$.eventToCacheIndex$java_awt_AWTEvent(entry.event);
if (index != -1 && Clazz.instanceOf(entry.event.getSource(), "java.awt.Component") ) {
var source = entry.event.getSource();
if (source.eventCache == null ) {
source.eventCache=Clazz.array((I$[5]||$incl$(5)), [5]);
}source.eventCache[index]=entry;
}});

Clazz.newMeth(C$, 'uncacheEQItem$java_awt_EventQueueItem', function (entry) {
var index = C$.eventToCacheIndex$java_awt_AWTEvent(entry.event);
if (index != -1 && Clazz.instanceOf(entry.event.getSource(), "java.awt.Component") ) {
var source = entry.event.getSource();
if (source.eventCache == null ) {
return;
}source.eventCache[index]=null;
}});

Clazz.newMeth(C$, 'eventToCacheIndex$java_awt_AWTEvent', function (e) {
switch (e.getID()) {
case 800:
return 0;
case 801:
return 1;
case 503:
return 2;
case 506:
return 3;
default:
return -1;
}
}, 1);

Clazz.newMeth(C$, 'noEvents', function () {
for (var i = 0; i < 4; i++) {
if (this.queues[i].head != null ) {
return false;
}}
return true;
});

Clazz.newMeth(C$, 'getNextEvent', function () {
(I$[3]||$incl$(3)).flushPendingEvents();
{
for (var i = 3; i >= 0; i--) {
if (this.queues[i].head != null ) {
var entry = this.queues[i].head;
this.queues[i].head=entry.next;
if (entry.next == null ) {
this.queues[i].tail=null;
}p$.uncacheEQItem$java_awt_EventQueueItem.apply(this, [entry]);
return entry.event;
}}
(I$[4]||$incl$(4)).getInstance().notifyThreadFree$Thread(this.dispatchThread);
}return null;
});

Clazz.newMeth(C$, 'getNextEventForID$I', function (id) {
(I$[3]||$incl$(3)).flushPendingEvents();
{
for (var i = 0; i < 4; i++) {
for (var entry = this.queues[i].head, prev = null; entry != null ; prev=entry, entry=entry.next) {
if (entry.id == id) {
if (prev == null ) {
this.queues[i].head=entry.next;
} else {
prev.next=entry.next;
}if (this.queues[i].tail === entry ) {
this.queues[i].tail=prev;
}p$.uncacheEQItem$java_awt_EventQueueItem.apply(this, [entry]);
return entry.event;
}}
}
this.waitForID=id;
this.waitForID=0;
}return null;
});

Clazz.newMeth(C$, 'peekEvent', function () {
for (var i = 3; i >= 0; i--) {
if (this.queues[i].head != null ) {
return this.queues[i].head.event;
}}
return null;
});

Clazz.newMeth(C$, 'peekEvent$I', function (id) {
for (var i = 3; i >= 0; i--) {
var q = this.queues[i].head;
for (; q != null ; q=q.next) {
if (q.id == id) {
return q.event;
}}
}
return null;
});

Clazz.newMeth(C$, 'dispatchEvent$java_awt_AWTEvent', function (event) {
var src = event.getSource();
p$.dispatchEventImpl$java_awt_AWTEvent$O$Z.apply(this, [event, src, false]);
});

Clazz.newMeth(C$, 'dispatchEventAndWait$java_awt_AWTEvent$O', function (event, src) {
p$.dispatchEventImpl$java_awt_AWTEvent$O$Z.apply(this, [event, src, true]);
});

Clazz.newMeth(C$, 'dispatchEventImpl$java_awt_AWTEvent$O$Z', function (event, src, andWait) {
event.isPosted=true;
if (Clazz.instanceOf(event, "java.awt.ActiveEvent")) {
p$.setCurrentEventAndMostRecentTimeImpl$java_awt_AWTEvent.apply(this, [event]);
(I$[6]||$incl$(6)).dispatchEvent$java_awt_AWTEvent$O$Z(event, null, andWait);
} else if (Clazz.instanceOf(src, "java.awt.Component")) {
(I$[6]||$incl$(6)).dispatchEvent$java_awt_AWTEvent$O$Z(event, src, andWait);
event.dispatched();
} else if (Clazz.instanceOf(src, "sun.awt.AWTAutoShutdown")) {
if (p$.noEvents.apply(this, [])) {
this.dispatchThread.stopDispatching();
}} else {
System.err.println$S("unable to dispatch event: " + event);
}});

Clazz.newMeth(C$, 'getMostRecentEventTime', function () {
return 0;
}, 1);

Clazz.newMeth(C$, 'getMostRecentEventTimeEx', function () {
return this.mostRecentEventTime;
});

Clazz.newMeth(C$, 'getCurrentEvent', function () {
return (I$[7]||$incl$(7)).getEventQueue().getCurrentEventImpl();
}, 1);

Clazz.newMeth(C$, 'getCurrentEventImpl', function () {
return ((I$[6]||$incl$(6)).isDispatchThread() ? (this.currentEvent) : null);
});

Clazz.newMeth(C$, 'push$java_awt_EventQueue', function (newEventQueue) {
if (this.nextQueue != null ) {
this.nextQueue.push$java_awt_EventQueue(newEventQueue);
return;
}{
while (this.peekEvent() != null ){
try {
newEventQueue.postEventPrivate$java_awt_AWTEvent(this.getNextEvent());
} catch (ie) {
if (Clazz.exceptionOf(ie, "java.lang.InterruptedException")){
} else {
throw ie;
}
}
}
newEventQueue.previousQueue=this;
}if (this.dispatchThread != null ) {
this.dispatchThread.stopDispatchingLater();
}this.nextQueue=newEventQueue;
var appContext = (I$[8]||$incl$(8)).getAppContext();
if (appContext.get$O((I$[8]||$incl$(8)).EVENT_QUEUE_KEY) === this ) {
appContext.put$O$O((I$[8]||$incl$(8)).EVENT_QUEUE_KEY, newEventQueue);
}});

Clazz.newMeth(C$, 'pop', function () {
var prev = this.previousQueue;
{
{
if (this.nextQueue != null ) {
this.nextQueue.pop();
return;
}if (this.previousQueue == null ) {
throw Clazz.new_(Clazz.load('java.util.EmptyStackException'));
}this.previousQueue.nextQueue=null;
while (this.peekEvent() != null ){
try {
this.previousQueue.postEventPrivate$java_awt_AWTEvent(this.getNextEvent());
} catch (ie) {
if (Clazz.exceptionOf(ie, "java.lang.InterruptedException")){
} else {
throw ie;
}
}
}
var appContext = (I$[8]||$incl$(8)).getAppContext();
if (appContext.get$O((I$[8]||$incl$(8)).EVENT_QUEUE_KEY) === this ) {
appContext.put$O$O((I$[8]||$incl$(8)).EVENT_QUEUE_KEY, this.previousQueue);
}this.previousQueue=null;
}}var dt = this.dispatchThread;
if (dt != null ) {
dt.stopDispatching();
}});

Clazz.newMeth(C$, 'isDispatchThread', function () {
return (I$[6]||$incl$(6)).isDispatchThread();
}, 1);

Clazz.newMeth(C$, 'initDispatchThread', function () {
{
if (this.dispatchThread == null ) {
var t = Clazz.new_((I$[9]||$incl$(9)).c$$ThreadGroup$S$java_awt_EventQueue,[this.threadGroup, this.name, this]);
(I$[4]||$incl$(4)).getInstance().notifyThreadBusy$Thread(t);
this.dispatchThread=t;
this.dispatchThread.start();
}}});

Clazz.newMeth(C$, 'detachDispatchThread', function () {
this.dispatchThread=null;
});

Clazz.newMeth(C$, 'getDispatchThread', function () {
return this.dispatchThread;
});

Clazz.newMeth(C$, 'removeSourceEvents$O$Z', function (source, removeAllEvents) {
(I$[3]||$incl$(3)).flushPendingEvents();
{
for (var i = 0; i < 4; i++) {
var entry = this.queues[i].head;
var prev = null;
while (entry != null ){
if ((entry.event.getSource() === source ) && (removeAllEvents || !(Clazz.instanceOf(entry.event, "java.awt.SequencedEvent") || Clazz.instanceOf(entry.event, "java.awt.SentEvent") || Clazz.instanceOf(entry.event, "java.awt.event.FocusEvent") || Clazz.instanceOf(entry.event, "java.awt.event.WindowEvent") || Clazz.instanceOf(entry.event, "java.awt.event.KeyEvent") || Clazz.instanceOf(entry.event, "java.awt.event.InputMethodEvent")  ) ) ) {
if (Clazz.instanceOf(entry.event, "java.awt.SequencedEvent")) {
(entry.event).dispose();
}if (Clazz.instanceOf(entry.event, "java.awt.SentEvent")) {
(entry.event).dispose();
}if (prev == null ) {
this.queues[i].head=entry.next;
} else {
prev.next=entry.next;
}p$.uncacheEQItem$java_awt_EventQueueItem.apply(this, [entry]);
} else {
prev=entry;
}entry=entry.next;
}
this.queues[i].tail=prev;
}
}});

Clazz.newMeth(C$, 'setCurrentEventAndMostRecentTime$java_awt_AWTEvent', function (e) {
(I$[7]||$incl$(7)).getEventQueue().setCurrentEventAndMostRecentTimeImpl$java_awt_AWTEvent(e);
}, 1);

Clazz.newMeth(C$, 'setCurrentEventAndMostRecentTimeImpl$java_awt_AWTEvent', function (e) {
if ((I$[6]||$incl$(6)).isDispatchThread()) {
return;
}this.currentEvent=e;
var mostRecentEventTime2 = -9223372036854775808;
if (Clazz.instanceOf(e, "java.awt.event.InputEvent")) {
var ie = e;
mostRecentEventTime2=ie.getWhen();
} else if (Clazz.instanceOf(e, "java.awt.event.InputMethodEvent")) {
var ime = e;
mostRecentEventTime2=ime.getWhen();
} else if (Clazz.instanceOf(e, "java.awt.event.ActionEvent")) {
var ae = e;
mostRecentEventTime2=ae.getWhen();
} else if (Clazz.instanceOf(e, "java.awt.event.InvocationEvent")) {
var ie = e;
mostRecentEventTime2=ie.getWhen();
}this.mostRecentEventTime=Math.max(this.mostRecentEventTime, mostRecentEventTime2);
});

Clazz.newMeth(C$, 'invokeLater$Runnable', function (runnable) {
(I$[7]||$incl$(7)).getEventQueue().postEvent$java_awt_AWTEvent(Clazz.new_((I$[10]||$incl$(10)).c$$O$I$Runnable$O$Z,[(I$[7]||$incl$(7)).getDefaultToolkit(), 1200, runnable, null, false]));
}, 1);

Clazz.newMeth(C$, 'invokeAndWait$Runnable', function (runnable) {
C$.invokeAndWaitStatic$O$Runnable((I$[7]||$incl$(7)).getDefaultToolkit(), runnable);
}, 1);

Clazz.newMeth(C$, 'invokeAndWaitStatic$O$Runnable', function (source, runnable) {
if (C$.isDispatchThread()) {
throw Clazz.new_((I$[11]||$incl$(11)).c$$S,["Cannot call invokeAndWait from the event dispatcher thread"]);
}var event = Clazz.new_((I$[10]||$incl$(10)).c$$O$I$Runnable$O$Z,[source, 1200, runnable, null, true]);
(I$[6]||$incl$(6)).dispatchEvent$java_awt_AWTEvent$O$Z(event, null, true);
var eventThrowable = event.getThrowable();
if (eventThrowable != null ) {
throw Clazz.new_(Clazz.load('java.lang.reflect.InvocationTargetException').c$$Throwable,[eventThrowable]);
}}, 1);

Clazz.newMeth(C$, 'wakeup$Z', function (isShutdown) {
{
if (this.nextQueue != null ) {
this.nextQueue.wakeup$Z(isShutdown);
} else if (this.dispatchThread != null ) {
try {
this.dispatchThread.start();
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.IllegalThreadStateException")){
this.dispatchThread.run();
} else {
throw e;
}
}
} else if (!isShutdown) {
this.initDispatchThread();
}}});
})();
//Created 2018-05-24 08:45:08
