(function(){var P$=java.util,I$=[['java.util.HashMap','java.util.LinkedHashMap','java.lang.InternalError']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "HashSet", null, 'java.util.AbstractSet', ['java.util.Set', 'Cloneable', 'java.io.Serializable']);
C$.PRESENT = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.PRESENT =  Clazz.new_();
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.map = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.map = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Collection', function (c) {
Clazz.super_(C$, this,1);
this.map = Clazz.new_((I$[1]||$incl$(1)).c$$I,[Math.max(((c.size() / 0.75)|0) + 1, 16)]);
this.addAll$java_util_Collection(c);
}, 1);

Clazz.newMeth(C$, 'c$$I$F', function (initialCapacity, loadFactor) {
Clazz.super_(C$, this,1);
this.map = Clazz.new_((I$[1]||$incl$(1)).c$$I$F,[initialCapacity, loadFactor]);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (initialCapacity) {
Clazz.super_(C$, this,1);
this.map = Clazz.new_((I$[1]||$incl$(1)).c$$I,[initialCapacity]);
}, 1);

Clazz.newMeth(C$, 'c$$I$F$Z', function (initialCapacity, loadFactor, dummy) {
Clazz.super_(C$, this,1);
this.map = Clazz.new_((I$[2]||$incl$(2)).c$$I$F,[initialCapacity, loadFactor]);
}, 1);

Clazz.newMeth(C$, 'iterator', function () {
return this.map.keySet().iterator();
});

Clazz.newMeth(C$, 'size', function () {
return this.map.size();
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.map.isEmpty();
});

Clazz.newMeth(C$, 'contains$O', function (o) {
return this.map.containsKey$O(o);
});

Clazz.newMeth(C$, ['add$TE'], function (e) {
return this.map.put$TK$TV(e, C$.PRESENT) == null ;
});

Clazz.newMeth(C$, 'remove$O', function (o) {
return this.map.remove$O(o) === C$.PRESENT ;
});

Clazz.newMeth(C$, 'clear', function () {
this.map.clear();
});

Clazz.newMeth(C$, 'clone', function () {
try {
var newSet = Clazz.clone(this);
newSet.map = this.map.clone();
return newSet;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
throw Clazz.new_((I$[3]||$incl$(3)));
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (s) {
s.defaultWriteObject();
s.writeInt$I(this.map.capacity());
s.writeFloat$F(this.map.loadFactor());
s.writeInt$I(this.map.size());
for (var i = this.map.keySet().iterator(); i.hasNext(); ) s.writeObject$O(i.next());

});

Clazz.newMeth(C$, 'readObject$java_io_ObjectInputStream', function (s) {
s.defaultReadObject();
var capacity = s.readInt();
var loadFactor = s.readFloat();
this.map = (Clazz.instanceOf((this), "java.util.LinkedHashSet") ? Clazz.new_((I$[2]||$incl$(2)).c$$I$F,[capacity, loadFactor]) : Clazz.new_((I$[1]||$incl$(1)).c$$I$F,[capacity, loadFactor]));
var size = s.readInt();
for (var i = 0; i < size; i++) {
var e = s.readObject();
this.map.put$TK$TV(e, C$.PRESENT);
}
});
})();
//Created 2018-02-06 08:58:50
