(function(){var P$=Clazz.newPackage("netscape.javascript"),I$=[['Boolean']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSObject");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, '$call$S$OA', function (jsFuncName, params) {
var ret = null;
try {

ret = self[jsFuncName].apply(null, params);
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException').c$$S,["" + t + " evaluating " + jsFuncName ]);
}
return p$.fixObject$O.apply(this, [ret]);
});

Clazz.newMeth(C$, 'fixObject$O', function (ret) {
var type = null;

type = typeof ret;
switch (type) {
case "number":
return Double.$valueOf("" + ret);
case "boolean":
return (I$[1]||$incl$(1)).$valueOf("" + ret);
default:
return ret;
}
});

Clazz.newMeth(C$, 'eval$S', function (params) {
var ret = null;
try {

ret = eval(params);
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException').c$$S,["" + t + " evaluating " + params ]);
}
return p$.fixObject$O.apply(this, [ret]);
});

Clazz.newMeth(C$, 'getMember$S', function (name) {
var ret = null;
try {

ret = self[name];
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException').c$$S,["" + t + " getMember " + name ]);
}
return p$.fixObject$O.apply(this, [ret]);
});

Clazz.newMeth(C$, 'setMember$S$O', function (name, value) {
try {

self[name] = value;
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException').c$$S,["" + t + " setMember " + name + " " + value ]);
}
});

Clazz.newMeth(C$, 'removeMember$S', function (name) {
try {

delete self[name];
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException').c$$S,["" + t + " removeMember " + name ]);
}
});

Clazz.newMeth(C$, 'getSlot$I', function (paramInt) {
throw Clazz.new_(Clazz.load('java.lang.RuntimeException').c$$S,["Not yet implemented (netscape.javascript.JSObject.getSlot(int))."]);
});

Clazz.newMeth(C$, 'setSlot$I$O', function (paramInt, paramObject) {
throw Clazz.new_(Clazz.load('java.lang.RuntimeException').c$$S,["Not yet implemented (netscape.javascript.JSObject.setSlot(int, Object))."]);
});

Clazz.newMeth(C$, 'getWindow$java_applet_Applet', function (paramApplet) {
{
return self;
}
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-06 10:23:35
