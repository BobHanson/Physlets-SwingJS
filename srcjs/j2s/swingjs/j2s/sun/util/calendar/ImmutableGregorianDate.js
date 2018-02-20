(function(){var P$=Clazz.newPackage("sun.util.calendar"),I$=[];
var C$=Clazz.newClass(P$, "ImmutableGregorianDate", null, ['sun.util.calendar.BaseCalendar','sun.util.calendar.BaseCalendar.Date']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.date = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$sun_util_calendar_BaseCalendar_Date', function (date) {
Clazz.super_(C$, this,1);
if (date == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.date = date;
}, 1);

Clazz.newMeth(C$, 'getEra', function () {
return this.date.getEra();
});

Clazz.newMeth(C$, 'setEra$sun_util_calendar_Era', function (era) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'getYear', function () {
return this.date.getYear();
});

Clazz.newMeth(C$, 'setYear$I', function (year) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'addYear$I', function (n) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'isLeapYear', function () {
return this.date.isLeapYear();
});

Clazz.newMeth(C$, 'setLeapYear$Z', function (leapYear) {
p$.unsupported.apply(this, []);
});

Clazz.newMeth(C$, 'getMonth', function () {
return this.date.getMonth();
});

Clazz.newMeth(C$, 'setMonth$I', function (month) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'addMonth$I', function (n) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'getDayOfMonth', function () {
return this.date.getDayOfMonth();
});

Clazz.newMeth(C$, 'setDayOfMonth$I', function (date) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'addDayOfMonth$I', function (n) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'getDayOfWeek', function () {
return this.date.getDayOfWeek();
});

Clazz.newMeth(C$, 'getHours', function () {
return this.date.getHours();
});

Clazz.newMeth(C$, 'setHours$I', function (hours) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'addHours$I', function (n) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'getMinutes', function () {
return this.date.getMinutes();
});

Clazz.newMeth(C$, 'setMinutes$I', function (minutes) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'addMinutes$I', function (n) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'getSeconds', function () {
return this.date.getSeconds();
});

Clazz.newMeth(C$, 'setSeconds$I', function (seconds) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'addSeconds$I', function (n) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'getMillis', function () {
return this.date.getMillis();
});

Clazz.newMeth(C$, 'setMillis$I', function (millis) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'addMillis$I', function (n) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'getTimeOfDay', function () {
return this.date.getTimeOfDay();
});

Clazz.newMeth(C$, 'setDate$I$I$I', function (year, month, dayOfMonth) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'addDate$I$I$I', function (year, month, dayOfMonth) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'setTimeOfDay$I$I$I$I', function (hours, minutes, seconds, millis) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'addTimeOfDay$I$I$I$I', function (hours, minutes, seconds, millis) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'setTimeOfDay$J', function (fraction) {
p$.unsupported.apply(this, []);
});

Clazz.newMeth(C$, 'isNormalized', function () {
return this.date.isNormalized();
});

Clazz.newMeth(C$, 'isStandardTime', function () {
return this.date.isStandardTime();
});

Clazz.newMeth(C$, 'setStandardTime$Z', function (standardTime) {
p$.unsupported.apply(this, []);
});

Clazz.newMeth(C$, 'isDaylightTime', function () {
return this.date.isDaylightTime();
});

Clazz.newMeth(C$, 'setLocale$java_util_Locale', function (loc) {
p$.unsupported.apply(this, []);
});

Clazz.newMeth(C$, 'getZone', function () {
return this.date.getZone();
});

Clazz.newMeth(C$, 'setZone$java_util_TimeZone', function (zoneinfo) {
p$.unsupported.apply(this, []);
return this;
});

Clazz.newMeth(C$, 'isSameDate$sun_util_calendar_CalendarDate', function (date) {
return date.isSameDate$sun_util_calendar_CalendarDate(date);
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
if (this === obj ) {
return true;
}if (!(Clazz.instanceOf(obj, "sun.util.calendar.ImmutableGregorianDate"))) {
return false;
}return this.date.equals$O((obj).date);
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.date.hashCode();
});

Clazz.newMeth(C$, 'clone', function () {
return Clazz.clone(this);
});

Clazz.newMeth(C$, 'toString', function () {
return this.date.toString();
});

Clazz.newMeth(C$, 'setDayOfWeek$I', function (dayOfWeek) {
p$.unsupported.apply(this, []);
});

Clazz.newMeth(C$, 'setNormalized$Z', function (normalized) {
p$.unsupported.apply(this, []);
});

Clazz.newMeth(C$, 'getZoneOffset', function () {
return this.date.getZoneOffset();
});

Clazz.newMeth(C$, 'setZoneOffset$I', function (offset) {
p$.unsupported.apply(this, []);
});

Clazz.newMeth(C$, 'getDaylightSaving', function () {
return this.date.getDaylightSaving();
});

Clazz.newMeth(C$, 'setDaylightSaving$I', function (daylightSaving) {
p$.unsupported.apply(this, []);
});

Clazz.newMeth(C$, 'getNormalizedYear', function () {
return this.date.getNormalizedYear();
});

Clazz.newMeth(C$, 'setNormalizedYear$I', function (normalizedYear) {
p$.unsupported.apply(this, []);
});

Clazz.newMeth(C$, 'unsupported', function () {
throw Clazz.new_(Clazz.load('java.lang.UnsupportedOperationException'));
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:14