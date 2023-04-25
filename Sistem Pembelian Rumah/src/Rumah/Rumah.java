package Rumah;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Rumah{
    private final int idRumah;
    private int NomorRumah;
    private String Alamat;
    private String StatusRumah;
    private String tipeRumah; 
    public Rumah(int idRumah,int NomorRumah,String Alamat,String StatusRumah, String tipeRumah){
        this.idRumah = idRumah;
        this.NomorRumah = NomorRumah;
        this.Alamat = Alamat;
        this.StatusRumah = StatusRumah;
        this.tipeRumah = tipeRumah;
    }
    public String getAlamat() {
        return Alamat;
    }
    public int getIdRumah() {
        return idRumah;
    }
    public int getNomorRumah() {
        return NomorRumah;
    }
    public String getStatusRumah() {
        return StatusRumah;
    }
    public String getTipeRumah() {
        return tipeRumah;
    }
    public void setAlamat(String alamat) {
        Alamat = alamat;
    }
    public void setNomorRumah(int nomorRumah) {
        NomorRumah = nomorRumah;
    }
    public void setStatusRumah(String statusRumah) {
        StatusRumah = statusRumah;
    }
    public void setTipeRumah(String tipeRumah) {
        this.tipeRumah = tipeRumah;
    }
    public void SaveFileRumah(int idRumah,int NomorRumah,String Alamat,String StatusRumah, String tipeRumah) {
        try {
            FileWriter writer = new FileWriter("data/Rumah.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(idRumah +"," + NomorRumah + ","+ Alamat + ","+StatusRumah +","+ tipeRumah + "\n");
            bw.close();
            writer.close();
            System.out.println("Data Save Sukses");
        } catch (IOException e) {
            System.out.println("An error occurred while registering to file.");
            e.printStackTrace();
        }
    }
}

