(function(){var P$=Clazz.newPackage("javax.sound.sampled"),I$=[];
var C$=Clazz.newClass(P$, "Control", function(){
Clazz.newInstance(this, arguments,0,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.type = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$javax_sound_sampled_Control_Type', function (type) {
C$.$init$.apply(this);
this.type=type;
}, 1);

Clazz.newMeth(C$, 'getType', function () {
return this.type;
});

Clazz.newMeth(C$, 'toString', function () {
return  String.instantialize(this.getType() + " Control");
});
;
(function(){var C$=Clazz.newClass(P$.Control, "Type", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.name = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.$init$.apply(this);
this.name=name;
}, 1);

Clazz.newMeth(C$, 'equals$O', function (obj) {
return C$.superclazz.prototype.equals$O.apply(this, [obj]);
});

Clazz.newMeth(C$, 'hashCode', function () {
return C$.superclazz.prototype.hashCode.apply(this, []);
});

Clazz.newMeth(C$, 'toString', function () {
return this.name;
});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:46:02
