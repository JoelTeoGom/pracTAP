package Insult;

import Estructura.Actor;
import Message.*;
import Observer.MonitorService;

import java.util.ArrayList;
import java.util.List;

public class InsultActor extends Actor {

    private List<String> listaInsultos = new ArrayList<>();


    public InsultActor() {
        super();
    }


    @Override
    public void process(Message message) throws InterruptedException {
        listaInsultos.add("taco");
        listaInsultos.add("taco2");
        listaInsultos.add("taco3");
        setState("RECEIVED MESSAGE");
        if(MonitorService.getInstance().getLlistaActorsObserver().containsKey(this))
            MonitorService.getInstance().notifyAllObservers(getState(),this);
        switch (message){
            case AddInsultMessage m1:
                listaInsultos.add(m1.getMessage());
                break;
            case GetInsultMessage m1:
                int numeroAleatorio = (int) (Math.random()*(listaInsultos.size()-1)+0);
                m1.setMessage(listaInsultos.get(numeroAleatorio));
                send(m1);
                break;
            case GetAllInsultMessage m1:
                m1.setMessage(listaInsultos.toString());
                send(m1);
                break;
            case QuitMessage m1:
                System.out.println("Oh hell naw!!!");
                setState("FINALIZATION INSULT");
                if(MonitorService.getInstance().getLlistaActorsObserver().containsKey(this))
                    MonitorService.getInstance().notifyAllObservers(getState(),this);
                setExit(true);
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
