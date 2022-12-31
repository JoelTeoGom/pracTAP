package Decorator;

import Estructura.Actor;
import Message.Message;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class ActorDecorator implements ActorInterface{
    protected Actor actor;

    public ActorDecorator(Actor actor){
        this.actor =actor;
    }


    public BlockingQueue<Message> getQueue() {
        return this.actor.getQueue();
    }

    @Override
    public void setQueue(BlockingQueue<Message> queue) {
        this.actor.setQueue(queue);
    }


    @Override
    public Boolean getExit() {
        return this.actor.getExit();
    }

    @Override
    public void setExit(Boolean exit) {
        this.actor.setExit(exit);
    }



    @Override
    public void process(Message m) throws InterruptedException {
        this.actor.process(m);
    }

    @Override
    public void send(Message message) throws InterruptedException {
    this.actor.send(message);
    }
}
