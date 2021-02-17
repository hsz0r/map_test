/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_map;

/**
 *
 * @author hsz0r
 */
public class Test_map {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        map test = new map();
        
        for (int i = 1; i <= 50; i++){
            test.add(i,i);
        }
        test.printKeyTree();
        System.out.println(test.maxHeightTest());
        test.clear();
        
        int a = 50;
        for (int i = 1; i <= 50; i++){
            test.add(i,a);
            a--;
        }
        test.printKeyTree();
        System.out.println(test.maxHeightTest());
        test.clear();
            
        for (int i = 1; i <= 20; i++){
            test.add(i,(int) (Math.random() * +999));
        }
        test.printKeyTree();
        System.out.println(test.maxHeightTest());
        //test.clear();
        
        map test1 = new map(test);        
        System.out.println(test1.contains(40));

    }
    
}
