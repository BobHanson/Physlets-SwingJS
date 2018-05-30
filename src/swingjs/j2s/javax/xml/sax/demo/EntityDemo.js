(function(){var P$=Clazz.newPackage("javax.xml.sax.demo"),I$=[['java.io.StringReader','javax.xml.sax.helpers.ParserFactory','javax.xml.sax.InputSource','java.net.URL']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "EntityDemo", null, 'javax.xml.sax.demo.DemoHandler');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.reader = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.reader = Clazz.new_((I$[1]||$incl$(1)).c$$S,["Entity resolution works!"]);
}, 1);

Clazz.newMeth(C$, 'main', function (args) {
var parser;
var handler;
if (args.length != 1) {
System.err.println$S("Usage: java -Djavax.xml.sax.parser=<classname> EntityDemo <document>");
System.exit(2);
}parser=(I$[2]||$incl$(2)).makeParser();
handler=Clazz.new_(C$);
parser.setEntityResolver$javax_xml_sax_EntityResolver(handler);
parser.setDTDHandler$javax_xml_sax_DTDHandler(handler);
parser.setDocumentHandler$javax_xml_sax_DocumentHandler(handler);
parser.setErrorHandler$javax_xml_sax_ErrorHandler(handler);
parser.parse$S(C$.makeAbsoluteURL$S(args[0]));
}, 1);

Clazz.newMeth(C$, 'resolveEntity$S$S', function (publicId, systemId) {
if (publicId != null  && publicId.equals$O("-//megginson//TEXT Sample Entity//EN") ) {
return Clazz.new_((I$[3]||$incl$(3)).c$$java_io_Reader,[this.reader]);
} else {
return null;
}});

Clazz.newMeth(C$, 'makeAbsoluteURL$S', function (url) {
var baseURL;
var currentDirectory = System.getProperty("user.dir");
var fileSep = System.getProperty("file.separator");
var file = currentDirectory.$replace(fileSep.charAt(0), "/") + '/';
if (file.charAt(0) != "/") {
file="/" + file;
}baseURL=Clazz.new_((I$[4]||$incl$(4)).c$$S$S$S,["file", null, file]);
return Clazz.new_((I$[4]||$incl$(4)).c$$java_net_URL$S,[baseURL, url]).toString();
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:14
