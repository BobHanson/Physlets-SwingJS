(function(){var P$=Clazz.newPackage("swingjs"),I$=[['javajs.util.Base64']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSImage", null, 'java.awt.image.BufferedImage');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.src = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$IA$I$I$S', function (argb, width, height, src) {
C$.superclazz.c$$I$I$I.apply(this, [width, height, 2]);
C$.$init$.apply(this);
this.src = src;
this._pix = argb;
}, 1);

Clazz.newMeth(C$, 'getDOMImage$BA$S', function (b, type) {
var dataurl = "data:image/" + type + ";base64," + (I$[1]||$incl$(1)).getBase64$BA(b).toString() ;
var img = null;
{
img = new Image(this.width, this.height);
//if (this.callback) img.onload = this.callback;
img.src = dataurl;
}
this._imgNode = img;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:15
