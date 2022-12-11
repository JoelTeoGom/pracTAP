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

        return new ActorProxy();
    }

    public Actor lookup(String name){


        return new Actor();
    }

    public void getNames(){

    }





}
