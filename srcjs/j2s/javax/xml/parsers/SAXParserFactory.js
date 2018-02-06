(function(){var P$=Clazz.newPackage("javax.xml.parsers"),I$=[];
var C$=Clazz.newClass(P$, "SAXParserFactory");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'newInstance', function () {
return Clazz.new_(C$);
}, 1);

Clazz.newMeth(C$, 'newSAXParser', function () {
try {
return C$.makeParser();
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
return null;
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'makeParser', function () {
var className = System.getProperty("org.xml.sax.parser", "swingjs.JSSAXParser");
if (className == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["No value for sax.parser property"]);
} else {
return C$.makeParser$S(className);
}}, 1);

Clazz.newMeth(C$, 'makeParser$S', function (className) {
return (Clazz.forName(className).newInstance());
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:04
