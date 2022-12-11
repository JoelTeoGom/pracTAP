package helloWorld;

import java.util.HashMap;

public class ActorContext {
    private static ActorContext actorContext = new ActorContext();

    private HashMap<String,Actor> actorLibrary = new HashMap<>();
    private ActorContext(){}

    public static ActorContext getInstance(){
        return actorContext;
    }

    public ActorProxy spawnActor(String name, Actor actor){
        ActorProxy proxy = new ActorProxy();

        return new ActorProxy();
    }

    public ActorProxy lookup(String name){


        return new ActorProxy();
    }

    public void getNames(){

    }





}
