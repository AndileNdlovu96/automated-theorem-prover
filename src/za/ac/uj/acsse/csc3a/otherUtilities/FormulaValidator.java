/**
 * 
 */
package za.ac.uj.acsse.csc3a.otherUtilities;

import java.util.Iterator;

import za.ac.uj.acsse.csc3a.binaryTree.BTNode;
import za.ac.uj.acsse.csc3a.binaryTree.BinaryTree;
import za.ac.uj.acsse.csc3a.linkedList.LNode;
import za.ac.uj.acsse.csc3a.linkedList.LinkedList;
import za.ac.uj.acsse.csc3a.queue.LinkedQueue;
import za.ac.uj.acsse.csc3a.stack.LinkedStack;

/**
 * @author Andile Ndlovu
 *
 */
public class FormulaValidator {
	String expression;
	boolean isValid;

	/**
	 * @param expression
	 */
	public FormulaValidator(String expression) {
		super();
		this.expression = expression;
		this.isValid = semanticTableauAlgorithm(expression);
	}

	/**
	 * @return the isValid
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}
	
	
	public static boolean isMatched(String expression) { 
		final String openingParentheses = "({["; // opening delimiters
		final String closingParentheses = ")}]"; // respective closing delimiters
		LinkedStack<Character> bufferStack = new LinkedStack<>();
		for (char c : expression.toCharArray()) {
			if (openingParentheses.indexOf(c) != -1) // this is a left delimiter
				bufferStack.push(c);
			else if (closingParentheses.indexOf(c) != -1) { // this is a right delimiter
				if (bufferStack.isEmpty( )) // nothing to match with
					return false;
				if (closingParentheses.indexOf(c) != openingParentheses.indexOf(bufferStack.pop()))
					return false; // mismatched delimiter
			} 
		} return bufferStack.isEmpty(); // were all opening delimiters matched?
	}
	
	public NonBranchingFormulaHandler isNonBranchingFormula(String expression, boolean sign){
		System.out.println("Checking "+ expression+ " to see if it is a non branching formula.");
		if((expression.startsWith("(")&&expression.endsWith("))")) || 
				(expression.startsWith("((")&&expression.endsWith("")) || 
				(expression.startsWith("((")&&expression.endsWith("))"))||
				(expression.startsWith("((")&&expression.endsWith(")"))){
			
			expression = expression.substring(1, expression.length()-1);
			System.out.println("Removing unnecessary parenthesis...");
			System.out.println(expression);
			
		}
		char[] chArr = expression.toCharArray();
		System.out.println("Looping through every character in formula string");
		for(int i = 0; i < expression.length(); i++){
			//for AND
			String A = expression.substring(0, i);
			if(A.startsWith("(") && !A.endsWith(")") && isMatched(A+")"))A = A+")";
			String B = expression.substring(i+1);
			if(!B.startsWith("(") && B.endsWith(")") && isMatched("("+B))B = "("+B;
			System.out.println("A: "+ A);
			System.out.println("B: "+ B);
			/***************AND***********************/
			// (stuff)A(stuff)
			if((chArr[i] == Connectives.AND.getSymbol()) && (A.length() > 2) && (B.length() > 2) && (chArr[i-1] == ')') && (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == true)){
				System.out.println("Matches and");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, true, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, true, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//thingA(stuff)
			else if((chArr[i] == Connectives.AND.getSymbol()) && (A.length()  == 1) && (B.length() > 2)&& (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == true)){
				System.out.println("Matches and");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, true, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, true, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//(stuff)Athing
			else if((chArr[i] == Connectives.AND.getSymbol()) && (A.length() > 2) && (B.length() == 1)&& (chArr[i-1] == ')')
					&& isMatched(A) && isMatched(B) && (sign == true)){
				System.out.println("Matches and");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, true, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, true, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//thingAthing
			else if((chArr[i] == Connectives.AND.getSymbol()) && (A.length() == 1) && (B.length() == 1) 
					&& isMatched(A) && isMatched(B) && (expression.length() == 3) && (sign == true)){
				System.out.println("Matches and");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, true, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, true, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//(thingAthing)
			else if((expression.indexOf("A") == 2) && (expression.indexOf("(") == 0) && (expression.indexOf(")") == 4) && isMatched(expression.substring(1, 2))
					&& isMatched(expression.substring(3, 4)) && (sign == true)){
				System.out.println("Matches and");
				System.out.println(expression.substring(1, 2));
				System.out.println(expression.substring(3, 4));
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(expression.substring(1, 2), 1, true, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(expression.substring(3, 4), 1, true, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			/***************OR***********************/
			// (stuff)V(stuff)
			if((chArr[i] == Connectives.OR.getSymbol()) && (A.length() > 2) && (B.length() > 2) && (chArr[i-1] == ')') && (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == false)){
				System.out.println("Matches or");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, false, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, false, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//thingV(stuff)
			else if((chArr[i] == Connectives.OR.getSymbol()) && (A.length()  == 1) && (B.length() > 2)&& (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == false)){
				System.out.println("Matches or");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, false, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, false, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//(stuff)Vthing
			else if((chArr[i] == Connectives.OR.getSymbol()) && (A.length() > 2) && (B.length() == 1)&& (chArr[i-1] == ')')
					&& isMatched(A) && isMatched(B) && (sign == false)){
				System.out.println("Matches or");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, false, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, false, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//thingVthing
			else if((chArr[i] == Connectives.OR.getSymbol()) && (A.length() == 1) && (B.length() == 1) 
					&& isMatched(A) && isMatched(B) && (expression.length() == 3) && (sign == false)){
				System.out.println("Matches or");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, false, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, false, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//(thingVthing)
			else if((expression.indexOf("V") == 2) && (expression.indexOf("(") == 0) && (expression.indexOf(")") == 4) && isMatched(expression.substring(1, 2))
					&& isMatched(expression.substring(3, 4)) && (sign == false)){
				System.out.println("Matches or");
				System.out.println(expression.substring(1, 2));
				System.out.println(expression.substring(3, 4));
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(expression.substring(1, 2), 1, false, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(expression.substring(3, 4), 1, false, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			/***************IMPLIES***********************/
			// (stuff):(stuff)
			if((chArr[i] == Connectives.IMPLIES.getSymbol()) && (A.length() > 2) && (B.length() > 2) && (chArr[i-1] == ')') && (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == false)){
				System.out.println("Matches implies");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, true, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, false, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//thing:(stuff)
			else if((chArr[i] == Connectives.IMPLIES.getSymbol()) && (A.length()  == 1) && (B.length() > 2)&& (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == false)){
				System.out.println("Matches implies");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, true, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, false, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//(stuff):thing
			else if((chArr[i] == Connectives.IMPLIES.getSymbol()) && (A.length() > 2) && (B.length() == 1)&& (chArr[i-1] == ')')
					&& isMatched(A) && isMatched(B) && (sign == false)){
				System.out.println("Matches implies");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, true, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, false, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//thing:thing
			else if((chArr[i] == Connectives.IMPLIES.getSymbol()) && (A.length() == 1) && (B.length() == 1) 
					&& isMatched(A) && isMatched(B) && (expression.length() == 3) && (sign == false)){
				System.out.println("Matches implies");
				System.out.println(A);
				System.out.println(B);
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(A, 1, true, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(B, 1, false, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			//(thing:thing)
			else if((expression.indexOf(":") == 2) && (expression.indexOf("(") == 0) && (expression.indexOf(")") == 4) && isMatched(expression.substring(1, 2))
					&& isMatched(expression.substring(3, 4)) && (sign == false)){
				System.out.println("Matches implies");
				System.out.println(expression.substring(1, 2));
				System.out.println(expression.substring(3, 4));
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(expression.substring(1, 2), 1, true, false);
				FormulaNodeHandler formulaB = new FormulaNodeHandler(expression.substring(3, 4), 1, false, false);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return new NonBranchingFormulaHandler(formulaA, formulaB, true);
			}
			/**********************NOT********************/
			// if statrts with ( followed by ! and ends with )
			else if(expression.startsWith("(") && expression.indexOf("!") == 1 && expression.endsWith(")") && isMatched(expression.substring(2, expression.length()-1))){
				System.out.println("Matches not");
				System.out.println(expression.substring(2, expression.length()-1));
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(expression.substring(2, expression.length()-1), 1, (sign? false: true), false);
				System.out.println("_________________________________________________________________________________________________________________");
				return new NonBranchingFormulaHandler(formulaA, null, true);
			}
			//if starts with ! followed by ( and ends with )
			else if (expression.startsWith("!") && expression.indexOf("(") == 1 && expression.endsWith(")") && isMatched(expression.substring(1))){
				System.out.println("Matches not");
				System.out.println(expression.substring(1));
				System.out.println("Non-branching");
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler formulaA = new FormulaNodeHandler(expression.substring(1), 1, (sign? false: true), false);
				System.out.println("_________________________________________________________________________________________________________________");
				return new NonBranchingFormulaHandler(formulaA, null, true);
			}

		}		
		return null;
	}
	
	public boolean isBranchingFormula(String expression, boolean sign, BTNode<TableauNodeHandler<FormulaNodeHandler>> currNode, LinkedQueue<BTNode<TableauNodeHandler<FormulaNodeHandler>>> tableauNodeQueue){
		System.out.println("Checking "+ expression+ " to see if it is a branching formula.");
		if((expression.startsWith("(")&&expression.endsWith("))")) || 
				(expression.startsWith("((")&&expression.endsWith("")) || 
				(expression.startsWith("((")&&expression.endsWith("))"))||
				(expression.startsWith("((")&&expression.endsWith(")"))){
			
			expression = expression.substring(1, expression.length()-1);
			System.out.println("Removing unnecessary parenthesis...");
			System.out.println(expression);
			
		}
		char[] chArr = expression.toCharArray();
		System.out.println("Looping through every character in formula string");
		for(int i = 0; i < expression.length(); i++){
			//for AND
			String A = expression.substring(0, i);
			if(A.startsWith("(") && !A.endsWith(")") && isMatched(A+")"))A = A+")";
			String B = expression.substring(i+1);
			if(!B.startsWith("(") && A.endsWith(")") && isMatched("("+B))B = "("+B;
			System.out.println("A: "+ A);
			System.out.println("B: "+ B);
			
			
			/***************AND***********************/
			// (stuff)A(stuff)
			if((chArr[i] == Connectives.AND.getSymbol()) && (A.length() > 2) && (B.length() > 2) && (chArr[i-1] == ')') && (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == false)){
				System.out.println("Matches and");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, false, B, false, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//thingA(stuff)
			else if((chArr[i] == Connectives.AND.getSymbol()) && (A.length()  == 1) && (B.length() > 2)&& (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == false)){
				System.out.println("Matches and");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, false, B, false, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			
			//(stuff)Athing
			else if((chArr[i] == Connectives.AND.getSymbol()) && (A.length() > 2) && (B.length() == 1)&& (chArr[i-1] == ')')
					&& isMatched(A) && isMatched(B) && (sign == false)){
				System.out.println("Matches and");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, false, B, false, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//thingAthing
			else if((chArr[i] == Connectives.AND.getSymbol()) && (A.length() == 1) && (B.length() == 1) 
					&& isMatched(A) && isMatched(B) && (expression.length() == 3) && (sign == false)){
				System.out.println("Matches and");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, false, B, false, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//(thingAthing)
			else if((expression.indexOf("A") == 2) && (expression.indexOf("(") == 0) && (expression.indexOf(")") == 4) && isMatched(expression.substring(1, 2))
					&& isMatched(expression.substring(3, 4)) && (sign == false)){
				System.out.println("Matches and");
				System.out.println(expression.substring(1, 2));
				System.out.println(expression.substring(3, 4));
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(expression.substring(1, 2), false, expression.substring(3, 4), false, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return true;
			}
			/***************OR***********************/
			// (stuff)V(stuff)
			if((chArr[i] == Connectives.OR.getSymbol()) && (A.length() > 2) && (B.length() > 2) && (chArr[i-1] == ')') && (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == true)){
				System.out.println("Matches or");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, true, B, true, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//thingV(stuff)
			else if((chArr[i] == Connectives.OR.getSymbol()) && (A.length()  == 1) && (B.length() > 2)&& (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == true)){
				System.out.println("Matches or");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, true, B, true, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//(stuff)Vthing
			else if((chArr[i] == Connectives.OR.getSymbol()) && (A.length() > 2) && (B.length() == 1)&& (chArr[i-1] == ')')
					&& isMatched(A) && isMatched(B) && (sign == true)){
				System.out.println("Matches or");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, true, B, true, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//thingVthing
			else if((chArr[i] == Connectives.OR.getSymbol()) && (A.length() == 1) && (B.length() == 1) 
					&& isMatched(A) && isMatched(B) && (expression.length() == 3) && (sign == true)){
				System.out.println("Matches or");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, true, B, true, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//(thingAthing)
			else if((expression.indexOf("V") == 2) && (expression.indexOf("(") == 0) && (expression.indexOf(")") == 4) && isMatched(expression.substring(1, 2))
					&& isMatched(expression.substring(3, 4)) && (sign == true)){
				System.out.println("Matches or");
				System.out.println(expression.substring(1, 2));
				System.out.println(expression.substring(3, 4));
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(expression.substring(1, 2), true, expression.substring(3, 4), true, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return true;
			}
			/***************IMPLIES***********************/
			// (stuff):(stuff)
			if((chArr[i] == Connectives.IMPLIES.getSymbol()) && (A.length() > 2) && (B.length() > 2) && (chArr[i-1] == ')') && (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == true)){
				System.out.println("Matches implies");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, false, B, true, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//thing:(stuff)
			else if((chArr[i] == Connectives.IMPLIES.getSymbol()) && (A.length()  == 1) && (B.length() > 2)&& (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B) && (sign == true)){
				System.out.println("Matches implies");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, false, B, true, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//(stuff):thing
			else if((chArr[i] == Connectives.IMPLIES.getSymbol()) && (A.length() > 2) && (B.length() == 1)&& (chArr[i-1] == ')')
					&& isMatched(A) && isMatched(B) && (sign == true)){
				System.out.println("Matches implies");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, false, B, true, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//thing:thing
			else if((chArr[i] == Connectives.IMPLIES.getSymbol()) && (A.length() == 1) && (B.length() == 1) 
					&& isMatched(A) && isMatched(B) && (expression.length() == 3) && (sign == true)){
				System.out.println("Matches implies");
				System.out.println(A);
				System.out.println(B);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(A, false, B, true, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//(thing:thing)
			else if((expression.indexOf(":") == 2) && (expression.indexOf("(") == 0) && (expression.indexOf(")") == 4) && isMatched(expression.substring(1, 2))
					&& isMatched(expression.substring(3, 4)) && (sign == true)){
				System.out.println("Matches and");
				System.out.println(expression.substring(1, 2));
				System.out.println(expression.substring(3, 4));
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				handleBranchingFormula(expression.substring(1, 2), false, expression.substring(3, 4), true, currNode, tableauNodeQueue);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return true;
			}
			/***************BI_IMPLIES***********************/
			// (stuff)=(stuff)
			if((chArr[i] == Connectives.BI_IMPLIES.getSymbol()) && (A.length() > 2) && (B.length() > 2) && (chArr[i-1] == ')') && (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B)){
				System.out.println("Matches bi-implies");
				System.out.println(A+":"+B);
				System.out.println(B+":"+A);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				//handling bidirection ... its a special case
				FormulaNodeHandler subFormula1A = new FormulaNodeHandler(A, 0, true, false);
				FormulaNodeHandler subFormula1B = new FormulaNodeHandler(B, 1, (sign == true? true:false), false);
				LinkedList<FormulaNodeHandler> formulaListFor1 = new LinkedList<>();
				formulaListFor1.addFirst(subFormula1A);
				formulaListFor1.addLast(subFormula1B);
				BTNode<TableauNodeHandler<FormulaNodeHandler>> subNode1 = new BTNode<>(currNode, null, null,
						new TableauNodeHandler<>(formulaListFor1, false));
				currNode.setLeftNode(subNode1);
				searchForClosure(currNode, subFormula1A, true, true);
				searchForClosure(currNode, subFormula1B, true, true);
				tableauNodeQueue.enqueue(subNode1);
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler subFormula2A = new FormulaNodeHandler(A, 0, false, false);
				FormulaNodeHandler subFormula2B = new FormulaNodeHandler(B, 1, (sign == false? false:true), false);
				LinkedList<FormulaNodeHandler> formulaListFor2 = new LinkedList<>();
				formulaListFor2.addFirst(subFormula2A);
				formulaListFor2.addLast(subFormula2B);
				BTNode<TableauNodeHandler<FormulaNodeHandler>> subNode2 = new BTNode<>(currNode, null, null,
						new TableauNodeHandler<>(formulaListFor2, false));
				currNode.setRightNode(subNode2);
				searchForClosure(currNode, subFormula2A, true, false);
				searchForClosure(currNode, subFormula2B, true, false);
				tableauNodeQueue.enqueue(subNode2);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return true;
			}
			//thing=(stuff)
			else if((chArr[i] == Connectives.BI_IMPLIES.getSymbol()) && (A.length()  == 1) && (B.length() > 2)&& (chArr[i+1] == '(')
					&& isMatched(A) && isMatched(B)){
				System.out.println("Matches bi-implies");
				System.out.println(A+":"+B);
				System.out.println(B+":"+A);
				System.out.println("branching");
				
				System.out.println("_________________________________________________________________________________________________________________");
				//handling bidirection ... its a special case
				FormulaNodeHandler subFormula1A = new FormulaNodeHandler(A, 0, true, false);
				FormulaNodeHandler subFormula1B = new FormulaNodeHandler(B, 1, (sign == true? true:false), false);
				LinkedList<FormulaNodeHandler> formulaListFor1 = new LinkedList<>();
				formulaListFor1.addFirst(subFormula1A);
				formulaListFor1.addLast(subFormula1B);
				BTNode<TableauNodeHandler<FormulaNodeHandler>> subNode1 = new BTNode<>(currNode, null, null,
						new TableauNodeHandler<>(formulaListFor1, false));
				currNode.setLeftNode(subNode1);
				searchForClosure(currNode, subFormula1A, true, true);
				searchForClosure(currNode, subFormula1B, true, true);
				tableauNodeQueue.enqueue(subNode1);
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler subFormula2A = new FormulaNodeHandler(A, 0, false, false);
				FormulaNodeHandler subFormula2B = new FormulaNodeHandler(B, 1, (sign == false? false:true), false);
				LinkedList<FormulaNodeHandler> formulaListFor2 = new LinkedList<>();
				formulaListFor2.addFirst(subFormula2A);
				formulaListFor2.addLast(subFormula2B);
				BTNode<TableauNodeHandler<FormulaNodeHandler>> subNode2 = new BTNode<>(currNode, null, null,
						new TableauNodeHandler<>(formulaListFor2, false));
				currNode.setRightNode(subNode2);
				searchForClosure(currNode, subFormula2A, true, false);
				searchForClosure(currNode, subFormula2B, true, false);
				tableauNodeQueue.enqueue(subNode2);
				System.out.println("_________________________________________________________________________________________________________________");
				
				return true;
			}
			//(stuff)=thing
			else if((chArr[i] == Connectives.BI_IMPLIES.getSymbol()) && (A.length() > 2) && (B.length() == 1)&& (chArr[i-1] == ')')
					&& isMatched(A) && isMatched(B)){
				System.out.println("Matches bi-implies");
				System.out.println(A+":"+B);
				System.out.println(B+":"+A);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				//handling bidirection ... its a special case
				FormulaNodeHandler subFormula1A = new FormulaNodeHandler(A, 0, true, false);
				FormulaNodeHandler subFormula1B = new FormulaNodeHandler(B, 1, (sign == true? true:false), false);
				LinkedList<FormulaNodeHandler> formulaListFor1 = new LinkedList<>();
				formulaListFor1.addFirst(subFormula1A);
				formulaListFor1.addLast(subFormula1B);
				BTNode<TableauNodeHandler<FormulaNodeHandler>> subNode1 = new BTNode<>(currNode, null, null,
						new TableauNodeHandler<>(formulaListFor1, false));
				currNode.setLeftNode(subNode1);
				searchForClosure(currNode, subFormula1A, true, true);
				searchForClosure(currNode, subFormula1B, true, true);
				tableauNodeQueue.enqueue(subNode1);
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler subFormula2A = new FormulaNodeHandler(A, 0, false, false);
				FormulaNodeHandler subFormula2B = new FormulaNodeHandler(B, 1, (sign == false? false:true), false);
				LinkedList<FormulaNodeHandler> formulaListFor2 = new LinkedList<>();
				formulaListFor2.addFirst(subFormula2A);
				formulaListFor2.addLast(subFormula2B);
				BTNode<TableauNodeHandler<FormulaNodeHandler>> subNode2 = new BTNode<>(currNode, null, null,
						new TableauNodeHandler<>(formulaListFor2, false));
				currNode.setRightNode(subNode2);
				searchForClosure(currNode, subFormula2A, true, false);
				searchForClosure(currNode, subFormula2B, true, false);
				tableauNodeQueue.enqueue(subNode2);			
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//thing=thing
			else if((chArr[i] == Connectives.BI_IMPLIES.getSymbol()) && (A.length() == 1) && (B.length() == 1) 
					&& isMatched(A) && isMatched(B) && (expression.length() == 3)){
				System.out.println("Matches bi-implies");
				System.out.println(A+":"+B);
				System.out.println(B+":"+A);
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				//handling bidirection ... its a special case
				FormulaNodeHandler subFormula1A = new FormulaNodeHandler(A, 0, true, false);
				FormulaNodeHandler subFormula1B = new FormulaNodeHandler(B, 1, (sign == true? true:false), false);
				LinkedList<FormulaNodeHandler> formulaListFor1 = new LinkedList<>();
				formulaListFor1.addFirst(subFormula1A);
				formulaListFor1.addLast(subFormula1B);
				BTNode<TableauNodeHandler<FormulaNodeHandler>> subNode1 = new BTNode<>(currNode, null, null,
						new TableauNodeHandler<>(formulaListFor1, false));
				currNode.setLeftNode(subNode1);
				searchForClosure(currNode, subFormula1A, true, true);
				searchForClosure(currNode, subFormula1B, true, true);
				tableauNodeQueue.enqueue(subNode1);
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler subFormula2A = new FormulaNodeHandler(A, 0, false, false);
				FormulaNodeHandler subFormula2B = new FormulaNodeHandler(B, 1, (sign == false? false:true), false);
				LinkedList<FormulaNodeHandler> formulaListFor2 = new LinkedList<>();
				formulaListFor2.addFirst(subFormula2A);
				formulaListFor2.addLast(subFormula2B);
				BTNode<TableauNodeHandler<FormulaNodeHandler>> subNode2 = new BTNode<>(currNode, null, null,
						new TableauNodeHandler<>(formulaListFor2, false));
				currNode.setRightNode(subNode2);
				searchForClosure(currNode, subFormula2A, true, false);
				searchForClosure(currNode, subFormula2B, true, false);
				tableauNodeQueue.enqueue(subNode2);			
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			//(thing=thing)
			else if((expression.indexOf("A") == 2) && (expression.indexOf("(") == 0) && (expression.indexOf(")") == 4) && isMatched(expression.substring(1, 2))
					&& isMatched(expression.substring(3, 4))){
				System.out.println("Matches bi-implies");
				System.out.println(expression.substring(1, 2)+":"+expression.substring(3, 4));
				System.out.println(expression.substring(3, 4)+":"+expression.substring(1, 2));
				System.out.println("branching");
				System.out.println("_________________________________________________________________________________________________________________");
				//handling bidirection ... its a special case
				FormulaNodeHandler subFormula1A = new FormulaNodeHandler(expression.substring(1, 2), 0, true, false);
				FormulaNodeHandler subFormula1B = new FormulaNodeHandler(expression.substring(3, 4), 1, (sign == true? true:false), false);
				LinkedList<FormulaNodeHandler> formulaListFor1 = new LinkedList<>();
				formulaListFor1.addFirst(subFormula1A);
				formulaListFor1.addLast(subFormula1B);
				BTNode<TableauNodeHandler<FormulaNodeHandler>> subNode1 = new BTNode<>(currNode, null, null,
						new TableauNodeHandler<>(formulaListFor1, false));
				currNode.setLeftNode(subNode1);
				searchForClosure(currNode, subFormula1A, true, true);
				searchForClosure(currNode, subFormula1B, true, true);
				tableauNodeQueue.enqueue(subNode1);
				System.out.println("_________________________________________________________________________________________________________________");
				FormulaNodeHandler subFormula2A = new FormulaNodeHandler(expression.substring(1, 2), 0, false, false);
				FormulaNodeHandler subFormula2B = new FormulaNodeHandler(expression.substring(3, 4), 1, (sign == false? false:true), false);
				LinkedList<FormulaNodeHandler> formulaListFor2 = new LinkedList<>();
				formulaListFor2.addFirst(subFormula2A);
				formulaListFor2.addLast(subFormula2B);
				BTNode<TableauNodeHandler<FormulaNodeHandler>> subNode2 = new BTNode<>(currNode, null, null,
						new TableauNodeHandler<>(formulaListFor2, false));
				currNode.setRightNode(subNode2);
				searchForClosure(currNode, subFormula2A, true, false);
				searchForClosure(currNode, subFormula2B, true, false);
				tableauNodeQueue.enqueue(subNode2);			
				System.out.println("_________________________________________________________________________________________________________________");
				return true;
			}
			
		}
		return false;
	}
	
	private void handleBranchingFormula(String A, boolean signForA, String B, boolean signForB, BTNode<TableauNodeHandler<FormulaNodeHandler>> currNode, LinkedQueue<BTNode<TableauNodeHandler<FormulaNodeHandler>>> tableauNodeQueue){
		//make 2 new formula handlers with the respective signs 
		//make 2 nodes, whose parent is the current node, that will take these formulas
		//search for closure using currentNode
		/*********formula A*************/
		System.out.println("Handling sub formula A................................................................................");
		System.out.println("Formula: "+ A+", Sign: "+ signForA);
		FormulaNodeHandler subFormulaA = new FormulaNodeHandler(A, 0, signForA, false);
		LinkedList<FormulaNodeHandler> formulaListForA = new LinkedList<>();
		formulaListForA.addFirst(subFormulaA);
		BTNode<TableauNodeHandler<FormulaNodeHandler>> subNodeA = 
				new BTNode<>(currNode, null, null, new TableauNodeHandler<>(formulaListForA, false));
		currNode.setLeftNode(subNodeA);
		if(currNode.getLeftNode() == subNodeA)System.out.println(subNodeA+" with subformula: "+ A);
		System.out.println("Is this formula closed??");
		searchForClosure(currNode, subFormulaA, true, true);
		System.out.println("Add subNode to the tableau node queue");
		tableauNodeQueue.enqueue(subNodeA);
		System.out.println("Size of tableau node queue: "+ tableauNodeQueue.size());
		
		/*********formula B*************/
		System.out.println("Handling sub formula B..................................................................................");
		System.out.println("Formula: "+ B+", Sign: "+ signForB);
		FormulaNodeHandler subFormulaB = new FormulaNodeHandler(B, 0, signForB, false);
		LinkedList<FormulaNodeHandler> formulaListForB = new LinkedList<>();
		formulaListForB.addFirst(subFormulaB);
		BTNode<TableauNodeHandler<FormulaNodeHandler>> subNodeB = 
				new BTNode<>(currNode, null, null, new TableauNodeHandler<>(formulaListForB, false));
		currNode.setRightNode(subNodeB);
		if(currNode.getRightNode() == subNodeB)System.out.println(subNodeB+" with subformula: "+ B);
		System.out.println("Is this formula closed??");
		searchForClosure(currNode, subFormulaB, true, false);
		System.out.println("Add subNode to the tableau node queue");
		tableauNodeQueue.enqueue(subNodeB);
		System.out.println("Size of tableau node queue: "+ tableauNodeQueue.size());
	}
	
	public  boolean semanticTableauAlgorithm(String inputFormula){
		
		FormulaNodeHandler formula = 
		new FormulaNodeHandler(inputFormula, 0, false, false);
		
		LinkedList<FormulaNodeHandler> formulaList = new LinkedList<>();
		formulaList.addFirst(formula);
		
		TableauNodeHandler<FormulaNodeHandler> btNodeHandler = new TableauNodeHandler<>(formulaList, false);
		
		BTNode<TableauNodeHandler<FormulaNodeHandler>> root = new BTNode<>(null, null, null, btNodeHandler);
		
		BinaryTree<TableauNodeHandler<FormulaNodeHandler>> tree = new BinaryTree<>(root);
		
		LinkedQueue<BTNode<TableauNodeHandler<FormulaNodeHandler>>> lQueue = new LinkedQueue<>();
		lQueue.enqueue(root);
		
		LinkedQueue<FormulaNodeHandler> branchingLQueue = new LinkedQueue<>();
		boolean allBranchesClosed = false;
		
		System.out.println("______________________________________________________________________________________________________________________________________");
		
		while(lQueue.size()> 0 && !allBranchesClosed){
			System.out.println("Dequeuing tablue node queue to get currrent node to operate on");
			System.out.println("Size of queue before dequeue: "+ lQueue.size());

			BTNode<TableauNodeHandler<FormulaNodeHandler>> currNode = lQueue.dequeue();
			System.out.println("Size of tableau queue now: "+ lQueue.size());
			
			System.out.println("the current  binary tree node is: "+ currNode.toString());
			System.out.println("______________________________________________________________________________________________________________________________________");
			System.out.println("Go through every formula node handler in the "+ currNode.toString()+ "'s formula list "+ currNode.getItem().getFormulaList().toString() + "of size: "+ currNode.getItem().getFormulaList().size());
			
			int counter = 0;
			LNode<FormulaNodeHandler> currListNode = currNode.getItem().getFormulaList().first();
			while(currListNode.getItem() != null){
				FormulaNodeHandler f = currListNode.getItem();
				if(f == null)System.out.println("f is null");

				System.out.println("______________________________________________________________________________________________________________________________________");
				if(!f.isStatus()){
					System.out.println("Formula: "+ f.getFormula()+ " has a status: FALSE");
					NonBranchingFormulaHandler nbfh = isNonBranchingFormula(f.getFormula(), f.isSign());
					System.out.println("______________________________________________________________________________________________________________________________________");
					if(nbfh != null && nbfh.isNonBranching()){
						if(nbfh.getSubFormulaA()!= null){
							System.out.println("Size of current bt node's formula list before adding subformulaA: "+currNode.getItem().getFormulaList().size());
							System.out.println("Adding "+ nbfh.getSubFormulaA().getFormula()+"to the end of "+currNode.toString()+"'s formula list: "+ currNode.getItem().getFormulaList().toString());
							currNode.getItem().getFormulaList().addLast(nbfh.getSubFormulaA());
							System.out.println("Size of current bt node's formula list after adding subformulaA: "+currNode.getItem().getFormulaList().size());
							
							System.out.println("______________________________________________________________________________________________________________________________________");
							System.out.println("Is "+ currNode.toString()+ " closed?");
							searchForClosure(currNode, nbfh.getSubFormulaA(), false, false);
						}else System.out.println("sub formula A is null");
						System.out.println("______________________________________________________________________________________________________________________________________");
						if(nbfh.getSubFormulaB()!= null){
							System.out.println("Size of current bt node's formula list before adding subformulaB: "+currNode.getItem().getFormulaList().size());
							System.out.println("Adding "+ nbfh.getSubFormulaB().getFormula()+"to the end of "+currNode.toString()+"'s formula list: "+ currNode.getItem().getFormulaList().toString());
							currNode.getItem().getFormulaList().addLast(nbfh.getSubFormulaB());
							System.out.println("Size of current bt node's formula list after adding subformulaB: "+currNode.getItem().getFormulaList().size());
							
							System.out.println("______________________________________________________________________________________________________________________________________");
							System.out.println("Is "+ currNode.toString()+ " closed?");
							searchForClosure(currNode, nbfh.getSubFormulaB(), false, false);
						}else System.out.println("sub formula B is null");
						
						f.setStatus(true);
						System.out.println("______________________________________________________________________________________________________________________________________");
					} else branchingLQueue.enqueue(f);
				}
				counter++;
				System.out.println("Size of the current bt node's formula list: "+ currNode.getItem().getFormulaList().size()+"|"+counter);
				
				
				currListNode = currListNode.getNextNode();
			}

			System.out.println("\n\nSize of branchingLQueue: "+ branchingLQueue.size());
			System.out.println("\n*\n*\n*\nDone looking at the branching formulas. Now, to expand on them.");
			while(!branchingLQueue.isEmpty() && !currNode.getItem().isClosed()){
				System.out.println("Dequeuing my branching tree...");
				FormulaNodeHandler f = branchingLQueue.dequeue();
				if (f!= null) {
					System.out.println("Formula: " + f.getFormula() + " ,Sign: " + f.isSign() + " ," + f.isStatus());
					System.out.println("*****************************************************************");
					System.out.println("is this formula a branching formula to be expanded?");
					if (isBranchingFormula(f.getFormula(), f.isSign(), currNode, lQueue)) {
						f.setStatus(true); 
						//do nothing. isBranchingFormula method handles everything
						System.out.println(
								"____________________________________________________________________________________________________________________________________________________________________");
					} 
				}
			}
			
			System.out.println("____________________________________________________________________________________________________________________________________________________________________");
////			BinaryTree<TableauNodeHandler<FormulaNodeHandler>> tree = new BinaryTree<>(root);
//			System.out.println("bt size: "+ tree.getSize());
//			System.out.println("The hight of the root node: "+ tree.height(tree.getRootNode()));
//			System.out.println("Does the root of this tree have a left child?");
//			if(tree.hasLeft(tree.getRootNode())){System.out.println("yes");}
//			else{System.out.println("no");}
//			System.out.println("Does the root of this tree have a right child?");
//			if(tree.hasRight(tree.getRootNode())){System.out.println("yes");}
//			else{System.out.println("no");}	
			
			System.out.println("Checking all external leaves for closure in tree");
			allBranchesClosed = true;
			Iterator<BTNode<TableauNodeHandler<FormulaNodeHandler>>> itr = tree.InOrderIterator();
			while(itr.hasNext()){//should be a while
				BTNode<TableauNodeHandler<FormulaNodeHandler>> extNode = itr.next();
				System.out.println("what is this node?");
				System.out.println(extNode.getItem().toString());
//				for(FormulaNodeHandler f: extNode.getItem().getFormulaList()){
//					System.out.println(f.getFormula());
//				}
				if(!extNode.getItem().isClosed()){
					allBranchesClosed = false;
				}
			}
			System.out.println("_______________________________________________________________________________________________________________________________________________________________________");
		}
//			System.out.println(root.getItem().getFormulaList().first().getItem().getFormula());
//			if(root.getLeftNode() != null)System.out.println(root.getLeftNode().getItem().getFormulaList().first().getItem().getFormula());
//			if(root.getRightNode() != null)System.out.println(root.getRightNode().getItem().getFormulaList().first().getItem().getFormula());
		
		return allBranchesClosed;
		
	}
	

		
	
	private void searchForClosure(BTNode<TableauNodeHandler<FormulaNodeHandler>> currNode, FormulaNodeHandler formula, boolean isbranchingFormula, boolean isLeftChild) {
		System.out.println("Searching for closure...");
		BTNode<TableauNodeHandler<FormulaNodeHandler>> tempNode;
		/*if(!isbranchingFormula)*/tempNode= currNode;
//		else tempNode = currNode.getParentNode();//will never be null
		while(tempNode != null){
			System.out.println("traversing the bt nodes");
			System.out.println("Size of formula list in current node: "+ tempNode.getItem().getFormulaList().size() );
			for(FormulaNodeHandler fnh: tempNode.getItem().getFormulaList()){
				System.out.println("traversing the list in a bt node");
				System.out.println(fnh.getFormula()+ " is the formula being inspected from formula node handler: "+ fnh.toString() + " ,for closure.");
				System.out.println(formula.getFormula() + "#" + formula.isSign() + " | " + fnh.getFormula() + "#" + fnh.isSign());
				if(fnh.getFormula().equals(formula.getFormula()) && (fnh.isSign() != formula.isSign())){
					System.out.println(fnh.getFormula()+" and "+formula + " contradict.");
					if(!isbranchingFormula){System.out.println("Closing this node");tempNode.getItem().setClosed(true);}
					else {
						if(isLeftChild){System.out.println("Closing this node");tempNode.getLeftNode().getItem().setClosed(true);}
						else{System.out.println("Closing this node"); tempNode.getRightNode().getItem().setClosed(true);}
					}
				}System.out.println("Moving to next formula on the list...");
			}
			System.out.println("moving to the parent of the current node: "+ tempNode.toString());
			tempNode = tempNode.getParentNode();
		}
		System.out.println("done searching for closure");
	}
	
}
