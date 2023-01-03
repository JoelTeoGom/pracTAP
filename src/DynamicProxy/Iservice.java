package DynamicProxy;

public interface Iservice {
    public void addInsult(String insult) throws InterruptedException;
    public String getAllInsult() throws InterruptedException;
    public String getInsult();
}
