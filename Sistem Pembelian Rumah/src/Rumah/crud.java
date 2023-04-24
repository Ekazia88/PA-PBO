package Rumah;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.io.IOException;
public class crud implements InterfaceRumah {
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    private static ArrayList<Rumah> rumahs;
    
    public crud(){
        this.rumahs = new ArrayList<>();
        loadfile();
        getList();
    }
    public ArrayList<Rumah> getList(){
        return rumahs;
    }
    public void setList(ArrayList<Rumah> rumas) {
        rumahs = rumas;
    }
    @Override
    public void create() throws IOException{
        System.out.println("Tambahkan Data");
        int idRumah = rumahs.size() + 1;
        System.out.print("Masukkan Nomor rumah : ");
        int Norumah = Integer.parseInt(br.readLine());
        System.out.print("Masukkan Alamat : ");
        String Alamat = br.readLine();
        System.out.print("Masukkan Status Rumah [Belum Selesai,Selesai,Kosong]: ");
        String StatusRumah = br.readLine();
        System.out.println("Masukkan Tipe Rumah [Standar/Premium]: ");
        String TipeRumah = br.readLine();
        Rumah rh = new Rumah(idRumah, Norumah, Alamat, StatusRumah, TipeRumah);
        rh.SaveFileRumah(idRumah, Norumah, Alamat, StatusRumah, TipeRumah);
    }
    @Override
    public void update() throws IOException {
        System.out.println("Ubah Data");
        if(rumahs.size() == 0){
            System.out.println("data Kosong");
        }else{
            System.out.print("Cari Nomor Rumah : ");
            int cari = Integer.parseInt(br.readLine());
            for(int i = 0; i < rumahs.size(); i++){
                Rumah rmhs = rumahs.get(i);
                if(rmhs.getNomorRumah() == cari ){
                    int idrumah = rmhs.getIdRumah();
                    System.out.print("Masukkan Nomor rumah : ");
                    int Norumah = Integer.parseInt(br.readLine());
                    System.out.print("Masukkan Alamat : ");
                    String Alamat =  br.readLine();
                    System.out.print("Masukkan Status Rumah [Belum Selesai,Selesai,Kosong]: ");
                    String StatusRumah = br.readLine();
                    System.out.println("Masukkan Tipe Rumah [Standar/Premium]: ");
                    String TipeRumah = br.readLine();
                    rmhs = new Rumah(idrumah, Norumah, Alamat, StatusRumah, TipeRumah);
                    rumahs.set(i, rmhs);                
            }
        }
        }
        try{
            FileWriter writer = new FileWriter("data/Rumah.txt", false);
            BufferedWriter bw = new BufferedWriter(writer);
            for(Rumah rmhs : rumahs){
            bw.write(rmhs.getIdRumah() +"," + rmhs.getNomorRumah() + ","+ rmhs.getAlamat() + ","+rmhs.getStatusRumah() +","+ rmhs.getStatusRumah() + "\n");
            bw.close();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void delete() throws IOException {
        System.out.println("hapus data");
        if(this.rumahs.size() == 0){
            System.out.println("data Kosong");
        }else{
            System.out.print("Cari Nomor Rumah : ");
            int cari = Integer.parseInt(br.readLine());
            for(int i = 0; i < rumahs.size(); i++){
                Rumah rmhs = rumahs.get(i);
                if(rmhs.getNomorRumah() == cari ){
                    rumahs.remove(i);
                }
            }
        }
        try{
            FileWriter writer = new FileWriter("data/Rumah.txt", false);
            BufferedWriter bw = new BufferedWriter(writer);
            for(Rumah rmhs : rumahs){
            bw.write(rmhs.getIdRumah() +"," + rmhs.getNomorRumah() + ","+ rmhs.getAlamat() + ","+rmhs.getStatusRumah() +","+ rmhs.getStatusRumah() + "\n");
            bw.close();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void loadfile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("data/Rumah.txt"));
            String line = reader.readLine();
            while(line != null){
                String[] tokens = line.split(",");
                    if (tokens.length >= 5) {
                        int idRumah = Integer.parseInt(tokens[0]);
                        int NomorRumah = Integer.parseInt(tokens[1]);
                        String Alamat = tokens[2];
                        String StatusRumah = tokens[3];
                        String tipeRumah = tokens[4];
                        Rumah rmh = new Rumah(idRumah, NomorRumah,Alamat,StatusRumah,tipeRumah);
                        rumahs.add(rmh);
                    }
                line = reader.readLine();
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void lihat() throws IOException {
        System.out.println("List items:");
        for (Rumah rmhs : rumahs) {
            int idrumah = rmhs.getIdRumah();
            int NomorRumah = rmhs.getNomorRumah();
            String Alamat = rmhs.getAlamat();
            String StatusRumah = rmhs.getStatusRumah();
            String TipeRumah = rmhs.getTipeRumah();
            System.out.println("-----");
            System.out.println("id : "+idrumah);
            System.out.println("Nomor Rumah: "+NomorRumah);
            System.out.println("Alamat : "+Alamat);
            System.out.println("Status : "+StatusRumah);
            System.out.println("Tipe : "+TipeRumah );
            System.out.println("-----");
        }
    }
    public static Rumah getRumah(int cari){
        try{
            String filename = "data/users.txt";
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            int nomorRumah;
            int idRumah;
            String Alamat;
            String StatusRumah;
            String TipeRumah;
            String[] tokens = line.split(",");   
            nomorRumah = Integer.parseInt(tokens[1]);
            idRumah = Integer.parseInt(tokens[0]);
            Alamat = tokens[2];
            StatusRumah = tokens[3];
            TipeRumah = tokens[4];
            Rumah getrmh = null;
            if(nomorRumah == cari){
                getrmh = new Rumah(idRumah, nomorRumah, Alamat, StatusRumah, TipeRumah);
                rumahs.add((Rumah) getrmh);
            }else{
                System.out.println("Nomor Rumah tidak ada!!");
            }
            bufferedReader.close();
            return getrmh;
        }catch(IOException e){
            System.out.println("And Error occurred while reading file!!!");
            e.printStackTrace();
        }
        return null;
    }
}
