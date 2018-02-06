(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['java.io.StringReader']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DataReader", null, 'java.io.BufferedReader');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.ptMark = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$java_io_Reader.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$S,[""])]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_Reader', function ($in) {
C$.superclazz.c$$java_io_Reader.apply(this, [$in]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getBufferedReader', function () {
return this;
});

Clazz.newMeth(C$, 'readBuf$CA$I$I', function (buf, off, len) {
var nRead = 0;
var line = this.readLine();
if (line == null ) return 0;
var linept = 0;
var linelen = line.length$();
for (var i = off; i < len && linelen >= 0 ; i++) {
if (linept >= linelen) {
linept = 0;
buf[i] = "\u000a";
line = this.readLine();
linelen = (line == null  ? -1 : line.length$());
} else {
buf[i] = line.charAt(linept++);
}nRead++;
}
return nRead;
});
})();
//Created 2018-02-06 08:59:03
