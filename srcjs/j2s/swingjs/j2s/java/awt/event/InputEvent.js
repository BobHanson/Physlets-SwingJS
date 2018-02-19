(function(){var P$=Clazz.newPackage("java.awt.event"),I$=[['java.lang.StringBuilder','java.awt.Toolkit']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "InputEvent", null, 'java.awt.event.ComponentEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.when = 0;
this.modifiers = 0;
this.canAccessSystemClipboard = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Component$I$J$I', function (source, id, when, modifiers) {
C$.superclazz.c$$java_awt_Component$I.apply(this, [source, id]);
C$.$init$.apply(this);
this.when = when;
this.modifiers = modifiers;
this.canAccessSystemClipboard = false;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'isShiftDown', function () {
return (this.modifiers & 1) != 0;
});

Clazz.newMeth(C$, 'isControlDown', function () {
return (this.modifiers & 2) != 0;
});

Clazz.newMeth(C$, 'isMetaDown', function () {
return (this.modifiers & 4) != 0;
});

Clazz.newMeth(C$, 'isAltDown', function () {
return (this.modifiers & 8) != 0;
});

Clazz.newMeth(C$, 'isAltGraphDown', function () {
return (this.modifiers & 32) != 0;
});

Clazz.newMeth(C$, 'getWhen', function () {
return this.when;
});

Clazz.newMeth(C$, 'getModifiers', function () {
return this.modifiers & -16321;
});

Clazz.newMeth(C$, 'getModifiersEx', function () {
return this.modifiers & -64;
});

Clazz.newMeth(C$, 'consume', function () {
{
if (this.bdata && this.bdata.jqevent) { this.bdata.jqevent.stopPropagation();
this.bdata.jqevent.preventDefault();
}
}
this.consumed = true;
});

Clazz.newMeth(C$, 'isConsumed', function () {
return this.consumed;
});

Clazz.newMeth(C$, 'getModifiersExText$I', function (modifiers) {
var buf = Clazz.new_((I$[1]||$incl$(1)));
if ((modifiers & 256) != 0) {
buf.append$S((I$[2]||$incl$(2)).getProperty$S$S("AWT.meta", "Meta"));
buf.append$S("+");
}if ((modifiers & 128) != 0) {
buf.append$S((I$[2]||$incl$(2)).getProperty$S$S("AWT.control", "Ctrl"));
buf.append$S("+");
}if ((modifiers & 512) != 0) {
buf.append$S((I$[2]||$incl$(2)).getProperty$S$S("AWT.alt", "Alt"));
buf.append$S("+");
}if ((modifiers & 64) != 0) {
buf.append$S((I$[2]||$incl$(2)).getProperty$S$S("AWT.shift", "Shift"));
buf.append$S("+");
}if ((modifiers & 8192) != 0) {
buf.append$S((I$[2]||$incl$(2)).getProperty$S$S("AWT.altGraph", "Alt Graph"));
buf.append$S("+");
}if ((modifiers & 1024) != 0) {
buf.append$S((I$[2]||$incl$(2)).getProperty$S$S("AWT.button1", "Button1"));
buf.append$S("+");
}if ((modifiers & 2048) != 0) {
buf.append$S((I$[2]||$incl$(2)).getProperty$S$S("AWT.button2", "Button2"));
buf.append$S("+");
}if ((modifiers & 4096) != 0) {
buf.append$S((I$[2]||$incl$(2)).getProperty$S$S("AWT.button3", "Button3"));
buf.append$S("+");
}if (buf.length$() > 0) {
buf.setLength$I(buf.length$() - 1);
}return buf.toString();
}, 1);
})();
//Created 2018-02-08 10:01:55
