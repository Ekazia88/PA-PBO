package Account;
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
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    public abstract void registerusers(String username, String password);

}
