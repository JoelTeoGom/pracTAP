package Decorator;

import Estructura.Actor;
import Message.Message;

import java.util.Queue;

/*
    Create a LambdaFirewallDecorator that accepts closures to filter the messages than can be received
    using an AddClosureMessage.
 */
public class EncryptionDecorator extends ActorDecorator {
    public EncryptionDecorator (Actor actor){
        super(actor);
    }

}
