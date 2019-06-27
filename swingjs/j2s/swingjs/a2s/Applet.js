(function(){var P$=Clazz.newPackage("swingjs.a2s"),I$=[[0,'swingjs.a2s.A2SContainer','swingjs.a2s.A2SListener','java.awt.FlowLayout','java.net.URL']],$I$=function(i){return I$[i]||(I$[i]=Clazz.load(I$[0][i]))};
var C$=Clazz.newClass(P$, "Applet", null, 'javax.swing.JApplet', 'swingjs.a2s.A2SContainer');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.listener=null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, ['isAWT$','isAWT'], function () {
});

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
$I$(1).fixAWTPaint$java_awt_Component$Class(this, Clazz.getClass(C$));
this.listener=Clazz.new_($I$(2));
this.addMouseListener$java_awt_event_MouseListener(this.listener);
this.addMouseMotionListener$java_awt_event_MouseMotionListener(this.listener);
this.setLayout$java_awt_LayoutManager(Clazz.new_($I$(3)));
(this.getContentPane$()).setOpaque$Z(false);
}, 1);

Clazz.newMeth(C$, ['setBackground$java_awt_Color','setBackground'], function (c) {
C$.superclazz.prototype.setBackground$java_awt_Color.apply(this, [c]);
this.getContentPane$().setBackground$java_awt_Color(c);
});

Clazz.newMeth(C$, ['getA2SListener$','getA2SListener'], function () {
return this.listener;
});

Clazz.newMeth(C$, ['getCodeBase$','getCodeBase'], function () {
var codeBase=C$.superclazz.prototype.getCodeBase$.apply(this, []).toString();
if (codeBase.endsWith$S("/bin/")) {
var appletPath=this.getClass$().getName$();
codeBase += appletPath.substring$I$I(0, appletPath.lastIndexOf$S(".") + 1).replace$C$C(".", "/");
}try {
return Clazz.new_($I$(4).c$$S,[codeBase]);
} catch (e) {
if (Clazz.exceptionOf(e,"java.net.MalformedURLException")){
return null;
} else {
throw e;
}
}
});
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 22:44:37 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
