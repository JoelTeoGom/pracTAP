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
    private final HashMap<Actor,List<Event>> llistaActorEvents = new HashMap<>();
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
        observer.update(Event.CREATED);                  //le ponemos el estado de created
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
     * @return hashmap amb el trafic, indexat amb l'enum Traffic
     */
    public HashMap<Traffic,List<String>> getTraffic(){

        List<String> low = monitoredActor.entrySet().stream().filter(actor -> llistaMessageActor.get(actor).size() <= 5).map(Map.Entry::getValue).toList();
        List<String> med = monitoredActor.entrySet().stream().filter(actor -> llistaMessageActor.get(actor).size() >5 && llistaMessageActor.get(actor).size() < 15).map(Map.Entry::getValue).toList();
        List<String> high = monitoredActor.entrySet().stream().filter(actor -> llistaMessageActor.get(actor).size() >= 15).map(Map.Entry::getValue).toList();

        llistaTraficActor.put(Traffic.LOW, low);
        llistaTraficActor.put(Traffic.MEDIUM,med);
        llistaTraficActor.put(Traffic.HIGH,high);

        return llistaTraficActor;
    }

    //Event log section

    /**
     * getter dels events
     * @return hashmap amb els actors corresponents a un cert event
     */
    public HashMap<Event,List<String>> getEvent(){
        for (Actor actor: monitoredActor.keySet()){
            Event ev = llistaActorEvents.get(actor).get(llistaActorEvents.size()-1);
            List<String> list = llistaEventsActor.get(ev);
            list.add(monitoredActor.get(actor));
            llistaEventsActor.put(ev,list);
        }
        return llistaEventsActor;
    }

    /**
     *
     * @param actor
     */
    public void logEventsActor(Actor actor,Event event){
        List<Event> list = llistaActorEvents.get(actor);
        list.add(event);
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


    public void publish(Event event, Actor actor,Message m){
        if(monitoredActor.containsKey(actor)) {
            switch (event) {
                case SEND -> {
                    notifyAllObservers(event,actor);
                    putAllMessages(actor,m);
                    putSentMessage(actor,m);
                    logEventsActor(actor,event);
                }
                case RECEIVE -> {
                    notifyAllObservers(event,actor);
                    putAllMessages(actor,m);
                    putReceivedMessage(actor,m);
                    logEventsActor(actor,event);
                }
                case CREATED, STOPPED, ERROR -> {
                    MonitorService.getInstance().notifyAllObservers(event, actor);
                    MonitorService.getInstance().logEventsActor(actor,event);
                }
            }
        }
    }

    /**
     *  getter del nombre de misstages
     * @param actor
     * @return llista de missatges
     */
    public List<Message> getNumberOfMessages(Actor actor){
        return llistaMessageActor.get(actor);
    }

    /**
     * getter del nomre de missatges enviats
     * @param actor
     * @return llista de missatges
     */
    public List<Message> getSentMessages(Actor actor){
        return llistaSentMessageActor.get(actor);
    }

    /**
     * getter dels missatges rebuts
     * @param actor
     * @return llista de missatges
     */
    public List<Message> getReceivedMessages(Actor actor){
        return llistaReceivedMessageActor.get(actor);
    }

    /**
     * getter dels events dels actors
     * @param actor
     * @return llista de missatges
     */
    public List<Event> getEventsActor(Actor actor){
        return llistaActorEvents.get(actor);
    }

    /**
     * getter de la llista de actors observers
     * @return mapa amb els observers per a un determinat actor
     */
    public HashMap<Actor, List<Observer>> getLlistaActorsObserver() {
        return llistaActorsObserver;
    }

    /**
     * getter del monitor Actor
     * @return hashmap per retornar els noms dels actors guardats
     */
    public HashMap<Actor, String> getMonitoredActor() {
        return monitoredActor;
    }
}
