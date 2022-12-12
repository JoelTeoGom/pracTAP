package Message;

import HelloWorld.ActorProxy;

public class Message {
    private ActorProxy from;  //PROXY
    private String message;   //texto


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
