(function(){var P$=Clazz.newPackage("org.xml.sax"),I$=[];
var C$=Clazz.newClass(P$, "SAXParseException", null, 'org.xml.sax.SAXException');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.publicId = null;
this.systemId = null;
this.lineNumber = 0;
this.columnNumber = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$org_xml_sax_Locator', function (message, locator) {
C$.superclazz.c$$S.apply(this, [message]);
C$.$init$.apply(this);
if (locator != null ) {
p$.init$S$S$I$I.apply(this, [locator.getPublicId(), locator.getSystemId(), locator.getLineNumber(), locator.getColumnNumber()]);
} else {
p$.init$S$S$I$I.apply(this, [null, null, -1, -1]);
}}, 1);

Clazz.newMeth(C$, 'c$$S$org_xml_sax_Locator$Exception', function (message, locator, e) {
C$.superclazz.c$$S$Exception.apply(this, [message, e]);
C$.$init$.apply(this);
if (locator != null ) {
p$.init$S$S$I$I.apply(this, [locator.getPublicId(), locator.getSystemId(), locator.getLineNumber(), locator.getColumnNumber()]);
} else {
p$.init$S$S$I$I.apply(this, [null, null, -1, -1]);
}}, 1);

Clazz.newMeth(C$, 'c$$S$S$S$I$I', function (message, publicId, systemId, lineNumber, columnNumber) {
C$.superclazz.c$$S.apply(this, [message]);
C$.$init$.apply(this);
p$.init$S$S$I$I.apply(this, [publicId, systemId, lineNumber, columnNumber]);
}, 1);

Clazz.newMeth(C$, 'c$$S$S$S$I$I$Exception', function (message, publicId, systemId, lineNumber, columnNumber, e) {
C$.superclazz.c$$S$Exception.apply(this, [message, e]);
C$.$init$.apply(this);
p$.init$S$S$I$I.apply(this, [publicId, systemId, lineNumber, columnNumber]);
}, 1);

Clazz.newMeth(C$, 'init$S$S$I$I', function (publicId, systemId, lineNumber, columnNumber) {
this.publicId = publicId;
this.systemId = systemId;
this.lineNumber = lineNumber;
this.columnNumber = columnNumber;
});

Clazz.newMeth(C$, 'getPublicId', function () {
return this.publicId;
});

Clazz.newMeth(C$, 'getSystemId', function () {
return this.systemId;
});

Clazz.newMeth(C$, 'getLineNumber', function () {
return this.lineNumber;
});

Clazz.newMeth(C$, 'getColumnNumber', function () {
return this.columnNumber;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:04