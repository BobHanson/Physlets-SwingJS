(function(){var P$=Clazz.newPackage("swingjs.image"),I$=[[0,'swingjs.image.JpgImageWriteParam']],$I$=function(i){return I$[i]||(I$[i]=Clazz.load(I$[0][i]))};
var C$=Clazz.newClass(P$, "JpgImageWriter", null, 'javax.imageio.ImageWriter');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.params.put$TK$TV("type", "JPG");
}, 1);

Clazz.newMeth(C$, 'write$javax_imageio_metadata_IIOMetadata$javax_imageio_IIOImage$javax_imageio_ImageWriteParam', function (streamMetadata, image, param) {
this.setMetaData$O(streamMetadata);
if (param == null ) param=this.getDefaultWriteParam$();
this.params.put$TK$TV("qualityJPG", Integer.valueOf$I(((param.getCompressionQuality$() * 100)|0)));
this.write$javax_imageio_IIOImage(image);
});

Clazz.newMeth(C$, 'getDefaultWriteParam$', function () {
return Clazz.new_($I$(1).c$$java_util_Locale,[null]);
});
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 21:47:54 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
