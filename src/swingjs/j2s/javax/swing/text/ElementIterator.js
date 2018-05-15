(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[['java.util.Stack','java.lang.InternalError',['javax.swing.text.ElementIterator','.StackItem']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ElementIterator", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, 'Cloneable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.root = null;
this.elementStack = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.elementStack = null;
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_text_Document', function (document) {
C$.$init$.apply(this);
this.root = document.getDefaultRootElement();
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_text_Element', function (root) {
C$.$init$.apply(this);
this.root = root;
}, 1);

Clazz.newMeth(C$, 'clone', function () {
try {
var it = Clazz.new_(C$.c$$javax_swing_text_Element,[this.root]);
if (this.elementStack != null ) {
it.elementStack = Clazz.new_((I$[1]||$incl$(1)));
for (var i = 0; i < this.elementStack.size(); i++) {
var item = this.elementStack.elementAt$I(i);
var clonee = item.clone();
it.elementStack.push$TE(clonee);
}
}return it;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
throw Clazz.new_((I$[2]||$incl$(2)));
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'first', function () {
if (this.root == null ) {
return null;
}this.elementStack = Clazz.new_((I$[1]||$incl$(1)));
if (this.root.getElementCount() != 0) {
this.elementStack.push$TE(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_text_Element, [this, null, this.root]));
}return this.root;
});

Clazz.newMeth(C$, 'depth', function () {
if (this.elementStack == null ) {
return 0;
}return this.elementStack.size();
});

Clazz.newMeth(C$, 'current', function () {
if (this.elementStack == null ) {
return this.first();
}if (!this.elementStack.empty()) {
var item = this.elementStack.peek();
var elem = item.getElement();
var index = item.getIndex();
if (index == -1) {
return elem;
}return elem.getElement$I(index);
}return null;
});

Clazz.newMeth(C$, 'next', function () {
if (this.elementStack == null ) {
return this.first();
}if (this.elementStack.isEmpty()) {
return null;
}var item = this.elementStack.peek();
var elem = item.getElement();
var index = item.getIndex();
if (index + 1 < elem.getElementCount()) {
var child = elem.getElement$I(index + 1);
if (child.isLeaf()) {
item.incrementIndex();
} else {
this.elementStack.push$TE(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_text_Element, [this, null, child]));
}return child;
} else {
this.elementStack.pop();
if (!this.elementStack.isEmpty()) {
var top = this.elementStack.peek();
top.incrementIndex();
return this.next();
}}return null;
});

Clazz.newMeth(C$, 'previous', function () {
var stackSize;
if (this.elementStack == null  || (stackSize = this.elementStack.size()) == 0 ) {
return null;
}var item = this.elementStack.peek();
var elem = item.getElement();
var index = item.getIndex();
if (index > 0) {
return p$.getDeepestLeaf$javax_swing_text_Element.apply(this, [elem.getElement$I(--index)]);
} else if (index == 0) {
return elem;
} else if (index == -1) {
if (stackSize == 1) {
return null;
}var top = this.elementStack.pop();
item = this.elementStack.peek();
this.elementStack.push$TE(top);
elem = item.getElement();
index = item.getIndex();
return ((index == -1) ? elem : p$.getDeepestLeaf$javax_swing_text_Element.apply(this, [elem.getElement$I(index)]));
}return null;
});

Clazz.newMeth(C$, 'getDeepestLeaf$javax_swing_text_Element', function (parent) {
if (parent.isLeaf()) {
return parent;
}var childCount = parent.getElementCount();
if (childCount == 0) {
return parent;
}return p$.getDeepestLeaf$javax_swing_text_Element.apply(this, [parent.getElement$I(childCount - 1)]);
});
;
(function(){var C$=Clazz.newClass(P$.ElementIterator, "StackItem", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'Cloneable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.item = null;
this.childIndex = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_text_Element', function (elem) {
C$.$init$.apply(this);
this.item = elem;
this.childIndex = -1;
}, 1);

Clazz.newMeth(C$, 'incrementIndex', function () {
this.childIndex++;
});

Clazz.newMeth(C$, 'getElement', function () {
return this.item;
});

Clazz.newMeth(C$, 'getIndex', function () {
return this.childIndex;
});

Clazz.newMeth(C$, 'clone', function () {
return Clazz.clone(this);
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:54
