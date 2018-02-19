(function(){var P$=java.io,I$=[['java.net.URI','java.lang.Error','java.util.ArrayList']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "File", null, null, 'Comparable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.path = null;
this.prefixLength = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getPrefixLength', function () {
return this.prefixLength;
});

Clazz.newMeth(C$, 'c$$S$I', function (pathname, prefixLength) {
C$.$init$.apply(this);
this.path = pathname;
this.prefixLength = prefixLength;
}, 1);

Clazz.newMeth(C$, 'c$$S$java_io_File', function (child, parent) {
C$.$init$.apply(this);
Clazz.assert(C$, this, function(){return parent.path != null });
Clazz.assert(C$, this, function(){return (!parent.path.equals$O(""))});
this.path = p$.resolve$S$S.apply(this, [parent.path, child]);
this.prefixLength = parent.prefixLength;
}, 1);

Clazz.newMeth(C$, 'resolve$S$S', function (path, child) {
if (child.length$() > 0 && !path.endsWith$S("/") ) path += "/";
return path + child;
});

Clazz.newMeth(C$, 'c$$S', function (pathname) {
C$.c$$S$S.apply(this, [pathname, ""]);
}, 1);

Clazz.newMeth(C$, 'c$$S$S', function (parent, child) {
C$.$init$.apply(this);
if (child == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}if (parent != null ) {
if (parent.equals$O("")) {
this.path = p$.resolve$S$S.apply(this, [".", child]);
} else {
this.path = p$.resolve$S$S.apply(this, [parent, child]);
}} else {
this.path = p$.resolve$S$S.apply(this, [".", child]);
}this.prefixLength = this.path.lastIndexOf("/") + 1;
}, 1);

Clazz.newMeth(C$, 'c$$java_io_File$S', function (parent, child) {
C$.c$$S$S.apply(this, [parent == null  ? null : parent.getPath(), child]);
}, 1);

Clazz.newMeth(C$, 'getName', function () {
var index = this.path.lastIndexOf("/");
if (index < this.prefixLength) return this.path.substring(this.prefixLength);
return this.path.substring(index + 1);
});

Clazz.newMeth(C$, 'getParent', function () {
var index = this.path.lastIndexOf("/");
if (index < this.prefixLength) {
if ((this.prefixLength > 0) && (this.path.length$() > this.prefixLength) ) return this.path.substring(0, this.prefixLength);
return null;
}return this.path.substring(0, index);
});

Clazz.newMeth(C$, 'getParentFile', function () {
var p = this.getParent();
if (p == null ) return null;
return Clazz.new_(C$.c$$S$I,[p, this.prefixLength]);
});

Clazz.newMeth(C$, 'getPath', function () {
return this.path;
});

Clazz.newMeth(C$, 'isAbsolute', function () {
switch (this.path.indexOf("/")) {
case 0:
return true;
case 2:
return this.path.indexOf(":") == 1;
}
return false;
});

Clazz.newMeth(C$, 'getAbsolutePath', function () {
return this.path;
});

Clazz.newMeth(C$, 'getAbsoluteFile', function () {
return this;
});

Clazz.newMeth(C$, 'getCanonicalPath', function () {
return this.path;
});

Clazz.newMeth(C$, 'getCanonicalFile', function () {
return this;
});

Clazz.newMeth(C$, 'slashify$S$Z', function (path, isDirectory) {
var p = path;
p = p.$replace("\\", "/");
if (!p.startsWith$S("/")) p = "/" + p;
if (!p.endsWith$S("/") && isDirectory ) p = p + "/";
return p;
}, 1);

Clazz.newMeth(C$, 'toURI', function () {
try {
var f = this.getAbsoluteFile();
var sp = C$.slashify$S$Z(f.getPath(), f.isDirectory());
if (sp.startsWith$S("//")) sp = "//" + sp;
return Clazz.new_((I$[1]||$incl$(1)).c$$S$S$S$S,["file", null, sp, null]);
} catch (x) {
if (Clazz.exceptionOf(x, "java.net.URISyntaxException")){
throw Clazz.new_((I$[2]||$incl$(2)).c$$Throwable,[x]);
} else {
throw x;
}
}
});

Clazz.newMeth(C$, 'canRead', function () {
return true;
});

Clazz.newMeth(C$, 'canWrite', function () {
return true;
});

Clazz.newMeth(C$, 'exists', function () {
return true;
});

Clazz.newMeth(C$, 'isDirectory', function () {
return true;
});

Clazz.newMeth(C$, 'isFile', function () {
return true;
});

Clazz.newMeth(C$, 'list', function () {
throw Clazz.new_(Clazz.load('java.security.AccessControlException').c$$S,["access denied"]);
});

Clazz.newMeth(C$, 'list$java_io_FilenameFilter', function (filter) {
var names = this.list();
if ((names == null ) || (filter == null ) ) {
return names;
}var v = Clazz.new_((I$[3]||$incl$(3)));
for (var i = 0; i < names.length; i++) {
if (filter.accept$java_io_File$S(this, names[i])) {
v.add$TE(names[i]);
}}
return (v.toArray$TTA(Clazz.array(java.lang.String, [v.size()])));
});

Clazz.newMeth(C$, 'listFiles', function () {
var ss = this.list();
if (ss == null ) return null;
var n = ss.length;
var fs = Clazz.array(C$, [n]);
for (var i = 0; i < n; i++) {
fs[i] = Clazz.new_(C$.c$$S$java_io_File,[ss[i], this]);
}
return fs;
});

Clazz.newMeth(C$, ['compareTo$java_io_File','compareTo$TT'], function (pathname) {
return this.getPath().compareTo$S(pathname.getPath());
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
if ((obj != null ) && (Clazz.instanceOf(obj, "java.io.File")) ) {
return this.compareTo$java_io_File(obj) == 0;
}return false;
});

Clazz.newMeth(C$, 'toString', function () {
return this.getPath();
});
C$.$_ASSERT_ENABLED_ = ClassLoader.$getClassAssertionStatus(C$);

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:04
