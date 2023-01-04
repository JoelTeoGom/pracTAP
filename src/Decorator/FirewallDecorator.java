package Decorator;

import Estructura.Actor;
import Estructura.ActorContext;
import Message.Message;

/*
    The FirewallDecorator will only let process messages whose sender is a valid Actor registered in the
    ActorContext. It will stop all messages coming from a Proxy.
 */
public class FirewallDecorator extends ActorDecorator {


    public FirewallDecorator (Actor actor){
        super(actor);

    }
    /*
    @Override
    public void process(Message m) throws InterruptedException {
      if((ActorContext.getInstance().getActorLibrary().containsValue(actor)==false)){

          System.out.println("Missatge no enviat ja que no esta registrat al ActorContext");
      }else{
          System.out.println("funciona");
          actor.process(m);
      }
    }
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