package otros;

import Estructura.Actor;
import Message.Message;
import Insult.*;

public class InsultActor extends Actor {
    public InsultActor() {
        super();
    }


    void process(Message message) {
        switch (message){
            case AddInsultMessage m1:

                break;
            case GetInsultMessage m1:
                break;
            case InsultMessage m1:
                break;
            default:
                break;
        }
    }


}
