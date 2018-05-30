(function(){var P$=java.util,I$=[[['java.util.Properties','.LineReader'],'java.util.Hashtable']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Properties", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.util.Hashtable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.defaults = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$java_util_Properties.apply(this, [null]);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Properties', function (defaults) {
Clazz.super_(C$, this,1);
this.defaults=defaults;
}, 1);

Clazz.newMeth(C$, 'setProperty$S$S', function (key, value) {
return this.put$TK$TV(key, value);
});

Clazz.newMeth(C$, 'load$java_io_Reader', function (reader) {
p$.load0$java_util_Properties_LineReader.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$java_io_Reader, [this, null, reader])]);
});

Clazz.newMeth(C$, 'load$java_io_InputStream', function (inStream) {
p$.load0$java_util_Properties_LineReader.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$java_io_InputStream, [this, null, inStream])]);
});

Clazz.newMeth(C$, 'load0$java_util_Properties_LineReader', function (lr) {
var convtBuf = Clazz.array(Character.TYPE, [1024]);
var limit;
var keyLen;
var valueStart;
var c;
var hasSep;
var precedingBackslash;
while ((limit=lr.readLine()) >= 0){
c=String.fromCharCode(0);
keyLen=0;
valueStart=limit;
hasSep=false;
precedingBackslash=false;
while (keyLen < limit){
c=lr.lineBuf[keyLen];
if ((c == "=" || c == ":" ) && !precedingBackslash ) {
valueStart=keyLen + 1;
hasSep=true;
break;
} else if ((c == " " || c == "\u0009"  || c == "\u000c" ) && !precedingBackslash ) {
valueStart=keyLen + 1;
break;
}if (c == "\\") {
precedingBackslash=!precedingBackslash;
} else {
precedingBackslash=false;
}keyLen++;
}
while (valueStart < limit){
c=lr.lineBuf[valueStart];
if (c != " " && c != "\u0009"  && c != "\u000c" ) {
if (!hasSep && (c == "=" || c == ":" ) ) {
hasSep=true;
} else {
break;
}}valueStart++;
}
var key = p$.loadConvert$CA$I$I$CA.apply(this, [lr.lineBuf, 0, keyLen, convtBuf]);
var value = p$.loadConvert$CA$I$I$CA.apply(this, [lr.lineBuf, valueStart, limit - valueStart, convtBuf]);
this.put$TK$TV(key, value);
}
});

Clazz.newMeth(C$, 'loadConvert$CA$I$I$CA', function ($in, off, len, convtBuf) {
if (convtBuf.length < len) {
var newLen = len * 2;
if (newLen < 0) {
newLen=2147483647;
}convtBuf=Clazz.array(Character.TYPE, [newLen]);
}var aChar;
var out = convtBuf;
var outLen = 0;
var end = off + len;
while (off < end){
aChar=$in[off++];
if (aChar == "\\") {
aChar=$in[off++];
if (aChar == "u") {
var value = 0;
for (var i = 0; i < 4; i++) {
aChar=$in[off++];
switch (aChar.$c()) {
case 48:
case 49:
case 50:
case 51:
case 52:
case 53:
case 54:
case 55:
case 56:
case 57:
value=(value << 4) + aChar.$c() - 48;
break;
case 97:
case 98:
case 99:
case 100:
case 101:
case 102:
value=(value << 4) + 10 + aChar.$c()  - 97;
break;
case 65:
case 66:
case 67:
case 68:
case 69:
case 70:
value=(value << 4) + 10 + aChar.$c()  - 65;
break;
default:
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Malformed \\uxxxx encoding."]);
}
}
out[outLen++]=String.fromCharCode(value);
} else {
if (aChar == "t") aChar="\u0009";
 else if (aChar == "r") aChar="\u000d";
 else if (aChar == "n") aChar="\u000a";
 else if (aChar == "f") aChar="\u000c";
out[outLen++]=aChar;
}} else {
out[outLen++]=aChar;
}}
return  String.instantialize(out, 0, outLen);
});

Clazz.newMeth(C$, 'getProperty$S', function (key) {
var oval = C$.superclazz.prototype.get$O.apply(this, [key]);
var sval = (Clazz.instanceOf(oval, "java.lang.String")) ? oval : null;
return ((sval == null ) && (this.defaults != null ) ) ? this.defaults.getProperty$S(key) : sval;
});

Clazz.newMeth(C$, 'getProperty$S$S', function (key, defaultValue) {
var val = this.getProperty$S(key);
return (val == null ) ? defaultValue : val;
});

Clazz.newMeth(C$, 'propertyNames', function () {
var h = Clazz.new_((I$[2]||$incl$(2)));
p$.enumerate$java_util_Hashtable.apply(this, [h]);
return h.keys();
});

Clazz.newMeth(C$, 'stringPropertyNames', function () {
var h = Clazz.new_((I$[2]||$incl$(2)));
p$.enumerateStringProperties$java_util_Hashtable.apply(this, [h]);
return h.keySet();
});

Clazz.newMeth(C$, 'enumerate$java_util_Hashtable', function (h) {
if (this.defaults != null ) {
this.defaults.enumerate$java_util_Hashtable(h);
}for (var e = this.keys(); e.hasMoreElements(); ) {
var key = e.nextElement();
h.put$TK$TV(key, this.get$O(key));
}
});

Clazz.newMeth(C$, 'enumerateStringProperties$java_util_Hashtable', function (h) {
if (this.defaults != null ) {
this.defaults.enumerateStringProperties$java_util_Hashtable(h);
}for (var e = this.keys(); e.hasMoreElements(); ) {
var k = e.nextElement();
var v = this.get$O(k);
if (Clazz.instanceOf(k, "java.lang.String") && Clazz.instanceOf(v, "java.lang.String") ) {
h.put$TK$TV(k, v);
}}
});
;
(function(){var C$=Clazz.newClass(P$.Properties, "LineReader", function(){
Clazz.newInstance(this, arguments[0],true,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.inByteBuf = null;
this.inCharBuf = null;
this.lineBuf = null;
this.inLimit = 0;
this.inOff = 0;
this.inStream = null;
this.reader = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.lineBuf = Clazz.array(Character.TYPE, [1024]);
this.inLimit = 0;
this.inOff = 0;
}, 1);

Clazz.newMeth(C$, 'c$$java_io_InputStream', function (inStream) {
C$.$init$.apply(this);
this.inStream=inStream;
this.inByteBuf=Clazz.array(Byte.TYPE, [8192]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_Reader', function (reader) {
C$.$init$.apply(this);
this.reader=reader;
this.inCharBuf=Clazz.array(Character.TYPE, [8192]);
}, 1);

Clazz.newMeth(C$, 'readLine', function () {
var len = 0;
var c = String.fromCharCode(0);
var skipWhiteSpace = true;
var isCommentLine = false;
var isNewLine = true;
var appendedLineBegin = false;
var precedingBackslash = false;
var skipLF = false;
while (true){
if (this.inOff >= this.inLimit) {
this.inLimit=(this.inStream == null ) ? this.reader.read$CA(this.inCharBuf) : this.inStream.read$BA(this.inByteBuf);
this.inOff=0;
if (this.inLimit <= 0) {
if (len == 0 || isCommentLine ) {
return -1;
}return len;
}}if (this.inStream != null ) {
c=String.fromCharCode((255 & this.inByteBuf[this.inOff++]));
} else {
c=this.inCharBuf[this.inOff++];
}if (skipLF) {
skipLF=false;
if (c == "\u000a") {
continue;
}}if (skipWhiteSpace) {
if (c == " " || c == "\u0009"  || c == "\u000c" ) {
continue;
}if (!appendedLineBegin && (c == "\u000d" || c == "\u000a" ) ) {
continue;
}skipWhiteSpace=false;
appendedLineBegin=false;
}if (isNewLine) {
isNewLine=false;
if (c == "#" || c == "!" ) {
isCommentLine=true;
continue;
}}if (c != "\u000a" && c != "\u000d" ) {
this.lineBuf[len++]=c;
if (len == this.lineBuf.length) {
var newLength = this.lineBuf.length * 2;
var buf = Clazz.array(Character.TYPE, [newLength]);
System.arraycopy(this.lineBuf, 0, buf, 0, this.lineBuf.length);
this.lineBuf=buf;
}if (c == "\\") {
precedingBackslash=!precedingBackslash;
} else {
precedingBackslash=false;
}} else {
if (isCommentLine || len == 0 ) {
isCommentLine=false;
isNewLine=true;
skipWhiteSpace=true;
len=0;
continue;
}if (this.inOff >= this.inLimit) {
this.inLimit=(this.inStream == null ) ? this.reader.read$CA(this.inCharBuf) : this.inStream.read$BA(this.inByteBuf);
this.inOff=0;
if (this.inLimit <= 0) {
return len;
}}if (precedingBackslash) {
len-=1;
skipWhiteSpace=true;
appendedLineBegin=true;
precedingBackslash=false;
if (c == "\u000d") {
skipLF=true;
}} else {
return len;
}}}
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-24 08:45:48
