package Message;

import Estructura.ActorProxy;

public class QuitMessage extends Message{
    /**
     * constructor QuitMessage
     * @param from
     * @param message
     */
    public QuitMessage(ActorProxy from, String message) {
        super(from, message);
    }
}
