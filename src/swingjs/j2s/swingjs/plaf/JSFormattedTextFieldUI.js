(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSFormattedTextFieldUI", null, 'swingjs.plaf.JSTextFieldUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'setProp$swingjs_api_js_DOMNode$S$S', function (obj, prop, val) {
if (prop == "value" && val.length$() >= 2 ) {
if ((val.charCodeAt(0)) == 164 ) val = "$" + val.substring(1);
 else if (val.charAt(0) == "-" && (val.charCodeAt(1)) == 164  ) val = "($" + val.substring(2) + ")" ;
}return (I$[1]||$incl$(1)).setAttr(obj, prop, val);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:22
