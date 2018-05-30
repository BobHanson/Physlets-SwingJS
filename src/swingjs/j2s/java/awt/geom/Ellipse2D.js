(function(){var P$=Clazz.newPackage("java.awt.geom"),I$=[[['java.awt.geom.Rectangle2D','.Float'],['java.awt.geom.Rectangle2D','.Double'],'java.awt.geom.EllipseIterator']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Ellipse2D", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.awt.geom.RectangularShape');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'contains$D$D', function (x, y) {
var ellw = this.getWidth();
if (ellw <= 0.0 ) {
return false;
}var normx = (x - this.getX()) / ellw - 0.5;
var ellh = this.getHeight();
if (ellh <= 0.0 ) {
return false;
}var normy = (y - this.getY()) / ellh - 0.5;
return (normx * normx + normy * normy) < 0.25 ;
});

Clazz.newMeth(C$, 'intersects$D$D$D$D', function (x, y, w, h) {
if (w <= 0.0  || h <= 0.0  ) {
return false;
}var ellw = this.getWidth();
if (ellw <= 0.0 ) {
return false;
}var normx0 = (x - this.getX()) / ellw - 0.5;
var normx1 = normx0 + w / ellw;
var ellh = this.getHeight();
if (ellh <= 0.0 ) {
return false;
}var normy0 = (y - this.getY()) / ellh - 0.5;
var normy1 = normy0 + h / ellh;
var nearx;
var neary;
if (normx0 > 0.0 ) {
nearx=normx0;
} else if (normx1 < 0.0 ) {
nearx=normx1;
} else {
nearx=0.0;
}if (normy0 > 0.0 ) {
neary=normy0;
} else if (normy1 < 0.0 ) {
neary=normy1;
} else {
neary=0.0;
}return (nearx * nearx + neary * neary) < 0.25 ;
});

Clazz.newMeth(C$, 'contains$D$D$D$D', function (x, y, w, h) {
return (this.contains$D$D(x, y) && this.contains$D$D(x + w, y) && this.contains$D$D(x, y + h) && this.contains$D$D(x + w, y + h)  );
});

Clazz.newMeth(C$, 'getPathIterator$java_awt_geom_AffineTransform', function (at) {
return Clazz.new_((I$[3]||$incl$(3)).c$$java_awt_geom_Ellipse2D$java_awt_geom_AffineTransform,[this, at]);
});

Clazz.newMeth(C$, 'hashCode', function () {
var bits = java.lang.Double.doubleToLongBits(this.getX());
bits+=java.lang.Double.doubleToLongBits(this.getY()) * 37;
bits+=java.lang.Double.doubleToLongBits(this.getWidth()) * 43;
bits+=java.lang.Double.doubleToLongBits(this.getHeight()) * 47;
return (((bits|0)) ^ (((bits >> 32)|0)));
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
if (obj === this ) {
return true;
}if (Clazz.instanceOf(obj, "java.awt.geom.Ellipse2D")) {
var e2d = obj;
return ((this.getX() == e2d.getX() ) && (this.getY() == e2d.getY() ) && (this.getWidth() == e2d.getWidth() ) && (this.getHeight() == e2d.getHeight() )  );
}return false;
});
;
(function(){var C$=Clazz.newClass(P$.Ellipse2D, "Float", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.awt.geom.Ellipse2D');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.x = 0;
this.y = 0;
this.width = 0;
this.height = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'c$$F$F$F$F', function (x, y, w, h) {
Clazz.super_(C$, this,1);
this.setFrame$F$F$F$F(x, y, w, h);
}, 1);

Clazz.newMeth(C$, 'getX', function () {
return this.x;
});

Clazz.newMeth(C$, 'getY', function () {
return this.y;
});

Clazz.newMeth(C$, 'getWidth', function () {
return this.width;
});

Clazz.newMeth(C$, 'getHeight', function () {
return this.height;
});

Clazz.newMeth(C$, 'isEmpty', function () {
return (this.width <= 0.0  || this.height <= 0.0  );
});

Clazz.newMeth(C$, 'setFrame$F$F$F$F', function (x, y, w, h) {
this.x=x;
this.y=y;
this.width=w;
this.height=h;
});

Clazz.newMeth(C$, 'setFrame$D$D$D$D', function (x, y, w, h) {
this.x=x;
this.y=y;
this.width=w;
this.height=h;
});

Clazz.newMeth(C$, 'getBounds2D', function () {
return Clazz.new_((I$[1]||$incl$(1)).c$$F$F$F$F,[this.x, this.y, this.width, this.height]);
});
})()
;
(function(){var C$=Clazz.newClass(P$.Ellipse2D, "Double", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'java.awt.geom.Ellipse2D');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.x = 0;
this.y = 0;
this.width = 0;
this.height = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'c$$D$D$D$D', function (x, y, w, h) {
Clazz.super_(C$, this,1);
this.setFrame$D$D$D$D(x, y, w, h);
}, 1);

Clazz.newMeth(C$, 'getX', function () {
return this.x;
});

Clazz.newMeth(C$, 'getY', function () {
return this.y;
});

Clazz.newMeth(C$, 'getWidth', function () {
return this.width;
});

Clazz.newMeth(C$, 'getHeight', function () {
return this.height;
});

Clazz.newMeth(C$, 'isEmpty', function () {
return (this.width <= 0.0  || this.height <= 0.0  );
});

Clazz.newMeth(C$, 'setFrame$D$D$D$D', function (x, y, w, h) {
this.x=x;
this.y=y;
this.width=w;
this.height=h;
});

Clazz.newMeth(C$, 'getBounds2D', function () {
return Clazz.new_((I$[2]||$incl$(2)).c$$D$D$D$D,[this.x, this.y, this.width, this.height]);
});
})()
})();
//Created 2018-05-24 08:45:21
