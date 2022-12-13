package Message;

import Estructura.ActorProxy;

public class QuitMessage extends Message{
    public QuitMessage(ActorProxy from, String message) {
        super(from, message);
    }
}
