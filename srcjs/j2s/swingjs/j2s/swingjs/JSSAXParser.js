(function(){var P$=Clazz.newPackage("swingjs"),I$=[['java.io.BufferedInputStream','javajs.util.Rdr','java.io.BufferedReader','swingjs.JSUtil','javajs.util.PT','swingjs.JSSAXContentHandler','swingjs.api.js.DOMNode','javajs.util.AU','swingjs.JSSAXAttributes','java.lang.Boolean','java.util.Hashtable']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSSAXParser", null, null, ['org.xml.sax.Parser', 'org.xml.sax.XMLReader']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.resolver = null;
this.dtdHandler = null;
this.docHandler = null;
this.contentHandler = null;
this.errorHandler = null;
this.havePre = false;
this.uniqueSeq = null;
this.ver2 = false;
this.tempChars = null;
this.props = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.tempChars = Clazz.array(Character.TYPE, [1024]);
}, 1);

Clazz.newMeth(C$, 'setLocale$java_util_Locale', function (locale) {
});

Clazz.newMeth(C$, 'setEntityResolver$org_xml_sax_EntityResolver', function (resolver) {
this.resolver = resolver;
});

Clazz.newMeth(C$, 'setDTDHandler$org_xml_sax_DTDHandler', function (handler) {
this.dtdHandler = handler;
});

Clazz.newMeth(C$, 'setDocumentHandler$org_xml_sax_DocumentHandler', function (handler) {
this.docHandler = handler;
});

Clazz.newMeth(C$, 'setErrorHandler$org_xml_sax_ErrorHandler', function (handler) {
this.errorHandler = handler;
});

Clazz.newMeth(C$, 'parse$org_xml_sax_InputSource$swingjs_JSSAXContentHandler', function (source, handler) {
this.setContentHandler$org_xml_sax_ContentHandler(handler);
p$.parseSource$org_xml_sax_InputSource.apply(this, [source]);
});

Clazz.newMeth(C$, 'parse$org_xml_sax_InputSource', function (source) {
p$.parseSource$org_xml_sax_InputSource.apply(this, [source]);
});

Clazz.newMeth(C$, 'parseSource$org_xml_sax_InputSource', function (source) {
var rdr = source.getCharacterStream();
var data = Clazz.array(java.lang.String, [1]);
if (rdr == null ) {
var bs = source.getByteStream();
if (!(Clazz.instanceOf(bs, "java.io.BufferedInputStream"))) bs = Clazz.new_((I$[1]||$incl$(1)).c$$java_io_InputStream,[bs]);
data[0] = (I$[2]||$incl$(2)).fixUTF$BA((I$[2]||$incl$(2)).getStreamAsBytes$java_io_BufferedInputStream$javajs_util_OC(bs, null));
} else {
if (!(Clazz.instanceOf(rdr, "java.io.BufferedReader"))) rdr = Clazz.new_((I$[3]||$incl$(3)).c$$java_io_Reader,[rdr]);
(I$[2]||$incl$(2)).readAllAsString$java_io_BufferedReader$I$Z$SA$I(rdr, -1, false, data, 0);
}try {
p$.parseDocument$swingjs_api_js_DOMNode.apply(this, [p$.parseXML$S.apply(this, [data[0]])]);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
p$.error$Exception.apply(this, [e]);
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'parse$S', function (fileName) {
try {
p$.parseDocument$swingjs_api_js_DOMNode.apply(this, [p$.parseXML$S.apply(this, [(I$[4]||$incl$(4)).getFileAsString$S(fileName)])]);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
p$.error$Exception.apply(this, [e]);
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'parseXMLString$S', function (data) {
try {
p$.parseDocument$swingjs_api_js_DOMNode.apply(this, [p$.parseXML$S.apply(this, [data])]);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
p$.error$Exception.apply(this, [e]);
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'parseXML$S', function (data) {
return (I$[4]||$incl$(4)).getJQuery().parseXML(p$.removeProcessing$S.apply(this, [data]));
});

Clazz.newMeth(C$, 'removeProcessing$S', function (data) {
if (data.indexOf("<?") >= 0) {
p$.getUniqueSequence$S.apply(this, [data]);
data = (I$[5]||$incl$(5)).rep$S$S$S((I$[5]||$incl$(5)).rep$S$S$S(data, "<?", "<![CDATA[" + this.uniqueSeq), "?>", "]]>");
if (data.startsWith$S("<!")) {
data = "<pre>" + data + "</pre>" ;
this.havePre = true;
}}return data;
});

Clazz.newMeth(C$, 'getUniqueSequence$S', function (data) {
var s = "~";
while (data.indexOf("<![CDATA[" + s) >= 0)s += "~";

this.uniqueSeq = s;
});

Clazz.newMeth(C$, 'error$Exception', function (e) {
var ee = Clazz.new_(Clazz.load('org.xml.sax.SAXParseException').c$$S$org_xml_sax_Locator,["Invalid Document", null]);
if (this.errorHandler == null ) throw (ee);
 else this.errorHandler.fatalError$org_xml_sax_SAXParseException(ee);
});

Clazz.newMeth(C$, 'parseDocument$swingjs_api_js_DOMNode', function (doc) {
if (this.docHandler == null  && this.contentHandler == null  ) this.contentHandler = Clazz.new_((I$[6]||$incl$(6)));
this.ver2 = (this.contentHandler != null );
if (this.ver2) this.contentHandler.startDocument();
 else this.docHandler.startDocument();
p$.walkDOMTree$swingjs_api_js_DOMNode$Z.apply(this, [(I$[7]||$incl$(7)).getAttr(doc, "firstChild"), this.havePre]);
if (this.ver2) this.contentHandler.endDocument();
 else this.docHandler.endDocument();
});

Clazz.newMeth(C$, 'walkDOMTree$swingjs_api_js_DOMNode$Z', function (node, skipTag) {
var localName = ((I$[7]||$incl$(7)).getAttr(node, "localName"));
var qName = (I$[7]||$incl$(7)).getAttr(node, "nodeName");
var uri = (I$[7]||$incl$(7)).getAttr(node, "namespaceURI");
if (localName == null ) {
var isText = "#text".equals$O(qName);
if (isText || "#cdata-section".equals$O(qName) ) {
var data = (I$[7]||$incl$(7)).getAttr(node, "textContent");
if (isText || this.uniqueSeq == null   || !data.startsWith$S(this.uniqueSeq) ) {
var len = data.length$();
var ch = this.tempChars;
if (len > ch.length) ch = this.tempChars = (I$[8]||$incl$(8)).ensureLength$O$I(ch, len * 2);
for (var i = len; --i >= 0; ) ch[i] = data.charAt(i);

if (this.ver2) this.contentHandler.characters$CA$I$I(ch, 0, len);
 else this.docHandler.characters$CA$I$I(ch, 0, len);
return;
}data = data.substring(this.uniqueSeq.length$());
var target = data + " ";
target = target.substring(0, target.indexOf(" "));
data = data.substring(target.length$()).trim();
if (this.ver2) this.contentHandler.processingInstruction$S$S(target, data);
 else this.docHandler.processingInstruction$S$S(target, data);
}} else if (!skipTag) {
var atts = Clazz.new_((I$[9]||$incl$(9)).c$$swingjs_api_js_DOMNode,[node]);
if (this.ver2) this.contentHandler.startElement$S$S$S$org_xml_sax_Attributes(uri, localName, qName, atts);
 else this.docHandler.startElement$S$org_xml_sax_AttributeList(localName, atts);
}node = (I$[7]||$incl$(7)).getAttr(node, "firstChild");
while (node != null ){
p$.walkDOMTree$swingjs_api_js_DOMNode$Z.apply(this, [node, false]);
node = (I$[7]||$incl$(7)).getAttr(node, "nextSibling");
}
if (localName == null  || skipTag ) return;
if (this.ver2) this.contentHandler.endElement$S$S$S(uri, localName, qName);
 else this.docHandler.endElement$S(localName);
});

Clazz.newMeth(C$, 'getFeature$S', function (name) {
return (this.getProperty$S("\1" + name) != null );
});

Clazz.newMeth(C$, 'setFeature$S$Z', function (name, value) {
this.setProperty$S$O("\1" + name, value ? (I$[10]||$incl$(10)).TRUE : null);
});

Clazz.newMeth(C$, 'getProperty$S', function (name) {
return (this.props == null  ? null : this.props.get$O(name));
});

Clazz.newMeth(C$, 'setProperty$S$O', function (name, value) {
if (value == null ) {
if (this.props != null ) this.props.remove$O(name);
return;
}if (this.props == null ) this.props = Clazz.new_((I$[11]||$incl$(11)));
this.props.put$TK$TV(name, value);
});

Clazz.newMeth(C$, 'getEntityResolver', function () {
return this.resolver;
});

Clazz.newMeth(C$, 'getDTDHandler', function () {
return this.dtdHandler;
});

Clazz.newMeth(C$, 'setContentHandler$org_xml_sax_ContentHandler', function (handler) {
this.contentHandler = handler;
});

Clazz.newMeth(C$, 'getContentHandler', function () {
return this.contentHandler;
});

Clazz.newMeth(C$, 'getErrorHandler', function () {
return this.errorHandler;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:18
