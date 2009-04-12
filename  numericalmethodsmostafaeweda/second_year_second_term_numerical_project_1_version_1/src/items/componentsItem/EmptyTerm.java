package items.componentsItem;

import items.TermIF;


public class EmptyTerm extends AbstractTerm 
{
	public EmptyTerm() 
	{
		super(0, 0);
	}
	
	public EmptyTerm(double c, double a) 
	{
		super(0, 0);
	}

	public TermIF differentiate() 
	{
		return null;
	}

	public double evaluate(double x) 
	{
		return 0;
	}

	public String toString() 
	{
		return "";
	}
}
