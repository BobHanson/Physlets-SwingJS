(function(){var P$=Clazz.newPackage("sun.misc"),I$=[['java.lang.StringBuffer','java.lang.Throwable']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "MessageUtils");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'subst$S$S', function (patt, arg) {
var args = Clazz.array(java.lang.String, -1, [arg]);
return C$.subst$S$SA(patt, args);
}, 1);

Clazz.newMeth(C$, 'subst$S$S$S', function (patt, arg1, arg2) {
var args = Clazz.array(java.lang.String, -1, [arg1, arg2]);
return C$.subst$S$SA(patt, args);
}, 1);

Clazz.newMeth(C$, 'subst$S$S$S$S', function (patt, arg1, arg2, arg3) {
var args = Clazz.array(java.lang.String, -1, [arg1, arg2, arg3]);
return C$.subst$S$SA(patt, args);
}, 1);

Clazz.newMeth(C$, 'subst$S$SA', function (patt, args) {
var result = Clazz.new_((I$[1]||$incl$(1)));
var len = patt.length$();
for (var i = 0; i >= 0 && i < len ; i++) {
var ch = patt.charAt(i);
if (ch == "%") {
if (i != len) {
var index = Character.digit(patt.charAt(i + 1), 10);
if (index == -1) {
result.append$C(patt.charAt(i + 1));
i++;
} else if (index < args.length) {
result.append$S(args[index]);
i++;
}}} else {
result.append$C(ch);
}}
return result.toString();
}, 1);

Clazz.newMeth(C$, 'substProp$S$S', function (propName, arg) {
return C$.subst$S$S(System.getProperty(propName), arg);
}, 1);

Clazz.newMeth(C$, 'substProp$S$S$S', function (propName, arg1, arg2) {
return C$.subst$S$S$S(System.getProperty(propName), arg1, arg2);
}, 1);

Clazz.newMeth(C$, 'substProp$S$S$S$S', function (propName, arg1, arg2, arg3) {
return C$.subst$S$S$S$S(System.getProperty(propName), arg1, arg2, arg3);
}, 1);

Clazz.newMeth(C$, 'toStderr$S', function (msg) {
{
System.out.println(msg);
}
}, 1);

Clazz.newMeth(C$, 'toStdout$S', function (msg) {
{
System.out.println(msg);
}
}, 1);

Clazz.newMeth(C$, 'err$S', function (s) {
C$.toStderr$S(s + "\n");
}, 1);

Clazz.newMeth(C$, 'out$S', function (s) {
C$.toStdout$S(s + "\n");
}, 1);

Clazz.newMeth(C$, 'where', function () {
var t = Clazz.new_((I$[2]||$incl$(2)));
var es = t.getStackTrace();
for (var i = 1; i < es.length; i++) C$.toStderr$S("\t" + es[i].toString() + "\n" );

}, 1);
})();
//Created 2018-02-06 09:00:21
