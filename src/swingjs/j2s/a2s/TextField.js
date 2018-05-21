(function(){var P$=Clazz.newPackage("a2s"),I$=[['java.awt.event.TextEvent','a2s.TextField$1']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "TextField", null, 'javax.swing.JTextField');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (width) {
C$.superclazz.c$$I.apply(this, [width]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (text) {
C$.superclazz.c$$S.apply(this, [text]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$I', function (text, width) {
C$.superclazz.c$$S$I.apply(this, [text, width]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'addTextListener$java_awt_event_TextListener', function (textListener) {
this.getDocument().addDocumentListener$javax_swing_event_DocumentListener(((
(function(){var C$=Clazz.newClass(P$, "TextField$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'javax.swing.event.DocumentListener', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'insertUpdate$javax_swing_event_DocumentEvent', function (e) {
this.$finals.textListener.textValueChanged$java_awt_event_TextEvent(Clazz.new_((I$[1]||$incl$(1)).c$$O$I,[this, 0]));
});

Clazz.newMeth(C$, 'removeUpdate$javax_swing_event_DocumentEvent', function (e) {
this.$finals.textListener.textValueChanged$java_awt_event_TextEvent(Clazz.new_((I$[1]||$incl$(1)).c$$O$I,[this, 0]));
});

Clazz.newMeth(C$, 'changedUpdate$javax_swing_event_DocumentEvent', function (e) {
this.$finals.textListener.textValueChanged$java_awt_event_TextEvent(Clazz.new_((I$[1]||$incl$(1)).c$$O$I,[this, 0]));
});
})()
), Clazz.new_((I$[2]||$incl$(2)).$init$, [this, {textListener: textListener}])));
});
})();
//Created 2018-05-15 01:01:45
