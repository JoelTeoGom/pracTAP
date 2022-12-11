package helloWorld;

import java.util.Queue;


public class Actor implements Iactor {
    private Queue<Message> queue;
    private String state = "contento";

    private Thread thread;

    public Actor(){
        thread = new Thread();
        thread.start(); //esto llamara a run
    }

    @Override
    public void run() {
        Message message;
        while (true){
            message = queue.poll(); //FIFO y luego procesamos
            if(message != null){
                process(message);    //procesamos mensajes si hay en la cola
            }
        }
    }

    //FALTA METODO PARA ENVIARSE A SI MISMO MENSAJE
    void process(Message message){  //en esta funcion actualizaremos estado
        if(message.getMessage().equals("helloWorld")){  //de momento solo esta este estado
            System.out.printf(message.getMessage());
            this.state = "contento";
        }else{
            System.out.printf("Bye World!!!");
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
}
