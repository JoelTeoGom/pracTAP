package HelloWorld;

import Estructura.ActorProxy;
import Message.Message;

public class HelloWorldMessage extends Message {
    /**
     * constructor HelloWorldMessage
     * @param from
     * @param message
     */
    public HelloWorldMessage(ActorProxy from, String message) {
        super(from, message);
    }
}
