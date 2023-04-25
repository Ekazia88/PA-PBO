package Pesan;

import java.io.IOException;
import java.util.ArrayList;

import Account.AccCustomer;

public interface interfacePesan {
    public ArrayList<Pesan> getList();
    public void tambahpesan(String username,int idcus) throws IOException;
    public void ubahpesan() throws IOException;
    public void lihatseluruhpesan() throws IOException;
    public void lihatpesancustomer(int idcus,String username,ArrayList<AccCustomer> cstr,int index) throws IOException;
    public void hpspesanuser(int idcus) throws IOException;
    public void lihatdatadiri(String Username,ArrayList<AccCustomer> cstr,int index) throws IOException;
    public void konfirmasipesan()throws IOException;
    public void hpspesanadmin() throws IOException;
}
