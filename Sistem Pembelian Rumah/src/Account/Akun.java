package Account;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public abstract class Akun implements interfaceAkun {
    private String username;
    private String password;
    public Akun(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return username;
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void register(String username, String password) {
        try {
            FileWriter writer = new FileWriter("data/users.txt", true);
            writer.write(this.getClass().getSimpleName() + "," + username + "," + password + "\n");
            writer.close();
            System.out.println("Successfully registered to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while registering to file.");
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
