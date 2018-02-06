(function(){var P$=Clazz.newPackage("javax.swing.colorchooser"),I$=[['java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "CenterLayout", null, null, 'java.awt.LayoutManager');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'addLayoutComponent$S$java_awt_Component', function (name, comp) {
});

Clazz.newMeth(C$, 'removeLayoutComponent$java_awt_Component', function (comp) {
});

Clazz.newMeth(C$, 'preferredLayoutSize$java_awt_Container', function (container) {
var c = container.getComponent$I(0);
if (c != null ) {
var size = c.getPreferredSize();
var insets = container.getInsets();
size.width = size.width+(insets.left + insets.right);
size.height = size.height+(insets.top + insets.bottom);
return size;
} else {
return Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[0, 0]);
}});

Clazz.newMeth(C$, 'minimumLayoutSize$java_awt_Container', function (cont) {
return this.preferredLayoutSize$java_awt_Container(cont);
});

Clazz.newMeth(C$, 'layoutContainer$java_awt_Container', function (container) {
try {
var c = container.getComponent$I(0);
c.setSize$java_awt_Dimension(c.getPreferredSize());
var size = c.getSize();
var containerSize = container.getSize();
var containerInsets = container.getInsets();
containerSize.width = containerSize.width-(containerInsets.left + containerInsets.right);
containerSize.height = containerSize.height-(containerInsets.top + containerInsets.bottom);
var componentLeft = ((containerSize.width/2|0)) - ((size.width/2|0));
var componentTop = ((containerSize.height/2|0)) - ((size.height/2|0));
componentLeft = componentLeft+(containerInsets.left);
componentTop = componentTop+(containerInsets.top);
c.setBounds$I$I$I$I(componentLeft, componentTop, size.width, size.height);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
} else {
throw e;
}
}
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:46
