package helloWorld;

import java.util.Queue;

public class ActorProxy implements Iactor{

    private Queue<Message> queue;
    private final Iactor iActor;

    public ActorProxy(Iactor sourceActor){
        this.iActor = sourceActor;     //referencia del actor que le pertoca al proxy
    }
    @Override
    public void send(Message message) {

    }

    public Message receive(){ //falta implementar
        return new Message(new ActorProxy(new Actor()), "HOLA");
    }

}
