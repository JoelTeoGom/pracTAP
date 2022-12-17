package Estructura;

import Message.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ActorProxy implements Iactor{

    private BlockingQueue<Message> queue;
    private final Actor sourceActor;

    public ActorProxy(Actor sourceActor){
        this.sourceActor = sourceActor;     //referencia del actor que le pertoca al proxy
        queue = new LinkedBlockingDeque<Message>();
    }
    @Override
    public void send(Message message) {
        if(message.getFrom() == null){    //en caso que el from del mensaje este Null por default se pondra el propio proxy
            message.setFrom(this);          //asi podra responder el actor
        }
        this.sourceActor.getQueue().add(message);
    }

    public Message receive() throws InterruptedException { //falta implementar
        return queue.take();
    }

    public BlockingQueue<Message> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public Actor getSourceActor() {
        return sourceActor;
    }
}
