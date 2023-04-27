package Account;

import java.io.FileWriter;
import java.io.IOException;

public class accAdmin extends Akun {
    public accAdmin(String username, String password) {
        super(username, password);
    }
    public boolean login(String user, String pass) {
        return this.getUsername().equals(user) && this.getPassword().equals(pass);
    }
    @Override
    public void registerusers(String username, String password){
        try{
            FileWriter writer = new FileWriter("data/users.txt");
            writer.write(this.getClass().getSimpleName() + "," + username + "," + password + "\n");
            writer.close();
            System.out.println("Successfully registered to file.");
            
            }catch(IOException e){
            System.out.println("An error occurred while registering to file.");
            e.printStackTrace();
        }
    }

}
