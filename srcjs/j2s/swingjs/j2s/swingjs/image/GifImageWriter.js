(function(){var P$=Clazz.newPackage("swingjs.image"),I$=[['swingjs.image.GifImageWriteParam']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "GifImageWriter", null, 'javax.imageio.ImageWriter');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.params.put$TK$TV("type", "GIF");
}, 1);

Clazz.newMeth(C$, 'write$javax_imageio_metadata_IIOMetadata$javax_imageio_IIOImage$javax_imageio_ImageWriteParam', function (streamMetadata, image, param) {
this.setMetaData$O(streamMetadata);
if (param == null ) param = this.getDefaultWriteParam();
this.write$javax_imageio_IIOImage(image);
});

Clazz.newMeth(C$, 'getDefaultWriteParam', function () {
return Clazz.new_((I$[1]||$incl$(1)).c$$java_util_Locale,[null]);
});
})();
//Created 2018-02-08 10:03:19
