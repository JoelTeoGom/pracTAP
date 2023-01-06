package Insult;

import Estructura.ActorProxy;
import Estructura.Iactor;
import Message.Message;

public class AddInsultMessage extends Message {
    /**
     * constructor AddInsultMessage
     * @param from
     * @param message
     */

    public AddInsultMessage(ActorProxy from, String message) {
        super(from, message);
    }
}
