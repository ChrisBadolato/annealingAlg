/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annealingalgcbadolato;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Chris Badolato
 */
public class AnnealingAlgCBadolato {

    /**
     * @param args the command line arguments
     */

    
    public static void main(String[] args) {
            // TODO code application logic here
        String originalPolishExpression = "123V4V567VH89VHVH";
        String allVerticalPolish = "12V3V4V5V6V7V8V9V";
        String allHorizontalPolish = "12H3H4H5H6H7H8H9H";
        
        char splitExpression[] = originalPolishExpression.toCharArray();
        char allVertical[] = allVerticalPolish.toCharArray();
        char allHorizontal[] = allHorizontalPolish.toCharArray();
        
        ArrayList<Block> blockList = new ArrayList();
        Block one = new Block(1,1);
        Block two = new Block(2,7);
        Block three = new Block(3,7);
        Block four = new Block(2,5);
        Block five = new Block(7,4);
        Block six = new Block(2,9);
        Block seven = new Block(9,7);
        Block eight = new Block(3,1);
        Block nine = new Block(4,4);
        blockList.add(one);
        blockList.add(two);
        blockList.add(three);
        blockList.add(four);
        blockList.add(five);
        blockList.add(six);
        blockList.add(seven);
        blockList.add(eight);
        blockList.add(nine);
        
        for(int i = 0; i < splitExpression.length;  i++){
            System.out.print(splitExpression[i]);
        } 
        System.out.println();
        printList(blockList);

        /*
        int vertcalStackArea = 0;
        vertcalStackArea = stackVerticalArea(allVertical, blockList);

        int horizontalStackArea = 0;
        horizontalStackArea = stackHorizontalArea(allHorizontal, blockList);
        */
        System.out.println();
        System.out.println();
        
        int polishArea = 0;
        if(isValidExpression(splitExpression)){
            polishArea = polishArea(splitExpression, blockList);
            System.out.println("Polish Area " + polishArea);
        }
        else{
            System.out.println("Invalid Expression");
        }

    }
    
    public static class Block{
        int height;
        int width;
        int area;
        
        public Block(int height, int width){
            this.height = height;
            this.width = width;  
            this.area = height*width;
                   
        }      
    }

    public static int polishArea(char[] polishExpression, ArrayList blockList){
         int area = 0;
        Block placementBlockOne = (Block) blockList.get(0);
        Block placementBlockTwo = (Block) blockList.get(0);      
        String mainStackPop1 = null;
        String mainStackPop2 = null;
        String newOperandExpression = null;
        String heightMax = null;
        String widthMax = null;
      
        int widthOne = 0;
        int widthTwo = 0;
        int heightOne = 0;
        int heightTwo = 0;   
        int widthSum = 0;
        int heightSum = 0;
                
        int areaArray[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        Stack <String> heightStack = new Stack<String>();
        Stack <String> widthStack = new Stack<String>();
        Stack <String> stack = new Stack<String>();
        for(int i = 0; i < polishExpression.length; i++){
            if(Character.isDigit(polishExpression[i])){
                    //get curent digit and add it to the string stack
                System.out.println();
                System.out.println("Number " + polishExpression[i] + " is found");
                stack.add(Character.toString(polishExpression[i]));
                    //get height of current block
                placementBlockOne = (Block) blockList.get(Character.getNumericValue(polishExpression[i] - 1));
                heightStack.add(Integer.toString(placementBlockOne.height));
                    //get width of curernt block add to stack
                placementBlockTwo = (Block) blockList.get(Character.getNumericValue(polishExpression[i] - 1));
                widthStack.add(Integer.toString(placementBlockTwo.width));           
            }
            else if(polishExpression[i] == 'V'){
                System.out.println();
                System.out.println("We have found a vertical slice");
                    //pop two values from the stack
                mainStackPop1 = stack.pop();
                mainStackPop2 = stack.pop();
                    //create new operand expression from these and add them back to the stack
                newOperandExpression = mainStackPop1 + mainStackPop2 + polishExpression[i];
                stack.add(newOperandExpression);
                    //get top values from Height Stack
                heightOne = Integer.parseInt(heightStack.pop());
                heightTwo = Integer.parseInt(heightStack.pop());
                    //find max of these two values and add it back to the height stack
                System.out.println("Height One: " + heightOne + "  " + "Height Two: " + heightTwo + "  " + "Max Height: " +  max(heightOne, heightTwo));
                heightStack.add(max(heightOne, heightTwo));
                
                    //new width is the sum of the previous widths add that to the width Stack
                widthOne = Integer.parseInt(widthStack.pop());
                widthTwo = Integer.parseInt(widthStack.pop());
                widthSum = widthOne + widthTwo;
                System.out.println("Width One: " + widthOne + "  " + "Width Two: " + widthTwo + "  " + "Sum Widths: " +  widthSum);
                widthStack.add(Integer.toString(widthSum)); 
                areaArray[i] = widthSum * Integer.parseInt(max(heightOne, heightTwo));                
            }           
            else if(polishExpression[i] == 'H'){
                System.out.println();
                System.out.println("We have found a Horizontal slice");
                    //pop two values from the stack
                mainStackPop1 = stack.pop();
                mainStackPop2 = stack.pop();
                    //create new operand expression from these and add them back to the stack
                newOperandExpression = mainStackPop1 + mainStackPop2 + polishExpression[i];
                stack.add(newOperandExpression);
                    //get top values from Height Stack
                widthOne = Integer.parseInt(widthStack.pop());
                widthTwo = Integer.parseInt(widthStack.pop());
                    //find max of these two values and add it back to the height stack
                System.out.println("Height One: " + widthOne + "  " + "Height Two: " + widthTwo + "  " + "Max Height: " +  max(widthOne, widthTwo));
                widthStack.add(max(widthOne, widthTwo));
                
                    //new width is the sum of the previous widths add that to the width Stack
                heightOne = Integer.parseInt(heightStack.pop());
                heightTwo = Integer.parseInt(heightStack.pop());
                heightSum = heightOne + heightTwo;
                System.out.println("Width One: " + heightOne + "  " + "Width Two: " + heightTwo + "  " + "Sum Widths: " +  heightSum);
                heightStack.add(Integer.toString(heightSum));  
                areaArray[i] = heightSum * Integer.parseInt(max(widthOne, widthTwo));
            }               
        }
        for(int i = 0; i < areaArray.length; i ++){
            System.out.println(areaArray[i]);
            area = areaArray[i];
        }     
        return area;
    }
    
    public static String max(int valueOne, int valueTwo){
        String stringMax = null;
        int max = valueOne;
        if(valueOne < valueTwo){
            max = valueTwo;
        }
        stringMax = Integer.toString(max);
        return stringMax;
    }
    
    
    public static void printList(ArrayList blockList){      
        for(int i = 0; i < blockList.size(); i++){
            Block placementBlock = (Block) blockList.get(i);      
            System.out.print(placementBlock.area + " ");
        }
    }
    public static boolean isValidExpression(char[] polishExpression){
        boolean validExpression = true;
        int operatorSum = 0, operandSum = 0, operatorOperandDifference = 0;
        for(int i = 0; i < polishExpression.length; i++){
                //there must always be more one more operand than operators for a valid expression
            if(polishExpression[i] == 'V' || polishExpression[i] == 'H'){
                operatorSum = operatorSum + 1;
            }
            else if(Character.isDigit(polishExpression[i])){
                operandSum = operandSum + 1;
            }
            operatorOperandDifference = operandSum - operatorSum;
            if(operatorOperandDifference == 1){
                validExpression = true;
            }
            else{
                validExpression = false;
            }
                //
           // if()
        }
        return validExpression;
    }
}