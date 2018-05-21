(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.util.Arrays']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "BasicStroke", null, null, ['java.awt.Stroke', 'Cloneable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.width = 0;
this.join = 0;
this.cap = 0;
this.miterlimit = 0;
this.dash = null;
this.dash_phase = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$F$I$I$F$FA$F', function (width, cap, join, miterlimit, dash, dash_phase) {
C$.$init$.apply(this);
if (width < 0.0 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["negative width"]);
}if (cap != 0 && cap != 1  && cap != 2 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["illegal end cap value"]);
}if (join == 0) {
if (miterlimit < 1.0 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["miter limit < 1"]);
}} else if (join != 1 && join != 2 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["illegal line join value"]);
}if (dash != null ) {
if (dash_phase < 0.0 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["negative dash phase"]);
}var allzero = true;
for (var i = 0; i < dash.length; i++) {
var d = dash[i];
if (d > 0.0 ) {
allzero = false;
} else if (d < 0.0 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["negative dash length"]);
}}
if (allzero) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["dash lengths all zero"]);
}}this.width = width;
this.cap = cap;
this.join = join;
this.miterlimit = miterlimit;
if (dash != null ) {
this.dash = dash.clone();
}this.dash_phase = dash_phase;
}, 1);

Clazz.newMeth(C$, 'c$$F$I$I$F', function (width, cap, join, miterlimit) {
C$.c$$F$I$I$F$FA$F.apply(this, [width, cap, join, miterlimit, null, 0.0]);
}, 1);

Clazz.newMeth(C$, 'c$$F$I$I', function (width, cap, join) {
C$.c$$F$I$I$F$FA$F.apply(this, [width, cap, join, 10.0, null, 0.0]);
}, 1);

Clazz.newMeth(C$, 'c$$F', function (width) {
C$.c$$F$I$I$F$FA$F.apply(this, [width, 2, 0, 10.0, null, 0.0]);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$F$I$I$F$FA$F.apply(this, [1.0, 2, 0, 10.0, null, 0.0]);
}, 1);

Clazz.newMeth(C$, 'createStrokedShape$java_awt_Shape', function (s) {
return s;
});

Clazz.newMeth(C$, 'getLineWidth', function () {
return this.width;
});

Clazz.newMeth(C$, 'getEndCap', function () {
return this.cap;
});

Clazz.newMeth(C$, 'getLineJoin', function () {
return this.join;
});

Clazz.newMeth(C$, 'getMiterLimit', function () {
return this.miterlimit;
});

Clazz.newMeth(C$, 'getDashArray', function () {
if (this.dash == null ) {
return null;
}return this.dash.clone();
});

Clazz.newMeth(C$, 'getDashPhase', function () {
return this.dash_phase;
});

Clazz.newMeth(C$, 'hashCode', function () {
var hash = Float.floatToIntBits(this.width);
hash = hash * 31 + this.join;
hash = hash * 31 + this.cap;
hash = hash * 31 + Float.floatToIntBits(this.miterlimit);
if (this.dash != null ) {
hash = hash * 31 + Float.floatToIntBits(this.dash_phase);
for (var i = 0; i < this.dash.length; i++) {
hash = hash * 31 + Float.floatToIntBits(this.dash[i]);
}
}return hash;
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
if (!(Clazz.instanceOf(obj, "java.awt.BasicStroke"))) {
return false;
}var bs = obj;
if (this.width != bs.width ) {
return false;
}if (this.join != bs.join) {
return false;
}if (this.cap != bs.cap) {
return false;
}if (this.miterlimit != bs.miterlimit ) {
return false;
}if (this.dash != null ) {
if (this.dash_phase != bs.dash_phase ) {
return false;
}if (!(I$[1]||$incl$(1)).equals$FA$FA(this.dash, bs.dash)) {
return false;
}} else if (bs.dash != null ) {
return false;
}return true;
});

Clazz.newMeth(C$, 'clone', function () {
return Clazz.new_(C$.c$$F$I$I$F$FA$F,[this.width, this.cap, this.join, this.miterlimit, this.dash, this.dash_phase]);
});
})();
//Created 2018-05-15 01:01:48
