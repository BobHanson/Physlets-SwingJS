(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "Random", null, null, 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.haveNextNextGaussian = false;
this.seed = 0;
this.nextNextGaussian = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.haveNextNextGaussian = false;
this.nextNextGaussian = 0;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.setSeed$J(System.currentTimeMillis());
}, 1);

Clazz.newMeth(C$, 'c$$J', function (seed) {
C$.$init$.apply(this);
this.setSeed$J(seed);
}, 1);

Clazz.newMeth(C$, 'next$I', function (bits) {
this.seed = (this.seed * 25214903917 + 11) & 281474976710655;
return ((this.seed >>> (48 - bits))|0);
});

Clazz.newMeth(C$, 'nextBoolean', function () {

return Math.random () > 0.5;
});

Clazz.newMeth(C$, 'nextBytes$BA', function (buf) {

for (var i = 0; i < bytes.length; i++) { bytes[i] = Math.round (0x100 * Math.random ());
}
});

Clazz.newMeth(C$, 'nextDouble', function () {

return Math.random ();
});

Clazz.newMeth(C$, 'nextFloat', function () {

return Math.random ();
});

Clazz.newMeth(C$, 'nextGaussian', function () {
if (this.haveNextNextGaussian) {
this.haveNextNextGaussian = false;
return this.nextNextGaussian;
}var v1;
var v2;
var s;
do {
v1 = 2 * this.nextDouble() - 1;
v2 = 2 * this.nextDouble() - 1;
s = v1 * v1 + v2 * v2;
} while (s >= 1 );
var norm = Math.sqrt(-2 * Math.log(s) / s);
this.nextNextGaussian = v2 * norm;
this.haveNextNextGaussian = true;
return v1 * norm;
});

Clazz.newMeth(C$, 'nextInt', function () {

return Math.ceil (0xffff * Math.random ()) - 0x8000;
});

Clazz.newMeth(C$, 'nextInt$I', function (n) {
if (n > 0) {
if ((n & -n) == n) {
return (((n * this.next$I(31)) >> 31)|0);
}var bits;
var val;
do {
bits = this.next$I(31);
val = bits % n;
} while (bits - val + (n - 1) < 0);
return val;
}throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
});

Clazz.newMeth(C$, 'nextLong', function () {

return Math.ceil (0xffffffff * Math.random ()) - 0x80000000;
});

Clazz.newMeth(C$, 'setSeed$J', function (seed) {
this.seed = (seed ^ 25214903917) & 281474976710655;
this.haveNextNextGaussian = false;
});
})();
//Created 2018-02-08 10:02:14
