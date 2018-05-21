(function(){var P$=Clazz.newPackage("swingjs"),I$=[['swingjs.JSUtil','javajs.util.SB','swingjs.JSSAXAttributes','javajs.util.PT']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSSAXContentHandler", null, 'org.xml.sax.helpers.DefaultHandler');
C$.debugging = false;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'processingInstruction$S$S', function (target, data) {
if (C$.debugging) System.out.println$S("JSSAX <?" + target + ' ' + data + "?>" );
});

Clazz.newMeth(C$, 'startDocument', function () {
C$.debugging = (I$[1]||$incl$(1)).debugging;
if (C$.debugging) System.out.println$S("JSSAX Start document");
});

Clazz.newMeth(C$, 'startElement$S$S$S$org_xml_sax_Attributes', function (uri, localName, nodeName, atts) {
localName = p$.fixXerces$S$S.apply(this, [localName, nodeName]);
var sb = Clazz.new_((I$[2]||$incl$(2)));
sb.append$S("Start element: " + (I$[3]||$incl$(3)).getFullName$S$S$S(uri, localName, nodeName));
for (var i = 0; i < atts.getLength(); i++) sb.append$S("\n  " + (I$[3]||$incl$(3)).getFullName$S$S$S(atts.getURI$I(i), atts.getLocalName$I(i), atts.getQName$I(i)) + " = \"" + atts.getValue$I(i) + "\"" );

if (C$.debugging) System.out.println$S("JSSAX " + sb.toString());
});

Clazz.newMeth(C$, 'characters$CA$I$I', function (ch, start, length) {
var s = "";
for (var i = start; i < start + length; i++) s += ch[i];

if (C$.debugging) System.out.println$S("JSSAX Characters: " + (I$[4]||$incl$(4)).esc$S(s));
});

Clazz.newMeth(C$, 'endElement$S$S$S', function (uri, localName, nodeName) {
localName = p$.fixXerces$S$S.apply(this, [localName, nodeName]);
if (C$.debugging) System.out.println$S("JSSAX End element: " + (I$[3]||$incl$(3)).getFullName$S$S$S(uri, localName, nodeName));
});

Clazz.newMeth(C$, 'endDocument', function () {
if (C$.debugging) System.out.println$S("JSSAX End document");
});

Clazz.newMeth(C$, 'fixXerces$S$S', function (localName, nodeName) {
if (localName != null  && localName.length$() > 0  || nodeName == null   || nodeName.length$() == 0 ) return (localName == null  ? "" : localName);
var pt = nodeName.indexOf(":");
return (pt < 0 ? nodeName : nodeName.substring(0, pt));
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:16
