(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['java.io.StringReader']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "StringDataReader", null, 'javajs.util.DataReader');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (data) {
C$.superclazz.c$$java_io_Reader.apply(this, [Clazz.new_((I$[1]||$incl$(1)).c$$S,[data])]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'setData$O', function (data) {
return Clazz.new_(C$.c$$S,[data]);
});
})();
//Created 2018-02-08 10:02:20
