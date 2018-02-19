(function(){var P$=java.util,I$=[['java.util.AbstractMap$1$1','java.util.AbstractSet','java.util.AbstractMap$2$1','java.util.AbstractCollection','java.lang.StringBuilder']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AbstractMap", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, 'java.util.Map');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$keySet = null;
this.$values = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$keySet = null;
this.$values = null;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'size', function () {
return this.entrySet().size();
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.size() == 0;
});

Clazz.newMeth(C$, 'containsValue$O', function (value) {
var i = this.entrySet().iterator();
if (value == null ) {
while (i.hasNext()){
var e = i.next();
if (e.getValue() == null ) return true;
}
} else {
while (i.hasNext()){
var e = i.next();
if (value.equals$O(e.getValue())) return true;
}
}return false;
});

Clazz.newMeth(C$, 'containsKey$O', function (key) {
var i = this.entrySet().iterator();
if (key == null ) {
while (i.hasNext()){
var e = i.next();
if (e.getKey() == null ) return true;
}
} else {
while (i.hasNext()){
var e = i.next();
if (key.equals$O(e.getKey())) return true;
}
}return false;
});

Clazz.newMeth(C$, 'get$O', function (key) {
var i = this.entrySet().iterator();
if (key == null ) {
while (i.hasNext()){
var e = i.next();
if (e.getKey() == null ) return e.getValue();
}
} else {
while (i.hasNext()){
var e = i.next();
if (key.equals$O(e.getKey())) return e.getValue();
}
}return null;
});

Clazz.newMeth(C$, ['put$TK$TV'], function (key, value) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'remove$O', function (key) {
var i = this.entrySet().iterator();
var correctEntry = null;
if (key == null ) {
while (correctEntry == null  && i.hasNext() ){
var e = i.next();
if (e.getKey() == null ) correctEntry = e;
}
} else {
while (correctEntry == null  && i.hasNext() ){
var e = i.next();
if (key.equals$O(e.getKey())) correctEntry = e;
}
}var oldValue = null;
if (correctEntry != null ) {
oldValue = correctEntry.getValue();
i.remove();
}return oldValue;
});

Clazz.newMeth(C$, 'putAll$java_util_Map', function (m) {
for (var e, $e = m.entrySet().iterator(); $e.hasNext()&&((e=$e.next()),1);) this.put$TK$TV(e.getKey(), e.getValue());

});

Clazz.newMeth(C$, 'clear', function () {
this.entrySet().clear();
});

Clazz.newMeth(C$, 'keySet', function () {
if (this.$keySet == null ) {
this.$keySet = ((
(function(){var C$=Clazz.newClass(P$, "AbstractMap$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.util.AbstractSet'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return ((
(function(){var C$=Clazz.newClass(P$, "AbstractMap$1$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Iterator', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.i = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.i = this.b$['java.util.AbstractMap'].entrySet().iterator();
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.i.hasNext();
});

Clazz.newMeth(C$, 'next', function () {
return this.i.next().getKey();
});

Clazz.newMeth(C$, 'remove', function () {
this.i.remove();
});
})()
), Clazz.new_((I$[1]||$incl$(1)).$init$, [this, null]));
});

Clazz.newMeth(C$, 'size', function () {
return this.b$['java.util.AbstractMap'].size();
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.b$['java.util.AbstractMap'].isEmpty();
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.AbstractMap'].clear();
});

Clazz.newMeth(C$, 'contains$O', function (k) {
return this.b$['java.util.AbstractMap'].containsKey$O(k);
});
})()
), Clazz.new_((I$[2]||$incl$(2)), [this, null],P$.AbstractMap$1));
}return this.$keySet;
});

Clazz.newMeth(C$, 'values', function () {
if (this.$values == null ) {
this.$values = ((
(function(){var C$=Clazz.newClass(P$, "AbstractMap$2", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.util.AbstractCollection'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return ((
(function(){var C$=Clazz.newClass(P$, "AbstractMap$2$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Iterator', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.i = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.i = this.b$['java.util.AbstractMap'].entrySet().iterator();
}, 1);

Clazz.newMeth(C$, 'hasNext', function () {
return this.i.hasNext();
});

Clazz.newMeth(C$, 'next', function () {
return this.i.next().getValue();
});

Clazz.newMeth(C$, 'remove', function () {
this.i.remove();
});
})()
), Clazz.new_((I$[3]||$incl$(3)).$init$, [this, null]));
});

Clazz.newMeth(C$, 'size', function () {
return this.b$['java.util.AbstractMap'].size();
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.b$['java.util.AbstractMap'].isEmpty();
});

Clazz.newMeth(C$, 'clear', function () {
this.b$['java.util.AbstractMap'].clear();
});

Clazz.newMeth(C$, 'contains$O', function (v) {
return this.b$['java.util.AbstractMap'].containsValue$O(v);
});
})()
), Clazz.new_((I$[4]||$incl$(4)), [this, null],P$.AbstractMap$2));
}return this.$values;
});

Clazz.newMeth(C$, 'equals$O', function (o) {
if (o === this ) return true;
if (!(Clazz.instanceOf(o, "java.util.Map"))) return false;
var m = o;
if (m.size() != this.size()) return false;
try {
var i = this.entrySet().iterator();
while (i.hasNext()){
var e = i.next();
var key = e.getKey();
var value = e.getValue();
if (value == null ) {
if (!(m.get$O(key) == null  && m.containsKey$O(key) )) return false;
} else {
if (!value.equals$O(m.get$O(key))) return false;
}}
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.lang.ClassCastException")){
var unused = e$$;
{
return false;
}
} else if (Clazz.exceptionOf(e$$, "java.lang.NullPointerException")){
var unused = e$$;
{
return false;
}
} else {
throw e$$;
}
}
return true;
});

Clazz.newMeth(C$, 'hashCode', function () {
var h = 0;
var i = this.entrySet().iterator();
while (i.hasNext())h = h+(i.next().hashCode());

return h;
});

Clazz.newMeth(C$, 'toString', function () {
var i = this.entrySet().iterator();
if (!i.hasNext()) return "{}";
var sb = Clazz.new_((I$[5]||$incl$(5)));
sb.append$C("{");
for (; ; ) {
var e = i.next();
var key = e.getKey();
var value = e.getValue();
sb.append$O(key === this  ? "(this Map)" : key);
sb.append$C("=");
sb.append$O(value === this  ? "(this Map)" : value);
if (!i.hasNext()) return sb.append$C("}").toString();
sb.append$S(", ");
}
});

Clazz.newMeth(C$, 'clone', function () {
var result = Clazz.clone(this);
result.$keySet = null;
result.$values = null;
return result;
});

Clazz.newMeth(C$, 'eq$O$O', function (o1, o2) {
return o1 == null  ? o2 == null  : o1.equals$O(o2);
}, 1);
;
(function(){var C$=Clazz.newClass(P$.AbstractMap, "SimpleEntry", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, [['java.util.Map','java.util.Map.Entry'], 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.key = null;
this.value = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$TK$TV'], function (key, value) {
C$.$init$.apply(this);
this.key = key;
this.value = value;
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Map_Entry', function (entry) {
C$.$init$.apply(this);
this.key = entry.getKey();
this.value = entry.getValue();
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
return P$.AbstractMap.eq$O$O(this.key, e.getKey()) && P$.AbstractMap.eq$O$O(this.value, e.getValue()) ;
});

Clazz.newMeth(C$, 'hashCode', function () {
return (this.key == null  ? 0 : this.key.hashCode()) ^ (this.value == null  ? 0 : this.value.hashCode());
});

Clazz.newMeth(C$, 'toString', function () {
return this.key + "=" + this.value ;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.AbstractMap, "SimpleImmutableEntry", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, [['java.util.Map','java.util.Map.Entry'], 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.key = null;
this.value = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['c$$TK$TV'], function (key, value) {
C$.$init$.apply(this);
this.key = key;
this.value = value;
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Map_Entry', function (entry) {
C$.$init$.apply(this);
this.key = entry.getKey();
this.value = entry.getValue();
}, 1);

Clazz.newMeth(C$, 'getKey', function () {
return this.key;
});

Clazz.newMeth(C$, 'getValue', function () {
return this.value;
});

Clazz.newMeth(C$, ['setValue$TV'], function (value) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$, 'equals$O', function (o) {
if (!(Clazz.instanceOf(o, "java.util.Map.Entry"))) return false;
var e = o;
return P$.AbstractMap.eq$O$O(this.key, e.getKey()) && P$.AbstractMap.eq$O$O(this.value, e.getValue()) ;
});

Clazz.newMeth(C$, 'hashCode', function () {
return (this.key == null  ? 0 : this.key.hashCode()) ^ (this.value == null  ? 0 : this.value.hashCode());
});

Clazz.newMeth(C$, 'toString', function () {
return this.key + "=" + this.value ;
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-08 10:02:11
