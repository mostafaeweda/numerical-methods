package controller;
import items.TermIF;
import items.componentsItem.CosTerm;
import items.componentsItem.ETerm;
import items.componentsItem.SinTerm;
import items.componentsItem.XTerm;
import items.compositesItem.ProductOfSums;
import items.compositesItem.SumOfProducts;



/** */
public class Controller
{
	/** */
	private Stack<Integer> signsStack;
	
	/** */
	private Stack<TermIF> itemsStack;
	
	/** */
	private MainWindow userIF;
	
	/** */
	private TermIF finalFunction;

	public Controller(MainWindow ui) {
		this.userIF = ui;
		signsStack = new Stack<Integer>();
		itemsStack = new Stack<TermIF>();
	}

	public String addTerm(String coeff, String attr, int operationType) {
		TermIF term = null;
		double co = Double.parseDouble(coeff);
		double at = Double.parseDouble(attr);
		switch(operationType) {
		case Constants.X:
			term = new XTerm(co, at);
			break;
		case Constants.SINE:
			term = new SinTerm(co, at);
			break;
		case Constants.COSINE:
			term  = new CosTerm(co, at);
			break;
		case Constants.E:
			term = new ETerm(co, at);
			break;
		default:
			throw new IllegalArgumentException();
		}
		itemsStack.push(term);
		return term.toString();
	}

	public void addSign(int val) {
		Integer i = new Integer(val);
		switch(val) {
		case Constants.OPEN_BRACE:
		case Constants.MULTIPLY:
			signsStack.push(i);
			break;
		case Constants.PLUS :
			while (! signsStack.isEmpty() && signsStack.peek() == Constants.MULTIPLY)
			{
				signsStack.pop();
				TermIF right = itemsStack.pop();
				TermIF left = itemsStack.pop();
				constructProductOfSum(left, right);
			}
			signsStack.push(i);
			break;
		case Constants.CLOSE_BRACE :
			while (! signsStack.isEmpty() && signsStack.peek() != Constants.OPEN_BRACE)
			{
				int temp = (signsStack.pop()).intValue();
				TermIF right = itemsStack.pop();
				TermIF left = itemsStack.pop();
				if (temp == Constants.PLUS)
					constructSumOfProduct(left, right);
				else
					constructProductOfSum(left, right);
			}
			signsStack.pop();
			break;
		}
		signsStack.push(i);
	}
	
	private void constructSumOfProduct(TermIF left, TermIF right)
	{
		if (left instanceof SumOfProducts)
		{
			left.addTerm(right);
			itemsStack.push(left);
		}
		else if (right instanceof SumOfProducts)
		{
			right.addTerm(left);
			itemsStack.push(right);
		}
		else
		{
			itemsStack.push(new SumOfProducts(left, right));
		}
	}
	
	private void constructProductOfSum(TermIF left, TermIF right)
	{
		if (left instanceof ProductOfSums)
		{
			left.addTerm(right);
			itemsStack.push(left);
		}
		else if (right instanceof ProductOfSums)
		{
			right.addTerm(left);
			itemsStack.push(right);
		}
		else
		{
			itemsStack.push(new ProductOfSums(left, right));
		}
	}
}
