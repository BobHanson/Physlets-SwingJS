(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[['javajs.util.Lst',['javax.swing.text.SegmentCache','.CachedSegment']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SegmentCache", function(){
Clazz.newInstance(this, arguments,0,C$);
});
C$.sharedCache = null;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.segments = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getSharedInstance', function () {
if (C$.sharedCache == null ) C$.sharedCache = Clazz.new_(C$);
return C$.sharedCache;
}, 1);

Clazz.newMeth(C$, 'getSharedSegment', function () {
return C$.getSharedInstance().getSegment();
}, 1);

Clazz.newMeth(C$, 'releaseSharedSegment$javax_swing_text_Segment', function (segment) {
C$.getSharedInstance().releaseSegment$javax_swing_text_Segment(segment);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.segments = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'getSegment', function () {
{
var size = this.segments.size();
if (size > 0) {
return this.segments.removeItemAt$I(size - 1);
}}return Clazz.new_((I$[2]||$incl$(2)));
});

Clazz.newMeth(C$, 'releaseSegment$javax_swing_text_Segment', function (segment) {
if (Clazz.instanceOf(segment, "javax.swing.text.SegmentCache.CachedSegment")) {
{
segment.array = null;
segment.count = 0;
this.segments.add$TE(segment);
}}});
;
(function(){var C$=Clazz.newClass(P$.SegmentCache, "CachedSegment", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.Segment');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-15 01:02:57
