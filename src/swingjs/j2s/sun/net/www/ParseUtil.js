(function(){var P$=Clazz.newPackage("sun.net.www"),I$=[['java.util.BitSet','java.net.URL']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ParseUtil");
C$.encodedInPath = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
{
C$.encodedInPath=Clazz.new_((I$[1]||$incl$(1)).c$$I,[256]);
C$.encodedInPath.set$I("=".$c());
C$.encodedInPath.set$I(";".$c());
C$.encodedInPath.set$I("?".$c());
C$.encodedInPath.set$I("/".$c());
C$.encodedInPath.set$I("#".$c());
C$.encodedInPath.set$I(" ".$c());
C$.encodedInPath.set$I("<".$c());
C$.encodedInPath.set$I(">".$c());
C$.encodedInPath.set$I("%".$c());
C$.encodedInPath.set$I("\"".$c());
C$.encodedInPath.set$I("{".$c());
C$.encodedInPath.set$I("}".$c());
C$.encodedInPath.set$I("|".$c());
C$.encodedInPath.set$I("\\".$c());
C$.encodedInPath.set$I("^".$c());
C$.encodedInPath.set$I("[".$c());
C$.encodedInPath.set$I("]".$c());
C$.encodedInPath.set$I("`".$c());
for (var i = 0; i < 32; i++) C$.encodedInPath.set$I(i);

C$.encodedInPath.set$I(127);
}
;
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'encodePath$S', function (path) {
return C$.encodePath$S$Z(path, true);
}, 1);

Clazz.newMeth(C$, 'encodePath$S$Z', function (path, flag) {
var retCC = Clazz.array(Character.TYPE, [path.length$() * 2 + 16]);
var retLen = 0;
var pathCC = path.toCharArray();
var n = path.length$();
for (var i = 0; i < n; i++) {
var c = pathCC[i];
if ((!flag && c == "/" ) || (flag && c == "/" ) ) retCC[retLen++]="/";
 else {
if (c.$c() <= 127 ) {
if (c >= "a" && c <= "z"  || c >= "A" && c <= "Z"   || c >= "0" && c <= "9"  ) {
retCC[retLen++]=c;
} else if (C$.encodedInPath.get$I(c.$c())) retLen=C$.escape$CA$C$I(retCC, c, retLen);
 else retCC[retLen++]=c;
} else if (c.$c() > 2047 ) {
retLen=C$.escape$CA$C$I(retCC, String.fromCharCode((224 | ((c.$c() >> 12) & 15))), retLen);
retLen=C$.escape$CA$C$I(retCC, String.fromCharCode((128 | ((c.$c() >> 6) & 63))), retLen);
retLen=C$.escape$CA$C$I(retCC, String.fromCharCode((128 | ((c.$c() >> 0) & 63))), retLen);
} else {
retLen=C$.escape$CA$C$I(retCC, String.fromCharCode((192 | ((c.$c() >> 6) & 31))), retLen);
retLen=C$.escape$CA$C$I(retCC, String.fromCharCode((128 | ((c.$c() >> 0) & 63))), retLen);
}}if (retLen + 9 > retCC.length) {
var newLen = retCC.length * 2 + 16;
if (newLen < 0) {
newLen=2147483647;
}var buf = Clazz.array(Character.TYPE, [newLen]);
System.arraycopy(retCC, 0, buf, 0, retLen);
retCC=buf;
}}
return  String.instantialize(retCC, 0, retLen);
}, 1);

Clazz.newMeth(C$, 'escape$CA$C$I', function (cc, c, index) {
cc[index++]="%";
cc[index++]=Character.forDigit((c.$c() >> 4) & 15, 16);
cc[index++]=Character.forDigit(c & 15, 16);
return index;
}, 1);

Clazz.newMeth(C$, 'fileToEncodedURL$java_io_File', function (file) {
var path = file.getAbsolutePath();
path=C$.encodePath$S(path);
if (!path.startsWith$S("/")) {
path="/" + path;
}if (!path.endsWith$S("/") && file.isDirectory() ) {
path=path + "/";
}return Clazz.new_((I$[2]||$incl$(2)).c$$S$S$S,["file", "", path]);
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:33
