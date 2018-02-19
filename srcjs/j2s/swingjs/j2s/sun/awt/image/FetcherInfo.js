(function(){var P$=Clazz.newPackage("sun.awt.image"),I$=[['sun.awt.image.FetcherInfo','Thread','sun.awt.AppContext','java.lang.StringBuffer','java.lang.Thread','java.util.Vector']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FetcherInfo");
var p$=C$.prototype;
C$.FETCHER_INFO_KEY = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.FETCHER_INFO_KEY = Clazz.new_((I$[4]||$incl$(4)).c$$S,["FetcherInfo"]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.fetchers = null;
this.numFetchers = 0;
this.numWaiting = 0;
this.waitList = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.fetchers = Clazz.array((I$[5]||$incl$(5)), [4]);
this.numFetchers = 0;
this.numWaiting = 0;
this.waitList = Clazz.new_((I$[6]||$incl$(6)));
}, 1);

Clazz.newMeth(C$, 'getFetcherInfo', function () {
var appContext = (I$[3]||$incl$(3)).getAppContext();
{
var info = appContext.get$O(C$.FETCHER_INFO_KEY);
if (info == null ) {
info = Clazz.new_(C$);
appContext.put$O$O(C$.FETCHER_INFO_KEY, info);
}return info;
}}, 1);
})();
//Created 2018-02-08 10:03:11
