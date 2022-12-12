package HelloWorld;



import Message.*;

import java.util.LinkedList;
import java.util.Queue;


public class Actor implements Iactor, Runnable{
    private Queue<Message> queue;
    private String state;

    private Thread thread;

    public Actor(){
        thread = new Thread();
         //esto llamara a run
        this.state = "activo";
        queue = new LinkedList<Message>();
        thread.start();
    }

    @Override
    public void run() {
        Message message;
        while (true) {
            if (!queue.isEmpty()){
                message = queue.poll(); //FIFO y luego procesamos
                process(message);    //procesamos mensajes si hay en la cola
            }
        }
    }

                        //FALTA METODO PARA ENVIARSE A SI MISMO MENSAJE
    void process(Message m){  //en esta funcion actualizaremos estado

        System.out.println(m.getMessage());
        switch (m){
            case HelloWorldMessage m1:
                System.out.printf(m1.getMessage());
                break;
            case QuitMessage m1:
                System.out.printf("Oh hell naw!!!");
                break;
            case InsultMessage m1:
                System.out.printf("Da fk did u just told me");
                break;
            default : System.out.printf("No se ha registrado");
        }
    }


    @Override
    public void send(Message message) {
        //FALTA IMPLEMENTACION
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

}
