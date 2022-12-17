package Insult;

import Estructura.ActorProxy;
import Estructura.Iactor;
import Message.Message;

public class GetInsultMessage extends Message {

    public GetInsultMessage(ActorProxy from) {
        super(from, "");
    }
}
