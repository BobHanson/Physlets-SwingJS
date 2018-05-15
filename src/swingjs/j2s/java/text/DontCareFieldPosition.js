(function(){var P$=Clazz.newPackage("java.text"),I$=[['java.text.DontCareFieldPosition$1']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DontCareFieldPosition", null, 'java.text.FieldPosition');
C$.INSTANCE = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.INSTANCE = Clazz.new_(C$);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.noDelegate = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.noDelegate = ((
(function(){var C$=Clazz.newClass(P$, "DontCareFieldPosition$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, [['java.text.Format','java.text.Format.FieldDelegate']], 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'formatted$java_text_Format_Field$O$I$I$StringBuffer', function (attr, value, start, end, buffer) {
});

Clazz.newMeth(C$, 'formatted$I$java_text_Format_Field$O$I$I$StringBuffer', function (fieldID, attr, value, start, end, buffer) {
});
})()
), Clazz.new_((I$[1]||$incl$(1)).$init$, [this, null]));
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$I.apply(this, [0]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getFieldDelegate', function () {
return this.noDelegate;
});
})();
//Created 2018-05-15 01:02:10
