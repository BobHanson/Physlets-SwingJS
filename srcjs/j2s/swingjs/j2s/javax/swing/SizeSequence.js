(function(){var P$=Clazz.newPackage("javax.swing"),I$=[];
var C$=Clazz.newClass(P$, "SizeSequence");
C$.emptyArray = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.emptyArray = Clazz.array(Integer.TYPE, [0]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.a = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.a = C$.emptyArray;
}, 1);

Clazz.newMeth(C$, 'c$$I', function (numEntries) {
C$.c$$I$I.apply(this, [numEntries, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$I$I', function (numEntries, value) {
C$.c$.apply(this, []);
this.insertEntries$I$I$I(0, numEntries, value);
}, 1);

Clazz.newMeth(C$, 'c$$IA', function (sizes) {
C$.c$.apply(this, []);
this.setSizes$IA(sizes);
}, 1);

Clazz.newMeth(C$, 'setSizes$I$I', function (length, size) {
if (this.a.length != length) {
this.a = Clazz.array(Integer.TYPE, [length]);
}p$.setSizes$I$I$I.apply(this, [0, length, size]);
});

Clazz.newMeth(C$, 'setSizes$I$I$I', function (from, to, size) {
if (to <= from) {
return 0;
}var m = ((from + to)/2|0);
this.a[m] = size + p$.setSizes$I$I$I.apply(this, [from, m, size]);
return this.a[m] + p$.setSizes$I$I$I.apply(this, [m + 1, to, size]);
});

Clazz.newMeth(C$, 'setSizes$IA', function (sizes) {
if (this.a.length != sizes.length) {
this.a = Clazz.array(Integer.TYPE, [sizes.length]);
}p$.setSizes$I$I$IA.apply(this, [0, this.a.length, sizes]);
});

Clazz.newMeth(C$, 'setSizes$I$I$IA', function (from, to, sizes) {
if (to <= from) {
return 0;
}var m = ((from + to)/2|0);
this.a[m] = sizes[m] + p$.setSizes$I$I$IA.apply(this, [from, m, sizes]);
return this.a[m] + p$.setSizes$I$I$IA.apply(this, [m + 1, to, sizes]);
});

Clazz.newMeth(C$, 'getSizes', function () {
var n = this.a.length;
var sizes = Clazz.array(Integer.TYPE, [n]);
p$.getSizes$I$I$IA.apply(this, [0, n, sizes]);
return sizes;
});

Clazz.newMeth(C$, 'getSizes$I$I$IA', function (from, to, sizes) {
if (to <= from) {
return 0;
}var m = ((from + to)/2|0);
sizes[m] = this.a[m] - p$.getSizes$I$I$IA.apply(this, [from, m, sizes]);
return this.a[m] + p$.getSizes$I$I$IA.apply(this, [m + 1, to, sizes]);
});

Clazz.newMeth(C$, 'getPosition$I', function (index) {
return p$.getPosition$I$I$I.apply(this, [0, this.a.length, index]);
});

Clazz.newMeth(C$, 'getPosition$I$I$I', function (from, to, index) {
if (to <= from) {
return 0;
}var m = ((from + to)/2|0);
if (index <= m) {
return p$.getPosition$I$I$I.apply(this, [from, m, index]);
} else {
return this.a[m] + p$.getPosition$I$I$I.apply(this, [m + 1, to, index]);
}});

Clazz.newMeth(C$, 'getIndex$I', function (position) {
return p$.getIndex$I$I$I.apply(this, [0, this.a.length, position]);
});

Clazz.newMeth(C$, 'getIndex$I$I$I', function (from, to, position) {
if (to <= from) {
return from;
}var m = ((from + to)/2|0);
var pivot = this.a[m];
if (position < pivot) {
return p$.getIndex$I$I$I.apply(this, [from, m, position]);
} else {
return p$.getIndex$I$I$I.apply(this, [m + 1, to, position - pivot]);
}});

Clazz.newMeth(C$, 'getSize$I', function (index) {
return this.getPosition$I(index + 1) - this.getPosition$I(index);
});

Clazz.newMeth(C$, 'setSize$I$I', function (index, size) {
p$.changeSize$I$I$I$I.apply(this, [0, this.a.length, index, size - this.getSize$I(index)]);
});

Clazz.newMeth(C$, 'changeSize$I$I$I$I', function (from, to, index, delta) {
if (to <= from) {
return;
}var m = ((from + to)/2|0);
if (index <= m) {
this.a[m] = this.a[m]+(delta);
p$.changeSize$I$I$I$I.apply(this, [from, m, index, delta]);
} else {
p$.changeSize$I$I$I$I.apply(this, [m + 1, to, index, delta]);
}});

Clazz.newMeth(C$, 'insertEntries$I$I$I', function (start, length, value) {
var sizes = this.getSizes();
var end = start + length;
var n = this.a.length + length;
this.a = Clazz.array(Integer.TYPE, [n]);
for (var i = 0; i < start; i++) {
this.a[i] = sizes[i];
}
for (var i = start; i < end; i++) {
this.a[i] = value;
}
for (var i = end; i < n; i++) {
this.a[i] = sizes[i - length];
}
this.setSizes$IA(this.a);
});

Clazz.newMeth(C$, 'removeEntries$I$I', function (start, length) {
var sizes = this.getSizes();
var n = this.a.length - length;
this.a = Clazz.array(Integer.TYPE, [n]);
for (var i = 0; i < start; i++) {
this.a[i] = sizes[i];
}
for (var i = start; i < n; i++) {
this.a[i] = sizes[i + length];
}
this.setSizes$IA(this.a);
});
})();
//Created 2018-02-08 10:02:42
