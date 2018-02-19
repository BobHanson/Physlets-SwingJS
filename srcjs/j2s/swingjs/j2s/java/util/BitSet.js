(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "BitSet", null, 'javajs.util.BS');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (nbits) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.init$I(nbits);
}, 1);

Clazz.newMeth(C$, 'flip$I', function (bitIndex) {
if (bitIndex < 0) throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException').c$$S,["bitIndex < 0: " + bitIndex]);
var wordIndex = javajs.util.BS.wordIndex$I(bitIndex);
this.expandTo$I(wordIndex);
this.words[wordIndex] = this.words[wordIndex]^((1 << bitIndex));
});

Clazz.newMeth(C$, 'flip$I$I', function (fromIndex, toIndex) {
if (fromIndex == toIndex) return;
var startWordIndex = javajs.util.BS.wordIndex$I(fromIndex);
var endWordIndex = javajs.util.BS.wordIndex$I(toIndex - 1);
this.expandTo$I(endWordIndex);
var firstWordMask = -1 << fromIndex;
var lastWordMask = -1 >>> -toIndex;
if (startWordIndex == endWordIndex) {
this.words[startWordIndex] = this.words[startWordIndex]^((firstWordMask & lastWordMask));
} else {
this.words[startWordIndex] = this.words[startWordIndex]^(firstWordMask);
for (var i = startWordIndex + 1; i < endWordIndex; i++) this.words[i] = this.words[i]^(-1);

this.words[endWordIndex] = this.words[endWordIndex]^(lastWordMask);
}});

Clazz.newMeth(C$, 'set$I$Z', function (bitIndex, value) {
this.setBitTo$I$Z(bitIndex, value);
});

Clazz.newMeth(C$, 'set$I$I', function (fromIndex, toIndex) {
this.setBits$I$I(fromIndex, toIndex);
});

Clazz.newMeth(C$, 'set$I$I$Z', function (fromIndex, toIndex, value) {
if (value) this.set$I$I(fromIndex, toIndex);
 else this.clear$I$I(fromIndex, toIndex);
});

Clazz.newMeth(C$, 'clear$I$I', function (fromIndex, toIndex) {
this.clearBits$I$I(fromIndex, toIndex);
});

Clazz.newMeth(C$, 'clear', function () {
this.clearAll();
});

Clazz.newMeth(C$, 'get$I$I', function (fromIndex, toIndex) {
var len = this.length$();
if (len <= fromIndex || fromIndex == toIndex ) return Clazz.new_(C$.c$$I,[0]);
if (toIndex > len) toIndex = len;
var result = Clazz.new_(C$.c$$I,[toIndex - fromIndex]);
var targetWords = javajs.util.BS.wordIndex$I(toIndex - fromIndex - 1 ) + 1;
var sourceIndex = javajs.util.BS.wordIndex$I(fromIndex);
var wordAligned = ((fromIndex & 31) == 0);
for (var i = 0; i < targetWords - 1; i++, sourceIndex++) result.words[i] = wordAligned ? this.words[sourceIndex] : (this.words[sourceIndex] >>> fromIndex) | (this.words[sourceIndex + 1] << -fromIndex);

var lastWordMask = -1 >>> -toIndex;
result.words[targetWords - 1] = ((toIndex - 1) & 31) < (fromIndex & 31) ? ((this.words[sourceIndex] >>> fromIndex) | (this.words[sourceIndex + 1] & lastWordMask) << -fromIndex) : ((this.words[sourceIndex] & lastWordMask) >>> fromIndex);
result.wordsInUse = targetWords;
result.recalculateWordsInUse();
return result;
});
})();
//Created 2018-02-08 10:02:11
