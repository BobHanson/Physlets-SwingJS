(function(){var P$=Clazz.newPackage("javax.swing.event"),I$=[];
var C$=Clazz.newClass(P$, "TreeExpansionEvent", null, 'java.util.EventObject');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.path = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O$javax_swing_tree_TreePath', function (source, path) {
C$.superclazz.c$.apply(this, [source]);
C$.$init$.apply(this);
this.path = path;
}, 1);

Clazz.newMeth(C$, 'getPath', function () {
return this.path;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:49
