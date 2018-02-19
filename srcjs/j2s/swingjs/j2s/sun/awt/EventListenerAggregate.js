(function(){var P$=Clazz.newPackage("sun.awt"),I$=[['java.util.EventListener','java.lang.reflect.Array']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "EventListenerAggregate");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.listenerList = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$Class', function (listenerClass) {
C$.$init$.apply(this);
if (listenerClass == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException').c$$S,["listener class is null"]);
}if (!Clazz.getClass((I$[1]||$incl$(1)),[]).isAssignableFrom$Class(listenerClass)) {
throw Clazz.new_(Clazz.load('java.lang.ClassCastException').c$$S,["listener class " + listenerClass + " is not assignable to EventListener" ]);
}this.listenerList = Clazz.array(listenerClass, 0);
}, 1);

Clazz.newMeth(C$, 'getListenerClass', function () {
return this.listenerList.getClass().getComponentType();
});

Clazz.newMeth(C$, 'add$java_util_EventListener', function (listener) {
var listenerClass = p$.getListenerClass.apply(this, []);
if (!listenerClass.isInstance$O(listener)) {
throw Clazz.new_(Clazz.load('java.lang.ClassCastException').c$$S,["listener " + listener + " is not " + "an instance of listener class " + listenerClass ]);
}var tmp = Clazz.array(listenerClass, this.listenerList.length + 1);
System.arraycopy(this.listenerList, 0, tmp, 0, this.listenerList.length);
tmp[this.listenerList.length] = listener;
this.listenerList = tmp;
});

Clazz.newMeth(C$, 'remove$java_util_EventListener', function (listener) {
var listenerClass = p$.getListenerClass.apply(this, []);
if (!listenerClass.isInstance$O(listener)) {
throw Clazz.new_(Clazz.load('java.lang.ClassCastException').c$$S,["listener " + listener + " is not " + "an instance of listener class " + listenerClass ]);
}for (var i = 0; i < this.listenerList.length; i++) {
if (this.listenerList[i].equals$O(listener)) {
var tmp = Clazz.array(listenerClass, this.listenerList.length - 1);
System.arraycopy(this.listenerList, 0, tmp, 0, i);
System.arraycopy(this.listenerList, i + 1, tmp, i, this.listenerList.length - i - 1 );
this.listenerList = tmp;
return true;
}}
return false;
});

Clazz.newMeth(C$, 'getListenersInternal', function () {
return this.listenerList;
});

Clazz.newMeth(C$, 'getListenersCopy', function () {
return (this.listenerList.length == 0) ? this.listenerList : this.listenerList.clone();
});

Clazz.newMeth(C$, 'size', function () {
return this.listenerList.length;
});

Clazz.newMeth(C$, 'isEmpty', function () {
return this.listenerList.length == 0;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:08
