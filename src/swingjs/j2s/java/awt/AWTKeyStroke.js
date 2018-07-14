(function(){var P$=Clazz.newPackage("java.awt"),I$=[['sun.awt.AppContext','java.util.HashMap','java.util.StringTokenizer','java.util.Collections','java.awt.VKCollection','java.lang.StringBuilder']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AWTKeyStroke");
C$.modifierKeywords = null;
C$.vks = null;
C$.APP_CONTEXT_CACHE_KEY = null;
C$.APP_CONTEXT_KEYSTROKE_KEY = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.APP_CONTEXT_CACHE_KEY =  Clazz.new_();
C$.APP_CONTEXT_KEYSTROKE_KEY = Clazz.new_(C$);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.keyChar = '\0';
this.keyCode = 0;
this.modifiers = 0;
this.onKeyRelease = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.keyChar = "\uffff";
this.keyCode = 0;
}, 1);

Clazz.newMeth(C$, 'getAWTKeyStrokeClass', function () {
var clazz = (I$[1]||$incl$(1)).getAppContext().get$O(Clazz.getClass(C$));
if (clazz == null ) {
clazz=Clazz.getClass(C$);
(I$[1]||$incl$(1)).getAppContext().put$O$O(Clazz.getClass(C$), Clazz.getClass(C$));
}return clazz;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$C$I$I$Z', function (keyChar, keyCode, modifiers, onKeyRelease) {
C$.$init$.apply(this);
this.keyChar=keyChar;
this.keyCode=keyCode;
this.modifiers=modifiers;
this.onKeyRelease=onKeyRelease;
}, 1);

Clazz.newMeth(C$, 'registerSubclass$Class', function (subclass) {
if (subclass == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["subclass cannot be null"]);
}{
var keyStrokeClass = (I$[1]||$incl$(1)).getAppContext().get$O(Clazz.getClass(C$));
if (keyStrokeClass != null  && keyStrokeClass.equals$O(subclass) ) {
return;
}}if (!Clazz.getClass(C$).isAssignableFrom$Class(subclass)) {
throw Clazz.new_(Clazz.load('java.lang.ClassCastException').c$$S,["subclass is not derived from AWTKeyStroke"]);
}var ctor = C$.getCtor$Class(subclass);
var couldNotInstantiate = "subclass could not be instantiated";
if (ctor == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[couldNotInstantiate]);
}try {
var stroke = ctor.newInstance$OA(null);
if (stroke == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[couldNotInstantiate]);
}} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.lang.NoSuchMethodError")){
var e = e$$;
{
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[couldNotInstantiate]);
}
} else if (Clazz.exceptionOf(e$$, "java.lang.ExceptionInInitializerError")){
var e = e$$;
{
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[couldNotInstantiate]);
}
} else if (Clazz.exceptionOf(e$$, "java.lang.InstantiationException")){
var e = e$$;
{
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[couldNotInstantiate]);
}
} else if (Clazz.exceptionOf(e$$, "java.lang.IllegalAccessException")){
var e = e$$;
{
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[couldNotInstantiate]);
}
} else if (Clazz.exceptionOf(e$$, "java.lang.reflect.InvocationTargetException")){
var e = e$$;
{
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[couldNotInstantiate]);
}
} else {
throw e$$;
}
}
{
(I$[1]||$incl$(1)).getAppContext().put$O$O(Clazz.getClass(C$), subclass);
(I$[1]||$incl$(1)).getAppContext().remove$O(C$.APP_CONTEXT_CACHE_KEY);
(I$[1]||$incl$(1)).getAppContext().remove$O(C$.APP_CONTEXT_KEYSTROKE_KEY);
}}, 1);

Clazz.newMeth(C$, 'getCtor$Class', function (clazz) {
try {
return clazz.getDeclaredConstructor$ClassA(null);
} catch (e) {
e.printStackTrace();
}
return null;
}, 1);

Clazz.newMeth(C$, 'getCachedStroke$C$I$I$Z', function (keyChar, keyCode, modifiers, onKeyRelease) {
var cache = (I$[1]||$incl$(1)).getAppContext().get$O(C$.APP_CONTEXT_CACHE_KEY);
var cacheKey = (I$[1]||$incl$(1)).getAppContext().get$O(C$.APP_CONTEXT_KEYSTROKE_KEY);
if (cache == null ) {
cache=Clazz.new_((I$[2]||$incl$(2)));
(I$[1]||$incl$(1)).getAppContext().put$O$O(C$.APP_CONTEXT_CACHE_KEY, cache);
}if (cacheKey == null ) {
try {
var clazz = C$.getAWTKeyStrokeClass();
cacheKey=C$.getCtor$Class(clazz).newInstance$OA(null);
(I$[1]||$incl$(1)).getAppContext().put$O$O(C$.APP_CONTEXT_KEYSTROKE_KEY, cacheKey);
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.lang.InstantiationException")){
var e = e$$;
{
Clazz.assert(C$, this, function(){return false});
}
} else if (Clazz.exceptionOf(e$$, "java.lang.IllegalAccessException")){
var e = e$$;
{
Clazz.assert(C$, this, function(){return false});
}
} else if (Clazz.exceptionOf(e$$, "java.lang.reflect.InvocationTargetException")){
var e = e$$;
{
Clazz.assert(C$, this, function(){return false});
}
} else {
throw e$$;
}
}
}cacheKey.keyChar=keyChar;
cacheKey.keyCode=keyCode;
cacheKey.modifiers=C$.mapNewModifiers$I(C$.mapOldModifiers$I(modifiers));
cacheKey.onKeyRelease=onKeyRelease;
var stroke = cache.get$O(cacheKey);
if (stroke == null ) {
stroke=cacheKey;
cache.put$TK$TV(stroke, stroke);
(I$[1]||$incl$(1)).getAppContext().remove$O(C$.APP_CONTEXT_KEYSTROKE_KEY);
}return stroke;
}, 1);

Clazz.newMeth(C$, 'getAWTKeyStroke$C', function (keyChar) {
return C$.getCachedStroke$C$I$I$Z(keyChar, 0, 0, false);
}, 1);

Clazz.newMeth(C$, 'getAWTKeyStroke$Character$I', function (keyChar, modifiers) {
if (keyChar == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["keyChar cannot be null"]);
}return C$.getCachedStroke$C$I$I$Z(keyChar.charValue(), 0, modifiers, false);
}, 1);

Clazz.newMeth(C$, 'getAWTKeyStroke$I$I$Z', function (keyCode, modifiers, onKeyRelease) {
return C$.getCachedStroke$C$I$I$Z("\uffff", keyCode, modifiers, onKeyRelease);
}, 1);

Clazz.newMeth(C$, 'getAWTKeyStroke$I$I', function (keyCode, modifiers) {
return C$.getCachedStroke$C$I$I$Z("\uffff", keyCode, modifiers, false);
}, 1);

Clazz.newMeth(C$, 'getAWTKeyStrokeForEvent$java_awt_event_KeyEvent', function (anEvent) {
var id = anEvent.getID();
switch (id) {
case 401:
case 402:
return C$.getCachedStroke$C$I$I$Z("\uffff", anEvent.getKeyCode(), anEvent.getModifiers(), (id == 402));
case 400:
return C$.getCachedStroke$C$I$I$Z(anEvent.getKeyChar(), 0, anEvent.getModifiers(), false);
default:
return null;
}
}, 1);

Clazz.newMeth(C$, 'getAWTKeyStroke$S', function (s) {
if (s == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["String cannot be null"]);
}var errmsg = "String formatted incorrectly";
var st = Clazz.new_((I$[3]||$incl$(3)).c$$S$S,[s, " "]);
var mask = 0;
var released = false;
var typed = false;
var pressed = false;
{
if (C$.modifierKeywords == null ) {
var uninitializedMap = Clazz.new_((I$[2]||$incl$(2)).c$$I$F,[8, 1.0]);
uninitializedMap.put$TK$TV("shift", Integer.$valueOf(65));
uninitializedMap.put$TK$TV("control", Integer.$valueOf(130));
uninitializedMap.put$TK$TV("ctrl", Integer.$valueOf(130));
uninitializedMap.put$TK$TV("meta", Integer.$valueOf(260));
uninitializedMap.put$TK$TV("alt", Integer.$valueOf(520));
uninitializedMap.put$TK$TV("altGraph", Integer.$valueOf(8224));
uninitializedMap.put$TK$TV("button1", Integer.$valueOf(1024));
uninitializedMap.put$TK$TV("button2", Integer.$valueOf(2048));
uninitializedMap.put$TK$TV("button3", Integer.$valueOf(4096));
C$.modifierKeywords=(I$[4]||$incl$(4)).synchronizedMap$java_util_Map(uninitializedMap);
}}var count = st.countTokens();
for (var i = 1; i <= count; i++) {
var token = st.nextToken();
if (typed) {
if (token.length$() != 1 || i != count ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["String formatted incorrectly"]);
}return C$.getCachedStroke$C$I$I$Z(token.charAt(0), 0, mask, false);
}if (pressed || released || i == count  ) {
if (i != count) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["String formatted incorrectly"]);
}var keyCodeName = "VK_" + token;
var keyCode = C$.getVKValue$S(keyCodeName);
return C$.getCachedStroke$C$I$I$Z("\uffff", keyCode, mask, released);
}if (token.equals$O("released")) {
released=true;
continue;
}if (token.equals$O("pressed")) {
pressed=true;
continue;
}if (token.equals$O("typed")) {
typed=true;
continue;
}var tokenMask = C$.modifierKeywords.get$O(token);
if (tokenMask != null ) {
mask|=tokenMask.intValue();
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["String formatted incorrectly"]);
}}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["String formatted incorrectly"]);
}, 1);

Clazz.newMeth(C$, 'getVKCollection', function () {
return (C$.vks == null  ? (C$.vks=Clazz.new_((I$[5]||$incl$(5)))) : C$.vks);
}, 1);

Clazz.newMeth(C$, 'addKeyCode$S$I', function (key, keyCode) {
C$.getVKCollection().put$S$Integer(key, Integer.$valueOf(keyCode));
}, 1);

Clazz.newMeth(C$, 'getVKValue$S', function (key) {
var vkCollect = C$.getVKCollection();
var value = vkCollect.findCode$S(key);
if (value == null ) {
vkCollect.put$S$Integer(key, Integer.$valueOf(-1));
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["String formatted incorrectly"]);
}return value.intValue();
}, 1);

Clazz.newMeth(C$, 'getKeyChar', function () {
return this.keyChar;
});

Clazz.newMeth(C$, 'getKeyCode', function () {
return this.keyCode;
});

Clazz.newMeth(C$, 'getModifiers', function () {
return this.modifiers;
});

Clazz.newMeth(C$, 'isOnKeyRelease', function () {
return this.onKeyRelease;
});

Clazz.newMeth(C$, 'getKeyEventType', function () {
if (this.keyCode == 0) {
return 400;
} else {
return (this.onKeyRelease) ? 402 : 401;
}});

Clazz.newMeth(C$, 'hashCode', function () {
return ((this.keyChar.$c()) + 1) * (2 * (this.keyCode + 1)) * (this.modifiers + 1)  + (this.onKeyRelease ? 1 : 2);
});

Clazz.newMeth(C$, 'equals$O', function (anObject) {
if (Clazz.instanceOf(anObject, "java.awt.AWTKeyStroke")) {
var ks = anObject;
return (ks.keyChar == this.keyChar && ks.keyCode == this.keyCode  && ks.onKeyRelease == this.onKeyRelease   && ks.modifiers == this.modifiers );
}return false;
});

Clazz.newMeth(C$, 'toString', function () {
if (this.keyCode == 0) {
return C$.getModifiersText$I(this.modifiers) + "typed " + this.keyChar ;
} else {
return C$.getModifiersText$I(this.modifiers) + (this.onKeyRelease ? "released" : "pressed") + " " + C$.getVKText$I(this.keyCode) ;
}});

Clazz.newMeth(C$, 'getModifiersText$I', function (modifiers) {
var buf = Clazz.new_((I$[6]||$incl$(6)));
if ((modifiers & 64) != 0) {
buf.append$S("shift ");
}if ((modifiers & 128) != 0) {
buf.append$S("ctrl ");
}if ((modifiers & 256) != 0) {
buf.append$S("meta ");
}if ((modifiers & 512) != 0) {
buf.append$S("alt ");
}if ((modifiers & 8192) != 0) {
buf.append$S("altGraph ");
}if ((modifiers & 1024) != 0) {
buf.append$S("button1 ");
}if ((modifiers & 2048) != 0) {
buf.append$S("button2 ");
}if ((modifiers & 4096) != 0) {
buf.append$S("button3 ");
}return buf.toString();
}, 1);

Clazz.newMeth(C$, 'getVKText$I', function (keyCode) {
var vkCollect = C$.getVKCollection();
var key = Integer.$valueOf(keyCode);
var name = vkCollect.findName$Integer(key);
if (name != null ) {
return name.substring(3);
}return "UNKNOWN";
}, 1);

Clazz.newMeth(C$, 'readResolve', function () {
{
var newClass = this.getClass();
var awtKeyStrokeClass = C$.getAWTKeyStrokeClass();
if (!newClass.equals$O(awtKeyStrokeClass)) {
C$.registerSubclass$Class(newClass);
}return C$.getCachedStroke$C$I$I$Z(this.keyChar, this.keyCode, this.modifiers, this.onKeyRelease);
}});

Clazz.newMeth(C$, 'mapOldModifiers$I', function (modifiers) {
if ((modifiers & 1) != 0) {
modifiers|=64;
}if ((modifiers & 8) != 0) {
modifiers|=512;
}if ((modifiers & 32) != 0) {
modifiers|=8192;
}if ((modifiers & 2) != 0) {
modifiers|=128;
}if ((modifiers & 4) != 0) {
modifiers|=256;
}modifiers&=16320;
return modifiers;
}, 1);

Clazz.newMeth(C$, 'mapNewModifiers$I', function (modifiers) {
if ((modifiers & 64) != 0) {
modifiers|=1;
}if ((modifiers & 512) != 0) {
modifiers|=8;
}if ((modifiers & 8192) != 0) {
modifiers|=32;
}if ((modifiers & 128) != 0) {
modifiers|=2;
}if ((modifiers & 256) != 0) {
modifiers|=4;
}return modifiers;
}, 1);
C$.$_ASSERT_ENABLED_ = ClassLoader.$getClassAssertionStatus(C$);
})();
//Created 2018-05-24 08:45:05