(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[];
var C$=Clazz.newClass(P$, "DefaultFormatterFactory", null, ['javax.swing.JFormattedTextField','javax.swing.JFormattedTextField.AbstractFormatterFactory']);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.defaultFormat = null;
this.displayFormat = null;
this.editFormat = null;
this.nullFormat = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JFormattedTextField_AbstractFormatter', function (defaultFormat) {
Clazz.super_(C$, this,1);
p$.set$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter.apply(this, [defaultFormat, null, null, null]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter', function (defaultFormat, displayFormat) {
Clazz.super_(C$, this,1);
p$.set$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter.apply(this, [defaultFormat, displayFormat, null, null]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter', function (defaultFormat, displayFormat, editFormat) {
Clazz.super_(C$, this,1);
p$.set$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter.apply(this, [defaultFormat, displayFormat, editFormat, null]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter', function (defaultFormat, displayFormat, editFormat, nullFormat) {
Clazz.super_(C$, this,1);
{
defaultFormat || (defaultFormat = null);
displayFormat || (displayFormat = null);
editFormat || (editFormat = null);
nullFormat || (nullFormat = null);
}
p$.set$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter.apply(this, [defaultFormat, displayFormat, editFormat, nullFormat]);
}, 1);

Clazz.newMeth(C$, 'set$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter$javax_swing_JFormattedTextField_AbstractFormatter', function (defaultFormat, displayFormat, editFormat, nullFormat) {
this.defaultFormat = defaultFormat;
this.displayFormat = displayFormat;
this.editFormat = editFormat;
this.nullFormat = nullFormat;
return this;
});

Clazz.newMeth(C$, 'setDefaultFormatter$javax_swing_JFormattedTextField_AbstractFormatter', function (atf) {
this.defaultFormat = atf;
});

Clazz.newMeth(C$, 'getDefaultFormatter', function () {
return this.defaultFormat;
});

Clazz.newMeth(C$, 'setDisplayFormatter$javax_swing_JFormattedTextField_AbstractFormatter', function (atf) {
this.displayFormat = atf;
});

Clazz.newMeth(C$, 'getDisplayFormatter', function () {
return this.displayFormat;
});

Clazz.newMeth(C$, 'setEditFormatter$javax_swing_JFormattedTextField_AbstractFormatter', function (atf) {
this.editFormat = atf;
});

Clazz.newMeth(C$, 'getEditFormatter', function () {
return this.editFormat;
});

Clazz.newMeth(C$, 'setNullFormatter$javax_swing_JFormattedTextField_AbstractFormatter', function (atf) {
this.nullFormat = atf;
});

Clazz.newMeth(C$, 'getNullFormatter', function () {
return this.nullFormat;
});

Clazz.newMeth(C$, 'getFormatter$javax_swing_JFormattedTextField', function (source) {
var format = null;
if (source == null ) {
return null;
}var value = source.getValue();
if (value == null ) {
format = this.getNullFormatter();
}if (format == null ) {
if (source.hasFocus()) {
format = this.getEditFormatter();
} else {
format = this.getDisplayFormatter();
}if (format == null ) {
format = this.getDefaultFormatter();
}}return format;
});
})();
//Created 2018-02-06 08:59:56
