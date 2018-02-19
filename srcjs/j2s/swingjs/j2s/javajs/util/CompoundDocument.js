(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['javajs.util.CompoundDocHeader','javajs.util.Lst','java.io.DataInputStream','javajs.util.SB','javajs.util.CompoundDocDirEntry','javajs.util.ZipData']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "CompoundDocument", null, 'javajs.util.BinaryDocument');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.header = null;
this.directory = null;
this.rootEntry = null;
this.jzt = null;
this.SAT = null;
this.SSAT = null;
this.sectorSize = 0;
this.shortSectorSize = 0;
this.nShortSectorsPerStandardSector = 0;
this.nIntPerSector = 0;
this.nDirEntriesperSector = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.header = Clazz.new_((I$[1]||$incl$(1)).c$$javajs_util_CompoundDocument,[this]);
this.directory = Clazz.new_((I$[2]||$incl$(2)));
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.isBigEndian = true;
}, 1);

Clazz.newMeth(C$, 'setDocStream$javajs_api_GenericZipTools$java_io_BufferedInputStream', function (jzt, bis) {
this.jzt = jzt;
if (!this.isRandom) {
this.stream = Clazz.new_((I$[3]||$incl$(3)).c$$java_io_InputStream,[bis]);
}this.stream.mark$I(2147483647);
if (!p$.readHeader.apply(this, [])) return;
p$.getSectorAllocationTable.apply(this, []);
p$.getShortSectorAllocationTable.apply(this, []);
p$.getDirectoryTable.apply(this, []);
});

Clazz.newMeth(C$, 'getDirectory', function () {
return this.directory;
});

Clazz.newMeth(C$, 'getDirectoryListing$S', function (separator) {
var sb = Clazz.new_((I$[4]||$incl$(4)));
for (var i = 0; i < this.directory.size(); i++) {
var thisEntry = this.directory.get$I(i);
if (!thisEntry.isEmpty) sb.append$S(separator).append$S(thisEntry.entryName).append$S("\u0009len=").appendI$I(thisEntry.lenStream).append$S("\u0009SID=").appendI$I(thisEntry.SIDfirstSector).append$S(thisEntry.isStandard ? "\tfileOffset=" + p$.getOffset$I.apply(this, [thisEntry.SIDfirstSector]) : "");
}
return sb.toString();
});

Clazz.newMeth(C$, 'getAllData', function () {
return this.getAllDataFiles$S$S(null, null);
});

Clazz.newMeth(C$, 'getAllDataMapped$S$S$java_util_Map', function (prefix, binaryFileList, fileData) {
fileData.put$TK$TV("#Directory_Listing", this.getDirectoryListing$S("|"));
binaryFileList = "|" + binaryFileList + "|" ;
for (var i = 0; i < this.directory.size(); i++) {
var thisEntry = this.directory.get$I(i);
if (!thisEntry.isEmpty && thisEntry.entryType != 5 ) {
var name = thisEntry.entryName;
System.out.println$S("CompoundDocument file " + name);
var isBinary = (binaryFileList.indexOf("|" + name + "|" ) >= 0);
if (isBinary) name += ":asBinaryString";
fileData.put$TK$TV(prefix + "/" + name , p$.appendData$javajs_util_SB$S$javajs_util_CompoundDocDirEntry$Z.apply(this, [Clazz.new_((I$[4]||$incl$(4))), name, thisEntry, isBinary]).toString());
}}
this.close();
});

Clazz.newMeth(C$, 'getAllDataFiles$S$S', function (binaryFileList, firstFile) {
var data = Clazz.new_((I$[4]||$incl$(4)));
data.append$S("Compound Document File Directory: ");
data.append$S(this.getDirectoryListing$S("|"));
data.append$S("\u000a");
var thisEntry;
binaryFileList = "|" + binaryFileList + "|" ;
for (var i = 0, n = this.directory.size(); i < n; i++) {
thisEntry = this.directory.get$I(i);
var name = thisEntry.entryName;
switch (thisEntry.entryType) {
case 5:
break;
case 1:
data.append$S("NEW Directory ").append$S(name).append$S("\u000a");
break;
case 2:
if (name.endsWith$S(".gz")) name = name.substring(0, name.length$() - 3);
p$.appendData$javajs_util_SB$S$javajs_util_CompoundDocDirEntry$Z.apply(this, [data, name, thisEntry, binaryFileList.indexOf("|" + thisEntry.entryName + "|" ) >= 0]);
break;
}
}
this.close();
return data;
});

Clazz.newMeth(C$, 'appendData$javajs_util_SB$S$javajs_util_CompoundDocDirEntry$Z', function (data, name, thisEntry, isBinary) {
data.append$S("BEGIN Directory Entry ").append$S(name).append$S("\u000a");
data.appendSB$javajs_util_SB(p$.getEntryAsString$javajs_util_CompoundDocDirEntry$Z.apply(this, [thisEntry, isBinary]));
data.append$S("\u000aEND Directory Entry ").append$S(name).append$S("\u000a");
return data;
});

Clazz.newMeth(C$, 'getFileAsString$S', function (entryName) {
for (var i = 0; i < this.directory.size(); i++) {
var thisEntry = this.directory.get$I(i);
if (thisEntry.entryName.equals$O(entryName)) return p$.getEntryAsString$javajs_util_CompoundDocDirEntry$Z.apply(this, [thisEntry, false]);
}
return Clazz.new_((I$[4]||$incl$(4)));
});

Clazz.newMeth(C$, 'getOffset$I', function (SID) {
return (SID + 1) * this.sectorSize;
});

Clazz.newMeth(C$, 'gotoSector$I', function (SID) {
this.seek$J(p$.getOffset$I.apply(this, [SID]));
});

Clazz.newMeth(C$, 'readHeader', function () {
if (!this.header.readData()) return false;
this.sectorSize = 1 << this.header.sectorPower;
this.shortSectorSize = 1 << this.header.shortSectorPower;
this.nShortSectorsPerStandardSector = (this.sectorSize/this.shortSectorSize|0);
this.nIntPerSector = (this.sectorSize/4|0);
this.nDirEntriesperSector = (this.sectorSize/128|0);
return true;
});

Clazz.newMeth(C$, 'getSectorAllocationTable', function () {
var nSID = 0;
var thisSID;
this.SAT = Clazz.array(Integer.TYPE, [this.header.nSATsectors * this.nIntPerSector + 109]);
try {
for (var i = 0; i < 109; i++) {
thisSID = this.header.MSAT0[i];
if (thisSID < 0) break;
p$.gotoSector$I.apply(this, [thisSID]);
for (var j = 0; j < this.nIntPerSector; j++) {
this.SAT[nSID++] = this.readInt();
}
}
var nMaster = this.header.nAdditionalMATsectors;
thisSID = this.header.SID_MSAT_next;
var MSAT = Clazz.array(Integer.TYPE, [this.nIntPerSector]);
out : while (nMaster-- > 0 && thisSID >= 0 ){
p$.gotoSector$I.apply(this, [thisSID]);
for (var i = 0; i < this.nIntPerSector; i++) MSAT[i] = this.readInt();

for (var i = 0; i < this.nIntPerSector - 1; i++) {
thisSID = MSAT[i];
if (thisSID < 0) break out;
p$.gotoSector$I.apply(this, [thisSID]);
for (var j = this.nIntPerSector; --j >= 0; ) this.SAT[nSID++] = this.readInt();

}
thisSID = MSAT[this.nIntPerSector - 1];
}
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S(e.toString());
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'getShortSectorAllocationTable', function () {
var nSSID = 0;
var thisSID = this.header.SID_SSAT_start;
var nMax = this.header.nSSATsectors * this.nIntPerSector;
this.SSAT = Clazz.array(Integer.TYPE, [nMax]);
try {
while (thisSID > 0 && nSSID < nMax ){
p$.gotoSector$I.apply(this, [thisSID]);
for (var j = 0; j < this.nIntPerSector; j++) {
this.SSAT[nSSID++] = this.readInt();
}
thisSID = this.SAT[thisSID];
}
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S(e.toString());
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'getDirectoryTable', function () {
var thisSID = this.header.SID_DIR_start;
var thisEntry;
this.rootEntry = null;
try {
while (thisSID > 0){
p$.gotoSector$I.apply(this, [thisSID]);
for (var j = this.nDirEntriesperSector; --j >= 0; ) {
thisEntry = Clazz.new_((I$[5]||$incl$(5)).c$$javajs_util_CompoundDocument,[this]);
thisEntry.readData();
this.directory.addLast$TV(thisEntry);
if (thisEntry.entryType == 5) this.rootEntry = thisEntry;
}
thisSID = this.SAT[thisSID];
}
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S(e.toString());
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'getEntryAsString$javajs_util_CompoundDocDirEntry$Z', function (thisEntry, asBinaryString) {
if (thisEntry.isEmpty) return Clazz.new_((I$[4]||$incl$(4)));
return (thisEntry.isStandard ? p$.getStandardStringData$I$I$Z.apply(this, [thisEntry.SIDfirstSector, thisEntry.lenStream, asBinaryString]) : p$.getShortStringData$I$I$Z.apply(this, [thisEntry.SIDfirstSector, thisEntry.lenStream, asBinaryString]));
});

Clazz.newMeth(C$, 'getStandardStringData$I$I$Z', function (thisSID, nBytes, asBinaryString) {
var data = Clazz.new_((I$[4]||$incl$(4)));
var byteBuf = Clazz.array(Byte.TYPE, [this.sectorSize]);
var gzipData = Clazz.new_((I$[6]||$incl$(6)).c$$I,[nBytes]);
try {
while (thisSID > 0 && nBytes > 0 ){
p$.gotoSector$I.apply(this, [thisSID]);
nBytes = p$.getSectorData$javajs_util_SB$BA$I$I$Z$javajs_util_ZipData.apply(this, [data, byteBuf, this.sectorSize, nBytes, asBinaryString, gzipData]);
thisSID = this.SAT[thisSID];
}
if (nBytes == -9999) return Clazz.new_((I$[4]||$incl$(4)));
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S(e.toString());
} else {
throw e;
}
}
if (gzipData.isEnabled) gzipData.addTo$javajs_api_GenericZipTools$javajs_util_SB(this.jzt, data);
return data;
});

Clazz.newMeth(C$, 'getSectorData$javajs_util_SB$BA$I$I$Z$javajs_util_ZipData', function (data, byteBuf, nSectorBytes, nBytes, asBinaryString, gzipData) {
this.readByteArray$BA$I$I(byteBuf, 0, byteBuf.length);
var n = gzipData.addBytes$BA$I$I(byteBuf, nSectorBytes, nBytes);
if (n >= 0) return n;
if (asBinaryString) {
for (var i = 0; i < nSectorBytes; i++) {
data.append$S(Integer.toHexString(byteBuf[i] & 255)).appendC$C(" ");
if (--nBytes < 1) break;
}
} else {
for (var i = 0; i < nSectorBytes; i++) {
if (byteBuf[i] == 0) return -9999;
data.appendC$C(String.fromCharCode(byteBuf[i]));
if (--nBytes < 1) break;
}
}return nBytes;
});

Clazz.newMeth(C$, 'getShortStringData$I$I$Z', function (shortSID, nBytes, asBinaryString) {
var data = Clazz.new_((I$[4]||$incl$(4)));
if (this.rootEntry == null ) return data;
var thisSID = this.rootEntry.SIDfirstSector;
var ptShort = 0;
var byteBuf = Clazz.array(Byte.TYPE, [this.shortSectorSize]);
var gzipData = Clazz.new_((I$[6]||$incl$(6)).c$$I,[nBytes]);
try {
while (thisSID >= 0 && shortSID >= 0  && nBytes > 0 ){
while (shortSID - ptShort >= this.nShortSectorsPerStandardSector){
ptShort = ptShort+(this.nShortSectorsPerStandardSector);
thisSID = this.SAT[thisSID];
}
this.seek$J(p$.getOffset$I.apply(this, [thisSID]) + (shortSID - ptShort) * this.shortSectorSize);
nBytes = p$.getSectorData$javajs_util_SB$BA$I$I$Z$javajs_util_ZipData.apply(this, [data, byteBuf, this.shortSectorSize, nBytes, asBinaryString, gzipData]);
shortSID = this.SSAT[shortSID];
}
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S(data.toString());
System.out.println$S("reader error in CompoundDocument " + e.toString());
} else {
throw e;
}
}
if (gzipData.isEnabled) gzipData.addTo$javajs_api_GenericZipTools$javajs_util_SB(this.jzt, data);
return data;
});
})();
//Created 2018-02-08 10:02:19
