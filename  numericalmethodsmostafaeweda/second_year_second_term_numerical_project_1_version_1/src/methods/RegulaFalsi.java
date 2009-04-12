package methods;

import items.TermIF;

public class RegulaFalsi extends Secant 
{

	public RegulaFalsi(TermIF term, double acc) throws NotFoundRootException 
	{
		super(term, acc);
	}
	public double getNext() 
	{
		double fxB = equationToSolve.evaluate(b);
		double c = b - (fxB*(b - a)/(fxB - equationToSolve.evaluate(a)));
		double fxC = equationToSolve.evaluate(c);
		steps.add(c);
		numberOfSteps ++;
		if(fxB * fxC < 0 )
			a = c;
		else
			b = c;
		return c;
	}
}
