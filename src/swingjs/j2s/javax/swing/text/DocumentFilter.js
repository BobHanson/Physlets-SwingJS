(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[];
var C$=Clazz.newClass(P$, "DocumentFilter", function(){
Clazz.newInstance(this, arguments,0,C$);
});

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'remove$javax_swing_text_DocumentFilter_FilterBypass$I$I', function (fb, offset, length) {
fb.remove$I$I(offset, length);
});

Clazz.newMeth(C$, 'insertString$javax_swing_text_DocumentFilter_FilterBypass$I$S$javax_swing_text_AttributeSet', function (fb, offset, string, attr) {
fb.insertString$I$S$javax_swing_text_AttributeSet(offset, string, attr);
});

Clazz.newMeth(C$, 'replace$javax_swing_text_DocumentFilter_FilterBypass$I$I$S$javax_swing_text_AttributeSet', function (fb, offset, length, text, attrs) {
fb.replace$I$I$S$javax_swing_text_AttributeSet(offset, length, text, attrs);
});
;
(function(){var C$=Clazz.newClass(P$.DocumentFilter, "FilterBypass", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:04
