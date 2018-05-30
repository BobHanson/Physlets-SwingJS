(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['sun.awt.AppContext','javax.swing.UIManager']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "LayoutStyle");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'setInstance$javax_swing_LayoutStyle', function (style) {
{
if (style == null ) {
(I$[1]||$incl$(1)).getAppContext().remove$O(Clazz.getClass(C$));
} else {
(I$[1]||$incl$(1)).getAppContext().put$O$O(Clazz.getClass(C$), style);
}}}, 1);

Clazz.newMeth(C$, 'getInstance', function () {
var style;
{
style=(I$[1]||$incl$(1)).getAppContext().get$O(Clazz.getClass(C$));
}if (style == null ) {
return (I$[2]||$incl$(2)).getLookAndFeel().getLayoutStyle();
}return style;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);
;
(function(){var C$=Clazz.newClass(P$.LayoutStyle, "ComponentPlacement", null, 'Enum');

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "RELATED", 0, []);
Clazz.newEnumConst($vals, C$.c$, "UNRELATED", 1, []);
Clazz.newEnumConst($vals, C$.c$, "INDENT", 2, []);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})()
})();
//Created 2018-05-24 08:46:32
