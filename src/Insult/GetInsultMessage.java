package Insult;

import Estructura.Iactor;
import Message.Message;

public class GetInsultMessage extends Message {


    public GetInsultMessage(Iactor from, String message) {
        super(from, message);
    }
}
