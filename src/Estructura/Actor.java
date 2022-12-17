package Estructura;

import HelloWorld.HelloWorldMessage;
import Insult.*;
import Message.*;
import java.util.LinkedList;
import java.util.Queue;

public class Actor implements Runnable, Iactor{
    private Queue<Message> queue;
    private String state;
    private Boolean exit;
    private Thread thread;

    public Actor(){
        thread = new Thread(this); //observacion: hay que pasarle el objeto que el thread tiene que correr
        this.state = "activo";
        queue = new LinkedList<Message>();
        exit = false;
        thread.start();
                        //Observacion: hay que poner la llamada del thread lo ultim
    }

    @Override
    public void run() {
        Message message;
        while (!exit) {
            if (!queue.isEmpty()){
                message = queue.poll(); //FIFO y luego procesamos
                try {
                    process(message);    //procesamos mensajes si hay en la cola
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void send(Message message) throws InterruptedException {
        Message m = new Message(new ActorProxy(this), message.getMessage());
        message.getFrom().getQueue().put(m);
    }

    protected void setExit(){
        exit = true;
    }

    protected void process(Message m) throws InterruptedException {  //en esta funcion actualizaremos estado
        System.out.println("SOY un actor padre");
        switch (m){
            case HelloWorldMessage m1:
                System.out.printf(m1.getMessage());
                break;
            case QuitMessage m1:
                System.out.printf("Oh hell naw!!!");
                setState("inactivo");
                setExit();
                break;
            default : System.out.printf("No se ha registrado");
        }
    }


    public Queue<Message> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Message> queue) {
        this.queue = queue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }


    public Boolean getExit() {
        return exit;
    }

    public void setExit(Boolean exit) {
        this.exit = exit;
    }
}
