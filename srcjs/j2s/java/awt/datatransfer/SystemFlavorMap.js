(function(){var P$=Clazz.newPackage("java.awt.datatransfer"),I$=[['java.util.HashMap','java.util.HashSet','Thread','java.awt.datatransfer.MimeType','sun.awt.datatransfer.DataTransferer','java.awt.datatransfer.DataFlavor','java.lang.StringBuilder','java.util.ArrayList','java.lang.ref.SoftReference','java.util.LinkedList']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SystemFlavorMap", null, null, ['java.awt.datatransfer.FlavorMap', 'java.awt.datatransfer.FlavorTable']);
C$.JavaMIME = null;
C$.flavorMaps = null;
C$.UNICODE_TEXT_CLASSES = null;
C$.ENCODED_TEXT_CLASSES = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.JavaMIME = "JAVA_DATAFLAVOR:";
C$.flavorMaps = Clazz.new_((I$[1]||$incl$(1)));
C$.UNICODE_TEXT_CLASSES = Clazz.array(java.lang.String, -1, ["java.io.Reader", "java.lang.String", "java.nio.CharBuffer", "\"[C\""]);
C$.ENCODED_TEXT_CLASSES = Clazz.array(java.lang.String, -1, ["java.io.InputStream", "java.nio.ByteBuffer", "\"[B\""]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.nativeToFlavor = null;
this.flavorToNative = null;
this.getNativesForFlavorCache = null;
this.getFlavorsForNativeCache = null;
this.disabledMappingGenerationKeys = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.nativeToFlavor = Clazz.new_((I$[1]||$incl$(1)));
this.flavorToNative = Clazz.new_((I$[1]||$incl$(1)));
this.getNativesForFlavorCache = Clazz.new_((I$[1]||$incl$(1)));
this.getFlavorsForNativeCache = Clazz.new_((I$[1]||$incl$(1)));
this.disabledMappingGenerationKeys = Clazz.new_((I$[2]||$incl$(2)));
}, 1);

Clazz.newMeth(C$, 'getDefaultFlavorMap', function () {
var contextClassLoader = (I$[3]||$incl$(3)).currentThread().getContextClassLoader();
if (contextClassLoader == null ) {
contextClassLoader = ClassLoader.getSystemClassLoader();
}var fm;
{
fm = C$.flavorMaps.get$O(contextClassLoader);
if (fm == null ) {
fm = Clazz.new_(C$);
C$.flavorMaps.put$TK$TV(contextClassLoader, fm);
}}return fm;
}, 1);

Clazz.newMeth(C$, 'parseAndStoreReader$java_io_BufferedReader', function ($in) {
while (true){
var line = $in.readLine();
if (line == null ) {
return;
}if (line.length$() > 0) {
var firstChar = line.charAt(0);
if (firstChar != "#" && firstChar != "!" ) {
while (p$.continueLine$S.apply(this, [line])){
var nextLine = $in.readLine();
if (nextLine == null ) {
nextLine =  String.instantialize("");
}var loppedLine = line.substring(0, line.length$() - 1);
var startIndex = 0;
for (; startIndex < nextLine.length$(); startIndex++) {
if (" \u0009\u000d\u000a\u000c".indexOf(nextLine.charAt(startIndex)) == -1) {
break;
}}
nextLine = nextLine.substring(startIndex, nextLine.length$());
line =  String.instantialize(loppedLine + nextLine);
}
var len = line.length$();
var keyStart = 0;
for (; keyStart < len; keyStart++) {
if (" \u0009\u000d\u000a\u000c".indexOf(line.charAt(keyStart)) == -1) {
break;
}}
if (keyStart == len) {
continue;
}var separatorIndex = keyStart;
for (; separatorIndex < len; separatorIndex++) {
var currentChar = line.charAt(separatorIndex);
if (currentChar == "\\") {
separatorIndex++;
} else if ("=: \u0009\u000d\u000a\u000c".indexOf(currentChar) != -1) {
break;
}}
var valueIndex = separatorIndex;
for (; valueIndex < len; valueIndex++) {
if (" \u0009\u000d\u000a\u000c".indexOf(line.charAt(valueIndex)) == -1) {
break;
}}
if (valueIndex < len) {
if ("=:".indexOf(line.charAt(valueIndex)) != -1) {
valueIndex++;
}}while (valueIndex < len){
if (" \u0009\u000d\u000a\u000c".indexOf(line.charAt(valueIndex)) == -1) {
break;
}valueIndex++;
}
var key = line.substring(keyStart, separatorIndex);
var value = (separatorIndex < len) ? line.substring(valueIndex, len) : "";
key = p$.loadConvert$S.apply(this, [key]);
value = p$.loadConvert$S.apply(this, [value]);
try {
var mime = Clazz.new_((I$[4]||$incl$(4)).c$$S,[value]);
if ("text".equals$O(mime.getPrimaryType())) {
var charset = mime.getParameter$S("charset");
if ((I$[5]||$incl$(5)).doesSubtypeSupportCharset$S$S(mime.getSubType(), charset)) {
var transferer = (I$[5]||$incl$(5)).getInstance();
if (transferer != null ) {
transferer.registerTextFlavorProperties$S$S$S$S(key, charset, mime.getParameter$S("eoln"), mime.getParameter$S("terminators"));
}}mime.removeParameter$S("charset");
mime.removeParameter$S("class");
mime.removeParameter$S("eoln");
mime.removeParameter$S("terminators");
value = mime.toString();
}} catch (e) {
if (Clazz.exceptionOf(e, "java.awt.datatransfer.MimeTypeParseException")){
e.printStackTrace();
continue;
} else {
throw e;
}
}
var flavor;
try {
flavor = Clazz.new_((I$[6]||$incl$(6)).c$$S,[value]);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
try {
flavor = Clazz.new_((I$[6]||$incl$(6)).c$$S$S,[value, null]);
} catch (ee) {
if (Clazz.exceptionOf(ee, "java.lang.Exception")){
ee.printStackTrace();
continue;
} else {
throw ee;
}
}
} else {
throw e;
}
}
if ("text".equals$O(flavor.getPrimaryType())) {
p$.store$O$O$java_util_Map.apply(this, [value, key, this.flavorToNative]);
p$.store$O$O$java_util_Map.apply(this, [key, value, this.nativeToFlavor]);
} else {
p$.store$O$O$java_util_Map.apply(this, [flavor, key, this.flavorToNative]);
p$.store$O$O$java_util_Map.apply(this, [key, flavor, this.nativeToFlavor]);
}}}}
});

Clazz.newMeth(C$, 'continueLine$S', function (line) {
var slashCount = 0;
var index = line.length$() - 1;
while ((index >= 0) && (line.charAt(index--) == "\\") ){
slashCount++;
}
return (slashCount % 2 == 1);
});

Clazz.newMeth(C$, 'loadConvert$S', function (theString) {
var aChar;
var len = theString.length$();
var outBuffer = Clazz.new_((I$[7]||$incl$(7)).c$$I,[len]);
for (var x = 0; x < len; ) {
aChar = theString.charAt(x++);
if (aChar == "\\") {
aChar = theString.charAt(x++);
if (aChar == "u") {
var value = 0;
for (var i = 0; i < 4; i++) {
aChar = theString.charAt(x++);
switch (aChar.$c()) {
case 48:
case 49:
case 50:
case 51:
case 52:
case 53:
case 54:
case 55:
case 56:
case 57:
{
value = (value << 4) + aChar.$c() - 48;
break;
}case 97:
case 98:
case 99:
case 100:
case 101:
case 102:
{
value = (value << 4) + 10 + aChar.$c()  - 97;
break;
}case 65:
case 66:
case 67:
case 68:
case 69:
case 70:
{
value = (value << 4) + 10 + aChar.$c()  - 65;
break;
}default:
{
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Malformed \\uxxxx encoding."]);
}}
}
outBuffer.append$C(String.fromCharCode(value));
} else {
if (aChar == "t") {
aChar = "\u0009";
} else if (aChar == "r") {
aChar = "\u000d";
} else if (aChar == "n") {
aChar = "\u000a";
} else if (aChar == "f") {
aChar = "\u000c";
}outBuffer.append$C(aChar);
}} else {
outBuffer.append$C(aChar);
}}
return outBuffer.toString();
});

Clazz.newMeth(C$, 'store$O$O$java_util_Map', function (hashed, listed, map) {
var list = map.get$O(hashed);
if (list == null ) {
list = Clazz.new_((I$[8]||$incl$(8)).c$$I,[1]);
map.put$TK$TV(hashed, list);
}if (!list.contains$O(listed)) {
list.add$TE(listed);
}});

Clazz.newMeth(C$, 'nativeToFlavorLookup$S', function (nat) {
var flavors = this.nativeToFlavor.get$O(nat);
if (nat != null  && !this.disabledMappingGenerationKeys.contains$O(nat) ) {
var transferer = (I$[5]||$incl$(5)).getInstance();
if (transferer != null ) {
var platformFlavors = transferer.getPlatformMappingsForNative$S(nat);
if (!platformFlavors.isEmpty()) {
if (flavors != null ) {
platformFlavors.removeAll$java_util_Collection(Clazz.new_((I$[2]||$incl$(2)).c$$java_util_Collection,[flavors]));
platformFlavors.addAll$java_util_Collection(flavors);
}flavors = platformFlavors;
}}}if (flavors == null  && C$.isJavaMIMEType$S(nat) ) {
var decoded = C$.decodeJavaMIMEType$S(nat);
var flavor = null;
try {
flavor = Clazz.new_((I$[6]||$incl$(6)).c$$S,[decoded]);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.err.println$S("Exception \"" + e.getClass().getName() + ": " + e.getMessage() + "\"while constructing DataFlavor for: " + decoded );
} else {
throw e;
}
}
if (flavor != null ) {
flavors = Clazz.new_((I$[8]||$incl$(8)).c$$I,[1]);
this.nativeToFlavor.put$TK$TV(nat, flavors);
flavors.add$TE(flavor);
this.getFlavorsForNativeCache.remove$O(nat);
this.getFlavorsForNativeCache.remove$O(null);
var natives = this.flavorToNative.get$O(flavor);
if (natives == null ) {
natives = Clazz.new_((I$[8]||$incl$(8)).c$$I,[1]);
this.flavorToNative.put$TK$TV(flavor, natives);
}natives.add$TE(nat);
this.getNativesForFlavorCache.remove$O(flavor);
this.getNativesForFlavorCache.remove$O(null);
}}return (flavors != null ) ? flavors : Clazz.new_((I$[8]||$incl$(8)).c$$I,[0]);
});

Clazz.newMeth(C$, 'flavorToNativeLookup$java_awt_datatransfer_DataFlavor$Z', function (flav, synthesize) {
var natives = this.flavorToNative.get$O(flav);
if (flav != null  && !this.disabledMappingGenerationKeys.contains$O(flav) ) {
var transferer = (I$[5]||$incl$(5)).getInstance();
if (transferer != null ) {
var platformNatives = transferer.getPlatformMappingsForFlavor$java_awt_datatransfer_DataFlavor(flav);
if (!platformNatives.isEmpty()) {
if (natives != null ) {
platformNatives.removeAll$java_util_Collection(Clazz.new_((I$[2]||$incl$(2)).c$$java_util_Collection,[natives]));
platformNatives.addAll$java_util_Collection(natives);
}natives = platformNatives;
}}}if (natives == null ) {
if (synthesize) {
var encoded = C$.encodeDataFlavor$java_awt_datatransfer_DataFlavor(flav);
natives = Clazz.new_((I$[8]||$incl$(8)).c$$I,[1]);
this.flavorToNative.put$TK$TV(flav, natives);
natives.add$TE(encoded);
this.getNativesForFlavorCache.remove$O(flav);
this.getNativesForFlavorCache.remove$O(null);
var flavors = this.nativeToFlavor.get$O(encoded);
if (flavors == null ) {
flavors = Clazz.new_((I$[8]||$incl$(8)).c$$I,[1]);
this.nativeToFlavor.put$TK$TV(encoded, flavors);
}flavors.add$TE(flav);
this.getFlavorsForNativeCache.remove$O(encoded);
this.getFlavorsForNativeCache.remove$O(null);
} else {
natives = Clazz.new_((I$[8]||$incl$(8)).c$$I,[0]);
}}return natives;
});

Clazz.newMeth(C$, 'getNativesForFlavor$java_awt_datatransfer_DataFlavor', function (flav) {
var retval = null;
var ref = this.getNativesForFlavorCache.get$O(flav);
if (ref != null ) {
retval = ref.get();
if (retval != null ) {
return Clazz.new_((I$[8]||$incl$(8)).c$$java_util_Collection,[retval]);
}}if (flav == null ) {
retval = Clazz.new_((I$[8]||$incl$(8)).c$$java_util_Collection,[this.nativeToFlavor.keySet()]);
} else if (this.disabledMappingGenerationKeys.contains$O(flav)) {
retval = p$.flavorToNativeLookup$java_awt_datatransfer_DataFlavor$Z.apply(this, [flav, false]);
} else if ((I$[5]||$incl$(5)).isFlavorCharsetTextType$java_awt_datatransfer_DataFlavor(flav)) {
if ("text".equals$O(flav.getPrimaryType())) {
retval = this.flavorToNative.get$O(flav.mimeType.getBaseType());
if (retval != null ) {
retval = Clazz.new_((I$[8]||$incl$(8)).c$$java_util_Collection,[retval]);
}}var textPlainList = this.flavorToNative.get$O("text/plain");
if (textPlainList != null  && !textPlainList.isEmpty() ) {
textPlainList = Clazz.new_((I$[8]||$incl$(8)).c$$java_util_Collection,[textPlainList]);
if (retval != null  && !retval.isEmpty() ) {
textPlainList.removeAll$java_util_Collection(Clazz.new_((I$[2]||$incl$(2)).c$$java_util_Collection,[retval]));
retval.addAll$java_util_Collection(textPlainList);
} else {
retval = textPlainList;
}}if (retval == null  || retval.isEmpty() ) {
retval = p$.flavorToNativeLookup$java_awt_datatransfer_DataFlavor$Z.apply(this, [flav, true]);
} else {
var explicitList = p$.flavorToNativeLookup$java_awt_datatransfer_DataFlavor$Z.apply(this, [flav, false]);
if (!explicitList.isEmpty()) {
explicitList = Clazz.new_((I$[8]||$incl$(8)).c$$java_util_Collection,[explicitList]);
explicitList.removeAll$java_util_Collection(Clazz.new_((I$[2]||$incl$(2)).c$$java_util_Collection,[retval]));
retval.addAll$java_util_Collection(explicitList);
}}} else if ((I$[5]||$incl$(5)).isFlavorNoncharsetTextType$java_awt_datatransfer_DataFlavor(flav)) {
retval = this.flavorToNative.get$O(flav.mimeType.getBaseType());
if (retval == null  || retval.isEmpty() ) {
retval = p$.flavorToNativeLookup$java_awt_datatransfer_DataFlavor$Z.apply(this, [flav, true]);
} else {
var explicitList = p$.flavorToNativeLookup$java_awt_datatransfer_DataFlavor$Z.apply(this, [flav, false]);
if (!explicitList.isEmpty()) {
retval = Clazz.new_((I$[8]||$incl$(8)).c$$java_util_Collection,[retval]);
explicitList = Clazz.new_((I$[8]||$incl$(8)).c$$java_util_Collection,[explicitList]);
explicitList.removeAll$java_util_Collection(Clazz.new_((I$[2]||$incl$(2)).c$$java_util_Collection,[retval]));
retval.addAll$java_util_Collection(explicitList);
}}} else {
retval = p$.flavorToNativeLookup$java_awt_datatransfer_DataFlavor$Z.apply(this, [flav, true]);
}this.getNativesForFlavorCache.put$TK$TV(flav, Clazz.new_((I$[9]||$incl$(9)).c$$TT,[retval]));
return Clazz.new_((I$[8]||$incl$(8)).c$$java_util_Collection,[retval]);
});

Clazz.newMeth(C$, 'getFlavorsForNative$S', function (nat) {
var ref = this.getFlavorsForNativeCache.get$O(nat);
if (ref != null ) {
var retval = ref.get();
if (retval != null ) {
return retval.clone();
}}var retval = Clazz.new_((I$[10]||$incl$(10)));
if (nat == null ) {
var natives = this.getNativesForFlavor$java_awt_datatransfer_DataFlavor(null);
var dups = Clazz.new_((I$[2]||$incl$(2)).c$$I,[natives.size()]);
for (var natives_iter = natives.iterator(); natives_iter.hasNext(); ) {
var flavors = this.getFlavorsForNative$S(natives_iter.next());
for (var flavors_iter = flavors.iterator(); flavors_iter.hasNext(); ) {
var flavor = flavors_iter.next();
if (dups.add$TE(flavor)) {
retval.add$TE(flavor);
}}
}
} else {
var flavors = p$.nativeToFlavorLookup$S.apply(this, [nat]);
if (this.disabledMappingGenerationKeys.contains$O(nat)) {
return flavors;
}var dups = Clazz.new_((I$[2]||$incl$(2)).c$$I,[flavors.size()]);
var flavorsAndbaseTypes = p$.nativeToFlavorLookup$S.apply(this, [nat]);
for (var flavorsAndbaseTypes_iter = flavorsAndbaseTypes.iterator(); flavorsAndbaseTypes_iter.hasNext(); ) {
var value = flavorsAndbaseTypes_iter.next();
if (Clazz.instanceOf(value, "java.lang.String")) {
var baseType = value;
var subType = null;
try {
var mimeType = Clazz.new_((I$[4]||$incl$(4)).c$$S,[baseType]);
subType = mimeType.getSubType();
} catch (mtpe) {
if (Clazz.exceptionOf(mtpe, "java.awt.datatransfer.MimeTypeParseException")){
Clazz.assert(C$, this, function(){return false});
} else {
throw mtpe;
}
}
if ((I$[5]||$incl$(5)).doesSubtypeSupportCharset$S$S(subType, null)) {
if ("text/plain".equals$O(baseType) && dups.add$TE((I$[6]||$incl$(6)).stringFlavor) ) {
retval.add$TE((I$[6]||$incl$(6)).stringFlavor);
}for (var i = 0; i < C$.UNICODE_TEXT_CLASSES.length; i++) {
var toAdd = null;
try {
toAdd = Clazz.new_((I$[6]||$incl$(6)).c$$S,[baseType + ";charset=Unicode;class=" + C$.UNICODE_TEXT_CLASSES[i] ]);
} catch (cannotHappen) {
if (Clazz.exceptionOf(cannotHappen, "java.lang.ClassNotFoundException")){
} else {
throw cannotHappen;
}
}
if (dups.add$TE(toAdd)) {
retval.add$TE(toAdd);
}}
for (var charset_iter = (I$[5]||$incl$(5)).standardEncodings(); charset_iter.hasNext(); ) {
var charset = charset_iter.next();
for (var i = 0; i < C$.ENCODED_TEXT_CLASSES.length; i++) {
var toAdd = null;
try {
toAdd = Clazz.new_((I$[6]||$incl$(6)).c$$S,[baseType + ";charset=" + charset + ";class=" + C$.ENCODED_TEXT_CLASSES[i] ]);
} catch (cannotHappen) {
if (Clazz.exceptionOf(cannotHappen, "java.lang.ClassNotFoundException")){
} else {
throw cannotHappen;
}
}
if (toAdd.equals$java_awt_datatransfer_DataFlavor((I$[6]||$incl$(6)).plainTextFlavor)) {
toAdd = (I$[6]||$incl$(6)).plainTextFlavor;
}if (dups.add$TE(toAdd)) {
retval.add$TE(toAdd);
}}
}
if ("text/plain".equals$O(baseType) && dups.add$TE((I$[6]||$incl$(6)).plainTextFlavor) ) {
retval.add$TE((I$[6]||$incl$(6)).plainTextFlavor);
}} else {
for (var i = 0; i < C$.ENCODED_TEXT_CLASSES.length; i++) {
var toAdd = null;
try {
toAdd = Clazz.new_((I$[6]||$incl$(6)).c$$S,[baseType + ";class=" + C$.ENCODED_TEXT_CLASSES[i] ]);
} catch (cannotHappen) {
if (Clazz.exceptionOf(cannotHappen, "java.lang.ClassNotFoundException")){
} else {
throw cannotHappen;
}
}
if (dups.add$TE(toAdd)) {
retval.add$TE(toAdd);
}}
}} else {
var flavor = value;
if (dups.add$TE(flavor)) {
retval.add$TE(flavor);
}}}
}var arrayList = Clazz.new_((I$[8]||$incl$(8)).c$$java_util_Collection,[retval]);
this.getFlavorsForNativeCache.put$TK$TV(nat, Clazz.new_((I$[9]||$incl$(9)).c$$TT,[arrayList]));
return arrayList.clone();
});

Clazz.newMeth(C$, 'getNativesForFlavors$java_awt_datatransfer_DataFlavorA', function (flavors) {
if (flavors == null ) {
var flavor_list = this.getFlavorsForNative$S(null);
flavors = Clazz.array((I$[6]||$incl$(6)), [flavor_list.size()]);
flavor_list.toArray$TTA(flavors);
}var retval = Clazz.new_((I$[1]||$incl$(1)).c$$I$F,[flavors.length, 1.0]);
for (var i = 0; i < flavors.length; i++) {
var natives = this.getNativesForFlavor$java_awt_datatransfer_DataFlavor(flavors[i]);
var nat = (natives.isEmpty()) ? null : natives.get$I(0);
retval.put$TK$TV(flavors[i], nat);
}
return retval;
});

Clazz.newMeth(C$, 'getFlavorsForNatives$SA', function (natives) {
if (natives == null ) {
var native_list = this.getNativesForFlavor$java_awt_datatransfer_DataFlavor(null);
natives = Clazz.array(java.lang.String, [native_list.size()]);
native_list.toArray$TTA(natives);
}var retval = Clazz.new_((I$[1]||$incl$(1)).c$$I$F,[natives.length, 1.0]);
for (var i = 0; i < natives.length; i++) {
var flavors = this.getFlavorsForNative$S(natives[i]);
var flav = (flavors.isEmpty()) ? null : flavors.get$I(0);
retval.put$TK$TV(natives[i], flav);
}
return retval;
});

Clazz.newMeth(C$, 'addUnencodedNativeForFlavor$java_awt_datatransfer_DataFlavor$S', function (flav, nat) {
if (flav == null  || nat == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["null arguments not permitted"]);
}var natives = this.flavorToNative.get$O(flav);
if (natives == null ) {
natives = Clazz.new_((I$[8]||$incl$(8)).c$$I,[1]);
this.flavorToNative.put$TK$TV(flav, natives);
} else if (natives.contains$O(nat)) {
return;
}natives.add$TE(nat);
this.getNativesForFlavorCache.remove$O(flav);
this.getNativesForFlavorCache.remove$O(null);
});

Clazz.newMeth(C$, 'setNativesForFlavor$java_awt_datatransfer_DataFlavor$SA', function (flav, natives) {
if (flav == null  || natives == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["null arguments not permitted"]);
}this.flavorToNative.remove$O(flav);
for (var i = 0; i < natives.length; i++) {
this.addUnencodedNativeForFlavor$java_awt_datatransfer_DataFlavor$S(flav, natives[i]);
}
this.disabledMappingGenerationKeys.add$TE(flav);
this.getNativesForFlavorCache.remove$O(flav);
this.getNativesForFlavorCache.remove$O(null);
});

Clazz.newMeth(C$, 'addFlavorForUnencodedNative$S$java_awt_datatransfer_DataFlavor', function (nat, flav) {
if (nat == null  || flav == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["null arguments not permitted"]);
}var flavors = this.nativeToFlavor.get$O(nat);
if (flavors == null ) {
flavors = Clazz.new_((I$[8]||$incl$(8)).c$$I,[1]);
this.nativeToFlavor.put$TK$TV(nat, flavors);
} else if (flavors.contains$O(flav)) {
return;
}flavors.add$TE(flav);
this.getFlavorsForNativeCache.remove$O(nat);
this.getFlavorsForNativeCache.remove$O(null);
});

Clazz.newMeth(C$, 'setFlavorsForNative$S$java_awt_datatransfer_DataFlavorA', function (nat, flavors) {
if (nat == null  || flavors == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["null arguments not permitted"]);
}this.nativeToFlavor.remove$O(nat);
for (var i = 0; i < flavors.length; i++) {
this.addFlavorForUnencodedNative$S$java_awt_datatransfer_DataFlavor(nat, flavors[i]);
}
this.disabledMappingGenerationKeys.add$TE(nat);
this.getFlavorsForNativeCache.remove$O(nat);
this.getFlavorsForNativeCache.remove$O(null);
});

Clazz.newMeth(C$, 'encodeJavaMIMEType$S', function (mimeType) {
return (mimeType != null ) ? C$.JavaMIME + mimeType : null;
}, 1);

Clazz.newMeth(C$, 'encodeDataFlavor$java_awt_datatransfer_DataFlavor', function (flav) {
return (flav != null ) ? C$.encodeJavaMIMEType$S(flav.getMimeType()) : null;
}, 1);

Clazz.newMeth(C$, 'isJavaMIMEType$S', function (str) {
return (str != null  && str.startsWith$S$I(C$.JavaMIME, 0) );
}, 1);

Clazz.newMeth(C$, 'decodeJavaMIMEType$S', function (nat) {
return (C$.isJavaMIMEType$S(nat)) ? nat.substring(C$.JavaMIME.length$(), nat.length$()).trim() : null;
}, 1);

Clazz.newMeth(C$, 'decodeDataFlavor$S', function (nat) {
var retval_str = C$.decodeJavaMIMEType$S(nat);
return (retval_str != null ) ? Clazz.new_((I$[6]||$incl$(6)).c$$S,[retval_str]) : null;
}, 1);
C$.$_ASSERT_ENABLED_ = ClassLoader.$getClassAssertionStatus(C$);

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:16