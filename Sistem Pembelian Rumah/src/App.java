import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import Account.*;
import Pesan.userpensan;
import Rumah.*;
public class App {

    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    static SaveFile save = new SaveFile();
    static crud crud = new crud();
    static userpensan psn = new userpensan();
    static boolean cont = true;
    static int menu;
    static ArrayList<AccCustomer> cstr = SaveFile.getCustomers();
    public static void prssEnterKeyToContinue()
    { 
    System.out.println("Press Enter key to continue...");
    Scanner s = new Scanner(System.in);
    s.nextLine();
    }
    //todo tambahkan pesanan
    public static void Pagerumah() throws IOException{
        while (cont){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Manajemen Rumah");
            System.out.println("1. lihat");
            System.out.println("2. tambah");
            System.out.println("3. ubah");
            System.out.println("4. delete");
            System.out.println("5. Kembali");
            System.out.print("Menu : ");
            switch(menu){
                case 1:
                    crud.lihat();
                    break;
                case 2:
                    crud.create();
                    break;
                case 3:
                    crud.update();
                    break;
                case 4:
                    crud.delete();
                    break;
                case 5:
                    cont = false;
                    break;
                default :
                    System.out.println("Invalid input!!");
                    break;  
            }
        }
    }
    public static void pagepesanAdmin() throws IOException{
        while(cont){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Manajemen Pesanan");
            System.out.println("1. lihat Pesanan");
            System.out.println("2. Konfirmasi pesanan");
            System.out.println("3. hapus Pesanan");
            System.out.print("Menu : ");
            menu = Integer.parseInt(br.readLine());
            switch (menu) {
                case 1 :
                    psn.lihatseluruhpesan();
                    prssEnterKeyToContinue();
                    break;
                case 2 :
                    psn.konfirmasipesan();
                    prssEnterKeyToContinue();
                case 3 :
                    psn.hpspesanadmin();
                    prssEnterKeyToContinue();
                default:
                    break;
            }
        }
    }
    public static void showpageAdmin() throws IOException{
        while (cont){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Selamat Datang Di Admin");
            System.out.println("1. Manajemen Rumah");
            System.out.println("2. Manajemen Pesanan");
            System.out.println("3. Log Out");
            System.out.print("Menu : ");
            int menu = Integer.parseInt(br.readLine());
            switch(menu){
                case 1:
                    Pagerumah();
                    prssEnterKeyToContinue();
                    break;
                case 2:
                    pagepesanAdmin();
                    prssEnterKeyToContinue();
                    break;
                case 3:
                    System.out.println("Log out Berhasil!!");
                    prssEnterKeyToContinue();
                    cont = false;
                    break;
            default:
                break;
            }
        }
    }
    public static void showpageuser(String usrnm) throws IOException{
        if(cstr.isEmpty() == false){
            for(int i = 0; i < cstr.size(); i++){
                AccCustomer customer = cstr.get(i);
                int index = cstr.indexOf(customer);
                int idcus = customer.getIdcustomer();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Selamat Datang "+ usrnm);
                System.out.println("1. Lihat data Rumah");
                System.out.println("2. pesan Rumah");
                System.out.println("3. Lihat Pesan");
                System.out.println("4. Lihat Data Diri");
                System.out.println("5. Log out");
                int menu = Integer.parseInt(br.readLine());
                switch(menu){
                    case 1:
                        crud.lihat();
                        prssEnterKeyToContinue();
                        break;
                    case 2:
                        System.out.println("Nomor Rumah Yang ingin dipesan : ");
                        int cari = Integer.parseInt(br.readLine());
                        psn.tambahpesan(usrnm, idcus,cari);
                        prssEnterKeyToContinue();
                        break;
                    case 3:
                        psn.lihatpesancustomer(idcus, usrnm, cstr,index);
                        prssEnterKeyToContinue();
                        break;
                    case 4:
                        psn.lihatdatadiri(usrnm, cstr,index);
                        break;
                    case 5:
                        cont =false;
                        break;
                    default:
                        System.out.println("Invalid Input");

                }
            }
        }else{
            System.out.println("Anda Harus Login Dulu");
            prssEnterKeyToContinue();
        }
    }
    public static void login() throws IOException{
        System.out.print("Masukkan username : ");
        String username = br.readLine();
        System.out.print("Masukkan Password : ");
        String Password = br.readLine();
        SaveFile.getUser(username);
        SaveFile.getCustomers();
        if(username.equals("Admin") && Password.equals("admin123")){
            
            save.loginAdmin(username, Password);
            if (save.loginAdmin(username, Password) == true) {
                System.out.println("Login successful!");
                prssEnterKeyToContinue();
                showpageAdmin();
            } else {
                System.out.println("Password Dan Username Salah");
                prssEnterKeyToContinue();
            }
        }else{
            SaveFile.loginCustomer(username, Password);
            if (SaveFile.loginCustomer(username, Password) == true) {
                System.out.println("Login successful!");
                prssEnterKeyToContinue();
                showpageuser(username);
            } else {
                System.out.println("Password Dan Username Salah");
                prssEnterKeyToContinue();
            }
        }
    }
    public static void register() throws IOException{
        int id = SaveFile.count() + 1;
        System.out.print("Masukkan username : ");
        String username = br.readLine();
        System.out.print("Masukkan Password : ");
        String Password = br.readLine();
        System.out.print("Masukkan Nama : ");
        String Nama = br.readLine();
        System.out.print("Masukkan Email : ");
        String Email = br.readLine();
        System.out.print("Masukkan Umur : ");
        int umur = Integer.parseInt(br.readLine());
        System.out.print("Masukkan Jenis Kelamin : ");
        String JenisKelamin = br.readLine();
        System.out.print("Masukkan Alamat : ");
        String Alamat = br.readLine();
        save.registerCustomer(username, Password, id, umur, Nama, JenisKelamin, Alamat, Email);
    }
    
    public static void main(String[] args) throws IOException {
       
        while(true){
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
        System.out.println("Selamat Datang di Aplikasi Pemesanan Rumah");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. exit");
        System.out.print("Menu : ");
        int menu = Integer.parseInt(br.readLine());
        switch (menu) {
            case 1:
                login();
                break;
            case 2:
                register();
                prssEnterKeyToContinue();
                break;
            case 3:
                System.exit(0);
            default:
                break;
        }
    }
}
}
