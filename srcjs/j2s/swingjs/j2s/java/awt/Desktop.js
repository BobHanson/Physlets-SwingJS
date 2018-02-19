(function(){var P$=Clazz.newPackage("java.awt"),I$=[[['java.awt.Desktop','.Action'],'swingjs.JSUtil']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Desktop");
C$.desktop = null;
var p$=C$.prototype;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getDesktop', function () {
if (C$.desktop == null ) {
C$.desktop = Clazz.new_(C$);
}return C$.desktop;
}, 1);

Clazz.newMeth(C$, 'isDesktopSupported', function () {
return true;
}, 1);

Clazz.newMeth(C$, 'isSupported$java_awt_Desktop_Action', function (action) {
switch (action) {
case (I$[1]||$incl$(1)).BROWSE:
return true;
default:
return false;
}
});

Clazz.newMeth(C$, 'open$java_io_File', function (file) {
});

Clazz.newMeth(C$, 'edit$java_io_File', function (file) {
});

Clazz.newMeth(C$, 'print$java_io_File', function (file) {
});

Clazz.newMeth(C$, 'browse$java_net_URI', function (uri) {
(I$[2]||$incl$(2)).showWebPage$java_net_URL$O(uri.toURL(), null);
});

Clazz.newMeth(C$, 'mail', function () {
});

Clazz.newMeth(C$, 'mail$java_net_URI', function (mailtoURI) {
});
;
(function(){var C$=Clazz.newClass(P$.Desktop, "Action", null, 'Enum');

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "OPEN", 0, []);
Clazz.newEnumConst($vals, C$.c$, "EDIT", 1, []);
Clazz.newEnumConst($vals, C$.c$, "PRINT", 2, []);
Clazz.newEnumConst($vals, C$.c$, "MAIL", 3, []);
Clazz.newEnumConst($vals, C$.c$, "BROWSE", 4, []);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})()
})();
//Created 2018-02-08 10:01:47
