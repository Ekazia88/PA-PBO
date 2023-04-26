package Pesan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import Account.*;

import Rumah.*;
public class userpensan{
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    private static crud cr = new crud();
    private static ArrayList<Pesan> psnlist;
    static ArrayList<AccCustomer> cstr = SaveFile.getCustomers();
    public userpensan(){
        psnlist = new ArrayList<>();
    }
    public ArrayList<Pesan> getList(){
        return psnlist;
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
    public void lihatpesancustomer(int idcus,ArrayList<AccCustomer> cstr,int index) throws IOException {
        loadfile();
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
    public void hpspesanuser(int idcus) throws IOException {
        loadfile();
        System.out.println("hapus Pesan");
        if(psnlist.size() == 0){
            System.out.println("Tidak ada Pesanan");
        }else{
            System.out.print("Anda yakin Menghapus Pesanan Anda [y/t]: ");
            String menu = br.readLine();  
            if(menu.equals("y")){
                for(int i = 0; i < psnlist.size(); i++){
                    Pesan psn = psnlist.get(i);
                    if(psn.getIdcus() == idcus){
                        Rumah rmh = cr.getidrmh(psn.getIdRumah());
                        cr.updstatuspesan(rmh.getNomorRumah());
                        psnlist.remove(i);
                    }
                    
                }
                try{
                    FileWriter writer = new FileWriter("data/pesan.txt", false);
                    BufferedWriter bw = new BufferedWriter(writer);
                    for(Pesan psn  : psnlist ){
                        bw.write(psn.getIdpesan() +"," + psn.getIdRumah() + ","+ psn.getIdcus() + ","+ psn.getStatuspsn() +","+ psn.getStatusPbyrn() + ","+psn.getTglPesan()+"\n");  
                    }
                    bw.close();
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
    public void tambahpesan(String username,int idcus) throws IOException{
        if(checkpesan(idcus) == true){
            System.out.println("Anda Hanya bisa memesan Sekali!!!");
        }else{
            System.out.print("Nomor Rumah Yang ingin dipesan : ");
            try{
                int cari = Integer.parseInt(br.readLine());
                Rumah rmh = cr.getnormh(cari);
                if(crud.getRumah(cari) == null ){
                    System.out.println("Nomor rumah yg kamu cari ngak ada");
                }else if(rmh.getDipesan() == true){
                    System.out.println("Rumah Ini sudah dipesan!!!");
                }else{
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    int idpesan = rmh.getIdRumah() + rmh.getNomorRumah();
                    int idRumah = rmh.getIdRumah();
                    String StatusPesanan = "Pending";
                    String Pembayaran = "Belum Bayar";
                    Date date = new Date();
                    String Tglpesan = format.format(date);
                    cr.updstatuspesan(rmh.getNomorRumah()); 
                    Pesan psn = new Pesan(idpesan, idRumah, idcus, StatusPesanan, Pembayaran,Tglpesan);
                    psn.SaveFilepesan(idpesan, idRumah, idcus, StatusPesanan, Pembayaran,Tglpesan);
                    
                }
            }catch (NumberFormatException e) {
                System.out.println("Error Input!! Harus Berupa Angka!!");
            } catch (IOException e) {
                System.out.println("An error occurred while reading input.");
            }
           
        }
    }
    public void lihatdatadiri(String Username,ArrayList<AccCustomer> cstr,int index) throws IOException {
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
    public void loadfile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("data/pesan.txt"));
            String line = reader.readLine();
            if(!psnlist.isEmpty()){
                psnlist.clear();
            }
            while(line != null){
                String[] tokens = line.split(",");
                    if (tokens.length == 6) {
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
