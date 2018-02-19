(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[[['javax.swing.text.MaskFormatter','.MaskCharacter'],'java.lang.StringBuffer','java.util.ArrayList',['javax.swing.text.MaskFormatter','.DigitMaskCharacter'],['javax.swing.text.MaskFormatter','.LiteralCharacter'],['javax.swing.text.MaskFormatter','.UpperCaseCharacter'],['javax.swing.text.MaskFormatter','.LowerCaseCharacter'],['javax.swing.text.MaskFormatter','.AlphaNumericCharacter'],['javax.swing.text.MaskFormatter','.CharCharacter'],['javax.swing.text.MaskFormatter','.HexCharacter']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "MaskFormatter", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.text.DefaultFormatter');
C$.EmptyMaskChars = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.EmptyMaskChars = Clazz.array((I$[1]||$incl$(1)), [0]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.mask = null;
this.maskChars = null;
this.validCharacters = null;
this.invalidCharacters = null;
this.placeholderString = null;
this.placeholder = '\0';
this.containsLiteralChars = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.setAllowsInvalid$Z(false);
this.containsLiteralChars = true;
this.maskChars = C$.EmptyMaskChars;
this.placeholder = " ";
}, 1);

Clazz.newMeth(C$, 'c$$S', function (mask) {
C$.c$.apply(this, []);
this.setMask$S(mask);
}, 1);

Clazz.newMeth(C$, 'setMask$S', function (mask) {
this.mask = mask;
p$.updateInternalMask.apply(this, []);
});

Clazz.newMeth(C$, 'getMask', function () {
return this.mask;
});

Clazz.newMeth(C$, 'setValidCharacters$S', function (validCharacters) {
this.validCharacters = validCharacters;
});

Clazz.newMeth(C$, 'getValidCharacters', function () {
return this.validCharacters;
});

Clazz.newMeth(C$, 'setInvalidCharacters$S', function (invalidCharacters) {
this.invalidCharacters = invalidCharacters;
});

Clazz.newMeth(C$, 'getInvalidCharacters', function () {
return this.invalidCharacters;
});

Clazz.newMeth(C$, 'setPlaceholder$S', function (placeholder) {
this.placeholderString = placeholder;
});

Clazz.newMeth(C$, 'getPlaceholder', function () {
return this.placeholderString;
});

Clazz.newMeth(C$, 'setPlaceholderCharacter$C', function (placeholder) {
this.placeholder = placeholder;
});

Clazz.newMeth(C$, 'getPlaceholderCharacter', function () {
return this.placeholder;
});

Clazz.newMeth(C$, 'setValueContainsLiteralCharacters$Z', function (containsLiteralChars) {
this.containsLiteralChars = containsLiteralChars;
});

Clazz.newMeth(C$, 'getValueContainsLiteralCharacters', function () {
return this.containsLiteralChars;
});

Clazz.newMeth(C$, 'stringToValue$S', function (value) {
return p$.stringToValue$S$Z.apply(this, [value, true]);
});

Clazz.newMeth(C$, 'valueToString$O', function (value) {
var sValue = (value == null ) ? "" : value.toString();
var result = Clazz.new_((I$[2]||$incl$(2)));
var placeholder = this.getPlaceholder();
var valueCounter = Clazz.array(Integer.TYPE, -1, [0]);
p$.append$StringBuffer$S$IA$S$javax_swing_text_MaskFormatter_MaskCharacterA.apply(this, [result, sValue, valueCounter, placeholder, this.maskChars]);
return result.toString();
});

Clazz.newMeth(C$, 'install$javax_swing_JFormattedTextField', function (ftf) {
C$.superclazz.prototype.install$javax_swing_JFormattedTextField.apply(this, [ftf]);
if (ftf != null ) {
var value = ftf.getValue();
try {
this.stringToValue$S(this.valueToString$O(value));
} catch (pe) {
if (Clazz.exceptionOf(pe, "java.text.ParseException")){
this.setEditValid$Z(false);
} else {
throw pe;
}
}
}});

Clazz.newMeth(C$, 'stringToValue$S$Z', function (value, completeMatch) {
var errorOffset = -1;
if ((errorOffset = p$.getInvalidOffset$S$Z.apply(this, [value, completeMatch])) == -1) {
if (!this.getValueContainsLiteralCharacters()) {
value = p$.stripLiteralChars$S.apply(this, [value]);
}return C$.superclazz.prototype.stringToValue$S.apply(this, [value]);
}throw Clazz.new_(Clazz.load('java.text.ParseException').c$$S$I,["stringToValue passed invalid value", errorOffset]);
});

Clazz.newMeth(C$, 'getInvalidOffset$S$Z', function (string, completeMatch) {
var iLength = string.length$();
if (iLength != p$.getMaxLength.apply(this, [])) {
return iLength;
}for (var counter = 0, max = string.length$(); counter < max; counter++) {
var aChar = string.charAt(counter);
if (!p$.isValidCharacter$I$C.apply(this, [counter, aChar]) && (completeMatch || !p$.isPlaceholder$I$C.apply(this, [counter, aChar]) ) ) {
return counter;
}}
return -1;
});

Clazz.newMeth(C$, 'append$StringBuffer$S$IA$S$javax_swing_text_MaskFormatter_MaskCharacterA', function (result, value, index, placeholder, mask) {
for (var counter = 0, maxCounter = mask.length; counter < maxCounter; counter++) {
mask[counter].append$StringBuffer$S$IA$S(result, value, index, placeholder);
}
});

Clazz.newMeth(C$, 'updateInternalMask', function () {
var mask = this.getMask();
var fixed = Clazz.new_((I$[3]||$incl$(3)));
var temp = fixed;
if (mask != null ) {
for (var counter = 0, maxCounter = mask.length$(); counter < maxCounter; counter++) {
var maskChar = mask.charAt(counter);
switch (maskChar.$c()) {
case "#".$c():
temp.add$TE(Clazz.new_((I$[4]||$incl$(4)), [this, null]));
break;
case "\'".$c():
if (++counter < maxCounter) {
maskChar = mask.charAt(counter);
temp.add$TE(Clazz.new_((I$[5]||$incl$(5)).c$$C, [this, null, maskChar]));
}break;
case "U".$c():
temp.add$TE(Clazz.new_((I$[6]||$incl$(6)), [this, null]));
break;
case "L".$c():
temp.add$TE(Clazz.new_((I$[7]||$incl$(7)), [this, null]));
break;
case "A".$c():
temp.add$TE(Clazz.new_((I$[8]||$incl$(8)), [this, null]));
break;
case "?".$c():
temp.add$TE(Clazz.new_((I$[9]||$incl$(9)), [this, null]));
break;
case "*".$c():
temp.add$TE(Clazz.new_((I$[1]||$incl$(1)), [this, null]));
break;
case "H".$c():
temp.add$TE(Clazz.new_((I$[10]||$incl$(10)), [this, null]));
break;
default:
temp.add$TE(Clazz.new_((I$[5]||$incl$(5)).c$$C, [this, null, maskChar]));
break;
}
}
}if (fixed.size() == 0) {
this.maskChars = C$.EmptyMaskChars;
} else {
this.maskChars = Clazz.array((I$[1]||$incl$(1)), [fixed.size()]);
fixed.toArray$TTA(this.maskChars);
}});

Clazz.newMeth(C$, 'getMaskCharacter$I', function (index) {
if (index >= this.maskChars.length) {
return null;
}return this.maskChars[index];
});

Clazz.newMeth(C$, 'isPlaceholder$I$C', function (index, aChar) {
return (this.getPlaceholderCharacter() == aChar);
});

Clazz.newMeth(C$, 'isValidCharacter$I$C', function (index, aChar) {
return p$.getMaskCharacter$I.apply(this, [index]).isValidCharacter$C(aChar);
});

Clazz.newMeth(C$, 'isLiteral$I', function (index) {
return p$.getMaskCharacter$I.apply(this, [index]).isLiteral();
});

Clazz.newMeth(C$, 'getMaxLength', function () {
return this.maskChars.length;
});

Clazz.newMeth(C$, 'getLiteral$I', function (index) {
return p$.getMaskCharacter$I.apply(this, [index]).getChar$C("\u0000");
});

Clazz.newMeth(C$, 'getCharacter$I$C', function (index, aChar) {
return p$.getMaskCharacter$I.apply(this, [index]).getChar$C(aChar);
});

Clazz.newMeth(C$, 'stripLiteralChars$S', function (string) {
var sb = null;
var last = 0;
for (var counter = 0, max = string.length$(); counter < max; counter++) {
if (p$.isLiteral$I.apply(this, [counter])) {
if (sb == null ) {
sb = Clazz.new_((I$[2]||$incl$(2)));
if (counter > 0) {
sb.append$S(string.substring(0, counter));
}last = counter + 1;
} else if (last != counter) {
sb.append$S(string.substring(last, counter));
}last = counter + 1;
}}
if (sb == null ) {
return string;
} else if (last != string.length$()) {
sb.append$S(string.substring(last));
}return sb.toString();
});

Clazz.newMeth(C$, 'isNavigatable$I', function (offset) {
if (!this.getAllowsInvalid()) {
return (offset < p$.getMaxLength.apply(this, []) && !p$.isLiteral$I.apply(this, [offset]) );
}return true;
});

Clazz.newMeth(C$, 'isValidEdit$javax_swing_text_DefaultFormatter_ReplaceHolder', function (rh) {
if (!this.getAllowsInvalid()) {
var newString = this.getReplaceString$I$I$S(rh.offset, rh.length, rh.text);
try {
rh.value = p$.stringToValue$S$Z.apply(this, [newString, false]);
return true;
} catch (pe) {
if (Clazz.exceptionOf(pe, "java.text.ParseException")){
return false;
} else {
throw pe;
}
}
}return true;
});

Clazz.newMeth(C$, 'canReplace$javax_swing_text_DefaultFormatter_ReplaceHolder', function (rh) {
if (!this.getAllowsInvalid()) {
var replace = null;
var text = rh.text;
var tl = (text != null ) ? text.length$() : 0;
if (tl == 0 && rh.length == 1  && this.getFormattedTextField().getSelectionStart() != rh.offset ) {
while (rh.offset > 0 && p$.isLiteral$I.apply(this, [rh.offset]) ){
rh.offset--;
}
}var max = Math.min(p$.getMaxLength.apply(this, []) - rh.offset, Math.max(tl, rh.length));
for (var counter = 0, textIndex = 0; counter < max; counter++) {
if (textIndex < tl && p$.isValidCharacter$I$C.apply(this, [rh.offset + counter, text.charAt(textIndex)]) ) {
var aChar = text.charAt(textIndex);
if (aChar != p$.getCharacter$I$C.apply(this, [rh.offset + counter, aChar])) {
if (replace == null ) {
replace = Clazz.new_((I$[2]||$incl$(2)));
if (textIndex > 0) {
replace.append$S(text.substring(0, textIndex));
}}}if (replace != null ) {
replace.append$C(p$.getCharacter$I$C.apply(this, [rh.offset + counter, aChar]));
}textIndex++;
} else if (p$.isLiteral$I.apply(this, [rh.offset + counter])) {
if (replace != null ) {
replace.append$C(p$.getLiteral$I.apply(this, [rh.offset + counter]));
if (textIndex < tl) {
max = Math.min(max + 1, p$.getMaxLength.apply(this, []) - rh.offset);
}} else if (textIndex > 0) {
replace = Clazz.new_((I$[2]||$incl$(2)).c$$I,[max]);
replace.append$S(text.substring(0, textIndex));
replace.append$C(p$.getLiteral$I.apply(this, [rh.offset + counter]));
if (textIndex < tl) {
max = Math.min(max + 1, p$.getMaxLength.apply(this, []) - rh.offset);
} else if (rh.cursorPosition == -1) {
rh.cursorPosition = rh.offset + counter;
}} else {
rh.offset++;
rh.length--;
counter--;
max--;
}} else if (textIndex >= tl) {
if (replace == null ) {
replace = Clazz.new_((I$[2]||$incl$(2)));
if (text != null ) {
replace.append$S(text);
}}replace.append$C(this.getPlaceholderCharacter());
if (tl > 0 && rh.cursorPosition == -1 ) {
rh.cursorPosition = rh.offset + counter;
}} else {
return false;
}}
if (replace != null ) {
rh.text = replace.toString();
} else if (text != null  && rh.offset + tl > p$.getMaxLength.apply(this, []) ) {
rh.text = text.substring(0, p$.getMaxLength.apply(this, []) - rh.offset);
}if (this.getOverwriteMode() && rh.text != null  ) {
rh.length = rh.text.length$();
}}return C$.superclazz.prototype.canReplace$javax_swing_text_DefaultFormatter_ReplaceHolder.apply(this, [rh]);
});
;
(function(){var C$=Clazz.newClass(P$.MaskFormatter, "MaskCharacter", function(){
Clazz.newInstance(this, arguments[0],true,C$);
});

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isLiteral', function () {
return false;
});

Clazz.newMeth(C$, 'isValidCharacter$C', function (aChar) {
if (this.isLiteral()) {
return (this.getChar$C(aChar) == aChar);
}aChar = this.getChar$C(aChar);
var filter = this.this$0.getValidCharacters();
if (filter != null  && filter.indexOf(aChar) == -1 ) {
return false;
}filter = this.this$0.getInvalidCharacters();
if (filter != null  && filter.indexOf(aChar) != -1 ) {
return false;
}return true;
});

Clazz.newMeth(C$, 'getChar$C', function (aChar) {
return aChar;
});

Clazz.newMeth(C$, 'append$StringBuffer$S$IA$S', function (buff, formatting, index, placeholder) {
var inString = index[0] < formatting.length$();
var aChar = inString ? formatting.charAt(index[0]) : String.fromCharCode(0);
if (this.isLiteral()) {
buff.append$C(this.getChar$C(aChar));
if (this.this$0.getValueContainsLiteralCharacters()) {
if (inString && aChar != this.getChar$C(aChar) ) {
throw Clazz.new_(Clazz.load('java.text.ParseException').c$$S$I,["Invalid character: " + aChar, index[0]]);
}index[0] = index[0] + 1;
}} else if (index[0] >= formatting.length$()) {
if (placeholder != null  && index[0] < placeholder.length$() ) {
buff.append$C(placeholder.charAt(index[0]));
} else {
buff.append$C(this.this$0.getPlaceholderCharacter());
}index[0] = index[0] + 1;
} else if (this.isValidCharacter$C(aChar)) {
buff.append$C(this.getChar$C(aChar));
index[0] = index[0] + 1;
} else {
throw Clazz.new_(Clazz.load('java.text.ParseException').c$$S$I,["Invalid character: " + aChar, index[0]]);
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.MaskFormatter, "LiteralCharacter", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['javax.swing.text.MaskFormatter','javax.swing.text.MaskFormatter.MaskCharacter']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.fixedChar = '\0';
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$C', function (fixedChar) {
Clazz.super_(C$, this,1);
this.fixedChar = fixedChar;
}, 1);

Clazz.newMeth(C$, 'isLiteral', function () {
return true;
});

Clazz.newMeth(C$, 'getChar$C', function (aChar) {
return this.fixedChar;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.MaskFormatter, "DigitMaskCharacter", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['javax.swing.text.MaskFormatter','javax.swing.text.MaskFormatter.MaskCharacter']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isValidCharacter$C', function (aChar) {
return (Character.isDigit(aChar) && C$.superclazz.prototype.isValidCharacter$C.apply(this, [aChar]) );
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.MaskFormatter, "UpperCaseCharacter", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['javax.swing.text.MaskFormatter','javax.swing.text.MaskFormatter.MaskCharacter']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isValidCharacter$C', function (aChar) {
return (Character.isLetter(aChar) && C$.superclazz.prototype.isValidCharacter$C.apply(this, [aChar]) );
});

Clazz.newMeth(C$, 'getChar$C', function (aChar) {
return Character.toUpperCase(aChar);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.MaskFormatter, "LowerCaseCharacter", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['javax.swing.text.MaskFormatter','javax.swing.text.MaskFormatter.MaskCharacter']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isValidCharacter$C', function (aChar) {
return (Character.isLetter(aChar) && C$.superclazz.prototype.isValidCharacter$C.apply(this, [aChar]) );
});

Clazz.newMeth(C$, 'getChar$C', function (aChar) {
return Character.toLowerCase(aChar);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.MaskFormatter, "AlphaNumericCharacter", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['javax.swing.text.MaskFormatter','javax.swing.text.MaskFormatter.MaskCharacter']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isValidCharacter$C', function (aChar) {
return (Character.isLetterOrDigit(aChar) && C$.superclazz.prototype.isValidCharacter$C.apply(this, [aChar]) );
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.MaskFormatter, "CharCharacter", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['javax.swing.text.MaskFormatter','javax.swing.text.MaskFormatter.MaskCharacter']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isValidCharacter$C', function (aChar) {
return (Character.isLetter(aChar) && C$.superclazz.prototype.isValidCharacter$C.apply(this, [aChar]) );
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.MaskFormatter, "HexCharacter", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, ['javax.swing.text.MaskFormatter','javax.swing.text.MaskFormatter.MaskCharacter']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isValidCharacter$C', function (aChar) {
return ((aChar == "0" || aChar == "1"  || aChar == "2"  || aChar == "3"  || aChar == "4"  || aChar == "5"  || aChar == "6"  || aChar == "7"  || aChar == "8"  || aChar == "9"  || aChar == "a"  || aChar == "A"  || aChar == "b"  || aChar == "B"  || aChar == "c"  || aChar == "C"  || aChar == "d"  || aChar == "D"  || aChar == "e"  || aChar == "E"  || aChar == "f"  || aChar == "F" ) && C$.superclazz.prototype.isValidCharacter$C.apply(this, [aChar]) );
});

Clazz.newMeth(C$, 'getChar$C', function (aChar) {
if (Character.isDigit(aChar)) {
return aChar;
}return Character.toUpperCase(aChar);
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-08 10:02:57
