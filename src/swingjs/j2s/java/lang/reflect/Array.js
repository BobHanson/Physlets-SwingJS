(function(){var P$=java.lang.reflect,I$=[];
var C$=Clazz.newClass(P$, "Array");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'newInstance', function (componentType, length) {
return C$.newArray(componentType, length);
}, 1);

Clazz.newMeth(C$, 'newInstance', function (componentType, dimensions) {
return C$.multiNewArray(componentType, dimensions);
}, 1);

Clazz.newMeth(C$, 'getAval', function (array, index) {
C$.checkArray(array, index, true);
return (array)[index];
}, 1);

Clazz.newMeth(C$, 'checkArray', function (array, index, checkIndex) {
if (array == null ) throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
if (checkIndex && (index < 0 || index >= (array).length ) ) throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}, 1);

Clazz.newMeth(C$, 'getLength', function (array) {
C$.checkArray(array, 0, false);
return (array).length;
}, 1);

Clazz.newMeth(C$, 'get', function (array, index) {
var x = C$.getAval(array, index);
{
switch (array.__ARRAYTYPE){ case "BA": return new Byte(x);
case "CA": return new Character(x); case "HA": return new Short(x); case "IA": return new Integer(x); case "JA": return new Long(x); case "ZA": return (x ? Boolean.TRUE : Boolean.FALSE); case "FA": return new Float(x); case "DA": return new Double(x); } return x;
}
}, 1);

Clazz.newMeth(C$, 'getBoolean', function (array, index) {
var x = C$.getAval(array, index);
var type = "";
var val = false;
{
type = array.__ARRAYTYPE; val = x;
}
switch (type) {
case "ZA":
return val;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'getByte', function (array, index) {
var x = C$.getAval(array, index);
var type = "";
var val = ($b$[0] = 0, $b$[0]);
{
type = array.__ARRAYTYPE; val = x;
}
switch (type) {
case "BA":
return $b$[0] = val, $b$[0];
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'getChar', function (array, index) {
var x = C$.getAval(array, index);
var type = "";
var val = "\u0000";
{
type = array.__ARRAYTYPE; val = x;
}
switch (type) {
case "CA":
return val;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'getShort', function (array, index) {
var x = C$.getAval(array, index);
var type = "";
var val = 0;
{
type = array.__ARRAYTYPE; val = x;
}
switch (type) {
case "BA":
case "HA":
return val;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'getInt', function (array, index) {
var x = C$.getAval(array, index);
var type = "";
var val = 0;
{
type = array.__ARRAYTYPE; val = x;
}
switch (type) {
case "BA":
case "HA":
case "IA":
return val;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'getLong', function (array, index) {
var x = C$.getAval(array, index);
var type = "";
var val = 0;
{
type = array.__ARRAYTYPE; val = x;
}
switch (type) {
case "BA":
case "HA":
case "IA":
case "JA":
return val;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'getFloat', function (array, index) {
var x = C$.getAval(array, index);
var type = "";
var val = 0;
{
type = array.__ARRAYTYPE; val = x;
}
switch (type) {
case "BA":
case "HA":
case "IA":
case "JA":
case "FA":
return val;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'getDouble', function (array, index) {
var x = C$.getAval(array, index);
var type = "";
var val = 0;
{
type = array.__ARRAYTYPE; val = x;
}
switch (type) {
case "BA":
case "HA":
case "IA":
case "JA":
case "FA":
case "DA":
return val;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'set', function (array, index, value) {
C$.checkArray(array, index, true);
var type = "";
{
type = array.__ARRAYTYPE;
}
try {
switch (type) {
case "BA":
(array)[index]=((value).byteValue()|0);
return;
case "CA":
(array)[index]=(value).charValue();
return;
case "HA":
(array)[index]=(value).shortValue();
return;
case "IA":
(array)[index]=(value).intValue();
return;
case "JA":
(array)[index]=(value).longValue();
return;
case "ZA":
(array)[index]=(value).booleanValue();
return;
case "FA":
(array)[index]=(value).floatValue();
return;
case "DA":
(array)[index]=(value).doubleValue();
return;
default:
{
(array)[index]=value;
}}
} catch (e) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}
}, 1);

Clazz.newMeth(C$, 'setBoolean', function (array, index, z) {
C$.checkArray(array, index, true);
var type = "";
{
type = array.__ARRAYTYPE;
}
switch (type) {
case "ZA":
(array)[index]=z;
return;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'setByte', function (array, index, b) {
var type = "";
{
type = array.__ARRAYTYPE;
}
switch (type) {
case "BA":
case "HA":
case "IA":
case "JA":
case "FA":
case "DA":
(array)[index]=(b|0);
return;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'setChar', function (array, index, c) {
var type = "";
{
type = array.__ARRAYTYPE;
}
switch (type) {
case "CA":
(array)[index]=c;
return;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'setShort', function (array, index, s) {
var type = "";
{
type = array.__ARRAYTYPE;
}
switch (type) {
case "HA":
case "IA":
case "JA":
case "FA":
case "DA":
(array)[index]=s;
return;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'setInt', function (array, index, i) {
var type = "";
{
type = array.__ARRAYTYPE;
}
switch (type) {
case "IA":
case "JA":
case "FA":
case "DA":
(array)[index]=i;
return;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'setLong', function (array, index, l) {
var type = "";
{
type = array.__ARRAYTYPE;
}
switch (type) {
case "JA":
case "FA":
case "DA":
(array)[index]=l;
return;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'setFloat', function (array, index, f) {
var type = "";
{
type = array.__ARRAYTYPE;
}
switch (type) {
case "FA":
case "DA":
(array)[index]=f;
return;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'setDouble', function (array, index, d) {
var type = "";
{
type = array.__ARRAYTYPE;
}
switch (type) {
case "DA":
(array)[index]=d;
return;
}
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}, 1);

Clazz.newMeth(C$, 'newArray', function (componentType, length) {
{
return Clazz.array(componentType, length);
}
}, 1);

Clazz.newMeth(C$, 'multiNewArray', function (componentType, dimensions) {
{
return Clazz.array(componentType, dimensions);
}
}, 1);
var $b$ = new Int8Array(1);
})();
//Created 2018-05-24 08:45:40
