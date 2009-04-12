package methods;

import items.TermIF;


public class Secant extends EquationSolver 
{
	public Secant(TermIF term, double acc) throws NotFoundRootException 
	{
		super(term, acc);
	}

	public double getNext() 
	{
		double x0 = steps.get(numberOfSteps-2);
		double x1 = steps.get(numberOfSteps-1);
		double fx1 = equationToSolve.evaluate(x1);
		double x2 = x1 - (fx1*(x1 - x0)/(fx1 - equationToSolve.evaluate(x0)));
		steps.add(x2);
		numberOfSteps ++;
		return x2;
	}

	public double solve() 
	{
		// a , b are either given or evaluated by init() method
		steps.add(a);
		steps.add(b);
		numberOfSteps = 2;
		while (numberOfSteps < 500)
		{
			getNext();
			if (Math.abs(equationToSolve.evaluate(steps.get(numberOfSteps-2)) - equationToSolve.evaluate(steps.get(numberOfSteps-1)))<= accuracy)
			{
				steps.add((steps.get(numberOfSteps-2)+steps.get(numberOfSteps-1))/2);
				numberOfSteps ++;
//				System.out.println(numberOfSteps);
				return steps.get(numberOfSteps-1);
			}
		}
		return 0;
	}
}