package HelloWorld;

import Estructura.Actor;
import Message.*;
import Observer.MonitorService;

public class HelloWorldActor extends Actor{
    public HelloWorldActor() {
        super();
    }

    @Override
    public void process(Message m){  //en esta funcion actualizaremos estado
        MonitorService.getInstance().notifyAllObservers("Message received",this);
        switch (m){
            case HelloWorldMessage m1:
                System.out.println(m1.getMessage());
                MonitorService.getInstance().notifyAllObservers("HELLO",this);
                break;
            case QuitMessage m1:
                MonitorService.getInstance().notifyAllObservers("FINISH",this);
                setExit(true);
                break;
            default : System.out.printf("No se ha registrado");
        }

    }
}
