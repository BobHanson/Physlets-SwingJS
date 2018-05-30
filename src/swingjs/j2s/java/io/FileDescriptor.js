(function(){var P$=java.io,I$=[['java.util.concurrent.atomic.AtomicInteger']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FileDescriptor");
var p$=C$.prototype;
C$.$in = null;
C$.out = null;
C$.err = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.$in = Clazz.new_(C$.c$$I,[0]);
C$.out = Clazz.new_(C$.c$$I,[1]);
C$.err = Clazz.new_(C$.c$$I,[2]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.fd = 0;
this.useCount = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.fd=-1;
this.useCount=Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'c$$I', function (fd) {
C$.$init$.apply(this);
this.fd=fd;
this.useCount=Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'valid', function () {
return this.fd != -1;
});

Clazz.newMeth(C$, 'incrementAndGetUseCount', function () {
return this.useCount.incrementAndGet();
});

Clazz.newMeth(C$, 'decrementAndGetUseCount', function () {
return this.useCount.decrementAndGet();
});
})();
//Created 2018-05-24 08:45:33
