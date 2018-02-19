(function(){var P$=Clazz.newPackage("jsjava.io"),I$=[['java.io.PushbackInputStream']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DataInputStream", null, 'java.io.FilterInputStream', 'java.io.DataInput');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.bytearr = null;
this.chararr = null;
this.readBuffer = null;
this.lineBuffer = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.bytearr = Clazz.array(Byte.TYPE, [80]);
this.chararr = Clazz.array(Character.TYPE, [80]);
this.readBuffer = Clazz.array(Byte.TYPE, [8]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_InputStream', function ($in) {
C$.superclazz.c$$java_io_InputStream.apply(this, [$in]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'read$BA$I$I', function (b, off, len) {
{
if (arguments.length == 1) { off = 0;
len = b.length;
}
}
return this.$in.read$BA$I$I(b, off, len);
});

Clazz.newMeth(C$, 'readFully$BA$I$I', function (b, off, len) {
if (len < 0) throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
var n = 0;
while (n < len){
var count = this.$in.read$BA$I$I(b, off + n, len - n);
if (count < 0) throw Clazz.new_(Clazz.load('java.io.EOFException'));
n = n+(count);
}
});

Clazz.newMeth(C$, 'skipBytes$I', function (n) {
var total = 0;
var cur = 0;
while ((total < n) && ((cur = (this.$in.skip$J(n - total)|0)) > 0) ){
total = total+(cur);
}
return total;
});

Clazz.newMeth(C$, 'readBoolean', function () {
var ch;
if (ch < 0) throw Clazz.new_(Clazz.load('java.io.EOFException'));
return (ch != 0);
});

Clazz.newMeth(C$, 'readByte', function () {
var ch;
if (ch < 0) throw Clazz.new_(Clazz.load('java.io.EOFException'));
return $b$[0] = ((ch)|0), $b$[0];
});

Clazz.newMeth(C$, 'readUnsignedByte', function () {
var ch;
if (ch < 0) throw Clazz.new_(Clazz.load('java.io.EOFException'));
return ch;
});

Clazz.newMeth(C$, 'readShort', function () {
var ch1;
var ch2;
if ((ch1 | ch2) < 0) throw Clazz.new_(Clazz.load('java.io.EOFException'));
var n = ($s$[0] = ((ch1 << 8) + (ch2 << 0)), $s$[0]);
{
return (n > 0x7FFF ? n - 0x10000 : n);
}
});

Clazz.newMeth(C$, 'readUnsignedShort', function () {
var ch1;
var ch2;
if ((ch1 | ch2) < 0) throw Clazz.new_(Clazz.load('java.io.EOFException'));
return (ch1 << 8) + (ch2 << 0);
});

Clazz.newMeth(C$, 'readChar', function () {
var ch1;
var ch2;
if ((ch1 | ch2) < 0) throw Clazz.new_(Clazz.load('java.io.EOFException'));
return String.fromCharCode(((ch1 << 8) + (ch2 << 0)));
});

Clazz.newMeth(C$, 'readInt', function () {
var ch1;
var ch2;
var ch3;
var ch4;
if ((ch1 | ch2 | ch3 | ch4 ) < 0) throw Clazz.new_(Clazz.load('java.io.EOFException'));
var n = ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0) );
{
return (n > 0x7FFFFFFF ? n - 0x100000000 : n);
}
});

Clazz.newMeth(C$, 'readLong', function () {
this.readFully$BA$I$I(this.readBuffer, 0, 8);
return ((this.readBuffer[0] << 56) + ((this.readBuffer[1] & 255) << 48) + ((this.readBuffer[2] & 255) << 40) + ((this.readBuffer[3] & 255) << 32) + ((this.readBuffer[4] & 255) << 24) + ((this.readBuffer[5] & 255) << 16) + ((this.readBuffer[6] & 255) << 8) + ((this.readBuffer[7] & 255) << 0) );
});

Clazz.newMeth(C$, 'readFloat', function () {
return Float.intBitsToFloat(this.readInt());
});

Clazz.newMeth(C$, 'readDouble', function () {
return Double.longBitsToDouble(this.readLong());
});

Clazz.newMeth(C$, 'readLine', function () {
var buf = this.lineBuffer;
if (buf == null ) {
buf = this.lineBuffer = Clazz.array(Character.TYPE, [128]);
}var room = buf.length;
var offset = 0;
var c;
loop : while (true){
switch () {
case -1:
case 10:
break loop;
case 13:
var c2;
if ((c2 != 10 ) && (c2 != -1) ) {
if (!(Clazz.instanceOf(this.$in, "java.io.PushbackInputStream"))) {
this.$in = Clazz.new_((I$[1]||$incl$(1)).c$$java_io_InputStream$I,[this.$in, 1]);
}(this.$in).unreadByte$I(c2);
}break loop;
default:
if (--room < 0) {
buf = Clazz.array(Character.TYPE, [offset + 128]);
room = buf.length - offset - 1 ;
System.arraycopy(this.lineBuffer, 0, buf, 0, offset);
this.lineBuffer = buf;
}buf[offset++] = String.fromCharCode(c);
break;
}
}
if ((c == -1) && (offset == 0) ) {
return null;
}return String.copyValueOf$CA$I$I(buf, 0, offset);
});

Clazz.newMeth(C$, 'readUTF', function () {
return java.io.DataInputStream.readUTFBytes$java_io_DataInput$I(this, -1);
});

Clazz.newMeth(C$, 'readUTFBytes$java_io_DataInput$I', function ($in, utflen) {
var isByteArray = (utflen >= 0);
if (!isByteArray) utflen = $in.readUnsignedShort();
var bytearr = null;
var chararr = null;
if (Clazz.instanceOf($in, "java.io.DataInputStream")) {
var dis = $in;
if (dis.bytearr.length < utflen) {
dis.bytearr = Clazz.array(Byte.TYPE, [isByteArray ? utflen : utflen * 2]);
dis.chararr = Clazz.array(Character.TYPE, [dis.bytearr.length]);
}chararr = dis.chararr;
bytearr = dis.bytearr;
} else {
bytearr = Clazz.array(Byte.TYPE, [utflen]);
chararr = Clazz.array(Character.TYPE, [utflen]);
}var c;
var char2;
var char3;
var count = 0;
var chararr_count = 0;
$in.readFully$BA$I$I(bytearr, 0, utflen);
while (count < utflen){
c = bytearr[count] & 255;
if (c > 127) break;
count++;
chararr[chararr_count++] = String.fromCharCode(c);
}
while (count < utflen){
c = bytearr[count] & 255;
switch (c >> 4) {
case 0:
case 1:
case 2:
case 3:
case 4:
case 5:
case 6:
case 7:
count++;
chararr[chararr_count++] = String.fromCharCode(c);
break;
case 12:
case 13:
count = count+(2);
if (count > utflen) throw Clazz.new_(Clazz.load('java.io.UTFDataFormatException').c$$S,["malformed input: partial character at end"]);
char2 = bytearr[count - 1];
if ((char2 & 192) != 128) throw Clazz.new_(Clazz.load('java.io.UTFDataFormatException').c$$S,["malformed input around byte " + count]);
chararr[chararr_count++] = String.fromCharCode((((c & 31) << 6) | (char2 & 63)));
break;
case 14:
count = count+(3);
if (count > utflen) throw Clazz.new_(Clazz.load('java.io.UTFDataFormatException').c$$S,["malformed input: partial character at end"]);
char2 = bytearr[count - 2];
char3 = bytearr[count - 1];
if (((char2 & 192) != 128) || ((char3 & 192) != 128) ) throw Clazz.new_(Clazz.load('java.io.UTFDataFormatException').c$$S,["malformed input around byte " + (count - 1)]);
chararr[chararr_count++] = String.fromCharCode((((c & 15) << 12) | ((char2 & 63) << 6) | ((char3 & 63) << 0) ));
break;
default:
throw Clazz.new_(Clazz.load('java.io.UTFDataFormatException').c$$S,["malformed input around byte " + count]);
}
}
return  String.instantialize(chararr, 0, chararr_count);
}, 1);
var $b$ = new Int8Array(1);
var $s$ = new Int16Array(1);

Clazz.newMeth(C$);
})();
//Created 2018-02-01 21:08:13
