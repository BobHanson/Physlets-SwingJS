(function(){var P$=Clazz.newPackage("java.util.zip"),I$=[['java.util.Date','java.lang.InternalError']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ZipEntry", null, null, ['java.util.zip.ZipConstants', 'Cloneable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.offset = 0;
this.name = null;
this.time = 0;
this.crc = 0;
this.size = 0;
this.csize = 0;
this.method = 0;
this.flag = 0;
this.extra = null;
this.comment = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.time = -1;
this.crc = -1;
this.size = -1;
this.csize = -1;
this.method = -1;
this.flag = 0;
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.$init$.apply(this);
if (name == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (name.length$() > 65535) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["entry name too long"]);
}this.name = name;
}, 1);

Clazz.newMeth(C$, 'getName', function () {
return this.name;
});

Clazz.newMeth(C$, 'setTime$J', function (time) {
this.time = C$.javaToDosTime$J(time);
});

Clazz.newMeth(C$, 'getTime', function () {
return this.time != -1 ? C$.dosToJavaTime$J(this.time) : -1;
});

Clazz.newMeth(C$, 'setSize$J', function (size) {
if (size < 0) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["invalid entry size"]);
}this.size = size;
});

Clazz.newMeth(C$, 'getSize', function () {
return this.size;
});

Clazz.newMeth(C$, 'getCompressedSize', function () {
return this.csize;
});

Clazz.newMeth(C$, 'setCompressedSize$J', function (csize) {
this.csize = csize;
});

Clazz.newMeth(C$, 'setCrc$J', function (crc) {
if (crc < 0 || crc > 4294967295 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["invalid entry crc-32"]);
}this.crc = crc;
});

Clazz.newMeth(C$, 'getCrc', function () {
return this.crc;
});

Clazz.newMeth(C$, 'setMethod$I', function (method) {
if (method != 0 && method != 8 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["invalid compression method"]);
}this.method = method;
});

Clazz.newMeth(C$, 'getMethod', function () {
return this.method;
});

Clazz.newMeth(C$, 'setExtra$BA', function (extra) {
if (extra != null  && extra.length > 65535 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["invalid extra field length"]);
}this.extra = extra;
});

Clazz.newMeth(C$, 'getExtra', function () {
return this.extra;
});

Clazz.newMeth(C$, 'setComment$S', function (comment) {
this.comment = comment;
});

Clazz.newMeth(C$, 'getComment', function () {
return this.comment;
});

Clazz.newMeth(C$, 'isDirectory', function () {
return this.name.endsWith$S("/");
});

Clazz.newMeth(C$, 'toString', function () {
return this.getName();
});

Clazz.newMeth(C$, 'dosToJavaTime$J', function (dtime) {
var d = Clazz.new_((I$[1]||$incl$(1)),[((((dtime >> 25) & 127) + 80)|0), ((((dtime >> 21) & 15) - 1)|0), (((dtime >> 16) & 31)|0), (((dtime >> 11) & 31)|0), (((dtime >> 5) & 63)|0), (((dtime << 1) & 62)|0)]);
return d.getTime();
}, 1);

Clazz.newMeth(C$, 'javaToDosTime$J', function (time) {
var d = Clazz.new_((I$[1]||$incl$(1)),[time]);
var year = d.getYear() + 1900;
if (year < 1980) {
return 2162688;
}return (year - 1980) << 25 | (d.getMonth() + 1) << 21 | d.getDate() << 16 | d.getHours() << 11 | d.getMinutes() << 5 | d.getSeconds() >> 1;
}, 1);

Clazz.newMeth(C$, 'hashCode', function () {
return this.name.hashCode();
});

Clazz.newMeth(C$, 'clone', function () {
try {
var e = Clazz.clone(this);
if (this.extra != null ) {
e.extra = Clazz.array(Byte.TYPE, [this.extra.length]);
System.arraycopy(this.extra, 0, e.extra, 0, this.extra.length);
}return e;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
throw Clazz.new_((I$[2]||$incl$(2)));
} else {
throw e;
}
}
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:16
