(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[[0,'gnu.jpdf.PDFStream','StringBuffer']],$I$=function(i){return I$[i]||(I$[i]=Clazz.load(I$[0][i]))};
var C$=Clazz.newClass(P$, "PDFObject", null, null, 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.type=null;
this.objser=0;
this.pdfDocument=null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (type) {
C$.$init$.apply(this);
this.type=type;
}, 1);

Clazz.newMeth(C$, 'getType$', function () {
return this.type;
});

Clazz.newMeth(C$, 'getSerialID$', function () {
return this.objser;
});

Clazz.newMeth(C$, 'getPDFDocument$', function () {
return this.pdfDocument;
});

Clazz.newMeth(C$, 'writeStart$java_io_OutputStream', function (os) {
$I$(1).write$java_io_OutputStream$S(os, Integer.toString$I(this.objser));
$I$(1).write$java_io_OutputStream$S(os, " 0 obj\n<<\n");
if (this.type != null ) {
$I$(1).write$java_io_OutputStream$S(os, "/Type ");
$I$(1).write$java_io_OutputStream$S(os, this.type);
$I$(1).write$java_io_OutputStream$S(os, "\n");
}});

Clazz.newMeth(C$, 'writeEnd$java_io_OutputStream', function (os) {
$I$(1).write$java_io_OutputStream$S(os, ">>\nendobj\n");
});

Clazz.newMeth(C$, 'toString', function () {
return "" + this.objser + " 0 R" ;
});

Clazz.newMeth(C$, 'toArray$java_util_Vector', function (v) {
if (v.size$() == 0) return "";
var b=Clazz.new_($I$(2));
var bs="[";
for (var x, $x = v.iterator$(); $x.hasNext$()&&((x=($x.next$())),1);) {
b.append$S(bs);
b.append$S(x.toString());
bs=" ";
}
b.append$S("]");
return b.toString();
}, 1);

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 21:46:39 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
