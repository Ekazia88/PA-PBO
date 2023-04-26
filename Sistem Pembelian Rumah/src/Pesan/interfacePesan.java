package Pesan;

import java.util.ArrayList;

import Account.AccCustomer;
import Account.SaveFile;
import Rumah.crud;

interface interfacePesan {
    static crud cr = new crud();
    static ArrayList <AccCustomer> cstr= SaveFile.getCustomers();
    public ArrayList<Pesan> getList();
    public void loadfile();
}
