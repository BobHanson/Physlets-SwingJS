(function(){var P$=Clazz.newPackage("swingjs"),I$=[['javajs.util.SB',['swingjs.JSAbstractDocument','.JSElement'],'swingjs.JSPosition','java.util.Hashtable']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSPlainDocument", null, 'swingjs.JSAbstractDocument');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.sb = Clazz.new_((I$[1]||$incl$(1)));
this.root = Clazz.new_((I$[2]||$incl$(2)), [this, null]);
}, 1);

Clazz.newMeth(C$, 'getLength', function () {
return this.sb.length$();
});

Clazz.newMeth(C$, 'getText$I$I', function (offset, length) {
this.checkLoc$I$I(offset, offset + length);
return this.sb.substring2$I$I(offset, offset + length);
});

Clazz.newMeth(C$, 'getText$I$I$javax_swing_text_Segment', function (offset, length, chars) {
this.checkLoc$I$I(offset, offset + length);
if (this.tempChar == null ) {
this.tempChar = Clazz.array(Character.TYPE, [this.sb.length$()]);
for (var i = this.tempChar.length; --i >= 0; ) this.tempChar[i] = this.sb.charAt$I(i);

}chars.array = this.tempChar;
chars.offset = offset;
chars.count = length;
});

Clazz.newMeth(C$, 'getStartPosition', function () {
return Clazz.new_((I$[3]||$incl$(3)).c$$I,[0]);
});

Clazz.newMeth(C$, 'getEndPosition', function () {
return Clazz.new_((I$[3]||$incl$(3)).c$$I,[this.sb.length$()]);
});

Clazz.newMeth(C$, 'createPosition$I', function (offs) {
this.checkLoc$I$I(offs, offs);
var i = Integer.$valueOf(offs);
if (this.positions == null ) this.positions = Clazz.new_((I$[4]||$incl$(4)));
var p = this.positions.get$O(i);
if (p == null ) this.positions.put$TK$TV(i, p = Clazz.new_((I$[3]||$incl$(3)).c$$I,[offs]));
return p;
});

Clazz.newMeth(C$, 'getDefaultRootElement', function () {
return this.root;
});

Clazz.newMeth(C$, 'render$Runnable', function (r) {
});
})();
//Created 2018-02-06 09:00:31