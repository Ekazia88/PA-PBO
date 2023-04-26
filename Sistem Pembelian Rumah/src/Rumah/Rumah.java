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
    private Boolean dipesan;
    public Rumah(int idRumah,int NomorRumah,String Alamat,String StatusRumah, String tipeRumah,Boolean dipesan){
        this.idRumah = idRumah;
        this.NomorRumah = NomorRumah;
        this.Alamat = Alamat;
        this.StatusRumah = StatusRumah;
        this.tipeRumah = tipeRumah;
        this.dipesan = dipesan;
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
    public Boolean getDipesan() {
        return dipesan;
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
    public void setDipesan(Boolean dipesan) {
        this.dipesan = dipesan;
    }
    public void SaveFileRumah(int idRumah,int NomorRumah,String Alamat,String StatusRumah, String tipeRumah, Boolean dipesan) {
        try {
            FileWriter writer = new FileWriter("data/Rumah.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(idRumah +"," + NomorRumah + ","+ Alamat + ","+StatusRumah +","+ tipeRumah + ","+dipesan+"\n");
            bw.close();
            writer.close();
            System.out.println("Data Save Sukses");
        } catch (IOException e) {
            System.out.println("An error occurred while registering to file.");
            e.printStackTrace();
        }
    }
}

