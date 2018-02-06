(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "BorderLayout", null, null, ['java.awt.LayoutManager2', 'java.io.Serializable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.hgap = 0;
this.vgap = 0;
this.north = null;
this.west = null;
this.east = null;
this.south = null;
this.center = null;
this.firstLine = null;
this.lastLine = null;
this.firstItem = null;
this.lastItem = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$I$I.apply(this, [0, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$I$I', function (hgap, vgap) {
C$.$init$.apply(this);
this.hgap = hgap;
this.vgap = vgap;
}, 1);

Clazz.newMeth(C$, 'getHgap', function () {
return this.hgap;
});

Clazz.newMeth(C$, 'setHgap$I', function (hgap) {
this.hgap = hgap;
});

Clazz.newMeth(C$, 'getVgap', function () {
return this.vgap;
});

Clazz.newMeth(C$, 'setVgap$I', function (vgap) {
this.vgap = vgap;
});

Clazz.newMeth(C$, 'addLayoutComponent$java_awt_Component$O', function (comp, constraints) {
{
if ((constraints == null ) || (Clazz.instanceOf(constraints, "java.lang.String")) ) {
this.addLayoutComponent$S$java_awt_Component(constraints, comp);
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["cannot add to layout: constraint must be a string (or null)"]);
}}});

Clazz.newMeth(C$, 'addLayoutComponent$S$java_awt_Component', function (name, comp) {
{
if (name == null ) {
name = "Center";
}if ("Center".equals$O(name)) {
this.center = comp;
} else if ("North".equals$O(name)) {
this.north = comp;
} else if ("South".equals$O(name)) {
this.south = comp;
} else if ("East".equals$O(name)) {
this.east = comp;
} else if ("West".equals$O(name)) {
this.west = comp;
} else if ("First".equals$O(name)) {
this.firstLine = comp;
} else if ("Last".equals$O(name)) {
this.lastLine = comp;
} else if ("Before".equals$O(name)) {
this.firstItem = comp;
} else if ("After".equals$O(name)) {
this.lastItem = comp;
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["cannot add to layout: unknown constraint: " + name]);
}}});

Clazz.newMeth(C$, 'removeLayoutComponent$java_awt_Component', function (comp) {
{
if (comp === this.center ) {
this.center = null;
} else if (comp === this.north ) {
this.north = null;
} else if (comp === this.south ) {
this.south = null;
} else if (comp === this.east ) {
this.east = null;
} else if (comp === this.west ) {
this.west = null;
}if (comp === this.firstLine ) {
this.firstLine = null;
} else if (comp === this.lastLine ) {
this.lastLine = null;
} else if (comp === this.firstItem ) {
this.firstItem = null;
} else if (comp === this.lastItem ) {
this.lastItem = null;
}}});

Clazz.newMeth(C$, 'getLayoutComponent$O', function (constraints) {
if ("Center".equals$O(constraints)) {
return this.center;
} else if ("North".equals$O(constraints)) {
return this.north;
} else if ("South".equals$O(constraints)) {
return this.south;
} else if ("West".equals$O(constraints)) {
return this.west;
} else if ("East".equals$O(constraints)) {
return this.east;
} else if ("First".equals$O(constraints)) {
return this.firstLine;
} else if ("Last".equals$O(constraints)) {
return this.lastLine;
} else if ("Before".equals$O(constraints)) {
return this.firstItem;
} else if ("After".equals$O(constraints)) {
return this.lastItem;
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["cannot get component: unknown constraint: " + constraints]);
}});

Clazz.newMeth(C$, 'getLayoutComponent$java_awt_Container$O', function (target, constraints) {
var ltr = target.getComponentOrientation().isLeftToRight();
var result = null;
if ("North".equals$O(constraints)) {
result = (this.firstLine != null ) ? this.firstLine : this.north;
} else if ("South".equals$O(constraints)) {
result = (this.lastLine != null ) ? this.lastLine : this.south;
} else if ("West".equals$O(constraints)) {
result = ltr ? this.firstItem : this.lastItem;
if (result == null ) {
result = this.west;
}} else if ("East".equals$O(constraints)) {
result = ltr ? this.lastItem : this.firstItem;
if (result == null ) {
result = this.east;
}} else if ("Center".equals$O(constraints)) {
result = this.center;
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["cannot get component: invalid constraint: " + constraints]);
}return result;
});

Clazz.newMeth(C$, 'getConstraints$java_awt_Component', function (comp) {
if (comp == null ) {
return null;
}if (comp === this.center ) {
return "Center";
} else if (comp === this.north ) {
return "North";
} else if (comp === this.south ) {
return "South";
} else if (comp === this.west ) {
return "West";
} else if (comp === this.east ) {
return "East";
} else if (comp === this.firstLine ) {
return "First";
} else if (comp === this.lastLine ) {
return "Last";
} else if (comp === this.firstItem ) {
return "Before";
} else if (comp === this.lastItem ) {
return "After";
}return null;
});

Clazz.newMeth(C$, 'minimumLayoutSize$java_awt_Container', function (target) {
{
var dim = Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[0, 0]);
var ltr = target.getComponentOrientation().isLeftToRight();
var c = null;
if ((c = p$.getChild$S$Z.apply(this, ["East", ltr])) != null ) {
var d = c.getMinimumSize();
dim.width = dim.width+(d.width + this.hgap);
dim.height = Math.max(d.height, dim.height);
}if ((c = p$.getChild$S$Z.apply(this, ["West", ltr])) != null ) {
var d = c.getMinimumSize();
dim.width = dim.width+(d.width + this.hgap);
dim.height = Math.max(d.height, dim.height);
}if ((c = p$.getChild$S$Z.apply(this, ["Center", ltr])) != null ) {
var d = c.getMinimumSize();
dim.width = dim.width+(d.width);
dim.height = Math.max(d.height, dim.height);
}if ((c = p$.getChild$S$Z.apply(this, ["North", ltr])) != null ) {
var d = c.getMinimumSize();
dim.width = Math.max(d.width, dim.width);
dim.height = dim.height+(d.height + this.vgap);
}if ((c = p$.getChild$S$Z.apply(this, ["South", ltr])) != null ) {
var d = c.getMinimumSize();
dim.width = Math.max(d.width, dim.width);
dim.height = dim.height+(d.height + this.vgap);
}var insets = target.getInsets();
dim.width = dim.width+(insets.left + insets.right);
dim.height = dim.height+(insets.top + insets.bottom);
return dim;
}});

Clazz.newMeth(C$, 'preferredLayoutSize$java_awt_Container', function (target) {
{
var dim = Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[0, 0]);
var ltr = target.getComponentOrientation().isLeftToRight();
var c = null;
if ((c = p$.getChild$S$Z.apply(this, ["East", ltr])) != null ) {
var d = c.getPreferredSize();
dim.width = dim.width+(d.width + this.hgap);
dim.height = Math.max(d.height, dim.height);
}if ((c = p$.getChild$S$Z.apply(this, ["West", ltr])) != null ) {
var d = c.getPreferredSize();
dim.width = dim.width+(d.width + this.hgap);
dim.height = Math.max(d.height, dim.height);
}if ((c = p$.getChild$S$Z.apply(this, ["Center", ltr])) != null ) {
var d = c.getPreferredSize();
dim.width = dim.width+(d.width);
dim.height = Math.max(d.height, dim.height);
}if ((c = p$.getChild$S$Z.apply(this, ["North", ltr])) != null ) {
var d = c.getPreferredSize();
dim.width = Math.max(d.width, dim.width);
dim.height = dim.height+(d.height + this.vgap);
}if ((c = p$.getChild$S$Z.apply(this, ["South", ltr])) != null ) {
var d = c.getPreferredSize();
dim.width = Math.max(d.width, dim.width);
dim.height = dim.height+(d.height + this.vgap);
}var insets = target.getInsets();
dim.width = dim.width+(insets.left + insets.right);
dim.height = dim.height+(insets.top + insets.bottom);
return dim;
}});

Clazz.newMeth(C$, 'maximumLayoutSize$java_awt_Container', function (target) {
return Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[2147483647, 2147483647]);
});

Clazz.newMeth(C$, 'getLayoutAlignmentX$java_awt_Container', function (parent) {
return 0.5;
});

Clazz.newMeth(C$, 'getLayoutAlignmentY$java_awt_Container', function (parent) {
return 0.5;
});

Clazz.newMeth(C$, 'invalidateLayout$java_awt_Container', function (target) {
});

Clazz.newMeth(C$, 'layoutContainer$java_awt_Container', function (target) {
{
var insets = target.getInsets();
var top = insets.top;
var bottom = target.height - insets.bottom;
var left = insets.left;
var right = target.width - insets.right;
var ltr = target.getComponentOrientation().isLeftToRight();
var c = null;
if ((c = p$.getChild$S$Z.apply(this, ["North", ltr])) != null ) {
c.setSize$I$I(right - left, c.height);
var d = c.getPreferredSize();
c.setBounds$I$I$I$I(left, top, right - left, d.height);
top = top+(d.height + this.vgap);
}if ((c = p$.getChild$S$Z.apply(this, ["South", ltr])) != null ) {
c.setSize$I$I(right - left, c.height);
var d = c.getPreferredSize();
c.setBounds$I$I$I$I(left, bottom - d.height, right - left, d.height);
bottom = bottom-(d.height + this.vgap);
}if ((c = p$.getChild$S$Z.apply(this, ["East", ltr])) != null ) {
c.setSize$I$I(c.width, bottom - top);
var d = c.getPreferredSize();
c.setBounds$I$I$I$I(right - d.width, top, d.width, bottom - top);
right = right-(d.width + this.hgap);
}if ((c = p$.getChild$S$Z.apply(this, ["West", ltr])) != null ) {
c.setSize$I$I(c.width, bottom - top);
var d = c.getPreferredSize();
c.setBounds$I$I$I$I(left, top, d.width, bottom - top);
left = left+(d.width + this.hgap);
}if ((c = p$.getChild$S$Z.apply(this, ["Center", ltr])) != null ) {
c.setBounds$I$I$I$I(left, top, right - left, bottom - top);
}}});

Clazz.newMeth(C$, 'getChild$S$Z', function (key, ltr) {
var result = null;
if (key == "North") {
result = (this.firstLine != null ) ? this.firstLine : this.north;
} else if (key == "South") {
result = (this.lastLine != null ) ? this.lastLine : this.south;
} else if (key == "West") {
result = ltr ? this.firstItem : this.lastItem;
if (result == null ) {
result = this.west;
}} else if (key == "East") {
result = ltr ? this.lastItem : this.firstItem;
if (result == null ) {
result = this.east;
}} else if (key == "Center") {
result = this.center;
}if (result != null  && !result.visible ) {
result = null;
}return result;
});

Clazz.newMeth(C$, 'toString', function () {
return this.getClass().getName() + "[hgap=" + this.hgap + ",vgap=" + this.vgap + "]" ;
});
})();
//Created 2018-02-06 08:58:07
