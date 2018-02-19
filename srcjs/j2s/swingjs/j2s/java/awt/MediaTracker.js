(function(){var P$=Clazz.newPackage("java.awt"),I$=[];
var C$=Clazz.newClass(P$, "MediaTracker", null, null, 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.target = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Component', function (comp) {
C$.$init$.apply(this);
this.target = comp;
}, 1);

Clazz.newMeth(C$, 'addImage$java_awt_Image$I', function (image, id) {
this.addImage$java_awt_Image$I$I$I(image, id, -1, -1);
});

Clazz.newMeth(C$, 'addImage$java_awt_Image$I$I$I', function (image, id, w, h) {
});

Clazz.newMeth(C$, 'checkAll', function () {
return true;
});

Clazz.newMeth(C$, 'checkAll$Z', function (load) {
return true;
});

Clazz.newMeth(C$, 'isErrorAny', function () {
return false;
});

Clazz.newMeth(C$, 'getErrorsAny', function () {
return null;
});

Clazz.newMeth(C$, 'waitForAll', function () {
return;
});

Clazz.newMeth(C$, 'waitForAll$J', function (ms) {
return true;
});

Clazz.newMeth(C$, 'statusAll$Z', function (load) {
return 8;
});

Clazz.newMeth(C$, 'checkID$I', function (id) {
return true;
});

Clazz.newMeth(C$, 'checkID$I$Z', function (id, load) {
return true;
});

Clazz.newMeth(C$, 'isErrorID$I', function (id) {
return false;
});

Clazz.newMeth(C$, 'getErrorsID$I', function (id) {
return null;
});

Clazz.newMeth(C$, 'waitForID$I', function (id) {
return;
});

Clazz.newMeth(C$, 'waitForID$I$J', function (id, ms) {
return true;
});

Clazz.newMeth(C$, 'statusID$I$Z', function (id, load) {
return 8;
});

Clazz.newMeth(C$, 'removeImage$java_awt_Image', function (image) {
});

Clazz.newMeth(C$, 'removeImage$java_awt_Image$I', function (image, id) {
});

Clazz.newMeth(C$, 'removeImage$java_awt_Image$I$I$I', function (image, id, width, height) {
});

Clazz.newMeth(C$, 'setDone', function () {
return;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:01:50
