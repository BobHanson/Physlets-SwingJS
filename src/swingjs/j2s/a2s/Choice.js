(function(){var P$=Clazz.newPackage("a2s"),I$=[];
var C$=Clazz.newClass(P$, "Choice", null, 'javax.swing.JComboBox');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'select$I', function (index) {
this.setSelectedIndex$I(index);
});

Clazz.newMeth(C$, 'select$S', function (key) {
this.setSelectedItem$O(key);
});

Clazz.newMeth(C$, 'add$S', function (label) {
this.addItem$O(label);
});

Clazz.newMeth(C$, 'getItem$I', function (n) {
return this.getItemAt$I(n);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:44:59
