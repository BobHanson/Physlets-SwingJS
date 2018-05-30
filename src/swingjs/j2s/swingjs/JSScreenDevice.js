(function(){var P$=Clazz.newPackage("swingjs"),I$=[['java.awt.GraphicsConfiguration','swingjs.JSToolkit']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSScreenDevice", null, 'java.awt.GraphicsDevice');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
System.out.println$S("JSScreenDevice initialized");
}, 1);

Clazz.newMeth(C$, 'getType', function () {
return 0;
});

Clazz.newMeth(C$, 'getIDstring', function () {
return "swingjs.JSScreenDevice";
});

Clazz.newMeth(C$, 'getConfigurations', function () {
return Clazz.array((I$[1]||$incl$(1)), -1, [this.getDefaultConfiguration()]);
});

Clazz.newMeth(C$, 'getDefaultConfiguration', function () {
return (I$[2]||$incl$(2)).getGraphicsConfiguration();
});
})();
//Created 2018-05-24 08:47:47
