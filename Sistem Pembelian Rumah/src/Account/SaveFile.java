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
    public static int count(){
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("data/users.txt"))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public SaveFile() {
        admins = new ArrayList<>();
        customers = new ArrayList<>();
    }
    public void nullcstr(){
        customers.clear();
    }
    public static ArrayList<AccCustomer> getCustomers() {
        return customers;
    }
    public static ArrayList<accAdmin> getAdmins() {
        return admins;
    }
    public static void setAdmins(ArrayList<accAdmin> admins) {
        SaveFile.admins = admins;
    }
    public static void setCustomers(ArrayList<AccCustomer> customers) {
        SaveFile.customers = customers;
    }
    public void registerCustomer(String username, String password,int idcustomer,int umur, String nama,String Jk,String Alamat,String email) {
        AccCustomer customer = new AccCustomer(username, password,idcustomer,umur,nama,Jk,Alamat,email);
        customer.register(username, password,idcustomer,umur,nama,Jk,Alamat,email);
        customer.registerusers(username, password);
        customers.add(customer);;
    }
    public boolean loginAdmin(String username, String password) {
        for (accAdmin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean loginCustomer(String username, String password) {
        for (AccCustomer cstr : customers) {
            if (cstr.getUsername().equals(username) && cstr.getPassword().equals(password)) {
                return true;
            }
        }
        
        return false;
        }

    public static void saveCustomersToFile(AccCustomer customer) {
        try {
            FileWriter writer = new FileWriter(Dir+ customer.getUsername() +".txt");
            writer.write(customer.getUsername()+"\n"+ customer.getPassword() + "\n" + customer.getNama()+"\n"+ customer.getIdcustomer()+"\n"+customer.getUmur() + "\n" +customer.getAlamat()+"\n"+customer.getJenisKelamin()+"\n"+customer.getEmail()+"\n");
            writer.close();
            System.out.println("Successfully saved File.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving customers to file.");
            e.printStackTrace();
        }
    }
public static Akun getUser(String username) {
    try {
        String filename = "data/users.txt";
        FileReader reader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        String usernames;
        String userType;
        String password;
        Akun ak = null;
        while((line = bufferedReader.readLine())!= null){
            String[] tokens = line.split(",");
            userType = tokens[0];
            usernames = tokens[1]; 
            password = tokens[2];   
            accAdmin admin = null;
            AccCustomer cstr = null;
            if (userType.equals("accAdmin")&& usernames.equals(username)) { 
                admin = new accAdmin(username, password);
                admins.add(admin);
            } else if (userType.equals("AccCustomer") && usernames.equals(username)) {
                String filename2 = Dir+username+".txt";
                FileReader reader2 = new FileReader(filename2);
                BufferedReader bufferedReader2 = new BufferedReader(reader2);
                String Username = bufferedReader2.readLine();
                String passwordc = bufferedReader2.readLine();
                String nama = bufferedReader2.readLine();
                int idcustomer = Integer.parseInt(bufferedReader2.readLine());
                int umur = Integer.parseInt(bufferedReader2.readLine());
                String Alamat = bufferedReader2.readLine();
                String Jk = bufferedReader2.readLine();
                String email = bufferedReader2.readLine();
                cstr = new AccCustomer(Username, passwordc,idcustomer,umur,nama,Jk,Alamat,email);
                customers.add(cstr);
                bufferedReader2.close();
            }
        }
        bufferedReader.close();
        return ak;
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
