(function(){var P$=Clazz.newPackage("a2s"),I$=[['a2s.A2SListener']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Frame", null, 'javax.swing.JFrame', 'a2s.A2SContainer');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.listener = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getA2SListener', function () {
return null;
});

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.listener = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'c$$S', function (title) {
C$.superclazz.c$$S.apply(this, [title]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_GraphicsConfiguration', function (gc) {
C$.superclazz.c$$java_awt_GraphicsConfiguration.apply(this, [gc]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$java_awt_GraphicsConfiguration', function (title, gc) {
C$.superclazz.c$$S$java_awt_GraphicsConfiguration.apply(this, [title, gc]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'remove$I', function (i) {
{
this.removeInt(i);
}
});

Clazz.newMeth(C$, 'setMenuBar$a2s_MenuBar', function (m) {
this.setJMenuBar$javax_swing_JMenuBar(m);
});

Clazz.newMeth(C$, 'unsetMenuBar', function () {
this.setJMenuBar$javax_swing_JMenuBar(null);
});

Clazz.newMeth(C$, 'getMenubar', function () {
return this.getJMenuBar();
});
})();
//Created 2018-02-08 10:01:39
