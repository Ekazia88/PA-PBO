package Account;
interface interfaceAkun {
    void registerusers(String username, String password);
    boolean login(String username, String password);
    String getUsername();
    String getPassword();
}