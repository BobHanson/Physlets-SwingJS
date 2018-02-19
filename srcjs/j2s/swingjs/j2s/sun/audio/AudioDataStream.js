(function(){var P$=Clazz.newPackage("sun.audio"),I$=[];
var C$=Clazz.newClass(P$, "AudioDataStream", null, 'java.io.ByteArrayInputStream');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.ad = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$sun_audio_AudioData', function (data) {
C$.superclazz.c$$BA.apply(this, [data.buffer]);
C$.$init$.apply(this);
this.ad = data;
}, 1);

Clazz.newMeth(C$, 'getAudioData', function () {
return this.ad;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:07
