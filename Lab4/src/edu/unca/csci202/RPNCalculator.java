package edu.unca.csci202;

import java.util.Scanner;
import java.util.Stack;

public class RPNCalculator {
	
	/**
	 *  RPN stands for Reverse Polish Notation. It is an alternate way to write mathematical expressions.
	 *  A standard expression is written in the format 3 + 4 - 5 whereas the same expression in RPN is written as 3 4 + 5 -.
	 *  Both expressions do the same thing, they both add 4 to 3 and then subtract 5 from the result.
	 *  Remember to add spaces between each term and that typing the letter "q" ends the program.
	 */
	
    private Stack<Double> calcStack;
    
    public RPNCalculator() {
        calcStack = new Stack<Double>();
    }

    public void run() {
        Scanner inputLineReader = new Scanner(System.in);
        String line = "";
        boolean quit = false;
        
        while (!quit) {
            while (line.equals("")) {
                System.out.print(":::> ");
                line = inputLineReader.nextLine().trim();
            }
            quit = interpretExpression(line);
            //if proper expression, result will be only element on stack
            printResult(quit);
            line = "";
        }
    
    }
    
    private boolean interpretExpression(String line) {
        Scanner lineParser = new Scanner(line);
        String token = "";
        boolean quit = false;
        double operand = 0.0;
        boolean passed = false;
        double value1 = 0;
        double value2 = 0;
        int size = 0;
        
        while (lineParser.hasNext()) {
            token = lineParser.next();
            System.out.println("processing token "+token);
            try {
            	operand = Double.parseDouble(token);
            	passed = true;
            }catch(NumberFormatException e){
            	passed = false;
            }
            
            if (token.equals("q")) {
                quit=true;
            }else if(passed) {
            	calcStack.add(operand);
            	size++;
            }else if(token.equals("+")) {
            	if(size <= 1) {
            		System.out.println("Invalid input: not enough operands");
            	}else {
            		value2 = calcStack.pop();
                	value1 = calcStack.pop();
                	calcStack.add(value1 + value2);
                	size--;
            	}
            }else if(token.equals("-")) {
            	if(size <= 1) {
            		System.out.println("Invalid input: not enough operands");
            	}else {
                	value2 = calcStack.pop();
                	value1 = calcStack.pop();
                	calcStack.add(value1 - value2);
                	size--;
            	}
            }else if(token.equals("*")) {
            	if(size <= 1) {
            		System.out.println("Invalid input: not enough operands");
            	}else {
                	value2 = calcStack.pop();
                	value1 = calcStack.pop();
                	calcStack.add(value1 * value2);
                	size--;
            	}
            }else if(token.equals("/")) {
            	if(size <= 1) {
            		System.out.println("Invalid input: not enough operands");
            	}else {
                	value2 = calcStack.pop();
                	value1 = calcStack.pop();
                	calcStack.add(value1 / value2);
                	size--;
            	}
            }else if(!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/") && !passed) {
        		System.out.println("Invalid input: unrecognized character");
            }
        }
        if(size >= 2){
        	System.out.println("Invalid input: too many operands");
        }
        return quit;
    }
    
    private void printResult(boolean quit) {
        if (!quit) {
            System.out.println("Result is " + calcStack.peek());
        }
    
    }

    public static void main(String [] args) {
        RPNCalculator calc = new RPNCalculator();
        calc.run();
    }

}
