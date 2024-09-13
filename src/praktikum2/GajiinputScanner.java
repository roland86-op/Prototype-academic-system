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
public class GajiinputScanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        
        System.out.println("## Program Java Menghitung Gaji Karyawan ##");
        System.out.println("=================================================");
        System.out.println();
        
        String nama;
        char golongan;
        int jamkerja=0, gajiperjam=0, totalgaji;
        
        System.out.println("Nama Karyawan: ");
        nama = input.nextLine();
        
        System.out.println("Golongan: ");
        golongan = input.next().charAt(0);
        
        System.out.println("Jumlah jam kerja: ");
        jamkerja = input.nextInt();
        
        System.out.println();
        
        //tentukan jumlah upah per jam berdasarkan golongan
        switch(golongan){
            case 'A':
                gajiperjam = 5000;
                break;
            case 'B':
                gajiperjam = 7000;
                break;
            case 'C':
                gajiperjam = 8000;
                break;
            case 'D':
                gajiperjam = 10000;
                break;
        }
        totalgaji=jamkerja*gajiperjam;
        
        //cek apakah jam kerja lebih dari 48 jam
        if((jamkerja-48)>0){
            totalgaji=totalgaji+((jamkerja-48)*4000);
        }
        
        //proses output
        System.out.println(nama+" menerima upah Rp."+totalgaji+" per minggu");
    }
    
}
