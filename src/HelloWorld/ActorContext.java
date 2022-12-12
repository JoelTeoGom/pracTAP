package HelloWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActorContext {
    private static final ActorContext actorContext = new ActorContext();
    private final HashMap<String,Actor> actorLibrary = new HashMap<>();
    private final HashMap<String,ActorProxy> proxyLibrary = new HashMap<>();

    private ActorContext(){}
    public static ActorContext getInstance(){
        return actorContext;
    }

    public ActorProxy spawnActor(String name, Actor actor){
        ActorProxy actorProxy = new ActorProxy(actor);
        this.actorLibrary.put(name,actor);
        this.proxyLibrary.put(name,actorProxy);
        return actorProxy;
    }

    public ActorProxy lookup(String name){
        return this.proxyLibrary.get(name);
    }

    public List<String> getNames(){
        return new ArrayList<>(actorLibrary.keySet());
    }





}
