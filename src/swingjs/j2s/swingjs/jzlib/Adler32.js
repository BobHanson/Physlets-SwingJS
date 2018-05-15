(function(){var P$=Clazz.newPackage("swingjs.jzlib"),I$=[];
var C$=Clazz.newClass(P$, "Adler32", null, null, 'swingjs.jzlib.Checksum');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.s1 = 0;
this.s2 = 0;
this.b1 = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.s1 = 1;
this.s2 = 0;
this.b1 = Clazz.array(Byte.TYPE, [1]);
}, 1);

Clazz.newMeth(C$, 'resetLong$J', function (init) {
this.s1 = init & 65535;
this.s2 = (init >> 16) & 65535;
});

Clazz.newMeth(C$, 'reset', function () {
this.s1 = 1;
this.s2 = 0;
});

Clazz.newMeth(C$, 'getValue', function () {
return ((this.s2 << 16) | this.s1);
});

Clazz.newMeth(C$, 'update$BA$I$I', function (buf, index, len) {
if (len == 1) {
this.s1 = this.s1+(buf[index++] & 255);
this.s2 = this.s2+(this.s1);
this.s1 = this.s1%(65521);
this.s2 = this.s2%(65521);
return;
}var len1 = (len/5552|0);
var len2 = len % 5552;
while (len1-- > 0){
var k = 5552;
len = len-(k);
while (k-- > 0){
this.s1 = this.s1+(buf[index++] & 255);
this.s2 = this.s2+(this.s1);
}
this.s1 = this.s1%(65521);
this.s2 = this.s2%(65521);
}
var k = len2;
len = len-(k);
while (k-- > 0){
this.s1 = this.s1+(buf[index++] & 255);
this.s2 = this.s2+(this.s1);
}
this.s1 = this.s1%(65521);
this.s2 = this.s2%(65521);
});

Clazz.newMeth(C$, 'updateByteAsInt$I', function (b) {
this.b1[0] = ((b|0)|0);
this.update$BA$I$I(this.b1, 0, 1);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:18
