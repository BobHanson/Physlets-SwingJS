(function(){var P$=Clazz.newPackage("java.awt.image"),I$=[['java.lang.InternalError']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Kernel", null, null, 'Cloneable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.width = 0;
this.height = 0;
this.xOrigin = 0;
this.yOrigin = 0;
this.data = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$I$FA', function (width, height, data) {
C$.$init$.apply(this);
this.width=width;
this.height=height;
this.xOrigin=(width - 1) >> 1;
this.yOrigin=(height - 1) >> 1;
var len = width * height;
if (data.length < len) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Data array too small (is " + data.length + " and should be " + len ]);
}this.data=Clazz.array(Float.TYPE, [len]);
System.arraycopy(data, 0, this.data, 0, len);
}, 1);

Clazz.newMeth(C$, 'getXOrigin', function () {
return this.xOrigin;
});

Clazz.newMeth(C$, 'getYOrigin', function () {
return this.yOrigin;
});

Clazz.newMeth(C$, 'getWidth', function () {
return this.width;
});

Clazz.newMeth(C$, 'getHeight', function () {
return this.height;
});

Clazz.newMeth(C$, 'getKernelData$FA', function (data) {
if (data == null ) {
data=Clazz.array(Float.TYPE, [this.data.length]);
} else if (data.length < this.data.length) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Data array too small (should be " + this.data.length + " but is " + data.length + " )" ]);
}System.arraycopy(this.data, 0, data, 0, this.data.length);
return data;
});

Clazz.newMeth(C$, 'clone', function () {
try {
return Clazz.clone(this);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
throw Clazz.new_((I$[1]||$incl$(1)));
} else {
throw e;
}
}
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:26
