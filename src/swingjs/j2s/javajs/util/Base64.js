(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['javajs.util.SB']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Base64");
C$.base64 = null;
C$.decode64 = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
C$.decode64 = Clazz.array(Integer.TYPE, -1, [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 62, 0, 62, 0, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 0, 0, 0, 0, 63, 0, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 0, 0, 0, 0, 0]);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getBytes64$BA', function (bytes) {
return C$.getBase64$BA(bytes).toBytes$I$I(0, -1);
}, 1);

Clazz.newMeth(C$, 'getBase64$BA', function (bytes) {
var nBytes = bytes.length;
var sout = Clazz.new_((I$[1]||$incl$(1)));
if (nBytes == 0) return sout;
for (var i = 0, nPad = 0; i < nBytes && nPad == 0 ; ) {
if (i % 75 == 0 && i != 0 ) sout.append$S("\u000d\u000a");
nPad=(i + 2 == nBytes ? 1 : i + 1 == nBytes ? 2 : 0);
var outbytes = ((bytes[i++] << 16) & 16711680) | ((nPad == 2 ? 0 : bytes[i++] << 8) & 65280) | ((nPad >= 1 ? 0 : (bytes[i++]|0)) & 255) ;
sout.appendC$C(C$.base64.charAt((outbytes >> 18) & 63));
sout.appendC$C(C$.base64.charAt((outbytes >> 12) & 63));
sout.appendC$C(nPad == 2 ? "=" : C$.base64.charAt((outbytes >> 6) & 63));
sout.appendC$C(nPad >= 1 ? "=" : C$.base64.charAt(outbytes & 63));
}
return sout;
}, 1);

Clazz.newMeth(C$, 'decodeBase64$S', function (strBase64) {
var nBytes = 0;
var ch;
var pt0 = strBase64.indexOf(";base64,") + 1;
if (pt0 > 0) pt0+=7;
var chars64 = strBase64.toCharArray();
var len64 = chars64.length;
if (len64 == 0) return Clazz.array(Byte.TYPE, [0]);
for (var i = len64; --i >= pt0; ) nBytes+=((ch=chars64[i] & 127) == 65  || C$.decode64[ch] > 0  ? 3 : 0);

nBytes=nBytes >> 2;
var bytes = Clazz.array(Byte.TYPE, [nBytes]);
var offset = 18;
for (var i = pt0, pt = 0, b = 0; i < len64; i++) {
if (C$.decode64[ch=chars64[i] & 127] > 0 || ch == 65   || ch == 61  ) {
b|=C$.decode64[ch] << offset;
offset-=6;
if (offset < 0) {
bytes[pt++]=((((b & 16711680) >> 16)|0)|0);
if (pt < nBytes) bytes[pt++]=((((b & 65280) >> 8)|0)|0);
if (pt < nBytes) bytes[pt++]=(((b & 255)|0)|0);
offset=18;
b=0;
}}}
return bytes;
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:55