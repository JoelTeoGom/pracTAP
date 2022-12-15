package Insult;

import Estructura.ActorProxy;
import Estructura.Iactor;
import Message.Message;

public class AddInsultMessage extends Message {

    public AddInsultMessage(ActorProxy from, String message) {
        super(from, message);
    }
}
