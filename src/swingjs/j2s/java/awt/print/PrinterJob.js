(function(){var P$=Clazz.newPackage("java.awt.print"),I$=[['java.awt.AWTError','java.awt.print.PageFormat']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PrinterJob");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getPrinterJob', function () {
var nm = System.getProperty("java.awt.printerjob", null);
try {
return Clazz.forName(nm).newInstance();
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.lang.ClassNotFoundException")){
var e = e$$;
{
throw Clazz.new_((I$[1]||$incl$(1)).c$$S,["PrinterJob not found: " + nm]);
}
} else if (Clazz.exceptionOf(e$$, "java.lang.InstantiationException")){
var e = e$$;
{
throw Clazz.new_((I$[1]||$incl$(1)).c$$S,["Could not instantiate PrinterJob: " + nm]);
}
} else if (Clazz.exceptionOf(e$$, "java.lang.IllegalAccessException")){
var e = e$$;
{
throw Clazz.new_((I$[1]||$incl$(1)).c$$S,["Could not access PrinterJob: " + nm]);
}
} else {
throw e$$;
}
}
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'printDialog$javax_print_attribute_PrintRequestAttributeSet', function (attributes) {
if (attributes == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["attributes"]);
}return this.printDialog();
});

Clazz.newMeth(C$, 'pageDialog$javax_print_attribute_PrintRequestAttributeSet', function (attributes) {
if (attributes == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["attributes"]);
}return this.pageDialog$java_awt_print_PageFormat(this.defaultPage());
});

Clazz.newMeth(C$, 'defaultPage', function () {
return this.defaultPage$java_awt_print_PageFormat(Clazz.new_((I$[2]||$incl$(2))));
});

Clazz.newMeth(C$, 'getPageFormat$javax_print_attribute_PrintRequestAttributeSet', function (attributes) {
var pf = this.defaultPage();
return pf;
});

Clazz.newMeth(C$, 'print$javax_print_attribute_PrintRequestAttributeSet', function (attributes) {
this.print();
});
})();
//Created 2018-05-24 08:45:30
