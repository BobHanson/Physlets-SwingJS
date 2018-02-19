(function(){var P$=Clazz.newPackage("javax.swing.event"),I$=[];
var C$=Clazz.newClass(P$, "EventListenerList");
C$.NULL_ARRAY = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.NULL_ARRAY = Clazz.array(java.lang.Object, [0]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.listenerList = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.listenerList = C$.NULL_ARRAY;
}, 1);

Clazz.newMeth(C$, 'getListenerList', function () {
return this.listenerList;
});

Clazz.newMeth(C$, 'getListeners$Class', function (t) {
var lList = this.listenerList;
var n = p$.getListenerCount$OA$Class.apply(this, [lList, t]);
var result = Clazz.array(t, n);
var j = 0;
for (var i = lList.length - 2; i >= 0; i = i-(2)) {
if (lList[i] === t ) {
result[j++] = lList[i + 1];
}}
return result;
});

Clazz.newMeth(C$, 'getListenerCount', function () {
return (this.listenerList.length/2|0);
});

Clazz.newMeth(C$, 'getListenerCount$Class', function (t) {
var lList = this.listenerList;
return p$.getListenerCount$OA$Class.apply(this, [lList, t]);
});

Clazz.newMeth(C$, 'getListenerCount$OA$Class', function (list, t) {
var count = 0;
for (var i = 0; i < list.length; i = i+(2)) {
if (t === list[i] ) count++;
}
return count;
});

Clazz.newMeth(C$, 'add$Class$TT', function (t, l) {
if (l == null ) {
return;
}if (this.listenerList === C$.NULL_ARRAY ) {
this.listenerList = Clazz.array(java.lang.Object, -1, [t, l]);
} else {
var i = this.listenerList.length;
var tmp = Clazz.array(java.lang.Object, [i + 2]);
System.arraycopy(this.listenerList, 0, tmp, 0, i);
tmp[i] = t;
tmp[i + 1] = l;
this.listenerList = tmp;
}});

Clazz.newMeth(C$, 'remove$Class$TT', function (t, l) {
if (l == null ) {
return;
}var index = -1;
for (var i = this.listenerList.length - 2; i >= 0; i = i-(2)) {
if ((this.listenerList[i] === t ) && (this.listenerList[i + 1].equals$O(l) == true ) ) {
index = i;
break;
}}
if (index != -1) {
var tmp = Clazz.array(java.lang.Object, [this.listenerList.length - 2]);
System.arraycopy(this.listenerList, 0, tmp, 0, index);
if (index < tmp.length) System.arraycopy(this.listenerList, index + 2, tmp, index, tmp.length - index);
this.listenerList = (tmp.length == 0) ? C$.NULL_ARRAY : tmp;
}});

Clazz.newMeth(C$, 'toString', function () {
var lList = this.listenerList;
var s = "EventListenerList: ";
s += (lList.length/2|0) + " listeners: ";
for (var i = 0; i <= lList.length - 2; i = i+(2)) {
s += " type " + (lList[i]).getName();
s += " listener " + lList[i + 1];
}
return s;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:47
