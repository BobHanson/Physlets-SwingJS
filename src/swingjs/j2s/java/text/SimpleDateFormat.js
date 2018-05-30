(function(){var P$=Clazz.newPackage("java.text"),I$=[['java.util.Hashtable',['java.text.DateFormat','.Field'],'java.util.Locale','java.text.DateFormatSymbols','sun.util.resources.LocaleData','java.text.MessageFormat','java.text.NumberFormat','java.util.Calendar','java.util.TimeZone','java.lang.StringBuilder','java.util.Date','java.lang.StringBuffer','java.text.CharacterIteratorFieldDelegate','sun.util.calendar.CalendarUtils','java.text.DontCareFieldPosition']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SimpleDateFormat", null, 'java.text.DateFormat');
C$.cachedLocaleData = null;
C$.cachedNumberFormatData = null;
var p$=C$.prototype;
C$.PATTERN_INDEX_TO_CALENDAR_FIELD = null;
C$.PATTERN_INDEX_TO_DATE_FORMAT_FIELD = null;
C$.PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.cachedLocaleData = Clazz.new_((I$[1]||$incl$(1)).c$$I,[3]);
C$.cachedNumberFormatData = Clazz.new_((I$[1]||$incl$(1)).c$$I,[3]);
C$.PATTERN_INDEX_TO_CALENDAR_FIELD = Clazz.array(Integer.TYPE, -1, [0, 1, 2, 5, 11, 11, 12, 13, 14, 7, 6, 8, 3, 4, 9, 10, 10, 15, 15]);
C$.PATTERN_INDEX_TO_DATE_FORMAT_FIELD = Clazz.array(Integer.TYPE, -1, [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 17]);
C$.PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID = Clazz.array((I$[2]||$incl$(2)), -1, [(I$[2]||$incl$(2)).ERA, (I$[2]||$incl$(2)).YEAR, (I$[2]||$incl$(2)).MONTH, (I$[2]||$incl$(2)).DAY_OF_MONTH, (I$[2]||$incl$(2)).HOUR_OF_DAY1, (I$[2]||$incl$(2)).HOUR_OF_DAY0, (I$[2]||$incl$(2)).MINUTE, (I$[2]||$incl$(2)).SECOND, (I$[2]||$incl$(2)).MILLISECOND, (I$[2]||$incl$(2)).DAY_OF_WEEK, (I$[2]||$incl$(2)).DAY_OF_YEAR, (I$[2]||$incl$(2)).DAY_OF_WEEK_IN_MONTH, (I$[2]||$incl$(2)).WEEK_OF_YEAR, (I$[2]||$incl$(2)).WEEK_OF_MONTH, (I$[2]||$incl$(2)).AM_PM, (I$[2]||$incl$(2)).HOUR1, (I$[2]||$incl$(2)).HOUR0, (I$[2]||$incl$(2)).TIME_ZONE, (I$[2]||$incl$(2)).TIME_ZONE]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.serialVersionOnStream = 0;
this.pattern = null;
this.compiledPattern = null;
this.zeroDigit = '\0';
this.formatData = null;
this.defaultCenturyStart = null;
this.defaultCenturyStartYear = 0;
this.locale = null;
this.$useDateFormatSymbols = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.serialVersionOnStream = 1;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$I$I$java_util_Locale.apply(this, [3, 3, (I$[3]||$incl$(3)).getDefault()]);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (pattern) {
C$.c$$S$java_util_Locale.apply(this, [pattern, (I$[3]||$incl$(3)).getDefault()]);
}, 1);

Clazz.newMeth(C$, 'c$$S$java_util_Locale', function (pattern, locale) {
Clazz.super_(C$, this,1);
if (pattern == null  || locale == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}p$.initializeCalendar$java_util_Locale.apply(this, [locale]);
this.pattern=pattern;
this.formatData=(I$[4]||$incl$(4)).getInstance$java_util_Locale(locale);
this.locale=locale;
p$.initialize$java_util_Locale.apply(this, [locale]);
}, 1);

Clazz.newMeth(C$, 'c$$S$java_text_DateFormatSymbols', function (pattern, formatSymbols) {
Clazz.super_(C$, this,1);
if (pattern == null  || formatSymbols == null  ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.pattern=pattern;
this.formatData=formatSymbols.clone();
this.locale=(I$[3]||$incl$(3)).getDefault();
p$.initializeCalendar$java_util_Locale.apply(this, [this.locale]);
p$.initialize$java_util_Locale.apply(this, [this.locale]);
this.$useDateFormatSymbols=true;
}, 1);

Clazz.newMeth(C$, 'c$$I$I$java_util_Locale', function (timeStyle, dateStyle, loc) {
Clazz.super_(C$, this,1);
if (loc == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.locale=loc;
p$.initializeCalendar$java_util_Locale.apply(this, [loc]);
var key = p$.getKey.apply(this, []);
var dateTimePatterns = C$.cachedLocaleData.get$O(key);
if (dateTimePatterns == null ) {
var r = (I$[5]||$incl$(5)).getDateFormatData$java_util_Locale(loc);
if (!p$.isGregorianCalendar.apply(this, [])) {
try {
dateTimePatterns=r.getStringArray$S(p$.getCalendarName.apply(this, []) + ".DateTimePatterns");
} catch (e) {
if (Clazz.exceptionOf(e, "java.util.MissingResourceException")){
} else {
throw e;
}
}
}if (dateTimePatterns == null ) {
dateTimePatterns=r.getStringArray$S("DateTimePatterns");
}C$.cachedLocaleData.put$TK$TV(key, dateTimePatterns);
}this.formatData=(I$[4]||$incl$(4)).getInstance$java_util_Locale(loc);
if ((timeStyle >= 0) && (dateStyle >= 0) ) {
var dateTimeArgs = Clazz.array(java.lang.Object, -1, [dateTimePatterns[timeStyle], dateTimePatterns[dateStyle + 4]]);
this.pattern=(I$[6]||$incl$(6)).format$S$OA(dateTimePatterns[8], dateTimeArgs);
} else if (timeStyle >= 0) {
this.pattern=dateTimePatterns[timeStyle];
} else if (dateStyle >= 0) {
this.pattern=dateTimePatterns[dateStyle + 4];
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["No date or time style specified"]);
}p$.initialize$java_util_Locale.apply(this, [loc]);
}, 1);

Clazz.newMeth(C$, 'initialize$java_util_Locale', function (loc) {
this.compiledPattern=p$.compile$S.apply(this, [this.pattern]);
this.numberFormat=C$.cachedNumberFormatData.get$O(loc);
if (this.numberFormat == null ) {
this.numberFormat=(I$[7]||$incl$(7)).getIntegerInstance$java_util_Locale(loc);
this.numberFormat.setGroupingUsed$Z(false);
C$.cachedNumberFormatData.put$TK$TV(loc, this.numberFormat);
}this.numberFormat=this.numberFormat.clone();
p$.initializeDefaultCentury.apply(this, []);
});

Clazz.newMeth(C$, 'initializeCalendar$java_util_Locale', function (loc) {
if (this.calendar == null ) {
Clazz.assert(C$, this, function(){return loc != null });
this.calendar=(I$[8]||$incl$(8)).getInstance$java_util_TimeZone$java_util_Locale((I$[9]||$incl$(9)).getDefault(), loc);
}});

Clazz.newMeth(C$, 'getKey', function () {
var sb = Clazz.new_((I$[10]||$incl$(10)));
sb.append$S(p$.getCalendarName.apply(this, [])).append$C(".");
sb.append$S(this.locale.getLanguage()).append$C("_").append$S(this.locale.getCountry()).append$C("_").append$S(this.locale.getVariant());
return sb.toString();
});

Clazz.newMeth(C$, 'compile$S', function (pattern) {
var length = pattern.length$();
var inQuote = false;
var compiledPattern = Clazz.new_((I$[10]||$incl$(10)).c$$I,[length * 2]);
var tmpBuffer = null;
var count = 0;
var lastTag = -1;
for (var i = 0; i < length; i++) {
var c = pattern.charAt(i);
if (c == "\'") {
if ((i + 1) < length) {
c=pattern.charAt(i + 1);
if (c == "\'") {
i++;
if (count != 0) {
C$.encode$I$I$StringBuilder(lastTag, count, compiledPattern);
lastTag=-1;
count=0;
}if (inQuote) {
tmpBuffer.append$C(c);
} else {
compiledPattern.append$C(String.fromCharCode((25600 | c)));
}continue;
}}if (!inQuote) {
if (count != 0) {
C$.encode$I$I$StringBuilder(lastTag, count, compiledPattern);
lastTag=-1;
count=0;
}if (tmpBuffer == null ) {
tmpBuffer=Clazz.new_((I$[10]||$incl$(10)).c$$I,[length]);
} else {
tmpBuffer.setLength$I(0);
}inQuote=true;
} else {
var len = tmpBuffer.length$();
if (len == 1) {
var ch = tmpBuffer.charAt$I(0);
if (ch.$c() < 128 ) {
compiledPattern.append$C(String.fromCharCode((25600 | ch)));
} else {
compiledPattern.append$C("\u6501");
compiledPattern.append$C(ch);
}} else {
C$.encode$I$I$StringBuilder(101, len, compiledPattern);
compiledPattern.append$CharSequence(tmpBuffer);
}inQuote=false;
}continue;
}if (inQuote) {
tmpBuffer.append$C(c);
continue;
}if (!(c >= "a" && c <= "z"  || c >= "A" && c <= "Z"  )) {
if (count != 0) {
C$.encode$I$I$StringBuilder(lastTag, count, compiledPattern);
lastTag=-1;
count=0;
}if (c.$c() < 128 ) {
compiledPattern.append$C(String.fromCharCode((25600 | c)));
} else {
var j;
for (j=i + 1; j < length; j++) {
var d = pattern.charAt(j);
if (d == "\'" || (d >= "a" && d <= "z"  || d >= "A" && d <= "Z"  ) ) {
break;
}}
compiledPattern.append$C(String.fromCharCode((25856 | (j - i))));
for (; i < j; i++) {
compiledPattern.append$C(pattern.charAt(i));
}
i--;
}continue;
}var tag;
if ((tag="GyMdkHmsSEDFwWahKzZ".indexOf(c)) == -1) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Illegal pattern character \'" + c + "'" ]);
}if (lastTag == -1 || lastTag == tag ) {
lastTag=tag;
count++;
continue;
}C$.encode$I$I$StringBuilder(lastTag, count, compiledPattern);
lastTag=tag;
count=1;
}
if (inQuote) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Unterminated quote"]);
}if (count != 0) {
C$.encode$I$I$StringBuilder(lastTag, count, compiledPattern);
}var len = compiledPattern.length$();
var r = Clazz.array(Character.TYPE, [len]);
compiledPattern.getChars$I$I$CA$I(0, len, r, 0);
return r;
});

Clazz.newMeth(C$, 'encode$I$I$StringBuilder', function (tag, length, buffer) {
if (length < 255) {
buffer.append$C(String.fromCharCode((tag << 8 | length)));
} else {
buffer.append$C(String.fromCharCode(((tag << 8) | 255)));
buffer.append$C(String.fromCharCode((length >>> 16)));
buffer.append$C(String.fromCharCode((length & 65535)));
}}, 1);

Clazz.newMeth(C$, 'initializeDefaultCentury', function () {
this.calendar.setTime$java_util_Date(Clazz.new_((I$[11]||$incl$(11))));
this.calendar.add$I$I(1, -80);
p$.parseAmbiguousDatesAsAfter$java_util_Date.apply(this, [this.calendar.getTime()]);
});

Clazz.newMeth(C$, 'parseAmbiguousDatesAsAfter$java_util_Date', function (startDate) {
this.defaultCenturyStart=startDate;
this.calendar.setTime$java_util_Date(startDate);
this.defaultCenturyStartYear=this.calendar.get$I(1);
});

Clazz.newMeth(C$, 'set2DigitYearStart$java_util_Date', function (startDate) {
p$.parseAmbiguousDatesAsAfter$java_util_Date.apply(this, [startDate]);
});

Clazz.newMeth(C$, 'get2DigitYearStart', function () {
return this.defaultCenturyStart;
});

Clazz.newMeth(C$, 'format$java_util_Date$StringBuffer$java_text_FieldPosition', function (date, toAppendTo, pos) {
pos.beginIndex=pos.endIndex=0;
return p$.format$java_util_Date$StringBuffer$java_text_Format_FieldDelegate.apply(this, [date, toAppendTo, pos.getFieldDelegate()]);
});

Clazz.newMeth(C$, 'format$java_util_Date$StringBuffer$java_text_Format_FieldDelegate', function (date, toAppendTo, delegate) {
this.calendar.setTime$java_util_Date(date);
var useDateFormatSymbols = p$.useDateFormatSymbols.apply(this, []);
for (var i = 0; i < this.compiledPattern.length; ) {
var tag = (this.compiledPattern[i]).$c() >>> 8;
var count = this.compiledPattern[i++] & 255;
if (count == 255) {
count=(this.compiledPattern[i++]).$c() << 16;
count|=this.compiledPattern[i++].$c();
}switch (tag) {
case 100:
toAppendTo.append$C(String.fromCharCode(count));
break;
case 101:
toAppendTo.append$CA$I$I(this.compiledPattern, i, count);
i+=count;
break;
default:
p$.subFormat$I$I$java_text_Format_FieldDelegate$StringBuffer$Z.apply(this, [tag, count, delegate, toAppendTo, useDateFormatSymbols]);
break;
}
}
return toAppendTo;
});

Clazz.newMeth(C$, 'formatToCharacterIterator$O', function (obj) {
var sb = Clazz.new_((I$[12]||$incl$(12)));
var delegate = Clazz.new_((I$[13]||$incl$(13)));
if (Clazz.instanceOf(obj, "java.util.Date")) {
p$.format$java_util_Date$StringBuffer$java_text_Format_FieldDelegate.apply(this, [obj, sb, delegate]);
} else if (Clazz.instanceOf(obj, "java.lang.Number")) {
p$.format$java_util_Date$StringBuffer$java_text_Format_FieldDelegate.apply(this, [Clazz.new_((I$[11]||$incl$(11)),[(obj).longValue()]), sb, delegate]);
} else if (obj == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["formatToCharacterIterator must be passed non-null object"]);
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Cannot format given Object as a Date"]);
}return delegate.getIterator$S(sb.toString());
});

Clazz.newMeth(C$, 'subFormat$I$I$java_text_Format_FieldDelegate$StringBuffer$Z', function (patternCharIndex, count, delegate, buffer, useDateFormatSymbols) {
var maxIntCount = 2147483647;
var current = null;
var beginOffset = buffer.length$();
var field = C$.PATTERN_INDEX_TO_CALENDAR_FIELD[patternCharIndex];
var value = this.calendar.get$I(field);
var style = (count >= 4) ? 2 : 1;
if (!useDateFormatSymbols) {
current=this.calendar.getDisplayName$I$I$java_util_Locale(field, style, this.locale);
}switch (patternCharIndex) {
case 0:
if (useDateFormatSymbols) {
var eras = this.formatData.getEras();
if (value < eras.length) current=eras[value];
}if (current == null ) current="";
break;
case 1:
if (Clazz.instanceOf(this.calendar, "java.util.GregorianCalendar")) {
if (count >= 4) p$.zeroPaddingNumber$I$I$I$StringBuffer.apply(this, [value, count, maxIntCount, buffer]);
 else p$.zeroPaddingNumber$I$I$I$StringBuffer.apply(this, [value, 2, 2, buffer]);
} else {
if (current == null ) {
p$.zeroPaddingNumber$I$I$I$StringBuffer.apply(this, [value, style == 2 ? 1 : count, maxIntCount, buffer]);
}}break;
case 2:
if (useDateFormatSymbols) {
var months;
if (count >= 4) {
months=this.formatData.getMonths();
current=months[value];
} else if (count == 3) {
months=this.formatData.getShortMonths();
current=months[value];
}} else {
if (count < 3) {
current=null;
}}if (current == null ) {
p$.zeroPaddingNumber$I$I$I$StringBuffer.apply(this, [value + 1, count, maxIntCount, buffer]);
}break;
case 4:
if (current == null ) {
if (value == 0) p$.zeroPaddingNumber$I$I$I$StringBuffer.apply(this, [this.calendar.getMaximum$I(11) + 1, count, maxIntCount, buffer]);
 else p$.zeroPaddingNumber$I$I$I$StringBuffer.apply(this, [value, count, maxIntCount, buffer]);
}break;
case 9:
if (useDateFormatSymbols) {
var weekdays;
if (count >= 4) {
weekdays=this.formatData.getWeekdays();
current=weekdays[value];
} else {
weekdays=this.formatData.getShortWeekdays();
current=weekdays[value];
}}break;
case 14:
if (useDateFormatSymbols) {
var ampm = this.formatData.getAmPmStrings();
current=ampm[value];
}break;
case 15:
if (current == null ) {
if (value == 0) p$.zeroPaddingNumber$I$I$I$StringBuffer.apply(this, [this.calendar.getLeastMaximum$I(10) + 1, count, maxIntCount, buffer]);
 else p$.zeroPaddingNumber$I$I$I$StringBuffer.apply(this, [value, count, maxIntCount, buffer]);
}break;
case 17:
if (current == null ) {
var id = this.calendar.getTimeZone().getID();
buffer.append$S(id);
}break;
case 18:
value=((this.calendar.get$I(15) + this.calendar.get$I(16))/60000|0);
var width = 4;
if (value >= 0) {
buffer.append$C("+");
} else {
width++;
}var num = ((value/60|0)) * 100 + (value % 60);
(I$[14]||$incl$(14)).sprintf0d$StringBuffer$I$I(buffer, num, width);
break;
default:
if (current == null ) {
p$.zeroPaddingNumber$I$I$I$StringBuffer.apply(this, [value, count, maxIntCount, buffer]);
}break;
}
if (current != null ) {
buffer.append$S(current);
}var fieldID = C$.PATTERN_INDEX_TO_DATE_FORMAT_FIELD[patternCharIndex];
var f = C$.PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID[patternCharIndex];
delegate.formatted$I$java_text_Format_Field$O$I$I$StringBuffer(fieldID, f, f, beginOffset, buffer.length$(), buffer);
});

Clazz.newMeth(C$, 'zeroPaddingNumber$I$I$I$StringBuffer', function (value, minDigits, maxDigits, buffer) {
try {
if (this.zeroDigit.$c() == 0 ) {
this.zeroDigit=(this.numberFormat).getDecimalFormatSymbols().getZeroDigit();
}if (value >= 0) {
if (value < 100 && minDigits >= 1  && minDigits <= 2 ) {
if (value < 10) {
if (minDigits == 2) {
buffer.append$C(this.zeroDigit);
}buffer.append$C(String.fromCharCode((this.zeroDigit.$c() + value)));
} else {
buffer.append$C(String.fromCharCode((this.zeroDigit.$c() + (value/10|0))));
buffer.append$C(String.fromCharCode((this.zeroDigit.$c() + value % 10)));
}return;
} else if (value >= 1000 && value < 10000 ) {
if (minDigits == 4) {
buffer.append$C(String.fromCharCode((this.zeroDigit.$c() + (value/1000|0))));
value%=1000;
buffer.append$C(String.fromCharCode((this.zeroDigit.$c() + (value/100|0))));
value%=100;
buffer.append$C(String.fromCharCode((this.zeroDigit.$c() + (value/10|0))));
buffer.append$C(String.fromCharCode((this.zeroDigit.$c() + value % 10)));
return;
}if (minDigits == 2 && maxDigits == 2 ) {
p$.zeroPaddingNumber$I$I$I$StringBuffer.apply(this, [value % 100, 2, 2, buffer]);
return;
}}}} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
} else {
throw e;
}
}
this.numberFormat.setMinimumIntegerDigits$I(minDigits);
this.numberFormat.setMaximumIntegerDigits$I(maxDigits);
this.numberFormat.format$J$StringBuffer$java_text_FieldPosition(value, buffer, (I$[15]||$incl$(15)).INSTANCE);
});

Clazz.newMeth(C$, 'parse$S$java_text_ParsePosition', function (text, pos) {
{
var i0 = pos.index; pos.index = Math.min(80, text.length()); while (pos.index >= i0) { var d = new Date(text.substring(i0, pos.index)); var x = d.getMilliseconds(); if (!isNaN(x)) return d; pos.index--; } pos.index = i0;
return null;
}
});

Clazz.newMeth(C$, 'getCalendarName', function () {
return this.calendar.getClass().getName();
});

Clazz.newMeth(C$, 'useDateFormatSymbols', function () {
if (this.$useDateFormatSymbols) {
return true;
}return p$.isGregorianCalendar.apply(this, []) || this.locale == null  ;
});

Clazz.newMeth(C$, 'isGregorianCalendar', function () {
return "java.util.GregorianCalendar".equals$O(p$.getCalendarName.apply(this, []));
});

Clazz.newMeth(C$, 'translatePattern$S$S$S', function (pattern, from, to) {
var result = Clazz.new_((I$[10]||$incl$(10)));
var inQuote = false;
for (var i = 0; i < pattern.length$(); ++i) {
var c = pattern.charAt(i);
if (inQuote) {
if (c == "\'") inQuote=false;
} else {
if (c == "\'") inQuote=true;
 else if ((c >= "a" && c <= "z" ) || (c >= "A" && c <= "Z" ) ) {
var ci = from.indexOf(c);
if (ci == -1) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Illegal pattern  character \'" + c + "'" ]);
c=to.charAt(ci);
}}result.append$C(c);
}
if (inQuote) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Unfinished quote in pattern"]);
return result.toString();
});

Clazz.newMeth(C$, 'toPattern', function () {
return this.pattern;
});

Clazz.newMeth(C$, 'toLocalizedPattern', function () {
return p$.translatePattern$S$S$S.apply(this, [this.pattern, "GyMdkHmsSEDFwWahKzZ", this.formatData.getLocalPatternChars()]);
});

Clazz.newMeth(C$, 'applyPattern$S', function (pattern) {
this.compiledPattern=p$.compile$S.apply(this, [pattern]);
this.pattern=pattern;
});

Clazz.newMeth(C$, 'applyLocalizedPattern$S', function (pattern) {
var p = p$.translatePattern$S$S$S.apply(this, [pattern, this.formatData.getLocalPatternChars(), "GyMdkHmsSEDFwWahKzZ"]);
this.compiledPattern=p$.compile$S.apply(this, [p]);
this.pattern=p;
});

Clazz.newMeth(C$, 'getDateFormatSymbols', function () {
return this.formatData.clone();
});

Clazz.newMeth(C$, 'setDateFormatSymbols$java_text_DateFormatSymbols', function (newFormatSymbols) {
this.formatData=newFormatSymbols.clone();
this.$useDateFormatSymbols=true;
});

Clazz.newMeth(C$, 'clone', function () {
var other = C$.superclazz.prototype.clone.apply(this, []);
other.formatData=this.formatData.clone();
return other;
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.pattern.hashCode();
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
if (!C$.superclazz.prototype.equals$O.apply(this, [obj])) return false;
var that = obj;
return (this.pattern.equals$O(that.pattern) && this.formatData.equals$O(that.formatData) );
});
C$.$_ASSERT_ENABLED_ = ClassLoader.$getClassAssertionStatus(C$);
})();
//Created 2018-05-24 08:45:44
