(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[];
var C$=Clazz.newClass(P$, "EditorKit", null, null, 'Cloneable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'clone', function () {
var o;
try {
o = Clazz.clone(this);
} catch (cnse) {
if (Clazz.exceptionOf(cnse, "java.lang.CloneNotSupportedException")){
o = null;
} else {
throw cnse;
}
}
return o;
});

Clazz.newMeth(C$, 'install$javax_swing_JEditorPane', function (c) {
});

Clazz.newMeth(C$, 'deinstall$javax_swing_JEditorPane', function (c) {
});
})();
//Created 2018-02-06 08:59:57
