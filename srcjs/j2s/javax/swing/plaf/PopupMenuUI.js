(function(){var P$=Clazz.newPackage("javax.swing.plaf"),I$=[['javax.swing.PopupFactory']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "PopupMenuUI", null, 'javax.swing.plaf.ComponentUI');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'isPopupTrigger$java_awt_event_MouseEvent', function (e) {
return e.isPopupTrigger();
});

Clazz.newMeth(C$, 'getPopup$javax_swing_JPopupMenu$I$I', function (popup, x, y) {
var popupFactory = (I$[1]||$incl$(1)).getSharedInstance();
return popupFactory.getPopup$java_awt_Component$java_awt_Component$I$I(popup.getInvoker(), popup, x, y);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:51
