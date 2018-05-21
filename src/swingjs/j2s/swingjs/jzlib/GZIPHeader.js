(function(){var P$=Clazz.newPackage("swingjs.jzlib"),I$=[['swingjs.jzlib.ZStream','java.lang.InternalError']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "GZIPHeader", null, null, 'Cloneable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.text = false;
this.fhcrc = false;
this.time = 0;
this.xflags = 0;
this.os = 0;
this.extra = null;
this.name = null;
this.comment = null;
this.hcrc = 0;
this.crc = 0;
this.done = false;
this.mtime = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.text = false;
this.fhcrc = false;
this.os = 255;
this.done = false;
this.mtime = 0;
}, 1);

Clazz.newMeth(C$, 'setModifiedTime$J', function (mtime) {
this.mtime = mtime;
});

Clazz.newMeth(C$, 'getModifiedTime', function () {
return this.mtime;
});

Clazz.newMeth(C$, 'setOS$I', function (os) {
if ((0 <= os && os <= 13 ) || os == 255 ) this.os = os;
 else throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["os: " + os]);
});

Clazz.newMeth(C$, 'getOS', function () {
return this.os;
});

Clazz.newMeth(C$, 'setName$S', function (name) {
this.name = (I$[1]||$incl$(1)).getBytes$S(name);
});

Clazz.newMeth(C$, 'getName', function () {
if (this.name == null ) return "";
try {
return  String.instantialize(this.name, "ISO-8859-1");
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.UnsupportedEncodingException")){
throw Clazz.new_((I$[2]||$incl$(2)).c$$S,[e.toString()]);
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'setComment$S', function (comment) {
this.comment = (I$[1]||$incl$(1)).getBytes$S(comment);
});

Clazz.newMeth(C$, 'getComment', function () {
if (this.comment == null ) return "";
try {
return  String.instantialize(this.comment, "ISO-8859-1");
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.UnsupportedEncodingException")){
throw Clazz.new_((I$[2]||$incl$(2)).c$$S,[e.toString()]);
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'setCRC$J', function (crc) {
this.crc = crc;
});

Clazz.newMeth(C$, 'getCRC', function () {
return this.crc;
});

Clazz.newMeth(C$, 'put$swingjs_jzlib_Deflate', function (d) {
var flag = 0;
if (this.text) {
flag = flag|(1);
}if (this.fhcrc) {
flag = flag|(2);
}if (this.extra != null ) {
flag = flag|(4);
}if (this.name != null ) {
flag = flag|(8);
}if (this.comment != null ) {
flag = flag|(16);
}var xfl = 0;
if (d.level == 1) {
xfl = xfl|(4);
} else if (d.level == 9) {
xfl = xfl|(2);
}d.put_short$I(-29921);
d.put_byteB$B(($b$[0] = 8, $b$[0]));
d.put_byteB$B(($b$[0] = (flag|0), $b$[0]));
d.put_byteB$B(($b$[0] = (this.mtime|0), $b$[0]));
d.put_byteB$B(($b$[0] = ((this.mtime >> 8)|0), $b$[0]));
d.put_byteB$B(($b$[0] = ((this.mtime >> 16)|0), $b$[0]));
d.put_byteB$B(($b$[0] = ((this.mtime >> 24)|0), $b$[0]));
d.put_byteB$B(($b$[0] = (xfl|0), $b$[0]));
d.put_byteB$B(($b$[0] = (this.os|0), $b$[0]));
if (this.extra != null ) {
d.put_byteB$B(($b$[0] = (this.extra.length|0), $b$[0]));
d.put_byteB$B(($b$[0] = ((this.extra.length >> 8)|0), $b$[0]));
d.put_byte$BA$I$I(this.extra, 0, this.extra.length);
}if (this.name != null ) {
d.put_byte$BA$I$I(this.name, 0, this.name.length);
d.put_byteB$B(($b$[0] = 0, $b$[0]));
}if (this.comment != null ) {
d.put_byte$BA$I$I(this.comment, 0, this.comment.length);
d.put_byteB$B(($b$[0] = 0, $b$[0]));
}});

Clazz.newMeth(C$, 'clone', function () {
var gheader = Clazz.clone(this);
var tmp;
if (gheader.extra != null ) {
tmp = Clazz.array(Byte.TYPE, [gheader.extra.length]);
System.arraycopy(gheader.extra, 0, tmp, 0, tmp.length);
gheader.extra = tmp;
}if (gheader.name != null ) {
tmp = Clazz.array(Byte.TYPE, [gheader.name.length]);
System.arraycopy(gheader.name, 0, tmp, 0, tmp.length);
gheader.name = tmp;
}if (gheader.comment != null ) {
tmp = Clazz.array(Byte.TYPE, [gheader.comment.length]);
System.arraycopy(gheader.comment, 0, tmp, 0, tmp.length);
gheader.comment = tmp;
}return gheader;
});
var $b$ = new Int8Array(1);

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:18
