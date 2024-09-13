/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum2;
import java.util.Scanner;
/**
 *
 * @author YV-Lab1-33
 */
public class MenghitungLuasPersegiPanjang {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        
        float panjang;
        float lebar;
        float luas;
        
        System.out.println("Panjang persegi panjang: ");
        panjang = input.nextFloat();
        
        System.out.println("Lebar persegi panjang: ");
        lebar = input.nextFloat();
        
        luas=panjang*lebar;
        
        System.out.println("Luas Persegi Panjang: "+luas);
    }
    
}
