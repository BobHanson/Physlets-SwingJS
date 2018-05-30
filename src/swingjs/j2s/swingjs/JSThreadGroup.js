(function(){var P$=Clazz.newPackage("swingjs"),I$=[];
var C$=Clazz.newClass(P$, "JSThreadGroup", null, 'ThreadGroup');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.superclazz.c$$S.apply(this, [name]);
C$.$init$.apply(this);
{
this.html5Applet = J2S._applets[name];
}
}, 1);

Clazz.newMeth(C$, 'c$$ThreadGroup$S', function (parent, name) {
C$.superclazz.c$$ThreadGroup$S.apply(this, [parent, name]);
C$.$init$.apply(this);
{
this.html5Applet = J2S._applets[name];
}
}, 1);

Clazz.newMeth(C$, 'getHtmlApplet', function () {
return this.html5Applet;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:47
