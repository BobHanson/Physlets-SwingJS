(function(){var P$=Clazz.newPackage("java.awt.print"),I$=[['java.awt.print.Paper','java.lang.InternalError']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PageFormat", null, null, 'Cloneable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.mPaper = null;
this.mOrientation = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.mOrientation = 1;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.mPaper = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'clone', function () {
var newPage;
try {
newPage = Clazz.clone(this);
newPage.mPaper = this.mPaper.clone();
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
e.printStackTrace();
newPage = null;
} else {
throw e;
}
}
return newPage;
});

Clazz.newMeth(C$, 'getWidth', function () {
var width;
var orientation = this.getOrientation();
if (orientation == 1) {
width = this.mPaper.getWidth();
} else {
width = this.mPaper.getHeight();
}return width;
});

Clazz.newMeth(C$, 'getHeight', function () {
var height;
var orientation = this.getOrientation();
if (orientation == 1) {
height = this.mPaper.getHeight();
} else {
height = this.mPaper.getWidth();
}return height;
});

Clazz.newMeth(C$, 'getImageableX', function () {
var x;
switch (this.getOrientation()) {
case 0:
x = this.mPaper.getHeight() - (this.mPaper.getImageableY() + this.mPaper.getImageableHeight());
break;
case 1:
x = this.mPaper.getImageableX();
break;
case 2:
x = this.mPaper.getImageableY();
break;
default:
throw Clazz.new_((I$[2]||$incl$(2)).c$$S,["unrecognized orientation"]);
}
return x;
});

Clazz.newMeth(C$, 'getImageableY', function () {
var y;
switch (this.getOrientation()) {
case 0:
y = this.mPaper.getImageableX();
break;
case 1:
y = this.mPaper.getImageableY();
break;
case 2:
y = this.mPaper.getWidth() - (this.mPaper.getImageableX() + this.mPaper.getImageableWidth());
break;
default:
throw Clazz.new_((I$[2]||$incl$(2)).c$$S,["unrecognized orientation"]);
}
return y;
});

Clazz.newMeth(C$, 'getImageableWidth', function () {
var width;
if (this.getOrientation() == 1) {
width = this.mPaper.getImageableWidth();
} else {
width = this.mPaper.getImageableHeight();
}return width;
});

Clazz.newMeth(C$, 'getImageableHeight', function () {
var height;
if (this.getOrientation() == 1) {
height = this.mPaper.getImageableHeight();
} else {
height = this.mPaper.getImageableWidth();
}return height;
});

Clazz.newMeth(C$, 'getPaper', function () {
return this.mPaper.clone();
});

Clazz.newMeth(C$, 'setPaper$java_awt_print_Paper', function (paper) {
this.mPaper = paper.clone();
});

Clazz.newMeth(C$, 'setOrientation$I', function (orientation) {
if (0 <= orientation && orientation <= 2 ) {
this.mOrientation = orientation;
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}});

Clazz.newMeth(C$, 'getOrientation', function () {
return this.mOrientation;
});

Clazz.newMeth(C$, 'getMatrix', function () {
var matrix = Clazz.array(Double.TYPE, [6]);
switch (this.mOrientation) {
case 0:
matrix[0] = 0;
matrix[1] = -1;
matrix[2] = 1;
matrix[3] = 0;
matrix[4] = 0;
matrix[5] = this.mPaper.getHeight();
break;
case 1:
matrix[0] = 1;
matrix[1] = 0;
matrix[2] = 0;
matrix[3] = 1;
matrix[4] = 0;
matrix[5] = 0;
break;
case 2:
matrix[0] = 0;
matrix[1] = 1;
matrix[2] = -1;
matrix[3] = 0;
matrix[4] = this.mPaper.getWidth();
matrix[5] = 0;
break;
default:
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}
return matrix;
});
})();
//Created 2018-05-15 01:02:03
