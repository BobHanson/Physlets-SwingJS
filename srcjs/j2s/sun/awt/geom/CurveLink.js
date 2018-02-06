(function(){var P$=Clazz.newPackage("sun.awt.geom"),I$=[['java.lang.InternalError','sun.awt.geom.Order0']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "CurveLink");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.curve = null;
this.ytop = 0;
this.ybot = 0;
this.etag = 0;
this.next = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$sun_awt_geom_Curve$D$D$I', function (curve, ystart, yend, etag) {
C$.$init$.apply(this);
this.curve = curve;
this.ytop = ystart;
this.ybot = yend;
this.etag = etag;
if (this.ytop < curve.getYTop()  || this.ybot > curve.getYBot()  ) {
throw Clazz.new_((I$[1]||$incl$(1)).c$$S,["bad curvelink [" + new Double(this.ytop).toString() + "=>" + new Double(this.ybot).toString() + "] for " + curve ]);
}}, 1);

Clazz.newMeth(C$, 'absorb$sun_awt_geom_CurveLink', function (link) {
return this.absorb$sun_awt_geom_Curve$D$D$I(link.curve, link.ytop, link.ybot, link.etag);
});

Clazz.newMeth(C$, 'absorb$sun_awt_geom_Curve$D$D$I', function (curve, ystart, yend, etag) {
if (this.curve !== curve  || this.etag != etag  || this.ybot < ystart   || this.ytop > yend  ) {
return false;
}if (ystart < curve.getYTop()  || yend > curve.getYBot()  ) {
throw Clazz.new_((I$[1]||$incl$(1)).c$$S,["bad curvelink [" + new Double(ystart).toString() + "=>" + new Double(yend).toString() + "] for " + curve ]);
}this.ytop = Math.min(this.ytop, ystart);
this.ybot = Math.max(this.ybot, yend);
return true;
});

Clazz.newMeth(C$, 'isEmpty', function () {
return (this.ytop == this.ybot );
});

Clazz.newMeth(C$, 'getCurve', function () {
return this.curve;
});

Clazz.newMeth(C$, 'getSubCurve', function () {
if (this.ytop == this.curve.getYTop()  && this.ybot == this.curve.getYBot()  ) {
return this.curve.getWithDirection$I(this.etag);
}return this.curve.getSubCurve$D$D$I(this.ytop, this.ybot, this.etag);
});

Clazz.newMeth(C$, 'getMoveto', function () {
return Clazz.new_((I$[2]||$incl$(2)).c$$D$D,[this.getXTop(), this.getYTop()]);
});

Clazz.newMeth(C$, 'getXTop', function () {
return this.curve.XforY$D(this.ytop);
});

Clazz.newMeth(C$, 'getYTop', function () {
return this.ytop;
});

Clazz.newMeth(C$, 'getXBot', function () {
return this.curve.XforY$D(this.ybot);
});

Clazz.newMeth(C$, 'getYBot', function () {
return this.ybot;
});

Clazz.newMeth(C$, 'getX', function () {
return this.curve.XforY$D(this.ytop);
});

Clazz.newMeth(C$, 'getEdgeTag', function () {
return this.etag;
});

Clazz.newMeth(C$, 'setNext$sun_awt_geom_CurveLink', function (link) {
this.next = link;
});

Clazz.newMeth(C$, 'getNext', function () {
return this.next;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:16
