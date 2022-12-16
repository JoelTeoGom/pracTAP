package Decorator;

import Estructura.Actor;
import Message.Message;

import java.util.Queue;

/*
    The FirewallDecorator will only let process messages whose sender is a valid Actor registered in the
    ActorContext. It will stop all messages coming from a Proxy.
 */
public class FirewallDecorator extends Actor{

    public FirewallDecorator(Actor actor) {
        super(actor);
    }


    public void send(Message message){  //metodo a decorar, solo dejara procesar messages que esten en el hash de actorContext
    }

    protected void setExit(){super.setExit();}
    public Queue<Message> getQueue() {return super.getQueue();}

   // public void setQueue(Queue<Message> queue) {super.setQueue();}

    public String getState() {return super.getState();}

    //public void setState(String state) {super.setState();}

    public Thread getThread() {return super.getThread();}

   // public void setThread(Thread thread){super.setThread();}


    public Boolean getExit() {return super.getExit();}

    public void setExit(Boolean exit) {super.setExit();}
}
