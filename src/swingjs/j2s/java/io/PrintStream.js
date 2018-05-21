(function(){var P$=java.io,I$=[['java.io.BufferedWriter','java.io.OutputStreamWriter','java.io.FileOutputStream','Thread','java.util.Locale','java.util.Formatter']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PrintStream", null, 'java.io.FilterOutputStream', ['Appendable', 'java.io.Closeable']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.autoFlush = false;
this.trouble = false;
this.formatter = null;
this.bufferedWriter = null;
this.streamWriter = null;
this.closing = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.autoFlush = false;
this.trouble = false;
this.closing = false;
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream', function (out) {
C$.c$$java_io_OutputStream$Z.apply(this, [out, false]);
}, 1);

Clazz.newMeth(C$, 'c$$Z$java_io_OutputStream', function (autoFlush, out) {
C$.superclazz.c$$java_io_OutputStream.apply(this, [out]);
C$.$init$.apply(this);
if (out == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["Null output stream"]);
this.autoFlush = autoFlush;
}, 1);

Clazz.newMeth(C$, 'init$java_io_OutputStreamWriter', function (osw) {
this.streamWriter = osw;
this.bufferedWriter = Clazz.new_((I$[1]||$incl$(1)).c$$java_io_Writer,[osw]);
});

Clazz.newMeth(C$, 'c$$java_io_OutputStream$Z', function (out, autoFlush) {
C$.c$$Z$java_io_OutputStream.apply(this, [autoFlush, out]);
p$.init$java_io_OutputStreamWriter.apply(this, [Clazz.new_((I$[2]||$incl$(2)).c$$java_io_OutputStream,[this])]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream$Z$S', function (out, autoFlush, encoding) {
C$.c$$Z$java_io_OutputStream.apply(this, [autoFlush, out]);
p$.init$java_io_OutputStreamWriter.apply(this, [Clazz.new_((I$[2]||$incl$(2)).c$$java_io_OutputStream$S,[this, encoding])]);
}, 1);

Clazz.newMeth(C$, 'c$$S$S', function (fileName, csn) {
C$.c$$Z$java_io_OutputStream.apply(this, [false, Clazz.new_((I$[3]||$incl$(3)).c$$S,[fileName])]);
p$.init$java_io_OutputStreamWriter.apply(this, [Clazz.new_((I$[2]||$incl$(2)).c$$java_io_OutputStream$S,[this, csn])]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_File', function (file) {
C$.c$$Z$java_io_OutputStream.apply(this, [false, Clazz.new_((I$[3]||$incl$(3)).c$$java_io_File,[file])]);
p$.init$java_io_OutputStreamWriter.apply(this, [Clazz.new_((I$[2]||$incl$(2)).c$$java_io_OutputStream,[this])]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_File$S', function (file, csn) {
C$.c$$Z$java_io_OutputStream.apply(this, [false, Clazz.new_((I$[3]||$incl$(3)).c$$java_io_File,[file])]);
p$.init$java_io_OutputStreamWriter.apply(this, [Clazz.new_((I$[2]||$incl$(2)).c$$java_io_OutputStream$S,[this, csn])]);
}, 1);

Clazz.newMeth(C$, 'ensureOpen', function () {
if (this.out == null ) throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,["Stream closed"]);
});

Clazz.newMeth(C$, 'flush', function () {
{
try {
p$.ensureOpen.apply(this, []);
this.out.flush();
} catch (x) {
if (Clazz.exceptionOf(x, "java.io.IOException")){
this.trouble = true;
} else {
throw x;
}
}
}});

Clazz.newMeth(C$, 'close', function () {
{
if (!this.closing) {
this.closing = true;
try {
this.bufferedWriter.close();
this.out.close();
} catch (x) {
if (Clazz.exceptionOf(x, "java.io.IOException")){
this.trouble = true;
} else {
throw x;
}
}
this.bufferedWriter = null;
this.streamWriter = null;
this.out = null;
}}});

Clazz.newMeth(C$, 'checkError', function () {
if (this.out != null ) this.flush();
if (Clazz.instanceOf(this.out, "java.io.PrintStream")) {
var ps = this.out;
return ps.checkError();
}return this.trouble;
});

Clazz.newMeth(C$, 'setError', function () {
this.trouble = true;
});

Clazz.newMeth(C$, 'clearError', function () {
this.trouble = false;
});

Clazz.newMeth(C$, 'write$I', function (b) {
try {
{
p$.ensureOpen.apply(this, []);
this.out.write$I(b);
if ((b == 10 ) && this.autoFlush ) this.out.flush();
}} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.InterruptedIOException")){
var x = e$$;
{
(I$[4]||$incl$(4)).currentThread().interrupt();
}
} else if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var x = e$$;
{
this.trouble = true;
}
} else {
throw e$$;
}
}
});

Clazz.newMeth(C$, 'write$BA$I$I', function (buf, off, len) {
try {
{
p$.ensureOpen.apply(this, []);
this.out.write$BA$I$I(buf, off, len);
if (this.autoFlush) this.out.flush();
}} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.InterruptedIOException")){
var x = e$$;
{
(I$[4]||$incl$(4)).currentThread().interrupt();
}
} else if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var x = e$$;
{
this.trouble = true;
}
} else {
throw e$$;
}
}
});

Clazz.newMeth(C$, 'write$CA', function (buf) {
try {
{
p$.ensureOpen.apply(this, []);
this.bufferedWriter.write$CA(buf);
this.bufferedWriter.flushBuffer();
this.streamWriter.flushBuffer();
if (this.autoFlush) {
for (var i = 0; i < buf.length; i++) if (buf[i] == "\u000a") {
this.out.flush();
}
}}} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.InterruptedIOException")){
var x = e$$;
{
(I$[4]||$incl$(4)).currentThread().interrupt();
}
} else if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var x = e$$;
{
this.trouble = true;
}
} else {
throw e$$;
}
}
});

Clazz.newMeth(C$, 'write$S', function (s) {
try {
{
p$.ensureOpen.apply(this, []);
this.bufferedWriter.write$S(s);
this.bufferedWriter.flushBuffer();
this.streamWriter.flushBuffer();
if (this.autoFlush && (s.indexOf("\u000a") >= 0) ) this.out.flush();
}} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.InterruptedIOException")){
var x = e$$;
{
(I$[4]||$incl$(4)).currentThread().interrupt();
}
} else if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var x = e$$;
{
this.trouble = true;
}
} else {
throw e$$;
}
}
});

Clazz.newMeth(C$, 'newLine', function () {
try {
{
p$.ensureOpen.apply(this, []);
this.bufferedWriter.newLine();
this.bufferedWriter.flushBuffer();
this.streamWriter.flushBuffer();
if (this.autoFlush) this.out.flush();
}} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.InterruptedIOException")){
var x = e$$;
{
(I$[4]||$incl$(4)).currentThread().interrupt();
}
} else if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var x = e$$;
{
this.trouble = true;
}
} else {
throw e$$;
}
}
});

Clazz.newMeth(C$, 'print$Z', function (b) {
p$.write$S.apply(this, [b ? "true" : "false"]);
});

Clazz.newMeth(C$, 'print$C', function (c) {
p$.write$S.apply(this, [String.valueOf(c)]);
});

Clazz.newMeth(C$, 'print$I', function (i) {
p$.write$S.apply(this, [String.valueOf(i)]);
});

Clazz.newMeth(C$, 'print$J', function (l) {
p$.write$S.apply(this, [String.valueOf(l)]);
});

Clazz.newMeth(C$, 'print$F', function (f) {
p$.write$S.apply(this, [String.valueOf(f)]);
});

Clazz.newMeth(C$, 'print$D', function (d) {
p$.write$S.apply(this, [String.valueOf(d)]);
});

Clazz.newMeth(C$, 'print$CA', function (s) {
p$.write$CA.apply(this, [s]);
});

Clazz.newMeth(C$, 'print$S', function (s) {
if (s == null ) {
s = "null";
}p$.write$S.apply(this, [s]);
});

Clazz.newMeth(C$, 'print$O', function (obj) {
p$.write$S.apply(this, [String.valueOf(obj)]);
});

Clazz.newMeth(C$, 'println', function () {
p$.newLine.apply(this, []);
});

Clazz.newMeth(C$, 'println$Z', function (x) {
{
this.print$Z(x);
p$.newLine.apply(this, []);
}});

Clazz.newMeth(C$, 'println$C', function (x) {
{
this.print$C(x);
p$.newLine.apply(this, []);
}});

Clazz.newMeth(C$, 'println$I', function (x) {
{
this.print$I(x);
p$.newLine.apply(this, []);
}});

Clazz.newMeth(C$, 'println$J', function (x) {
{
this.print$J(x);
p$.newLine.apply(this, []);
}});

Clazz.newMeth(C$, 'println$F', function (x) {
{
this.print$F(x);
p$.newLine.apply(this, []);
}});

Clazz.newMeth(C$, 'println$D', function (x) {
{
this.print$D(x);
p$.newLine.apply(this, []);
}});

Clazz.newMeth(C$, 'println$CA', function (x) {
{
this.print$CA(x);
p$.newLine.apply(this, []);
}});

Clazz.newMeth(C$, 'println$S', function (x) {
{
this.print$S(x);
p$.newLine.apply(this, []);
}});

Clazz.newMeth(C$, 'println$O', function (x) {
var s = String.valueOf(x);
{
this.print$S(s);
p$.newLine.apply(this, []);
}});

Clazz.newMeth(C$, 'printf$S$OA', function (format, args) {
return this.format$S$OA(format, args);
});

Clazz.newMeth(C$, 'printf$java_util_Locale$S$OA', function (l, format, args) {
return this.format$java_util_Locale$S$OA(l, format, args);
});

Clazz.newMeth(C$, 'format$S$OA', function (format, args) {
try {
{
p$.ensureOpen.apply(this, []);
if ((this.formatter == null ) || (this.formatter.locale() !== (I$[5]||$incl$(5)).getDefault() ) ) this.formatter = Clazz.new_((I$[6]||$incl$(6)).c$$Appendable,[this]);
this.formatter.format$java_util_Locale$S$OA((I$[5]||$incl$(5)).getDefault(), format, args);
}} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.InterruptedIOException")){
var x = e$$;
{
(I$[4]||$incl$(4)).currentThread().interrupt();
}
} else if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var x = e$$;
{
this.trouble = true;
}
} else {
throw e$$;
}
}
return this;
});

Clazz.newMeth(C$, 'format$java_util_Locale$S$OA', function (l, format, args) {
try {
{
p$.ensureOpen.apply(this, []);
if ((this.formatter == null ) || (this.formatter.locale() !== l ) ) this.formatter = Clazz.new_((I$[6]||$incl$(6)).c$$Appendable$java_util_Locale,[this, l]);
this.formatter.format$java_util_Locale$S$OA(l, format, args);
}} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.InterruptedIOException")){
var x = e$$;
{
(I$[4]||$incl$(4)).currentThread().interrupt();
}
} else if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var x = e$$;
{
this.trouble = true;
}
} else {
throw e$$;
}
}
return this;
});

Clazz.newMeth(C$, 'append$CharSequence', function (csq) {
if (csq == null ) this.print$S("null");
 else this.print$S(csq.toString());
return this;
});

Clazz.newMeth(C$, 'append$CharSequence$I$I', function (csq, start, end) {
var cs = (csq == null  ? "null" : csq);
p$.write$S.apply(this, [cs.subSequence$I$I(start, end).toString()]);
return this;
});

Clazz.newMeth(C$, 'append$C', function (c) {
this.print$C(c);
return this;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:05
