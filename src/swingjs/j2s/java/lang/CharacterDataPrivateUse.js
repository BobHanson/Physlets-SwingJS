(function(){var P$=java.lang,I$=[['java.lang.CharacterDataPrivateUse']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(java.lang, "CharacterDataPrivateUse", null, 'CharacterData');
C$.instance = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.instance = Clazz.new_((I$[1]||$incl$(1)));
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getProperties$I', function (ch) {
return 0;
});

Clazz.newMeth(C$, 'getType$I', function (ch) {
return (ch & 65534) == 65534 ? ($b$[0] = 0, $b$[0]) : ($b$[0] = 18, $b$[0]);
});

Clazz.newMeth(C$, 'isJavaIdentifierStart$I', function (ch) {
return false;
});

Clazz.newMeth(C$, 'isJavaIdentifierPart$I', function (ch) {
return false;
});

Clazz.newMeth(C$, 'isUnicodeIdentifierStart$I', function (ch) {
return false;
});

Clazz.newMeth(C$, 'isUnicodeIdentifierPart$I', function (ch) {
return false;
});

Clazz.newMeth(C$, 'isIdentifierIgnorable$I', function (ch) {
return false;
});

Clazz.newMeth(C$, 'toLowerCase$I', function (ch) {
return ch;
});

Clazz.newMeth(C$, 'toUpperCase$I', function (ch) {
return ch;
});

Clazz.newMeth(C$, 'toTitleCase$I', function (ch) {
return ch;
});

Clazz.newMeth(C$, 'digit$I$I', function (ch, radix) {
return -1;
});

Clazz.newMeth(C$, 'getNumericValue$I', function (ch) {
return -1;
});

Clazz.newMeth(C$, 'isWhitespace$I', function (ch) {
return false;
});

Clazz.newMeth(C$, 'getDirectionality$I', function (ch) {
return $b$[0] = (ch & 65534) == 65534 ? (-1|0) : (0|0), $b$[0];
});

Clazz.newMeth(C$, 'isMirrored$I', function (ch) {
return false;
});

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);
var $b$ = new Int8Array(1);
})();
//Created 2018-05-15 01:02:06
