(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DisplayMode");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.size = null;
this.bitDepth = 0;
this.refreshRate = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$I$I$I', function (width, height, bitDepth, refreshRate) {
C$.$init$.apply(this);
this.size=Clazz.new_((I$[1]||$incl$(1)).c$$I$I,[width, height]);
this.bitDepth=bitDepth;
this.refreshRate=refreshRate;
}, 1);

Clazz.newMeth(C$, 'getHeight', function () {
return this.size.height;
});

Clazz.newMeth(C$, 'getWidth', function () {
return this.size.width;
});

Clazz.newMeth(C$, 'getBitDepth', function () {
return this.bitDepth;
});

Clazz.newMeth(C$, 'getRefreshRate', function () {
return this.refreshRate;
});

Clazz.newMeth(C$, 'equals$java_awt_DisplayMode', function (dm) {
if (dm == null ) {
return false;
}return (this.getHeight() == dm.getHeight() && this.getWidth() == dm.getWidth()  && this.getBitDepth() == dm.getBitDepth()  && this.getRefreshRate() == dm.getRefreshRate() );
});

Clazz.newMeth(C$, 'equals$O', function (dm) {
if (Clazz.instanceOf(dm, "java.awt.DisplayMode")) {
return this.equals$java_awt_DisplayMode(dm);
} else {
return false;
}});

Clazz.newMeth(C$, 'hashCode', function () {
return this.getWidth() + this.getHeight() + this.getBitDepth() * 7  + this.getRefreshRate() * 13;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:08
