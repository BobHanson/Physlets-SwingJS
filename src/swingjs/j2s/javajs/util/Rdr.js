(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['java.io.InputStreamReader','java.io.BufferedInputStream','java.io.ByteArrayInputStream','javajs.util.Encoding',['javajs.util.Rdr','.StreamReader'],'java.io.BufferedReader','java.io.StringReader','javajs.util.AU','javajs.util.Base64','javajs.util.SB','javajs.api.Interface','java.io.OutputStream']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Rdr", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, 'javajs.api.GenericLineReader');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.reader = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_io_BufferedReader', function (reader) {
C$.$init$.apply(this);
this.reader = reader;
}, 1);

Clazz.newMeth(C$, 'readNextLine', function () {
return this.reader.readLine();
});

Clazz.newMeth(C$, 'readCifData$javajs_api_GenericCifDataParser$java_io_BufferedReader', function (parser, br) {
return parser.set$javajs_api_GenericLineReader$java_io_BufferedReader$Z(null, br, false).getAllCifData();
}, 1);

Clazz.newMeth(C$, 'bytesToUTF8String$BA', function (bytes) {
return C$.streamToUTF8String$java_io_BufferedInputStream(Clazz.new_((I$[2]||$incl$(2)).c$$java_io_InputStream,[Clazz.new_((I$[3]||$incl$(3)).c$$BA,[bytes])]));
}, 1);

Clazz.newMeth(C$, 'streamToUTF8String$java_io_BufferedInputStream', function (bis) {
var data = Clazz.array(java.lang.String, [1]);
try {
C$.readAllAsString$java_io_BufferedReader$I$Z$SA$I(C$.getBufferedReader$java_io_BufferedInputStream$S(bis, "UTF-8"), -1, true, data, 0);
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
} else {
throw e;
}
}
return data[0];
}, 1);

Clazz.newMeth(C$, 'getBufferedReader$java_io_BufferedInputStream$S', function (bis, charSet) {
if (C$.getUTFEncodingForStream$java_io_BufferedInputStream(bis) === (I$[4]||$incl$(4)).NONE ) return Clazz.new_((I$[5]||$incl$(5)).c$$java_io_BufferedInputStream$S,[bis, (charSet == null  ? "UTF-8" : charSet)]);
var bytes = C$.getLimitedStreamBytes$java_io_InputStream$J(bis, -1);
bis.close();
return C$.getBR$S(charSet == null  ? C$.fixUTF$BA(bytes) :  String.instantialize(bytes, charSet));
}, 1);

Clazz.newMeth(C$, 'fixUTF$BA', function (bytes) {
var encoding = C$.getUTFEncoding$BA(bytes);
if (encoding !== (I$[4]||$incl$(4)).NONE ) try {
var s =  String.instantialize(bytes, encoding.name().$replace("_", "-"));
switch (encoding) {
case (I$[4]||$incl$(4)).UTF8:
case (I$[4]||$incl$(4)).UTF_16BE:
case (I$[4]||$incl$(4)).UTF_16LE:
s = s.substring(1);
break;
default:
break;
}
return s;
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.UnsupportedEncodingException")){
System.out.println$O(e);
} else {
throw e;
}
}
return  String.instantialize(bytes);
}, 1);

Clazz.newMeth(C$, 'getUTFEncoding$BA', function (bytes) {
if (bytes.length >= 3 && (bytes[0] & 255) == 239  && (bytes[1] & 255) == 187  && (bytes[2] & 255) == 191 ) return (I$[4]||$incl$(4)).UTF8;
if (bytes.length >= 4 && (bytes[0] & 255) == 0  && (bytes[1] & 255) == 0  && (bytes[2] & 255) == 254  && (bytes[3] & 255) == 255 ) return (I$[4]||$incl$(4)).UTF_32BE;
if (bytes.length >= 4 && (bytes[0] & 255) == 255  && (bytes[1] & 255) == 254  && (bytes[2] & 255) == 0  && (bytes[3] & 255) == 0 ) return (I$[4]||$incl$(4)).UTF_32LE;
if (bytes.length >= 2 && (bytes[0] & 255) == 255  && (bytes[1] & 255) == 254 ) return (I$[4]||$incl$(4)).UTF_16LE;
if (bytes.length >= 2 && (bytes[0] & 255) == 254  && (bytes[1] & 255) == 255 ) return (I$[4]||$incl$(4)).UTF_16BE;
return (I$[4]||$incl$(4)).NONE;
}, 1);

Clazz.newMeth(C$, 'getUTFEncodingForStream$java_io_BufferedInputStream', function (is) {
var abMagic = Clazz.array(Byte.TYPE, [4]);
abMagic[3] = (1|0);
try {
is.mark$I(5);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
return (I$[4]||$incl$(4)).NONE;
} else {
throw e;
}
}
is.read$BA$I$I(abMagic, 0, 4);
is.reset();
return C$.getUTFEncoding$BA(abMagic);
}, 1);

Clazz.newMeth(C$, 'isBase64$javajs_util_SB', function (sb) {
return (sb.indexOf$S(";base64,") == 0);
}, 1);

Clazz.newMeth(C$, 'isCompoundDocumentS$java_io_InputStream', function (is) {
return C$.isCompoundDocumentB$BA(C$.getMagic$java_io_InputStream$I(is, 8));
}, 1);

Clazz.newMeth(C$, 'isCompoundDocumentB$BA', function (bytes) {
return (bytes.length >= 8 && (bytes[0] & 255) == 208  && (bytes[1] & 255) == 207  && (bytes[2] & 255) == 17  && (bytes[3] & 255) == 224  && (bytes[4] & 255) == 161  && (bytes[5] & 255) == 177  && (bytes[6] & 255) == 26  && (bytes[7] & 255) == 225 );
}, 1);

Clazz.newMeth(C$, 'isBZip2S$java_io_InputStream', function (is) {
return C$.isBZip2B$BA(C$.getMagic$java_io_InputStream$I(is, 3));
}, 1);

Clazz.newMeth(C$, 'isGzipS$java_io_InputStream', function (is) {
return C$.isGzipB$BA(C$.getMagic$java_io_InputStream$I(is, 2));
}, 1);

Clazz.newMeth(C$, 'isBZip2B$BA', function (bytes) {
return (bytes != null  && bytes.length >= 3  && (bytes[0] & 255) == 66  && (bytes[1] & 255) == 90  && (bytes[2] & 255) == 104 );
}, 1);

Clazz.newMeth(C$, 'isGzipB$BA', function (bytes) {
return (bytes != null  && bytes.length >= 2  && (bytes[0] & 255) == 31  && (bytes[1] & 255) == 139 );
}, 1);

Clazz.newMeth(C$, 'isPickleS$java_io_InputStream', function (is) {
return C$.isPickleB$BA(C$.getMagic$java_io_InputStream$I(is, 2));
}, 1);

Clazz.newMeth(C$, 'isPickleB$BA', function (bytes) {
return (bytes != null  && bytes.length >= 2  && (bytes[0] & 255) == 125  && (bytes[1] & 255) == 113 );
}, 1);

Clazz.newMeth(C$, 'isMessagePackS$java_io_InputStream', function (is) {
return C$.isMessagePackB$BA(C$.getMagic$java_io_InputStream$I(is, 2));
}, 1);

Clazz.newMeth(C$, 'isMessagePackB$BA', function (bytes) {
var b;
return (bytes != null  && bytes.length >= 1  && (((b = bytes[0] & 255)) == 222 || (b & 224) == 128 && bytes[1] != 80  ) );
}, 1);

Clazz.newMeth(C$, 'isPngZipStream$java_io_InputStream', function (is) {
return C$.isPngZipB$BA(C$.getMagic$java_io_InputStream$I(is, 55));
}, 1);

Clazz.newMeth(C$, 'isPngZipB$BA', function (bytes) {
return (bytes[50] == 0 && bytes[51] == 80  && bytes[52] == 78  && bytes[53] == 71  && bytes[54] == 74 );
}, 1);

Clazz.newMeth(C$, 'isZipS$java_io_InputStream', function (is) {
return C$.isZipB$BA(C$.getMagic$java_io_InputStream$I(is, 4));
}, 1);

Clazz.newMeth(C$, 'isZipB$BA', function (bytes) {
return (bytes.length >= 4 && bytes[0] == 80  && bytes[1] == 75  && bytes[2] == 3  && bytes[3] == 4 );
}, 1);

Clazz.newMeth(C$, 'getMagic$java_io_InputStream$I', function (is, n) {
var abMagic = Clazz.array(Byte.TYPE, [n]);
try {
is.mark$I(n + 1);
is.read$BA$I$I(abMagic, 0, n);
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
} else {
throw e;
}
}
try {
is.reset();
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
} else {
throw e;
}
}
return abMagic;
}, 1);

Clazz.newMeth(C$, 'guessMimeTypeForBytes$BA', function (bytes) {
switch (bytes.length < 2 ? ($b$[0] = -1, $b$[0]) : ($b$[0] = bytes[1], $b$[0])) {
case 0:
return "image/jpg";
case 0x49:
return "image/gif";
case 0x4D:
return "image/BMP";
case 0x50:
return "image/png";
default:
return "image/unknown";
}
}, 1);

Clazz.newMeth(C$, 'getBIS$BA', function (bytes) {
return Clazz.new_((I$[2]||$incl$(2)).c$$java_io_InputStream,[Clazz.new_((I$[3]||$incl$(3)).c$$BA,[bytes])]);
}, 1);

Clazz.newMeth(C$, 'getBR$S', function (string) {
return Clazz.new_((I$[6]||$incl$(6)).c$$java_io_Reader,[Clazz.new_((I$[7]||$incl$(7)).c$$S,[string])]);
}, 1);

Clazz.newMeth(C$, 'toBIS$O', function (o) {
return ((I$[8]||$incl$(8)).isAB$O(o) ? C$.getBIS$BA(o) : Clazz.instanceOf(o, "javajs.util.SB") ? C$.getBIS$BA(C$.getBytesFromSB$javajs_util_SB(o)) : Clazz.instanceOf(o, "java.lang.String") ? C$.getBIS$BA((o).getBytes()) : null);
}, 1);

Clazz.newMeth(C$, 'getUnzippedInputStream$javajs_api_GenericZipTools$java_io_BufferedInputStream', function (jzt, bis) {
while (C$.isGzipS$java_io_InputStream(bis))bis = Clazz.new_((I$[2]||$incl$(2)).c$$java_io_InputStream,[jzt.newGZIPInputStream$java_io_InputStream(bis)]);

return bis;
}, 1);

Clazz.newMeth(C$, 'getUnzippedInputStreamBZip2$javajs_api_GenericZipTools$java_io_BufferedInputStream', function (jzt, bis) {
while (C$.isBZip2S$java_io_InputStream(bis))bis = Clazz.new_((I$[2]||$incl$(2)).c$$java_io_InputStream,[jzt.newBZip2InputStream$java_io_InputStream(bis)]);

return bis;
}, 1);

Clazz.newMeth(C$, 'getBytesFromSB$javajs_util_SB', function (sb) {
return (C$.isBase64$javajs_util_SB(sb) ? (I$[9]||$incl$(9)).decodeBase64$S(sb.substring$I(8)) : sb.toBytes$I$I(0, -1));
}, 1);

Clazz.newMeth(C$, 'getStreamAsBytes$java_io_BufferedInputStream$javajs_util_OC', function (bis, out) {
var buf = Clazz.array(Byte.TYPE, [1024]);
var bytes = (out == null  ? Clazz.array(Byte.TYPE, [4096]) : null);
var len = 0;
var totalLen = 0;
while ((len = bis.read$BA$I$I(buf, 0, 1024)) > 0){
totalLen = totalLen+(len);
if (out == null ) {
if (totalLen >= bytes.length) bytes = (I$[8]||$incl$(8)).ensureLengthByte$BA$I(bytes, totalLen * 2);
System.arraycopy(buf, 0, bytes, totalLen - len, len);
} else {
out.write$BA$I$I(buf, 0, len);
}}
bis.close();
if (out == null ) {
return (I$[8]||$incl$(8)).arrayCopyByte$BA$I(bytes, totalLen);
}return totalLen + " bytes";
}, 1);

Clazz.newMeth(C$, 'getLimitedStreamBytes$java_io_InputStream$J', function (is, n) {
var buflen = (n > 0 && n < 1024  ? (n|0) : 1024);
var buf = Clazz.array(Byte.TYPE, [buflen]);
var bytes = Clazz.array(Byte.TYPE, [n < 0 ? 4096 : (n|0)]);
var len = 0;
var totalLen = 0;
if (n < 0) n = 2147483647;
while (totalLen < n && (len = is.read$BA$I$I(buf, 0, buflen)) > 0 ){
totalLen = totalLen+(len);
if (totalLen > bytes.length) bytes = (I$[8]||$incl$(8)).ensureLengthByte$BA$I(bytes, totalLen * 2);
System.arraycopy(buf, 0, bytes, totalLen - len, len);
if (n != 2147483647 && totalLen + buflen > bytes.length ) buflen = bytes.length - totalLen;
}
if (totalLen == bytes.length) return bytes;
buf = Clazz.array(Byte.TYPE, [totalLen]);
System.arraycopy(bytes, 0, buf, 0, totalLen);
return buf;
}, 1);

Clazz.newMeth(C$, 'readAllAsString$java_io_BufferedReader$I$Z$SA$I', function (br, nBytesMax, allowBinary, data, i) {
try {
var sb = (I$[10]||$incl$(10)).newN$I(8192);
var line;
if (nBytesMax < 0) {
line = br.readLine();
if (allowBinary || line != null  && line.indexOf("\u0000") < 0  && (line.length$() != 4 || (line.charCodeAt(0)) != 65533   || line.indexOf("PNG") != 1 )  ) {
sb.append$S(line).appendC$C("\u000a");
while ((line = br.readLine()) != null )sb.append$S(line).appendC$C("\u000a");

}} else {
var n = 0;
var len;
while (n < nBytesMax && (line = br.readLine()) != null  ){
if (nBytesMax - n < (len = line.length$()) + 1) line = line.substring(0, nBytesMax - n - 1 );
sb.append$S(line).appendC$C("\u000a");
n = n+(len + 1);
}
}br.close();
data[i] = sb.toString();
return true;
} catch (ioe) {
if (Clazz.exceptionOf(ioe, "java.lang.Exception")){
data[i] = ioe.toString();
return false;
} else {
throw ioe;
}
}
}, 1);

Clazz.newMeth(C$, 'getPngZipPointAndCount$java_io_BufferedInputStream$IA', function (bis, pt_count) {
bis.mark$I(75);
try {
var data = C$.getLimitedStreamBytes$java_io_InputStream$J(bis, 74);
bis.reset();
var pt = 0;
for (var i = 64, f = 1; --i > 54; f = f*(10)) pt = pt+((data[i] - 48) * f);

var n = 0;
for (var i = 74, f = 1; --i > 64; f = f*(10)) n = n+((data[i] - 48) * f);

pt_count[0] = pt;
pt_count[1] = n;
} catch (e) {
pt_count[1] = 0;
}
}, 1);

Clazz.newMeth(C$, 'getPngZipStream$java_io_BufferedInputStream$Z', function (bis, asNewStream) {
if (!C$.isPngZipStream$java_io_InputStream(bis)) return bis;
var data = Clazz.array(Byte.TYPE, [0]);
bis.mark$I(75);
try {
var pt_count = Clazz.array(Integer.TYPE, [2]);
C$.getPngZipPointAndCount$java_io_BufferedInputStream$IA(bis, pt_count);
if (pt_count[1] != 0) {
var pt = pt_count[0];
while (pt > 0)pt = pt-(bis.skip$J(pt));

if (!asNewStream) return bis;
data = C$.getLimitedStreamBytes$java_io_InputStream$J(bis, pt_count[1]);
}} catch (e) {
} finally {
try {
if (asNewStream) bis.close();
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
} else {
throw e;
}
}
}
return C$.getBIS$BA(data);
}, 1);

Clazz.newMeth(C$, 'getZipRoot$S', function (fileName) {
var pt = fileName.indexOf("|");
return (pt < 0 ? fileName : fileName.substring(0, pt));
}, 1);

Clazz.newMeth(C$, 'getBufferedWriter$java_io_OutputStream$S', function (os, charSetName) {
var osw = (I$[11]||$incl$(11)).getInstanceWithParams$S$ClassA$OA("java.io.OutputStreamWriter", Clazz.array(java.lang.Class, -1, [Clazz.getClass((I$[12]||$incl$(12))), Clazz.getClass(java.lang.String)]), Clazz.array(java.lang.Object, -1, [os, charSetName == null  ? "UTF-8" : charSetName]));
{
return osw.getBufferedWriter();
}
}, 1);
var $b$ = new Int8Array(1);
;
(function(){var C$=Clazz.newClass(P$.Rdr, "StreamReader", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.io.BufferedReader');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.stream = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_io_BufferedInputStream$S', function (bis, charSet) {
C$.superclazz.c$$java_io_Reader.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$java_io_InputStream$S,[bis, (charSet == null  ? "UTF-8" : charSet)])]);
C$.$init$.apply(this);
this.stream = bis;
}, 1);

Clazz.newMeth(C$, 'getStream', function () {
try {
this.stream.reset();
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
} else {
throw e;
}
}
return this.stream;
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:19
