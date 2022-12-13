package Insult;

import Estructura.Iactor;
import Message.Message;

public class AddInsultMessage extends Message {

    public AddInsultMessage(Iactor from, String message) {
        super(from, message);
    }
}
