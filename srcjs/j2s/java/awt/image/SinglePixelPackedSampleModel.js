(function(){var P$=Clazz.newPackage("java.awt.image"),I$=[['java.awt.image.DataBufferByte','java.awt.image.DataBufferInt','java.util.Arrays']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SinglePixelPackedSampleModel", null, 'java.awt.image.SampleModel');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.bitMasks = null;
this.bitOffsets = null;
this.bitSizes = null;
this.maxBitSize = 0;
this.scanlineStride = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$I$I$IA', function (dataType, w, h, bitMasks) {
C$.c$$I$I$I$I$IA.apply(this, [dataType, w, h, w, bitMasks]);
if (dataType != 0 && dataType != 3 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Unsupported data type " + dataType]);
}}, 1);

Clazz.newMeth(C$, 'c$$I$I$I$I$IA', function (dataType, w, h, scanlineStride, bitMasks) {
C$.superclazz.c$$I$I$I$I.apply(this, [dataType, w, h, bitMasks.length]);
C$.$init$.apply(this);
if (dataType != 0 && dataType != 3 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Unsupported data type " + dataType]);
}this.dataType = dataType;
this.bitMasks = bitMasks.clone();
this.scanlineStride = scanlineStride;
this.bitOffsets = Clazz.array(Integer.TYPE, [this.numBands]);
this.bitSizes = Clazz.array(Integer.TYPE, [this.numBands]);
this.maxBitSize = 0;
for (var i = 0; i < this.numBands; i++) {
var bitOffset = 0;
var bitSize = 0;
var mask;
mask = bitMasks[i];
if (mask != 0) {
while ((mask & 1) == 0){
mask = mask >>> 1;
bitOffset++;
}
while ((mask & 1) == 1){
mask = mask >>> 1;
bitSize++;
}
if (mask != 0) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Mask " + bitMasks[i] + " must be contiguous" ]);
}}this.bitOffsets[i] = bitOffset;
this.bitSizes[i] = bitSize;
if (bitSize > this.maxBitSize) {
this.maxBitSize = bitSize;
}}
}, 1);

Clazz.newMeth(C$, 'getNumDataElements', function () {
return 1;
});

Clazz.newMeth(C$, 'getBufferSize', function () {
var size = this.scanlineStride * (this.height - 1) + this.width;
return size;
});

Clazz.newMeth(C$, 'createCompatibleSampleModel$I$I', function (w, h) {
var sampleModel = Clazz.new_(C$.c$$I$I$I$IA,[this.dataType, w, h, this.bitMasks]);
return sampleModel;
});

Clazz.newMeth(C$, 'createDataBuffer', function () {
var dataBuffer = null;
var size = (p$.getBufferSize.apply(this, [])|0);
switch (this.dataType) {
case 0:
dataBuffer = Clazz.new_((I$[1]||$incl$(1)).c$$I,[size]);
break;
case 3:
dataBuffer = Clazz.new_((I$[2]||$incl$(2)).c$$I,[size]);
break;
}
return dataBuffer;
});

Clazz.newMeth(C$, 'getSampleSize', function () {
var mask;
var sampleSize = Clazz.array(Integer.TYPE, [this.numBands]);
for (var i = 0; i < this.numBands; i++) {
sampleSize[i] = 0;
mask = this.bitMasks[i] >>> this.bitOffsets[i];
while ((mask & 1) != 0){
sampleSize[i]++;
mask = mask >>> 1;
}
}
return sampleSize;
});

Clazz.newMeth(C$, 'getSampleSize$I', function (band) {
var sampleSize = 0;
var mask = this.bitMasks[band] >>> this.bitOffsets[band];
while ((mask & 1) != 0){
sampleSize++;
mask = mask >>> 1;
}
return sampleSize;
});

Clazz.newMeth(C$, 'getOffset$I$I', function (x, y) {
var offset = y * this.scanlineStride + x;
return offset;
});

Clazz.newMeth(C$, 'getBitOffsets', function () {
return this.bitOffsets.clone();
});

Clazz.newMeth(C$, 'getBitMasks', function () {
return this.bitMasks.clone();
});

Clazz.newMeth(C$, 'getScanlineStride', function () {
return this.scanlineStride;
});

Clazz.newMeth(C$, 'createSubsetSampleModel$IA', function (bands) {
if (bands.length > this.numBands) throw Clazz.new_(Clazz.load('java.awt.image.RasterFormatException').c$$S,["There are only " + this.numBands + " bands" ]);
var newBitMasks = Clazz.array(Integer.TYPE, [bands.length]);
for (var i = 0; i < bands.length; i++) newBitMasks[i] = this.bitMasks[bands[i]];

return Clazz.new_(C$.c$$I$I$I$I$IA,[this.dataType, this.width, this.height, this.scanlineStride, newBitMasks]);
});

Clazz.newMeth(C$, 'getDataElements$I$I$O$java_awt_image_DataBuffer', function (x, y, obj, data) {
if ((x < 0) || (y < 0) || (x >= this.width) || (y >= this.height)  ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,["Coordinate out of bounds!"]);
}var type = this.getTransferType();
switch (type) {
case 0:
var bdata;
if (obj == null ) bdata = Clazz.array(Byte.TYPE, [1]);
 else bdata = obj;
bdata[0] = ((data.getElem$I(y * this.scanlineStride + x)|0)|0);
obj = bdata;
break;
case 3:
var idata;
if (obj == null ) idata = Clazz.array(Integer.TYPE, [1]);
 else idata = obj;
idata[0] = data.getElem$I(y * this.scanlineStride + x);
obj = idata;
break;
}
return obj;
});

Clazz.newMeth(C$, 'getPixel$I$I$IA$java_awt_image_DataBuffer', function (x, y, iArray, data) {
if ((x < 0) || (y < 0) || (x >= this.width) || (y >= this.height)  ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,["Coordinate out of bounds!"]);
}var pixels;
if (iArray == null ) {
pixels = Clazz.array(Integer.TYPE, [this.numBands]);
} else {
pixels = iArray;
}var value = data.getElem$I(y * this.scanlineStride + x);
for (var i = 0; i < this.numBands; i++) {
pixels[i] = (value & this.bitMasks[i]) >>> this.bitOffsets[i];
}
return pixels;
});

Clazz.newMeth(C$, 'getPixels$I$I$I$I$IA$java_awt_image_DataBuffer', function (x, y, w, h, iArray, data) {
if ((x < 0) || (y < 0) || (x + w > this.width) || (y + h > this.height)  ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,["Coordinate out of bounds!"]);
}var pixels;
if (iArray != null ) {
pixels = iArray;
} else {
pixels = Clazz.array(Integer.TYPE, [w * h * this.numBands ]);
}var lineOffset = y * this.scanlineStride + x;
var dstOffset = 0;
for (var i = 0; i < h; i++) {
for (var j = 0; j < w; j++) {
var value = data.getElem$I(lineOffset + j);
for (var k = 0; k < this.numBands; k++) {
pixels[dstOffset++] = ((value & this.bitMasks[k]) >>> this.bitOffsets[k]);
}
}
lineOffset = lineOffset+(this.scanlineStride);
}
return pixels;
});

Clazz.newMeth(C$, 'getSample$I$I$I$java_awt_image_DataBuffer', function (x, y, b, data) {
if ((x < 0) || (y < 0) || (x >= this.width) || (y >= this.height)  ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,["Coordinate out of bounds!"]);
}var sample = data.getElem$I(y * this.scanlineStride + x);
return ((sample & this.bitMasks[b]) >>> this.bitOffsets[b]);
});

Clazz.newMeth(C$, 'getSamples$I$I$I$I$I$IA$java_awt_image_DataBuffer', function (x, y, w, h, b, iArray, data) {
if ((x < 0) || (y < 0) || (x + w > this.width) || (y + h > this.height)  ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,["Coordinate out of bounds!"]);
}var samples;
if (iArray != null ) {
samples = iArray;
} else {
samples = Clazz.array(Integer.TYPE, [w * h]);
}var lineOffset = y * this.scanlineStride + x;
var dstOffset = 0;
for (var i = 0; i < h; i++) {
for (var j = 0; j < w; j++) {
var value = data.getElem$I(lineOffset + j);
samples[dstOffset++] = ((value & this.bitMasks[b]) >>> this.bitOffsets[b]);
}
lineOffset = lineOffset+(this.scanlineStride);
}
return samples;
});

Clazz.newMeth(C$, 'setDataElements$I$I$O$java_awt_image_DataBuffer', function (x, y, obj, data) {
if ((x < 0) || (y < 0) || (x >= this.width) || (y >= this.height)  ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,["Coordinate out of bounds!"]);
}var type = this.getTransferType();
switch (type) {
case 0:
var barray = obj;
data.setElem$I$I(y * this.scanlineStride + x, ((barray[0]|0)) & 255);
break;
case 3:
var iarray = obj;
data.setElem$I$I(y * this.scanlineStride + x, iarray[0]);
break;
}
});

Clazz.newMeth(C$, 'setPixel$I$I$IA$java_awt_image_DataBuffer', function (x, y, iArray, data) {
if ((x < 0) || (y < 0) || (x >= this.width) || (y >= this.height)  ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,["Coordinate out of bounds!"]);
}var lineOffset = y * this.scanlineStride + x;
var value = data.getElem$I(lineOffset);
for (var i = 0; i < this.numBands; i++) {
value = value&(~this.bitMasks[i]);
value = value|(((iArray[i] << this.bitOffsets[i]) & this.bitMasks[i]));
}
data.setElem$I$I(lineOffset, value);
});

Clazz.newMeth(C$, 'setPixels$I$I$I$I$IA$java_awt_image_DataBuffer', function (x, y, w, h, iArray, data) {
if ((x < 0) || (y < 0) || (x + w > this.width) || (y + h > this.height)  ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,["Coordinate out of bounds!"]);
}var lineOffset = y * this.scanlineStride + x;
var srcOffset = 0;
for (var i = 0; i < h; i++) {
for (var j = 0; j < w; j++) {
var value = data.getElem$I(lineOffset + j);
for (var k = 0; k < this.numBands; k++) {
value = value&(~this.bitMasks[k]);
var srcValue = iArray[srcOffset++];
value = value|(((srcValue << this.bitOffsets[k]) & this.bitMasks[k]));
}
data.setElem$I$I(lineOffset + j, value);
}
lineOffset = lineOffset+(this.scanlineStride);
}
});

Clazz.newMeth(C$, 'setSample$I$I$I$I$java_awt_image_DataBuffer', function (x, y, b, s, data) {
if ((x < 0) || (y < 0) || (x >= this.width) || (y >= this.height)  ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,["Coordinate out of bounds!"]);
}var value = data.getElem$I(y * this.scanlineStride + x);
value = value&(~this.bitMasks[b]);
value = value|((s << this.bitOffsets[b]) & this.bitMasks[b]);
data.setElem$I$I(y * this.scanlineStride + x, value);
});

Clazz.newMeth(C$, 'setSamples$I$I$I$I$I$IA$java_awt_image_DataBuffer', function (x, y, w, h, b, iArray, data) {
if ((x < 0) || (y < 0) || (x + w > this.width) || (y + h > this.height)  ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,["Coordinate out of bounds!"]);
}var lineOffset = y * this.scanlineStride + x;
var srcOffset = 0;
for (var i = 0; i < h; i++) {
for (var j = 0; j < w; j++) {
var value = data.getElem$I(lineOffset + j);
value = value&(~this.bitMasks[b]);
var sample = iArray[srcOffset++];
value = value|((sample << this.bitOffsets[b]) & this.bitMasks[b]);
data.setElem$I$I(lineOffset + j, value);
}
lineOffset = lineOffset+(this.scanlineStride);
}
});

Clazz.newMeth(C$, 'equals$O', function (o) {
if ((o == null ) || !(Clazz.instanceOf(o, "java.awt.image.SinglePixelPackedSampleModel")) ) {
return false;
}var that = o;
return this.width == that.width && this.height == that.height  && this.numBands == that.numBands  && this.dataType == that.dataType  && (I$[3]||$incl$(3)).equals$IA$IA(this.bitMasks, that.bitMasks)  && (I$[3]||$incl$(3)).equals$IA$IA(this.bitOffsets, that.bitOffsets)  && (I$[3]||$incl$(3)).equals$IA$IA(this.bitSizes, that.bitSizes)  && this.maxBitSize == that.maxBitSize  && this.scanlineStride == that.scanlineStride ;
});

Clazz.newMeth(C$, 'hashCode', function () {
var hash = 0;
hash = this.width;
hash = hash<<(8);
hash = hash^(this.height);
hash = hash<<(8);
hash = hash^(this.numBands);
hash = hash<<(8);
hash = hash^(this.dataType);
hash = hash<<(8);
for (var i = 0; i < this.bitMasks.length; i++) {
hash = hash^(this.bitMasks[i]);
hash = hash<<(8);
}
for (var i = 0; i < this.bitOffsets.length; i++) {
hash = hash^(this.bitOffsets[i]);
hash = hash<<(8);
}
for (var i = 0; i < this.bitSizes.length; i++) {
hash = hash^(this.bitSizes[i]);
hash = hash<<(8);
}
hash = hash^(this.maxBitSize);
hash = hash<<(8);
hash = hash^(this.scanlineStride);
return hash;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:24
