(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['javax.swing.text.Segment']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JPasswordField", null, 'javax.swing.JTextField');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.echoChar = '\0';
this.echoCharSet = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.echoCharSet = false;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$javax_swing_text_Document$S$I.apply(this, [null, null, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (text) {
C$.c$$javax_swing_text_Document$S$I.apply(this, [null, text, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (columns) {
C$.c$$javax_swing_text_Document$S$I.apply(this, [null, null, columns]);
}, 1);

Clazz.newMeth(C$, 'c$$S$I', function (text, columns) {
C$.c$$javax_swing_text_Document$S$I.apply(this, [null, text, columns]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_text_Document$S$I', function (doc, txt, columns) {
C$.superclazz.c$$javax_swing_text_Document$S$I.apply(this, [doc, txt, columns]);
C$.$init$.apply(this);
this.uiClassID="PasswordFieldUI";
this.updateUI();
}, 1);

Clazz.newMeth(C$, 'updateUI', function () {
if (!this.echoCharSet) {
this.echoChar="*";
}C$.superclazz.prototype.updateUI.apply(this, []);
});

Clazz.newMeth(C$, 'getEchoChar', function () {
return this.echoChar;
});

Clazz.newMeth(C$, 'setEchoChar$C', function (c) {
this.echoChar=c;
this.echoCharSet=true;
this.repaint();
this.revalidate();
});

Clazz.newMeth(C$, 'echoCharIsSet', function () {
return this.echoChar.$c() != 0 ;
});

Clazz.newMeth(C$, 'cut', function () {
});

Clazz.newMeth(C$, 'copy', function () {
});

Clazz.newMeth(C$, 'getText', function () {
return C$.superclazz.prototype.getText.apply(this, []);
});

Clazz.newMeth(C$, 'getText$I$I', function (offs, len) {
return C$.superclazz.prototype.getText$I$I.apply(this, [offs, len]);
});

Clazz.newMeth(C$, 'getPassword', function () {
var doc = this.getDocument();
var txt = Clazz.new_((I$[1]||$incl$(1)));
try {
doc.getText$I$I$javax_swing_text_Segment(0, doc.getLength(), txt);
} catch (e) {
if (Clazz.exceptionOf(e, "javax.swing.text.BadLocationException")){
return null;
} else {
throw e;
}
}
var retValue = Clazz.array(Character.TYPE, [txt.count]);
System.arraycopy(txt.array, txt.offset, retValue, 0, txt.count);
return retValue;
});

Clazz.newMeth(C$, 'paramString', function () {
return C$.superclazz.prototype.paramString.apply(this, []) + ",echoChar=" + this.echoChar ;
});

Clazz.newMeth(C$, 'customSetUIProperty$S$O', function (propertyName, value) {
if (propertyName == "echoChar") {
if (!this.echoCharSet) {
this.setEchoChar$C((value).charValue());
this.echoCharSet=false;
}return true;
}return false;
});
})();
//Created 2018-05-24 08:46:20
