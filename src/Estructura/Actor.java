package Estructura;

import Decorator.ActorInterface;
import HelloWorld.HelloWorldMessage;
import Message.*;
import Observer.MonitorService;

import java.beans.EventSetDescriptor;
import java.util.LinkedList;
import java.util.Queue;

public class Actor implements ActorInterface,Iactor{
    private Queue<Message> queue;
    private String state;
    private Boolean exit;

    private int traffic;


    public Actor(){
        traffic = 0;
        this.state = "CREATED";
        queue = new LinkedList<Message>();
        exit = false;

    }


    @Override
    public void send(Message message) throws InterruptedException {
        Message m = new Message(new ActorProxy(this), message.getMessage());
        message.getFrom().getQueue().put(m);
        if(MonitorService.getInstance().getLlistaActorsObserver().containsKey(this)){
            MonitorService.getInstance().putAllMessages(this,m);
            MonitorService.getInstance().putSentMessage(this,m);
        }
        traffic++;      //nuevo mensaje generado
    }


    public void process(Message m) throws InterruptedException {  //en esta funcion actualizaremos estado
        System.out.println("SOY un actor padre");
        setState("RECEIVED MESSAGE");

        if(MonitorService.getInstance().getLlistaActorsObserver().containsKey(this)){
            MonitorService.getInstance().notifyAllObservers(state,this);
            MonitorService.getInstance().putAllMessages(this,m);
            MonitorService.getInstance().putReceivedMessage(this,m);
        }

        traffic++; //nuevo mensaje procesado

        switch (m){
            case HelloWorldMessage m1:
                System.out.printf(m1.getMessage());
                break;
            case QuitMessage m1:
                System.out.printf("Oh hell naw!!!");
                setState("FINALIZADO");
                MonitorService.getInstance().notifyAllObservers(state,this);
                send(m1);
                exit = true;
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

    public Boolean getExit() {return exit;}

    public void setExit(Boolean exit) {
        this.exit = exit;
    }


    public int getTraffic() {
        return traffic;
    }

    public void setTraffic(int traffic) {
        this.traffic = traffic;
    }
}
