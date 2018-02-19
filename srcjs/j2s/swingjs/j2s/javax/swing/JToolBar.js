(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['javax.swing.BoxLayout',['javax.swing.JToolBar','.DefaultToolBarLayout'],'java.awt.Insets','java.lang.Boolean',['javax.swing.JToolBar','.Separator'],'javax.swing.JButton']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JToolBar", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.JComponent', 'javax.swing.SwingConstants');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$paintBorder = false;
this.margin = null;
this.floatable = false;
this.orientation = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.$paintBorder = true;
this.margin = null;
this.floatable = true;
this.orientation = 0;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$I.apply(this, [0]);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (orientation) {
C$.c$$S$I.apply(this, [null, orientation]);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.c$$S$I.apply(this, [name, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$S$I', function (name, orientation) {
Clazz.super_(C$, this,1);
this.setName$S(name);
p$.checkOrientation$I.apply(this, [orientation]);
this.orientation = orientation;
var layout = Clazz.new_((I$[2]||$incl$(2)).c$$I, [this, null, orientation]);
this.setLayout$java_awt_LayoutManager(layout);
this.addPropertyChangeListener$java_beans_PropertyChangeListener(layout);
this.uiClassID = "ToolBarUI";
this.updateUI();
}, 1);

Clazz.newMeth(C$, 'updateUI', function () {
C$.superclazz.prototype.updateUI.apply(this, []);
if (this.getLayout() == null ) {
this.setLayout$java_awt_LayoutManager(Clazz.new_((I$[2]||$incl$(2)).c$$I, [this, null, this.getOrientation()]));
}this.invalidate();
});

Clazz.newMeth(C$, 'getComponentIndex$java_awt_Component', function (c) {
var ncomponents = this.getComponentCount();
var component = this.getComponents();
for (var i = 0; i < ncomponents; i++) {
var comp = component[i];
if (comp === c ) return i;
}
return -1;
});

Clazz.newMeth(C$, 'getComponentAtIndex$I', function (i) {
var ncomponents = this.getComponentCount();
if (i >= 0 && i < ncomponents ) {
var component = this.getComponents();
return component[i];
}return null;
});

Clazz.newMeth(C$, 'setMargin$java_awt_Insets', function (m) {
var old = this.margin;
this.margin = m;
this.firePropertyChange$S$O$O("margin", old, m);
this.revalidate();
this.repaint();
});

Clazz.newMeth(C$, 'getMargin', function () {
if (this.margin == null ) {
return Clazz.new_((I$[3]||$incl$(3)).c$$I$I$I$I,[0, 0, 0, 0]);
} else {
return this.margin;
}});

Clazz.newMeth(C$, 'isBorderPainted', function () {
return this.$paintBorder;
});

Clazz.newMeth(C$, 'setBorderPainted$Z', function (b) {
if (this.$paintBorder != b ) {
var old = this.$paintBorder;
this.$paintBorder = b;
this.firePropertyChange$S$Z$Z("borderPainted", old, b);
this.revalidate();
this.repaint();
}});

Clazz.newMeth(C$, 'paintBorder$java_awt_Graphics', function (g) {
if (this.isBorderPainted()) {
C$.superclazz.prototype.paintBorder$java_awt_Graphics.apply(this, [g]);
}});

Clazz.newMeth(C$, 'isFloatable', function () {
return this.floatable;
});

Clazz.newMeth(C$, 'setFloatable$Z', function (b) {
if (this.floatable != b ) {
var old = this.floatable;
this.floatable = b;
this.firePropertyChange$S$Z$Z("floatable", old, b);
this.revalidate();
this.repaint();
}});

Clazz.newMeth(C$, 'getOrientation', function () {
return this.orientation;
});

Clazz.newMeth(C$, 'setOrientation$I', function (o) {
p$.checkOrientation$I.apply(this, [o]);
if (this.orientation != o) {
var old = this.orientation;
this.orientation = o;
this.firePropertyChange$S$I$I("orientation", old, o);
this.revalidate();
this.repaint();
}});

Clazz.newMeth(C$, 'setRollover$Z', function (rollover) {
this.putClientProperty$O$O("JToolBar.isRollover", rollover ? (I$[4]||$incl$(4)).TRUE : (I$[4]||$incl$(4)).FALSE);
});

Clazz.newMeth(C$, 'isRollover', function () {
var rollover = this.getClientProperty$O("JToolBar.isRollover");
if (rollover != null ) {
return rollover.booleanValue();
}return false;
});

Clazz.newMeth(C$, 'checkOrientation$I', function (orientation) {
switch (orientation) {
case 1:
case 0:
break;
default:
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["orientation must be one of: VERTICAL, HORIZONTAL"]);
}
});

Clazz.newMeth(C$, 'addSeparator', function () {
this.addSeparator$java_awt_Dimension(null);
});

Clazz.newMeth(C$, 'addSeparator$java_awt_Dimension', function (size) {
var s = Clazz.new_((I$[5]||$incl$(5)).c$$java_awt_Dimension,[size]);
this.add$java_awt_Component(s);
});

Clazz.newMeth(C$, 'add$javax_swing_Action', function (a) {
var b = this.createActionComponent$javax_swing_Action(a);
b.setAction$javax_swing_Action(a);
this.add$java_awt_Component(b);
return b;
});

Clazz.newMeth(C$, 'createActionComponent$javax_swing_Action', function (a) {
var b = ((
(function(){var C$=Clazz.newClass(P$, "JToolBar$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('javax.swing.JButton'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'createActionPropertyChangeListener$javax_swing_Action', function (a) {
var pcl = this.b$['javax.swing.JToolBar'].createActionChangeListener$javax_swing_JButton(this);
if (pcl == null ) {
pcl = C$.superclazz.prototype.createActionPropertyChangeListener$javax_swing_Action.apply(this, [a]);
}return pcl;
});
})()
), Clazz.new_((I$[6]||$incl$(6)), [this, null],P$.JToolBar$1));
if (a != null  && (a.getValue$S("SmallIcon") != null  || a.getValue$S("SwingLargeIconKey") != null  ) ) {
b.setHideActionText$Z(true);
}b.setHorizontalTextPosition$I(0);
b.setVerticalTextPosition$I(3);
return b;
});

Clazz.newMeth(C$, 'createActionChangeListener$javax_swing_JButton', function (b) {
return null;
});

Clazz.newMeth(C$, 'addImpl$java_awt_Component$O$I', function (comp, constraints, index) {
if (Clazz.instanceOf(comp, "javax.swing.JToolBar.Separator")) {
if (this.getOrientation() == 1) {
(comp).setOrientation$I(0);
} else {
(comp).setOrientation$I(1);
}}this.addImplCont$java_awt_Component$O$I(comp, constraints, index);
if (Clazz.instanceOf(comp, "javax.swing.JButton")) {
(comp).setDefaultCapable$Z(false);
}return comp;
});

Clazz.newMeth(C$, 'paramString', function () {
var paintBorderString = (this.$paintBorder ? "true" : "false");
var marginString = (this.margin != null  ? this.margin.toString() : "");
var floatableString = (this.floatable ? "true" : "false");
var orientationString = (this.orientation == 0 ? "HORIZONTAL" : "VERTICAL");
return C$.superclazz.prototype.paramString.apply(this, []) + ",floatable=" + floatableString + ",margin=" + marginString + ",orientation=" + orientationString + ",paintBorder=" + paintBorderString ;
});

Clazz.newMeth(C$, 'setLayout$java_awt_LayoutManager', function (mgr) {
var oldMgr = this.getLayout();
if (Clazz.instanceOf(oldMgr, "java.beans.PropertyChangeListener")) {
this.removePropertyChangeListener$java_beans_PropertyChangeListener(oldMgr);
}C$.superclazz.prototype.setLayout$java_awt_LayoutManager.apply(this, [mgr]);
});
;
(function(){var C$=Clazz.newClass(P$.JToolBar, "Separator", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.JSeparator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.separatorSize = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$java_awt_Dimension.apply(this, [null]);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Dimension', function (size) {
C$.superclazz.c$$I$S.apply(this, [0, "ToolBarSeparatorUI"]);
C$.$init$.apply(this);
this.setSeparatorSize$java_awt_Dimension(size);
}, 1);

Clazz.newMeth(C$, 'setSeparatorSize$java_awt_Dimension', function (size) {
if (size != null ) {
this.separatorSize = size;
} else {
C$.superclazz.prototype.updateUI.apply(this, []);
}this.invalidate();
});

Clazz.newMeth(C$, 'getSeparatorSize', function () {
return this.separatorSize;
});

Clazz.newMeth(C$, 'getMinimumSize', function () {
if (this.separatorSize != null ) {
return this.separatorSize.getSize();
} else {
return C$.superclazz.prototype.getMinimumSize.apply(this, []);
}});

Clazz.newMeth(C$, 'getMaximumSize', function () {
if (this.separatorSize != null ) {
return this.separatorSize.getSize();
} else {
return C$.superclazz.prototype.getMaximumSize.apply(this, []);
}});

Clazz.newMeth(C$, 'getPreferredSize', function () {
if (this.separatorSize != null ) {
return this.separatorSize.getSize();
} else {
return this.getPrefSizeJComp();
}});
})()
;
(function(){var C$=Clazz.newClass(P$.JToolBar, "DefaultToolBarLayout", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, ['java.awt.LayoutManager2', 'java.beans.PropertyChangeListener', 'javax.swing.plaf.UIResource']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.lm = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (orientation) {
C$.$init$.apply(this);
if (orientation == 1) {
this.lm = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Container$I,[this.this$0, 3]);
} else {
this.lm = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Container$I,[this.this$0, 2]);
}}, 1);

Clazz.newMeth(C$, 'addLayoutComponent$S$java_awt_Component', function (name, comp) {
this.lm.addLayoutComponent$S$java_awt_Component(name, comp);
});

Clazz.newMeth(C$, 'addLayoutComponent$java_awt_Component$O', function (comp, constraints) {
this.lm.addLayoutComponent$java_awt_Component$O(comp, constraints);
});

Clazz.newMeth(C$, 'removeLayoutComponent$java_awt_Component', function (comp) {
this.lm.removeLayoutComponent$java_awt_Component(comp);
});

Clazz.newMeth(C$, 'preferredLayoutSize$java_awt_Container', function (target) {
return this.lm.preferredLayoutSize$java_awt_Container(target);
});

Clazz.newMeth(C$, 'minimumLayoutSize$java_awt_Container', function (target) {
return this.lm.minimumLayoutSize$java_awt_Container(target);
});

Clazz.newMeth(C$, 'maximumLayoutSize$java_awt_Container', function (target) {
return this.lm.maximumLayoutSize$java_awt_Container(target);
});

Clazz.newMeth(C$, 'layoutContainer$java_awt_Container', function (target) {
this.lm.layoutContainer$java_awt_Container(target);
});

Clazz.newMeth(C$, 'getLayoutAlignmentX$java_awt_Container', function (target) {
return this.lm.getLayoutAlignmentX$java_awt_Container(target);
});

Clazz.newMeth(C$, 'getLayoutAlignmentY$java_awt_Container', function (target) {
return this.lm.getLayoutAlignmentY$java_awt_Container(target);
});

Clazz.newMeth(C$, 'invalidateLayout$java_awt_Container', function (target) {
this.lm.invalidateLayout$java_awt_Container(target);
});

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (e) {
var name = e.getPropertyName();
if (name.equals$O("orientation")) {
var o = (e.getNewValue()).intValue();
if (o == 1) this.lm = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Container$I,[this.this$0, 3]);
 else {
this.lm = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Container$I,[this.this$0, 2]);
}}});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-08 10:02:38
