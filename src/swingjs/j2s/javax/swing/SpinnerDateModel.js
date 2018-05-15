(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.util.Calendar','java.util.Date']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SpinnerDateModel", null, 'javax.swing.AbstractSpinnerModel');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.start = null;
this.end = null;
this.value = null;
this.calendarField = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'calendarFieldOK$I', function (calendarField) {
switch (calendarField) {
case 0:
case 1:
case 2:
case 3:
case 4:
case 5:
case 6:
case 7:
case 8:
case 9:
case 10:
case 11:
case 12:
case 13:
case 14:
return true;
default:
return false;
}
});

Clazz.newMeth(C$, 'c$$java_util_Date$Comparable$Comparable$I', function (value, start, end, calendarField) {
Clazz.super_(C$, this,1);
if (value == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["value is null"]);
}if (!p$.calendarFieldOK$I.apply(this, [calendarField])) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["invalid calendarField"]);
}if (!(((start == null ) || (start.compareTo$TT(value) <= 0) ) && ((end == null ) || (end.compareTo$TT(value) >= 0) ) )) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["(start <= value <= end) is false"]);
}this.value = (I$[1]||$incl$(1)).getInstance();
this.start = start;
this.end = end;
this.calendarField = calendarField;
this.value.setTime$java_util_Date(value);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$java_util_Date$Comparable$Comparable$I.apply(this, [Clazz.new_((I$[2]||$incl$(2))), null, null, 5]);
}, 1);

Clazz.newMeth(C$, 'setStart$Comparable', function (start) {
if ((start == null ) ? (this.start != null ) : !start.equals$O(this.start)) {
this.start = start;
this.fireStateChanged();
}});

Clazz.newMeth(C$, 'getStart', function () {
return this.start;
});

Clazz.newMeth(C$, 'setEnd$Comparable', function (end) {
if ((end == null ) ? (this.end != null ) : !end.equals$O(this.end)) {
this.end = end;
this.fireStateChanged();
}});

Clazz.newMeth(C$, 'getEnd', function () {
return this.end;
});

Clazz.newMeth(C$, 'setCalendarField$I', function (calendarField) {
if (!p$.calendarFieldOK$I.apply(this, [calendarField])) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["invalid calendarField"]);
}if (calendarField != this.calendarField) {
this.calendarField = calendarField;
this.fireStateChanged();
}});

Clazz.newMeth(C$, 'getCalendarField', function () {
return this.calendarField;
});

Clazz.newMeth(C$, 'getNextValue', function () {
var cal = (I$[1]||$incl$(1)).getInstance();
cal.setTime$java_util_Date(this.value.getTime());
cal.add$I$I(this.calendarField, 1);
var next = cal.getTime();
return ((this.end == null ) || (this.end.compareTo$TT(next) >= 0) ) ? next : null;
});

Clazz.newMeth(C$, 'getPreviousValue', function () {
var cal = (I$[1]||$incl$(1)).getInstance();
cal.setTime$java_util_Date(this.value.getTime());
cal.add$I$I(this.calendarField, -1);
var prev = cal.getTime();
return ((this.start == null ) || (this.start.compareTo$TT(prev) <= 0) ) ? prev : null;
});

Clazz.newMeth(C$, 'getDate', function () {
return this.value.getTime();
});

Clazz.newMeth(C$, 'getValue', function () {
return this.value.getTime();
});

Clazz.newMeth(C$, 'setValue$O', function (value) {
if ((value == null ) || !(Clazz.instanceOf(value, "java.util.Date")) ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["illegal value"]);
}if (!value.equals$O(this.value.getTime())) {
this.value.setTime$java_util_Date(value);
this.fireStateChanged();
}});
})();
//Created 2018-05-15 01:02:40
