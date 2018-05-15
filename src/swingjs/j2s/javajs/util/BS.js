(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['javajs.util.SB']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "BS", null, null, ['Cloneable', 'javajs.api.JSONEncodable']);
var p$=C$.prototype;
C$.emptyBitmap = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.emptyBitmap = Clazz.array(Integer.TYPE, [0]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.words = null;
this.wordsInUse = 0;
this.sizeIsSticky = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.wordsInUse = 0;
this.sizeIsSticky = false;
}, 1);

Clazz.newMeth(C$, 'wordIndex$I', function (bitIndex) {
return bitIndex >> 5;
}, 1);

Clazz.newMeth(C$, 'recalculateWordsInUse', function () {
var i;
for (i = this.wordsInUse - 1; i >= 0; i--) if (this.words[i] != 0) break;

this.wordsInUse = i + 1;
});

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
p$.initWords$I.apply(this, [32]);
this.sizeIsSticky = false;
}, 1);

Clazz.newMeth(C$, 'newN$I', function (nbits) {
var bs = Clazz.new_(C$);
bs.init$I(nbits);
return bs;
}, 1);

Clazz.newMeth(C$, 'init$I', function (nbits) {
if (nbits < 0) throw Clazz.new_(Clazz.load('java.lang.NegativeArraySizeException').c$$S,["nbits < 0: " + nbits]);
p$.initWords$I.apply(this, [nbits]);
this.sizeIsSticky = true;
});

Clazz.newMeth(C$, 'initWords$I', function (nbits) {
this.words = Clazz.array(Integer.TYPE, [C$.wordIndex$I(nbits - 1) + 1]);
});

Clazz.newMeth(C$, 'ensureCapacity$I', function (wordsRequired) {
if (this.words.length < wordsRequired) {
var request = Math.max(2 * this.words.length, wordsRequired);
p$.setLength$I.apply(this, [request]);
this.sizeIsSticky = false;
}});

Clazz.newMeth(C$, 'expandTo$I', function (wordIndex) {
var wordsRequired = wordIndex + 1;
if (this.wordsInUse < wordsRequired) {
p$.ensureCapacity$I.apply(this, [wordsRequired]);
this.wordsInUse = wordsRequired;
}});

Clazz.newMeth(C$, 'set$I', function (bitIndex) {
if (bitIndex < 0) throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException').c$$S,["bitIndex < 0: " + bitIndex]);
var wordIndex = C$.wordIndex$I(bitIndex);
this.expandTo$I(wordIndex);
this.words[$j$=wordIndex] = this.words[$j$]|((1 << bitIndex));
});

Clazz.newMeth(C$, 'setBitTo$I$Z', function (bitIndex, value) {
if (value) this.set$I(bitIndex);
 else this.clear$I(bitIndex);
});

Clazz.newMeth(C$, 'setBits$I$I', function (fromIndex, toIndex) {
if (fromIndex == toIndex) return;
var startWordIndex = C$.wordIndex$I(fromIndex);
var endWordIndex = C$.wordIndex$I(toIndex - 1);
this.expandTo$I(endWordIndex);
var firstWordMask = -1 << fromIndex;
var lastWordMask = -1 >>> -toIndex;
if (startWordIndex == endWordIndex) {
this.words[$j$=startWordIndex] = this.words[$j$]|((firstWordMask & lastWordMask));
} else {
this.words[$j$=startWordIndex] = this.words[$j$]|(firstWordMask);
for (var i = startWordIndex + 1; i < endWordIndex; i++) this.words[i] = -1;

this.words[$j$=endWordIndex] = this.words[$j$]|(lastWordMask);
}});

Clazz.newMeth(C$, 'clear$I', function (bitIndex) {
if (bitIndex < 0) throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException').c$$S,["bitIndex < 0: " + bitIndex]);
var wordIndex = C$.wordIndex$I(bitIndex);
if (wordIndex >= this.wordsInUse) return;
this.words[$j$=wordIndex] = this.words[$j$]&(~(1 << bitIndex));
this.recalculateWordsInUse();
});

Clazz.newMeth(C$, 'clearBits$I$I', function (fromIndex, toIndex) {
if (fromIndex == toIndex) return;
var startWordIndex = C$.wordIndex$I(fromIndex);
if (startWordIndex >= this.wordsInUse) return;
var endWordIndex = C$.wordIndex$I(toIndex - 1);
if (endWordIndex >= this.wordsInUse) {
toIndex = this.length$();
endWordIndex = this.wordsInUse - 1;
}var firstWordMask = -1 << fromIndex;
var lastWordMask = -1 >>> -toIndex;
if (startWordIndex == endWordIndex) {
this.words[$j$=startWordIndex] = this.words[$j$]&(~(firstWordMask & lastWordMask));
} else {
this.words[$j$=startWordIndex] = this.words[$j$]&(~firstWordMask);
for (var i = startWordIndex + 1; i < endWordIndex; i++) this.words[i] = 0;

this.words[$j$=endWordIndex] = this.words[$j$]&(~lastWordMask);
}this.recalculateWordsInUse();
});

Clazz.newMeth(C$, 'clearAll', function () {
while (this.wordsInUse > 0)this.words[--this.wordsInUse] = 0;

});

Clazz.newMeth(C$, 'get$I', function (bitIndex) {
if (bitIndex < 0) throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException').c$$S,["bitIndex < 0: " + bitIndex]);
var wordIndex = C$.wordIndex$I(bitIndex);
return (wordIndex < this.wordsInUse) && ((this.words[wordIndex] & (1 << bitIndex)) != 0) ;
});

Clazz.newMeth(C$, 'nextSetBit$I', function (fromIndex) {
if (fromIndex < 0) throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException').c$$S,["fromIndex < 0: " + fromIndex]);
var u = C$.wordIndex$I(fromIndex);
if (u >= this.wordsInUse) return -1;
var word = this.words[u] & (-1 << fromIndex);
while (true){
if (word != 0) return (u * 32) + Integer.numberOfTrailingZeros(word);
if (++u == this.wordsInUse) return -1;
word = this.words[u];
}
});

Clazz.newMeth(C$, 'nextClearBit$I', function (fromIndex) {
if (fromIndex < 0) throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException').c$$S,["fromIndex < 0: " + fromIndex]);
var u = C$.wordIndex$I(fromIndex);
if (u >= this.wordsInUse) return fromIndex;
var word = ~this.words[u] & (-1 << fromIndex);
while (true){
if (word != 0) return (u * 32) + Integer.numberOfTrailingZeros(word);
if (++u == this.wordsInUse) return this.wordsInUse * 32;
word = ~this.words[u];
}
});

Clazz.newMeth(C$, 'length$', function () {
if (this.wordsInUse == 0) return 0;
return 32 * (this.wordsInUse - 1) + (32 - Integer.numberOfLeadingZeros(this.words[this.wordsInUse - 1]));
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.wordsInUse == 0;
});

Clazz.newMeth(C$, 'intersects$javajs_util_BS', function (set) {
for (var i = Math.min(this.wordsInUse, set.wordsInUse) - 1; i >= 0; i--) if ((this.words[i] & set.words[i]) != 0) return true;

return false;
});

Clazz.newMeth(C$, 'cardinality', function () {
var sum = 0;
for (var i = 0; i < this.wordsInUse; i++) sum = sum+(Integer.bitCount(this.words[i]));

return sum;
});

Clazz.newMeth(C$, 'and$javajs_util_BS', function (set) {
if (this === set ) return;
while (this.wordsInUse > set.wordsInUse)this.words[--this.wordsInUse] = 0;

for (var i = 0; i < this.wordsInUse; i++) this.words[i] = this.words[i]&(set.words[i]);

this.recalculateWordsInUse();
});

Clazz.newMeth(C$, 'or$javajs_util_BS', function (set) {
if (this === set ) return;
var wordsInCommon = Math.min(this.wordsInUse, set.wordsInUse);
if (this.wordsInUse < set.wordsInUse) {
p$.ensureCapacity$I.apply(this, [set.wordsInUse]);
this.wordsInUse = set.wordsInUse;
}for (var i = 0; i < wordsInCommon; i++) this.words[i] = this.words[i]|(set.words[i]);

if (wordsInCommon < set.wordsInUse) System.arraycopy(set.words, wordsInCommon, this.words, wordsInCommon, this.wordsInUse - wordsInCommon);
});

Clazz.newMeth(C$, 'xor$javajs_util_BS', function (set) {
var wordsInCommon = Math.min(this.wordsInUse, set.wordsInUse);
if (this.wordsInUse < set.wordsInUse) {
p$.ensureCapacity$I.apply(this, [set.wordsInUse]);
this.wordsInUse = set.wordsInUse;
}for (var i = 0; i < wordsInCommon; i++) this.words[i] = this.words[i]^(set.words[i]);

if (wordsInCommon < set.wordsInUse) System.arraycopy(set.words, wordsInCommon, this.words, wordsInCommon, set.wordsInUse - wordsInCommon);
this.recalculateWordsInUse();
});

Clazz.newMeth(C$, 'andNot$javajs_util_BS', function (set) {
for (var i = Math.min(this.wordsInUse, set.wordsInUse) - 1; i >= 0; i--) this.words[i] = this.words[i]&(~set.words[i]);

this.recalculateWordsInUse();
});

Clazz.newMeth(C$, 'hashCode', function () {
var h = 1234;
for (var i = this.wordsInUse; --i >= 0; ) h = h^(this.words[i] * (i + 1));

return (((h >> 32) ^ h)|0);
});

Clazz.newMeth(C$, 'size', function () {
return this.words.length * 32;
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
if (!(Clazz.instanceOf(obj, "javajs.util.BS"))) return false;
if (this === obj ) return true;
var set = obj;
if (this.wordsInUse != set.wordsInUse) return false;
for (var i = 0; i < this.wordsInUse; i++) if (this.words[i] != set.words[i]) return false;

return true;
});

Clazz.newMeth(C$, 'clone', function () {
if (!this.sizeIsSticky && this.wordsInUse != this.words.length ) p$.setLength$I.apply(this, [this.wordsInUse]);
return C$.copy$javajs_util_BS(this);
});

Clazz.newMeth(C$, 'setLength$I', function (n) {
{
if (n == this.words.length) return;
if (n == this.wordsInUse) { this.words = Clazz.array(-1, this.words, 0, n);
return;
}
}
var a = Clazz.array(Integer.TYPE, [n]);
System.arraycopy(this.words, 0, a, 0, this.wordsInUse);
this.words = a;
});

Clazz.newMeth(C$, 'toString', function () {
return C$.escape$javajs_util_BS$C$C(this, "(", ")");
});

Clazz.newMeth(C$, 'copy$javajs_util_BS', function (bitsetToCopy) {
var bs;
{
bs = Clazz.clone(bitsetToCopy);
}
var wordCount = bitsetToCopy.wordsInUse;
if (wordCount == 0) {
bs.words = C$.emptyBitmap;
} else {
{
bs.words = Clazz.array(-1, bitsetToCopy.words, 0, bs.wordsInUse = wordCount);
}
}return bs;
}, 1);

Clazz.newMeth(C$, 'cardinalityN$I', function (max) {
var n = this.cardinality();
for (var i = this.length$(); --i >= max; ) if (this.get$I(i)) n--;

return n;
});

Clazz.newMeth(C$, 'toJSON', function () {
var numBits = (this.wordsInUse > 128 ? this.cardinality() : this.wordsInUse * 32);
var b = (I$[1]||$incl$(1)).newN$I(6 * numBits + 2);
b.appendC$C("[");
var i = this.nextSetBit$I(0);
if (i != -1) {
b.appendI$I(i);
for (i = this.nextSetBit$I(i + 1); i >= 0; i = this.nextSetBit$I(i + 1)) {
var endOfRun = this.nextClearBit$I(i);
do {
b.append$S(", ").appendI$I(i);
} while (++i < endOfRun);
}
}b.appendC$C("]");
return b.toString();
});

Clazz.newMeth(C$, 'escape$javajs_util_BS$C$C', function (bs, chOpen, chClose) {
if (bs == null ) return chOpen + "{}" + chClose ;
var s = Clazz.new_((I$[1]||$incl$(1)));
s.append$S(chOpen + "{");
var imax = bs.length$();
var iLast = -1;
var iFirst = -2;
var i = -1;
while (++i <= imax){
var isSet = bs.get$I(i);
if (i == imax || iLast >= 0 && !isSet  ) {
if (iLast >= 0 && iFirst != iLast ) s.append$S((iFirst == iLast - 1 ? " " : ":") + iLast);
if (i == imax) break;
iLast = -1;
}if (bs.get$I(i)) {
if (iLast < 0) {
s.append$S((iFirst == -2 ? "" : " ") + i);
iFirst = i;
}iLast = i;
}}
s.append$S("}").appendC$C(chClose);
return s.toString();
}, 1);

Clazz.newMeth(C$, 'unescape$S', function (str) {
var ch;
var len;
if (str == null  || (len = (str = str.trim()).length$()) < 4  || str.equalsIgnoreCase$S("({null})")  || (ch = str.charAt(0)) != "(" && ch != "["   || str.charAt(len - 1) != (ch == "(" ? ")" : "]")  || str.charAt(1) != "{"  || str.indexOf("}") != len - 2 ) return null;
len = len-(2);
for (var i = len; --i >= 2; ) if (((ch = str.charAt(i)).$c() < 48  || ch.$c() > 57  ) && ch != " "  && ch != "\u0009"  && ch != ":" ) return null;

var lastN = len;
while (48 <= (ch = str.charAt(--lastN)).$c()  && ch.$c() <= 57  ){
}
if (++lastN == len) lastN = 0;
 else try {
lastN = Integer.parseInt(str.substring(lastN, len));
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.NumberFormatException")){
return null;
} else {
throw e;
}
}
var bs = C$.newN$I(lastN);
lastN = -1;
var iPrev = -1;
var iThis = -2;
for (var i = 2; i <= len; i++) {
switch ((ch = str.charAt(i)).$c()) {
case 9:
case 32:
case 125:
if (iThis < 0) break;
if (iThis < lastN) return null;
lastN = iThis;
if (iPrev < 0) iPrev = iThis;
bs.setBits$I$I(iPrev, iThis + 1);
iPrev = -1;
iThis = -2;
break;
case 58:
iPrev = lastN = iThis;
iThis = -2;
break;
default:
if (48 <= ch.$c()  && ch.$c() <= 57  ) {
if (iThis < 0) iThis = 0;
iThis = (iThis * 10) + (ch.$c() - 48);
}}
}
return (iPrev >= 0 ? null : bs);
}, 1);
var $j$;
})();
//Created 2018-05-15 01:02:18
