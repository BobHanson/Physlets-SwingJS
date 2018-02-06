(function(){var P$=java.lang,I$=[['java.io.ObjectStreamField',['java.lang.String','.CaseInsensitiveComparator'],'java.util.Arrays','StringCoding','java.util.regex.Pattern','java.util.regex.Matcher','ConditionalSpecialCasing','java.util.Locale','java.util.Formatter']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(java.lang, "String", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, ['java.io.Serializable', 'Comparable', 'CharSequence']);
C$.serialPersistentFields = null;
var p$=C$.prototype;
C$.CASE_INSENSITIVE_ORDER = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.serialPersistentFields = Clazz.array((I$[1]||$incl$(1)), [0]);
C$.CASE_INSENSITIVE_ORDER = Clazz.new_((I$[2]||$incl$(2)));
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.value = null;
this.offset = 0;
this.count = 0;
this.hash = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.offset = 0;
this.count = 0;
this.value = Clazz.array(Character.TYPE, [0]);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (original) {
C$.$init$.apply(this);
var size = original.count;
var originalValue = original.value;
var v;
if (originalValue.length > size) {
var off = original.offset;
v = (I$[3]||$incl$(3)).copyOfRange$CA$I$I(originalValue, off, off + size);
} else {
v = originalValue;
}this.offset = 0;
this.count = size;
this.value = v;
}, 1);

Clazz.newMeth(C$, 'c$$CA', function (value) {
C$.$init$.apply(this);
var size = value.length;
this.offset = 0;
this.count = size;
this.value = (I$[3]||$incl$(3)).copyOf$CA$I(value, size);
}, 1);

Clazz.newMeth(C$, 'c$$CA$I$I', function (value, offset, count) {
C$.$init$.apply(this);
if (offset < 0) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[offset]);
}if (count < 0) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[count]);
}if (offset > value.length - count) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[offset + count]);
}this.offset = 0;
this.count = count;
this.value = (I$[3]||$incl$(3)).copyOfRange$CA$I$I(value, offset, offset + count);
}, 1);

Clazz.newMeth(C$, 'c$$IA$I$I', function (codePoints, offset, count) {
C$.$init$.apply(this);
if (offset < 0) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[offset]);
}if (count < 0) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[count]);
}if (offset > codePoints.length - count) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[offset + count]);
}var n = 0;
for (var i = offset; i < offset + count; i++) {
var c = codePoints[i];
if (c >= 0 && c < 65536 ) n = n+(1);
 else if (Character.isSupplementaryCodePoint(c)) n = n+(2);
 else throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[Integer.toString(c)]);
}
var v = Clazz.array(Character.TYPE, [n]);
for (var i = offset, j = 0; i < offset + count; i++) {
var c = codePoints[i];
if (c < 65536) {
v[j++] = String.fromCharCode(c);
} else {
Character.toSurrogates(c, v, j);
j = j+(2);
}}
this.value = v;
this.count = v.length;
this.offset = 0;
}, 1);

Clazz.newMeth(C$, 'c$$BA$I$I$I', function (ascii, hibyte, offset, count) {
C$.$init$.apply(this);
String.checkBounds$BA$I$I(ascii, offset, count);
var value = Clazz.array(Character.TYPE, [count]);
if (hibyte == 0) {
for (var i = count; i-- > 0; ) {
value[i] = String.fromCharCode((ascii[i + offset] & 255));
}
} else {
hibyte = hibyte<<(8);
for (var i = count; i-- > 0; ) {
value[i] = String.fromCharCode((hibyte | (ascii[i + offset] & 255)));
}
}this.offset = 0;
this.count = count;
this.value = value;
}, 1);

Clazz.newMeth(C$, 'c$$BA$I', function (ascii, hibyte) {
C$.c$$BA$I$I$I.apply(this, [ascii, hibyte, 0, ascii.length]);
}, 1);

Clazz.newMeth(C$, 'checkBounds$BA$I$I', function (bytes, offset, length) {
if (length < 0) throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[length]);
if (offset < 0) throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[offset]);
if (offset > bytes.length - length) throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[offset + length]);
}, 1);

Clazz.newMeth(C$, 'c$$BA$I$I$S', function (bytes, offset, length, charsetName) {
C$.$init$.apply(this);
if (charsetName == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["charsetName"]);
String.checkBounds$BA$I$I(bytes, offset, length);
var v = (I$[4]||$incl$(4)).decode$S$BA$I$I(charsetName, bytes, offset, length);
this.offset = 0;
this.count = v.length;
this.value = v;
}, 1);

Clazz.newMeth(C$, 'c$$BA$I$I$java_nio_charset_Charset', function (bytes, offset, length, charset) {
C$.$init$.apply(this);
if (charset == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["charset"]);
String.checkBounds$BA$I$I(bytes, offset, length);
var v = (I$[4]||$incl$(4)).decode$java_nio_charset_Charset$BA$I$I(charset, bytes, offset, length);
this.offset = 0;
this.count = v.length;
this.value = v;
}, 1);

Clazz.newMeth(C$, 'c$$BA$S', function (bytes, charsetName) {
C$.c$$BA$I$I$S.apply(this, [bytes, 0, bytes.length, charsetName]);
}, 1);

Clazz.newMeth(C$, 'c$$BA$java_nio_charset_Charset', function (bytes, charset) {
C$.c$$BA$I$I$java_nio_charset_Charset.apply(this, [bytes, 0, bytes.length, charset]);
}, 1);

Clazz.newMeth(C$, 'c$$BA$I$I', function (bytes, offset, length) {
C$.$init$.apply(this);
String.checkBounds$BA$I$I(bytes, offset, length);
var v = (I$[4]||$incl$(4)).decode$BA$I$I(bytes, offset, length);
this.offset = 0;
this.count = v.length;
this.value = v;
}, 1);

Clazz.newMeth(C$, 'c$$BA', function (bytes) {
C$.c$$BA$I$I.apply(this, [bytes, 0, bytes.length]);
}, 1);

Clazz.newMeth(C$, 'c$$StringBuffer', function (buffer) {
C$.$init$.apply(this);
var result = buffer.toString();
this.value = result.value;
this.count = result.count;
this.offset = result.offset;
}, 1);

Clazz.newMeth(C$, 'c$$StringBuilder', function (builder) {
C$.$init$.apply(this);
var result = builder.toString();
this.value = result.value;
this.count = result.count;
this.offset = result.offset;
}, 1);

Clazz.newMeth(C$, 'c$$I$I$CA', function (offset, count, value) {
C$.$init$.apply(this);
this.value = value;
this.offset = offset;
this.count = count;
}, 1);

Clazz.newMeth(C$, 'length$', function () {
return this.count;
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.count == 0;
});

Clazz.newMeth(C$, 'charAt', function (index) {
if ((index < 0) || (index >= this.count) ) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[index]);
}return this.value[index + this.offset];
});

Clazz.newMeth(C$, 'codePointAt', function (index) {
if ((index < 0) || (index >= this.count) ) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[index]);
}return Character.codePointAtImpl(this.value, this.offset + index, this.offset + this.count);
});

Clazz.newMeth(C$, 'codePointBefore$I', function (index) {
var i = index - 1;
if ((i < 0) || (i >= this.count) ) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[index]);
}return Character.codePointBeforeImpl(this.value, this.offset + index, this.offset);
});

Clazz.newMeth(C$, 'codePointCount$I$I', function (beginIndex, endIndex) {
if (beginIndex < 0 || endIndex > this.count  || beginIndex > endIndex ) {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}return Character.codePointCountImpl(this.value, this.offset + beginIndex, endIndex - beginIndex);
});

Clazz.newMeth(C$, 'offsetByCodePoints$I$I', function (index, codePointOffset) {
if (index < 0 || index > this.count ) {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}return Character.offsetByCodePointsImpl(this.value, this.offset, this.count, this.offset + index, codePointOffset) - this.offset;
});

Clazz.newMeth(C$, 'getChars$CA$I', function (dst, dstBegin) {
System.arraycopy(this.value, this.offset, dst, dstBegin, this.count);
});

Clazz.newMeth(C$, 'getChars$I$I$CA$I', function (srcBegin, srcEnd, dst, dstBegin) {
if (srcBegin < 0) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[srcBegin]);
}if (srcEnd > this.count) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[srcEnd]);
}if (srcBegin > srcEnd) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[srcEnd - srcBegin]);
}System.arraycopy(this.value, this.offset + srcBegin, dst, dstBegin, srcEnd - srcBegin);
});

Clazz.newMeth(C$, 'getBytes', function (srcBegin, srcEnd, dst, dstBegin) {
if (srcBegin < 0) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[srcBegin]);
}if (srcEnd > this.count) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[srcEnd]);
}if (srcBegin > srcEnd) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[srcEnd - srcBegin]);
}var j = dstBegin;
var n = this.offset + srcEnd;
var i = this.offset + srcBegin;
var val = this.value;
while (i < n){
dst[j++] = ((val[i++].$c()|0)|0);
}
});

Clazz.newMeth(C$, 'getBytes', function (charsetName) {
if (charsetName == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
return (I$[4]||$incl$(4)).encode$S$CA$I$I(charsetName, this.value, this.offset, this.count);
});

Clazz.newMeth(C$, 'getBytes', function (charset) {
if (charset == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
return (I$[4]||$incl$(4)).encode$java_nio_charset_Charset$CA$I$I(charset, this.value, this.offset, this.count);
});

Clazz.newMeth(C$, 'getBytes', function () {
return (I$[4]||$incl$(4)).encode$CA$I$I(this.value, this.offset, this.count);
});

Clazz.newMeth(C$, 'equals$O', function (anObject) {
if (this === anObject ) {
return true;
}if (Clazz.instanceOf(anObject, "java.lang.String")) {
var anotherString = anObject;
var n = this.count;
if (n == anotherString.count) {
var v1 = this.value;
var v2 = anotherString.value;
var i = this.offset;
var j = anotherString.offset;
while (n-- != 0){
if (v1[i++] != v2[j++]) return false;
}
return true;
}}return false;
});

Clazz.newMeth(C$, 'contentEquals$StringBuffer', function (sb) {
{
return this.contentEquals$CharSequence(sb);
}});

Clazz.newMeth(C$, 'contentEquals$CharSequence', function (cs) {
if (this.count != cs.length$()) return false;
if (Clazz.instanceOf(cs, "java.lang.AbstractStringBuilder")) {
var v1 = this.value;
var v2 = (cs).getValue();
var i = this.offset;
var j = 0;
var n = this.count;
while (n-- != 0){
if (v1[i++] != v2[j++]) return false;
}
return true;
}if (cs.equals$O(this)) return true;
var v1 = this.value;
var i = this.offset;
var j = 0;
var n = this.count;
while (n-- != 0){
if (v1[i++] != cs.charAt$I(j++)) return false;
}
return true;
});

Clazz.newMeth(C$, 'equalsIgnoreCase$S', function (anotherString) {
return (this == anotherString) ? true : (anotherString != null ) && (anotherString.count == this.count) && this.regionMatches$Z$I$S$I$I(true, 0, anotherString, 0, this.count)  ;
});

Clazz.newMeth(C$, ['compareTo$S','compareTo$TT'], function (anotherString) {
var len1 = this.count;
var len2 = anotherString.count;
var n = Math.min(len1, len2);
var v1 = this.value;
var v2 = anotherString.value;
var i = this.offset;
var j = anotherString.offset;
if (i == j) {
var k = i;
var lim = n + i;
while (k < lim){
var c1 = v1[k];
var c2 = v2[k];
if (c1 != c2) {
return c1.$c() - c2.$c();
}k++;
}
} else {
while (n-- != 0){
var c1 = v1[i++];
var c2 = v2[j++];
if (c1 != c2) {
return c1.$c() - c2.$c();
}}
}return len1 - len2;
});

Clazz.newMeth(C$, 'compareToIgnoreCase$S', function (str) {
return String.CASE_INSENSITIVE_ORDER.compare$TT$TT(this, str);
});

Clazz.newMeth(C$, 'regionMatches$I$S$I$I', function (toffset, other, ooffset, len) {
var ta = this.value;
var to = this.offset + toffset;
var pa = other.value;
var po = other.offset + ooffset;
if ((ooffset < 0) || (toffset < 0) || (toffset > this.count - len) || (ooffset > other.count - len)  ) {
return false;
}while (len-- > 0){
if (ta[to++] != pa[po++]) {
return false;
}}
return true;
});

Clazz.newMeth(C$, 'regionMatches$Z$I$S$I$I', function (ignoreCase, toffset, other, ooffset, len) {
var ta = this.value;
var to = this.offset + toffset;
var pa = other.value;
var po = other.offset + ooffset;
if ((ooffset < 0) || (toffset < 0) || (toffset > this.count - len) || (ooffset > other.count - len)  ) {
return false;
}while (len-- > 0){
var c1 = ta[to++];
var c2 = pa[po++];
if (c1 == c2) {
continue;
}if (ignoreCase) {
var u1 = Character.toUpperCase(c1);
var u2 = Character.toUpperCase(c2);
if (u1 == u2) {
continue;
}if (Character.toLowerCase(u1) == Character.toLowerCase(u2)) {
continue;
}}return false;
}
return true;
});

Clazz.newMeth(C$, 'startsWith$S$I', function (prefix, toffset) {
var ta = this.value;
var to = this.offset + toffset;
var pa = prefix.value;
var po = prefix.offset;
var pc = prefix.count;
if ((toffset < 0) || (toffset > this.count - pc) ) {
return false;
}while (--pc >= 0){
if (ta[to++] != pa[po++]) {
return false;
}}
return true;
});

Clazz.newMeth(C$, 'startsWith$S', function (prefix) {
return this.startsWith$S$I(prefix, 0);
});

Clazz.newMeth(C$, 'endsWith$S', function (suffix) {
return this.startsWith$S$I(suffix, this.count - suffix.count);
});

Clazz.newMeth(C$, 'hashCode', function () {
var h = this.hash;
if (h == 0) {
var off = this.offset;
var val = this.value;
var len = this.count;
for (var i = 0; i < len; i++) {
h = 31 * h + (val[off++]).$c();
}
this.hash = h;
}return h;
});

Clazz.newMeth(C$, 'indexOf', function (ch) {
return this.indexOf(ch, 0);
});

Clazz.newMeth(C$, 'indexOf', function (ch, fromIndex) {
var max = this.offset + this.count;
var v = this.value;
if (fromIndex < 0) {
fromIndex = 0;
} else if (fromIndex >= this.count) {
return -1;
}var i = this.offset + fromIndex;
if (ch < 65536) {
for (; i < max; i++) {
if ((v[i]).$c() == ch ) {
return i - this.offset;
}}
return -1;
}if (ch <= 1114111) {
var surrogates = Character.toChars(ch);
for (; i < max; i++) {
if (v[i] == surrogates[0]) {
if (i + 1 == max) {
break;
}if (v[i + 1] == surrogates[1]) {
return i - this.offset;
}}}
}return -1;
});

Clazz.newMeth(C$, 'lastIndexOf', function (ch) {
return this.lastIndexOf(ch, this.count - 1);
});

Clazz.newMeth(C$, 'lastIndexOf', function (ch, fromIndex) {
var min = this.offset;
var v = this.value;
var i = this.offset + ((fromIndex >= this.count) ? this.count - 1 : fromIndex);
if (ch < 65536) {
for (; i >= min; i--) {
if ((v[i]).$c() == ch ) {
return i - this.offset;
}}
return -1;
}var max = this.offset + this.count;
if (ch <= 1114111) {
var surrogates = Character.toChars(ch);
for (; i >= min; i--) {
if (v[i] == surrogates[0]) {
if (i + 1 == max) {
break;
}if (v[i + 1] == surrogates[1]) {
return i - this.offset;
}}}
}return -1;
});

Clazz.newMeth(C$, 'indexOf', function (str) {
return this.indexOf(str, 0);
});

Clazz.newMeth(C$, 'indexOf', function (str, fromIndex) {
return String.indexOf(this.value, this.offset, this.count, str.value, str.offset, str.count, fromIndex);
});

Clazz.newMeth(C$, 'indexOf', function (source, sourceOffset, sourceCount, target, targetOffset, targetCount, fromIndex) {
if (fromIndex >= sourceCount) {
return (targetCount == 0 ? sourceCount : -1);
}if (fromIndex < 0) {
fromIndex = 0;
}if (targetCount == 0) {
return fromIndex;
}var first = target[targetOffset];
var max = sourceOffset + (sourceCount - targetCount);
for (var i = sourceOffset + fromIndex; i <= max; i++) {
if (source[i] != first) {
while (++i <= max && source[i] != first );
}if (i <= max) {
var j = i + 1;
var end = j + targetCount - 1;
for (var k = targetOffset + 1; j < end && source[j] == target[k] ; j++, k++) ;
if (j == end) {
return i - sourceOffset;
}}}
return -1;
}, 1);

Clazz.newMeth(C$, 'lastIndexOf', function (str) {
return this.lastIndexOf(str, this.count);
});

Clazz.newMeth(C$, 'lastIndexOf', function (str, fromIndex) {
return String.lastIndexOf(this.value, this.offset, this.count, str.value, str.offset, str.count, fromIndex);
});

Clazz.newMeth(C$, 'lastIndexOf', function (source, sourceOffset, sourceCount, target, targetOffset, targetCount, fromIndex) {
var rightIndex = sourceCount - targetCount;
if (fromIndex < 0) {
return -1;
}if (fromIndex > rightIndex) {
fromIndex = rightIndex;
}if (targetCount == 0) {
return fromIndex;
}var strLastIndex = targetOffset + targetCount - 1;
var strLastChar = target[strLastIndex];
var min = sourceOffset + targetCount - 1;
var i = min + fromIndex;
startSearchForLastChar : while (true){
while (i >= min && source[i] != strLastChar ){
i--;
}
if (i < min) {
return -1;
}var j = i - 1;
var start = j - (targetCount - 1);
var k = strLastIndex - 1;
while (j > start){
if (source[j--] != target[k--]) {
i--;
continue startSearchForLastChar;
}}
return start - sourceOffset + 1;
}
}, 1);

Clazz.newMeth(C$, 'substring', function (beginIndex) {
return this.substring(beginIndex, this.count);
});

Clazz.newMeth(C$, 'substring', function (beginIndex, endIndex) {
if (beginIndex < 0) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[beginIndex]);
}if (endIndex > this.count) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[endIndex]);
}if (beginIndex > endIndex) {
throw Clazz.new_(Clazz.load('java.lang.StringIndexOutOfBoundsException').c$$I,[endIndex - beginIndex]);
}return ((beginIndex == 0) && (endIndex == this.count) ) ? this :  String.instantialize(this.offset + beginIndex, endIndex - beginIndex, this.value);
});

Clazz.newMeth(C$, 'subSequence$I$I', function (beginIndex, endIndex) {
return this.substring(beginIndex, endIndex);
});

Clazz.newMeth(C$, 'concat$S', function (str) {
var otherLen = str.length$();
if (otherLen == 0) {
return this;
}var buf = Clazz.array(Character.TYPE, [this.count + otherLen]);
this.getChars$I$I$CA$I(0, this.count, buf, 0);
str.getChars$I$I$CA$I(0, otherLen, buf, this.count);
return  String.instantialize(0, this.count + otherLen, buf);
});

Clazz.newMeth(C$, 'replace$C$C', function (oldChar, newChar) {
if (oldChar != newChar) {
var len = this.count;
var i = -1;
var val = this.value;
var off = this.offset;
while (++i < len){
if (val[off + i] == oldChar) {
break;
}}
if (i < len) {
var buf = Clazz.array(Character.TYPE, [len]);
for (var j = 0; j < i; j++) {
buf[j] = val[off + j];
}
while (i < len){
var c = val[off + i];
buf[i] = (c == oldChar) ? newChar : c;
i++;
}
return  String.instantialize(0, len, buf);
}}return this;
});

Clazz.newMeth(C$, 'matches$S', function (regex) {
return (I$[5]||$incl$(5)).matches$S$CharSequence(regex, this);
});

Clazz.newMeth(C$, 'contains$CharSequence', function (s) {
return this.indexOf(s.toString()) > -1;
});

Clazz.newMeth(C$, 'replaceFirst$S$S', function (regex, replacement) {
return (I$[5]||$incl$(5)).compile$S(regex).matcher$CharSequence(this).replaceFirst$S(replacement);
});

Clazz.newMeth(C$, 'replaceAll$S$S', function (regex, replacement) {
return (I$[5]||$incl$(5)).compile$S(regex).matcher$CharSequence(this).replaceAll$S(replacement);
});

Clazz.newMeth(C$, 'replace$CharSequence$CharSequence', function (target, replacement) {
return (I$[5]||$incl$(5)).compile$S$I(target.toString(), 16).matcher$CharSequence(this).replaceAll$S((I$[6]||$incl$(6)).quoteReplacement$S(replacement.toString()));
});

Clazz.newMeth(C$, 'split$S$I', function (regex, limit) {
return (I$[5]||$incl$(5)).compile$S(regex).split$CharSequence$I(this, limit);
});

Clazz.newMeth(C$, 'split$S', function (regex) {
return $plit(regex, 0);
});

Clazz.newMeth(C$, 'toLowerCase', function (locale) {
if (locale == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}var firstUpper;
scan : {
for (firstUpper = 0; firstUpper < this.count; ) {
var c = this.value[this.offset + firstUpper];
if ((c >= "\ud800") && (c <= "\udbff") ) {
var supplChar = this.codePointAt(firstUpper);
if (supplChar != Character.toLowerCase(supplChar)) {
break scan;
}firstUpper = firstUpper+(Character.charCount(supplChar));
} else {
if (c != Character.toLowerCase(c)) {
break scan;
}firstUpper++;
}}
return this;
}var result = Clazz.array(Character.TYPE, [this.count]);
var resultOffset = 0;
System.arraycopy(this.value, this.offset, result, 0, firstUpper);
var lang = locale.getLanguage();
var localeDependent = (lang == "tr" || lang == "az"  || lang == "lt" );
var lowerCharArray;
var lowerChar;
var srcChar;
var srcCount;
for (var i = firstUpper; i < this.count; i = i+(srcCount)) {
srcChar = this.value[this.offset + i].$c();
if (String.fromCharCode(srcChar) >= "\ud800" && String.fromCharCode(srcChar) <= "\udbff" ) {
srcChar = this.codePointAt(i);
srcCount = Character.charCount(srcChar);
} else {
srcCount = 1;
}if (localeDependent || srcChar == 931  ) {
lowerChar = (I$[7]||$incl$(7)).toLowerCaseEx$S$I$java_util_Locale(this, i, locale);
} else {
lowerChar = Character.toLowerCase(srcChar);
}if ((lowerChar == -1) || (lowerChar >= 65536) ) {
if (lowerChar == -1) {
lowerCharArray = (I$[7]||$incl$(7)).toLowerCaseCharArray$S$I$java_util_Locale(this, i, locale);
} else if (srcCount == 2) {
resultOffset = resultOffset+(Character.toChars(lowerChar, result, i + resultOffset) - srcCount);
continue;
} else {
lowerCharArray = Character.toChars(lowerChar);
}var mapLen = lowerCharArray.length;
if (mapLen > srcCount) {
var result2 = Clazz.array(Character.TYPE, [result.length + mapLen - srcCount]);
System.arraycopy(result, 0, result2, 0, i + resultOffset);
result = result2;
}for (var x = 0; x < mapLen; ++x) {
result[i + resultOffset + x ] = lowerCharArray[x];
}
resultOffset = resultOffset+((mapLen - srcCount));
} else {
result[i + resultOffset] = String.fromCharCode(lowerChar);
}}
return  String.instantialize(0, this.count + resultOffset, result);
});

Clazz.newMeth(C$, 'toLowerCase', function () {
return this.toLowerCase((I$[8]||$incl$(8)).getDefault());
});

Clazz.newMeth(C$, 'toUpperCase', function (locale) {
if (locale == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}var firstLower;
scan : {
for (firstLower = 0; firstLower < this.count; ) {
var c = this.value[this.offset + firstLower].$c();
var srcCount;
if ((c >= "\ud800".$c() ) && (c <= "\udbff".$c() ) ) {
c = this.codePointAt(firstLower);
srcCount = Character.charCount(c);
} else {
srcCount = 1;
}var upperCaseChar = Character.toUpperCaseEx(c);
if ((upperCaseChar == -1) || (c != upperCaseChar) ) {
break scan;
}firstLower = firstLower+(srcCount);
}
return this;
}var result = Clazz.array(Character.TYPE, [this.count]);
var resultOffset = 0;
System.arraycopy(this.value, this.offset, result, 0, firstLower);
var lang = locale.getLanguage();
var localeDependent = (lang == "tr" || lang == "az"  || lang == "lt" );
var upperCharArray;
var upperChar;
var srcChar;
var srcCount;
for (var i = firstLower; i < this.count; i = i+(srcCount)) {
srcChar = this.value[this.offset + i].$c();
if (String.fromCharCode(srcChar) >= "\ud800" && String.fromCharCode(srcChar) <= "\udbff" ) {
srcChar = this.codePointAt(i);
srcCount = Character.charCount(srcChar);
} else {
srcCount = 1;
}if (localeDependent) {
upperChar = (I$[7]||$incl$(7)).toUpperCaseEx$S$I$java_util_Locale(this, i, locale);
} else {
upperChar = Character.toUpperCaseEx(srcChar);
}if ((upperChar == -1) || (upperChar >= 65536) ) {
if (upperChar == -1) {
if (localeDependent) {
upperCharArray = (I$[7]||$incl$(7)).toUpperCaseCharArray$S$I$java_util_Locale(this, i, locale);
} else {
upperCharArray = Character.toUpperCaseCharArray(srcChar);
}} else if (srcCount == 2) {
resultOffset = resultOffset+(Character.toChars(upperChar, result, i + resultOffset) - srcCount);
continue;
} else {
upperCharArray = Character.toChars(upperChar);
}var mapLen = upperCharArray.length;
if (mapLen > srcCount) {
var result2 = Clazz.array(Character.TYPE, [result.length + mapLen - srcCount]);
System.arraycopy(result, 0, result2, 0, i + resultOffset);
result = result2;
}for (var x = 0; x < mapLen; ++x) {
result[i + resultOffset + x ] = upperCharArray[x];
}
resultOffset = resultOffset+((mapLen - srcCount));
} else {
result[i + resultOffset] = String.fromCharCode(upperChar);
}}
return  String.instantialize(0, this.count + resultOffset, result);
});

Clazz.newMeth(C$, 'toUpperCase', function () {
return this.toUpperCase((I$[8]||$incl$(8)).getDefault());
});

Clazz.newMeth(C$, 'trim', function () {
var len = this.count;
var st = 0;
var off = this.offset;
var val = this.value;
while ((st < len) && (val[off + st] <= " ") ){
st++;
}
while ((st < len) && (val[off + len - 1] <= " ") ){
len--;
}
return ((st > 0) || (len < this.count) ) ? this.substring(st, len) : this;
});

Clazz.newMeth(C$, 'toString', function () {
return this;
});

Clazz.newMeth(C$, 'toCharArray', function () {
var result = Clazz.array(Character.TYPE, [this.count]);
this.getChars$I$I$CA$I(0, this.count, result, 0);
return result;
});

Clazz.newMeth(C$, 'format', function (format, args) {
return Clazz.new_((I$[9]||$incl$(9))).format$S$OA(format, args).toString();
}, 1);

Clazz.newMeth(C$, 'format', function (l, format, args) {
return Clazz.new_((I$[9]||$incl$(9)).c$$java_util_Locale,[l]).format$S$OA(format, args).toString();
}, 1);

Clazz.newMeth(C$, '$valueOf', function (obj) {
return (obj == null ) ? "null" : obj.toString();
}, 1);

Clazz.newMeth(C$, '$valueOf', function (data) {
return  String.instantialize(data);
}, 1);

Clazz.newMeth(C$, '$valueOf', function (data, offset, count) {
return  String.instantialize(data, offset, count);
}, 1);

Clazz.newMeth(C$, 'copyValueOf$CA$I$I', function (data, offset, count) {
return  String.instantialize(data, offset, count);
}, 1);

Clazz.newMeth(C$, 'copyValueOf$CA', function (data) {
return String.copyValueOf$CA$I$I(data, 0, data.length);
}, 1);

Clazz.newMeth(C$, '$valueOf', function (b) {
return b ? "true" : "false";
}, 1);

Clazz.newMeth(C$, '$valueOf', function (c) {
var data = Clazz.array(Character.TYPE, -1, [c]);
return  String.instantialize(0, 1, data);
}, 1);

Clazz.newMeth(C$, '$valueOf', function (i) {
return Integer.toString(i, 10);
}, 1);

Clazz.newMeth(C$, '$valueOf', function (l) {
return Long.toString(l, 10);
}, 1);

Clazz.newMeth(C$, '$valueOf', function (f) {
return Float.toString(f);
}, 1);

Clazz.newMeth(C$, '$valueOf', function (d) {
return Double.toString(d);
}, 1);

Clazz.newMeth(C$, 'intern', function () {
alert('native method must be replaced! Ljava/lang/String;.intern()Ljava/lang/String;');
}
);
;
(function(){var C$=Clazz.newClass(String, "CaseInsensitiveComparator", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, ['java.util.Comparator', 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['compare$S$S','compare$TT$TT'], function (s1, s2) {
var n1 = s1.length$();
var n2 = s2.length$();
var min = Math.min(n1, n2);
for (var i = 0; i < min; i++) {
var c1 = s1.charAt(i);
var c2 = s2.charAt(i);
if (c1 != c2) {
c1 = Character.toUpperCase(c1);
c2 = Character.toUpperCase(c2);
if (c1 != c2) {
c1 = Character.toLowerCase(c1);
c2 = Character.toLowerCase(c2);
if (c1 != c2) {
return c1.$c() - c2.$c();
}}}}
return n1 - n2;
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-06 08:58:37
