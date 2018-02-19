(function(){var P$=Clazz.newPackage("org.apache.harmony.luni.util"),I$=[['java.lang.StringBuilder']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "MsgHelp");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'format$S$OA', function (format, args) {
var answer = Clazz.new_((I$[1]||$incl$(1)).c$$I,[format.length$() + (args.length * 20)]);
var argStrings = Clazz.array(java.lang.String, [args.length]);
for (var i = 0; i < args.length; ++i) {
if (args[i] == null ) argStrings[i] = "<null>";
 else argStrings[i] = args[i].toString();
}
var lastI = 0;
for (var i = format.indexOf("{", 0); i >= 0; i = format.indexOf("{", lastI)) {
if (i != 0 && format.charAt(i - 1) == "\\" ) {
if (i != 1) answer.append$S(format.substring(lastI, i - 1));
answer.append$C("{");
lastI = i + 1;
} else {
if (i > format.length$() - 3) {
answer.append$S(format.substring(lastI, format.length$()));
lastI = format.length$();
} else {
var argnum = ($b$[0] = ((format.charCodeAt(i + 1)) - 48), $b$[0]);
if (argnum < 0 || format.charAt(i + 2) != "}" ) {
answer.append$S(format.substring(lastI, i + 1));
lastI = i + 1;
} else {
answer.append$S(format.substring(lastI, i));
if (argnum >= argStrings.length) answer.append$S("<missing argument>");
 else answer.append$S(argStrings[argnum]);
lastI = i + 3;
}}}}
if (lastI < format.length$()) answer.append$S(format.substring(lastI, format.length$()));
return answer.toString();
}, 1);
var $b$ = new Int8Array(1);

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:03
