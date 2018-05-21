(function(){var P$=Clazz.newPackage("swingjs"),I$=[['java.io.ByteArrayOutputStream','java.io.FileOutputStream']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSPrintJob", null, 'gnu.jpdf.PDFJob');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.fileName = null;
this.jobTitle = null;
this.jobAttributes = null;
this.pageAttributes = null;
this.properties = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'setAttributes$S$java_awt_JobAttributes$java_awt_PageAttributes', function (jobtitle, jobAttributes, pageAttributes) {
p$.setFile$S.apply(this, [jobtitle]);
this.jobAttributes = jobAttributes;
this.pageAttributes = pageAttributes;
});

Clazz.newMeth(C$, 'setProperties$S$java_util_Properties', function (jobtitle, properties) {
p$.setFile$S.apply(this, [jobtitle]);
this.properties = properties;
});

Clazz.newMeth(C$, 'setFile$S', function (jobtitle) {
this.fileName = this.jobTitle = jobtitle;
if (this.fileName != null  && !this.fileName.endsWith$S(".pdf") ) this.fileName += ".pdf";
try {
this.os = (this.fileName == null  ? Clazz.new_((I$[1]||$incl$(1))) : Clazz.new_((I$[2]||$incl$(2)).c$$S,[this.fileName]));
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.FileNotFoundException")){
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'getOutputStream', function () {
return this.os;
});

Clazz.newMeth(C$, 'getGraphics', function () {
var name = (this.pageAttributes != null  ? this.pageAttributes.getOrientationRequested().toString() : this.properties != null  ? this.properties.getProperty$S("orientation") : null);
return this.getGraphics$I("landscape".equals$O(name) ? 0 : 1);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:16
