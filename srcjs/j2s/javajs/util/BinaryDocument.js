(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['java.io.DataInputStream']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "BinaryDocument", null, 'javajs.util.BC', 'javajs.api.GenericBinaryDocument');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.stream = null;
this.isRandom = false;
this.isBigEndian = false;
this.bis = null;
this.nBytes = 0;
this.out = null;
this.t8 = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.isRandom = false;
this.isBigEndian = true;
this.t8 = Clazz.array(Byte.TYPE, [8]);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'close', function () {
if (this.stream != null ) try {
this.stream.close();
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
} else {
throw e;
}
}
if (this.out != null ) this.out.closeChannel();
});

Clazz.newMeth(C$, 'setStream$java_io_BufferedInputStream$Z', function (bis, isBigEndian) {
this.bis = bis;
if (bis != null ) {
this.stream = Clazz.new_((I$[1]||$incl$(1)).c$$java_io_InputStream,[bis]);
}this.isBigEndian = isBigEndian;
return this;
});

Clazz.newMeth(C$, 'getInputStream', function () {
return this.bis;
});

Clazz.newMeth(C$, 'setStreamData$java_io_DataInputStream$Z', function (stream, isBigEndian) {
if (stream != null ) this.stream = stream;
this.isBigEndian = isBigEndian;
});

Clazz.newMeth(C$, 'setOutputChannel$javajs_api_GenericOutputChannel', function (out) {
this.out = out;
});

Clazz.newMeth(C$, 'setRandom$Z', function (TF) {
this.isRandom = TF;
});

Clazz.newMeth(C$, 'readByte', function () {
this.nBytes++;
return $b$[0] = p$.ioReadByte.apply(this, []), $b$[0];
});

Clazz.newMeth(C$, 'readUInt8', function () {
this.nBytes++;
var b = this.stream.readUnsignedByte();
if (this.out != null ) this.out.writeByteAsInt$I(b);
return b;
});

Clazz.newMeth(C$, 'ioReadByte', function () {
var b = ($b$[0] = this.stream.readByte(), $b$[0]);
if (this.out != null ) this.out.writeByteAsInt$I(b);
return $b$[0] = b, $b$[0];
});

Clazz.newMeth(C$, 'readBytes$I', function (n) {
var b = Clazz.array(Byte.TYPE, [n]);
this.readByteArray$BA$I$I(b, 0, n);
return b;
});

Clazz.newMeth(C$, 'readByteArray$BA$I$I', function (b, off, len) {
var n = p$.ioRead$BA$I$I.apply(this, [b, off, len]);
this.nBytes = this.nBytes+(n);
return n;
});

Clazz.newMeth(C$, 'ioRead$BA$I$I', function (b, off, len) {
var m = 0;
while (len > 0){
var n = this.stream.read$BA$I$I(b, off, len);
m = m+(n);
if (n > 0 && this.out != null  ) this.out.write$BA$I$I(b, off, n);
if (n >= len) break;
off = off+(n);
len = len-(n);
}
return m;
});

Clazz.newMeth(C$, 'readString$I', function (nChar) {
var temp = Clazz.array(Byte.TYPE, [nChar]);
var n = this.readByteArray$BA$I$I(temp, 0, nChar);
return  String.instantialize(temp, 0, n, "UTF-8");
});

Clazz.newMeth(C$, 'readShort', function () {
this.nBytes = this.nBytes+(2);
var n = (this.isBigEndian ? p$.ioReadShort.apply(this, []) : ($s$[0] = ((p$.ioReadByte.apply(this, []) & 255) | (p$.ioReadByte.apply(this, []) & 255) << 8), $s$[0]));
{
return (n > 0x7FFF ? n - 0x10000 : n);
}
});

Clazz.newMeth(C$, 'ioReadShort', function () {
var b = this.stream.readShort();
if (this.out != null ) this.out.writeShort$H(b);
return b;
});

Clazz.newMeth(C$, 'readIntLE', function () {
this.nBytes = this.nBytes+(4);
return p$.readLEInt.apply(this, []);
});

Clazz.newMeth(C$, 'readInt', function () {
this.nBytes = this.nBytes+(4);
return (this.isBigEndian ? p$.ioReadInt.apply(this, []) : p$.readLEInt.apply(this, []));
});

Clazz.newMeth(C$, 'ioReadInt', function () {
var i = this.stream.readInt();
if (this.out != null ) this.out.writeInt$I(i);
return i;
});

Clazz.newMeth(C$, 'swapBytesI$I', function (n) {
return (((n >> 24) & 255) | ((n >> 16) & 255) << 8 | ((n >> 8) & 255) << 16 | (n & 255) << 24);
});

Clazz.newMeth(C$, 'swapBytesS$H', function (n) {
return ($s$[0] = ((((n >> 8) & 255) | (n & 255) << 8)), $s$[0]);
});

Clazz.newMeth(C$, 'readUnsignedShort', function () {
this.nBytes = this.nBytes+(2);
var a = (p$.ioReadByte.apply(this, []) & 255);
var b = (p$.ioReadByte.apply(this, []) & 255);
return (this.isBigEndian ? (a << 8) + b : (b << 8) + a);
});

Clazz.newMeth(C$, 'readLong', function () {
this.nBytes = this.nBytes+(8);
return (this.isBigEndian ? p$.ioReadLong.apply(this, []) : (((p$.ioReadByte.apply(this, [])) & 255) | ((p$.ioReadByte.apply(this, [])) & 255) << 8 | ((p$.ioReadByte.apply(this, [])) & 255) << 16 | ((p$.ioReadByte.apply(this, [])) & 255) << 24 | ((p$.ioReadByte.apply(this, [])) & 255) << 32 | ((p$.ioReadByte.apply(this, [])) & 255) << 40 | ((p$.ioReadByte.apply(this, [])) & 255) << 48 | ((p$.ioReadByte.apply(this, [])) & 255) << 54));
});

Clazz.newMeth(C$, 'ioReadLong', function () {
var b = this.stream.readLong();
if (this.out != null ) this.out.writeLong$J(b);
return b;
});

Clazz.newMeth(C$, 'readLEInt', function () {
p$.ioRead$BA$I$I.apply(this, [this.t8, 0, 4]);
return P$.BC.bytesToInt$BA$I$Z(this.t8, 0, false);
});

Clazz.newMeth(C$, 'readFloat', function () {
return P$.BC.intToFloat$I(this.readInt());
});

Clazz.newMeth(C$, 'readDouble', function () {
{

}
this.readByteArray$BA$I$I(this.t8, 0, 8);
return P$.BC.bytesToDoubleToFloat$BA$I$Z(this.t8, 0, this.isBigEndian);
});

Clazz.newMeth(C$, 'ioReadDouble', function () {
var d = this.stream.readDouble();
if (this.out != null ) this.out.writeLong$J(Double.doubleToRawLongBits(d));
return d;
});

Clazz.newMeth(C$, 'readLELong', function () {
return (((p$.ioReadByte.apply(this, [])) & 255) | ((p$.ioReadByte.apply(this, [])) & 255) << 8 | ((p$.ioReadByte.apply(this, [])) & 255) << 16 | ((p$.ioReadByte.apply(this, [])) & 255) << 24 | ((p$.ioReadByte.apply(this, [])) & 255) << 32 | ((p$.ioReadByte.apply(this, [])) & 255) << 40 | ((p$.ioReadByte.apply(this, [])) & 255) << 48 | ((p$.ioReadByte.apply(this, [])) & 255) << 56);
});

Clazz.newMeth(C$, 'seek$J', function (offset) {
try {
if (offset == this.nBytes) return;
if (offset < this.nBytes) {
this.stream.reset();
if (this.out != null  && this.nBytes != 0 ) this.out.reset();
this.nBytes = 0;
} else {
offset = offset-(this.nBytes);
}if (this.out == null ) {
this.stream.skipBytes$I((offset|0));
} else {
this.readByteArray$BA$I$I(Clazz.array(Byte.TYPE, [(offset|0)]), 0, (offset|0));
}this.nBytes = this.nBytes+(offset);
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
System.out.println$S(e.toString());
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'getPosition', function () {
return this.nBytes;
});

Clazz.newMeth(C$, 'getAllDataFiles$S$S', function (binaryFileList, firstFile) {
return null;
});

Clazz.newMeth(C$, 'getAllDataMapped$S$S$java_util_Map', function (replace, string, fileData) {
});
var $b$ = new Int8Array(1);
var $s$ = new Int16Array(1);
})();
//Created 2018-02-06 08:59:02
