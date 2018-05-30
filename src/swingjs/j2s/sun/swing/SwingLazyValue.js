(function(){var P$=Clazz.newPackage("sun.swing"),I$=[['java.lang.Boolean','java.awt.Color']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SwingLazyValue", null, null, [['javax.swing.UIDefaults','javax.swing.UIDefaults.LazyValue']]);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.className = null;
this.methodName = null;
this.args = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (c) {
C$.c$$S$S$OA.apply(this, [c, null, null]);
}, 1);

Clazz.newMeth(C$, 'c$$S$S', function (c, m) {
C$.c$$S$S$OA.apply(this, [c, m, null]);
}, 1);

Clazz.newMeth(C$, 'c$$S$OA', function (c, o) {
C$.c$$S$S$OA.apply(this, [c, null, o]);
}, 1);

Clazz.newMeth(C$, 'c$$S$S$OA', function (c, m, o) {
C$.$init$.apply(this);
this.className=c;
this.methodName=m;
if (o != null ) {
this.args=o.clone();
}}, 1);

Clazz.newMeth(C$, 'createValue$javax_swing_UIDefaults', function (table) {
try {
var c = Clazz.forName(this.className, true, null);
if (this.methodName != null ) {
var types = p$.getClassArray$OA.apply(this, [this.args]);
var m = c.getMethod$S$ClassA(this.methodName, types);
return m.invoke$O$OA(c, this.args);
} else {
var types = p$.getClassArray$OA.apply(this, [this.args]);
var constructor = c.getConstructor$ClassA(types);
return constructor.newInstance$OA(this.args);
}} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
} else {
throw e;
}
}
return null;
});

Clazz.newMeth(C$, 'getClassArray$OA', function (args) {
var types = null;
if (args != null ) {
types=Clazz.array(java.lang.Class, [args.length]);
for (var i = 0; i < args.length; i++) {
if (Clazz.instanceOf(args[i], "java.lang.Integer")) {
types[i]=Integer.TYPE;
} else if (Clazz.instanceOf(args[i], "java.lang.Boolean")) {
types[i]=(I$[1]||$incl$(1)).TYPE;
} else if (Clazz.instanceOf(args[i], "javax.swing.plaf.ColorUIResource")) {
types[i]=Clazz.getClass((I$[2]||$incl$(2)));
} else {
types[i]=args[i].getClass();
}}
}return types;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:36
