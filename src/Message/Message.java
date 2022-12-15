package Message;

import Estructura.ActorProxy;
import Estructura.Iactor;

public class Message {
    protected ActorProxy from;  //PROXY
    protected String message;   //texto


    public Message(ActorProxy from, String message) {
        this.from = from;
        this.message = message;
    }

    public ActorProxy getFrom() {
        return from;
    }

    public void setFrom(ActorProxy from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
