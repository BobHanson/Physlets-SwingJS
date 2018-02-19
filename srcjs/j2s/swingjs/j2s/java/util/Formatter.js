(function(){var P$=java.util,I$=[[['java.util.Formatter','.Flags'],['java.util.Formatter','.Conversion'],'java.util.Calendar','java.util.Locale','java.util.Formatter','java.lang.StringBuilder',['java.util.Formatter','.DateTime'],['java.util.Formatter','.FormattedFloatingDecimal'],'java.text.DateFormatSymbols','java.text.DecimalFormatSymbols','java.text.NumberFormat','java.util.regex.Pattern','javajs.util.Rdr','java.util.ArrayList',['java.util.Formatter','.FixedString'],['java.util.Formatter','.FormatSpecifier'],['java.util.Formatter','.FormatString']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Formatter", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, 'java.io.Flushable');
C$.scaleUp = 0;
var p$=C$.prototype;
C$.fsPattern = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.fsPattern = (I$[12]||$incl$(12)).compile$S("%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])");
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.a = null;
this.l = null;
this.lastException = null;
this.zero = '\0';
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.zero = "0";
}, 1);

Clazz.newMeth(C$, 'init$Appendable$java_util_Locale', function (a, l) {
this.a = a;
this.l = l;
p$.setZero.apply(this, []);
});

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
p$.init$Appendable$java_util_Locale.apply(this, [Clazz.new_((I$[6]||$incl$(6))), (I$[4]||$incl$(4)).getDefault()]);
}, 1);

Clazz.newMeth(C$, 'c$$Appendable', function (a) {
C$.$init$.apply(this);
if (a == null ) a = Clazz.new_((I$[6]||$incl$(6)));
p$.init$Appendable$java_util_Locale.apply(this, [a, (I$[4]||$incl$(4)).getDefault()]);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Locale', function (l) {
C$.$init$.apply(this);
p$.init$Appendable$java_util_Locale.apply(this, [Clazz.new_((I$[6]||$incl$(6))), l]);
}, 1);

Clazz.newMeth(C$, 'c$$Appendable$java_util_Locale', function (a, l) {
C$.$init$.apply(this);
if (a == null ) a = Clazz.new_((I$[6]||$incl$(6)));
p$.init$Appendable$java_util_Locale.apply(this, [a, l]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream', function (os) {
C$.$init$.apply(this);
p$.init$Appendable$java_util_Locale.apply(this, [(I$[13]||$incl$(13)).getBufferedWriter$java_io_OutputStream$S(os, null), (I$[4]||$incl$(4)).getDefault()]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream$S', function (os, csn) {
C$.c$$java_io_OutputStream$S$java_util_Locale.apply(this, [os, csn, (I$[4]||$incl$(4)).getDefault()]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream$S$java_util_Locale', function (os, csn, l) {
C$.$init$.apply(this);
p$.init$Appendable$java_util_Locale.apply(this, [(I$[13]||$incl$(13)).getBufferedWriter$java_io_OutputStream$S(os, csn), l]);
}, 1);

Clazz.newMeth(C$, 'setZero', function () {
if ((this.l != null ) && !this.l.equals$O((I$[4]||$incl$(4)).US) ) {
var dfs = (I$[10]||$incl$(10)).getInstance$java_util_Locale(this.l);
this.zero = dfs.getZeroDigit();
}});

Clazz.newMeth(C$, 'locale', function () {
p$.ensureOpen.apply(this, []);
return this.l;
});

Clazz.newMeth(C$, 'out', function () {
p$.ensureOpen.apply(this, []);
return this.a;
});

Clazz.newMeth(C$, 'toString', function () {
p$.ensureOpen.apply(this, []);
return this.a.toString();
});

Clazz.newMeth(C$, 'flush', function () {
p$.ensureOpen.apply(this, []);
if (Clazz.instanceOf(this.a, "java.io.Flushable")) {
try {
(this.a).flush();
} catch (ioe) {
if (Clazz.exceptionOf(ioe, "java.io.IOException")){
this.lastException = ioe;
} else {
throw ioe;
}
}
}});

Clazz.newMeth(C$, 'close', function () {
if (this.a == null ) return;
try {
if (Clazz.instanceOf(this.a, "java.io.Closeable")) (this.a).close();
} catch (ioe) {
if (Clazz.exceptionOf(ioe, "java.io.IOException")){
this.lastException = ioe;
} else {
throw ioe;
}
} finally {
this.a = null;
}
});

Clazz.newMeth(C$, 'ensureOpen', function () {
if (this.a == null ) throw Clazz.new_(Clazz.load('java.util.FormatterClosedException'));
});

Clazz.newMeth(C$, 'ioException', function () {
return this.lastException;
});

Clazz.newMeth(C$, 'format$S$OA', function (format, args) {
return this.format$java_util_Locale$S$OA(this.l, format, args);
});

Clazz.newMeth(C$, 'format$java_util_Locale$S$OA', function (l, format, args) {
p$.ensureOpen.apply(this, []);
var last = -1;
var lasto = -1;
var fsa = p$.parse$S.apply(this, [format]);
for (var i = 0; i < fsa.length; i++) {
var fs = fsa[i];
var index = fs.index();
try {
switch (index) {
case -2:
fs.printOL$O$java_util_Locale(null, l);
break;
case -1:
if (last < 0 || (args != null  && last > args.length - 1 ) ) throw Clazz.new_(Clazz.load('java.util.MissingFormatArgumentException').c$$S,[fs.toString()]);
fs.printOL$O$java_util_Locale((args == null  ? null : args[last]), l);
break;
case 0:
lasto++;
last = lasto;
if (args != null  && lasto > args.length - 1 ) throw Clazz.new_(Clazz.load('java.util.MissingFormatArgumentException').c$$S,[fs.toString()]);
fs.printOL$O$java_util_Locale((args == null  ? null : args[lasto]), l);
break;
default:
last = index - 1;
if (args != null  && last > args.length - 1 ) throw Clazz.new_(Clazz.load('java.util.MissingFormatArgumentException').c$$S,[fs.toString()]);
fs.printOL$O$java_util_Locale((args == null  ? null : args[last]), l);
break;
}
} catch (x) {
if (Clazz.exceptionOf(x, "java.io.IOException")){
this.lastException = x;
} else {
throw x;
}
}
}
return this;
});

Clazz.newMeth(C$, 'parse$S', function (s) {
var al = Clazz.new_((I$[14]||$incl$(14)));
var m = C$.fsPattern.matcher$CharSequence(s);
var i = 0;
while (i < s.length$()){
var have = m.find$I(i);
if (have) {
if (m.start() != i) {
p$.checkText$S.apply(this, [s.substring(i, m.start())]);
al.add$TE(Clazz.new_((I$[15]||$incl$(15)).c$$S, [this, null, s.substring(i, m.start())]));
}var n = m.groupCount() - 1;
var sa = Clazz.array(java.lang.String, [n]);
for (var j = 0; j < n; j++) {
sa[j] = m.group$I(j + 1);
}
al.add$TE(Clazz.new_((I$[16]||$incl$(16)).c$$java_util_Formatter$SA, [this, null, this, sa]));
i = m.end();
} else {
p$.checkText$S.apply(this, [s.substring(i)]);
al.add$TE(Clazz.new_((I$[15]||$incl$(15)).c$$S, [this, null, s.substring(i)]));
break;
}}
return al.toArray$TTA(Clazz.array((I$[17]||$incl$(17)), [0]));
});

Clazz.newMeth(C$, 'checkText$S', function (s) {
var idx;
if ((idx = s.indexOf("%")) != -1) {
var c = (idx > s.length$() - 2 ? "%" : s.charAt(idx + 1));
throw Clazz.new_(Clazz.load('java.util.UnknownFormatConversionException').c$$S,[String.valueOf(c)]);
}});
;
(function(){var C$=Clazz.newInterface(P$.Formatter, "FormatString", function(){
});
})()
;
(function(){var C$=Clazz.newClass(P$.Formatter, "FixedString", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, [['java.util.Formatter','java.util.Formatter.FormatString']]);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.s = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (s) {
C$.$init$.apply(this);
this.s = s;
}, 1);

Clazz.newMeth(C$, 'index', function () {
return -2;
});

Clazz.newMeth(C$, 'printOL$O$java_util_Locale', function (arg, l) {
this.this$0.a.append$CharSequence(this.s);
});

Clazz.newMeth(C$, 'toString', function () {
return this.s;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Formatter, "FormatSpecifier", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, [['java.util.Formatter','java.util.Formatter.FormatString']]);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$index = 0;
this.f = null;
this.$width = 0;
this.$precision = 0;
this.dt = false;
this.c = '\0';
this.formatter = null;
this.ls = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$index = -1;
this.f = (I$[1]||$incl$(1)).NONE;
this.dt = false;
}, 1);

Clazz.newMeth(C$, 'index$S', function (s) {
if (s != null ) {
try {
this.$index = Integer.parseInt(s.substring(0, s.length$() - 1));
} catch (x) {
if (Clazz.exceptionOf(x, "java.lang.NumberFormatException")){
Clazz.assert(C$, this, function(){return false});
} else {
throw x;
}
}
} else {
this.$index = 0;
}return this.$index;
});

Clazz.newMeth(C$, 'index', function () {
return this.$index;
});

Clazz.newMeth(C$, 'flags$S', function (s) {
this.f = (I$[1]||$incl$(1)).parse$S(s);
if (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).PREVIOUS)) this.$index = -1;
return this.f;
});

Clazz.newMeth(C$, 'width$S', function (s) {
this.$width = -1;
if (s != null ) {
try {
this.$width = Integer.parseInt(s);
if (this.$width < 0) throw Clazz.new_(Clazz.load('java.util.IllegalFormatWidthException').c$$I,[this.$width]);
} catch (x) {
if (Clazz.exceptionOf(x, "java.lang.NumberFormatException")){
Clazz.assert(C$, this, function(){return false});
} else {
throw x;
}
}
}return this.$width;
});

Clazz.newMeth(C$, 'precision$S', function (s) {
this.$precision = -1;
if (s != null ) {
try {
this.$precision = Integer.parseInt(s.substring(1));
if (this.$precision < 0) throw Clazz.new_(Clazz.load('java.util.IllegalFormatPrecisionException').c$$I,[this.$precision]);
} catch (x) {
if (Clazz.exceptionOf(x, "java.lang.NumberFormatException")){
Clazz.assert(C$, this, function(){return false});
} else {
throw x;
}
}
}return this.$precision;
});

Clazz.newMeth(C$, 'conversion$S', function (s) {
this.c = s.charAt(0);
if (!this.dt) {
if (!(I$[2]||$incl$(2)).isValid$C(this.c)) throw Clazz.new_(Clazz.load('java.util.UnknownFormatConversionException').c$$S,[String.valueOf(this.c)]);
if (Character.isUpperCase(this.c)) this.f.add$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE);
this.c = Character.toLowerCase(this.c);
if ((I$[2]||$incl$(2)).isText$C(this.c)) this.$index = -2;
}return this.c;
});

Clazz.newMeth(C$, 'c$$java_util_Formatter$SA', function (formatter, sa) {
C$.$init$.apply(this);
this.formatter = formatter;
p$.index$S.apply(this, [sa[0]]);
p$.flags$S.apply(this, [sa[1]]);
p$.width$S.apply(this, [sa[2]]);
p$.precision$S.apply(this, [sa[3]]);
if (sa[4] != null ) {
this.dt = true;
if (sa[4].equals$O("T")) this.f.add$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE);
}p$.conversion$S.apply(this, [sa[5]]);
if (this.dt) p$.checkDateTime.apply(this, []);
 else if ((I$[2]||$incl$(2)).isGeneral$C(this.c)) p$.checkGeneral.apply(this, []);
 else if ((I$[2]||$incl$(2)).isCharacter$C(this.c)) p$.checkCharacter.apply(this, []);
 else if ((I$[2]||$incl$(2)).isInteger$C(this.c)) p$.checkInteger.apply(this, []);
 else if ((I$[2]||$incl$(2)).isFloat$C(this.c)) p$.checkFloat.apply(this, []);
 else if ((I$[2]||$incl$(2)).isText$C(this.c)) p$.checkText.apply(this, []);
 else throw Clazz.new_(Clazz.load('java.util.UnknownFormatConversionException').c$$S,[String.valueOf(this.c)]);
}, 1);

Clazz.newMeth(C$, 'printOL$O$java_util_Locale', function (arg, l) {
if (this.dt) {
p$.printDateTime$O$java_util_Locale.apply(this, [arg, l]);
return;
}switch (this.c.$c()) {
case "d".$c():
case "o".$c():
case "x".$c():
p$.printInteger$O$java_util_Locale.apply(this, [arg, l]);
break;
case "e".$c():
case "g".$c():
case "f".$c():
case "a".$c():
p$.printFloat$O$java_util_Locale.apply(this, [arg, l]);
break;
case "c".$c():
case "C".$c():
p$.printCharacter$O.apply(this, [arg]);
break;
case "b".$c():
p$.printBoolean$O.apply(this, [arg]);
break;
case "s".$c():
p$.printString$O$java_util_Locale.apply(this, [arg, l]);
break;
case "h".$c():
p$.printHashCode$O.apply(this, [arg]);
break;
case "n".$c():
if (this.ls == null ) this.ls = System.getProperty("line.separator");
this.this$0.a.append$CharSequence(this.ls);
break;
case "%".$c():
this.this$0.a.append$CharSequence("%");
break;
default:
Clazz.assert(C$, this, function(){return false});
}
});

Clazz.newMeth(C$, 'printInteger$O$java_util_Locale', function (arg, l) {
if (arg == null ) p$.printS$S.apply(this, ["null"]);
 else if (Clazz.instanceOf(arg, "java.lang.Byte")) p$.printB$B$java_util_Locale.apply(this, [($b$[0] = (arg).byteValue(), $b$[0]), l]);
 else if (Clazz.instanceOf(arg, "java.lang.Short")) p$.printSh$H$java_util_Locale.apply(this, [(arg).shortValue(), l]);
 else if (Clazz.instanceOf(arg, "java.lang.Integer")) p$.printI$I$java_util_Locale.apply(this, [(arg).intValue(), l]);
 else if (Clazz.instanceOf(arg, "java.lang.Long")) p$.printL$J$java_util_Locale.apply(this, [(arg).longValue(), l]);
 else p$.failConversion$C$O.apply(this, [this.c, arg]);
});

Clazz.newMeth(C$, 'printFloat$O$java_util_Locale', function (arg, l) {
if (arg == null ) p$.printS$S.apply(this, ["null"]);
 else if (Clazz.instanceOf(arg, "java.lang.Float")) p$.printF$F$java_util_Locale.apply(this, [(arg).floatValue(), l]);
 else if (Clazz.instanceOf(arg, "java.lang.Double")) p$.printDL$D$java_util_Locale.apply(this, [(arg).doubleValue(), l]);
 else p$.failConversion$C$O.apply(this, [this.c, arg]);
});

Clazz.newMeth(C$, 'printDateTime$O$java_util_Locale', function (arg, l) {
if (arg == null ) {
p$.printS$S.apply(this, ["null"]);
return;
}var cal = null;
if (Clazz.instanceOf(arg, "java.util.Date")) {
cal = (I$[3]||$incl$(3)).getInstance$java_util_Locale(l == null  ? (I$[4]||$incl$(4)).US : l);
cal.setTime$java_util_Date(arg);
} else if (Clazz.instanceOf(arg, "java.util.Calendar")) {
cal = (arg).clone();
cal.setLenient$Z(true);
} else {
p$.failConversion$C$O.apply(this, [this.c, arg]);
}p$.printDT$java_util_Calendar$C$java_util_Locale.apply(this, [cal, this.c, l]);
});

Clazz.newMeth(C$, 'printCharacter$O', function (arg) {
if (arg == null ) {
p$.printS$S.apply(this, ["null"]);
return;
}var s = null;
if (Clazz.instanceOf(arg, "java.lang.Character")) {
s = (arg).toString();
} else if (Clazz.instanceOf(arg, "java.lang.Byte")) {
var i = ($b$[0] = (arg).byteValue(), $b$[0]);
if (Character.isValidCodePoint(i)) s =  String.instantialize(Character.toChars(i));
 else throw Clazz.new_(Clazz.load('java.util.IllegalFormatCodePointException').c$$I,[i]);
} else if (Clazz.instanceOf(arg, "java.lang.Short")) {
var i = (arg).shortValue();
if (Character.isValidCodePoint(i)) s =  String.instantialize(Character.toChars(i));
 else throw Clazz.new_(Clazz.load('java.util.IllegalFormatCodePointException').c$$I,[i]);
} else if (Clazz.instanceOf(arg, "java.lang.Integer")) {
var i = (arg).intValue();
if (Character.isValidCodePoint(i)) s =  String.instantialize(Character.toChars(i));
 else throw Clazz.new_(Clazz.load('java.util.IllegalFormatCodePointException').c$$I,[i]);
} else {
p$.failConversion$C$O.apply(this, [this.c, arg]);
}p$.printS$S.apply(this, [s]);
});

Clazz.newMeth(C$, 'printString$O$java_util_Locale', function (arg, l) {
if (arg == null ) {
p$.printS$S.apply(this, ["null"]);
} else if (Clazz.instanceOf(arg, "java.util.Formattable")) {
var fmt = this.formatter;
if (this.formatter.locale() !== l ) fmt = Clazz.new_((I$[5]||$incl$(5)).c$$Appendable$java_util_Locale,[this.formatter.out(), l]);
(arg).formatTo$java_util_Formatter$I$I$I(fmt, this.f.$valueOf(), this.$width, this.$precision);
} else {
p$.printS$S.apply(this, [arg.toString()]);
}});

Clazz.newMeth(C$, 'printBoolean$O', function (arg) {
var s;
if (arg != null ) s = ((Clazz.instanceOf(arg, "java.lang.Boolean")) ? (arg).toString() : "true");
 else s = "false";
p$.printS$S.apply(this, [s]);
});

Clazz.newMeth(C$, 'printHashCode$O', function (arg) {
var s = (arg == null  ? "null" : Integer.toHexString(arg.hashCode()));
p$.printS$S.apply(this, [s]);
});

Clazz.newMeth(C$, 'printS$S', function (s) {
if (this.$precision != -1 && this.$precision < s.length$() ) s = s.substring(0, this.$precision);
if (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE)) s = s.toUpperCase();
this.this$0.a.append$CharSequence(p$.justify$S.apply(this, [s]));
});

Clazz.newMeth(C$, 'justify$S', function (s) {
if (this.$width == -1) return s;
var sb = Clazz.new_((I$[6]||$incl$(6)));
var pad = this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).LEFT_JUSTIFY);
var sp = this.$width - s.length$();
if (!pad) for (var i = 0; i < sp; i++) sb.append$C(" ");

sb.append$S(s);
if (pad) for (var i = 0; i < sp; i++) sb.append$C(" ");

return sb.toString();
});

Clazz.newMeth(C$, 'toString', function () {
var sb = Clazz.new_((I$[6]||$incl$(6)).c$$I,["%".$c()]);
var dupf = this.f.dup().remove$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE);
sb.append$S(dupf.toString());
if (this.$index > 0) sb.append$I(this.$index).append$C("$");
if (this.$width != -1) sb.append$I(this.$width);
if (this.$precision != -1) sb.append$C(".").append$I(this.$precision);
if (this.dt) sb.append$C(this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE) ? "T" : "t");
sb.append$C(this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE) ? Character.toUpperCase(this.c) : this.c);
return sb.toString();
});

Clazz.newMeth(C$, 'checkGeneral', function () {
if ((this.c == "b" || this.c == "h" ) && this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ALTERNATE) ) p$.failMismatch$java_util_Formatter_Flags$C.apply(this, [(I$[1]||$incl$(1)).ALTERNATE, this.c]);
if (this.$width == -1 && this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).LEFT_JUSTIFY) ) throw Clazz.new_(Clazz.load('java.util.MissingFormatWidthException').c$$S,[this.toString()]);
p$.checkBadFlags$java_util_Formatter_FlagsA.apply(this, [[(I$[1]||$incl$(1)).PLUS, (I$[1]||$incl$(1)).LEADING_SPACE, (I$[1]||$incl$(1)).ZERO_PAD, (I$[1]||$incl$(1)).GROUP, (I$[1]||$incl$(1)).PARENTHESES]]);
});

Clazz.newMeth(C$, 'checkDateTime', function () {
if (this.$precision != -1) throw Clazz.new_(Clazz.load('java.util.IllegalFormatPrecisionException').c$$I,[this.$precision]);
if (!(I$[7]||$incl$(7)).isValid$C(this.c)) throw Clazz.new_(Clazz.load('java.util.UnknownFormatConversionException').c$$S,["t" + this.c]);
p$.checkBadFlags$java_util_Formatter_FlagsA.apply(this, [[(I$[1]||$incl$(1)).ALTERNATE, (I$[1]||$incl$(1)).PLUS, (I$[1]||$incl$(1)).LEADING_SPACE, (I$[1]||$incl$(1)).ZERO_PAD, (I$[1]||$incl$(1)).GROUP, (I$[1]||$incl$(1)).PARENTHESES]]);
if (this.$width == -1 && this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).LEFT_JUSTIFY) ) throw Clazz.new_(Clazz.load('java.util.MissingFormatWidthException').c$$S,[this.toString()]);
});

Clazz.newMeth(C$, 'checkCharacter', function () {
if (this.$precision != -1) throw Clazz.new_(Clazz.load('java.util.IllegalFormatPrecisionException').c$$I,[this.$precision]);
p$.checkBadFlags$java_util_Formatter_FlagsA.apply(this, [[(I$[1]||$incl$(1)).ALTERNATE, (I$[1]||$incl$(1)).PLUS, (I$[1]||$incl$(1)).LEADING_SPACE, (I$[1]||$incl$(1)).ZERO_PAD, (I$[1]||$incl$(1)).GROUP, (I$[1]||$incl$(1)).PARENTHESES]]);
if (this.$width == -1 && this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).LEFT_JUSTIFY) ) throw Clazz.new_(Clazz.load('java.util.MissingFormatWidthException').c$$S,[this.toString()]);
});

Clazz.newMeth(C$, 'checkInteger', function () {
p$.checkNumeric.apply(this, []);
if (this.$precision != -1) throw Clazz.new_(Clazz.load('java.util.IllegalFormatPrecisionException').c$$I,[this.$precision]);
if (this.c == "d") p$.checkBadFlags$java_util_Formatter_FlagsA.apply(this, [[(I$[1]||$incl$(1)).ALTERNATE]]);
 else if (this.c == "o") p$.checkBadFlags$java_util_Formatter_FlagsA.apply(this, [[(I$[1]||$incl$(1)).GROUP]]);
 else p$.checkBadFlags$java_util_Formatter_FlagsA.apply(this, [[(I$[1]||$incl$(1)).GROUP]]);
});

Clazz.newMeth(C$, 'checkBadFlags$java_util_Formatter_FlagsA', function (badFlags) {
for (var i = 0; i < badFlags.length; i++) if (this.f.contains$java_util_Formatter_Flags(badFlags[i])) p$.failMismatch$java_util_Formatter_Flags$C.apply(this, [badFlags[i], this.c]);

});

Clazz.newMeth(C$, 'checkFloat', function () {
p$.checkNumeric.apply(this, []);
if (this.c == "f") {
} else if (this.c == "a") {
p$.checkBadFlags$java_util_Formatter_FlagsA.apply(this, [[(I$[1]||$incl$(1)).PARENTHESES, (I$[1]||$incl$(1)).GROUP]]);
} else if (this.c == "e") {
p$.checkBadFlags$java_util_Formatter_FlagsA.apply(this, [[(I$[1]||$incl$(1)).GROUP]]);
} else if (this.c == "g") {
p$.checkBadFlags$java_util_Formatter_FlagsA.apply(this, [[(I$[1]||$incl$(1)).ALTERNATE]]);
}});

Clazz.newMeth(C$, 'checkNumeric', function () {
if (this.$width != -1 && this.$width < 0 ) throw Clazz.new_(Clazz.load('java.util.IllegalFormatWidthException').c$$I,[this.$width]);
if (this.$precision != -1 && this.$precision < 0 ) throw Clazz.new_(Clazz.load('java.util.IllegalFormatPrecisionException').c$$I,[this.$precision]);
if (this.$width == -1 && (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).LEFT_JUSTIFY) || this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ZERO_PAD) ) ) throw Clazz.new_(Clazz.load('java.util.MissingFormatWidthException').c$$S,[this.toString()]);
if ((this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).PLUS) && this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).LEADING_SPACE) ) || (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).LEFT_JUSTIFY) && this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ZERO_PAD) ) ) throw Clazz.new_(Clazz.load('java.util.IllegalFormatFlagsException').c$$S,[this.f.toString()]);
});

Clazz.newMeth(C$, 'checkText', function () {
if (this.$precision != -1) throw Clazz.new_(Clazz.load('java.util.IllegalFormatPrecisionException').c$$I,[this.$precision]);
switch (this.c.$c()) {
case "%".$c():
if (this.f.$valueOf() != (I$[1]||$incl$(1)).LEFT_JUSTIFY.$valueOf() && this.f.$valueOf() != (I$[1]||$incl$(1)).NONE.$valueOf() ) throw Clazz.new_(Clazz.load('java.util.IllegalFormatFlagsException').c$$S,[this.f.toString()]);
if (this.$width == -1 && this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).LEFT_JUSTIFY) ) throw Clazz.new_(Clazz.load('java.util.MissingFormatWidthException').c$$S,[this.toString()]);
break;
case "n".$c():
if (this.$width != -1) throw Clazz.new_(Clazz.load('java.util.IllegalFormatWidthException').c$$I,[this.$width]);
if (this.f.$valueOf() != (I$[1]||$incl$(1)).NONE.$valueOf()) throw Clazz.new_(Clazz.load('java.util.IllegalFormatFlagsException').c$$S,[this.f.toString()]);
break;
default:
Clazz.assert(C$, this, function(){return false});
}
});

Clazz.newMeth(C$, 'printB$B$java_util_Locale', function (value, l) {
var v = value;
if (value < 0 && (this.c == "o" || this.c == "x" ) ) {
v = v+(256);
Clazz.assert(C$, this, function(){return v >= 0}, function(){return v});
}p$.printL$J$java_util_Locale.apply(this, [v, l]);
});

Clazz.newMeth(C$, 'printSh$H$java_util_Locale', function (value, l) {
var v = value;
if (value < 0 && (this.c == "o" || this.c == "x" ) ) {
v = v+(65536);
Clazz.assert(C$, this, function(){return v >= 0}, function(){return v});
}p$.printL$J$java_util_Locale.apply(this, [v, l]);
});

Clazz.newMeth(C$, 'printI$I$java_util_Locale', function (value, l) {
var v = value;
if (value < 0 && (this.c == "o" || this.c == "x" ) ) {
v = v+(4294967296);
Clazz.assert(C$, this, function(){return v >= 0}, function(){return v});
}p$.printL$J$java_util_Locale.apply(this, [v, l]);
});

Clazz.newMeth(C$, 'printL$J$java_util_Locale', function (value, l) {
var sb = Clazz.new_((I$[6]||$incl$(6)));
if (this.c == "d") {
var neg = value < 0;
var va;
if (value < 0) va = Long.toString(value, 10).substring(1).toCharArray();
 else va = Long.toString(value, 10).toCharArray();
p$.leadingSign$StringBuilder$Z.apply(this, [sb, neg]);
p$.localizedMagnitude$StringBuilder$CA$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [sb, va, this.f, p$.adjustWidth$I$java_util_Formatter_Flags$Z.apply(this, [this.$width, this.f, neg]), l]);
p$.trailingSign$StringBuilder$Z.apply(this, [sb, neg]);
} else if (this.c == "o") {
p$.checkBadFlags$java_util_Formatter_FlagsA.apply(this, [[(I$[1]||$incl$(1)).PARENTHESES, (I$[1]||$incl$(1)).LEADING_SPACE, (I$[1]||$incl$(1)).PLUS]]);
var s = Long.toOctalString(value);
var len = (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ALTERNATE) ? s.length$() + 1 : s.length$());
if (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ALTERNATE)) sb.append$C("0");
if (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ZERO_PAD)) for (var i = 0; i < this.$width - len; i++) sb.append$C("0");

sb.append$S(s);
} else if (this.c == "x") {
p$.checkBadFlags$java_util_Formatter_FlagsA.apply(this, [[(I$[1]||$incl$(1)).PARENTHESES, (I$[1]||$incl$(1)).LEADING_SPACE, (I$[1]||$incl$(1)).PLUS]]);
var s = Long.toHexString(value);
var len = (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ALTERNATE) ? s.length$() + 2 : s.length$());
if (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ALTERNATE)) sb.append$S(this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE) ? "0X" : "0x");
if (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ZERO_PAD)) for (var i = 0; i < this.$width - len; i++) sb.append$C("0");

if (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE)) s = s.toUpperCase();
sb.append$S(s);
}this.this$0.a.append$CharSequence(p$.justify$S.apply(this, [sb.toString()]));
});

Clazz.newMeth(C$, 'leadingSign$StringBuilder$Z', function (sb, neg) {
if (!neg) {
if (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).PLUS)) {
sb.append$C("+");
} else if (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).LEADING_SPACE)) {
sb.append$C(" ");
}} else {
if (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).PARENTHESES)) sb.append$C("(");
 else sb.append$C("-");
}return sb;
});

Clazz.newMeth(C$, 'trailingSign$StringBuilder$Z', function (sb, neg) {
if (neg && this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).PARENTHESES) ) sb.append$C(")");
return sb;
});

Clazz.newMeth(C$, 'printF$F$java_util_Locale', function (value, l) {
p$.printDL$D$java_util_Locale.apply(this, [value, l]);
});

Clazz.newMeth(C$, 'printDL$D$java_util_Locale', function (value, l) {
var sb = Clazz.new_((I$[6]||$incl$(6)));
var neg = Double.compare(value, 0.0) == -1;
if (!Double.isNaN(value)) {
var v = Math.abs(value);
p$.leadingSign$StringBuilder$Z.apply(this, [sb, neg]);
if (!Double.isInfinite(v)) p$.printD$StringBuilder$D$java_util_Locale$java_util_Formatter_Flags$C$I$Z.apply(this, [sb, v, l, this.f, this.c, this.$precision, neg]);
 else sb.append$S(this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE) ? "INFINITY" : "Infinity");
p$.trailingSign$StringBuilder$Z.apply(this, [sb, neg]);
} else {
sb.append$S(this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE) ? "NAN" : "NaN");
}this.this$0.a.append$CharSequence(p$.justify$S.apply(this, [sb.toString()]));
});

Clazz.newMeth(C$, 'printD$StringBuilder$D$java_util_Locale$java_util_Formatter_Flags$C$I$Z', function (sb, value, l, f, c, precision, neg) {
if (c == "e") {
var prec = (precision == -1 ? 6 : precision);
var fd = Clazz.new_((I$[8]||$incl$(8)).c$$D$I$I, [this, null, value, prec, 2]);
var v = Clazz.array(Character.TYPE, [30]);
var len = fd.getChars$CA(v);
var mant = p$.addZeros$CA$I.apply(this, [p$.mantissa$CA$I.apply(this, [v, len]), prec]);
if (f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ALTERNATE) && (prec == 0) ) mant = p$.addDot$CA.apply(this, [mant]);
var exp = (value == 0.0 ) ? Clazz.array(Character.TYPE, -1, ["+", "0", "0"]) : p$.exponent$CA$I.apply(this, [v, len]);
var newW = this.$width;
if (this.$width != -1) newW = p$.adjustWidth$I$java_util_Formatter_Flags$Z.apply(this, [this.$width - exp.length - 1 , f, neg]);
p$.localizedMagnitude$StringBuilder$CA$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [sb, mant, f, newW, l]);
sb.append$C(f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE) ? "E" : "e");
var flags = f.dup().remove$java_util_Formatter_Flags((I$[1]||$incl$(1)).GROUP);
var sign = exp[0];
sb.append$C(sign);
var tmp = Clazz.array(Character.TYPE, [exp.length - 1]);
System.arraycopy(exp, 1, tmp, 0, exp.length - 1);
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$CA$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, tmp, flags, -1, l]));
} else if (c == "f") {
var prec = (precision == -1 ? 6 : precision);
var fd = Clazz.new_((I$[8]||$incl$(8)).c$$D$I$I, [this, null, value, prec, 1]);
var v = Clazz.array(Character.TYPE, [30 + 1 + Math.abs(fd.getExponent()) ]);
var len = fd.getChars$CA(v);
var mant = p$.addZeros$CA$I.apply(this, [p$.mantissa$CA$I.apply(this, [v, len]), prec]);
if (f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ALTERNATE) && (prec == 0) ) mant = p$.addDot$CA.apply(this, [mant]);
var newW = this.$width;
if (this.$width != -1) newW = p$.adjustWidth$I$java_util_Formatter_Flags$Z.apply(this, [this.$width, f, neg]);
p$.localizedMagnitude$StringBuilder$CA$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [sb, mant, f, newW, l]);
} else if (c == "g") {
var prec = precision;
if (precision == -1) prec = 6;
 else if (precision == 0) prec = 1;
var fd = Clazz.new_((I$[8]||$incl$(8)).c$$D$I$I, [this, null, value, prec, 0]);
var v = Clazz.array(Character.TYPE, [30 + 1 + Math.abs(fd.getExponent()) ]);
var len = fd.getChars$CA(v);
var exp = p$.exponent$CA$I.apply(this, [v, len]);
if (exp != null ) {
prec = prec-(1);
} else {
prec = prec - (value == 0  ? 0 : fd.getExponentRounded()) - 1 ;
}var mant = p$.addZeros$CA$I.apply(this, [p$.mantissa$CA$I.apply(this, [v, len]), prec]);
if (f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ALTERNATE) && (prec == 0) ) mant = p$.addDot$CA.apply(this, [mant]);
var newW = this.$width;
if (this.$width != -1) {
if (exp != null ) newW = p$.adjustWidth$I$java_util_Formatter_Flags$Z.apply(this, [this.$width - exp.length - 1 , f, neg]);
 else newW = p$.adjustWidth$I$java_util_Formatter_Flags$Z.apply(this, [this.$width, f, neg]);
}p$.localizedMagnitude$StringBuilder$CA$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [sb, mant, f, newW, l]);
if (exp != null ) {
sb.append$C(f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE) ? "E" : "e");
var flags = f.dup().remove$java_util_Formatter_Flags((I$[1]||$incl$(1)).GROUP);
var sign = exp[0];
sb.append$C(sign);
var tmp = Clazz.array(Character.TYPE, [exp.length - 1]);
System.arraycopy(exp, 1, tmp, 0, exp.length - 1);
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$CA$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, tmp, flags, -1, l]));
}} else if (c == "a") {
var prec = precision;
if (precision == -1) prec = 0;
 else if (precision == 0) prec = 1;
var s = p$.hexDouble$D$I.apply(this, [value, prec]);
var va;
var upper = f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE);
sb.append$S(upper ? "0X" : "0x");
if (f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ZERO_PAD)) for (var i = 0; i < this.$width - s.length$() - 2 ; i++) sb.append$C("0");

var idx = s.indexOf("p");
va = s.substring(0, idx).toCharArray();
if (upper) {
var tmp =  String.instantialize(va);
tmp = tmp.toUpperCase();
va = tmp.toCharArray();
}sb.append$CA(prec != 0 ? p$.addZeros$CA$I.apply(this, [va, prec]) : va);
sb.append$C(upper ? "P" : "p");
sb.append$S(s.substring(idx + 1));
}});

Clazz.newMeth(C$, 'mantissa$CA$I', function (v, len) {
var i;
for (i = 0; i < len; i++) {
if (v[i] == "e") break;
}
var tmp = Clazz.array(Character.TYPE, [i]);
System.arraycopy(v, 0, tmp, 0, i);
return tmp;
});

Clazz.newMeth(C$, 'exponent$CA$I', function (v, len) {
var i;
for (i = len - 1; i >= 0; i--) {
if (v[i] == "e") break;
}
if (i == -1) return null;
var tmp = Clazz.array(Character.TYPE, [len - i - 1 ]);
System.arraycopy(v, i + 1, tmp, 0, len - i - 1 );
return tmp;
});

Clazz.newMeth(C$, 'addZeros$CA$I', function (v, prec) {
var i;
for (i = 0; i < v.length; i++) {
if (v[i] == ".") break;
}
var needDot = false;
if (i == v.length) {
needDot = true;
}var outPrec = v.length - i - (needDot ? 0 : 1) ;
Clazz.assert(C$, this, function(){return (outPrec <= prec)});
if (outPrec == prec) return v;
var tmp = Clazz.array(Character.TYPE, [v.length + prec - outPrec + (needDot ? 1 : 0)]);
System.arraycopy(v, 0, tmp, 0, v.length);
var start = v.length;
if (needDot) {
tmp[v.length] = ".";
start++;
}for (var j = start; j < tmp.length; j++) tmp[j] = "0";

return tmp;
});

Clazz.newMeth(C$, 'hexDouble$D$I', function (d, prec) {
return Integer.toHexString((d|0));
});

Clazz.newMeth(C$, 'adjustWidth$I$java_util_Formatter_Flags$Z', function (width, f, neg) {
var newW = width;
if (newW != -1 && neg  && f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).PARENTHESES) ) newW--;
return newW;
});

Clazz.newMeth(C$, 'addDot$CA', function (mant) {
var tmp = mant;
tmp = Clazz.array(Character.TYPE, [mant.length + 1]);
System.arraycopy(mant, 0, tmp, 0, mant.length);
tmp[tmp.length - 1] = ".";
return tmp;
});

Clazz.newMeth(C$, 'printDT$java_util_Calendar$C$java_util_Locale', function (t, c, l) {
var sb = Clazz.new_((I$[6]||$incl$(6)));
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, c, l]);
var s = p$.justify$S.apply(this, [sb.toString()]);
if (this.f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).UPPERCASE)) s = s.toUpperCase();
this.this$0.a.append$CharSequence(s);
});

Clazz.newMeth(C$, 'printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale', function (sb, t, c, l) {
if (sb == null ) sb = Clazz.new_((I$[6]||$incl$(6)));
switch (c.$c()) {
case "H".$c():
case "I".$c():
case "k".$c():
case "l".$c():
{
var i = t.get$I(11);
if (c == "I" || c == "l" ) i = (i == 0 || i == 12  ? 12 : i % 12);
var flags = (c == "H" || c == "I"  ? (I$[1]||$incl$(1)).ZERO_PAD : (I$[1]||$incl$(1)).NONE);
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, i, flags, 2, l]));
break;
}case "M".$c():
{
var i = t.get$I(12);
var flags = (I$[1]||$incl$(1)).ZERO_PAD;
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, i, flags, 2, l]));
break;
}case "N".$c():
{
var i = t.get$I(14) * 1000000;
var flags = (I$[1]||$incl$(1)).ZERO_PAD;
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, i, flags, 9, l]));
break;
}case "L".$c():
{
var i = t.get$I(14);
var flags = (I$[1]||$incl$(1)).ZERO_PAD;
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, i, flags, 3, l]));
break;
}case "Q".$c():
{
var i = t.getTimeInMillis();
var flags = (I$[1]||$incl$(1)).NONE;
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, i, flags, this.$width, l]));
break;
}case "p".$c():
{
var ampm = Clazz.array(java.lang.String, -1, ["AM", "PM"]);
if (l != null  && l !== (I$[4]||$incl$(4)).US  ) {
var dfs = (I$[9]||$incl$(9)).getInstance$java_util_Locale(l);
ampm = dfs.getAmPmStrings();
}var s = ampm[t.get$I(9)];
sb.append$S(s.toLowerCase());
break;
}case "s".$c():
{
var i = (t.getTimeInMillis()/1000|0);
var flags = (I$[1]||$incl$(1)).NONE;
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, i, flags, this.$width, l]));
break;
}case "S".$c():
{
var i = t.get$I(13);
var flags = (I$[1]||$incl$(1)).ZERO_PAD;
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, i, flags, 2, l]));
break;
}case "z".$c():
{
var i = t.get$I(15) + t.get$I(16);
var neg = i < 0;
sb.append$C(neg ? "-" : "+");
if (neg) i = -i;
var min = (i/60000|0);
var offset = ((min/60|0)) * 100 + (min % 60);
var flags = (I$[1]||$incl$(1)).ZERO_PAD;
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, offset, flags, 4, l]));
break;
}case "Z".$c():
{
throw Clazz.new_(Clazz.load('java.io.UnsupportedEncodingException'));
}case "a".$c():
case "A".$c():
{
var i = t.get$I(7);
var lt = ((l == null ) ? (I$[4]||$incl$(4)).US : l);
var dfs = (I$[9]||$incl$(9)).getInstance$java_util_Locale(lt);
if (c == "A") sb.append$S(dfs.getWeekdays()[i]);
 else sb.append$S(dfs.getShortWeekdays()[i]);
break;
}case "b".$c():
case "h".$c():
case "B".$c():
{
var i = t.get$I(2);
var lt = ((l == null ) ? (I$[4]||$incl$(4)).US : l);
var dfs = (I$[9]||$incl$(9)).getInstance$java_util_Locale(lt);
if (c == "B") sb.append$S(dfs.getMonths()[i]);
 else sb.append$S(dfs.getShortMonths()[i]);
break;
}case "C".$c():
case "y".$c():
case "Y".$c():
{
var i = t.get$I(1);
var size = 2;
switch (c.$c()) {
case "C".$c():
i = (i/(100)|0);
break;
case "y".$c():
i = i%(100);
break;
case "Y".$c():
size = 4;
break;
}
var flags = (I$[1]||$incl$(1)).ZERO_PAD;
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, i, flags, size, l]));
break;
}case "d".$c():
case "e".$c():
{
var i = t.get$I(5);
var flags = (c == "d" ? (I$[1]||$incl$(1)).ZERO_PAD : (I$[1]||$incl$(1)).NONE);
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, i, flags, 2, l]));
break;
}case "j".$c():
{
var i = t.get$I(6);
var flags = (I$[1]||$incl$(1)).ZERO_PAD;
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, i, flags, 3, l]));
break;
}case "m".$c():
{
var i = t.get$I(2) + 1;
var flags = (I$[1]||$incl$(1)).ZERO_PAD;
sb.append$CharSequence(p$.localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [null, i, flags, 2, l]));
break;
}case "T".$c():
case "R".$c():
{
var sep = ":";
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "H", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "M", l]);
if (c == "T") {
sb.append$S(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "S", l]);
}break;
}case "r".$c():
{
var sep = ":";
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "I", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "M", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "S", l]).append$CharSequence(" ");
var tsb = Clazz.new_((I$[6]||$incl$(6)));
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [tsb, t, "p", l]);
sb.append$S(tsb.toString().toUpperCase());
break;
}case "c".$c():
{
var sep = " ";
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "a", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "b", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "d", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "T", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "Z", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "Y", l]);
break;
}case "D".$c():
{
var sep = "/";
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "m", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "d", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "y", l]);
break;
}case "F".$c():
{
var sep = "-";
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "Y", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "m", l]).append$CharSequence(sep);
p$.printDTL$StringBuilder$java_util_Calendar$C$java_util_Locale.apply(this, [sb, t, "d", l]);
break;
}default:
Clazz.assert(C$, this, function(){return false});
}
return sb;
});

Clazz.newMeth(C$, 'failMismatch$java_util_Formatter_Flags$C', function (f, c) {
var fs = f.toString();
throw Clazz.new_(Clazz.load('java.util.FormatFlagsConversionMismatchException').c$$S$C,[fs, c]);
});

Clazz.newMeth(C$, 'failConversion$C$O', function (c, arg) {
throw Clazz.new_(Clazz.load('java.util.IllegalFormatConversionException').c$$C$Class,[c, arg.getClass()]);
});

Clazz.newMeth(C$, 'getZero$java_util_Locale', function (l) {
if ((l != null ) && !l.equals$O(this.this$0.locale()) ) {
var dfs = (I$[10]||$incl$(10)).getInstance$java_util_Locale(l);
return dfs.getZeroDigit();
}return this.this$0.zero;
});

Clazz.newMeth(C$, 'localizedMagnitude$StringBuilder$J$java_util_Formatter_Flags$I$java_util_Locale', function (sb, value, f, width, l) {
var va = Long.toString(value, 10).toCharArray();
return p$.localizedMagnitude$StringBuilder$CA$java_util_Formatter_Flags$I$java_util_Locale.apply(this, [sb, va, f, width, l]);
});

Clazz.newMeth(C$, 'localizedMagnitude$StringBuilder$CA$java_util_Formatter_Flags$I$java_util_Locale', function (sb, value, f, width, l) {
if (sb == null ) sb = Clazz.new_((I$[6]||$incl$(6)));
var begin = sb.length$();
var zero = p$.getZero$java_util_Locale.apply(this, [l]);
var grpSep = "\u0000";
var grpSize = -1;
var decSep = "\u0000";
var len = value.length;
var dot = len;
for (var j = 0; j < len; j++) {
if (value[j] == ".") {
dot = j;
break;
}}
if (dot < len) {
if (l == null  || l.equals$O((I$[4]||$incl$(4)).US) ) {
decSep = ".";
} else {
var dfs = (I$[10]||$incl$(10)).getInstance$java_util_Locale(l);
decSep = dfs.getDecimalSeparator();
}}if (f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).GROUP)) {
if (l == null  || l.equals$O((I$[4]||$incl$(4)).US) ) {
grpSep = ",";
grpSize = 3;
} else {
var dfs = (I$[10]||$incl$(10)).getInstance$java_util_Locale(l);
grpSep = dfs.getGroupingSeparator();
var df = (I$[11]||$incl$(11)).getIntegerInstance$java_util_Locale(l);
grpSize = df.getGroupingSize();
}}for (var j = 0; j < len; j++) {
if (j == dot) {
sb.append$C(decSep);
grpSep = "\u0000";
continue;
}var c = value[j];
sb.append$C(String.fromCharCode(((c.$c() - 48) + zero.$c())));
if (grpSep != "\u0000" && j != dot - 1  && ((dot - j) % grpSize == 1) ) sb.append$C(grpSep);
}
len = sb.length$();
if (width != -1 && f.contains$java_util_Formatter_Flags((I$[1]||$incl$(1)).ZERO_PAD) ) for (var k = 0; k < width - len; k++) sb.insert$I$C(begin, zero);

return sb;
});
C$.$_ASSERT_ENABLED_ = ClassLoader.$getClassAssertionStatus(C$);
var $b$ = new Int8Array(1);
})()
;
(function(){var C$=Clazz.newClass(P$.Formatter, "Flags", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});
C$.NONE = null;
C$.LEFT_JUSTIFY = null;
C$.UPPERCASE = null;
C$.ALTERNATE = null;
C$.PLUS = null;
C$.LEADING_SPACE = null;
C$.ZERO_PAD = null;
C$.GROUP = null;
C$.PARENTHESES = null;
C$.PREVIOUS = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.NONE = Clazz.new_(C$.c$$I,[0]);
C$.LEFT_JUSTIFY = Clazz.new_(C$.c$$I,[1]);
C$.UPPERCASE = Clazz.new_(C$.c$$I,[2]);
C$.ALTERNATE = Clazz.new_(C$.c$$I,[4]);
C$.PLUS = Clazz.new_(C$.c$$I,[8]);
C$.LEADING_SPACE = Clazz.new_(C$.c$$I,[16]);
C$.ZERO_PAD = Clazz.new_(C$.c$$I,[32]);
C$.GROUP = Clazz.new_(C$.c$$I,[64]);
C$.PARENTHESES = Clazz.new_(C$.c$$I,[128]);
C$.PREVIOUS = Clazz.new_(C$.c$$I,[256]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.flags = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (f) {
C$.$init$.apply(this);
this.flags = f;
}, 1);

Clazz.newMeth(C$, '$valueOf', function () {
return this.flags;
});

Clazz.newMeth(C$, 'contains$java_util_Formatter_Flags', function (f) {
return (this.flags & f.$valueOf()) == f.$valueOf();
});

Clazz.newMeth(C$, 'dup', function () {
return Clazz.new_(C$.c$$I,[this.flags]);
});

Clazz.newMeth(C$, 'add$java_util_Formatter_Flags', function (f) {
this.flags = this.flags|(f.$valueOf());
return this;
});

Clazz.newMeth(C$, 'remove$java_util_Formatter_Flags', function (f) {
this.flags = this.flags&(~f.$valueOf());
return this;
});

Clazz.newMeth(C$, 'parse$S', function (s) {
var f = Clazz.new_(C$.c$$I,[0]);
if (s == null ) return f;
var ca = s.toCharArray();
for (var i = 0; i < ca.length; i++) {
var v = C$.parseChar$C(ca[i]);
if (f.contains$java_util_Formatter_Flags(v)) throw Clazz.new_(Clazz.load('java.util.DuplicateFormatFlagsException').c$$S,[v.toString()]);
f.add$java_util_Formatter_Flags(v);
}
return f;
}, 1);

Clazz.newMeth(C$, 'parseChar$C', function (c) {
switch (c.$c()) {
case 45:
return C$.LEFT_JUSTIFY;
case 35:
return C$.ALTERNATE;
case 43:
return C$.PLUS;
case 32:
return C$.LEADING_SPACE;
case 48:
return C$.ZERO_PAD;
case 44:
return C$.GROUP;
case 40:
return C$.PARENTHESES;
case 60:
return C$.PREVIOUS;
default:
throw Clazz.new_(Clazz.load('java.util.UnknownFormatFlagsException').c$$S,[String.valueOf(c)]);
}
}, 1);

Clazz.newMeth(C$, 'toString', function () {
var sb = Clazz.new_((I$[6]||$incl$(6)));
if (this.contains$java_util_Formatter_Flags(C$.LEFT_JUSTIFY)) sb.append$C("-");
if (this.contains$java_util_Formatter_Flags(C$.UPPERCASE)) sb.append$C("^");
if (this.contains$java_util_Formatter_Flags(C$.ALTERNATE)) sb.append$C("#");
if (this.contains$java_util_Formatter_Flags(C$.PLUS)) sb.append$C("+");
if (this.contains$java_util_Formatter_Flags(C$.LEADING_SPACE)) sb.append$C(" ");
if (this.contains$java_util_Formatter_Flags(C$.ZERO_PAD)) sb.append$C("0");
if (this.contains$java_util_Formatter_Flags(C$.GROUP)) sb.append$C(",");
if (this.contains$java_util_Formatter_Flags(C$.PARENTHESES)) sb.append$C("(");
if (this.contains$java_util_Formatter_Flags(C$.PREVIOUS)) sb.append$C("<");
return sb.toString();
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Formatter, "Conversion", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isValid$C', function (c) {
return (C$.isGeneral$C(c) || C$.isInteger$C(c) || C$.isFloat$C(c) || C$.isText$C(c) || c == "t"   || C$.isCharacter$C(c) );
}, 1);

Clazz.newMeth(C$, 'isGeneral$C', function (c) {
switch (c.$c()) {
case "b".$c():
case "B".$c():
case "s".$c():
case "S".$c():
case "h".$c():
case "H".$c():
return true;
default:
return false;
}
}, 1);

Clazz.newMeth(C$, 'isCharacter$C', function (c) {
switch (c.$c()) {
case "c".$c():
case "C".$c():
return true;
default:
return false;
}
}, 1);

Clazz.newMeth(C$, 'isInteger$C', function (c) {
switch (c.$c()) {
case "d".$c():
case "o".$c():
case "x".$c():
case "X".$c():
return true;
default:
return false;
}
}, 1);

Clazz.newMeth(C$, 'isFloat$C', function (c) {
switch (c.$c()) {
case "e".$c():
case "E".$c():
case "g".$c():
case "G".$c():
case "f".$c():
case "a".$c():
case "A".$c():
return true;
default:
return false;
}
}, 1);

Clazz.newMeth(C$, 'isText$C', function (c) {
switch (c.$c()) {
case "n".$c():
case "%".$c():
return true;
default:
return false;
}
}, 1);

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Formatter, "DateTime", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isValid$C', function (c) {
switch (c.$c()) {
case "H".$c():
case "I".$c():
case "k".$c():
case "l".$c():
case "M".$c():
case "N".$c():
case "L".$c():
case "Q".$c():
case "p".$c():
case "s".$c():
case "S".$c():
case "T".$c():
case "z".$c():
case "Z".$c():
case "a".$c():
case "A".$c():
case "b".$c():
case "B".$c():
case "C".$c():
case "d".$c():
case "e".$c():
case "h".$c():
case "j".$c():
case "m".$c():
case "y".$c():
case "Y".$c():
case "r".$c():
case "R".$c():
case "c".$c():
case "D".$c():
case "F".$c():
return true;
default:
return false;
}
}, 1);

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.Formatter, "FormattedFloatingDecimal", function(){
Clazz.newInstance(this, arguments[0],true,C$);
});
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.str = null;
this.exp = 0;
this.expr = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$D$I$I', function (value, prec, type) {
C$.$init$.apply(this);
this.str = "" + new Double(value).toString();
p$.getString$I$D$I.apply(this, [type, value, prec]);
}, 1);

Clazz.newMeth(C$, 'getString$I$D$I', function (type, value, prec) {
if (Double.isNaN(value) || Double.isInfinite(value) ) return;
var sNoRound = "";
var sRound = "";
{
sNoRound = value.toExponential(13);
sRound = value.toExponential(prec);
}
this.exp = p$.getExp$S.apply(this, [sNoRound]);
this.expr = p$.getExp$S.apply(this, [sRound]);
if (type == 0) {
{
sRound = value.toExponential(prec-1);
}
if (this.expr < -4 || this.exp >= prec ) type = 2;
}if (type == 1) {
{
sRound = value.toFixed(); sNoRound = value.toFixed(prec);
}
}if (sNoRound.length$() < sRound.length$()) sRound = sNoRound;
var pt = sRound.indexOf("e");
switch (type) {
case 2:
if (pt >= 0 && sRound.indexOf("e-") < 0  && sRound.indexOf("e+") < 0 ) sRound = sRound.substring(0, pt + 1) + "+" + sRound.substring(pt + 1) ;
if (pt == sRound.length$() - 3) sRound = sRound.substring(0, pt + 2) + "0" + sRound.substring(pt + 2) ;
break;
case 1:
sRound = sNoRound;
break;
case 0:
var ndig = pt - (value < 0  ? 2 : 1);
ndig = Math.max(0, ndig - 1 - this.exp );
{
sRound = parseFloat(sRound).toFixed(ndig);
}
}
this.str = sRound;
});

Clazz.newMeth(C$, 'getExp$S', function (s) {
return Integer.parseInt(s.substring(s.indexOf("e") + 1));
});

Clazz.newMeth(C$, 'getChars$CA', function (v) {
var len = this.str.length$();
System.arraycopy(this.str.toCharArray(), 0, v, 0, len);
return len;
});

Clazz.newMeth(C$, 'getExponent', function () {
return this.exp;
});

Clazz.newMeth(C$, 'getExponentRounded', function () {
return this.expr;
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-08 10:02:12
