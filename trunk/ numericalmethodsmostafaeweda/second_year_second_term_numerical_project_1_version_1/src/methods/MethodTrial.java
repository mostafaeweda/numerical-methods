package methods;

import items.TermIF;
import items.componentsItem.XTerm;
import items.compositesItem.SumOfProducts;

public class MethodTrial {

	public static void main(String[] args) throws NotFoundRootException {
		TermIF t1 = new XTerm(3, 3);
		TermIF t2 = new XTerm(-7, 2);
		TermIF t3 = new XTerm(14, 1);
		TermIF t4 = new XTerm(-6, 0);
		TermIF eqn = new SumOfProducts(t1, t2);
		eqn.addTerm(t3);
		eqn.addTerm(t4);
		EquationSolver bi = new BirgVieta(eqn, Math.pow(10, -3));
		double result = bi.solve();
		System.out.println(result);
		System.out.println(eqn.evaluate(result));
	}
}
