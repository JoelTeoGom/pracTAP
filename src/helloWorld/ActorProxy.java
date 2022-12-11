package helloWorld;

import java.util.Queue;

public class ActorProxy implements Iactor{

    private Queue<Message> queue;

    private Thread thread;

    public ActorProxy(){
        thread = new Thread();
        thread.start(); //esto llamara a run
    }

    @Override
    public void run() {     //El proxy va procesando los mensajes que tiene en la cola y los va enviando
        Message message;
        while (true){
            message = queue.poll();
            if(message != null){
                send(message);
            }
        }
    }

    public void send (Message message){
        message.getActorReference().getQueue().add(message);
    }

    public Message receive(){
        return new Message(new Actor(),"hola");
    }


}
