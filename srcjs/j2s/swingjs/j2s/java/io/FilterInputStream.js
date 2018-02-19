(function(){var P$=java.io,I$=[];
var C$=Clazz.newClass(P$, "FilterInputStream", null, 'java.io.InputStream');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$in = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_io_InputStream', function ($in) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.$in = $in;
}, 1);

Clazz.newMeth(C$, 'available', function () {
return this.$in.available();
});

Clazz.newMeth(C$, 'close', function () {
this.$in.close();
});

Clazz.newMeth(C$, 'mark$I', function (readlimit) {
this.$in.mark$I(readlimit);
});

Clazz.newMeth(C$, 'markSupported', function () {
return this.$in.markSupported();
});

Clazz.newMeth(C$, 'read', function () {
return this.$in.read();
});

Clazz.newMeth(C$, 'read$BA', function (buffer) {
return this.read$BA$I$I(buffer, 0, buffer.length);
});

Clazz.newMeth(C$, 'read$BA$I$I', function (buffer, offset, count) {
return this.$in.read$BA$I$I(buffer, offset, count);
});

Clazz.newMeth(C$, 'reset', function () {
this.$in.reset();
});

Clazz.newMeth(C$, 'skip$J', function (count) {
return this.$in.skip$J(count);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:04
