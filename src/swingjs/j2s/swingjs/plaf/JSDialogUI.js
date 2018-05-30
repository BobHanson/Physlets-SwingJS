(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.plaf.JSComponentUI','javax.swing.LookAndFeel']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSDialogUI", null, 'swingjs.plaf.JSFrameUI', 'java.awt.peer.DialogPeer');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.z=(I$[1]||$incl$(1)).frameZ + 500;
this.zModal=this.z - 1;
this.isFrame=true;
this.isDialog=true;
this.defaultWidth=500;
this.defaultHeight=300;
this.setDoc();
}, 1);

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.frame=this.c;
this.isModal=(this.c).isModal();
(I$[2]||$incl$(2)).installColors$javax_swing_JComponent$S$S(jc, "Frame.background", "Frame.foreground");
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
});

Clazz.newMeth(C$, 'blockWindows$java_util_List', function (windows) {
});
})();
//Created 2018-05-24 08:47:53
