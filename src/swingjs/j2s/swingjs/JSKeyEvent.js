(function(){var P$=Clazz.newPackage("swingjs"),I$=[['java.awt.AWTKeyStroke']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSKeyEvent", null, 'java.awt.event.KeyEvent');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'newJSKeyEvent$javax_swing_JComponent$O$Z', function (source, jQueryEvent, isList) {
var evType = null;
var jskey = null;
var jskeyCode = 0;
var jskeyLocation = 0;
var ev = jQueryEvent;

evType = ev.type; jskey = ev.key;
jskeyCode = ev.keyCode || ev.which;
jskeyLocation = ev.originalEvent.location || 0;
if (isList && evType == "keypress") ev.originalEvent.preventDefault();
var id = (evType == "keydown" ? 401 : evType == "keypress" ? 400 : evType == "keyup" ? 402 : 0);
var keyCode = C$.getJavaKeyCode$I$S(jskeyCode, jskey);
var keyChar = C$.getJavaKeyChar$I$S(keyCode, jskey);
return (id == 0 || keyChar == "\uffff" && id == 400   ? null : Clazz.new_(C$.c$$javax_swing_JComponent$O$I$I$C$I,[source, jQueryEvent, id, (id == 400 ? 0 : keyCode), keyChar, (id == 400 ? 0 : jskeyLocation + 1)]));
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JComponent$O$I$I$C$I', function (source, ev, id, keyCode, keyChar, location) {
C$.superclazz.c$$java_awt_Component$I$J$I$I$C$I.apply(this, [source, id, System.currentTimeMillis(), 0, keyCode, keyChar, location]);
C$.$init$.apply(this);
var shift = false;
var ctrl = false;
var meta = false;
var alt = false;
var altGraph = false;

shift = ev.shiftKey;
ctrl = ev.ctrlKey;
alt = ev.altKey;
meta = ev.metaKey;
altGraph = ev.altGraphKey;
this.modifiers=C$.getModifiers$Z$Z$Z$Z$Z(shift, ctrl, alt, meta, altGraph);
}, 1);

Clazz.newMeth(C$, 'getJavaKeyCode$I$S', function (jskeyCode, jskey) {
if (jskeyCode <= 40) {
if (jskeyCode == 13) return 10;
return jskeyCode;
}if (jskey.length$() == 1) {
return (jskeyCode >= 96 && jskeyCode <= 105  ? jskeyCode : 0 + (jskey.toUpperCase().charCodeAt(0)));
}switch (jskeyCode) {
case 91:
return 157;
case 93:
return 525;
case 144:
case 145:
return jskeyCode;
case 244:
return 25;
}
var keyName = "VK_" + jskey.toUpperCase();
try {
return (I$[1]||$incl$(1)).getVKValue$S(keyName);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
System.out.println$S("JSKeyEvent adding key/keyCode " + keyName + " " + jskeyCode );
(I$[1]||$incl$(1)).addKeyCode$S$I(keyName, jskeyCode);
} else {
throw e;
}
}
return jskeyCode;
}, 1);

Clazz.newMeth(C$, 'getJavaKeyChar$I$S', function (jsKeyCode, jskey) {
if (jskey.length$() == 1) return jskey.charAt(0);
switch (jsKeyCode) {
case 13:
jsKeyCode=10;
case 10:
case 8:
case 9:
case 127:
case 27:
return String.fromCharCode(jsKeyCode);
default:
return "\uffff";
}
}, 1);

Clazz.newMeth(C$, 'hasKeyChar$I$S', function (javaKeyCode, jskey) {
switch (javaKeyCode) {
case 10:
case 8:
case 9:
case 127:
case 27:
return true;
default:
return (jskey.length$() == 1);
}
}, 1);

Clazz.newMeth(C$, 'getModifiers$Z$Z$Z$Z$Z', function (shift, ctrl, alt, meta, altGraph) {
var modifiers = 0;
if (shift) modifiers|=65;
if (ctrl) modifiers|=130;
if (alt) modifiers|=520;
if (meta) modifiers|=260;
if (altGraph) modifiers|=8224;
return 0;
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:44
