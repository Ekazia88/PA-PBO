package Pesan;
//Todo  buat Interface Pesan

import java.io.IOException;
import java.util.ArrayList;

public interface interfacePesan {
    ArrayList<Pesan> getList();
    public void tambahpesan(String username,int idcus,int cari) throws IOException;
    public void ubahpesan() throws IOException;
    public void lihatseluruhpesan() throws IOException;
    public void lihatpesancustomer(int idcus,String username) throws IOException;
    public void hpspesan(int idcus) throws IOException;
    public void lihatdatadiri(String Username) throws IOException;
}
