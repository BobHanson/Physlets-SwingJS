(function(){var P$=Clazz.newPackage("netscape.javascript"),I$=[['Boolean']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSObject");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.obj = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, '$call', function (jsFuncName, params) {
var ret = null;
try {
if (params == null ) params = Clazz.array(java.lang.Object, [0]);
for (var i = params.length; --i >= 0; ) {
params[i] = p$.unfixObject.apply(this, [params[i]]);
}

ret = this.obj[jsFuncName].apply(this.obj, params);
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException'),["" + t + " evaluating " + jsFuncName ]);
}
return p$.fixObject.apply(this, [ret]);
});

Clazz.newMeth(C$, 'unfixObject', function (o) {
var ret = o;
if (o == null ) {
return null;
} else if (Clazz.instanceOf(o, "java.lang.Number")) {

return o.doubleValue();
} else if (Clazz.instanceOf(o, "java.lang.Boolean")) {

return o.BooleanValue();
} else if (Clazz.instanceOf(o, "netscape.javascript.JSObject")) {
return (o).obj;
}return ret;
});

Clazz.newMeth(C$, 'fixObject', function (ret) {
if (ret == null ) return null;
var type = null;

type = typeof ret;
switch (type) {
case "number":
return Double.$valueOf("" + ret);
case "boolean":
return (I$[1]||$incl$(1)).$valueOf("" + ret);
default:
var jsobject = Clazz.new_(C$);
jsobject.obj = ret;
return jsobject;
}
});

Clazz.newMeth(C$, 'eval', function (params) {
var ret = null;
try {

ret = this.obj.eval(params);
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException'),["" + t + " evaluating " + params ]);
}
return p$.fixObject.apply(this, [ret]);
});

Clazz.newMeth(C$, 'getMember', function (name) {
var ret = null;
try {

ret = this.obj[name];
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException'),["" + t + " getMember " + name ]);
}
return p$.fixObject.apply(this, [ret]);
});

Clazz.newMeth(C$, 'setMember', function (name, value) {
try {

this.obj[name] = value;
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException'),["" + t + " setMember " + name + " " + value ]);
}
});

Clazz.newMeth(C$, 'removeMember', function (name) {
try {

delete this.obj[name];
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException'),["" + t + " removeMember " + name ]);
}
});

Clazz.newMeth(C$, 'getSlot', function (index) {
var ret = null;
try {

return this.obj[index];
return p$.fixObject.apply(this, [ret]);
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException'),["" + t + " getSlot" ]);
}
});

Clazz.newMeth(C$, 'setSlot', function (index, val) {
try {

this.obj[index] = val;
} catch (t) {
throw Clazz.new_(Clazz.load('netscape.javascript.JSException'),["" + t + " setSlot" ]);
}
});

Clazz.newMeth(C$, 'getWindow', function (applet) {
var jsobject = Clazz.new_(C$);
var context = applet.getAppletContext();
jsobject.obj = context.html5Applet._window ||null;
return jsobject;
}, 1);
})();
//Created 2018-05-15 01:03:02
