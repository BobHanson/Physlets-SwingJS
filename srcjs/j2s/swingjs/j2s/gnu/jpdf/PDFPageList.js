(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[['java.util.Vector','gnu.jpdf.PDFStream','gnu.jpdf.PDFObject']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PDFPageList", null, 'gnu.jpdf.PDFObject');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.pages = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["/Pages"]);
C$.$init$.apply(this);
this.pages = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'add$gnu_jpdf_PDFPage', function (page) {
this.pages.addElement$TE(page);
page.pdfPageList = this;
});

Clazz.newMeth(C$, 'getPage$I', function (page) {
return (this.pages.elementAt$I(page));
});

Clazz.newMeth(C$, 'write$java_io_OutputStream', function (os) {
this.writeStart$java_io_OutputStream(os);
(I$[2]||$incl$(2)).write$java_io_OutputStream$S(os, "/Kids ");
(I$[2]||$incl$(2)).write$java_io_OutputStream$S(os, (I$[3]||$incl$(3)).toArray$java_util_Vector(this.pages));
(I$[2]||$incl$(2)).write$java_io_OutputStream$S(os, "\u000a");
(I$[2]||$incl$(2)).write$java_io_OutputStream$S(os, "/Count ");
(I$[2]||$incl$(2)).write$java_io_OutputStream$S(os, Integer.toString(this.pages.size()));
(I$[2]||$incl$(2)).write$java_io_OutputStream$S(os, "\u000a");
this.writeEnd$java_io_OutputStream(os);
});
})();
//Created 2018-02-08 10:01:44
