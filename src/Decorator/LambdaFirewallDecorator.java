package Decorator;

import Estructura.Actor;

import Message.Message;

import java.util.function.Predicate;

/**
 * Create a LambdaFirewallDecorator that accepts closures to filter the messages than can be received
 * using an AddClosureMessage.
 */
public class LambdaFirewallDecorator extends ActorDecorator {
    /**
     * constructor
     * @param actor
     */
    public LambdaFirewallDecorator(Actor actor) {
        super(actor);
    }


    Predicate<String> checker = a -> a.startsWith("h");

    /**
     * metode que utilitza el predicate declarat anteriorment i si el misstage comença pel char 'h' no el enviara
     * @param message
     * @throws InterruptedException
     */
    @Override
    public void send(Message message) throws InterruptedException {

        if(checker.test(message.getMessage())){
            System.out.println("No s'ha enviat missatge perque comença per [h] ");
        }else{
            System.out.println("missatge enviat");
            super.send(message);
        }

    }
}


