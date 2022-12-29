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

    private HashMap<Actor,String> monitoredActor = new HashMap<>();     //lista actores monitorizados y su respectivo nombre

    private HashMap<Actor,List<Observer>> llistaActorsObserver = new HashMap<>();   //lista observer x actor

    private HashMap<Traffic,List<String>> llistaTraficActor = new HashMap<>();  //retorna lista de nombres segun trafico

    private HashMap<Event,List<String>> llistaEventsActor = new HashMap<>();    //retorna lista de nombres segun evento

    private HashMap<Actor, List<Message>> llistaMessageActor = new HashMap<>();

    private HashMap<Actor, List<Message>> llistaSentMessageActor = new HashMap<>();
    private HashMap<Actor, List<Message>> llistaReceivedMessageActor = new HashMap<>();

    private MonitorService(){}

    public static MonitorService getInstance(){
        return monitorService;
    }

    public void monitorActor(String name){
        Actor actor = ActorContext.getInstance().getActorLibrary().get(name);
        monitoredActor.put(actor,name);
        llistaActorsObserver.put(actor, new ArrayList<Observer>());
        llistaMessageActor.put(actor,new ArrayList<>());
        llistaSentMessageActor.put(actor,new ArrayList<>());
        llistaReceivedMessageActor.put(actor,new ArrayList<>());
    }
    public void monitorAllActor(){
        for (String name : ActorContext.getInstance().getActorLibrary().keySet()) {
            Actor actor = ActorContext.getInstance().getActorLibrary().get(name);
            monitoredActor.put(actor,name);
            llistaActorsObserver.put(actor, new ArrayList<Observer>());
            llistaMessageActor.put(actor,new ArrayList<>());
            llistaSentMessageActor.put(actor,new ArrayList<>());
            llistaReceivedMessageActor.put(actor,new ArrayList<>());
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


    public HashMap<Traffic,List<String>> getTraffic(){

        List<String> low = monitoredActor.entrySet().stream().filter(actor -> actor.getKey().getTraffic() <= 5).map(Map.Entry::getValue).toList();
        List<String> med = monitoredActor.entrySet().stream().filter(actor -> actor.getKey().getTraffic() > 5 && actor.getKey().getTraffic() < 15).map(Map.Entry::getValue).toList();
        List<String> high = monitoredActor.entrySet().stream().filter(actor -> actor.getKey().getTraffic() >= 15).map(Map.Entry::getValue).toList();

        llistaTraficActor.put(Traffic.LOW, low);
        llistaTraficActor.put(Traffic.MEDIUM,med);
        llistaTraficActor.put(Traffic.HIGH,high);

        return llistaTraficActor;
    }

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



    private enum Traffic{
        LOW, MEDIUM, HIGH;
    }
    private enum Event{
        CREATED, STOPPED, ERROR;
    }

    public HashMap<Actor, List<Observer>> getLlistaActorsObserver() {
        return llistaActorsObserver;
    }

    public void setLlistaActorsObserver(HashMap<Actor, List<Observer>> llistaActorsObserver) {
        this.llistaActorsObserver = llistaActorsObserver;
    }

    public HashMap<Traffic, List<String>> getLlistaTraficActor() {
        return llistaTraficActor;
    }

    public void setLlistaTraficActor(HashMap<Traffic, List<String>> llistaTraficActor) {
        this.llistaTraficActor = llistaTraficActor;
    }

    public HashMap<Event, List<String>> getLlistaEventsActor() {
        return llistaEventsActor;
    }

    public void setLlistaEventsActor(HashMap<Event, List<String>> llistaEventsActor) {
        this.llistaEventsActor = llistaEventsActor;
    }


    public HashMap<Actor, List<Message>> getLlistaMessageActor() {
        return llistaMessageActor;
    }

    public void setLlistaMessageActor(HashMap<Actor, List<Message>> llistaMessageActor) {
        this.llistaMessageActor = llistaMessageActor;
    }

    public HashMap<Actor, List<Message>> getLlistaSentMessageActor() {
        return llistaSentMessageActor;
    }

    public void setLlistaSentMessageActor(HashMap<Actor, List<Message>> llistaSentMessageActor) {
        this.llistaSentMessageActor = llistaSentMessageActor;
    }

    public HashMap<Actor, List<Message>> getLlistaReceivedMessageActor() {
        return llistaReceivedMessageActor;
    }

    public void setLlistaReceivedMessageActor(HashMap<Actor, List<Message>> llistaReceivedMessageActor) {
        this.llistaReceivedMessageActor = llistaReceivedMessageActor;
    }
}
