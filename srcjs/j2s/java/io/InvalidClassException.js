(function(){var P$=java.io,I$=[];
var C$=Clazz.newClass(P$, "InvalidClassException", null, 'java.io.ObjectStreamException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.classname = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (detailMessage) {
C$.superclazz.c$$S.apply(this, [detailMessage]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$S', function (className, detailMessage) {
C$.superclazz.c$$S.apply(this, [detailMessage]);
C$.$init$.apply(this);
this.classname = className;
}, 1);

Clazz.newMeth(C$, 'getMessage', function () {
var msg = C$.superclazz.prototype.getMessage.apply(this, []);
if (this.classname != null ) {
msg = this.classname + ';' + ' ' + msg ;
}return msg;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:31
