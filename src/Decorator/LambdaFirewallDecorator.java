package Decorator;

import Estructura.Actor;

import Message.Message;

import java.util.function.Predicate;

/*
Create a LambdaFirewallDecorator that accepts closures to filter the messages than can be received
using an AddClosureMessage.
 */
public class LambdaFirewallDecorator extends ActorDecorator {

    public LambdaFirewallDecorator(Actor actor) {super(actor);}

    Predicate<String> checker = a -> a.startsWith("!");

    @Override
    public void send(Message message) throws InterruptedException {

        if(checker.test(message.getMessage())){
            System.out.println("No s'ha enviat missatge perque comença per [!] ");
        }else{
            super.send(message);
        }

    }
}


