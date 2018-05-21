(function(){var P$=Clazz.newPackage("java.text"),I$=[[['java.text.FieldPosition','.Delegate']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FieldPosition", function(){
Clazz.newInstance(this, arguments,0,C$);
});
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.field = 0;
this.endIndex = 0;
this.beginIndex = 0;
this.attribute = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.field = 0;
this.endIndex = 0;
this.beginIndex = 0;
}, 1);

Clazz.newMeth(C$, 'c$$I', function (field) {
C$.$init$.apply(this);
this.field = field;
}, 1);

Clazz.newMeth(C$, 'c$$java_text_Format_Field', function (attribute) {
C$.c$$java_text_Format_Field$I.apply(this, [attribute, -1]);
}, 1);

Clazz.newMeth(C$, 'c$$java_text_Format_Field$I', function (attribute, fieldID) {
C$.$init$.apply(this);
this.attribute = attribute;
this.field = fieldID;
}, 1);

Clazz.newMeth(C$, 'getFieldAttribute', function () {
return this.attribute;
});

Clazz.newMeth(C$, 'getField', function () {
return this.field;
});

Clazz.newMeth(C$, 'getBeginIndex', function () {
return this.beginIndex;
});

Clazz.newMeth(C$, 'getEndIndex', function () {
return this.endIndex;
});

Clazz.newMeth(C$, 'setBeginIndex$I', function (bi) {
this.beginIndex = bi;
});

Clazz.newMeth(C$, 'setEndIndex$I', function (ei) {
this.endIndex = ei;
});

Clazz.newMeth(C$, 'getFieldDelegate', function () {
return Clazz.new_((I$[1]||$incl$(1)), [this, null]);
});

Clazz.newMeth(C$, 'equals$O', function (obj) {
if (obj == null ) return false;
if (!(Clazz.instanceOf(obj, "java.text.FieldPosition"))) return false;
var other = obj;
if (this.attribute == null ) {
if (other.attribute != null ) {
return false;
}} else if (!this.attribute.equals$O(other.attribute)) {
return false;
}return (this.beginIndex == other.beginIndex && this.endIndex == other.endIndex  && this.field == other.field );
});

Clazz.newMeth(C$, 'hashCode', function () {
return (this.field << 24) | (this.beginIndex << 16) | this.endIndex ;
});

Clazz.newMeth(C$, 'toString', function () {
return this.getClass().getName() + "[field=" + this.field + ",attribute=" + this.attribute + ",beginIndex=" + this.beginIndex + ",endIndex=" + this.endIndex + ']' ;
});

Clazz.newMeth(C$, 'matchesField$java_text_Format_Field', function (attribute) {
if (this.attribute != null ) {
return this.attribute.equals$O(attribute);
}return false;
});

Clazz.newMeth(C$, 'matchesField$java_text_Format_Field$I', function (attribute, field) {
if (this.attribute != null ) {
return this.attribute.equals$O(attribute);
}return (field == this.field);
});
;
(function(){var C$=Clazz.newClass(P$.FieldPosition, "Delegate", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, [['java.text.Format','java.text.Format.FieldDelegate']]);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.encounteredField = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'formatted$java_text_Format_Field$O$I$I$StringBuffer', function (attr, value, start, end, buffer) {
if (!this.encounteredField && this.this$0.matchesField$java_text_Format_Field.apply(this.this$0, [attr]) ) {
this.this$0.setBeginIndex$I(start);
this.this$0.setEndIndex$I(end);
this.encounteredField = (start != end);
}});

Clazz.newMeth(C$, 'formatted$I$java_text_Format_Field$O$I$I$StringBuffer', function (fieldID, attr, value, start, end, buffer) {
if (!this.encounteredField && this.this$0.matchesField$java_text_Format_Field$I.apply(this.this$0, [attr, fieldID]) ) {
this.this$0.setBeginIndex$I(start);
this.this$0.setEndIndex$I(end);
this.encounteredField = (start != end);
}});

Clazz.newMeth(C$);
})()

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:11
