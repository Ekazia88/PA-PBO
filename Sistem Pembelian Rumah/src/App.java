import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Account.*;
import Rumah.*;
public class App {

    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    static SaveFile save = new SaveFile();
    static crud crud = new crud();
    public static void showpageAdmin() throws IOException{
        while (true){
            System.out.println("Selamat Datang Di Admin");
            System.out.println("1. lihat");
            System.out.println("2. tambah");
            System.out.println("3. ubah");
            System.out.println("4. delete");
            System.out.print("Menu : ");
            int menu = Integer.parseInt(br.readLine());
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
            default:
                break;
            }
        }
    }
    public static void login() throws IOException{
        System.out.print("Masukkan username : ");
        String username = br.readLine();
        System.out.print("Masukkan Password : ");
        String Password = br.readLine();
        SaveFile.getUser(username);
        if(username.equals("Admin") && Password.equals("admin123")){
            save.loginAdmin("Admin", "admin123");
            if (save.loginAdmin(username, Password) == true) {
                System.out.println("Login successful!");
                showpageAdmin();
            } else {
                System.out.println("Password Dan Username Salah");
            }
        }else{
            save.loginCustomer(username, Password);
            if (save.loginCustomer(username, Password) == true) {
                System.out.println("Login successful!");
                
            } else {
                System.out.println("Password Dan Username Salah");
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
        System.out.println("Selamat Datang di wkwkw");
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
                break;
            case 3:
                System.exit(0);
            default:
                break;
        }
    }
}
}
