package Estructura;


import Message.Message;

public class Runner implements Runnable{

    private Thread thread;
    private Actor actor;

    /**
     * constructor
     * @param actor
     */
    public Runner(Actor actor){
        this.actor = actor;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * metode per fer el run als actors
     * @throws RuntimeException,RuntimeException
     */
    @Override
    public void run() {
        Message message;
        while (!actor.getExit()) {
            if (!actor.getQueue().isEmpty()){
                try {
                    message = actor.getQueue().take(); //FIFO y luego procesamos
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    actor.process(message);    //procesamos mensajes si hay en la cola
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * getter per obtindre el thread
     * @return Thread
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * setter del Thread
     * @param thread
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    /**
     * getter actor
     * @return Actor
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * setter actor
     * @param actor
     */
    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
