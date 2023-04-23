package Rumah;

import java.io.IOException;
import java.util.ArrayList;

public interface InterfaceRumah {
    ArrayList<Rumah> getList();
    public void create() throws IOException;
    public void update() throws IOException;
    public void delete() throws IOException;
    public void lihat() throws IOException;

}
