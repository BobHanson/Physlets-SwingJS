(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[['java.lang.StringBuffer']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PDFStringHelper");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'makePDFString$S', function (s) {
if (s.indexOf("(") > -1) s=C$.replace$S$S$S(s, "(", "\\(");
if (s.indexOf(")") > -1) s=C$.replace$S$S$S(s, ")", "\\)");
return "(" + s + ")" ;
}, 1);

Clazz.newMeth(C$, 'replace$S$S$S', function (source, removeThis, replaceWith) {
var b = Clazz.new_((I$[1]||$incl$(1)));
var p = 0;
var c = 0;
while (c > -1){
if ((c=source.indexOf(removeThis, p)) > -1) {
b.append$S(source.substring(p, c));
b.append$S(replaceWith);
p=c + 1;
}}
if (p < source.length$()) b.append$S(source.substring(p));
return b.toString();
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:03
