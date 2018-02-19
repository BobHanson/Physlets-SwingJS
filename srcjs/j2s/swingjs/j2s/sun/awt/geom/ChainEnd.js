(function(){var P$=Clazz.newPackage("sun.awt.geom"),I$=[['java.lang.InternalError']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ChainEnd");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.head = null;
this.tail = null;
this.partner = null;
this.etag = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$sun_awt_geom_CurveLink$sun_awt_geom_ChainEnd', function (first, partner) {
C$.$init$.apply(this);
this.head = first;
this.tail = first;
this.partner = partner;
this.etag = first.getEdgeTag();
}, 1);

Clazz.newMeth(C$, 'getChain', function () {
return this.head;
});

Clazz.newMeth(C$, 'setOtherEnd$sun_awt_geom_ChainEnd', function (partner) {
this.partner = partner;
});

Clazz.newMeth(C$, 'getPartner', function () {
return this.partner;
});

Clazz.newMeth(C$, 'linkTo$sun_awt_geom_ChainEnd', function (that) {
if (this.etag == 0 || that.etag == 0 ) {
throw Clazz.new_((I$[1]||$incl$(1)).c$$S,["ChainEnd linked more than once!"]);
}if (this.etag == that.etag) {
throw Clazz.new_((I$[1]||$incl$(1)).c$$S,["Linking chains of the same type!"]);
}var enter;
var exit;
if (this.etag == 1) {
enter = this;
exit = that;
} else {
enter = that;
exit = this;
}this.etag = 0;
that.etag = 0;
enter.tail.setNext$sun_awt_geom_CurveLink(exit.head);
enter.tail = exit.tail;
if (this.partner === that ) {
return enter.head;
}var otherenter = exit.partner;
var otherexit = enter.partner;
otherenter.partner = otherexit;
otherexit.partner = otherenter;
if (enter.head.getYTop() < otherenter.head.getYTop() ) {
enter.tail.setNext$sun_awt_geom_CurveLink(otherenter.head);
otherenter.head = enter.head;
} else {
otherexit.tail.setNext$sun_awt_geom_CurveLink(enter.head);
otherexit.tail = enter.tail;
}return null;
});

Clazz.newMeth(C$, 'addLink$sun_awt_geom_CurveLink', function (newlink) {
if (this.etag == 1) {
this.tail.setNext$sun_awt_geom_CurveLink(newlink);
this.tail = newlink;
} else {
newlink.setNext$sun_awt_geom_CurveLink(this.head);
this.head = newlink;
}});

Clazz.newMeth(C$, 'getX', function () {
if (this.etag == 1) {
return this.tail.getXBot();
} else {
return this.head.getXBot();
}});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:09
