(function(){var P$=Clazz.newPackage("javax.xml.sax.helpers"),I$=[['java.util.Vector','javax.xml.sax.helpers.NamespaceSupport','java.util.Hashtable',['javax.xml.sax.helpers.NamespaceSupport','.Context']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "NamespaceSupport", function(){
Clazz.newInstance(this, arguments,0,C$);
});
C$.EMPTY_ENUMERATION = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.EMPTY_ENUMERATION = Clazz.new_((I$[1]||$incl$(1))).elements();
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.contexts = null;
this.currentContext = null;
this.contextPos = 0;
this.namespaceDeclUris = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.reset();
}, 1);

Clazz.newMeth(C$, 'reset', function () {
this.contexts = Clazz.array((I$[4]||$incl$(4)), [32]);
this.namespaceDeclUris = false;
this.contextPos = 0;
this.contexts[this.contextPos] = this.currentContext = Clazz.new_((I$[4]||$incl$(4)), [this, null]);
this.currentContext.declarePrefix$S$S("xml", "http://www.w3.org/XML/1998/namespace");
});

Clazz.newMeth(C$, 'pushContext', function () {
var max = this.contexts.length;
this.contexts[this.contextPos].declsOK = false;
this.contextPos++;
if (this.contextPos >= max) {
var newContexts = Clazz.array((I$[4]||$incl$(4)), [max * 2]);
System.arraycopy(this.contexts, 0, newContexts, 0, max);
max = max*(2);
this.contexts = newContexts;
}this.currentContext = this.contexts[this.contextPos];
if (this.currentContext == null ) {
this.contexts[this.contextPos] = this.currentContext = Clazz.new_((I$[4]||$incl$(4)), [this, null]);
}if (this.contextPos > 0) {
this.currentContext.setParent$javax_xml_sax_helpers_NamespaceSupport_Context(this.contexts[this.contextPos - 1]);
}});

Clazz.newMeth(C$, 'popContext', function () {
this.contexts[this.contextPos].clear();
this.contextPos--;
if (this.contextPos < 0) {
throw Clazz.new_(Clazz.load('java.util.EmptyStackException'));
}this.currentContext = this.contexts[this.contextPos];
});

Clazz.newMeth(C$, 'declarePrefix$S$S', function (prefix, uri) {
if (prefix.equals$O("xml") || prefix.equals$O("xmlns") ) {
return false;
} else {
this.currentContext.declarePrefix$S$S(prefix, uri);
return true;
}});

Clazz.newMeth(C$, 'processName$S$SA$Z', function (qName, parts, isAttribute) {
var myParts = this.currentContext.processName$S$Z(qName, isAttribute);
if (myParts == null ) {
return null;
} else {
parts[0] = myParts[0];
parts[1] = myParts[1];
parts[2] = myParts[2];
return parts;
}});

Clazz.newMeth(C$, 'getURI$S', function (prefix) {
return this.currentContext.getURI$S(prefix);
});

Clazz.newMeth(C$, 'getPrefixes', function () {
return this.currentContext.getPrefixes();
});

Clazz.newMeth(C$, 'getPrefix$S', function (uri) {
return this.currentContext.getPrefix$S(uri);
});

Clazz.newMeth(C$, 'getPrefixes$S', function (uri) {
var prefixes = Clazz.new_((I$[1]||$incl$(1)));
var allPrefixes = this.getPrefixes();
while (allPrefixes.hasMoreElements()){
var prefix = allPrefixes.nextElement();
if (uri.equals$O(this.getURI$S(prefix))) {
prefixes.addElement$TE(prefix);
}}
return prefixes.elements();
});

Clazz.newMeth(C$, 'getDeclaredPrefixes', function () {
return this.currentContext.getDeclaredPrefixes();
});

Clazz.newMeth(C$, 'setNamespaceDeclUris$Z', function (value) {
if (this.contextPos != 0) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException'));
if (value == this.namespaceDeclUris ) return;
this.namespaceDeclUris = value;
if (value) this.currentContext.declarePrefix$S$S("xmlns", "http://www.w3.org/xmlns/2000/");
 else {
this.contexts[this.contextPos] = this.currentContext = Clazz.new_((I$[4]||$incl$(4)), [this, null]);
this.currentContext.declarePrefix$S$S("xml", "http://www.w3.org/XML/1998/namespace");
}});

Clazz.newMeth(C$, 'isNamespaceDeclUris', function () {
return this.namespaceDeclUris;
});
;
(function(){var C$=Clazz.newClass(P$.NamespaceSupport, "Context", function(){
Clazz.newInstance(this, arguments[0],true,C$);
});
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.prefixTable = null;
this.uriTable = null;
this.elementNameTable = null;
this.attributeNameTable = null;
this.defaultNS = null;
this.declsOK = false;
this.declarations = null;
this.declSeen = false;
this.parent = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.defaultNS = null;
this.declsOK = true;
this.declarations = null;
this.declSeen = false;
this.parent = null;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
p$.copyTables.apply(this, []);
}, 1);

Clazz.newMeth(C$, 'setParent$javax_xml_sax_helpers_NamespaceSupport_Context', function (parent) {
this.parent = parent;
this.declarations = null;
this.prefixTable = parent.prefixTable;
this.uriTable = parent.uriTable;
this.elementNameTable = parent.elementNameTable;
this.attributeNameTable = parent.attributeNameTable;
this.defaultNS = parent.defaultNS;
this.declSeen = false;
this.declsOK = true;
});

Clazz.newMeth(C$, 'clear', function () {
this.parent = null;
this.prefixTable = null;
this.uriTable = null;
this.elementNameTable = null;
this.attributeNameTable = null;
this.defaultNS = null;
});

Clazz.newMeth(C$, 'declarePrefix$S$S', function (prefix, uri) {
if (!this.declsOK) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["can\'t declare any more prefixes in this context"]);
if (!this.declSeen) {
p$.copyTables.apply(this, []);
}if (this.declarations == null ) {
this.declarations = Clazz.new_((I$[1]||$incl$(1)));
}prefix = prefix.intern();
uri = uri.intern();
if ("".equals$O(prefix)) {
if ("".equals$O(uri)) {
this.defaultNS = null;
} else {
this.defaultNS = uri;
}} else {
this.prefixTable.put$TK$TV(prefix, uri);
this.uriTable.put$TK$TV(uri, prefix);
}this.declarations.addElement$TE(prefix);
});

Clazz.newMeth(C$, 'processName$S$Z', function (qName, isAttribute) {
var name;
var table;
this.declsOK = false;
if (isAttribute) {
table = this.attributeNameTable;
} else {
table = this.elementNameTable;
}name = table.get$O(qName);
if (name != null ) {
return name;
}name = Clazz.array(java.lang.String, [3]);
name[2] = qName.intern();
var index = qName.indexOf(":");
if (index == -1) {
if (isAttribute) {
if (qName == "xmlns" && this.this$0.namespaceDeclUris ) name[0] = "http://www.w3.org/xmlns/2000/";
 else name[0] = "";
} else if (this.defaultNS == null ) {
name[0] = "";
} else {
name[0] = this.defaultNS;
}name[1] = name[2];
} else {
var prefix = qName.substring(0, index);
var local = qName.substring(index + 1);
var uri;
if ("".equals$O(prefix)) {
uri = this.defaultNS;
} else {
uri = this.prefixTable.get$O(prefix);
}if (uri == null  || (!isAttribute && "xmlns".equals$O(prefix) ) ) {
return null;
}name[0] = uri;
name[1] = local.intern();
}table.put$TK$TV(name[2], name);
return name;
});

Clazz.newMeth(C$, 'getURI$S', function (prefix) {
if ("".equals$O(prefix)) {
return this.defaultNS;
} else if (this.prefixTable == null ) {
return null;
} else {
return this.prefixTable.get$O(prefix);
}});

Clazz.newMeth(C$, 'getPrefix$S', function (uri) {
if (this.uriTable == null ) {
return null;
} else {
return this.uriTable.get$O(uri);
}});

Clazz.newMeth(C$, 'getDeclaredPrefixes', function () {
if (this.declarations == null ) {
return (I$[2]||$incl$(2)).EMPTY_ENUMERATION;
} else {
return this.declarations.elements();
}});

Clazz.newMeth(C$, 'getPrefixes', function () {
if (this.prefixTable == null ) {
return (I$[2]||$incl$(2)).EMPTY_ENUMERATION;
} else {
return this.prefixTable.keys();
}});

Clazz.newMeth(C$, 'copyTables', function () {
if (this.prefixTable != null ) {
this.prefixTable = this.prefixTable.clone();
} else {
this.prefixTable = Clazz.new_((I$[3]||$incl$(3)));
}if (this.uriTable != null ) {
this.uriTable = this.uriTable.clone();
} else {
this.uriTable = Clazz.new_((I$[3]||$incl$(3)));
}this.elementNameTable = Clazz.new_((I$[3]||$incl$(3)));
this.attributeNameTable = Clazz.new_((I$[3]||$incl$(3)));
this.declSeen = true;
});
})()
})();
//Created 2018-02-08 10:03:03