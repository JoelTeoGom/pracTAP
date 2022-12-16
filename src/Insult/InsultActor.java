package Insult;

import Estructura.Actor;
import Message.*;

import java.util.ArrayList;
import java.util.List;

public class InsultActor extends Actor {

    private List<String> listaInsultos = new ArrayList<>();

    public InsultActor() {
        super();
        listaInsultos.add("taco");
        listaInsultos.add("taco2");
        listaInsultos.add("taco3");
    }


    @Override
    protected void process(Message message) {
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
                setState("inactivo");
                setExit();
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
