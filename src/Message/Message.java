package Message;

import Estructura.Iactor;

public class Message {
    private Iactor from;  //PROXY
    private String message;   //texto


    public Message(Iactor from, String message) {
        this.from = from;
        this.message = message;
    }

    public Iactor getFrom() {
        return from;
    }

    public void setFrom(Iactor from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
