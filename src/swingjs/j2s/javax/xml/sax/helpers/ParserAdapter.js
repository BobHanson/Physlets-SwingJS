(function(){var P$=Clazz.newPackage("javax.xml.sax.helpers"),I$=[['javax.xml.sax.helpers.ParserFactory','javax.xml.sax.helpers.AttributesImpl','javax.xml.sax.helpers.NamespaceSupport',['javax.xml.sax.helpers.ParserAdapter','.AttributeListAdapter'],'javax.xml.sax.InputSource','java.util.Vector']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ParserAdapter", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, ['javax.xml.sax.XMLReader', 'javax.xml.sax.DocumentHandler']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.nsSupport = null;
this.attAdapter = null;
this.parsing = false;
this.nameParts = null;
this.parser = null;
this.atts = null;
this.namespaces = false;
this.prefixes = false;
this.uris = false;
this.locator = null;
this.entityResolver = null;
this.dtdHandler = null;
this.contentHandler = null;
this.errorHandler = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.parsing = false;
this.nameParts = Clazz.array(java.lang.String, [3]);
this.parser = null;
this.atts = null;
this.namespaces = true;
this.prefixes = false;
this.uris = false;
this.entityResolver = null;
this.dtdHandler = null;
this.contentHandler = null;
this.errorHandler = null;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
var driver = System.getProperty("javax.xml.sax.parser");
try {
p$.setup$javax_xml_sax_Parser.apply(this, [(I$[1]||$incl$(1)).makeParser()]);
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.lang.ClassNotFoundException")){
var e1 = e$$;
{
throw Clazz.new_(Clazz.load('javax.xml.sax.SAXException').c$$S$Exception,["Cannot find SAX1 driver class " + driver, e1]);
}
} else if (Clazz.exceptionOf(e$$, "java.lang.IllegalAccessException")){
var e2 = e$$;
{
throw Clazz.new_(Clazz.load('javax.xml.sax.SAXException').c$$S$Exception,["SAX1 driver class " + driver + " found but cannot be loaded" , e2]);
}
} else if (Clazz.exceptionOf(e$$, "java.lang.InstantiationException")){
var e3 = e$$;
{
throw Clazz.new_(Clazz.load('javax.xml.sax.SAXException').c$$S$Exception,["SAX1 driver class " + driver + " loaded but cannot be instantiated" , e3]);
}
} else if (Clazz.exceptionOf(e$$, "java.lang.ClassCastException")){
var e4 = e$$;
{
throw Clazz.new_(Clazz.load('javax.xml.sax.SAXException').c$$S,["SAX1 driver class " + driver + " does not implement javax.xml.sax.Parser" ]);
}
} else if (Clazz.exceptionOf(e$$, "java.lang.NullPointerException")){
var e5 = e$$;
{
throw Clazz.new_(Clazz.load('javax.xml.sax.SAXException').c$$S,["System property javax.xml.sax.parser not specified"]);
}
} else {
throw e$$;
}
}
}, 1);

Clazz.newMeth(C$, 'c$$javax_xml_sax_Parser', function (parser) {
C$.$init$.apply(this);
p$.setup$javax_xml_sax_Parser.apply(this, [parser]);
}, 1);

Clazz.newMeth(C$, 'setup$javax_xml_sax_Parser', function (parser) {
if (parser == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["Parser argument must not be null"]);
}this.parser = parser;
this.atts = Clazz.new_((I$[2]||$incl$(2)));
this.nsSupport = Clazz.new_((I$[3]||$incl$(3)));
this.attAdapter = Clazz.new_((I$[4]||$incl$(4)), [this, null]);
});

Clazz.newMeth(C$, 'setFeature$S$Z', function (name, value) {
if (name.equals$O("http://xml.org/sax/features/namespaces")) {
p$.checkNotParsing$S$S.apply(this, ["feature", name]);
this.namespaces = value;
if (!this.namespaces && !this.prefixes ) {
this.prefixes = true;
}} else if (name.equals$O("http://xml.org/sax/features/namespace-prefixes")) {
p$.checkNotParsing$S$S.apply(this, ["feature", name]);
this.prefixes = value;
if (!this.prefixes && !this.namespaces ) {
this.namespaces = true;
}} else if (name.equals$O("http://xml.org/sax/features/xmlns-uris")) {
p$.checkNotParsing$S$S.apply(this, ["feature", name]);
this.uris = value;
} else {
throw Clazz.new_(Clazz.load('javax.xml.sax.SAXNotRecognizedException').c$$S,["Feature: " + name]);
}});

Clazz.newMeth(C$, 'getFeature$S', function (name) {
if (name.equals$O("http://xml.org/sax/features/namespaces")) {
return this.namespaces;
} else if (name.equals$O("http://xml.org/sax/features/namespace-prefixes")) {
return this.prefixes;
} else if (name.equals$O("http://xml.org/sax/features/xmlns-uris")) {
return this.uris;
} else {
throw Clazz.new_(Clazz.load('javax.xml.sax.SAXNotRecognizedException').c$$S,["Feature: " + name]);
}});

Clazz.newMeth(C$, 'setProperty$S$O', function (name, value) {
throw Clazz.new_(Clazz.load('javax.xml.sax.SAXNotRecognizedException').c$$S,["Property: " + name]);
});

Clazz.newMeth(C$, 'getProperty$S', function (name) {
throw Clazz.new_(Clazz.load('javax.xml.sax.SAXNotRecognizedException').c$$S,["Property: " + name]);
});

Clazz.newMeth(C$, 'setEntityResolver$javax_xml_sax_EntityResolver', function (resolver) {
this.entityResolver = resolver;
});

Clazz.newMeth(C$, 'getEntityResolver', function () {
return this.entityResolver;
});

Clazz.newMeth(C$, 'setDTDHandler$javax_xml_sax_DTDHandler', function (handler) {
this.dtdHandler = handler;
});

Clazz.newMeth(C$, 'getDTDHandler', function () {
return this.dtdHandler;
});

Clazz.newMeth(C$, 'setContentHandler$javax_xml_sax_ContentHandler', function (handler) {
this.contentHandler = handler;
});

Clazz.newMeth(C$, 'getContentHandler', function () {
return this.contentHandler;
});

Clazz.newMeth(C$, 'setErrorHandler$javax_xml_sax_ErrorHandler', function (handler) {
this.errorHandler = handler;
});

Clazz.newMeth(C$, 'getErrorHandler', function () {
return this.errorHandler;
});

Clazz.newMeth(C$, 'parse$S', function (systemId) {
this.parse$javax_xml_sax_InputSource(Clazz.new_((I$[5]||$incl$(5)).c$$S,[systemId]));
});

Clazz.newMeth(C$, 'parse$javax_xml_sax_InputSource', function (input) {
if (this.parsing) {
throw Clazz.new_(Clazz.load('javax.xml.sax.SAXException').c$$S,["Parser is already in use"]);
}p$.setupParser.apply(this, []);
this.parsing = true;
try {
this.parser.parse$javax_xml_sax_InputSource(input);
} finally {
this.parsing = false;
}
this.parsing = false;
});

Clazz.newMeth(C$, 'setDocumentLocator$javax_xml_sax_Locator', function (locator) {
this.locator = locator;
if (this.contentHandler != null ) {
this.contentHandler.setDocumentLocator$javax_xml_sax_Locator(locator);
}});

Clazz.newMeth(C$, 'startDocument', function () {
if (this.contentHandler != null ) {
this.contentHandler.startDocument();
}});

Clazz.newMeth(C$, 'endDocument', function () {
if (this.contentHandler != null ) {
this.contentHandler.endDocument();
}});

Clazz.newMeth(C$, 'startElement$S$javax_xml_sax_AttributeList', function (qName, qAtts) {
var exceptions = null;
if (!this.namespaces) {
if (this.contentHandler != null ) {
this.attAdapter.setAttributeList$javax_xml_sax_AttributeList(qAtts);
this.contentHandler.startElement$S$S$S$javax_xml_sax_Attributes("", "", qName.intern(), this.attAdapter);
}return;
}this.nsSupport.pushContext();
var length = qAtts.getLength();
for (var i = 0; i < length; i++) {
var attQName = qAtts.getName$I(i);
if (!attQName.startsWith$S("xmlns")) continue;
var prefix;
var n = attQName.indexOf(":");
if (n == -1 && attQName.length$() == 5 ) {
prefix = "";
} else if (n != 5) {
continue;
} else prefix = attQName.substring(n + 1);
var value = qAtts.getValue$I(i);
if (!this.nsSupport.declarePrefix$S$S(prefix, value)) {
this.reportError$S("Illegal Namespace prefix: " + prefix);
continue;
}if (this.contentHandler != null ) this.contentHandler.startPrefixMapping$S$S(prefix, value);
}
this.atts.clear();
for (var i = 0; i < length; i++) {
var attQName = qAtts.getName$I(i);
var type = qAtts.getType$I(i);
var value = qAtts.getValue$I(i);
if (attQName.startsWith$S("xmlns")) {
var prefix;
var n = attQName.indexOf(":");
if (n == -1 && attQName.length$() == 5 ) {
prefix = "";
} else if (n != 5) {
prefix = null;
} else {
prefix = attQName.substring(6);
}if (prefix != null ) {
if (this.prefixes) {
if (this.uris) this.atts.addAttribute$S$S$S$S$S("http://www.w3.org/XML/1998/namespace", prefix, attQName.intern(), type, value);
 else this.atts.addAttribute$S$S$S$S$S("", "", attQName.intern(), type, value);
}continue;
}}try {
var attName = p$.processName$S$Z$Z.apply(this, [attQName, true, true]);
this.atts.addAttribute$S$S$S$S$S(attName[0], attName[1], attName[2], type, value);
} catch (e) {
if (Clazz.exceptionOf(e, "javax.xml.sax.SAXException")){
if (exceptions == null ) exceptions = Clazz.new_((I$[6]||$incl$(6)));
exceptions.addElement$TE(e);
this.atts.addAttribute$S$S$S$S$S("", attQName, attQName, type, value);
} else {
throw e;
}
}
}
if (exceptions != null  && this.errorHandler != null  ) {
for (var i = 0; i < exceptions.size(); i++) this.errorHandler.error$javax_xml_sax_SAXParseException((exceptions.elementAt$I(i)));

}if (this.contentHandler != null ) {
var name = p$.processName$S$Z$Z.apply(this, [qName, false, false]);
this.contentHandler.startElement$S$S$S$javax_xml_sax_Attributes(name[0], name[1], name[2], this.atts);
}});

Clazz.newMeth(C$, 'endElement$S', function (qName) {
if (!this.namespaces) {
if (this.contentHandler != null ) {
this.contentHandler.endElement$S$S$S("", "", qName.intern());
}return;
}var names = p$.processName$S$Z$Z.apply(this, [qName, false, false]);
if (this.contentHandler != null ) {
this.contentHandler.endElement$S$S$S(names[0], names[1], names[2]);
var prefixes = this.nsSupport.getDeclaredPrefixes();
while (prefixes.hasMoreElements()){
var prefix = prefixes.nextElement();
this.contentHandler.endPrefixMapping$S(prefix);
}
}this.nsSupport.popContext();
});

Clazz.newMeth(C$, 'characters$CA$I$I', function (ch, start, length) {
if (this.contentHandler != null ) {
this.contentHandler.characters$CA$I$I(ch, start, length);
}});

Clazz.newMeth(C$, 'ignorableWhitespace$CA$I$I', function (ch, start, length) {
if (this.contentHandler != null ) {
this.contentHandler.ignorableWhitespace$CA$I$I(ch, start, length);
}});

Clazz.newMeth(C$, 'processingInstruction$S$S', function (target, data) {
if (this.contentHandler != null ) {
this.contentHandler.processingInstruction$S$S(target, data);
}});

Clazz.newMeth(C$, 'setupParser', function () {
if (!this.prefixes && !this.namespaces ) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
this.nsSupport.reset();
if (this.uris) this.nsSupport.setNamespaceDeclUris$Z(true);
if (this.entityResolver != null ) {
this.parser.setEntityResolver$javax_xml_sax_EntityResolver(this.entityResolver);
}if (this.dtdHandler != null ) {
this.parser.setDTDHandler$javax_xml_sax_DTDHandler(this.dtdHandler);
}if (this.errorHandler != null ) {
this.parser.setErrorHandler$javax_xml_sax_ErrorHandler(this.errorHandler);
}this.parser.setDocumentHandler$javax_xml_sax_DocumentHandler(this);
this.locator = null;
});

Clazz.newMeth(C$, 'processName$S$Z$Z', function (qName, isAttribute, useException) {
var parts = this.nsSupport.processName$S$SA$Z(qName, this.nameParts, isAttribute);
if (parts == null ) {
if (useException) throw p$.makeException$S.apply(this, ["Undeclared prefix: " + qName]);
this.reportError$S("Undeclared prefix: " + qName);
parts = Clazz.array(java.lang.String, [3]);
parts[0] = parts[1] = "";
parts[2] = qName.intern();
}return parts;
});

Clazz.newMeth(C$, 'reportError$S', function (message) {
if (this.errorHandler != null ) this.errorHandler.error$javax_xml_sax_SAXParseException(p$.makeException$S.apply(this, [message]));
});

Clazz.newMeth(C$, 'makeException$S', function (message) {
if (this.locator != null ) {
return Clazz.new_(Clazz.load('javax.xml.sax.SAXParseException').c$$S$javax_xml_sax_Locator,[message, this.locator]);
} else {
return Clazz.new_(Clazz.load('javax.xml.sax.SAXParseException').c$$S$S$S$I$I,[message, null, null, -1, -1]);
}});

Clazz.newMeth(C$, 'checkNotParsing$S$S', function (type, name) {
if (this.parsing) {
throw Clazz.new_(Clazz.load('javax.xml.sax.SAXNotSupportedException').c$$S,["Cannot change " + type + ' ' + name + " while parsing" ]);
}});
;
(function(){var C$=Clazz.newClass(P$.ParserAdapter, "AttributeListAdapter", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'javax.xml.sax.Attributes');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.qAtts = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'setAttributeList$javax_xml_sax_AttributeList', function (qAtts) {
this.qAtts = qAtts;
});

Clazz.newMeth(C$, 'getLength', function () {
return this.qAtts.getLength();
});

Clazz.newMeth(C$, 'getURI$I', function (i) {
return "";
});

Clazz.newMeth(C$, 'getLocalName$I', function (i) {
return "";
});

Clazz.newMeth(C$, 'getQName$I', function (i) {
return this.qAtts.getName$I(i).intern();
});

Clazz.newMeth(C$, 'getType$I', function (i) {
return this.qAtts.getType$I(i).intern();
});

Clazz.newMeth(C$, 'getValue$I', function (i) {
return this.qAtts.getValue$I(i);
});

Clazz.newMeth(C$, 'getIndex$S$S', function (uri, localName) {
return -1;
});

Clazz.newMeth(C$, 'getIndex$S', function (qName) {
var max = this.this$0.atts.getLength();
for (var i = 0; i < max; i++) {
if (this.qAtts.getName$I(i).equals$O(qName)) {
return i;
}}
return -1;
});

Clazz.newMeth(C$, 'getType$S$S', function (uri, localName) {
return null;
});

Clazz.newMeth(C$, 'getType$S', function (qName) {
return this.qAtts.getType$S(qName).intern();
});

Clazz.newMeth(C$, 'getValue$S$S', function (uri, localName) {
return null;
});

Clazz.newMeth(C$, 'getValue$S', function (qName) {
return this.qAtts.getValue$S(qName);
});
})()
})();
//Created 2018-05-15 01:03:02
