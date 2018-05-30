(function(){var P$=Clazz.newPackage("org.xml.sax.demo"),I$=[['org.xml.sax.helpers.ParserFactory','org.xml.sax.demo.DemoHandler','java.io.StringReader','org.xml.sax.InputSource']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "CharacterStreamDemo");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'main', function (args) {
var handler;
var source;
var parser;
var reader;
if (args.length != 0) {
System.err.println$S("Usage: java CharTest");
System.exit(2);
}parser=(I$[1]||$incl$(1)).makeParser();
handler=Clazz.new_((I$[2]||$incl$(2)));
parser.setEntityResolver$org_xml_sax_EntityResolver(handler);
parser.setDTDHandler$org_xml_sax_DTDHandler(handler);
parser.setDocumentHandler$org_xml_sax_DocumentHandler(handler);
parser.setErrorHandler$org_xml_sax_ErrorHandler(handler);
reader=Clazz.new_((I$[3]||$incl$(3)).c$$S,["<?xml version=\"1.0\"?><doc>\u000a<title>Hello</title>\u000a<para>Hello, world!</para>\u000a</doc>\u000a"]);
parser.parse$org_xml_sax_InputSource(Clazz.new_((I$[4]||$incl$(4)).c$$java_io_Reader,[reader]));
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:17
