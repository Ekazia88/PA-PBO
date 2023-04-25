package Pesan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.sound.midi.Soundbank;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import Account.*;

import Rumah.*;
public class userpensan implements interfacePesan{
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    private static crud cr = new crud();
    private static ArrayList<Pesan> psnlist;
    static ArrayList<AccCustomer> cstr = SaveFile.getCustomers();
    public userpensan(){
        psnlist = new ArrayList<>();
        loadfile();
        getList();
    }
    public ArrayList<Pesan> getList(){
        return psnlist;
    }
    public void setList(ArrayList<Pesan> pslist){
        psnlist = pslist;
    }
    @Override
    public void lihatseluruhpesan() throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("List Pesan");
        for(Pesan pslist : psnlist){
            Integer Idpesan = pslist.getIdpesan();
            Integer IdRumah = pslist.getIdRumah();
            Integer Idcus   = pslist.getIdpesan();
            String Statuspsn = pslist.getStatuspsn();
            String StatusPbyrn = pslist.getStatusPbyrn();
            System.out.println("-------");
            System.out.println("Idpesan : "+Idpesan);
            System.out.println("IdRumah : "+IdRumah);
            System.out.println("id customer : "+Idcus);
            System.out.println("Status Pesanan : "+Statuspsn);
            System.out.println("Status Pembayaran : "+StatusPbyrn);
            System.out.println("--------");
        }
    }
    @Override
    public void ubahpesan() throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Ubah Data ");
        if(psnlist.size() == 0){
            System.out.println("Pesanan Kosong");
        }else{
            System.out.println("cari Idpesan : ");
            int cari = Integer.parseInt(br.readLine());
            for(int i =0; i < psnlist.size(); i++){
                Pesan updlist = psnlist.get(i);
                if(updlist.getIdpesan() == cari){
                    System.out.println();
                    int updidpesan = updlist.getIdpesan();
                    int updidRumah = updlist.getIdRumah();
                    int updidcustomer = updlist.getIdcus();
                    System.out.println("Ubah Status Pesanan : ");
                    String Statuspsn = br.readLine();
                    System.out.println("Ubah Status Pesanan : ");
                    String tglpesan = updlist.getTglPesan();
                    updlist = new Pesan(updidpesan, updidRumah, updidcustomer, Statuspsn, Statuspsn,tglpesan);
                    psnlist.set(i, updlist);
                }
            }
            try{
                FileWriter writer = new FileWriter("data/pesan.txt", false);
                BufferedWriter bw = new BufferedWriter(writer);
                for(Pesan psn : psnlist){
                bw.write(psn.getIdpesan() +"," + psn.getIdRumah() + ","+ psn.getIdcus() + ","+ psn.getStatuspsn() +","+ psn.getStatusPbyrn() + "\n");
                bw.close();
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public Pesan getpsn(int idcus){
        psnlist = getList();
        for(Pesan psnl : psnlist){
            if(psnl.getIdcus() == idcus){
                return psnl;
            }
        }
        return null;
    }
    @Override
    //todo baikin lihatpesan customer 
    public void lihatpesancustomer(int idcus,String username,ArrayList<AccCustomer> cstr,int index) throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Pesan listpns = getpsn(idcus);
        if(listpns == null){
            System.out.println("Anda Belum Memesan Apapun");
        }else{
            System.out.println("Liat Pesan");
            int id = listpns.getIdRumah();
            AccCustomer customer = cstr.get(index);
            Rumah rmh = cr.getidrmh(id);
            System.out.println("--------------");
            System.out.println("1. id pesan : " +listpns.getIdpesan());
            System.out.println("2. Nama Pemesan : " + customer.getNama());
            System.out.println("3. Alamat : "+customer.getAlamat());
            System.out.println("4. Tgl pesan : "+ listpns.getTglPesan() );
            System.out.println("5. Tipe Rumah : "+ rmh.getTipeRumah());
            System.out.println("6. Status Rumah : "+rmh.getStatusRumah());
            System.out.println("7. Status Pembayaran : "+listpns.getStatusPbyrn());
            System.out.println("8. Status Pesan : "+listpns.getStatuspsn());
            System.out.println("-----------------");
        }
    }
    @Override
    public void hpspesanuser(int idcus) throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("hapus Pesan");
        if(psnlist.size() == 0){
            System.out.println("Tidak ada Pesanan");
        }else{
            System.out.print("Anda yakin Menghapus Pesanan [y/t]: ");
            String menu = br.readLine();
            if(menu.equals("y")){
                for(int i = 0; i < psnlist.size(); i++){
                    Pesan psn = psnlist.get(i);
                    if(psn.getIdcus() == idcus){
                        psnlist.remove(i);
                    }
                }
                try{
                    FileWriter writer = new FileWriter("data/pesan.txt", false);
                    BufferedWriter bw = new BufferedWriter(writer);
                    for(Pesan psn  : psnlist ){
                        bw.write(psn.getIdpesan() +"," + psn.getIdRumah() + ","+ psn.getIdcus() + ","+ psn.getStatuspsn() +","+ psn.getStatusPbyrn() + "\n");
                        bw.close();
                    }
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                } 
            }else if(menu.equals("t")){

            }else{
                System.out.println("Invalid Input");
            }
        }
    }
    public boolean checkpesan(int idcus){
        psnlist = getList();
        for(Pesan chkpsn : psnlist){
            if(chkpsn.getIdcus() == idcus){
                return true;
            }
        }
        return false;
    }
    @Override
    public void tambahpesan(String username,int idcus) throws IOException{
        if(checkpesan(idcus) == true){
            System.out.println("Anda Hanya bisa memesan Sekali!!!");
        }else{
            System.out.print("Nomor Rumah Yang ingin dipesan : ");
            int cari = Integer.parseInt(br.readLine());
            Rumah rmh = cr.getnormh(cari);
            if(crud.getRumah(cari) == null ){
                System.out.println("Nomor rumah yg kamu cari ngak ada");
            }else if(rmh.getDipesan() == true){
                System.out.println("Rumah Ini sudah dipesan!!!");
            }else{
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                int idpesan = psnlist.size() + 1;
                int idRumah = rmh.getIdRumah();
                String StatusPesanan = "KonfirmasiPesan";
                String Pembayaran = "Belum Bayar";
                Date date = new Date();
                String Tglpesan = format.format(date); 
                Pesan psn = new Pesan(idpesan, idRumah, idcus, StatusPesanan, Pembayaran,Tglpesan);
                psn.SaveFilepesan(idpesan, idRumah, idcus, StatusPesanan, Pembayaran,Tglpesan);
            }
        }
    }
    public void lihatdatadiri(String Username,ArrayList<AccCustomer> cstr,int index) throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        AccCustomer customer = cstr.get(index);
        System.out.println("Data Diri");
        System.out.println("Nama  : "+ customer.getNama());
        System.out.println("Username : "+ customer.getUsername());
        System.out.println("Password : "+ customer.getPassword());
        System.out.println("Umur : "+customer.getUmur());
        System.out.println("Alamat : "+ customer.getAlamat());
        System.out.println("Jenis Kelamin : "+customer.getJenisKelamin());
        System.out.println("-------------");
    }
    public void konfirmasipesan() throws IOException{
        lihatseluruhpesan();
        System.out.println("Cari id : ");
        int idpesan = Integer.parseInt(br.readLine());
        getList();
        for(int i = 0; i < psnlist.size();i++ ){
            Pesan pslist = psnlist.get(i);
            if(idpesan == pslist.getIdpesan() ){
                int idcus = pslist.getIdcus();
                int idRumah = pslist.getIdRumah();
                System.out.println("Status Pesanan : "+ pslist.getStatuspsn());
                System.out.print("Ubah status Pesanan ? [ya/tidak]: ");
                String pilih = br.readLine();
                String konfirmasipesan = pslist.getStatuspsn();
                if(pilih.equals("ya")){
                    System.out.print("Konfirmasi Pesan[gagal/berhasil/pending] : ");
                    konfirmasipesan = br.readLine();
                }else if(pilih.equals("tidak")){
                    konfirmasipesan = pslist.getStatuspsn();
                }else{
                    System.out.println("pilihan harus ya/tidak ");
                }
                System.out.println("Status Pembayaran : "+ pslist.getStatusPbyrn());
                System.out.print("ubah Status Pembayaran [lunas/cicil] : ");
                String statusPbyrn = br.readLine();
                String tglpesan = pslist.getTglPesan();
                pslist = new Pesan(idpesan, idRumah, idcus, konfirmasipesan, statusPbyrn,tglpesan);
            }
            try {
                FileWriter wr = new FileWriter("data/pesan.txt",false);
                BufferedWriter bw = new BufferedWriter(wr);
                for(Pesan psn : psnlist){
                    bw.write(psn.getIdpesan() +"," + psn.getIdRumah() + ","+ psn.getIdcus() + ","+ psn.getStatuspsn() +","+ psn.getStatusPbyrn() + "\n");
                    bw.close();
                }
            } catch (Exception e) {
                System.out.println("Failed to save in file!!");
                System.out.println(e.getMessage());
            }
        }

    }
    public void hpspesanadmin() throws IOException {
        lihatseluruhpesan();
        System.out.println("hapus Pesan admin");
        if(psnlist.size() == 0){
            System.out.println("Tidak ada Pesanan");
        }else{
            System.out.println("Cari id Pesan : ");
            int idpsn = Integer.parseInt(br.readLine());
                for(int i = 0; i < psnlist.size(); i++){
                    Pesan psn = psnlist.get(i);
                    if(psn.getIdcus() == idpsn){
                        psnlist.remove(i);
                    }
                }
                try{
                    FileWriter writer = new FileWriter("data/pesan.txt", false);
                    BufferedWriter bw = new BufferedWriter(writer);
                    for(Pesan psn  : psnlist ){
                        bw.write(psn.getIdpesan() +"," + psn.getIdRumah() + ","+ psn.getIdcus() + ","+ psn.getStatuspsn() +","+ psn.getStatusPbyrn() + "\n");
                        bw.close();
                    }
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }
    }
    public void loadfile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("data/pesan.txt"));
            String line = reader.readLine();
            while(line != null){
                String[] tokens = line.split(",");
                    if (tokens.length >= 5) {
                        int idpesan = Integer.parseInt(tokens[0]);
                        int idRumah = Integer.parseInt(tokens[1]);
                        int idcustomer = Integer.parseInt(tokens[2]);
                        String StatusPesanan = tokens[3];
                        String StatusPembayaran = tokens[4];
                        String tglpesan = tokens[5];
                        Pesan psn = new Pesan(idpesan, idRumah,idcustomer,StatusPesanan,StatusPembayaran,tglpesan);
                        psnlist.add(psn);
                    }
                line = reader.readLine();
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }   
}
