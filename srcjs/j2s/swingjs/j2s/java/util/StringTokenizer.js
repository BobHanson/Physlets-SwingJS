(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "StringTokenizer", null, null, 'java.util.Enumeration');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.string = null;
this.delimiters = null;
this.returnDelimiters = false;
this.position = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (string) {
C$.c$$S$S$Z.apply(this, [string, " \u0009\u000a\u000d\u000c", false]);
}, 1);

Clazz.newMeth(C$, 'c$$S$S', function (string, delimiters) {
C$.c$$S$S$Z.apply(this, [string, delimiters, false]);
}, 1);

Clazz.newMeth(C$, 'c$$S$S$Z', function (string, delimiters, returnDelimiters) {
C$.$init$.apply(this);
if (string != null ) {
this.string = string;
this.delimiters = delimiters;
this.returnDelimiters = returnDelimiters;
this.position = 0;
} else throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}, 1);

Clazz.newMeth(C$, 'countTokens', function () {
var count = 0;
var inToken = false;
for (var i = this.position, length = this.string.length$(); i < length; i++) {
if (this.delimiters.indexOf(this.string.charAt(i), 0) >= 0) {
if (this.returnDelimiters) count++;
if (inToken) {
count++;
inToken = false;
}} else {
inToken = true;
}}
if (inToken) count++;
return count;
});

Clazz.newMeth(C$, 'hasMoreElements', function () {
return this.hasMoreTokens();
});

Clazz.newMeth(C$, 'hasMoreTokens', function () {
var length = this.string.length$();
if (this.position < length) {
if (this.returnDelimiters) return true;
for (var i = this.position; i < length; i++) if (this.delimiters.indexOf(this.string.charAt(i), 0) == -1) return true;

}return false;
});

Clazz.newMeth(C$, 'nextElement', function () {
return this.nextToken();
});

Clazz.newMeth(C$, 'nextToken', function () {
var i = this.position;
var length = this.string.length$();
if (i < length) {
if (this.returnDelimiters) {
if (this.delimiters.indexOf(this.string.charAt(this.position), 0) >= 0) return String.valueOf(this.string.charAt(this.position++));
for (this.position++; this.position < length; this.position++) if (this.delimiters.indexOf(this.string.charAt(this.position), 0) >= 0) return this.string.substring(i, this.position);

return this.string.substring(i);
}while (i < length && this.delimiters.indexOf(this.string.charAt(i), 0) >= 0 )i++;

this.position = i;
if (i < length) {
for (this.position++; this.position < length; this.position++) if (this.delimiters.indexOf(this.string.charAt(this.position), 0) >= 0) return this.string.substring(i, this.position);

return this.string.substring(i);
}}throw Clazz.new_(Clazz.load('java.util.NoSuchElementException'));
});

Clazz.newMeth(C$, 'nextToken$S', function (delims) {
this.delimiters = delims;
return this.nextToken();
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:15
