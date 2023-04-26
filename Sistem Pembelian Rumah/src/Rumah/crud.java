package Rumah;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;
public class crud{
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    private static ArrayList<Rumah> rumahs;
    
    public crud(){
        rumahs = new ArrayList<>();
    }
    public ArrayList<Rumah> getList(){
        return rumahs;
    }
    public void setList(ArrayList<Rumah> rumas) {
        rumahs = rumas;
    }
    public void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean checkRumah(int NomorRumah){
        loadfile();
        getList();
        for(Rumah rmhs : rumahs){
            if(rmhs.getNomorRumah() == NomorRumah){
                return true;
            }
        }
        return false;
    }

    public void create() throws IOException{
        cls();
        System.out.println("Tambahkan Data");
        System.out.print("Masukkan Nomor rumah : ");
        try{
            int Norumah = Integer.parseInt(br.readLine());
            if(checkRumah(Norumah)== true){
                System.out.println("Nomor Rumah Sudah Ada!!");
            }else{
                System.out.print("Masukkan Alamat : ");
                String Alamat = br.readLine();
                System.out.print("Masukkan Status Rumah [Belum Selesai/Selesai/Kosong]: ");
                String StatusRumah = br.readLine();
                if(StatusRumah.equals("Belum Selesai") || StatusRumah.equals("Selesai") || StatusRumah.equals("Kosong")){
                    System.out.print("Masukkan Tipe Rumah [Standar/Premium]: ");
                    String TipeRumah = br.readLine();
                    if(TipeRumah.equals("Standar") || TipeRumah.equals("Premium")){
                        int idRumah = rumahs.size() + Norumah;
                        Boolean dipesan = false;
                        Rumah rh = new Rumah(idRumah, Norumah, Alamat, StatusRumah, TipeRumah,dipesan);
                        rh.SaveFileRumah(idRumah, Norumah, Alamat, StatusRumah, TipeRumah,dipesan);
                    }else{
                        System.out.println("Tipe Rumah Harus Standar / Premium !!");
                    }             
                }else{
                    System.out.println("Status Rumah Harus Sesuai 3 kondisi yg telah disedikan!!!");
                }
            }
        }catch (NumberFormatException e) {
            System.out.println("Error Input!! Harus Berupa Angka!!");
        } 
    }
    public void updstatuspesan(int NomorRumah){
        rumahs.clear();
        loadfile();
        getList();
        for(Rumah rmhs : rumahs){
            if(rmhs.getNomorRumah() == NomorRumah){
                if(rmhs.getDipesan() == false){
                    rmhs.setDipesan(true);
                }else{
                    rmhs.setDipesan(false);
                }
                break;
            }
       
        }
        try{
            FileWriter writer = new FileWriter("data/Rumah.txt");
            BufferedWriter bw = new BufferedWriter(writer);
            for(Rumah rmhs : rumahs){
            bw.write(rmhs.getIdRumah() +"," + rmhs.getNomorRumah() + ","+ rmhs.getAlamat() + ","+rmhs.getStatusRumah() +","+ rmhs.getTipeRumah()+","+rmhs.getDipesan()+"\n");
            }
            bw.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    public void update() throws IOException {
        cls();
        System.out.println("Ubah Data");
        if(rumahs.size() == 0){
            System.out.println("data Kosong");
        }else{
            System.out.print("Cari Nomor Rumah : ");
            try{
                int cari = Integer.parseInt(br.readLine());
                if(checkRumah(cari) == false){
                    System.out.println("Nomor Rumah Yg Anda Cari Tidak Ada!!!");
                }else{
                    for(int i = 0; i < rumahs.size(); i++){
                        Rumah rmhs = rumahs.get(i);
                        if(rmhs.getNomorRumah() == cari ){
                            int idrumah = rmhs.getIdRumah();
                            System.out.print("Masukkan Nomor rumah : ");
                            int Norumah = Integer.parseInt(br.readLine());
                            if(checkRumah(Norumah) == true){
                                System.out.println("Nomor Rumah Sudah Ada!!");
                            }else{
                                System.out.print("Masukkan Alamat : ");
                                String Alamat = br.readLine();
                                System.out.print("Masukkan Status Rumah [Belum Selesai/Selesai/Kosong]: ");
                                String StatusRumah = br.readLine();
                                if(StatusRumah.equals("Belum Selesai") || StatusRumah.equals("Selesai") || StatusRumah.equals("Kosong")){
                                    System.out.print("Masukkan Tipe Rumah [Standar/Premium]: ");
                                    String TipeRumah = br.readLine();
                                    if(TipeRumah.equals("Standar") || TipeRumah.equals("Premium")){
                                        Boolean dipesan = rmhs.getDipesan();
                                        rmhs = new Rumah(idrumah, Norumah, Alamat, StatusRumah, TipeRumah,dipesan);
                                        rumahs.set(i, rmhs);
                                    }else{
                                        System.out.println("Tipe Rumah Harus Standar / Premium !!");
                                    }             
                                }else{
                                    System.out.println("Status Rumah Harus Sesuai 3 kondisi yg telah disedikan!!!");
                                }
                            }                
                        }
                    }
                    try{
                        FileWriter writer = new FileWriter("data/Rumah.txt");
                        BufferedWriter bw = new BufferedWriter(writer);
                        for(Rumah rmhs : rumahs){
                        bw.write(rmhs.getIdRumah() +"," + rmhs.getNomorRumah() + ","+ rmhs.getAlamat() + ","+rmhs.getStatusRumah() +","+ rmhs.getTipeRumah()+","+rmhs.getDipesan()+"\n");
                        }
                        bw.close();
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }catch (NumberFormatException e) {
                System.out.println("Error Input!! Harus Berupa Angka!!");
            }
        }
    }
    public void delete() throws IOException {
        cls();
        System.out.println("hapus data");
        if(rumahs.size() == 0){
            System.out.println("data Kosong!!!");
        }else{
            System.out.print("Cari Nomor Rumah : ");
            int cari = Integer.parseInt(br.readLine());
            try{
                if(checkRumah(cari) == false){
                    System.out.println("Nomor Rumah Yg Anda Cari Tidak Ada!!!");
                }else{
                    for(int i = 0; i < rumahs.size(); i++){
                        Rumah rmhs = rumahs.get(i);
                        if(rmhs.getNomorRumah() == cari ){
                            rumahs.remove(i);
                        }
                    }
                }
                try{
                    FileWriter writer = new FileWriter("data/Rumah.txt");
                    BufferedWriter bw = new BufferedWriter(writer);
                    for(Rumah rmhs : rumahs){
                        bw.write(rmhs.getIdRumah() +"," + rmhs.getNomorRumah() + ","+ rmhs.getAlamat() + ","+rmhs.getStatusRumah() +","+ rmhs.getTipeRumah()+","+rmhs.getDipesan()+"\n");
                    }
                    bw.close();
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }catch (NumberFormatException e) {
                System.out.println("Error Input!! Harus Berupa Angka!!");
            }
        }
    }
    public void loadfile() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("data/Rumah.txt"));
            String line = reader.readLine();
            if(!rumahs.isEmpty()){
                rumahs.clear();
            }
            while(line != null){
                String[] tokens = line.split(",");
                    if (tokens.length >= 6) {
                        int idRumah = Integer.parseInt(tokens[0]);
                        int NomorRumah = Integer.parseInt(tokens[1]);
                        String Alamat = tokens[2];
                        String StatusRumah = tokens[3];
                        String tipeRumah = tokens[4];
                        Boolean dihuni = Boolean.valueOf(tokens[5]);
                        Rumah rmh = new Rumah(idRumah, NomorRumah,Alamat,StatusRumah,tipeRumah,dihuni);
                        rumahs.add(rmh);
                    }
                line = reader.readLine();
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void lihat() throws IOException {
        cls();
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
            if(rmhs.getDipesan() == false){
                System.out.println("Status pesan : kosong");
            }else{
                System.out.println("Status Pesan : Sudah Dipesan");
            }
            System.out.println("-----");
        }
    }
    public static Rumah getRumah(int cari){
        try{
            String filename = "data/rumah.txt";
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            Rumah rhm = null;
            while((line = bufferedReader.readLine())!= null){
                String[] tokens= line.split(",");
                int idRumah = Integer.parseInt(tokens[0]);
                int NomorRumah = Integer.parseInt(tokens[1]);
                String Alamat = tokens[2];
                String StatusRumah = tokens[3];
                String tipeRumah = tokens[4];
                boolean dihuni = Boolean.valueOf(tokens[5]);
                if(NomorRumah == cari){
                    rhm = new Rumah(idRumah, NomorRumah, Alamat, StatusRumah, tipeRumah,dihuni);
                    rumahs.add(rhm);
                    break;
                }
            }
            bufferedReader.close();
            return rhm;
        } catch (FileNotFoundException e) {
            System.out.println("file rumah not found: ");
            return null;
        } catch (IOException e) {
            System.out.println("An error occurred while reading file");
            e.printStackTrace();
            return null;
        }
    }
    public Rumah getidrmh(int idrumah){
        for(Rumah rhm : rumahs){
            if(rhm.getIdRumah() == idrumah){
                return rhm;
            }
        }
        return null;
    }
    public Rumah getnormh(int nomorRumah){
        for(Rumah rhm : rumahs){
            if(rhm.getNomorRumah() == nomorRumah){
                return rhm;
            }
        }
        return null;
    }
}
