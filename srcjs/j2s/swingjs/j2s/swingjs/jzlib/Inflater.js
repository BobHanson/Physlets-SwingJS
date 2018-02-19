(function(){var P$=Clazz.newPackage("swingjs.jzlib"),I$=[['swingjs.jzlib.Inflate']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Inflater", null, 'swingjs.jzlib.ZStream');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'init$I$Z', function (w, nowrap) {
this.setAdler32();
if (w == 0) w = 15;
this.istate = Clazz.new_((I$[1]||$incl$(1)).c$$swingjs_jzlib_ZStream,[this]);
this.istate.inflateInit$I(nowrap ? -w : w);
return this;
});

Clazz.newMeth(C$, 'inflate$I', function (f) {
if (this.istate == null ) return -2;
var ret = this.istate.inflate$I(f);
return ret;
});

Clazz.newMeth(C$, 'end', function () {
if (this.istate == null ) return -2;
var ret = this.istate.inflateEnd();
return ret;
});

Clazz.newMeth(C$, 'sync', function () {
if (this.istate == null ) return -2;
return this.istate.inflateSync();
});

Clazz.newMeth(C$, 'syncPoint', function () {
if (this.istate == null ) return -2;
return this.istate.inflateSyncPoint();
});

Clazz.newMeth(C$, 'setDictionary$BA$I', function (dictionary, dictLength) {
if (this.istate == null ) return -2;
return this.istate.inflateSetDictionary$BA$I(dictionary, dictLength);
});

Clazz.newMeth(C$, 'finished', function () {
return this.istate.mode == 12;
});

Clazz.newMeth(C$, 'reset', function () {
this.avail_in = 0;
if (this.istate != null ) this.istate.reset();
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:20
