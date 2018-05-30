(function(){var P$=Clazz.newPackage("javax.imageio"),I$=[['java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ImageWriteParam", null, 'javax.imageio.IIOParam');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$canWriteTiles = false;
this.tilingMode = 0;
this.preferredTileSizes = null;
this.tilingSet = false;
this.tileWidth = 0;
this.tileHeight = 0;
this.$canOffsetTiles = false;
this.tileGridXOffset = 0;
this.tileGridYOffset = 0;
this.$canWriteProgressive = false;
this.progressiveMode = 0;
this.$canWriteCompressed = false;
this.compressionMode = 0;
this.compressionTypes = null;
this.compressionType = null;
this.compressionQuality = 0;
this.locale = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$canWriteTiles = false;
this.tilingMode = 3;
this.preferredTileSizes = null;
this.tilingSet = false;
this.tileWidth = 0;
this.tileHeight = 0;
this.$canOffsetTiles = false;
this.tileGridXOffset = 0;
this.tileGridYOffset = 0;
this.$canWriteProgressive = false;
this.progressiveMode = 3;
this.$canWriteCompressed = false;
this.compressionMode = 3;
this.compressionTypes = null;
this.compressionType = null;
this.compressionQuality = 1.0;
this.locale = null;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Locale', function (locale) {
Clazz.super_(C$, this,1);
this.locale=locale;
}, 1);

Clazz.newMeth(C$, 'clonePreferredTileSizes$java_awt_DimensionA', function (sizes) {
if (sizes == null ) {
return null;
}var temp = Clazz.array((I$[1]||$incl$(1)), [sizes.length]);
for (var i = 0; i < sizes.length; i++) {
temp[i]=Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Dimension,[sizes[i]]);
}
return temp;
}, 1);

Clazz.newMeth(C$, 'getLocale', function () {
return this.locale;
});

Clazz.newMeth(C$, 'canWriteTiles', function () {
return this.$canWriteTiles;
});

Clazz.newMeth(C$, 'canOffsetTiles', function () {
return this.$canOffsetTiles;
});

Clazz.newMeth(C$, 'setTilingMode$I', function (mode) {
if (this.canWriteTiles() == false ) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Tiling not supported!"]);
}if (mode < 0 || mode > 3 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Illegal value for mode!"]);
}this.tilingMode=mode;
if (mode == 2) {
this.unsetTiling();
}});

Clazz.newMeth(C$, 'getTilingMode', function () {
if (!this.canWriteTiles()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Tiling not supported"]);
}return this.tilingMode;
});

Clazz.newMeth(C$, 'getPreferredTileSizes', function () {
if (!this.canWriteTiles()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Tiling not supported"]);
}return C$.clonePreferredTileSizes$java_awt_DimensionA(this.preferredTileSizes);
});

Clazz.newMeth(C$, 'setTiling$I$I$I$I', function (tileWidth, tileHeight, tileGridXOffset, tileGridYOffset) {
if (!this.canWriteTiles()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Tiling not supported!"]);
}if (this.getTilingMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Tiling mode not MODE_EXPLICIT!"]);
}if (tileWidth <= 0 || tileHeight <= 0 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["tile dimensions are non-positive!"]);
}var tilesOffset = (tileGridXOffset != 0) || (tileGridYOffset != 0) ;
if (!this.canOffsetTiles() && tilesOffset ) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Can\'t offset tiles!"]);
}if (this.preferredTileSizes != null ) {
var ok = true;
for (var i = 0; i < this.preferredTileSizes.length; i+=2) {
var min = this.preferredTileSizes[i];
var max = this.preferredTileSizes[i + 1];
if ((tileWidth < min.width) || (tileWidth > max.width) || (tileHeight < min.height) || (tileHeight > max.height)  ) {
ok=false;
break;
}}
if (!ok) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Illegal tile size!"]);
}}this.tilingSet=true;
this.tileWidth=tileWidth;
this.tileHeight=tileHeight;
this.tileGridXOffset=tileGridXOffset;
this.tileGridYOffset=tileGridYOffset;
});

Clazz.newMeth(C$, 'unsetTiling', function () {
if (!this.canWriteTiles()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Tiling not supported!"]);
}if (this.getTilingMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Tiling mode not MODE_EXPLICIT!"]);
}this.tilingSet=false;
this.tileWidth=0;
this.tileHeight=0;
this.tileGridXOffset=0;
this.tileGridYOffset=0;
});

Clazz.newMeth(C$, 'getTileWidth', function () {
if (!this.canWriteTiles()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Tiling not supported!"]);
}if (this.getTilingMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Tiling mode not MODE_EXPLICIT!"]);
}if (!this.tilingSet) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Tiling parameters not set!"]);
}return this.tileWidth;
});

Clazz.newMeth(C$, 'getTileHeight', function () {
if (!this.canWriteTiles()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Tiling not supported!"]);
}if (this.getTilingMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Tiling mode not MODE_EXPLICIT!"]);
}if (!this.tilingSet) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Tiling parameters not set!"]);
}return this.tileHeight;
});

Clazz.newMeth(C$, 'getTileGridXOffset', function () {
if (!this.canWriteTiles()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Tiling not supported!"]);
}if (this.getTilingMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Tiling mode not MODE_EXPLICIT!"]);
}if (!this.tilingSet) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Tiling parameters not set!"]);
}return this.tileGridXOffset;
});

Clazz.newMeth(C$, 'getTileGridYOffset', function () {
if (!this.canWriteTiles()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Tiling not supported!"]);
}if (this.getTilingMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Tiling mode not MODE_EXPLICIT!"]);
}if (!this.tilingSet) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Tiling parameters not set!"]);
}return this.tileGridYOffset;
});

Clazz.newMeth(C$, 'canWriteProgressive', function () {
return this.$canWriteProgressive;
});

Clazz.newMeth(C$, 'setProgressiveMode$I', function (mode) {
if (!this.canWriteProgressive()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Progressive output not supported"]);
}if (mode < 0 || mode > 3 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Illegal value for mode!"]);
}if (mode == 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["MODE_EXPLICIT not supported for progressive output"]);
}this.progressiveMode=mode;
});

Clazz.newMeth(C$, 'getProgressiveMode', function () {
if (!this.canWriteProgressive()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Progressive output not supported"]);
}return this.progressiveMode;
});

Clazz.newMeth(C$, 'canWriteCompressed', function () {
return this.$canWriteCompressed;
});

Clazz.newMeth(C$, 'setCompressionMode$I', function (mode) {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported."]);
}if (mode < 0 || mode > 3 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Illegal value for mode!"]);
}this.compressionMode=mode;
if (mode == 2) {
this.unsetCompression();
}});

Clazz.newMeth(C$, 'getCompressionMode', function () {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported."]);
}return this.compressionMode;
});

Clazz.newMeth(C$, 'getCompressionTypes', function () {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported"]);
}if (this.compressionTypes == null ) {
return null;
}return this.compressionTypes.clone();
});

Clazz.newMeth(C$, 'setCompressionType$S', function (compressionType) {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported"]);
}if (this.getCompressionMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Compression mode not MODE_EXPLICIT!"]);
}var legalTypes = this.getCompressionTypes();
if (legalTypes == null ) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["No settable compression types"]);
}if (compressionType != null ) {
var found = false;
if (legalTypes != null ) {
for (var i = 0; i < legalTypes.length; i++) {
if (compressionType.equals$O(legalTypes[i])) {
found=true;
break;
}}
}if (!found) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Unknown compression type!"]);
}}this.compressionType=compressionType;
});

Clazz.newMeth(C$, 'getCompressionType', function () {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported."]);
}if (this.getCompressionMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Compression mode not MODE_EXPLICIT!"]);
}return this.compressionType;
});

Clazz.newMeth(C$, 'unsetCompression', function () {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported"]);
}if (this.getCompressionMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Compression mode not MODE_EXPLICIT!"]);
}this.compressionType=null;
this.compressionQuality=1.0;
});

Clazz.newMeth(C$, 'getLocalizedCompressionTypeName', function () {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported."]);
}if (this.getCompressionMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Compression mode not MODE_EXPLICIT!"]);
}if (this.getCompressionType() == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["No compression type set!"]);
}return this.getCompressionType();
});

Clazz.newMeth(C$, 'isCompressionLossless', function () {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported"]);
}if (this.getCompressionMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Compression mode not MODE_EXPLICIT!"]);
}if ((this.getCompressionTypes() != null ) && (this.getCompressionType() == null ) ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["No compression type set!"]);
}return true;
});

Clazz.newMeth(C$, 'setCompressionQuality$F', function (quality) {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported"]);
}if (this.getCompressionMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Compression mode not MODE_EXPLICIT!"]);
}if (this.getCompressionTypes() != null  && this.getCompressionType() == null  ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["No compression type set!"]);
}if (quality < 0.0  || quality > 1.0  ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Quality out-of-bounds!"]);
}this.compressionQuality=quality;
});

Clazz.newMeth(C$, 'getCompressionQuality', function () {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported."]);
}if (this.getCompressionMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Compression mode not MODE_EXPLICIT!"]);
}if ((this.getCompressionTypes() != null ) && (this.getCompressionType() == null ) ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["No compression type set!"]);
}return this.compressionQuality;
});

Clazz.newMeth(C$, 'getBitRate$F', function (quality) {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported."]);
}if (this.getCompressionMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Compression mode not MODE_EXPLICIT!"]);
}if ((this.getCompressionTypes() != null ) && (this.getCompressionType() == null ) ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["No compression type set!"]);
}if (quality < 0.0  || quality > 1.0  ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Quality out-of-bounds!"]);
}return -1.0;
});

Clazz.newMeth(C$, 'getCompressionQualityDescriptions', function () {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported."]);
}if (this.getCompressionMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Compression mode not MODE_EXPLICIT!"]);
}if ((this.getCompressionTypes() != null ) && (this.getCompressionType() == null ) ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["No compression type set!"]);
}return null;
});

Clazz.newMeth(C$, 'getCompressionQualityValues', function () {
if (!this.canWriteCompressed()) {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException').c$$S,["Compression not supported."]);
}if (this.getCompressionMode() != 2) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Compression mode not MODE_EXPLICIT!"]);
}if ((this.getCompressionTypes() != null ) && (this.getCompressionType() == null ) ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["No compression type set!"]);
}return null;
});
})();
//Created 2018-05-24 08:45:59
