(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[];
var C$=Clazz.newClass(P$, "JSCaret", null, null, ['javax.swing.text.Caret', 'javax.swing.plaf.UIResource']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.c = null;
this.dot = 0;
this.mark = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'install$javax_swing_text_JTextComponent', function (c) {
this.c = c;
});

Clazz.newMeth(C$, 'deinstall$javax_swing_text_JTextComponent', function (c) {
this.c = null;
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
});

Clazz.newMeth(C$, 'addChangeListener$javax_swing_event_ChangeListener', function (l) {
});

Clazz.newMeth(C$, 'removeChangeListener$javax_swing_event_ChangeListener', function (l) {
});

Clazz.newMeth(C$, 'isVisible', function () {
return true;
});

Clazz.newMeth(C$, 'setVisible$Z', function (v) {
});

Clazz.newMeth(C$, 'isSelectionVisible', function () {
return true;
});

Clazz.newMeth(C$, 'setSelectionVisible$Z', function (v) {
});

Clazz.newMeth(C$, 'setMagicCaretPosition$java_awt_Point', function (p) {
});

Clazz.newMeth(C$, 'getMagicCaretPosition', function () {
return null;
});

Clazz.newMeth(C$, 'setBlinkRate$I', function (rate) {
});

Clazz.newMeth(C$, 'getBlinkRate', function () {
return 0;
});

Clazz.newMeth(C$, 'getDot', function () {
return this.dot;
});

Clazz.newMeth(C$, 'getMark', function () {
return this.mark;
});

Clazz.newMeth(C$, 'setDot$I', function (dot) {
this.dot = this.mark = dot;
});

Clazz.newMeth(C$, 'moveDot$I', function (dot) {
this.mark = this.dot;
this.dot = dot;
});

Clazz.newMeth(C$, 'toString', function () {
return "caret[" + this.dot + "," + this.mark + "]" ;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:21
