(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[['gnu.jpdf.PDFStream','java.lang.StringBuffer']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PDFObject", null, null, 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.type = null;
this.objser = 0;
this.pdfDocument = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (type) {
C$.$init$.apply(this);
this.type=type;
}, 1);

Clazz.newMeth(C$, 'getType', function () {
return this.type;
});

Clazz.newMeth(C$, 'getSerialID', function () {
return this.objser;
});

Clazz.newMeth(C$, 'getPDFDocument', function () {
return this.pdfDocument;
});

Clazz.newMeth(C$, 'writeStart$java_io_OutputStream', function (os) {
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, Integer.toString(this.objser));
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, " 0 obj\u000a<<\u000a");
if (this.type != null ) {
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, "/Type ");
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, this.type);
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, "\u000a");
}});

Clazz.newMeth(C$, 'writeEnd$java_io_OutputStream', function (os) {
(I$[1]||$incl$(1)).write$java_io_OutputStream$S(os, ">>\u000aendobj\u000a");
});

Clazz.newMeth(C$, 'toString', function () {
return "" + this.objser + " 0 R" ;
});

Clazz.newMeth(C$, 'toArray$java_util_Vector', function (v) {
if (v.size() == 0) return "";
var b = Clazz.new_((I$[2]||$incl$(2)));
var bs = "[";
for (var x, $x = v.iterator(); $x.hasNext()&&((x=$x.next()),1);) {
b.append$S(bs);
b.append$S(x.toString());
bs=" ";
}
b.append$S("]");
return b.toString();
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:03
