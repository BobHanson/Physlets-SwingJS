(function(){var P$=java.lang,I$=[['java.lang.CharacterDataLatin1','java.lang.CharacterData00','java.lang.CharacterData01','java.lang.CharacterData02','java.lang.CharacterData0E','java.lang.CharacterDataPrivateUse','java.lang.CharacterDataUndefined']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(java.lang, "CharacterData");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'toUpperCaseEx$I', function (ch) {
return this.toUpperCase$I(ch);
});

Clazz.newMeth(C$, 'toUpperCaseCharArray$I', function (ch) {
return null;
});

Clazz.newMeth(C$, 'of$I', function (ch) {
if (ch >>> 8 == 0) {
return (I$[1]||$incl$(1)).instance;
} else {
switch (ch >>> 16) {
case (0):
return (I$[2]||$incl$(2)).instance;
case (1):
return (I$[3]||$incl$(3)).instance;
case (2):
return (I$[4]||$incl$(4)).instance;
case (14):
return (I$[5]||$incl$(5)).instance;
case (15):
case (16):
return (I$[6]||$incl$(6)).instance;
default:
return (I$[7]||$incl$(7)).instance;
}
}}, 1);

Clazz.newMeth(C$, 'of1$I', function (ch) {
if (ch >>> 8 == 0) {
return (I$[1]||$incl$(1)).instance;
} else {
switch (ch >>> 16) {
case (0):
return (I$[2]||$incl$(2)).instance;
case (1):
return (I$[3]||$incl$(3)).instance;
case (2):
return (I$[4]||$incl$(4)).instance;
case (14):
return (I$[5]||$incl$(5)).instance;
case (15):
case (16):
return (I$[6]||$incl$(6)).instance;
default:
return (I$[7]||$incl$(7)).instance;
}
}});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:05
