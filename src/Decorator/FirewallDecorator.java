package Decorator;

import Estructura.Actor;
import Estructura.ActorContext;
import Message.Message;

/*
    The FirewallDecorator will only let process messages whose sender is a valid Actor registered in the
    ActorContext. It will stop all messages coming from a Proxy.
 */
public class FirewallDecorator extends ActorDecorator {
    private ActorContext a;
    public FirewallDecorator (Actor actor){
        super(actor);
    }

    public void process(Message m) throws InterruptedException {
      if(actor.getState().equals("activo") && a.isThere(actor)) this.actor.process(m);
    }

}
