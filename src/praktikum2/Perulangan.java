/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum2;

/**
 *
 * @author YV-Lab1-33
 */
public class Perulangan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //perulangan for
        int i;
        for (i=1;i<6;i++){
            System.out.println(i);
        }
        
        //perulangan while
        int j=5;
        while(j>0){
            System.out.print(j);
            j--;
        }
        
        //perulangando while
        int x=0;
        do{
            System.out.println(x);
            x++;
        }while(x<10);
        
    }
    
}
