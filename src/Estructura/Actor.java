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

public abstract class Actor implements Iactor{
    protected BlockingQueue<Message> queue;
    protected String state;
    protected Boolean exit;


    /**
     * constructor
     * inicialitzem state
     * inicialitzem la cua
     * inicialitzem exit a false
     */
    public Actor(){
        state = "tranquilo";
        queue = new LinkedBlockingDeque<>();
        MonitorService.getInstance().publish(Event.CREATED,this,null);
        exit = false;
    }


    /**
     * metode process: mirem quin tipus de missatge i actuem depenent d'aquest
     * @param m
     * @throws InterruptedException
     */
    public void process(Message m) throws InterruptedException {  //en esta funcion actualizaremos estado
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


    /**
     * @param message
     * @throws InterruptedException
     */
    @Override
    public void send(Message message) throws InterruptedException {
    }
}
