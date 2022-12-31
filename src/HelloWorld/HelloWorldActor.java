package HelloWorld;

import Estructura.Actor;
import Message.*;
import Observer.Event;
import Observer.MonitorService;

public class HelloWorldActor extends Actor{
    public HelloWorldActor() {
        super();
    }

    @Override
    public void process(Message m){  //en esta funcion actualizaremos estado

        if(MonitorService.getInstance().getLlistaActorsObserver().containsKey(this))
            MonitorService.getInstance().notifyAllObservers(getEvent(),this);
        switch (m){
            case HelloWorldMessage m1:
                System.out.println(m1.getMessage());
                MonitorService.getInstance().notifyAllObservers(getEvent(),this);
                break;
            case QuitMessage m1:
                this.setEvent(Event.STOPPED);
                if(MonitorService.getInstance().getMonitoredActor().containsKey(this)) {
                    MonitorService.getInstance().notifyAllObservers(this.getEvent(), this);
                    MonitorService.getInstance().logEventsActor(this);
                }
                setExit(true);
                break;
            default : System.out.printf("No se ha registrado");
        }

    }
}
