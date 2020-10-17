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


        int vertcalStackArea = 0;
        vertcalStackArea = stackVerticalArea(allVertical, blockList);

        int horizontalStackArea = 0;
        horizontalStackArea = stackHorizontalArea(allHorizontal, blockList);
        
        System.out.println();
        System.out.println();
        
        int polishArea = 0;
        polishArea = polishArea(splitExpression, blockList);
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
    
    public static int stackVerticalArea(char[] allVerticalPolish, ArrayList blockList){
        int area = 0;
        Block placementBlockOne = (Block) blockList.get(0);
        Block placementBlockTwo = (Block) blockList.get(0);
        int maxHeight = placementBlockOne.height;
        int widthSum = placementBlockOne.width;
        int areaArray[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int multipleBeforeChar = 0;   
        
      
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < allVerticalPolish.length; i++){
                
            if(allVerticalPolish[i] == 'V'){
                if(multipleBeforeChar > 1){
                    placementBlockOne = (Block)blockList.get(Integer.parseInt(stack.pop())- 2);             
                    placementBlockTwo = (Block)blockList.get(Integer.parseInt(stack.pop()));

                    widthSum = placementBlockOne.width + placementBlockTwo.width;
                    maxHeight = findMax(maxHeight,placementBlockOne,placementBlockTwo);
                    areaArray[i] = maxHeight * widthSum;
                    multipleBeforeChar = 0;
                }
                else{
                    placementBlockOne = (Block)blockList.get(Integer.parseInt(stack.pop())- 1);

                    widthSum = widthSum + placementBlockOne.width;              
                    maxHeight = findMax(maxHeight,placementBlockOne,placementBlockTwo);
                    areaArray[i] = maxHeight * widthSum;
                    multipleBeforeChar = 0;
                }
               
            }
            else{
             
                stack.add(Character.toString(allVerticalPolish[i]));
                multipleBeforeChar = multipleBeforeChar + 1;
            }
            
        }
        area = widthSum * maxHeight;

        System.out.println();
        System.out.println("areaArray:");      
        for (int i = 0; i < areaArray.length; i++){
            System.out.print(areaArray[i] + " ");
        }
        return area;
    }
    
    public static int stackHorizontalArea(char[] allHorizontalPolish, ArrayList blockList){
        int area = 0;
        Block placementBlockOne = (Block) blockList.get(0);
        Block placementBlockTwo = (Block) blockList.get(0);
        int maxWidth = placementBlockOne.width;
        int heightSum = placementBlockOne.height;
        int areaArray[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int multipleBeforeChar = 0;   
        
        
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < allHorizontalPolish.length; i++){
               
            if(allHorizontalPolish[i] == 'H'){
                if(multipleBeforeChar > 1){
                    placementBlockOne = (Block)blockList.get(Integer.parseInt(stack.pop())- 2);             
                    placementBlockTwo = (Block)blockList.get(Integer.parseInt(stack.pop()));
                    heightSum = placementBlockOne.height + placementBlockTwo.height;
                    maxWidth = findMax(maxWidth,placementBlockOne,placementBlockTwo);
                    areaArray[i] = maxWidth * heightSum;
                    multipleBeforeChar = 0;
                }
                else{
                    placementBlockOne = (Block)blockList.get(Integer.parseInt(stack.pop())- 1);

                    heightSum = heightSum + placementBlockOne.height;
                    maxWidth = findMax(maxWidth,placementBlockOne,placementBlockTwo);
                    areaArray[i] = maxWidth * heightSum;
                    multipleBeforeChar = 0;
                }            
            }
            else{
                stack.add(Character.toString(allHorizontalPolish[i]));
                multipleBeforeChar = multipleBeforeChar + 1;
            }
        }
        area = heightSum * maxWidth;
        System.out.println();
        System.out.println("areaArray:");
        for (int i = 0; i < areaArray.length; i++){
            System.out.print(areaArray[i] + " ");
        }
        return area;
    }
    
    public static int polishArea(char[] polishExpression, ArrayList blockList){
        int area = 0;
        Block placementBlockOne = (Block) blockList.get(0);
        Block placementBlockTwo = (Block) blockList.get(0);
        int pop1 = 0;
        int pop2 = 0;
        int maxWidth = placementBlockOne.width;
        int heightSum = placementBlockOne.height;
        int areaArray[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int multipleBeforeChar = 0;   
        
        System.out.println("Polish Stack Traversal:");
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < polishExpression.length; i++){
                System.out.println("iteration: " + i);
            if(polishExpression[i] == 'H'){
                if(multipleBeforeChar > 1){
                    pop1 = Integer.parseInt(stack.pop())- 2;
                    pop2 = Integer.parseInt(stack.pop());
                    System.out.println();
                    System.out.println("pop1: " + pop1 + " from Stack");
                    System.out.println("pop2: " + pop2 + " from Stack");
                    System.out.println();
                    placementBlockOne = (Block)blockList.get(pop1 - 2);             
                    placementBlockTwo = (Block)blockList.get(pop2);
                    System.out.println("Area Block 1: " + placementBlockOne.area);
                    System.out.println("Area Block 2: " + placementBlockTwo.area);
                    System.out.println("Height Block 1: " + placementBlockOne.height);
                    System.out.println("Width Block 1: " + placementBlockOne.width);
                    System.out.println("Height Block 2: " + placementBlockTwo.height);                    
                    System.out.println("Width Block 2: " + placementBlockTwo.width);
                    multipleBeforeChar = 0;
                }
                else{
                    pop1 = Integer.parseInt(stack.pop());
                    System.out.println();
                    System.out.println("pop1: " + pop1 + " from Stack");
                    System.out.println();
                    placementBlockOne = (Block)blockList.get(pop1);
                    System.out.println("Area Block 1: " + placementBlockOne.area);
                    System.out.println("Height Block 1: " + placementBlockOne.height);
                    System.out.println("Width Block 1: " + placementBlockOne.width);
                    multipleBeforeChar = 0;
                }
               
            }
            else if(polishExpression[i] == 'V'){
                if(multipleBeforeChar > 1){
                    pop1 = Integer.parseInt(stack.pop());
                    pop2 = Integer.parseInt(stack.pop());
                    System.out.println();
                    System.out.println("pop1: " + pop1 + " from Stack");
                    System.out.println("pop2: " + pop2 + " from Stack");
                    System.out.println();
                    placementBlockOne = (Block)blockList.get(pop1 - 2);             
                    placementBlockTwo = (Block)blockList.get(pop2);
                    System.out.println("Area Block 1: " + placementBlockOne.area);
                    System.out.println("Area Block 2: " + placementBlockTwo.area);
                    System.out.println("Height Block 1: " + placementBlockOne.height);
                    System.out.println("Width Block 1: " + placementBlockOne.width);
                    System.out.println("Height Block 2: " + placementBlockTwo.height);                    
                    System.out.println("Width Block 2: " + placementBlockTwo.width);
                    multipleBeforeChar = 0;
                }
                else{
                    pop1 = Integer.parseInt(stack.pop());
                    System.out.println();
                    System.out.println("pop1: " + pop1 + " from Stack");
                    placementBlockOne = (Block)blockList.get(pop1);    
                    System.out.println();
                    System.out.println("Area Block 1: " + placementBlockOne.area);
                    System.out.println("Height Block 1: " + placementBlockOne.height);
                    System.out.println("Width Block 1: " + placementBlockOne.width);
                    multipleBeforeChar = 0;
                }
            }
            else{
                System.out.println("Add: " + Character.toString(polishExpression[i]) + " to Stack");
                stack.add(Character.toString(polishExpression[i]));
                multipleBeforeChar = multipleBeforeChar + 1;
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("areaArray:");
        for (int i = 0; i < areaArray.length; i++){
            System.out.print(areaArray[i] + " ");
        }      
        return area;
    }
    
    
    public static int findMax(int currentMax, Block currentBlockOne, Block currentBlockTwo){
        int max = currentMax;
        if(max < currentBlockOne.height){
            max = currentBlockOne.height;
        }
        else if(max < currentBlockTwo.height){  
            max = currentBlockTwo.height;
        }  
        else{

        }
        return max;
    }
    
    public static void printList(ArrayList blockList){      
        for(int i = 0; i < blockList.size(); i++){
            Block placementBlock = (Block) blockList.get(i);      
            System.out.print(placementBlock.area + " ");
        }
    }
    
 
}