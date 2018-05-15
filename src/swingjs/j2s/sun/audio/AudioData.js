(function(){var P$=Clazz.newPackage("sun.audio"),I$=[['javax.sound.sampled.AudioFormat',['javax.sound.sampled.AudioFormat','.Encoding'],'javax.sound.sampled.AudioSystem','java.io.ByteArrayInputStream']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AudioData");
C$.DEFAULT_FORMAT = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.DEFAULT_FORMAT = Clazz.new_((I$[1]||$incl$(1)).c$$javax_sound_sampled_AudioFormat_Encoding$F$I$I$I$F$Z,[(I$[2]||$incl$(2)).ULAW, 8000, 8, 1, 1, 8000, true]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.format = null;
this.buffer = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$BA', function (buffer) {
C$.$init$.apply(this);
this.buffer = buffer;
this.format = C$.DEFAULT_FORMAT;
try {
var ais = (I$[3]||$incl$(3)).getAudioInputStream$java_io_ByteArrayInputStream(Clazz.new_((I$[4]||$incl$(4)).c$$BA,[buffer]));
this.format = ais.getFormat();
ais.close();
} catch (e$$) {
if (Clazz.exceptionOf(e$$, "java.io.IOException")){
var e = e$$;
{
}
} else if (Clazz.exceptionOf(e$$, "javax.sound.sampled.UnsupportedAudioFileException")){
var e1 = e$$;
{
}
} else {
throw e$$;
}
}
}, 1);

Clazz.newMeth(C$, 'c$$javax_sound_sampled_AudioFormat$BA', function (format, buffer) {
C$.$init$.apply(this);
this.format = format;
this.buffer = buffer;
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:04
