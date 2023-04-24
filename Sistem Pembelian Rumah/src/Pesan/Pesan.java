package Pesan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//TODO buat class pesan
public class Pesan  {
    private Integer Idpesan;
    private Integer IdRumah;
    private Integer Idcus;
    private String Statuspsn;
    private String StatusPbyrn;
    private String TglPesan;

    public Pesan(Integer Idpesan, Integer IdRumah, Integer Idcus,String Statuspsn,String StatusPbyrn,String TglPesan){
        this.Idpesan = Idpesan;
        this.IdRumah = IdRumah;
        this.Idcus = Idcus;
        this.Statuspsn = Statuspsn;
        this.StatusPbyrn = StatusPbyrn;      
        this.TglPesan = TglPesan;  
    }
    public Integer getIdRumah() {
        return IdRumah;
    }
    public Integer getIdcus() {
        return Idcus;
    }
    public Integer getIdpesan() {
        return Idpesan;
    }
    public String getStatusPbyrn() {
        return StatusPbyrn;
    }
    public String getStatuspsn() {
        return Statuspsn;
    }
    public String getTglPesan() {
        return TglPesan;
    }
    public void setIdpesan(Integer idpesan) {
        Idpesan = idpesan;
    }
    public void setIdRumah(Integer idRumah) {
        IdRumah = idRumah;
    }
    public void setIdcus(Integer idcus) {
        Idcus = idcus;
    }
    public void setStatusPbyrn(String statusPbyrn) {
        StatusPbyrn = statusPbyrn;
    }
    public void setStatuspsn(String statuspsn) {
        Statuspsn = statuspsn;
    }
    public void setTglPesan(String tglPesan) {
        TglPesan = tglPesan;
    }
    public void SaveFilepesan(Integer Idpesan, Integer IdRumah, Integer Idcus,String Statuspsn,String StatusPbyrn,String Tglpesan){
        try{
            FileWriter writer = new FileWriter("data/Pesan.txt",true);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(Idpesan+","+IdRumah+","+Idcus+","+Statuspsn+","+StatusPbyrn+","+TglPesan+"\n");
            bw.close();
            writer.close();
            System.out.println("Anda Berhasil Pesan");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
