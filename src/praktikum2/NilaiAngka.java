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
public class NilaiAngka {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        
        int NilaiAngka;
        String Nilai;
        
        System.out.println("Nilai: ");
        NilaiAngka = input.nextInt();
        
        if(NilaiAngka>80){
            Nilai = "A";
        }
        else if(NilaiAngka>=75){
            Nilai = "AB";
        }
        else if(NilaiAngka>=70){
            Nilai = "B";
        }
        else if(NilaiAngka>=65){
            Nilai = "BC";
        }
        else if(NilaiAngka>=60){
            Nilai = "C";
        }
        else if(NilaiAngka>=50){
            Nilai = "D";
        }
        else {
            Nilai = "E";
        }
        
        System.out.println("Nilai: "+Nilai);   
    }
}