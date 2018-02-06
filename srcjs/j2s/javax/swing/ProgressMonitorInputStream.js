(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['javax.swing.ProgressMonitor']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ProgressMonitorInputStream", null, 'java.io.FilterInputStream');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.monitor = null;
this.nread = 0;
this.size = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.nread = 0;
this.size = 0;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Component$O$java_io_InputStream', function (parentComponent, message, $in) {
C$.superclazz.c$$java_io_InputStream.apply(this, [$in]);
C$.$init$.apply(this);
try {
this.size = $in.available();
} catch (ioe) {
if (Clazz.exceptionOf(ioe, "java.io.IOException")){
this.size = 0;
} else {
throw ioe;
}
}
this.monitor = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Component$O$S$I$I,[parentComponent, message, null, 0, this.size]);
}, 1);

Clazz.newMeth(C$, 'getProgressMonitor', function () {
return this.monitor;
});

Clazz.newMeth(C$, 'read', function () {
var c = this.$in.read();
if (c >= 0) this.monitor.setProgress$I(++this.nread);
if (this.monitor.isCanceled()) {
var exc = Clazz.new_(Clazz.load('java.io.InterruptedIOException').c$$S,["progress"]);
exc.bytesTransferred = this.nread;
throw exc;
}return c;
});

Clazz.newMeth(C$, 'read$BA', function (b) {
var nr = this.$in.read$BA(b);
if (nr > 0) this.monitor.setProgress$I(this.nread = this.nread+(nr));
if (this.monitor.isCanceled()) {
var exc = Clazz.new_(Clazz.load('java.io.InterruptedIOException').c$$S,["progress"]);
exc.bytesTransferred = this.nread;
throw exc;
}return nr;
});

Clazz.newMeth(C$, 'read$BA$I$I', function (b, off, len) {
var nr = this.$in.read$BA$I$I(b, off, len);
if (nr > 0) this.monitor.setProgress$I(this.nread = this.nread+(nr));
if (this.monitor.isCanceled()) {
var exc = Clazz.new_(Clazz.load('java.io.InterruptedIOException').c$$S,["progress"]);
exc.bytesTransferred = this.nread;
throw exc;
}return nr;
});

Clazz.newMeth(C$, 'skip$J', function (n) {
var nr = this.$in.skip$J(n);
if (nr > 0) this.monitor.setProgress$I(this.nread = this.nread+(nr));
return nr;
});

Clazz.newMeth(C$, 'close', function () {
this.$in.close();
this.monitor.close();
});

Clazz.newMeth(C$, 'reset', function () {
this.$in.reset();
this.nread = this.size - this.$in.available();
this.monitor.setProgress$I(this.nread);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:40
