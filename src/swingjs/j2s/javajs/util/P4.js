(function(){var P$=Clazz.newPackage("javajs.util"),I$=[];
var C$=Clazz.newClass(P$, "P4", null, 'javajs.util.T4');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'new4$F$F$F$F', function (x, y, z, w) {
var pt = Clazz.new_(C$);
pt.set4$F$F$F$F(x, y, z, w);
return pt;
}, 1);

Clazz.newMeth(C$, 'newPt$javajs_util_P4', function (value) {
var pt = Clazz.new_(C$);
pt.set4$F$F$F$F(value.x, value.y, value.z, value.w);
return pt;
}, 1);

Clazz.newMeth(C$, 'distance4$javajs_util_P4', function (p1) {
var dx = this.x - p1.x;
var dy = this.y - p1.y;
var dz = this.z - p1.z;
var dw = this.w - p1.w;
return Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
});
})();
//Created 2018-05-15 01:02:19
