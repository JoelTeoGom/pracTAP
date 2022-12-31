package Observer;

import Estructura.Actor;
import Estructura.ActorContext;
import Message.Message;
import java.lang.ref.PhantomReference;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonitorService{

    private static final MonitorService monitorService = new MonitorService();

    //list section
    private final HashMap<Actor,String> monitoredActor = new HashMap<>();

    private final HashMap<Actor,List<Observer>> llistaActorsObserver = new HashMap<>();

    private final HashMap<Traffic,List<String>> llistaTraficActor = new HashMap<>();

    private final HashMap<Event,List<String>> llistaEventsActor = new HashMap<>();

    private final HashMap<Actor,List<String>> llistaActorEvents = new HashMap<>();
    private final HashMap<Actor, List<Message>> llistaMessageActor = new HashMap<>();

    private final HashMap<Actor, List<Message>> llistaSentMessageActor = new HashMap<>();
    private final HashMap<Actor, List<Message>> llistaReceivedMessageActor = new HashMap<>();

    //constructor section
    private MonitorService(){}

    //get MonitorService instance
    public static MonitorService getInstance(){
        return monitorService;
    }


    //Actor Section
    public void monitorActor(String name){
        Actor actor = ActorContext.getInstance().getActorLibrary().get(name);
        monitoredActor.put(actor,name);
        llistaActorsObserver.put(actor, new ArrayList<Observer>());
        llistaMessageActor.put(actor,new ArrayList<>());
        llistaSentMessageActor.put(actor,new ArrayList<>());
        llistaReceivedMessageActor.put(actor,new ArrayList<>());
        llistaActorEvents.put(actor, new ArrayList<>());
    }
    public void monitorAllActor(){
        for (String name : ActorContext.getInstance().getActorLibrary().keySet()) {
            Actor actor = ActorContext.getInstance().getActorLibrary().get(name);
            monitoredActor.put(actor,name);
            llistaActorsObserver.put(actor, new ArrayList<Observer>());
            llistaMessageActor.put(actor,new ArrayList<>());
            llistaSentMessageActor.put(actor,new ArrayList<>());
            llistaReceivedMessageActor.put(actor,new ArrayList<>());
            llistaActorEvents.put(actor,new ArrayList<>());
        }
    }

    //Observer section
    public void subscribe(String name, Observer observer){     //falta comprovar si esta suscrito ( excpeciones)
        Actor actor = ActorContext.getInstance().getActorLibrary().get(name);
        List<Observer> observerList = llistaActorsObserver.get(actor);
        observer.update(actor.getEvent());                  //le ponemos el estado de created
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


    public synchronized void notifyAllObservers(Event state, Actor actor){
        List<Observer> observerList = llistaActorsObserver.get(actor);
        for (Observer observer : observerList) {
            observer.update(state);
        }
    }

    //Traffic log section
    public HashMap<Traffic,List<String>> getTraffic(){

        List<String> low = monitoredActor.entrySet().stream().filter(actor -> actor.getKey().getTraffic()<= 5).map(Map.Entry::getValue).toList();
        List<String> med = monitoredActor.entrySet().stream().filter(actor -> actor.getKey().getTraffic() >5 && actor.getKey().getTraffic() < 15).map(Map.Entry::getValue).toList();
        List<String> high = monitoredActor.entrySet().stream().filter(actor -> actor.getKey().getTraffic() >= 15).map(Map.Entry::getValue).toList();

        llistaTraficActor.put(Traffic.LOW, low);
        llistaTraficActor.put(Traffic.MEDIUM,med);
        llistaTraficActor.put(Traffic.HIGH,high);

        return llistaTraficActor;
    }

    //Event log section
    public HashMap<Event,List<String>> getEvent(){

        List<String> created = monitoredActor.entrySet().stream().filter(actor -> actor.getKey().getEvent().equals(Event.CREATED)).map(Map.Entry::getValue).toList();
        List<String> stopped = monitoredActor.entrySet().stream().filter(actor -> actor.getKey().getEvent().equals(Event.STOPPED)).map(Map.Entry::getValue).toList();
        List<String> error = monitoredActor.entrySet().stream().filter(actor -> actor.getKey().getEvent().equals(Event.ERROR)).map(Map.Entry::getValue).toList();
        List<String> message = monitoredActor.entrySet().stream().filter(actor -> actor.getKey().getEvent().equals(Event.MESSAGE)).map(Map.Entry::getValue).toList();

        llistaEventsActor.put(Event.CREATED, created);
        llistaEventsActor.put(Event.STOPPED,stopped);
        llistaEventsActor.put(Event.ERROR,error);
        llistaEventsActor.put(Event.MESSAGE,message);

        return llistaEventsActor;
    }

    public void logEventsActor(Actor actor){
        List<String> list = llistaActorEvents.get(actor);
        list.add(actor.getEvent().name());
        llistaActorEvents.put(actor,list);
    }


    //Message log section
    public void putSentMessage(Actor actor, Message message){
        List<Message> list = llistaSentMessageActor.get(actor);
        list.add(message);
        llistaSentMessageActor.put(actor,list);
    }
    public void putReceivedMessage(Actor actor, Message message){
        List<Message> list = llistaReceivedMessageActor.get(actor);
        list.add(message);
        llistaReceivedMessageActor.put(actor,list);
    }

    public void putAllMessages(Actor actor, Message message){
        List<Message> list = llistaMessageActor.get(actor);
        list.add(message);
        llistaMessageActor.put(actor,list);
    }

    public List<Message> getNumberOfMessages(Actor actor){
        return llistaMessageActor.get(actor);
    }
    public List<Message> getSentMessages(Actor actor){
        return llistaSentMessageActor.get(actor);
    }
    public List<Message> getReceivedMessages(Actor actor){
        return llistaReceivedMessageActor.get(actor);
    }
    public List<String> getEventsActor(Actor actor){
        return llistaActorEvents.get(actor);
    }
    public HashMap<Actor, List<Observer>> getLlistaActorsObserver() {
        return llistaActorsObserver;
    }
    public HashMap<Actor, String> getMonitoredActor() {
        return monitoredActor;
    }
}
