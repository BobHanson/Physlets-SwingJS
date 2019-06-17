(function(){var P$=Clazz.newPackage("swingjs.a2s"),I$=[[0,'java.awt.Color','java.awt.Point','java.awt.Dimension']],$I$=function(i){return I$[i]||(I$[i]=Clazz.load(I$[0][i]))};
var C$=Clazz.newClass(P$, "ScrollPane", null, 'javax.swing.JScrollPane');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isAWT$', function () {
});

Clazz.newMeth(C$, 'isAWTContainer$', function () {
});

Clazz.newMeth(C$, 'c$', function () {
C$.c$$I.apply(this, [0]);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (scrollbars) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
switch (scrollbars) {
case 2:
this.setVerticalScrollBarPolicy$I(21);
this.setHorizontalScrollBarPolicy$I(31);
break;
case 1:
this.setVerticalScrollBarPolicy$I(22);
this.setHorizontalScrollBarPolicy$I(32);
break;
case 0:
break;
}
this.setBackground$java_awt_Color($I$(1).LIGHT_GRAY);
this.setOpaque$Z(true);
}, 1);

Clazz.newMeth(C$, 'add$java_awt_Component', function (c) {
this.getViewport$().add$java_awt_Component(c);
return c;
});

Clazz.newMeth(C$, 'getVAdjustable$', function () {
return this.getVerticalScrollBar$();
});

Clazz.newMeth(C$, 'getHAdjustable$', function () {
return this.getHorizontalScrollBar$();
});

Clazz.newMeth(C$, 'setScrollPosition$I$I', function (x, y) {
/*sync org.eclipse.jdt.core.dom.MethodInvocation*/(this.getTreeLock$());
{
if (this.getComponentCount$() == 0) {
throw Clazz.new_(Clazz.load('NullPointerException').c$$S,["child is null"]);
}this.getHorizontalScrollBar$().setValue$I(x);
this.getVerticalScrollBar$().setValue$I(y);
}});

Clazz.newMeth(C$, 'setScrollPosition$java_awt_Point', function (p) {
this.setScrollPosition$I$I(p.x, p.y);
});

Clazz.newMeth(C$, 'getScrollPosition$', function () {
/*sync org.eclipse.jdt.core.dom.MethodInvocation*/(this.getTreeLock$());
{
if (this.getComponentCount$() == 0) {
throw Clazz.new_(Clazz.load('NullPointerException').c$$S,["child is null"]);
}return Clazz.new_($I$(2).c$$I$I,[this.getHorizontalScrollBar$().getValue$(), this.getVerticalScrollBar$().getValue$()]);
}});

Clazz.newMeth(C$, 'getViewportSize$', function () {
var i=this.getInsets$();
return Clazz.new_($I$(3).c$$I$I,[this.width - i.right - i.left , this.height - i.top - i.bottom ]);
});

Clazz.newMeth(C$, 'getInsets$', function () {
return this.秘getInsetsC$();
});
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 22:44:30 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
