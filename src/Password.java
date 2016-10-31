public class Password {
    private String passcode;
    private long key;

    public Password(String password) {
        setPass(password);
        setKey(calcKey());
    }

    private void setPass(String password) {
        this.passcode = password;
    }

    private String getPass() {
        return this.passcode;
    }

    private long calcKey() {
        long key;
        key = 0;
        getPass();
        return key;
    }

    private void setKey(long val) {
        this.key = val;
    }

    private long getKey() {
        return this.key;
    }
}
