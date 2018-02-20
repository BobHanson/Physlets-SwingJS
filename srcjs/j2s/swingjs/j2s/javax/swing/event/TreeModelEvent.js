(function(){var P$=Clazz.newPackage("javax.swing.event"),I$=[['javax.swing.tree.TreePath','java.lang.StringBuffer']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "TreeModelEvent", null, 'java.util.EventObject');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.path = null;
this.childIndices = null;
this.children = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O$OA$IA$OA', function (source, path, childIndices, children) {
C$.c$$O$javax_swing_tree_TreePath$IA$OA.apply(this, [source, Clazz.new_((I$[1]||$incl$(1)).c$$OA,[path]), childIndices, children]);
}, 1);

Clazz.newMeth(C$, 'c$$O$javax_swing_tree_TreePath$IA$OA', function (source, path, childIndices, children) {
C$.superclazz.c$.apply(this, [source]);
C$.$init$.apply(this);
this.path = path;
this.childIndices = childIndices;
this.children = children;
}, 1);

Clazz.newMeth(C$, 'c$$O$OA', function (source, path) {
C$.c$$O$javax_swing_tree_TreePath.apply(this, [source, Clazz.new_((I$[1]||$incl$(1)).c$$OA,[path])]);
}, 1);

Clazz.newMeth(C$, 'c$$O$javax_swing_tree_TreePath', function (source, path) {
C$.superclazz.c$.apply(this, [source]);
C$.$init$.apply(this);
this.path = path;
this.childIndices = Clazz.array(Integer.TYPE, [0]);
}, 1);

Clazz.newMeth(C$, 'getTreePath', function () {
return this.path;
});

Clazz.newMeth(C$, 'getPath', function () {
if (this.path != null ) return this.path.getPath();
return null;
});

Clazz.newMeth(C$, 'getChildren', function () {
if (this.children != null ) {
var cCount = this.children.length;
var retChildren = Clazz.array(java.lang.Object, [cCount]);
System.arraycopy(this.children, 0, retChildren, 0, cCount);
return retChildren;
}return null;
});

Clazz.newMeth(C$, 'getChildIndices', function () {
if (this.childIndices != null ) {
var cCount = this.childIndices.length;
var retArray = Clazz.array(Integer.TYPE, [cCount]);
System.arraycopy(this.childIndices, 0, retArray, 0, cCount);
return retArray;
}return null;
});

Clazz.newMeth(C$, 'toString', function () {
var retBuffer = Clazz.new_((I$[2]||$incl$(2)));
retBuffer.append$S(this.getClass().getName() + " " + Integer.toString(this.hashCode()) );
if (this.path != null ) retBuffer.append$S(" path " + this.path);
if (this.childIndices != null ) {
retBuffer.append$S(" indices [ ");
for (var counter = 0; counter < this.childIndices.length; counter++) retBuffer.append$S(Integer.toString(this.childIndices[counter]) + " ");

retBuffer.append$S("]");
}if (this.children != null ) {
retBuffer.append$S(" children [ ");
for (var counter = 0; counter < this.children.length; counter++) retBuffer.append$S(this.children[counter] + " ");

retBuffer.append$S("]");
}return retBuffer.toString();
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:48