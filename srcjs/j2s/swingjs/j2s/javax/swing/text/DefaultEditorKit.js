(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[['javax.swing.UIManager','java.awt.Rectangle','java.awt.Point',['javax.swing.text.Position','.Bias'],'javax.swing.text.Utilities',['javax.swing.text.DefaultEditorKit','.BeginWordAction'],['javax.swing.text.DefaultEditorKit','.EndWordAction'],['javax.swing.text.DefaultEditorKit','.BeginLineAction'],['javax.swing.text.DefaultEditorKit','.EndLineAction'],['javax.swing.text.DefaultEditorKit','.BeginParagraphAction'],['javax.swing.text.DefaultEditorKit','.EndParagraphAction'],'java.awt.ComponentOrientation','javax.swing.Action',['javax.swing.text.DefaultEditorKit','.InsertContentAction'],['javax.swing.text.DefaultEditorKit','.DeletePrevCharAction'],['javax.swing.text.DefaultEditorKit','.DeleteNextCharAction'],['javax.swing.text.DefaultEditorKit','.ReadOnlyAction'],['javax.swing.text.DefaultEditorKit','.WritableAction'],['javax.swing.text.DefaultEditorKit','.CutAction'],['javax.swing.text.DefaultEditorKit','.CopyAction'],['javax.swing.text.DefaultEditorKit','.PasteAction'],['javax.swing.text.DefaultEditorKit','.VerticalPageAction'],['javax.swing.text.DefaultEditorKit','.PageAction'],['javax.swing.text.DefaultEditorKit','.InsertBreakAction'],['javax.swing.text.DefaultEditorKit','.BeepAction'],['javax.swing.text.DefaultEditorKit','.NextVisualPositionAction'],['javax.swing.text.DefaultEditorKit','.PreviousWordAction'],['javax.swing.text.DefaultEditorKit','.NextWordAction'],['javax.swing.text.DefaultEditorKit','.BeginAction'],['javax.swing.text.DefaultEditorKit','.EndAction'],['javax.swing.text.DefaultEditorKit','.DefaultKeyTypedAction'],['javax.swing.text.DefaultEditorKit','.InsertTabAction'],['javax.swing.text.DefaultEditorKit','.SelectWordAction'],['javax.swing.text.DefaultEditorKit','.SelectLineAction'],['javax.swing.text.DefaultEditorKit','.SelectParagraphAction'],['javax.swing.text.DefaultEditorKit','.SelectAllAction'],['javax.swing.text.DefaultEditorKit','.UnselectAction'],['javax.swing.text.DefaultEditorKit','.ToggleComponentOrientationAction'],'swingjs.JSPlainDocument','java.io.InputStreamReader','swingjs.api.Interface','java.io.OutputStream','javax.swing.text.Segment']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DefaultEditorKit", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.text.EditorKit');
C$.defaultActions = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.defaultActions = Clazz.array((I$[13]||$incl$(13)), -1, [Clazz.new_((I$[14]||$incl$(14))), Clazz.new_((I$[15]||$incl$(15))), Clazz.new_((I$[16]||$incl$(16))), Clazz.new_((I$[17]||$incl$(17))), Clazz.new_((I$[18]||$incl$(18))), Clazz.new_((I$[19]||$incl$(19))), Clazz.new_((I$[20]||$incl$(20))), Clazz.new_((I$[21]||$incl$(21))), Clazz.new_((I$[22]||$incl$(22)).c$$S$I$Z,["page-up", -1, false]), Clazz.new_((I$[22]||$incl$(22)).c$$S$I$Z,["page-down", 1, false]), Clazz.new_((I$[22]||$incl$(22)).c$$S$I$Z,["selection-page-up", -1, true]), Clazz.new_((I$[22]||$incl$(22)).c$$S$I$Z,["selection-page-down", 1, true]), Clazz.new_((I$[23]||$incl$(23)).c$$S$Z$Z,["selection-page-left", true, true]), Clazz.new_((I$[23]||$incl$(23)).c$$S$Z$Z,["selection-page-right", false, true]), Clazz.new_((I$[24]||$incl$(24))), Clazz.new_((I$[25]||$incl$(25))), Clazz.new_((I$[26]||$incl$(26)).c$$S$Z$I,["caret-forward", false, 3]), Clazz.new_((I$[26]||$incl$(26)).c$$S$Z$I,["caret-backward", false, 7]), Clazz.new_((I$[26]||$incl$(26)).c$$S$Z$I,["selection-forward", true, 3]), Clazz.new_((I$[26]||$incl$(26)).c$$S$Z$I,["selection-backward", true, 7]), Clazz.new_((I$[26]||$incl$(26)).c$$S$Z$I,["caret-up", false, 1]), Clazz.new_((I$[26]||$incl$(26)).c$$S$Z$I,["caret-down", false, 5]), Clazz.new_((I$[26]||$incl$(26)).c$$S$Z$I,["selection-up", true, 1]), Clazz.new_((I$[26]||$incl$(26)).c$$S$Z$I,["selection-down", true, 5]), Clazz.new_((I$[6]||$incl$(6)).c$$S$Z,["caret-begin-word", false]), Clazz.new_((I$[7]||$incl$(7)).c$$S$Z,["caret-end-word", false]), Clazz.new_((I$[6]||$incl$(6)).c$$S$Z,["selection-begin-word", true]), Clazz.new_((I$[7]||$incl$(7)).c$$S$Z,["selection-end-word", true]), Clazz.new_((I$[27]||$incl$(27)).c$$S$Z,["caret-previous-word", false]), Clazz.new_((I$[28]||$incl$(28)).c$$S$Z,["caret-next-word", false]), Clazz.new_((I$[27]||$incl$(27)).c$$S$Z,["selection-previous-word", true]), Clazz.new_((I$[28]||$incl$(28)).c$$S$Z,["selection-next-word", true]), Clazz.new_((I$[8]||$incl$(8)).c$$S$Z,["caret-begin-line", false]), Clazz.new_((I$[9]||$incl$(9)).c$$S$Z,["caret-end-line", false]), Clazz.new_((I$[8]||$incl$(8)).c$$S$Z,["selection-begin-line", true]), Clazz.new_((I$[9]||$incl$(9)).c$$S$Z,["selection-end-line", true]), Clazz.new_((I$[10]||$incl$(10)).c$$S$Z,["caret-begin-paragraph", false]), Clazz.new_((I$[11]||$incl$(11)).c$$S$Z,["caret-end-paragraph", false]), Clazz.new_((I$[10]||$incl$(10)).c$$S$Z,["selection-begin-paragraph", true]), Clazz.new_((I$[11]||$incl$(11)).c$$S$Z,["selection-end-paragraph", true]), Clazz.new_((I$[29]||$incl$(29)).c$$S$Z,["caret-begin", false]), Clazz.new_((I$[30]||$incl$(30)).c$$S$Z,["caret-end", false]), Clazz.new_((I$[29]||$incl$(29)).c$$S$Z,["selection-begin", true]), Clazz.new_((I$[30]||$incl$(30)).c$$S$Z,["selection-end", true]), Clazz.new_((I$[31]||$incl$(31))), Clazz.new_((I$[32]||$incl$(32))), Clazz.new_((I$[33]||$incl$(33))), Clazz.new_((I$[34]||$incl$(34))), Clazz.new_((I$[35]||$incl$(35))), Clazz.new_((I$[36]||$incl$(36))), Clazz.new_((I$[37]||$incl$(37))), Clazz.new_((I$[38]||$incl$(38)))]);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'getContentType', function () {
return "text/plain";
});

Clazz.newMeth(C$, 'getViewFactory', function () {
return null;
});

Clazz.newMeth(C$, 'getActions', function () {
return C$.defaultActions;
});

Clazz.newMeth(C$, 'createCaret', function () {
return null;
});

Clazz.newMeth(C$, 'createDefaultDocument', function () {
return Clazz.new_((I$[39]||$incl$(39)));
});

Clazz.newMeth(C$, 'read$java_io_InputStream$javax_swing_text_Document$I', function ($in, doc, pos) {
this.read$java_io_Reader$javax_swing_text_Document$I(Clazz.new_((I$[40]||$incl$(40)).c$$java_io_InputStream,[$in]), doc, pos);
});

Clazz.newMeth(C$, 'write$java_io_OutputStream$javax_swing_text_Document$I$I', function (out, doc, pos, len) {
var osw = (I$[41]||$incl$(41)).getInstanceWithParams$S$ClassA$OA("java.io.OutputStreamWriter", Clazz.array(java.lang.Class, -1, [Clazz.getClass((I$[42]||$incl$(42)))]), Clazz.array(java.lang.Object, -1, [out]));
this.write$java_io_Writer$javax_swing_text_Document$I$I(osw, doc, pos, len);
osw.flush();
});

Clazz.newMeth(C$, 'getInputAttributes', function () {
return null;
});

Clazz.newMeth(C$, 'read$java_io_Reader$javax_swing_text_Document$I', function ($in, doc, pos) {
var buff = Clazz.array(Character.TYPE, [4096]);
var nch;
var lastWasCR = false;
var isCRLF = false;
var isCR = false;
var last;
var wasEmpty = (doc.getLength() == 0);
var attr = this.getInputAttributes();
while ((nch = $in.read$CA$I$I(buff, 0, buff.length)) != -1){
last = 0;
for (var counter = 0; counter < nch; counter++) {
switch ((buff[counter]).$c()) {
case 13:
if (lastWasCR) {
isCR = true;
if (counter == 0) {
doc.insertString$I$S$javax_swing_text_AttributeSet(pos, "\u000a", attr);
pos++;
} else {
buff[counter - 1] = "\u000a";
}} else {
lastWasCR = true;
}break;
case 10:
if (lastWasCR) {
if (counter > (last + 1)) {
doc.insertString$I$S$javax_swing_text_AttributeSet(pos,  String.instantialize(buff, last, counter - last - 1 ), attr);
pos = pos+((counter - last - 1 ));
}lastWasCR = false;
last = counter;
isCRLF = true;
}break;
default:
if (lastWasCR) {
isCR = true;
if (counter == 0) {
doc.insertString$I$S$javax_swing_text_AttributeSet(pos, "\u000a", attr);
pos++;
} else {
buff[counter - 1] = "\u000a";
}lastWasCR = false;
}break;
}
}
if (last < nch) {
if (lastWasCR) {
if (last < (nch - 1)) {
doc.insertString$I$S$javax_swing_text_AttributeSet(pos,  String.instantialize(buff, last, nch - last - 1 ), attr);
pos = pos+((nch - last - 1 ));
}} else {
doc.insertString$I$S$javax_swing_text_AttributeSet(pos,  String.instantialize(buff, last, nch - last), attr);
pos = pos+((nch - last));
}}}
if (lastWasCR) {
doc.insertString$I$S$javax_swing_text_AttributeSet(pos, "\u000a", attr);
isCR = true;
}if (wasEmpty) {
if (isCRLF) {
doc.putProperty$O$O("__EndOfLine__", "\u000d\u000a");
} else if (isCR) {
doc.putProperty$O$O("__EndOfLine__", "\u000d");
} else {
doc.putProperty$O$O("__EndOfLine__", "\u000a");
}}});

Clazz.newMeth(C$, 'write$java_io_Writer$javax_swing_text_Document$I$I', function (out, doc, pos, len) {
if ((pos < 0) || ((pos + len) > doc.getLength()) ) {
throw Clazz.new_(Clazz.load('javax.swing.text.BadLocationException').c$$S$I,["DefaultEditorKit.write", pos]);
}var data = Clazz.new_((I$[43]||$incl$(43)));
var nleft = len;
var offs = pos;
var endOfLineProperty = doc.getProperty$O("__EndOfLine__");
if (endOfLineProperty == null ) {
try {
endOfLineProperty = System.getProperty("line.separator");
} catch (se) {
if (Clazz.exceptionOf(se, "java.lang.SecurityException")){
} else {
throw se;
}
}
}var endOfLine;
if (Clazz.instanceOf(endOfLineProperty, "java.lang.String")) {
endOfLine = endOfLineProperty;
} else {
endOfLine = null;
}if (endOfLineProperty != null  && !endOfLine.equals$O("\u000a") ) {
while (nleft > 0){
var n = Math.min(nleft, 4096);
doc.getText$I$I$javax_swing_text_Segment(offs, n, data);
var last = data.offset;
var array = data.array;
var maxCounter = last + data.count;
for (var counter = last; counter < maxCounter; counter++) {
if (array[counter] == "\u000a") {
if (counter > last) {
out.write$CA$I$I(array, last, counter - last);
}out.write$S(endOfLine);
last = counter + 1;
}}
if (maxCounter > last) {
out.write$CA$I$I(array, last, maxCounter - last);
}offs = offs+(n);
nleft = nleft-(n);
}
} else {
while (nleft > 0){
var n = Math.min(nleft, 4096);
doc.getText$I$I$javax_swing_text_Segment(offs, n, data);
out.write$CA$I$I(data.array, data.offset, data.count);
offs = offs+(n);
nleft = nleft-(n);
}
}out.flush();
});
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "DefaultKeyTypedAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["default-typed"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if ((target != null ) && (e != null ) ) {
if ((!target.isEditable()) || (!target.isEnabled()) ) {
return;
}var content = e.getActionCommand();
var mod = e.getModifiers();
if ((content != null ) && (content.length$() > 0) && ((mod & 8) == (mod & 2))  ) {
var c = content.charAt(0);
if ((c.$c() >= 32 ) && (c.$c() != 127 ) ) {
target.replaceSelection$S(content);
}}}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "InsertContentAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["insert-content"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if ((target != null ) && (e != null ) ) {
if ((!target.isEditable()) || (!target.isEnabled()) ) {
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
return;
}var content = e.getActionCommand();
if (content != null ) {
target.replaceSelection$S(content);
} else {
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
}}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "InsertBreakAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["insert-break"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
if ((!target.isEditable()) || (!target.isEnabled()) ) {
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
return;
}target.replaceSelection$S("\u000a");
}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "InsertTabAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["insert-tab"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
if ((!target.isEditable()) || (!target.isEnabled()) ) {
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
return;
}target.replaceSelection$S("\u0009");
}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "DeletePrevCharAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["delete-previous"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
var beep = true;
if ((target != null ) && (target.isEditable()) ) {
try {
var doc = target.getDocument();
var caret = target.getCaret();
var dot = caret.getDot();
var mark = caret.getMark();
if (dot != mark) {
doc.remove$I$I(Math.min(dot, mark), Math.abs(dot - mark));
beep = false;
} else if (dot > 0) {
var delChars = 1;
if (dot > 1) {
var dotChars = doc.getText$I$I(dot - 2, 2);
var c0 = dotChars.charAt(0);
var c1 = dotChars.charAt(1);
if (c0 >= "\ud800" && c0 <= "\udbff"  && c1 >= "\udc00"  && c1 <= "\udfff" ) {
delChars = 2;
}}doc.remove$I$I(dot - delChars, delChars);
beep = false;
}} catch (bl) {
if (Clazz.exceptionOf(bl, "javax.swing.text.BadLocationException")){
} else {
throw bl;
}
}
}if (beep) {
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "DeleteNextCharAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["delete-next"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
var beep = true;
if ((target != null ) && (target.isEditable()) ) {
try {
var doc = target.getDocument();
var caret = target.getCaret();
var dot = caret.getDot();
var mark = caret.getMark();
if (dot != mark) {
doc.remove$I$I(Math.min(dot, mark), Math.abs(dot - mark));
beep = false;
} else if (dot < doc.getLength()) {
var delChars = 1;
if (dot < doc.getLength() - 1) {
var dotChars = doc.getText$I$I(dot, 2);
var c0 = dotChars.charAt(0);
var c1 = dotChars.charAt(1);
if (c0 >= "\ud800" && c0 <= "\udbff"  && c1 >= "\udc00"  && c1 <= "\udfff" ) {
delChars = 2;
}}doc.remove$I$I(dot, delChars);
beep = false;
}} catch (bl) {
if (Clazz.exceptionOf(bl, "javax.swing.text.BadLocationException")){
} else {
throw bl;
}
}
}if (beep) {
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "ReadOnlyAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["set-read-only"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
target.setEditable$Z(false);
}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "WritableAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["set-writable"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
target.setEditable$Z(true);
}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "CutAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["cut-to-clipboard"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
target.cut();
}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "CopyAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["copy-to-clipboard"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
target.copy();
}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "PasteAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["paste-from-clipboard"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
target.paste();
}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "BeepAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["beep"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "VerticalPageAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
this.direction = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$I$Z', function (nm, direction, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
this.direction = direction;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
var visible = target.getVisibleRect();
var newVis = Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_Rectangle,[visible]);
var selectedIndex = target.getCaretPosition();
var scrollAmount = this.direction * target.getScrollableBlockIncrement$java_awt_Rectangle$I$I(visible, 1, this.direction);
var initialY = visible.y;
var caret = target.getCaret();
var magicPosition = caret.getMagicCaretPosition();
if (selectedIndex != -1) {
try {
var dotBounds = target.modelToView$I(selectedIndex);
var x = (magicPosition != null ) ? magicPosition.x : dotBounds.x;
var h = dotBounds.height;
if (h > 0) {
scrollAmount = (scrollAmount/h|0) * h;
}newVis.y = p$.constrainY$javax_swing_text_JTextComponent$I$I.apply(this, [target, initialY + scrollAmount, visible.height]);
var newIndex;
if (visible.contains$I$I(dotBounds.x, dotBounds.y)) {
newIndex = target.viewToModel$java_awt_Point(Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[x, p$.constrainY$javax_swing_text_JTextComponent$I$I.apply(this, [target, dotBounds.y + scrollAmount, 0])]));
} else {
if (this.direction == -1) {
newIndex = target.viewToModel$java_awt_Point(Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[x, newVis.y]));
} else {
newIndex = target.viewToModel$java_awt_Point(Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[x, newVis.y + visible.height]));
}}newIndex = p$.constrainOffset$javax_swing_text_JTextComponent$I.apply(this, [target, newIndex]);
if (newIndex != selectedIndex) {
p$.adjustScrollIfNecessary$javax_swing_text_JTextComponent$java_awt_Rectangle$I$I.apply(this, [target, newVis, initialY, newIndex]);
if (this.select) {
target.moveCaretPosition$I(newIndex);
} else {
target.setCaretPosition$I(newIndex);
}}} catch (ble) {
if (Clazz.exceptionOf(ble, "javax.swing.text.BadLocationException")){
} else {
throw ble;
}
}
} else {
newVis.y = p$.constrainY$javax_swing_text_JTextComponent$I$I.apply(this, [target, initialY + scrollAmount, visible.height]);
}if (magicPosition != null ) {
caret.setMagicCaretPosition$java_awt_Point(magicPosition);
}target.scrollRectToVisible$java_awt_Rectangle(newVis);
}});

Clazz.newMeth(C$, 'constrainY$javax_swing_text_JTextComponent$I$I', function (target, y, vis) {
if (y < 0) {
y = 0;
} else if (y + vis > target.getHeight()) {
y = Math.max(0, target.getHeight() - vis);
}return y;
});

Clazz.newMeth(C$, 'constrainOffset$javax_swing_text_JTextComponent$I', function (text, offset) {
var doc = text.getDocument();
if ((offset != 0) && (offset > doc.getLength()) ) {
offset = doc.getLength();
}if (offset < 0) {
offset = 0;
}return offset;
});

Clazz.newMeth(C$, 'adjustScrollIfNecessary$javax_swing_text_JTextComponent$java_awt_Rectangle$I$I', function (text, visible, initialY, index) {
try {
var dotBounds = text.modelToView$I(index);
if (dotBounds.y < visible.y || (dotBounds.y > (visible.y + visible.height))  || (dotBounds.y + dotBounds.height) > (visible.y + visible.height) ) {
var y;
if (dotBounds.y < visible.y) {
y = dotBounds.y;
} else {
y = dotBounds.y + dotBounds.height - visible.height;
}if ((this.direction == -1 && y < initialY ) || (this.direction == 1 && y > initialY ) ) {
visible.y = y;
}}} catch (ble) {
if (Clazz.exceptionOf(ble, "javax.swing.text.BadLocationException")){
} else {
throw ble;
}
}
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "PageAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
this.left = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z$Z', function (nm, left, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
this.left = left;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
var selectedIndex;
var visible = Clazz.new_((I$[2]||$incl$(2)));
target.computeVisibleRect$java_awt_Rectangle(visible);
if (this.left) {
visible.x = Math.max(0, visible.x - visible.width);
} else {
visible.x = visible.x+(visible.width);
}selectedIndex = target.getCaretPosition();
if (selectedIndex != -1) {
if (this.left) {
selectedIndex = target.viewToModel$java_awt_Point(Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[visible.x, visible.y]));
} else {
selectedIndex = target.viewToModel$java_awt_Point(Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[visible.x + visible.width - 1, visible.y + visible.height - 1]));
}var doc = target.getDocument();
if ((selectedIndex != 0) && (selectedIndex > (doc.getLength() - 1)) ) {
selectedIndex = doc.getLength() - 1;
} else if (selectedIndex < 0) {
selectedIndex = 0;
}if (this.select) target.moveCaretPosition$I(selectedIndex);
 else target.setCaretPosition$I(selectedIndex);
}}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "NextVisualPositionAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
this.direction = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z$I', function (nm, select, direction) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
this.direction = direction;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
var caret = target.getCaret();
var bidiCaret = (Clazz.instanceOf(caret, "javax.swing.text.DefaultCaret")) ? caret : null;
var dot = caret.getDot();
var bias = Clazz.array((I$[4]||$incl$(4)), [1]);
var magicPosition = caret.getMagicCaretPosition();
try {
if (magicPosition == null  && (this.direction == 1 || this.direction == 5 ) ) {
var r = (bidiCaret != null ) ? (target.getUI()).modelToView$javax_swing_text_JTextComponent$I$javax_swing_text_Position_Bias(target, dot, bidiCaret.getDotBias()) : target.modelToView$I(dot);
magicPosition = Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[r.x, r.y]);
}var filter = target.getNavigationFilter();
if (filter != null ) {
dot = filter.getNextVisualPositionFrom$javax_swing_text_JTextComponent$I$javax_swing_text_Position_Bias$I$javax_swing_text_Position_BiasA(target, dot, (bidiCaret != null ) ? bidiCaret.getDotBias() : (I$[4]||$incl$(4)).Forward, this.direction, bias);
} else {
dot = (target.getUI()).getNextVisualPositionFrom$javax_swing_text_JTextComponent$I$javax_swing_text_Position_Bias$I$javax_swing_text_Position_BiasA(target, dot, (bidiCaret != null ) ? bidiCaret.getDotBias() : (I$[4]||$incl$(4)).Forward, this.direction, bias);
}if (bias[0] == null ) {
bias[0] = (I$[4]||$incl$(4)).Forward;
}if (bidiCaret != null ) {
if (this.select) {
bidiCaret.moveDot$I$javax_swing_text_Position_Bias(dot, bias[0]);
} else {
bidiCaret.setDot$I$javax_swing_text_Position_Bias(dot, bias[0]);
}} else {
if (this.select) {
caret.moveDot$I(dot);
} else {
caret.setDot$I(dot);
}}if (magicPosition != null  && (this.direction == 1 || this.direction == 5 ) ) {
target.getCaret().setMagicCaretPosition$java_awt_Point(magicPosition);
}} catch (ex) {
if (Clazz.exceptionOf(ex, "javax.swing.text.BadLocationException")){
} else {
throw ex;
}
}
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "BeginWordAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (nm, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
try {
var offs = target.getCaretPosition();
var begOffs = (I$[5]||$incl$(5)).getWordStart$javax_swing_text_JTextComponent$I(target, offs);
if (this.select) {
target.moveCaretPosition$I(begOffs);
} else {
target.setCaretPosition$I(begOffs);
}} catch (bl) {
if (Clazz.exceptionOf(bl, "javax.swing.text.BadLocationException")){
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
} else {
throw bl;
}
}
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "EndWordAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (nm, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
try {
var offs = target.getCaretPosition();
var endOffs = (I$[5]||$incl$(5)).getWordEnd$javax_swing_text_JTextComponent$I(target, offs);
if (this.select) {
target.moveCaretPosition$I(endOffs);
} else {
target.setCaretPosition$I(endOffs);
}} catch (bl) {
if (Clazz.exceptionOf(bl, "javax.swing.text.BadLocationException")){
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
} else {
throw bl;
}
}
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "PreviousWordAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (nm, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
var offs = target.getCaretPosition();
var failed = false;
try {
var curPara = (I$[5]||$incl$(5)).getParagraphElement$javax_swing_text_JTextComponent$I(target, offs);
offs = (I$[5]||$incl$(5)).getPreviousWord$javax_swing_text_JTextComponent$I(target, offs);
if (offs < curPara.getStartOffset()) {
offs = (I$[5]||$incl$(5)).getParagraphElement$javax_swing_text_JTextComponent$I(target, offs).getEndOffset() - 1;
}} catch (bl) {
if (Clazz.exceptionOf(bl, "javax.swing.text.BadLocationException")){
if (offs != 0) {
offs = 0;
} else {
failed = true;
}} else {
throw bl;
}
}
if (!failed) {
if (this.select) {
target.moveCaretPosition$I(offs);
} else {
target.setCaretPosition$I(offs);
}} else {
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
}}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "NextWordAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (nm, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
var offs = target.getCaretPosition();
var failed = false;
var oldOffs = offs;
var curPara = (I$[5]||$incl$(5)).getParagraphElement$javax_swing_text_JTextComponent$I(target, offs);
try {
offs = (I$[5]||$incl$(5)).getNextWord$javax_swing_text_JTextComponent$I(target, offs);
if (offs >= curPara.getEndOffset() && oldOffs != curPara.getEndOffset() - 1 ) {
offs = curPara.getEndOffset() - 1;
}} catch (bl) {
if (Clazz.exceptionOf(bl, "javax.swing.text.BadLocationException")){
var end = target.getDocument().getLength();
if (offs != end) {
if (oldOffs != curPara.getEndOffset() - 1) {
offs = curPara.getEndOffset() - 1;
} else {
offs = end;
}} else {
failed = true;
}} else {
throw bl;
}
}
if (!failed) {
if (this.select) {
target.moveCaretPosition$I(offs);
} else {
target.setCaretPosition$I(offs);
}} else {
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
}}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "BeginLineAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (nm, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
try {
var offs = target.getCaretPosition();
var begOffs = (I$[5]||$incl$(5)).getRowStart$javax_swing_text_JTextComponent$I(target, offs);
if (this.select) {
target.moveCaretPosition$I(begOffs);
} else {
target.setCaretPosition$I(begOffs);
}} catch (bl) {
if (Clazz.exceptionOf(bl, "javax.swing.text.BadLocationException")){
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
} else {
throw bl;
}
}
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "EndLineAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (nm, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
try {
var offs = target.getCaretPosition();
var endOffs = (I$[5]||$incl$(5)).getRowEnd$javax_swing_text_JTextComponent$I(target, offs);
if (this.select) {
target.moveCaretPosition$I(endOffs);
} else {
target.setCaretPosition$I(endOffs);
}} catch (bl) {
if (Clazz.exceptionOf(bl, "javax.swing.text.BadLocationException")){
(I$[1]||$incl$(1)).getLookAndFeel().provideErrorFeedback$java_awt_Component(target);
} else {
throw bl;
}
}
}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "BeginParagraphAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (nm, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
var offs = target.getCaretPosition();
var elem = (I$[5]||$incl$(5)).getParagraphElement$javax_swing_text_JTextComponent$I(target, offs);
offs = elem.getStartOffset();
if (this.select) {
target.moveCaretPosition$I(offs);
} else {
target.setCaretPosition$I(offs);
}}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "EndParagraphAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (nm, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
var offs = target.getCaretPosition();
var elem = (I$[5]||$incl$(5)).getParagraphElement$javax_swing_text_JTextComponent$I(target, offs);
offs = Math.min(target.getDocument().getLength(), elem.getEndOffset());
if (this.select) {
target.moveCaretPosition$I(offs);
} else {
target.setCaretPosition$I(offs);
}}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "BeginAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (nm, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
if (this.select) {
target.moveCaretPosition$I(0);
} else {
target.setCaretPosition$I(0);
}}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "EndAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.select = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (nm, select) {
C$.superclazz.c$$S.apply(this, [nm]);
C$.$init$.apply(this);
this.select = select;
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
var doc = target.getDocument();
var dot = doc.getLength();
if (this.select) {
target.moveCaretPosition$I(dot);
} else {
target.setCaretPosition$I(dot);
}}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "SelectWordAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.start = null;
this.end = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["select-word"]);
C$.$init$.apply(this);
this.start = Clazz.new_((I$[6]||$incl$(6)).c$$S$Z,["pigdog", false]);
this.end = Clazz.new_((I$[7]||$incl$(7)).c$$S$Z,["pigdog", true]);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.start.actionPerformed$java_awt_event_ActionEvent(e);
this.end.actionPerformed$java_awt_event_ActionEvent(e);
});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "SelectLineAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.start = null;
this.end = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["select-line"]);
C$.$init$.apply(this);
this.start = Clazz.new_((I$[8]||$incl$(8)).c$$S$Z,["pigdog", false]);
this.end = Clazz.new_((I$[9]||$incl$(9)).c$$S$Z,["pigdog", true]);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.start.actionPerformed$java_awt_event_ActionEvent(e);
this.end.actionPerformed$java_awt_event_ActionEvent(e);
});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "SelectParagraphAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.start = null;
this.end = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["select-paragraph"]);
C$.$init$.apply(this);
this.start = Clazz.new_((I$[10]||$incl$(10)).c$$S$Z,["pigdog", false]);
this.end = Clazz.new_((I$[11]||$incl$(11)).c$$S$Z,["pigdog", true]);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.start.actionPerformed$java_awt_event_ActionEvent(e);
this.end.actionPerformed$java_awt_event_ActionEvent(e);
});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "SelectAllAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["select-all"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
var doc = target.getDocument();
target.setCaretPosition$I(0);
target.moveCaretPosition$I(doc.getLength());
}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "UnselectAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["unselect"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
target.setCaretPosition$I(target.getCaretPosition());
}});
})()
;
(function(){var C$=Clazz.newClass(P$.DefaultEditorKit, "ToggleComponentOrientationAction", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.text.TextAction');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, ["toggle-componentOrientation"]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
var target = this.getTextComponent$java_awt_event_ActionEvent(e);
if (target != null ) {
var last = target.getComponentOrientation();
var next;
if (last === (I$[12]||$incl$(12)).RIGHT_TO_LEFT ) next = (I$[12]||$incl$(12)).LEFT_TO_RIGHT;
 else next = (I$[12]||$incl$(12)).RIGHT_TO_LEFT;
target.setComponentOrientation$java_awt_ComponentOrientation(next);
target.repaint();
}});
})()
})();
//Created 2018-02-08 10:02:55
