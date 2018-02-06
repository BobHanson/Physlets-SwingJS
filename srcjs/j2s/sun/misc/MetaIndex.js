(function(){var P$=Clazz.newPackage("sun.misc"),I$=[['java.io.File','java.io.BufferedReader','java.io.FileReader','java.util.ArrayList','java.util.HashMap']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "MetaIndex");
C$.jarMap = null;
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.contents = null;
this.isClassOnlyJar = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'forJar$java_io_File', function (jar) {
return C$.getJarMap().get$O(jar);
}, 1);

Clazz.newMeth(C$, 'registerDirectory$java_io_File', function (dir) {
var indexFile = Clazz.new_((I$[1]||$incl$(1)).c$$java_io_File$S,[dir, "meta-index"]);
if (indexFile.exists()) {
try {
var reader = Clazz.new_((I$[2]||$incl$(2)).c$$java_io_Reader,[Clazz.new_((I$[3]||$incl$(3)).c$$java_io_File,[indexFile])]);
var line = null;
var curJarName = null;
var isCurJarContainClassOnly = false;
var contents = Clazz.new_((I$[4]||$incl$(4)));
var map = C$.getJarMap();
dir = dir.getCanonicalFile();
line = reader.readLine();
if (line == null  || !line.equals$O("% VERSION 2") ) {
reader.close();
return;
}while ((line = reader.readLine()) != null ){
switch ((line.charCodeAt(0))) {
case 33:
case 35:
case 64:
{
if ((curJarName != null ) && (contents.size() > 0) ) {
map.put$TK$TV(Clazz.new_((I$[1]||$incl$(1)).c$$java_io_File$S,[dir, curJarName]), Clazz.new_(C$.c$$java_util_List$Z,[contents, isCurJarContainClassOnly]));
contents.clear();
}curJarName = line.substring(2);
if (line.charAt(0) == "!") {
isCurJarContainClassOnly = true;
} else if (isCurJarContainClassOnly) {
isCurJarContainClassOnly = false;
}break;
}case 37:
break;
default:
{
contents.add$TE(line);
}}
}
if ((curJarName != null ) && (contents.size() > 0) ) {
map.put$TK$TV(Clazz.new_((I$[1]||$incl$(1)).c$$java_io_File$S,[dir, curJarName]), Clazz.new_(C$.c$$java_util_List$Z,[contents, isCurJarContainClassOnly]));
}reader.close();
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
} else {
throw e;
}
}
}}, 1);

Clazz.newMeth(C$, 'mayContain$S', function (entry) {
if (this.isClassOnlyJar && !entry.endsWith$S(".class") ) {
return false;
}var conts = this.contents;
for (var i = 0; i < conts.length; i++) {
if (entry.startsWith$S(conts[i])) {
return true;
}}
return false;
});

Clazz.newMeth(C$, 'c$$java_util_List$Z', function (entries, isClassOnlyJar) {
C$.$init$.apply(this);
if (entries == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}this.contents = entries.toArray$TTA(Clazz.array(java.lang.String, [0]));
this.isClassOnlyJar = isClassOnlyJar;
}, 1);

Clazz.newMeth(C$, 'getJarMap', function () {
if (C$.jarMap == null ) {
{
if (C$.jarMap == null ) {
C$.jarMap = Clazz.new_((I$[5]||$incl$(5)));
}}}Clazz.assert(C$, this, function(){return C$.jarMap != null });
return C$.jarMap;
}, 1);
C$.$_ASSERT_ENABLED_ = ClassLoader.$getClassAssertionStatus(C$);

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:21
