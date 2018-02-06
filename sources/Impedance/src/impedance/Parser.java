package impedance;
/* Recursive descent parser

	BNF:
	expr -> expr + expr | expr / expr | expr // expr | ( expr ) | {R|r|C|c|L|l} (double)
	double -> getal | getal {E|e} getal | getal {E|e} - getal

	production rules:
	expr			->	serOperand restSerOperand

	restSerOperand	->	+ serOperand restSerOperand |
						empty

	serOperand		->	parOperand restParOperand

	restParOperand	->	{//|/} parOperand restParOperand |
						empty

	parOperand		->	( expr ) |
						{R|r} doubleConst  |
						{C|c} doubleConst  |
						{L|l} doubleConst

	doubleConst		->	( getal exponent )

	exponent		->	{E|e} getal |
						{E|e} - getal |
						empty
*/

public class Parser {
	public boolean parseExpr(String s) {
		s+=EIND; // afsluitteken is handig bij parsen.
		a=s.toCharArray();
		errorText=null;
		nextToken=errorPos=0;
		netwerk=expr();
		if (a[nextToken]!=EIND)
			error("Unknown token", nextToken);
		return errorText==null;
	}
	public String getErrorText() {
		return errorText;
	}
	public int getErrorPos() {
		return errorPos+1;
	}
	public Netwerk getResult() {
		return netwerk;
	}
	private static final char EIND='\uBDBD';
	private char[] a;
	private int nextToken;
	private String errorText;
	private int errorPos;
	private Netwerk netwerk;
	private void error(String text, int errorToken) {
		if (errorText==null) { // registreer alleen de eerste fout
			errorPos=errorToken;
			errorText=text;
		}
	}
	private Netwerk expr() {
		return restSerOperand(serOperand());
	}
	private Netwerk restSerOperand(Netwerk n) {
		if (a[nextToken]=='+') {
			++nextToken;
			return restSerOperand(new SeriesNetwork(n, serOperand()));
		}
		return n;
	}
	private Netwerk serOperand() {
		return restParOperand(parOperand());
	}
	private Netwerk restParOperand(Netwerk n) {
		if (a[nextToken]=='/') {
			++nextToken;
			if (a[nextToken]=='/')
				++nextToken;
			return restParOperand(new P(n, parOperand()));
		}
		return n;
	}
	private Netwerk parOperand() {
		switch (a[nextToken]) {
			case '(':	++nextToken;
						Netwerk n=expr();
						if (a[nextToken]!=')')
							error(") missing", nextToken);
						if (a[nextToken]!=EIND)
							++nextToken;
						return n;
			case 'R':
			case 'r':   ++nextToken;
						return new R(doubleConst());
			case 'C':
			case 'c':	++nextToken;
						return new C(doubleConst());
			case 'L':
			case 'l':   ++nextToken;
						return new L(doubleConst());
			default:	error("R, C or L expected", nextToken);
		}
		return null;
	}
	private double doubleConst() {
		if (a[nextToken]!='(')
			error("( missing", nextToken);
		if (a[nextToken]!=EIND) 
			++nextToken;
		long m=getal();
		long e=exponent();
		if (a[nextToken]!=')')
			error(") missing", nextToken);
		if (a[nextToken]!=EIND)
			++nextToken;
		return m*Math.pow(10, e);
	}
	private long exponent() {
		if (a[nextToken]=='E'||a[nextToken]=='e') {
			++nextToken;
			if (a[nextToken]=='-') {
				++nextToken;
				return -getal();
			}
			return getal();
		}
		return 0;
	}
	private long getal() {
		long g=0;
		if (Character.isDigit(a[nextToken])) {
			while (Character.isDigit(a[nextToken]))
				g=g*10+a[nextToken++]-'0';
		}
		else
			error("digit 0 .. 9 expected", nextToken);
		return g;
	}
}
