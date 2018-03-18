// physlets-swingjs.js 
// Bob Hanson hansonr@stolaf.edu 3/17/2018 8:08:50 AM
// BH 3/18/2018 7:33:17 AM added allApplets

// presumes body onload=initApplet()

initApplet0 = initApplet;
initApplet = function() { createSwingJSApplets() };

allApplets = {}; // all must report loaded=true before initApplet0()

function createSwingJSApplets() {
  // converts all <applet> tags to SwingJS applets
  // called by body.onLoad()
  var applets = document.getElementsByTagName("applet");
  // first sweep page collecting applet HTML
  for (var i = 0; i < applets.length; i++)
    createSwingJSApplet(applets[i]);
  // then start them all -- note that applet loading is asynchronous
  // we could also set this up to be synchronous by just starting the
  // first and then, when it returns, start the next.
  for (var i = 0; i < applets.length; i++)
    startSwingJSApplet(applets[i]);
}

function swingAppletReady(jsapplet) {
  // called by SwingJS when the applet has finished running start()
  var javaName = jsapplet.__Info.name;
  document[javaName] = jsapplet._applet;
  allApplets[javaName].loaded = true;
  // check that all applets are loaded
  for (var name in allApplets)
    if (!allApplets[name].loaded)
      return;
  initApplet0();
}

var startSwingJSApplet = function(applet) {
  $(applet).after(allApplets[applet.getAttribute("name")].html);
  $(applet).remove();
}

var createSwingJSApplet = function(applet) {
  var Info = {
    code: null,
    main: null,
  	width: 850,
  	height: 550,
  	serverURL: 'http://chemapps.stolaf.edu/jmol/jsmol/php/jsmol.php',
  	j2sPath: 'j2s',
  	console:'none',
  	allowjavascript: true,
    disableJ2SLoadMonitor: false,
    disableInitialConsole: false,
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
  var javaName = Info.name;
  var jsName = "js" + javaName;
  allApplets[javaName] = {
    loaded: false,
    html: SwingJS.getAppletHtml(jsName, Info)
  }
  console.log(Info);
}

var addSwingJSInfo = function(Info, tagName, name, value) {
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

