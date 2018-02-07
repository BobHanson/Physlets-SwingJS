(function(){var P$=java.io,I$=[['org.apache.harmony.luni.util.Msg']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "StringReader", null, 'java.io.Reader');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.str = null;
this.markpos = 0;
this.pos = 0;
this.count = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.markpos = -1;
}, 1);

Clazz.newMeth(C$, 'c$$S', function (str) {
C$.superclazz.c$$O.apply(this, [str]);
C$.$init$.apply(this);
this.str = str;
this.count = str.length$();
}, 1);

Clazz.newMeth(C$, 'close', function () {
{
if (p$.isOpen.apply(this, [])) {
this.str = null;
}}});

Clazz.newMeth(C$, 'isOpen', function () {
return this.str != null ;
});

Clazz.newMeth(C$, 'mark$I', function (readLimit) {
if (readLimit >= 0) {
{
if (p$.isOpen.apply(this, [])) {
this.markpos = this.pos;
} else {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0083")]);
}}} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}});

Clazz.newMeth(C$, 'markSupported', function () {
return true;
});

Clazz.newMeth(C$, 'read', function () {
{
if (p$.isOpen.apply(this, [])) {
if (this.pos != this.count) {
return this.str.charAt(this.pos++).$c();
}return -1;
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0083")]);
}});

Clazz.newMeth(C$, 'read$CA$I$I', function (buf, offset, len) {
if (0 <= offset && offset <= buf.length  && 0 <= len  && len <= buf.length - offset ) {
{
if (p$.isOpen.apply(this, [])) {
if (this.pos == this.count) {
return -1;
}var end = this.pos + len > this.count ? this.count : this.pos + len;
this.str.getChars$I$I$CA$I(this.pos, end, buf, offset);
var read = end - this.pos;
this.pos = end;
return read;
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0083")]);
}}throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException'));
});

Clazz.newMeth(C$, 'ready', function () {
{
if (p$.isOpen.apply(this, [])) {
return true;
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0083")]);
}});

Clazz.newMeth(C$, 'reset', function () {
{
if (p$.isOpen.apply(this, [])) {
this.pos = this.markpos != -1 ? this.markpos : 0;
} else {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0083")]);
}}});

Clazz.newMeth(C$, 'skip$J', function (ns) {
{
if (p$.isOpen.apply(this, [])) {
if (ns <= 0) {
return 0;
}var skipped = 0;
if (ns < this.count - this.pos) {
this.pos = this.pos + (ns|0);
skipped = ns;
} else {
skipped = this.count - this.pos;
this.pos = this.count;
}return skipped;
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0083")]);
}});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:33