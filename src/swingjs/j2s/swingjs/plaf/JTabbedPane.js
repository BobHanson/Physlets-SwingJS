(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['javajs.util.Lst','javax.swing.JPanel','javax.swing.BoxLayout','javax.swing.JComboBox','java.awt.Dimension','swingjs.plaf.JTabbedPane$1',['swingjs.plaf.JTabbedPane','.Page']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JTabbedPane", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.JPanel');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.pagePanel = null;
this.tabs = null;
this.pages = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.pages = Clazz.new_((I$[1]||$incl$(1)));
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.pagePanel = Clazz.new_((I$[2]||$incl$(2)));
this.pagePanel.setLayout$java_awt_LayoutManager(Clazz.new_((I$[3]||$incl$(3)).c$$java_awt_Container$I,[this.pagePanel, 3]));
this.tabs = Clazz.new_((I$[4]||$incl$(4)));
this.tabs.setMaximumSize$java_awt_Dimension(Clazz.new_((I$[5]||$incl$(5)).c$$I$I,[150, 15]));
this.tabs.addItemListener$java_awt_event_ItemListener(((
(function(){var C$=Clazz.newClass(P$, "JTabbedPane$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.awt.event.ItemListener', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'itemStateChanged$java_awt_event_ItemEvent', function (e) {
this.b$['swingjs.plaf.JTabbedPane'].update$Z.apply(this.b$['swingjs.plaf.JTabbedPane'], [false]);
});
})()
), Clazz.new_((I$[6]||$incl$(6)).$init$, [this, null])));
this.setLayout$java_awt_LayoutManager(Clazz.new_((I$[3]||$incl$(3)).c$$java_awt_Container$I,[this, 3]));
this.add$java_awt_Component(this.tabs);
this.add$java_awt_Component(this.pagePanel);
}, 1);

Clazz.newMeth(C$, 'add$javax_swing_JComponent$S$I', function (panel, title, index) {
this.addTab$javax_swing_JComponent$S$I(panel, title, index);
});

Clazz.newMeth(C$, 'add$S$javax_swing_JComponent', function (title, panel) {
this.addTab$javax_swing_JComponent$S$I(panel, title, this.pages.size());
});

Clazz.newMeth(C$, 'addTab$S$javax_swing_JComponent', function (title, panel) {
this.addTab$javax_swing_JComponent$S$I(panel, title, this.pages.size());
});

Clazz.newMeth(C$, 'addTab$javax_swing_JComponent$S$I', function (panel, title, index) {
var page = Clazz.new_((I$[7]||$incl$(7)), [this, null]);
page.component = panel;
page.tab = (title == null  ? "tab" + index : title);
panel.setVisible$Z(true);
if (index < this.pages.size()) {
this.pages.get$I(index).component = panel;
} else {
this.pages.addLast$TV(page);
p$.update$Z.apply(this, [true]);
}});

Clazz.newMeth(C$, 'update$Z', function (combo) {
if (combo) {
var i0 = this.tabs.getSelectedIndex();
this.tabs.removeAllItems();
for (var i = 0; i < this.pages.size(); i++) {
this.tabs.addItem$O(this.pages.get$I(i).tab);
}
this.tabs.setSelectedIndex$I(i0 < 0 ? 0 : i0);
}this.pagePanel.removeAll();
var selected = this.getSelectedIndex();
if (selected >= 0) {
this.pagePanel.add$java_awt_Component(this.pages.get$I(selected).component);
}this.revalidate();
this.repaint();
});

Clazz.newMeth(C$, 'setComponentAt$I$javax_swing_JPanel', function (index, panel) {
if (index < 0 || index >= this.pages.size() ) throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
this.pages.get$I(index).component = panel;
p$.update$Z.apply(this, [false]);
});

Clazz.newMeth(C$, 'getTabCount', function () {
return this.tabs.getItemCount();
});

Clazz.newMeth(C$, 'setSelectedIndex$I', function (index) {
this.tabs.setSelectedIndex$I(index);
p$.update$Z.apply(this, [false]);
});

Clazz.newMeth(C$, 'getSelectedIndex', function () {
return this.tabs.getSelectedIndex();
});

Clazz.newMeth(C$, 'setEnabledAt$I$Z', function (index, bEnable) {
});

Clazz.newMeth(C$, 'remove$I', function (n) {
this.pages.removeItemAt$I(n);
this.tabs.removeItemAt$I(n);
p$.update$Z.apply(this, [false]);
});

Clazz.newMeth(C$, 'setMnemonicAt$I$I', function (i, mnemonic) {
});

Clazz.newMeth(C$, 'setDisplayedMnemonicIndexAt$I$I', function (i, displayedMnemonicIndex) {
});
;
(function(){var C$=Clazz.newClass(P$.JTabbedPane, "Page", function(){
Clazz.newInstance(this, arguments[0],true,C$);
});

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.tab = null;
this.component = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
})()
})();
//Created 2017-12-31 21:52:28
