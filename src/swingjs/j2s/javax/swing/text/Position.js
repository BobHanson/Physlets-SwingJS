(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[];
var C$=Clazz.newInterface(P$, "Position", function(){
});
;
(function(){var C$=Clazz.newClass(P$.Position, "Bias", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});
C$.Forward = null;
C$.Backward = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.Forward = Clazz.new_(C$.c$$S,["Forward"]);
C$.Backward = Clazz.new_(C$.c$$S,["Backward"]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.name = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'toString', function () {
return this.name;
});

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.$init$.apply(this);
this.name=name;
}, 1);

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-24 08:47:07
