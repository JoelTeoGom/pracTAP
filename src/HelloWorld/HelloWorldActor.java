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
        MonitorService.getInstance().publish(Event.RECEIVE,this,m);
        switch (m) {
            case HelloWorldMessage m1 -> {
                System.out.println(m1.getMessage());
            }
            case QuitMessage m1 -> {
                System.out.println("Oh hell naw!!!");
                MonitorService.getInstance().publish(Event.STOPPED,this,m);
                exit = true;
            }
            default -> System.out.print("No se ha registrado");
        }
    }
}
