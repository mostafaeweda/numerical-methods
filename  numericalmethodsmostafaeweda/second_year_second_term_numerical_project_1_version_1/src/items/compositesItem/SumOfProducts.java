package items.compositesItem;

import items.TermIF;
import items.componentsItem.EmptyTerm;
import java.util.ArrayList;
import java.util.Iterator;


/** */
public class SumOfProducts implements TermIF
{
	/** */
	private ArrayList<TermIF> terms;
	
	/**
	 * This constructs new sum of products with empty list of terms 
	 */
	public SumOfProducts() 
	{
		terms = new ArrayList<TermIF>();
	}
	
	/**
	 * This constructs new sum of products with the given terms 
	 */
	public SumOfProducts(TermIF term1, TermIF term2)
	{
		terms = new ArrayList<TermIF>();
		terms.add(term1);
		terms.add(term2);
	}
	
	/** */
	public double evaluate(double x) {
		double result = 0;
		Iterator<TermIF> it = terms.iterator();
		while(it.hasNext())
		{
			result += it.next().evaluate(x);
		}
		return result;
	}
	
	/** */
	public TermIF differentiate() 
	{
		TermIF finalResult = new SumOfProducts();
		TermIF temp;
		int size = terms.size();
		for(int i = 0 ; i < size ; i++)
		{
			temp = terms.get(i).differentiate();
			if(!(temp instanceof EmptyTerm))
				finalResult.addTerm(temp);
		}
		return finalResult;
	}
	
	/** */
	public String toString() 
	{
		boolean termAdded = false;
		StringBuffer result = new StringBuffer();
		String tmp;
		Iterator<TermIF> it = terms.iterator();
		if(it.hasNext())
		{
			tmp = it.next().toString();
			if(!tmp.equals(""))
			{
				result.append(tmp);
				termAdded = true;
			}
			
			while(it.hasNext())
			{
				tmp = it.next().toString();
				if(!tmp.equals(""))
				{
					if(tmp.charAt(0) != '-' && termAdded)
						result.append(" +" + tmp);
					else
						result.append(tmp);
					termAdded = true;
				}
			}	
		}
		return result.toString();
	}
	
	/** */
	public void addTerm(TermIF term) 
	{
		terms.add(term);
	}

	@Override
	public TermIF[] getTerms()
	{
		return terms.toArray(new TermIF[terms.size()]);
	}

	/**
	 * NOT USED METHOD
	 */
	@Override
	public double getAttribute()
	{
		// TODO Auto-generated method stub
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
}