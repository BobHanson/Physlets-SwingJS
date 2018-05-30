(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['javajs.util.Rdr','javajs.util.ZipTools']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ZipData");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.isEnabled = false;
this.buf = null;
this.pt = 0;
this.nBytes = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.isEnabled = true;
}, 1);

Clazz.newMeth(C$, 'c$$I', function (nBytes) {
C$.$init$.apply(this);
this.nBytes=nBytes;
}, 1);

Clazz.newMeth(C$, 'addBytes$BA$I$I', function (byteBuf, nSectorBytes, nBytesRemaining) {
if (this.pt == 0) {
if (!(I$[1]||$incl$(1)).isGzipB$BA(byteBuf)) {
this.isEnabled=false;
return -1;
}this.buf=Clazz.array(Byte.TYPE, [nBytesRemaining]);
}var nToAdd = Math.min(nSectorBytes, nBytesRemaining);
System.arraycopy(byteBuf, 0, this.buf, this.pt, nToAdd);
this.pt+=nToAdd;
return nBytesRemaining - nToAdd;
});

Clazz.newMeth(C$, 'addTo$javajs_api_GenericZipTools$javajs_util_SB', function (jzt, data) {
data.append$S(C$.getGzippedBytesAsString$javajs_api_GenericZipTools$BA(jzt, this.buf));
});

Clazz.newMeth(C$, 'getGzippedBytesAsString$javajs_api_GenericZipTools$BA', function (jzt, bytes) {
try {
var bis = jzt.getUnGzippedInputStream$BA(bytes);
var s = (I$[2]||$incl$(2)).getStreamAsString$java_io_InputStream(bis);
bis.close();
return s;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
return "";
} else {
throw e;
}
}
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:58
