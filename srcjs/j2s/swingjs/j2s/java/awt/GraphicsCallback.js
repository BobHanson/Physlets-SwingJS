(function(){var P$=Clazz.newPackage("java.awt"),I$=[];
var C$=Clazz.newClass(P$, "GraphicsCallback", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'sun.awt.SunGraphicsCallback');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);
;
(function(){var C$=Clazz.newClass(P$.GraphicsCallback, "PaintCallback", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.awt.GraphicsCallback');
C$.instance = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.instance = Clazz.new_(C$);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'run$java_awt_Component$java_awt_Graphics', function (comp, cg) {
comp.paint$java_awt_Graphics(cg);
});

Clazz.newMeth(C$, 'getInstance', function () {
return C$.instance;
}, 1);
})()
;
(function(){var C$=Clazz.newClass(P$.GraphicsCallback, "PaintAllCallback", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.awt.GraphicsCallback');
C$.instance = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.instance = Clazz.new_(C$);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'run$java_awt_Component$java_awt_Graphics', function (comp, cg) {
comp.paintAll$java_awt_Graphics(cg);
});

Clazz.newMeth(C$, 'getInstance', function () {
return C$.instance;
}, 1);
})()
;
(function(){var C$=Clazz.newClass(P$.GraphicsCallback, "PaintHeavyweightComponentsCallback", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.awt.GraphicsCallback');
C$.instance = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.instance = Clazz.new_(C$);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'run$java_awt_Component$java_awt_Graphics', function (comp, cg) {
if (comp.isLightweight()) {
comp.paintHeavyweightComponents$java_awt_Graphics(cg);
} else {
comp.paintAll$java_awt_Graphics(cg);
}});

Clazz.newMeth(C$, 'getInstance', function () {
return C$.instance;
}, 1);
})()

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:01:49
