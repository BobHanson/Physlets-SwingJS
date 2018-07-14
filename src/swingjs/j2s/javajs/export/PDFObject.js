(function(){var P$=Clazz.newPackage("javajs.export"),I$=[['java.util.Hashtable','java.util.zip.Deflater','java.io.ByteArrayOutputStream','java.util.zip.DeflaterOutputStream','javajs.util.SB']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PDFObject", null, 'javajs.util.SB');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.dictionary = null;
this.stream = null;
this.index = 0;
this.type = null;
this.len = 0;
this.pt = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (index) {
Clazz.super_(C$, this,1);
this.index=index;
}, 1);

Clazz.newMeth(C$, 'getRef', function () {
return this.index + " 0 R";
});

Clazz.newMeth(C$, 'getID', function () {
return this.type.substring(0, 1) + this.index;
});

Clazz.newMeth(C$, 'isFont', function () {
return "Font".equals$O(this.type);
});

Clazz.newMeth(C$, 'setStream$BA', function (stream) {
this.stream=stream;
});

Clazz.newMeth(C$, 'getDef$S', function (key) {
return this.dictionary.get$O(key);
});

Clazz.newMeth(C$, 'addDef$S$O', function (key, value) {
if (this.dictionary == null ) this.dictionary=Clazz.new_((I$[1]||$incl$(1)));
this.dictionary.put$TK$TV(key, value);
if (key.equals$O("Type")) this.type=(value).substring(1);
});

Clazz.newMeth(C$, 'setAsStream', function () {
this.stream=this.toBytes$I$I(0, -1);
this.setLength$I(0);
});

Clazz.newMeth(C$, 'output$java_io_OutputStream', function (os) {
if (this.index > 0) {
var s = this.index + " 0 obj\n";
p$.write$java_io_OutputStream$BA$I.apply(this, [os, s.getBytes(), 0]);
}var streamLen = 0;
if (this.dictionary != null ) {
if (this.dictionary.containsKey$O("Length")) {
if (this.stream == null ) this.setAsStream();
streamLen=this.stream.length;
var doDeflate = (streamLen > 1000);
if (doDeflate) {
var deflater = Clazz.new_((I$[2]||$incl$(2)).c$$I,[9]);
var outBytes = Clazz.new_((I$[3]||$incl$(3)).c$$I,[1024]);
var compBytes = Clazz.new_((I$[4]||$incl$(4)).c$$java_io_ByteArrayOutputStream$java_util_zip_Deflater,[outBytes, deflater]);
compBytes.write$BA$I$I(this.stream, 0, streamLen);
compBytes.finish();
this.stream=outBytes.toByteArray();
this.dictionary.put$TK$TV("Filter", "/FlateDecode");
streamLen=this.stream.length;
}this.dictionary.put$TK$TV("Length", "" + streamLen);
}p$.write$java_io_OutputStream$BA$I.apply(this, [os, p$.getDictionaryText$java_util_Map$S.apply(this, [this.dictionary, "\u000a"]).getBytes(), 0]);
}if (this.length$() > 0) p$.write$java_io_OutputStream$BA$I.apply(this, [os, this.toString().getBytes(), 0]);
if (this.stream != null ) {
p$.write$java_io_OutputStream$BA$I.apply(this, [os, "stream\r\n".getBytes(), 0]);
p$.write$java_io_OutputStream$BA$I.apply(this, [os, this.stream, streamLen]);
p$.write$java_io_OutputStream$BA$I.apply(this, [os, "\r\nendstream\r\n".getBytes(), 0]);
}if (this.index > 0) p$.write$java_io_OutputStream$BA$I.apply(this, [os, "endobj\n".getBytes(), 0]);
return this.len;
});

Clazz.newMeth(C$, 'write$java_io_OutputStream$BA$I', function (os, bytes, nBytes) {
if (nBytes == 0) nBytes=bytes.length;
this.len+=nBytes;
os.write$BA$I$I(bytes, 0, nBytes);
});

Clazz.newMeth(C$, 'getDictionaryText$java_util_Map$S', function (d, nl) {
var sb = Clazz.new_((I$[5]||$incl$(5)));
sb.append$S("<<");
if (d.containsKey$O("Type")) sb.append$S("/Type").appendO$O(d.get$O("Type"));
for (var e, $e = d.entrySet().iterator(); $e.hasNext()&&((e=$e.next()),1);) {
var s = e.getKey();
if (s.equals$O("Type") || s.startsWith$S("!") ) continue;
sb.append$S("/" + s);
var o = e.getValue();
if (Clazz.instanceOf(o, "java.util.Map")) {
sb.append$S((p$.getDictionaryText$java_util_Map$S.apply(this, [o, ""])));
continue;
}s=e.getValue();
if (!s.startsWith$S("/")) sb.append$S(" ");
sb.appendO$O(s);
}
return (sb.length$() > 3 ? sb.append$S(">>").append$S(nl).toString() : "");
});

Clazz.newMeth(C$, 'createSubdict$java_util_Map$S', function (d0, dict) {
var d = d0.get$O(dict);
if (d == null ) d0.put$TK$TV(dict, d=Clazz.new_((I$[1]||$incl$(1))));
return d;
});

Clazz.newMeth(C$, 'addResource$S$S$S', function (type, key, value) {
var r = p$.createSubdict$java_util_Map$S.apply(this, [this.dictionary, "Resources"]);
if (type != null ) r=p$.createSubdict$java_util_Map$S.apply(this, [r, type]);
r.put$TK$TV(key, value);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:53