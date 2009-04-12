package methods;

import items.TermIF;

public class BirgVieta extends EquationSolver 
{
	private double[] as;
	private double[] bs;
	private double[] cs;

	public BirgVieta(TermIF equation, double acc) throws NotFoundRootException
	{
		super(equation, acc);
		TermIF[] terms = equationToSolve.getTerms();
		int maxPow = 0;
		int n = terms.length;
		for (int i = 0; i < n; i++) {
			int temp = (int) terms[i].getAttribute();
			if (temp > maxPow)
				maxPow = temp;
		}
		as = new double[maxPow + 1];
		bs = new double[maxPow + 1];
		cs = new double[maxPow];
		for (int i = 0; i < n; i++)
		{
			as[maxPow - (int) terms[i].getAttribute()] = terms[i].getCoefficient();
		}
	}

	public double solve()
	{
		steps.add(a);
		getNext();
		while (steps.get(steps.size() - 1) - steps.get(steps.size() - 2) > accuracy)
		{
			steps.add(getNext());
		}
		return steps.get(steps.size() - 1);
	}

	@Override
	public double getNext() {
		double x = steps.get(steps.size() - 1);
		int len = as.length;
		bs[0] = as[0];
		cs[0] = as[0];
		for (int i = 1; i < as.length - 1; i++)
		{
			bs[i] = bs[i - 1] * x + as[i];
			cs[i] = cs[i - 1] * x + bs[i];
		}
		bs[len - 1] = bs[len - 2] * x + as[len - 1];
		return x - (bs[len - 1] / cs[len - 2]);
	}
}
