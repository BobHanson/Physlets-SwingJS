(function(){var P$=Clazz.newPackage("javax.xml.sax.demo"),I$=[['javax.xml.sax.helpers.ParserFactory','javax.xml.sax.demo.DemoHandler','java.io.FileInputStream','javax.xml.sax.InputSource']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ByteStreamDemo");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'main', function (args) {
var parser;
var source;
var handler;
var input;
if (args.length != 1) {
System.err.println$S("Usage: java -Djavax.xml.sax.parser=<classname> SystemIdDemo <document>");
System.exit(2);
}parser = (I$[1]||$incl$(1)).makeParser();
handler = Clazz.new_((I$[2]||$incl$(2)));
parser.setEntityResolver$javax_xml_sax_EntityResolver(handler);
parser.setDTDHandler$javax_xml_sax_DTDHandler(handler);
parser.setDocumentHandler$javax_xml_sax_DocumentHandler(handler);
parser.setErrorHandler$javax_xml_sax_ErrorHandler(handler);
input = Clazz.new_((I$[3]||$incl$(3)).c$$S,[args[0]]);
source = Clazz.new_((I$[4]||$incl$(4)).c$$java_io_InputStream,[input]);
source.setSystemId$S(args[0]);
parser.parse$javax_xml_sax_InputSource(source);
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:05
