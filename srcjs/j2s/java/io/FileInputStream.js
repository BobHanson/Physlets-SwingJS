(function(){var P$=java.io,I$=[['java.io.File','java.io.FileDescriptor','java.io.ByteArrayInputStream','swingjs.JSUtil']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FileInputStream", null, 'java.io.InputStream');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.fd = null;
this.is = null;
this.closed = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.closed = false;
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.c$$java_io_File.apply(this, [name != null  ? Clazz.new_((I$[1]||$incl$(1)).c$$S,[name]) : null]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_File', function (file) {
Clazz.super_(C$, this,1);
var name = (file != null  ? file.getPath() : null);
if (name == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.fd = Clazz.new_((I$[2]||$incl$(2)));
this.fd.incrementAndGetUseCount();
p$.open$java_io_File.apply(this, [file]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_FileDescriptor', function (fdObj) {
Clazz.super_(C$, this,1);
var security = System.getSecurityManager();
if (fdObj == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (security != null ) {
security.checkRead$java_io_FileDescriptor(fdObj);
}this.fd = fdObj;
this.fd.incrementAndGetUseCount();
}, 1);

Clazz.newMeth(C$, 'open$java_io_File', function (file) {
this.is = Clazz.new_((I$[3]||$incl$(3)).c$$BA,[(I$[4]||$incl$(4)).getFileAsBytes$O(file)]);
});

Clazz.newMeth(C$, 'read', function () {
return this.is.read();
});

Clazz.newMeth(C$, 'readBytes$BA$I$I', function (b, off, len) {
return this.is.read$BA$I$I(b, off, len);
});

Clazz.newMeth(C$, 'read$BA', function (b) {
return p$.readBytes$BA$I$I.apply(this, [b, 0, b.length]);
});

Clazz.newMeth(C$, 'read$BA$I$I', function (b, off, len) {
return p$.readBytes$BA$I$I.apply(this, [b, off, len]);
});

Clazz.newMeth(C$, 'skip$J', function (n) {
return this.is.skip$J(n);
});

Clazz.newMeth(C$, 'available', function () {
return this.is.available();
});

Clazz.newMeth(C$, 'close', function () {
if (this.closed) {
return;
}this.is.close();
this.closed = true;
});

Clazz.newMeth(C$, 'getFD', function () {
if (this.fd != null ) return this.fd;
throw Clazz.new_(Clazz.load('java.io.IOException'));
});

Clazz.newMeth(C$, 'finalize', function () {
this.close();
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:30
