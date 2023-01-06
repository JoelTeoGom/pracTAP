package Insult;

import Estructura.ActorProxy;
import Estructura.Iactor;
import Message.Message;

public class GetAllInsultMessage extends Message {
    /**
     * constructor GetAllInsultMessage
     * @param from
     */
    public GetAllInsultMessage(ActorProxy from) {
        super(from, "");
    }
}
