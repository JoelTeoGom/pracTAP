package Estructura;

import Message.Message;

public class Runner implements Runnable{

    private Thread thread;
    private Actor actor;

    public Runner(Actor actor){
        this.actor = actor;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        Message message;
        while (!actor.getExit()) {
            if (!actor.getQueue().isEmpty()){
                message = actor.getQueue().poll(); //FIFO y luego procesamos
                try {
                    actor.process(message);    //procesamos mensajes si hay en la cola
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
