(function(){var P$=java.lang,I$=[['java.lang.Enum']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(java.lang, "Enum", null, null, ['Comparable', 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$name = null;
this.$ordinal = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'name', function () {
return this.$name;
});

Clazz.newMeth(C$, 'ordinal', function () {
return this.$ordinal;
});

Clazz.newMeth(C$, 'c$$S$I', function (name, ordinal) {
C$.$init$.apply(this);
this.$name = name;
this.$ordinal = ordinal;
}, 1);

Clazz.newMeth(C$, 'toString', function () {
return this.$name;
});

Clazz.newMeth(C$, 'equals$O', function (other) {
return this === other ;
});

Clazz.newMeth(C$, 'hashCode', function () {
return C$.superclazz.prototype.hashCode.apply(this, []);
});

Clazz.newMeth(C$, 'clone', function () {
throw Clazz.new_(Clazz.load('java.lang.CloneNotSupportedException'));
});

Clazz.newMeth(C$, ['compareTo$TE','compareTo$TT'], function (o) {
var other = o;
var self = this;
if (self.getClass() !== other.getClass()  && self.getDeclaringClass() !== other.getDeclaringClass()  ) throw Clazz.new_(Clazz.load('java.lang.ClassCastException'));
return self.$ordinal - other.$ordinal;
});

Clazz.newMeth(C$, 'getDeclaringClass', function () {
var clazz = this.getClass();
var zuper = clazz.getSuperclass();
return ((zuper === Clazz.getClass((I$[1]||$incl$(1))) ) ? clazz : zuper);
});

Clazz.newMeth(C$, '$valueOf$Class$S', function (enumType, name) {
var result = null;
{
result = enumType.$clazz$[name];
}
if (result != null ) return result;
if (name == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["Name is null"]);
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["No enum const " + enumType + "." + name ]);
}, 1);

Clazz.newMeth(C$, 'finalize', function () {
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:07
