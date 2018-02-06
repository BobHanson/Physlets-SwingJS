(function(){var P$=Clazz.newPackage("java.awt"),I$=[];
var C$=Clazz.newClass(P$, "Cursor");
C$.predefined = null;
C$.predefinedPrivate = null;
C$.cursorProperties = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.predefined = Clazz.array(C$, [14]);
C$.predefinedPrivate = Clazz.array(C$, [14]);
C$.cursorProperties = Clazz.array(java.lang.String, -2, [Clazz.array(java.lang.String, -1, ["AWT.DefaultCursor", "Default Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.CrosshairCursor", "Crosshair Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.TextCursor", "Text Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.WaitCursor", "Wait Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.SWResizeCursor", "Southwest Resize Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.SEResizeCursor", "Southeast Resize Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.NWResizeCursor", "Northwest Resize Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.NEResizeCursor", "Northeast Resize Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.NResizeCursor", "North Resize Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.SResizeCursor", "South Resize Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.WResizeCursor", "West Resize Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.EResizeCursor", "East Resize Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.HandCursor", "Hand Cursor"]), Clazz.array(java.lang.String, -1, ["AWT.MoveCursor", "Move Cursor"])]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.type = 0;
this.name = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.type = 0;
}, 1);

Clazz.newMeth(C$, 'getPredefinedCursor$I', function (type) {
if (type < 0 || type > 13 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["illegal cursor type"]);
}var c = C$.predefinedPrivate[type];
if (c == null ) {
C$.predefinedPrivate[type] = c = Clazz.new_(C$.c$$I,[type]);
}if (C$.predefined[type] == null ) {
C$.predefined[type] = c;
}return c;
}, 1);

Clazz.newMeth(C$, 'getSystemCustomCursor$S', function (name) {
return null;
}, 1);

Clazz.newMeth(C$, 'getDefaultCursor', function () {
return C$.getPredefinedCursor$I(0);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (type) {
C$.$init$.apply(this);
if (type < 0 || type > 13 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["illegal cursor type"]);
}this.type = type;
this.name = "TODO_CURSOR";
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.$init$.apply(this);
this.type = -1;
this.name = name;
}, 1);

Clazz.newMeth(C$, 'getType', function () {
return this.type;
});

Clazz.newMeth(C$, 'getName', function () {
return this.name;
});

Clazz.newMeth(C$, 'toString', function () {
return this.getClass().getName() + "[" + this.getName() + "]" ;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:08
