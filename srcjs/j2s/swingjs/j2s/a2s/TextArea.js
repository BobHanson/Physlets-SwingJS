(function(){var P$=Clazz.newPackage("a2s"),I$=[['javax.swing.JTextArea']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "TextArea", null, 'javax.swing.JScrollPane');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.ta = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$I', function (rows, cols) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.setViewportView$java_awt_Component(this.ta = Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[rows, cols]));
this.awtDefaults();
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.setViewportView$java_awt_Component(this.ta = Clazz.new_((I$[1]||$incl$(1))));
this.awtDefaults();
}, 1);

Clazz.newMeth(C$, 'c$$S', function (text) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.setViewportView$java_awt_Component(this.ta = Clazz.new_((I$[1]||$incl$(1)).c$$S$I$I,[text, 0, 9]));
this.awtDefaults();
}, 1);

Clazz.newMeth(C$, 'c$$S$I$I', function (text, rows, cols) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.setViewportView$java_awt_Component(this.ta = Clazz.new_((I$[1]||$incl$(1)).c$$S$I$I,[text, rows, cols]));
this.awtDefaults();
}, 1);

Clazz.newMeth(C$, 'awtDefaults', function () {
});

Clazz.newMeth(C$, 'getText', function () {
return this.ta.getText();
});

Clazz.newMeth(C$, 'setEditable$Z', function (b) {
this.ta.setEditable$Z(b);
});

Clazz.newMeth(C$, 'selectAll', function () {
this.ta.selectAll();
});

Clazz.newMeth(C$, 'setText$S', function (t) {
this.ta.setText$S(t);
});

Clazz.newMeth(C$, 'insertText$S$I', function (str, pos) {
this.ta.insert$S$I(str, pos);
});

Clazz.newMeth(C$, 'insert$S$I', function (str, pos) {
this.ta.insert$S$I(str, pos);
});

Clazz.newMeth(C$, 'appendText$S', function (str) {
this.ta.append$S(str);
});

Clazz.newMeth(C$, 'append$S', function (str) {
this.ta.append$S(str);
});

Clazz.newMeth(C$, 'replaceRange$S$I$I', function (str, start, end) {
this.ta.replaceRange$S$I$I(str, start, end);
});

Clazz.newMeth(C$, 'replaceText$S$I$I', function (str, start, end) {
this.ta.replaceRange$S$I$I(str, start, end);
});

Clazz.newMeth(C$, 'setColumns$I', function (columns) {
this.ta.setColumns$I(columns);
});

Clazz.newMeth(C$, 'setRows$I', function (rows) {
this.ta.setRows$I(rows);
});

Clazz.newMeth(C$, 'getColumns', function () {
return this.ta.getColumns();
});

Clazz.newMeth(C$, 'getRows', function () {
return this.ta.getRows();
});
})();
//Created 2018-02-08 10:01:41
