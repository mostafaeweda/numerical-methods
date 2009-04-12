package items.componentsItem;

import items.TermIF;


/** */
public class ETerm extends AbstractTerm
{
	public ETerm(double c, double a) {
		super(c, a);
	}

	/** */
	public double evaluate(double x) {
		return coeff * Math.pow(Math.E, x*attribute);
	}
	
	/** */
	public TermIF differentiate() {
		return new ETerm(coeff * attribute,attribute);
	}
	
	/** */
	public String toString() {
		return coeff+" e ^ ("+attribute+" X)";
	}
	
	/** */
	public void addTerm(TermIF term) {
	}
}