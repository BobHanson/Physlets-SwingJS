(function(){var P$=Clazz.newPackage("java.applet"),I$=[['java.net.URL','sun.applet.AppletAudioClip','java.util.Locale']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Applet", null, 'java.awt.Panel');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.stub = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'setStub$java_applet_AppletStub', function (stub) {
this.stub = stub;
});

Clazz.newMeth(C$, 'isActive', function () {
if (this.stub != null ) {
return this.stub.isActive();
} else {
return false;
}});

Clazz.newMeth(C$, 'getDocumentBase', function () {
return this.stub.getDocumentBase();
});

Clazz.newMeth(C$, 'getCodeBase', function () {
return this.stub.getCodeBase();
});

Clazz.newMeth(C$, 'getParameter$S', function (name) {
return this.stub.getParameter$S(name);
});

Clazz.newMeth(C$, 'getAppletContext', function () {
return this.stub.getAppletContext();
});

Clazz.newMeth(C$, 'setVisible$Z', function (b) {
C$.superclazz.prototype.setVisible$Z.apply(this, [b]);
if (b) this.repaint();
});

Clazz.newMeth(C$, 'resize$I$I', function (width, height) {
var d = this.size();
if ((d.width != width) || (d.height != height) ) {
C$.superclazz.prototype.resize$I$I.apply(this, [width, height]);
if (this.stub != null ) {
this.stub.appletResize$I$I(width, height);
}}});

Clazz.newMeth(C$, 'resize$java_awt_Dimension', function (d) {
this.resize$I$I(d.width, d.height);
});

Clazz.newMeth(C$, 'showStatus$S', function (msg) {
this.getAppletContext().showStatus$S(msg);
});

Clazz.newMeth(C$, 'getImage$java_net_URL', function (url) {
return this.getAppletContext().getImage$java_net_URL(url);
});

Clazz.newMeth(C$, 'getImage$java_net_URL$S', function (url, name) {
try {
return this.getImage$java_net_URL(Clazz.new_((I$[1]||$incl$(1)).c$$java_net_URL$S,[url, name]));
} catch (e) {
if (Clazz.exceptionOf(e, "java.net.MalformedURLException")){
return null;
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'newAudioClip$java_net_URL', function (url) {
return Clazz.new_((I$[2]||$incl$(2)).c$$java_net_URL,[url]);
}, 1);

Clazz.newMeth(C$, 'getAudioClip$java_net_URL', function (url) {
return this.getAppletContext().getAudioClip$java_net_URL(url);
});

Clazz.newMeth(C$, 'getAudioClip$java_net_URL$S', function (url, name) {
try {
return this.getAudioClip$java_net_URL(Clazz.new_((I$[1]||$incl$(1)).c$$java_net_URL$S,[url, name]));
} catch (e) {
if (Clazz.exceptionOf(e, "java.net.MalformedURLException")){
return null;
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'getAppletInfo', function () {
return null;
});

Clazz.newMeth(C$, 'getLocale', function () {
var locale = C$.superclazz.prototype.getLocale.apply(this, []);
if (locale == null ) {
return (I$[3]||$incl$(3)).getDefault();
}return locale;
});

Clazz.newMeth(C$, 'getParameterInfo', function () {
return null;
});

Clazz.newMeth(C$, 'init', function () {
});

Clazz.newMeth(C$, 'start', function () {
});

Clazz.newMeth(C$, 'stop', function () {
});

Clazz.newMeth(C$, 'destroy', function () {
});
})();
//Created 2018-02-06 08:58:05
