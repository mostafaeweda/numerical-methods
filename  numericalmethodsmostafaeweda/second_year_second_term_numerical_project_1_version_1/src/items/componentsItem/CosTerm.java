package items.componentsItem;

import items.TermIF;


/** */
public class CosTerm extends AbstractTerm
{
	public CosTerm(double c, double a) {
		super(c, a);
	}

	/** */
	public double evaluate(double x) {
		return coeff * Math.cos(attribute * x);
	}
	
	/** */
	public TermIF differentiate() {
		return new SinTerm(-coeff*attribute,attribute);
	}
	
	/** */
	public String toString() {
		return coeff+" cos (" + attribute + " X)";
	}
	
	/** */
	public void addTerm(TermIF term) {
	}
}