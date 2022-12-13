package HelloWorld;

import Estructura.ActorProxy;
import Message.Message;

public class HelloWorldMessage extends Message {
    public HelloWorldMessage(ActorProxy from, String message) {
        super(from, message);
    }
}
