package Estructura;

import Decorator.ActorInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActorContext {
    private static final ActorContext actorContext = new ActorContext();
    private final HashMap<String,Actor> actorLibrary = new HashMap<>();


    private ActorContext(){}
    public static ActorContext getInstance(){
        return actorContext;
    }

    public ActorProxy spawnActor(String name, Actor actor){
        ActorProxy actorProxy = new ActorProxy(actor);
        Runner runner = new Runner(actor);
        this.actorLibrary.put(name,actor);
        return actorProxy;
    }

    public ActorProxy lookup(String name){
        return new ActorProxy(this.actorLibrary.get(name));
    }

    public List<String> getNames(){
        return new ArrayList<>(actorLibrary.keySet());
    }

    public boolean isThere(Actor actor){
       return actorLibrary.containsKey(actor);
    }

    public HashMap<String, Actor> getActorLibrary() {
        return actorLibrary;
    }
}
