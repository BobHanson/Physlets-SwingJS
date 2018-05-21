(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[['java.awt.Rectangle','gnu.jpdf.PDFDocument','gnu.jpdf.PDFPage',['gnu.jpdf.PDFJob','.PDFGraphic']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PDFJob", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.awt.PrintJob', 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.os = null;
this.pdfDocument = null;
this.page = null;
this.pagenum = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$java_io_OutputStream.apply(this, [null]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream', function (os) {
C$.c$$java_io_OutputStream$S.apply(this, [os, "PDF Doc"]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream$S', function (os, title) {
Clazz.super_(C$, this,1);
this.os = os;
this.pdfDocument = Clazz.new_((I$[2]||$incl$(2)));
this.pagenum = 0;
this.pdfDocument.getPDFInfo().setTitle$S(title);
}, 1);

Clazz.newMeth(C$, 'getGraphics$I', function (orient) {
this.page = Clazz.new_((I$[3]||$incl$(3)).c$$I,[orient]);
this.pdfDocument.add$gnu_jpdf_PDFObject(this.page);
this.pagenum++;
return Clazz.new_((I$[4]||$incl$(4)).c$$gnu_jpdf_PDFPage$gnu_jpdf_PDFJob, [this, null, this.page, this]);
});

Clazz.newMeth(C$, 'getGraphics$java_awt_print_PageFormat', function (pageFormat) {
this.page = Clazz.new_((I$[3]||$incl$(3)).c$$java_awt_print_PageFormat,[pageFormat]);
this.pdfDocument.add$gnu_jpdf_PDFObject(this.page);
this.pagenum++;
return Clazz.new_((I$[4]||$incl$(4)).c$$gnu_jpdf_PDFPage$gnu_jpdf_PDFJob, [this, null, this.page, this]);
});

Clazz.newMeth(C$, 'end', function () {
try {
this.pdfDocument.write$java_io_OutputStream(this.os);
} catch (ioe) {
if (Clazz.exceptionOf(ioe, "java.io.IOException")){
ioe.printStackTrace();
} else {
throw ioe;
}
} finally {
try {
if (this.os != null ) {
this.os.close();
}} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
e.printStackTrace();
} else {
throw e;
}
}
}
System.out.println$S("GNU JPDF job complete: " + this.pdfDocument.getPDFInfo().getTitle());
this.os = null;
this.pdfDocument = null;
});

Clazz.newMeth(C$, 'getGraphics', function () {
return this.getGraphics$I(1);
});

Clazz.newMeth(C$, 'getPageDimension', function () {
if (this.page == null ) {
System.err.println$S("PDFJob.getPageDimension(), page is null");
}return this.page.getDimension();
});

Clazz.newMeth(C$, 'getPageResolution', function () {
return 72;
});

Clazz.newMeth(C$, 'lastPageFirst', function () {
return false;
});

Clazz.newMeth(C$, 'getPDFDocument', function () {
return this.pdfDocument;
});

Clazz.newMeth(C$, 'getCurrentPage', function () {
return this.page;
});

Clazz.newMeth(C$, 'getCurrentPageNumber', function () {
return this.pagenum;
});

Clazz.newMeth(C$, 'addOutline$S', function (title) {
return this.page.addOutline$S(title);
});

Clazz.newMeth(C$, 'addOutline$S$I$I$I$I', function (title, x, y, w, h) {
return this.page.addOutline$S$I$I$I$I(title, x, y, w, h);
});

Clazz.newMeth(C$, 'addNote$S$I$I$I$I', function (note, x, y, w, h) {
return this.page.addNote$S$I$I$I$I(note, x, y, w, h);
});
;
(function(){var C$=Clazz.newClass(P$.PDFJob, "PDFGraphic", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'gnu.jpdf.PDFGraphics', 'java.awt.PrintGraphics');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.job = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$gnu_jpdf_PDFPage$gnu_jpdf_PDFJob', function (page, job) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.init$gnu_jpdf_PDFPage(page);
this.job = job;
}, 1);

Clazz.newMeth(C$, 'c$$gnu_jpdf_PDFPage$gnu_jpdf_PDFJob$java_io_PrintWriter', function (page, job, pw) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.init$gnu_jpdf_PDFPage$java_io_PrintWriter(page, pw);
this.job = job;
}, 1);

Clazz.newMeth(C$, 'create', function () {
this.closeBlock();
var g = Clazz.new_(C$.c$$gnu_jpdf_PDFPage$gnu_jpdf_PDFJob$java_io_PrintWriter, [this, null, this.getPage(), this.job, this.getWriter()]);
g.clipRectangle = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[this.clipRectangle]);
return g;
});

Clazz.newMeth(C$, 'getPrintJob', function () {
return this.job;
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-15 01:01:46
