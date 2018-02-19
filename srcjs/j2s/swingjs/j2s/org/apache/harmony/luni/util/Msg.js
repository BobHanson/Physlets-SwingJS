(function(){var P$=Clazz.newPackage("org.apache.harmony.luni.util"),I$=[['java.lang.StringBuilder']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Msg");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'getString$S', function (msg) {
return C$.getMsg$S(msg);
}, 1);

Clazz.newMeth(C$, 'getString$S$O', function (msg, arg) {
return C$.getString$S$OA(msg, Clazz.array(java.lang.Object, -1, [arg]));
}, 1);

Clazz.newMeth(C$, 'getString$S$I', function (msg, arg) {
return C$.getString$S$OA(msg, Clazz.array(java.lang.Object, -1, [Integer.toString(arg)]));
}, 1);

Clazz.newMeth(C$, 'getString$S$C', function (msg, arg) {
return C$.getString$S$OA(msg, Clazz.array(java.lang.Object, -1, [String.valueOf(arg)]));
}, 1);

Clazz.newMeth(C$, 'getString$S$O$O', function (msg, arg1, arg2) {
return C$.getString$S$OA(msg, Clazz.array(java.lang.Object, -1, [arg1, arg2]));
}, 1);

Clazz.newMeth(C$, 'getString$S$OA', function (msg, args) {
var format = C$.getMsg$S(msg);
return C$.format$S$OA(format, args);
}, 1);

Clazz.newMeth(C$, 'getMsg$S', function (msg) {
var pt = "K0006=Negative index specified\u000d\u000aK0007=attempt to write after finish\u000d\u000aK0008=Cannot read version\u000d\u000aK0009=Missing version string\\: {0}\u000d\u000aK000a=Entry is not named\u000d\u000aK000b=Invalid attribute {0}\u000d\u000aK000c=cannot resolve subclasses\u000d\u000aK000d=Unknown attribute\u000d\u000aK000e=Cannot add attributes to empty string\u000d\u000aK0014=Unquoted {0} in suffix\\: {1}\u000d\u000aK0015=Unexpected {0} in fraction\\: {1}\u000d\u000aK0016=Unexpected {0} in {1}\u000d\u000aK0017=Missing pattern before {0} in {1}\u000d\u000aK0018=Missing exponent format {0}\u000d\u000aK0019=Unterminated quote {0}\u000d\u000aK001a=Missing grouping format {0}\u000d\u000aK001b=Invalid exponent format {0}\u000d\u000aK001c=Invalid pattern char {0} in {1}\u000d\u000aK001d=Invalid argument number\u000d\u000aK001e=Missing element format\u000d\u000aK001f=Unknown element format\u000d\u000aK0020=Unknown format\u000d\u000aK002b=Unknown pattern character - \'{0}\'\u000d\u000aK002c=Access denied {0}\u000d\u000aK002e=Offset out of bounds\u000d\u000aK002f=Arguments out of bounds\u000d\u000aK0032=Address null or destination port out of range\u000d\u000aK0033=Unknown socket type\u000d\u000aK0034=Packet address mismatch with connected address\u000d\u000aK0035=Zero or negative buffer size\u000d\u000aK0036=Invalid negative timeout\u000d\u000aK0037=Connection already established\u000d\u000aK0038=No host name provided\u000d\u000aK0039=Attempted to join a non-multicast group\u000d\u000aK003a=Attempted to leave a non-multicast group\u000d\u000aK003c=TimeToLive out of bounds\u000d\u000aK003d=Socket is closed\u000d\u000aK003e=SOCKS connection failed\\: {0}\u000d\u000aK003f=Unable to connect to SOCKS server\\: {0}\u000d\u000aK0040=Invalid SOCKS client.\u000d\u000aK0041=Socket implementation does not support SOCKS.\u000d\u000aK0042=Socket implementation factory already set\u000d\u000aK0044=The factory has already been set\u000d\u000aK0045=Attempted to set a negative SoLinger\u000d\u000aK0046=Local port declared out of range\u000d\u000aK0047=buffer is null\u000d\u000aK0048=Invalid action specified\\: {0}\u000d\u000aK0049=MinPort is greater than MaxPort\u000d\u000aK004a=Invalid port number specified\u000d\u000aK004b=Attempt to set factory more than once.\u000d\u000aK004c=Package is sealed\u000d\u000aK004d=Does not support writing to the input stream\u000d\u000aK004e=Duplicate Factory\u000d\u000aK004f=rounding necessary\u000d\u000aK0050=wrong rounding mode\u000d\u000aK0051=scale value < than zero\u000d\u000aK0052=Array index out of range\\: {0}\u000d\u000aK0053=Package {0} already defined.\u000d\u000aK0055=String index out of range\\: {0}\u000d\u000aK0056=Already destroyed\u000d\u000aK0057=Has threads\u000d\u000aK0058=size must be > 0\u000d\u000aK0059=Stream is closed\u000d\u000aK005a=Mark has been invalidated.\u000d\u000aK005b=BufferedReader is closed\u000d\u000aK005c=Invalid Mark.\u000d\u000aK005d=Writer is closed.\u000d\u000aK005e=size must be >\\= 0\u000d\u000aK005f=Does not support writing to the output stream\u000d\u000aK0060=CharArrayReader is closed.\u000d\u000aK0062=Second byte at {0} does not match UTF8 Specification\u000d\u000aK0063=Third byte at {0} does not match UTF8 Specification\u000d\u000aK0064=Second or third byte at {0} does not match UTF8 Specification\u000d\u000aK0065=Input at {0} does not match UTF8 Specification\u000d\u000aK0066=Entry already exists: {0}\u000d\u000aK0068=String is too long\u000d\u000aK0069=File cannot compare to non File\u000d\u000aK006a=time must be positive\u000d\u000aK006b=Prefix must be at least 3 characters\u000d\u000aK006c=FileDescriptor is null\u000d\u000aK006d=actions invalid\u000d\u000aK006e=path is null\u000d\u000aK006f=invalid permission\\: {0}\u000d\u000aK0070=InputStreamReader is closed.\u000d\u000aK0071=Error fetching SUID\\: {0}\u000d\u000aK0072={0} computing SHA-1 / SUID\u000d\u000aK0073=OutputStreamWriter is closed.\u000d\u000aK0074=Not connected\u000d\u000aK0075=InputStream is closed\u000d\u000aK0076=Pipe broken\u000d\u000aK0077=Crc mismatch\u000d\u000aK0078=Pipe is closed\u000d\u000aK0079=Already connected\u000d\u000aK007a=Pipe already connected\u000d\u000aK007b=Pipe Not Connected\u000d\u000aK007e=Pushback buffer full\u000d\u000aK007f=Mark/Reset not supported\u000d\u000aK0080=Reader is closed\u000d\u000aK0081=Mode must be one of \"r\" or \"rw\"\u000d\u000aK0083=StringReader is closed.\u000d\u000aK0084=can only instantiate one BootstrapClassLoader\u000d\u000aK0086=Referenced reflect object is no longer valid\u000d\u000aK0087=Referenced reflect object is no longer valid\\: {0}\u000d\u000aK0088=Incorrect end of BER tag\u000d\u000aK0089=Unknown type\\: {0}\u000d\u000aK008a=Read {0} bytes trying to read {1} bytes from {2}\u000d\u000aK008b=Position\\: {0}\u000d\u000aK008c=Invalid Base64 char\\:{0}\u000d\u000aK008d=This protocol does not support input\u000d\u000aK008e=Does not support output\u000d\u000aK008f=This method does not support writing\\: {0}\u000d\u000aK0090=can\'t open OutputStream after reading from an inputStream\u000d\u000aK0091=Cannot access request header fields after connection is set\u000d\u000aK0092=Cannot set method after connection is made\u000d\u000aK0093=Too many redirects\u000d\u000aK0094=Unable to change directories\u000d\u000aK0095=Could not establish data connection\u000d\u000aK0096=Unable to retrieve file\\: {0}\u000d\u000aK0097=Unable to connect to server\\: {0}\u000d\u000aK0098=Unable to log into server\\: {0}\u000d\u000aK0099=Unable to configure data port\u000d\u000aK009a=Unable to store file\u000d\u000aK009b=Unable to set transfer type\u000d\u000aK00a2=Parsing policy file\\: {0}, expected quoted {1}, found unquoted\\: {2}\u000d\u000aK00a3=Parsing policy file\\: {0}, found unexpected\\: {1}\u000d\u000aK00a4=Content-Length underflow\u000d\u000aK00a5=Invalid parameter - {0}\u000d\u000aK00a8=Parsing policy file\\: {0}, invalid codesource URL\\: {1}\u000d\u000aK00ab=No active entry\u000d\u000aK00ae=Size mismatch\u000d\u000aK00af=Invalid proxy port\\: {0}\u000d\u000aK00b0=Proxy port out of range\u000d\u000aK00b1=Invalid port number\u000d\u000aK00b2=Content-Length exceeded\u000d\u000aK00b3=Unknown protocol\\: {0}\u000d\u000aK00b6=No entries\u000d\u000aK00b7=File is closed\u000d\u000aK00c1=Illegal character\u000d\u000aK00cd=Failure to connect to SOCKS server.\u000d\u000aK00ce=Unable to connect to identd to verify user.\u000d\u000aK00cf=Failure - user ids do not match.\u000d\u000aK00d0=Success\u000d\u000aK00d1=Read null attempting to read class descriptor for an object\u000d\u000aK00d2=Wrong format\\: 0x{0}\u000d\u000aK00d3=Read an exception\u000d\u000aK00d4={2} - {0} not compatible with {1}\u000d\u000aK00d5=Invalid typecode\\: {0}\u000d\u000aK00d7=Wrong base type in\\: {0}\u000d\u000aK00d8=Protocol not found\\: {0}\u000d\u000aK00d9=Callback object cannot be null\u000d\u000aK00da=Incompatible class (SUID)\\: {0} but expected {1}\u000d\u000aK00dc=IllegalAccessException\u000d\u000aK00e3=Could not create specified security manager\\: {0}\u000d\u000aK00e4=Key usage is critical and cannot be used for digital signature purposes.\u000d\u000aK00e5=month\\: {0}\u000d\u000aK00e6=day of month\\: {0}\u000d\u000aK00e7=day of week\\: {0}\u000d\u000aK00e8=time\\: {0}\u000d\u000aK00e9=DST offset\\: {0}\u000d\u000aK00ea=era\\: {0}\u000d\u000aK00eb={0} failed verification of {1}\u000d\u000aK00ec={0} has invalid digest for {1} in {2}\u000d\u000aK00ed={0} is not an interface\u000d\u000aK00ee={0} is not visible from class loader\u000d\u000aK00ef={0} appears more than once\u000d\u000aK00f0=non-public interfaces must be in the same package\u000d\u000aK00f1=not a proxy instance\u000d\u000aK00f2=the methods named {0} must have the same return type\u000d\u000aK00f3=Timer was cancelled\u000d\u000aK00f5=Illegal delay to start the TimerTask\u000d\u000aK00f6=TimerTask is scheduled already\u000d\u000aK00f7=TimerTask is cancelled\u000d\u000aK00f8=day of week in month\\: {0}\u000d\u000aK00f9=min or max digit count too large\u000d\u000aK00fa=min digits greater than max digits\u000d\u000aK00fb=min or max digits negative\u000d\u000aK00fc=Jar entry not specified\u000d\u000aK00fd=Invalid keystore\u000d\u000aK00fe=Incorrect password\u000d\u000aK0185=The alias already exists for a key entry.\u000d\u000aK018f=Can\'t convert to BMPString \\: {0}\u000d\u000aK0190=No data to decode\u000d\u000aK0191=Invalid size, must be a multiple of 64 from 512 to 1024\u000d\u000aK0193=An identity with this name already exists in this scope\u000d\u000aK0194=An identity in the scope has the same public key\u000d\u000aK0195=The existing public key and the one contained in the certificate do not match.\u000d\u000aK0196=Certificate is missing\u000d\u000aK0199=Count out of range\u000d\u000aK01a0=End of stream condition\u000d\u000aK01a4=Already shutting down\u000d\u000aK01a5=Illegal shutdown hook\\: {0}\u000d\u000aK01a6=Invalid filter\u000d\u000aK01a7=Name too long: {0}\u000d\u000aK01b3=Incorrect number of arguments\u000d\u000aK01b4=Cannot convert {0} to {1}\u000d\u000aK01b6=Cannot find \\!/\u000d\u000aK01c1=File is a Directory\u000d\u000aK01c2=Cannot create\\: {0}\u000d\u000aK01c3=Unable to open\\: {0}\u000d\u000aK01c4=Invalid zip file\\: {0}\u000d\u000aK01c6=No Main-Class specified in manifest\\: {0}\u000d\u000aK01d1=Signers of \'{0}\' do not match signers of other classes in package\u000d\u000aK01d2={1} - protected system package \'{0}\'\u000d\u000aK01ec=key size must be a multiple of 8 bits\u000d\u000aK01ed=key size must be at least 512 bits\u000d\u000aK01fe=Incomplete % sequence at\\: {0}\u000d\u000aK01ff=Invalid % sequence ({0}) at\\: {1}\u000d\u000aK0220=UTFDataFormatException\u000d\u000aK0222=No Manifest found in jar file\\: {0}\u000d\u000aK0300=Unsupported encoding\u000d\u000aK0301=Not signed data\u000d\u000aK0302=Relative path\u000d\u000aK0303=Scheme-specific part expected\u000d\u000aK0304=Authority expected\u000d\u000aK0305=Illegal character in scheme\u000d\u000aK0306={0} in schemeSpecificPart\u000d\u000aK0307={0} in authority\u000d\u000aK0308={0} in path\u000d\u000aK0309={0} in query\u000d\u000aK030a={0} in fragment\u000d\u000aK030c=Expected host\u000d\u000aK030d=Illegal character in userinfo\u000d\u000aK030e=Expected a closing square bracket for ipv6 address\u000d\u000aK030f=Malformed ipv6 address\u000d\u000aK0310=Illegal character in host name\u000d\u000aK0311=Malformed ipv4 address\u000d\u000aK0312=URI is not absolute\u000d\u000aK0313=Incomplete % sequence\u000d\u000aK0314=Invalid % sequence ({0})\u000d\u000aK0315=Socket is already bound\u000d\u000aK0316=SocketAddress {0} not supported\u000d\u000aK0317=Host is unresolved\\: {0}\u000d\u000aK0318=SocketAddress is null\u000d\u000aK0319=Exception in thread \"{0}\"\\ \u000d\u000aK031a=URI is not absolute\\: {0}\u000d\u000aK031b=URI is not hierarchical\\: {0}\u000d\u000aK031c=Expected file scheme in URI\\: {0}\u000d\u000aK031d=Expected non-empty path in URI\\: {0}\u000d\u000aK031e=Found {0} component in URI\\: {1}\u000d\u000aK031f=Socket is not bound\u000d\u000aK0320=Socket is not connected\u000d\u000aK0321=Socket input is shutdown\u000d\u000aK0322=Not a supported ISO 4217 Currency Code\\: {0}\u000d\u000aK0323=Not a supported ISO 3166 Country locale\\: {0}\u000d\u000aK0324=Needs dictionary\u000d\u000aK0325=Port out of range\\: {0}\u000d\u000aK0326={0} at index {1}\\: {2}\u000d\u000aK0327={0}\\: {1}\u000d\u000aK0328=Certificate not yet valid\u000d\u000aK0329=Certificate expired\u000d\u000aK0330=interface name is null\u000d\u000aK0331=address is null\u000d\u000aK0332=Invalid IP Address is neither 4 or 16 bytes\\: {0}\u000d\u000aK0333=Urgent data not supported\u000d\u000aK0334=Cannot set network interface with null\u000d\u000aK0335=No addresses associated with Interface\u000d\u000aK0337=null type not allowed\u000d\u000aK0338=Address not associated with an interface - not set\u000d\u000aK0339=Invalid IP Address is neither 4 or 16 bytes\u000d\u000aK0340={0} incompatible with {1}\u000d\u000aK0342=Scheme expected\u000d\u000aK0344=Not a valid {0}, subclass should override readResolve()\u000d\u000aK0346=Unmatched braces in the pattern\u000d\u000aK0347=seek position is negative\u000d\u000aK0348=Format specifier \'{0}\'\u000d\u000aK0349=Conversion is \'{0}\'\u000d\u000aK034a=The flags are {0}\u000d\u000aK034b=url and proxy can not be null\u000d\u000aK034c=proxy should not be null\u000d\u000aK034d=method has not been implemented yet\u000d\u000aK034e=Build rules empty\u000d\u000aK0351=format is null\u000d\u000aKA000=Line too long\u000d\u000aKA001=Argument must not be null\u000d\u000aKA002=Unshared read of back reference\u000d\u000aKA003=different mode already set\u000d\u000aKA004=Enums may not be cloned\u000d\u000aKA005={0} is not an enum type\u000d\u000aKA006={0} is not a constant in the enum type {1}\u000d\u000aKA007=field is null\u000d\u000aKA008={0} is an illegal radix\u000d\u000aKA009=CharsetName is illegal\u000d\u000aKA00a=File is null\u000d\u000aKA00b=InputStream is null\u000d\u000aKA00c=Readable is null\u000d\u000aKA00d=ReadableByteChannel is null\u000d\u000aKA00e=Radix {0} is less than Character.MIN_RADIX or greater than Character.MAX_RADIX\u000d\u000aKA00f=Socket output is shutdown\u000d\u000aKA010=Cannot read back reference to unshared object\u000d\u000aKA011=Malformed reply from SOCKS server\u000d\u000aKA012=No such file or directory\u000d\u000aKA013=Number of bytes to skip cannot be negative\u000d\u000aKA014=Invalit UUID string\u000d\u000aKA015=Incompatible class (base name)\\: {0} but expected {1}\u000d\u000a\u000d\u000a".indexOf(msg);
return (pt < 0 ? msg : msg.substring(pt + 6, msg.indexOf("\u000d", pt)));
}, 1);

Clazz.newMeth(C$, 'format$S$OA', function (format, args) {
var answer = Clazz.new_((I$[1]||$incl$(1)).c$$I,[format.length$() + (args.length * 20)]);
var argStrings = Clazz.array(java.lang.String, [args.length]);
for (var i = 0; i < args.length; ++i) {
if (args[i] == null ) argStrings[i] = "<null>";
 else argStrings[i] = args[i].toString();
}
var lastI = 0;
for (var i = format.indexOf("{", 0); i >= 0; i = format.indexOf("{", lastI)) {
if (i != 0 && format.charAt(i - 1) == "\\" ) {
if (i != 1) answer.append$S(format.substring(lastI, i - 1));
answer.append$C("{");
lastI = i + 1;
} else {
if (i > format.length$() - 3) {
answer.append$S(format.substring(lastI, format.length$()));
lastI = format.length$();
} else {
var argnum = ($b$[0] = ((format.charCodeAt(i + 1)) - 48), $b$[0]);
if (argnum < 0 || format.charAt(i + 2) != "}" ) {
answer.append$S(format.substring(lastI, i + 1));
lastI = i + 1;
} else {
answer.append$S(format.substring(lastI, i));
if (argnum >= argStrings.length) answer.append$S("<missing argument>");
 else answer.append$S(argStrings[argnum]);
lastI = i + 3;
}}}}
if (lastI < format.length$()) answer.append$S(format.substring(lastI, format.length$()));
return answer.toString();
}, 1);
var $b$ = new Int8Array(1);

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:03
