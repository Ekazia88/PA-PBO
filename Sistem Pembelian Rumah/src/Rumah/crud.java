package Rumah;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;
public class crud implements InterfaceRumah {
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    ArrayList <Rumah> rumahs;
    public crud(){
        this.rumahs = new ArrayList<>();
    }
    @Override
    public void create(Rumah rumahs) throws IOException{
        System.out.println("Tambahkan Data");
        int idRumah = this.rumahs.size() + 1;
        System.out.print("Masukkan Nomor rumah : ");
        int Norumah = Integer.parseInt(br.readLine());
        System.out.print("Masukkan Status Rumah [Belum Selesai,Selesai,Kosong]: ");
        String StatusRumah = br.readLine();
        System.out.println("Masukkan Tipe Rumah [Standar/Premium]: ");
        String TipeRumah = br.readLine();
        Rumah rh = new Rumah(idRumah, Norumah, TipeRumah, StatusRumah, TipeRumah);
        rh.SaveFileRumah(idRumah, Norumah, TipeRumah, StatusRumah, TipeRumah);
    }
    @Override
    public void update(Rumah rumahs) throws IOException {
        System.out.println("Ubah Data");
        if(this.rumahs.size() == 0){
            System.out.println("data Kosong");
        }else{
            System.out.println("Cari Nomor Rumah");
            int cari = Integer.parseInt(br.readLine());
            if(rumahs.getNomorRumah() == cari );
        }
            System.out.print("Masukkan Nomor rumah : ");
            int Norumah = Integer.parseInt(br.readLine());
            System.out.print("Masukkan Status Rumah [Belum Selesai,Selesai,Kosong]: ");
            String StatusRumah = br.readLine();
            System.out.println("Masukkan Tipe Rumah [Standar/Premium]: ");
            String TipeRumah = br.readLine();
    }
    
}
