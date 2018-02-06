(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['javax.swing.Timer',['javax.swing.ToolTipManager','.insideTimerAction'],['javax.swing.ToolTipManager','.outsideTimerAction'],['javax.swing.ToolTipManager','.stillInsideTimerAction'],['javax.swing.ToolTipManager','.MoveBeforeEnterListener'],['javax.swing.ToolTipManager','.AccessibilityKeyListener'],'java.awt.Point','java.awt.Toolkit','javax.swing.SwingUtilities','java.awt.Rectangle','javax.swing.PopupFactory','java.awt.event.FocusAdapter']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ToolTipManager", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.awt.event.MouseAdapter', 'java.awt.event.MouseMotionListener');
C$.TOOL_TIP_MANAGER_KEY = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.TOOL_TIP_MANAGER_KEY =  Clazz.new_();
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.enterTimer = null;
this.exitTimer = null;
this.insideTimer = null;
this.toolTipText = null;
this.preferredLocation = null;
this.insideComponent = null;
this.mouseEvent = null;
this.showImmediately = false;
this.tipWindow = null;
this.window = null;
this.tip = null;
this.popupRect = null;
this.popupFrameRect = null;
this.enabled = false;
this.tipShowing = false;
this.focusChangeListener = null;
this.moveBeforeEnterListener = null;
this.accessibilityKeyListener = null;
this.lightWeightPopupEnabled = false;
this.heavyWeightPopupEnabled = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.popupRect = null;
this.popupFrameRect = null;
this.enabled = true;
this.tipShowing = false;
this.focusChangeListener = null;
this.moveBeforeEnterListener = null;
this.accessibilityKeyListener = null;
this.lightWeightPopupEnabled = true;
this.heavyWeightPopupEnabled = false;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.enterTimer = Clazz.new_((I$[1]||$incl$(1)).c$$I$java_awt_event_ActionListener,[750, Clazz.new_((I$[2]||$incl$(2)), [this, null])]);
this.enterTimer.setRepeats$Z(false);
this.exitTimer = Clazz.new_((I$[1]||$incl$(1)).c$$I$java_awt_event_ActionListener,[500, Clazz.new_((I$[3]||$incl$(3)), [this, null])]);
this.exitTimer.setRepeats$Z(false);
this.insideTimer = Clazz.new_((I$[1]||$incl$(1)).c$$I$java_awt_event_ActionListener,[4000, Clazz.new_((I$[4]||$incl$(4)), [this, null])]);
this.insideTimer.setRepeats$Z(false);
this.moveBeforeEnterListener = Clazz.new_((I$[5]||$incl$(5)), [this, null]);
this.accessibilityKeyListener = Clazz.new_((I$[6]||$incl$(6)), [this, null]);
}, 1);

Clazz.newMeth(C$, 'setEnabled$Z', function (flag) {
this.enabled = flag;
if (!flag) {
this.hideTipWindow();
}});

Clazz.newMeth(C$, 'isEnabled', function () {
return this.enabled;
});

Clazz.newMeth(C$, 'setLightWeightPopupEnabled$Z', function (aFlag) {
this.lightWeightPopupEnabled = aFlag;
});

Clazz.newMeth(C$, 'isLightWeightPopupEnabled', function () {
return this.lightWeightPopupEnabled;
});

Clazz.newMeth(C$, 'setInitialDelay$I', function (milliseconds) {
this.enterTimer.setInitialDelay$I(milliseconds);
});

Clazz.newMeth(C$, 'getInitialDelay', function () {
return this.enterTimer.getInitialDelay();
});

Clazz.newMeth(C$, 'setDismissDelay$I', function (milliseconds) {
this.insideTimer.setInitialDelay$I(milliseconds);
});

Clazz.newMeth(C$, 'getDismissDelay', function () {
return this.insideTimer.getInitialDelay();
});

Clazz.newMeth(C$, 'setReshowDelay$I', function (milliseconds) {
this.exitTimer.setInitialDelay$I(milliseconds);
});

Clazz.newMeth(C$, 'getReshowDelay', function () {
return this.exitTimer.getInitialDelay();
});

Clazz.newMeth(C$, 'showTipWindow', function () {
if (this.insideComponent == null  || !this.insideComponent.isShowing() ) return;
if (this.enabled) {
var size;
var screenLocation = this.insideComponent.getLocationOnScreen();
var location = Clazz.new_((I$[7]||$incl$(7)));
var gc;
gc = this.insideComponent.getGraphicsConfiguration();
var sBounds = gc.getBounds();
var screenInsets = (I$[8]||$incl$(8)).getDefaultToolkit().getScreenInsets$java_awt_GraphicsConfiguration(gc);
sBounds.x = sBounds.x+(screenInsets.left);
sBounds.y = sBounds.y+(screenInsets.top);
sBounds.width = sBounds.width-((screenInsets.left + screenInsets.right));
sBounds.height = sBounds.height-((screenInsets.top + screenInsets.bottom));
var leftToRight = (I$[9]||$incl$(9)).isLeftToRight$java_awt_Component(this.insideComponent);
this.hideTipWindow();
this.tip = this.insideComponent.createToolTip();
this.tip.setTipText$S(this.toolTipText);
size = this.tip.getPreferredSize();
if (this.preferredLocation != null ) {
location.x = screenLocation.x + this.preferredLocation.x;
location.y = screenLocation.y + this.preferredLocation.y;
if (!leftToRight) {
location.x = location.x-(size.width);
}} else {
location.x = screenLocation.x + this.mouseEvent.getX();
location.y = screenLocation.y + this.mouseEvent.getY() + 20 ;
if (!leftToRight) {
if (location.x - size.width >= 0) {
location.x = location.x-(size.width);
}}}if (this.popupRect == null ) {
this.popupRect = Clazz.new_((I$[10]||$incl$(10)));
}this.popupRect.setBounds$I$I$I$I(location.x, location.y, size.width, size.height);
if (location.x < sBounds.x) {
location.x = sBounds.x;
} else if (location.x - sBounds.x + size.width > sBounds.width) {
location.x = sBounds.x + Math.max(0, sBounds.width - size.width);
}if (location.y < sBounds.y) {
location.y = sBounds.y;
} else if (location.y - sBounds.y + size.height > sBounds.height) {
location.y = sBounds.y + Math.max(0, sBounds.height - size.height);
}var popupFactory = (I$[11]||$incl$(11)).getSharedInstance();
if (this.lightWeightPopupEnabled) {
var y = p$.getPopupFitHeight$java_awt_Rectangle$java_awt_Component.apply(this, [this.popupRect, this.insideComponent]);
var x = p$.getPopupFitWidth$java_awt_Rectangle$java_awt_Component.apply(this, [this.popupRect, this.insideComponent]);
if (x > 0 || y > 0 ) {
popupFactory.setPopupType$I(1);
} else {
popupFactory.setPopupType$I(0);
}} else {
popupFactory.setPopupType$I(1);
}this.tipWindow = popupFactory.getPopup$java_awt_Component$java_awt_Component$I$I(this.insideComponent, this.tip, location.x, location.y);
popupFactory.setPopupType$I(0);
this.tipWindow.show();
var componentWindow = (I$[9]||$incl$(9)).windowForComponent$java_awt_Component(this.insideComponent);
this.window = (I$[9]||$incl$(9)).windowForComponent$java_awt_Component(this.tip);
if (this.window != null  && this.window !== componentWindow  ) {
this.window.addMouseListener$java_awt_event_MouseListener(this);
} else {
this.window = null;
}this.insideTimer.start();
this.tipShowing = true;
}});

Clazz.newMeth(C$, 'hideTipWindow', function () {
if (this.tipWindow != null ) {
if (this.window != null ) {
this.window.removeMouseListener$java_awt_event_MouseListener(this);
this.window = null;
}this.tipWindow.hide();
this.tipWindow = null;
this.tipShowing = false;
this.tip = null;
this.insideTimer.stop();
}});

Clazz.newMeth(C$, 'sharedInstance', function () {
var value = (I$[9]||$incl$(9)).appContextGet$O(C$.TOOL_TIP_MANAGER_KEY);
if (Clazz.instanceOf(value, "javax.swing.ToolTipManager")) {
return value;
}var manager = Clazz.new_(C$);
(I$[9]||$incl$(9)).appContextPut$O$O(C$.TOOL_TIP_MANAGER_KEY, manager);
return manager;
}, 1);

Clazz.newMeth(C$, 'registerComponent$javax_swing_JComponent', function (component) {
component.removeMouseListener$java_awt_event_MouseListener(this);
component.addMouseListener$java_awt_event_MouseListener(this);
component.removeMouseMotionListener$java_awt_event_MouseMotionListener(this.moveBeforeEnterListener);
component.addMouseMotionListener$java_awt_event_MouseMotionListener(this.moveBeforeEnterListener);
component.removeKeyListener$java_awt_event_KeyListener(this.accessibilityKeyListener);
component.addKeyListener$java_awt_event_KeyListener(this.accessibilityKeyListener);
});

Clazz.newMeth(C$, 'unregisterComponent$javax_swing_JComponent', function (component) {
component.removeMouseListener$java_awt_event_MouseListener(this);
component.removeMouseMotionListener$java_awt_event_MouseMotionListener(this.moveBeforeEnterListener);
component.removeKeyListener$java_awt_event_KeyListener(this.accessibilityKeyListener);
});

Clazz.newMeth(C$, 'mouseEntered$java_awt_event_MouseEvent', function (event) {
p$.initiateToolTip$java_awt_event_MouseEvent.apply(this, [event]);
});

Clazz.newMeth(C$, 'initiateToolTip$java_awt_event_MouseEvent', function (event) {
if (event.getSource() === this.window ) {
return;
}var component = event.getSource();
component.removeMouseMotionListener$java_awt_event_MouseMotionListener(this.moveBeforeEnterListener);
this.exitTimer.stop();
var location = event.getPoint();
if (location.x < 0 || location.x >= component.getWidth()  || location.y < 0  || location.y >= component.getHeight() ) {
return;
}if (this.insideComponent != null ) {
this.enterTimer.stop();
}component.removeMouseMotionListener$java_awt_event_MouseMotionListener(this);
component.addMouseMotionListener$java_awt_event_MouseMotionListener(this);
var sameComponent = (this.insideComponent === component );
this.insideComponent = component;
if (this.tipWindow != null ) {
this.mouseEvent = event;
if (this.showImmediately) {
var newToolTipText = component.getToolTipText$java_awt_event_MouseEvent(event);
var newPreferredLocation = component.getToolTipLocation$java_awt_event_MouseEvent(event);
var sameLoc = (this.preferredLocation != null ) ? this.preferredLocation.equals$O(newPreferredLocation) : (newPreferredLocation == null );
if (!sameComponent || !this.toolTipText.equals$O(newToolTipText) || !sameLoc  ) {
this.toolTipText = newToolTipText;
this.preferredLocation = newPreferredLocation;
this.showTipWindow();
}} else {
this.enterTimer.start();
}}});

Clazz.newMeth(C$, 'mouseExited$java_awt_event_MouseEvent', function (event) {
var shouldHide = true;
if (this.insideComponent == null ) {
}if (this.window != null  && event.getSource() === this.window  ) {
var insideComponentWindow = this.insideComponent.getTopLevelAncestor();
if (insideComponentWindow != null ) {
var location = event.getPoint();
(I$[9]||$incl$(9)).convertPointToScreen$java_awt_Point$java_awt_Component(location, this.window);
location.x = location.x-(insideComponentWindow.getX());
location.y = location.y-(insideComponentWindow.getY());
location = (I$[9]||$incl$(9)).convertPoint$java_awt_Component$java_awt_Point$java_awt_Component(null, location, this.insideComponent);
if (location.x >= 0 && location.x < this.insideComponent.getWidth()  && location.y >= 0  && location.y < this.insideComponent.getHeight() ) {
shouldHide = false;
} else {
shouldHide = true;
}}} else if (event.getSource() === this.insideComponent  && this.tipWindow != null  ) {
var win = (I$[9]||$incl$(9)).getWindowAncestor$java_awt_Component(this.insideComponent);
if (win != null ) {
var location = (I$[9]||$incl$(9)).convertPoint$java_awt_Component$java_awt_Point$java_awt_Component(this.insideComponent, event.getPoint(), win);
var bounds = this.insideComponent.getTopLevelAncestor().getBounds();
location.x = location.x+(bounds.x);
location.y = location.y+(bounds.y);
var loc = Clazz.new_((I$[7]||$incl$(7)).c$$I$I,[0, 0]);
(I$[9]||$incl$(9)).convertPointToScreen$java_awt_Point$java_awt_Component(loc, this.tip);
bounds.x = loc.x;
bounds.y = loc.y;
bounds.width = this.tip.getWidth();
bounds.height = this.tip.getHeight();
if (location.x >= bounds.x && location.x < (bounds.x + bounds.width)  && location.y >= bounds.y  && location.y < (bounds.y + bounds.height) ) {
shouldHide = false;
} else {
shouldHide = true;
}}}if (shouldHide) {
this.enterTimer.stop();
if (this.insideComponent != null ) {
this.insideComponent.removeMouseMotionListener$java_awt_event_MouseMotionListener(this);
}this.insideComponent = null;
this.toolTipText = null;
this.mouseEvent = null;
this.hideTipWindow();
this.exitTimer.restart();
}});

Clazz.newMeth(C$, 'mousePressed$java_awt_event_MouseEvent', function (event) {
this.hideTipWindow();
this.enterTimer.stop();
this.showImmediately = false;
this.insideComponent = null;
this.mouseEvent = null;
});

Clazz.newMeth(C$, 'mouseDragged$java_awt_event_MouseEvent', function (event) {
});

Clazz.newMeth(C$, 'mouseMoved$java_awt_event_MouseEvent', function (event) {
if (this.tipShowing) {
p$.checkForTipChange$java_awt_event_MouseEvent.apply(this, [event]);
} else if (this.showImmediately) {
var component = event.getSource();
this.toolTipText = component.getToolTipText$java_awt_event_MouseEvent(event);
if (this.toolTipText != null ) {
this.preferredLocation = component.getToolTipLocation$java_awt_event_MouseEvent(event);
this.mouseEvent = event;
this.insideComponent = component;
this.exitTimer.stop();
this.showTipWindow();
}} else {
this.insideComponent = event.getSource();
this.mouseEvent = event;
this.toolTipText = null;
this.enterTimer.restart();
}});

Clazz.newMeth(C$, 'checkForTipChange$java_awt_event_MouseEvent', function (event) {
var component = event.getSource();
var newText = component.getToolTipText$java_awt_event_MouseEvent(event);
var newPreferredLocation = component.getToolTipLocation$java_awt_event_MouseEvent(event);
if (newText != null  || newPreferredLocation != null  ) {
this.mouseEvent = event;
if (((newText != null  && newText.equals$O(this.toolTipText) ) || newText == null  ) && ((newPreferredLocation != null  && newPreferredLocation.equals$O(this.preferredLocation) ) || newPreferredLocation == null  ) ) {
if (this.tipWindow != null ) {
this.insideTimer.restart();
} else {
this.enterTimer.restart();
}} else {
this.toolTipText = newText;
this.preferredLocation = newPreferredLocation;
if (this.showImmediately) {
this.hideTipWindow();
this.showTipWindow();
this.exitTimer.stop();
} else {
this.enterTimer.restart();
}}} else {
this.toolTipText = null;
this.preferredLocation = null;
this.mouseEvent = null;
this.insideComponent = null;
this.hideTipWindow();
this.enterTimer.stop();
this.exitTimer.restart();
}});

Clazz.newMeth(C$, 'frameForComponent$java_awt_Component', function (component) {
while (!(Clazz.instanceOf(component, "java.awt.Frame"))){
component = component.getParent();
}
return component;
}, 1);

Clazz.newMeth(C$, 'createFocusChangeListener', function () {
return ((
(function(){var C$=Clazz.newClass(P$, "ToolTipManager$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.awt.event.FocusAdapter'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'focusLost$java_awt_event_FocusEvent', function (evt) {
this.b$['javax.swing.ToolTipManager'].hideTipWindow();
this.b$['javax.swing.ToolTipManager'].insideComponent = null;
var c = evt.getSource();
c.removeFocusListener$java_awt_event_FocusListener(this.b$['javax.swing.ToolTipManager'].focusChangeListener);
});
})()
), Clazz.new_((I$[12]||$incl$(12)), [this, null],P$.ToolTipManager$1));
});

Clazz.newMeth(C$, 'getPopupFitWidth$java_awt_Rectangle$java_awt_Component', function (popupRectInScreen, invoker) {
if (invoker != null ) {
var parent;
for (parent = invoker.getParent(); parent != null ; parent = parent.getParent()) {
if (Clazz.instanceOf(parent, "javax.swing.JFrame") || Clazz.instanceOf(parent, "javax.swing.JDialog") || Clazz.instanceOf(parent, "javax.swing.JWindow")  ) {
return p$.getWidthAdjust$java_awt_Rectangle$java_awt_Rectangle.apply(this, [parent.getBounds(), popupRectInScreen]);
} else if (Clazz.instanceOf(parent, "javax.swing.JApplet")) {
if (this.popupFrameRect == null ) {
this.popupFrameRect = Clazz.new_((I$[10]||$incl$(10)));
}var p = parent.getLocationOnScreen();
this.popupFrameRect.setBounds$I$I$I$I(p.x, p.y, parent.getBounds().width, parent.getBounds().height);
return p$.getWidthAdjust$java_awt_Rectangle$java_awt_Rectangle.apply(this, [this.popupFrameRect, popupRectInScreen]);
}}
}return 0;
});

Clazz.newMeth(C$, 'getPopupFitHeight$java_awt_Rectangle$java_awt_Component', function (popupRectInScreen, invoker) {
if (invoker != null ) {
var parent;
for (parent = invoker.getParent(); parent != null ; parent = parent.getParent()) {
if (Clazz.instanceOf(parent, "javax.swing.JFrame") || Clazz.instanceOf(parent, "javax.swing.JDialog") || Clazz.instanceOf(parent, "javax.swing.JWindow")  ) {
return p$.getHeightAdjust$java_awt_Rectangle$java_awt_Rectangle.apply(this, [parent.getBounds(), popupRectInScreen]);
} else if (Clazz.instanceOf(parent, "javax.swing.JApplet")) {
if (this.popupFrameRect == null ) {
this.popupFrameRect = Clazz.new_((I$[10]||$incl$(10)));
}var p = parent.getLocationOnScreen();
this.popupFrameRect.setBounds$I$I$I$I(p.x, p.y, parent.getBounds().width, parent.getBounds().height);
return p$.getHeightAdjust$java_awt_Rectangle$java_awt_Rectangle.apply(this, [this.popupFrameRect, popupRectInScreen]);
}}
}return 0;
});

Clazz.newMeth(C$, 'getHeightAdjust$java_awt_Rectangle$java_awt_Rectangle', function (a, b) {
if (b.y >= a.y && (b.y + b.height) <= (a.y + a.height) ) return 0;
 else return (((b.y + b.height) - (a.y + a.height)) + 5);
});

Clazz.newMeth(C$, 'getWidthAdjust$java_awt_Rectangle$java_awt_Rectangle', function (a, b) {
if (b.x >= a.x && (b.x + b.width) <= (a.x + a.width) ) {
return 0;
} else {
return (((b.x + b.width) - (a.x + a.width)) + 5);
}});

Clazz.newMeth(C$, 'show$javax_swing_JComponent', function (source) {
if (this.tipWindow != null ) {
this.hideTipWindow();
this.insideComponent = null;
} else {
this.hideTipWindow();
this.enterTimer.stop();
this.exitTimer.stop();
this.insideTimer.stop();
this.insideComponent = source;
if (this.insideComponent != null ) {
this.toolTipText = this.insideComponent.getToolTipText();
this.preferredLocation = Clazz.new_((I$[7]||$incl$(7)).c$$I$I,[10, this.insideComponent.getHeight() + 10]);
this.showTipWindow();
if (this.focusChangeListener == null ) {
this.focusChangeListener = p$.createFocusChangeListener.apply(this, []);
}this.insideComponent.addFocusListener$java_awt_event_FocusListener(this.focusChangeListener);
}}});

Clazz.newMeth(C$, 'hide$javax_swing_JComponent', function (source) {
this.hideTipWindow();
source.removeFocusListener$java_awt_event_FocusListener(this.focusChangeListener);
this.preferredLocation = null;
this.insideComponent = null;
});
;
(function(){var C$=Clazz.newClass(P$.ToolTipManager, "insideTimerAction", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.awt.event.ActionListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
if (this.this$0.insideComponent != null  && this.this$0.insideComponent.isShowing() ) {
if (this.this$0.toolTipText == null  && this.this$0.mouseEvent != null  ) {
this.this$0.toolTipText = this.this$0.insideComponent.getToolTipText$java_awt_event_MouseEvent(this.this$0.mouseEvent);
this.this$0.preferredLocation = this.this$0.insideComponent.getToolTipLocation$java_awt_event_MouseEvent(this.this$0.mouseEvent);
}if (this.this$0.toolTipText != null ) {
this.this$0.showImmediately = true;
this.this$0.showTipWindow();
} else {
this.this$0.insideComponent = null;
this.this$0.toolTipText = null;
this.this$0.preferredLocation = null;
this.this$0.mouseEvent = null;
this.this$0.hideTipWindow();
}}});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.ToolTipManager, "outsideTimerAction", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.awt.event.ActionListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.this$0.showImmediately = false;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.ToolTipManager, "stillInsideTimerAction", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'java.awt.event.ActionListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'actionPerformed$java_awt_event_ActionEvent', function (e) {
this.this$0.hideTipWindow();
this.this$0.enterTimer.stop();
this.this$0.showImmediately = false;
this.this$0.insideComponent = null;
this.this$0.mouseEvent = null;
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.ToolTipManager, "MoveBeforeEnterListener", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.awt.event.MouseMotionAdapter');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'mouseMoved$java_awt_event_MouseEvent', function (e) {
this.this$0.initiateToolTip$java_awt_event_MouseEvent.apply(this.this$0, [e]);
});

Clazz.newMeth(C$);
})()
;
(function(){var C$=Clazz.newClass(P$.ToolTipManager, "AccessibilityKeyListener", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.awt.event.KeyAdapter');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'keyPressed$java_awt_event_KeyEvent', function (e) {
if (!e.isConsumed()) {
var source = e.getComponent();
if (e.getKeyCode() == 27) {
if (this.this$0.tipWindow != null ) {
this.this$0.hide$javax_swing_JComponent.apply(this.this$0, [source]);
e.consume();
}} else if (e.getKeyCode() == 112 && e.getModifiers() == 2 ) {
this.this$0.show$javax_swing_JComponent(source);
e.consume();
}}});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-06 08:59:44
