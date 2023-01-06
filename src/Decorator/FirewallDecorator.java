package Decorator;

import Estructura.Actor;
import Estructura.ActorContext;
import Message.Message;

/**
 * The FirewallDecorator will only let process messages whose sender is a valid Actor registered in the
 *     ActorContext. It will stop all messages coming from a Proxy.
 */
public class FirewallDecorator extends ActorDecorator {

    /**
     * Constructor
     * @param actor
     */
    public FirewallDecorator (Actor actor){
        super(actor);

    }


    /**
     * Aquest metode deixarà processar el missatge si aquest es un actor del actorContext.
     * @param m
     * @throws InterruptedException
     */
    public void process(Message m) throws InterruptedException {

        if (ActorContext.getInstance().getActorLibrary().containsValue(m.getFrom().getSourceActor())) {
            System.out.println("trobat");
            actor.process(m);
        } else {
            System.out.println("No trobat");
        }
    }
}