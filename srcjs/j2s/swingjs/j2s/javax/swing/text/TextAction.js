(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[['java.util.Hashtable','javax.swing.Action','javax.swing.text.JTextComponent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "TextAction", null, 'javax.swing.AbstractAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.superclazz.c$$S.apply(this, [name]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getTextComponent$java_awt_event_ActionEvent', function (e) {
if (e != null ) {
var o = e.getSource();
if (Clazz.instanceOf(o, "javax.swing.text.JTextComponent")) {
return o;
}}return this.getFocusedComponent();
});

Clazz.newMeth(C$, 'augmentList$javax_swing_ActionA$javax_swing_ActionA', function (list1, list2) {
var h = Clazz.new_((I$[1]||$incl$(1)));
if (list1 != null ) for (var i = 0; i < list1.length; i++) {
var a = list1[i];
var value = a.getValue$S("Name");
h.put$TK$TV((value != null  ? value : ""), a);
}
for (var i = 0; i < list2.length; i++) {
var a = list2[i];
var value = a.getValue$S("Name");
h.put$TK$TV((value != null  ? value : ""), a);
}
var actions = Clazz.array((I$[2]||$incl$(2)), [h.size()]);
var index = 0;
for (var e = h.elements(); e.hasMoreElements(); ) {
actions[index++] = e.nextElement();
}
return actions;
}, 1);

Clazz.newMeth(C$, 'getFocusedComponent', function () {
return (I$[3]||$incl$(3)).getFocusedComponent();
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:59
