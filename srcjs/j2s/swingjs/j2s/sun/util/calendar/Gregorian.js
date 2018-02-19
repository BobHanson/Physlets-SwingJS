(function(){var P$=Clazz.newPackage("sun.util.calendar"),I$=[[['sun.util.calendar.Gregorian','.Date']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Gregorian", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'sun.util.calendar.BaseCalendar');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'getName', function () {
return "gregorian";
});

Clazz.newMeth(C$, 'getCalendarDate', function () {
return this.getCalendarDate$J$sun_util_calendar_CalendarDate(System.currentTimeMillis(), this.newCalendarDate());
});

Clazz.newMeth(C$, 'getCalendarDate$J', function (millis) {
return this.getCalendarDate$J$sun_util_calendar_CalendarDate(millis, this.newCalendarDate());
});

Clazz.newMeth(C$, 'getCalendarDate$J$sun_util_calendar_CalendarDate', function (millis, date) {
return C$.superclazz.prototype.getCalendarDate$J$sun_util_calendar_CalendarDate.apply(this, [millis, date]);
});

Clazz.newMeth(C$, 'getCalendarDate$J$java_util_TimeZone', function (millis, zone) {
return this.getCalendarDate$J$sun_util_calendar_CalendarDate(millis, this.newCalendarDate$java_util_TimeZone(zone));
});

Clazz.newMeth(C$, 'newCalendarDate', function () {
return Clazz.new_((I$[1]||$incl$(1)));
});

Clazz.newMeth(C$, 'newCalendarDate$java_util_TimeZone', function (zone) {
return Clazz.new_((I$[1]||$incl$(1)).c$$java_util_TimeZone,[zone]);
});
;
(function(){var C$=Clazz.newClass(P$.Gregorian, "Date", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, ['sun.util.calendar.BaseCalendar','sun.util.calendar.BaseCalendar.Date']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_TimeZone', function (zone) {
C$.superclazz.c$$java_util_TimeZone.apply(this, [zone]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getNormalizedYear', function () {
return this.getYear();
});

Clazz.newMeth(C$, 'setNormalizedYear$I', function (normalizedYear) {
this.setYear$I(normalizedYear);
});
})()
})();
//Created 2018-02-08 10:03:14
