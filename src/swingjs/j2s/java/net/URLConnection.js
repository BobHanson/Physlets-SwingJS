(function(){var P$=Clazz.newPackage("java.net"),I$=[['javajs.util.Lst']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "URLConnection");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.url = null;
this.doInput = false;
this.doOutput = false;
this.connected = false;
this.requests = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.doInput = true;
this.doOutput = false;
this.connected = false;
}, 1);

Clazz.newMeth(C$, 'setDoInput$Z', function (doinput) {
if (this.connected) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Already connected"]);
this.doInput = doinput;
});

Clazz.newMeth(C$, 'getDoInput', function () {
return this.doInput;
});

Clazz.newMeth(C$, 'setDoOutput$Z', function (dooutput) {
if (this.connected) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Already connected"]);
this.doOutput = dooutput;
});

Clazz.newMeth(C$, 'getDoOutput', function () {
return this.doOutput;
});

Clazz.newMeth(C$, 'c$$java_net_URL', function (url) {
C$.$init$.apply(this);
this.url = url;
}, 1);

Clazz.newMeth(C$, 'getURL', function () {
return this.url;
});

Clazz.newMeth(C$, 'getInputStream', function () {
throw Clazz.new_(Clazz.load('java.net.UnknownServiceException').c$$S,["protocol doesn\'t support input"]);
});

Clazz.newMeth(C$, 'getOutputStream', function () {
throw Clazz.new_(Clazz.load('java.net.UnknownServiceException').c$$S,["protocol doesn\'t support output"]);
});

Clazz.newMeth(C$, 'setRequestProperty$S$S', function (key, value) {
if (this.connected) throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Already connected"]);
if (key == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["key is null"]);
if (this.requests == null ) this.requests = Clazz.new_((I$[1]||$incl$(1)));
for (var i = this.requests.size(); --i >= 0; ) if (this.requests.get$I(i)[0].equals$O(key)) {
this.requests.get$I(i)[1] = value;
return;
}
this.requests.addLast$TV(Clazz.array(java.lang.String, -1, [key, value]));
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:09
