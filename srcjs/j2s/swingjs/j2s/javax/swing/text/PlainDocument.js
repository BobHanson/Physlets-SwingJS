(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[['java.util.Vector','java.lang.Boolean','java.lang.StringBuffer','javax.swing.text.Element','javax.swing.text.Segment',['javax.swing.text.AbstractDocument','.ElementEdit'],'javax.swing.text.Utilities','java.lang.Error',['javax.swing.text.AbstractDocument','.BranchElement']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PlainDocument", null, 'javax.swing.text.AbstractDocument');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.defaultRoot = null;
this.added = null;
this.removed = null;
this.s = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.added = Clazz.new_((I$[1]||$incl$(1)));
this.removed = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$javax_swing_text_AbstractDocument_Content.apply(this, [null]);
}, 1);

Clazz.newMeth(C$, 'c$$javax_swing_text_AbstractDocument_Content', function (c) {
C$.superclazz.c$$javax_swing_text_AbstractDocument_Content.apply(this, [c]);
C$.$init$.apply(this);
this.putProperty$O$O("tabSize",  new Integer(8));
this.defaultRoot = this.createDefaultRoot();
}, 1);

Clazz.newMeth(C$, 'insertString$I$S$javax_swing_text_AttributeSet', function (offs, str, a) {
var filterNewlines = this.getProperty$O("filterNewlines");
if ((Clazz.instanceOf(filterNewlines, "java.lang.Boolean")) && filterNewlines.equals$O((I$[2]||$incl$(2)).TRUE) ) {
if ((str != null ) && (str.indexOf("\u000a") >= 0) ) {
var filtered = Clazz.new_((I$[3]||$incl$(3)).c$$S,[str]);
var n = filtered.length$();
for (var i = 0; i < n; i++) {
if (filtered.charAt$I(i) == "\u000a") {
filtered.setCharAt$I$C(i, " ");
}}
str = filtered.toString();
}}C$.superclazz.prototype.insertString$I$S$javax_swing_text_AttributeSet.apply(this, [offs, str, a]);
});

Clazz.newMeth(C$, 'getDefaultRootElement', function () {
return this.defaultRoot;
});

Clazz.newMeth(C$, 'createDefaultRoot', function () {
var map = this.createBranchElement$javax_swing_text_Element$javax_swing_text_AttributeSet(null, null);
var line = this.createLeafElement$javax_swing_text_Element$javax_swing_text_AttributeSet$I$I(map, null, 0, 1);
var lines = Clazz.array((I$[4]||$incl$(4)), [1]);
lines[0] = line;
map.replace$I$I$javax_swing_text_ElementA(0, 0, lines);
return map;
});

Clazz.newMeth(C$, 'getParagraphElement$I', function (pos) {
var lineMap = this.getDefaultRootElement();
return lineMap.getElement$I(lineMap.getElementIndex$I(pos));
});

Clazz.newMeth(C$, 'insertUpdate$javax_swing_text_AbstractDocument_DefaultDocumentEvent$javax_swing_text_AttributeSet', function (chng, attr) {
this.removed.removeAllElements();
this.added.removeAllElements();
var lineMap = this.getDefaultRootElement();
var offset = chng.getOffset();
var length = chng.getLength();
if (offset > 0) {
offset = offset-(1);
length = length+(1);
}var index = lineMap.getElementIndex$I(offset);
var rmCandidate = lineMap.getElement$I(index);
var rmOffs0 = rmCandidate.getStartOffset();
var rmOffs1 = rmCandidate.getEndOffset();
var lastOffset = rmOffs0;
try {
if (this.s == null ) {
this.s = Clazz.new_((I$[5]||$incl$(5)));
}this.getContent().getChars$I$I$javax_swing_text_Segment(offset, length, this.s);
var hasBreaks = false;
for (var i = 0; i < length; i++) {
var c = this.s.array[this.s.offset + i];
if (c == "\u000a") {
var breakOffset = offset + i + 1 ;
this.added.addElement$TE(this.createLeafElement$javax_swing_text_Element$javax_swing_text_AttributeSet$I$I(lineMap, null, lastOffset, breakOffset));
lastOffset = breakOffset;
hasBreaks = true;
}}
if (hasBreaks) {
this.removed.addElement$TE(rmCandidate);
if ((offset + length == rmOffs1) && (lastOffset != rmOffs1) && ((index + 1) < lineMap.getElementCount())  ) {
var e = lineMap.getElement$I(index + 1);
this.removed.addElement$TE(e);
rmOffs1 = e.getEndOffset();
}if (lastOffset < rmOffs1) {
this.added.addElement$TE(this.createLeafElement$javax_swing_text_Element$javax_swing_text_AttributeSet$I$I(lineMap, null, lastOffset, rmOffs1));
}var aelems = Clazz.array((I$[4]||$incl$(4)), [this.added.size()]);
this.added.copyInto$OA(aelems);
var relems = Clazz.array((I$[4]||$incl$(4)), [this.removed.size()]);
this.removed.copyInto$OA(relems);
var ee = Clazz.new_((I$[6]||$incl$(6)).c$$javax_swing_text_Element$I$javax_swing_text_ElementA$javax_swing_text_ElementA,[lineMap, index, relems, aelems]);
chng.addEdit$javax_swing_undo_UndoableEdit(ee);
lineMap.replace$I$I$javax_swing_text_ElementA(index, relems.length, aelems);
}if ((I$[7]||$incl$(7)).isComposedTextAttributeDefined$javax_swing_text_AttributeSet(attr)) {
p$.insertComposedTextUpdate$javax_swing_text_AbstractDocument_DefaultDocumentEvent$javax_swing_text_AttributeSet.apply(this, [chng, attr]);
}} catch (e) {
if (Clazz.exceptionOf(e, "javax.swing.text.BadLocationException")){
throw Clazz.new_((I$[8]||$incl$(8)).c$$S,["Internal error: " + e.toString()]);
} else {
throw e;
}
}
C$.superclazz.prototype.insertUpdate$javax_swing_text_AbstractDocument_DefaultDocumentEvent$javax_swing_text_AttributeSet.apply(this, [chng, attr]);
});

Clazz.newMeth(C$, 'removeUpdate$javax_swing_text_AbstractDocument_DefaultDocumentEvent', function (chng) {
this.removed.removeAllElements();
var map = this.getDefaultRootElement();
var offset = chng.getOffset();
var length = chng.getLength();
var line0 = map.getElementIndex$I(offset);
var line1 = map.getElementIndex$I(offset + length);
if (line0 != line1) {
for (var i = line0; i <= line1; i++) {
this.removed.addElement$TE(map.getElement$I(i));
}
var p0 = map.getElement$I(line0).getStartOffset();
var p1 = map.getElement$I(line1).getEndOffset();
var aelems = Clazz.array((I$[4]||$incl$(4)), [1]);
aelems[0] = this.createLeafElement$javax_swing_text_Element$javax_swing_text_AttributeSet$I$I(map, null, p0, p1);
var relems = Clazz.array((I$[4]||$incl$(4)), [this.removed.size()]);
this.removed.copyInto$OA(relems);
var ee = Clazz.new_((I$[6]||$incl$(6)).c$$javax_swing_text_Element$I$javax_swing_text_ElementA$javax_swing_text_ElementA,[map, line0, relems, aelems]);
chng.addEdit$javax_swing_undo_UndoableEdit(ee);
map.replace$I$I$javax_swing_text_ElementA(line0, relems.length, aelems);
} else {
var line = map.getElement$I(line0);
if (!line.isLeaf()) {
var leaf = line.getElement$I(line.getElementIndex$I(offset));
if ((I$[7]||$incl$(7)).isComposedTextElement$javax_swing_text_Element(leaf)) {
var aelem = Clazz.array((I$[4]||$incl$(4)), [1]);
aelem[0] = this.createLeafElement$javax_swing_text_Element$javax_swing_text_AttributeSet$I$I(map, null, line.getStartOffset(), line.getEndOffset());
var relem = Clazz.array((I$[4]||$incl$(4)), [1]);
relem[0] = line;
var ee = Clazz.new_((I$[6]||$incl$(6)).c$$javax_swing_text_Element$I$javax_swing_text_ElementA$javax_swing_text_ElementA,[map, line0, relem, aelem]);
chng.addEdit$javax_swing_undo_UndoableEdit(ee);
map.replace$I$I$javax_swing_text_ElementA(line0, 1, aelem);
}}}C$.superclazz.prototype.removeUpdate$javax_swing_text_AbstractDocument_DefaultDocumentEvent.apply(this, [chng]);
});

Clazz.newMeth(C$, 'insertComposedTextUpdate$javax_swing_text_AbstractDocument_DefaultDocumentEvent$javax_swing_text_AttributeSet', function (chng, attr) {
this.added.removeAllElements();
var lineMap = this.getDefaultRootElement();
var offset = chng.getOffset();
var length = chng.getLength();
var index = lineMap.getElementIndex$I(offset);
var elem = lineMap.getElement$I(index);
var elemStart = elem.getStartOffset();
var elemEnd = elem.getEndOffset();
var abelem = Clazz.array((I$[9]||$incl$(9)), [1]);
abelem[0] = this.createBranchElement$javax_swing_text_Element$javax_swing_text_AttributeSet(lineMap, null);
var relem = Clazz.array((I$[4]||$incl$(4)), [1]);
relem[0] = elem;
if (elemStart != offset) this.added.addElement$TE(this.createLeafElement$javax_swing_text_Element$javax_swing_text_AttributeSet$I$I(abelem[0], null, elemStart, offset));
this.added.addElement$TE(this.createLeafElement$javax_swing_text_Element$javax_swing_text_AttributeSet$I$I(abelem[0], attr, offset, offset + length));
if (elemEnd != offset + length) this.added.addElement$TE(this.createLeafElement$javax_swing_text_Element$javax_swing_text_AttributeSet$I$I(abelem[0], null, offset + length, elemEnd));
var alelem = Clazz.array((I$[4]||$incl$(4)), [this.added.size()]);
this.added.copyInto$OA(alelem);
var ee = Clazz.new_((I$[6]||$incl$(6)).c$$javax_swing_text_Element$I$javax_swing_text_ElementA$javax_swing_text_ElementA,[lineMap, index, relem, abelem]);
chng.addEdit$javax_swing_undo_UndoableEdit(ee);
abelem[0].replace$I$I$javax_swing_text_ElementA(0, 0, alelem);
lineMap.replace$I$I$javax_swing_text_ElementA(index, 1, abelem);
});

Clazz.newMeth(C$, 'render$Runnable', function (r) {
});
})();
//Created 2018-02-08 10:02:58
