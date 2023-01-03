package Insult;

import Estructura.Actor;
import Message.*;
import Observer.Event;
import Observer.MonitorService;

import java.util.ArrayList;
import java.util.List;

public class InsultActor extends Actor {

    private List<String> listaInsultos = new ArrayList<>();


    public InsultActor() {
        super();
    }


    @Override
    public void process(Message m) throws InterruptedException {
//        listaInsultos.add("taco");
//        listaInsultos.add("taco2");
//        listaInsultos.add("taco3");

        event = Event.MESSAGE;
        traffic++;

        if(MonitorService.getInstance().getMonitoredActor().containsKey(this)){
            MonitorService.getInstance().notifyAllObservers(event,this);
            MonitorService.getInstance().putAllMessages(this,m);
            MonitorService.getInstance().putReceivedMessage(this,m);
            MonitorService.getInstance().logEventsActor(this);
        }
        switch (m){
            case AddInsultMessage m1:
                listaInsultos.add(m1.getMessage());
                break;
            case GetInsultMessage m1:
                int numeroAleatorio = (int) (Math.random()*(listaInsultos.size())+0);
                m1.setMessage(listaInsultos.get(numeroAleatorio));
                send(m1);
                break;
            case GetAllInsultMessage m1:
                m1.setMessage(listaInsultos.toString());
                send(m1);
                break;
            case QuitMessage m1:
                System.out.println("Oh hell naw!!!");
                event = Event.STOPPED;
                if(MonitorService.getInstance().getMonitoredActor().containsKey(this)) {
                    MonitorService.getInstance().notifyAllObservers(event, this);
                    MonitorService.getInstance().logEventsActor(this);
                }
                exit = true;
            default:
                break;
        }


    }

    public List<String> getListaInsultos() {
        return listaInsultos;
    }

    public void setListaInsultos(List<String> listaInsultos) {
        this.listaInsultos = listaInsultos;
    }
}
