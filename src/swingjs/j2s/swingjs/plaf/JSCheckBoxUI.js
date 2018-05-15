(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[];
var C$=Clazz.newClass(P$, "JSCheckBoxUI", null, 'swingjs.plaf.JSRadioButtonUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
return this.updateButton$S("checkBox");
});

Clazz.newMeth(C$, 'getPropertyPrefix', function () {
return "CheckBox.";
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:20
