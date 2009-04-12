package items.componentsItem;

import items.TermIF;
import items.compositesItem.ProductOfSums;

public class TanTerm extends AbstractTerm{

	public TanTerm(double c,double a){
		super (c,a);
	}
	
	public TermIF differentiate() {
		return new ProductOfSums(new SecTerm(coeff * attribute,attribute),new SecTerm(1,attribute));
	}

	public double evaluate(double x) {
		return coeff * Math.tan(x * attribute);
	}

	public String toString() {
		return coeff +" tan (" + attribute + "X)";
	}

}