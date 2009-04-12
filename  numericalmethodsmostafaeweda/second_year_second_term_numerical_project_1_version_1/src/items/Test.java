package items;

import items.componentsItem.CosTerm;
import items.componentsItem.ETerm;
import items.componentsItem.SinTerm;
import items.componentsItem.XTerm;
import items.compositesItem.ProductOfSums;
import items.compositesItem.SumOfProducts;

public class Test {
	public static void main(String[] args) {
//		TermIF t1 = new XTerm (5,2);
//		TermIF t2 = new XTerm (2,1);
//		TermIF t3 = new ETerm (3,2);
//		TermIF t4 = new SinTerm(5,3);
//		TermIF t5 = new CosTerm(7,2);
		
		TermIF pos = new ProductOfSums(new ETerm(3,2),new CosTerm(1,2));
		System.out.println(pos.toString());
		TermIF pos2 = new ProductOfSums(new SumOfProducts(new XTerm(3,0),new XTerm(2,1)),new SinTerm(1,3));
		System.out.println(pos2.toString());
		TermIF sop = new SumOfProducts(pos,pos2);
		TermIF function = new ProductOfSums(sop,new SinTerm(1,1));
		System.out.println(function.toString());
		System.out.println("The derivative is:");
		System.out.println(function.differentiate().toString());
//		TermIF function2 = new ProductOfSums(t1,t2);
//		pos.addTerm(t3);
//		pos.addTerm(t4);
//		pos.addTerm(t5);
//		System.out.println(pos.toString());
//		System.out.println(pos.differentiate().differentiate().differentiate().toString());
//		System.out.println(function2.differentiate().toString());
//		System.out.println(function2.differentiate().differentiate().toString());
	}
}