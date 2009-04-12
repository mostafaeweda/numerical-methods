package items.componentsItem;

import items.TermIF;


/** 
 * This class represents an abstract term
 * */
public abstract class AbstractTerm implements TermIF
{
	/** 
	 * The coefficient of the term 
	 */
	protected double coeff;
	
	/** 
	 * The attribute of the term 
	 */
	protected double attribute;
	
	/**
	 * Constructs new abstract term with coefficient and attribute  
	 * @param c
	 * @param a
	 */
	public AbstractTerm (double c,double a)
	{
		coeff = c;
		attribute = a;
	}
	/** 
	 * This method evaluates the value of the term 
	 */
	public abstract double evaluate(double x);
	
	/** 
	 * Differentiate the term and returns the differentiation of the term
	 */
	public abstract TermIF differentiate();
	
	/**
	 * Converts the expression to string 
	 */
	public abstract String toString();
	
	/**
	 * Do nothing
	 */
	public void addTerm(TermIF term)
	{
		//Do nothing
	}

	@Override
	public final double getAttribute()
	{
		return attribute;
	}

	@Override
	public final double getCoefficient()
	{
		return coeff;
	}

	@Override
	public final TermIF[] getTerms()
	{
		return new TermIF[] {this};
	}
}
