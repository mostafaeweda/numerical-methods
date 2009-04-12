package items.componentsItem;

import items.TermIF;


/** */
public class XTerm extends AbstractTerm
{
	public XTerm(double c, double a) {
		super(c, a);
	}

	/** */
	public double evaluate(double x) {
		return coeff * (Math.pow(x, attribute));
	}
	
	/** */
	public TermIF differentiate() {
		if (attribute != 0)
			return new XTerm(coeff*attribute,attribute-1);
		return new EmptyTerm(); 
	}
	
	/** */
	public String toString() {
		if(coeff == 0)
			return "";
		else if (attribute != 0)
			return coeff + " X ^ " + attribute;
		return coeff + "";
	}
	
	/** */
	public void addTerm(TermIF term) {
	}
}
