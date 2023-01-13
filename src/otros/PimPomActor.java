package otros;

import Estructura.Actor;
import Estructura.ActorContext;
import Estructura.ActorProxy;
import Message.Message;

public class PimPomActor extends Actor {

    public PimPomActor(){
        super();
    }

    @Override
    public void process(Message message) throws InterruptedException {
        if(message instanceof PimPomMessage){
            int contador = Integer.parseInt(message.getMessage());
            contador--;
            if(contador > 0){
                message.getFrom().send(new PimPomMessage(new ActorProxy(this), ""+contador));
                System.out.println(message.getFrom().getSourceActor()+""+contador);
            }else{
                System.out.println("FI CONTADOR "+message.getMessage());
            }
        }
    }

}
