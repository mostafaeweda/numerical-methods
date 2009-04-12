package items.compositesItem;
import items.TermIF;
import items.componentsItem.EmptyTerm;

import java.util.ArrayList;
import java.util.Iterator;


/** */
public class ProductOfSums implements TermIF
{
	/** */
	private ArrayList<TermIF> terms;
	
	/**
	 * This constructs new product of sums with empty list of terms 
	 */
	public ProductOfSums()
	{
		terms = new ArrayList<TermIF>();
	}
	
	/**
	 * This constructs new sum of products with the given 
	 * terms 
	 */
	public ProductOfSums(TermIF term1, TermIF term2)
	{
		terms = new ArrayList<TermIF>();
		terms.add(term1);
		terms.add(term2);
	}
	
	/** */
	public double evaluate(double x) 
	{
		double result = 1;
		Iterator<TermIF> it = terms.iterator();
		while(it.hasNext())
		{
			result *= it.next().evaluate(x);
		}
		return result;
	}
	
	/** */
	public TermIF differentiate() 
	{
		TermIF finalResult = new SumOfProducts();
		TermIF temp;
		TermIF termTemp;
		int size = terms.size();
		for(int i = 0 ; i < size ; i++)
		{
			//Create new product of sums to hold the new term
			temp = new ProductOfSums();
			/*
			 * Add all the terms that precede the term to be differentiated
			 * to the result of the sum of product
			 */
			for(int j = 0 ; j < i ; j++)
			{
				temp.addTerm(terms.get(j));
			}
			termTemp = terms.get(i).differentiate();
			if(!(termTemp instanceof EmptyTerm))
			{
				temp.addTerm(termTemp);
				/*
				 * Add all the terms that follow the term to be differentiated
				 * to the result of the sum of product
				 */
				for(int j = i + 1 ; j < size ; j++)
				{
					temp.addTerm(terms.get(j));
				}
				//Add the last term to the final result of the differentiation
				finalResult.addTerm(temp);
			}
		}
		return finalResult;
	}
	
	/** */
	public String toString() 
	{
		StringBuffer result = new StringBuffer();
		String tmp;
		Iterator<TermIF> it = terms.iterator();
		while(it.hasNext())
		{
			tmp = it.next().toString();
			if(!tmp.equals(""))
				result.append("(" + tmp + ")");
		}
		return result.toString();
	}
	
	/** */
	public void addTerm(TermIF term) 
	{
		terms.add(term);
	}

	/**
	 * NOT USED METHOD
	 */
	@Override
	public double getAttribute()
	{
		return 0;
	}

	/**
	 * NOT USED METHOD
	 */
	@Override
	public double getCoefficient()
	{
		return 0;
	}

	@Override
	public TermIF[] getTerms()
	{
		return terms.toArray(new TermIF[terms.size()]);
	}
}