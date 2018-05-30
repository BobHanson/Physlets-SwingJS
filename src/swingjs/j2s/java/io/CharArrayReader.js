(function(){var P$=java.io,I$=[['org.apache.harmony.luni.util.Msg']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "CharArrayReader", null, 'java.io.Reader');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.buf = null;
this.pos = 0;
this.markedPos = 0;
this.count = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.markedPos = -1;
}, 1);

Clazz.newMeth(C$, 'c$$CA', function (buf) {
C$.superclazz.c$$O.apply(this, [buf]);
C$.$init$.apply(this);
this.buf=buf;
this.count=buf.length;
}, 1);

Clazz.newMeth(C$, 'c$$CA$I$I', function (buf, offset, length) {
C$.superclazz.c$$O.apply(this, [buf]);
C$.$init$.apply(this);
if (0 <= offset && offset <= buf.length  && length >= 0 ) {
this.buf=buf;
this.pos=offset;
this.count=this.pos + length < buf.length ? length : buf.length;
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}}, 1);

Clazz.newMeth(C$, 'close', function () {
{
if (p$.isOpen.apply(this, [])) {
this.buf=null;
}}});

Clazz.newMeth(C$, 'isOpen', function () {
return this.buf != null ;
});

Clazz.newMeth(C$, 'mark$I', function (readLimit) {
{
if (p$.isOpen.apply(this, [])) {
this.markedPos=this.pos;
} else {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0060")]);
}}});

Clazz.newMeth(C$, 'markSupported', function () {
return true;
});

Clazz.newMeth(C$, 'read', function () {
{
if (p$.isOpen.apply(this, [])) {
if (this.pos != this.count) {
return this.buf[this.pos++].$c();
}return -1;
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0060")]);
}});

Clazz.newMeth(C$, 'read$CA$I$I', function (buffer, offset, len) {
if (0 <= offset && offset <= buffer.length  && 0 <= len  && len <= buffer.length - offset ) {
{
if (p$.isOpen.apply(this, [])) {
if (this.pos < this.count) {
var bytesRead = this.pos + len > this.count ? this.count - this.pos : len;
System.arraycopy(this.buf, this.pos, buffer, offset, bytesRead);
this.pos+=bytesRead;
return bytesRead;
}return -1;
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0060")]);
}}throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException'));
});

Clazz.newMeth(C$, 'ready', function () {
{
if (p$.isOpen.apply(this, [])) {
return this.pos != this.count;
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0060")]);
}});

Clazz.newMeth(C$, 'reset', function () {
{
if (p$.isOpen.apply(this, [])) {
this.pos=this.markedPos != -1 ? this.markedPos : 0;
} else {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0060")]);
}}});

Clazz.newMeth(C$, 'skip$J', function (n) {
{
if (p$.isOpen.apply(this, [])) {
if (n <= 0) {
return 0;
}var skipped = 0;
if (n < this.count - this.pos) {
this.pos=this.pos + (n|0);
skipped=n;
} else {
skipped=this.count - this.pos;
this.pos=this.count;
}return skipped;
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0060")]);
}});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:32
