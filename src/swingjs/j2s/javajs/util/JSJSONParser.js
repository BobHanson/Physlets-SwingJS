(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['java.lang.Boolean','javajs.util.SB','java.util.Hashtable','java.util.HashMap','javajs.util.Lst']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSJSONParser");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.str = null;
this.index = 0;
this.len = 0;
this.asHashTable = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'parseMap$S$Z', function (str, asHashTable) {
this.index = 0;
this.asHashTable = asHashTable;
this.str = str;
this.len = str.length$();
if (p$.getChar.apply(this, []) != "{") return null;
p$.returnChar.apply(this, []);
return p$.getValue$Z.apply(this, [false]);
});

Clazz.newMeth(C$, 'parse$S$Z', function (str, asHashTable) {
this.index = 0;
this.asHashTable = asHashTable;
this.str = str;
this.len = str.length$();
return p$.getValue$Z.apply(this, [false]);
});

Clazz.newMeth(C$, 'next', function () {
return (this.index < this.len ? this.str.charAt(this.index++) : "\u0000");
});

Clazz.newMeth(C$, 'returnChar', function () {
this.index--;
});

Clazz.newMeth(C$, 'getChar', function () {
for (; ; ) {
var c = p$.next.apply(this, []);
if (c.$c() == 0  || c > " " ) {
return c;
}}
});

Clazz.newMeth(C$, 'getValue$Z', function (isKey) {
var i = this.index;
var c = p$.getChar.apply(this, []);
switch (c.$c()) {
case 0:
return null;
case 34:
case 39:
return p$.getString$C.apply(this, [c]);
case 123:
if (!isKey) return p$.getObject.apply(this, []);
c = String.fromCharCode(0);
break;
case 91:
if (!isKey) return p$.getArray.apply(this, []);
c = String.fromCharCode(0);
break;
default:
p$.returnChar.apply(this, []);
while (c >= " " && "[,]{:}'\"".indexOf(c) < 0 )c = p$.next.apply(this, []);

p$.returnChar.apply(this, []);
if (isKey && c != ":" ) c = String.fromCharCode(0);
break;
}
if (isKey && c.$c() == 0  ) throw Clazz.new_(Clazz.load('javajs.util.JSONException').c$$S,["invalid key"]);
var string = this.str.substring(i, this.index).trim();
if (!isKey) {
if (string.equals$O("true")) {
return (I$[1]||$incl$(1)).TRUE;
}if (string.equals$O("false")) {
return (I$[1]||$incl$(1)).FALSE;
}if (string.equals$O("null")) {
return (this.asHashTable ? string : null);
}}c = string.charAt(0);
if (c >= "0" && c <= "9"  || c == "-" ) try {
if (string.indexOf(".") < 0 && string.indexOf("e") < 0  && string.indexOf("E") < 0 ) return  new Integer(string);
var d = Float.$valueOf(string);
if (!d.isInfinite() && !d.isNaN() ) return d;
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
} else {
throw e;
}
}
System.out.println$S("JSON parser cannot parse " + string);
throw Clazz.new_(Clazz.load('javajs.util.JSONException').c$$S,["invalid value"]);
});

Clazz.newMeth(C$, 'getString$C', function (quote) {
var c;
var sb = null;
var i0 = this.index;
for (; ; ) {
var i1 = this.index;
switch ((c = p$.next.apply(this, [])).$c()) {
case 0:
case 10:
case 13:
throw this.syntaxError$S("Unterminated string");
case 92:
switch ((c = p$.next.apply(this, [])).$c()) {
case 34:
case 39:
case 92:
case 47:
break;
case 98:
c = "\u0008";
break;
case 116:
c = "\u0009";
break;
case 110:
c = "\u000a";
break;
case 102:
c = "\u000c";
break;
case 114:
c = "\u000d";
break;
case 117:
var i = this.index;
this.index = this.index+(4);
try {
c = String.fromCharCode(Integer.parseInt(this.str.substring(i, this.index), 16));
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
throw this.syntaxError$S("Substring bounds error");
} else {
throw e;
}
}
break;
default:
throw this.syntaxError$S("Illegal escape.");
}
break;
default:
if (c == quote) return (sb == null  ? this.str.substring(i0, i1) : sb.toString());
break;
}
if (this.index > i1 + 1) {
if (sb == null ) {
sb = Clazz.new_((I$[2]||$incl$(2)));
sb.append$S(this.str.substring(i0, i1));
}}if (sb != null ) sb.appendC$C(c);
}
});

Clazz.newMeth(C$, 'getObject', function () {
var map = (this.asHashTable ? Clazz.new_((I$[3]||$incl$(3))) : Clazz.new_((I$[4]||$incl$(4))));
var key = null;
switch ((p$.getChar.apply(this, [])).$c()) {
case 125:
return map;
case 0:
throw Clazz.new_(Clazz.load('javajs.util.JSONException').c$$S,["invalid object"]);
}
p$.returnChar.apply(this, []);
var isKey = false;
for (; ; ) {
if ((isKey = !isKey) == true ) key = p$.getValue$Z.apply(this, [true]).toString();
 else map.put$TK$TV(key, p$.getValue$Z.apply(this, [false]));
switch ((p$.getChar.apply(this, [])).$c()) {
case 125:
return map;
case 58:
if (isKey) continue;
isKey = true;
case 44:
if (!isKey) continue;
default:
throw this.syntaxError$S("Expected \',\' or \':\' or \'}\'");
}
}
});

Clazz.newMeth(C$, 'getArray', function () {
var l = Clazz.new_((I$[5]||$incl$(5)));
switch ((p$.getChar.apply(this, [])).$c()) {
case 93:
return l;
case 0:
throw Clazz.new_(Clazz.load('javajs.util.JSONException').c$$S,["invalid array"]);
}
p$.returnChar.apply(this, []);
var isNull = false;
for (; ; ) {
if (isNull) {
l.addLast$TV(null);
isNull = false;
} else {
l.addLast$TV(p$.getValue$Z.apply(this, [false]));
}switch ((p$.getChar.apply(this, [])).$c()) {
case 44:
switch ((p$.getChar.apply(this, [])).$c()) {
case 93:
return l;
case 44:
isNull = true;
default:
p$.returnChar.apply(this, []);
}
continue;
case 93:
return l;
default:
throw this.syntaxError$S("Expected \',\' or \']\'");
}
}
});

Clazz.newMeth(C$, 'syntaxError$S', function (message) {
return Clazz.new_(Clazz.load('javajs.util.JSONException').c$$S,[message + " for " + this.str.substring(0, Math.min(this.index, this.len)) ]);
});
})();
//Created 2018-05-15 01:02:18
