// physlets-swingjs.js 
// Bob Hanson hansonr@stolaf.edu 3/17/2018 8:08:50 AM

initApplet0 = initApplet;
initApplet = function() { createSwingJSApplets() };


var addSwingJSInfo = function(Info, tagName, name, value) {
  // add  
  if (tagName && (tagName != "PARAM" || name == "permissions"))
    return;
  switch (value) {
  case "false":
    value = false;
    break;
  case "true":
    value = true;
    break;
  }
  Info[name] = value; 
}

var swingAppletReady = function(jsapplet) {
  xx = jsapplet;
  document[jsapplet.__Info.name] = jsapplet._applet;  
  initApplet0();
}
var createSwingJSApplet = function(applet) {
  Info = {
    code: null,
    main: null,
  	width: 850,
  	height: 550,
  	serverURL: 'http://chemapps.stolaf.edu/jmol/jsmol/php/jsmol.php',
  	j2sPath: 'j2s',
  	console:'none',
  	allowjavascript: true,
    readyFunction: swingAppletReady
  }
  var names = applet.getAttributeNames();
  for (var i = 0; i < names.length; i++) {
    addSwingJSInfo(Info, null, names[i], applet.getAttribute(names[i]));
  }
  for (var i = 0; i < applet.children.length; i++) {
    var p = applet.children[i];
    addSwingJSInfo(Info, p.tagName, p.name, p.value);
  }
  Info.code && (Info.code = Info.code.replace(/\.class/,""));
  var jsName = "js" + Info.name;
  $(applet).after(SwingJS.getAppletHtml(jsName, Info));
  $(applet).remove();
  console.log(Info);
}

function createSwingJSApplets() {
  var applets = document.getElementsByTagName("applet");
  for (var i = 0; i < applets.length; i++)
    createSwingJSApplet(applets[i]);
}


