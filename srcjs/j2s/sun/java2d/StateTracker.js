(function(){var P$=Clazz.newPackage("sun.java2d"),I$=[['sun.java2d.StateTracker$1','sun.java2d.StateTracker$2']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newInterface(P$, "StateTracker");
C$.ALWAYS_CURRENT = null;
C$.NEVER_CURRENT = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.ALWAYS_CURRENT = ((
(function(){var C$=Clazz.newClass(P$, "StateTracker$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'sun.java2d.StateTracker', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isCurrent', function () {
return true;
});
})()
), Clazz.new_((I$[1]||$incl$(1)).$init$, [this, null]));
C$.NEVER_CURRENT = ((
(function(){var C$=Clazz.newClass(P$, "StateTracker$2", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'sun.java2d.StateTracker', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isCurrent', function () {
return false;
});
})()
), Clazz.new_((I$[2]||$incl$(2)).$init$, [this, null]));
}
})();
//Created 2018-02-06 09:00:20
