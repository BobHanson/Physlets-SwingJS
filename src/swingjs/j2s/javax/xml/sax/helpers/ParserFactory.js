(function(){var P$=Clazz.newPackage("javax.xml.sax.helpers"),I$=[['javax.xml.sax.helpers.NewInstance']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ParserFactory");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'makeParser', function () {
var className = System.getProperty("javax.xml.sax.parser");
if (className == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["No value for sax.parser property"]);
} else {
return C$.makeParser$S(className);
}}, 1);

Clazz.newMeth(C$, 'makeParser$S', function (className) {
return (I$[1]||$incl$(1)).newInstance$ClassLoader$S((I$[1]||$incl$(1)).getClassLoader(), className);
}, 1);
})();
//Created 2018-05-15 01:03:02
