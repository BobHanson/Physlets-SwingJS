(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.util.ArrayList','java.io.File','swingjs.JSUtil','javax.swing.JFileChooser$1','javax.swing.JDialog','java.awt.event.WindowAdapter','javax.swing.JOptionPane','java.awt.BorderLayout','javax.swing.UIManager','javax.swing.filechooser.FileFilter','java.awt.Toolkit','java.awt.event.ActionListener','java.awt.EventQueue','java.awt.event.ActionEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JFileChooser", null, 'javax.swing.JComponent');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.dialogTitle = null;
this.approveButtonText = null;
this.approveButtonToolTipText = null;
this.approveButtonMnemonic = 0;
this.filters = null;
this.dialog = null;
this.dialogType = 0;
this.returnValue = 0;
this.accessory = null;
this.fileView = null;
this.uiFileView = null;
this.controlsShown = false;
this.useFileHiding = false;
this.showFilesListener = null;
this.fileSelectionMode = 0;
this.multiSelectionEnabled = false;
this.useAcceptAllFileFilter = false;
this.dragEnabled = false;
this.fileFilter = null;
this.currentDirectory = null;
this.selectedFile = null;
this.selectedFiles = null;
this.lastFileName = null;
this.opensaveType = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.dialogTitle = null;
this.approveButtonText = null;
this.approveButtonToolTipText = null;
this.approveButtonMnemonic = 0;
this.filters = Clazz.new_((I$[1]||$incl$(1)).c$$I,[5]);
this.dialog = null;
this.dialogType = 0;
this.returnValue = -1;
this.accessory = null;
this.fileView = null;
this.uiFileView = null;
this.controlsShown = true;
this.useFileHiding = true;
this.showFilesListener = null;
this.fileSelectionMode = 0;
this.multiSelectionEnabled = false;
this.useAcceptAllFileFilter = true;
this.dragEnabled = false;
this.fileFilter = null;
this.currentDirectory = null;
this.selectedFile = null;
this.lastFileName = "";
this.opensaveType = 2;
}, 1);

Clazz.newMeth(C$, 'getOpenSaveType', function () {
return this.opensaveType;
});

Clazz.newMeth(C$, 'c$', function () {
C$.c$$java_io_File.apply(this, [Clazz.new_((I$[2]||$incl$(2)).c$$S,["."])]);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (currentDirectoryPath) {
C$.c$$java_io_File.apply(this, [Clazz.new_((I$[2]||$incl$(2)).c$$S,[currentDirectoryPath])]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_File', function (currentDirectory) {
Clazz.super_(C$, this,1);
this.currentDirectory = currentDirectory;
}, 1);

Clazz.newMeth(C$, 'setDragEnabled$Z', function (b) {
this.dragEnabled = b;
});

Clazz.newMeth(C$, 'getDragEnabled', function () {
return this.dragEnabled;
});

Clazz.newMeth(C$, 'getSelectedFile', function () {
return this.selectedFile;
});

Clazz.newMeth(C$, 'setSelectedFile$java_io_File', function (file) {
var oldValue = this.selectedFile;
this.selectedFile = file;
if (this.selectedFile != null ) {
if (file.isAbsolute() && !p$.isParent$java_io_File$java_io_File.apply(this, [this.getCurrentDirectory(), this.selectedFile]) ) {
this.setCurrentDirectory$java_io_File(this.selectedFile.getParentFile());
}if (!this.isMultiSelectionEnabled() || this.selectedFiles == null   || this.selectedFiles.length == 1 ) {
this.ensureFileIsVisible$java_io_File(this.selectedFile);
}}this.firePropertyChange$S$O$O("SelectedFileChangedProperty", oldValue, this.selectedFile);
});

Clazz.newMeth(C$, 'isParent$java_io_File$java_io_File', function (dir, file) {
return file.getPath().indexOf(dir.getPath() + "/") == 0;
});

Clazz.newMeth(C$, 'getSelectedFiles', function () {
if (this.selectedFiles == null ) {
return Clazz.array((I$[2]||$incl$(2)), [0]);
} else {
return this.selectedFiles.clone();
}});

Clazz.newMeth(C$, 'setSelectedFiles$java_io_FileA', function (selectedFiles) {
var oldValue = this.selectedFiles;
if (selectedFiles == null  || selectedFiles.length == 0 ) {
selectedFiles = null;
this.selectedFiles = null;
this.setSelectedFile$java_io_File(null);
} else {
this.selectedFiles = selectedFiles.clone();
this.setSelectedFile$java_io_File(this.selectedFiles[0]);
}this.firePropertyChange$S$O$O("SelectedFilesChangedProperty", oldValue, selectedFiles);
});

Clazz.newMeth(C$, 'getCurrentDirectory', function () {
return this.currentDirectory;
});

Clazz.newMeth(C$, 'setCurrentDirectory$java_io_File', function (dir) {
var oldValue = this.currentDirectory;
if (dir != null  && !dir.exists() ) {
dir = this.currentDirectory;
}this.firePropertyChange$S$O$O("directoryChanged", oldValue, this.currentDirectory);
});

Clazz.newMeth(C$, 'changeToParentDirectory', function () {
this.selectedFile = null;
var oldValue = this.getCurrentDirectory();
this.setCurrentDirectory$java_io_File(p$.getParentDirectory$java_io_File.apply(this, [oldValue]));
});

Clazz.newMeth(C$, 'getParentDirectory$java_io_File', function (f) {
var path = f.getPath();
if (path.endsWith$S("/")) path = path.substring(path.length$() - 1);
return Clazz.new_((I$[2]||$incl$(2)).c$$S,[path.substring(0, path.lastIndexOf("/"))]);
});

Clazz.newMeth(C$, 'rescanCurrentDirectory', function () {
});

Clazz.newMeth(C$, 'ensureFileIsVisible$java_io_File', function (f) {
});

Clazz.newMeth(C$, 'showOpenDialog$java_awt_Component', function (parent) {
this.setDialogType$I(0);
return this.showDialog$java_awt_Component$S(parent, null);
});

Clazz.newMeth(C$, 'showSaveDialog$java_awt_Component', function (parent) {
this.setDialogType$I(1);
return this.showDialog$java_awt_Component$S(parent, null);
});

Clazz.newMeth(C$, 'showDialog$java_awt_Component$S', function (parent, approveButtonText) {
switch (this.dialogType) {
case 2:
(I$[3]||$incl$(3)).notImplemented$S("JFileChooser.CUSTOM_DIALOG");
return 1;
case 0:
if (!(Clazz.instanceOf(parent, "java.beans.PropertyChangeListener"))) {
C$.warnJSDeveloper();
return 1;
}this.removePropertyChangeListener$java_beans_PropertyChangeListener(parent);
this.addPropertyChangeListener$java_beans_PropertyChangeListener(parent);
var r = ((
(function(){var C$=Clazz.newClass(P$, "JFileChooser$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'Runnable', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'run', function () {

this.b$['javax.swing.JFileChooser'].selectedFile = arguments[0] || null;
this.b$['javax.swing.JFileChooser'].firePropertyChange$S$O$O("SelectedFile", null, this.b$['javax.swing.JFileChooser'].selectedFile);
});
})()
), Clazz.new_((I$[4]||$incl$(4)).$init$, [this, null]));
(I$[3]||$incl$(3)).J2S._getFileFromDialog(function(file){r.run(file)}||null, "java.io.File");
return (I$[5]||$incl$(5)).ASYNCHRONOUS_INTEGER;
case 1:
var name = (I$[3]||$incl$(3)).prompt$S$S((this.dialogTitle == null  ? "File to Save?" : this.dialogTitle), this.lastFileName);
if (name == null ) return 1;
this.selectedFile = Clazz.new_((I$[2]||$incl$(2)).c$$S,[name]);
p$.closeDialog.apply(this, []);
return 0;
}
if (approveButtonText != null ) {
this.setApproveButtonText$S(approveButtonText);
this.setDialogType$I(2);
}this.dialog = this.createDialog$java_awt_Component(parent);
this.dialog.addWindowListener$java_awt_event_WindowListener(((
(function(){var C$=Clazz.newClass(P$, "JFileChooser$2", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.awt.event.WindowAdapter'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'windowClosing$java_awt_event_WindowEvent', function (e) {
this.b$['javax.swing.JFileChooser'].returnValue = 1;
this.b$['javax.swing.JFileChooser'].closeDialog.apply(this.b$['javax.swing.JFileChooser'], []);
});
})()
), Clazz.new_((I$[6]||$incl$(6)), [this, null],P$.JFileChooser$2)));
this.returnValue = (I$[5]||$incl$(5)).ASYNCHRONOUS_INTEGER;
this.dialog.setVisible$Z(true);
return this.returnValue;
});

Clazz.newMeth(C$, 'warnJSDeveloper', function () {
System.err.println$S("JFileChooser: Component does not implement PropertyChangeListener.");
}, 1);

Clazz.newMeth(C$, 'closeDialog', function () {
this.firePropertyChange$S$O$O("SelectedFile", null, this.selectedFile);
if (this.dialog != null ) {
this.firePropertyChange$S$O$O("JFileChooserDialogIsClosingProperty", this.dialog, null);
this.dialog.dispose();
this.dialog = null;
}});

Clazz.newMeth(C$, 'createDialog$java_awt_Component', function (parent) {
var title = this.dialogTitle;
if (title == null ) title = "SwingJS";
var dialog;
var window = (I$[7]||$incl$(7)).getWindowForComponent$java_awt_Component(parent);
if (Clazz.instanceOf(window, "java.awt.Frame")) {
dialog = Clazz.new_((I$[5]||$incl$(5)).c$$java_awt_Frame$S$Z,[window, title, true]);
} else {
dialog = Clazz.new_((I$[5]||$incl$(5)).c$$java_awt_Dialog$S$Z,[window, title, true]);
}dialog.setComponentOrientation$java_awt_ComponentOrientation(this.getComponentOrientation());
var contentPane = dialog.getContentPane();
contentPane.setLayout$java_awt_LayoutManager(Clazz.new_((I$[8]||$incl$(8))));
contentPane.add$java_awt_Component$O(this, "Center");
if ((I$[5]||$incl$(5)).isDefaultLookAndFeelDecorated()) {
var supportsWindowDecorations = (I$[9]||$incl$(9)).getLookAndFeel().getSupportsWindowDecorations();
if (supportsWindowDecorations) {
dialog.getRootPane().setWindowDecorationStyle$I(6);
}}dialog.pack();
dialog.setLocationRelativeTo$java_awt_Component(parent);
return dialog;
});

Clazz.newMeth(C$, 'getControlButtonsAreShown', function () {
return this.controlsShown;
});

Clazz.newMeth(C$, 'setControlButtonsAreShown$Z', function (b) {
if (this.controlsShown == b ) {
return;
}var oldValue = this.controlsShown;
this.controlsShown = b;
this.firePropertyChange$S$Z$Z("ControlButtonsAreShownChangedProperty", oldValue, this.controlsShown);
});

Clazz.newMeth(C$, 'getDialogType', function () {
return this.dialogType;
});

Clazz.newMeth(C$, 'setDialogType$I', function (dialogType) {
if (this.dialogType == dialogType) {
return;
}if (!(dialogType == 0 || dialogType == 1  || dialogType == 2 )) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Incorrect Dialog Type: " + dialogType]);
}if (dialogType != 2) this.opensaveType = dialogType;
var oldValue = this.dialogType;
this.dialogType = dialogType;
if (dialogType == 0 || dialogType == 1 ) {
this.setApproveButtonText$S(null);
}this.firePropertyChange$S$I$I("DialogTypeChangedProperty", oldValue, dialogType);
});

Clazz.newMeth(C$, 'setDialogTitle$S', function (dialogTitle) {
var oldValue = this.dialogTitle;
this.dialogTitle = dialogTitle;
if (this.dialog != null ) {
this.dialog.setTitle$S(dialogTitle);
}this.firePropertyChange$S$O$O("DialogTitleChangedProperty", oldValue, dialogTitle);
});

Clazz.newMeth(C$, 'getDialogTitle', function () {
return this.dialogTitle;
});

Clazz.newMeth(C$, 'setApproveButtonToolTipText$S', function (toolTipText) {
if (this.approveButtonToolTipText == toolTipText) {
return;
}var oldValue = this.approveButtonToolTipText;
this.approveButtonToolTipText = toolTipText;
this.firePropertyChange$S$O$O("ApproveButtonToolTipTextChangedProperty", oldValue, this.approveButtonToolTipText);
});

Clazz.newMeth(C$, 'getApproveButtonToolTipText', function () {
return this.approveButtonToolTipText;
});

Clazz.newMeth(C$, 'getApproveButtonMnemonic', function () {
return this.approveButtonMnemonic;
});

Clazz.newMeth(C$, 'setApproveButtonMnemonic$I', function (mnemonic) {
if (this.approveButtonMnemonic == mnemonic) {
return;
}var oldValue = this.approveButtonMnemonic;
this.approveButtonMnemonic = mnemonic;
this.firePropertyChange$S$I$I("ApproveButtonMnemonicChangedProperty", oldValue, this.approveButtonMnemonic);
});

Clazz.newMeth(C$, 'setApproveButtonMnemonic$C', function (mnemonic) {
var vk = mnemonic.$c();
if (vk >= 97  && vk <= 122  ) {
vk = vk-(32);
}this.setApproveButtonMnemonic$I(vk);
});

Clazz.newMeth(C$, 'setApproveButtonText$S', function (approveButtonText) {
if (this.approveButtonText == approveButtonText) {
return;
}var oldValue = this.approveButtonText;
this.approveButtonText = approveButtonText;
this.firePropertyChange$S$O$O("ApproveButtonTextChangedProperty", oldValue, approveButtonText);
});

Clazz.newMeth(C$, 'getApproveButtonText', function () {
return this.approveButtonText;
});

Clazz.newMeth(C$, 'getChoosableFileFilters', function () {
var filterArray = Clazz.array((I$[10]||$incl$(10)), [this.filters.size()]);
this.filters.toArray$TTA(filterArray);
return filterArray;
});

Clazz.newMeth(C$, 'addChoosableFileFilter$javax_swing_filechooser_FileFilter', function (filter) {
if (filter != null  && !this.filters.contains$O(filter) ) {
var oldValue = this.getChoosableFileFilters();
this.filters.add$TE(filter);
this.firePropertyChange$S$O$O("ChoosableFileFilterChangedProperty", oldValue, this.getChoosableFileFilters());
if (this.fileFilter == null  && this.filters.size() == 1 ) {
this.setFileFilter$javax_swing_filechooser_FileFilter(filter);
}}});

Clazz.newMeth(C$, 'removeChoosableFileFilter$javax_swing_filechooser_FileFilter', function (f) {
if (this.filters.contains$O(f)) {
if (this.getFileFilter() === f ) {
this.setFileFilter$javax_swing_filechooser_FileFilter(null);
}var oldValue = this.getChoosableFileFilters();
this.filters.remove$O(f);
this.firePropertyChange$S$O$O("ChoosableFileFilterChangedProperty", oldValue, this.getChoosableFileFilters());
return true;
} else {
return false;
}});

Clazz.newMeth(C$, 'resetChoosableFileFilters', function () {
var oldValue = this.getChoosableFileFilters();
this.setFileFilter$javax_swing_filechooser_FileFilter(null);
this.filters.clear();
if (this.isAcceptAllFileFilterUsed()) {
this.addChoosableFileFilter$javax_swing_filechooser_FileFilter(this.getAcceptAllFileFilter());
}this.firePropertyChange$S$O$O("ChoosableFileFilterChangedProperty", oldValue, this.getChoosableFileFilters());
});

Clazz.newMeth(C$, 'getAcceptAllFileFilter', function () {
return Clazz.new_((I$[10]||$incl$(10)).c$$SA,[Clazz.array(java.lang.String, -1, ["*.*", "All Files"])]);
});

Clazz.newMeth(C$, 'isAcceptAllFileFilterUsed', function () {
return this.useAcceptAllFileFilter;
});

Clazz.newMeth(C$, 'setAcceptAllFileFilterUsed$Z', function (b) {
var oldValue = this.useAcceptAllFileFilter;
this.useAcceptAllFileFilter = b;
if (!b) {
this.removeChoosableFileFilter$javax_swing_filechooser_FileFilter(this.getAcceptAllFileFilter());
} else {
this.removeChoosableFileFilter$javax_swing_filechooser_FileFilter(this.getAcceptAllFileFilter());
this.addChoosableFileFilter$javax_swing_filechooser_FileFilter(this.getAcceptAllFileFilter());
}this.firePropertyChange$S$Z$Z("acceptAllFileFilterUsedChanged", oldValue, this.useAcceptAllFileFilter);
});

Clazz.newMeth(C$, 'getAccessory', function () {
return this.accessory;
});

Clazz.newMeth(C$, 'setAccessory$javax_swing_JComponent', function (newAccessory) {
var oldValue = this.accessory;
this.accessory = newAccessory;
this.firePropertyChange$S$O$O("AccessoryChangedProperty", oldValue, this.accessory);
});

Clazz.newMeth(C$, 'setFileSelectionMode$I', function (mode) {
if (this.fileSelectionMode == mode) {
return;
}if ((mode == 0) || (mode == 1) || (mode == 2)  ) {
var oldValue = this.fileSelectionMode;
this.fileSelectionMode = mode;
this.firePropertyChange$S$I$I("fileSelectionChanged", oldValue, this.fileSelectionMode);
} else {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Incorrect Mode for file selection: " + mode]);
}});

Clazz.newMeth(C$, 'getFileSelectionMode', function () {
return this.fileSelectionMode;
});

Clazz.newMeth(C$, 'isFileSelectionEnabled', function () {
return ((this.fileSelectionMode == 0) || (this.fileSelectionMode == 2) );
});

Clazz.newMeth(C$, 'isDirectorySelectionEnabled', function () {
return ((this.fileSelectionMode == 1) || (this.fileSelectionMode == 2) );
});

Clazz.newMeth(C$, 'setMultiSelectionEnabled$Z', function (b) {
if (this.multiSelectionEnabled == b ) {
return;
}var oldValue = this.multiSelectionEnabled;
this.multiSelectionEnabled = b;
this.firePropertyChange$S$Z$Z("MultiSelectionEnabledChangedProperty", oldValue, this.multiSelectionEnabled);
});

Clazz.newMeth(C$, 'isMultiSelectionEnabled', function () {
return this.multiSelectionEnabled;
});

Clazz.newMeth(C$, 'isFileHidingEnabled', function () {
return this.useFileHiding;
});

Clazz.newMeth(C$, 'setFileHidingEnabled$Z', function (b) {
if (this.showFilesListener != null ) {
(I$[11]||$incl$(11)).getDefaultToolkit().removePropertyChangeListener$S$java_beans_PropertyChangeListener("awt.file.showHiddenFiles", this.showFilesListener);
this.showFilesListener = null;
}var oldValue = this.useFileHiding;
this.useFileHiding = b;
this.firePropertyChange$S$Z$Z("FileHidingChanged", oldValue, this.useFileHiding);
});

Clazz.newMeth(C$, 'setFileFilter$javax_swing_filechooser_FileFilter', function (filter) {
var oldValue = this.fileFilter;
this.fileFilter = filter;
if (filter != null ) {
if (this.isMultiSelectionEnabled() && this.selectedFiles != null   && this.selectedFiles.length > 0 ) {
var fList = Clazz.new_((I$[1]||$incl$(1)));
var failed = false;
for (var i = 0; i < this.selectedFiles.length; i++) {
if (filter.accept$java_io_File(this.selectedFiles[i])) {
fList.add$TE(this.selectedFiles[i]);
} else {
failed = true;
}}
if (failed) {
this.setSelectedFiles$java_io_FileA((fList.size() == 0) ? null : fList.toArray$TTA(Clazz.array((I$[2]||$incl$(2)), [fList.size()])));
}} else if (this.selectedFile != null  && !filter.accept$java_io_File(this.selectedFile) ) {
this.setSelectedFile$java_io_File(null);
}}this.firePropertyChange$S$O$O("fileFilterChanged", oldValue, this.fileFilter);
});

Clazz.newMeth(C$, 'getFileFilter', function () {
return this.fileFilter;
});

Clazz.newMeth(C$, 'setFileView$javax_swing_filechooser_FileView', function (fileView) {
var oldValue = this.fileView;
this.fileView = fileView;
this.firePropertyChange$S$O$O("fileViewChanged", oldValue, fileView);
});

Clazz.newMeth(C$, 'getFileView', function () {
return this.fileView;
});

Clazz.newMeth(C$, 'getName$java_io_File', function (f) {
var filename = null;
if (f != null ) {
if (this.getFileView() != null ) {
filename = this.getFileView().getName$java_io_File(f);
}if (filename == null  && this.uiFileView != null  ) {
filename = this.uiFileView.getName$java_io_File(f);
}}return filename;
});

Clazz.newMeth(C$, 'getDescription$java_io_File', function (f) {
var description = null;
if (f != null ) {
if (this.getFileView() != null ) {
description = this.getFileView().getDescription$java_io_File(f);
}if (description == null  && this.uiFileView != null  ) {
description = this.uiFileView.getDescription$java_io_File(f);
}}return description;
});

Clazz.newMeth(C$, 'getTypeDescription$java_io_File', function (f) {
var typeDescription = null;
if (f != null ) {
if (this.getFileView() != null ) {
typeDescription = this.getFileView().getTypeDescription$java_io_File(f);
}if (typeDescription == null  && this.uiFileView != null  ) {
typeDescription = this.uiFileView.getTypeDescription$java_io_File(f);
}}return typeDescription;
});

Clazz.newMeth(C$, 'getIcon$java_io_File', function (f) {
var icon = null;
if (f != null ) {
if (this.getFileView() != null ) {
icon = this.getFileView().getIcon$java_io_File(f);
}if (icon == null  && this.uiFileView != null  ) {
icon = this.uiFileView.getIcon$java_io_File(f);
}}return icon;
});

Clazz.newMeth(C$, 'accept$java_io_File', function (f) {
var shown = true;
if (f != null  && this.fileFilter != null  ) {
shown = this.fileFilter.accept$java_io_File(f);
}return shown;
});

Clazz.newMeth(C$, 'approveSelection', function () {
this.returnValue = 0;
if (this.dialog != null ) {
this.dialog.setVisible$Z(false);
}this.fireActionPerformed$S("ApproveSelection");
});

Clazz.newMeth(C$, 'cancelSelection', function () {
this.returnValue = 1;
if (this.dialog != null ) {
this.dialog.setVisible$Z(false);
}this.fireActionPerformed$S("CancelSelection");
});

Clazz.newMeth(C$, 'addActionListener$java_awt_event_ActionListener', function (l) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[12]||$incl$(12)),['actionPerformed$java_awt_event_ActionEvent']), l);
});

Clazz.newMeth(C$, 'removeActionListener$java_awt_event_ActionListener', function (l) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[12]||$incl$(12)),['actionPerformed$java_awt_event_ActionEvent']), l);
});

Clazz.newMeth(C$, 'getActionListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[12]||$incl$(12)),['actionPerformed$java_awt_event_ActionEvent']));
});

Clazz.newMeth(C$, 'fireActionPerformed$S', function (command) {
var listeners = this.listenerList.getListenerList();
var mostRecentEventTime = (I$[13]||$incl$(13)).getMostRecentEventTime();
var modifiers = 0;
var currentEvent = (I$[13]||$incl$(13)).getCurrentEvent();
if (Clazz.instanceOf(currentEvent, "java.awt.event.InputEvent")) {
modifiers = (currentEvent).getModifiers();
} else if (Clazz.instanceOf(currentEvent, "java.awt.event.ActionEvent")) {
modifiers = (currentEvent).getModifiers();
}var e = null;
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[12]||$incl$(12)),['actionPerformed$java_awt_event_ActionEvent']) ) {
if (e == null ) {
e = Clazz.new_((I$[14]||$incl$(14)).c$$O$I$S$J$I,[this, 1001, command, mostRecentEventTime, modifiers]);
}(listeners[i + 1]).actionPerformed$java_awt_event_ActionEvent(e);
}}
});

Clazz.newMeth(C$, 'paramString', function () {
var approveButtonTextString = (this.approveButtonText != null  ? this.approveButtonText : "");
var dialogTitleString = (this.dialogTitle != null  ? this.dialogTitle : "");
var dialogTypeString;
if (this.dialogType == 0) {
dialogTypeString = "OPEN_DIALOG";
} else if (this.dialogType == 1) {
dialogTypeString = "SAVE_DIALOG";
} else if (this.dialogType == 2) {
dialogTypeString = "CUSTOM_DIALOG";
} else dialogTypeString = "";
var returnValueString;
if (this.returnValue == 1) {
returnValueString = "CANCEL_OPTION";
} else if (this.returnValue == 0) {
returnValueString = "APPROVE_OPTION";
} else if (this.returnValue == -1) {
returnValueString = "ERROR_OPTION";
} else returnValueString = "";
var useFileHidingString = (this.useFileHiding ? "true" : "false");
var fileSelectionModeString;
if (this.fileSelectionMode == 0) {
fileSelectionModeString = "FILES_ONLY";
} else if (this.fileSelectionMode == 1) {
fileSelectionModeString = "DIRECTORIES_ONLY";
} else if (this.fileSelectionMode == 2) {
fileSelectionModeString = "FILES_AND_DIRECTORIES";
} else fileSelectionModeString = "";
var currentDirectoryString = (this.currentDirectory != null  ? this.currentDirectory.toString() : "");
var selectedFileString = (this.selectedFile != null  ? this.selectedFile.toString() : "");
return C$.superclazz.prototype.paramString.apply(this, []) + ",approveButtonText=" + approveButtonTextString + ",currentDirectory=" + currentDirectoryString + ",dialogTitle=" + dialogTitleString + ",dialogType=" + dialogTypeString + ",fileSelectionMode=" + fileSelectionModeString + ",returnValue=" + returnValueString + ",selectedFile=" + selectedFileString + ",useFileHiding=" + useFileHidingString ;
});
})();
//Created 2018-02-08 10:02:30
