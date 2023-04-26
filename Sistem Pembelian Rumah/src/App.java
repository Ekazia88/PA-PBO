import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Account.*;
import Pesan.Adminpesan;
import Pesan.userpensan;
import Rumah.*;
public class App {

    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    static SaveFile save = new SaveFile();
    static crud crud = new crud();
    static userpensan psnuser = new userpensan();
    static Adminpesan psnAdmin = new Adminpesan(); 
    static boolean cont = true;
    static int menu;
    static ArrayList<AccCustomer> cstr = SaveFile.getCustomers();

    public static void cls() {
    try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (IOException | InterruptedException e) {
        // Handle the exception appropriately
        e.printStackTrace();
    }
    }
    public static void prssEnterKeyToContinue() throws IOException
    { 
    System.out.println("Press Enter key to continue...");
    String s = br.readLine();
    }
    public static void Pagerumah() throws IOException{
            crud.loadfile();
            crud.getList();
            cls();
            System.out.println("Manajemen Rumah");
            System.out.println("1. lihat");
            System.out.println("2. tambah");
            System.out.println("3. ubah");
            System.out.println("4. delete");
            System.out.println("5. Kembali");
            System.out.print("Menu : ");
            try{
                menu =Integer.parseInt(br.readLine());
                switch(menu){
                    case 1:
                        crud.lihat();
                        prssEnterKeyToContinue();
                        Pagerumah();
                        break;
                    case 2:
                        crud.create();
                        prssEnterKeyToContinue();
                        Pagerumah();
                        break;
                    case 3:
                        crud.update();
                        prssEnterKeyToContinue();
                        Pagerumah();
                        break;
                    case 4:
                        crud.delete();
                        prssEnterKeyToContinue();
                        Pagerumah();
                        break;
                    case 5:
                        cont = false;
                        showpageAdmin();
                        break;
                    default :
                        System.out.println("Invalid input!!");
                        break;  
                }
            } catch (NumberFormatException e) {
                System.out.println("Error Input!! Harus Berupa Angka!!");
                prssEnterKeyToContinue();
                Pagerumah();
            } catch (IOException e) {
                System.out.println("An error occurred while reading input.");
            }
        }
    public static void pagepesanAdmin() throws IOException{
            cls();
            psnAdmin.loadfile();
            psnAdmin.getList();
            System.out.println("Manajemen Pesanan");
            System.out.println("1. lihat Pesanan");
            System.out.println("2. Konfirmasi pesanan");
            System.out.println("3. hapus Pesanan");
            System.out.println("4. kembali");
            System.out.print("Menu : ");
            try{
            menu = Integer.parseInt(br.readLine());
            cls();
            switch (menu) {
                case 1 :
                    psnAdmin.lihatseluruhpesan();
                    prssEnterKeyToContinue();
                    pagepesanAdmin();
                    break;
                case 2 :
                    psnAdmin.konfirmasipesan();
                    prssEnterKeyToContinue();
                    pagepesanAdmin();
                    break;
                case 3 :
                    psnAdmin.hpspesanadmin();
                    prssEnterKeyToContinue();
                    pagepesanAdmin();
                    break;
                case 4:
                    showpageAdmin();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error Input!! Harus Berupa Angka!!");
            prssEnterKeyToContinue();
            pagepesanAdmin();
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.");
        }
    }
    public static void showpageAdmin() throws IOException{
            cls();
            System.out.println("Selamat Datang Di Admin");
            System.out.println("1. Manajemen Rumah");
            System.out.println("2. Manajemen Pesanan");
            System.out.println("3. Log Out");
            System.out.print("Menu : ");
            try{
                menu = Integer.parseInt(br.readLine());
                switch (menu){
                    case 1:
                        Pagerumah();
                        prssEnterKeyToContinue();
                        break;
                    case 2:
                        cls();
                        pagepesanAdmin();
                        prssEnterKeyToContinue();
                        break;
                    case 3:
                        cont =false;
                        System.out.println("Anda berhasil Log out!!");
                        prssEnterKeyToContinue();
                        mainMenu();
                        break;
                    default:
                        System.out.println("Invalid Input ");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error Input!! Harus Berupa Angka!!");
                prssEnterKeyToContinue();
                showpageAdmin();
            } catch (IOException e) {
                System.out.println("An error occurred while reading input.");
            }
        }
    public static void showpageuser(String usrnm) throws IOException{
        if(cstr.isEmpty() == false){
            for(int i = 0; i < cstr.size(); i++){
                AccCustomer customer = cstr.get(i);
                int index = cstr.indexOf(customer);
                int idcus = customer.getIdcustomer();
                crud.loadfile();
                crud.getList();
                psnuser.loadfile();
                psnuser.getList();
                cls();
                System.out.println("Selamat Datang "+ usrnm);
                System.out.println("1. Lihat data Rumah");
                System.out.println("2. pesan Rumah");
                System.out.println("3. Lihat Pesan");
                System.out.println("4. Lihat Data Diri");
                System.out.println("5. Hapus Pesanan");
                System.out.println("6. Log out");
                System.out.print("Menu : ");  
                try{
                    int menu = Integer.parseInt(br.readLine());
                    cls();
                    switch(menu){
                        case 1:
                            crud.lihat();
                            prssEnterKeyToContinue();
                            showpageuser(usrnm);
                            break;
                        case 2:
                            psnuser.tambahpesan(usrnm, idcus);
                            prssEnterKeyToContinue();
                            showpageuser(usrnm);
                            break;
                        case 3:
                            psnuser.lihatpesancustomer(idcus, cstr,index);
                            prssEnterKeyToContinue();
                            showpageuser(usrnm);
                            break;
                        case 4:
                            psnuser.lihatdatadiri(usrnm, cstr,index);
                            prssEnterKeyToContinue();
                            showpageuser(usrnm);
                            break;
                        case 5:
                            psnuser.lihatpesancustomer(idcus, cstr, index);
                            psnuser.hpspesanuser(idcus);
                            prssEnterKeyToContinue();
                            showpageuser(usrnm);
                        case 6:
                            cls();
                            System.out.println("Anda berhasil Log out!!");
                            cstr.clear();
                            prssEnterKeyToContinue();
                            mainMenu();
                            break;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error Input!! Harus Berupa Angka!!");
                    prssEnterKeyToContinue();
                    showpageuser(usrnm);
                } catch (IOException e) {
                    System.out.println("An error occurred while reading input.");
                }
            }
        }else{
            System.out.println("Anda Harus Login Dulu");
            prssEnterKeyToContinue();
        }
    }
    public static void login() throws IOException{
        System.out.println("-----Login------");
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
                cont =true;
                showpageAdmin();
            } else {
                System.out.println("Password Dan Username Salah");
                prssEnterKeyToContinue();
                mainMenu();
            }
        }else{
            SaveFile.loginCustomer(username, Password);
            if (SaveFile.loginCustomer(username, Password) == true) {
                System.out.println("Login successful!");
                prssEnterKeyToContinue();
                cont = true;
                showpageuser(username);
            } else {
                System.out.println("Password Dan Username Salah");
                prssEnterKeyToContinue();
                mainMenu();
            }
        }
    }
    public static void register() throws IOException{
        int id = SaveFile.count();
        System.out.println("-----Register------");
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
    public static void mainMenu() throws IOException{
        cls();
        System.out.println("Selamat Datang di Aplikasi Pemesanan Rumah");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. exit");
        System.out.print("Menu : ");
        try {
            int menu = Integer.parseInt(br.readLine());
        switch (menu) {
            case 1:
                cls();
                login();
                break;
            case 2:
                cls();
                register();
                prssEnterKeyToContinue();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Invalid Input");
                mainMenu();
                break;
        }
        } catch (NumberFormatException e) {
            System.out.println("Error Input!! Harus Berupa Angka!!");
            prssEnterKeyToContinue();
            mainMenu();
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.");
        }
    }
    public static void main(String[] args) throws IOException {
        mainMenu();
    }
}
