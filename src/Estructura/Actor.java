package Estructura;


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

public class Actor implements Iactor{
    protected BlockingQueue<Message> queue;
    protected Event event;
    protected Boolean exit;
    protected int traffic;

    /**
     * constructor
     * inicialitzem traffic a 0
     * inicialitzem event
     * inicialitzem la cua
     * inicialitzem exit a false
     */
    public Actor(){
        traffic = 0;
        event = Event.CREATED;
        queue = new LinkedBlockingDeque<>();
        exit = false;
    }

    /**
     * missatge que agafa el missatge i l'encua a l'actor
     * suma 1 a trafic
     * @param message
     * @throws InterruptedException
     */
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

    /**
     * metode process: mirem quin tipus de missatge i actuem depenent d'aquest
     * @param m
     * @throws InterruptedException
     */
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
            case HelloWorldMessage m1 -> {
                    System.out.printf(m1.getMessage());
                  //  send(m1);
            }
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

    /**
     * getter de la cua de missatges
     * @return
     */
    public BlockingQueue<Message> getQueue() {
        return queue;
    }

    /**
     * setter de la cua de missatges
     * @param queue
     */
    public void setQueue(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    /**
     *
     * @return
     */
    public Boolean getExit() {return exit;}

    /**
     * setter del exit
     * @param exit
     */
    public void setExit(Boolean exit) {
        this.exit = exit;
    }

    /**
     * setter del trrafic
     * @return traffic
     */
    public int getTraffic() {
        return traffic;
    }

    /**
     * setter del trafic
     * @param traffic
     */
    public void setTraffic(int traffic) {
        this.traffic = traffic;
    }

    /**
     * getter del event
     * @return event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * setter del event
     * @param event
     */
    public void setEvent(Event event) {
        this.event = event;
    }


}
