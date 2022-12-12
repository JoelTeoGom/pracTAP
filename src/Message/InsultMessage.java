package Message;

import HelloWorld.ActorProxy;

public class InsultMessage extends Message{
    public InsultMessage(ActorProxy from, String message) {
        super(from, message);
    }
}
