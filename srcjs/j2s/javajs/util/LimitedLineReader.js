(function(){var P$=Clazz.newPackage("javajs.util"),I$=[];
var C$=Clazz.newClass(P$, "LimitedLineReader");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.buf = null;
this.cchBuf = 0;
this.ichCurrent = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_io_BufferedReader$I', function (bufferedReader, readLimit) {
C$.$init$.apply(this);
bufferedReader.mark$I(readLimit + 1);
this.buf = Clazz.array(Character.TYPE, [readLimit]);
this.cchBuf = Math.max(bufferedReader.read$CA$I$I(this.buf, 0, readLimit), 0);
this.ichCurrent = 0;
bufferedReader.reset();
}, 1);

Clazz.newMeth(C$, 'getHeader$I', function (n) {
return (n == 0 ?  String.instantialize(this.buf) :  String.instantialize(this.buf, 0, Math.min(this.cchBuf, n)));
});

Clazz.newMeth(C$, 'readLineWithNewline', function () {
while (this.ichCurrent < this.cchBuf){
var ichBeginningOfLine = this.ichCurrent;
var ch = String.fromCharCode(0);
while (this.ichCurrent < this.cchBuf && (ch = this.buf[this.ichCurrent++]) != "\u000d"  && ch != "\u000a" ){
}
if (ch == "\u000d" && this.ichCurrent < this.cchBuf  && this.buf[this.ichCurrent] == "\u000a" ) ++this.ichCurrent;
var cchLine = this.ichCurrent - ichBeginningOfLine;
if (this.buf[ichBeginningOfLine] == "#") continue;
return  String.instantialize(this.buf, ichBeginningOfLine, cchLine);
}
return "";
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:05
