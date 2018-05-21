(function(){var P$=Clazz.newPackage("java.security"),I$=[];
var C$=Clazz.newClass(P$, "AccessController", null, null, 'java.security.AccessControlContext');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'doPrivileged$java_security_PrivilegedAction', function (action) {
return action.run();
}, 1);

Clazz.newMeth(C$, 'getContext', function () {
return Clazz.new_(C$);
}, 1);

Clazz.newMeth(C$, 'checkPermission$O', function (perm) {
return true;
});

Clazz.newMeth(C$, 'doPrivileged$java_security_PrivilegedAction$java_security_AccessControlContext', function (action, context) {
return action.run();
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:10
