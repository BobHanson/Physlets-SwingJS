(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['javajs.api.GenericZipInputStream','java.io.BufferedInputStream','javajs.util.SB','javajs.util.PT','javajs.util.Rdr','java.util.zip.ZipInputStream','javajs.util.Lst','java.util.zip.GZIPInputStream','javajs.api.Interface','java.util.zip.ZipEntry','java.util.zip.ZipOutputStream','java.util.zip.CRC32','javajs.util.BArray','java.lang.Boolean']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ZipTools", null, null, 'javajs.api.GenericZipTools');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'newZipInputStream$java_io_InputStream', function (is) {
return C$.newZIS$java_io_InputStream(is);
});

Clazz.newMeth(C$, 'newZIS$java_io_InputStream', function (is) {
return (Clazz.instanceOf(is, "javajs.api.ZInputStream") ? is : Clazz.instanceOf(is, "java.io.BufferedInputStream") ? Clazz.new_((I$[1]||$incl$(1)).c$$java_io_InputStream,[is]) : Clazz.new_((I$[1]||$incl$(1)).c$$java_io_InputStream,[Clazz.new_((I$[2]||$incl$(2)).c$$java_io_InputStream,[is])]));
}, 1);

Clazz.newMeth(C$, 'getAllZipData$java_io_InputStream$SA$S$S$S$java_util_Map', function (is, subfileList, name0, binaryFileList, exclude, fileData) {
var zis = C$.newZIS$java_io_InputStream(is);
var ze;
var listing = Clazz.new_((I$[3]||$incl$(3)));
binaryFileList = "|" + binaryFileList + "|" ;
var prefix = (I$[4]||$incl$(4)).join$SA$C$I(subfileList, "/", 1);
var prefixd = null;
if (prefix != null ) {
prefixd = prefix.substring(0, prefix.indexOf("/") + 1);
if (prefixd.length$() == 0) prefixd = null;
}try {
while ((ze = zis.getNextEntry()) != null ){
var name = ze.getName();
if (prefix != null  && prefixd != null   && !(name.equals$O(prefix) || name.startsWith$S(prefixd) )  || exclude != null  && name.contains$CharSequence(exclude)  ) continue;
listing.append$S(name).appendC$C("\u000a");
var sname = "|" + name.substring(name.lastIndexOf("/") + 1) + "|" ;
var asBinaryString = (binaryFileList.indexOf(sname) >= 0);
var bytes = (I$[5]||$incl$(5)).getLimitedStreamBytes$java_io_InputStream$J(zis, ze.getSize());
var str;
if (asBinaryString) {
str = p$.getBinaryStringForBytes$BA.apply(this, [bytes]);
name += ":asBinaryString";
} else {
str = (I$[5]||$incl$(5)).fixUTF$BA(bytes);
}str = "BEGIN Directory Entry " + name + "\n" + str + "\nEND Directory Entry " + name + "\n" ;
var key = name0 + "|" + name ;
fileData.put$TK$TV(key, str);
}
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
} else {
throw e;
}
}
fileData.put$TK$TV("#Directory_Listing", listing.toString());
});

Clazz.newMeth(C$, 'getBinaryStringForBytes$BA', function (bytes) {
var ret = Clazz.new_((I$[3]||$incl$(3)));
for (var i = 0; i < bytes.length; i++) ret.append$S(Integer.toHexString(bytes[i] & 255)).appendC$C(" ");

return ret.toString();
});

Clazz.newMeth(C$, 'getZipFileDirectory$java_io_BufferedInputStream$SA$I$Z', function (bis, list, listPtr, asBufferedInputStream) {
var ret;
if (list == null  || listPtr >= list.length ) return this.getZipDirectoryAsStringAndClose$java_io_BufferedInputStream(bis);
bis = (I$[5]||$incl$(5)).getPngZipStream$java_io_BufferedInputStream$Z(bis, true);
var fileName = list[listPtr];
var zis = Clazz.new_((I$[6]||$incl$(6)).c$$java_io_InputStream,[bis]);
var ze;
try {
var isAll = (fileName.equals$O("."));
if (isAll || fileName.lastIndexOf("/") == fileName.length$() - 1 ) {
ret = Clazz.new_((I$[3]||$incl$(3)));
while ((ze = zis.getNextEntry()) != null ){
var name = ze.getName();
if (isAll || name.startsWith$S(fileName) ) ret.append$S(name).appendC$C("\u000a");
}
var str = ret.toString();
return (asBufferedInputStream ? (I$[5]||$incl$(5)).getBIS$BA(str.getBytes()) : str);
}var pt = fileName.indexOf(":asBinaryString");
var asBinaryString = (pt > 0);
if (asBinaryString) fileName = fileName.substring(0, pt);
fileName = fileName.$replace("\\", "/");
while ((ze = zis.getNextEntry()) != null  && !fileName.equals$O(ze.getName()) ){
}
var bytes = (ze == null  ? null : (I$[5]||$incl$(5)).getLimitedStreamBytes$java_io_InputStream$J(zis, ze.getSize()));
ze = null;
zis.close();
if (bytes == null ) return "";
if ((I$[5]||$incl$(5)).isZipB$BA(bytes) || (I$[5]||$incl$(5)).isPngZipB$BA(bytes) ) return this.getZipFileDirectory$java_io_BufferedInputStream$SA$I$Z((I$[5]||$incl$(5)).getBIS$BA(bytes), list, ++listPtr, asBufferedInputStream);
if (asBufferedInputStream) return (I$[5]||$incl$(5)).getBIS$BA(bytes);
if (asBinaryString) {
ret = Clazz.new_((I$[3]||$incl$(3)));
for (var i = 0; i < bytes.length; i++) ret.append$S(Integer.toHexString(bytes[i] & 255)).appendC$C(" ");

return ret.toString();
}if ((I$[5]||$incl$(5)).isGzipB$BA(bytes)) bytes = (I$[5]||$incl$(5)).getLimitedStreamBytes$java_io_InputStream$J(this.getUnGzippedInputStream$BA(bytes), -1);
return (I$[5]||$incl$(5)).fixUTF$BA(bytes);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
return "";
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'getZipFileContentsAsBytes$java_io_BufferedInputStream$SA$I', function (bis, list, listPtr) {
var ret = Clazz.array(Byte.TYPE, [0]);
var fileName = list[listPtr];
if (fileName.lastIndexOf("/") == fileName.length$() - 1) return ret;
try {
bis = (I$[5]||$incl$(5)).getPngZipStream$java_io_BufferedInputStream$Z(bis, true);
var zis = Clazz.new_((I$[6]||$incl$(6)).c$$java_io_InputStream,[bis]);
var ze;
while ((ze = zis.getNextEntry()) != null ){
if (!fileName.equals$O(ze.getName())) continue;
var bytes = (I$[5]||$incl$(5)).getLimitedStreamBytes$java_io_InputStream$J(zis, ze.getSize());
return (((I$[5]||$incl$(5)).isZipB$BA(bytes) || (I$[5]||$incl$(5)).isPngZipB$BA(bytes) ) && ++listPtr < list.length  ? this.getZipFileContentsAsBytes$java_io_BufferedInputStream$SA$I((I$[5]||$incl$(5)).getBIS$BA(bytes), list, listPtr) : bytes);
}
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
} else {
throw e;
}
}
return ret;
});

Clazz.newMeth(C$, 'getZipDirectoryAsStringAndClose$java_io_BufferedInputStream', function (bis) {
var sb = Clazz.new_((I$[3]||$incl$(3)));
var s = Clazz.array(java.lang.String, [0]);
try {
s = p$.getZipDirectoryOrErrorAndClose$java_io_BufferedInputStream$S.apply(this, [bis, null]);
bis.close();
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S(e.toString());
} else {
throw e;
}
}
for (var i = 0; i < s.length; i++) sb.append$S(s[i]).appendC$C("\u000a");

return sb.toString();
});

Clazz.newMeth(C$, 'getZipDirectoryAndClose$java_io_BufferedInputStream$S', function (bis, manifestID) {
var s = Clazz.array(java.lang.String, [0]);
try {
s = p$.getZipDirectoryOrErrorAndClose$java_io_BufferedInputStream$S.apply(this, [bis, manifestID]);
bis.close();
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S(e.toString());
} else {
throw e;
}
}
return s;
});

Clazz.newMeth(C$, 'getZipDirectoryOrErrorAndClose$java_io_BufferedInputStream$S', function (bis, manifestID) {
bis = (I$[5]||$incl$(5)).getPngZipStream$java_io_BufferedInputStream$Z(bis, true);
var v = Clazz.new_((I$[7]||$incl$(7)));
var zis = Clazz.new_((I$[6]||$incl$(6)).c$$java_io_InputStream,[bis]);
var ze;
var manifest = null;
while ((ze = zis.getNextEntry()) != null ){
var fileName = ze.getName();
if (manifestID != null  && fileName.startsWith$S(manifestID) ) manifest = C$.getStreamAsString$java_io_InputStream(zis);
 else if (!fileName.startsWith$S("__MACOS")) v.addLast$TV(fileName);
}
zis.close();
if (manifestID != null ) v.add$I$TE(0, manifest == null  ? "" : manifest + "\n############\n");
return v.toArray$TTA(Clazz.array(java.lang.String, [v.size()]));
});

Clazz.newMeth(C$, 'getStreamAsString$java_io_InputStream', function (is) {
return (I$[5]||$incl$(5)).fixUTF$BA((I$[5]||$incl$(5)).getLimitedStreamBytes$java_io_InputStream$J(is, -1));
}, 1);

Clazz.newMeth(C$, 'newGZIPInputStream$java_io_InputStream', function (is) {
return Clazz.new_((I$[2]||$incl$(2)).c$$java_io_InputStream,[Clazz.new_((I$[8]||$incl$(8)).c$$java_io_InputStream$I,[is, 512])]);
});

Clazz.newMeth(C$, 'newBZip2InputStream$java_io_InputStream', function (is) {
return Clazz.new_((I$[2]||$incl$(2)).c$$java_io_InputStream,[((I$[9]||$incl$(9)).getInterface$S("org.apache.tools.bzip2.CBZip2InputStreamFactory")).getStream$java_io_InputStream(is)]);
});

Clazz.newMeth(C$, 'getUnGzippedInputStream$BA', function (bytes) {
try {
return (I$[5]||$incl$(5)).getUnzippedInputStream$javajs_api_GenericZipTools$java_io_BufferedInputStream(this, (I$[5]||$incl$(5)).getBIS$BA(bytes));
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
return null;
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'addZipEntry$O$S', function (zos, fileName) {
(zos).putNextEntry$java_util_zip_ZipEntry(Clazz.new_((I$[10]||$incl$(10)).c$$S,[fileName]));
});

Clazz.newMeth(C$, 'closeZipEntry$O', function (zos) {
(zos).closeEntry();
});

Clazz.newMeth(C$, 'getZipOutputStream$O', function (bos) {
return Clazz.new_((I$[11]||$incl$(11)).c$$java_io_OutputStream,[bos]);
});

Clazz.newMeth(C$, 'getCrcValue$BA', function (bytes) {
var crc = Clazz.new_((I$[12]||$incl$(12)));
crc.update$BA$I$I(bytes, 0, bytes.length);
return (crc.getValue()|0);
});

Clazz.newMeth(C$, 'readFileAsMap$java_io_BufferedInputStream$java_util_Map$S', function (bis, bdata, name) {
C$.readFileAsMapStatic$java_io_BufferedInputStream$java_util_Map$S(bis, bdata, name);
});

Clazz.newMeth(C$, 'readFileAsMapStatic$java_io_BufferedInputStream$java_util_Map$S', function (bis, bdata, name) {
var pt = (name == null  ? -1 : name.indexOf("|"));
name = (pt >= 0 ? name.substring(pt + 1) : null);
try {
if ((I$[5]||$incl$(5)).isPngZipStream$java_io_InputStream(bis)) {
var isImage = "_IMAGE_".equals$O(name);
if (name == null  || isImage ) bdata.put$TK$TV((isImage ? "_DATA_" : "_IMAGE_"), Clazz.new_((I$[13]||$incl$(13)).c$$BA,[C$.getPngImageBytes$java_io_BufferedInputStream(bis)]));
if (!isImage) C$.cacheZipContentsStatic$java_io_BufferedInputStream$S$java_util_Map$Z(bis, name, bdata, true);
} else if ((I$[5]||$incl$(5)).isZipS$java_io_InputStream(bis)) {
C$.cacheZipContentsStatic$java_io_BufferedInputStream$S$java_util_Map$Z(bis, name, bdata, true);
} else if (name == null ) {
bdata.put$TK$TV("_DATA_", Clazz.new_((I$[13]||$incl$(13)).c$$BA,[(I$[5]||$incl$(5)).getLimitedStreamBytes$java_io_InputStream$J(bis, -1)]));
} else {
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,["ZIP file " + name + " not found" ]);
}bdata.put$TK$TV("$_BINARY_$", (I$[14]||$incl$(14)).TRUE);
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
bdata.clear();
bdata.put$TK$TV("_ERROR_", e.getMessage());
} else {
throw e;
}
}
}, 1);

Clazz.newMeth(C$, 'cacheZipContents$java_io_BufferedInputStream$S$java_util_Map$Z', function (bis, fileName, cache, asByteArray) {
return C$.cacheZipContentsStatic$java_io_BufferedInputStream$S$java_util_Map$Z(bis, fileName, cache, asByteArray);
});

Clazz.newMeth(C$, 'cacheZipContentsStatic$java_io_BufferedInputStream$S$java_util_Map$Z', function (bis, fileName, cache, asByteArray) {
var zis = C$.newZIS$java_io_InputStream(bis);
var ze;
var listing = Clazz.new_((I$[3]||$incl$(3)));
var n = 0;
var isPath = (fileName != null  && fileName.endsWith$S("/") );
var oneFile = (asByteArray && !isPath && fileName != null   );
var pt = (oneFile ? fileName.indexOf("|") : -1);
var file0 = (pt >= 0 ? fileName : null);
if (pt >= 0) fileName = fileName.substring(0, pt);
var prefix = (fileName == null  ? "" : isPath ? fileName : fileName + "|");
try {
while ((ze = zis.getNextEntry()) != null ){
var name = ze.getName();
if (fileName != null ) {
if (oneFile) {
if (!name.equalsIgnoreCase$S(fileName)) continue;
} else {
listing.append$S(name).appendC$C("\u000a");
}}var nBytes = ze.getSize();
var bytes = (I$[5]||$incl$(5)).getLimitedStreamBytes$java_io_InputStream$J(zis, nBytes);
if (file0 != null ) {
C$.readFileAsMapStatic$java_io_BufferedInputStream$java_util_Map$S((I$[5]||$incl$(5)).getBIS$BA(bytes), cache, file0);
return null;
}n = n+(bytes.length);
var o = (asByteArray ? Clazz.new_((I$[13]||$incl$(13)).c$$BA,[bytes]) : bytes);
cache.put$TK$TV((oneFile ? "_DATA_" : prefix + name), o);
if (oneFile) break;
}
zis.close();
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
try {
zis.close();
} catch (e1) {
if (Clazz.exceptionOf(e1, "java.io.IOException")){
} else {
throw e1;
}
}
return null;
} else {
throw e;
}
}
if (n == 0 || fileName == null  ) return null;
System.out.println$S("ZipTools cached " + n + " bytes from " + fileName );
return listing.toString();
}, 1);

Clazz.newMeth(C$, 'getPngImageBytes$java_io_BufferedInputStream', function (bis) {
try {
if ((I$[5]||$incl$(5)).isPngZipStream$java_io_InputStream(bis)) {
var pt_count = Clazz.array(Integer.TYPE, [2]);
(I$[5]||$incl$(5)).getPngZipPointAndCount$java_io_BufferedInputStream$IA(bis, pt_count);
if (pt_count[1] != 0) return C$.deActivatePngZipB$BA((I$[5]||$incl$(5)).getLimitedStreamBytes$java_io_InputStream$J(bis, pt_count[0]));
}return (I$[5]||$incl$(5)).getLimitedStreamBytes$java_io_InputStream$J(bis, -1);
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
return null;
} else {
throw e;
}
}
}, 1);

Clazz.newMeth(C$, 'deActivatePngZipB$BA', function (bytes) {
if ((I$[5]||$incl$(5)).isPngZipB$BA(bytes)) bytes[51] = (32|0);
return bytes;
}, 1);
})();
//Created 2018-02-06 08:59:07
