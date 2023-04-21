package Account;
interface interfaceAkun {
    void register(String username, String password);
    boolean login(String username, String password);
    String getUsername();
    String getPassword();
}