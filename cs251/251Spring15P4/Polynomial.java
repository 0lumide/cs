import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.Iterator;
import java.util.Collections;

public class Polynomial {
	List<ExpressionAtom> infixExpression = new ArrayList<ExpressionAtom>();
	
	List<ExpressionAtom> finalExpression;

	ListRepresentation listRepresentation;

	private Stack postfixStack;

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
	private ListRepresentation linkifyPostfixList(ArrayList <ListRepresentation> linkRepresentation){
		String lastOp = "";
		int operandCount = 1;
		ExpressionAtom expressionAtom;
		while(!postfixStack.empty()){
			expressionAtom = (ExpressionAtom) postfixStack.pop();
			if(expressionAtom.getAtomType() == AtomType.OPERATOR){
				if(lastOp.equals("")){
					listRepresentation.setNodeVal(expressionAtom);
					if("-".equals(expressionAtom.getVariablesOrOperator())){
						listRepresentation.setNegative(true);
					lastOp = expressionAtom.getVariablesOrOperator();
				}
				if(lastOp.equals(expressionAtom.getVariablesOrOperator())){
					operandCount--;
				}
				else{
					postfixStack.push(expressionAtom);
					linkRepresentation.operands.add(LinkifyPostfixList(linkRepresentation.operands));
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
						linkRep = new LinkRepresentation();
						linkRep.setNodeVal(expressionAtom);
						linkRepresentation.operands.add(linkRep);
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
	private ListRepresentation convertToListRepresentation() {
		/*
		 * TODO: Write code here to operate on this.infixExpression and obtain a ListRepresentation
		 * object that contains a list representation of the the original expression given
		 * 
		 * NOTE THAT WE WILL BE PRINTING THE ListRepresentation object immediately after
		 * this function is called, so make sure it is in the right form.
		 */
		Stack stack = new Stack();
		postfixStack = new Stack();
		ExpressionAtom expressionAtom;
		ListRepresentation listRepresentation = new ListRepresentation();
		
		List<ExpressionAtom> postfixExpression = new ArrayList<ExpressionAtom>();
		List<ExpressionAtom> localInfix = this.infixExpression;
		Collections.reverse(localInfix);
		Iterator <ExpressionAtom> it = localInfix.iterator();
		while(it.hasNext()){
			expressionAtom = it.next();
			if(expressionAtom.getAtomType() == AtomType.OPERATOR){
				if(expressionAtom.getVariablesOrOperator().equals("(")){
					ExpressionAtom atom = (ExpressionAtom) stack.pop();
					while(!atom.getVariablesOrOperator().equals(")")){
						postfixExpression.add(atom);
						postfixStack.push(atom);
						atom = (ExpressionAtom) stack.pop();
					}
				}
				else{
					stack.push(expressionAtom);
				}
			}
			else{
				postfixExpression.add(expressionAtom);
				postfixStack.push(expressionAtom);
			}
		}
		while(!stack.empty()){
			expressionAtom = stack.pop();
			postfixExpression.add(expressionAtom);
			postfixStack.push(expressionAtom);
		}
		Collections.reverse(postfixExpression);
		it = postfixExpression.iterator();

		String lastOperator = "";
		System.out.println("prefix");
		while(it.hasNext()){
			expressionAtom = it.next();
                        if(expressionAtom.getAtomType() == AtomType.OPERATOR){
				System.out.printf("%s\n", expressionAtom.getVariablesOrOperator());
				lastOperator = expressionAtom.getVariablesOrOperator();
                        }
                        else{   
                                System.out.printf("%s %s\n", expressionAtom.getCoefficient(), expressionAtom.getVariablesOrOperator());
                        }

		}
		return linkifyPostfixList(listRepresentation);
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
