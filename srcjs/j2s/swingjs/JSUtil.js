(function(){var P$=Clazz.newPackage("swingjs"),I$=[['java.util.Hashtable','javajs.util.Rdr','java.net.URL','javajs.util.AU','java.io.BufferedInputStream','swingjs.api.Interface','javajs.util.PT','java.lang.Boolean','Thread','java.util.Locale','javajs.util.AjaxURLConnection']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSUtil");
C$.debugging = false;
C$.J2S = null;
C$.fileCache = null;
C$.useCache = false;
var p$=C$.prototype;
C$.zipTools = null;
C$.mapNotImpl = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
{
var j2sdebug = false;
var j2sself = null;
{
j2sself = self.J2S;
j2sdebug = J2S._checkLoad || J2S._debugCode
}
C$.debugging = j2sdebug;
C$.J2S = j2sself;
}
;
C$.useCache = true;
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
System.out.println$S("JSUtil initialized");
}, 1);

Clazz.newMeth(C$, 'getFileCache', function () {
if (C$.fileCache == null  && (C$.fileCache = C$.J2S._getSetJavaFileCache(null)) == null  ) {
C$.fileCache = Clazz.new_((I$[1]||$incl$(1)));
C$.J2S._getSetJavaFileCache(C$.fileCache);
}return C$.fileCache;
}, 1);

Clazz.newMeth(C$, 'getCachedFileData$S', function (path) {
return (C$.useCache && C$.fileCache != null   ? C$.fileCache.get$O(path) : null);
}, 1);

Clazz.newMeth(C$, 'getFileContents$O', function (uriOrJSFile) {
if (Clazz.instanceOf(uriOrJSFile, "java.io.File")) {
var bytes = uriOrJSFile.bytes ||null;
if (bytes != null ) return bytes;
}var uri = uriOrJSFile.toString();
var data = C$.getCachedFileData$S(uri);
if (data == null ) {


try {
data = (I$[2]||$incl$(2)).streamToUTF8String$java_io_BufferedInputStream(Clazz.new_((I$[3]||$incl$(3)).c$$S,[uri]).getContent());
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
} else {
throw e;
}
}
data = C$.J2S._getFileData(uri, null, false, false);
}return data;
}, 1);

Clazz.newMeth(C$, 'getFileAsString$S', function (filename) {
var data = C$.getFileContents$O(filename);
return C$.ensureString$O(data);
}, 1);

Clazz.newMeth(C$, 'getFileAsBytes$O', function (file) {
var data = C$.getFileContents$O(file);
var b = null;
if ((I$[4]||$incl$(4)).isAB$O(data)) b = data;
 else if (Clazz.instanceOf(data, "java.lang.String")) b = (data).getBytes();
 else if (Clazz.instanceOf(data, "javajs.util.SB")) b = (I$[2]||$incl$(2)).getBytesFromSB$javajs_util_SB(data);
 else if (Clazz.instanceOf(data, "java.io.InputStream")) try {
b = (I$[2]||$incl$(2)).getLimitedStreamBytes$java_io_InputStream$J(data, -1);
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
} else {
throw e;
}
}
return (I$[4]||$incl$(4)).ensureSignedBytes$BA(b);
}, 1);

Clazz.newMeth(C$, 'haveCachedResource$S$Z', function (resourceName, isJavaPath) {
var path = C$.J2S._getResourcePath(resourceName, isJavaPath);
return (path != null  && C$.getCachedFileData$S(path) != null  );
}, 1);

Clazz.newMeth(C$, 'getJavaResource$S$Z$Z$Z', function (resourceName, isJavaPath, doCache, doProcess) {
System.out.println$S("JSUtil getting Java resource " + resourceName);
var path = C$.J2S._getResourcePath(resourceName, isJavaPath);
if (path == null ) return null;
var data = C$.getCachedFileData$S(path);
if (data == null  && (data = C$.J2S._getFileData(path, null, false, false)) != null   && C$.useCache  && doCache ) C$.cacheFileData$S$O(path, data);
var sdata = C$.ensureString$O(data);
var ok = (sdata != null  && sdata.indexOf("[Exception") != 0 );
System.out.println$S("Processing " + path + " [" + (ok ? "" + sdata.length$() : sdata) + "]" );
return (!ok ? null : !doProcess ? sdata : path.endsWith$S(".css") ? C$.processCSS$S$S(sdata, path) : path.endsWith$S(".js") ? C$.processJS$S(sdata) : sdata);
}, 1);

Clazz.newMeth(C$, 'cacheFileData$S$O', function (path, data) {
C$.getFileCache().put$TK$TV(path, data);
}, 1);

Clazz.newMeth(C$, 'loadJavaResourcesFromZip$ClassLoader$S$java_util_Map', function (cl, zipFileName, mapByteData) {
if (mapByteData == null ) mapByteData = C$.getFileCache();
var fileList = "";
try {
var bis = Clazz.new_((I$[5]||$incl$(5)).c$$java_io_InputStream,[cl.getResourceAsStream$S(zipFileName)]);
var prefix = C$.J2S._getResourcePath(null, true);
fileList = C$.getZipTools().cacheZipContentsStatic$java_io_BufferedInputStream$S$java_util_Map$Z(bis, prefix, mapByteData, false);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S("JSUtil could not cache files from " + zipFileName);
return;
} else {
throw e;
}
}
if (C$.debugging) System.out.println$S("JSUtil loaded resources from " + zipFileName + ":\n" + fileList );
}, 1);

Clazz.newMeth(C$, 'getZipTools', function () {
return (C$.zipTools == null  ? (C$.zipTools = (I$[6]||$incl$(6)).getInstance$S$Z("javajs.util.ZipTools", true)) : C$.zipTools);
}, 1);

Clazz.newMeth(C$, 'loadStaticResource$S', function (file) {
var s = "alert('" + file + "' was not found)" ;
if (!C$.J2S._isResourceLoaded(file, false)) {
s = C$.getJavaResource$S$Z$Z$Z(file, true, false, true);
C$.J2S._isResourceLoaded(file, true);
}return s;
}, 1);

Clazz.newMeth(C$, 'processCSS$S$S', function (css, path) {
if (path != null  && css.indexOf("images/") >= 0 ) {
path = path.substring(0, path.lastIndexOf("/") + 1) + "images/";
css = (I$[7]||$incl$(7)).rep$S$S$S(css, "images/", path);
}var jq = C$.getJQuery();
jq.$("head").append(jq.$("<style type='text/css'>" + css + "</style>" ));
return css;
}, 1);

Clazz.newMeth(C$, 'processJS$S', function (js) {
try {
{
eval(js);
}
} catch (e) {
C$.alert$O("error processing " + js);
return null;
}
return js;
}, 1);

Clazz.newMeth(C$, 'ensureString$O', function (data) {
if (data == null ) return null;
if ((I$[4]||$incl$(4)).isAB$O(data)) return (I$[2]||$incl$(2)).bytesToUTF8String$BA(data);
if (Clazz.instanceOf(data, "java.lang.String") || Clazz.instanceOf(data, "javajs.util.SB") ) return data.toString();
if (Clazz.instanceOf(data, "java.io.InputStream")) return (I$[2]||$incl$(2)).streamToUTF8String$java_io_BufferedInputStream(Clazz.new_((I$[5]||$incl$(5)).c$$java_io_InputStream,[data]));
return null;
}, 1);

Clazz.newMeth(C$, 'getJQuery', function () {
{
if (!window.jQuery) alert( "jQuery is required for SwingJS, but window.jQuery is not defined." ); jQuery.$ || (jQuery.$ = jQuery); return(jQuery);
}
}, 1);

Clazz.newMeth(C$, 'getStackTrace$I', function (n) {
{
return Clazz._getStackTrace(n);
}
}, 1);

Clazz.newMeth(C$, 'getStackTrace', function () {
{
return Clazz._getStackTrace();
}
}, 1);

Clazz.newMeth(C$, 'notImplemented$S', function (msg) {
var s = null;
if (C$.mapNotImpl == null ) C$.mapNotImpl = Clazz.new_((I$[1]||$incl$(1)));
{
s = arguments.callee.caller;
var cl = s.claxxOwner || s.exClazz;
s = (cl ? cl.__CLASS_NAME__ + "." : "") + arguments.callee.caller.exName;
}
if (C$.mapNotImpl.containsKey$O(s)) return;
C$.mapNotImpl.put$TK$TV(s, (I$[8]||$incl$(8)).TRUE);
System.out.println$S(s + " has not been implemented in SwingJS. " + (msg == "" ? "" : (msg == null  ? "" : msg) + C$.getStackTrace$I(-5)) );
}, 1);

Clazz.newMeth(C$, 'log$S', function (msg) {
{
System.out.println(msg);
console.log(msg);
}
}, 1);

Clazz.newMeth(C$, 'getInstance$S', function (className) {
return (I$[6]||$incl$(6)).getInstance$S$Z(className, false);
}, 1);

Clazz.newMeth(C$, 'getAppletViewer', function () {
return ((I$[9]||$incl$(9)).currentThread()).appletViewer;
}, 1);

Clazz.newMeth(C$, 'readyCallback$S$S$java_awt_Container$swingjs_JSAppletViewer', function (aname, fname, applet, appletPanel) {
try {
C$.J2S._readyCallback(aname, fname, true, applet, appletPanel);
} catch (e) {
System.out.println$S("JSUtil Error running readyCallback method for " + fname + " -- check your page JavaScript" );
}
}, 1);

Clazz.newMeth(C$, 'getSignedStreamBytes$java_io_BufferedInputStream', function (bis) {
try {
return (I$[4]||$incl$(4)).ensureSignedBytes$BA((I$[2]||$incl$(2)).getStreamAsBytes$java_io_BufferedInputStream$javajs_util_OC(bis, null));
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
return null;
} else {
throw e;
}
}
}, 1);

Clazz.newMeth(C$, 'saveFile$S$O$S$S', function (fileName, data, mimeType, encoding) {
C$.J2S._saveFile(fileName, data, mimeType, encoding);
}, 1);

Clazz.newMeth(C$, 'getResourceAsStream$S', function (name) {
var cl = null;
{
cl = swingjs.JSUtil.getClassLoader();
}
return cl.getResourceAsStream$S(name);
}, 1);

Clazz.newMeth(C$, 'getResource$S', function (name) {
var cl = null;
{
cl = swingjs.JSUtil.getClassLoader();
}
return cl.getResource$S(name);
}, 1);

Clazz.newMeth(C$, 'getDefaultLocale$S', function (language) {
var region;
var country;
var variant;
if (language == null ) language = C$.J2S._getDefaultLanguage(true);
language = language.$replace("-", "_");
if (language == null  || language.length$() == 0  || language.equalsIgnoreCase$S("en") ) language = "en_US";
var i = language.indexOf("_");
if (i > 0) {
region = language.substring(i + 1);
language = language.substring(0, i);
} else {
region = "";
}region = region.toUpperCase();
i = region.indexOf("_");
if (i > 0) {
country = region.substring(0, i);
variant = region.substring(i + 1);
} else {
country = region;
variant = "";
}return Clazz.new_((I$[10]||$incl$(10)).c$$S$S$S,[language, country, variant]);
}, 1);

Clazz.newMeth(C$, 'getURLInputStream$java_net_URL$Z', function (url, andDelete) {
try {
var bis = (I$[11]||$incl$(11)).getAttachedStreamData$java_net_URL$Z(url, andDelete);
return (bis == null  ? url.openStream() : bis);
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
} else {
throw e;
}
}
return null;
}, 1);

Clazz.newMeth(C$, 'showWebPage$java_net_URL$O', function (url, target) {
{
if (target) window.open(url.toString(), target); else window.open(url.toString());
}
}, 1);

Clazz.newMeth(C$, 'warn$S', function (msg) {
C$.alert$O(msg);
}, 1);

Clazz.newMeth(C$, 'alert$O', function (object) {
{
console.log("[JSUtil] " + object);
alert(object);
}
}, 1);

Clazz.newMeth(C$, 'confirm$S', function (msg) {
{
return confirm(msg);
}
}, 1);

Clazz.newMeth(C$, 'prompt$S$S', function (msg, defaultRet) {
{
return prompt(msg, defaultRet);
}
}, 1);
})();
//Created 2018-02-06 09:00:32
