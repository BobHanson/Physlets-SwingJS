(function(){var P$=Clazz.newPackage("java.util.zip"),I$=[['javajs.util.Lst','java.util.Hashtable','java.util.zip.CRC32','java.util.zip.Deflater','swingjs.jzlib.ZStream','java.lang.Boolean']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ZipOutputStream", null, 'java.util.zip.DeflaterOutputStream', 'java.util.zip.ZipConstants');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.current = null;
this.xentries = null;
this.names = null;
this.crc = null;
this.written = 0;
this.locoff = 0;
this.comment = null;
this.method = 0;
this.finished = false;
this.$closed = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.xentries = Clazz.new_((I$[1]||$incl$(1)));
this.names = Clazz.new_((I$[2]||$incl$(2)));
this.crc = Clazz.new_((I$[3]||$incl$(3)));
this.written = 0;
this.locoff = 0;
this.method = 8;
this.$closed = false;
}, 1);

Clazz.newMeth(C$, 'version$java_util_zip_ZipEntry', function (e) {
switch (e.method) {
case 8:
return 20;
case 0:
return 10;
default:
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["unsupported compression method"]);
}
}, 1);

Clazz.newMeth(C$, 'ensureOpen', function () {
if (this.$closed) {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,["Stream closed"]);
}});

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream', function (out) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.setZOS$java_io_OutputStream(out);
}, 1);

Clazz.newMeth(C$, 'setZOS$java_io_OutputStream', function (out) {
this.setDOS$java_io_OutputStream$java_util_zip_Deflater(out, C$.newDeflater());
return this;
});

Clazz.newMeth(C$, 'newDeflater', function () {
return (Clazz.new_((I$[4]||$incl$(4)).c$$I,[2147483647])).init$I$I$Z(-1, 0, true);
}, 1);

Clazz.newMeth(C$, 'setComment$S', function (comment) {
if (comment != null ) {
this.comment = (I$[5]||$incl$(5)).getBytes$S(comment);
if (this.comment.length > 65535) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["ZIP file comment too long."]);
}});

Clazz.newMeth(C$, 'putNextEntry$java_util_zip_ZipEntry', function (e) {
p$.ensureOpen.apply(this, []);
if (this.current != null ) {
this.closeEntry();
}if (e.time == -1) {
e.setTime$J(System.currentTimeMillis());
}if (e.method == -1) {
e.method = this.method;
}e.flag = 0;
switch (e.method) {
case 8:
if (e.size == -1 || e.csize == -1  || e.crc == -1 ) e.flag = 8;
break;
case 0:
if (e.size == -1) {
e.size = e.csize;
} else if (e.csize == -1) {
e.csize = e.size;
} else if (e.size != e.csize) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["STORED entry where compressed != uncompressed size"]);
}if (e.size == -1 || e.crc == -1 ) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["STORED entry missing size, compressed size, or crc-32"]);
}break;
default:
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["unsupported compression method"]);
}
if (this.names.containsKey$O(e.name)) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["duplicate entry: " + e.name]);
}this.names.put$TK$TV(e.name, (I$[6]||$incl$(6)).TRUE);
e.flag = e.flag|(2048);
this.current = e;
this.current.offset = this.written;
this.xentries.addLast$TV(this.current);
p$.writeLOC$java_util_zip_ZipEntry.apply(this, [this.current]);
});

Clazz.newMeth(C$, 'closeEntry', function () {
p$.ensureOpen.apply(this, []);
if (this.current != null ) {
var e = this.current;
switch (e.method) {
case 8:
this.deflater.finish();
C$.superclazz.prototype.finish.apply(this, []);
if ((e.flag & 8) == 0) {
if (e.size != this.deflater.getBytesRead()) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["invalid entry size (expected " + e.size + " but got " + this.deflater.getBytesRead() + " bytes)" ]);
}if (e.csize != this.deflater.getBytesWritten()) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["invalid entry compressed size (expected " + e.csize + " but got " + this.deflater.getBytesWritten() + " bytes)" ]);
}if (e.crc != this.crc.getValue()) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["invalid entry CRC-32 (expected 0x" + Long.toHexString(e.crc) + " but got 0x" + Long.toHexString(this.crc.getValue()) + ")" ]);
}} else {
e.size = this.deflater.getBytesRead();
e.csize = this.deflater.getBytesWritten();
e.crc = this.crc.getValue();
p$.writeEXT$java_util_zip_ZipEntry.apply(this, [e]);
}this.deflater = C$.newDeflater();
this.written = this.written+(e.csize);
break;
case 0:
if (e.size != this.written - this.locoff) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["invalid entry size (expected " + e.size + " but got " + (this.written - this.locoff) + " bytes)" ]);
}if (e.crc != this.crc.getValue()) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["invalid entry crc-32 (expected 0x" + Long.toHexString(e.crc) + " but got 0x" + Long.toHexString(this.crc.getValue()) + ")" ]);
}break;
default:
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["invalid compression method"]);
}
this.crc.reset();
this.current = null;
}});

Clazz.newMeth(C$, 'write$BA$I$I', function (b, off, len) {
p$.ensureOpen.apply(this, []);
if (off < 0 || len < 0  || off > b.length - len ) {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
} else if (len == 0) {
return;
}if (this.current == null ) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["no current ZIP entry"]);
}var entry = this.current;
switch (entry.method) {
case 8:
C$.superclazz.prototype.write$BA$I$I.apply(this, [b, off, len]);
break;
case 0:
this.written = this.written+(len);
if (this.written - this.locoff > entry.size) {
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["attempt to write past end of STORED entry"]);
}this.out.write$BA$I$I(this.buffer, 0, len);
break;
default:
throw Clazz.new_(Clazz.load('java.util.zip.ZipException').c$$S,["invalid compression method"]);
}
this.crc.update$BA$I$I(b, off, len);
});

Clazz.newMeth(C$, 'finish', function () {
p$.ensureOpen.apply(this, []);
if (this.finished) {
return;
}if (this.current != null ) {
this.closeEntry();
}var off = this.written;
for (var xentry, $xentry = this.xentries.iterator(); $xentry.hasNext()&&((xentry=$xentry.next()),1);) p$.writeCEN$java_util_zip_ZipEntry.apply(this, [xentry]);

p$.writeEND$J$J.apply(this, [off, this.written - off]);
this.finished = true;
});

Clazz.newMeth(C$, 'close', function () {
if (!this.$closed) {
C$.superclazz.prototype.close.apply(this, []);
this.$closed = true;
}});

Clazz.newMeth(C$, 'writeLOC$java_util_zip_ZipEntry', function (entry) {
var e = entry;
var flag = e.flag;
var elen = (e.extra != null ) ? e.extra.length : 0;
var hasZip64 = false;
p$.writeInt$J.apply(this, [67324752]);
if ((flag & 8) == 8) {
p$.writeShort$I.apply(this, [C$.version$java_util_zip_ZipEntry(e)]);
p$.writeShort$I.apply(this, [flag]);
p$.writeShort$I.apply(this, [e.method]);
p$.writeInt$J.apply(this, [e.time]);
p$.writeInt$J.apply(this, [0]);
p$.writeInt$J.apply(this, [0]);
p$.writeInt$J.apply(this, [0]);
} else {
if (e.csize >= 4294967295 || e.size >= 4294967295 ) {
hasZip64 = true;
p$.writeShort$I.apply(this, [45]);
} else {
p$.writeShort$I.apply(this, [C$.version$java_util_zip_ZipEntry(e)]);
}p$.writeShort$I.apply(this, [flag]);
p$.writeShort$I.apply(this, [e.method]);
p$.writeInt$J.apply(this, [e.time]);
p$.writeInt$J.apply(this, [e.crc]);
if (hasZip64) {
p$.writeInt$J.apply(this, [4294967295]);
p$.writeInt$J.apply(this, [4294967295]);
elen = elen+(20);
} else {
p$.writeInt$J.apply(this, [e.csize]);
p$.writeInt$J.apply(this, [e.size]);
}}var nameBytes = (I$[5]||$incl$(5)).getBytes$S(e.name);
p$.writeShort$I.apply(this, [nameBytes.length]);
p$.writeShort$I.apply(this, [elen]);
p$.writeBytes$BA$I$I.apply(this, [nameBytes, 0, nameBytes.length]);
if (hasZip64) {
p$.writeShort$I.apply(this, [1]);
p$.writeShort$I.apply(this, [16]);
p$.writeLong$J.apply(this, [e.size]);
p$.writeLong$J.apply(this, [e.csize]);
}if (e.extra != null ) {
p$.writeBytes$BA$I$I.apply(this, [e.extra, 0, e.extra.length]);
}this.locoff = this.written;
});

Clazz.newMeth(C$, 'writeEXT$java_util_zip_ZipEntry', function (e) {
p$.writeInt$J.apply(this, [134695760]);
p$.writeInt$J.apply(this, [e.crc]);
if (e.csize >= 4294967295 || e.size >= 4294967295 ) {
p$.writeLong$J.apply(this, [e.csize]);
p$.writeLong$J.apply(this, [e.size]);
} else {
p$.writeInt$J.apply(this, [e.csize]);
p$.writeInt$J.apply(this, [e.size]);
}});

Clazz.newMeth(C$, 'writeCEN$java_util_zip_ZipEntry', function (entry) {
var e = entry;
var flag = e.flag;
var version = C$.version$java_util_zip_ZipEntry(e);
var csize = e.csize;
var size = e.size;
var offset = entry.offset;
var e64len = 0;
var hasZip64 = false;
if (e.csize >= 4294967295) {
csize = 4294967295;
e64len = e64len+(8);
hasZip64 = true;
}if (e.size >= 4294967295) {
size = 4294967295;
e64len = e64len+(8);
hasZip64 = true;
}if (entry.offset >= 4294967295) {
offset = 4294967295;
e64len = e64len+(8);
hasZip64 = true;
}p$.writeInt$J.apply(this, [33639248]);
if (hasZip64) {
p$.writeShort$I.apply(this, [45]);
p$.writeShort$I.apply(this, [45]);
} else {
p$.writeShort$I.apply(this, [version]);
p$.writeShort$I.apply(this, [version]);
}p$.writeShort$I.apply(this, [flag]);
p$.writeShort$I.apply(this, [e.method]);
p$.writeInt$J.apply(this, [e.time]);
p$.writeInt$J.apply(this, [e.crc]);
p$.writeInt$J.apply(this, [csize]);
p$.writeInt$J.apply(this, [size]);
var nameBytes = (I$[5]||$incl$(5)).getBytes$S(e.name);
p$.writeShort$I.apply(this, [nameBytes.length]);
if (hasZip64) {
p$.writeShort$I.apply(this, [e64len + 4 + (e.extra != null  ? e.extra.length : 0) ]);
} else {
p$.writeShort$I.apply(this, [e.extra != null  ? e.extra.length : 0]);
}var commentBytes;
if (e.comment != null ) {
commentBytes = (I$[5]||$incl$(5)).getBytes$S(e.comment);
p$.writeShort$I.apply(this, [Math.min(commentBytes.length, 65535)]);
} else {
commentBytes = null;
p$.writeShort$I.apply(this, [0]);
}p$.writeShort$I.apply(this, [0]);
p$.writeShort$I.apply(this, [0]);
p$.writeInt$J.apply(this, [0]);
p$.writeInt$J.apply(this, [offset]);
p$.writeBytes$BA$I$I.apply(this, [nameBytes, 0, nameBytes.length]);
if (hasZip64) {
p$.writeShort$I.apply(this, [1]);
p$.writeShort$I.apply(this, [e64len]);
if (size == 4294967295) p$.writeLong$J.apply(this, [e.size]);
if (csize == 4294967295) p$.writeLong$J.apply(this, [e.csize]);
if (offset == 4294967295) p$.writeLong$J.apply(this, [entry.offset]);
}if (e.extra != null ) {
p$.writeBytes$BA$I$I.apply(this, [e.extra, 0, e.extra.length]);
}if (commentBytes != null ) {
p$.writeBytes$BA$I$I.apply(this, [commentBytes, 0, Math.min(commentBytes.length, 65535)]);
}});

Clazz.newMeth(C$, 'writeEND$J$J', function (off, len) {
var hasZip64 = false;
var xlen = len;
var xoff = off;
if (xlen >= 4294967295) {
xlen = 4294967295;
hasZip64 = true;
}if (xoff >= 4294967295) {
xoff = 4294967295;
hasZip64 = true;
}var count = this.xentries.size();
if (count >= 65535) {
count = 65535;
hasZip64 = true;
}if (hasZip64) {
var off64 = this.written;
p$.writeInt$J.apply(this, [101075792]);
p$.writeLong$J.apply(this, [44]);
p$.writeShort$I.apply(this, [45]);
p$.writeShort$I.apply(this, [45]);
p$.writeInt$J.apply(this, [0]);
p$.writeInt$J.apply(this, [0]);
p$.writeLong$J.apply(this, [this.xentries.size()]);
p$.writeLong$J.apply(this, [this.xentries.size()]);
p$.writeLong$J.apply(this, [len]);
p$.writeLong$J.apply(this, [off]);
p$.writeInt$J.apply(this, [117853008]);
p$.writeInt$J.apply(this, [0]);
p$.writeLong$J.apply(this, [off64]);
p$.writeInt$J.apply(this, [1]);
}p$.writeInt$J.apply(this, [101010256]);
p$.writeShort$I.apply(this, [0]);
p$.writeShort$I.apply(this, [0]);
p$.writeShort$I.apply(this, [count]);
p$.writeShort$I.apply(this, [count]);
p$.writeInt$J.apply(this, [xlen]);
p$.writeInt$J.apply(this, [xoff]);
if (this.comment != null ) {
p$.writeShort$I.apply(this, [this.comment.length]);
p$.writeBytes$BA$I$I.apply(this, [this.comment, 0, this.comment.length]);
} else {
p$.writeShort$I.apply(this, [0]);
}});

Clazz.newMeth(C$, 'writeShort$I', function (v) {
var out = this.out;
out.write$I((v >>> 0) & 255);
out.write$I((v >>> 8) & 255);
this.written = this.written+(2);
});

Clazz.newMeth(C$, 'writeInt$J', function (v) {
var out = this.out;
out.write$I((((v >>> 0) & 255)|0));
out.write$I((((v >>> 8) & 255)|0));
out.write$I((((v >>> 16) & 255)|0));
out.write$I((((v >>> 24) & 255)|0));
this.written = this.written+(4);
});

Clazz.newMeth(C$, 'writeLong$J', function (v) {
var out = this.out;
out.write$I((((v >>> 0) & 255)|0));
out.write$I((((v >>> 8) & 255)|0));
out.write$I((((v >>> 16) & 255)|0));
out.write$I((((v >>> 24) & 255)|0));
out.write$I((((v >>> 32) & 255)|0));
out.write$I((((v >>> 40) & 255)|0));
out.write$I((((v >>> 48) & 255)|0));
out.write$I((((v >>> 56) & 255)|0));
this.written = this.written+(8);
});

Clazz.newMeth(C$, 'writeBytes$BA$I$I', function (b, off, len) {
this.out.write$BA$I$I(b, off, len);
this.written = this.written+(len);
});
})();
//Created 2018-02-06 08:58:58