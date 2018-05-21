(function(){var P$=java.util,I$=[['java.util.ListResourceBundle$1','java.util.Hashtable']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ListResourceBundle", null, 'java.util.ResourceBundle');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.table = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getKeys', function () {
if (this.table == null ) {
p$.initializeTable.apply(this, []);
}if (this.parent == null ) {
return this.table.keys();
}return ((
(function(){var C$=Clazz.newClass(P$, "ListResourceBundle$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.Enumeration', 1);
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.local = null;
this.pEnum = null;
this.$nextElement = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.local = this.b$['java.util.ListResourceBundle'].table.keys();
this.pEnum = this.b$['java.util.ListResourceBundle'].parent.getKeys();
}, 1);

Clazz.newMeth(C$, 'findNext', function () {
if (this.$nextElement != null ) {
return true;
}while (this.pEnum.hasMoreElements()){
var next = this.pEnum.nextElement();
if (!this.b$['java.util.ListResourceBundle'].table.containsKey$O(next)) {
this.$nextElement = next;
return true;
}}
return false;
});

Clazz.newMeth(C$, 'hasMoreElements', function () {
if (this.local.hasMoreElements()) {
return true;
}return p$.findNext.apply(this, []);
});

Clazz.newMeth(C$, 'nextElement', function () {
if (this.local.hasMoreElements()) {
return this.local.nextElement();
}if (p$.findNext.apply(this, [])) {
var result = this.$nextElement;
this.$nextElement = null;
return result;
}return this.pEnum.nextElement();
});
})()
), Clazz.new_((I$[1]||$incl$(1)).$init$, [this, null]));
});

Clazz.newMeth(C$, 'handleGetObject$S', function (key) {
if (this.table == null ) {
p$.initializeTable.apply(this, []);
}return this.table.get$O(key);
});

Clazz.newMeth(C$, 'initializeTable', function () {
if (this.table == null ) {
var contents = this.getContents();
this.table = Clazz.new_((I$[2]||$incl$(2)).c$$I,[(contents.length/3|0) * 4 + 3]);
for (var i = 0; i < contents.length; i++) {
this.table.put$TK$TV(contents[i][0], contents[i][1]);
}
}});
})();
//Created 2018-05-15 01:02:13
