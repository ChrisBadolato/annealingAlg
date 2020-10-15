/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annealingalgcbadolato;

import java.util.ArrayList;
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
        char splitExpression[] = originalPolishExpression.toCharArray();

        Block one = new Block(1,1);
        Block two = new Block(2,7);
        Block three = new Block(3,7);
        Block four = new Block(2,5);
        Block five = new Block(7,5);
        Block six = new Block(2,9);
        Block seven = new Block(9,7);
        Block eight = new Block(3,1);
        Block nine = new Block(4,4);

        for(int i = 0; i < splitExpression.length;  i++){
            System.out.print(splitExpression[i]);
        } 

        System.out.println();
        System.out.println(one.width + " " +  one.height);
        System.out.println(one.area);

        System.out.println(two.width + " " +  two.height);
        System.out.println(two.area);

        System.out.println(three.width + " " +  three.height);
        System.out.println(three.area);

        System.out.println(four.width + " " +  four.height);
        System.out.println(four.area);

        System.out.println(five.width + " " +  five.height);
        System.out.println(five.area);

        System.out.println(six.width + " " +  six.height);
        System.out.println(six.area);

        System.out.println(seven.width + " " +  seven.height);
        System.out.println(seven.area);

        System.out.println(eight.width + " " +  eight.height);
        System.out.println(eight.area);

        System.out.println(nine.width + " " +  nine.height);
        System.out.println(nine.area);

        System.out.println();

        System.out.println("Stack:");
        Stack<String> originalStack = new Stack<String>();
        originalStack = createStack(splitExpression);

        while(!originalStack.empty()){
            System.out.println(originalStack.pop());
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
    
}
