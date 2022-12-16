package Decorator;

import Estructura.Actor;
import Message.Message;

import java.util.Queue;

/*
    Create a LambdaFirewallDecorator that accepts closures to filter the messages than can be received
    using an AddClosureMessage.
 */
public class EncryptionDecorator extends Actor {

    public EncryptionDecorator(Actor actor) {
        super(actor);
    }

    public void send(Message message){
    }

    protected void setExit(){super.setExit();}
    public Queue<Message> getQueue() {return super.getQueue();}

    public void setQueue(Queue<Message> queue) {super.setQueue();}

    public String getState() {return super.getState();}

    public void setState(String state) {super.setState();}

    public Thread getThread() {return super.getThread();}

    public void setThread(Thread thread){super.setThread();}


    public Boolean getExit() {return super.getExit();}

    public void setExit(Boolean exit) {super.setExit();}
}
