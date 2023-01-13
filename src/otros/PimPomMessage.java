package otros;

import Estructura.ActorProxy;
import Message.Message;

public class PimPomMessage extends Message {
    /**
     * constructor message
     *
     * @param from
     * @param message
     */
    public PimPomMessage(ActorProxy from, String message) {
        super(from, message);
    }
}
