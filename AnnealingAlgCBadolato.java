/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annealingalgcbadolato;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        System.out.println();
        int areaVertical = computeAllVerticalArea(blockList);
        System.out.println();
        int areaHorizontal = computeAllHorizontalArea(blockList);
        System.out.println();
        System.out.println("Stack:");
        
        Stack<String> originalStack = new Stack<String>();
        originalStack = createStack(splitExpression);
        while(!originalStack.empty()){
            System.out.println(originalStack.pop());
        }   
       
        Stack<String> horizontalStack = new Stack<String>();
        horizontalStack = createStack(allVertical);
        System.out.println("Horizontal Stack");       
        while(!horizontalStack.empty()){
            System.out.println(horizontalStack.pop());
        } 
        
        Stack<String> verticalStack = new Stack<String>();
        verticalStack = createStack(allHorizontal);
        System.out.println();

        System.out.println("Vertical Stack");
        while(!verticalStack.empty()){
            System.out.println(verticalStack.pop());
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
    public static Stack<String> createStack(char[] polishExpression){
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < polishExpression.length; i++){
            stack.add(Character.toString(polishExpression[i]));
        }
        return stack;
    }  
    
    public static int stackVerticalArea(char[] allVerticalPolish, ArrayList blockList){
        int area = 0;
        int areaValue1 = 0;
        int areaValue2 = 0;
        Block placementBlock = (Block) blockList.get(0);
        
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < allVerticalPolish.length; i++){
            if(allVerticalPolish[i] == 'V'){               
                areaValue2 = Integer.parseInt(stack.pop());
               
            }
            else{
                stack.add(Character.toString(allVerticalPolish[i]));
            }
        }
        return area;
    }
    
    public static int computeAllVerticalArea(ArrayList blockList){
        int area = 0;     
        Block placementBlock = (Block) blockList.get(0);
        int maxHeight = placementBlock.height;
        int widthSum = placementBlock.width;
        for(int i = 1; i < blockList.size(); i++){
            placementBlock = (Block) blockList.get(i);
            if(maxHeight < placementBlock.height){
                maxHeight = placementBlock.height;
            }
            else{  
                
            }  
            widthSum = widthSum + placementBlock.width;
        }
        System.out.println("width sum: " + widthSum);
        System.out.println("height Max: " + maxHeight);
        area = widthSum * maxHeight;
        System.out.println("Area Vertical: " + area);
        return area;
    }
    
    public static int computeAllHorizontalArea(ArrayList blockList){
        int area = 0;     
        Block placementBlock = (Block) blockList.get(0);
        int heightSum = placementBlock.height;
        int widthMax = placementBlock.width;
        for(int i = 1; i < blockList.size(); i++){
            placementBlock = (Block) blockList.get(i);
            if(widthMax < placementBlock.width){
                widthMax = placementBlock.width;
            }
            else{  
                
            }  
            heightSum = heightSum + placementBlock.height;
        }
        System.out.println("height sum: " + heightSum);
        System.out.println("width Max: " + widthMax);
        area = heightSum * widthMax;
        System.out.println("area horizontal: " + area);
        return area;
    }
    
    public static void printList(ArrayList blockList){      
        for(int i = 0; i < blockList.size(); i++){
            Block placementBlock = (Block) blockList.get(i);      
            System.out.print(placementBlock.area + " ");
        }
    }
    
 
}
