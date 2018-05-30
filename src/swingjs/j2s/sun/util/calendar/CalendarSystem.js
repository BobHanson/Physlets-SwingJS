(function(){var P$=Clazz.newPackage("sun.util.calendar"),I$=[['java.util.HashMap','java.lang.StringBuilder','swingjs.api.Interface']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "CalendarSystem");
C$.initialized = false;
C$.names = null;
C$.calendars = null;
C$.namePairs = null;
var p$=C$.prototype;
C$.GREGORIAN_INSTANCE = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.initialized = false;
C$.namePairs = Clazz.array(java.lang.String, -1, ["gregorian", "Gregorian", "japanese", "LocalGregorianCalendar", "julian", "JulianCalendar"]);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'initNames', function () {
var nameMap = Clazz.new_((I$[1]||$incl$(1)));
var clName = Clazz.new_((I$[2]||$incl$(2)));
for (var i = 0; i < C$.namePairs.length; i+=2) {
clName.setLength$I(0);
var cl = clName.append$S("sun.util.calendar.").append$S(C$.namePairs[i + 1]).toString();
nameMap.put$TK$TV(C$.namePairs[i], cl);
}
{
if (!C$.initialized) {
C$.names=nameMap;
C$.calendars=Clazz.new_((I$[1]||$incl$(1)));
C$.initialized=true;
}}}, 1);

Clazz.newMeth(C$, 'getGregorianCalendar', function () {
if (C$.GREGORIAN_INSTANCE == null ) C$.GREGORIAN_INSTANCE=(I$[3]||$incl$(3)).getInstance$S$Z("sun.util.calendar.Gregorian", false);
return C$.GREGORIAN_INSTANCE;
}, 1);

Clazz.newMeth(C$, 'forName$S', function (calendarName) {
if ("gregorian".equals$O(calendarName)) {
return C$.GREGORIAN_INSTANCE;
}if (!C$.initialized) {
C$.initNames();
}var cal = C$.calendars.get$O(calendarName);
if (cal != null ) {
return cal;
}var className = C$.names.get$O(calendarName);
if (className == null ) {
return null;
}try {
var cl = Clazz.forName(className);
cal=cl.newInstance();
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
throw Clazz.new_(Clazz.load('java.lang.RuntimeException').c$$S$Throwable,["internal error", e]);
} else {
throw e;
}
}
if (cal == null ) {
return null;
}var cs = C$.calendars.put$TK$TV(calendarName, cal);
return (cs == null ) ? cal : cs;
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:38
