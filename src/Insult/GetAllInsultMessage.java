package Insult;

import Estructura.ActorProxy;
import Estructura.Iactor;
import Message.Message;

public class GetAllInsultMessage extends Message {
    public GetAllInsultMessage(ActorProxy from) {
        super(from, "");
    }
}
