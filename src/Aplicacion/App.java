package Aplicacion;

import Estructura.Actor;
import Estructura.ActorContext;
import Estructura.ActorProxy;
import HelloWorld.HelloWorldActor;
import HelloWorld.HelloWorldMessage;
import Message.*;


public class App {
    public static void main(String[] args) {
        ActorProxy x = ActorContext.getInstance().spawnActor("Mundo", new HelloWorldActor());
        x.send(new HelloWorldMessage(null,"HOLA MUNDO"));


//
//        ActorProxy proxy2 = ActorContext.getInstance().spawnActor("Actor2", new Actor());
//        ActorProxy proxy1 = ActorContext.getInstance().spawnActor("Actor1", new Actor());
//
//        System.out.println(ActorContext.getInstance().getNames().toString());
//        ActorProxy proxy3 = ActorContext.getInstance().lookup("Actor1");
//
//        if(proxy3==proxy1)
//            System.out.println("son el mismoooo!!!!");
//
////        System.out.println(proxy1.getSourceActor());
//
//
//        //proxy1.send(new HelloWorldMessage(null,"Hello World from ACTOR 1"));
//
//        proxy2.send(new HelloWorldMessage(proxy1,"Hello World from ACTOR 1"));
//        //desde proxy 1(actor1) le enviamos mensaje a proxy2(actor2)
//
//        proxy2.send(new QuitMessage(proxy1,"bye"));
//
//        System.out.println(ActorContext.getInstance().getNames().toString());



    }

}
