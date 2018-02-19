(function(){var P$=Clazz.newPackage("javax.swing.tree"),I$=[];
var C$=Clazz.newClass(P$, "PathPlaceHolder");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.isNew = false;
this.path = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_tree_TreePath$Z', function (path, isNew) {
C$.$init$.apply(this);
this.path = path;
this.isNew = isNew;
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:01
