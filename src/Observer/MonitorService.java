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



    /**
     * constructor MonitorService
     */
    private MonitorService(){}

    /**
     * metode per obtindre la instancia del MonitorService
     * @return MonitorService
     */
    public static MonitorService getInstance(){
        return monitorService;
    }


    //Actor Section

    /**
     * inicialitzacio del monitorActor, afegim a les llistges el actor
     * @param name
     */
    public void monitorActor(String name){
        Actor actor = ActorContext.getInstance().getActorLibrary().get(name);
        monitoredActor.put(actor,name);
        llistaActorsObserver.put(actor, new ArrayList<Observer>());
        llistaMessageActor.put(actor,new ArrayList<>());
        llistaSentMessageActor.put(actor,new ArrayList<>());
        llistaReceivedMessageActor.put(actor,new ArrayList<>());
        llistaActorEvents.put(actor, new ArrayList<>());
    }

    /**
     *
     */
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

    /**
     *
     * @param name
     * @param observer
     */
    public void subscribe(String name, Observer observer){     //falta comprovar si esta suscrito ( excpeciones)
        Actor actor = ActorContext.getInstance().getActorLibrary().get(name);
        List<Observer> observerList = llistaActorsObserver.get(actor);
        observer.update(actor.getEvent());                  //le ponemos el estado de created
        observerList.add(observer);
        llistaActorsObserver.put(actor,observerList);
    }

    /**
     *
     * @param name
     * @param observer
     */
    public void unsubscribe(String name, Observer observer){   //falta comprovar si esta suscrito ( excpeciones)
        Actor actor = ActorContext.getInstance().getActorLibrary().get(name);
        List<Observer> observerList = llistaActorsObserver.get(actor);
        observer.update(null);              //le borramos el estado ya que no esta suscrito anymore
        observerList.remove(observer);
        llistaActorsObserver.put(actor,observerList);
    }

    /**
     * metode per actulitzar estats
     * @param state
     * @param actor
     */
    public synchronized void notifyAllObservers(Event state, Actor actor){
        List<Observer> observerList = llistaActorsObserver.get(actor);
        for (Observer observer : observerList) {
            observer.update(state);
        }
    }

    //Traffic log section

    /**
     * getter del trafic
     * @return HashMap<Traffic,List<String>>
     */
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

    /**
     * getter dels events
     * @return HashMap<Event,List<String>>
     */
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

    /**
     *
     * @param actor
     */
    public void logEventsActor(Actor actor){
        List<String> list = llistaActorEvents.get(actor);
        list.add(actor.getEvent().name());
        llistaActorEvents.put(actor,list);
    }


    //Message log section

    /**
     *
     * @param actor
     * @param message
     */
    public void putSentMessage(Actor actor, Message message){
        List<Message> list = llistaSentMessageActor.get(actor);
        list.add(message);
        llistaSentMessageActor.put(actor,list);
    }

    /**
     * metode fer afegir el misstage rebut
     * @param actor
     * @param message
     */
    public void putReceivedMessage(Actor actor, Message message){
        List<Message> list = llistaReceivedMessageActor.get(actor);
        list.add(message);
        llistaReceivedMessageActor.put(actor,list);
    }

    /**
     * metode per afegir tots els misstages
     * @param actor
     * @param message
     */
    public void putAllMessages(Actor actor, Message message){
        List<Message> list = llistaMessageActor.get(actor);
        list.add(message);
        llistaMessageActor.put(actor,list);
    }

    /**
     *  getter del nombre de misstages
     * @param actor
     * @return List<Message>
     */
    public List<Message> getNumberOfMessages(Actor actor){
        return llistaMessageActor.get(actor);
    }

    /**
     * getter del nomre de missatges enviats
     * @param actor
     * @return List<Message>
     */
    public List<Message> getSentMessages(Actor actor){
        return llistaSentMessageActor.get(actor);
    }

    /**
     * getter dels missatges rebuts
     * @param actor
     * @return List<Message>
     */
    public List<Message> getReceivedMessages(Actor actor){
        return llistaReceivedMessageActor.get(actor);
    }

    /**
     * getter dels events dels actors
     * @param actor
     * @return List<Message>
     */
    public List<String> getEventsActor(Actor actor){
        return llistaActorEvents.get(actor);
    }

    /**
     * getter de la llista de actors observers
     * @return HashMap<Actor, List<Observer>>
     */
    public HashMap<Actor, List<Observer>> getLlistaActorsObserver() {
        return llistaActorsObserver;
    }

    /**
     * getter del monitor Actor
     * @return HashMap<Actor, List<Observer>>
     */
    public HashMap<Actor, String> getMonitoredActor() {
        return monitoredActor;
    }
}
