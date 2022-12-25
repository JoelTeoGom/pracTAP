package Observer;

import Estructura.Actor;
import Estructura.ActorContext;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MonitorService{

    private static final MonitorService monitorService = new MonitorService();

    private HashMap<Actor,List<Observer>> llistaActorsObserver = new HashMap<>();
    private HashMap<Traffic,String> llistaTraficActor = new HashMap<>();

    private MonitorService(){}

    public static MonitorService getInstance(){
        return monitorService;
    }


    public void monitorActor(String name){     //monitorizamos ese actor y le añadimos una lista
        ArrayList<Observer> llistaObserver = new ArrayList<>();
        llistaActorsObserver.put(ActorContext.getInstance().getActorLibrary().get(name), llistaObserver);
    }
    public void monitorAllActor(){
        for (Actor actor : ActorContext.getInstance().getActorLibrary().values()) {    //monitorizamos todos los actores y le añadimos una lista
            ArrayList<Observer> llistaObserver = new ArrayList<>();
            llistaActorsObserver.put(actor,llistaObserver);
        }
    }

    public void subscribe(String name, Observer observer){     //falta comprovar si esta suscrito ( excpeciones)
        Actor actor = ActorContext.getInstance().getActorLibrary().get(name);
        List<Observer> observerList = llistaActorsObserver.get(actor);
        observer.update(actor.getState());                  //le ponemos el estado de created
        observerList.add(observer);
        llistaActorsObserver.put(actor,observerList);
    }

    public void unsubscribe(String name, Observer observer){   //falta comprovar si esta suscrito ( excpeciones)
        Actor actor = ActorContext.getInstance().getActorLibrary().get(name);
        List<Observer> observerList = llistaActorsObserver.get(actor);
        observer.update(null);              //le borramos el estado ya que no esta suscrito anymore
        observerList.remove(observer);
        llistaActorsObserver.put(actor,observerList);
    }

    public synchronized void notifyAllObservers(String state, Actor actor){
        List<Observer> observerList = llistaActorsObserver.get(actor);
        for (Observer observer : observerList) {
            observer.update(state);
        }
    }



    private enum Traffic{
        LOW, MEDIUM, HIGH;
    }
    private enum Event{
        CREATED, STOPPED, ERROR;
    }
}
