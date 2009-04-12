package items.componentsItem;

import items.TermIF;
import items.compositesItem.ProductOfSums;

public class SecTerm extends AbstractTerm{

	public SecTerm (double c,double a){
		super (c,a);
	}
	public TermIF differentiate() {
		return new ProductOfSums(new SecTerm(coeff  * attribute,attribute),new TanTerm(1,attribute));
	}

	public double evaluate(double x) 
	{
		return coeff / Math.cos(x*attribute);
	}

	public String toString() 
	{
		return coeff + " sec (" + attribute + " X)";
	}

}
