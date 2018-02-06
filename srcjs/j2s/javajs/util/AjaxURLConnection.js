(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['javajs.util.Rdr']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AjaxURLConnection", null, 'java.net.URLConnection');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.bytesOut = null;
this.postOut = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.postOut = "";
}, 1);

Clazz.newMeth(C$, 'c$$java_net_URL', function (url) {
C$.superclazz.c$$java_net_URL.apply(this, [url]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'doAjax$Z', function (isBinary) {
var j2s = null;
{
j2s = J2S;
}
return j2s._doAjax(this.url, this.postOut, this.bytesOut, isBinary);
});

Clazz.newMeth(C$, 'connect', function () {
});

Clazz.newMeth(C$, 'outputBytes$BA', function (bytes) {
this.bytesOut = bytes;
});

Clazz.newMeth(C$, 'outputString$S', function (post) {
this.postOut = post;
});

Clazz.newMeth(C$, 'getInputStream', function () {
var is = C$.getAttachedStreamData$java_net_URL$Z(this.url, false);
return (is == null  ? C$.attachStreamData$java_net_URL$O(this.url, p$.doAjax$Z.apply(this, [true])) : is);
});

Clazz.newMeth(C$, 'getAttachedStreamData$java_net_URL$Z', function (url, andDelete) {
var data = null;
{
data = url._streamData;
if (andDelete) url._streamData = null;
}
return (data == null  ? null : (I$[1]||$incl$(1)).toBIS$O(data));
}, 1);

Clazz.newMeth(C$, 'attachStreamData$java_net_URL$O', function (url, o) {

url._streamData = o;
return (o == null  ? null : (I$[1]||$incl$(1)).toBIS$O(o));
}, 1);

Clazz.newMeth(C$, 'getContents', function () {
return p$.doAjax$Z.apply(this, [false]);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:01
