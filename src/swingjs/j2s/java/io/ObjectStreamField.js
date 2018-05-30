(function(){var P$=java.io,I$=[['java.lang.Boolean','java.io.ObjectStreamField$1','java.util.Arrays']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ObjectStreamField", null, null, 'Comparable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.name = null;
this.type = null;
this.offset = 0;
this.typeString = null;
this.unshared = false;
this.isDeserialized = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Class', function (name, cl) {
C$.$init$.apply(this);
if (name == null  || cl == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.name=name;
this.type=cl;
}, 1);

Clazz.newMeth(C$, 'c$$S$Class$Z', function (name, cl, unshared) {
C$.$init$.apply(this);
if (name == null  || cl == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.name=name;
this.type=cl;
this.unshared=unshared;
}, 1);

Clazz.newMeth(C$, 'c$$S$S', function (signature, name) {
C$.$init$.apply(this);
if (name == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.name=name;
this.typeString=signature.$replace(".", "/");
this.isDeserialized=true;
}, 1);

Clazz.newMeth(C$, ['compareTo$O','compareTo$TT'], function (o) {
var f = o;
var thisPrimitive = this.isPrimitive();
var fPrimitive = f.isPrimitive();
if (thisPrimitive != fPrimitive ) {
return thisPrimitive ? -1 : 1;
}return this.getName().compareTo$S(f.getName());
});

Clazz.newMeth(C$, 'equals$O', function (arg0) {
return this.compareTo$O(arg0) == 0;
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.getName().hashCode();
});

Clazz.newMeth(C$, 'getName', function () {
return this.name;
});

Clazz.newMeth(C$, 'getOffset', function () {
return this.offset;
});

Clazz.newMeth(C$, 'getTypeInternal', function () {
return this.type;
});

Clazz.newMeth(C$, 'getType', function () {
var cl = p$.getTypeInternal.apply(this, []);
if (this.isDeserialized && !cl.isPrimitive() ) {
return Clazz.getClass(java.lang.Object);
}return cl;
});

Clazz.newMeth(C$, 'getTypeCode', function () {
var t = p$.getTypeInternal.apply(this, []);
if (t === Integer.TYPE ) {
return "I";
}if (t === Byte.TYPE ) {
return "B";
}if (t === Character.TYPE ) {
return "C";
}if (t === Short.TYPE ) {
return "S";
}if (t === (I$[1]||$incl$(1)).TYPE ) {
return "Z";
}if (t === Long.TYPE ) {
return "J";
}if (t === Float.TYPE ) {
return "F";
}if (t === Double.TYPE ) {
return "D";
}if (t.isArray()) {
return "[";
}return "L";
});

Clazz.newMeth(C$, 'getTypeString', function () {
if (this.isPrimitive()) {
return null;
}if (this.typeString == null ) {
var t = p$.getTypeInternal.apply(this, []);
var typeName = t.getName().$replace(".", "/");
var str = (t.isArray()) ? typeName : ("L" + typeName + ';' );
this.typeString=str.intern();
}return this.typeString;
});

Clazz.newMeth(C$, 'isPrimitive', function () {
var t = p$.getTypeInternal.apply(this, []);
return t != null  && t.isPrimitive() ;
});

Clazz.newMeth(C$, 'setOffset$I', function (newValue) {
this.offset=newValue;
});

Clazz.newMeth(C$, 'toString', function () {
return this.getClass().getName() + '(' + this.getName() + ':' + p$.getTypeInternal.apply(this, []) + ')' ;
});

Clazz.newMeth(C$, 'sortFields$java_io_ObjectStreamFieldA', function (fields) {
if (fields.length > 1) {
var fieldDescComparator = ((
(function(){var C$=Clazz.newClass(P$, "ObjectStreamField$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Comparator', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['compare$java_io_ObjectStreamField$java_io_ObjectStreamField','compare$TT$TT'], function (f1, f2) {
return f1.compareTo$O(f2);
});
})()
), Clazz.new_((I$[2]||$incl$(2)).$init$, [this, null]));
(I$[3]||$incl$(3)).sort$TTA$java_util_Comparator(fields, fieldDescComparator);
}}, 1);

Clazz.newMeth(C$, 'resolve$ClassLoader', function (loader) {
if (this.typeString.length$() == 1) {
switch ((this.typeString.charCodeAt(0))) {
case 73:
this.type=Integer.TYPE;
return;
case 66:
this.type=Byte.TYPE;
return;
case 67:
this.type=Character.TYPE;
return;
case 83:
this.type=Short.TYPE;
return;
case 90:
this.type=(I$[1]||$incl$(1)).TYPE;
return;
case 74:
this.type=Long.TYPE;
return;
case 70:
this.type=Float.TYPE;
return;
case 68:
this.type=Double.TYPE;
return;
}
}var className = this.typeString.$replace("/", ".");
if (className.charAt(0) == "L") {
className=className.substring(1, className.length$() - 1);
}try {
var cl = Clazz.forName(className, false, loader);
this.type=cl;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.ClassNotFoundException")){
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'isUnshared', function () {
return this.unshared;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:34
