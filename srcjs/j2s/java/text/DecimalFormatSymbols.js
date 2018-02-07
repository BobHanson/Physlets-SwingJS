(function(){var P$=Clazz.newPackage("java.text"),I$=[['java.util.Hashtable','java.util.Locale','java.lang.InternalError','sun.util.resources.LocaleData']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DecimalFormatSymbols", null, null, 'Cloneable');
var p$=C$.prototype;
C$.cachedLocaleData = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.cachedLocaleData = Clazz.new_((I$[1]||$incl$(1)).c$$I,[3]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.zeroDigit = '\0';
this.groupingSeparator = '\0';
this.decimalSeparator = '\0';
this.perMill = '\0';
this.percent = '\0';
this.digit = '\0';
this.patternSeparator = '\0';
this.infinity = null;
this.NaN = null;
this.minusSign = '\0';
this.currencySymbol = null;
this.intlCurrencySymbol = null;
this.monetarySeparator = '\0';
this.exponential = '\0';
this.exponentialSeparator = null;
this.locale = null;
this.serialVersionOnStream = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.serialVersionOnStream = 3;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
p$.initialize$java_util_Locale.apply(this, [(I$[2]||$incl$(2)).getDefault()]);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Locale', function (locale) {
C$.$init$.apply(this);
p$.initialize$java_util_Locale.apply(this, [locale]);
}, 1);

Clazz.newMeth(C$, 'getInstance', function () {
return C$.getInstance$java_util_Locale((I$[2]||$incl$(2)).getDefault());
}, 1);

Clazz.newMeth(C$, 'getInstance$java_util_Locale', function (locale) {
return Clazz.new_(C$.c$$java_util_Locale,[locale]);
}, 1);

Clazz.newMeth(C$, 'getZeroDigit', function () {
return this.zeroDigit;
});

Clazz.newMeth(C$, 'setZeroDigit$C', function (zeroDigit) {
this.zeroDigit = zeroDigit;
});

Clazz.newMeth(C$, 'getGroupingSeparator', function () {
return this.groupingSeparator;
});

Clazz.newMeth(C$, 'setGroupingSeparator$C', function (groupingSeparator) {
this.groupingSeparator = groupingSeparator;
});

Clazz.newMeth(C$, 'getDecimalSeparator', function () {
return this.decimalSeparator;
});

Clazz.newMeth(C$, 'setDecimalSeparator$C', function (decimalSeparator) {
this.decimalSeparator = decimalSeparator;
});

Clazz.newMeth(C$, 'getPerMill', function () {
return this.perMill;
});

Clazz.newMeth(C$, 'setPerMill$C', function (perMill) {
this.perMill = perMill;
});

Clazz.newMeth(C$, 'getPercent', function () {
return this.percent;
});

Clazz.newMeth(C$, 'setPercent$C', function (percent) {
this.percent = percent;
});

Clazz.newMeth(C$, 'getDigit', function () {
return this.digit;
});

Clazz.newMeth(C$, 'setDigit$C', function (digit) {
this.digit = digit;
});

Clazz.newMeth(C$, 'getPatternSeparator', function () {
return this.patternSeparator;
});

Clazz.newMeth(C$, 'setPatternSeparator$C', function (patternSeparator) {
this.patternSeparator = patternSeparator;
});

Clazz.newMeth(C$, 'getInfinity', function () {
return this.infinity;
});

Clazz.newMeth(C$, 'setInfinity$S', function (infinity) {
this.infinity = infinity;
});

Clazz.newMeth(C$, 'getNaN', function () {
return this.NaN;
});

Clazz.newMeth(C$, 'setNaN$S', function (NaN) {
this.NaN = NaN;
});

Clazz.newMeth(C$, 'getMinusSign', function () {
return this.minusSign;
});

Clazz.newMeth(C$, 'setMinusSign$C', function (minusSign) {
this.minusSign = minusSign;
});

Clazz.newMeth(C$, 'getCurrencySymbol', function () {
return this.currencySymbol;
});

Clazz.newMeth(C$, 'setCurrencySymbol$S', function (currency) {
this.currencySymbol = currency;
});

Clazz.newMeth(C$, 'getInternationalCurrencySymbol', function () {
return this.intlCurrencySymbol;
});

Clazz.newMeth(C$, 'setInternationalCurrencySymbol$S', function (currencyCode) {
this.intlCurrencySymbol = currencyCode;
});

Clazz.newMeth(C$, 'getMonetaryDecimalSeparator', function () {
return this.monetarySeparator;
});

Clazz.newMeth(C$, 'setMonetaryDecimalSeparator$C', function (sep) {
this.monetarySeparator = sep;
});

Clazz.newMeth(C$, 'getExponentialSymbol', function () {
return this.exponential;
});

Clazz.newMeth(C$, 'getExponentSeparator', function () {
return this.exponentialSeparator;
});

Clazz.newMeth(C$, 'setExponentialSymbol$C', function (exp) {
this.exponential = exp;
});

Clazz.newMeth(C$, 'setExponentSeparator$S', function (exp) {
if (exp == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.exponentialSeparator = exp;
});

Clazz.newMeth(C$, 'clone', function () {
try {
return Clazz.clone(this);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
throw Clazz.new_((I$[3]||$incl$(3)));
} else {
throw e;
}
}
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
if (obj == null ) return false;
if (this === obj ) return true;
if (this.getClass() !== obj.getClass() ) return false;
var other = obj;
return (this.zeroDigit == other.zeroDigit && this.groupingSeparator == other.groupingSeparator  && this.decimalSeparator == other.decimalSeparator  && this.percent == other.percent  && this.perMill == other.perMill  && this.digit == other.digit  && this.minusSign == other.minusSign  && this.patternSeparator == other.patternSeparator  && this.infinity.equals$O(other.infinity)  && this.NaN.equals$O(other.NaN)  && this.currencySymbol.equals$O(other.currencySymbol)  && this.intlCurrencySymbol.equals$O(other.intlCurrencySymbol)  && this.monetarySeparator == other.monetarySeparator  && this.exponentialSeparator.equals$O(other.exponentialSeparator)  && this.locale.equals$O(other.locale) );
});

Clazz.newMeth(C$, 'hashCode', function () {
var result = this.zeroDigit.$c();
result = result * 37 + this.groupingSeparator.$c();
result = result * 37 + this.decimalSeparator.$c();
return result;
});

Clazz.newMeth(C$, 'initialize$java_util_Locale', function (locale) {
this.locale = locale;
var needCacheUpdate = false;
var data = C$.cachedLocaleData.get$O(locale);
if (data == null ) {
data = Clazz.array(java.lang.Object, [3]);
var rb = (I$[4]||$incl$(4)).getNumberFormatData$java_util_Locale(locale);
data[0] = rb.getStringArray$S("NumberElements");
needCacheUpdate = true;
}var numberElements = data[0];
this.decimalSeparator = numberElements[0].charAt(0);
this.groupingSeparator = numberElements[1].charAt(0);
this.patternSeparator = numberElements[2].charAt(0);
this.percent = numberElements[3].charAt(0);
this.zeroDigit = numberElements[4].charAt(0);
this.digit = numberElements[5].charAt(0);
this.minusSign = numberElements[6].charAt(0);
this.exponential = numberElements[7].charAt(0);
this.exponentialSeparator = numberElements[7];
this.perMill = numberElements[8].charAt(0);
this.infinity = numberElements[9];
this.NaN = numberElements[10];
this.intlCurrencySymbol = "\u00a4";
this.currencySymbol = "$";
this.monetarySeparator = this.decimalSeparator;
if (needCacheUpdate) {
C$.cachedLocaleData.put$TK$TV(locale, data);
}});
})();
//Created 2018-02-06 08:58:44