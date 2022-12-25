package Decorator;

import Estructura.Actor;
import Message.Message;

import java.util.Queue;

public class ActorDecorator implements ActorInterface{
    protected Actor actor;

    public ActorDecorator(Actor actor){
        this.actor =actor;
    }


    public void run() {
    this.actor.run();
    }


    public void setExit() {
    this.actor.setExit();
    }


    public Queue<Message> getQueue() {
        return this.actor.getQueue();
    }


    public String getState() {
        return actor.getState();
    }


    public void setState(String state) {
        this.actor.setState(state);
    }


    public Thread getThread() {
        return this.actor.getThread();
    }

    @Override
    public void setThread(Thread thread) {
        this.actor.setThread(thread);
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
    public void setQueue(Queue<Message> queue) {
        this.actor.setQueue(queue);
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
