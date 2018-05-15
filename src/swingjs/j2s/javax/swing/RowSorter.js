(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['javax.swing.event.EventListenerList','javax.swing.event.RowSorterListener','javax.swing.event.RowSorterEvent',['javax.swing.event.RowSorterEvent','.Type']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "RowSorter", function(){
Clazz.newInstance(this, arguments,0,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.listenerList = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.listenerList = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'addRowSorterListener$javax_swing_event_RowSorterListener', function (l) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[2]||$incl$(2)),['sorterChanged$javax_swing_event_RowSorterEvent']), l);
});

Clazz.newMeth(C$, 'removeRowSorterListener$javax_swing_event_RowSorterListener', function (l) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[2]||$incl$(2)),['sorterChanged$javax_swing_event_RowSorterEvent']), l);
});

Clazz.newMeth(C$, 'fireSortOrderChanged', function () {
this.fireRowSorterChanged$javax_swing_event_RowSorterEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_RowSorter,[this]));
});

Clazz.newMeth(C$, 'fireRowSorterChanged$IA', function (lastRowIndexToModel) {
this.fireRowSorterChanged$javax_swing_event_RowSorterEvent(Clazz.new_((I$[3]||$incl$(3)).c$$javax_swing_RowSorter$javax_swing_event_RowSorterEvent_Type$IA,[this, (I$[4]||$incl$(4)).SORTED, lastRowIndexToModel]));
});

Clazz.newMeth(C$, 'fireRowSorterChanged$javax_swing_event_RowSorterEvent', function (event) {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[2]||$incl$(2)),['sorterChanged$javax_swing_event_RowSorterEvent']) ) {
(listeners[i + 1]).sorterChanged$javax_swing_event_RowSorterEvent(event);
}}
});
;
(function(){var C$=Clazz.newClass(P$.RowSorter, "SortKey", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.column = 0;
this.sortOrder = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$javax_swing_SortOrder', function (column, sortOrder) {
C$.$init$.apply(this);
if (sortOrder == null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["sort order must be non-null"]);
}this.column = column;
this.sortOrder = sortOrder;
}, 1);

Clazz.newMeth(C$, 'getColumn', function () {
return this.column;
});

Clazz.newMeth(C$, 'getSortOrder', function () {
return this.sortOrder;
});

Clazz.newMeth(C$, 'hashCode', function () {
var result = 17;
result = 37 * result + this.column;
result = 37 * result + this.sortOrder.hashCode();
return result;
});

Clazz.newMeth(C$, 'equals$O', function (o) {
if (o === this ) {
return true;
}if (Clazz.instanceOf(o, "javax.swing.RowSorter.SortKey")) {
return ((o).column == this.column && (o).sortOrder === this.sortOrder  );
}return false;
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-15 01:02:39
