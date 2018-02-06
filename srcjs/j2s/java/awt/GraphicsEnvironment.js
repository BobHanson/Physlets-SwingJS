(function(){var P$=Clazz.newPackage("java.awt"),I$=[['swingjs.JSUtil']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "GraphicsEnvironment");
C$.localEnv = null;
var p$=C$.prototype;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getLocalGraphicsEnvironment', function () {
if (C$.localEnv == null ) {
C$.localEnv = (I$[1]||$incl$(1)).getInstance$S("swingjs.JSGraphicsEnvironment");
}return C$.localEnv;
}, 1);

Clazz.newMeth(C$, 'isHeadless', function () {
return false;
}, 1);

Clazz.newMeth(C$, 'getHeadlessProperty', function () {
return false;
}, 1);

Clazz.newMeth(C$, 'checkHeadless', function () {
}, 1);

Clazz.newMeth(C$, 'isHeadlessInstance', function () {
return C$.getHeadlessProperty();
});

Clazz.newMeth(C$, 'registerFont$java_awt_Font', function (font) {
return true;
});

Clazz.newMeth(C$, 'preferLocaleFonts', function () {
});

Clazz.newMeth(C$, 'preferProportionalFonts', function () {
});

Clazz.newMeth(C$, 'getCenterPoint', function () {
return null;
});
})();
//Created 2018-02-06 08:58:11
