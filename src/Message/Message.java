package Message;

import Estructura.ActorProxy;
import Estructura.Iactor;

public class Message {
    protected ActorProxy from;  //PROXY
    protected String message;   //texto

    /**
     * constructor message
     * @param from
     * @param message
     */
    public Message(ActorProxy from, String message) {
        this.from = from;
        this.message = message;
    }

    /**
     * getter que retorna de quin actorProxy prove el missatge
     * @return ActorProxy
     */
    public ActorProxy getFrom() {
        return from;
    }

    /**
     * setter del quin actorProxy prove el misstage
     * @param from
     */
    public void setFrom(ActorProxy from) {
        this.from = from;
    }

    /**
     * getter del missatge
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * setter del missatge
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
