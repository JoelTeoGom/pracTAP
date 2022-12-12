package HelloWorld;

import Message.*;

import java.util.Queue;

public class ActorProxy implements Iactor{

    private Queue<Message> queue;
    private final Actor sourceActor;

    public ActorProxy(Actor sourceActor){
        this.sourceActor = sourceActor;     //referencia del actor que le pertoca al proxy
    }
    @Override
    public void send(Message message) {
        this.sourceActor.getQueue().add(message);
    }

    public Message receive(){ //falta implementar
        return new Message(new ActorProxy(new Actor()), "HOLA");
    }

    public Queue<Message> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Message> queue) {
        this.queue = queue;
    }

    public Actor getSourceActor() {
        return sourceActor;
    }
}
