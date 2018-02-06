(function(){var P$=Clazz.newPackage("swingjs"),I$=[['javajs.util.AU','java.util.HashMap','javax.swing.text.Element',['swingjs.JSAbstractDocument','.DefaultFilterBypass'],['swingjs.JSAbstractDocument','.JSElement'],'swingjs.JSDocumentEvent',['javax.swing.event.DocumentEvent','.EventType'],'javax.swing.event.DocumentListener','javax.swing.event.EventListenerList']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSAbstractDocument", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, 'swingjs.api.JSMinimalAbstractDocument');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.props = null;
this.root = null;
this.positions = null;
this.listenerList = null;
this.notifyingListeners = false;
this.filterBypass = null;
this.me = null;
this.sb = null;
this.tempChar = null;
this.filter = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.me = this;
this.props = Clazz.new_((I$[2]||$incl$(2)));
}, 1);

Clazz.newMeth(C$, 'getRootElements', function () {
return Clazz.array((I$[3]||$incl$(3)), -1, [this.root, null]);
});

Clazz.newMeth(C$, 'checkLoc$I$I', function (start, end) {
if (start < 0 || end > this.getLength() ) throw Clazz.new_(Clazz.load('javax.swing.text.BadLocationException').c$$S$I,["JSAbstractDocument: out of range", (start < 0 ? start : end)]);
});

Clazz.newMeth(C$, 'fixPositions$I$I$Z', function (offset, length, isInsert) {
if (this.positions == null  || this.positions.isEmpty() ) return;
if (isInsert) {
for (var i, $i = this.positions.keySet().iterator(); $i.hasNext()&&((i=$i.next()),1);) {
var pos = i.intValue();
if (pos > offset) this.positions.get$O(i).pos = this.positions.get$O(i).pos+(length);
}
return;
}for (var i, $i = this.positions.keySet().iterator(); $i.hasNext()&&((i=$i.next()),1);) {
var pos = i.intValue();
if (pos <= offset) continue;
if (pos >= offset + length) this.positions.get$O(i).pos = this.positions.get$O(i).pos-(length);
 else this.positions.get$O(i).pos = offset;
}
});

Clazz.newMeth(C$, 'getFilterBypass', function () {
if (this.filterBypass == null ) {
this.filterBypass = Clazz.new_((I$[4]||$incl$(4)), [this, null]);
}return this.filterBypass;
});

Clazz.newMeth(C$, 'remove$I$I', function (offs, len) {
var filter = this.getDocumentFilter();
if (filter == null ) this.handleRemove$I$I(offs, len);
 else filter.remove$javax_swing_text_DocumentFilter_FilterBypass$I$I(p$.getFilterBypass.apply(this, []), offs, len);
});

Clazz.newMeth(C$, 'insertString$I$S$javax_swing_text_AttributeSet', function (offset, str, a) {
var filter = this.getDocumentFilter();
if (filter == null ) this.handleInsertString$I$S$javax_swing_text_AttributeSet(offset, str, a);
 else filter.insertString$javax_swing_text_DocumentFilter_FilterBypass$I$S$javax_swing_text_AttributeSet(p$.getFilterBypass.apply(this, []), offset, str, a);
});

Clazz.newMeth(C$, 'replace$I$I$S$javax_swing_text_AttributeSet', function (offset, length, text, attrs) {
if (length == 0 && (text == null  || text.length$() == 0 ) ) return;
var filter = this.getDocumentFilter();
if (filter != null ) {
filter.replace$javax_swing_text_DocumentFilter_FilterBypass$I$I$S$javax_swing_text_AttributeSet(p$.getFilterBypass.apply(this, []), offset, length, text, attrs);
} else {
if (length > 0) this.remove$I$I(offset, length);
if (text != null  && text.length$() > 0 ) this.insertString$I$S$javax_swing_text_AttributeSet(offset, text, attrs);
}});

Clazz.newMeth(C$, 'taint', function () {
this.tempChar = null;
});

Clazz.newMeth(C$, 'setLines', function () {
this.root = Clazz.new_((I$[5]||$incl$(5)), [this, null]);
var s = this.sb.toString();
if (s.lastIndexOf("\u000a") != s.length$() - 1) s += "\n";
var ilast = 0;
for (var i = 0; i < s.length$(); i++) {
if (s.charAt(i) != "\u000a") continue;
var e = Clazz.new_((I$[5]||$incl$(5)), [this, null]);
e.start = ilast;
e.end = i;
ilast = i + 1;
this.root.addChild$swingjs_JSAbstractDocument_JSElement(e);
}
});

Clazz.newMeth(C$, 'handleInsertString$I$S$javax_swing_text_AttributeSet', function (offs, str, a) {
if ((str == null ) || (str.length$() == 0) ) {
return;
}this.checkLoc$I$I(offs, offs);
p$.taint.apply(this, []);
this.sb.insert$I$S(offs, str);
this.fixPositions$I$I$Z(offs, str.length$(), true);
if (str.indexOf("\u000a") >= 0) p$.setLines.apply(this, []);
var e = Clazz.new_((I$[6]||$incl$(6)).c$$swingjs_JSAbstractDocument$I$I$javax_swing_event_DocumentEvent_EventType,[this, offs, str.length$(), (I$[7]||$incl$(7)).INSERT]);
this.fireInsertUpdate$javax_swing_event_DocumentEvent(e);
});

Clazz.newMeth(C$, 'handleRemove$I$I', function (offs, len) {
this.checkLoc$I$I(offs, offs + len);
p$.taint.apply(this, []);
var str = this.sb.substring2$I$I(offs, offs + len);
this.sb.replace$I$I$S(offs, offs + len, "");
this.fixPositions$I$I$Z(offs, offs + len, false);
if (str.indexOf("\u000a") >= 0) p$.setLines.apply(this, []);
if (len > 0) {
var chng = Clazz.new_((I$[6]||$incl$(6)).c$$swingjs_JSAbstractDocument$I$I$javax_swing_event_DocumentEvent_EventType,[this, offs, len, (I$[7]||$incl$(7)).REMOVE]);
this.fireRemoveUpdate$javax_swing_event_DocumentEvent(chng);
}});

Clazz.newMeth(C$, 'fireInsertUpdate$javax_swing_event_DocumentEvent', function (e) {
if (this.listenerList == null ) return;
p$.checkAlreadyNotifying.apply(this, []);
this.notifyingListeners = true;
try {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[8]||$incl$(8)),['changedUpdate$javax_swing_event_DocumentEvent','insertUpdate$javax_swing_event_DocumentEvent','removeUpdate$javax_swing_event_DocumentEvent']) ) {
(listeners[i + 1]).insertUpdate$javax_swing_event_DocumentEvent(e);
}}
} finally {
this.notifyingListeners = false;
}
});

Clazz.newMeth(C$, 'fireChangedUpdate$javax_swing_event_DocumentEvent', function (e) {
if (this.listenerList == null ) return;
p$.checkAlreadyNotifying.apply(this, []);
this.notifyingListeners = true;
try {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[8]||$incl$(8)),['changedUpdate$javax_swing_event_DocumentEvent','insertUpdate$javax_swing_event_DocumentEvent','removeUpdate$javax_swing_event_DocumentEvent']) ) {
(listeners[i + 1]).changedUpdate$javax_swing_event_DocumentEvent(e);
}}
} finally {
this.notifyingListeners = false;
}
});

Clazz.newMeth(C$, 'fireRemoveUpdate$javax_swing_event_DocumentEvent', function (e) {
if (this.listenerList == null ) return;
p$.checkAlreadyNotifying.apply(this, []);
this.notifyingListeners = true;
try {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[8]||$incl$(8)),['changedUpdate$javax_swing_event_DocumentEvent','insertUpdate$javax_swing_event_DocumentEvent','removeUpdate$javax_swing_event_DocumentEvent']) ) {
(listeners[i + 1]).removeUpdate$javax_swing_event_DocumentEvent(e);
}}
} finally {
this.notifyingListeners = false;
}
});

Clazz.newMeth(C$, 'checkAlreadyNotifying', function () {
if (this.notifyingListeners) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["One of the document listeners modifed the document. This is not allowed."]);
});

Clazz.newMeth(C$, 'addDocumentListener$javax_swing_event_DocumentListener', function (listener) {
if (this.listenerList == null ) this.listenerList = Clazz.new_((I$[9]||$incl$(9)));
this.listenerList.add$Class$TT(Clazz.getClass((I$[8]||$incl$(8)),['changedUpdate$javax_swing_event_DocumentEvent','insertUpdate$javax_swing_event_DocumentEvent','removeUpdate$javax_swing_event_DocumentEvent']), listener);
});

Clazz.newMeth(C$, 'removeDocumentListener$javax_swing_event_DocumentListener', function (listener) {
if (this.listenerList != null ) this.listenerList.remove$Class$TT(Clazz.getClass((I$[8]||$incl$(8)),['changedUpdate$javax_swing_event_DocumentEvent','insertUpdate$javax_swing_event_DocumentEvent','removeUpdate$javax_swing_event_DocumentEvent']), listener);
});

Clazz.newMeth(C$, 'addUndoableEditListener$javax_swing_event_UndoableEditListener', function (listener) {
});

Clazz.newMeth(C$, 'removeUndoableEditListener$javax_swing_event_UndoableEditListener', function (listener) {
});

Clazz.newMeth(C$, 'getProperty$O', function (key) {
return this.props.get$O(key);
});

Clazz.newMeth(C$, 'putProperty$O$O', function (key, value) {
this.props.put$TK$TV(key, value);
});

Clazz.newMeth(C$, 'getAsynchronousLoadPriority', function () {
return -1;
});

Clazz.newMeth(C$, 'setDocumentFilter$javax_swing_text_DocumentFilter', function (filter) {
this.filter = filter;
});

Clazz.newMeth(C$, 'getDocumentFilter', function () {
return this.filter;
});
;
(function(){var C$=Clazz.newClass(P$.JSAbstractDocument, "DefaultFilterBypass", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['javax.swing.text.DocumentFilter','javax.swing.text.DocumentFilter.FilterBypass']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getDocument', function () {
return this.this$0.me;
});

Clazz.newMeth(C$, 'remove$I$I', function (offset, length) {
this.this$0.handleRemove$I$I(offset, length);
});

Clazz.newMeth(C$, 'insertString$I$S$javax_swing_text_AttributeSet', function (offset, string, attr) {
this.this$0.handleInsertString$I$S$javax_swing_text_AttributeSet(offset, string, attr);
});

Clazz.newMeth(C$, 'replace$I$I$S$javax_swing_text_AttributeSet', function (offset, length, text, attrs) {
this.this$0.handleRemove$I$I(offset, length);
this.this$0.handleInsertString$I$S$javax_swing_text_AttributeSet(offset, text, attrs);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.JSAbstractDocument, "JSElement", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'javax.swing.text.Element');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.parent = null;
this.attributeSet = null;
this.start = 0;
this.end = 0;
this.nchildren = 0;
this.children = null;
this.lastIndex = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.children = null;
this.nchildren = 0;
this.lastIndex = -1;
}, 1);

Clazz.newMeth(C$, 'addChild$swingjs_JSAbstractDocument_JSElement', function (e) {
if (this.children == null ) this.children = Clazz.array(C$, [10]);
 else if (this.nchildren == this.children.length) this.children = (I$[1]||$incl$(1)).doubleLength$O(this.children);
this.children[this.nchildren++] = e;
});

Clazz.newMeth(C$, 'getDocument', function () {
return this.this$0.me;
});

Clazz.newMeth(C$, 'getParentElement', function () {
return this.parent;
});

Clazz.newMeth(C$, 'getName', function () {
return this.getName();
});

Clazz.newMeth(C$, 'getAttributes', function () {
return this.attributeSet;
});

Clazz.newMeth(C$, 'getStartOffset', function () {
return this.start;
});

Clazz.newMeth(C$, 'getEndOffset', function () {
return this.end;
});

Clazz.newMeth(C$, 'getElementIndex$I', function (offset) {
var index;
var lower = 0;
var upper = this.nchildren - 1;
var mid = 0;
var p0 = this.getStartOffset();
var p1;
if (this.nchildren == 0) {
return 0;
}if (offset >= this.getEndOffset()) {
return this.nchildren - 1;
}if ((this.lastIndex >= lower) && (this.lastIndex <= upper) ) {
var lastHit = this.children[this.lastIndex];
p0 = lastHit.getStartOffset();
p1 = lastHit.getEndOffset();
if ((offset >= p0) && (offset < p1) ) {
return this.lastIndex;
}if (offset < p0) {
upper = this.lastIndex;
} else {
lower = this.lastIndex;
}}while (lower <= upper){
mid = lower + (((upper - lower)/2|0));
var elem = this.children[mid];
p0 = elem.getStartOffset();
p1 = elem.getEndOffset();
if ((offset >= p0) && (offset < p1) ) {
index = mid;
this.lastIndex = index;
return index;
} else if (offset < p0) {
upper = mid - 1;
} else {
lower = mid + 1;
}}
if (offset < p0) {
index = mid;
} else {
index = mid + 1;
}this.lastIndex = index;
return index;
});

Clazz.newMeth(C$, 'getElementCount', function () {
return this.nchildren;
});

Clazz.newMeth(C$, 'getElement$I', function (index) {
return (index >= this.nchildren ? null : this.children[index]);
});

Clazz.newMeth(C$, 'isLeaf', function () {
return (this.parent != null );
});
})()
})();
//Created 2018-02-06 09:00:26
