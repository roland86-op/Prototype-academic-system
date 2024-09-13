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
public class FungsiIF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int nilaiangka=60;
        String keterangan;
                if (nilaiangka>10){
                    keterangan="Lulus";
                }
                else{
                    keterangan="Gagal";
                }
                System.out.println("Nilai Angka : "+ nilaiangka);
                System.out.println("Keterangan  : "+ keterangan);
    }
    
}
