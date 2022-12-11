package helloWorld;

public class Message {
    private Actor actorReference; //sera el actor que recibira el mensaje
    private String message;        //texto


    public Message(Actor actorReference, String message) {
        this.actorReference = actorReference;
        this.message = message;
    }

    public Actor getActorReference() {
        return actorReference;
    }

    public void setActorReference(Actor actorReference) {
        this.actorReference = actorReference;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
