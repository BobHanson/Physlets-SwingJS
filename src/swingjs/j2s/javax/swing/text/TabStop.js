(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[];
var C$=Clazz.newClass(P$, "TabStop");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.alignment = 0;
this.position = 0;
this.leader = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$F', function (pos) {
C$.c$$F$I$I.apply(this, [pos, 0, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$F$I$I', function (pos, align, leader) {
C$.$init$.apply(this);
this.alignment=align;
this.leader=leader;
this.position=pos;
}, 1);

Clazz.newMeth(C$, 'getPosition', function () {
return this.position;
});

Clazz.newMeth(C$, 'getAlignment', function () {
return this.alignment;
});

Clazz.newMeth(C$, 'getLeader', function () {
return this.leader;
});

Clazz.newMeth(C$, 'equals$O', function (other) {
if (other === this ) {
return true;
}if (Clazz.instanceOf(other, "javax.swing.text.TabStop")) {
var o = other;
return ((this.alignment == o.alignment) && (this.leader == o.leader) && (this.position == o.position )  );
}return false;
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.alignment ^ this.leader ^ Math.round(this.position) ;
});

Clazz.newMeth(C$, 'toString', function () {
var buf;
switch (this.alignment) {
default:
case 0:
buf="";
break;
case 1:
buf="right ";
break;
case 2:
buf="center ";
break;
case 4:
buf="decimal ";
break;
case 5:
buf="bar ";
break;
}
buf=buf + "tab @" + String.valueOf(this.position) ;
if (this.leader != 0) buf=buf + " (w/leaders)";
return buf;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:09