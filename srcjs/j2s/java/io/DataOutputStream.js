(function(){var P$=java.io,I$=[];
var C$=Clazz.newClass(P$, "DataOutputStream", null, 'java.io.FilterOutputStream', 'java.io.DataOutput');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.written = 0;
this.bytearr = null;
this.writeBuffer = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.bytearr = null;
this.writeBuffer = Clazz.array(Byte.TYPE, [8]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream', function (out) {
C$.superclazz.c$$java_io_OutputStream.apply(this, [out]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'incCount$I', function (value) {
var temp = this.written + value;
if (temp < 0) {
temp = 2147483647;
}this.written = temp;
});

Clazz.newMeth(C$, 'write$I', function (b) {
this.out.write$I(b);
p$.incCount$I.apply(this, [1]);
});

Clazz.newMeth(C$, 'write$BA$I$I', function (b, off, len) {
this.out.write$BA$I$I(b, off, len);
p$.incCount$I.apply(this, [len]);
});

Clazz.newMeth(C$, 'flush', function () {
this.out.flush();
});

Clazz.newMeth(C$, 'writeBoolean$Z', function (v) {
this.out.write$I(v ? 1 : 0);
p$.incCount$I.apply(this, [1]);
});

Clazz.newMeth(C$, 'writeByte$I', function (v) {
this.out.write$I(v);
p$.incCount$I.apply(this, [1]);
});

Clazz.newMeth(C$, 'writeShort$I', function (v) {
this.out.write$I((v >>> 8) & 255);
this.out.write$I((v >>> 0) & 255);
p$.incCount$I.apply(this, [2]);
});

Clazz.newMeth(C$, 'writeChar$I', function (v) {
this.out.write$I((v >>> 8) & 255);
this.out.write$I((v >>> 0) & 255);
p$.incCount$I.apply(this, [2]);
});

Clazz.newMeth(C$, 'writeInt$I', function (v) {
this.out.write$I((v >>> 24) & 255);
this.out.write$I((v >>> 16) & 255);
this.out.write$I((v >>> 8) & 255);
this.out.write$I((v >>> 0) & 255);
p$.incCount$I.apply(this, [4]);
});

Clazz.newMeth(C$, 'writeLong$J', function (v) {
this.writeBuffer[0] = (((v >>> 56)|0)|0);
this.writeBuffer[1] = (((v >>> 48)|0)|0);
this.writeBuffer[2] = (((v >>> 40)|0)|0);
this.writeBuffer[3] = (((v >>> 32)|0)|0);
this.writeBuffer[4] = (((v >>> 24)|0)|0);
this.writeBuffer[5] = (((v >>> 16)|0)|0);
this.writeBuffer[6] = (((v >>> 8)|0)|0);
this.writeBuffer[7] = (((v >>> 0)|0)|0);
this.out.write$BA$I$I(this.writeBuffer, 0, 8);
p$.incCount$I.apply(this, [8]);
});

Clazz.newMeth(C$, 'writeFloat$F', function (v) {
this.writeInt$I(Float.floatToIntBits(v));
});

Clazz.newMeth(C$, 'writeDouble$D', function (v) {
this.writeLong$J(Double.doubleToLongBits(v));
});

Clazz.newMeth(C$, 'writeBytes$S', function (s) {
var len = s.length$();
for (var i = 0; i < len; i++) {
this.out.write$I(($b$[0] = s.charAt(i).$c(), $b$[0]));
}
p$.incCount$I.apply(this, [len]);
});

Clazz.newMeth(C$, 'writeChars$S', function (s) {
var len = s.length$();
for (var i = 0; i < len; i++) {
var v = s.charAt(i).$c();
this.out.write$I((v >>> 8) & 255);
this.out.write$I((v >>> 0) & 255);
}
p$.incCount$I.apply(this, [len * 2]);
});

Clazz.newMeth(C$, 'writeUTF$S', function (str) {
C$.writeUTF$S$java_io_DataOutput(str, this);
});

Clazz.newMeth(C$, 'writeUTF$S$java_io_DataOutput', function (str, out) {
var strlen = str.length$();
var utflen = 0;
var c;
var count = 0;
for (var i = 0; i < strlen; i++) {
c = str.charAt(i).$c();
if ((c >= 1) && (c <= 127) ) {
utflen++;
} else if (c > 2047) {
utflen = utflen+(3);
} else {
utflen = utflen+(2);
}}
if (utflen > 65535) throw Clazz.new_(Clazz.load('java.io.UTFDataFormatException').c$$S,["encoded string too long: " + utflen + " bytes" ]);
var bytearr = null;
if (Clazz.instanceOf(out, "java.io.DataOutputStream")) {
var dos = out;
if (dos.bytearr == null  || (dos.bytearr.length < (utflen + 2)) ) dos.bytearr = Clazz.array(Byte.TYPE, [(utflen * 2) + 2]);
bytearr = dos.bytearr;
} else {
bytearr = Clazz.array(Byte.TYPE, [utflen + 2]);
}bytearr[count++] = ((((utflen >>> 8) & 255)|0)|0);
bytearr[count++] = ((((utflen >>> 0) & 255)|0)|0);
var i = 0;
for (i = 0; i < strlen; i++) {
c = str.charAt(i).$c();
if (!((c >= 1) && (c <= 127) )) break;
bytearr[count++] = ((c|0)|0);
}
for (; i < strlen; i++) {
c = str.charAt(i).$c();
if ((c >= 1) && (c <= 127) ) {
bytearr[count++] = ((c|0)|0);
} else if (c > 2047) {
bytearr[count++] = (((224 | ((c >> 12) & 15))|0)|0);
bytearr[count++] = (((128 | ((c >> 6) & 63))|0)|0);
bytearr[count++] = (((128 | ((c >> 0) & 63))|0)|0);
} else {
bytearr[count++] = (((192 | ((c >> 6) & 31))|0)|0);
bytearr[count++] = (((128 | ((c >> 0) & 63))|0)|0);
}}
out.write$BA$I$I(bytearr, 0, utflen + 2);
return utflen + 2;
}, 1);

Clazz.newMeth(C$, 'size', function () {
return this.written;
});
var $b$ = new Int8Array(1);

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:29