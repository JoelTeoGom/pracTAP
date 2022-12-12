package Message;

import HelloWorld.ActorProxy;

public class HelloWorldMessage extends Message{
    public HelloWorldMessage(ActorProxy from, String message) {
        super(from, message);
    }
}
