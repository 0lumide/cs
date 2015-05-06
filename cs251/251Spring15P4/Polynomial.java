import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;

public class Polynomial {
	List<ExpressionAtom> infixExpression = new ArrayList<ExpressionAtom>();

	List<ExpressionAtom> finalExpression;

	ListRepresentation listRepresentation;

	private Stack postfixStack;

	private List<ExpressionAtom> postfixExpression = new ArrayList<ExpressionAtom>();

	private Stack stack;

	private String identifyUnaryMinuses(String expression) {
		if(expression.startsWith("-")) {
			expression = "%" + expression.substring(1); 
		}

		char[] expressionAtoms = expression.toCharArray();
		String returnExpression = String.valueOf(expressionAtoms[0]);
		for(int i = 1; i < expressionAtoms.length; ++i) {
			if(expressionAtoms[i] == '-' && expressionAtoms[i-1] == '(')
				returnExpression += "%";
			else
				returnExpression += String.valueOf(expressionAtoms[i]);
		}

		return returnExpression;
	}

	private String insertMultiplicationSigns(String expression) {
		char[] expressionAtoms = expression.toCharArray();
		String returnExpression = String.valueOf(expressionAtoms[0]);

		for(int i = 1; i < expressionAtoms.length; ++i) {
			if(!isOperator(expressionAtoms[i]) && !Character.isDigit(expressionAtoms[i]) 
					&& Character.isDigit(expressionAtoms[i-1]))
				returnExpression += "*";
			returnExpression +=  String.valueOf(expressionAtoms[i]);
		}

		return returnExpression;
	}

	private boolean isOperator(char token) {
		return token == '+' || token == '-' || token == '*' || token == '^'
			|| token == '(' || token == ')';
	}

	private List<ExpressionAtom> parseInputPolynomial(String inputExpression) {
		inputExpression = identifyUnaryMinuses(inputExpression);
		inputExpression = insertMultiplicationSigns(inputExpression);

		List<ExpressionAtom> inputExpressionTokens = new ArrayList<ExpressionAtom>();

		char[] inputChars = inputExpression.toCharArray();
		for(int i = 0; i < inputChars.length; ++i) {
			if(isOperator(inputChars[i]) || inputChars[i] == '%') {
				inputExpressionTokens.add(new ExpressionAtom(String.valueOf(inputChars[i]), 
							AtomType.OPERATOR, 1));
			} else {
				int lastIndex = inputExpressionTokens.size() - 1;
				if(lastIndex >= 0 && inputExpressionTokens.get(lastIndex).getAtomType() 
						== AtomType.OPERAND) {
					ExpressionAtom lastElement = inputExpressionTokens.remove(lastIndex);
					if(Character.isDigit(inputChars[i])) {
						lastElement.setCoefficient(lastElement.getCoefficient() * 10 + 
								Character.getNumericValue(inputChars[i]));
					} else {
						lastElement.setVariablesOrOperator(lastElement.getVariablesOrOperator() + 
								String.valueOf(inputChars[i]));
					}
					inputExpressionTokens.add(lastElement);
				} else if(Character.isDigit(inputChars[i])) {
					inputExpressionTokens.add(new ExpressionAtom("", AtomType.OPERAND, 
								Character.getNumericValue(inputChars[i])));
				} else {
					inputExpressionTokens.add(new ExpressionAtom(String.valueOf(
									inputChars[i]), AtomType.OPERAND, 1));
				}
			}
		}

		return inputExpressionTokens;
	}

	//Helper function
	private ListRepresentation linkifyPostfixList(){
		ListRepresentation listRepresentation = new ListRepresentation();
		String lastOp = "";
		int operandCount = 1;
		ExpressionAtom expressionAtom;
		while(!postfixStack.empty()){
			expressionAtom = (ExpressionAtom) postfixStack.pop();
			if(expressionAtom.getAtomType() == AtomType.OPERATOR){
				if(lastOp.equals("")){
					listRepresentation.setNodeVal(expressionAtom);
					//if("-".equals(expressionAtom.getVariablesOrOperator()))
					//	listRepresentation.setNegative(true);
					lastOp = expressionAtom.getVariablesOrOperator();
				}
				if(lastOp.equals(expressionAtom.getVariablesOrOperator())){
					operandCount--;
				}
				else{
					ListRepresentation listRep = new ListRepresentation();
					postfixStack.push(expressionAtom);
					listRepresentation.operands.add(linkifyPostfixList());
				}
				lastOp = expressionAtom.getVariablesOrOperator();
			}
			else{
				if(lastOp.equals("")){
					System.out.println("Invalid Operation");
					return(listRepresentation);
				}
				else{
					operandCount++;
					if(operandCount <= 2){
						ListRepresentation listRep = new ListRepresentation();
						listRep.setNodeVal(expressionAtom);
						listRepresentation.operands.add(listRep);
					}
					else{
						postfixStack.push(expressionAtom);
						return listRepresentation;
					}
				}
			}
		}
		return listRepresentation;
	}
	private ListRepresentation makeListRepresentation(int pos){
		/*Iterator <ExpressionAtom> it = postfixStack.iterator();//postfixExpression.iterator();
		while(it.hasNext()){
			ExpressionAtom expressionAtom = it.next();
			System.out.println(expressionAtom.getVariablesOrOperator());
			if(expressionAtom.getAtomType() == AtomType.OPERAND)
				System.out.println(expressionAtom.getCoefficient());
		}*/
		ExpressionAtom exp1 = postfixExpression.get(pos);
		if()
		return new ListRepresentation();
	}
	private ListRepresentation convertToListRepresentation() {
		/*
		 * TODO: Write code here to operate on this.infixExpression and obtain a ListRepresentation
		 * object that contains a list representation of the the original expression given
		 * 
		 * NOTE THAT WE WILL BE PRINTING THE ListRepresentation object immediately after
		 * this function is called, so make sure it is in the right form.
		 */
		Iterator <ExpressionAtom> it = infixExpression.iterator();
		postfixStack = new Stack();
		
		stack = new Stack();
		ExpressionAtom expressionAtom;
		while(it.hasNext()){
			expressionAtom = it.next();
			//System.out.println(expressionAtom.getVariablesOrOperator());
			if(expressionAtom.getAtomType() == AtomType.OPERAND){
				postfixStack.push(expressionAtom);
				//System.out.println("Operand");
				postfixExpression.add(expressionAtom);
			}
			else{
				switch(expressionAtom.getVariablesOrOperator().charAt(0)){
					case '+':
					case '-':
					case '*':
					case '^':
						gotOper(expressionAtom);
						break;
					case '(':
						stack.push(expressionAtom);
						break;
					case ')':
						gotParen();
						break;
				}
			}
		}
		while (!stack.isEmpty()) {
			expressionAtom = (ExpressionAtom) stack.pop();
			String var = expressionAtom.getVariablesOrOperator();
			if(!var.equals("(") && !var.equals(")")){
        			postfixStack.push(expressionAtom);
				postfixExpression.add(expressionAtom);
			}
      		}
		//Collections.reverse(postfixExpression);
		return makeListRepresentation(0);
		//return linkifyPostfixList();
	}

	public void gotParen(){ 
		while (!stack.isEmpty()) {
			ExpressionAtom exp = (ExpressionAtom) stack.pop();
			if (exp.getVariablesOrOperator().equals("(")) 
				break; 
			else{
				String var = exp.getVariablesOrOperator();
				if(!var.equals("(") && !var.equals(")")){
					postfixStack.push(exp);
					postfixExpression.add(exp);
				}
			}
		}
	}
	public void gotOper(ExpressionAtom opThis) {
		while (!stack.isEmpty()) {
			ExpressionAtom opTop = (ExpressionAtom) stack.pop();
			if (opTop.getVariablesOrOperator().equals("(")) {
				stack.push(opTop);
				break;
			}
			else {
				if (isHigherPrecedence(opThis, opTop)) { 
					stack.push(opTop);
					break;
				}
				else{
					String var = opTop.getVariablesOrOperator();
					if(!var.equals("(") && !var.equals(")")){
						postfixStack.push(opTop);
						postfixExpression.add(opTop);
					}
				}
			}
		}
		stack.push(opThis);
	}
	//Helper function returns true if exp1 is of higher precedence than exp2
	boolean isHigherPrecedence(ExpressionAtom exp1, ExpressionAtom exp2){
		String op1 = exp1.getVariablesOrOperator();
		String op2 = exp2.getVariablesOrOperator();
		if(op1.equals("^"))
			return true;
		else if(op1.equals("*") && !op2.equals("^"))
			return true;
		else
			return false;
	}

	private List<ExpressionAtom> evaluateExpression() {
		/*
		 * TODO: Write code here to operate on this.listRepresentation and obtain a List of
		 * ExpressionAtom objects which represent the atoms of the final expression. Note that you
		 * are allowed to have repeated variables here. This is not printed anywhere in the code, so
		 * you are free to return the ExpressionAtom objects in whatever form you wish.
		 */
		return new ArrayList<ExpressionAtom>();
	}

	private List<ExpressionAtom> simplifyAndNormalize(List<ExpressionAtom> evaluatedExpression) {
		/*
		 * TODO: Write code here to operate on the List<ExpressionAtom> object obtaind from
		 * 'evaluateExpression'. Specifically, ensure that you combine terms with same variables and
		 * modify coefficients appropriately.
		 */
		return new ArrayList<ExpressionAtom>();
	}

	public Polynomial(String inputPolynomial) {
		this.infixExpression = parseInputPolynomial(inputPolynomial);

		this.listRepresentation = convertToListRepresentation();
	}

	private String sortString(String termVars) {
		char[] ar = termVars.toCharArray();
		Arrays.sort(ar);
		return String.valueOf(ar);
	}

	public void evaluate() {
		List<ExpressionAtom> evaluatedExpression = evaluateExpression();

		for(int i = 0; i < evaluatedExpression.size(); ++i) {
			evaluatedExpression.get(i).setVariablesOrOperator((sortString(
							evaluatedExpression.get(i).getVariablesOrOperator())));
		}

		this.finalExpression = simplifyAndNormalize(evaluatedExpression);
	}
}
