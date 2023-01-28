package Estructura;


import Message.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ActorProxy implements Iactor{

    private BlockingQueue<Message> queue;
    private final Actor sourceActor;

    /**
     * Constructor
     * @param sourceActor
     */
    public ActorProxy(Actor sourceActor){
        this.sourceActor = sourceActor;     //referencia del actor que le pertoca al proxy
        queue = new LinkedBlockingDeque<Message>();
    }

    /**
     * metode per encuar el missatge
     * Si el from del misstage esta null llavors el propi proxy es posara a ell mateix
     * @param message
     * @throws InterruptedException
     */
    @Override
    public void send(Message message) throws InterruptedException {
        if(message.getFrom() == null){    //en caso que el from del mensaje este Null por default se pondra el propio proxy
            message.setFrom(this);          //asi podra responder el actor
        }
        sourceActor.send(message);
    }

    /**
     * metode per rebre el misstage de la cua
     * @return Message
     * @throws InterruptedException
     */
    public Message receive() throws InterruptedException { //falta implementar
        return queue.take();
    }

    /**
     * getter de la cua
     * @return BlockingQueue<Message>
     */
    public BlockingQueue<Message> getQueue() {
        return queue;
    }

    /**
     * setter de la cua
     * @param queue
     */
    public void setQueue(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    /**
     * metode per rebre el actor conectat al seu proxy
     * @return Actor
     */
    public Actor getSourceActor() {
        return sourceActor;
    }
}
