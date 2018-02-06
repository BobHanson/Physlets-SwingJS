package impedance;

abstract class Component implements Netwerk {
	protected Component(double v) {
		value=v;
	}
	protected double value;
};

