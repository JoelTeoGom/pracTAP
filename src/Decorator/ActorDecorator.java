package Decorator;
/**
 *  ActorDecorator class extends from Actor class
 */

import Estructura.Actor;
import Message.Message;

import java.util.concurrent.BlockingQueue;

public class ActorDecorator extends Actor {
    protected Actor actor;

    /**
     * Constructor
     * @param actor
     */
    public ActorDecorator(Actor actor){
        this.actor =actor;
    }

    /**
     * Gets the queue
     * @return BlockingQueue<Message>
     */
    public BlockingQueue<Message> getQueue() {
        return this.actor.getQueue();
    }

    /**
     * Sets the queue
     * @param queue
     */
    @Override
    public void setQueue(BlockingQueue<Message> queue) {
        this.actor.setQueue(queue);
    }

    /**
     *  gets the boolean exit
     * @return boolean exit
     */
    @Override
    public Boolean getExit() {
        return this.actor.getExit();
    }

    /**
     *  Sets the boolean exit
     * @param exit
     */
    @Override
    public void setExit(Boolean exit) {
        this.actor.setExit(exit);
    }

    /**
     *  Process the message
     * @param m misstage
     * @throws InterruptedException
     */

    @Override
    public void process(Message m) throws InterruptedException {
        this.actor.process(m);
    }

    /**
     * sends the message
     * @param message misstage
     * @throws InterruptedException
     *
     */
    @Override
    public void send(Message message) throws InterruptedException {
        this.actor.send(message);
    }
}
