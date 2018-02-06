(function(){var P$=Clazz.newPackage("java.util.zip"),I$=[['java.util.zip.CRC32','java.util.zip.Inflater','java.util.zip.CheckedInputStream']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "GZIPInputStream", null, 'java.util.zip.InflaterInputStream');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.crc = null;
this.eos = false;
this.$closed = false;
this.tmpbuf = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.crc = Clazz.new_((I$[1]||$incl$(1)));
this.$closed = false;
this.tmpbuf = Clazz.array(Byte.TYPE, [128]);
}, 1);

Clazz.newMeth(C$, 'ensureOpen', function () {
if (this.$closed) {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,["Stream closed"]);
}});

Clazz.newMeth(C$, 'c$$java_io_InputStream$I', function ($in, size) {
C$.superclazz.c$$java_io_InputStream$java_util_zip_Inflater$I.apply(this, [$in, Clazz.new_((I$[2]||$incl$(2))).init$I$Z(0, true), size]);
C$.$init$.apply(this);
p$.readHeader$java_io_InputStream.apply(this, [$in]);
}, 1);

Clazz.newMeth(C$, 'read$BA$I$I', function (buf, off, len) {
p$.ensureOpen.apply(this, []);
if (this.eos) {
return -1;
}var n = this.readInf$BA$I$I(buf, off, len);
if (n == -1) {
if (p$.readTrailer.apply(this, [])) this.eos = true;
 else return this.read$BA$I$I(buf, off, len);
} else {
this.crc.update$BA$I$I(buf, off, n);
}return n;
});

Clazz.newMeth(C$, 'close', function () {
if (!this.$closed) {
C$.superclazz.prototype.close.apply(this, []);
this.eos = true;
this.$closed = true;
}});

Clazz.newMeth(C$, 'readHeader$java_io_InputStream', function (this_in) {
var $in = Clazz.new_((I$[3]||$incl$(3)).c$$java_io_InputStream$swingjs_jzlib_Checksum,[this_in, this.crc]);
this.crc.reset();
if (p$.readUShort$java_io_InputStream.apply(this, [$in]) != 35615) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["Not in GZIP format"]);
}if (p$.readUByte$java_io_InputStream.apply(this, [$in]) != 8) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["Unsupported compression method"]);
}var flg = p$.readUByte$java_io_InputStream.apply(this, [$in]);
p$.skipBytes$java_io_InputStream$I.apply(this, [$in, 6]);
var n = 10;
if ((flg & 4) == 4) {
var m = p$.readUShort$java_io_InputStream.apply(this, [$in]);
p$.skipBytes$java_io_InputStream$I.apply(this, [$in, m]);
n = n+(m + 2);
}if ((flg & 8) == 8) {
do {
n++;
} while (p$.readUByte$java_io_InputStream.apply(this, [$in]) != 0);
}if ((flg & 16) == 16) {
do {
n++;
} while (p$.readUByte$java_io_InputStream.apply(this, [$in]) != 0);
}if ((flg & 2) == 2) {
var v = (this.crc.getValue()|0) & 65535;
if (p$.readUShort$java_io_InputStream.apply(this, [$in]) != v) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["Corrupt GZIP header"]);
}n = n+(2);
}this.crc.reset();
return n;
});

Clazz.newMeth(C$, 'readTrailer', function () {
return true;
});

Clazz.newMeth(C$, 'readUShort$java_io_InputStream', function ($in) {
var b = p$.readUByte$java_io_InputStream.apply(this, [$in]);
return (p$.readUByte$java_io_InputStream.apply(this, [$in]) << 8) | b;
});

Clazz.newMeth(C$, 'readUByte$java_io_InputStream', function ($in) {
var b = $in.read();
if (b == -1) {
throw Clazz.new_(Clazz.load('java.io.EOFException'));
}if (b < -1 || b > 255 ) {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[this.$in.getClass().getName() + ".read() returned value out of range -1..255: " + b ]);
}return b;
});

Clazz.newMeth(C$, 'skipBytes$java_io_InputStream$I', function ($in, n) {
while (n > 0){
var len = $in.read$BA$I$I(this.tmpbuf, 0, n < this.tmpbuf.length ? n : this.tmpbuf.length);
if (len == -1) {
throw Clazz.new_(Clazz.load('java.io.EOFException'));
}n = n-(len);
}
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:57
