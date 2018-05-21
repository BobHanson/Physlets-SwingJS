(function(){var P$=Clazz.newPackage("sun.audio"),I$=[['swingjs.JSToolkit']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AudioPlayer", null, 'javajs.util.JSThread');
C$.player = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.player = C$.getAudioPlayer();
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getAudioPlayer', function () {
return Clazz.new_(C$);
}, 1);

Clazz.newMeth(C$, 'myInit', function () {
return false;
});

Clazz.newMeth(C$, 'isLooping', function () {
return false;
});

Clazz.newMeth(C$, 'myLoop', function () {
return false;
});

Clazz.newMeth(C$, 'whenDone', function () {
});

Clazz.newMeth(C$, 'getDelayMillis', function () {
return 0;
});

Clazz.newMeth(C$, 'onException$Exception', function (e) {
});

Clazz.newMeth(C$, 'doFinally', function () {
});

Clazz.newMeth(C$, 'start$java_io_InputStream', function (is) {
var ads = is;
try {
(I$[1]||$incl$(1)).playAudio$BA$javax_sound_sampled_AudioFormat(ads.getAudioData().buffer, ads.getAudioData().format);
} catch (e) {
if (Clazz.exceptionOf(e, "javax.sound.sampled.UnsupportedAudioFileException")){
e.printStackTrace();
} else {
throw e;
}
}
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:04
