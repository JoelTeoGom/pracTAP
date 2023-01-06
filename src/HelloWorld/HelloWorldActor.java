package HelloWorld;

import Estructura.Actor;
import Message.*;
import Observer.Event;
import Observer.MonitorService;

public class HelloWorldActor extends Actor{
    public HelloWorldActor() {
        super();
    }

    /**
     * metode process: mirem quin tipus de missatge i actuem depenent d'aquest
     * @param m
     */
    @Override
    public void process(Message m){  //en esta funcion actualizaremos estado

        event = Event.MESSAGE;
        traffic++;

        if(MonitorService.getInstance().getMonitoredActor().containsKey(this)){
            MonitorService.getInstance().notifyAllObservers(event,this);
            MonitorService.getInstance().putAllMessages(this,m);
            MonitorService.getInstance().putReceivedMessage(this,m);
            MonitorService.getInstance().logEventsActor(this);
        }

        switch (m) {
            case HelloWorldMessage m1 -> {
                System.out.println(m1.getMessage());
            }
            case QuitMessage m1 -> {
                System.out.println("Oh hell naw!!!");
                event = Event.STOPPED;
                if(MonitorService.getInstance().getMonitoredActor().containsKey(this)) {
                    MonitorService.getInstance().notifyAllObservers(event, this);
                    MonitorService.getInstance().logEventsActor(this);
                }
                exit = true;
            }
            default -> System.out.print("No se ha registrado");
        }

    }
}
