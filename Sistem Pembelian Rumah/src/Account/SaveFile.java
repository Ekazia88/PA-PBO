package Account;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveFile {
    private static final String Dir = "data/users/";
    private static ArrayList<accAdmin> admins;
    private static ArrayList<AccCustomer> customers;
    public static int count() {
        return customers.size();
    }
    public SaveFile() {
        admins = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public void registerAdmin(String username, String password) {
        accAdmin admin = new accAdmin("admin", "admin123");
        admin.register(username, password);
        this.admins.add(admin);
    }

    public void registerCustomer(String username, String password,int idcustomer,int umur, String nama,String Jk,String Alamat,String email) {
        AccCustomer customer = new AccCustomer(username, password,idcustomer,umur,nama,Jk,Alamat,email);
        customer.register(username, password);
        this.customers.add(customer);
    }

    public boolean loginAdmin(String username, String password) {
        for (accAdmin admin : this.admins) {
            if (admin.login(username, password)) {
                return true;
            }
        }
        return false;
    }

    public boolean loginCustomer(String username, String password) {
        for (AccCustomer customer : customers) {
            if (customer.login(username, password)) {
                return true;
            }
        }
        return false;
    }

    public static void saveCustomersToFile(AccCustomer customer) {
        try {
            FileWriter writer = new FileWriter(Dir+ customer.getUsername() +".txt");
            for (AccCustomer cstr : customers) {
                writer.write(cstr.getUsername()+".\n"+ cstr.getPassword() + ".\n" + cstr.getNama()+".\n"+ cstr.getIdcustomer()+".\n"+cstr.getUmur() + ".\n" +cstr.getAlamat()+".\n"+cstr.getEmail());
            }
            writer.close();
            System.out.println("Successfully saved File.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving customers to file.");
            e.printStackTrace();
        }
    }
public static Akun getUser(String username) {
    try {
        String filename = Dir + username + ".txt";
        FileReader reader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        String userType;
        String password;
        String[] tokens = line.split(".");   
        userType = tokens[1];
        password = tokens[2];
        Akun user = null;
        if (userType.equals("Admin")) {
            user = new accAdmin(username, password);
        } else if (userType.equals("Customer")) {
            String email = bufferedReader.readLine();
            int idcustomer = Integer.parseInt(bufferedReader.readLine());
            int umur = Integer.parseInt(bufferedReader.readLine());
            String nama = bufferedReader.readLine();
            String Jk = bufferedReader.readLine();
            String Alamat = bufferedReader.readLine();
            user = new AccCustomer(username, password,idcustomer,umur,nama,Jk,Alamat,email);
        }
           
        bufferedReader.close();
        return user;
    } catch (FileNotFoundException e) {
        System.out.println("User file not found: " + username);
        return null;
    } catch (IOException e) {
        System.out.println("An error occurred while reading user file: " + username);
        e.printStackTrace();
        return null;
    }
}
}
