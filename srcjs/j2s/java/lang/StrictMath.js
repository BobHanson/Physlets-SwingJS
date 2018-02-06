(function(){var P$=java.lang,I$=[];
var C$=Clazz.newClass(java.lang, "StrictMath");
C$.$random = null;
var p$=C$.prototype;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'abs$D', function (d) {

return Math.abs (d);
}, 1);

Clazz.newMeth(C$, 'abs$F', function (f) {

return Math.abs (f);
}, 1);

Clazz.newMeth(C$, 'abs$I', function (i) {
return i >= 0 ? i : -i;
}, 1);

Clazz.newMeth(C$, 'abs$J', function (l) {
return l >= 0 ? l : -l;
}, 1);

Clazz.newMeth(C$, 'acos$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.acos(D)D');
}
, 2);

Clazz.newMeth(C$, 'asin$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.asin(D)D');
}
, 2);

Clazz.newMeth(C$, 'atan$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.atan(D)D');
}
, 2);

Clazz.newMeth(C$, 'atan2$D$D', function (d1, d2) {
alert('native method must be replaced! Ljava/lang/StrictMath;.atan2(DD)D');
}
, 2);

Clazz.newMeth(C$, 'cbrt$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.cbrt(D)D');
}
, 2);

Clazz.newMeth(C$, 'ceil$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.ceil(D)D');
}
, 2);

Clazz.newMeth(C$, 'cosh$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.cosh(D)D');
}
, 2);

Clazz.newMeth(C$, 'cos$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.cos(D)D');
}
, 2);

Clazz.newMeth(C$, 'exp$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.exp(D)D');
}
, 2);

Clazz.newMeth(C$, 'expm1$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.expm1(D)D');
}
, 2);

Clazz.newMeth(C$, 'floor$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.floor(D)D');
}
, 2);

Clazz.newMeth(C$, 'hypot$D$D', function (x, y) {
alert('native method must be replaced! Ljava/lang/StrictMath;.hypot(DD)D');
}
, 2);

Clazz.newMeth(C$, 'IEEEremainder$D$D', function (d1, d2) {
alert('native method must be replaced! Ljava/lang/StrictMath;.IEEEremainder(DD)D');
}
, 2);

Clazz.newMeth(C$, 'log$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.log(D)D');
}
, 2);

Clazz.newMeth(C$, 'log10$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.log10(D)D');
}
, 2);

Clazz.newMeth(C$, 'log1p$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.log1p(D)D');
}
, 2);

Clazz.newMeth(C$, 'max$D$D', function (d1, d2) {

return Math.max (d1, d2);
}, 1);

Clazz.newMeth(C$, 'max$F$F', function (f1, f2) {

return Math.max (f1, f2);
}, 1);

Clazz.newMeth(C$, 'max$I$I', function (i1, i2) {

return Math.max (i1, i2);
}, 1);

Clazz.newMeth(C$, 'max$J$J', function (l1, l2) {

return Math.max (l1, l2);
}, 1);

Clazz.newMeth(C$, 'min$D$D', function (d1, d2) {

return Math.min (d1, d2);
}, 1);

Clazz.newMeth(C$, 'min$F$F', function (f1, f2) {

return Math.min (f1, f2);
}, 1);

Clazz.newMeth(C$, 'min$I$I', function (i1, i2) {

return Math.min (i1, i2);
}, 1);

Clazz.newMeth(C$, 'min$J$J', function (l1, l2) {

return Math.min (l1, l2);
}, 1);

Clazz.newMeth(C$, 'pow$D$D', function (d1, d2) {
alert('native method must be replaced! Ljava/lang/StrictMath;.pow(DD)D');
}
, 2);

Clazz.newMeth(C$, 'random', function () {

return Math.random ();
}, 1);

Clazz.newMeth(C$, 'rint$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.rint(D)D');
}
, 2);

Clazz.newMeth(C$, 'round$D', function (d) {

return Math.round (d);
}, 1);

Clazz.newMeth(C$, 'round$F', function (f) {

return Math.round (f);
}, 1);

Clazz.newMeth(C$, 'signum$D', function (d) {
if (Double.isNaN(d)) {
return NaN;
}var sig = d;
if (d > 0 ) {
sig = 1.0;
} else if (d < 0 ) {
sig = -1.0;
}return sig;
}, 1);

Clazz.newMeth(C$, 'signum$F', function (f) {
if (Float.isNaN(f)) {
return NaN;
}var sig = f;
if (f > 0 ) {
sig = 1.0;
} else if (f < 0 ) {
sig = -1.0;
}return sig;
}, 1);

Clazz.newMeth(C$, 'sinh$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.sinh(D)D');
}
, 2);

Clazz.newMeth(C$, 'sin$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.sin(D)D');
}
, 2);

Clazz.newMeth(C$, 'sqrt$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.sqrt(D)D');
}
, 2);

Clazz.newMeth(C$, 'tan$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.tan(D)D');
}
, 2);

Clazz.newMeth(C$, 'tanh$D', function (d) {
alert('native method must be replaced! Ljava/lang/StrictMath;.tanh(D)D');
}
, 2);

Clazz.newMeth(C$, 'toDegrees$D', function (angrad) {
return angrad * 180.0 / 3.141592653589793;
}, 1);

Clazz.newMeth(C$, 'toRadians$D', function (angdeg) {
return angdeg / 180.0 * 3.141592653589793;
}, 1);

Clazz.newMeth(C$, 'nextafter$D$D', function (x, y) {
alert('native method must be replaced! Ljava/lang/StrictMath;.nextafter(DD)D');
}
, 2);

Clazz.newMeth(C$, 'nextafterf$F$F', function (x, y) {
alert('native method must be replaced! Ljava/lang/StrictMath;.nextafterf(FF)F');
}
, 2);
})();
//Created 2018-02-06 08:58:37
