(function(){var P$=Clazz.newPackage("a2s"),I$=[['a2s.A2SListener','javax.swing.JPanel','java.net.URL']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Applet", null, 'javax.swing.JApplet', 'a2s.A2SContainer');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.listener = null;
this.paintMeNotified = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.listener = Clazz.new_((I$[1]||$incl$(1)));
this.addMouseListener$java_awt_event_MouseListener(this.listener);
this.addMouseMotionListener$java_awt_event_MouseMotionListener(this.listener);
this.setContentPane$java_awt_Container(((
(function(){var C$=Clazz.newClass(P$, "Applet$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('javax.swing.JPanel'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['paintComponent$java_awt_Graphics','paintComponent'], function (g) {
C$.superclazz.prototype.paintComponent$java_awt_Graphics.apply(this, [g]);
try {
if (this.getWidth() > 0) this.b$['a2s.Applet'].paintComponent_$java_awt_Graphics(g);
} catch (e) {
System.out.println$S("There was a problem in Applet.paintComponent(g) " + e);
e.printStackTrace();
}
});
})()
), Clazz.new_((I$[2]||$incl$(2)), [this, null],P$.Applet$1)));
}, 1);

Clazz.newMeth(C$, 'getA2SListener', function () {
return this.listener;
});

Clazz.newMeth(C$, 'paintComponent_$java_awt_Graphics', function (g) {
if (!this.paintMeNotified) {
System.out.println$S("JComponent.paintComponent(g) has not been overridden (including a super.paintComponent(g)) \nfor the " + this.getClass().getName() + " AWT applet.\nThis many be no problem, or it may mean the applet is not displaying properly.\n See https://docs.oracle.com/javase/tutorial/uiswing/painting/refining.html\n and https://docs.oracle.com/javase/tutorial/uiswing/painting/closer.html" );
this.paintMeNotified = true;
}});

Clazz.newMeth(C$, 'getCodeBase', function () {
var codeBase = C$.superclazz.prototype.getCodeBase.apply(this, []).toString();
if (codeBase.endsWith$S("/bin/")) {
var appletPath = this.getClass().getName();
codeBase += appletPath.substring(0, appletPath.lastIndexOf(".") + 1).$replace(".", "/");
}try {
return Clazz.new_((I$[3]||$incl$(3)).c$$S,[codeBase]);
} catch (e) {
if (Clazz.exceptionOf(e, "java.net.MalformedURLException")){
return null;
} else {
throw e;
}
}
});
})();
//Created 2018-05-15 01:01:42
