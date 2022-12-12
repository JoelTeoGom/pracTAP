package Aplicacion;

import HelloWorld.Actor;
import HelloWorld.ActorContext;
import HelloWorld.ActorProxy;
import Message.*;


public class App {
    public static void main(String[] args) {

//        ActorProxy proxy2 = ActorContext.getInstance().spawnActor("Actor2", new Actor());
//
//        System.out.println(ActorContext.getInstance().getNames().toString());
//        ActorProxy proxy3 = ActorContext.getInstance().lookup("Actor1");
//
//        if(proxy3==proxy1)
//            System.out.println("son el mismoooo!!!!");

//        System.out.println(proxy1.getSourceActor());

        ActorProxy proxy1 = ActorContext.getInstance().spawnActor("Actor1", new Actor());
        proxy1.send(new HelloWorldMessage(null,"Hello World from ACTOR 1"));


    }

}
