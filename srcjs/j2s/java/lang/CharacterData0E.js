(function(){var P$=java.lang,I$=[['java.lang.CharacterData0E']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(java.lang, "CharacterData0E", null, 'CharacterData');
C$.instance = null;
var p$=C$.prototype;
C$.X = null;
C$.Y = null;
C$.A = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.instance = Clazz.new_((I$[1]||$incl$(1)));
C$.X = ("\u0000\u0010\u0010\u0010    0000000@                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ").toCharArray();
C$.Y = ("\u0000\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0006\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002").toCharArray();
C$.A = Clazz.array(Integer.TYPE, [8]);
{
{
var data = "\u7800\u0000\u4800\u1010\u7800\u0000\u7800\u0000\u4800\u1010\u4800\u1010\u4000\u3006\u4000\u3006".toCharArray();
Clazz.assert(C$, this, function(){return (data.length == 16)});
var i = 0;
var j = 0;
while (i < 16){
var entry = (data[i++]).$c() << 16;
CharacterData0E.A[j++] = entry | data[i++];
}
}}
;
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getProperties$I', function (ch) {
var offset = String.fromCharCode(ch);
var props = CharacterData0E.A[CharacterData0E.Y[CharacterData0E.X[offset.$c() >> 5] | ((offset.$c() >> 1) & 15)] | (offset & 1)];
return props;
});

Clazz.newMeth(C$, 'getType$I', function (ch) {
var props = this.getProperties$I(ch);
return (props & 31);
});

Clazz.newMeth(C$, 'isJavaIdentifierStart$I', function (ch) {
var props = this.getProperties$I(ch);
return ((props & 28672) >= 20480);
});

Clazz.newMeth(C$, 'isJavaIdentifierPart$I', function (ch) {
var props = this.getProperties$I(ch);
return ((props & 12288) != 0);
});

Clazz.newMeth(C$, 'isUnicodeIdentifierStart$I', function (ch) {
var props = this.getProperties$I(ch);
return ((props & 28672) == 28672);
});

Clazz.newMeth(C$, 'isUnicodeIdentifierPart$I', function (ch) {
var props = this.getProperties$I(ch);
return ((props & 4096) != 0);
});

Clazz.newMeth(C$, 'isIdentifierIgnorable$I', function (ch) {
var props = this.getProperties$I(ch);
return ((props & 28672) == 4096);
});

Clazz.newMeth(C$, 'toLowerCase$I', function (ch) {
var mapChar = ch;
var val = this.getProperties$I(ch);
if ((val & 131072) != 0) {
var offset = val << 5 >> 23;
mapChar = ch + offset;
}return mapChar;
});

Clazz.newMeth(C$, 'toUpperCase$I', function (ch) {
var mapChar = ch;
var val = this.getProperties$I(ch);
if ((val & 65536) != 0) {
var offset = val << 5 >> 23;
mapChar = ch - offset;
}return mapChar;
});

Clazz.newMeth(C$, 'toTitleCase$I', function (ch) {
var mapChar = ch;
var val = this.getProperties$I(ch);
if ((val & 32768) != 0) {
if ((val & 65536) == 0) {
mapChar = ch + 1;
} else if ((val & 131072) == 0) {
mapChar = ch - 1;
}} else if ((val & 65536) != 0) {
mapChar = this.toUpperCase$I(ch);
}return mapChar;
});

Clazz.newMeth(C$, 'digit$I$I', function (ch, radix) {
var value = -1;
if (radix >= 2 && radix <= 36 ) {
var val = this.getProperties$I(ch);
var kind = val & 31;
if (kind == 9) {
value = ch + ((val & 992) >> 5) & 31;
} else if ((val & 3072) == 3072) {
value = (ch + ((val & 992) >> 5) & 31) + 10;
}}return (value < radix) ? value : -1;
});

Clazz.newMeth(C$, 'getNumericValue$I', function (ch) {
var val = this.getProperties$I(ch);
var retval = -1;
switch (val & 3072) {
default:
case (0x00000000):
retval = -1;
break;
case (0x00000400):
retval = ch + ((val & 992) >> 5) & 31;
break;
case (0x00000800):
retval = -2;
break;
case (0x00000C00):
retval = (ch + ((val & 992) >> 5) & 31) + 10;
break;
}
return retval;
});

Clazz.newMeth(C$, 'isWhitespace$I', function (ch) {
var props = this.getProperties$I(ch);
return ((props & 28672) == 16384);
});

Clazz.newMeth(C$, 'getDirectionality$I', function (ch) {
var val = this.getProperties$I(ch);
var directionality = ($b$[0] = (((val & 2013265920) >> 27)|0), $b$[0]);
if (directionality == 15) {
directionality = ($b$[0] = -1, $b$[0]);
}return $b$[0] = directionality, $b$[0];
});

Clazz.newMeth(C$, 'isMirrored$I', function (ch) {
var props = this.getProperties$I(ch);
return ((props & -2147483648) != 0);
});

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);
C$.$_ASSERT_ENABLED_ = ClassLoader.$getClassAssertionStatus(C$);
var $b$ = new Int8Array(1);
})();
//Created 2018-02-06 08:58:34
