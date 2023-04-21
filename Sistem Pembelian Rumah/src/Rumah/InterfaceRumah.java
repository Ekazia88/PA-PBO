package Rumah;

import java.io.IOException;
import java.util.ArrayList;

public interface InterfaceRumah {
    ArrayList<Rumah> rumahs = new ArrayList<>();
    public void create(Rumah rumahs) throws IOException;
    public void update(Rumah rumahs) throws IOException;
    public void delete(Rumah rumahs) throws IOException;
    public void lihat(Rumah rumahs) throws IOException;


}
