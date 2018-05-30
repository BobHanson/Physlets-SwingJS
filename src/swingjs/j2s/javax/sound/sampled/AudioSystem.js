(function(){var P$=Clazz.newPackage("javax.sound.sampled"),I$=[['swingjs.JSToolkit','swingjs.JSAudio','javax.sound.sampled.AudioFileFormat',['javax.sound.sampled.AudioFileFormat','.Type'],'java.io.ByteArrayInputStream','javajs.util.Rdr','swingjs.JSUtil']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AudioSystem");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getLine$javax_sound_sampled_Line_Info', function (info) {
var line = (I$[1]||$incl$(1)).getAudioLine$javax_sound_sampled_Line_Info(info);
if (line != null ) return line;
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["No line matching " + info.toString() + " is supported." ]);
}, 1);

Clazz.newMeth(C$, 'getAudioInputStream$java_io_ByteArrayInputStream', function (stream) {
return (I$[2]||$incl$(2)).getAudioInputStream$java_io_ByteArrayInputStream(stream);
}, 1);

Clazz.newMeth(C$, 'getAudioFileFormat$java_net_URL', function (url) {
var ais = C$.getAudioInputStream$java_net_URL(url);
var format = (ais == null  ? null : ais.getFormat());
if (format == null ) throw Clazz.new_(Clazz.load('javax.sound.sampled.UnsupportedAudioFileException').c$$S,["file is not a supported file type"]);
return Clazz.new_((I$[3]||$incl$(3)).c$$javax_sound_sampled_AudioFileFormat_Type$javax_sound_sampled_AudioFormat$I,[(I$[4]||$incl$(4)).getType$javax_sound_sampled_AudioFormat(format), format, -1]);
}, 1);

Clazz.newMeth(C$, 'getAudioInputStream$java_io_InputStream', function (stream) {
if (Clazz.instanceOf(stream, "java.io.ByteArrayInputStream")) return C$.getAudioInputStream$java_io_ByteArrayInputStream(stream);
return C$.getAudioInputStream$java_io_ByteArrayInputStream(Clazz.new_((I$[5]||$incl$(5)).c$$BA,[(I$[6]||$incl$(6)).getLimitedStreamBytes$java_io_InputStream$J(stream, -1)]));
}, 1);

Clazz.newMeth(C$, 'getAudioInputStream$java_net_URL', function (url) {
return C$.getAudioInputStream$java_io_InputStream((I$[7]||$incl$(7)).getURLInputStream$java_net_URL$Z(url, false));
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:46:02
