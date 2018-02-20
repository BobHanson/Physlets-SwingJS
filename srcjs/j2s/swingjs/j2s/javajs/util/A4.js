(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['javajs.util.T3']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "A4", null, null, ['javajs.api.JSONEncodable', 'java.io.Serializable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.x = 0;
this.y = 0;
this.z = 0;
this.angle = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.z = 1.0;
}, 1);

Clazz.newMeth(C$, 'new4$F$F$F$F', function (x, y, z, angle) {
var a = Clazz.new_(C$);
a.set4$F$F$F$F(x, y, z, angle);
return a;
}, 1);

Clazz.newMeth(C$, 'newAA$javajs_util_A4', function (a1) {
var a = Clazz.new_(C$);
a.set4$F$F$F$F(a1.x, a1.y, a1.z, a1.angle);
return a;
}, 1);

Clazz.newMeth(C$, 'newVA$javajs_util_V3$F', function (axis, angle) {
var a = Clazz.new_(C$);
a.setVA$javajs_util_V3$F(axis, angle);
return a;
}, 1);

Clazz.newMeth(C$, 'setVA$javajs_util_V3$F', function (axis, angle) {
this.x = axis.x;
this.y = axis.y;
this.z = axis.z;
this.angle = angle;
});

Clazz.newMeth(C$, 'set4$F$F$F$F', function (x, y, z, angle) {
this.x = x;
this.y = y;
this.z = z;
this.angle = angle;
});

Clazz.newMeth(C$, 'setAA$javajs_util_A4', function (a) {
this.x = a.x;
this.y = a.y;
this.z = a.z;
this.angle = a.angle;
});

Clazz.newMeth(C$, 'setM$javajs_util_M3', function (m1) {
p$.setFromMat$D$D$D$D$D$D$D$D$D.apply(this, [m1.m00, m1.m01, m1.m02, m1.m10, m1.m11, m1.m12, m1.m20, m1.m21, m1.m22]);
});

Clazz.newMeth(C$, 'setFromMat$D$D$D$D$D$D$D$D$D', function (m00, m01, m02, m10, m11, m12, m20, m21, m22) {
var cos = (m00 + m11 + m22  - 1.0) * 0.5;
this.x = (m21 - m12);
this.y = (m02 - m20);
this.z = (m10 - m01);
var sin = 0.5 * Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
if (sin == 0  && cos == 1  ) {
this.x = this.y = 0;
this.z = 1;
this.angle = 0;
} else {
this.angle = Math.atan2(sin, cos);
}});

Clazz.newMeth(C$, 'hashCode', function () {
return (I$[1]||$incl$(1)).floatToIntBits$F(this.x) ^ (I$[1]||$incl$(1)).floatToIntBits$F(this.y) ^ (I$[1]||$incl$(1)).floatToIntBits$F(this.z) ^ (I$[1]||$incl$(1)).floatToIntBits$F(this.angle) ;
});

Clazz.newMeth(C$, 'equals$O', function (o) {
if (!(Clazz.instanceOf(o, "javajs.util.A4"))) return false;
var a1 = o;
return this.x == a1.x  && this.y == a1.y   && this.z == a1.z   && this.angle == a1.angle  ;
});

Clazz.newMeth(C$, 'toString', function () {
return "(" + new Float(this.x).toString() + ", " + new Float(this.y).toString() + ", " + new Float(this.z).toString() + ", " + new Float(this.angle).toString() + ")" ;
});

Clazz.newMeth(C$, 'toJSON', function () {
return "[" + new Float(this.x).toString() + "," + new Float(this.y).toString() + "," + new Float(this.z).toString() + "," + new Float((this.angle * 180.0 / 3.141592653589793)).toString() + "]" ;
});
})();
//Created 2018-02-08 10:02:18