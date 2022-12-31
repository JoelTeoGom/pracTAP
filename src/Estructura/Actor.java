package Estructura;

import Decorator.ActorInterface;
import HelloWorld.HelloWorldMessage;
import Message.*;
import Observer.Event;
import Observer.MonitorService;
import Observer.Traffic;

import java.beans.EventSetDescriptor;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Actor implements ActorInterface,Iactor{
    protected BlockingQueue<Message> queue;
    protected Event event;
    protected Boolean exit;
    protected int traffic;

    public Actor(){
        traffic = 0;
        event = Event.CREATED;
        queue = new LinkedBlockingDeque<>();
        exit = false;
    }

    @Override
    public void send(Message message) throws InterruptedException {
        Message m = new Message(new ActorProxy(this), message.getMessage());
        message.getFrom().getQueue().put(m);

        if(MonitorService.getInstance().getMonitoredActor().containsKey(this)){
            MonitorService.getInstance().putAllMessages(this,m);
            MonitorService.getInstance().putSentMessage(this,m);
        }
        traffic++;
    }


    public void process(Message m) throws InterruptedException {  //en esta funcion actualizaremos estado
        System.out.println("Soy un actor padre");

        event = Event.MESSAGE;
        traffic++; //nuevo mensaje procesado

        if(MonitorService.getInstance().getMonitoredActor().containsKey(this)){
            MonitorService.getInstance().notifyAllObservers(event,this);
            MonitorService.getInstance().putAllMessages(this,m);
            MonitorService.getInstance().putReceivedMessage(this,m);
            MonitorService.getInstance().logEventsActor(this);
        }

        switch (m) {
            case HelloWorldMessage m1 -> System.out.printf(m1.getMessage());
            case QuitMessage m1 -> {
                System.out.println("Oh hell naw!!!");
                event = Event.STOPPED;
                if (MonitorService.getInstance().getMonitoredActor().containsKey(this)) {
                    MonitorService.getInstance().notifyAllObservers(event, this);
                    MonitorService.getInstance().logEventsActor(this);
                }
                send(m1);
                exit = true;
            }
            default -> System.out.print("No se ha registrado");
        }
    }

    @Override
    public BlockingQueue<Message> getQueue() {
        return queue;
    }


    public void setQueue(BlockingQueue<Message> queue) {
        this.queue = queue;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }


}
