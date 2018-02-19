(function(){var P$=Clazz.newPackage("org.xml.sax.demo"),I$=[['java.io.StringReader','org.xml.sax.helpers.ParserFactory','org.xml.sax.InputSource','java.net.URL']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "EntityDemo", null, 'org.xml.sax.demo.DemoHandler');
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
System.err.println$S("Usage: java -Dorg.xml.sax.parser=<classname> EntityDemo <document>");
System.exit(2);
}parser = (I$[2]||$incl$(2)).makeParser();
handler = Clazz.new_(C$);
parser.setEntityResolver$org_xml_sax_EntityResolver(handler);
parser.setDTDHandler$org_xml_sax_DTDHandler(handler);
parser.setDocumentHandler$org_xml_sax_DocumentHandler(handler);
parser.setErrorHandler$org_xml_sax_ErrorHandler(handler);
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
file = "/" + file;
}baseURL = Clazz.new_((I$[4]||$incl$(4)).c$$S$S$S,["file", null, file]);
return Clazz.new_((I$[4]||$incl$(4)).c$$java_net_URL$S,[baseURL, url]).toString();
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:06
