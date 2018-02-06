(function(){var P$=java.io,I$=[['org.apache.harmony.luni.util.Msg','java.lang.StringBuilder']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "BufferedReader", null, 'java.io.Reader');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$in = null;
this.buf = null;
this.marklimit = 0;
this.count = 0;
this.markpos = 0;
this.pos = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.marklimit = -1;
this.markpos = -1;
}, 1);

Clazz.newMeth(C$, 'c$$java_io_Reader', function ($in) {
C$.superclazz.c$$O.apply(this, [$in]);
C$.$init$.apply(this);
this.$in = $in;
this.buf = Clazz.array(Character.TYPE, [8192]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_Reader$I', function ($in, size) {
C$.superclazz.c$$O.apply(this, [$in]);
C$.$init$.apply(this);
if (size > 0) {
this.$in = $in;
this.buf = Clazz.array(Character.TYPE, [size]);
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[(I$[1]||$incl$(1)).getString$S("K0058")]);
}}, 1);

Clazz.newMeth(C$, 'close', function () {
{
if (p$.isOpen.apply(this, [])) {
this.$in.close();
this.buf = null;
}}});

Clazz.newMeth(C$, 'fillbuf', function () {
if (this.markpos == -1 || (this.pos - this.markpos >= this.marklimit) ) {
var result = this.$in.read$CA$I$I(this.buf, 0, this.buf.length);
if (result > 0) {
this.markpos = -1;
this.pos = 0;
this.count = result == -1 ? 0 : result;
}return result;
}if (this.markpos == 0 && this.marklimit > this.buf.length ) {
var newLength = this.buf.length * 2;
if (newLength > this.marklimit) {
newLength = this.marklimit;
}var newbuf = Clazz.array(Character.TYPE, [newLength]);
System.arraycopy(this.buf, 0, newbuf, 0, this.buf.length);
this.buf = newbuf;
} else if (this.markpos > 0) {
System.arraycopy(this.buf, this.markpos, this.buf, 0, this.buf.length - this.markpos);
}this.pos = this.pos-(this.markpos);
this.count = this.markpos = 0;
var charsread = this.$in.read$CA$I$I(this.buf, this.pos, this.buf.length - this.pos);
this.count = charsread == -1 ? this.pos : this.pos + charsread;
return charsread;
});

Clazz.newMeth(C$, 'isOpen', function () {
return this.buf != null ;
});

Clazz.newMeth(C$, 'mark$I', function (readlimit) {
if (readlimit >= 0) {
{
if (p$.isOpen.apply(this, [])) {
this.marklimit = readlimit;
this.markpos = this.pos;
} else {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K005b")]);
}}} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}});

Clazz.newMeth(C$, 'markSupported', function () {
return true;
});

Clazz.newMeth(C$, 'read', function () {
{
if (p$.isOpen.apply(this, [])) {
if (this.pos < this.count || p$.fillbuf.apply(this, []) != -1 ) {
return this.buf[this.pos++].$c();
}return -1;
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K005b")]);
}});

Clazz.newMeth(C$, 'read$CA$I$I', function (buffer, offset, length) {
{
if (!p$.isOpen.apply(this, [])) {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K005b")]);
}if (offset < 0 || offset > buffer.length - length  || length < 0 ) {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}if (length == 0) {
return 0;
}var required;
if (this.pos < this.count) {
var copylength = this.count - this.pos >= length ? length : this.count - this.pos;
System.arraycopy(this.buf, this.pos, buffer, offset, copylength);
this.pos = this.pos+(copylength);
if (copylength == length || !this.$in.ready() ) {
return copylength;
}offset = offset+(copylength);
required = length - copylength;
} else {
required = length;
}while (true){
var read;
if (this.markpos == -1 && required >= this.buf.length ) {
read = this.$in.read$CA$I$I(buffer, offset, required);
if (read == -1) {
return required == length ? -1 : length - required;
}} else {
if (p$.fillbuf.apply(this, []) == -1) {
return required == length ? -1 : length - required;
}read = this.count - this.pos >= required ? required : this.count - this.pos;
System.arraycopy(this.buf, this.pos, buffer, offset, read);
this.pos = this.pos+(read);
}required = required-(read);
if (required == 0) {
return length;
}if (!this.$in.ready()) {
return length - required;
}offset = offset+(read);
}
}});

Clazz.newMeth(C$, 'readLine', function () {
{
if (p$.isOpen.apply(this, [])) {
if ((this.pos >= this.count) && (p$.fillbuf.apply(this, []) == -1) ) {
return null;
}for (var charPos = this.pos; charPos < this.count; charPos++) {
var ch = this.buf[charPos];
if (ch > "\u000d") continue;
if (ch == "\u000a") {
var res =  String.instantialize(this.buf, this.pos, charPos - this.pos);
this.pos = charPos + 1;
return res;
} else if (ch == "\u000d") {
var res =  String.instantialize(this.buf, this.pos, charPos - this.pos);
this.pos = charPos + 1;
if (((this.pos < this.count) || (p$.fillbuf.apply(this, []) != -1) ) && (this.buf[this.pos] == "\u000a") ) {
this.pos++;
}return res;
}}
var eol = "\u0000";
var result = Clazz.new_((I$[2]||$incl$(2)).c$$I,[80]);
result.append$CA$I$I(this.buf, this.pos, this.count - this.pos);
this.pos = this.count;
while (true){
if (this.pos >= this.count) {
if (eol == "\u000a") {
return result.toString();
}if (p$.fillbuf.apply(this, []) == -1) {
return result.length$() > 0 || eol != "\u0000"  ? result.toString() : null;
}}for (var charPos = this.pos; charPos < this.count; charPos++) {
if (eol == "\u0000") {
if ((this.buf[charPos] == "\u000a" || this.buf[charPos] == "\u000d" )) {
eol = this.buf[charPos];
}} else if (eol == "\u000d" && (this.buf[charPos] == "\u000a") ) {
if (charPos > this.pos) {
result.append$CA$I$I(this.buf, this.pos, charPos - this.pos - 1 );
}this.pos = charPos + 1;
return result.toString();
} else if (eol != "\u0000") {
if (charPos > this.pos) {
result.append$CA$I$I(this.buf, this.pos, charPos - this.pos - 1 );
}this.pos = charPos;
return result.toString();
}}
if (eol == "\u0000") {
result.append$CA$I$I(this.buf, this.pos, this.count - this.pos);
} else {
result.append$CA$I$I(this.buf, this.pos, this.count - this.pos - 1 );
}this.pos = this.count;
}
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K005b")]);
}});

Clazz.newMeth(C$, 'ready', function () {
{
if (p$.isOpen.apply(this, [])) {
return ((this.count - this.pos) > 0) || this.$in.ready() ;
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K005b")]);
}});

Clazz.newMeth(C$, 'reset', function () {
{
if (p$.isOpen.apply(this, [])) {
if (this.markpos != -1) {
this.pos = this.markpos;
} else {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K005c")]);
}} else {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K005b")]);
}}});

Clazz.newMeth(C$, 'skip$J', function (amount) {
if (amount >= 0) {
{
if (p$.isOpen.apply(this, [])) {
if (amount < 1) {
return 0;
}if (this.count - this.pos >= amount) {
this.pos = this.pos+(amount);
return amount;
}var read = this.count - this.pos;
this.pos = this.count;
while (read < amount){
if (p$.fillbuf.apply(this, []) == -1) {
return read;
}if (this.count - this.pos >= amount - read) {
this.pos = this.pos+(amount - read);
return amount;
}read = read+((this.count - this.pos));
this.pos = this.count;
}
return amount;
}throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,[(I$[1]||$incl$(1)).getString$S("K005b")]);
}}throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:28
