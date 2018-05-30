(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[['gnu.jpdf.PDFStream','gnu.jpdf.PDFDocument']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PDFCatalog", null, 'gnu.jpdf.PDFObject');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.pdfPageList = null;
this.outlines = null;
this.pagemode = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$gnu_jpdf_PDFPageList$I', function (pdfPageList, pagemode) {
C$.superclazz.c$$S.apply(this, ["/Catalog"]);
C$.$init$.apply(this);
this.pdfPageList=pdfPageList;
this.pagemode=pagemode;
}, 1);

Clazz.newMeth(C$, 'setOutline$gnu_jpdf_PDFOutline', function (outline) {
this.outlines=outline;
});

Clazz.newMeth(C$, 'write$java_io_OutputStream', function (os) {
this.writeStart$java_io_OutputStream(os);
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, "/Pages ");
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, this.pdfPageList.toString());
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, "\u000a");
if (this.outlines != null ) {
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, "/Outlines ");
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, this.outlines.toString());
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, "\u000a");
}(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, "/PageMode ");
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, (I$[2]||$incl$(2)).PDF_PAGE_MODES[this.pagemode]);
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, "\u000a");
this.writeEnd$java_io_OutputStream(os);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:02
