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
        
        test.add(1, 10);
        test.add(2, 20);
        test.add(3, 30);
        test.add(4, 40);
        test.add(5, 50);
        test.add(6, 60);
        test.add(7, 70);
        test.add(8, 80);
        test.add(9, 90);
        test.add(10, 100);
        test.add(11, 110);
        test.add(12, 120);
        test.add(13, 130);
        test.add(14, 140);
        test.add(15, 150);
        test.add(16, 160);
        
        map test1 = new map(test);        
        test.printKeyTree();
        System.out.println(test.contains(40));
    }
    
}
