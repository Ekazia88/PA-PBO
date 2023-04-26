package Pesan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Rumah.crud;

public class Adminpesan implements interfacePesan{
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    private static crud cr = new crud();
    private static ArrayList<Pesan> psnlist;
    public Adminpesan(){
        psnlist = new ArrayList<>();
    }
    public ArrayList<Pesan> getList(){
        return psnlist;
    }
    public void lihatseluruhpesan() throws IOException {
        System.out.println(psnlist.size());
        System.out.println("List Pesan");
        for(Pesan pslist : psnlist){
            Integer Idpesan = pslist.getIdpesan();
            Integer IdRumah = pslist.getIdRumah();
            Integer Idcus   = pslist.getIdcus();
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
    public void konfirmasipesan() throws IOException{
        lihatseluruhpesan();
        System.out.println("Konfirmasi Pesan");
        if(psnlist.size() == 0){
            System.out.println("Tidak ada Pesanan");
        }else{
            lihatseluruhpesan();
            System.out.println("Cari id Pesan : ");
            try{
                int idpesan = Integer.parseInt(br.readLine());
                if(checkpsn(idpesan) == false){
                    System.out.println("Data Tidak ditemukan!!");
                }else{
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
                            String tglpesan = pslist.getTglPesan();
                            System.out.println("Status Pembayaran : "+ pslist.getStatusPbyrn());
                            System.out.println(tglpesan);
                            
                            System.out.print("ubah Status Pembayaran [lunas/cicil] : ");
                            String statusPbyrn = br.readLine();
                            
                            pslist = new Pesan(idpesan, idRumah, idcus, konfirmasipesan, statusPbyrn,tglpesan);
                            psnlist.set(i, pslist);
                        }
                        try {
                            FileWriter wr = new FileWriter("data/pesan.txt",false);
                            BufferedWriter bw = new BufferedWriter(wr);
                            for(Pesan psn : psnlist){
                                bw.write(psn.getIdpesan() +"," + psn.getIdRumah() + ","+ psn.getIdcus() + ","+ psn.getStatuspsn() +","+ psn.getStatusPbyrn() + ","+psn.getTglPesan()+"\n");
                                bw.close();
                                    }
                        } catch (Exception e) {
                            System.out.println("Failed to save in file!!");
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }catch (NumberFormatException e) {
                    System.out.println("Error Input!! Harus Berupa Angka!!");    
            } catch (IOException e) {
                System.out.println("An error occurred while reading input.");
            }
        }
    }
    public boolean checkpsn(int idpesan){
        loadfile();
        getList();
        for(Pesan psn : psnlist){
            if(psn.getIdpesan() == idpesan){
                return true;
            }
        }
        return false;
    }
    public void hpspesanadmin() throws IOException {
        System.out.println("hapus Pesan admin");
        if(psnlist.size() == 0){
            System.out.println("Tidak ada Pesanan");
        }else{
            lihatseluruhpesan();
            System.out.println("Cari id Pesan : ");
            try{
                int idpsn = Integer.parseInt(br.readLine());
                if(checkpsn(idpsn) == false){
                    System.out.println("Data Tidak ditemukan!!");
                }else{
                    for(int i = 0; i < psnlist.size(); i++){
                        Pesan psn = psnlist.get(i);
                        if(psn.getIdcus() == idpsn){
                            psnlist.remove(i);
                        }
                    }
                    System.out.println("Pesan Berhasil dihapus");
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
            }catch (NumberFormatException e) {
                System.out.println("Error Input!! Harus Berupa Angka!!");
            } catch (IOException e) {
                System.out.println("An error occurred while reading input.");
            }
        }
    }
    @Override
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
