(function(){var P$=java.util,I$=[['java.util.Arrays','java.lang.reflect.Array',['java.util.Arrays','.ArrayList'],'java.lang.StringBuilder','java.util.HashSet']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Arrays", function(){
Clazz.newInstance(this, arguments,0,C$);
});
var p$=C$.prototype;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'sort$JA', function (a) {
C$.sort1$JA$I$I(a, 0, a.length);
}, 1);

Clazz.newMeth(C$, 'sort$JA$I$I', function (a, fromIndex, toIndex) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
C$.sort1$JA$I$I(a, fromIndex, toIndex - fromIndex);
}, 1);

Clazz.newMeth(C$, 'sort$IA', function (a) {
C$.sort1$IA$I$I(a, 0, a.length);
}, 1);

Clazz.newMeth(C$, 'sort$IA$I$I', function (a, fromIndex, toIndex) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
C$.sort1$IA$I$I(a, fromIndex, toIndex - fromIndex);
}, 1);

Clazz.newMeth(C$, 'sort$HA', function (a) {
C$.sort1$HA$I$I(a, 0, a.length);
}, 1);

Clazz.newMeth(C$, 'sort$HA$I$I', function (a, fromIndex, toIndex) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
C$.sort1$HA$I$I(a, fromIndex, toIndex - fromIndex);
}, 1);

Clazz.newMeth(C$, 'sort$CA', function (a) {
C$.sort1$CA$I$I(a, 0, a.length);
}, 1);

Clazz.newMeth(C$, 'sort$CA$I$I', function (a, fromIndex, toIndex) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
C$.sort1$CA$I$I(a, fromIndex, toIndex - fromIndex);
}, 1);

Clazz.newMeth(C$, 'sort$BA', function (a) {
C$.sort1$BA$I$I(a, 0, a.length);
}, 1);

Clazz.newMeth(C$, 'sort$BA$I$I', function (a, fromIndex, toIndex) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
C$.sort1$BA$I$I(a, fromIndex, toIndex - fromIndex);
}, 1);

Clazz.newMeth(C$, 'sort$DA', function (a) {
C$.sort2$DA$I$I(a, 0, a.length);
}, 1);

Clazz.newMeth(C$, 'sort$DA$I$I', function (a, fromIndex, toIndex) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
C$.sort2$DA$I$I(a, fromIndex, toIndex);
}, 1);

Clazz.newMeth(C$, 'sort$FA', function (a) {
C$.sort2$FA$I$I(a, 0, a.length);
}, 1);

Clazz.newMeth(C$, 'sort$FA$I$I', function (a, fromIndex, toIndex) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
C$.sort2$FA$I$I(a, fromIndex, toIndex);
}, 1);

Clazz.newMeth(C$, 'sort2$DA$I$I', function (a, fromIndex, toIndex) {
var NEG_ZERO_BITS = Double.doubleToLongBits(-0.0);
var numNegZeros = 0;
var i = fromIndex;
var n = toIndex;
while (i < n){
if (a[i] != a[i] ) {
C$.swap$DA$I$I(a, i, --n);
} else {
if (a[i] == 0  && Double.doubleToLongBits(a[i]) == NEG_ZERO_BITS ) {
a[i] = 0.0;
numNegZeros++;
}i++;
}}
C$.sort1$DA$I$I(a, fromIndex, n - fromIndex);
if (numNegZeros != 0) {
var j = C$.binarySearch0$DA$I$I$D(a, fromIndex, n, 0.0);
do {
j--;
} while (j >= fromIndex && a[j] == 0.0  );
for (var k = 0; k < numNegZeros; k++) a[++j] = -0.0;

}}, 1);

Clazz.newMeth(C$, 'sort2$FA$I$I', function (a, fromIndex, toIndex) {
var NEG_ZERO_BITS = Float.floatToIntBits(-0.0);
var numNegZeros = 0;
var i = fromIndex;
var n = toIndex;
while (i < n){
if (a[i] != a[i] ) {
C$.swap$FA$I$I(a, i, --n);
} else {
if (a[i] == 0  && Float.floatToIntBits(a[i]) == NEG_ZERO_BITS ) {
a[i] = 0.0;
numNegZeros++;
}i++;
}}
C$.sort1$FA$I$I(a, fromIndex, n - fromIndex);
if (numNegZeros != 0) {
var j = C$.binarySearch0$FA$I$I$F(a, fromIndex, n, 0.0);
do {
j--;
} while (j >= fromIndex && a[j] == 0.0  );
for (var k = 0; k < numNegZeros; k++) a[++j] = -0.0;

}}, 1);

Clazz.newMeth(C$, 'sort1$JA$I$I', function (x, off, len) {
if (len < 7) {
for (var i = off; i < len + off; i++) for (var j = i; j > off && x[j - 1] > x[j] ; j--) C$.swap$JA$I$I(x, j, j - 1);


return;
}var m = off + (len >> 1);
if (len > 7) {
var l = off;
var n = off + len - 1;
if (len > 40) {
var s = (len/8|0);
l = C$.med3$JA$I$I$I(x, l, l + s, l + 2 * s);
m = C$.med3$JA$I$I$I(x, m - s, m, m + s);
n = C$.med3$JA$I$I$I(x, n - 2 * s, n - s, n);
}m = C$.med3$JA$I$I$I(x, l, m, n);
}var v = x[m];
var a = off;
var b = a;
var c = off + len - 1;
var d = c;
while (true){
while (b <= c && x[b] <= v ){
if (x[b] == v) C$.swap$JA$I$I(x, a++, b);
b++;
}
while (c >= b && x[c] >= v ){
if (x[c] == v) C$.swap$JA$I$I(x, c, d--);
c--;
}
if (b > c) break;
C$.swap$JA$I$I(x, b++, c--);
}
var s;
var n = off + len;
s = Math.min(a - off, b - a);
C$.vecswap$JA$I$I$I(x, off, b - s, s);
s = Math.min(d - c, n - d - 1 );
C$.vecswap$JA$I$I$I(x, b, n - s, s);
if ((s = b - a) > 1) C$.sort1$JA$I$I(x, off, s);
if ((s = d - c) > 1) C$.sort1$JA$I$I(x, n - s, s);
}, 1);

Clazz.newMeth(C$, 'swap$JA$I$I', function (x, a, b) {
var t = x[a];
x[a] = x[b];
x[b] = t;
}, 1);

Clazz.newMeth(C$, 'vecswap$JA$I$I$I', function (x, a, b, n) {
for (var i = 0; i < n; i++, a++, b++) C$.swap$JA$I$I(x, a, b);

}, 1);

Clazz.newMeth(C$, 'med3$JA$I$I$I', function (x, a, b, c) {
return (x[a] < x[b] ? (x[b] < x[c] ? b : x[a] < x[c] ? c : a) : (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
}, 1);

Clazz.newMeth(C$, 'sort1$IA$I$I', function (x, off, len) {
if (len < 7) {
for (var i = off; i < len + off; i++) for (var j = i; j > off && x[j - 1] > x[j] ; j--) C$.swap$IA$I$I(x, j, j - 1);


return;
}var m = off + (len >> 1);
if (len > 7) {
var l = off;
var n = off + len - 1;
if (len > 40) {
var s = (len/8|0);
l = C$.med3$IA$I$I$I(x, l, l + s, l + 2 * s);
m = C$.med3$IA$I$I$I(x, m - s, m, m + s);
n = C$.med3$IA$I$I$I(x, n - 2 * s, n - s, n);
}m = C$.med3$IA$I$I$I(x, l, m, n);
}var v = x[m];
var a = off;
var b = a;
var c = off + len - 1;
var d = c;
while (true){
while (b <= c && x[b] <= v ){
if (x[b] == v) C$.swap$IA$I$I(x, a++, b);
b++;
}
while (c >= b && x[c] >= v ){
if (x[c] == v) C$.swap$IA$I$I(x, c, d--);
c--;
}
if (b > c) break;
C$.swap$IA$I$I(x, b++, c--);
}
var s;
var n = off + len;
s = Math.min(a - off, b - a);
C$.vecswap$IA$I$I$I(x, off, b - s, s);
s = Math.min(d - c, n - d - 1 );
C$.vecswap$IA$I$I$I(x, b, n - s, s);
if ((s = b - a) > 1) C$.sort1$IA$I$I(x, off, s);
if ((s = d - c) > 1) C$.sort1$IA$I$I(x, n - s, s);
}, 1);

Clazz.newMeth(C$, 'swap$IA$I$I', function (x, a, b) {
var t = x[a];
x[a] = x[b];
x[b] = t;
}, 1);

Clazz.newMeth(C$, 'vecswap$IA$I$I$I', function (x, a, b, n) {
for (var i = 0; i < n; i++, a++, b++) C$.swap$IA$I$I(x, a, b);

}, 1);

Clazz.newMeth(C$, 'med3$IA$I$I$I', function (x, a, b, c) {
return (x[a] < x[b] ? (x[b] < x[c] ? b : x[a] < x[c] ? c : a) : (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
}, 1);

Clazz.newMeth(C$, 'sort1$HA$I$I', function (x, off, len) {
if (len < 7) {
for (var i = off; i < len + off; i++) for (var j = i; j > off && x[j - 1] > x[j] ; j--) C$.swap$HA$I$I(x, j, j - 1);


return;
}var m = off + (len >> 1);
if (len > 7) {
var l = off;
var n = off + len - 1;
if (len > 40) {
var s = (len/8|0);
l = C$.med3$HA$I$I$I(x, l, l + s, l + 2 * s);
m = C$.med3$HA$I$I$I(x, m - s, m, m + s);
n = C$.med3$HA$I$I$I(x, n - 2 * s, n - s, n);
}m = C$.med3$HA$I$I$I(x, l, m, n);
}var v = x[m];
var a = off;
var b = a;
var c = off + len - 1;
var d = c;
while (true){
while (b <= c && x[b] <= v ){
if (x[b] == v) C$.swap$HA$I$I(x, a++, b);
b++;
}
while (c >= b && x[c] >= v ){
if (x[c] == v) C$.swap$HA$I$I(x, c, d--);
c--;
}
if (b > c) break;
C$.swap$HA$I$I(x, b++, c--);
}
var s;
var n = off + len;
s = Math.min(a - off, b - a);
C$.vecswap$HA$I$I$I(x, off, b - s, s);
s = Math.min(d - c, n - d - 1 );
C$.vecswap$HA$I$I$I(x, b, n - s, s);
if ((s = b - a) > 1) C$.sort1$HA$I$I(x, off, s);
if ((s = d - c) > 1) C$.sort1$HA$I$I(x, n - s, s);
}, 1);

Clazz.newMeth(C$, 'swap$HA$I$I', function (x, a, b) {
var t = x[a];
x[a] = x[b];
x[b] = t;
}, 1);

Clazz.newMeth(C$, 'vecswap$HA$I$I$I', function (x, a, b, n) {
for (var i = 0; i < n; i++, a++, b++) C$.swap$HA$I$I(x, a, b);

}, 1);

Clazz.newMeth(C$, 'med3$HA$I$I$I', function (x, a, b, c) {
return (x[a] < x[b] ? (x[b] < x[c] ? b : x[a] < x[c] ? c : a) : (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
}, 1);

Clazz.newMeth(C$, 'sort1$CA$I$I', function (x, off, len) {
if (len < 7) {
for (var i = off; i < len + off; i++) for (var j = i; j > off && x[j - 1] > x[j] ; j--) C$.swap$CA$I$I(x, j, j - 1);


return;
}var m = off + (len >> 1);
if (len > 7) {
var l = off;
var n = off + len - 1;
if (len > 40) {
var s = (len/8|0);
l = C$.med3$CA$I$I$I(x, l, l + s, l + 2 * s);
m = C$.med3$CA$I$I$I(x, m - s, m, m + s);
n = C$.med3$CA$I$I$I(x, n - 2 * s, n - s, n);
}m = C$.med3$CA$I$I$I(x, l, m, n);
}var v = x[m];
var a = off;
var b = a;
var c = off + len - 1;
var d = c;
while (true){
while (b <= c && x[b] <= v ){
if (x[b] == v) C$.swap$CA$I$I(x, a++, b);
b++;
}
while (c >= b && x[c] >= v ){
if (x[c] == v) C$.swap$CA$I$I(x, c, d--);
c--;
}
if (b > c) break;
C$.swap$CA$I$I(x, b++, c--);
}
var s;
var n = off + len;
s = Math.min(a - off, b - a);
C$.vecswap$CA$I$I$I(x, off, b - s, s);
s = Math.min(d - c, n - d - 1 );
C$.vecswap$CA$I$I$I(x, b, n - s, s);
if ((s = b - a) > 1) C$.sort1$CA$I$I(x, off, s);
if ((s = d - c) > 1) C$.sort1$CA$I$I(x, n - s, s);
}, 1);

Clazz.newMeth(C$, 'swap$CA$I$I', function (x, a, b) {
var t = x[a];
x[a] = x[b];
x[b] = t;
}, 1);

Clazz.newMeth(C$, 'vecswap$CA$I$I$I', function (x, a, b, n) {
for (var i = 0; i < n; i++, a++, b++) C$.swap$CA$I$I(x, a, b);

}, 1);

Clazz.newMeth(C$, 'med3$CA$I$I$I', function (x, a, b, c) {
return (x[a] < x[b] ? (x[b] < x[c] ? b : x[a] < x[c] ? c : a) : (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
}, 1);

Clazz.newMeth(C$, 'sort1$BA$I$I', function (x, off, len) {
if (len < 7) {
for (var i = off; i < len + off; i++) for (var j = i; j > off && x[j - 1] > x[j] ; j--) C$.swap$BA$I$I(x, j, j - 1);


return;
}var m = off + (len >> 1);
if (len > 7) {
var l = off;
var n = off + len - 1;
if (len > 40) {
var s = (len/8|0);
l = C$.med3$BA$I$I$I(x, l, l + s, l + 2 * s);
m = C$.med3$BA$I$I$I(x, m - s, m, m + s);
n = C$.med3$BA$I$I$I(x, n - 2 * s, n - s, n);
}m = C$.med3$BA$I$I$I(x, l, m, n);
}var v = ($b$[0] = x[m], $b$[0]);
var a = off;
var b = a;
var c = off + len - 1;
var d = c;
while (true){
while (b <= c && x[b] <= v ){
if (x[b] == v) C$.swap$BA$I$I(x, a++, b);
b++;
}
while (c >= b && x[c] >= v ){
if (x[c] == v) C$.swap$BA$I$I(x, c, d--);
c--;
}
if (b > c) break;
C$.swap$BA$I$I(x, b++, c--);
}
var s;
var n = off + len;
s = Math.min(a - off, b - a);
C$.vecswap$BA$I$I$I(x, off, b - s, s);
s = Math.min(d - c, n - d - 1 );
C$.vecswap$BA$I$I$I(x, b, n - s, s);
if ((s = b - a) > 1) C$.sort1$BA$I$I(x, off, s);
if ((s = d - c) > 1) C$.sort1$BA$I$I(x, n - s, s);
}, 1);

Clazz.newMeth(C$, 'swap$BA$I$I', function (x, a, b) {
var t = ($b$[0] = x[a], $b$[0]);
x[a] = (x[b]|0);
x[b] = (t|0);
}, 1);

Clazz.newMeth(C$, 'vecswap$BA$I$I$I', function (x, a, b, n) {
for (var i = 0; i < n; i++, a++, b++) C$.swap$BA$I$I(x, a, b);

}, 1);

Clazz.newMeth(C$, 'med3$BA$I$I$I', function (x, a, b, c) {
return (x[a] < x[b] ? (x[b] < x[c] ? b : x[a] < x[c] ? c : a) : (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
}, 1);

Clazz.newMeth(C$, 'sort1$DA$I$I', function (x, off, len) {
if (len < 7) {
for (var i = off; i < len + off; i++) for (var j = i; j > off && x[j - 1] > x[j]  ; j--) C$.swap$DA$I$I(x, j, j - 1);


return;
}var m = off + (len >> 1);
if (len > 7) {
var l = off;
var n = off + len - 1;
if (len > 40) {
var s = (len/8|0);
l = C$.med3$DA$I$I$I(x, l, l + s, l + 2 * s);
m = C$.med3$DA$I$I$I(x, m - s, m, m + s);
n = C$.med3$DA$I$I$I(x, n - 2 * s, n - s, n);
}m = C$.med3$DA$I$I$I(x, l, m, n);
}var v = x[m];
var a = off;
var b = a;
var c = off + len - 1;
var d = c;
while (true){
while (b <= c && x[b] <= v  ){
if (x[b] == v ) C$.swap$DA$I$I(x, a++, b);
b++;
}
while (c >= b && x[c] >= v  ){
if (x[c] == v ) C$.swap$DA$I$I(x, c, d--);
c--;
}
if (b > c) break;
C$.swap$DA$I$I(x, b++, c--);
}
var s;
var n = off + len;
s = Math.min(a - off, b - a);
C$.vecswap$DA$I$I$I(x, off, b - s, s);
s = Math.min(d - c, n - d - 1 );
C$.vecswap$DA$I$I$I(x, b, n - s, s);
if ((s = b - a) > 1) C$.sort1$DA$I$I(x, off, s);
if ((s = d - c) > 1) C$.sort1$DA$I$I(x, n - s, s);
}, 1);

Clazz.newMeth(C$, 'swap$DA$I$I', function (x, a, b) {
var t = x[a];
x[a] = x[b];
x[b] = t;
}, 1);

Clazz.newMeth(C$, 'vecswap$DA$I$I$I', function (x, a, b, n) {
for (var i = 0; i < n; i++, a++, b++) C$.swap$DA$I$I(x, a, b);

}, 1);

Clazz.newMeth(C$, 'med3$DA$I$I$I', function (x, a, b, c) {
return (x[a] < x[b]  ? (x[b] < x[c]  ? b : x[a] < x[c]  ? c : a) : (x[b] > x[c]  ? b : x[a] > x[c]  ? c : a));
}, 1);

Clazz.newMeth(C$, 'sort1$FA$I$I', function (x, off, len) {
if (len < 7) {
for (var i = off; i < len + off; i++) for (var j = i; j > off && x[j - 1] > x[j]  ; j--) C$.swap$FA$I$I(x, j, j - 1);


return;
}var m = off + (len >> 1);
if (len > 7) {
var l = off;
var n = off + len - 1;
if (len > 40) {
var s = (len/8|0);
l = C$.med3$FA$I$I$I(x, l, l + s, l + 2 * s);
m = C$.med3$FA$I$I$I(x, m - s, m, m + s);
n = C$.med3$FA$I$I$I(x, n - 2 * s, n - s, n);
}m = C$.med3$FA$I$I$I(x, l, m, n);
}var v = x[m];
var a = off;
var b = a;
var c = off + len - 1;
var d = c;
while (true){
while (b <= c && x[b] <= v  ){
if (x[b] == v ) C$.swap$FA$I$I(x, a++, b);
b++;
}
while (c >= b && x[c] >= v  ){
if (x[c] == v ) C$.swap$FA$I$I(x, c, d--);
c--;
}
if (b > c) break;
C$.swap$FA$I$I(x, b++, c--);
}
var s;
var n = off + len;
s = Math.min(a - off, b - a);
C$.vecswap$FA$I$I$I(x, off, b - s, s);
s = Math.min(d - c, n - d - 1 );
C$.vecswap$FA$I$I$I(x, b, n - s, s);
if ((s = b - a) > 1) C$.sort1$FA$I$I(x, off, s);
if ((s = d - c) > 1) C$.sort1$FA$I$I(x, n - s, s);
}, 1);

Clazz.newMeth(C$, 'swap$FA$I$I', function (x, a, b) {
var t = x[a];
x[a] = x[b];
x[b] = t;
}, 1);

Clazz.newMeth(C$, 'vecswap$FA$I$I$I', function (x, a, b, n) {
for (var i = 0; i < n; i++, a++, b++) C$.swap$FA$I$I(x, a, b);

}, 1);

Clazz.newMeth(C$, 'med3$FA$I$I$I', function (x, a, b, c) {
return (x[a] < x[b]  ? (x[b] < x[c]  ? b : x[a] < x[c]  ? c : a) : (x[b] > x[c]  ? b : x[a] > x[c]  ? c : a));
}, 1);

Clazz.newMeth(C$, 'sort$OA', function (a) {
var aux = a.clone();
C$.mergeSort$OA$OA$I$I$I(aux, a, 0, a.length, 0);
}, 1);

Clazz.newMeth(C$, 'sort$OA$I$I', function (a, fromIndex, toIndex) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
var aux = C$.copyOfRange$TTA$I$I(a, fromIndex, toIndex);
C$.mergeSort$OA$OA$I$I$I(aux, a, fromIndex, toIndex, -fromIndex);
}, 1);

Clazz.newMeth(C$, 'mergeSort$OA$OA$I$I$I', function (src, dest, low, high, off) {
var length = high - low;
if (length < 7) {
for (var i = low; i < high; i++) for (var j = i; j > low && (dest[j - 1]).compareTo$TT(dest[j]) > 0 ; j--) C$.swap$OA$I$I(dest, j, j - 1);


return;
}var destLow = low;
var destHigh = high;
low = low+(off);
high = high+(off);
var mid = (low + high) >>> 1;
C$.mergeSort$OA$OA$I$I$I(dest, src, low, mid, -off);
C$.mergeSort$OA$OA$I$I$I(dest, src, mid, high, -off);
if ((src[mid - 1]).compareTo$TT(src[mid]) <= 0) {
System.arraycopy(src, low, dest, destLow, length);
return;
}for (var i = destLow, p = low, q = mid; i < destHigh; i++) {
if (q >= high || p < mid && (src[p]).compareTo$TT(src[q]) <= 0  ) dest[i] = src[p++];
 else dest[i] = src[q++];
}
}, 1);

Clazz.newMeth(C$, 'swap$OA$I$I', function (x, a, b) {
var t = x[a];
x[a] = x[b];
x[b] = t;
}, 1);

Clazz.newMeth(C$, 'sort$TTA$java_util_Comparator', function (a, c) {
var aux = a.clone();
if (c == null ) C$.mergeSort$OA$OA$I$I$I(aux, a, 0, a.length, 0);
 else C$.mergeSort$OA$OA$I$I$I$java_util_Comparator(aux, a, 0, a.length, 0, c);
}, 1);

Clazz.newMeth(C$, 'sort$TTA$I$I$java_util_Comparator', function (a, fromIndex, toIndex, c) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
var aux = C$.copyOfRange$TTA$I$I(a, fromIndex, toIndex);
if (c == null ) C$.mergeSort$OA$OA$I$I$I(aux, a, fromIndex, toIndex, -fromIndex);
 else C$.mergeSort$OA$OA$I$I$I$java_util_Comparator(aux, a, fromIndex, toIndex, -fromIndex, c);
}, 1);

Clazz.newMeth(C$, 'mergeSort$OA$OA$I$I$I$java_util_Comparator', function (src, dest, low, high, off, c) {
var length = high - low;
if (length < 7) {
for (var i = low; i < high; i++) for (var j = i; j > low && c.compare$TT$TT(dest[j - 1], dest[j]) > 0 ; j--) C$.swap$OA$I$I(dest, j, j - 1);


return;
}var destLow = low;
var destHigh = high;
low = low+(off);
high = high+(off);
var mid = (low + high) >>> 1;
C$.mergeSort$OA$OA$I$I$I$java_util_Comparator(dest, src, low, mid, -off, c);
C$.mergeSort$OA$OA$I$I$I$java_util_Comparator(dest, src, mid, high, -off, c);
if (c.compare$TT$TT(src[mid - 1], src[mid]) <= 0) {
System.arraycopy(src, low, dest, destLow, length);
return;
}for (var i = destLow, p = low, q = mid; i < destHigh; i++) {
if (q >= high || p < mid && c.compare$TT$TT(src[p], src[q]) <= 0  ) dest[i] = src[p++];
 else dest[i] = src[q++];
}
}, 1);

Clazz.newMeth(C$, 'rangeCheck$I$I$I', function (arrayLen, fromIndex, toIndex) {
if (fromIndex > toIndex) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")" ]);
if (fromIndex < 0) throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$I,[fromIndex]);
if (toIndex > arrayLen) throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$I,[toIndex]);
}, 1);

Clazz.newMeth(C$, 'binarySearch$JA$J', function (a, key) {
return C$.binarySearch0$JA$I$I$J(a, 0, a.length, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch$JA$I$I$J', function (a, fromIndex, toIndex, key) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
return C$.binarySearch0$JA$I$I$J(a, fromIndex, toIndex, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch0$JA$I$I$J', function (a, fromIndex, toIndex, key) {
var low = fromIndex;
var high = toIndex - 1;
while (low <= high){
var mid = (low + high) >>> 1;
var midVal = a[mid];
if (midVal < key) low = mid + 1;
 else if (midVal > key) high = mid - 1;
 else return mid;
}
return -(low + 1);
}, 1);

Clazz.newMeth(C$, 'binarySearch$IA$I', function (a, key) {
return C$.binarySearch0$IA$I$I$I(a, 0, a.length, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch$IA$I$I$I', function (a, fromIndex, toIndex, key) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
return C$.binarySearch0$IA$I$I$I(a, fromIndex, toIndex, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch0$IA$I$I$I', function (a, fromIndex, toIndex, key) {
var low = fromIndex;
var high = toIndex - 1;
while (low <= high){
var mid = (low + high) >>> 1;
var midVal = a[mid];
if (midVal < key) low = mid + 1;
 else if (midVal > key) high = mid - 1;
 else return mid;
}
return -(low + 1);
}, 1);

Clazz.newMeth(C$, 'binarySearch$HA$H', function (a, key) {
return C$.binarySearch0$HA$I$I$H(a, 0, a.length, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch$HA$I$I$H', function (a, fromIndex, toIndex, key) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
return C$.binarySearch0$HA$I$I$H(a, fromIndex, toIndex, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch0$HA$I$I$H', function (a, fromIndex, toIndex, key) {
var low = fromIndex;
var high = toIndex - 1;
while (low <= high){
var mid = (low + high) >>> 1;
var midVal = a[mid];
if (midVal < key) low = mid + 1;
 else if (midVal > key) high = mid - 1;
 else return mid;
}
return -(low + 1);
}, 1);

Clazz.newMeth(C$, 'binarySearch$CA$C', function (a, key) {
return C$.binarySearch0$CA$I$I$C(a, 0, a.length, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch$CA$I$I$C', function (a, fromIndex, toIndex, key) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
return C$.binarySearch0$CA$I$I$C(a, fromIndex, toIndex, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch0$CA$I$I$C', function (a, fromIndex, toIndex, key) {
var low = fromIndex;
var high = toIndex - 1;
while (low <= high){
var mid = (low + high) >>> 1;
var midVal = a[mid];
if (midVal < key) low = mid + 1;
 else if (midVal > key) high = mid - 1;
 else return mid;
}
return -(low + 1);
}, 1);

Clazz.newMeth(C$, 'binarySearch$BA$B', function (a, key) {
return C$.binarySearch0$BA$I$I$B(a, 0, a.length, ($b$[0] = key, $b$[0]));
}, 1);

Clazz.newMeth(C$, 'binarySearch$BA$I$I$B', function (a, fromIndex, toIndex, key) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
return C$.binarySearch0$BA$I$I$B(a, fromIndex, toIndex, ($b$[0] = key, $b$[0]));
}, 1);

Clazz.newMeth(C$, 'binarySearch0$BA$I$I$B', function (a, fromIndex, toIndex, key) {
var low = fromIndex;
var high = toIndex - 1;
while (low <= high){
var mid = (low + high) >>> 1;
var midVal = ($b$[0] = a[mid], $b$[0]);
if (midVal < key) low = mid + 1;
 else if (midVal > key) high = mid - 1;
 else return mid;
}
return -(low + 1);
}, 1);

Clazz.newMeth(C$, 'binarySearch$DA$D', function (a, key) {
return C$.binarySearch0$DA$I$I$D(a, 0, a.length, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch$DA$I$I$D', function (a, fromIndex, toIndex, key) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
return C$.binarySearch0$DA$I$I$D(a, fromIndex, toIndex, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch0$DA$I$I$D', function (a, fromIndex, toIndex, key) {
var low = fromIndex;
var high = toIndex - 1;
while (low <= high){
var mid = (low + high) >>> 1;
var midVal = a[mid];
if (midVal < key ) low = mid + 1;
 else if (midVal > key ) high = mid - 1;
 else {
var midBits = Double.doubleToLongBits(midVal);
var keyBits = Double.doubleToLongBits(key);
if (midBits == keyBits) return mid;
 else if (midBits < keyBits) low = mid + 1;
 else high = mid - 1;
}}
return -(low + 1);
}, 1);

Clazz.newMeth(C$, 'binarySearch$FA$F', function (a, key) {
return C$.binarySearch0$FA$I$I$F(a, 0, a.length, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch$FA$I$I$F', function (a, fromIndex, toIndex, key) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
return C$.binarySearch0$FA$I$I$F(a, fromIndex, toIndex, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch0$FA$I$I$F', function (a, fromIndex, toIndex, key) {
var low = fromIndex;
var high = toIndex - 1;
while (low <= high){
var mid = (low + high) >>> 1;
var midVal = a[mid];
if (midVal < key ) low = mid + 1;
 else if (midVal > key ) high = mid - 1;
 else {
var midBits = Float.floatToIntBits(midVal);
var keyBits = Float.floatToIntBits(key);
if (midBits == keyBits) return mid;
 else if (midBits < keyBits) low = mid + 1;
 else high = mid - 1;
}}
return -(low + 1);
}, 1);

Clazz.newMeth(C$, 'binarySearch$OA$O', function (a, key) {
return C$.binarySearch0$OA$I$I$O(a, 0, a.length, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch$OA$I$I$O', function (a, fromIndex, toIndex, key) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
return C$.binarySearch0$OA$I$I$O(a, fromIndex, toIndex, key);
}, 1);

Clazz.newMeth(C$, 'binarySearch0$OA$I$I$O', function (a, fromIndex, toIndex, key) {
var low = fromIndex;
var high = toIndex - 1;
while (low <= high){
var mid = (low + high) >>> 1;
var midVal = a[mid];
var cmp = midVal.compareTo$TT(key);
if (cmp < 0) low = mid + 1;
 else if (cmp > 0) high = mid - 1;
 else return mid;
}
return -(low + 1);
}, 1);

Clazz.newMeth(C$, 'binarySearch$TTA$TT$java_util_Comparator', function (a, key, c) {
return C$.binarySearch0$TTA$I$I$TT$java_util_Comparator(a, 0, a.length, key, c);
}, 1);

Clazz.newMeth(C$, 'binarySearch$TTA$I$I$TT$java_util_Comparator', function (a, fromIndex, toIndex, key, c) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
return C$.binarySearch0$TTA$I$I$TT$java_util_Comparator(a, fromIndex, toIndex, key, c);
}, 1);

Clazz.newMeth(C$, 'binarySearch0$TTA$I$I$TT$java_util_Comparator', function (a, fromIndex, toIndex, key, c) {
if (c == null ) {
return C$.binarySearch0$OA$I$I$O(a, fromIndex, toIndex, key);
}var low = fromIndex;
var high = toIndex - 1;
while (low <= high){
var mid = (low + high) >>> 1;
var midVal = a[mid];
var cmp = c.compare$TT$TT(midVal, key);
if (cmp < 0) low = mid + 1;
 else if (cmp > 0) high = mid - 1;
 else return mid;
}
return -(low + 1);
}, 1);

Clazz.newMeth(C$, 'equals$JA$JA', function (a, a2) {
if (a === a2 ) return true;
if (a == null  || a2 == null  ) return false;
var length = a.length;
if (a2.length != length) return false;
for (var i = 0; i < length; i++) if (a[i] != a2[i]) return false;

return true;
}, 1);

Clazz.newMeth(C$, 'equals$IA$IA', function (a, a2) {
if (a === a2 ) return true;
if (a == null  || a2 == null  ) return false;
var length = a.length;
if (a2.length != length) return false;
for (var i = 0; i < length; i++) if (a[i] != a2[i]) return false;

return true;
}, 1);

Clazz.newMeth(C$, 'equals$HA$HA', function (a, a2) {
if (a === a2 ) return true;
if (a == null  || a2 == null  ) return false;
var length = a.length;
if (a2.length != length) return false;
for (var i = 0; i < length; i++) if (a[i] != a2[i]) return false;

return true;
}, 1);

Clazz.newMeth(C$, 'equals$CA$CA', function (a, a2) {
if (a === a2 ) return true;
if (a == null  || a2 == null  ) return false;
var length = a.length;
if (a2.length != length) return false;
for (var i = 0; i < length; i++) if (a[i] != a2[i]) return false;

return true;
}, 1);

Clazz.newMeth(C$, 'equals$BA$BA', function (a, a2) {
if (a === a2 ) return true;
if (a == null  || a2 == null  ) return false;
var length = a.length;
if (a2.length != length) return false;
for (var i = 0; i < length; i++) if (a[i] != a2[i]) return false;

return true;
}, 1);

Clazz.newMeth(C$, 'equals$ZA$ZA', function (a, a2) {
if (a === a2 ) return true;
if (a == null  || a2 == null  ) return false;
var length = a.length;
if (a2.length != length) return false;
for (var i = 0; i < length; i++) if (a[i] != a2[i] ) return false;

return true;
}, 1);

Clazz.newMeth(C$, 'equals$DA$DA', function (a, a2) {
if (a === a2 ) return true;
if (a == null  || a2 == null  ) return false;
var length = a.length;
if (a2.length != length) return false;
for (var i = 0; i < length; i++) if (Double.doubleToLongBits(a[i]) != Double.doubleToLongBits(a2[i])) return false;

return true;
}, 1);

Clazz.newMeth(C$, 'equals$FA$FA', function (a, a2) {
if (a === a2 ) return true;
if (a == null  || a2 == null  ) return false;
var length = a.length;
if (a2.length != length) return false;
for (var i = 0; i < length; i++) if (Float.floatToIntBits(a[i]) != Float.floatToIntBits(a2[i])) return false;

return true;
}, 1);

Clazz.newMeth(C$, 'equals$OA$OA', function (a, a2) {
if (a === a2 ) return true;
if (a == null  || a2 == null  ) return false;
var length = a.length;
if (a2.length != length) return false;
for (var i = 0; i < length; i++) {
var o1 = a[i];
var o2 = a2[i];
if (!(o1 == null  ? o2 == null  : o1.equals$O(o2))) return false;
}
return true;
}, 1);

Clazz.newMeth(C$, 'fill$JA$J', function (a, val) {
for (var i = 0, len = a.length; i < len; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$JA$I$I$J', function (a, fromIndex, toIndex, val) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
for (var i = fromIndex; i < toIndex; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$IA$I', function (a, val) {
for (var i = 0, len = a.length; i < len; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$IA$I$I$I', function (a, fromIndex, toIndex, val) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
for (var i = fromIndex; i < toIndex; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$HA$H', function (a, val) {
for (var i = 0, len = a.length; i < len; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$HA$I$I$H', function (a, fromIndex, toIndex, val) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
for (var i = fromIndex; i < toIndex; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$CA$C', function (a, val) {
for (var i = 0, len = a.length; i < len; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$CA$I$I$C', function (a, fromIndex, toIndex, val) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
for (var i = fromIndex; i < toIndex; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$BA$B', function (a, val) {
for (var i = 0, len = a.length; i < len; i++) a[i] = (val|0);

}, 1);

Clazz.newMeth(C$, 'fill$BA$I$I$B', function (a, fromIndex, toIndex, val) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
for (var i = fromIndex; i < toIndex; i++) a[i] = (val|0);

}, 1);

Clazz.newMeth(C$, 'fill$ZA$Z', function (a, val) {
for (var i = 0, len = a.length; i < len; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$ZA$I$I$Z', function (a, fromIndex, toIndex, val) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
for (var i = fromIndex; i < toIndex; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$DA$D', function (a, val) {
for (var i = 0, len = a.length; i < len; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$DA$I$I$D', function (a, fromIndex, toIndex, val) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
for (var i = fromIndex; i < toIndex; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$FA$F', function (a, val) {
for (var i = 0, len = a.length; i < len; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$FA$I$I$F', function (a, fromIndex, toIndex, val) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
for (var i = fromIndex; i < toIndex; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$OA$O', function (a, val) {
for (var i = 0, len = a.length; i < len; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'fill$OA$I$I$O', function (a, fromIndex, toIndex, val) {
C$.rangeCheck$I$I$I(a.length, fromIndex, toIndex);
for (var i = fromIndex; i < toIndex; i++) a[i] = val;

}, 1);

Clazz.newMeth(C$, 'copyOf$TTA$I', function (original, newLength) {
return C$.copyOf$TUA$I$Class(original, newLength, original.getClass());
}, 1);

Clazz.newMeth(C$, 'copyOf$TUA$I$Class', function (original, newLength, newType) {
var copy = (newType === Clazz.array(java.lang.Object, -1) ) ? Clazz.array(java.lang.Object, [newLength]) : Clazz.array(newType.getComponentType(), newLength);
System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOf$BA$I', function (original, newLength) {
var copy = Clazz.array(Byte.TYPE, [newLength]);
System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOf$HA$I', function (original, newLength) {
var copy = Clazz.array(Short.TYPE, [newLength]);
System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOf$IA$I', function (original, newLength) {
var copy = Clazz.array(Integer.TYPE, [newLength]);
System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOf$JA$I', function (original, newLength) {
var copy = Clazz.array(Long.TYPE, [newLength]);
System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOf$CA$I', function (original, newLength) {
var copy = Clazz.array(Character.TYPE, [newLength]);
System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOf$FA$I', function (original, newLength) {
var copy = Clazz.array(Float.TYPE, [newLength]);
System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOf$DA$I', function (original, newLength) {
var copy = Clazz.array(Double.TYPE, [newLength]);
System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOf$ZA$I', function (original, newLength) {
var copy = Clazz.array(Boolean.TYPE, [newLength]);
System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOfRange$TTA$I$I', function (original, from, to) {
return C$.copyOfRange$TUA$I$I$Class(original, from, to, original.getClass());
}, 1);

Clazz.newMeth(C$, 'copyOfRange$TUA$I$I$Class', function (original, from, to, newType) {
var newLength = to - from;
if (newLength < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[from + " > " + to ]);
var copy = (newType === Clazz.array(java.lang.Object, -1) ) ? Clazz.array(java.lang.Object, [newLength]) : Clazz.array(newType.getComponentType(), newLength);
System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOfRange$BA$I$I', function (original, from, to) {
var newLength = to - from;
if (newLength < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[from + " > " + to ]);
var copy = Clazz.array(Byte.TYPE, [newLength]);
System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOfRange$HA$I$I', function (original, from, to) {
var newLength = to - from;
if (newLength < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[from + " > " + to ]);
var copy = Clazz.array(Short.TYPE, [newLength]);
System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOfRange$IA$I$I', function (original, from, to) {
var newLength = to - from;
if (newLength < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[from + " > " + to ]);
var copy = Clazz.array(Integer.TYPE, [newLength]);
System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOfRange$JA$I$I', function (original, from, to) {
var newLength = to - from;
if (newLength < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[from + " > " + to ]);
var copy = Clazz.array(Long.TYPE, [newLength]);
System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOfRange$CA$I$I', function (original, from, to) {
var newLength = to - from;
if (newLength < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[from + " > " + to ]);
var copy = Clazz.array(Character.TYPE, [newLength]);
System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOfRange$FA$I$I', function (original, from, to) {
var newLength = to - from;
if (newLength < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[from + " > " + to ]);
var copy = Clazz.array(Float.TYPE, [newLength]);
System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOfRange$DA$I$I', function (original, from, to) {
var newLength = to - from;
if (newLength < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[from + " > " + to ]);
var copy = Clazz.array(Double.TYPE, [newLength]);
System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'copyOfRange$ZA$I$I', function (original, from, to) {
var newLength = to - from;
if (newLength < 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[from + " > " + to ]);
var copy = Clazz.array(Boolean.TYPE, [newLength]);
System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
return copy;
}, 1);

Clazz.newMeth(C$, 'asList$TTA', function (a) {
return Clazz.new_((I$[3]||$incl$(3)).c$$TEA,[a]);
}, 1);

Clazz.newMeth(C$, 'hashCode$JA', function (a) {
if (a == null ) return 0;
var result = 1;
for (var element, $element = 0, $$element = a; $element<$$element.length&&((element=$$element[$element]),1);$element++) {
var elementHash = ((element ^ (element >>> 32))|0);
result = 31 * result + elementHash;
}
return result;
}, 1);

Clazz.newMeth(C$, 'hashCode$IA', function (a) {
if (a == null ) return 0;
var result = 1;
for (var element, $element = 0, $$element = a; $element<$$element.length&&((element=$$element[$element]),1);$element++) result = 31 * result + element;

return result;
}, 1);

Clazz.newMeth(C$, 'hashCode$HA', function (a) {
if (a == null ) return 0;
var result = 1;
for (var element, $element = 0, $$element = a; $element<$$element.length&&((element=$$element[$element]),1);$element++) result = 31 * result + element;

return result;
}, 1);

Clazz.newMeth(C$, 'hashCode$CA', function (a) {
if (a == null ) return 0;
var result = 1;
for (var element, $element = 0, $$element = a; $element<$$element.length&&((element=$$element[$element]),1);$element++) result = 31 * result + element.$c();

return result;
}, 1);

Clazz.newMeth(C$, 'hashCode$BA', function (a) {
if (a == null ) return 0;
var result = 1;
for (var element, $element = 0, $$element = a; $element<$$element.length&&((element=$$element[$element]),1);$element++) result = 31 * result + element;

return result;
}, 1);

Clazz.newMeth(C$, 'hashCode$ZA', function (a) {
if (a == null ) return 0;
var result = 1;
for (var element, $element = 0, $$element = a; $element<$$element.length&&((element=$$element[$element]),1);$element++) result = 31 * result + (element ? 1231 : 1237);

return result;
}, 1);

Clazz.newMeth(C$, 'hashCode$FA', function (a) {
if (a == null ) return 0;
var result = 1;
for (var element, $element = 0, $$element = a; $element<$$element.length&&((element=$$element[$element]),1);$element++) result = 31 * result + Float.floatToIntBits(element);

return result;
}, 1);

Clazz.newMeth(C$, 'hashCode$DA', function (a) {
if (a == null ) return 0;
var result = 1;
for (var element, $element = 0, $$element = a; $element<$$element.length&&((element=$$element[$element]),1);$element++) {
var bits = Double.doubleToLongBits(element);
result = 31 * result + ((bits ^ (bits >>> 32))|0);
}
return result;
}, 1);

Clazz.newMeth(C$, 'hashCode$OA', function (a) {
if (a == null ) return 0;
var result = 1;
for (var element, $element = 0, $$element = a; $element<$$element.length&&((element=$$element[$element]),1);$element++) result = 31 * result + (element == null  ? 0 : element.hashCode());

return result;
}, 1);

Clazz.newMeth(C$, 'deepHashCode$OA', function (a) {
if (a == null ) return 0;
var result = 1;
for (var element, $element = 0, $$element = a; $element<$$element.length&&((element=$$element[$element]),1);$element++) {
var elementHash = 0;
if (Clazz.instanceOf(element, Clazz.array(java.lang.Object, -1))) elementHash = C$.deepHashCode$OA(element);
 else if (Clazz.instanceOf(element, Clazz.array(Byte.TYPE, -1))) elementHash = C$.hashCode$BA(element);
 else if (Clazz.instanceOf(element, Clazz.array(Short.TYPE, -1))) elementHash = C$.hashCode$HA(element);
 else if (Clazz.instanceOf(element, Clazz.array(Integer.TYPE, -1))) elementHash = C$.hashCode$IA(element);
 else if (Clazz.instanceOf(element, Clazz.array(Long.TYPE, -1))) elementHash = C$.hashCode$JA(element);
 else if (Clazz.instanceOf(element, Clazz.array(Character.TYPE, -1))) elementHash = C$.hashCode$CA(element);
 else if (Clazz.instanceOf(element, Clazz.array(Float.TYPE, -1))) elementHash = C$.hashCode$FA(element);
 else if (Clazz.instanceOf(element, Clazz.array(Double.TYPE, -1))) elementHash = C$.hashCode$DA(element);
 else if (Clazz.instanceOf(element, Clazz.array(Boolean.TYPE, -1))) elementHash = C$.hashCode$ZA(element);
 else if (element != null ) elementHash = element.hashCode();
result = 31 * result + elementHash;
}
return result;
}, 1);

Clazz.newMeth(C$, 'deepEquals$OA$OA', function (a1, a2) {
if (a1 === a2 ) return true;
if (a1 == null  || a2 == null  ) return false;
var length = a1.length;
if (a2.length != length) return false;
for (var i = 0; i < length; i++) {
var e1 = a1[i];
var e2 = a2[i];
if (e1 === e2 ) continue;
if (e1 == null ) return false;
var eq;
if (Clazz.instanceOf(e1, Clazz.array(java.lang.Object, -1)) && Clazz.instanceOf(e2, Clazz.array(java.lang.Object, -1)) ) eq = C$.deepEquals$OA$OA(e1, e2);
 else if (Clazz.instanceOf(e1, Clazz.array(Byte.TYPE, -1)) && Clazz.instanceOf(e2, Clazz.array(Byte.TYPE, -1)) ) eq = C$.equals$BA$BA(e1, e2);
 else if (Clazz.instanceOf(e1, Clazz.array(Short.TYPE, -1)) && Clazz.instanceOf(e2, Clazz.array(Short.TYPE, -1)) ) eq = C$.equals$HA$HA(e1, e2);
 else if (Clazz.instanceOf(e1, Clazz.array(Integer.TYPE, -1)) && Clazz.instanceOf(e2, Clazz.array(Integer.TYPE, -1)) ) eq = C$.equals$IA$IA(e1, e2);
 else if (Clazz.instanceOf(e1, Clazz.array(Long.TYPE, -1)) && Clazz.instanceOf(e2, Clazz.array(Long.TYPE, -1)) ) eq = C$.equals$JA$JA(e1, e2);
 else if (Clazz.instanceOf(e1, Clazz.array(Character.TYPE, -1)) && Clazz.instanceOf(e2, Clazz.array(Character.TYPE, -1)) ) eq = C$.equals$CA$CA(e1, e2);
 else if (Clazz.instanceOf(e1, Clazz.array(Float.TYPE, -1)) && Clazz.instanceOf(e2, Clazz.array(Float.TYPE, -1)) ) eq = C$.equals$FA$FA(e1, e2);
 else if (Clazz.instanceOf(e1, Clazz.array(Double.TYPE, -1)) && Clazz.instanceOf(e2, Clazz.array(Double.TYPE, -1)) ) eq = C$.equals$DA$DA(e1, e2);
 else if (Clazz.instanceOf(e1, Clazz.array(Boolean.TYPE, -1)) && Clazz.instanceOf(e2, Clazz.array(Boolean.TYPE, -1)) ) eq = C$.equals$ZA$ZA(e1, e2);
 else eq = e1.equals$O(e2);
if (!eq) return false;
}
return true;
}, 1);

Clazz.newMeth(C$, 'toString$JA', function (a) {
if (a == null ) return "null";
var iMax = a.length - 1;
if (iMax == -1) return "[]";
var b = Clazz.new_((I$[4]||$incl$(4)));
b.append$C("[");
for (var i = 0; ; i++) {
b.append$J(a[i]);
if (i == iMax) return b.append$C("]").toString();
b.append$S(", ");
}
}, 1);

Clazz.newMeth(C$, 'toString$IA', function (a) {
if (a == null ) return "null";
var iMax = a.length - 1;
if (iMax == -1) return "[]";
var b = Clazz.new_((I$[4]||$incl$(4)));
b.append$C("[");
for (var i = 0; ; i++) {
b.append$I(a[i]);
if (i == iMax) return b.append$C("]").toString();
b.append$S(", ");
}
}, 1);

Clazz.newMeth(C$, 'toString$HA', function (a) {
if (a == null ) return "null";
var iMax = a.length - 1;
if (iMax == -1) return "[]";
var b = Clazz.new_((I$[4]||$incl$(4)));
b.append$C("[");
for (var i = 0; ; i++) {
b.append$I(a[i]);
if (i == iMax) return b.append$C("]").toString();
b.append$S(", ");
}
}, 1);

Clazz.newMeth(C$, 'toString$CA', function (a) {
if (a == null ) return "null";
var iMax = a.length - 1;
if (iMax == -1) return "[]";
var b = Clazz.new_((I$[4]||$incl$(4)));
b.append$C("[");
for (var i = 0; ; i++) {
b.append$C(a[i]);
if (i == iMax) return b.append$C("]").toString();
b.append$S(", ");
}
}, 1);

Clazz.newMeth(C$, 'toString$BA', function (a) {
if (a == null ) return "null";
var iMax = a.length - 1;
if (iMax == -1) return "[]";
var b = Clazz.new_((I$[4]||$incl$(4)));
b.append$C("[");
for (var i = 0; ; i++) {
b.append$I(a[i]);
if (i == iMax) return b.append$C("]").toString();
b.append$S(", ");
}
}, 1);

Clazz.newMeth(C$, 'toString$ZA', function (a) {
if (a == null ) return "null";
var iMax = a.length - 1;
if (iMax == -1) return "[]";
var b = Clazz.new_((I$[4]||$incl$(4)));
b.append$C("[");
for (var i = 0; ; i++) {
b.append$Z(a[i]);
if (i == iMax) return b.append$C("]").toString();
b.append$S(", ");
}
}, 1);

Clazz.newMeth(C$, 'toString$FA', function (a) {
if (a == null ) return "null";
var iMax = a.length - 1;
if (iMax == -1) return "[]";
var b = Clazz.new_((I$[4]||$incl$(4)));
b.append$C("[");
for (var i = 0; ; i++) {
b.append$F(a[i]);
if (i == iMax) return b.append$C("]").toString();
b.append$S(", ");
}
}, 1);

Clazz.newMeth(C$, 'toString$DA', function (a) {
if (a == null ) return "null";
var iMax = a.length - 1;
if (iMax == -1) return "[]";
var b = Clazz.new_((I$[4]||$incl$(4)));
b.append$C("[");
for (var i = 0; ; i++) {
b.append$D(a[i]);
if (i == iMax) return b.append$C("]").toString();
b.append$S(", ");
}
}, 1);

Clazz.newMeth(C$, 'toString$OA', function (a) {
if (a == null ) return "null";
var iMax = a.length - 1;
if (iMax == -1) return "[]";
var b = Clazz.new_((I$[4]||$incl$(4)));
b.append$C("[");
for (var i = 0; ; i++) {
b.append$S(String.valueOf(a[i]));
if (i == iMax) return b.append$C("]").toString();
b.append$S(", ");
}
}, 1);

Clazz.newMeth(C$, 'deepToString$OA', function (a) {
if (a == null ) return "null";
var bufLen = 20 * a.length;
if (a.length != 0 && bufLen <= 0 ) bufLen = 2147483647;
var buf = Clazz.new_((I$[4]||$incl$(4)).c$$I,[bufLen]);
C$.deepToString$OA$StringBuilder$java_util_Set(a, buf, Clazz.new_((I$[5]||$incl$(5))));
return buf.toString();
}, 1);

Clazz.newMeth(C$, 'deepToString$OA$StringBuilder$java_util_Set', function (a, buf, dejaVu) {
if (a == null ) {
buf.append$S("null");
return;
}var iMax = a.length - 1;
if (iMax == -1) {
buf.append$S("[]");
return;
}dejaVu.add$TE(a);
buf.append$C("[");
for (var i = 0; ; i++) {
var element = a[i];
if (element == null ) {
buf.append$S("null");
} else {
var eClass = element.getClass();
if (eClass.isArray()) {
if (eClass === Clazz.array(Byte.TYPE, -1) ) buf.append$S(C$.toString$BA(element));
 else if (eClass === Clazz.array(Short.TYPE, -1) ) buf.append$S(C$.toString$HA(element));
 else if (eClass === Clazz.array(Integer.TYPE, -1) ) buf.append$S(C$.toString$IA(element));
 else if (eClass === Clazz.array(Long.TYPE, -1) ) buf.append$S(C$.toString$JA(element));
 else if (eClass === Clazz.array(Character.TYPE, -1) ) buf.append$S(C$.toString$CA(element));
 else if (eClass === Clazz.array(Float.TYPE, -1) ) buf.append$S(C$.toString$FA(element));
 else if (eClass === Clazz.array(Double.TYPE, -1) ) buf.append$S(C$.toString$DA(element));
 else if (eClass === Clazz.array(Boolean.TYPE, -1) ) buf.append$S(C$.toString$ZA(element));
 else {
if (dejaVu.contains$O(element)) buf.append$S("[...]");
 else C$.deepToString$OA$StringBuilder$java_util_Set(element, buf, dejaVu);
}} else {
buf.append$S(element.toString());
}}if (i == iMax) break;
buf.append$S(", ");
}
buf.append$C("]");
dejaVu.remove$O(a);
}, 1);
var $b$ = new Int8Array(1);
;
(function(){var C$=Clazz.newClass(P$.Arrays, "ArrayList", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.util.AbstractList', ['java.util.RandomAccess', 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.a = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$TEA', function (array) {
Clazz.super_(C$, this,1);
if (array == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
this.a = array;
}, 1);

Clazz.newMeth(C$, 'size', function () {
return this.a.length;
});

Clazz.newMeth(C$, 'toArray', function () {
return this.a.clone();
});

Clazz.newMeth(C$, 'toArray$TTA', function (a) {
var size = this.size();
if (a.length < size) return (I$[1]||$incl$(1)).copyOf$TUA$I$Class(this.a, size, a.getClass());
System.arraycopy(this.a, 0, a, 0, size);
if (a.length > size) a[size] = null;
return a;
});

Clazz.newMeth(C$, 'get$I', function (index) {
return this.a[index];
});

Clazz.newMeth(C$, 'set$I$TE', function (index, element) {
var oldValue = this.a[index];
this.a[index] = element;
return oldValue;
});

Clazz.newMeth(C$, 'indexOf$O', function (o) {
if (o == null ) {
for (var i = 0; i < this.a.length; i++) if (this.a[i] == null ) return i;

} else {
for (var i = 0; i < this.a.length; i++) if (o.equals$O(this.a[i])) return i;

}return -1;
});

Clazz.newMeth(C$, 'contains$O', function (o) {
return this.indexOf$O(o) != -1;
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-15 01:02:11
