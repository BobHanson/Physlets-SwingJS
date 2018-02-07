(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[['javax.swing.text.TabStop','java.lang.StringBuffer']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "TabSet");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.tabs = null;
this.$hashCode = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$hashCode = 2147483647;
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_text_TabStopA', function (tabs) {
C$.$init$.apply(this);
if (tabs != null ) {
var tabCount = tabs.length;
this.tabs = Clazz.array((I$[1]||$incl$(1)), [tabCount]);
System.arraycopy(tabs, 0, this.tabs, 0, tabCount);
} else this.tabs = null;
}, 1);

Clazz.newMeth(C$, 'getTabCount', function () {
return (this.tabs == null ) ? 0 : this.tabs.length;
});

Clazz.newMeth(C$, 'getTab$I', function (index) {
var numTabs = this.getTabCount();
if (index < 0 || index >= numTabs ) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,[index + " is outside the range of tabs"]);
return this.tabs[index];
});

Clazz.newMeth(C$, 'getTabAfter$F', function (location) {
var index = this.getTabIndexAfter$F(location);
return (index == -1) ? null : this.tabs[index];
});

Clazz.newMeth(C$, 'getTabIndex$javax_swing_text_TabStop', function (tab) {
for (var counter = this.getTabCount() - 1; counter >= 0; counter--) if (this.getTab$I(counter) === tab ) return counter;

return -1;
});

Clazz.newMeth(C$, 'getTabIndexAfter$F', function (location) {
var current;
var min;
var max;
min = 0;
max = this.getTabCount();
while (min != max){
current = ((max - min)/2|0) + min;
if (location > this.tabs[current].getPosition() ) {
if (min == current) min = max;
 else min = current;
} else {
if (current == 0 || location > this.tabs[current - 1].getPosition()  ) return current;
max = current;
}}
return -1;
});

Clazz.newMeth(C$, 'equals$O', function (o) {
if (o === this ) {
return true;
}if (Clazz.instanceOf(o, "javax.swing.text.TabSet")) {
var ts = o;
var count = this.getTabCount();
if (ts.getTabCount() != count) {
return false;
}for (var i = 0; i < count; i++) {
var ts1 = this.getTab$I(i);
var ts2 = ts.getTab$I(i);
if ((ts1 == null  && ts2 != null  ) || (ts1 != null  && !this.getTab$I(i).equals$O(ts.getTab$I(i)) ) ) {
return false;
}}
return true;
}return false;
});

Clazz.newMeth(C$, 'hashCode', function () {
if (this.$hashCode == 2147483647) {
this.$hashCode = 0;
var len = this.getTabCount();
for (var i = 0; i < len; i++) {
var ts = this.getTab$I(i);
this.$hashCode = this.$hashCode^(ts != null  ? this.getTab$I(i).hashCode() : 0);
}
if (this.$hashCode == 2147483647) {
this.$hashCode = this.$hashCode-(1);
}}return this.$hashCode;
});

Clazz.newMeth(C$, 'toString', function () {
var tabCount = this.getTabCount();
var buffer = Clazz.new_((I$[2]||$incl$(2)).c$$S,["[ "]);
for (var counter = 0; counter < tabCount; counter++) {
if (counter > 0) buffer.append$S(" - ");
buffer.append$S(this.getTab$I(counter).toString());
}
buffer.append$S(" ]");
return buffer.toString();
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 09:00:01