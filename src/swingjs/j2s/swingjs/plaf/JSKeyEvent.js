(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['java.awt.AWTKeyStroke']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSKeyEvent", null, 'java.awt.event.KeyEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.jqEvent = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JComponent$O', function (source, jQueryEvent) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
var evType = null;
var keyCode = 0;
var which = 0;
var modifiers = 0;
var id = 0;
var when = System.currentTimeMillis();
var keyChar = String.fromCharCode(0);
var keyLocation = 0;
var key = "";
var ev = jQueryEvent;
{
key = ev.key;
evType = ev.type;
keyCode = ev.keyCode || ev.which;
if (keyCode == 13) keyCode = 10;
if (evType == "keypress") ev.originalEvent.preventDefault();
if (ev.shiftKey) modifiers |= (1<<0)|(1<<6); //InputEvent.SHIFT_MASK + InputEvent.SHIFT_DOWN_MASK;
if (ev.ctrlKey) modifiers |= (1<<1)|(1<<7); //InputEvent.CTRL_MASK + InputEvent.CTRL_DOWN_MASK;
if (ev.metaKey) modifiers |= (1<<2)|(1<<8); //InputEvent.META_MASK + InputEvent.META_DOWN_MASK;
if (ev.altKey) modifiers |= (1<<3)|(1<<9); //InputEvent.ALT_MASK + InputEvent.ALT_DOWN_MASK;
if (ev.altGraphKey) modifiers |= (1<<5)|(1<<13); //InputEvent.ALT_GRAPH_MASK + InputEvent.ALT_GRAPH_DOWN_MASK;
if (keyCode == 8 && evType == "keypress") ev.originalEvent.preventDefault();
}
id = (evType == "keydown" ? 401 : evType == "keypress" ? 400 : evType == "keyup" ? 402 : 0);
this.source = source;
this.id = id;
this.when = when;
this.modifiers = modifiers;
this.keyCode = keyCode;
var ch;
var test;
try {
this.keyCode = (key.length$() == 1 ? 0 + (id == 400 ? "\u0000" : key.toUpperCase().charAt(0)).$c() : keyCode == 10 || keyCode == 8  || keyCode == 9  ? 0 : (ch = (I$[1]||$incl$(1)).getVKValue$S("VK_" + key.toUpperCase())) == 0 ? keyCode : ch);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
} else {
throw e;
}
}
this.keyChar = (key.length$() == 1 ? key.charAt(0) : keyCode == 10 ? "\u000a" : keyCode == 8 ? "\u0008" : keyCode == 9 ? "\u0009" : "\u0000");
this.keyLocation = keyLocation;
this.jqEvent = ev;
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-03-16 05:50:14
