(function(){var P$=Clazz.newPackage("swingjs.image"),I$=[];
var C$=Clazz.newClass(P$, "GifImageWriteParam", null, 'javax.imageio.ImageWriteParam');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Locale', function (l) {
C$.superclazz.c$$java_util_Locale.apply(this, [l]);
C$.$init$.apply(this);
this.$canWriteCompressed = true;
this.compressionTypes = Clazz.array(java.lang.String, -1, ["LZW", "lzw"]);
this.compressionMode = 3;
this.compressionType = "LZW";
this.compressionQuality = 1.0;
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:33
