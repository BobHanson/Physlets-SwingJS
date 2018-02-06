(function(){var P$=java.lang,I$=[['java.lang.StringBuilder']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(java.lang, "StackTraceElement", null, null, 'java.io.Serializable');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.declaringClass = null;
this.methodName = null;
this.fileName = null;
this.lineNumber = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$S$S$I', function (cls, method, file, line) {
C$.$init$.apply(this);
if (cls == null  || method == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.declaringClass = cls;
this.methodName = method;
this.fileName = file;
this.lineNumber = line;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'equals$O', function (obj) {
if (!(Clazz.instanceOf(obj, "java.lang.StackTraceElement"))) {
return false;
}var castObj = obj;
if ((this.methodName == null ) || (castObj.methodName == null ) ) {
return false;
}if (!this.getMethodName().equals$O(castObj.getMethodName())) {
return false;
}if (!this.getClassName().equals$O(castObj.getClassName())) {
return false;
}var localFileName = this.getFileName();
if (localFileName == null ) {
if (castObj.getFileName() != null ) {
return false;
}} else {
if (!localFileName.equals$O(castObj.getFileName())) {
return false;
}}if (this.getLineNumber() != castObj.getLineNumber()) {
return false;
}return true;
});

Clazz.newMeth(C$, 'getClassName', function () {
return (this.declaringClass == null ) ? "<unknown class>" : this.declaringClass;
});

Clazz.newMeth(C$, 'getFileName', function () {
return this.fileName;
});

Clazz.newMeth(C$, 'getLineNumber', function () {
return this.lineNumber;
});

Clazz.newMeth(C$, 'getMethodName', function () {
return (this.methodName == null ) ? "<unknown method>" : this.methodName;
});

Clazz.newMeth(C$, 'hashCode', function () {
if (this.methodName == null ) {
return 0;
}return this.methodName.hashCode() ^ this.declaringClass.hashCode();
});

Clazz.newMeth(C$, 'isNativeMethod', function () {
return this.lineNumber == -2;
});

Clazz.newMeth(C$, 'toString', function () {
var buf = Clazz.new_((I$[1]||$incl$(1)).c$$I,[80]);
buf.append$S(this.getClassName());
buf.append$C(".");
buf.append$S(this.getMethodName());
if (this.isNativeMethod()) {
buf.append$S("(Native Method)");
} else {
var fName = this.getFileName();
if (fName == null ) {
buf.append$S("(Unknown Source)");
} else {
var lineNum = this.getLineNumber();
buf.append$C("(");
buf.append$S(fName);
if (lineNum >= 0) {
buf.append$C(":");
buf.append$I(lineNum);
}buf.append$C(")");
}}return buf.toString();
});
})();
//Created 2018-02-06 08:58:37
