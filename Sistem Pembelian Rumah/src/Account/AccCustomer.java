package Account;

import java.io.FileWriter;
import java.io.IOException;

public class AccCustomer extends Akun {
    private int idcustomer;
    private int Umur;
    private String Nama;
    private String JenisKelamin;
    private String Alamat;
    private String Email;
    public AccCustomer(String username, String password,int idcustomer,int umur, String nama,String Jk,String Alamat,String Email ) {
        super(username, password);
        this.idcustomer = idcustomer;
        this.Umur = umur;
        this.Nama = nama;
        this.JenisKelamin = Jk;
        this.Alamat = Alamat;
        this.Email = Email;
    }
    public int getIdcustomer() {
        return idcustomer;
    }
    public int getUmur() {
        return Umur;
    }
    public String getAlamat() {
        return Alamat;
    }
    public String getJenisKelamin() {
        return JenisKelamin;
    }
    public String getNama() {
        return Nama;
    }
    public String getEmail() {
        return Email;
    }
    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }
    public void setAlamat(String alamat) {
        Alamat = alamat;
    }
    public void setNama(String nama) {
        Nama = nama;
    }
    public void setJenisKelamin(String jenisKelamin) {
        JenisKelamin = jenisKelamin;
    }
    public void setUmur(int umur) {
        Umur = umur;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public void register(String username, String password,int idcustomer,int Umur, String Nama, String JenisKelamin,String Alamat,String Email) {
        SaveFile.saveCustomersToFile(new AccCustomer(username, password, idcustomer, Umur, Nama, JenisKelamin, Alamat, Email));
    }
    public void registerusers(String username, String password){
        try{
            FileWriter writer = new FileWriter("data/users.txt",true);
            writer.write(this.getClass().getSimpleName() + "," + username + "," + password + "\n");
            writer.close();
            System.out.println("Successfully registered to file.");
            
            }catch(IOException e){
            System.out.println("An error occurred while registering to file.");
            e.printStackTrace();
        }
    }
}
