package otros;

import Estructura.*;
import HelloWorld.*;
import Message.Message;


public class RingActor extends Actor {
    private ActorProxy next = null;

    public RingActor(){
        super();
    }

    @Override
    public void process(Message message) throws InterruptedException {
        System.out.println(message.getMessage());
        if(message.getFrom().getSourceActor().equals(this)){
            System.out.println("Fin de la vuelta "+ Integer.parseInt(message.getMessage()));
        }else
            next.send(message);

        if(Integer.parseInt(message.getMessage()) == 100){
            ActorContext.getInstance().getActorThreadHashMap().get(this).stop();    //No hay manera limpia de parar un thread
        }

    }

    public ActorProxy getNext() {
        return next;
    }

    public void setNext(ActorProxy next) {
        this.next = next;
    }
}