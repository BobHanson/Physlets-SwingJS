(function(){var P$=Clazz.newPackage("javax.sound.sampled"),I$=[['javax.sound.sampled.Line']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newInterface(P$, "Line", function(){
});
;
(function(){var C$=Clazz.newClass(P$.Line, "Info", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.lineClass = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$Class', function (lineClass) {
C$.$init$.apply(this);
if (lineClass == null ) {
this.lineClass=Clazz.getClass((I$[1]||$incl$(1)),['addLineListener$javax_sound_sampled_LineListener','close','getControl$javax_sound_sampled_Control_Type','getControls','getLineInfo','isControlSupported$javax_sound_sampled_Control_Type','isOpen','open','removeLineListener$javax_sound_sampled_LineListener']);
} else {
this.lineClass=lineClass;
}}, 1);

Clazz.newMeth(C$, 'getLineClass', function () {
return this.lineClass;
});

Clazz.newMeth(C$, 'matches$javax_sound_sampled_Line_Info', function (info) {
if (!(this.getClass().isInstance$O(info))) {
return false;
}if (!(this.getLineClass().isAssignableFrom$Class(info.getLineClass()))) {
return false;
}return true;
});

Clazz.newMeth(C$, 'toString', function () {
var fullPackagePath = "javax.sound.sampled.";
var initialString =  String.instantialize(this.getLineClass().toString());
var finalString;
var index = initialString.indexOf(fullPackagePath);
if (index != -1) {
finalString=initialString.substring(0, index) + initialString.substring((index + fullPackagePath.length$()), initialString.length$());
} else {
finalString=initialString;
}return finalString;
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-24 08:46:02
