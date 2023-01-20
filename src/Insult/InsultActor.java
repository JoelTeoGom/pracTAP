package Insult;

import Estructura.Actor;
import Estructura.ActorProxy;
import Message.*;
import Observer.Event;
import Observer.MonitorService;

import java.util.ArrayList;
import java.util.List;

public class InsultActor extends Actor {

    private List<String> listaInsultos = new ArrayList<>();

    /**
     * constructor InsultActor
     */
    public InsultActor() {
        super();
    }

    /**
     * metode process: mirem quin tipus de classe insult es i actuem depenent d'aquesta
     * @param m
     */
    @Override
    public void process(Message m) throws InterruptedException {
        Message message = null;
        MonitorService.getInstance().publish(Event.RECEIVE,this,m);
        switch (m){
            case AddInsultMessage m1:
                listaInsultos.add(m1.getMessage());
                break;
            case GetInsultMessage m1:
                int numeroAleatorio = (int) (Math.random()*(listaInsultos.size())+0);
                message = new Message(new ActorProxy(this), listaInsultos.get(numeroAleatorio));
                MonitorService.getInstance().publish(Event.SEND,this,message);
                m1.getFrom().getQueue().put(message);
                break;
            case GetAllInsultMessage m1:
                message = new Message(new ActorProxy(this),listaInsultos.toString());
                MonitorService.getInstance().publish(Event.SEND,this,message);
                m1.getFrom().getQueue().put(message);
                break;
            case QuitMessage m1:
                MonitorService.getInstance().publish(Event.STOPPED,this,m);
                System.out.println("Oh hell naw!!!");
                exit = true;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + m);
        }


    }

    public List<String> getListaInsultos() {
        return listaInsultos;
    }

    public void setListaInsultos(List<String> listaInsultos) {
        this.listaInsultos = listaInsultos;
    }
}
