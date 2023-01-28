package Estructura;


import Decorator.ActorDecorator;
import Observer.MonitorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActorContext {
    private static final ActorContext actorContext = new ActorContext();
    private final HashMap<String,Actor> actorLibrary = new HashMap<>();
    private final HashMap<Actor,Thread> actorThreadHashMap = new HashMap<>();

    private ActorContext(){}

    /**
     * getter static per a poder agafar l'instancia del actor context
     * @return
     */
    public static ActorContext getInstance(){
        return actorContext;
    }

    /**
     * metode per poder crear un actor proxy i afegir-lo al hashmap on els guardem tots
     * @param name
     * @param actor
     * @return actorProxy
     */
    public ActorProxy spawnActor(String name, Actor actor){

        ActorProxy actorProxy = new ActorProxy(actor);
        Runner runner = new Runner(actor);
        actorLibrary.put(name,actor);
        actorThreadHashMap.put(actor,runner.getThread());
        MonitorService.getInstance().monitorActor(name);

        return actorProxy;
    }
    /**
     * metode que crea l'actor proxy pero no l'afegeix al actorn context ja que esta fet per provar el funcionament del FirewallDecorator
     * @param name
     * @param actor
     * @return actorProxy
     */
    public ActorProxy spawnActor2(String name, Actor actor){
        ActorProxy actorProxy = new ActorProxy(actor);
        Runner runner = new Runner(actor);
        return actorProxy;
    }

    public ActorProxy lookup(String name){
        return new ActorProxy(actorLibrary.get(name));
    }


    /**
     * getter de la llista per poder obtindre els noms
     * @return llista de strings amb els noms
     */
    public List<String> getNames(){
        return new ArrayList<>(actorLibrary.keySet());
    }

    public HashMap<Actor, Thread> getActorThreadHashMap() {
        return actorThreadHashMap;
    }

    /**
     * getter del hashmap
     * @return actorLibrary
     */

    public HashMap<String, Actor> getActorLibrary() {
        return actorLibrary;
    }
}
